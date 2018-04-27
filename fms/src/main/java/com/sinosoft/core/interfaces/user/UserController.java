package com.sinosoft.core.interfaces.user;
/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 * Description 用户控制器
 *****************************************************/
import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fms.db.model.ComBelongTrace;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sinosoft.core.application.RoleService;
import com.sinosoft.core.application.UserService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.application.util.Nodes;
import com.sinosoft.core.db.model.DefCom;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserRoleRela;
import com.sinosoft.core.domain.model.user.User;
import com.sinosoft.core.domain.model.user.User2role;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.StringUtils;

@Controller
@RequestMapping("/user")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class UserController {
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;


	private static final Logger log = Logger.getLogger(UserController.class);
	/**
	 * 获取用户页面url
	 */
	@RequestMapping(value = "/listUserUrl", method = RequestMethod.GET)
	public String list(Model model) {
		return "user/listUser";
	}
	

	/**
	 * 获取用户页面url
	 */
	@RequestMapping(value = "/userTestUrl", method = RequestMethod.GET)
	public String testUser() {
		return "user/testUser";
	}
	/**
	 * 查询用户列表
	 */
	@RequestMapping(value = "/queryList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryList(DataGridModel dgm, DefUser defUser){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(userService.getPageList(dgm, defUser));
	}
	/**
	 * 获取增加用户页面url
	 */
	@RequestMapping(value = "/addUserUrl", method = RequestMethod.GET)
	public String addUserUrl(){
		return "user/addUser";
	}
	
	/**
	 * 获取增加用户页面url
	 */
	@RequestMapping(value = "/detailUserUrl", method = RequestMethod.GET)
	public String detailUserUrl(){
		return "user/detailUser";
	}
	
	/**
	 * 获取机构更新页面URL
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/updateUserUrl", method = RequestMethod.GET)
	public String updateUserUrl()
	{
		return "user/updateUser";
	}
	
	/**
	 * 机构更新页面初始化页面控件页面
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/updateUserInitUrl", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateUserInit(@RequestParam("userId") String userId)//
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.userService.getUpdateListInfo(new Long(userId));
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(map);
	}
	
	/**
	 * 增加用户
	 */
	@RequestMapping(value = "/saveAdd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAdd(String param,ModelMap model) {
		Map<String, String> map = new HashMap<String, String>();
		try 
		{
			log.info("param===" + param);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String userInfo = JsonUtils.getJsonValueByKey("userInfo", param);
			DefUser userSchema = gson.fromJson(userInfo, DefUser.class);
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			userService.addUser(userSchema,loginInfo);
			map.put("succ", "true");
			map.put("msg", "新增保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "操作失败,原因是"+e.getMessage());
		}
		return map;
	}
	/**
	 * 更新用户
	 */
	@RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveUpdate(String param,ModelMap model) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			log.info("param===" + param);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String userInfo = JsonUtils.getJsonValueByKey("userInfo", param);
			DefUser userSchema = gson.fromJson(userInfo, DefUser.class);
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			userService.updateUser(userSchema,loginInfo);
			map.put("msg", "操作成功");
			map.put("succ", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "操作失败,原因是:"+e.getMessage());
		}
		return map;
	}
	/**
	 * 获取分配角色页面url
	 */
	@RequestMapping(value = "/setUserUrl", method = RequestMethod.GET)
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
		return new ModelAndView("user/setUser", map);
	}
	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestParam("id") List<Long> uid,ModelMap model) {
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			for (Long id : uid) {
				userService.deleteUser(id,loginInfo);
			}
			map.put("mes", "删除成功");
			map.put("succ", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("succ", "false");
			map.put("mes", "删除失败,原因是:"+e.getMessage());
		}
		return map;// 重定向
	}
	/**
	 * 根据用户id直接重置用户密码至默认
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> resetPwd(@RequestParam("id") List<Long> uid,ModelMap model) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Long id : uid) {
				userService.resetPwd(id);
			}
			 map.put("mes", "密码重置成功!");
			 map.put("succ", "true");
			 log.info("密码重置成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("succ", "false");
			map.put("mes", "密码重置失败,原因是:"+e.getMessage());
		}
		return map; 
	}
	/**
	 * 查询用户已有角色
	 */
	@RequestMapping(value = "/queryRole", method = RequestMethod.POST)
	@ResponseBody
	public List<Nodes> queryRole(@RequestParam("userId") String uid) throws Exception {
		return userService.queryRole(new Long(uid));
	}
	/**
	 * 分配角色
	 */
	@RequestMapping(value = "/saveSet", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveSet(@RequestParam("list") String s,ModelMap model) {
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(s, List.class);
			LinkedHashMap<String, Object> m = list.get(0);
			Long userId = new Long(m.get("userId").toString());
			userService.deleteU2R(userId,loginInfo);
			DefUser defUser = userService.get(userId);
			ArrayList roleId = (ArrayList)m.get("roleId");
			for(int i=0;i<roleId.size();i++){
				DefUserRoleRela defUserRoleRela = new DefUserRoleRela();
				String id = roleId.get(i).toString();
				defUserRoleRela.setRoleId(new Long(id));
				defUserRoleRela.setUserId(userId);
				userService.saveSet(defUserRoleRela,loginInfo);
			}
			map.put("mes", "操作成功");
			map.put("succ", "true");
			log.info("角色分配成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("succ", "false");
			map.put("mes", "删除失败,原因是:"+e.getMessage());
			log.info("角色分配失败");
		}
		return map;
	}
	
	/**
	 *modifyUserUrl
	 * */
	/**
	 * 获取修改用户名页面url
	 */
	@RequestMapping(value = "/modifyUserUrl", method = RequestMethod.GET)
	public String modifyUserUrl(){
		return "user/modifyUser";
	}
	
	
	/**
	 * 保存修改过的用户名和密码
	 */
	@RequestMapping(value = "/modifySave", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> modifySave(@RequestParam("password") String password,@RequestParam("password1") String password1,ModelMap model) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try
		{
	
		 LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		 userService.modifySave(password,password1,loginInfo.getUserId());
		 map.put("mes", "操作成功");
		 map.put("succ", "true");
		 log.info("密码修改成功");
		}catch(Exception e){
			map.put("succ", "false");
			map.put("mes", "删除失败,原因是:"+e.getMessage());
			log.info("密码修改失败");
		}
		 return map;
	}
	
	
	
}
