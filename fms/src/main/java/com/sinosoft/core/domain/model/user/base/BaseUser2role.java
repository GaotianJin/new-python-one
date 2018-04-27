package com.sinosoft.core.domain.model.user.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the user2role table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="user2role"
 */

public abstract class BaseUser2role  implements Serializable {

	public static String REF = "User2role";
	public static String PROP_USER = "User";
	public static String PROP_ROLE = "Role";
	public static String PROP_ID = "Id";


	// constructors
	public BaseUser2role () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUser2role (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// many to one
	private com.sinosoft.core.domain.model.user.Role role;
	private com.sinosoft.core.domain.model.user.User user;



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



	/**
	 * Return the value associated with the column: user_id
	 */
	public com.sinosoft.core.domain.model.user.User getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: user_id
	 * @param user the user_id value
	 */
	public void setUser (com.sinosoft.core.domain.model.user.User user) {
		this.user = user;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.user.User2role)) return false;
		else {
			com.sinosoft.core.domain.model.user.User2role user2role = (com.sinosoft.core.domain.model.user.User2role) obj;
			if (null == this.getId() || null == user2role.getId()) return false;
			else return (this.getId().equals(user2role.getId()));
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