package com.eighth.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IUsersDao;
import com.eighth.entity.Users;
import com.eighth.service.UsersService;
@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private IUsersDao dao;
	
	/**
	 * 新增用户
	 * */
	public int insertUsers(Users u) {
		return dao.insertUsers(u);
	}
	
	/**
	 * 修改用户
	 * */
	public int updateUsers(Users u) {
		return dao.updateUsers(u);
	}
	
	/**
	 * 删除用户
	 * */
	
	public int deleteUsers(String id) {
		return dao.deleteUsers(id);
	}

	/**
	 * 修改用户
	 * */
	public int updateUsersPwdById(String id) {
		return dao.updateUsersPwdById(id);
	}
	
	/**
	 * 锁定用户
	 * */
	public int updateUsersLockTrueById(String id) {
		return dao.updateUsersLockTrueById(id);
	}

	/**
	 * 解锁用户
	 * */
	
	public int updateUsersLockFalseById(String id) {
		return dao.updateUsersLockFalseById(id);
	}
	
	/**
	 * 用户登录验证
	 * */
	public Object LoginUsers(Users u){
		Map map=new HashMap();
		if(dao.selectUserNameByuserName(u)==null){
			map.put("success", false);
			map.put("message", "用户名不存在！");
		}else{
			if(dao.selectIfLock(u).equals("是")){
				map.put("success", false);
				map.put("message", "此用户已被锁定！(请联系管理员解锁)");
			}else{
				if(dao.selectUserPwdByUserName(u)==null){
				this.UserPwdLockNum(u);
				map.put("success", false);
				map.put("message", "密码错误！错误5次将被锁定。剩余"+(5-u.getUserPsdWrongTime())+"次");
				if(u.getUserPsdWrongTime()==5){
					System.out.println(dao.selectUserIdByUserName(u));
					dao.updateUsersLockTrueById(dao.selectUserIdByUserName(u));
				}
			}else{
					this.updateClearLocknum(dao.selectUserIdByUserName(u));
					map.put("success", true);
					map.put("message", "登陆成功！");
				}
			}
		}
		return map;
	}
	
	public Object UserPwdLockNum(Users u){
		Users user=dao.selectUserNameByuserName(u);//查询出当前登录的对象
		int a=user.getUserPsdWrongTime();//获取登录对象的密码错误次数
		a++;
		u.setUserPsdWrongTime(a);//重新赋给当前对象
		return dao.updateLocknum(u);//修改操作
	}

	@Override
	public int updateClearLocknum(String id) {
		return dao.updateClearLocknum(id);
	}

	@Override
	public Users selectUserNameByuserName(Users u) {
		return dao.selectUserNameByuserName(u);
	}

	@Override
	public String selectUserIdByUserName(Users u) {
		return dao.selectUserIdByUserName(u);
	}

	@Override
	public Map getDatagrid(Users u) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=u.getPage();
		int pageSize=u.getRows();
		int begin=(page-1)*pageSize;
		u.setPage(begin);
		map.put("total", dao.getCount(u));
		map.put("rows", dao.selectlistPage(u));
		return map;
	}

	public Users selectUserPwdByUserName(Users u) {
		return dao.selectUserPwdByUserName(u);
	}


	public int updatePwd(Users u) {
		return dao.updatePwd(u);
	}

	@Override
	public String selectNameById(String id) {
		return dao.selectNameById(id);
	}

	@Override
	public int updateLastLoginTime(String name) {
		return dao.updateLastLoginTime(name);
	}

	@Override
	public String selectIfLock(Users u) {
		return dao.selectIfLock(u);
	}
}
