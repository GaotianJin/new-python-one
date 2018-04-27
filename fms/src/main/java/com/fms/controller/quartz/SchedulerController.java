package com.fms.controller.quartz;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.alibaba.fastjson.JSONArray;
import com.fms.service.quartz.SchedulerService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.domain.model.quartz.JobDetails;
import com.sinosoft.core.domain.model.quartz.Tcschedulerallocate;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/quartz")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class SchedulerController extends SimpleFormController {

	@InitBinder
	protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
		super.initBinder(request, binder);
	}

	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Autowired
	private SchedulerService schedulerService;
	 

	/**
	 * 获取页面url
	 */
	@RequestMapping(value = "/listSchedulerUrl", method = RequestMethod.GET)
	public String listMenuUrl() {
		return "fms/quartz/listScheduler";
	}

	/**
	 * 获取页面url
	 */
	@RequestMapping(value = "/addSchedulerUrl", method = RequestMethod.GET)
	public String addScheduler() {
		return "fms/quartz/addScheduler";
	}

	/**
	 * 获取调度数据
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm,HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String job_name = request.getParameter("job_name");
			String trigger_name = request.getParameter("trigger_name");
			return schedulerService.getPageList(dgm, job_name, trigger_name);
		} catch (CisCoreException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取所有任务
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryJob", method = RequestMethod.POST)
	@ResponseBody
	public List queryJob(DataGridModel dgm, HttpServletRequest request) {
		try {
			return schedulerService.queryJob();
		} catch (CisCoreException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 添加触发器
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveAdd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAdd(@RequestParam("list") String s) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Map<String, String> resultmap = new HashMap<String, String>();
		try {
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(s, List.class);
			map = list.get(0);
			System.out.println("savaAdd:"+map);
			schedulerService.schedule(map);
			resultmap.put("mes", "操作成功");
		} catch (JsonParseException e) {
			e.printStackTrace();
			resultmap.put("mes", "操作失败,原因是" + e.getMessage());
		} catch (JsonMappingException e) {
			e.printStackTrace();
			resultmap.put("mes", "操作失败,原因是" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			resultmap.put("mes", "操作失败,原因是" + e.getMessage());
		} catch (CisCoreException e) {
			e.printStackTrace();
			resultmap.put("mes", "操作失败,原因是" + e.getMessage());
		}
		return resultmap;
	}

	/**
	 * 删除触发器
	 */
	@RequestMapping(value = "/deleteSchedulerUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam("trigger_name") String[] trigger_names) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (String trigger_name : trigger_names) {
				schedulerService.removeTrigger(trigger_name, "DEFAULT");
			}
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
		}
		return map;// 重定向
	}

	/**
	 * 暂停触发器
	 */
	@RequestMapping(value = "/pauseSchedulerUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> pauseScheduler(
			@RequestParam("trigger_name") String[] trigger_names) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (String trigger_name : trigger_names) {
				schedulerService.pauseTrigger(trigger_name, "DEFAULT");
			}
			map.put("mes", "暂停成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "暂停失败");
		}
		return map;// 重定向
	}

	/**
	 * 恢复触发器
	 */
	@RequestMapping(value = "/resumeSchedulerUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> resumeScheduler(
			@RequestParam("trigger_name") String[] trigger_names) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (String trigger_name : trigger_names) {
				schedulerService.resumeTrigger(trigger_name, "DEFAULT");
			}
			map.put("mes", "恢复成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "恢复失败");
		}
		return map;// 重定向
	}

	/**
	 * 获取基本任务查询页面
	 */
	@RequestMapping(value = "/listBaseTaskUrl", method = RequestMethod.GET)
	public String listBaseTask() {
		return "quartz/listBaseTask";
	}

	/***
	 * 页面显示基本任务列表请求
	 * 
	 * @param dgm
	 * @param business
	 * @return
	 */
	@RequestMapping(value = "/queryBaseTaskList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryBaseTaskList(DataGridModel dgm,
			JobDetails jobDetails) {
		System.out.println("查询出来：" + jobDetails.getJobName() + ""
				+ jobDetails.getId());
		return schedulerService.getPageList(dgm, jobDetails);
	}
	
	/**
	 * 
	 * 页面显示调度关系的列表请求
	 * @param dgm
	 * @param jobDetails
	 * @return
	 */
	
	@RequestMapping(value = "/querSchedulerAllocateList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> querSchedulerAllocateList(DataGridModel dgm,
			Tcschedulerallocate tcschedulerallocate) {
		System.out.println("查询出来：" + tcschedulerallocate.getJobtname1() + ""
				+ tcschedulerallocate.getId());
		return schedulerService.getPageList(dgm, tcschedulerallocate);
	}

	/**
	 * 查询基本任务下拉名称列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryJobTnameCombox", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryJobTnameCombox() {
		JSONArray jsonArray;
		try {
			List reslist = new ArrayList();
			List list = schedulerService.queryJobTnameCombox();

			for (int i = 0; i < list.size(); i++) {
				Map map = new HashMap();
				JobDetails tc = (JobDetails) list.get(i);
				map.put("schedulername", tc.getJobTname());
				map.put("id", tc.getJobTname());
				reslist.add(map);
			}
			jsonArray = new JSONArray();
			jsonArray.addAll(reslist);
			System.out.println("jsonArray0000:" + jsonArray.toJSONString());
			return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 根据基本任务名称下拉选择处理方法名 */
	@RequestMapping(value = "/queryJobNameCombox", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, String> queryJobNameCombox(JobDetails jobdetails) {
		System.out.println("---------:"+jobdetails.getJobTname());
		Map<String, String> map = new HashMap<String, String>();
		try {
			JobDetails tjobdetails = schedulerService
					.queryJobNameByTname(jobdetails.getJobTname());
			map.put("jobname", tjobdetails.getJobName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取页面新增基本任务页面
	 */
	@RequestMapping(value = "/addBaseTaskUrl", method = RequestMethod.GET)
	public String addBaseTask() {
		System.out.println("进入addBaseTask");
		return "quartz/addBaseTask";
	}

	/**
	 * 新增基本任务的实现方法
	 * 
	 * */
	@RequestMapping(value = "/saveAddBaseTask", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAddBaseTask(JobDetails jobdetails) {

		System.out.println("-----saveAdd--:" + jobdetails.getMakedate()+"||"+jobdetails.getJobName()+jobdetails.getJobTname()+jobdetails.getRemark1());

		Map<String, String> map = new HashMap<String, String>();
		try {

			schedulerService.addBaseTask(jobdetails);
			map.put("mes", "操作成功");
		} catch (CisCoreException e) {
			e.printStackTrace();
			map.put("mes", "操作失败,原因是" + e.getMessage());
		}
		return map;
	}
	/**
	 * 新增调度关系配置的实现方法
	 * 
	 */
	@RequestMapping(value = "/saveAddSchedulerAllocate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String>saveAddSchedulerAllocate(Tcschedulerallocate tcschedulerallocate) {

		System.out.println("-----saveAdd--:" + tcschedulerallocate.getJobtname1()+"||"+tcschedulerallocate.getJobtname2());

		Map<String, String> map = new HashMap<String, String>();
		try {

			schedulerService.addSchedulerAllocate(tcschedulerallocate);
			map.put("mes", "操作成功");
		} catch (CisCoreException e) {
			e.printStackTrace();
			map.put("mes", "操作失败,原因是" + e.getMessage());
		}
		return map;
	}
	
	
	/**
	 * 获取调度配置的页面
	 */
	@RequestMapping(value = "/listSchedulerAllocateUrl", method = RequestMethod.GET)
	public String listSchdulerAllocate() {
		System.out.println("进入------listSchedulerAllocate");
		//return "quartz/listSchedulerAllocate";
		return  "quartz/listQuartzAllocate";
	}
	
	/**
	 * 获取新增调度分配的页面
	 */
 
	@RequestMapping(value = "/addQuartzAllocateUrl", method = RequestMethod.GET)
	public String addSchdulerAllocate() {
		System.out.println("进入------addQuartzAllocate");
		return "quartz/addQuartzAllocate";
	}
	/**
	 * 
	 * listPathQueryUrl
	 */

	@RequestMapping(value = "/listPathQueryUrl", method = RequestMethod.GET)
	public String listPathQuery() {
		System.out.println("进入--批处理操作轨迹查询----listPathQuery");
		return "quartz/listPathQuery";
	}

	/**
	 * 获取页面url
	 */
	@RequestMapping(value = "/handSchedulerUrl", method = RequestMethod.GET)
	public String handSchedulerUrl() {
		return "quartz/handScheduler";
	}

	/**
	 * 手动执行NFO接口批处理
	 */
	/*@RequestMapping(value = "/handDealTaskNFOUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> handDealTaskNFO() {
		HashMap<String, String> msgMap = new HashMap<String, String>();
		try {
			ybtnfoBatch.uploadData();
			msgMap.put("msg", "success");
		} catch (CisCoreException e) {
			e.printStackTrace();
			msgMap.put("msg", "操作失败,原因是：" + e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			msgMap.put("msg", "操作失败,原因是：出现未知异常");
		}
		return msgMap;
	}*/
}
