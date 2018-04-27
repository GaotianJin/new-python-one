package com.fms.service.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReportsServiceMapper {
	@SuppressWarnings("rawtypes")
	public Integer queryBusinessManageListCount(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryBusinessManageList(Map paramMap);
	
	/**
	 * 产品设置获取查询符合条件的行数
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer productQueryListCount(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryProductBasicInfo(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getProductCoreInfo(Map paramMap);

	/**
	 * 产品设置页面初始化查询脚本
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> productQueryList(Map queryParam);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getMonthes(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryBusinessManageDetailList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public Integer queryRaisingProductsListCount(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryRaisingProductsList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public Integer querySaleProductsListCount(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> querySaleProductsList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryRaisingProductsDetailList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> querySaleProductsDetailList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public Integer queryRedemptionDetailListCount(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryRedemptionDetailList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryRedemptionDetail(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getYears(Map paramMap);

}
