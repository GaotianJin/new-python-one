package com.sinosoft.core.application.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hibernate.Query;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sinosoft.core.application.WorkFlowService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.domain.model.workflow.Workflow;
import com.sinosoft.core.domain.model.workflow.dao.WorkflowDAO;
import com.sinosoft.core.interfaces.util.DataGridModel;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkFlowServiceImpl extends SqlSessionDaoSupport implements
		WorkFlowService {
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	protected WorkflowDAO workflowDAO;
	@Autowired
	protected ProcessEngine processEngine;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected ExecutionService executionService;
	@Autowired
	protected ManagementService managementService;
	@Autowired
	protected TaskService taskService;

	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected IdentityService identityService;

	/**
	 * 保存工作流为xml
	 */
	public String generateDocument(List node, List tran, String name, String id) {
		String sql = "select count(*) from Workflow w where w.Name= '" + name
				+ "'";
		Query re = workflowDAO.getSessionFactory().getCurrentSession()
				.createQuery(sql);
		List list = re.list();
		// if (Integer.parseInt(String.valueOf(list.get(0))) < 1) {
		Document document = DocumentHelper.createDocument();
		Element process = document.addElement("process",
				"http://jbpm.org/4.4/jpdl");
		process.addAttribute("name", name);
		for (int i = 0; i < node.size(); i++) {
			HashMap nodemap = (HashMap) node.get(i);
			String[] classname = nodemap.get("class").toString().split(" ");
			String Coordinate = nodemap.get("Coordinate").toString();
			Element nodes = process.addElement(classname[1]);
			nodes.addAttribute("g", Coordinate);
			nodes.addAttribute("name", nodemap.get("name").toString());
			nodes.addAttribute("id", nodemap.get("id").toString());
			if ("decision".equals(classname[1])) {
				nodes.addAttribute("expr", "#{content}");
			}
			if ("join".equals(classname[1])) {
				nodes.addAttribute("multiplicity", "2");
			}
			if ("task".equals(classname[1])) {
				nodes.addAttribute("assignee", "#{userId}");
			} else {
			}
		}
		for (int i = 0; i < tran.size(); i++) {
			HashMap transition = (HashMap) tran.get(i);
			String from = transition.get("from").toString();
			String to = transition.get("to").toString();
			for (Iterator<Element> it = process.elementIterator(); it.hasNext();) {
				Element ele = (Element) it.next();
				// 找到transition节点的to节点，更新transition节点的to属性为to节点的name
				if (ele.attributeValue("id").equals(to)) {
					to = ele.attributeValue("name");
				}
			}
			for (Iterator<Element> it = process.elementIterator(); it.hasNext();) {
				Element ele = (Element) it.next();
				// 找到transition节点的from节点，，将本身加入到from节点下
				if (ele.attributeValue("id").equals(from)) {
					Element a = ele.addElement("transition");
					a.addAttribute("name", transition.get("name").toString());
					a.addAttribute("g", transition.get("Coordinate") + ":");
					a.addAttribute("to", to);
					a.addAttribute("id", transition.get("id").toString());
				}
			}
		}
		try {
			String url = "jbpm/" + name + ".jpdl.xml";
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			String real = WorkFlowServiceImpl.class.getResource("").getPath();
			String[] path = real.split("WEB-INF");
			if (!"".equals(id)) {
				Workflow workflow = workflowDAO.get(Long.parseLong(id));
				String workflowurl = workflow.getUrl();
				File file = new File(path[0] + workflowurl);
				file.delete();
				XMLWriter output = new XMLWriter(new FileOutputStream(path[0]
						+ url), format);
				output.write(document);
				output.flush();
				output.close();
				workflow.setName(name);
				workflow.setUrl(url);
				workflowDAO.update(workflow);
				// List<Deployment> deployments=
				// repositoryService.createDeploymentQuery().list();
				// for(Deployment deployment:deployments){
				// deployment.getName();
				// }
			} else {
				XMLWriter output = new XMLWriter(new FileOutputStream(path[0]
						+ url), format);
				output.write(document);
				output.flush();
				output.close();
				Workflow workflow = new Workflow();
				workflow.setUrl(url);
				workflow.setName(name);
				Long longID = workflowDAO.save(workflow);
				id = String.valueOf(longID);
			}
			repositoryService.createDeployment()
					.addResourceFromFile(new File(path[0] + url)).deploy();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}

	/**
	 * 查询工作流
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Workflow workflow) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		String countQuery = "select count(*) from Workflow workflow where 1=1 ";
		String fullQuery = "select new map(workflow as workflow,workflow.id as wid) from Workflow workflow where 1=1 ";
		String orderString = "";
		if (StringUtils.hasLength(dgm.getSort()))
			orderString = " order by " + dgm.getSort() + " " + dgm.getOrder();
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (workflow != null) {
			if (StringUtils.hasLength(workflow.getName())) {
				sb.append(" and workflow.Name like :Name");
				params.put("Name", "%" + workflow.getName() + "%");
			}
		}
		Query queryTotal = workflowDAO.getSessionFactory().getCurrentSession()
				.createQuery(countQuery + sb.toString());
		Query queryList = workflowDAO.getSessionFactory().getCurrentSession()
				.createQuery(fullQuery + sb.toString() + orderString)
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
		int total = ((Long) queryTotal.uniqueResult()).intValue();
		List list = queryList.list();
		result.put("total", total);
		result.put("rows", list);
		return result;

	}

	/**
	 * 将xml工作流转换为html
	 */

	public String getHtml(String id) {
		String html = "";
		String real = WorkFlowServiceImpl.class.getResource("").getPath();
		String[] path = real.split("WEB-INF");
		Workflow w = workflowDAO.get(Long.parseLong(id));
		String workflowurl = w.getUrl();
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("utf-8");
		Document document = null;
		try {
			document = saxReader.read(new File(path[0] + workflowurl));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element process = document.getRootElement();
		html = getNode(process);

		return html;
	}

	/**
	 * 循环遍历xml并组装成html
	 */
	private String getNode(Element element) {
		String html = "";

		for (Iterator<Element> it = element.elementIterator(); it.hasNext();) {
			Element ele = (Element) it.next();

			if ("transition".equals(ele.getName())) {
				String[] tCoordinate = ele.attributeValue("g").split(",");
				String tname = ele.attributeValue("name");
				String[] tid = ele.attributeValue("id").split("[*]");
				String to = getId(element.getParent(), ele.attributeValue("to"));
				html += "<div id='" + tid[0] + "' class='transition' to='" + to
						+ "' from='" + ele.getParent().attributeValue("id")
						+ "' title='" + tname + "'>" + "<div id='" + tid[0]
						+ tid[1] + "' class='point' style='top:"
						+ tCoordinate[1].split(":")[0] + "px;left:"
						+ tCoordinate[0] + "px;'></div></div>";
			} else {
				String name = ele.attributeValue("name");
				String[] nCoordinate = ele.attributeValue("g").split(",");
				String nid = ele.attributeValue("id");
				html += "<div id='" + nid + "' class='node " + ele.getName()
						+ "' style='top:" + nCoordinate[1] + "px;left:"
						+ nCoordinate[0] + "px;'>" + name + "</div>";
			}
			if (ele.elementIterator().hasNext()) {
				html += getNode(ele);
			}
		}
		return html;
	}

	private String getId(Element element, String name) {
		String id = "";
		for (Iterator<Element> it = element.elementIterator(); it.hasNext();) {
			Element ele = (Element) it.next();
			if (ele.attributeValue("name").equals(name)) {
				id = ele.attributeValue("id");
			}
		}
		return id;
	}

	/**
	 * 将工作流文件删除
	 */
	public void deleteWorkflow(Long id) {
		Workflow w = workflowDAO.get(id);
		String url = w.getUrl();
		String real = WorkFlowServiceImpl.class.getResource("").getPath();
		String[] path = real.split("WEB-INF");
		File file = new File(path[0] + url);
		if (!file.exists()) {
			log.info("文件不存在");
			workflowDAO.delete(w);
			// throw new CisCoreException("文件不存在");
			return;
		}
		boolean rs = file.delete(); // 调用delete()方法
		if (rs) {
			log.info("文件删除成功");
			workflowDAO.delete(w);
		} else {
			log.info("文件删除失败");
			throw new CisCoreException("文件删除失败");
		}

	}

	/**
	 * 部署启动新的process流程，业务号为key
	 * 
	 * @param process
	 * @param key
	 */
	public void deployAndStart(String process, String key) {
		String deploymentId = repositoryService.createDeployment()
				.addResourceFromClasspath("jbpm/" + process + ".jpdl.xml")
				.deploy();
		ProcessInstance processInstance = executionService
				.startProcessInstanceByKey(process, key);
	}

	/**
	 * 启动新的process流程，业务号为key
	 * 
	 * @param process
	 * @param key
	 */
	public void start(String process, String key) {
		ProcessInstance processInstance = executionService
				.startProcessInstanceByKey(process, key);
	}

	/**
	 * 获取key的当前节点activeExecution，并触发动作到下一个节点
	 * 
	 * @param key
	 * @param activeExecution
	 * @param processName
	 */
	public void signal(String key, String activeExecution, String processName) {
		List<ProcessInstance> processInstanceList = executionService
				.createProcessInstanceQuery().processInstanceKey(key).list();
		for (ProcessInstance processInstance : processInstanceList) {
			if (processInstance.getId().startsWith(processName)
					&& processInstance != null) {
				Execution executionInA = processInstance
						.findActiveExecutionIn(activeExecution);
				System.out.println(executionInA.getId());
				processInstance = executionService
						.signalExecutionById(executionInA.getId());
			}
		}
	}

	/**
	 * 获取key的当前节点activeExecution，并触发动作到下一个节点，可带参数用来判断流程走向
	 * 
	 * @param key
	 * @param activeExecution
	 * @param variables
	 */
	public void signal(String key, String activeExecution, String processName,
			Map<String, Object> variables) {
		List<ProcessInstance> processInstanceList = executionService
				.createProcessInstanceQuery().processInstanceKey(key).list();
		for (ProcessInstance processInstance : processInstanceList) {
			if (processInstance.getId().startsWith(processName)
					&& processInstance != null) {
				Execution executionInA = processInstance
						.findActiveExecutionIn(activeExecution);
				processInstance = executionService.signalExecutionById(
						executionInA.getId(), variables);
			}
		}
	}

	/**
	 * 展示工作流案例
	 */
	public List showWorkflow(String active, String key, String chiose) {
		String processname = "新契约流程";
		if ("start".equals(active)) {
			deployAndStart("新契约流程", key);
			return getState(key, processname);
		} else if ("load".equals(active)) {
			load(key);
			return getState(key, processname);
		} else if ("charge".equals(active)) {
			charge(key);
			return getState(key, processname);
		} else if ("review".equals(active)) {
			review(key);
			return getState(key, processname);
		} else if ("autocheck".equals(active)) {
			autocheck(key, chiose);
			return getState(key, processname);
		} else if ("check".equals(active)) {
			check(key, chiose);
			return getState(key, processname);
		} else if ("sign".equals(active)) {
			sign(key);
			return getState(key, processname);
		} else {
			List list = new ArrayList();
			return list;
		}
	}

	public void load(String key) {
		signal(key, "录入", "新契约流程");
	}

	public void charge(String key) {
		signal(key, "财务收付费", "新契约流程");
	}

	public void review(String key) {
		signal(key, "复核", "新契约流程");
	}

	public void autocheck(String key, String chiose) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("content", chiose);
		signal(key, "自核", "新契约流程", variables);
	}

	public void check(String key, String chiose) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("content", chiose);
		signal(key, "人核", "新契约流程", variables);
	}

	public void sign(String key) {
		signal(key, "签单", "新契约流程");
	}

	public void deleteDeploy() {
		String deploymentId = repositoryService.createProcessDefinitionQuery()
				.processDefinitionName("新契约流程").uniqueResult()
				.getDeploymentId();
		repositoryService.deleteDeploymentCascade(deploymentId);
	}

	public List getState(String key, String processName) {
		ArrayList state = new ArrayList();
		List<ProcessInstance> processInstanceList = executionService
				.createProcessInstanceQuery().processInstanceKey(key).list();
		for (ProcessInstance processInstance : processInstanceList) {
			if (processInstance.getId().startsWith(processName)
					&& processInstance != null) {
				Set<String> set = processInstance.findActiveActivityNames();
				for (String s : set) {
					state.add(s);
				}
			}
		}
		return state;
	}

	/**
	 * 查询工作流明细，返回ANME、key
	 */
	public Map<String, Object> getWorkflowList(String key) {
		Map<String, Object> result = new HashMap<String, Object>();
		List list1 = new ArrayList();
		if ("".equals(key) || ",".equals(key)) {
			List<ProcessInstance> results = executionService
					.createProcessInstanceQuery().list();
			for (ProcessInstance p : results) {
				if (p.findActiveActivityNames().size() > 0) {
					for (String name : p.findActiveActivityNames()) {
						List list = new ArrayList();
						list.add(p.getKey());
						list.add(name);
						list1.add(list);
					}
				}
			}
		} else {
			List<ProcessInstance> results = executionService
					.createProcessInstanceQuery()
					.processInstanceKey(key.split(",")[0]).notSuspended()
					.list();
			for (ProcessInstance p : results) {
				if (p.findActiveActivityNames().size() > 0) {
					for (String name : p.findActiveActivityNames()) {
						List list = new ArrayList();
						list.add(p.getKey());
						list.add(name);
						list1.add(list);
					}
				}
			}
		}

		result.put("total", list1.size());
		result.put("rows", list1);
		return result;
	}

	/**
	 * 以上方法均是工作流模块用到的，以下方法为提供其他模块用的工具
	 */


	@Override
	public void createTask(String workFlowName, Map<String, Object> variables,
			String key) {
		if (variables.get("userId") == null) {
			variables.put("userId", "");
		}
		executionService
				.startProcessInstanceByKey(workFlowName, variables, key);
	}


	@Override
	public void doTask(String key, String workFlowName, String activityName,
			Map<String, Object> variables) {
		List<HashMap<String, Object>> listReturn = workflowDAO.queryTask(null,
				activityName, key, workFlowName, null, null);
		if (listReturn != null && listReturn.size() > 0) {
			for (int i = 0; i < listReturn.size(); i++) {
				HashMap<String, Object> map = listReturn.get(i);
				String taskId = (String) map.get("TaskId");
				taskService.setVariables(taskId, variables);
				taskService.completeTask(taskId);
			}
		}
	}


	@Override
	public List queryHistoryWorkflow(String key, String workFlowName) {
		List<HistoryProcessInstance> list = historyService
				.createHistoryProcessInstanceQuery().processInstanceKey(key)
				.processDefinitionId(workFlowName).list();
		if (list != null && list.size() > 0) {
			HistoryProcessInstance historyProcessInstance = list.get(0);
			String id = historyProcessInstance.getProcessInstanceId();
			log.info("ID:" + id);
			// return workflowDAO.queryTaskByID(id);
			return (List) this
					.getSqlSession()
					.selectList(
							"com.sinosoft.core.domain.model.workflow.WorkflowMapper.findHisTaskByID",
							id + "%");
		}
		return null;
	}

	@Override
	public Map<String, Object> queryWorkflowByParam(String userId, String key,
			String workFlowName, ArrayList<String> bussnames, String startDate,
			String endDate) {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		List<HashMap<String, Object>> listReturn = new ArrayList<HashMap<String, Object>>();
		if (bussnames != null && bussnames.size() > 0) {
			for (int i = 0; i < bussnames.size(); i++) {
				List list = workflowDAO.queryTask(userId, bussnames.get(i),
						key, workFlowName, startDate, endDate);
				if (list != null && list.size() > 0) {
					for (int j = 0; j < list.size(); j++) {
						listReturn.add((HashMap<String, Object>) list.get(j));
					}
				}
			}
		}
		mapResult.put("total", listReturn.size());
		mapResult.put("rows", listReturn);
		return mapResult;
	}

	@Override
	@Transactional
	public void setTaskUserId(String userId, String key, String workFlowName,
			String bussname) {
		List<HashMap<String, Object>> listReturn = workflowDAO.queryTask(null,
				bussname, key, workFlowName, null, null);
		if (listReturn != null && listReturn.size() > 0) {
			for (int i = 0; i < listReturn.size(); i++) {
				HashMap<String, Object> map = listReturn.get(i);
				String taskId = (String) map.get("TaskId");
				taskService.assignTask(taskId, userId);
			}
		}
	}

	@Override
	public List<HashMap<String, Object>> queryPublicWorkflowByParam(String key,
			String workFlowName, ArrayList<String> bussnames, String startDate,
			String endDate) {
		List<HashMap<String, Object>> listReturn = new ArrayList<HashMap<String, Object>>();
		if (bussnames != null && bussnames.size() > 0) {
			for (int i = 0; i < bussnames.size(); i++) {
				List list = workflowDAO.queryPublicTask(bussnames.get(i), key,
						workFlowName, startDate, endDate);
				if (list != null && list.size() > 0) {
					for (int j = 0; j < list.size(); j++) {
						listReturn.add((HashMap<String, Object>) list.get(j));
					}
				}
			}
		}
		return listReturn;
	}

	@Override
	@Transactional
	public void setTaskUserId(String userId, String taskId) {
		taskService.assignTask(taskId, userId);
	}
}
