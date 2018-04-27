package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface TradeStatusServiceMapper {
	
	/**
	 * 认购书信息列表查询（全部）
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getTradeStausTotal(Map queryParam);
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getTradeStausServiceList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Map<String, String> getTradeDetailInfo(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Map<String, String> getLastTradeStatusInfo(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getRiskTradeInfo(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getRiskTradeStatusInfo(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getTradeStatusInfoId(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Map<String, String> getSubscriptionCopies(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List getFoundProductList(Map paramMap);
}
