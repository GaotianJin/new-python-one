package com.sinosoft.core.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sinosoft.core.db.mapper.BDCompanyMapper;
import com.sinosoft.core.db.mapper.BDCompanyMappingMapper;
import com.sinosoft.core.db.model.BDCompany;
import com.sinosoft.core.db.model.BDCompanyExample;
import com.sinosoft.core.db.model.BDCompanyMapping;
import com.sinosoft.core.db.model.BDCompanyMappingExample;
import com.sinosoft.core.service.BDCompanyService;
import com.sinosoft.core.service.mapper.BDCompanyServiceMapper;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BDCompanyServiceImpl implements BDCompanyService {
	
	@Autowired
	private BDCompanyServiceMapper bdCompanyServiceMapper;
	@Autowired
	private BDCompanyMapper bdCompanyMapper;
	@Autowired
	private BDCompanyMappingMapper bdCompanyMappingMapper;
	
	public String getCompanyChildrenIDToString(BigDecimal id) {
		StringBuffer stringBuffer = new StringBuffer();
		Map<String, String> paramMap = new HashMap<String, String>();
		List<BDCompany> list = this.getCompanyChildrenID(id);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				stringBuffer.append(list.get(i).getDbid().toString());
				if (i != list.size() - 1) {
					stringBuffer.append(",");
				}
			}
		}
		return stringBuffer.toString();
	}
	
	
	public List<BDCompany> getCompanyChildrenID(BigDecimal id) {
		Map<String, BigDecimal> paramMap = new HashMap<String, BigDecimal>();
		paramMap.put("id", id);
		List<BDCompany> list = bdCompanyServiceMapper.getAllCompanyChildrenByID(paramMap);
		return list;
	} 


	@CacheEvict(value = "companyCache", allEntries = true)
	public void reload() {
	}

	@Override
	public List<BDCompany> getAllCompany() {
		return bdCompanyServiceMapper.getAllCompany();
	}

	@Override
	public BDCompany getCompanyById(Integer id) {
		return bdCompanyMapper.selectByPrimaryKey(new BigDecimal(id));
	}

	@Override
	public String getSubCompanyCode(Integer id) {
		String companyCode = getCompanyById(id).getCompanyCode();
		if (companyCode.length() < 6) {
			return companyCode;
		} else {
			return companyCode.substring(0, 6);
		}
	}

	@Override
	public String getDepartmentCode(Integer id) {
		String companyCode = getCompanyById(id).getCompanyCode();
		if (companyCode.length() < 10) {
			return companyCode;
		} else {
			return companyCode.substring(0, 9);
		}
	}

	@Override
	public BDCompany getCompanyByCode(String companyCode) {
		BDCompanyExample bdCompanyExample = new BDCompanyExample();
		bdCompanyExample.createCriteria().andCompanyCodeEqualTo(companyCode);
		List<BDCompany> resultList = bdCompanyMapper.selectByExample(bdCompanyExample);
		if (resultList!=null&&resultList.size()>0) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public BDCompanyMapping getCompanyMappingByCode(String companyCode) {
		BDCompanyMappingExample bdCompanyMappingExample = new BDCompanyMappingExample();
		bdCompanyMappingExample.createCriteria().andCompanyCodeEqualTo(companyCode);
		List<BDCompanyMapping> resultList = bdCompanyMappingMapper.selectByExample(bdCompanyMappingExample);
		if (resultList!=null&&resultList.size()>0) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public String getUpLevelCompanyCodeByCode(String companyCode) {

		BDCompany company = getCompanyByCode(companyCode);
		if (null == company)
			return null;                                                   
		else
			return company.getUpCompanyCode();
	}


	@Override
	public String getCompanyChildrenIDToString(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<BDCompany> getCompanyChildrenID(Long companyId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
