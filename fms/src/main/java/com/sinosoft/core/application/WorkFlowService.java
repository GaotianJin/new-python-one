package com.sinosoft.core.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.core.domain.model.workflow.Workflow;
import com.sinosoft.core.interfaces.util.DataGridModel;

public interface WorkFlowService {

	public String generateDocument(List node, List tran, String name, String id);
	
	Map<String, Object> getPageList(DataGridModel dgm, Workflow workflow);
	
	public String getHtml(String id);
	
	public void deleteWorkflow(Long id);
	
	public List showWorkflow(String active,String key,String chiose);
	
	public List getState(String key, String processname);
	
	public void deployAndStart(String processname, String key);

	public void signal(String key, String activeExecution, String processName);

	public void signal(String key, String activeExecution, String processName, Map<String, Object> variables);
	
	public void start(String string, String string2);
	
	public Map<String, Object> getWorkflowList(String key);
	
	/**
	 * 创建工作流
	 * @param workFlowName 工作流名称
	 * @param variables 参数
	 * @param key 业务号，例如保单ID
	 */
	public void createTask(String workFlowName,Map<String, Object> variables,String key);
	
	
	/**
	 * 根据业务号执行工作流任务
	 * 
	 * @param key
	 *            业务号
	 * @param workFlowName
	 *            工作流名称
	 * @param activityName
	 *            要流转的任务名称
	 * @param variables
	 *            参数
	 */
	public void doTask(String key, String workFlowName, String activityName,
			Map<String, Object> variables);

	/**
	 * 根据业务号查询历史工作流
	 * @param key 业务号，例如保单ID
	 * @param workFlowName 工作流名称
	 * @return Map<String, Object>
	 */
	public List queryHistoryWorkflow(String key,String workFlowName);
	

	/**
	 * 根据一些参数查询活动的任务
	 * 
	 * @param userId
	 *            用户ID
	 * @param key
	 *            业务号
	 * @param workFlowName
	 *            工作流名称
	 * @param bussnames
	 *            任务名称集合
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return Map
	 */
	public Map<String, Object> queryWorkflowByParam(String userId, String key,
			String workFlowName, ArrayList<String> bussnames, String startDate,
			String endDate);
	
	/**
	 * 把任务赋给某个用户
	 * 
	 * @param userId
	 *            用户ID
	 * @param key
	 *            业务号
	 * @param workFlowName
	 *            工作流名称
	 * @param bussname
	 *            任务名称
	 */
	public void setTaskUserId(String userId, String key, String workFlowName,
			String bussname);

	/**
	 * 把任务赋给某个用户
	 * 
	 * @param userId
	 *            用户ID
	 * @param taskId
	 *            工作流任务ID，需要通过queryWorkflowByParam或queryPublicWorkflowByParam查询出
	 */
	public void setTaskUserId(String userId, String taskId);

	/**
	 * 查询公共任务池
	 * 
	 * @param key
	 *            业务号
	 * @param workFlowName
	 *            工作流名称
	 * @param bussnames
	 *            任务名称集合
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return List
	 */
	public List<HashMap<String, Object>> queryPublicWorkflowByParam(String key,
			String workFlowName, ArrayList<String> bussnames, String startDate,
			String endDate);

}
