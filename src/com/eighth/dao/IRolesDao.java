package com.eighth.dao;

import java.util.List;

import com.eighth.entity.Roles;
import com.eighth.entity.Users;

public interface IRolesDao {
	/**
	 * 查询所有角色
	 * */
	List<Roles> getAllRole(Roles role);
	
	/**
	 * 查询总条数
	 * @param u
	 * @return
	 */
	int getCountRoles(Roles role);
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
	
	/**
	 * 根据角色ID查询对象
	 * 主要是用来查询角色名称
	 * @param id
	 * @return
	 */
	Roles selectRolesName(Integer id);
	
	/**
	 * 查询数据库是否存在此角色名
	 * @param name
	 * @return
	 */
	int selectRolesByName(String name);
}
