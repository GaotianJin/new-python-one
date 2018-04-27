package com.sinosoft.core.domain.model.user.base;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * This is an object that contains data related to the tcuser table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="tcuser"
 */

public abstract class BaseUser  implements Serializable {

	public static String REF = "User";
	public static String PROP_COMPANY_ID = "CompanyId";
	public static String PROP_PASSWORD = "Password";
	public static String PROP_USERNAME = "Username";
	public static String PROP_ID = "Id";
	public static String PROP_USERCODE = "Usercode";


	// constructors
	public BaseUser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUser (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String usercode;
	private java.lang.String username;
	private java.lang.String password;
	private java.lang.Integer companyId;

	// collections
	private java.util.Set<com.sinosoft.core.domain.model.user.User2role> user2roles;



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
	 * Return the value associated with the column: usercode
	 */
	public java.lang.String getUsercode () {
		return usercode;
	}

	/**
	 * Set the value related to the column: usercode
	 * @param usercode the usercode value
	 */
	public void setUsercode (java.lang.String usercode) {
		this.usercode = usercode;
	}



	/**
	 * Return the value associated with the column: username
	 */
	public java.lang.String getUsername () {
		return username;
	}

	/**
	 * Set the value related to the column: username
	 * @param username the username value
	 */
	public void setUsername (java.lang.String username) {
		this.username = username;
	}



	/**
	 * Return the value associated with the column: password
	 */
	public java.lang.String getPassword () {
		return password;
	}

	/**
	 * Set the value related to the column: password
	 * @param password the password value
	 */
	public void setPassword (java.lang.String password) {
		this.password = password;
	}



	/**
	 * Return the value associated with the column: company_id
	 */
	public java.lang.Integer getCompanyId () {
		return companyId;
	}

	/**
	 * Set the value related to the column: company_id
	 * @param companyId the company_id value
	 */
	public void setCompanyId (java.lang.Integer companyId) {
		this.companyId = companyId;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.sinosoft.core.domain.model.user.User)) return false;
		else {
			com.sinosoft.core.domain.model.user.User user = (com.sinosoft.core.domain.model.user.User) obj;
			if (null == this.getId() || null == user.getId()) return false;
			else return (this.getId().equals(user.getId()));
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