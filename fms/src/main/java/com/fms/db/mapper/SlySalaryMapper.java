package com.fms.db.mapper;

import com.fms.db.model.SlySalary;
import com.fms.db.model.SlySalaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SlySalaryMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	int countByExample(SlySalaryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	int deleteByExample(SlySalaryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	int deleteByPrimaryKey(Long slySalaryId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	int insert(SlySalary record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	int insertSelective(SlySalary record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */List<SlySalary> selectByExample(SlySalaryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	SlySalary selectByPrimaryKey(Long slySalaryId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */int updateByExampleSelective(@Param("record") SlySalary record,@Param("example") SlySalaryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */int updateByExample(@Param("record") SlySalary record,@Param("example") SlySalaryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	int updateByPrimaryKeySelective(SlySalary record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	int updateByPrimaryKey(SlySalary record);
}