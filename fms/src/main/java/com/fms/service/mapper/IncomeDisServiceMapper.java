package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface IncomeDisServiceMapper {
	
	
	/**
	 * 获取所有的产品收益分配相关配置信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllProductDisInfo(Map paramMap);
	
	
	/**
	 * 获取转让产品收益分配相关配置信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProductDisInfo(Map paramMap);
	
	/**
	 * 根据产品获取所有的交易信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllTradeInfoByProductId(Map paramMap);
	
	
	/**
	 * 获取产品收益分配信息记录数量
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryProductIncomeDistibuteListCount(Map paramMap);
	
	/**
	 * 获取产品收益分配信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryProductIncomeDistibuteList(Map paramMap);
	
	/**
	 * 获取产品所有交易收益分配详细信息记录数量
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryProductIncomeDistibuteDetailListCount(Map paramMap);
	
	/**
	 * 获取产品所有交易收益分配详细信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryProductIncomeDistibuteDetailList(Map paramMap);
	
	/**
	 * 下载一个产品在某段区间内所有交易的收益分配信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProductIncomeDistributeDetailInfo(Map paramMap);

	/**
	 * 获取所有未到期收益分配产品相关配置信息
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> getAllNotDueProductDisInfo(Map paramMap);
	
	/**
	 * 获取特定产品的最大日期
	 * @param paramMap
	 * @return
	 */
	public Map getMaxDistributeDate(Map paramMap);


	public List<Map> getTradeInfoByProductId(Map productIncomeDisInfo);
}
