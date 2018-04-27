package com.fms.controller.trade;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ctc.wstx.util.StringUtil;
import com.fms.db.model.TradeFundsShareChange;
import com.fms.db.model.TradeInfo;
import com.fms.service.trade.FundsShareService;
import com.google.gson.Gson;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

import com.sinosoft.util.*;


@Controller
@RequestMapping("/funds")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class FundsShareController {
	
	private static final Logger log = Logger.getLogger(FundsShareController.class);
	
	@Autowired
	private FundsShareService fundsShareService;
	@Autowired
	private CommonService commonService;
	
	/**
	 * 交易状体维护 
	 */
	@RequestMapping(value = "/listTradeFundsUrl", method = RequestMethod.GET)
	public String listTrade(Model model) {
		return "fms/trade/listTradeFunds";
	}
	
	/**
	 * 基金份额受让列表页面
	 * 
	 */
	@RequestMapping(value="/listTradeFundsTransFereeUrl",method=RequestMethod.GET)
	public String listTradeFundsTransFeree(Model model)
	{
	return "fms/trade/listTradeFundsTransFeree";
	}

	/**
	 * 转让审核列表页面 
	 */
	@RequestMapping(value = "/listTransferAuditUrl", method = RequestMethod.GET)
	public String listTransferAudit(Model model) {
		return "fms/trade/listTransferAudit";
	}
	
	/**
	 * 跳转至转让审核界面
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/transferAuditInputUrl", method = RequestMethod.GET)
	public ModelAndView transferAuditInput(@RequestParam("param") String param,ModelMap model) {
		LoginInfo loginInfo=(LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap=(Map<String,String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/transferAuditInput",paramMap);
	}
	/**
	 * 基金份额受让功能页面
	 * 
	 */
	@RequestMapping(value="/transFereeInput",method=RequestMethod.GET)
	public ModelAndView transFereeInput(@RequestParam("param") String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/transFereeInput",reqParamMap);
	}
	/**
	 * 
	 * @param dgm
	 * @param queryParam 
	 * @param model
	 * 根据条件查询交易信息
	 * @return
	 */
	@RequestMapping(value = "/queryFundsList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryFundsList(DataGridModel dgm,String queryParam,ModelMap model) {
		Gson gson = new Gson();
		log.info(gson.toJson(dgm));
		log.info("====queryFundsList param==="+queryParam);
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			dataGrid = fundsShareService.queryFundsList(dgm,paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 
	 * @param dgm
	 * @param queryParam 
	 * @param model
	 * 根据条件查询客户受让信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryFundsTranFereeList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryFundsTranFereeList(DataGridModel dgm,String queryParam,ModelMap model) {
		Gson gson = new Gson();
		log.info(gson.toJson(dgm));
		log.info("====queryFundsTranFereeList param==="+queryParam);
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			dataGrid = fundsShareService.queryFundsTranFereeList(dgm,paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 基本份额受让-跳转至修改界面
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/modifyTradeFundsTransfereeUrl", method = RequestMethod.GET)
	public ModelAndView modifyTradeFundsTransfereeUrl(@RequestParam("param") String param,ModelMap modelMap) {
		//String	paramStr = StringUtils.encodeStr(param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/modifyTradeFundsTransferee",reqParamMap);
	}
	
	/**
	 * 转让审核查询列表
	 * @param dgm
	 * @param queryParam
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryTransferAuditList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryTransferAuditList(DataGridModel dgm,String queryParam,ModelMap model){
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("====FundsShareController层 queryTransferAuditList param==="+queryParam);
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			Map paramMap = new HashMap();
			paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			dataGrid = fundsShareService.queryTransferAuditList(dgm, paramMap, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 
	 * @param dgm
	 * @param queryParam 
	 * @param model
	 * 初始化基金份额受让页面理财经理、产品等信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTradeTransFereeInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getTradeTransFereeInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = fundsShareService.getTradeTransFereeInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	
	/**
	 * 转让审核界面，初始化产品转让金额等信息
	 * @param param
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTransferAuditProInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getTransferAuditProInfo(String param,ModelMap model){
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = fundsShareService.getTransferAuditProInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/submitTransferAudit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> submitTransferAudit(@RequestParam HashMap paramMap,ModelMap model){
		Map resultMap = new HashMap();
		try{
			LoginInfo loginInfo=(LoginInfo)model.get(Constants.USER_INFO_SESSION);
			resultMap=fundsShareService.saveTradeAudit(paramMap,loginInfo);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success","false");
			resultMap.put("msg","操作失败原因是："+e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 提交受让复核
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/saveTradeInput", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveTradeInput(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = fundsShareService.saveTradeInput(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 提交审核
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/saveTradeAudit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveTradeAudit(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = fundsShareService.saveTradeAudit(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 获取更新交易状态页面url
	 */
	@RequestMapping(value = "/updateTradeFunds", method = RequestMethod.GET)
	public ModelAndView updateTradeFunds(@RequestParam("param") String param,Model model) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/updateTradeFunds",reqParamMap);
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
				resultInfo = fundsShareService.getTradeDetailInfo(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取交易详细信息出现异常！");
		}
		return resultInfo;
	}
	

	/**
	 * @param tradeId
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	@RequestMapping(value = "/getTradeTotalAssets")
	@ResponseBody
	public ResultInfo getTradeTotalAssets(String tradeInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (tradeInfoId==null||"".equals(tradeInfoId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易流水号为空，未获取到交易详细信息");
			}else {
				resultInfo = fundsShareService.getTradeTotalAssets(tradeInfoId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取交易详细信息出现异常！");
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/saveFundsShareChangeInfo")
	@ResponseBody
	public ResultInfo saveFundsShareChangeInfo(String param,ModelMap model){
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			if (param==null||"".equals(param)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易信息为空，提交基金份额转让失败！");
			}else {
				String tradeType = JsonUtils.getJsonValueByKey("tradeType", param);
				String tradeInfoId = JsonUtils.getJsonValueByKey("tradeInfoId", param);
				String fundsShareChangeInfoStr = JsonUtils.getJsonValueByKey("fundsShareChangeInfo", param);
				if ("1".equals(tradeType)) {
					TradeFundsShareChange tradeFundsShareChange = (TradeFundsShareChange)JsonUtils.jsonStrToObject(fundsShareChangeInfoStr, TradeFundsShareChange.class);
					tradeFundsShareChange.setOriginTradeInfoId(tradeInfoId);
					resultInfo = fundsShareService.saveFundsShareChangeInfo(tradeFundsShareChange, loginInfo);
				}else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("对不起，仅财富类产品可进行基金份额转移！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("提交基金份额转移出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 * 基金份额转让新增交易基本信息 
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/saveTradeInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveTradeInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			TradeInfo tradeInfo = (TradeInfo)JsonUtils.jsonStrToObject(param, TradeInfo.class);
			resultInfo = fundsShareService.saveTradeInfo(tradeInfo,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 撤销删除基金份额转让信息
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/deleteTradeFundsShareRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo deleteTradeFundsShareRecord(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			if (loginInfo.getUserId() == 1 ) {
				String orTradeId = JsonUtils.getJsonValueByKey("orTradeId", param);
				String tradeInfoId = JsonUtils.getJsonValueByKey("tradeInfoId", param);
				String tradeFundsShareChangeId = JsonUtils.getJsonValueByKey("tradeFundsShareChangeId", param);
				paramMap.put("orTradeId", orTradeId);
				paramMap.put("tradeInfoId", tradeInfoId);
				paramMap.put("tradeFundsShareChangeId", tradeFundsShareChangeId);
				resultInfo = fundsShareService.deleteTradeFundsShareRecord(paramMap,loginInfo);
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("当前用户不可进行撤销操作！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
}
