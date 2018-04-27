package com.fms.db.model;

import java.math.BigDecimal;
import java.util.Date;

public class PDWealthStockDis {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.PD_WEALTH_STOCK_DIS_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private Long pdWealthStockDisId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.PD_WEALTH_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private Long pdWealthId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.DISTRIBUTE_DATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private Date distributeDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.DISTRIBUTE_MONEY
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private BigDecimal distributeMoney;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.DIS_RATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private BigDecimal disRate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.RC_STATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private String rcState;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.CREATE_DATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private Date createDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.MODIFY_DATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private Date modifyDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.OPER_COM_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private Long operComId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.CREATE_USER_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private Long createUserId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_pd_wealth_stock_dis.MODIFY_USER_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	private Long modifyUserId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.PD_WEALTH_STOCK_DIS_ID
	 * @return  the value of t_pd_wealth_stock_dis.PD_WEALTH_STOCK_DIS_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public Long getPdWealthStockDisId() {
		return pdWealthStockDisId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.PD_WEALTH_STOCK_DIS_ID
	 * @param pdWealthStockDisId  the value for t_pd_wealth_stock_dis.PD_WEALTH_STOCK_DIS_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setPdWealthStockDisId(Long pdWealthStockDisId) {
		this.pdWealthStockDisId = pdWealthStockDisId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.PD_WEALTH_ID
	 * @return  the value of t_pd_wealth_stock_dis.PD_WEALTH_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public Long getPdWealthId() {
		return pdWealthId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.PD_WEALTH_ID
	 * @param pdWealthId  the value for t_pd_wealth_stock_dis.PD_WEALTH_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setPdWealthId(Long pdWealthId) {
		this.pdWealthId = pdWealthId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.DISTRIBUTE_DATE
	 * @return  the value of t_pd_wealth_stock_dis.DISTRIBUTE_DATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public Date getDistributeDate() {
		return distributeDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.DISTRIBUTE_DATE
	 * @param distributeDate  the value for t_pd_wealth_stock_dis.DISTRIBUTE_DATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setDistributeDate(Date distributeDate) {
		this.distributeDate = distributeDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.DISTRIBUTE_MONEY
	 * @return  the value of t_pd_wealth_stock_dis.DISTRIBUTE_MONEY
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public BigDecimal getDistributeMoney() {
		return distributeMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.DISTRIBUTE_MONEY
	 * @param distributeMoney  the value for t_pd_wealth_stock_dis.DISTRIBUTE_MONEY
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setDistributeMoney(BigDecimal distributeMoney) {
		this.distributeMoney = distributeMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.DIS_RATE
	 * @return  the value of t_pd_wealth_stock_dis.DIS_RATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public BigDecimal getDisRate() {
		return disRate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.DIS_RATE
	 * @param disRate  the value for t_pd_wealth_stock_dis.DIS_RATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setDisRate(BigDecimal disRate) {
		this.disRate = disRate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.RC_STATE
	 * @return  the value of t_pd_wealth_stock_dis.RC_STATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public String getRcState() {
		return rcState;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.RC_STATE
	 * @param rcState  the value for t_pd_wealth_stock_dis.RC_STATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setRcState(String rcState) {
		this.rcState = rcState;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.CREATE_DATE
	 * @return  the value of t_pd_wealth_stock_dis.CREATE_DATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.CREATE_DATE
	 * @param createDate  the value for t_pd_wealth_stock_dis.CREATE_DATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.MODIFY_DATE
	 * @return  the value of t_pd_wealth_stock_dis.MODIFY_DATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.MODIFY_DATE
	 * @param modifyDate  the value for t_pd_wealth_stock_dis.MODIFY_DATE
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.OPER_COM_ID
	 * @return  the value of t_pd_wealth_stock_dis.OPER_COM_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public Long getOperComId() {
		return operComId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.OPER_COM_ID
	 * @param operComId  the value for t_pd_wealth_stock_dis.OPER_COM_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setOperComId(Long operComId) {
		this.operComId = operComId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.CREATE_USER_ID
	 * @return  the value of t_pd_wealth_stock_dis.CREATE_USER_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.CREATE_USER_ID
	 * @param createUserId  the value for t_pd_wealth_stock_dis.CREATE_USER_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_pd_wealth_stock_dis.MODIFY_USER_ID
	 * @return  the value of t_pd_wealth_stock_dis.MODIFY_USER_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_pd_wealth_stock_dis.MODIFY_USER_ID
	 * @param modifyUserId  the value for t_pd_wealth_stock_dis.MODIFY_USER_ID
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
}