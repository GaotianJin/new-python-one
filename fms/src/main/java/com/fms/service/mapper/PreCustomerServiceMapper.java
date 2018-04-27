package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface PreCustomerServiceMapper {
	/**
	 * 准客户信息条数查询
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer preCustomerQueryListCount(Map queryParam);

	/**
	 * 准客户信息查询
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> preCustomerQueryListPage(Map queryParam);
	
	
	
	
	/**
	 * 准客户时间访问查询信息查询
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> preCustomerQueryVisitTime(Map queryParam);
	/**
	 * 准客户活动量信息条数查询
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getAllPreCustActivityInfoCount(Map queryParam);
	/**
	 * 准客户活动量信息查询
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllPreCustActivityInfo(Map queryParam);
	
	/**
	 * 导出准客户活动量信息明细
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> preCustActivityDetail(Map queryParam);
	/**
	 * 导出准客户活动量信息明细
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryPreCustActivityScoreManagementDetail(Map queryParam);
	
}
