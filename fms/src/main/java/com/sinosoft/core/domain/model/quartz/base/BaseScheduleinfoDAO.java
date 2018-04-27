package com.sinosoft.core.domain.model.quartz.base;

import com.sinosoft.core.domain.model.quartz.dao.ScheduleinfoDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseScheduleinfoDAO extends com.sinosoft.core.domain.model.quartz.dao._RootDAO {

	public BaseScheduleinfoDAO () {}

	// query name references


	public static ScheduleinfoDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static ScheduleinfoDAO getInstance () {
		if (null == instance) instance = new com.sinosoft.core.domain.model.quartz.dao.ScheduleinfoDAO();
		return instance;
	}

  public Class getReferenceClass () {
        return com.sinosoft.core.domain.model.quartz.Scheduleinfo.class;
    }


    /**
     * Cast the object as a com.sinosoft.core.domain.model.quartz.Scheduleinfo
     */
    public com.sinosoft.core.domain.model.quartz.Scheduleinfo cast (Object object) {
        return (com.sinosoft.core.domain.model.quartz.Scheduleinfo) object;
    }

    public com.sinosoft.core.domain.model.quartz.Scheduleinfo load(java.lang.String key)
        throws org.springframework.dao.DataAccessException {
        return (com.sinosoft.core.domain.model.quartz.Scheduleinfo) load(getReferenceClass(), key);
    }

    public com.sinosoft.core.domain.model.quartz.Scheduleinfo get(java.lang.String key)
        throws org.springframework.dao.DataAccessException {
        return (com.sinosoft.core.domain.model.quartz.Scheduleinfo) get(getReferenceClass(), key);
    }

    public java.util.List loadAll()
        throws org.springframework.dao.DataAccessException {
        return loadAll(getReferenceClass());
    }


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param scheduleinfo a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(com.sinosoft.core.domain.model.quartz.Scheduleinfo scheduleinfo)
		throws org.springframework.dao.DataAccessException {
		return (java.lang.String) super.save(scheduleinfo);
	}



	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param scheduleinfo a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.sinosoft.core.domain.model.quartz.Scheduleinfo scheduleinfo)
		throws org.springframework.dao.DataAccessException {
		saveOrUpdate((Object) scheduleinfo);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param scheduleinfo a transient instance containing updated state
	 */
	public void update(com.sinosoft.core.domain.model.quartz.Scheduleinfo scheduleinfo) 
		throws org.springframework.dao.DataAccessException {
		update((Object) scheduleinfo);
	}



	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.String id)
		throws org.springframework.dao.DataAccessException {
		delete((Object) load(id));
	}



	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param scheduleinfo the instance to be removed
	 */
	public void delete(com.sinosoft.core.domain.model.quartz.Scheduleinfo scheduleinfo)
		throws org.springframework.dao.DataAccessException {
		delete((Object) scheduleinfo);
	}
	
	


}