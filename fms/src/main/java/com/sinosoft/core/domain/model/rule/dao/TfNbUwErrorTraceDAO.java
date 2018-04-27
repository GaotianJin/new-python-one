package com.sinosoft.core.domain.model.rule.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinosoft.core.domain.model.rule.base.BaseTfNbUwErrorTraceDAO;


@Repository
public class TfNbUwErrorTraceDAO extends BaseTfNbUwErrorTraceDAO {

	public TfNbUwErrorTraceDAO () {}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}