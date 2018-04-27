package com.fms.service.mapper;

import java.util.List;
import java.util.Map;
public interface CooperationServiceMapper {

	/**
	 * 合做机构信息列表查询（全部）
	 */
	@SuppressWarnings("rawtypes")
	public Integer  agencyComListCount(Map queryParam);
	
	/**
	 * 合作机构信息列表查询（全部）
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> agencyComListPage(Map queryParam);
	
	/**
	 * 获取合作机构信息联系人信息列表
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAgencyComContactsInfoList(Map queryParam);
	
	/**
	 * 合作机构协议信息列表查询（全部）
	 */
	@SuppressWarnings("rawtypes")
	public Integer  agencyComProtocolListCount(Map queryParam);
	
	/**
	 * 合作机构协议信息列表查询（全部）
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> agencyComProtocolListPage(Map queryParam);
	
	
	
}
