package com.sinosoft.core.domain.model.rule.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the TC_PD_RULEVERIFY table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="TC_PD_RULEVERIFY"
 */

public abstract class BaseTcPdRuleverify  implements Serializable {

	public static String REF = "TcPdRuleverify";
	public static String PROP_PASS_FLAG = "PassFlag";
	public static String PROP_VERIFY_GRADE = "VerifyGrade";
	public static String PROP_RULE_ID = "RuleId";
	public static String PROP_RISK_ID = "RiskId";
	public static String PROP_ID = "Id";
	public static String PROP_REMARK = "Remark";
	public static String PROP_VERIFY_ORDER = "VerifyOrder";
	public static String PROP_VERIFY_TYPE = "VerifyType";


	// constructors
	public BaseTcPdRuleverify () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTcPdRuleverify (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseTcPdRuleverify (
		java.lang.Integer id,
		java.lang.Integer riskId,
		java.lang.Integer ruleId,
		java.lang.String verifyType) {

		this.setId(id);
		this.setRiskId(riskId);
		this.setRuleId(ruleId);
		this.setVerifyType(verifyType);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer riskId;
	private java.lang.Integer ruleId;
	private java.lang.String verifyType;
	private java.lang.String verifyGrade;
	private java.lang.Integer verifyOrder;
	private java.lang.String passFlag;
	private java.lang.String remark;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="ID"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: RISK_ID
	 */
	public java.lang.Integer getRiskId () {
		return riskId;
	}

	/**
	 * Set the value related to the column: RISK_ID
	 * @param riskId the RISK_ID value
	 */
	public void setRiskId (java.lang.Integer riskId) {
		this.riskId = riskId;
	}



	/**
	 * Return the value associated with the column: RULE_ID
	 */
	public java.lang.Integer getRuleId () {
		return ruleId;
	}

	/**
	 * Set the value related to the column: RULE_ID
	 * @param ruleId the RULE_ID value
	 */
	public void setRuleId (java.lang.Integer ruleId) {
		this.ruleId = ruleId;
	}



	/**
	 * Return the value associated with the column: VERIFY_TYPE
	 */
	public java.lang.String getVerifyType () {
		return verifyType;
	}

	/**
	 * Set the value related to the column: VERIFY_TYPE
	 * @param verifyType the VERIFY_TYPE value
	 */
	public void setVerifyType (java.lang.String verifyType) {
		this.verifyType = verifyType;
	}



	/**
	 * Return the value associated with the column: VERIFY_GRADE
	 */
	public java.lang.String getVerifyGrade () {
		return verifyGrade;
	}

	/**
	 * Set the value related to the column: VERIFY_GRADE
	 * @param verifyGrade the VERIFY_GRADE value
	 */
	public void setVerifyGrade (java.lang.String verifyGrade) {
		this.verifyGrade = verifyGrade;
	}



	/**
	 * Return the value associated with the column: VERIFY_ORDER
	 */
	public java.lang.Integer getVerifyOrder () {
		return verifyOrder;
	}

	/**
	 * Set the value related to the column: VERIFY_ORDER
	 * @param verifyOrder the VERIFY_ORDER value
	 */
	public void setVerifyOrder (java.lang.Integer verifyOrder) {
		this.verifyOrder = verifyOrder;
	}



	/**
	 * Return the value associated with the column: PASS_FLAG
	 */
	public java.lang.String getPassFlag () {
		return passFlag;
	}

	/**
	 * Set the value related to the column: PASS_FLAG
	 * @param passFlag the PASS_FLAG value
	 */
	public void setPassFlag (java.lang.String passFlag) {
		this.passFlag = passFlag;
	}



	/**
	 * Return the value associated with the column: REMARK
	 */
	public java.lang.String getRemark () {
		return remark;
	}

	/**
	 * Set the value related to the column: REMARK
	 * @param remark the REMARK value
	 */
	public void setRemark (java.lang.String remark) {
		this.remark = remark;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.rule.TcPdRuleverify)) return false;
		else {
			com.sinosoft.core.domain.model.rule.TcPdRuleverify tcPdRuleverify = (com.sinosoft.core.domain.model.rule.TcPdRuleverify) obj;
			if (null == this.getId() || null == tcPdRuleverify.getId()) return false;
			else return (this.getId().equals(tcPdRuleverify.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}