package com.fms.db.model;

public class SysEmailAdress {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_sys_email_adress.SYS_EMAIL_ADRESS_ID
	 * @mbggenerated  Tue Jun 21 13:47:44 CST 2016
	 */
	private Long sysEmailAdressId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_sys_email_adress.EMAIL_TYPE
	 * @mbggenerated  Tue Jun 21 13:47:44 CST 2016
	 */
	private String emailType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_sys_email_adress.EMAIL_ADDRESS
	 * @mbggenerated  Tue Jun 21 13:47:44 CST 2016
	 */
	private String emailAddress;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_sys_email_adress.SYS_EMAIL_ADRESS_ID
	 * @return  the value of t_sys_email_adress.SYS_EMAIL_ADRESS_ID
	 * @mbggenerated  Tue Jun 21 13:47:44 CST 2016
	 */
	public Long getSysEmailAdressId() {
		return sysEmailAdressId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_sys_email_adress.SYS_EMAIL_ADRESS_ID
	 * @param sysEmailAdressId  the value for t_sys_email_adress.SYS_EMAIL_ADRESS_ID
	 * @mbggenerated  Tue Jun 21 13:47:44 CST 2016
	 */
	public void setSysEmailAdressId(Long sysEmailAdressId) {
		this.sysEmailAdressId = sysEmailAdressId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_sys_email_adress.EMAIL_TYPE
	 * @return  the value of t_sys_email_adress.EMAIL_TYPE
	 * @mbggenerated  Tue Jun 21 13:47:44 CST 2016
	 */
	public String getEmailType() {
		return emailType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_sys_email_adress.EMAIL_TYPE
	 * @param emailType  the value for t_sys_email_adress.EMAIL_TYPE
	 * @mbggenerated  Tue Jun 21 13:47:44 CST 2016
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_sys_email_adress.EMAIL_ADDRESS
	 * @return  the value of t_sys_email_adress.EMAIL_ADDRESS
	 * @mbggenerated  Tue Jun 21 13:47:44 CST 2016
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_sys_email_adress.EMAIL_ADDRESS
	 * @param emailAddress  the value for t_sys_email_adress.EMAIL_ADDRESS
	 * @mbggenerated  Tue Jun 21 13:47:44 CST 2016
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}