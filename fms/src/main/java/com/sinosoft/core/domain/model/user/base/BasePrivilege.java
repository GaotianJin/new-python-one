package com.sinosoft.core.domain.model.user.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the privilege table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="privilege"
 */

public abstract class BasePrivilege  implements Serializable {

	public static String REF = "Privilege";
	public static String PROP_NEXT = "Next";
	public static String PROP_NUM = "Num";
	public static String PROP_URL = "Url";
	public static String PROP_METHOD = "Method";
	public static String PROP_ID = "Id";
	public static String PROP_PRIVILEGECODE = "Privilegecode";
	public static String PROP_PRE = "Pre";
	public static String PROP_PID = "Pid";
	public static String PROP_PRIVILEGETYPE = "Privilegetype";
	public static String PROP_PRIVILEGENAME = "Privilegename";


	// constructors
	public BasePrivilege () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePrivilege (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String privilegecode;
	private java.lang.String privilegename;
	private java.lang.String privilegetype;
	private java.lang.String url;
	private java.lang.String pid;
	private java.lang.String method;
	private java.lang.String pre;
	private java.lang.String next;
	private java.lang.Integer num;

	// collections
	private java.util.Set<com.sinosoft.core.domain.model.user.Role2privilege> role2privileges;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
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
	 * Return the value associated with the column: privilegecode
	 */
	public java.lang.String getPrivilegecode () {
		return privilegecode;
	}

	/**
	 * Set the value related to the column: privilegecode
	 * @param privilegecode the privilegecode value
	 */
	public void setPrivilegecode (java.lang.String privilegecode) {
		this.privilegecode = privilegecode;
	}



	/**
	 * Return the value associated with the column: privilegename
	 */
	public java.lang.String getPrivilegename () {
		return privilegename;
	}

	/**
	 * Set the value related to the column: privilegename
	 * @param privilegename the privilegename value
	 */
	public void setPrivilegename (java.lang.String privilegename) {
		this.privilegename = privilegename;
	}



	/**
	 * Return the value associated with the column: privilegetype
	 */
	public java.lang.String getPrivilegetype () {
		return privilegetype;
	}

	/**
	 * Set the value related to the column: privilegetype
	 * @param privilegetype the privilegetype value
	 */
	public void setPrivilegetype (java.lang.String privilegetype) {
		this.privilegetype = privilegetype;
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



	/**
	 * Return the value associated with the column: pid
	 */
	public java.lang.String getPid () {
		return pid;
	}

	/**
	 * Set the value related to the column: pid
	 * @param pid the pid value
	 */
	public void setPid (java.lang.String pid) {
		this.pid = pid;
	}



	/**
	 * Return the value associated with the column: method
	 */
	public java.lang.String getMethod () {
		return method;
	}

	/**
	 * Set the value related to the column: method
	 * @param method the method value
	 */
	public void setMethod (java.lang.String method) {
		this.method = method;
	}



	/**
	 * Return the value associated with the column: pre
	 */
	public java.lang.String getPre () {
		return pre;
	}

	/**
	 * Set the value related to the column: pre
	 * @param pre the pre value
	 */
	public void setPre (java.lang.String pre) {
		this.pre = pre;
	}



	/**
	 * Return the value associated with the column: next
	 */
	public java.lang.String getNext () {
		return next;
	}

	/**
	 * Set the value related to the column: next
	 * @param next the next value
	 */
	public void setNext (java.lang.String next) {
		this.next = next;
	}



	/**
	 * Return the value associated with the column: num
	 */
	public java.lang.Integer getNum () {
		return num;
	}

	/**
	 * Set the value related to the column: num
	 * @param num the num value
	 */
	public void setNum (java.lang.Integer num) {
		this.num = num;
	}


@JsonIgnore
	/**
	 * Return the value associated with the column: Role2privileges
	 */
	public java.util.Set<com.sinosoft.core.domain.model.user.Role2privilege> getRole2privileges () {
		return role2privileges;
	}

	/**
	 * Set the value related to the column: Role2privileges
	 * @param role2privileges the Role2privileges value
	 */
	public void setRole2privileges (java.util.Set<com.sinosoft.core.domain.model.user.Role2privilege> role2privileges) {
		this.role2privileges = role2privileges;
	}

	public void addToRole2privileges (com.sinosoft.core.domain.model.user.Role2privilege role2privilege) {
		if (null == getRole2privileges()) setRole2privileges(new java.util.TreeSet<com.sinosoft.core.domain.model.user.Role2privilege>());
		getRole2privileges().add(role2privilege);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.user.Privilege)) return false;
		else {
			com.sinosoft.core.domain.model.user.Privilege privilege = (com.sinosoft.core.domain.model.user.Privilege) obj;
			if (null == this.getId() || null == privilege.getId()) return false;
			else return (this.getId().equals(privilege.getId()));
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