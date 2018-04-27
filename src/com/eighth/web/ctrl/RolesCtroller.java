package com.eighth.web.ctrl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.entity.RoleModules;
import com.eighth.entity.Roles;
import com.eighth.service.ModulesService;
import com.eighth.service.RoleModulesService;
import com.eighth.service.RolesService;
import com.eighth.service.UserRolesService;
import com.eighth.util.StringUtil;


@Controller
public class RolesCtroller {
	@Autowired
	private RolesService rolesservice;
	@Autowired
	private UserRolesService urser;
	@Autowired
	private RoleModulesService rmser;
	@Autowired
	private ModulesService mser;
	/**
	 * 显示所有角色
	 * @param s_rolename
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/showAllRole")
	@ResponseBody
	public Object getAllRole(String s_rolename,int page,int rows){
		Roles role=new Roles(StringUtil.isNull(s_rolename), page, rows);
		Map map=rolesservice.getDatagrid(role);
		return map;
	}
	
	/**
	 * 添加角色
	 * @param roles
	 * @return
	 */
	@RequestMapping(value="/addRole")
	@ResponseBody
	public  Object addRole(Roles roles){
		System.out.println(roles.getRoleName());
		int row=0;
		if(rolesservice.selectRolesByName(roles.getRoleName())==0){
			row=rolesservice.addRoles(roles);
		}
		return row;
		
	}
	
	/**
	 * 修改角色
	 * @param roles
	 * @return
	 */
	@RequestMapping(value="/updRole")
	@ResponseBody
	public  Object updRole(Roles roles){
		Roles r=rolesservice.selectRolesName(roles.getRoleId());
		int row=0;
		if(rolesservice.selectRolesByName(roles.getRoleName())==0){//查不到执行添加方法
			row=rolesservice.updRoles(roles);
		}else{//查到
			if(r.getRoleName().equals(roles.getRoleName())||r.getRoleName()==roles.getRoleName()){
				row=rolesservice.updRoles(roles);//如果被修改的name=修改后的name依然成功
			}//否则无
		}
		return row;
		
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/delRole")
	@ResponseBody
	public Object delRole(Integer roleId){
		Map map=new HashMap();
		List<RoleModules> list=rmser.selectModuleId(roleId.toString());
		if(list.size()==0){
			rolesservice.delRoles(roleId);
			map.put("success", true);
			map.put("message", "删除成功！");
		}else{
			map.put("success", false);
			map.put("message", "该角色拥有权限,无法删除！");
		}
		return map;
	}
	
	/**
	 * 给用户设置角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selectUserRoles")
	@ResponseBody
	public Object selectUserRoles(String id){
		List<Roles> list=urser.getRolesByUserid(id);
		return list;
	}
	/**
	 * 查询模块的ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/searchModulesId")
	@ResponseBody
	public Object searchModulesid(String id){
		List<RoleModules> list=rmser.selectModuleId(id);
		int ModulesId=0;
		for (RoleModules rm : list) {
			ModulesId=rm.getModuleId();
		}
		return ModulesId;
	}
	
	/**
	 * 设置权限
	 * @param roleid
	 * @param menuIds
	 * @return
	 */
	@RequestMapping("/AddRoleModules")
	@ResponseBody
	public Object AddRoleModules(Integer roleid,String menuIds){
		rmser.deleteRoleModules(roleid);
		if(menuIds==null||menuIds.equals("")){
			
		}else{
			String ids[]=menuIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				int pid=Integer.parseInt(ids[i]);
				mser.selectParentIdByMid(Integer.parseInt(ids[i]),roleid);
			}
		}
		return true;
	}
}
