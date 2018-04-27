package com.sinosoft.core.domain.model.rule.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinosoft.core.domain.model.rule.TcPdRuleverify;
import com.sinosoft.core.domain.model.rule.base.BaseTcPdRuleverifyDAO;


@Repository
public class TcPdRuleverifyDAO extends BaseTcPdRuleverifyDAO {

	public TcPdRuleverifyDAO () {}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public List<TcPdRuleverify> findByRiskAndType(int riskId,String verifyType) {
		String hql = " from TcPdRuleverify  where risk_id = "+riskId+" and verify_type = '"+verifyType+"'";
		Query query =   this.getSessionFactory().getCurrentSession().createQuery(hql);
		List<TcPdRuleverify> list = query.list();
		return list;
	}
}