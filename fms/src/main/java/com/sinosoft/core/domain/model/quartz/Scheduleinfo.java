package com.sinosoft.core.domain.model.quartz;

import com.sinosoft.core.domain.model.quartz.base.BaseScheduleinfo;



public class Scheduleinfo extends BaseScheduleinfo {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Scheduleinfo () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Scheduleinfo (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Scheduleinfo (
		java.lang.String id,
		java.lang.String scheduleName,
		java.lang.String cronExpression,
		java.lang.String targetObject,
		java.lang.String targetMethod,
		java.lang.String concurrent) {

		super (
			id,
			scheduleName,
			cronExpression,
			targetObject,
			targetMethod,
			concurrent);
	}

/*[CONSTRUCTOR MARKER END]*/


}