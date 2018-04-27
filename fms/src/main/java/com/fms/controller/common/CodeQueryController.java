package com.fms.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.activemq.leveldb.RecordLog.LogInfo;
import org.apache.activemq.leveldb.replicated.dto.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fms.service.common.CodeQueryService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;

@Controller
@RequestMapping("/codeQuery")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class CodeQueryController {

	@Autowired
	private CodeQueryService codeQueryService;
	
	/**
	 * 根据codeType获取T_Def_Code表中的数据
	 */
	@RequestMapping(value = "/tdCodeQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String codeQuery(String codeType){
		List<Map<String, String>> codeMapList = codeQueryService.tdCodeQuery(codeType);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	/**
	 * 根据param获取T_Def_Code表中的数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/codeQueryByParam", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String codeQueryByParam(String param){
		Map paramMap=new HashMap();
		String codeType=JsonUtils.getJsonValueByKey("codeType", param);
		String businessType=JsonUtils.getJsonValueByKey("businessType", param);
		paramMap.put("codeType", codeType);
		paramMap.put("businessType", businessType);
		List<Map<String, String>> codeMapList = codeQueryService.codeQueryByParam(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	/**
	 * 根据codeType获取T_Def_Code表中的数据
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tdCodeQueryIn", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String codeQuery(String codeType,String codeListStr){
		//String[] codeArray = codeListStr.split(",");
		//List<String> codeList  = new ArrayList<String>(); 
		//codeList = Arrays.asList(codeArray);
		List<String> codeList = JsonUtils.jsonArrStrToList(codeListStr, String.class);
		List<Map<String, String>> codeMapList = codeQueryService.tdCodeQueryIn(codeType,codeList);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/placeCodeQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addressCodeQuery(String placeType,String upPlaceCode){
		List<Map<String, String>> addressMapList = codeQueryService.addressCodeQuery(placeType, upPlaceCode);
		String addressMapJson = JsonUtils.objectToJsonStr(addressMapList);
		return addressMapJson;
	}
	
	/**
	 * 根据codeType获取T_AGENCY_COM表中的数据
	 */
	@RequestMapping(value = "/agencyQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String agencyQuery(){
		List<Map<String, String>> codeMapList = codeQueryService.tAgencyQuery();
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	/**
	 * 根据agentComId获取t_pd_product表中所有的数据
	 */
	@RequestMapping(value = "/productQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String productQuery(String codeType){
		List<Map<String, String>> codeMapList = codeQueryService.tdProductQuery(codeType);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	/**
	 * 根据agentComId获取t_pd_product表中的财富数据
	 */
	@RequestMapping(value = "/productwealthQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String productwealthQuery(String codeType){
		List<Map<String, String>> codeMapList = codeQueryService.productwealthQuery(codeType);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	/**
	 * 只获取t_pd_product表中财富产品的数据
	 */
	@RequestMapping(value = "/wealthproductQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String wealthproductQuery(){
		List<Map<String, String>> codeMapList = codeQueryService.tdwealthProductQuery();
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	/**
	 * 根据合作机构获取固定收益类产品，合作机构为空时获取所有在售的固定收益类产品
	 */
	@RequestMapping(value = "/queryAllFixedIncomeProduct", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryAllFixedIncomeProduct(String agencyComId){
		List<Map<String, String>> codeMapList = codeQueryService.queryAllFixedIncomeProduct(agencyComId);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	/**
	 * 获取t_pd_product表中的数据
	 */
	@RequestMapping(value = "/productQueryAll", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String productQueryAll(){
		List<Map<String, String>> codeMapList = codeQueryService.tdProductQuery();
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	
	/**
	 * 获取t_pd_product表中的数据
	 */
	@RequestMapping(value = "/productQueryByType", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String productQueryByType(String productType,String productSubType){
		List<Map<String, String>> codeMapList = codeQueryService.tdProductQuery(productType,productSubType);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/comQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String comQuery(ModelMap modelMap){
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		List<Map<String, String>> comMapList = codeQueryService.comCodeQuery(loginInfo);
		String comMapJson = JsonUtils.objectToJsonStr(comMapList);
		return comMapJson;
	}
	
	/**
	 * @return
	 */
	/*@RequestMapping(value = "/storeQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String storeQuery(ModelMap modelMap){
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		List<Map<String, String>> storeMapList = codeQueryService.storeCodeQuery(loginInfo);
		String storeMapJson = JsonUtils.objectToJsonStr(storeMapList);
		return storeMapJson;
	}*/
	
	/**
	 * @author LIWENTAO
	 * @param String comId
	 * @return
	 */
	/*@RequestMapping(value = "/storeQueryByComId", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String storeQueryByComId(String comId){
		List<Map<String, String>> storeMapList = codeQueryService.storeCodeQueryByComId(comId);
		String storeMapJson = JsonUtils.objectToJsonStr(storeMapList);
		return storeMapJson;
	}*/
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/storeQueryOnly", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String storeQuery(){
		List<Map<String, String>> storeMapList = codeQueryService.storeCodeQuery();
		String storeMapJson = JsonUtils.objectToJsonStr(storeMapList);
		return storeMapJson;
	}
	
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/departmentQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String departmentQuery(ModelMap modelMap){
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		List<Map<String, String>> departmentMapList = codeQueryService.departmentCodeQuery(loginInfo);
		String departmentMapJson = JsonUtils.objectToJsonStr(departmentMapList);
		return departmentMapJson;
	}
	
	/**
	 * @author LIWENTAO
	 * @param String comId
	 * @return
	 */
	@RequestMapping(value = "/departmentQueryByComId", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String departmentQueryByComId(String comId){
		List<Map<String, String>> departmentMapList = codeQueryService.departmentCodeQueryByComId(comId);
		String departmentMapJson = JsonUtils.objectToJsonStr(departmentMapList);
		return departmentMapJson;
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/protocolQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String protocolQuery(String comId){
		List<Map<String, String>> beloneProtocolMapList = codeQueryService.protocolQuery(comId);
		String frameProtocolMapJson = JsonUtils.objectToJsonStr(beloneProtocolMapList);
		return frameProtocolMapJson;
	}
	
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/agentQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String agentQuery(ModelMap modelMap){
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		List<Map<String, String>> agentQuery = codeQueryService.agentQuery(loginInfo);
		String agentQueryMapJson = JsonUtils.objectToJsonStr(agentQuery);
		return agentQueryMapJson;
	}
	
	

	
	/**
	 * execState 1: 启用状态 0:停用状态 01:全部状态
	 * @return
	 */
	@RequestMapping(value = "/basicLawVersionQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String basicLawVersionQuery(String execState){
		List<Map<String, String>> basicLawVersionMapList = codeQueryService.basicLawVersionQuery(execState);
		String basicLawVersionMapListMapJson = JsonUtils.objectToJsonStr(basicLawVersionMapList);
		return basicLawVersionMapListMapJson;
	}
	
	
	@RequestMapping(value = "/queryBankInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryBankInfo(){
		List<Map<String, String>> bankMapList = codeQueryService.queryBankInfo();
		String bankMapJson = JsonUtils.objectToJsonStr(bankMapList);
		return bankMapJson;
	}
	
	
	@RequestMapping(value = "/queryAgentInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryAgentInfo(String q){
		List<Map<String, String>> agentMapList = codeQueryService.queryAgentInfo(q);
		String agentMapJson = JsonUtils.objectToJsonStr(agentMapList);
		return agentMapJson;
	}
	
	@RequestMapping(value = "/queryAllAgent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryAllAgent(String agentName){
		List<Map<String, String>> agentMapList = codeQueryService.queryAllAgent(agentName);
		String agentMapJson = JsonUtils.objectToJsonStr(agentMapList);
		return agentMapJson;
	}
	/**
	 * 根据网点和团队获取财富顾问
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/limitAgentInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String limitAgentInfo(@RequestParam("param") String param){
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		List<Map<String, String>> agentMapList = codeQueryService.limitAgentInfo(reqParamMap);
		String agentMapJson = JsonUtils.objectToJsonStr(agentMapList);
		return agentMapJson;
	}
	/**
	 * 根据机构获取网点信息
	 * @param codeType
	 * @return
	 */
/*	@RequestMapping(value = "/defStoreQuery",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String defStoreQuery(String codeType){
		List<Map<String, String>> codeMapList = codeQueryService.defStoreQuery(codeType);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}*/
	/**
	 * 根据机构获取团队信息
	 * @param codeType
	 * @return
	 */
	@RequestMapping(value = "/defDepartmentQuery",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String defDepartmentQuery(String codeType){
		List<Map<String, String>> codeMapList = codeQueryService.defDepartmentQuery(codeType);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	@RequestMapping(value = "/defAgentQuery",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String defAgentQuery(String codeType){
		List<Map<String, String>> codeMapList = codeQueryService.defAgentQuery(codeType);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	/**
	 * 查询所有理财经理信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/agentAllQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String agentAllQuery(ModelMap modelMap){
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		List<Map<String, String>> agentQuery = codeQueryService.agentAllQuery(loginInfo);
		String agentQueryMapJson = JsonUtils.objectToJsonStr(agentQuery);
		return agentQueryMapJson;
	}
	
	/**
	 * @author ZYM
	 * @param String agentId
	 * @return
	 */
	@RequestMapping(value = "/customerQueryByUserId", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String storeQueryByComId(String userId){
		List<Map<String, String>> customerMapList = codeQueryService.customerQueryByUserId(userId);
		String customerMapJson = JsonUtils.objectToJsonStr(customerMapList);
		return customerMapJson;
	}
	
	/**
	 * 查询所有合同号
	 */
	@RequestMapping(value = "/contractNumberQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String contractNumberQuery(String productId){
		List<Map<String, String>> numberQuery = codeQueryService.contractNumberAllQuery(productId);
		String contractNumMapJson = JsonUtils.objectToJsonStr(numberQuery);
		return contractNumMapJson;
	}
	
	/**
	 * 产品预约审核中预约状态条件查询下拉框
	 * @author wh
	 */
	@RequestMapping(value = "/tdCodeQueryOrderStatus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String tdCodeQueryOrderStatus(String codeType){
		List<Map<String, String>> codeMapList = codeQueryService.tdCodeQueryOrderStatus(codeType);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
}
