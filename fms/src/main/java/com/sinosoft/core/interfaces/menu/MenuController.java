package com.sinosoft.core.interfaces.menu;

/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 * Description 菜单控制器
 *****************************************************/
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.core.application.MenuService;
import com.sinosoft.core.application.util.Nodes;
import com.sinosoft.core.db.model.DefPrivilege;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.StringUtils;

@Controller
@RequestMapping("/menu")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class MenuController
{
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private MenuService menuService;
	private static final Logger log = Logger.getLogger(MenuController.class);

	/**
	 * 获取页面url
	 */
	@RequestMapping(value = "/listMenuUrl", method = RequestMethod.GET)
	public String listMenuUrl()
	{
		return "menu/listMenu";
	}

	/**
	 * 获取新增菜单url
	 */
	@RequestMapping(value = "/addMenuUrl", method = RequestMethod.GET)
	public String addMenuUrl()
	{
		return "menu/addMenu";
	}

	/**
	 * 获取更新菜单url
	 */
	@RequestMapping(value = "/updateMenuUrl", produces = "application/json; charset=utf-8")
	public ModelAndView updateMenuUrl(@RequestParam("list") String s)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try
		{
			s = StringUtils.encodeStr(s);
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(s, List.class);
			map = list.get(0);
		}
		catch (JsonParseException e)
		{
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return new ModelAndView("menu/updateMenu", map);
	}

	/**
	 * 获取菜单数据
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm,
			DefPrivilege defPrivilege)
	{
		return menuService.getPageList(dgm, defPrivilege);
	}

	/**
	 * 新增菜单
	 */
	@RequestMapping(value = "/saveAdd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addMenu(@RequestParam("list") String s,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{	
			s = StringUtils.encodeStr(s);
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(s, List.class);
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			menuService.saveMenu(list,loginInfo);
			map.put("mes", "操作成功");
			map.put("succ", "true");
			log.info("菜单增加成功");
		}
		catch (Exception e)
		{
			map.put("mes", "操作失败");
			map.put("succ", "false");
			log.info("菜单增加失败,原因是" + e.getMessage());
			e.printStackTrace();

		}
		return map;
	}

	/**
	 * 删除菜单
	 */
	@RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteMenu(
			@RequestParam("pid") List<Long> pid,ModelMap model) throws Exception
	{
		// spring mvc 还可以将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			for (Long id : pid)
			{
				menuService.deleteMenu(id,loginInfo);
			}
			map.put("mes", "删除成功");
			map.put("succ", "true");
			log.info("菜单删除成功");
		}
		catch (Exception e)
		{
			map.put("succ", "false");
			map.put("mes", "操作失败，原因是" + e.getMessage());
			log.info("菜单删除失败，原因是" + e.getMessage());
		}
		return map;// 重定向
	}

	/**
	 * 修改菜单
	 */
	@RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateMenu(@RequestParam("list") String s,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{	
			s = StringUtils.encodeStr(s);
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(
					s, List.class);
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			for (int i = 0; i < list.size(); i++)
			{
				DefPrivilege defPrivilege = new DefPrivilege();
				defPrivilege.setPrivilegeName(list.get(i).get("name")
						.toString());

				String tPrivilegeId = list.get(i).get("id").toString();
				String tPid = list.get(i).get("pid").toString();
				String tPre = list.get(i).get("pre").toString();
				String tNext = list.get(i).get("next").toString();
				if (tPrivilegeId != null && !"".equals(tPrivilegeId))
				{
					defPrivilege.setPrivilegeId(new Long(tPrivilegeId));
				}

				if (tPid != null && !"".equals(tPid))
				{
					defPrivilege.setPid(new Long(tPid));
				}
				if (tPre != null && !"".equals(tPre))
				{
					defPrivilege.setPre(new Long(tPre));
				}
				if (tNext != null && !"".equals(tNext))
				{
					defPrivilege.setNext(new Long(tNext));
				}

				defPrivilege.setUrl(list.get(i).get("url").toString());
				defPrivilege.setMethod((list.get(i).get("method") == null) ? ""
						: list.get(i).get("method").toString());
				menuService.updatePrivilege(defPrivilege,loginInfo);
			}
			map.put("mes", "操作成功");
			map.put("succ", "true");
			log.info("菜单更新成功");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("mes", "操作失败，原因是" + e.getMessage());
			log.info("菜单修改失败，原因是" + e.getMessage());
		}
		return map;
	}

	/**
	 * 查询菜单
	 */
	@RequestMapping(value = "/listMenu", method = RequestMethod.POST)
	@ResponseBody
	public List<Nodes> listMenu(ModelMap model)
	{
 		return menuService.queryMenu();
	}
}
