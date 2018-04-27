package com.eighth.dao;

import java.util.List;

import com.eighth.entity.RoleModules;

public interface IRoleModulesDao {
	/**
	 * 根据角色ID集合
	 * @param id
	 * @return String类型的id字符串，逗号隔开
	 * */
	public List<RoleModules> selectModuleId(String id);
	
	/**
	 * 添加方法
	 * @param roleid角色ID，外键
	 * @param menuid菜单ID，外键
	 * */
	public int insertRoleModules(Integer roleid,int menuid);
	
	/**
	 * 根据角色ID删除其所有权限
	 * @param roleid
	 * @return
	 */
	public int deleteRoleModules(int roleid);
}
