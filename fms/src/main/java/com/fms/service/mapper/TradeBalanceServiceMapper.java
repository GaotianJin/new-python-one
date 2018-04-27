package com.fms.service.mapper;

import java.util.*;

public interface TradeBalanceServiceMapper {

	@SuppressWarnings("rawtypes")
	public List<Map> getTradeBalanceRiskInfoList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getTradeBalanceWealthInfoList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getTradeBalanceWealthNotFixedInfoList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getOpenDayNetValueList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getNetValueList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Map getBeforeDateActuBalaDateAndNetValue(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryBalanceTradeInfoList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer queryBalanceTradeInfoListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Map getAfterDateBalaNetValue(Map paramMap);
	
}
