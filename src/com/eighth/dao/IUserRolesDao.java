package com.eighth.dao;


import java.util.List;

import com.eighth.entity.UserRoles;

public interface IUserRolesDao {
	/**
	 * 根据用户Id查询对象
	 * @param id
	 * @return
	 */
	public List<UserRoles> selectRoleIdByUserId(String id);
	
	/**
	 * 添加方法
	 * @param ur
	 * @return
	 */
	public int insertUserRoles(UserRoles ur);
	
	/**
	 * 修改方法
	 * @param ur
	 * @return
	 */
	public int updateUserRoles(UserRoles ur);
	
	/**
	 * 根据用户ID和角色ID查询存不存在
	 * @param uid
	 * @param rid
	 * @return
	 */
	public int selectRole(String uid,String rid);
	
	/**
	 * 删除方法
	 * @param ur
	 * @return
	 */
	public int deleteUserRoles(UserRoles ur);
	
}
