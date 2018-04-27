package com.sinosoft.core.interfaces.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.core.application.SystemService;
import com.sinosoft.core.domain.model.user.Privilege;
import com.sinosoft.core.interfaces.util.DataGridModel;

@Controller
@RequestMapping("/system")
public class SystemController {
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private SystemService systemService;
	private static final Logger log = Logger.getLogger(SystemController.class);

	/**
	 * 获取页面url
	 */
	@RequestMapping(value = "/listSystemUrl", method = RequestMethod.GET)
	public String listSystemUrl() {
		return "system/listSystem";
	}

	/**
	 * 获取新增应用url
	 */
	@RequestMapping(value = "/addSystemUrl", method = RequestMethod.GET)
	public String addSystemUrl() {
		return "system/addSystem";
	}

	/**
	 * 获取更新应用url
	 */
	@RequestMapping(value = "/updateSystemUrl", method = RequestMethod.GET)
	public ModelAndView updateSystemUrl(@RequestParam("list") String s) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(
					s, List.class);
			map = list.get(0);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("system/updateSystem", map);
	}

	/**
	 * 获取应用数据
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, Privilege privilege){
		return systemService.getPageList(dgm, privilege);
	}

	/**
	 * 新增应用
	 */
	@RequestMapping(value = "/saveAdd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addSystem(Privilege privilege) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			systemService.saveSystem(privilege);
			map.put("mes", "操作成功");
			log.info("应用增加成功");
		} catch (Exception e) {
			map.put("mes", "操作失败");
			log.info("应用增加失败");
			e.printStackTrace();

		}
		return map;
	}
	
	/**
	 * 删除应用
	 */
	@RequestMapping(value = "/deleteSystem", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteSystem(@RequestParam("pid") List<Integer> pid)
			throws Exception {
		// spring mvc 还可以将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Integer id : pid) {
				systemService.deleteSystem(id);
			}
			map.put("mes", "删除成功");
			log.info("应用删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			log.info("应用删除失败");
		}
		return map;// 重定向
	}

	/**
	 * 修改应用
	 */
	@RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateSystem(Privilege privilege) {
		Map<String, String> map = new HashMap<String, String>();
		try {
				systemService.updatePrivilege(privilege);
			map.put("mes", "操作成功");
			log.info("应用更新成功");
		} catch (Exception e) {
			map.put("mes", "操作失败");
			log.info("应用更新失败");
			e.printStackTrace();
		}
		return map;
	}

}
