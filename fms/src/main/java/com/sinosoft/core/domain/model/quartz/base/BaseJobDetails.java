package com.sinosoft.core.domain.model.quartz.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the JOB_DETAILS table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="JOB_DETAILS"
 */

public abstract class BaseJobDetails  implements Serializable {

	public static String REF = "JobDetails";
	public static String PROP_JOB_NAME = "JobName";
	public static String PROP_JOB_TNAME = "JobTname";
	public static String PROP_MAKEDATE = "Makedate";
	public static String PROP_ID = "Id";
	public static String PROP_REMARK2 = "Remark2";
	public static String PROP_REMARK1 = "Remark1";


	// constructors
	public BaseJobDetails () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseJobDetails (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String jobTname;
	private java.lang.String jobName;
	private java.lang.String remark1;
	private java.lang.String remark2;
	private java.util.Date makedate;



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
	 * Return the value associated with the column: JOB_TNAME
	 */
	public java.lang.String getJobTname () {
		return jobTname;
	}

	/**
	 * Set the value related to the column: JOB_TNAME
	 * @param jobTname the JOB_TNAME value
	 */
	public void setJobTname (java.lang.String jobTname) {
		this.jobTname = jobTname;
	}



	/**
	 * Return the value associated with the column: JOB_NAME
	 */
	public java.lang.String getJobName () {
		return jobName;
	}

	/**
	 * Set the value related to the column: JOB_NAME
	 * @param jobName the JOB_NAME value
	 */
	public void setJobName (java.lang.String jobName) {
		this.jobName = jobName;
	}



	/**
	 * Return the value associated with the column: REMARK1
	 */
	public java.lang.String getRemark1 () {
		return remark1;
	}

	/**
	 * Set the value related to the column: REMARK1
	 * @param remark1 the REMARK1 value
	 */
	public void setRemark1 (java.lang.String remark1) {
		this.remark1 = remark1;
	}



	/**
	 * Return the value associated with the column: REMARK2
	 */
	public java.lang.String getRemark2 () {
		return remark2;
	}

	/**
	 * Set the value related to the column: REMARK2
	 * @param remark2 the REMARK2 value
	 */
	public void setRemark2 (java.lang.String remark2) {
		this.remark2 = remark2;
	}



	/**
	 * Return the value associated with the column: MAKEDATE
	 */
	public java.util.Date getMakedate () {
		return makedate;
	}

	/**
	 * Set the value related to the column: MAKEDATE
	 * @param makedate the MAKEDATE value
	 */
	public void setMakedate (java.util.Date makedate) {
		this.makedate = makedate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.quartz.JobDetails)) return false;
		else {
			com.sinosoft.core.domain.model.quartz.JobDetails jobDetails = (com.sinosoft.core.domain.model.quartz.JobDetails) obj;
			if (null == this.getId() || null == jobDetails.getId()) return false;
			else return (this.getId().equals(jobDetails.getId()));
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