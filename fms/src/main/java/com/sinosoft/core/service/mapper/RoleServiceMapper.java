package com.sinosoft.core.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinosoft.core.db.model.DefPrivilege;
import com.sinosoft.core.db.model.DefRole;

public interface RoleServiceMapper {
	
	/**
	 * 根据角色ID查询
	 * @param defRole
	 * @return
	 */
	public ArrayList<DefPrivilege> queryPrivilageByRoleID(DefRole defRole);
	
	/**
	 * 根据角色编码查询
	 * @param defRole
	 * @return
	 */
	public ArrayList<DefRole> queryRoleByRoleCode(DefRole defRole);
	
	/**
	 * 根据角色姓名查询
	 * @param defRole
	 * @return
	 */
	public ArrayList<DefRole> queryRoleByRoleName(DefRole defRole);
	
	/**
	 * 角色信息列表查询（总记录数）
	 * @param paramMap
	 * @return
	 */
	public Integer queryRoleListInfoCounts(Map paramMap);
	
	
	/**
	 * 角色信息列表查询（分页查询）
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryRoleListInfoPages(Map paramMap);
	
}
