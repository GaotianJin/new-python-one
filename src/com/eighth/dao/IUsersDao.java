package com.eighth.dao;

import java.util.List;

import com.eighth.entity.Users;

public interface IUsersDao {

	/**
	 * 查询是数据总条数
	 * @return
	 */
	int getCount(Users u);
	
	/**
	 * 查询数据并分页
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	List<Users> selectlistPage(Users u);
	/**
	 * 新增用户
	 * 
	 * @param Users
	 * */
	public int insertUsers(Users u);

	/**
	 * 修改用户
	 * 
	 * @param Users
	 * */
	public int updateUsers(Users u);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * */
	public int deleteUsers(String id);

	/**
	 * 重置密码
	 * 
	 * @param id
	 * */
	public int updateUsersPwdById(String id);

	/**
	 * 锁定用户
	 * 
	 * @param id
	 * */
	public int updateUsersLockTrueById(String id);

	/**
	 * 解锁用户
	 * */
	public int updateUsersLockFalseById(String id);
	
	/**
	 * 查询用户名是否存在
	 * @param com.eighth.entity.Users
	 * @return com.eighth.entity.Users
	 * */
	public Users selectUserNameByuserName(Users u);
	
	/**
	 * 查询密码是否正确
	 * @param com.eighth.entity.Users
	 * @return com.eighth.entity.Users
	 * */
	public Users selectUserPwdByUserName(Users u);
	
	/**
	 * 查询用户是否被锁定
	 * @param com.eighth.entity.Users
	 * @return String 是or否
	 * */
	public String selectIfLock(Users u);
	
	/**
	 * 记录密码错误次数
	 * */
	public int updateLocknum(Users u);
	
	/**
	 * 清楚密码错误次数
	 * */
	public int updateClearLocknum(String id);
	
	/**
	 * 根据用户名查询ID
	 * */
	public String selectUserIdByUserName(Users u);
	
	/**
	 * 修改密码
	 * */
	public int updatePwd(Users u);
	
	/**
	 * 根据Id查名称
	 * @param id
	 * @return
	 */
	public String selectNameById(String id);
	
	/**
	 * 最后登录时间
	 * @param id
	 * @return
	 */
	public int updateLastLoginTime(String name);
}
