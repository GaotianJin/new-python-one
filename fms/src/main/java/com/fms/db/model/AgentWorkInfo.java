package com.fms.db.model;

import java.util.Date;

public class AgentWorkInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.AGENT_WORK_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long agentWorkInfoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.AGENT_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long agentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.OCCUPATION
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String occupation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.SUB_OCCUPATION
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String subOccupation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.DUTIES
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String duties;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.RANK
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String rank;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.START_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Date startDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.END_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Date endDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String rcState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Date modifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long operComId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_agent_work_info.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long modifyUserId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.AGENT_WORK_INFO_ID
     *
     * @return the value of t_agent_work_info.AGENT_WORK_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getAgentWorkInfoId() {
        return agentWorkInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.AGENT_WORK_INFO_ID
     *
     * @param agentWorkInfoId the value for t_agent_work_info.AGENT_WORK_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setAgentWorkInfoId(Long agentWorkInfoId) {
        this.agentWorkInfoId = agentWorkInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.AGENT_ID
     *
     * @return the value of t_agent_work_info.AGENT_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getAgentId() {
        return agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.AGENT_ID
     *
     * @param agentId the value for t_agent_work_info.AGENT_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.OCCUPATION
     *
     * @return the value of t_agent_work_info.OCCUPATION
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.OCCUPATION
     *
     * @param occupation the value for t_agent_work_info.OCCUPATION
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.SUB_OCCUPATION
     *
     * @return the value of t_agent_work_info.SUB_OCCUPATION
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getSubOccupation() {
        return subOccupation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.SUB_OCCUPATION
     *
     * @param subOccupation the value for t_agent_work_info.SUB_OCCUPATION
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setSubOccupation(String subOccupation) {
        this.subOccupation = subOccupation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.DUTIES
     *
     * @return the value of t_agent_work_info.DUTIES
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getDuties() {
        return duties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.DUTIES
     *
     * @param duties the value for t_agent_work_info.DUTIES
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setDuties(String duties) {
        this.duties = duties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.RANK
     *
     * @return the value of t_agent_work_info.RANK
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getRank() {
        return rank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.RANK
     *
     * @param rank the value for t_agent_work_info.RANK
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.START_DATE
     *
     * @return the value of t_agent_work_info.START_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.START_DATE
     *
     * @param startDate the value for t_agent_work_info.START_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.END_DATE
     *
     * @return the value of t_agent_work_info.END_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.END_DATE
     *
     * @param endDate the value for t_agent_work_info.END_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.RC_STATE
     *
     * @return the value of t_agent_work_info.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getRcState() {
        return rcState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.RC_STATE
     *
     * @param rcState the value for t_agent_work_info.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setRcState(String rcState) {
        this.rcState = rcState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.CREATE_DATE
     *
     * @return the value of t_agent_work_info.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.CREATE_DATE
     *
     * @param createDate the value for t_agent_work_info.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.MODIFY_DATE
     *
     * @return the value of t_agent_work_info.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.MODIFY_DATE
     *
     * @param modifyDate the value for t_agent_work_info.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.OPER_COM_ID
     *
     * @return the value of t_agent_work_info.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getOperComId() {
        return operComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.OPER_COM_ID
     *
     * @param operComId the value for t_agent_work_info.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setOperComId(Long operComId) {
        this.operComId = operComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.CREATE_USER_ID
     *
     * @return the value of t_agent_work_info.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.CREATE_USER_ID
     *
     * @param createUserId the value for t_agent_work_info.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_agent_work_info.MODIFY_USER_ID
     *
     * @return the value of t_agent_work_info.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_agent_work_info.MODIFY_USER_ID
     *
     * @param modifyUserId the value for t_agent_work_info.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}