package com.fms.db.model;

import java.math.BigDecimal;
import java.util.Date;

public class RedemptionTradeInfo {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.REDEMPTION_TRADE_INFO_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private Long redemptionTradeInfoId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.REDEMPTION_INFO_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private Long redemptionInfoId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.TRADE_INFO_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private Long tradeInfoId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.REDEMPTION_SHARE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private BigDecimal redemptionShare;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.ACTUAL_REDEMPTION_SHARE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private BigDecimal actualRedemptionShare;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.ACTUAL_REDEMPTION_MONEY
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private BigDecimal actualRedemptionMoney;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.RC_STATE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private String rcState;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.CREATE_DATE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private Date createDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.MODIFY_DATE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private Date modifyDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.OPER_COM_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private Long operComId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.CREATE_USER_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private Long createUserId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_redemption_trade_info.MODIFY_USER_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	private Long modifyUserId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.REDEMPTION_TRADE_INFO_ID
	 * @return  the value of t_redemption_trade_info.REDEMPTION_TRADE_INFO_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public Long getRedemptionTradeInfoId() {
		return redemptionTradeInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.REDEMPTION_TRADE_INFO_ID
	 * @param redemptionTradeInfoId  the value for t_redemption_trade_info.REDEMPTION_TRADE_INFO_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setRedemptionTradeInfoId(Long redemptionTradeInfoId) {
		this.redemptionTradeInfoId = redemptionTradeInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.REDEMPTION_INFO_ID
	 * @return  the value of t_redemption_trade_info.REDEMPTION_INFO_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public Long getRedemptionInfoId() {
		return redemptionInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.REDEMPTION_INFO_ID
	 * @param redemptionInfoId  the value for t_redemption_trade_info.REDEMPTION_INFO_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setRedemptionInfoId(Long redemptionInfoId) {
		this.redemptionInfoId = redemptionInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.TRADE_INFO_ID
	 * @return  the value of t_redemption_trade_info.TRADE_INFO_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public Long getTradeInfoId() {
		return tradeInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.TRADE_INFO_ID
	 * @param tradeInfoId  the value for t_redemption_trade_info.TRADE_INFO_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setTradeInfoId(Long tradeInfoId) {
		this.tradeInfoId = tradeInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.REDEMPTION_SHARE
	 * @return  the value of t_redemption_trade_info.REDEMPTION_SHARE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public BigDecimal getRedemptionShare() {
		return redemptionShare;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.REDEMPTION_SHARE
	 * @param redemptionShare  the value for t_redemption_trade_info.REDEMPTION_SHARE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setRedemptionShare(BigDecimal redemptionShare) {
		this.redemptionShare = redemptionShare;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.ACTUAL_REDEMPTION_SHARE
	 * @return  the value of t_redemption_trade_info.ACTUAL_REDEMPTION_SHARE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public BigDecimal getActualRedemptionShare() {
		return actualRedemptionShare;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.ACTUAL_REDEMPTION_SHARE
	 * @param actualRedemptionShare  the value for t_redemption_trade_info.ACTUAL_REDEMPTION_SHARE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setActualRedemptionShare(BigDecimal actualRedemptionShare) {
		this.actualRedemptionShare = actualRedemptionShare;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.ACTUAL_REDEMPTION_MONEY
	 * @return  the value of t_redemption_trade_info.ACTUAL_REDEMPTION_MONEY
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public BigDecimal getActualRedemptionMoney() {
		return actualRedemptionMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.ACTUAL_REDEMPTION_MONEY
	 * @param actualRedemptionMoney  the value for t_redemption_trade_info.ACTUAL_REDEMPTION_MONEY
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setActualRedemptionMoney(BigDecimal actualRedemptionMoney) {
		this.actualRedemptionMoney = actualRedemptionMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.RC_STATE
	 * @return  the value of t_redemption_trade_info.RC_STATE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public String getRcState() {
		return rcState;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.RC_STATE
	 * @param rcState  the value for t_redemption_trade_info.RC_STATE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setRcState(String rcState) {
		this.rcState = rcState;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.CREATE_DATE
	 * @return  the value of t_redemption_trade_info.CREATE_DATE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.CREATE_DATE
	 * @param createDate  the value for t_redemption_trade_info.CREATE_DATE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.MODIFY_DATE
	 * @return  the value of t_redemption_trade_info.MODIFY_DATE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.MODIFY_DATE
	 * @param modifyDate  the value for t_redemption_trade_info.MODIFY_DATE
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.OPER_COM_ID
	 * @return  the value of t_redemption_trade_info.OPER_COM_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public Long getOperComId() {
		return operComId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.OPER_COM_ID
	 * @param operComId  the value for t_redemption_trade_info.OPER_COM_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setOperComId(Long operComId) {
		this.operComId = operComId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.CREATE_USER_ID
	 * @return  the value of t_redemption_trade_info.CREATE_USER_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.CREATE_USER_ID
	 * @param createUserId  the value for t_redemption_trade_info.CREATE_USER_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_redemption_trade_info.MODIFY_USER_ID
	 * @return  the value of t_redemption_trade_info.MODIFY_USER_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_redemption_trade_info.MODIFY_USER_ID
	 * @param modifyUserId  the value for t_redemption_trade_info.MODIFY_USER_ID
	 * @mbggenerated  Tue Nov 17 16:32:10 CST 2015
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
}