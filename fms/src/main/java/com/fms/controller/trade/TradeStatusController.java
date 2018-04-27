package com.fms.controller.trade;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fms.controller.file.FileUploadController;
import com.fms.db.model.TradeStatusInfo;
import com.fms.service.trade.TradeStatusService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;


@Controller
@RequestMapping("/tradeS")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class TradeStatusController {
	
	private static final Logger log = Logger.getLogger(FileUploadController.class);
	
	@Autowired
	private TradeStatusService tradeStatusService;
	/**
	 * 楼盘录入页面查询楼盘录入信息列表
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/tradeStrus",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryBuildList(DataGridModel dgm,String queryParam,ModelMap model) {
		DataGrid dataGrid = new DataGrid();
		try {
			Log.info("==请求数据===" + queryParam);
			if(queryParam==null||"".equals(queryParam)){
				return dataGrid;
			}
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
			Map paramMap = new HashMap();
			paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			dataGrid = tradeStatusService.queryStausList(dgm, paramMap,loginInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	

	/**
	 * 获取浮动份额上传页面url
	 */
	@RequestMapping(value = "/importFloatCopiesFileUrl", method = RequestMethod.GET)
	public String importFloatCopiesFile(Model model) {
		return "fms/trade/importFloatCopies";
	}
	
	/**
	 * 获取更新交易状态页面url
	 */
	@RequestMapping(value = "/updateTradeStatus", method = RequestMethod.GET)
	public ModelAndView updateTradeStrusCF(@RequestParam("param") String param,Model model) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/updateTradeStatus",reqParamMap);
		//return "fms/trade/updateTradeStatus";
	}
	
	/**
	 * 获取更新楼盘页面url
	 */
	@RequestMapping(value = "/TradeStrusBXUrl", method = RequestMethod.GET)
	public String updateTradeStrusBX(Model model) {
		return "fms/trade/TradeStrusBX";
	}
	
	/**
	 * @param tradeId
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	@RequestMapping(value = "/getTradeDetailInfo")
	@ResponseBody
	public ResultInfo getTradeDetailInfo(String tradeInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (tradeInfoId==null||"".equals(tradeInfoId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易流水号为空，未获取到交易详细信息");
			}else {
				Map paramMap = new HashMap();
				paramMap.put("tradeInfoId", tradeInfoId);
				resultInfo = tradeStatusService.getTradeDetailInfo(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取交易详细信息出现异常！");
		}
		return resultInfo;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveTradeStatus")
	@ResponseBody
	public ResultInfo saveTradeStatusInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			if (param==null||"".equals(param)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易信息为空，更新交易状态失败！");
			}else {
				String tradeType = JsonUtils.getJsonValueByKey("tradeType", param);
				String tradeInfoId = JsonUtils.getJsonValueByKey("tradeInfoId", param);
				String tradeStatusInfoStr = JsonUtils.getJsonValueByKey("tradeStatusInfo", param);
				if ("1".equals(tradeType)) {
					TradeStatusInfo tradeStatusInfo = (TradeStatusInfo)JsonUtils.jsonStrToObject(tradeStatusInfoStr, TradeStatusInfo.class);
					tradeStatusInfo.setTradeInfoId(new Long(tradeInfoId));
					resultInfo = tradeStatusService.saveTradeStatus(tradeStatusInfo, loginInfo);
				}else {
					List<TradeStatusInfo> tradeStatusInfoList = JsonUtils.jsonArrStrToList(tradeStatusInfoStr, TradeStatusInfo.class);
					resultInfo = tradeStatusService.saveTradeStatus(tradeStatusInfoList, loginInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新交易状态出现异常！");
		}
		return resultInfo;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getLastTradeStatusInfo")
	@ResponseBody
	public ResultInfo getLastTradeStatusInfo(String tradeInfoId,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			if(loginInfo==null){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("此次登录已失效，请重新登录！");
				return resultInfo;
			}
			if (tradeInfoId==null||"".equals(tradeInfoId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易信息为空，修改交易状态失败！");
				return resultInfo;
			}
			Map paramMap = new HashMap();
			paramMap.put("tradeInfoId", tradeInfoId);
			resultInfo = tradeStatusService.getLastTradeStatusInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新交易状态出现异常！");
		}
		return resultInfo;
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getRiskTradeInfo")
	@ResponseBody
	public ResultInfo getRiskTradeInfo(String tradeInfoId,String operate,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			if(loginInfo==null){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("此次登录已失效，请重新登录！");
				return resultInfo;
			}
			if (tradeInfoId==null||"".equals(tradeInfoId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易信息为空，修改交易状态失败！");
				return resultInfo;
			}
			Map paramMap = new HashMap();
			paramMap.put("tradeInfoId", tradeInfoId);
			resultInfo = tradeStatusService.getRiskTradeInfo(paramMap,operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新交易状态出现异常！");
		}
		return resultInfo;
	}
	

	@RequestMapping(value = "/importFloatCopiesFile", method = RequestMethod.POST)
	@ResponseBody
	public void importFloatCopiesFile(HttpServletResponse response,
			@RequestParam MultipartFile[] floatCopiesFile,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = tradeStatusService.importFloatCopiesFile(floatCopiesFile, loginInfo);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			System.out.println("JsonUtils.objectToJsonStr(resultInfo)：==="+JsonUtils.objectToJsonStr(resultInfo));
			writer.write(JsonUtils.objectToJsonStr(resultInfo));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("浮动产品份额导入文件出错");
		}
	}
}
