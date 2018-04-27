package com.sinosoft.core.interfaces.role;
/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 * Description 规则控制器
 *****************************************************/
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.core.application.MenuService;
import com.sinosoft.core.application.RoleService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.application.util.Nodes;
import com.sinosoft.core.db.model.DefPrivilege;
import com.sinosoft.core.db.model.DefRole;
import com.sinosoft.core.db.model.DefRolePrivilegeRela;
import com.sinosoft.core.domain.model.user.Role;
import com.sinosoft.core.domain.model.user.Role2privilege;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.StringUtils;

/**
 * @author gyas-itzijunj
 *
 */
@Controller
@RequestMapping("/role")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class RoleController {
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	private static final Logger log = Logger.getLogger(RoleController.class);

	/**
	 * 获取角色页面url
	 */
	@RequestMapping(value = "/listRoleUrl", method = RequestMethod.GET)
	public String rolelist() {
		return "role/listRole";
	}

	/**
	 * 查询角色列表
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, DefRole defRole) {
		// spring可以自动装配两个对象 会自动的装返回的Map转换成JSON对象
		return roleService.getPageList(dgm, defRole);
	}

	/**
	 * 获取角色增加页面url
	 */
	@RequestMapping(value = "/addRoleUrl", method = RequestMethod.GET)
	public String menuadd() {
		return "role/addRole";
	}

	/**
	 * 获取角色更新页面url
	 */
	@RequestMapping(value = "/updateRoleUrl", method = RequestMethod.GET)
	public ModelAndView updateRoleUrl(@RequestParam("list") String s) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			s = StringUtils.encodeStr(s);
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(s, List.class);
			map = list.get(0);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("role/updateRole", map);
	}

	/**
	 * 获取分配菜单页面url
	 */
	@RequestMapping(value = "/setRoleUrl", method = RequestMethod.GET)
	public ModelAndView setRoleUrl(@RequestParam("list") String s) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			s = StringUtils.encodeStr(s);
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(s, List.class);
			map = list.get(0);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("role/setRole", map);
	}

	/**
	 * 获取分配系统页面url
	 */
	@RequestMapping(value = "/setSystemUrl", method = RequestMethod.GET)
	public ModelAndView setSystemUrl(@RequestParam("list") String s) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(s, List.class);
			map = list.get(0);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("role/setSystem", map);
	}

	/**
	 * 增加菜单
	 */
	@RequestMapping(value = "/saveAdd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAdd(DefRole defRole,ModelMap model) {
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			roleService.save(defRole,loginInfo);
			map.put("mes", "操作成功");
			map.put("succ", "true");
			log.info("角色增加成功");
		} catch (CisCoreException e) {
			e.printStackTrace();
			map.put("succ", "false");
			map.put("mes", "操作失败，原因是" + e.getMessage());
			log.info("角色增加失败，原因是" + e.getMessage());
		}
		return map;
	}

	/**
	 * 更新菜单
	 */
	@RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveUpdate(DefRole defRole,ModelMap model) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			roleService.updateRole(defRole,loginInfo);
			map.put("mes", "操作成功");
			map.put("succ", "true");
			log.info("角色更新成功");
		} catch (CisCoreException e) {
			e.printStackTrace();
			map.put("succ", "false");
			map.put("mes", "操作失败，原因是" + e.getMessage());
			log.info("角色更新失败，原因是" + e.getMessage());
		}
		return map;
	}

	/**
	 * 分配菜单
	 */
	@RequestMapping(value = "/saveSet", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveSet(@RequestParam("list") String s,ModelMap model) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(s, List.class);
			LinkedHashMap<String, Object> m = list.get(0);
			Long roleId = new Long(m.get("roleId").toString());
			roleService.deleteR2P(roleId, "localmenu",loginInfo);
			roleService.deleteR2P(roleId, "remotemenu",loginInfo);
			DefRole defRole = roleService.get(roleId);
			ArrayList menuId = (ArrayList)m.get("menuId");
			ArrayList distinctMenuId = distinctMenuId(menuId);
			for(int i=0;i<distinctMenuId.size();i++){
				DefRolePrivilegeRela defRolePrivilegeRela = new DefRolePrivilegeRela();
				
				//菜单项
				String id = distinctMenuId.get(i).toString();
				defRolePrivilegeRela.setPrivilegeId(new Long(id));
				//角色项
				defRolePrivilegeRela.setRoleId(roleId);
				roleService.saveSet(defRolePrivilegeRela,loginInfo);
			}
			map.put("mes", "操作成功");
			map.put("succ", "true");
			log.info("分配菜单成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("succ", "false");
			map.put("mes", "操作失败");
			log.info("分配菜单失败");
		}
		return map;
	}

	/**去除重复的菜单
	 * @param menuIds
	 * @return 
	 */
	private ArrayList distinctMenuId(ArrayList menuIds) {
		ArrayList list = new ArrayList();
		for (Object menuId : menuIds) {
			if(!list.contains(menuId)){
				list.add(menuId);
			}
		}
		return list;
	}

	/**
	 * 分配应用
	 */
	@RequestMapping(value = "/saveSetSystem", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveSetSystem(@RequestParam("list") String s,ModelMap model) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(s, List.class);
			LinkedHashMap<String, Object> m = list.get(0);
			Long roleId = new Long(m.get("roleId").toString());
			roleService.deleteR2P(roleId, "system",loginInfo);
			DefRole defRole = roleService.get(roleId);
			ArrayList menuId = (ArrayList) m.get("menuId");
			for (int i = 0; i < menuId.size(); i++) {
				Role2privilege role2privilege = new Role2privilege();
				String id = menuId.get(i).toString();
				//role2privilege.setPrivilege(menuService.get(Integer.parseInt(id)));
				//role2privilege.setRole(defRole);
				//roleService.saveSet(role2privilege);
			}
			// roleService.save(role);
			map.put("mes", "操作成功");
			map.put("succ", "true");
			log.info("分配应用成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("false", "true");
			map.put("mes", "操作失败");
			log.info("分配应用失败");
		}
		return map;
	}

	/**
	 * 删除菜单
	 */
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestParam("rid") List<Long> rid,ModelMap model) throws Exception {
		// spring mvc 还可以将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			for(Long id : rid){
				roleService.deleteRole(id,loginInfo);
			}
			map.put("mes", "删除成功");
			map.put("succ", "true");
			log.info("角色删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("succ", "false");
			map.put("mes", "删除失败");
			log.info("角色删除失败");
		}
		return map;// 重定向
	}

	/**
	 * 查询角色已有菜单
	 */
	@RequestMapping(value = "/queryMenu", method = RequestMethod.POST)
	@ResponseBody
	public List<Nodes> queryMenu(@RequestParam("roleId") String rid) {
		return roleService.queryMenu(new Long(rid));
	}

	/**
	 * 查询角色已有菜单
	 */
	@RequestMapping(value = "/queryMenuWithRemote", method = RequestMethod.POST)
	@ResponseBody
	public List<Nodes> queryMenuWithRemote(@RequestParam("roleId") String rid) {
		return roleService.queryMenu(new Long(rid), true);
	}

	/**
	 * 查询已有角色
	 */
	@RequestMapping(value = "/listRole", method = RequestMethod.POST)
	@ResponseBody
	public List<Nodes> listRole() {
		return roleService.queryRole();
	}

	/**
	 * 查询角色已有系统
	 */
	@RequestMapping(value = "/querySystem", method = RequestMethod.POST)
	@ResponseBody
	public List<Nodes> querySystem(@RequestParam("roleId") String rid) {
		return roleService.querySystem(new Long(rid));
	}

	/**
	 * 查询所有系统
	 */
	@RequestMapping(value = "/listSystem", method = RequestMethod.POST)
	@ResponseBody
	public List<Nodes> listSystem() {
		return roleService.querySystem();
	}

}
