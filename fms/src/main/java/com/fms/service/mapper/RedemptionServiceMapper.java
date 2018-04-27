package com.fms.service.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RedemptionServiceMapper {
	
	@SuppressWarnings("rawtypes")
	public List<Map>  customerQuery(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public List<Map>  queryTradeInfoByCustandPro(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public List<Map>  redemptionOpenDayQuery(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryNetValueByOpenDay(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public BigDecimal queryAlreadyRedemptionTotalShare(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public BigDecimal querysubscribeTotalShare(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public Integer tradeRedemptionQueryListCount(Map queryParam);

	@SuppressWarnings("rawtypes")
	public List<Map> tradeRedemptionQueryListPage(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getUpdateTradeRedemptionInfo(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getRedemptionCustProInfo(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public Integer queryRedemptionConfirmListCount(Map queryParam);

	@SuppressWarnings("rawtypes")
	public List<Map> queryRedemptionConfirmListPage(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public Integer queryRedemptionUploadInfoListCount(Map queryParam);

	@SuppressWarnings("rawtypes")
	public List<Map> queryRedemptionUploadInfoListPage(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public Map getRedemptionFormInfo(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public Integer printTotal(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public Integer getRedemptionPrintCount(Map queryParam);

	@SuppressWarnings("rawtypes")
	public List<Map> getRedemptionPrintList(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public Map getActuRedemptionInfo(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map>  prodcutBycustNoQuery(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getDetailRedemptionUploadConclusion(Map queryParam);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getDetailRedemptionActuPrem(Map queryParam);
	@SuppressWarnings("rawtypes")
	public Map<String, String> getRedemtionEmailData(Map redemptionInfoId);
	
	
	
}
