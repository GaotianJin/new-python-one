package com.fms.db.model;

import java.math.BigDecimal;

public class AgentPositionInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_position_info.POSITONCODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String positoncode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_position_info.GRADECODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String gradecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_position_info.MANAGERATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private BigDecimal managerate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_position_info.POSITONCODE
     *
     * @return the value of t_agent_position_info.POSITONCODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getPositoncode() {
        return positoncode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_position_info.POSITONCODE
     *
     * @param positoncode the value for t_agent_position_info.POSITONCODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setPositoncode(String positoncode) {
        this.positoncode = positoncode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_position_info.GRADECODE
     *
     * @return the value of t_agent_position_info.GRADECODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getGradecode() {
        return gradecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_position_info.GRADECODE
     *
     * @param gradecode the value for t_agent_position_info.GRADECODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setGradecode(String gradecode) {
        this.gradecode = gradecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_position_info.MANAGERATE
     *
     * @return the value of t_agent_position_info.MANAGERATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public BigDecimal getManagerate() {
        return managerate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_position_info.MANAGERATE
     *
     * @param managerate the value for t_agent_position_info.MANAGERATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setManagerate(BigDecimal managerate) {
        this.managerate = managerate;
    }
}