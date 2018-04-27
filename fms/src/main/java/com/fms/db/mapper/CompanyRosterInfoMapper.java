package com.fms.db.mapper;

import com.fms.db.model.CompanyRosterInfo;
import com.fms.db.model.CompanyRosterInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyRosterInfoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */
	int countByExample(CompanyRosterInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */
	int deleteByExample(CompanyRosterInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */
	int deleteByPrimaryKey(Long companyRosterInfoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */
	int insert(CompanyRosterInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */
	int insertSelective(CompanyRosterInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */List<CompanyRosterInfo> selectByExample(CompanyRosterInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */
	CompanyRosterInfo selectByPrimaryKey(Long companyRosterInfoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */int updateByExampleSelective(@Param("record") CompanyRosterInfo record,@Param("example") CompanyRosterInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */int updateByExample(@Param("record") CompanyRosterInfo record,@Param("example") CompanyRosterInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */
	int updateByPrimaryKeySelective(CompanyRosterInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company_roster_info
	 * @mbggenerated  Tue Sep 13 11:43:33 CST 2016
	 */
	int updateByPrimaryKey(CompanyRosterInfo record);
}