package com.sinosoft.core.domain.model.base;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Based on Hibernate Synchronizer For more information or documentation, visit
 * The Hibernate Synchronizer page at http://www.binamics.com/hibernatesync or
 * contact Joe Hudson at joe@binamics.com.
 * 
 * @author Joe Hudson
 * @author Todd McGrath mcgrath@supergloo.com
 */
public abstract class SpringHibernate3BaseRootDAO extends HibernateTemplate implements Serializable {

	protected final transient Log log = LogFactory.getLog(SpringHibernate3BaseRootDAO.class);

	/**
	 * Return the name of the configuration file to be used with this DAO or
	 * null if default
	 */
	public String getConfigurationFileName() {
		return null;
	}

	/**
	 * Return the specific Object class that will be used for class-specific
	 * implementation of this DAO.
	 * 
	 * @return the reference Class
	 */
	protected abstract Class getReferenceClass();

	/**
	 * Return a Criteria object that relates to the DAO's table
	 */
	protected Criteria createCriteria(Session s) throws DataAccessException {
		return s.createCriteria(getReferenceClass());
	}

	/**
	 * Return a Criteria object that relates to the DAO's table
	 */
	public Criteria createCriteria() throws HibernateException {
		Session s = getSessionFactory().openSession();
		return s.createCriteria(getReferenceClass());
	}

	/**
	 * Return the property of the class you would like to use for default
	 * ordering
	 * 
	 * @return the property name
	 */
	public String getDefaultOrderProperty() {
		return null;
	}

}