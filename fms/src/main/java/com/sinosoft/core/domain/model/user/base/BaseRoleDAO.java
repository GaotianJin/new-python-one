package com.sinosoft.core.domain.model.user.base;

import com.sinosoft.core.domain.model.user.dao.RoleDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseRoleDAO extends com.sinosoft.core.domain.model.user.dao._RootDAO {

	public BaseRoleDAO () {}

	// query name references


	public static RoleDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static RoleDAO getInstance () {
		if (null == instance) instance = new com.sinosoft.core.domain.model.user.dao.RoleDAO();
		return instance;
	}

  public Class getReferenceClass () {
        return com.sinosoft.core.domain.model.user.Role.class;
    }


    /**
     * Cast the object as a com.sinosoft.core.domain.model.user.Role
     */
    public com.sinosoft.core.domain.model.user.Role cast (Object object) {
        return (com.sinosoft.core.domain.model.user.Role) object;
    }

    public com.sinosoft.core.domain.model.user.Role load(java.lang.Integer key)
        throws org.hibernate.HibernateException {
        return (com.sinosoft.core.domain.model.user.Role) load(getReferenceClass(), key);
    }

    public com.sinosoft.core.domain.model.user.Role get(java.lang.Integer key)
        throws org.hibernate.HibernateException {
        return (com.sinosoft.core.domain.model.user.Role) get(getReferenceClass(), key);
    }

    public java.util.List loadAll()
        throws org.hibernate.HibernateException {
        return loadAll(getReferenceClass());
    }


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param role a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.sinosoft.core.domain.model.user.Role role)
		throws org.hibernate.HibernateException {
		return (java.lang.Integer) super.save(role);
	}



	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param role a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.sinosoft.core.domain.model.user.Role role)
		throws org.hibernate.HibernateException {
		saveOrUpdate((Object) role);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param role a transient instance containing updated state
	 */
	public void update(com.sinosoft.core.domain.model.user.Role role) 
		throws org.hibernate.HibernateException {
		update((Object) role);
	}



	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.Integer id)
		throws org.hibernate.HibernateException {
		delete((Object) load(id));
	}



	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param role the instance to be removed
	 */
	public void delete(com.sinosoft.core.domain.model.user.Role role)
		throws org.hibernate.HibernateException {
		delete((Object) role);
	}
	
	


}