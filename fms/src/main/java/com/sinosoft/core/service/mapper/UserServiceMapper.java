package com.sinosoft.core.service.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinosoft.core.db.model.DefRole;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.domain.model.user.User;

public interface UserServiceMapper {
	
	/**
	 * 根据用户ID查询
	 * @param defUser
	 * @return
	 */
	public User queryUserByUserID(DefUser defUser);
	
	
	/**
	 * 根据用户登陆账号查询
	 * @param defUser
	 * @return
	 */
	public ArrayList<DefUser> queryUserByUserCode(DefUser defUser);
	
	/**
	 * 根据用户名查询
	 * @param defUser
	 * @return
	 */
	public ArrayList<DefUser> queryUserByUserName(DefUser defUser);
	
	/**
	 * 根据用户ID查询权限
	 * @param defUser
	 * @return
	 */
	public String queryUserRolePrivilegeByUserId(DefUser defUser);
	
	
	/**
	 * 根据用户ID查询角色信息
	 * @param defUser
	 * @return
	 */
	public ArrayList<DefRole> queryRolesByUserId(DefUser defUser);
	
	/**
	 * 用户信息列表查询（总记录数）
	 * @param paramMap
	 * @return
	 */
	public Integer queryUserListInfoCounts(Map paramMap);
	
	
	/**
	 * 用户信息列表查询（分页查询）
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryUserListInfoPages(Map paramMap);
}
