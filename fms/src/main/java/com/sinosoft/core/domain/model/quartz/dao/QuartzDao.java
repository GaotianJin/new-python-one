package com.sinosoft.core.domain.model.quartz.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.quartz.Constant;

@Repository("quartzDao")
@SuppressWarnings("unchecked")
public class QuartzDao extends com.sinosoft.core.domain.model.user.dao._RootDAO {

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Map<String, Object> getPageList(DataGridModel dgm, String jobName, String trigger_name) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		String countQuery = "select count(*) from QRTZ_TRIGGERS q where 1=1 ";
		String fullQuery = "select trigger_name,trigger_group,job_name,next_fire_time,prev_fire_time,trigger_state,trigger_type,start_time,end_time,"
				+ "(select cron_expression from qrtz_cron_triggers where q.trigger_name = trigger_name and q.trigger_group = trigger_group) as cron_expression,"
				+ "(select repeat_count from qrtz_simple_triggers where q.trigger_name = trigger_name and q.trigger_group = trigger_group),"
				+ "(select repeat_interval/1000 from qrtz_simple_triggers where q.trigger_name = trigger_name and q.trigger_group = trigger_group),"
				+ "(select times_triggered from qrtz_simple_triggers where q.trigger_name = trigger_name and q.trigger_group = trigger_group)" + " from QRTZ_TRIGGERS q where 1=1 ";
		System.out.println("fullQuery:"+fullQuery);
		String orderString = "";
		if (StringUtils.hasLength(dgm.getSort()))
			orderString = " order by " + dgm.getSort() + " " + dgm.getOrder();
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (jobName != null) {
			if (StringUtils.hasLength(jobName)) {
				sb.append(" and q.job_name like '%" + jobName + "%'");
			}
		}
		if (trigger_name != null) {
			if (StringUtils.hasLength(trigger_name)) {
				sb.append(" and q.Trigger_name like  '%" + trigger_name + "%'");
			}
		}
		Query queryTotal = getSessionFactory().getCurrentSession().createSQLQuery(countQuery + sb.toString());
		Query queryList = getSessionFactory().getCurrentSession().createSQLQuery(fullQuery + sb.toString() + orderString).setFirstResult((dgm.getPage() - 1) * dgm.getRows())
				.setMaxResults(dgm.getRows());
		if (params != null && !params.isEmpty()) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				queryTotal.setParameter(key, params.get(key));
				queryList.setParameter(key, params.get(key));
			}
		}
		ArrayList<Integer> countlist = (ArrayList<Integer>) queryTotal.list();
		Number total = ((Number) countlist.get(0)).intValue();
		List list = new ArrayList();
		Iterator<List> it = queryList.list().iterator();
		long val = 0;
		while (it.hasNext()) {
			Object o = it.next();
			Map map = new HashMap();
			map.put("trigger_name", ((Object[]) o)[0]);
			map.put("trigger_group", ((Object[]) o)[1]);
			map.put("job_name", ((Object[]) o)[2]);
			map.put("next_fire_time", ((Object[]) o)[3]);
			map.put("prev_fire_time", ((Object[]) o)[4]);
			map.put("trigger_state", ((Object[]) o)[5]);
			map.put("trigger_type", ((Object[]) o)[6]);
			map.put("start_time", ((Object[]) o)[7]);
			map.put("end_time", ((Object[]) o)[8]);
			map.put("cron_expression", ((Object[]) o)[9]);
			map.put("repeat_count", ((Object[]) o)[10]);
			map.put("repeat_internal", ((Object[]) o)[11]);
			map.put("times_triggered", ((Object[]) o)[12]);
			val = MapUtils.getLongValue(map, "next_fire_time");
			if (val > 0) {
				map.put("next_fire_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}
			val = MapUtils.getLongValue(map, "prev_fire_time");
			if (val > 0) {
				map.put("prev_fire_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}
			val = MapUtils.getLongValue(map, "start_time");
			if (val > 0) {
				map.put("start_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}
			val = MapUtils.getLongValue(map, "end_time");
			if (val > 0) {
				map.put("end_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}
			map.put("trigger_state", Constant.status.get(MapUtils.getString(map, "trigger_state")));

			list.add(map);
		}
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	public List queryJob() {
		List result = new ArrayList();
		String query = "select job_name from qrtz_job_details q where 1=1 ";
		Query queryList = getSessionFactory().getCurrentSession().createSQLQuery(query);
		Iterator<List> it = queryList.list().iterator();
		while (it.hasNext()) {
			Object o = it.next();
			Map map = new HashMap();
			map.put("job_name", o);
			result.add(map);
		}
		return result;
	}

	@Override
	protected Class getReferenceClass() {
		return com.sinosoft.core.domain.model.quartz.dao.QuartzDao.class;
	}
}
