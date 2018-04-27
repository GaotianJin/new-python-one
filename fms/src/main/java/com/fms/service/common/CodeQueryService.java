package com.fms.service.common;

import java.util.*;

import com.sinosoft.util.LoginInfo;

public interface CodeQueryService {
	
	/**
	 * 从T_DEF_CODE表中获取下拉数据
	 * @return
	 */
	public List<Map<String,String>> tdCodeQuery(String codeType);
	
	/**
	 * 从T_DEF_CODE表中获取下拉数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,String>> codeQueryByParam(Map paramMap);
	
	/**
	 * 从T_DEF_CODE表中获取下拉数据
	 * @return
	 */
	public List<Map<String, String>> tdCodeQueryIn(String codeType,List<String> codeList);
	
	/**
	 * 从T_PD_Product表中获取下拉数据
	 * @return
	 */
	public List<Map<String,String>>  tdProductQuery();
	

	/**
	 * 从T_PD_Product表中获取下拉数据
	 * @return
	 */
	public List<Map<String,String>>  tdwealthProductQuery( );
	
	/**
	 * 从T_AGENCY_COM表中获取下拉数据
	 * @return
	 */
	public List<Map<String,String>>  tAgencyQuery();
	
	/**
	 * 根据agentCode从T_PD_Product表中获取合作机构下的产品信息
	 * @return
	 */
	public List<Map<String,String>>  tdProductQuery(String agentComId);
	
	
	/**
	 * 根据agentCode从T_PD_Product表中获取合作机构下的产品信息
	 * @return
	 */
	public List<Map<String,String>>  productwealthQuery(String agentComId);
	
	/**
	 * 根据产品类别，产品子类获取产品代码
	 * @return
	 */
	public List<Map<String,String>> tdProductQuery(String productType,String productSubType);

	
	/**
	 * 从T_DEF_ADDRESS表中获取下拉数据
	 * @return
	 */
	public List<Map<String,String>> addressCodeQuery(String placeType,String upPlaceCode);
	
	/**
	 * 从T_DEF_COM表中获取下拉数据
	 * @return
	 */
	public List<Map<String,String>> comCodeQuery(LoginInfo loginInfo);
	
	/**
	 * 从T_DEF_STORE表中获取下拉数据
	 * @return
	 */
	/*public List<Map<String,String>> storeCodeQuery(LoginInfo loginInfo);*/
	
	/**
	 * 根据comId查询网点信息，级联使用
	 * @author LIWENTAO
	 * @param  
	 * @return
	 * 
	 */
	/*public List<Map<String,String>> storeCodeQueryByComId(String comId);*/
	
	/**
	 * 从T_DEF_DEPARTMENT表中获取下拉数据
	 * @return
	 */
	public List<Map<String,String>> departmentCodeQuery(LoginInfo loginInfo);
	
	/**
	 * 根据comId查询业务部信息，级联使用
	 * @author LIWENTAO
	 * @param  
	 * @return
	 * 
	 */
	public List<Map<String,String>> departmentCodeQueryByComId(String comId);
	
	/**
	 * 从T_AGENCY_PROTOCOL表中获取框架协议信息
	 * @return
	 */
	public List<Map<String,String>> protocolQuery(String comId);
	
	
	
	/**
	 * 从T_BASIC_LAW表中获取下拉数据
	 * @return
	 */
	public List<Map<String,String>> basicLawVersionQuery(String execState);
	
	
	/**从T_DEF_BANK中获取银行数据
	 * @return
	 */
	public List<Map<String, String>> queryBankInfo();
	
	
	/**从T_DEF_Store中获取网点数据
	 * @return
	 */
	public List<Map<String, String>> storeCodeQuery();
	
	
	/**从T_agent中获取理财经理数据
	 * @return
	 */
	public List<Map<String, String>> agentQuery(LoginInfo loginInfo);
	
	/**获取固定收益类产品
	 * @return
	 */
	public List<Map<String, String>> queryAllFixedIncomeProduct(String agencyComId);
	
	/**
	 * 获取财富顾问信息
	 * @param agentName
	 * @return
	 */
	public List<Map<String, String>> queryAgentInfo(String agentName);
	
	/**
	 * 获取财富顾问信息
	 * @param agentName
	 * @return
	 */
	public List<Map<String, String>> queryAllAgent(String agentName);
	/**
	 * 根据机构获取网点信息
	 * @param codeType
	 * @return
	 */
	/*public List<Map<String, String>> defStoreQuery(String codeType);*/
	/**
	 * 根据机构获取业务部信息
	 * @param codeType
	 * @return
	 */
	public List<Map<String, String>> defDepartmentQuery(String codeType);
	/**
	 * 根据业务部和网点获取理财经理信息
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> limitAgentInfo(Map param);
	/**
	 * 根据机构获取理财经理信息
	 * @param codeType
	 * @return
	 */
	public List<Map<String, String>> defAgentQuery(String codeType);

	/**
	 * 查询所有理财经理信息
	 * @param loginInfo
	 * @return
	 */
	public List<Map<String, String>> agentAllQuery(LoginInfo loginInfo);
	/**
	 * 根据理财经理ID查询客户
	 * @param userId
	 * @author ZYM
	 * @return
	 */
	public List<Map<String, String>> customerQueryByUserId(String userId);
	
	/**
	 * 查询所有合同号
	 */
	public List<Map<String, String>> contractNumberAllQuery(String productId);
	

	/**
	 * 产品预约审核中预约状态条件查询下拉框
	 * @author wh
	 */
	public List<Map<String,String>> tdCodeQueryOrderStatus(String codeType);
 }
