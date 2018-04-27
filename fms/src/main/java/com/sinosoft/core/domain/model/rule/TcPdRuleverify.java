package com.sinosoft.core.domain.model.rule;

import com.sinosoft.core.domain.model.rule.base.BaseTcPdRuleverify;



public class TcPdRuleverify extends BaseTcPdRuleverify {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TcPdRuleverify () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TcPdRuleverify (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public TcPdRuleverify (
		java.lang.Integer id,
		java.lang.Integer riskId,
		java.lang.Integer ruleId,
		java.lang.String verifyType) {

		super (
			id,
			riskId,
			ruleId,
			verifyType);
	}

/*[CONSTRUCTOR MARKER END]*/


}