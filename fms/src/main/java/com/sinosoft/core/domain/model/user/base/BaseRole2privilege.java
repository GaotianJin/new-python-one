package com.sinosoft.core.domain.model.user.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the role2privilege table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="role2privilege"
 */

public abstract class BaseRole2privilege  implements Serializable {

	public static String REF = "Role2privilege";
	public static String PROP_PRIVILEGE = "Privilege";
	public static String PROP_ROLE = "Role";
	public static String PROP_ID = "Id";


	// constructors
	public BaseRole2privilege () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRole2privilege (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// many to one
	private com.sinosoft.core.domain.model.user.Role role;
	private com.sinosoft.core.domain.model.user.Privilege privilege;



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
	 * Return the value associated with the column: privilege_id
	 */
	public com.sinosoft.core.domain.model.user.Privilege getPrivilege () {
		return privilege;
	}

	/**
	 * Set the value related to the column: privilege_id
	 * @param privilege the privilege_id value
	 */
	public void setPrivilege (com.sinosoft.core.domain.model.user.Privilege privilege) {
		this.privilege = privilege;
	}



	/**
	 * Return the value associated with the column: role_id
	 */
	public com.sinosoft.core.domain.model.user.Role getRole () {
		return role;
	}

	/**
	 * Set the value related to the column: role_id
	 * @param role the role_id value
	 */
	public void setRole (com.sinosoft.core.domain.model.user.Role role) {
		this.role = role;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.user.Role2privilege)) return false;
		else {
			com.sinosoft.core.domain.model.user.Role2privilege role2privilege = (com.sinosoft.core.domain.model.user.Role2privilege) obj;
			if (null == this.getId() || null == role2privilege.getId()) return false;
			else return (this.getId().equals(role2privilege.getId()));
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