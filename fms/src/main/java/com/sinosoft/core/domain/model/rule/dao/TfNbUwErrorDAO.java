package com.sinosoft.core.domain.model.rule.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinosoft.core.domain.model.rule.TfNbUwError;
import com.sinosoft.core.domain.model.rule.base.BaseTfNbUwErrorDAO;


@Repository
public class TfNbUwErrorDAO extends BaseTfNbUwErrorDAO {

	public TfNbUwErrorDAO () {}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 通过相关条件获取TfNbUwError列表
	 * @param uWRelationID
	 * @param uwLayer
	 * @param verifyType
	 * @return List<TfNbUwError>
	 */
	public List<TfNbUwError> getTfNbUwErrorList(int uWRelationID,
			String uwLayer, String verifyType) {
		String hql = "from TfNbUwError where uw_relation_id = "
				+ uWRelationID
				+ " and uw_layer = '"
				+ uwLayer
				+ "' and  uw_type = '" + verifyType + "'";
		List<TfNbUwError> tfNbUwErrorList = this.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return tfNbUwErrorList;
	}
}