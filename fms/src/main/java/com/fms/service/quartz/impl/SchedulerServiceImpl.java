package com.fms.service.quartz.impl;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Query;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.fms.service.quartz.SchedulerService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.domain.model.quartz.JobDetails;
import com.sinosoft.core.domain.model.quartz.Tcschedulerallocate;
import com.sinosoft.core.domain.model.quartz.dao.JobDetailsDAO;
import com.sinosoft.core.domain.model.quartz.dao.QuartzDao;
import com.sinosoft.core.domain.model.quartz.dao.TcschedulerallocateDAO;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.quartz.Constant;


@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SchedulerServiceImpl implements SchedulerService, Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private Scheduler scheduler;
	private JobDetail jobDetail;
	private QuartzDao quartzDao;

	@Autowired
	JobDetailsDAO jobDetailsDAO;
	@Autowired
	TcschedulerallocateDAO tcSchedulerallocateDAO;

	private static final Logger logger = LoggerFactory
			.getLogger(SchedulerServiceImpl.class);

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	@Autowired
	public void setScheduler(@Qualifier("quartzScheduler") Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Autowired
	public void setQuartzDao(@Qualifier("quartzDao") QuartzDao quartzDao) {
		this.quartzDao = quartzDao;
	}

	public void schedule(String cronExpression, String jobName) {
		schedule("", cronExpression, jobName);
	}

	public void schedule(String name, String cronExpression, String jobName) {
		schedule(name, cronExpression, Scheduler.DEFAULT_GROUP, jobName);
	}

	// 表达式调度 and 模板调度
	public void schedule(String name, String cronExpression, String group,
			String jobName) {
		try {
			if (cronExpression == null || "".equals(cronExpression)) {
				throw new CisCoreException("cronExpression不能为空");
			}
			if (jobName == null || "".equals(jobName)) {
				throw new CisCoreException("jobName不能为空");
			}
			schedule(name, new CronExpression(cronExpression), group, jobName);
		} catch (ParseException e) {
			throw new CisCoreException("cronExpression解析错误", e);
		}
	}

	public void schedule(CronExpression cronExpression, String jobName) {
		schedule(null, cronExpression, jobName);
	}

	public void schedule(String name, CronExpression cronExpression,
			String jobName) {
		schedule(name, cronExpression, Scheduler.DEFAULT_GROUP, jobName);
	}

	// 表达式调度
	public void schedule(String name, CronExpression cronExpression,
			String group, String jobName) {
		if (name == null || name.trim().equals("")) {
			name = UUID.randomUUID().toString();
		} else {
			// 在名称后添加UUID，保证名称的唯一性
			name += "&" + UUID.randomUUID().toString();
			System.out.println("schedule：" + name);
		}

		try {
			if (jobName != null & !"".equals(jobName)) {
				jobDetail = scheduler.getJobDetail(jobName, group);
			}
			System.out.println(jobDetail);
			scheduler.addJob(jobDetail, true);

			CronTrigger cronTrigger = new CronTrigger(name, group,
					jobDetail.getName(), Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(cronExpression);
			scheduler.scheduleJob(cronTrigger);
			// scheduler.rescheduleJob(cronTrigger.getName(),
			// cronTrigger.getGroup(), cronTrigger);
		} catch (SchedulerException e) {
			throw new CisCoreException(e.getMessage());
		}
	}

	public void schedule(Date startTime, String jobName) {
		schedule(startTime, Scheduler.DEFAULT_GROUP, jobName);
	}

	public void schedule(Date startTime, String group, String jobName) {
		schedule(startTime, null, group, jobName);
	}

	public void schedule(String name, Date startTime, String jobName) {
		schedule(name, startTime, Scheduler.DEFAULT_GROUP, jobName);
	}

	public void schedule(String name, Date startTime, String group,
			String jobName) {
		schedule(name, startTime, null, group, jobName);
	}

	public void schedule(Date startTime, Date endTime, String jobName) {
		schedule(startTime, endTime, Scheduler.DEFAULT_GROUP, jobName);
	}

	public void schedule(Date startTime, Date endTime, String group,
			String jobName) {
		schedule(startTime, endTime, 0, group, jobName);
	}

	public void schedule(String name, Date startTime, Date endTime,
			String jobName) {
		schedule(name, startTime, endTime, Scheduler.DEFAULT_GROUP, jobName);
	}

	public void schedule(String name, Date startTime, Date endTime,
			String group, String jobName) {
		schedule(name, startTime, endTime, 0, group, jobName);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount,
			String jobName) {
		schedule(startTime, endTime, 0, Scheduler.DEFAULT_GROUP, jobName);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount,
			String group, String jobName) {
		schedule(null, startTime, endTime, 0, group, jobName);
	}

	public void schedule(String name, Date startTime, Date endTime,
			int repeatCount, String jobName) {
		schedule(name, startTime, endTime, 0, Scheduler.DEFAULT_GROUP, jobName);
	}

	public void schedule(String name, Date startTime, Date endTime,
			int repeatCount, String group, String jobName) {
		schedule(name, startTime, endTime, 0, 1L, group, jobName);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount,
			long repeatInterval, String jobName) {
		schedule(startTime, endTime, repeatCount, repeatInterval,
				Scheduler.DEFAULT_GROUP, jobName);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount,
			long repeatInterval, String group, String jobName) {
		schedule(null, startTime, endTime, repeatCount, repeatInterval, group,
				jobName);
	}

	public void schedule(String name, Date startTime, Date endTime,
			int repeatCount, long repeatInterval, String jobName) {
		this.schedule(name, startTime, endTime, repeatCount, repeatInterval,
				Scheduler.DEFAULT_GROUP, jobName);
	}

	// 简单调度
	public void schedule(String name, Date startTime, Date endTime,
			int repeatCount, long repeatInterval, String group, String jobName) {
		if (startTime == null || "".equals(startTime)) {
			throw new CisCoreException("startTime不能为空");
		}
		if (jobName == null || "".equals(jobName)) {
			throw new CisCoreException("jobName不能为空");
		}
		if (name == null || name.trim().equals("")) {
			name = UUID.randomUUID().toString();
		} else {
			// 在名称后添加UUID，保证名称的唯一性
			name += "&" + UUID.randomUUID().toString();
			System.out.println("name:" + name);
		}

		try {
			if (jobName != null & !"".equals(jobName)) {
				jobDetail = scheduler.getJobDetail(jobName, group);
			}
			// Object[] arguments = new Object[1];
			// arguments[0] = "111";
			// jobDetail.getJobDataMap().put("arguments", arguments);
			scheduler.addJob(jobDetail, true);
			SimpleTrigger SimpleTrigger = new SimpleTrigger(name, group,
					jobName, Scheduler.DEFAULT_GROUP, startTime, endTime,
					repeatCount, repeatInterval);
			scheduler.scheduleJob(SimpleTrigger);
			// scheduler.rescheduleJob(SimpleTrigger.getName(),
			// SimpleTrigger.getGroup(), SimpleTrigger);
		} /*catch (SchedulerException e) {
			throw new CisCoreException(e.getMessage());
		}*/
		catch (Exception e) {
			e.printStackTrace();
			throw new CisCoreException(e.getMessage());
		}
	}

	/**
	 * 
	 * 前台页面传送Map集合，里面是页面录入的基本信息。
	 */
	public void schedule(Map<String, Object> map) {
		String trigger_type = (String) map.get(Constant.TRIGGERTYPE);
		String trigger_name = (String) map.get(Constant.TRIGGERNAME);
		String job_name = (String) map.get(Constant.JOBNAME);
		System.out.println("我们开始了:" + trigger_type + "||" + trigger_name);
		try {
			if (job_name != null & !"".equals(job_name)) {
				jobDetail = scheduler.getJobDetail(job_name,
						Scheduler.DEFAULT_GROUP);
			}
			if (trigger_type.equals("simpletrigger")
					|| trigger_type == "simpletrigger") {
				int unit = Integer.valueOf(String.valueOf(map.get("unit")));
				String t_starttime = String
						.valueOf(map.get(Constant.STARTTIME));
				String t_endtime = String.valueOf(map.get(Constant.ENDTIME));
				Object rc = map.get(Constant.REPEATCOUNT);
				String t_repeatInterval = String.valueOf(map
						.get(Constant.REPEATINTERVEL));
				if (t_starttime == null || "".equals(t_starttime)) {
					throw new CisCoreException("请输入开始时间");
				}

				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date startTime = dateFormat.parse(t_starttime);
				Date endTime = null;
				if (t_endtime != null && !"".equals(t_endtime)) {
					endTime = dateFormat.parse(t_endtime);
				}
				int repeatCount = 0;
				long repeatInterval = 60L;
				if (rc != null && !"".equals(rc)) {
					repeatCount = Integer.valueOf(String.valueOf(rc));
				}

				if (t_repeatInterval != null && !"".equals(t_repeatInterval)) {
					repeatInterval = Long.valueOf(t_repeatInterval);
				}
				// 简单调度
				schedule(trigger_name, startTime, endTime, repeatCount,
						repeatInterval * unit, Scheduler.DEFAULT_GROUP,
						job_name);
			} else if (trigger_type.equals("crontrigger")
					|| trigger_type == "crontrigger") {
				String cron_expression = (String) map
						.get(Constant.CRONEXPRESSION);
				// 表达式调度
				schedule(trigger_name, cron_expression,
						Scheduler.DEFAULT_GROUP, job_name);
			} else if (trigger_type.equals("templatetrigger")
					|| trigger_type == "templatetrigger") {
				String template_type = (String) map.get("template_type");
				String executeTime = (String) map.get("executeTime");
				String executeHour = executeTime.substring(0, 2);
				String executeMinute = executeTime.substring(3, 5);
				String executeSecond = executeTime.substring(6, 8);
				String cron_expression = "";
				int type = Integer.valueOf(template_type.substring(8));
				switch (type) {
				default:
					System.out.println("default");
				case 1:
					cron_expression = executeSecond + " " + executeMinute + " "
							+ executeHour + " * * ?";
				case 2:
					System.out.println(2);
				}
				// 模板调度
				schedule(trigger_name, cron_expression,
						Scheduler.DEFAULT_GROUP, job_name);
			}
		} catch (SchedulerException e) {
			throw new CisCoreException(e.getMessage(), e);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new CisCoreException("日期格式解析失败");
		}
	}

	/**
	 * 列表查询
	 */
	@Transactional
	public Map<String, Object> getPageList(DataGridModel dgm, String jobName,
			String trigger_name) {
		return quartzDao.getPageList(dgm, jobName, trigger_name);
	}

	/**
	 * 查询所有任务
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public List queryJob() {
		return quartzDao.queryJob();
	}

	public void pauseTrigger(String triggerName, String group) {
		try {
			scheduler.pauseTrigger(triggerName, group);// 停止触发器
		} catch (SchedulerException e) {
			throw new CisCoreException(e.getMessage());
		}
	}

	public void resumeTrigger(String triggerName, String group) {
		try {
			// Trigger trigger = scheduler.getTrigger(triggerName, group);

			scheduler.resumeTrigger(triggerName, group);// 重启触发器
		} catch (SchedulerException e) {
			throw new CisCoreException(e.getMessage());
		}
	}

	public boolean removeTrigger(String triggerName, String group) {
		try {
			scheduler.pauseTrigger(triggerName, group);// 停止触发器
			return scheduler.unscheduleJob(triggerName, group);// 移除触发器
		} catch (SchedulerException e) {
			throw new CisCoreException(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private Date parseDate(String time) {
		try {
			return DateUtils.parseDate(time,
					new String[] { "yyyy-MM-dd HH:mm" });
		} catch (ParseException e) {
			logger.error("日期格式错误{}，正确格式为：yyyy-MM-dd HH:mm", time);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * 任务名称下拉查询
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public List queryJobTnameCombox() {
		String dataSQL = " from QRTZ_TRIGGERS where 1=1 ";
		Query queryData = jobDetailsDAO.getSessionFactory().getCurrentSession().createQuery(dataSQL);
		List dataList = queryData.list();
		System.out.println("liszz:" + dataList);
		return dataList;

	}

	/**
	 * 
	 * 基本任务信息查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Transactional
	public Map<String, Object> getPageList(DataGridModel dgm,
			JobDetails jobDetails) {
		System.out.println("getPagelist");
		Map<String, Object> result = new HashMap<String, Object>(2);

		StringBuffer tSql = new StringBuffer();
		StringBuffer countQuery = new StringBuffer();
		StringBuffer shareSql = new StringBuffer();
		countQuery.append("select count(*) from Job_Details a where 1 = 1 ");
		System.out.println(countQuery);
		tSql.append("select id,"
				+ "job_tname,"
				+ "job_name,"
				+ "(select trigger_state  from  qrtz_triggers q  where  a.job_tname=q.job_name) as triggerstate,"
				+ "makedate,remark1 from Job_Details a where 1=1 ");
		System.out.println("eric:" + tSql);
		Map<String, Object> params = new HashMap<String, Object>();
		System.out.println("params:" + params);

		if (jobDetails != null) {
			if (jobDetails.getJobTname() != null) {
				System.out.println("基本任务名称:");
				shareSql.append(" and a.JobTname = " + jobDetails.getJobTname());

			}
			if (StringUtils.hasLength(jobDetails.getJobName())) {
				System.out.println("任务处理接口明:");
				shareSql.append(" and a.JobName like :jobname");
				params.put("veritystate", "%" + jobDetails.getJobName() + "%");
			}

		}

		Query queryTotal = jobDetailsDAO.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(countQuery.toString() + shareSql.toString());
		Query queryList = jobDetailsDAO.getSessionFactory().getCurrentSession()
				.createSQLQuery(tSql.toString() + shareSql.toString())
				.setFirstResult((dgm.getPage() - 1) * dgm.getRows())
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
			map.put("id", ((Object[]) o)[0]);
			map.put("jobtname", ((Object[]) o)[1]);
			map.put("jobname", ((Object[]) o)[2]);
			map.put("triggerstate", ((Object[]) o)[3]);
			map.put("makedate", ((Object[]) o)[4]);
			map.put("remark", ((Object[]) o)[5]);
			list.add(map);
			System.out.println("------------:" + map);
		}
		result.put("total", total);
		result.put("rows", list);
		return result;

	}

	/**
	 * 新增基本任务
	 * 
	 */
	@Transactional
	public void addBaseTask(JobDetails jobdetails) {
		System.out.println("新增基本任务成功");
		jobDetailsDAO.save(jobdetails);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public JobDetails queryJobNameByTname(String jobtname) {
		System.out.println("Code:" + jobtname);
		String dataSQL = "   from JobDetails j where j.JobTname='" + jobtname
				+ "'";
		Query queryData = jobDetailsDAO.getSessionFactory().getCurrentSession()
				.createQuery(dataSQL);
		List dataList = queryData.list();
		return (JobDetails) dataList.get(0);
	}

	@Transactional
	public void addSchedulerAllocate(Tcschedulerallocate tcschedulerallocate) {
		System.out.println("新增调度关系分配");
		tcSchedulerallocateDAO.save(tcschedulerallocate);
		 
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Transactional
	public Map<String, Object> getPageList(DataGridModel dgm,
			Tcschedulerallocate tcschedulerallocate) {
		System.out.println("getTcschedulerallocatePagelist");
		Map<String, Object> result = new HashMap<String, Object>(2);

		StringBuffer tSql = new StringBuffer();
		StringBuffer countQuery = new StringBuffer();
		StringBuffer shareSql = new StringBuffer();
		countQuery
				.append("select count(*) from Tcschedulerallocate a where 1 = 1 ");
		System.out.println(countQuery);
		tSql.append("select id," + "Jobtname1," + "Jobtname2,"
				+ "Startdate,Enddate from Tcschedulerallocate a where 1=1 ");
		System.out.println("eric:" + tSql);
		Map<String, Object> params = new HashMap<String, Object>();
		System.out.println("params:" + params);

		if (tcschedulerallocate != null) {/*
										 * if (jobDetails.getJobTname() != null)
										 * { System.out.println("基本任务名称:");
										 * shareSql.append(" and a.JobTname = "
										 * + jobDetails.getJobTname());
										 * 
										 * } if
										 * (StringUtils.hasLength(jobDetails
										 * .getJobName())) {
										 * System.out.println("任务处理接口明:");
										 * shareSql
										 * .append(" and a.JobName like :jobname"
										 * ); params.put("veritystate", "%" +
										 * jobDetails.getJobName() + "%"); }
										 */
		}

		Query queryTotal = jobDetailsDAO.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(countQuery.toString() + shareSql.toString());
		Query queryList = jobDetailsDAO.getSessionFactory().getCurrentSession()
				.createSQLQuery(tSql.toString() + shareSql.toString())
				.setFirstResult((dgm.getPage() - 1) * dgm.getRows())
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
			map.put("id", ((Object[]) o)[0]);
			map.put("jobtname1", ((Object[]) o)[1]);
			map.put("jobtname2", ((Object[]) o)[2]);
			map.put("startdate", ((Object[]) o)[3]);
			map.put("enddate", ((Object[]) o)[4]);
			list.add(map);
		}
		result.put("total", total);
		result.put("rows", list);
		return result;

	}
}
