package com.sinosoft.core.interfaces.user;

/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 * Description 首页控制器
 *****************************************************/
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.core.application.UserService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.application.util.Nodes;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.util.LoginInfo;

@Controller
@RequestMapping("/welcome")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class WelcomeController {

	@Autowired
	private UserService userService;

	private static final Logger log = Logger.getLogger(WelcomeController.class);

	/**
	 * 获取页面url
	 */
	@RequestMapping(value = "/listStartUrl", method = RequestMethod.GET)
	public String listMenuUrl() {
		return "start";
	}

	@RequestMapping(value = "/loginUrl", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> findUserByName(DefUser defUser, ModelMap model,HttpSession session) {
		System.out.println("这是登陆的账号>>"+defUser.getUserCode());
		session.setAttribute("user", userService.getDefUserAllByUserCode(defUser.getUserCode()));
		DefUser du=(DefUser) session.getAttribute("user");
		System.out.println(du.getUserName()+"这是姓名");
		HashMap<String, String> msgMap = new HashMap<String, String>();
		try {
			userService.findUserByUser(defUser);
			msgMap.put("msg", "success");
			log.info("用户登录成功");
		} catch (CisCoreException e) {
			msgMap.put("msg", "fail");
			msgMap.put("code", e.getErrorCode());
			msgMap.put("message", e.getMessage());
			msgMap.put("usercode", defUser.getUserCode());
			msgMap.put("password", defUser.getPassword());
			msgMap.put("companyCode", String.valueOf(defUser.getComId()));
			log.info("用户登录失败");
		}
		return msgMap;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView welcomeUrl(DefUser defUser, ModelMap model) {
		DefUser returnUser = userService.findUserByUser(defUser);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserId(returnUser.getUserId());
		loginInfo.setUserCode(returnUser.getUserCode());
		loginInfo.setComId(returnUser.getComId());
		loginInfo.setLoginComId(returnUser.getComId());
		String userRolePivilege = this.userService.getUserRolePrivilege(returnUser);
		loginInfo.setRolePivilege(userRolePivilege);
		model.addAttribute(Constants.USER_INFO_SESSION, loginInfo); 
		// 名为Constants.USER_INFO_SESSION的属性放到Session属性列表中
		HashMap<String, String> map = userService.queryLoginInfo(loginInfo);
		return new ModelAndView("welcome", map);
	}

	@RequestMapping(value = "/reloadUrl", method = RequestMethod.GET)
	public String addUserUrl(){
		return  "reloadurl";
	}
	
	@RequestMapping(value = "/sessionReloadUrl", method = RequestMethod.GET)
	public String sessionReloadUrl(){
		return  "sessionreloadurl";
	}
	
	@RequestMapping(value = "/queryMenu", method = RequestMethod.POST)
	@ResponseBody
	public List<Nodes> queryMenu(ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		return userService.queryMenu(loginInfo.getUserId());
	}

}
