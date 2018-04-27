package com.sinosoft.core.domain.model.rule;

import com.sinosoft.core.domain.model.rule.base.BaseTfNbUwErrorTrace;



public class TfNbUwErrorTrace extends BaseTfNbUwErrorTrace {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TfNbUwErrorTrace () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TfNbUwErrorTrace (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public TfNbUwErrorTrace (
		java.lang.Integer id,
		java.lang.String uwLayer,
		java.lang.Integer uwRelationId,
		java.lang.Integer uwBatchNo,
		java.lang.String uwRuleId,
		java.lang.String uwInfo,
		java.lang.Integer uwMasterId,
		java.lang.Integer operatorId,
		java.util.Date makeTime,
		java.util.Date modifyTime) {

		super (
			id,
			uwLayer,
			uwRelationId,
			uwBatchNo,
			uwRuleId,
			uwInfo,
			uwMasterId,
			operatorId,
			makeTime,
			modifyTime);
	}

/*[CONSTRUCTOR MARKER END]*/


}