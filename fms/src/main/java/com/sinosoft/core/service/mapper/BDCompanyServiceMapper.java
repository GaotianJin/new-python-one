package com.sinosoft.core.service.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.sinosoft.core.db.model.BDCompany;


public interface BDCompanyServiceMapper {
	public List<BDCompany> getAllCompanyChildrenByID(Map<String, BigDecimal> paramMap);
	public List<BDCompany> getAllCompany();
}
