package com.sinosoft.core.application.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sinosoft.core.interfaces.util.DataGridModel;

/**
 * Hibernate工具
 * @author Eric
 *
 */
public class HibernateUtil {

	/**
	 * 查询菜单列表
	 */
	/**
	 * 查询菜单列表
	 */
	public static Map<String, Object> getPageList(Session session, DataGridModel dgm, String countQuery, String fullQuery, String orderString, StringBuffer sb, Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String, Object>(2);

		Query queryTotal = session.createQuery(countQuery + sb.toString());
		Query queryList = session.createQuery(fullQuery + sb.toString() + orderString).setFirstResult((dgm.getPage() - 1) * dgm.getRows()).setMaxResults(dgm.getRows());
		if (params != null && !params.isEmpty()) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				queryTotal.setParameter(key, params.get(key));
				queryList.setParameter(key, params.get(key));
			}
		}
		int total = ((Long) queryTotal.uniqueResult()).intValue(); // 这里必须先转成Long再取intValue，不能转成Integer
		List list = queryList.list();
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
}
