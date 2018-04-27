package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface CodeQueryServiceMapper {
	/**
	 * 根据网点和业务部获取理财经理
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAgentList(Map param); 
	/**
	 * 权限控制页面机构
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,String>> getDefComInfo(Map paramMap);
	/**
	 * 权限控制页面网点
	 * @param paramMap
	 * @return
	 */
	/*@SuppressWarnings("rawtypes")
	public List<Map<String,String>> getDefStoreInfo(Map paramMap);*/
	
	/**
	 * 根据comId查询网点信息，级联使用
	 * @author LIWENTAO
	 * @param  
	 * @return
	 * 
	 */
	/*@SuppressWarnings("rawtypes")
	public List<Map<String,String>> getDefStoreInfoByComId(Map paramMap);*/
	/**
	 * 权限控制页面业务部
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,String>> getDefDepartmentInfo(Map paramMap);
	
	/**
	 * 根据comId查询网点信息，级联使用
	 * @author LIWENTAO
	 * @param  
	 * @return
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,String>> getDefDepartmentInfoByComId(Map paramMap);
	/**
	 * 权限控制页面财富顾问
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,String>> getAgentInfoList(Map paramMap);
	/**
	 * 根据理财经理号获取客户姓名
	 * @author ZYM
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, String>> getCustNameByUserId(Map paramMap);
	
	/**
	 * 根据productId获取合同号
	 */
	public List<Map<String, String>> getContractNumberByPdId(Map paramMap);
	
}
