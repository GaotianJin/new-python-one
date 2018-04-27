package com.sinosoft.core.domain.model.rule;

import com.sinosoft.core.domain.model.rule.base.BaseTfNbUwError;



public class TfNbUwError extends BaseTfNbUwError {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TfNbUwError () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TfNbUwError (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public TfNbUwError (
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

		super (
			id,
			uwLayer,
			uwRelationId,
			uwBatchNo,
			uwRuleId,
			uwInfo,
			uwType,
			operatorId,
			makeTime,
			modifyTime);
	}

/*[CONSTRUCTOR MARKER END]*/


}