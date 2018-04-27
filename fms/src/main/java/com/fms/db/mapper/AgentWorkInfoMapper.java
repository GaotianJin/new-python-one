package com.fms.db.mapper;

import com.fms.db.model.AgentWorkInfo;
import com.fms.db.model.AgentWorkInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentWorkInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int countByExample(AgentWorkInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByExample(AgentWorkInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByPrimaryKey(Long agentWorkInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insert(AgentWorkInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insertSelective(AgentWorkInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    List<AgentWorkInfo> selectByExample(AgentWorkInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    AgentWorkInfo selectByPrimaryKey(Long agentWorkInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExampleSelective(@Param("record") AgentWorkInfo record, @Param("example") AgentWorkInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExample(@Param("record") AgentWorkInfo record, @Param("example") AgentWorkInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKeySelective(AgentWorkInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_work_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKey(AgentWorkInfo record);
}