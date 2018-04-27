package com.sinosoft.core.domain.model.workflow.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the workflow table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="workflow"
 */

public abstract class BaseWorkflow  implements Serializable {

	public static String REF = "Workflow";
	public static String PROP_NAME = "Name";
	public static String PROP_URL = "Url";
	public static String PROP_ID = "Id";


	// constructors
	public BaseWorkflow () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseWorkflow (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String url;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: url
	 */
	public java.lang.String getUrl () {
		return url;
	}

	/**
	 * Set the value related to the column: url
	 * @param url the url value
	 */
	public void setUrl (java.lang.String url) {
		this.url = url;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.workflow.Workflow)) return false;
		else {
			com.sinosoft.core.domain.model.workflow.Workflow workflow = (com.sinosoft.core.domain.model.workflow.Workflow) obj;
			if (null == this.getId() || null == workflow.getId()) return false;
			else return (this.getId().equals(workflow.getId()));
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