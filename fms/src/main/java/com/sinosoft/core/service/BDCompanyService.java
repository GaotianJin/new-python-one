package com.sinosoft.core.service;

import java.util.List;

import com.sinosoft.core.db.model.BDCompany;
import com.sinosoft.core.db.model.BDCompanyMapping;

public interface BDCompanyService {
	
	public String getCompanyChildrenIDToString(Long id);
	
	/**
	 * 根据companyId获取所有当前及下级机构，可缓存
	 * 
	 * @param companyId
	 * @return List<BdCompany>
	 */
	public List<BDCompany> getCompanyChildrenID(Long companyId);

	/**
	 * 清空缓存
	 */
	public void reload();

	/**
	 * 获取所有机构
	 * 
	 * @return List<BdCompany>
	 */
	public List<BDCompany> getAllCompany();

	/**
	 * 根据机构ID查询机构
	 * 
	 * @param id
	 * @return BdCompany
	 */
	public BDCompany getCompanyById(Integer id);

	/**
	 * 根据机构取6位分公司编码
	 * 
	 * @param id
	 * @return String
	 */
	public String getSubCompanyCode(Integer id);

	/**
	 * 根据机构取9位城市编码
	 * 
	 * @param id
	 * @return String
	 */
	public String getDepartmentCode(Integer id);

	/**
	 * 根据机构编码获取管理机构
	 * 
	 * @param companyCode
	 * @return BdCompany
	 */
	public BDCompany getCompanyByCode(String companyCode);

	/**
	 * 根据机构编码获取BdCompanyMapping
	 * 
	 * @param companyCode
	 * @return BdCompanyMapping
	 */
	public BDCompanyMapping getCompanyMappingByCode(String companyCode);

	/**
	 * 查询机构的上一级管理机构编码
	 * 
	 * @param companyCode
	 * @return String
	 */

	public String getUpLevelCompanyCodeByCode(String companyCode);
}
