package com.sinosoft.core.domain.model.quartz.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinosoft.core.domain.model.quartz.base.BaseJobDetailsDAO;


@Repository
public class JobDetailsDAO extends BaseJobDetailsDAO {

	public JobDetailsDAO () {}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}