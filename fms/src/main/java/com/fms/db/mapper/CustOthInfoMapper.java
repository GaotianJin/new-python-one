package com.fms.db.mapper;

import com.fms.db.model.CustOthInfo;
import com.fms.db.model.CustOthInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustOthInfoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	int countByExample(CustOthInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	int deleteByExample(CustOthInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	int deleteByPrimaryKey(Long custOthInfoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	int insert(CustOthInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	int insertSelective(CustOthInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	List<CustOthInfo> selectByExample(CustOthInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	CustOthInfo selectByPrimaryKey(Long custOthInfoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	int updateByExampleSelective(@Param("record") CustOthInfo record, @Param("example") CustOthInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	int updateByExample(@Param("record") CustOthInfo record, @Param("example") CustOthInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	int updateByPrimaryKeySelective(CustOthInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_oth_info
	 * @mbggenerated  Sat Mar 18 15:20:28 CST 2017
	 */
	int updateByPrimaryKey(CustOthInfo record);
}