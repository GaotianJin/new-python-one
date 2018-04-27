package com.fms.controller.customer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fms.db.model.Agent;
import com.fms.db.model.CustAccInfo;
import com.fms.db.model.CustAddressInfo;
import com.fms.db.model.CustAssetInfo;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.CustBelongOperation;
import com.fms.db.model.CustCarInfo;
import com.fms.db.model.CustContactInfo;
import com.fms.db.model.CustFamilyInfo;
import com.fms.db.model.CustHobbyInfo;
import com.fms.db.model.CustHouseInfo;
import com.fms.db.model.CustIncomeInfo;
import com.fms.db.model.CustInvestInfo;
import com.fms.db.model.CustOthInfo;
import com.fms.service.customer.CustomerService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.ReportCreateExcel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.ExcelTool;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;

@Controller
@RequestMapping("/customer")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	private static final Logger log = Logger.getLogger(CustomerController.class);
	
	/**
	 * 获取客户页面url
	 */
	@RequestMapping(value = "/listCustomerUrl", method = RequestMethod.GET)
	public String list() {
		return "fms/customer/listCustomer";
	}
	/**
	 * 获取投资查询页面url
	 */
	@RequestMapping(value = "/listInvestmentInfoUrl", method = RequestMethod.GET)
	public String searchInvest() {
		return "fms/customer/listInvestmentInfo";
	}
	/**
	 * 获取查询客户页面url
	 */
	@RequestMapping(value = "/searchCustomer", method = RequestMethod.GET)
	public String searchCustomer() {
		return "fms/customer/searchCustomer";
	}
	/**
	 * 获取活动量信息
	 * @return
	 */
	@RequestMapping(value = "/listPreCustActivityUrl", method = RequestMethod.GET)
	public String listActivity() {
		return "fms/preCustomer/listPreCustActivity";
	}
	/**
	 * 获取增加用户页面url
	 */
	@RequestMapping(value = "/addCustomerUrl", method = RequestMethod.GET)
	public ModelAndView addCustomerUrl(@RequestParam("param") String param,ModelMap modelMap) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		log.info("==登录信息===");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		reqParamMap.put("rolePivilege", loginInfo.getRolePivilege());
		return new ModelAndView("fms/customer/modifyCustomerBaseInfo",reqParamMap);
	}

	/**`
	 * 获取增加用户页面url
	 */
	@RequestMapping(value = "/modifyCustomerInfoUrl", method = RequestMethod.GET)
	public ModelAndView modifyCustomerInfoUrl(@RequestParam("param") String param,ModelMap modelMap) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		log.info("==登录信息===");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		reqParamMap.put("rolePivilege", loginInfo.getRolePivilege());
		return new ModelAndView("fms/customer/modifyCustomerBaseInfo",reqParamMap);
	}
	/**
	 * 获取更新用户页面urlxtso
	 */
	@RequestMapping(value = "/updateCustomerUrl", method = RequestMethod.GET)
	public ModelAndView updateCustomerUrl(@RequestParam("custBaseInfo") String custBaseInfo) {
		custBaseInfo = StringUtils.encodeStr(custBaseInfo);
		log.info("==请求数据===" + custBaseInfo);
		Map<String, String> reqParamMap = new HashMap<String, String>(); 
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(custBaseInfo);
		return new ModelAndView("fms/customer/modifyCustomerBaseInfo", reqParamMap);
		//return null;
	}
	/**
	 * 保存客户基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saveCustBaseInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveCustomerBaseInfo(String custBaseInfoData,String agentId,
			ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + custBaseInfoData);
			LoginInfo loginInfo = (LoginInfo) modelMap
					.get(Constants.USER_INFO_SESSION);
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(
					custBaseInfoData, custBaseInfo);
			resultInfo = customerService.saveCustomerBaseInfo(custBaseInfo,agentId,
					loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户信息出错");
		}
		return resultInfo;
	}

	/**
	 * 保存客户联系信息和地址信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saveCustContactAndAddressInfo", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResultInfo saveCustomerContactAndAddressInfo(
			String custContactAndAddressInfo, ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + custContactAndAddressInfo);
			// 获取操作信息
			String operate = JsonUtils.getJsonValueByKey("operate",
					custContactAndAddressInfo);
			String agentId = JsonUtils.getJsonValueByKey("agentId", custContactAndAddressInfo);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			// 获取登录用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap
					.get(Constants.USER_INFO_SESSION);
			// 获取客户基本信息
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			String custBaseInfoStr = JsonUtils.getJsonValueByKey(
					"custBaseInfo", custContactAndAddressInfo);
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(
					custBaseInfoStr, custBaseInfo);
			// 获取客户联系信息
			CustContactInfo custContactInfo = new CustContactInfo();
			String custContactInfoStr = JsonUtils.getJsonValueByKey(
					"custContactInfo", custContactAndAddressInfo);
			custContactInfo = (CustContactInfo) JsonUtils.jsonStrToObject(
					custContactInfoStr, custContactInfo);
			// 获取客户地址信息
			String custAddressListStr = JsonUtils.getJsonValueByKey(
					"custAddressList", custContactAndAddressInfo);
			List<CustAddressInfo> custAddressInfoList = JsonUtils
					.jsonArrStrToList(custAddressListStr, CustAddressInfo.class);
			resultInfo = customerService.saveCustContactAndAddressInfo(
					custBaseInfo, custContactInfo, custAddressInfoList,
					loginInfo,agent, operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户信息出错");
			return resultInfo;
		}
		return resultInfo;
	}

	/**
	 * 保存客户账户信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveCustAccInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveCustAccInfo(String custAccInfoParam, ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + custAccInfoParam);
			// 获取操作信息
			String operate = JsonUtils.getJsonValueByKey("operate",
					custAccInfoParam);
			// 获取登录用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap
					.get(Constants.USER_INFO_SESSION);
			String agentId = JsonUtils.getJsonValueByKey("agentId", custAccInfoParam);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			// 获取客户基本信息
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			String custBaseInfoStr = JsonUtils.getJsonValueByKey(
					"custBaseInfo", custAccInfoParam);
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(
					custBaseInfoStr, custBaseInfo);
			// 获取账户信息
			String custAccInfoListStr = JsonUtils.getJsonValueByKey(
					"custAccInfo", custAccInfoParam);
			List<CustAccInfo> custAccInfoList = JsonUtils.jsonArrStrToList(
					custAccInfoListStr, CustAccInfo.class);
			resultInfo = customerService.saveCustAccInfo(custBaseInfo,
					custAccInfoList,agent, loginInfo, operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户信息出错");
		}
		return resultInfo;
	}

	/**
	 * 保存客户个人信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveCustPersonalInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveCustPersonalInfo(String custPersonalInfoData,
			ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + custPersonalInfoData);
			// 获取操作信息
			String operate = JsonUtils.getJsonValueByKey("operate",
					custPersonalInfoData);
			// 获取登录用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap
					.get(Constants.USER_INFO_SESSION);
			String agentId = JsonUtils.getJsonValueByKey("agentId", custPersonalInfoData);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			// 获取客户基本信息
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			String custBaseInfoStr = JsonUtils.getJsonValueByKey(
					"custBaseInfo", custPersonalInfoData);
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(
					custBaseInfoStr, custBaseInfo);
			// 获取客户个人信息
			String custPersonalInfo = JsonUtils.getJsonValueByKey(
					"custPersonalInfo", custPersonalInfoData);
			CustOthInfo custOthInfo = (CustOthInfo) JsonUtils.jsonStrToObject(
					custPersonalInfo, CustOthInfo.class);
			// 获取客户爱好信息
			String custHobbyListStr = JsonUtils.getJsonValueByKey("hobbyInfo",
					custPersonalInfoData);
			List<CustHobbyInfo> custHobbyInfoList = JsonUtils.jsonArrStrToList(
					custHobbyListStr, CustHobbyInfo.class);
			// 获取客户收入来源信息
			String incomeListStr = JsonUtils.getJsonValueByKey("incomeList",
					custPersonalInfoData);
			
			List<CustIncomeInfo> custIncomeInfoList = JsonUtils.jsonArrStrToList(
					incomeListStr, CustIncomeInfo.class);
			// 获取客户资产构成信息
			String assetList = JsonUtils.getJsonValueByKey("assetList",
					custPersonalInfoData);
			List<CustAssetInfo> custAssetInfoList =  JsonUtils.jsonArrStrToList(
					assetList, CustAssetInfo.class);
			//获取客户投资金融产品类型信息
			String investList = JsonUtils.getJsonValueByKey("investList",
					custPersonalInfoData);
			List<CustInvestInfo> custInvestInfoList =  JsonUtils.jsonArrStrToList(
					investList, CustInvestInfo.class);
			
			
			resultInfo = customerService.saveCustPersonalInfo(custBaseInfo,
					custOthInfo, custHobbyInfoList,custIncomeInfoList,
					custAssetInfoList,custInvestInfoList,agent,loginInfo, operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户信息出错");
		}
		return resultInfo;
	}

	
	
	/**
	 * 保存客户财富状况信息信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveCustWealthInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveCustWealthInfo(String custWealthInfoData,
			ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + custWealthInfoData);
			// 获取操作信息
			String operate = JsonUtils.getJsonValueByKey("operate",
					custWealthInfoData);
			// 获取登录用户信息
			LoginInfo loginInfo = (LoginInfo) modelMap
					.get(Constants.USER_INFO_SESSION);
			String agentId = JsonUtils.getJsonValueByKey("agentId", custWealthInfoData);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			// 获取客户基本信息
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			String custBaseInfoStr = JsonUtils.getJsonValueByKey(
					"custBaseInfo", custWealthInfoData);
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(
					custBaseInfoStr, custBaseInfo);
			// 获取客户财富状况信息
			String custPersonalInfo = JsonUtils.getJsonValueByKey(
					"custOthInfo", custWealthInfoData);
			CustOthInfo custOthInfo = (CustOthInfo) JsonUtils.jsonStrToObject(
					custPersonalInfo, CustOthInfo.class);
			// 获取客户收入来源信息
			String incomeListStr = JsonUtils.getJsonValueByKey("incomeList",
					custWealthInfoData);
			
			List<CustIncomeInfo> custIncomeInfoList = JsonUtils.jsonArrStrToList(
					incomeListStr, CustIncomeInfo.class);
			// 获取客户资产构成信息
			String assetList = JsonUtils.getJsonValueByKey("assetList",
					custWealthInfoData);
			List<CustAssetInfo> custAssetInfoList =  JsonUtils.jsonArrStrToList(
					assetList, CustAssetInfo.class);
			//获取客户投资金融产品类型信息
			String investList = JsonUtils.getJsonValueByKey("investList",
					custWealthInfoData);
			List<CustInvestInfo> custInvestInfoList =  JsonUtils.jsonArrStrToList(
					investList, CustInvestInfo.class);

			resultInfo = customerService.saveCustWealthInfo(custBaseInfo, custOthInfo, 
					custIncomeInfoList, custAssetInfoList, custInvestInfoList,agent,loginInfo, operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户个人信息出错");
		}
		return resultInfo;
	}
	
	
	/**
	 * 保存客户家庭信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveCustFamilyInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveCustomerFamilyInfo(String custFamilyInfoData,
			ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + custFamilyInfoData);
			// 获取操作信息
			String operate = JsonUtils.getJsonValueByKey("operate",
								custFamilyInfoData);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap
					.get(Constants.USER_INFO_SESSION);
			String agentId = JsonUtils.getJsonValueByKey("agentId", custFamilyInfoData);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			//客户基本信息
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			String custBaseInfoStr = JsonUtils.getJsonValueByKey(
					"custBaseInfo", custFamilyInfoData);
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(
					custBaseInfoStr, custBaseInfo);
			String familyInfoList = JsonUtils.getJsonValueByKey("familyInfoList",
					custFamilyInfoData);
			List<CustFamilyInfo> custFamilyInfoList =  JsonUtils.jsonArrStrToList(
					familyInfoList, CustFamilyInfo.class);
			resultInfo = customerService.saveCustFamilyInfo(custBaseInfo, 
					custFamilyInfoList, agent,loginInfo, operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户家庭信息出错");
		}
		return resultInfo;
	}
	
	
	/**
	 * 保存客户房产信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveCustHouseInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveCustomerHouseInfo(String custHouseInfoData,
			ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + custHouseInfoData);
			// 获取操作信息
			String operate = JsonUtils.getJsonValueByKey("operate",
					custHouseInfoData);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap
					.get(Constants.USER_INFO_SESSION);
			String agentId = JsonUtils.getJsonValueByKey("agentId", custHouseInfoData);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			//客户基本信息
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			String custBaseInfoStr = JsonUtils.getJsonValueByKey(
					"custBaseInfo", custHouseInfoData);
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(
					custBaseInfoStr, custBaseInfo);
			String houseInfoList = JsonUtils.getJsonValueByKey("houseInfoList",
					custHouseInfoData);
			List<CustHouseInfo> custHouseInfoList =  JsonUtils.jsonArrStrToList(
					houseInfoList, CustHouseInfo.class);
			
			resultInfo = customerService.saveCustHouseInfo(custBaseInfo,
					custHouseInfoList,agent,loginInfo, operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户房产信息出错");
		}
		return resultInfo;
	}
	
	
	/**
	 * 保存客户车辆信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveCustCarInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveCustomerCarInfo(String custCarInfoData,
			ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + custCarInfoData);
			// 获取操作信息
			String operate = JsonUtils.getJsonValueByKey("operate",
					custCarInfoData);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap
					.get(Constants.USER_INFO_SESSION);
			String agentId = JsonUtils.getJsonValueByKey("agentId", custCarInfoData);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			//客户基本信息
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			String custBaseInfoStr = JsonUtils.getJsonValueByKey(
					"custBaseInfo", custCarInfoData);
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(
					custBaseInfoStr, custBaseInfo);
			String carInfoList = JsonUtils.getJsonValueByKey("carInfoList",
					custCarInfoData);
			List<CustCarInfo> custCarInfoList =  JsonUtils.jsonArrStrToList(
					carInfoList, CustCarInfo.class);
			resultInfo = customerService.saveCustCarInfo(custBaseInfo,
					custCarInfoList,agent, loginInfo, operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户车辆信息出错");
		}
		return resultInfo;
	}
	
	
	/**
	 * @param searchParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/searchCustomerInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid searchCustomer(DataGridModel dgm,String searchParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("==请求数据===" + searchParam);
			if(searchParam==null||"".equals(searchParam)){
				return dataGrid;
			}
			searchParam = JsonUtils.decodeUrlParams(searchParam, "utf-8");
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			Map paramMap = new HashMap();
			paramMap = (Map)JsonUtils.jsonStrToObject(searchParam, paramMap);
			dataGrid = customerService.searchCustomerInfo(dgm, paramMap, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * @param dgm
	 * @param searchParam
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getCustDetailInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo searchCustomer(String queryParams,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + queryParams);
			if(queryParams==null||"".equals(queryParams)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请求数据为空");
				return resultInfo;
			}
			//searchParam = JsonUtils.decodeUrlParams(searchParam, "utf-8");
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取操作信息
			//String operate = JsonUtils.getJsonValueByKey("operate", queryParams);
			//获取客户基本信息
			String custBaseInfoStr = JsonUtils.getJsonValueByKey("custBaseInfo", queryParams);
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			custBaseInfo = (CustBaseInfo)JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfo);
			resultInfo = customerService.getCustInfo(custBaseInfo, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户详细信息出错");
		}
		return resultInfo;
	}
	
	
	/**
	 * @param searchParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryCustomerList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryCustomerList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = customerService.queryCustomerList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = customerService.queryCustomerList(dgm, paramMap, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * @param searchParam
	 * 初始化客户基本信息
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryAllCustomerList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryAllCustomerList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = customerService.queryAllCustomerList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = customerService.queryAllCustomerList(dgm, paramMap, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	@RequestMapping(value = "/getCustBaseInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getCustBaseInfo(String queryParams){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + queryParams);
			if(queryParams==null||"".equals(queryParams)){
				return resultInfo;
			}
			queryParams = JsonUtils.decodeUrlParams(queryParams, "utf-8");
			String custBaseInfoStr = JsonUtils.getJsonValueByKey("custBaseInfo", queryParams);
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			custBaseInfo = (CustBaseInfo)JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfo);
			String getType = JsonUtils.getJsonValueByKey("getType", queryParams);
			resultInfo = customerService.getCustBaseInfo(custBaseInfo, getType);
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户基本信息出错");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryCustContactInfoList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryCustContactInfoList(String queryParam,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			paramMap = JsonUtils.jsonStrToMap(queryParam); 
			String operate = JsonUtils.getJsonValueByKey("operate", queryParam);
			resultInfo = customerService.queryCustContactInfoList(paramMap,loginInfo,operate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryCustContactDetailInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryCustContactDetailInfo(String queryParam,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			paramMap = JsonUtils.jsonStrToMap(queryParam); 
			String operate = JsonUtils.getJsonValueByKey("operate", queryParam);
			resultInfo = customerService.queryCustContactDetailInfo(paramMap,loginInfo,operate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/verifyCustomerTradeInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo verifyCustomerTradeInfo(String custBaseInfoId){
		ResultInfo resultInfo = new ResultInfo();
		try {
			if(custBaseInfoId==null||"".equals(custBaseInfoId)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("客户信息为空！");
			}
			log.info("==请求数据===" + custBaseInfoId);
			resultInfo = customerService.verifyCustomerTradeInfo(custBaseInfoId);
		}catch(Exception e){
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("判断当前客户是否有正在处理中的交易出现异常");
		}
		return resultInfo;
	}
	
	
	@RequestMapping(value = "/saveCustAllInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveCustAllInfo(String customerInfo,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("=saveCustAllInfo=请求数据===" + customerInfo);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = customerService.saveCustAllInfo(customerInfo, loginInfo);
		}catch(Exception e){
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户信息出现异常");
		}
		return resultInfo;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getCustByAgentInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getCustByAgentInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			if(param==null||"".equals(param)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户信息时，未获取到财富顾问相关信息");
				return resultInfo;
			}
			log.info("=getCustByAgentInfo=请求数据===" + param);
			//获取用户登录信息			
			Map paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = customerService.getCustByAgentInfo(paramMap);
		}catch(Exception e){
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户信息出现异常");
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/verifyCustBelongAgent",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo verifyCustBelongAgent(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			if(param==null||"".equals(param)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("校验客户归属信息失败，请联系IT运维人员！");
				return resultInfo;
			}
			log.info("=verifyCustBelongAgent=请求数据===" + param);
			String custBaseInfoStr = JsonUtils.getJsonValueByKey("custBaseInfo", param);
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			custBaseInfo = (CustBaseInfo)JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfo);
			String agentId = JsonUtils.getJsonValueByKey("agentId", param);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = customerService.verifyCustBelongAgent(custBaseInfo, agent, loginInfo);
		}catch(Exception e){
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户信息出现异常");
		}
		return resultInfo;
	}
	/*
	@RequestMapping(value = "/createExcel.xls", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView donwloadProductIncomeDis(HttpServletRequest request,HttpServletResponse response){
		
		String path = System.getProperty("fms.webroot");
		path = path+"/WEB-INF/classes/reportTemplate/businessOfDailyReport.xls";
    	
    	//声明一个map
        Map<String, Object> map = new HashMap<String, Object>(); 
        map.put("serialNo", "1");
        map.put("productName", "产品测试");
        map.put("totalOrder", "10000000");
        map.put("totalAccount", "7000000");
        map.put("hzOrder", "3000000");
        map.put("hzAccount", "2000000");
        map.put("jxOrder", "2000000");
        map.put("jxAccount", "1500000");
        
        Map<String, Object> map1 = new HashMap<String, Object>(); 
        map1.put("serialNo", "2");
        map1.put("productName", "产品测试1");
        map1.put("totalOrder", "20000000");
        map1.put("totalAccount", "14000000");
        map1.put("hzOrder", "6000000");
        map1.put("hzAccount", "4000000");
        map1.put("jxOrder", "4000000");
        map1.put("jxAccount", "3000000");
        
        Map<String, Object> map2 = new HashMap<String, Object>();  
        map2.put("serialNo", "3");
        map2.put("productName", "产品测试1");
        map2.put("totalOrder", "20000000");
        map2.put("totalAccount", "14000000");
        map2.put("hzOrder", "6000000");
        map2.put("hzAccount", "4000000");
        map2.put("jxOrder", "4000000");
        map2.put("jxAccount", "3000000");
        
        
        
        Map<String, Object> map3 = new HashMap<String, Object>(); 
        map3.put("serialNo", "1");
        map3.put("productName", "产品测试2");
        map3.put("totalOrder", "22000000");
        map3.put("totalAccount", "14200000");
        map3.put("hzOrder", "6200000");
        map3.put("hzAccount", "4200000");
        map3.put("jxOrder", "4200000");
        map3.put("jxAccount", "3200000");
        
        Map<String, Object> map4 = new HashMap<String, Object>();  
        map4.put("serialNo", "2");
        map4.put("productName", "产品测试3");
        map4.put("totalOrder", "21200000");
        map4.put("totalAccount", "14100000");
        map4.put("hzOrder", "6100000");
        map4.put("hzAccount", "4100000");
        map4.put("jxOrder", "4010000");
        map4.put("jxAccount", "3100000");
        
        
        
        List<Map<String, Object>> listData = new ArrayList<Map<String,Object>>();
        listData.add(map);
        listData.add(map1);
        listData.add(map2);
        
        List<Map<String, Object>> listData1 = new ArrayList<Map<String,Object>>();
        listData1.add(map3);
        listData1.add(map4);
        
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("dateBetween", "2015年11月1日—2015年11月30日");
        datas.put("data", listData);
        datas.put("data1", listData1);
		
		
		
	 	OutputStream out=null;
        BufferedOutputStream bos=null;
        BufferedInputStream bis=null;
        InputStream in=null;
        try{
	        in=ExcelTool.exportExcel(path, datas);
	        bis=new BufferedInputStream(in);
	        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())); 
	        String fileName = "BusinessOfDailyReport"+now+".xls";
	        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));//设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
	        byte[] data=new byte[1024];
	        int bytes=0;
	        out=response.getOutputStream();
	        bos=new BufferedOutputStream(out);
	        while((bytes=bis.read(data, 0, data.length))!=-1){
	            bos.write(data,0,bytes);                                        //写出文件流                                     
	        }
	        bos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                bos.close();
                out.close();
                bis.close(); 
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
		return null;
	}*/
	
	
	////////////////////////////////////////////////////客户归属调整///////////////////////////////////////////////
	
	/**
	 * 获取客户归属页面URL
	 * @return
	 */
	@RequestMapping(value="/listBelongCustomerUrl" ,method=RequestMethod.GET)
	public String custBelongPage(){
		return "fms/customer/listCustomerBelong";
	}
	/**
	 *  获取‘客户归属调整’更新页面
	 * @return
	 */
	@RequestMapping(value = "/updateCustBelongInfoUrl", method = RequestMethod.GET)
	public String updateCustBelong() {
		return "fms/customer/updateCustBelongInfo";
	}
	
	/**
	 * 查询当前客户历史调整信息
	 * 
	 */
	@RequestMapping(value="/queryCustHistoryBelongURL",method=RequestMethod.POST)
	@ResponseBody
	public DataGrid queryCustHistoryBelongInfo(DataGridModel dgm, ModelMap modelMap, String queryParam) {
		log.info("==========================客户历史归属信息查询请求参数:"+queryParam+"===============");
		// 待返回对象
		DataGrid dataGrid = new DataGrid();
		// 异常捕获机制
		try {
			// 存储请求参数
			Map queryMap = new HashMap();
			// 获取用户登录信息
			LoginInfo loginInfo = (LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
			// 请求参数为空
			if (org.apache.commons.lang.StringUtils.isBlank(queryParam)) {
				
			} 
			else 
			{
				// queryParam不为空，queryParam参数解码
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				// queryParam字符串转为Map对象
				queryMap = 	JsonUtils.jsonStrToMap(queryParam);
				// 调用业务层处理业务逻辑
				dataGrid = this.customerService.queryCustHistoryBelongInfo(dgm, queryMap, loginInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dataGrid;
	}
	
	/**
	 * 查询客户归属分页
	 * @param dgm
	 * @param modelMap
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value="/queryCustomerBelongList",method=RequestMethod.POST)
	@ResponseBody
	public DataGrid queryCustomerBelongList(DataGridModel dgm,ModelMap modelMap,String queryParam){
		DataGrid dataGrid = new DataGrid();
		Map queryMap = new  HashMap();
		try {
			log.info("请求参数====="+queryParam+"====");
			//获取用户登陆信息
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(org.apache.commons.lang.StringUtils.isBlank(queryParam)){
				dataGrid = this.customerService.queryCustomerBelongList(dgm, queryMap, loginInfo);
			}else {
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				queryMap = 	JsonUtils.jsonStrToMap(queryParam);
				dataGrid = 	this.customerService.queryCustomerBelongList(dgm, queryMap, loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	} 
	/**
	 * 客户归属详情报表导出
	 * @param queryParam
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/customerBelongDetailTable.xls", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public ModelAndView exportCustBelongExcel(@RequestParam String queryParam,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//1获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			// 2.获取请求参数
			queryParam = StringUtils.encodeStr(queryParam);
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			// 3.调用业务层处理导出
			resultInfo = this.customerService.exportCustBelongExcel(paramMap,loginInfo);
			//生成Excel
			Map<String, Object> model = new HashMap<String, Object>();
			ReportCreateExcel reportCreateExcel = new ReportCreateExcel();
			model.put("reportList", resultInfo.getObj());
			return new ModelAndView(reportCreateExcel,model);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导出客户归属信息出现异常！");
		}
		return null;
	}
	

	//保存操作
			@RequestMapping(value="/saveUpadateAgentInfo",method=RequestMethod.POST)
			@ResponseBody
			public ResultInfo saveUpadateAgentInfo(String param,ModelMap modelMap){
				log.info("=====保存客户归属轨迹请求传入参数:====="+param);
				ResultInfo resultInfo = new ResultInfo();
				Map map = new HashMap();
				try {
					param = java.net.URLDecoder.decode(param, "UTF-8");
					
					LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
					String custBelongInfo = JsonUtils.getJsonValueByKey("custBelongInfo", param);
					String custBaseInfoId = JsonUtils.getJsonValueByKey("custBaseInfoId", param);
					List<CustBelongOperation> custBelongOperation = JsonUtils.jsonArrStrToList(custBelongInfo, CustBelongOperation.class);
					
					resultInfo = this.customerService.saveBelongOperation(custBelongOperation, custBaseInfoId, loginInfo);
					
				} catch (Exception e) {
					e.printStackTrace();
					resultInfo.setSuccess(false);
					resultInfo.setMsg("保存客户归属信息出现异常！");
				}
				return resultInfo;
			
			}
	
	/**
	 *  客户基本信息与客户历史归属信息赋值
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getCustBelongInoUrl",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo getCustBelongInfoByCustBaseInfoId(@RequestParam("param") String param){
		log.info("=============根据客户基本信息流水号获取客户基本信息与历史归属信息请求参数:" + param);
		ResultInfo resultInfo = new ResultInfo();
		Map map = new HashMap();
		try {
			// 若传入客户基本流水号为空，则此次请求结束并返回用户提示信息
			if (param == null || "".equals(param)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户基本信息与归属信息出错");
				return resultInfo;
			}
			// 若传入客户基本信息Id不为空，则根据Id查询客户基本信息与归属信息
			//String custBaseInfoId = JsonUtils.getJsonValueByKey("custBaseInfoId", param);
			//map.put("custBaseInfoId", custBaseInfoId);
			map.put("custBaseInfoId", param);
			resultInfo = this.customerService.getCustBelongInfoByCustBaseInfoId(map);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户基本 信息出错");
		}
		return resultInfo;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 		导出客户基本信息
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/custBaseInfoDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ModelAndView custBaseInfoDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
		// 输出日志，便于调试
		log.info("Controller层类中custBaseInfoDetail()接受参数queryParam:" + queryParam);
		// 获取业务报表导出Excel模板
		String path = System.getProperty("fms.webroot");
		path = path+"/WEB-INF/classes/reportTemplate/custBaseInfoDetail.xls";
		// 分配ResultInfo对象返回程序异常信息
		ResultInfo resultInfo = new ResultInfo();
		// 
		try {
			// 查询参数解码
			queryParam = StringUtils.encodeStr(queryParam);
			// 查询参数转成Map集合
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			// 调用Service层方法统计客户基本信息
			Map  datas = this.customerService.getCustBaseInfoDetail(paramMap);
			// 调用Excel模板生成Excel业务报表
			OutputStream out=null;
	        BufferedOutputStream bos=null;
	        BufferedInputStream bis=null;
	        InputStream in=null;
	        try{
		        in=ExcelTool.exportExcel(path, datas);
		        bis=new BufferedInputStream(in);
		        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
		        String fileName = "custBaseInfoDetail"+now+".xls";
		      //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
		        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		        byte[] data=new byte[1024];
		        int bytes=0;
		        out=response.getOutputStream();
		        bos=new BufferedOutputStream(out);
		        while((bytes=bis.read(data, 0, data.length))!=-1){
		            bos.write(data,0,bytes);                                        //写出文件流                                     
		        }
		        bos.flush();
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            try {
	                bos.close();
	                out.close();
	                bis.close(); 
	                in.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导出客户基本信息表出现异常！");
		}
		return null;
	}
	
	/**
	 * @author wanghao
	 * 客户手机号信息导出
	 */
	@RequestMapping(value = "/listCustomerPhoneUrl", method = RequestMethod.GET)
	public String listCustomerPhone() {
		return "fms/customer/listCustomerPhone";
	}
	
	/**
	 * @author wanghao
	 * 	导出客户手机号基本信息
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/custPhoneInfoDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ModelAndView custPhoneInfoDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
		// 输出日志，便于调试
		log.info("Controller层类中custPhoneInfoDetail()接受参数queryParam:" + queryParam);
		// 获取业务报表导出Excel模板
		String path = System.getProperty("fms.webroot");
		path = path+"/WEB-INF/classes/reportTemplate/custPhoneInfoDetail.xls";
		// 分配ResultInfo对象返回程序异常信息
		ResultInfo resultInfo = new ResultInfo();
		// 
		try {
			// 查询参数解码
			queryParam = StringUtils.encodeStr(queryParam);
			// 查询参数转成Map集合
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			// 调用Service层方法统计客户基本信息
			Map  datas = this.customerService.getCustBaseInfoDetail(paramMap);
			// 调用Excel模板生成Excel业务报表
			OutputStream out=null;
	        BufferedOutputStream bos=null;
	        BufferedInputStream bis=null;
	        InputStream in=null;
	        try{
		        in=ExcelTool.exportExcel(path, datas);
		        bis=new BufferedInputStream(in);
		        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
		        String fileName = "custPhoneInfoDetail"+now+".xls";
		      //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
		        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		        byte[] data=new byte[1024];
		        int bytes=0;
		        out=response.getOutputStream();
		        bos=new BufferedOutputStream(out);
		        while((bytes=bis.read(data, 0, data.length))!=-1){
		            bos.write(data,0,bytes);                                        //写出文件流                                     
		        }
		        bos.flush();
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            try {
	                bos.close();
	                out.close();
	                bis.close(); 
	                in.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导出客户手机号基本信息表出现异常！");
		}
		return null;
	}
	
	/**
	 * 获取客户查询页面url
	 * @author wh
	 */
	@RequestMapping(value = "/listCustomerSearchUrl", method = RequestMethod.GET)
	public String listCustomerSearch() {
		return "fms/customer/listCustomerSearch";
	}
	
	
	/**
	 *  客户升级审核提交复核按钮
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/investCustAudit",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo investCustAudit(String custBaseInfoId,ModelMap modelMap){
		log.info("=============请求参数:" + custBaseInfoId);
		ResultInfo resultInfo = new ResultInfo();
		// 获取用户登录信息
		LoginInfo loginInfo = (LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		try {
			// 若传入客户基本流水号为空，则此次请求结束并返回用户提示信息
			if (custBaseInfoId == null || "".equals(custBaseInfoId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户流水号出错");
				return resultInfo;
			}
			resultInfo = this.customerService.investCustAudit(custBaseInfoId,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("提交复核出错");
		}
		return resultInfo;
	}
	
	/**
	 * 获取客户升级审核列表页面
	 */
	@RequestMapping(value = "/listCustUpgradeAuditUrl", method = RequestMethod.GET)
	public String listCustUpgradeAuditUrl() {
			return "fms/customer/listCustUpgradeAudit";
		}
	
	/**
	 * @param searchParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryCustUpgradeAuditList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryCustUpgradeAuditList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = customerService.queryCustUpgradeAuditList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = customerService.queryCustUpgradeAuditList(dgm, paramMap, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 升级客户提交审核
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/saveCustUpgradeAudit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveCustUpgradeAudit(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = customerService.saveCustUpgradeAudit(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	/**
	 * 客户升级审核退回查询
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/queryCustConclusion",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryCustConclusion(@RequestParam HashMap paramMap) {
		HashMap codeMap = customerService.queryCustConclusion(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMap);
		return codeMapJson;
	}
	
	/**
	 * 统一升级客户等级
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/updateCustInvestGrade", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateCustInvestGrade(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = customerService.updateCustInvestGrade(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 查询客户信息
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/queryCustAndAgentInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryCustAndAgentInfo(@RequestParam HashMap paramMap) {
		Map codeMap = customerService.queryCustAndAgentInfo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMap);
		return codeMapJson;
	}
	
	/**
	 * 客户查询列表 包含存续规模
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryCustomerInvestList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryCustomerInvestList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = customerService.queryCustomerInvestList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = customerService.queryCustomerInvestList(dgm, paramMap, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 客户强制升级
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/custForceUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> custForceUpdate(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = customerService.custForceUpdate(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
}
