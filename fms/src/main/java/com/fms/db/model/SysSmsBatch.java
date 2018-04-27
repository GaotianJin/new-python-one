package com.fms.db.model;

import java.util.Date;

public class SysSmsBatch {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.SYS_SMS_BATCH_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private Long sysSmsBatchId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.SMS_TYPE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private String smsType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.SMS_TYPE_RELATION_NO
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private Long smsTypeRelationNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.SMS_TYPE_RELATION_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private Date smsTypeRelationDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.SMS_CREATE_TIME
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private Date smsCreateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.SMS_STATUS
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private String smsStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private String rcState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private Date modifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private Long operComId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_sms_batch.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    private Long modifyUserId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.SYS_SMS_BATCH_ID
     *
     * @return the value of t_sys_sms_batch.SYS_SMS_BATCH_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Long getSysSmsBatchId() {
        return sysSmsBatchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.SYS_SMS_BATCH_ID
     *
     * @param sysSmsBatchId the value for t_sys_sms_batch.SYS_SMS_BATCH_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setSysSmsBatchId(Long sysSmsBatchId) {
        this.sysSmsBatchId = sysSmsBatchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.SMS_TYPE
     *
     * @return the value of t_sys_sms_batch.SMS_TYPE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public String getSmsType() {
        return smsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.SMS_TYPE
     *
     * @param smsType the value for t_sys_sms_batch.SMS_TYPE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.SMS_TYPE_RELATION_NO
     *
     * @return the value of t_sys_sms_batch.SMS_TYPE_RELATION_NO
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Long getSmsTypeRelationNo() {
        return smsTypeRelationNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.SMS_TYPE_RELATION_NO
     *
     * @param smsTypeRelationNo the value for t_sys_sms_batch.SMS_TYPE_RELATION_NO
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setSmsTypeRelationNo(Long smsTypeRelationNo) {
        this.smsTypeRelationNo = smsTypeRelationNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.SMS_TYPE_RELATION_DATE
     *
     * @return the value of t_sys_sms_batch.SMS_TYPE_RELATION_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Date getSmsTypeRelationDate() {
        return smsTypeRelationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.SMS_TYPE_RELATION_DATE
     *
     * @param smsTypeRelationDate the value for t_sys_sms_batch.SMS_TYPE_RELATION_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setSmsTypeRelationDate(Date smsTypeRelationDate) {
        this.smsTypeRelationDate = smsTypeRelationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.SMS_CREATE_TIME
     *
     * @return the value of t_sys_sms_batch.SMS_CREATE_TIME
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Date getSmsCreateTime() {
        return smsCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.SMS_CREATE_TIME
     *
     * @param smsCreateTime the value for t_sys_sms_batch.SMS_CREATE_TIME
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setSmsCreateTime(Date smsCreateTime) {
        this.smsCreateTime = smsCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.SMS_STATUS
     *
     * @return the value of t_sys_sms_batch.SMS_STATUS
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public String getSmsStatus() {
        return smsStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.SMS_STATUS
     *
     * @param smsStatus the value for t_sys_sms_batch.SMS_STATUS
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.RC_STATE
     *
     * @return the value of t_sys_sms_batch.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public String getRcState() {
        return rcState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.RC_STATE
     *
     * @param rcState the value for t_sys_sms_batch.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setRcState(String rcState) {
        this.rcState = rcState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.CREATE_DATE
     *
     * @return the value of t_sys_sms_batch.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.CREATE_DATE
     *
     * @param createDate the value for t_sys_sms_batch.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.MODIFY_DATE
     *
     * @return the value of t_sys_sms_batch.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.MODIFY_DATE
     *
     * @param modifyDate the value for t_sys_sms_batch.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.OPER_COM_ID
     *
     * @return the value of t_sys_sms_batch.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Long getOperComId() {
        return operComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.OPER_COM_ID
     *
     * @param operComId the value for t_sys_sms_batch.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setOperComId(Long operComId) {
        this.operComId = operComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.CREATE_USER_ID
     *
     * @return the value of t_sys_sms_batch.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.CREATE_USER_ID
     *
     * @param createUserId the value for t_sys_sms_batch.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_sms_batch.MODIFY_USER_ID
     *
     * @return the value of t_sys_sms_batch.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_sms_batch.MODIFY_USER_ID
     *
     * @param modifyUserId the value for t_sys_sms_batch.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}