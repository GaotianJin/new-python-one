package com.sinosoft.core.domain.model.rule.base;

import com.sinosoft.core.domain.model.rule.dao.TfNbUwErrorTraceDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseTfNbUwErrorTraceDAO extends com.sinosoft.core.domain.model.rule.dao._RootDAO {

	public BaseTfNbUwErrorTraceDAO () {}

	// query name references


	public static TfNbUwErrorTraceDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static TfNbUwErrorTraceDAO getInstance () {
		if (null == instance) instance = new com.sinosoft.core.domain.model.rule.dao.TfNbUwErrorTraceDAO();
		return instance;
	}

  public Class getReferenceClass () {
        return com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace.class;
    }


    /**
     * Cast the object as a com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace
     */
    public com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace cast (Object object) {
        return (com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace) object;
    }

    public com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace load(java.lang.Integer key)
        throws org.hibernate.HibernateException {
        return (com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace) load(getReferenceClass(), key);
    }

    public com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace get(java.lang.Integer key)
        throws org.hibernate.HibernateException {
        return (com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace) get(getReferenceClass(), key);
    }

    public java.util.List loadAll()
        throws org.hibernate.HibernateException {
        return loadAll(getReferenceClass());
    }


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param tfNbUwErrorTrace a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace tfNbUwErrorTrace)
		throws org.hibernate.HibernateException {
		return (java.lang.Integer) super.save(tfNbUwErrorTrace);
	}



	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param tfNbUwErrorTrace a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace tfNbUwErrorTrace)
		throws org.hibernate.HibernateException {
		saveOrUpdate((Object) tfNbUwErrorTrace);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param tfNbUwErrorTrace a transient instance containing updated state
	 */
	public void update(com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace tfNbUwErrorTrace) 
		throws org.hibernate.HibernateException {
		update((Object) tfNbUwErrorTrace);
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
	 * @param tfNbUwErrorTrace the instance to be removed
	 */
	public void delete(com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace tfNbUwErrorTrace)
		throws org.hibernate.HibernateException {
		delete((Object) tfNbUwErrorTrace);
	}
	
	


}