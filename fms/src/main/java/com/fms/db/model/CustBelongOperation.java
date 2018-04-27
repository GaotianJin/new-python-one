package com.fms.db.model;

import java.util.Date;

public class CustBelongOperation {
	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.cust_operation_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Long custOperationId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.cust_base_info_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Long custBaseInfoId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.agent_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Long agentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.user_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Long userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.adjust_date
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Date adjustDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.belong_start_date
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Date belongStartDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.belong_end_date
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Date belongEndDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.adjust_cause
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private String adjustCause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.RC_STATE
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private String rcState;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.CREATE_DATE
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Date createDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.MODIFY_DATE
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Date modifyDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.OPER_COM_ID
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Long operComId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.CREATE_USER_ID
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Long createUserId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_cust_belong_operation.MODIFY_USER_ID
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	private Long modifyUserId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.cust_operation_id
	 * @return  the value of t_cust_belong_operation.cust_operation_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Long getCustOperationId() {
		return custOperationId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.cust_operation_id
	 * @param custOperationId  the value for t_cust_belong_operation.cust_operation_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setCustOperationId(Long custOperationId) {
		this.custOperationId = custOperationId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.cust_base_info_id
	 * @return  the value of t_cust_belong_operation.cust_base_info_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Long getCustBaseInfoId() {
		return custBaseInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.cust_base_info_id
	 * @param custBaseInfoId  the value for t_cust_belong_operation.cust_base_info_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setCustBaseInfoId(Long custBaseInfoId) {
		this.custBaseInfoId = custBaseInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.agent_id
	 * @return  the value of t_cust_belong_operation.agent_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Long getAgentId() {
		return agentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.agent_id
	 * @param agentId  the value for t_cust_belong_operation.agent_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.user_id
	 * @return  the value of t_cust_belong_operation.user_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.user_id
	 * @param userId  the value for t_cust_belong_operation.user_id
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.adjust_date
	 * @return  the value of t_cust_belong_operation.adjust_date
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Date getAdjustDate() {
		return adjustDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.adjust_date
	 * @param adjustDate  the value for t_cust_belong_operation.adjust_date
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.belong_start_date
	 * @return  the value of t_cust_belong_operation.belong_start_date
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Date getBelongStartDate() {
		return belongStartDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.belong_start_date
	 * @param belongStartDate  the value for t_cust_belong_operation.belong_start_date
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setBelongStartDate(Date belongStartDate) {
		this.belongStartDate = belongStartDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.belong_end_date
	 * @return  the value of t_cust_belong_operation.belong_end_date
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Date getBelongEndDate() {
		return belongEndDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.belong_end_date
	 * @param belongEndDate  the value for t_cust_belong_operation.belong_end_date
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setBelongEndDate(Date belongEndDate) {
		this.belongEndDate = belongEndDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.adjust_cause
	 * @return  the value of t_cust_belong_operation.adjust_cause
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public String getAdjustCause() {
		return adjustCause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.adjust_cause
	 * @param adjustCause  the value for t_cust_belong_operation.adjust_cause
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setAdjustCause(String adjustCause) {
		this.adjustCause = adjustCause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.RC_STATE
	 * @return  the value of t_cust_belong_operation.RC_STATE
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public String getRcState() {
		return rcState;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.RC_STATE
	 * @param rcState  the value for t_cust_belong_operation.RC_STATE
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setRcState(String rcState) {
		this.rcState = rcState;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.CREATE_DATE
	 * @return  the value of t_cust_belong_operation.CREATE_DATE
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.CREATE_DATE
	 * @param createDate  the value for t_cust_belong_operation.CREATE_DATE
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.MODIFY_DATE
	 * @return  the value of t_cust_belong_operation.MODIFY_DATE
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.MODIFY_DATE
	 * @param modifyDate  the value for t_cust_belong_operation.MODIFY_DATE
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.OPER_COM_ID
	 * @return  the value of t_cust_belong_operation.OPER_COM_ID
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Long getOperComId() {
		return operComId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.OPER_COM_ID
	 * @param operComId  the value for t_cust_belong_operation.OPER_COM_ID
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setOperComId(Long operComId) {
		this.operComId = operComId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.CREATE_USER_ID
	 * @return  the value of t_cust_belong_operation.CREATE_USER_ID
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.CREATE_USER_ID
	 * @param createUserId  the value for t_cust_belong_operation.CREATE_USER_ID
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_cust_belong_operation.MODIFY_USER_ID
	 * @return  the value of t_cust_belong_operation.MODIFY_USER_ID
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_cust_belong_operation.MODIFY_USER_ID
	 * @param modifyUserId  the value for t_cust_belong_operation.MODIFY_USER_ID
	 * @mbggenerated  Wed Feb 17 20:29:46 CST 2016
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustBelongOperation other = (CustBelongOperation) obj;
		if (custOperationId == null) {
			if (other.custOperationId != null)
				return false;
		} else if (!custOperationId.equals(other.custOperationId))
			return false;
		if (custBaseInfoId == null) {
			if (other.custBaseInfoId != null)
				return false;
		} else if (!custBaseInfoId.equals(other.custBaseInfoId))
			return false;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
		if (adjustCause == null) {
			if (other.adjustCause != null)
				return false;
		} else if (!adjustCause.equals(other.adjustCause))
			return false;
		if (belongStartDate == null) {
			if (other.belongStartDate != null)
				return false;
		} else if (!belongStartDate.equals(other.belongStartDate)){
			return false;
		}
		return true;
	}
}