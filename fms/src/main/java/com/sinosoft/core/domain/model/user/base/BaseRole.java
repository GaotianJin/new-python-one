package com.sinosoft.core.domain.model.user.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the role table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="role"
 */

public abstract class BaseRole  implements Serializable {

	public static String REF = "Role";
	public static String PROP_ROLENAME = "Rolename";
	public static String PROP_ROLECODE = "Rolecode";
	public static String PROP_ID = "Id";


	// constructors
	public BaseRole () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRole (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String rolecode;
	private java.lang.String rolename;

	// collections
	private java.util.Set<com.sinosoft.core.domain.model.user.User2role> user2roles;
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
	 * Return the value associated with the column: rolecode
	 */
	public java.lang.String getRolecode () {
		return rolecode;
	}

	/**
	 * Set the value related to the column: rolecode
	 * @param rolecode the rolecode value
	 */
	public void setRolecode (java.lang.String rolecode) {
		this.rolecode = rolecode;
	}



	/**
	 * Return the value associated with the column: rolename
	 */
	public java.lang.String getRolename () {
		return rolename;
	}

	/**
	 * Set the value related to the column: rolename
	 * @param rolename the rolename value
	 */
	public void setRolename (java.lang.String rolename) {
		this.rolename = rolename;
	}


@JsonIgnore
	/**
	 * Return the value associated with the column: User2roles
	 */
	public java.util.Set<com.sinosoft.core.domain.model.user.User2role> getUser2roles () {
		return user2roles;
	}

	/**
	 * Set the value related to the column: User2roles
	 * @param user2roles the User2roles value
	 */
	public void setUser2roles (java.util.Set<com.sinosoft.core.domain.model.user.User2role> user2roles) {
		this.user2roles = user2roles;
	}

	public void addToUser2roles (com.sinosoft.core.domain.model.user.User2role user2role) {
		if (null == getUser2roles()) setUser2roles(new java.util.TreeSet<com.sinosoft.core.domain.model.user.User2role>());
		getUser2roles().add(user2role);
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
		if (!(obj instanceof com.sinosoft.core.domain.model.user.Role)) return false;
		else {
			com.sinosoft.core.domain.model.user.Role role = (com.sinosoft.core.domain.model.user.Role) obj;
			if (null == this.getId() || null == role.getId()) return false;
			else return (this.getId().equals(role.getId()));
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