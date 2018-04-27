package com.sinosoft.util;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Service
public class SqlQueryUtil extends SpringBeanAutowiringSupport{
	
	@Autowired
	public   SessionFactory sessionFactory ;
	public  List querySql(String sql){
		return sessionFactory.getCurrentSession().createSQLQuery(sql).list();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
