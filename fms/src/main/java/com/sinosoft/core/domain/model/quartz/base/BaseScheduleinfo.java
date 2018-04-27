package com.sinosoft.core.domain.model.quartz.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the scheduleinfo table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="scheduleinfo"
 */

public abstract class BaseScheduleinfo  implements Serializable {

	public static String REF = "Scheduleinfo";
	public static String PROP_TARGET_OBJECT = "TargetObject";
	public static String PROP_TARGET_METHOD = "TargetMethod";
	public static String PROP_CRON_EXPRESSION = "CronExpression";
	public static String PROP_ID = "Id";
	public static String PROP_CONCURRENT = "Concurrent";
	public static String PROP_SCHEDULE_NAME = "ScheduleName";


	// constructors
	public BaseScheduleinfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseScheduleinfo (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseScheduleinfo (
		java.lang.String id,
		java.lang.String scheduleName,
		java.lang.String cronExpression,
		java.lang.String targetObject,
		java.lang.String targetMethod,
		java.lang.String concurrent) {

		this.setId(id);
		this.setScheduleName(scheduleName);
		this.setCronExpression(cronExpression);
		this.setTargetObject(targetObject);
		this.setTargetMethod(targetMethod);
		this.setConcurrent(concurrent);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String concurrent;
	private java.lang.String cronExpression;
	private java.lang.String scheduleName;
	private java.lang.String targetMethod;
	private java.lang.String targetObject;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="ID"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: Concurrent
	 */
	public java.lang.String getConcurrent () {
		return concurrent;
	}

	/**
	 * Set the value related to the column: Concurrent
	 * @param concurrent the Concurrent value
	 */
	public void setConcurrent (java.lang.String concurrent) {
		this.concurrent = concurrent;
	}



	/**
	 * Return the value associated with the column: CronExpression
	 */
	public java.lang.String getCronExpression () {
		return cronExpression;
	}

	/**
	 * Set the value related to the column: CronExpression
	 * @param cronExpression the CronExpression value
	 */
	public void setCronExpression (java.lang.String cronExpression) {
		this.cronExpression = cronExpression;
	}



	/**
	 * Return the value associated with the column: ScheduleName
	 */
	public java.lang.String getScheduleName () {
		return scheduleName;
	}

	/**
	 * Set the value related to the column: ScheduleName
	 * @param scheduleName the ScheduleName value
	 */
	public void setScheduleName (java.lang.String scheduleName) {
		this.scheduleName = scheduleName;
	}



	/**
	 * Return the value associated with the column: TargetMethod
	 */
	public java.lang.String getTargetMethod () {
		return targetMethod;
	}

	/**
	 * Set the value related to the column: TargetMethod
	 * @param targetMethod the TargetMethod value
	 */
	public void setTargetMethod (java.lang.String targetMethod) {
		this.targetMethod = targetMethod;
	}



	/**
	 * Return the value associated with the column: TargetObject
	 */
	public java.lang.String getTargetObject () {
		return targetObject;
	}

	/**
	 * Set the value related to the column: TargetObject
	 * @param targetObject the TargetObject value
	 */
	public void setTargetObject (java.lang.String targetObject) {
		this.targetObject = targetObject;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.quartz.Scheduleinfo)) return false;
		else {
			com.sinosoft.core.domain.model.quartz.Scheduleinfo scheduleinfo = (com.sinosoft.core.domain.model.quartz.Scheduleinfo) obj;
			if (null == this.getId() || null == scheduleinfo.getId()) return false;
			else return (this.getId().equals(scheduleinfo.getId()));
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