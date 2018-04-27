package com.fms.controller.customer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.parser.MacOsPeterFTPEntryParser;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fms.db.model.CustAccInfo;
import com.fms.db.model.CustAddressInfo;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.CustCarInfo;
import com.fms.db.model.CustContactInfo;
import com.fms.db.model.CustFamilyInfo;
import com.fms.db.model.CustHistoryInvestment;
import com.fms.db.model.CustHobbyInfo;
import com.fms.db.model.CustHouseInfo;
import com.fms.db.model.CustInvestmentSuggest;
import com.fms.db.model.CustOthInfo;
import com.fms.db.model.CustVisitInfo;
import com.fms.service.customer.ModifyCustomerService;
import com.google.zxing.qrcode.decoder.Mode;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

import jcifs.dcerpc.msrpc.netdfs;
import net.sf.jasperreports.components.barbecue.BarcodeProviders.NW7Provider;
import oracle.net.aso.r;
import sun.util.logging.resources.logging_zh_HK;

@Controller
@RequestMapping("/modifyCustomer")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class ModifyCustomerController {
	
	private static final Logger log = Logger.getLogger(ModifyCustomerController.class);
	
	@Autowired
	private ModifyCustomerService modifyCustomerService;
	
	
	@RequestMapping(value = "/modifyCustomerQuestionnaireInfo", method = RequestMethod.GET)
	public ModelAndView modifyCustomerQuestionnaireInfoUrl(@RequestParam("param") String param,ModelMap modelMap) {
		log.info("==客户风控问卷页面请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/customer/modifyCustomerQuestionnaireInfo",reqParamMap);
	}
	
	
	/**
	 * 静态方法：解决Controller层接收url中文参数时出现的乱码问题
	 * @author Administrator
	 */
	public static String EncodedString(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 根据五要素查询客户是否已存在
	 * @author Liwentao
	 * @param  custBaseInfoData
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	/*@RequestMapping(value = "/verifyCustFivElementsUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo  verifyCustFivElements( String custBaseInfoJson, String agentId, ModelMap modelMap ) {
		ResultInfo resultInfo  =  null;
		try   {
			// 请求参数输出到日志
			log.info("浏览器用户提交的客户信息内容为:"+ custBaseInfoJson);
			log.info(  "浏览器用户提交的agentId为:"+agentId);
			// 获取浏览器用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap.get( Constants.USER_INFO_SESSION );
			// 将Map集合传入Service层类
			resultInfo   =    this.modifyCustomerService.verifyCustFiveElements( custBaseInfoJson, agentId, loginInfo );
		}   catch (Exception e)  {
			e.printStackTrace();
			resultInfo.setMsg(  "保存客户信息出错"  );
		}
		return resultInfo;
	}*/
	/**
	 * 验证手机号是否发生冲突
	 * @author Liwentao
	 * @param  custBaseInfoData
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	@RequestMapping(value = "/verifyCustMobileUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo  verifyCustMobile( String custBaseInfoJson, String agentId, ModelMap modelMap ) {
		ResultInfo resultInfo  =  null;
		try   {
			// 请求参数输出到日志
			log.info("浏览器用户提交的客户信息内容为:"+ custBaseInfoJson);
			log.info(  "浏览器用户提交的agentId为:"+agentId);
			// 获取浏览器用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap.get( Constants.USER_INFO_SESSION );
			// 将Map集合传入Service层类
			resultInfo   =    this.modifyCustomerService.verifyCustMobileInfo( custBaseInfoJson, agentId, loginInfo );
		}   catch (Exception e)  {
			e.printStackTrace();
			resultInfo.setMsg(  "保存客户信息出错"  );
		}
		return resultInfo;
	}
	/**
	 * 验证身份证号冲突
	 * @author Liwentao
	 * @param  custBaseInfoData
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	@RequestMapping(value = "/verifyCustIdNoInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo  verifyCustIdNoInfo( String custBaseInfoJson, String agentId, ModelMap modelMap ) {
		ResultInfo resultInfo  =  null;
		try   {
			// 请求参数输出到日志
			log.info("浏览器用户提交的客户信息内容为:"+ custBaseInfoJson);
			log.info(  "浏览器用户提交的agentId为:"+agentId);
			// 获取浏览器用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap.get( Constants.USER_INFO_SESSION );
			// 将Map集合传入Service层类
			resultInfo   =    this.modifyCustomerService.verifyCustIdNoInfo( custBaseInfoJson, agentId, loginInfo );
		}   catch (Exception e)  {
			e.printStackTrace();
			resultInfo.setMsg(  "保存客户信息出错"  );
		}
		return resultInfo;
	}
	/**
	 * 根据手机号与姓名查询客户是否与其他理财经理已录信息冲突
	 * @author Liwentao
	 * @param  custBaseInfoData
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	@RequestMapping(value = "/verifyCustMobileAndNameUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo verifyCustMobileAndName( String custBaseInfoJson, String agentId, ModelMap modelMap ) {
		ResultInfo resultInfo  =  null;
		try   {
			// 请求参数输出到日志
			log.info("浏览器传入的客户信息内容为:"+ custBaseInfoJson);
			log.info(  "浏览器传入的agentId为:"+agentId);
			// 获取浏览器用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap.get( Constants.USER_INFO_SESSION );
			// 将Map集合传入Service层类
			resultInfo   =    modifyCustomerService.verifyCustMobileAndName( custBaseInfoJson, agentId, loginInfo );
		}   catch (Exception e)  {
			e.printStackTrace();
			resultInfo = new ResultInfo();
			resultInfo.setMsg(  "保存客户信息出错"  );
			return  resultInfo;
		}
		return resultInfo;
	}
	/**
	 * 根据手机号与姓名查询客户是否与其他理财经理已录信息冲突
	 * @author Liwentao
	 * @param  custBaseInfoData
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	@RequestMapping(value = "/verifyCustNameUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo verifyCustName( String custBaseInfoJson, String agentId, ModelMap modelMap ) {
		ResultInfo resultInfo  =  null;
		try   {
			// 请求参数输出到日志
			log.info("浏览器传入的客户信息内容为:"+ custBaseInfoJson);
			log.info(  "浏览器传入的agentId为:"+agentId);
			// 获取浏览器用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap.get( Constants.USER_INFO_SESSION );
			// 将Map集合传入Service层类
			resultInfo   =    modifyCustomerService.verifyCustName( custBaseInfoJson, agentId, loginInfo );
		}   catch (Exception e)  {
			e.printStackTrace();
			resultInfo = new ResultInfo();
			resultInfo.setMsg(  "保存客户信息出错"  );
			return  resultInfo;
		}
		return resultInfo;
	}
	/**
	 * 跳转客户拜访记录页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/modifyCustomerVisitorInfoUrl", method = RequestMethod.GET)
	public ModelAndView updateProductCoreInfoUrl(@RequestParam("param") String param) {
		log.info("==客户拜访记录页面请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/customer/modifyCustomerVisitInfo",reqParamMap);
	}
	/**
	 * 跳转客户投资信息页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/modifyCustomerInvestInfoUrl", method = RequestMethod.GET)
	public ModelAndView modifyCustomerInvestInfoUrl(@RequestParam("param") String param) {
		log.info("==客户投资信息页面请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/customer/modifyCustomerInvestInfo",reqParamMap);
	}
	/**
	 * 跳转客户个人信息页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/modifyCustomerPersonalInfoUrl", method = RequestMethod.GET)
	public ModelAndView modifyCustomerPersonalInfo(@RequestParam("param") String param) {
		log.info("==客户个人信息页面请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/customer/modifyCustomerPersonalInfo",reqParamMap);
	}
	/**
	 * 客户基本信息赋值
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getModifyCustomerBaseInfoUrl",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getModifyCustomerBaseInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		Map map = new HashMap();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		log.info("------开始获取客户基本信息:" + param);
		try {
			if (param == null || "".equals(param)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户基本信息出错");
				return resultInfo;
			}
			String custBaseInfoId = JsonUtils.getJsonValueByKey("custBaseInfoId", param);
			String agentId = JsonUtils.getJsonValueByKey("agentId", param);
			map.put("custBaseInfoId", custBaseInfoId);
			map.put("agentId", agentId);
			resultInfo = modifyCustomerService.getModifyCustomerBaseInfo(map,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户基本 信息出错");
		}
		return resultInfo;
	}
	/**
	 * 客户个人信息赋值
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerPersonalInfoUrl",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getModifyCustomerPersonalInfo(@RequestParam("param") String param){
		ResultInfo resultInfo = new ResultInfo();
		log.info("------开始获取客户个人信息:"+param);
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户个人信息出错");
		return resultInfo;
		}
		resultInfo = modifyCustomerService.getModifyCustomerPersonalInfo(param);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户个人信息出错");
		}
		return resultInfo;
	}
	/**
	 * 客户拜访信息赋值
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerVisitInfoUrl",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getModifyCustomerVisitInfo(@RequestParam("param") String param){
		ResultInfo resultInfo = new ResultInfo();
		log.info("------开始获取客户拜访信息:"+param);
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		return resultInfo;
		}
		resultInfo = modifyCustomerService.getModifyCustomerVisitInfo(param);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		}
		return resultInfo;
	}
	/**
	 * 客户投资信息赋值
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerInvestInfoUrl",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getModifyCustomerInvestInfo(@RequestParam("param") String param){
		ResultInfo resultInfo = new ResultInfo();
		log.info("------开始获取客户拜访信息:"+param);
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		return resultInfo;
		}
		resultInfo = modifyCustomerService.getModifyCustomerInvestInfo(param);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 客户投资信息赋值
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerInvestInfo02Url",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getModifyCustomerInvestInfo02(@RequestParam("param") String param){
		ResultInfo resultInfo = new ResultInfo();
		log.info("------开始获取客户拜访信息:"+param);
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		return resultInfo;
		}
		resultInfo = modifyCustomerService.getModifyCustomerInvestInfo02(param);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 客户投资信息赋值
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerInvestInfo03Url",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getModifyCustomerInvestInfo03(@RequestParam("param") String param){
		ResultInfo resultInfo = new ResultInfo();
		log.info("------开始获取客户拜访信息:"+param);
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		return resultInfo;
		}
		resultInfo = modifyCustomerService.getModifyCustomerInvestInfo03(param);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 客户海外投资信息赋值
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerInvestInfo04Url",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getModifyCustomerInvestInfo04(@RequestParam("param") String param){
		ResultInfo resultInfo = new ResultInfo();
		log.info("------开始获取客户拜访信息:"+param);
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		return resultInfo;
		}
		resultInfo = modifyCustomerService.getModifyCustomerInvestInfo04(param);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取客户拜访信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 校验客户基本信息是否与他人准客户/客户冲突
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/checkModifyCustomerBaseInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo checkModifyCustomerBaseInfo(@RequestParam("param") String param, ModelMap modelMap) {
		log.info("****************校验客户基本信息是否与他人准客户/客户冲突********************");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
		paramMap = JsonUtils.jsonStrToMap(param);
		// 将组装好的Map传给后台,进行数据保存
		resultInfo = modifyCustomerService.checkModifyCustomerBaseInfo(paramMap, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 校验客户基本信息是否与自己准客户/客户冲突
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/checkMyModifyCustomerBaseInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo checkMyModifyCustomerBaseInfo(@RequestParam("param") String param, ModelMap modelMap) {
		log.info("****************校验客户基本信息是否与自己准客户/客户冲突********************");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
		paramMap = JsonUtils.jsonStrToMap(param);
		// 将组装好的Map传给后台,进行数据保存
		resultInfo = modifyCustomerService.checkMyModifyCustomerBaseInfo(paramMap, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 更新客户基本信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/updateModifyCustomerBaseInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateModifyCustomerBaseInfo(String modifyCustomerBaseInfoData,String param,
			ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			log.info("==更新客户基本信息请求数据===" + modifyCustomerBaseInfoData+param);
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(modifyCustomerBaseInfoData, custBaseInfo);
			CustContactInfo custContactInfo = new CustContactInfo();
			custContactInfo = (CustContactInfo) JsonUtils.jsonStrToObject(modifyCustomerBaseInfoData, custContactInfo);
			CustOthInfo custOthInfo = new CustOthInfo();
			custOthInfo = (CustOthInfo) JsonUtils.jsonStrToObject(modifyCustomerBaseInfoData, custOthInfo);
			paramMap = JsonUtils.jsonStrToMap(param);
			paramMap.put("custBaseInfo", custBaseInfo);
			paramMap.put("custContactInfo", custContactInfo);
			paramMap.put("custOthInfo", custOthInfo);
			resultInfo = modifyCustomerService.updateModifyCustomerBaseInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户信息出错！");
		}
		return resultInfo;
	}

	/**
	 * 更新客户联系地址信息和账户信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/submitCustomerAddressAndAccInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo submitCustomerAddressAndAccInfo(String param,ModelMap modelMap) {
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map tMap = new HashMap();
		try {
		param = java.net.URLDecoder.decode(param, "UTF-8");
		log.info("更新客户联系地址信息和账户信息前台数据========"+param);
		String custBaseInfoId=JsonUtils.getJsonValueByKey("custBaseInfoId", param);
		tMap.put("custBaseInfoId", custBaseInfoId);
		String agentId = JsonUtils.getJsonValueByKey("agentId", param);
		tMap.put("agentId", agentId);
		String custLevel = JsonUtils.getJsonValueByKey("custLevel", param);
		tMap.put("custLevel", custLevel);
		String custAddressTable = JsonUtils.getJsonValueByKey("custAddressTable", param);
		List<CustAddressInfo> custAddressInfoList = JsonUtils.jsonArrStrToList(custAddressTable, CustAddressInfo.class);
		tMap.put("custAddressInfoList", custAddressInfoList);
		String custAccountTable = JsonUtils.getJsonValueByKey("custAccountTable", param);
		List<CustAccInfo> custAccInfoList = JsonUtils.jsonArrStrToList(custAccountTable, CustAccInfo.class);
	    tMap.put("custAccInfoList", custAccInfoList);
	    resultInfo=modifyCustomerService.submitCustomerAddressAndAccInfo(tMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 更新客户拜访信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/submitAllCustomerVisitInfoUrl",method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo submitAllCustomerVisitInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		Map map = new HashMap();
		try {
			param = java.net.URLDecoder.decode(param, "UTF-8");
			log.info("新增更新客户拜访信息=========="+param);
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			String custBaseInfoId = JsonUtils.getJsonValueByKey("custBaseInfoId", param);
			String agentId = JsonUtils.getJsonValueByKey("agentId", param);
			String custVisitTable = JsonUtils.getJsonValueByKey("custVisitTable", param);
			List<CustVisitInfo> custVisitInfoList = JsonUtils.jsonArrStrToList(custVisitTable, CustVisitInfo.class);
			map.put("custBaseInfoId", custBaseInfoId);
			map.put("custVisitInfoList", custVisitInfoList);
			map.put("agentId", agentId);
			resultInfo = modifyCustomerService.submitAllCustomerVisitInfo(map,loginInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户拜访信息出现异常！");
		}
		return resultInfo;
	}
	/**
	 * 更新客户其他投资信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/submitAllCustomerInvestInfoUrl",method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo submitAllCustomerInvestInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		Map map = new HashMap();
		try {
			param = java.net.URLDecoder.decode(param, "UTF-8");
			log.info("客户其他公司投资信息======="+param);
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			String custBaseInfoId = JsonUtils.getJsonValueByKey("custBaseInfoId", param);
			String custInvestTable = JsonUtils.getJsonValueByKey("custInvestTable", param);
			String agentId = JsonUtils.getJsonValueByKey("agentId", param);
			List<CustHistoryInvestment> custHistoryInvestmentList = JsonUtils.jsonArrStrToList(custInvestTable, CustHistoryInvestment.class);
			map.put("custBaseInfoId", custBaseInfoId);
			map.put("agentId", agentId);
			map.put("custHistoryInvestmentList", custHistoryInvestmentList);
			resultInfo = modifyCustomerService.submitAllCustomerInvestInfo(map,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户其他投资信息出现异常！");
		}
		return resultInfo;
	}
	/**
	 * 增加更新客户个人信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/submitModifyCustomerPersonalInfoUrl",method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo submitModifyCustomerPersonalInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		Map map = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			param = java.net.URLDecoder.decode(param, "UTF-8");
			log.info("客户个人信息========"+param);
			String agentId = JsonUtils.getJsonValueByKey("agentId", param);
			String custBaseInfoId = JsonUtils.getJsonValueByKey("custBaseInfoId", param);
			String custPersonalInfoinvestTable = JsonUtils.getJsonValueByKey("custPersonalInfoinvestTable", param);
			List<CustInvestmentSuggest> custInvestmentSuggestList = JsonUtils.jsonArrStrToList(custPersonalInfoinvestTable, CustInvestmentSuggest.class);
			String custPersonalInfoFamilyTable = JsonUtils.getJsonValueByKey("custPersonalInfoFamilyTable", param);
			List<CustFamilyInfo> custFamilyInfoList = JsonUtils.jsonArrStrToList(custPersonalInfoFamilyTable, CustFamilyInfo.class);
			String custPersonalInfoHouseTable = JsonUtils.getJsonValueByKey("custPersonalInfoHouseTable", param);
			List<CustHouseInfo> custHouseInfoList = JsonUtils.jsonArrStrToList(custPersonalInfoHouseTable, CustHouseInfo.class);
			String custPersonalInfoCarTable = JsonUtils.getJsonValueByKey("custPersonalInfoCarTable", param);
			List<CustCarInfo> custCarInfoList = JsonUtils.jsonArrStrToList(custPersonalInfoCarTable, CustCarInfo.class);
			String customerPersonalWealthInfo = JsonUtils.getJsonValueByKey("customerPersonalWealthInfo", param);
			CustOthInfo custOthWealthInfo = new CustOthInfo();
			custOthWealthInfo = (CustOthInfo) JsonUtils.jsonStrToObject(customerPersonalWealthInfo, custOthWealthInfo);
			String customerPersonalInfo = JsonUtils.getJsonValueByKey("customerPersonalInfo", param);
			CustOthInfo custOthInfo = new CustOthInfo();
			custOthInfo = (CustOthInfo) JsonUtils.jsonStrToObject(customerPersonalInfo, custOthInfo);
			custOthInfo.setAnnualIncome(custOthWealthInfo.getAnnualIncome());
			custOthInfo.setCustAssetEstimate(custOthWealthInfo.getCustAssetEstimate());
			custOthInfo.setCustQuality(custOthWealthInfo.getCustQuality());
			//更新客户兴趣爱好
			String custHobbyList = JsonUtils.getJsonValueByKey("custHobbyList", param);
			List<CustHobbyInfo> custHobbyInfoList = JsonUtils.jsonArrStrToList(custHobbyList, CustHobbyInfo.class);
			map.put("custBaseInfoId", custBaseInfoId);
			map.put("agentId", agentId);
			map.put("custInvestmentSuggestList", custInvestmentSuggestList);
			map.put("custFamilyInfoList", custFamilyInfoList);
			map.put("custHouseInfoList", custHouseInfoList);
			map.put("custCarInfoList", custCarInfoList);
			map.put("custOthInfo", custOthInfo);
			map.put("custHobbyInfoList", custHobbyInfoList);
			resultInfo = modifyCustomerService.submitModifyCustomerPersonalInfo(map,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户其他投资信息出现异常！");
		}
		return resultInfo;
	}

	/**
	 * 根据五要素查询客户是否已存在
	 * @author Liwentao
	 * @param  custBaseInfoData
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	@RequestMapping(value = "/verifyCollisionWithOwnCustUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo  verifyCollisionWithOwnCust( String custBaseInfoJson, String agentId, ModelMap modelMap ) {
		ResultInfo resultInfo  =  new ResultInfo();
		try{
			// 请求参数输出到日志
			log.info("浏览器用户提交的客户信息内容为:"+ custBaseInfoJson);
			log.info(  "浏览器用户提交的agentId为:"+agentId);
			// 获取浏览器用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap.get( Constants.USER_INFO_SESSION );
			// 将页面数据传入Service层类
			resultInfo = this.modifyCustomerService.verifyCollisionWithOwnCust(custBaseInfoJson,agentId,loginInfo);
		}   
		catch (Exception e)  {
			e.printStackTrace();
			resultInfo.setMsg(  "验证您录入的客户基本信息数据时出错"  );
		}
		return resultInfo;
	}

		/**
		 * 根据五要素查询客户是否已存在
		 * @author Liwentao
		 * @param  custBaseInfoData
		 * @param  agentId
		 * @param  modelMap
		 * @return  resultInfo
		 */
		@RequestMapping(value = "/verifyCollisionWithOthAgentCustUrl", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo  verifyCollisionWithOthAgentCust( String custBaseInfoJson, String agentId, ModelMap modelMap ) {
				ResultInfo resultInfo  =  null;
				try   {
					// 请求参数输出到日志
					log.info("浏览器用户提交的客户信息内容为:"+ custBaseInfoJson);
					log.info(  "浏览器用户提交的agentId为:"+agentId);
					// 获取浏览器用户信息
					LoginInfo loginInfo = (LoginInfo) modelMap.get( Constants.USER_INFO_SESSION );
					// 将页面数据传入Service层类
					resultInfo   =    this.modifyCustomerService.verifyCollisionWithOthAgentCust( custBaseInfoJson, agentId, loginInfo );
				}   
				catch (Exception e)  {
					e.printStackTrace();
					resultInfo.setMsg(  "验证您录入的客户基本信息数据时出错"  );
				}
				return resultInfo;
		}
		/**
		 * 根据五要素查询客户是否已存在
		 * @author Liwentao
		 * @param  custBaseInfoData
		 * @param  agentId
		 * @param  modelMap
		 * @return  resultInfo
		 */
		@RequestMapping(value = "/verifyCollisionWithOthPreAgentCustUrl", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo  verifyCollisionWithOthPreAgentCust( String custBaseInfoJson, String agentId, ModelMap modelMap ) {
				ResultInfo resultInfo  =  null;
				try   {
					// 请求参数输出到日志
					log.info("浏览器用户提交的客户信息内容为:"+ custBaseInfoJson);
					log.info(  "浏览器用户提交的agentId为:"+agentId);
					// 获取浏览器用户信息
					LoginInfo loginInfo = (LoginInfo) modelMap.get( Constants.USER_INFO_SESSION );
					// 将页面数据传入Service层类
					resultInfo   =    this.modifyCustomerService.verifyCollisionWithOthPreAgentCust( custBaseInfoJson, agentId, loginInfo );
				}   
				catch (Exception e)  {
					e.printStackTrace();
					resultInfo.setMsg(  "验证您录入的客户基本信息数据时出错"  );
				}
				return resultInfo;
		}
	/**
	 * 校验客户是否保存客户地址和账户信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/checkCustAdressAndAccInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo checkCustAdressAndAccInfo(@RequestParam("param") String param, ModelMap modelMap) {
		log.info("****************校验客户基本信息是否与自己准客户/客户冲突********************");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
		paramMap = JsonUtils.jsonStrToMap(param);
		// 将组装好的Map传给后台,进行数据保存
		resultInfo = modifyCustomerService.checkCustAdressAndAccInfo(paramMap, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value = "/checkTradeInputUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo cheackTradeInput(@RequestParam("param") String param, ModelMap modelMap) {
		log.info("****************校验客户基本信息是否与他人准客户/客户冲突********************");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
		// 将组装好的Map传给后台,进行数据保存
		resultInfo = modifyCustomerService.checkTradeInput(param, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 获取客户其他公司投资总额
	 * @param queryParam
	 * @param operate
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerInvestAmountUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getModifyCustomerInvestAmount(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getModifyCustomerInvestAmount(param);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取客户本公司投资总额
	 * @param queryParam
	 * @param operate
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerMyInvestAmountUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getModifyCustomerMyInvestAmount(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getModifyCustomerMyInvestAmount(param);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	/**
	 * 获取客户本公司投资总额
	 * @param queryParam
	 * @param operate
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerMyInvestAmount02Url", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getModifyCustomerMyInvestAmount02(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getModifyCustomerMyInvestAmount02(param);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取客户本公司投资份额
	 * @param queryParam
	 * @param operate
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerMyInvestShareUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getModifyCustomerMyInvestShare(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getModifyCustomerMyInvestShare(param);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取客户本公司海外投资份额
	 * @param queryParam
	 * @param operate
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerMyInvestShareUrl02", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getModifyCustomerMyInvestShare02(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getModifyCustomerMyInvestShare02(param);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	/**
	 * 获取客户本公司存续金额
	 * @param queryParam
	 * @param operate
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerMyRemainAmountUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getModifyCustomerMyRemainAmount(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getModifyCustomerMyRemainAmount(param);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取客户本公司存续金额
	 * @param queryParam
	 * @param operate
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerMyRemainAmount02Url", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getModifyCustomerMyRemainAmount02(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getModifyCustomerMyRemainAmount02(param);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取客户本公司存续份额
	 * @param queryParam
	 * @param operate
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerMyRemainShareUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getModifyCustomerMyRemainShare(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getModifyCustomerMyRemainShare(param);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取客户本公司海外存续份额
	 * @param queryParam
	 * @param operate
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getModifyCustomerMyRemainShareUrl02", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getModifyCustomerMyRemainShare02(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getModifyCustomerMyRemainShare02(param);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	
	/**
	 * 根据财富顾问流水号查询财富顾问信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchAgentInfo", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid searchAgentInfo(@RequestParam("searchParam") String searchParam, ModelMap modelMap) {
		log.info("****************根据agentId查询财富顾问信息********************");
		DataGrid dataGrid = new DataGrid();
		try {
			Map agentIdMap = JsonUtils.jsonStrToMap(searchParam);
			if (agentIdMap != null) {
				String agentIdStr = (String)agentIdMap.get("agentId");
				// 将agentId传给service层
				dataGrid = modifyCustomerService.searchAgentInfo(agentIdStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	/**
	 * 根据问卷编码获取问卷所有问题
	 * @param questionnaireCode
	 * @return
	 */
	@RequestMapping(value = "/getQuestionnaireQuestion", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getQuestionnaireQuestion(String questionnaireType){
		ResultInfo resultInfo= new ResultInfo();
		try {
			//获取用户登录信息			
			resultInfo = modifyCustomerService.getQuestionnaireQuestion(questionnaireType);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 根据问卷编码获取问卷所有问题
	 * @param questionnaireCode
	 * @return
	 */
	@RequestMapping(value = "/saveQuestionnaireQuestionInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveQuestionnaireQuestionInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(param==null||"".equals(param)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("页面传输问卷数据为空，保存失败！");
				return resultInfo;
			}
			//获取用户登录信息			
			resultInfo = modifyCustomerService.saveQuestionnaireQuestionInfo(param,loginInfo);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 查询客户风控问卷信息
	 * @param custQuestionnaireId
	 * @return
	 */
	@RequestMapping(value = "/getCustQuestionnaireInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getCustQuestionnaireInfo(String custBaseInfoId){
		ResultInfo resultInfo= new ResultInfo();
		try {
			if(custBaseInfoId==null||"".equals(custBaseInfoId)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("客户流水号为空，获取客户风控问卷信息失败！");
				return resultInfo;
			}
			resultInfo = modifyCustomerService.getCustQuestionnaireInfo(Long.parseLong(custBaseInfoId));
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	/**
	 * 风控问卷历史记录
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getHistoryRecord",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid getHistoryRecord(DataGridModel dgm,String custBaseInfoId,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		Map map = new HashMap();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		log.info("------开始获取风控问卷历史记录的参数:" + custBaseInfoId);
		try {
			map.put("custBaseInfoId", custBaseInfoId);
			dataGrid = modifyCustomerService.getHistoryRecord(dgm,map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	/**`
	 * 获取风控问卷历史记录页面url
	 */
	@RequestMapping(value = "/custHistoryRecordInfoUrl", method = RequestMethod.GET)
	public ModelAndView custHistoryRecordInfoUrl(@RequestParam("param") String param,ModelMap modelMap) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		log.info("==登录信息===");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		reqParamMap.put("rolePivilege", loginInfo.getRolePivilege());
		return new ModelAndView("fms/customer/custHistoryRecordInfo",reqParamMap);
	}
	
	/**
	 * 查询客户风控问卷信息
	 * @param custQuestionnaireId
	 * @return
	 */
	@RequestMapping(value = "/getCustHistoryRecordInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getCustHistoryRecordInfo(String custQuestionnaireId){
		ResultInfo resultInfo= new ResultInfo();
		try {
			if(custQuestionnaireId==null||"".equals(custQuestionnaireId)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("风控问卷流水号为空，获取客户风控问卷信息失败！");
				return resultInfo;
			}
			resultInfo = modifyCustomerService.getCustHistoryRecordInfo(Long.parseLong(custQuestionnaireId));
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	/**
	 * 校验准客户 填写专业投资者的人 是否上传过资产证明材料
	 * @param custBaseInfoId
	 * @return
	 */
	@RequestMapping(value = "/checkInvestCustInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String checkInvestCustInfo(@RequestParam String custBaseInfoId) {
		
		String codeMapList = modifyCustomerService.checkInvestCustInfo(custBaseInfoId);
		//String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapList;
	}
	
	/**
	 * 校验客户是否上传身份证复印件
	 * @param custBaseInfoId
	 * @return
	 */
	@RequestMapping(value = "/queryCustIdCardUpload",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryCustIdCardUpload(@RequestParam String custBaseInfoId) {
		
		String codeMapList = modifyCustomerService.queryCustIdCardUpload(custBaseInfoId);
		//String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapList;
	}
}
