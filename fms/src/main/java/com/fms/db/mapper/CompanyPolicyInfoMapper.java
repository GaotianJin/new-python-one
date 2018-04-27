package com.fms.db.mapper;

import com.fms.db.model.CompanyPolicyInfo;
import com.fms.db.model.CompanyPolicyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyPolicyInfoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int countByExample(CompanyPolicyInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int deleteByExample(CompanyPolicyInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int deleteByPrimaryKey(Long companyPolicyInfoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int insert(CompanyPolicyInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int insertSelective(CompanyPolicyInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	List<CompanyPolicyInfo> selectByExampleWithBLOBs(CompanyPolicyInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	List<CompanyPolicyInfo> selectByExample(CompanyPolicyInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	CompanyPolicyInfo selectByPrimaryKey(Long companyPolicyInfoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int updateByExampleSelective(@Param("record") CompanyPolicyInfo record,
			@Param("example") CompanyPolicyInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int updateByExampleWithBLOBs(@Param("record") CompanyPolicyInfo record,
			@Param("example") CompanyPolicyInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int updateByExample(@Param("record") CompanyPolicyInfo record, @Param("example") CompanyPolicyInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int updateByPrimaryKeySelective(CompanyPolicyInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(CompanyPolicyInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_policy_info
	 * @mbggenerated  Mon Sep 12 15:08:41 CST 2016
	 */
	int updateByPrimaryKey(CompanyPolicyInfo record);
}