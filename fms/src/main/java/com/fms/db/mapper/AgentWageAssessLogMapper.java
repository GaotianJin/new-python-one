package com.fms.db.mapper;

import com.fms.db.model.AgentWageAssessLog;
import com.fms.db.model.AgentWageAssessLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentWageAssessLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int countByExample(AgentWageAssessLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByExample(AgentWageAssessLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByPrimaryKey(Long logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insert(AgentWageAssessLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insertSelective(AgentWageAssessLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    List<AgentWageAssessLog> selectByExample(AgentWageAssessLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    AgentWageAssessLog selectByPrimaryKey(Long logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExampleSelective(@Param("record") AgentWageAssessLog record, @Param("example") AgentWageAssessLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExample(@Param("record") AgentWageAssessLog record, @Param("example") AgentWageAssessLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKeySelective(AgentWageAssessLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_wage_assess_log
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKey(AgentWageAssessLog record);
}