package com.fms.db.model;

import java.math.BigDecimal;
import java.util.Date;

public class CustCarInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.CUST_CAR_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long custCarInfoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.CUST_BASE_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long custBaseInfoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.CAR_CODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String carCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.CAR_TYPE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String carType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.CAR_BRAND
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String carBrand;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.ENGINE_NO
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String engineNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.CAR_FRAME_NO
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String carFrameNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.LICENSE_PLATE_NO
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String licensePlateNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.RE_PRICE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private BigDecimal rePrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.AGENT_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long agentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private String rcState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Date modifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long operComId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cust_car_info.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    private Long modifyUserId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.CUST_CAR_INFO_ID
     *
     * @return the value of t_cust_car_info.CUST_CAR_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getCustCarInfoId() {
        return custCarInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.CUST_CAR_INFO_ID
     *
     * @param custCarInfoId the value for t_cust_car_info.CUST_CAR_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCustCarInfoId(Long custCarInfoId) {
        this.custCarInfoId = custCarInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.CUST_BASE_INFO_ID
     *
     * @return the value of t_cust_car_info.CUST_BASE_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getCustBaseInfoId() {
        return custBaseInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.CUST_BASE_INFO_ID
     *
     * @param custBaseInfoId the value for t_cust_car_info.CUST_BASE_INFO_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCustBaseInfoId(Long custBaseInfoId) {
        this.custBaseInfoId = custBaseInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.CAR_CODE
     *
     * @return the value of t_cust_car_info.CAR_CODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getCarCode() {
        return carCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.CAR_CODE
     *
     * @param carCode the value for t_cust_car_info.CAR_CODE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.CAR_TYPE
     *
     * @return the value of t_cust_car_info.CAR_TYPE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getCarType() {
        return carType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.CAR_TYPE
     *
     * @param carType the value for t_cust_car_info.CAR_TYPE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.CAR_BRAND
     *
     * @return the value of t_cust_car_info.CAR_BRAND
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getCarBrand() {
        return carBrand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.CAR_BRAND
     *
     * @param carBrand the value for t_cust_car_info.CAR_BRAND
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.ENGINE_NO
     *
     * @return the value of t_cust_car_info.ENGINE_NO
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getEngineNo() {
        return engineNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.ENGINE_NO
     *
     * @param engineNo the value for t_cust_car_info.ENGINE_NO
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.CAR_FRAME_NO
     *
     * @return the value of t_cust_car_info.CAR_FRAME_NO
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getCarFrameNo() {
        return carFrameNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.CAR_FRAME_NO
     *
     * @param carFrameNo the value for t_cust_car_info.CAR_FRAME_NO
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCarFrameNo(String carFrameNo) {
        this.carFrameNo = carFrameNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.LICENSE_PLATE_NO
     *
     * @return the value of t_cust_car_info.LICENSE_PLATE_NO
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getLicensePlateNo() {
        return licensePlateNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.LICENSE_PLATE_NO
     *
     * @param licensePlateNo the value for t_cust_car_info.LICENSE_PLATE_NO
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setLicensePlateNo(String licensePlateNo) {
        this.licensePlateNo = licensePlateNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.RE_PRICE
     *
     * @return the value of t_cust_car_info.RE_PRICE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public BigDecimal getRePrice() {
        return rePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.RE_PRICE
     *
     * @param rePrice the value for t_cust_car_info.RE_PRICE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setRePrice(BigDecimal rePrice) {
        this.rePrice = rePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.AGENT_ID
     *
     * @return the value of t_cust_car_info.AGENT_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getAgentId() {
        return agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.AGENT_ID
     *
     * @param agentId the value for t_cust_car_info.AGENT_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.RC_STATE
     *
     * @return the value of t_cust_car_info.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public String getRcState() {
        return rcState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.RC_STATE
     *
     * @param rcState the value for t_cust_car_info.RC_STATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setRcState(String rcState) {
        this.rcState = rcState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.CREATE_DATE
     *
     * @return the value of t_cust_car_info.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.CREATE_DATE
     *
     * @param createDate the value for t_cust_car_info.CREATE_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.MODIFY_DATE
     *
     * @return the value of t_cust_car_info.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.MODIFY_DATE
     *
     * @param modifyDate the value for t_cust_car_info.MODIFY_DATE
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.OPER_COM_ID
     *
     * @return the value of t_cust_car_info.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getOperComId() {
        return operComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.OPER_COM_ID
     *
     * @param operComId the value for t_cust_car_info.OPER_COM_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setOperComId(Long operComId) {
        this.operComId = operComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.CREATE_USER_ID
     *
     * @return the value of t_cust_car_info.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.CREATE_USER_ID
     *
     * @param createUserId the value for t_cust_car_info.CREATE_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cust_car_info.MODIFY_USER_ID
     *
     * @return the value of t_cust_car_info.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cust_car_info.MODIFY_USER_ID
     *
     * @param modifyUserId the value for t_cust_car_info.MODIFY_USER_ID
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}