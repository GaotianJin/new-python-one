package com.eighth.service;

import java.util.List;

import com.eighth.entity.Roles;
import com.eighth.entity.UserRoles;

public interface UserRolesService {
	
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
	 * 根据用户ID查询关联表对象，
	 * 然后根据角色ID查询角色对象
	 * @param userid
	 * @return
	 */
	public List<Roles> getRolesByUserid(String userid);
	
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
