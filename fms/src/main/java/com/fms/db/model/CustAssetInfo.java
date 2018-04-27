package com.fms.db.model;

import java.math.BigDecimal;
import java.util.Date;

public class CustAssetInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.CUST_ASSET_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long custAssetInfoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.CUST_BASE_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long custBaseInfoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.ASSET_CODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String assetCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.PERCENT
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private BigDecimal percent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.AGENT_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long agentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String rcState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Date modifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long operComId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_asset_info.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long modifyUserId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.CUST_ASSET_INFO_ID
     *
     * @return the value of t_cust_asset_info.CUST_ASSET_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getCustAssetInfoId() {
        return custAssetInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.CUST_ASSET_INFO_ID
     *
     * @param custAssetInfoId the value for t_cust_asset_info.CUST_ASSET_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCustAssetInfoId(Long custAssetInfoId) {
        this.custAssetInfoId = custAssetInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.CUST_BASE_INFO_ID
     *
     * @return the value of t_cust_asset_info.CUST_BASE_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getCustBaseInfoId() {
        return custBaseInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.CUST_BASE_INFO_ID
     *
     * @param custBaseInfoId the value for t_cust_asset_info.CUST_BASE_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCustBaseInfoId(Long custBaseInfoId) {
        this.custBaseInfoId = custBaseInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.ASSET_CODE
     *
     * @return the value of t_cust_asset_info.ASSET_CODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.ASSET_CODE
     *
     * @param assetCode the value for t_cust_asset_info.ASSET_CODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.PERCENT
     *
     * @return the value of t_cust_asset_info.PERCENT
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.PERCENT
     *
     * @param percent the value for t_cust_asset_info.PERCENT
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.AGENT_ID
     *
     * @return the value of t_cust_asset_info.AGENT_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getAgentId() {
        return agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.AGENT_ID
     *
     * @param agentId the value for t_cust_asset_info.AGENT_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.RC_STATE
     *
     * @return the value of t_cust_asset_info.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getRcState() {
        return rcState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.RC_STATE
     *
     * @param rcState the value for t_cust_asset_info.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setRcState(String rcState) {
        this.rcState = rcState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.CREATE_DATE
     *
     * @return the value of t_cust_asset_info.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.CREATE_DATE
     *
     * @param createDate the value for t_cust_asset_info.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.MODIFY_DATE
     *
     * @return the value of t_cust_asset_info.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.MODIFY_DATE
     *
     * @param modifyDate the value for t_cust_asset_info.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.OPER_COM_ID
     *
     * @return the value of t_cust_asset_info.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getOperComId() {
        return operComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.OPER_COM_ID
     *
     * @param operComId the value for t_cust_asset_info.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setOperComId(Long operComId) {
        this.operComId = operComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.CREATE_USER_ID
     *
     * @return the value of t_cust_asset_info.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.CREATE_USER_ID
     *
     * @param createUserId the value for t_cust_asset_info.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_asset_info.MODIFY_USER_ID
     *
     * @return the value of t_cust_asset_info.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_asset_info.MODIFY_USER_ID
     *
     * @param modifyUserId the value for t_cust_asset_info.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}