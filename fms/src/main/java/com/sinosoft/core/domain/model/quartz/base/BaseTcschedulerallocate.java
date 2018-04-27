package com.sinosoft.core.domain.model.quartz.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the TCSCHEDULERALLOCATE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="TCSCHEDULERALLOCATE"
 */

public abstract class BaseTcschedulerallocate  implements Serializable {

	public static String REF = "Tcschedulerallocate";
	public static String PROP_JOBTNAME2 = "Jobtname2";
	public static String PROP_STARTDATE = "Startdate";
	public static String PROP_OTHER = "Other";
	public static String PROP_JOBTNAME1 = "Jobtname1";
	public static String PROP_ENDDATE = "Enddate";
	public static String PROP_ID = "Id";
	public static String PROP_VERIFYSTATE = "Verifystate";


	// constructors
	public BaseTcschedulerallocate () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTcschedulerallocate (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String jobtname1;
	private java.lang.String jobtname2;
	private java.util.Date startdate;
	private java.util.Date enddate;
	private java.lang.String verifystate;
	private java.lang.String other;



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
	 * Return the value associated with the column: JOBTNAME1
	 */
	public java.lang.String getJobtname1 () {
		return jobtname1;
	}

	/**
	 * Set the value related to the column: JOBTNAME1
	 * @param jobtname1 the JOBTNAME1 value
	 */
	public void setJobtname1 (java.lang.String jobtname1) {
		this.jobtname1 = jobtname1;
	}



	/**
	 * Return the value associated with the column: JOBTNAME2
	 */
	public java.lang.String getJobtname2 () {
		return jobtname2;
	}

	/**
	 * Set the value related to the column: JOBTNAME2
	 * @param jobtname2 the JOBTNAME2 value
	 */
	public void setJobtname2 (java.lang.String jobtname2) {
		this.jobtname2 = jobtname2;
	}



	/**
	 * Return the value associated with the column: STARTDATE
	 */
	public java.util.Date getStartdate () {
		return startdate;
	}

	/**
	 * Set the value related to the column: STARTDATE
	 * @param startdate the STARTDATE value
	 */
	public void setStartdate (java.util.Date startdate) {
		this.startdate = startdate;
	}



	/**
	 * Return the value associated with the column: ENDDATE
	 */
	public java.util.Date getEnddate () {
		return enddate;
	}

	/**
	 * Set the value related to the column: ENDDATE
	 * @param enddate the ENDDATE value
	 */
	public void setEnddate (java.util.Date enddate) {
		this.enddate = enddate;
	}



	/**
	 * Return the value associated with the column: VERIFYSTATE
	 */
	public java.lang.String getVerifystate () {
		return verifystate;
	}

	/**
	 * Set the value related to the column: VERIFYSTATE
	 * @param verifystate the VERIFYSTATE value
	 */
	public void setVerifystate (java.lang.String verifystate) {
		this.verifystate = verifystate;
	}



	/**
	 * Return the value associated with the column: OTHER
	 */
	public java.lang.String getOther () {
		return other;
	}

	/**
	 * Set the value related to the column: OTHER
	 * @param other the OTHER value
	 */
	public void setOther (java.lang.String other) {
		this.other = other;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.quartz.Tcschedulerallocate)) return false;
		else {
			com.sinosoft.core.domain.model.quartz.Tcschedulerallocate tcschedulerallocate = (com.sinosoft.core.domain.model.quartz.Tcschedulerallocate) obj;
			if (null == this.getId() || null == tcschedulerallocate.getId()) return false;
			else return (this.getId().equals(tcschedulerallocate.getId()));
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