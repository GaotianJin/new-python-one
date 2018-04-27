package com.fms.controller.trade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.fms.db.mapper.RedemptionInfoMapper;
import com.fms.db.model.DefPrintInfo;
import com.fms.db.model.RedemptionInfo;
import com.fms.db.model.RedemptionInfoExample;
import com.fms.db.model.RedemptionOperation;
import com.fms.db.model.RedemptionTradeInfo;
import com.fms.service.trade.RedemptionService;
import com.fms.service.trade.TradeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;

@Controller
@RequestMapping("/redemption")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class RedemptionController {
	private static final Logger log = Logger.getLogger(RedemptionController.class);
	@Autowired
	RedemptionService redemptionService;
	@Autowired
	RedemptionInfoMapper redemptionInfoMapper;
	@Autowired
	TradeService tradeService;

	/**
	 * 交易赎回初始页面
	 */
	@RequestMapping(value = "/tradeRedemptionListUrl", method = RequestMethod.GET)
	public String listTradeRedemption(Model model) {
		return "fms/trade/tradeRedemptionList";
	}
	
	/**
	 * 交易赎回操作页面
	 */
	@RequestMapping(value = "/tradeRedemptionAddUrl", method = RequestMethod.GET)
	public ModelAndView addTradeRedemption(@RequestParam("param") String param,ModelMap modelMap) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/addTradeRedemption",reqParamMap);
	}
	
	/**
	 * 赎回确认初始页面
	 */
	@RequestMapping(value = "/redemptionConfirmListUrl", method = RequestMethod.GET)
	public String listRedemptionConfirm(Model model) {
		return "fms/trade/redemptionConfirmList";
	}
	
	/**
	 * 赎回确认操作页面
	 */
	@RequestMapping(value = "/redemptionConfirmAddUrl", method = RequestMethod.GET)
	public ModelAndView addRedemptionConfirm(@RequestParam("param") String param,ModelMap modelMap) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/redemptionConfirmAdd",reqParamMap);
	}
	
	/**
	 * 赎回明细上传初始页面
	 */
	@RequestMapping(value = "/redemptionInfoUploadListUrl", method = RequestMethod.GET)
	public String listRedemptionInfoUpload(Model model) {
		return "fms/trade/redemptionInfoUploadList";
	}
	
	
	/**
	 * 赎回明细上传页面
	 */
	@RequestMapping(value = "/tradeRedemptionUploadDetailUrl", method = RequestMethod.GET)
	public ModelAndView tradeRedemptionUploadDetail(@RequestParam("param") String param,ModelMap modelMap) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/redemptionConfirmAdd",reqParamMap);
	}
	
	
	
	/**
	 * 赎回明细上传操作页面
	 */
	@RequestMapping(value = "/redemptionInfoUploadUrl", method = RequestMethod.GET)
	public String redemptionInfoUpload(Model model) {
		return "fms/trade/redemptionInfoUpload";
	}
	
	/**
	 * 根据权限查询客户下拉信息
	 */
	
	@RequestMapping(value = "/redemtionCustomerQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String redemtionCustomerQuery(ModelMap model){
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		List<Map<String, String>> codeMapList = redemptionService.redemptionCustomerQuery(loginInfo);
		String customerMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return customerMapJson;
	}
	/**
	 * 查询可以赎回的产品名称
	 */
	@RequestMapping(value = "/redemtionProductQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String redemtionProductQuery(){
		List<Map<String, String>> codeMapList = redemptionService.redemptionProductQuery();
		String customerMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return customerMapJson;
	}
	/**
	 * 根据客户姓名和产品名称查询交易信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryTradeInfoByCustandPro", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryTradeInfoByCustandPro(String custNo, String productId) {

		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		paramMap.put("custNo", custNo);
		paramMap.put("productId", productId);
		if (custNo != null && productId != null) {
			resultInfo = redemptionService.queryTradeInfoByCustandPro(paramMap);
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户号或者产品信息为空");
		}
		return resultInfo;
	}
	
	/***
	 * 根据产品去查询相应的所有开放日
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/redemtionExpectOpenDayQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String redemtionExpectOpenDayQuery(String productId){
		Map paramMap=new HashMap ();
		paramMap.put("productId", productId);
		List<Map<String, String>> codeMapList = redemptionService.redemptionExpectOpenDayQuery(paramMap);
		String openDateMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return openDateMapJson;
	}
	
	/**
	 * 根据选择的开放日查询出相应的净值信息(有费后的显示费后,没有费后的显示费前)
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryNetValueByOpenDay", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryNetValueByOpenDay(String openDay,String productId) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		paramMap.put("openDay", openDay);
		paramMap.put("productId", productId);
		if (openDay != null && openDay != null) {
			resultInfo = redemptionService.queryNetValueByOpenDay(paramMap);
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取开放日信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 保存相应的赎回信息
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveRedemptionTradeInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveRedemptionTradeInfo(String param,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		String productId=JsonUtils.getJsonValueByKey("productId", param);
		String custNo=JsonUtils.getJsonValueByKey("custNo", param);
		String operateFlag=JsonUtils.getJsonValueByKey("operateFlag", param);
		String redemptionInfoId=JsonUtils.getJsonValueByKey("redemptionInfoId", param);
		if (custNo == null || custNo == "") {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回信息的客户号为空");
			return resultInfo;
		}
		if (productId==null||productId=="") {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回信息的产品Id为空");
			return resultInfo;
		}
		if (operateFlag==null||operateFlag=="") {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回信息的操作为空");
			return resultInfo;
		}
		
	    //新增的时候，校验当前的这个客户的这款产品是否已经存在正在赎回的记录，如存在，则不允许进行再次申请赎回
		if (operateFlag.equals("addTradeRedemption")) {
			RedemptionInfoExample  redemptionInfoExample=new RedemptionInfoExample();
			redemptionInfoExample.createCriteria().andRcStateEqualTo("E")
								.andCustomerNoEqualTo(custNo)
								.andPdProductIdEqualTo(new Long(productId))
								.andRedemptionStatusNotEqualTo("01")
								.andRedemptionStatusNotEqualTo("04")
								.andRedemptionStatusNotEqualTo("05");
			List<RedemptionInfo> redemptionInfos= redemptionInfoMapper.selectByExample(redemptionInfoExample);
			if (redemptionInfos.size()>0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该客户的该产品已经存在正在赎回处理的申请,所以无法再次申请赎回操作");
				return resultInfo;
			}
		}
		
	    LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String redemptionRefenceInfo=JsonUtils.getJsonValueByKey("redemptionRefenceInfo", param);
		RedemptionInfo redemptionRefenceInfoMain = gson.fromJson(redemptionRefenceInfo,RedemptionInfo.class);
		
		String redemptionTradeInfo=JsonUtils.getJsonValueByKey("redemptionTradeInfo", param);
		RedemptionInfo redemptionRefenceInfoSec = gson.fromJson(redemptionTradeInfo,RedemptionInfo.class);
		
		String custAllTradeTableInfo=JsonUtils.getJsonValueByKey("custAllTradeTableInfo", param);
		List<RedemptionTradeInfo> redemptionTradeInfoList = JsonUtils.jsonArrStrToList(custAllTradeTableInfo, RedemptionTradeInfo.class);
		
		HashMap paramMap=new HashMap();
		paramMap.put("redemptionRefenceInfo", redemptionRefenceInfoMain);
		paramMap.put("redemptionTradeInfo", redemptionRefenceInfoSec);
		paramMap.put("custAllTradeTableInfo", redemptionTradeInfoList);
		paramMap.put("productId", productId);
		paramMap.put("custNo", custNo);
		paramMap.put("redemptionInfoId", redemptionInfoId);
		//将组装好的信息进行存储
		if (operateFlag.equals("addTradeRedemption")) {
			
			resultInfo=redemptionService.addRedemptionInfo(paramMap,loginInfo);//赎回信息新增
		}
		else{
//			String redemptionInfoId=JsonUtils.getJsonValueByKey("redemptionInfoId", param);//获得修改的赎回Id
			
			resultInfo=redemptionService.updateRedemptionInfo(paramMap,loginInfo);//赎回信息修改
		}
		return resultInfo;
	}
	
	
	/**
	 * 生成赎回单
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/printApplicationForm", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo printApplicationForm(String redemptionInfoId,HttpServletRequest request, ModelMap modelMap) {
		
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		String uploadDir = request.getSession().getServletContext().getRealPath("/");
		Map map = new HashMap();
		map.put("redemptionInfoId", redemptionInfoId);
		if (redemptionInfoId != null) {
			resultInfo = redemptionService.printApplicationForm(map, uploadDir,loginInfo);
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("赎回信息Id获取失败，无法生成赎回申请单");
		}
		return resultInfo;
	}
	
	
	/**
	 * 下载赎回单
	 */
	@RequestMapping(value = "/downApplicationForm", method = RequestMethod.GET)
	public ModelAndView listApplyOfPrint(@RequestParam("param") String param) {
		
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/downRedemptionForm",reqParamMap);
	}
	
	/**
	 * 赎回申请单下载初始页面
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/redemptionPrintList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid redemptionPrintList(DataGridModel dgm,String param){
		DataGrid dataGrid = new DataGrid();
		try {
			Map paramMap = new HashMap();
			if(param!=null&&!"".equals(param)){
				paramMap.put("redemptionInfoId", param);
			}
			dataGrid=redemptionService.getRedemptionPrintList(dgm, paramMap);
		} catch (Exception e) {
		}
		return dataGrid;
	}
	
	/**
	 * 赎回申请单下载动作
	 */
	@RequestMapping(value = "/downloadPrint", method = RequestMethod.POST)
	public void downloadPrint(HttpServletRequest request,HttpServletResponse response,@RequestParam("param") String param, ModelMap modelMap){
		try {
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			param = StringUtils.encodeStr(param);
			DefPrintInfo defPrintInfo = (DefPrintInfo)JsonUtils.jsonStrToObject(param, DefPrintInfo.class);
			tradeService.downloadPrint(request, response, defPrintInfo,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * 查询赎回信息列表
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryListTradeRedemption", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryListTradeRedemption(DataGridModel dgm, String param, ModelMap modelMap) {
		Map paramMap = new HashMap();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		DataGrid dataGrid = new DataGrid();
		try {
			paramMap = (Map) JsonUtils.jsonStrToObject(param, paramMap);
			dataGrid = redemptionService.getTradeRedemptionPageList(dgm, paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 赎回撤销
	 */
	
	
	@SuppressWarnings({ "rawtypes", "unchecked"  })
	@RequestMapping(value = "/delTradeRedemption", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo delTradeRedemption(String redemptionInfoId,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		Map paramMap = new HashMap();
		if (redemptionInfoId != null ) {
			paramMap.put("redemptionInfoId", redemptionInfoId);
			resultInfo = redemptionService.cancelTradeRedemption(paramMap,loginInfo);
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回撤销的Id为空");
		}
		return resultInfo;
	}
	
	/**
	 * 交易赎回修改页面初始获取值
	 */
	@RequestMapping(value = "/getTradeRedemptionInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getTradeRedemptionInfo(String queryParams,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + queryParams);
			if(queryParams==null||"".equals(queryParams)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请求数据为空");
				return resultInfo;
			}
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取赎回信息
			String redemptionInfoId= JsonUtils.getJsonValueByKey("redemptionInfoId", queryParams);
			resultInfo = redemptionService.getTradeRedemptionInfo(redemptionInfoId, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户详细信息出错");
		}
		return resultInfo;
	}
	/**
	 * 提交确认将赎回提交至复核岗
	 */

	@RequestMapping(value = "/commitRedemptionCheck",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo commitRedemptionCheck(String redemptionInfoId,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据redemptionInfoId===" + redemptionInfoId);
			if(redemptionInfoId==null||"".equals(redemptionInfoId)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("redemptionInfoId为空");
				return resultInfo;
			}
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取赎回信息
			resultInfo = redemptionService.commitRedemptionCheck(redemptionInfoId, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户详细信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 查询赎回确认初始页面列表
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryRedemptionConfirmList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryRedemptionConfirmList(DataGridModel dgm, String param, ModelMap modelMap) {
		Map paramMap = new HashMap();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		DataGrid dataGrid = new DataGrid();
		try {
			paramMap = (Map) JsonUtils.jsonStrToObject(param, paramMap);
			dataGrid = redemptionService.queryRedemptionConfirmList(dgm, paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 赎回确认页面获取值
	 */
	@RequestMapping(value = "/getRedemptionConfirmInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getRedemptionConfirmInfo(String queryParams,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + queryParams);
			if(queryParams==null||"".equals(queryParams)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请求数据为空");
				return resultInfo;
			}
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取赎回信息
			String redemptionInfoId= JsonUtils.getJsonValueByKey("redemptionInfoId", queryParams);
			resultInfo = redemptionService.getRedemptionConfirmInfo(redemptionInfoId, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户详细信息出错");
		}
		return resultInfo;
	}
	/**
	 * 保存赎回确认页面信息及结论
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveRedemptionConfirmCheckInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveRedemptionConfirmCheckInfo(String param,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		String redemptionInfoId = JsonUtils.getJsonValueByKey("redemptionInfoId", param);
		String redemptionOperationId = JsonUtils.getJsonValueByKey("redemptionOperationId", param);
		if (redemptionInfoId == null || redemptionInfoId == "") {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回信息的流水号为空，无法提交赎回确认结论");
			return resultInfo;
		}
	    LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String redemptionConfirmCheckInfo=JsonUtils.getJsonValueByKey("redemptionConfirmCheckInfo", param);
		RedemptionOperation redemptionOperation = gson.fromJson(redemptionConfirmCheckInfo,RedemptionOperation.class);
		HashMap paramMap=new HashMap();
		paramMap.put("redemptionConfirmCheckInfo", redemptionOperation);
		paramMap.put("redemptionInfoId", redemptionInfoId);
		paramMap.put("redemptionOperationId", redemptionOperationId);
		resultInfo=redemptionService.saveRedemptionConfirmCheckInfo(paramMap,loginInfo);//赎回信息修改
		return resultInfo;
	}
	
	/**
	 * 查询赎回确认初始页面列表
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryRedemptionUploadInfoList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryRedemptionUploadInfoList(DataGridModel dgm, String param, ModelMap modelMap) {
		Map paramMap = new HashMap();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		DataGrid dataGrid = new DataGrid();
		try {
			paramMap = (Map) JsonUtils.jsonStrToObject(param, paramMap);
			dataGrid = redemptionService.queryRedemptionUploadInfoList(dgm, paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 赎回明细上传操作页面
	 */
	@RequestMapping(value = "/redemptionUploadInfoAddUrl", method = RequestMethod.GET)
	public ModelAndView redemptionUploadInfoAdd(@RequestParam("param") String param,ModelMap modelMap) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/redemptionInfoUploadAdd",reqParamMap);
	}
	
	
	@RequestMapping(value = "/getRedemptionUploadInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getRedemptionUploadInfo(String queryParams,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + queryParams);
			if(queryParams==null||"".equals(queryParams)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请求数据为空");
				return resultInfo;
			}
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取赎回信息
			String redemptionInfoId= JsonUtils.getJsonValueByKey("redemptionInfoId", queryParams);
			resultInfo = redemptionService.getRedemptionUploadInfo(redemptionInfoId, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户详细信息出错");
		}
		return resultInfo;
	}
	
	//保存赎回明细上传信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveRedemptionUploadInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveRedemptionUploadInfo(String param,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		String redemptionInfoId=JsonUtils.getJsonValueByKey("redemptionInfoId", param);
		String redemptionOperationId = JsonUtils.getJsonValueByKey("redemptionOperationId", param);
		if (redemptionInfoId == null || redemptionInfoId == "") {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回信息的流水号为空，无法提交赎回明细信息");
			return resultInfo;
		}
	    LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

	    
	    //获得赎回明细上传结论信息
		String redemptionUploadConclusionInfo=JsonUtils.getJsonValueByKey("redemptionUploadConclusionInfo", param);
		RedemptionOperation redemptionOperationConclusion = gson.fromJson(redemptionUploadConclusionInfo,RedemptionOperation.class);
		//获得赎回明细实际参数信息
		String redemptionUploadActuallyInfo=JsonUtils.getJsonValueByKey("redemptionUploadActuallyInfo", param);
		//RedemptionInfo redemptionInfoActuParam = gson.fromJson(redemptionUploadActuallyInfo,RedemptionInfo.class);
		redemptionUploadActuallyInfo = JsonUtils.decodeUrlParams(redemptionUploadActuallyInfo, "UTF-8");
		RedemptionInfo redemptionInfoActuParam = new RedemptionInfo();
		String actuRedemptionTotalShare = JsonUtils.getJsonValueByKey("actuRedemptionTotalShare", redemptionUploadActuallyInfo);
		String actuRedemptionNetValue = JsonUtils.getJsonValueByKey("actuRedemptionNetValue", redemptionUploadActuallyInfo);
		String actuRedemptionTotalMoney = JsonUtils.getJsonValueByKey("actuRedemptionTotalMoney", redemptionUploadActuallyInfo);
		redemptionInfoActuParam.setActuRedemptionTotalShare(new BigDecimal(actuRedemptionTotalShare.replace(",", "")));
		redemptionInfoActuParam.setActuRedemptionNetValue(new BigDecimal(actuRedemptionNetValue));
		redemptionInfoActuParam.setActuRedemptionTotalMoney(new BigDecimal(actuRedemptionTotalMoney.replace(",", "")));
		//获取赎回交易信息列表
		String custAllTradeTableInfo=JsonUtils.getJsonValueByKey("custAllTradeTableInfo", param);
		List<RedemptionTradeInfo> redemptionTradeInfoList = JsonUtils.jsonArrStrToList(custAllTradeTableInfo, RedemptionTradeInfo.class);
		
//		String custAllTradeTableInfo=JsonUtils.getJsonValueByKey("custAllTradeTableInfo", param);
//		List<RedemptionTradeInfo> redemptionTradeInfoList = JsonUtils.jsonArrStrToList(custAllTradeTableInfo, RedemptionTradeInfo.class);
		
		//获得赎回明细上传实际参数信息
		HashMap paramMap=new HashMap();
		paramMap.put("redemptionOperation", redemptionOperationConclusion);
		paramMap.put("redemptionInfo", redemptionInfoActuParam);
		paramMap.put("redemptionInfoId", redemptionInfoId);
		paramMap.put("redemptionOperationId", redemptionOperationId);
		paramMap.put("redemptionTradeInfoList", redemptionTradeInfoList);
		resultInfo=redemptionService.saveRedemptionUploadInfo(paramMap,loginInfo);//赎回参数信息上传保存
		return resultInfo;
	}
	
	
	
	@RequestMapping(value = "/getActuRedemptionInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getActuRedemptionInfo(String redemptionInfoId){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据redemptionInfoId===" + redemptionInfoId);
			if(redemptionInfoId==null||"".equals(redemptionInfoId)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("redemptionInfoId为空");
				return resultInfo;
			}
			//获取赎回信息
			resultInfo = redemptionService.getActuRedemptionInfo(redemptionInfoId);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取实际赎回总信息出错");
		}
		return resultInfo;
	}
	
	
	/**
	 * 根据客户号查询可以赎回的产品名称
	 */
	@RequestMapping(value = "/redemtionProductQueryByCustNo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String redemtionProductQueryByCustNo(ModelMap modelMap,String custNo){
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		List<Map<String, String>> codeMapList = redemptionService.redemtionProductQueryByCustNo(loginInfo,custNo);
		String customerMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return customerMapJson;
	}
	
	
	@RequestMapping(value = "/getDetailActuRedemptionInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getDetailActuRedemptionInfo(String redemptionInfoId){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据redemptionInfoId===" + redemptionInfoId);
			if(redemptionInfoId==null||"".equals(redemptionInfoId)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("redemptionInfoId为空");
				return resultInfo;
			}
			//获取赎回信息
			resultInfo = redemptionService.getDetailActuRedemptionInfo(redemptionInfoId);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取实际赎回总信息出错");
		}
		return resultInfo;
	}
	
	
	@RequestMapping(value = "/queryCustomerInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryCustomerInfo(String custNo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			if(custNo==null||"".equals(custNo)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("custNo为空");
				return resultInfo;
			}
			//根据客户号查询证件号码
			resultInfo = redemptionService.queryCustomerInfo(custNo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取实际赎回总信息出错");
		}
		return resultInfo;
	}
	
	
	
}

