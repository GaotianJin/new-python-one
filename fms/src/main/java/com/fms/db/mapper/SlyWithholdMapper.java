package com.fms.db.mapper;

import com.fms.db.model.SlyWithhold;
import com.fms.db.model.SlyWithholdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SlyWithholdMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int countByExample(SlyWithholdExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int deleteByExample(SlyWithholdExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int deleteByPrimaryKey(Long slyWithholdId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int insert(SlyWithhold record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int insertSelective(SlyWithhold record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	List<SlyWithhold> selectByExample(SlyWithholdExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	SlyWithhold selectByPrimaryKey(Long slyWithholdId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int updateByExampleSelective(@Param("record") SlyWithhold record, @Param("example") SlyWithholdExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int updateByExample(@Param("record") SlyWithhold record, @Param("example") SlyWithholdExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int updateByPrimaryKeySelective(SlyWithhold record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_withhold
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int updateByPrimaryKey(SlyWithhold record);
}