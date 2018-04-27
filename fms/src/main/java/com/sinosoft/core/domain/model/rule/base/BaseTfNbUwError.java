package com.sinosoft.core.domain.model.rule.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the TF_NB_UW_ERROR table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="TF_NB_UW_ERROR"
 */

public abstract class BaseTfNbUwError  implements Serializable {

	public static String REF = "TfNbUwError";
	public static String PROP_OPERATOR_ID = "OperatorId";
	public static String PROP_MAKE_TIME = "MakeTime";
	public static String PROP_UW_BATCH_NO = "UwBatchNo";
	public static String PROP_UW_INFO_LEVEL_FLAG = "UwInfoLevelFlag";
	public static String PROP_UW_INFO = "UwInfo";
	public static String PROP_UW_RELATION_ID = "UwRelationId";
	public static String PROP_UW_RULE_ID = "UwRuleId";
	public static String PROP_ID = "Id";
	public static String PROP_MODIFY_TIME = "ModifyTime";
	public static String PROP_UW_TYPE = "UwType";
	public static String PROP_UW_LAYER = "UwLayer";


	// constructors
	public BaseTfNbUwError () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTfNbUwError (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseTfNbUwError (
		java.lang.Integer id,
		java.lang.String uwLayer,
		java.lang.Integer uwRelationId,
		java.lang.Integer uwBatchNo,
		java.lang.String uwRuleId,
		java.lang.String uwInfo,
		java.lang.String uwType,
		java.lang.Integer operatorId,
		java.util.Date makeTime,
		java.util.Date modifyTime) {

		this.setId(id);
		this.setUwLayer(uwLayer);
		this.setUwRelationId(uwRelationId);
		this.setUwBatchNo(uwBatchNo);
		this.setUwRuleId(uwRuleId);
		this.setUwInfo(uwInfo);
		this.setUwType(uwType);
		this.setOperatorId(operatorId);
		this.setMakeTime(makeTime);
		this.setModifyTime(modifyTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String uwLayer;
	private java.lang.Integer uwRelationId;
	private java.lang.Integer uwBatchNo;
	private java.lang.String uwRuleId;
	private java.lang.String uwInfo;
	private java.lang.String uwInfoLevelFlag;
	private java.lang.String uwType;
	private java.lang.Integer operatorId;
	private java.util.Date makeTime;
	private java.util.Date modifyTime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="DBID"
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
	 * Return the value associated with the column: UW_LAYER
	 */
	public java.lang.String getUwLayer () {
		return uwLayer;
	}

	/**
	 * Set the value related to the column: UW_LAYER
	 * @param uwLayer the UW_LAYER value
	 */
	public void setUwLayer (java.lang.String uwLayer) {
		this.uwLayer = uwLayer;
	}



	/**
	 * Return the value associated with the column: UW_RELATION_ID
	 */
	public java.lang.Integer getUwRelationId () {
		return uwRelationId;
	}

	/**
	 * Set the value related to the column: UW_RELATION_ID
	 * @param uwRelationId the UW_RELATION_ID value
	 */
	public void setUwRelationId (java.lang.Integer uwRelationId) {
		this.uwRelationId = uwRelationId;
	}



	/**
	 * Return the value associated with the column: UW_BATCH_NO
	 */
	public java.lang.Integer getUwBatchNo () {
		return uwBatchNo;
	}

	/**
	 * Set the value related to the column: UW_BATCH_NO
	 * @param uwBatchNo the UW_BATCH_NO value
	 */
	public void setUwBatchNo (java.lang.Integer uwBatchNo) {
		this.uwBatchNo = uwBatchNo;
	}



	/**
	 * Return the value associated with the column: UW_RULE_ID
	 */
	public java.lang.String getUwRuleId () {
		return uwRuleId;
	}

	/**
	 * Set the value related to the column: UW_RULE_ID
	 * @param uwRuleId the UW_RULE_ID value
	 */
	public void setUwRuleId (java.lang.String uwRuleId) {
		this.uwRuleId = uwRuleId;
	}



	/**
	 * Return the value associated with the column: UW_INFO
	 */
	public java.lang.String getUwInfo () {
		return uwInfo;
	}

	/**
	 * Set the value related to the column: UW_INFO
	 * @param uwInfo the UW_INFO value
	 */
	public void setUwInfo (java.lang.String uwInfo) {
		this.uwInfo = uwInfo;
	}



	/**
	 * Return the value associated with the column: UW_INFO_LEVEL_FLAG
	 */
	public java.lang.String getUwInfoLevelFlag () {
		return uwInfoLevelFlag;
	}

	/**
	 * Set the value related to the column: UW_INFO_LEVEL_FLAG
	 * @param uwInfoLevelFlag the UW_INFO_LEVEL_FLAG value
	 */
	public void setUwInfoLevelFlag (java.lang.String uwInfoLevelFlag) {
		this.uwInfoLevelFlag = uwInfoLevelFlag;
	}



	/**
	 * Return the value associated with the column: UW_TYPE
	 */
	public java.lang.String getUwType () {
		return uwType;
	}

	/**
	 * Set the value related to the column: UW_TYPE
	 * @param uwType the UW_TYPE value
	 */
	public void setUwType (java.lang.String uwType) {
		this.uwType = uwType;
	}



	/**
	 * Return the value associated with the column: OPERATOR_ID
	 */
	public java.lang.Integer getOperatorId () {
		return operatorId;
	}

	/**
	 * Set the value related to the column: OPERATOR_ID
	 * @param operatorId the OPERATOR_ID value
	 */
	public void setOperatorId (java.lang.Integer operatorId) {
		this.operatorId = operatorId;
	}



	/**
	 * Return the value associated with the column: MAKE_TIME
	 */
	public java.util.Date getMakeTime () {
		return makeTime;
	}

	/**
	 * Set the value related to the column: MAKE_TIME
	 * @param makeTime the MAKE_TIME value
	 */
	public void setMakeTime (java.util.Date makeTime) {
		this.makeTime = makeTime;
	}



	/**
	 * Return the value associated with the column: MODIFY_TIME
	 */
	public java.util.Date getModifyTime () {
		return modifyTime;
	}

	/**
	 * Set the value related to the column: MODIFY_TIME
	 * @param modifyTime the MODIFY_TIME value
	 */
	public void setModifyTime (java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.rule.TfNbUwError)) return false;
		else {
			com.sinosoft.core.domain.model.rule.TfNbUwError tfNbUwError = (com.sinosoft.core.domain.model.rule.TfNbUwError) obj;
			if (null == this.getId() || null == tfNbUwError.getId()) return false;
			else return (this.getId().equals(tfNbUwError.getId()));
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