package com.sinosoft.core.interfaces.workflow;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.core.application.WorkFlowService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.domain.model.workflow.Workflow;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;

@Controller
@RequestMapping("/workflow")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class WorkFlowController {

	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private WorkFlowService workFlowService;

	protected final transient Log log = LogFactory.getLog(WorkFlowController.class);

	/**
	 * 工作流页面
	 */
	@RequestMapping(value = "/listWorkflowUrl", method = RequestMethod.GET)
	public String listWorkflowUrl(Model model) throws Exception {
		return "workflow/listWorkflow";
	}
	
	/**
	 * 新增工作流页面
	 */
	@RequestMapping(value = "/addWorkflowUrl", method = RequestMethod.GET)
	public ModelAndView design(Model model) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "add");
		return new ModelAndView("workflow/workflow", map);
	}
	
	/**
	 * 更新工作流页面
	 */
	@RequestMapping(value = "/updateWorkflowUrl", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam("list") String id) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			List<LinkedHashMap<String, String>> list = objectMapper.readValue(
					id, List.class);
			map = list.get(0);
			map.put("type", "update");
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("workflow/workflow", map);
	}
	
	/**
	 * 查询工作流
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, Workflow workflow){
		return workFlowService.getPageList(dgm, workflow);
	}
	
	/**
	 * 保存工作流
	 */
	@RequestMapping(value = "/saveAdd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addMenu(@RequestParam("node") String node,@RequestParam("tran") String tran,@RequestParam("name") String name,@RequestParam("id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			List<LinkedHashMap<String, Object>> Node = objectMapper.readValue(node, List.class);
			List<LinkedHashMap<String, Object>> Tran = objectMapper.readValue(tran, List.class);
			id = workFlowService.generateDocument(Node, Tran, name, id);
			map.put("id", id);
			map.put("mes", "操作成功");
			log.info("工作流保存成功");
		} catch (CisCoreException e) {
			map.put("mes", "操作失败，原因是：" + e.getMessage());
			log.info("工作流保存失败");
		} catch (Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 更新工作流
	 */
	@RequestMapping(value = "/getHtml", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getHtml(@RequestParam("id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		String html = workFlowService.getHtml(id);
		map.put("mes", html);
		return map;
	}
	
	/**
	 * 删除工作流
	 */
	@RequestMapping(value = "/delWorkflow", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String>  delWorkflow(@RequestParam("wid") List<Long> wid) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Long id : wid) {
				workFlowService.deleteWorkflow(id);
			}
			map.put("mes", "删除成功");
			log.info("删除成功");
		} catch (CisCoreException e) {
			map.put("mes", "删除失败,原因是：" + e.getMessage());
			log.info("删除失败");
		}
		return map;// 重定向
	}
	
	/**
	 * 工作流展示页面
	 */
	@RequestMapping(value = "/showWorkflowUrl", method = RequestMethod.GET)
	public String  showWorkflowUrl() {
		return "workflow/showWorkflow";
	}
	
	/**
	 * 工作流展示页面
	 */
	@RequestMapping(value = "/showWorkflowDemoUrl", method = RequestMethod.GET)
	public ModelAndView  showWorkflowDemoUrl(@RequestParam("key") String key) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		return new ModelAndView("workflow/showWorkflow", map);
	}
	
	/**
	 * 工作流展示
	 */
	@RequestMapping(value = "/showWorkflow", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  showWorkflow(@RequestParam("active") String active,@RequestParam("key") String key,@RequestParam("chiose") String chiose) {
		Map<String, Object> map = new HashMap<String, Object>();
		List list = workFlowService.showWorkflow(active,key,chiose);
		map.put("mes", list);
		return map;
	}
	
	/**
	 * 工作流展示
	 */
	@RequestMapping(value = "/showWorkflowDemo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  showWorkflowDemo(@RequestParam("key") String key) {
		Map<String, Object> map = new HashMap<String, Object>();
		List list = workFlowService.getState(key, "NB");
		map.put("mes", list);
		return map;
	}
	
	/**
	 * 工作流明细页面
	 */
	@RequestMapping(value = "/queryWorkflowUrl", method = RequestMethod.GET)
	public String  queryWorkflow() {
		return "workflow/queryWorkflow";
	}
	
	/**
	 * 查询工作流明细
	 */
	@RequestMapping(value = "/queryWorkflow", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryWorkflow(DataGridModel dgm, @RequestParam("key") String key){
		return workFlowService.getWorkflowList( key);
	}
}
