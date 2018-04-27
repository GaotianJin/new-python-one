package com.eighth.service;

import java.util.Map;

import com.eighth.entity.Roles;

public interface RolesService {
	/**
	 * 分页
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map getDatagrid(Roles role);
	/**
	 * 添加角色
	 * */
	int addRoles(Roles role);
	/**
	 * 修改角色
	 * */
	int updRoles(Roles role);
	/**
	 * 删除角色
	 */
	int delRoles(int roleId);
	
	Roles selectRolesName(Integer id);
	
	/**
	 * 查询数据库是否存在此角色名
	 * @param name
	 * @return
	 */
	int selectRolesByName(String name);
}
