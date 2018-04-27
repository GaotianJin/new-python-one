package com.fms.service.mapper;

import java.util.Map;

public interface FeeCalculationServiceMapper {

	@SuppressWarnings("rawtypes")
	public Double getRiskCounterFeeRate(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Double getConsultationServiceFeeRate(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Double getBeforeDateMaxNetValue(Map paramMap);
	
}
