package com.fms.service.quartz;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.quartz.CronExpression;
import com.sinosoft.core.domain.model.quartz.JobDetails;
import com.sinosoft.core.domain.model.quartz.Tcschedulerallocate;
import com.sinosoft.core.interfaces.util.DataGridModel;

public interface SchedulerService {
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param cronExpression
	 *            Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	void schedule(String cronExpression, String jobName);

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param name
	 *            Quartz CronTrigger名称
	 * @param cronExpression
	 *            Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	void schedule(String name, String cronExpression, String jobName);

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param name
	 *            Quartz CronTrigger名称
	 * @param cronExpression
	 *            Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 * @param group
	 *            Quartz CronExpression Group
	 */
	void schedule(String name, String cronExpression, String group, String jobName);

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param cronExpression
	 *            Quartz CronExpression
	 */
	void schedule(CronExpression cronExpression, String jobName);

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param name
	 *            Quartz CronTrigger名称
	 * @param cronExpression
	 *            Quartz CronExpression
	 */
	void schedule(String name, CronExpression cronExpression, String jobName);

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param name
	 *            Quartz CronTrigger名称
	 * @param cronExpression
	 *            Quartz CronExpression
	 * @param group
	 *            Quartz CronExpression Group
	 */
	void schedule(String name, CronExpression cronExpression, String group, String jobName);

	/**
	 * 在startTime时执行调试一次
	 * 
	 * @param startTime
	 *            调度开始时间
	 */
	void schedule(Date startTime, String jobName);

	/**
	 * 在startTime时执行调试一次
	 * 
	 * @param startTime
	 *            调度开始时间
	 * @param group
	 *            Quartz SimpleTrigger Group
	 */
	void schedule(Date startTime, String group, String jobName);

	/**
	 * 在startTime时执行调试一次
	 * 
	 * @param name
	 *            Quartz SimpleTrigger 名称
	 * @param startTime
	 *            调度开始时间
	 */
	void schedule(String name, Date startTime, String jobName);

	/**
	 * 在startTime时执行调试一次
	 * 
	 * @param name
	 *            Quartz SimpleTrigger 名称
	 * @param startTime
	 *            调度开始时间
	 * @param group
	 *            Quartz SimpleTrigger Group
	 */
	void schedule(String name, Date startTime, String group, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * 
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 */
	void schedule(Date startTime, Date endTime, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * 
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param group
	 *            Quartz SimpleTrigger Group
	 */
	void schedule(Date startTime, Date endTime, String group, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * 
	 * @param name
	 *            Quartz SimpleTrigger 名称
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 */
	void schedule(String name, Date startTime, Date endTime, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * 
	 * @param name
	 *            Quartz SimpleTrigger 名称
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param group
	 *            Quartz SimpleTrigger Group
	 */
	void schedule(String name, Date startTime, Date endTime, String group, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * 
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param repeatCount
	 *            重复执行次数
	 */
	void schedule(Date startTime, Date endTime, int repeatCount, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * 
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param repeatCount
	 *            重复执行次数
	 * @param group
	 *            Quartz SimpleTrigger Group
	 */
	void schedule(Date startTime, Date endTime, int repeatCount, String group, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * 
	 * @param name
	 *            Quartz SimpleTrigger 名称
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param repeatCount
	 *            重复执行次数
	 */
	void schedule(String name, Date startTime, Date endTime, int repeatCount, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * 
	 * @param name
	 *            Quartz SimpleTrigger 名称
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param repeatCount
	 *            重复执行次数
	 * @param group
	 *            Quartz SimpleTrigger Group
	 */
	void schedule(String name, Date startTime, Date endTime, int repeatCount, String group, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * 
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param repeatCount
	 *            重复执行次数
	 * @param repeatInterval
	 *            执行时间隔间
	 */
	void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * 
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param repeatCount
	 *            重复执行次数
	 * @param repeatInterval
	 *            执行时间隔间
	 * @param group
	 *            Quartz SimpleTrigger Group
	 */
	void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval, String group, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * 
	 * @param name
	 *            Quartz SimpleTrigger 名称
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param repeatCount
	 *            重复执行次数
	 * @param repeatInterval
	 *            执行时间隔间
	 */
	void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval, String jobName);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * 
	 * @param name
	 *            Quartz SimpleTrigger 名称
	 * @param startTime
	 *            调度开始时间
	 * @param endTime
	 *            调度结束时间
	 * @param repeatCount
	 *            重复执行次数
	 * @param repeatInterval
	 *            执行时间隔间
	 * @param group
	 *            Quartz SimpleTrigger Group
	 */
	void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval, String group, String jobName);

	/**
	 * Trigger 参数,以com.sundoctor.example.Constant常量为键封装的Map
	 * 
	 * @param map
	 */
	void schedule(Map<String, Object> filterMap);

	/**
	 * 取得所有调度Triggers
	 * 
	 * @return
	 */
	Map<String, Object> getPageList(DataGridModel dgm, String jobName, String trigger_name);
	
	/**
	 * 取得所有任务
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List queryJob();

	/**
	 * 根据名称和组别暂停Tigger
	 * 
	 * @param triggerName
	 * @param group
	 */
	void pauseTrigger(String triggerName, String group);

	/**
	 * 恢复Trigger
	 * 
	 * @param triggerName
	 * @param group
	 */
	void resumeTrigger(String triggerName, String group);

	/**
	 * 删除Trigger
	 * 
	 * @param triggerName
	 * @param group
	 */
	boolean removeTrigger(String triggerName, String group);

	/**
	 * 查询基本任务信息列表
	 * 
	 */
	Map<String, Object> getPageList(DataGridModel dgm, JobDetails  jobDetails );
	
	/**
	 * 
	 * 查询基本调度关系信息列表
	 */
	Map<String, Object> getPageList(DataGridModel dgm, Tcschedulerallocate tcschedulerallocate );
		
	/**
	 * 
	 * 任务名称下拉查询
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List queryJobTnameCombox();
	
   /**
    * 新增基本任务
    * 
    */
   void addBaseTask(JobDetails jobdetails);
   
   /**
    * 新增基本任务
    * 
    */
   void addSchedulerAllocate(Tcschedulerallocate tcschedulerallocate);
   
   
	/**
	 * 根据任务名称下拉选择任务的相应处理方法
	 * @param jobtname
	 * @return
	 */
   
   public JobDetails queryJobNameByTname(String jobtname);
}
