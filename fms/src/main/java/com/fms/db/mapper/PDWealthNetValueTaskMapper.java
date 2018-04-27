package com.fms.db.mapper;

import com.fms.db.model.PDWealthNetValueTask;
import com.fms.db.model.PDWealthNetValueTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PDWealthNetValueTaskMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	int countByExample(PDWealthNetValueTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	int deleteByExample(PDWealthNetValueTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	int deleteByPrimaryKey(Long pdWealthNetValueTaskId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	int insert(PDWealthNetValueTask record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	int insertSelective(PDWealthNetValueTask record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	List<PDWealthNetValueTask> selectByExample(PDWealthNetValueTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	PDWealthNetValueTask selectByPrimaryKey(Long pdWealthNetValueTaskId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	int updateByExampleSelective(@Param("record") PDWealthNetValueTask record,
			@Param("example") PDWealthNetValueTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	int updateByExample(@Param("record") PDWealthNetValueTask record,
			@Param("example") PDWealthNetValueTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	int updateByPrimaryKeySelective(PDWealthNetValueTask record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_net_value_task
	 * @mbggenerated  Wed Apr 12 09:40:25 CST 2017
	 */
	int updateByPrimaryKey(PDWealthNetValueTask record);
}