package com.sinosoft.core.domain.model.quartz.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinosoft.core.domain.model.quartz.base.BaseScheduleinfoDAO;


@Repository
public class ScheduleinfoDAO extends BaseScheduleinfoDAO {

	public ScheduleinfoDAO () {}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}