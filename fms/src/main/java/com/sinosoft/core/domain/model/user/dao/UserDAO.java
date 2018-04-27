package com.sinosoft.core.domain.model.user.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sinosoft.core.domain.model.user.User;
import com.sinosoft.core.domain.model.user.base.BaseUserDAO;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.BDCompanyService;


@Repository
public class UserDAO extends BaseUserDAO {
	@Autowired
	private BDCompanyService bdCompanyService;
	public UserDAO () {}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<User> findByUsercodeAndCompanyCode(User user) {
		String sql = "select * from tcuser a where a.Usercode = '"
				+ user.getUsercode()
				+ "' and   a.company_id is not null  and (select company.company_code from bd_company company where company.dbid = "
				+ user.getCompanyId()
				+ ") like (select company.company_code from bd_company company where company.dbid = a.company_id)||'%'";
		return (ArrayList<User>)getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> findByUsername(User user) {
		return (ArrayList<User>) find("from User a where a.Username = ? ", user.getUsername());
	}

	@SuppressWarnings("unchecked")
	public ArrayList<User> findByUsercode(User user) {
		return (ArrayList<User>) find("from User a where a.Usercode = ?", user.getUsercode());
	}
	

	public Map<String, Object> getPageList(DataGridModel dgm, User user) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		String countQuery = "select count(*) from User user where 1=1 ";
		String fullQuery = "select user.Id,user.Username,user.Usercode,(select company.CompanyName from BDCompany as company where company.Dbid  = user.CompanyId ),"
		+"user.CompanyId  from User as user where 1=1 ";
		String orderString = "";
		if (StringUtils.hasLength(dgm.getSort()))
			orderString = " order by " + dgm.getSort() + " " + dgm.getOrder();
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (user != null) {
			if (StringUtils.hasLength(user.getUsername())) {
				sb.append(" and user.Username like :Username");
				params.put("Username", "%" + user.getUsername() + "%");
			}
			if (StringUtils.hasLength(user.getUsercode())) {
				sb.append(" and user.Usercode like :Usercode");
				params.put("Usercode", "%" + user.getUsercode() + "%");
			}
			if (user.getCompanyId() != null) {
			//暂时注释	sb.append(" and user.CompanyId in(" +bdCompanyService.getCompanyChildrenIDToString(new BigDecimal(
			//暂时//					.getCompanyId()_)) + ")");
//				params.put("CompanyId", user.getC--=ompanyId());
			}
		}
		Query queryTotal = getSessionFactory().getCurrentSession().createQuery(countQuery + sb.toString());
		Query queryList = getSessionFactory().getCurrentSession().createQuery(fullQuery + sb.toString() + orderString).setFirstResult((dgm.getPage() - 1) * dgm.getRows())
				.setMaxResults(dgm.getRows());
		if (params != null && !params.isEmpty()) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				queryTotal.setParameter(key, params.get(key));
				queryList.setParameter(key, params.get(key));
			}
		}
		int total = ((Long) queryTotal.uniqueResult()).intValue();		
		List list = new ArrayList();
		Iterator<List> it = queryList.list().iterator();
		while(it.hasNext()){
			Object o = it.next();
			Map map = new HashMap();
			map.put("id", ((Object[])o)[0]);
			map.put("username", ((Object[])o)[1]);
			map.put("usercode", ((Object[])o)[2]);
			map.put("companyName", ((Object[])o)[3]);
			map.put("companyId", ((Object[])o)[4]);


			list.add(map);
		}
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
}