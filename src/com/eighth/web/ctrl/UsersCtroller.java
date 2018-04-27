package com.eighth.web.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.entity.Askers;
import com.eighth.entity.Roles;
import com.eighth.entity.UserRoles;
import com.eighth.entity.Users;
import com.eighth.service.NetStudentService;
import com.eighth.service.RolesService;
import com.eighth.service.SetWeightService;
import com.eighth.service.UserRolesService;
import com.eighth.service.UsersService;
import com.eighth.util.StringUtil;

@Controller
public class UsersCtroller {
	@Autowired
	private UsersService service;
	
	@Autowired
	private UserRolesService urser;
	@Autowired
	private RolesService rser;
	@Autowired 
    private SetWeightService weightser;
	@Autowired
	private NetStudentService stuser;
	/**
	 * 显示所有用户
	 * */
	@RequestMapping("/ShowUsers")
	@ResponseBody
	public Object getAllUser(int page,int rows,String s_Username,String s_Useremail,String s_Userphone){
		Users u=new Users(StringUtil.isNull(s_Username), StringUtil.isNull(s_Useremail), StringUtil.isNull(s_Userphone),page,rows);
		Map map=service.getDatagrid(u);
		return map;
	}

	/**
	 * 添加用户
	 * @param u
	 * @return
	 */
	@RequestMapping("/addUsers")
	@ResponseBody
	public Object addUser(Users u){
		u.setUserId(StringUtil.getUUID());
		Map map=new HashMap();
		if(service.selectUserNameByuserName(u)==null){
			service.insertUsers(u);
			map.put("success", true);
			map.put("message", "添加成功！");
		}else{
				map.put("success", false);
				map.put("message", "用户名重复！添加失败！");
			}
		return map;
	}
	
	/**
	 * 修改用户
	 * @param u
	 * @return
	 */
	@RequestMapping("/modUsers")
	@ResponseBody
	public Object modUser(Users u){
		Map map=new HashMap();
		if(service.selectUserNameByuserName(u)==null||u.getUserId().equals(service.selectUserIdByUserName(u))){
			service.updateUsers(u);
			map.put("success", true);
			map.put("message", "修改成功！");
		}else{
				map.put("success", false);
				map.put("message", "用户名重复！修改失败！");
			}
		return map;
	}
	
	/**
	 * 删除用户
	 * @param delIds
	 * @return
	 */
	@RequestMapping("/delUsers")
	@ResponseBody
	public Object delUser(String id){
		int row=0;
		Map map=new HashMap();
		List<UserRoles> list=urser.selectRoleIdByUserId(id);
		if(list.size()==0){
			row=service.deleteUsers(id);
			map.put("success", true);
			map.put("delNum", row);
		}else{
			map.put("success", false);
			map.put("errorMsg", "该用户拥有角色,无法删除！");
		}
		return map;
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/ResetUsersPwd")
	@ResponseBody
	public Object ResetPwd(String id,HttpSession session){
		int row=service.updateUsersPwdById(id);
		Users u=(Users) session.getAttribute("user");
		String name=service.selectNameById(id);
		if(u.getUserName().equals(name)||u.getUserName()==name){
			if(row==0){
				return "shibai";
			}else{
				return "czmmyesdl";
			}
		}else{
			if(row==0){
				return "shibai";
			}else{
				return "czmmnodl";
			}
		}
		
	}
	
	/**
	 * 锁定用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/LockUsers")
	@ResponseBody
	public Object LockTrueUser(String id){
		int row=service.updateUsersLockTrueById(id);
		if(row==0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 解锁用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/DeLockUsers")
	@ResponseBody
	public Object LockFalseUser(String id){
		int row=service.updateUsersLockFalseById(id);
		service.updateClearLocknum(id);
		if(row==0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 设置角色
	 * @param ur
	 * @return
	 */
	@RequestMapping("/insertUserRoles")
	@ResponseBody
	public Object addUserRoles(UserRoles ur){
		int row=urser.selectRole(ur.getUserId(), ur.getRoleId());
		String askerName=service.selectNameById(ur.getUserId());
		if(row==0){
			urser.insertUserRoles(ur);
			if(ur.getRoleId()=="3"||ur.getRoleId().equals("3")){
				Askers a=new Askers();
				a.setAskerId(ur.getUserId());
				a.setAskerName(askerName);
				weightser.insertAskers(a);
			}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除角色
	 * @param ur
	 * @return
	 */
	@RequestMapping("/delUserRoles")
	@ResponseBody
	public Object delUserRoles(UserRoles ur){
		Roles r=rser.selectRolesName(Integer.parseInt(ur.getRoleId()));
		int row=0;
		if(stuser.getCountAskerID(ur.getUserId())!=0){
			if(r.getRoleName().equals("咨询师")||r.getRoleName()=="咨询师"){
				row=0;
			}else{
				weightser.deleteAskers(ur.getUserId());
				row=urser.deleteUserRoles(ur);
			}
		}else{
			weightser.deleteAskers(ur.getUserId());
			row=urser.deleteUserRoles(ur);
		}
		if(row==0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 登录
	 * @param u
	 * @param session
	 * @return
	 */
	@RequestMapping("/Login")
	@ResponseBody
	public Object loginUsers(Users u,HttpSession session){
		Object obj=service.LoginUsers(u);
		session.setAttribute("user", service.selectUserPwdByUserName(u));
		if(session.getAttribute("user")!=null&&service.selectIfLock(u).equals("否")){
			service.updateLastLoginTime(u.getUserName());
		}
		return obj;
	}
	
	/**
	 * 获取登录的用户
	 * @param session
	 * @return
	 */
	@RequestMapping("/getLoginUser")
	@ResponseBody
	public Object loginUser(HttpSession session){
		return true;
	}
	
	/**
	 * 安全退出清空Session
	 * @param session
	 * @return
	 */
	@RequestMapping("/clearSession")
	@ResponseBody
	public Object clearSession(HttpSession session){
		session.setAttribute("user", null);
		return true;
	}
	
	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @param newPwd1
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Object updatePwd(String oldPwd,String newPwd,Boolean ifnewPwd,String newPwd1,HttpSession session){
		Users us=(Users)session.getAttribute("user");
		String old=us.getUserPASSWORD();
		Map map=new HashMap();
		Users u=new Users();
		u.setUserPASSWORD(newPwd);
		u.setUserId(us.getUserId());
		if(old.equals(oldPwd)||old==oldPwd){
			if(newPwd==null||newPwd==""){
				map.put("success", false);
				map.put("message", "新密码不能为空，请输入新密码！");
			}else{
				 if(newPwd==newPwd1||newPwd.equals(newPwd1)){
					 if(ifnewPwd){
						service.updatePwd(u);
						map.put("success", true);
						map.put("message", "修改成功，请重新登录！");
					 }else{
						map.put("success", false);
						map.put("message", "错误：新密码应由6-15位数字和字母组成！");
					 }
				}else{
					map.put("success", false);
					map.put("message", "两次密码输入不一致！");
				}
			}
		}else{
			map.put("success", false);
			map.put("message", "旧密码输入错误！！");
		}
		return map;
	}
}
