package com.sinosoft.core.domain.model.workflow.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinosoft.core.domain.model.workflow.base.BaseWorkflowDAO;
import com.sinosoft.util.DateUtils;

@Repository
public class WorkflowDAO extends BaseWorkflowDAO {

	public WorkflowDAO() {
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public List queryTaskByID(String id) {
		String sql = "select task.assignee_,actinst.activity_name_,to_char(task.create_,'yyyy-mm-dd hh:mi:ss'),to_char(task.end_,'yyyy-mm-dd hh:mi:ss') "
				+ "from jbpm4_hist_task task,jbpm4_hist_actinst actinst  where task.execution_ like '"
				+ id
				+ "%' and task.dbid_ = actinst.htask_ and task.state_ = 'completed' "
				+ "order by task.create_";
		return getSessionFactory().getCurrentSession().createSQLQuery(sql)
				.list();
	}

	/**
	 * 查询任务
	 * 
	 * @param userId
	 * @param bussName
	 * @param key
	 * @param workFlowName
	 * @return List
	 * @throws ParseException
	 */
	public List<HashMap<String, Object>> queryTask(String userId,
			String bussName, String key, String workFlowName, String startDate,
			String endDate) {
		List<HashMap<String, Object>> listReturn = new ArrayList<HashMap<String, Object>>();
		String sql = "select t.execution_id_ as ID,"
				+ " t.name_ as ActivityName,"
				+ "  to_char(t.create_,'YYYY-MM-DD hh24:mi:ss') as CreateTime, "
				+ "to_char(t.duedate_,'YYYY-MM-DD hh24:mi:ss') as DueTime,"
				+ "(select key_ from JBPM4_EXECUTION e1 where e.instance_ = e1.instance_ and e1.key_ is not null ) as BussNo,"
				+ "t.assignee_ as Assignee,t.dbid_  from  JBPM4_TASK t,JBPM4_EXECUTION e where t.execution_ = e.dbid_";
		if (userId != null && !"".equals(userId)) {
			sql += " and t.assignee_ = '" + userId + "'";
		}
		if (bussName != null && !"".equals(bussName)) {
			sql += " and t.name_ = '" + bussName + "'";
		}
		if (key != null && !"".equals(key)) {
			sql += " and (e.key_ = '"
					+ key
					+ "' or exists (select 1 from JBPM4_EXECUTION where instance_ = e.instance_ and key_ = '"
					+ key + "'))";
		}
		if (workFlowName != null && !"".equals(workFlowName)) {
			sql += " and exists (select 1 from  jbpm4_deployprop where objname_ = '"
					+ workFlowName
					+ "' and key_ = 'pdid' and stringval_ = e.procdefid_)";
		}
		if (startDate != null && !"".equals(startDate)) {
			sql += " and t.create_ >=  to_date('" + startDate
					+ "', 'YYYY-MM-DD hh24:mi:ss')";
		}
		if (endDate != null && !"".equals(endDate)) {
			try {
				endDate = DateUtils.calDate(endDate, 1, "D");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sql += " and t.create_ <  to_date('" + endDate
					+ "', 'YYYY-MM-DD hh24:mi:ss')";
		}
		List list = getSessionFactory().getCurrentSession().createSQLQuery(sql)
				.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ID", String.valueOf(o[0]));
				map.put("ActivityName", String.valueOf(o[1]));
				String createTime = String.valueOf(o[2]);
				String dueTime = String.valueOf(o[3]);
				try {
					if (createTime != null && !"".equals(createTime)) {
						map.put("CreateTime", DateUtils.getDateTime(createTime));
					}
					if (dueTime != null && !"".equals(dueTime)
							&& !"null".equals(dueTime)) {
						map.put("DueTime", DateUtils.getDateTime(dueTime));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				map.put("BussNo", String.valueOf(o[4]));
				map.put("Assignee", String.valueOf(o[5]));
				map.put("TaskId", String.valueOf(o[6]));

				listReturn.add(map);
			}
			return listReturn;
		}
		return null;
	}

	/**
	 * 查询公共工作池
	 * 
	 * @param bussName
	 * @param key
	 * @param workFlowName
	 * @param startDate
	 * @param endDate
	 * @return List
	 */
	public List queryPublicTask(String bussName, String key,
			String workFlowName, String startDate, String endDate) {
		List<HashMap<String, Object>> listReturn = new ArrayList<HashMap<String, Object>>();
		String sql = "select t.execution_id_ as ID,"
				+ " t.name_ as ActivityName,"
				+ "  to_char(t.create_,'YYYY-MM-DD hh24:mi:ss') as CreateTime, "
				+ "to_char(t.duedate_,'YYYY-MM-DD hh24:mi:ss') as DueTime,"
				+ "(select key_ from JBPM4_EXECUTION e1 where e.instance_ = e1.instance_ and e1.key_ is not null ) as BussNo,"
				+ "t.assignee_ as Assignee,t.dbid_  from  JBPM4_TASK t,JBPM4_EXECUTION e where t.execution_ = e.dbid_ and t.assignee_ is null";
		if (bussName != null && !"".equals(bussName)) {
			sql += " and t.name_ = '" + bussName + "'";
		}
		if (key != null && !"".equals(key)) {
			sql += " and (e.key_ = '"
					+ key
					+ "' or exists (select 1 from JBPM4_EXECUTION where instance_ = e.instance_ and key_ = '"
					+ key + "'))";
		}
		if (workFlowName != null && !"".equals(workFlowName)) {
			sql += " and exists (select 1 from  jbpm4_deployprop where objname_ = '"
					+ workFlowName
					+ "' and key_ = 'pdid' and stringval_ = e.procdefid_)";
		}
		if (startDate != null && !"".equals(startDate)) {
			sql += " and t.create_ >=  to_date('" + startDate
					+ "', 'YYYY-MM-DD hh24:mi:ss')";
		}
		if (endDate != null && !"".equals(endDate)) {
			try {
				endDate = DateUtils.calDate(endDate, 1, "D");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sql += " and t.create_ <  to_date('" + endDate
					+ "', 'YYYY-MM-DD hh24:mi:ss')";
		}
		sql += " order by t.create_ desc";
		List list = getSessionFactory().getCurrentSession().createSQLQuery(sql)
				.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ID", String.valueOf(o[0]));
				map.put("ActivityName", String.valueOf(o[1]));
				String createTime = String.valueOf(o[2]);
				String dueTime = String.valueOf(o[3]);
				try {
					if (createTime != null && !"".equals(createTime)) {
						map.put("CreateTime", DateUtils.getDateTime(createTime));
					}
					if (dueTime != null && !"".equals(dueTime)
							&& !"null".equals(dueTime)) {
						map.put("DueTime", DateUtils.getDateTime(dueTime));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				map.put("BussNo", String.valueOf(o[4]));
				map.put("Assignee", String.valueOf(o[5]));
				map.put("TaskId", String.valueOf(o[6]));

				listReturn.add(map);
			}
			return listReturn;
		}
		return null;
	}
}
