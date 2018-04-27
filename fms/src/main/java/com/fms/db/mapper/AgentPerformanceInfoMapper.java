package com.fms.db.mapper;

import com.fms.db.model.AgentPerformanceInfo;
import com.fms.db.model.AgentPerformanceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentPerformanceInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    int countByExample(AgentPerformanceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    int deleteByExample(AgentPerformanceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    int deleteByPrimaryKey(Long agentPerformanceInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    int insert(AgentPerformanceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    int insertSelective(AgentPerformanceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    List<AgentPerformanceInfo> selectByExample(AgentPerformanceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    AgentPerformanceInfo selectByPrimaryKey(Long agentPerformanceInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    int updateByExampleSelective(@Param("record") AgentPerformanceInfo record, @Param("example") AgentPerformanceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    int updateByExample(@Param("record") AgentPerformanceInfo record, @Param("example") AgentPerformanceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    int updateByPrimaryKeySelective(AgentPerformanceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_performance_info
     *
     * @mbggenerated Sat Mar 18 15:20:28 CST 2017
     */
    int updateByPrimaryKey(AgentPerformanceInfo record);
}