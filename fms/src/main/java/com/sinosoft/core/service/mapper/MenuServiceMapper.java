package com.sinosoft.core.service.mapper;

import java.util.List;
import java.util.Map;

import com.sinosoft.core.db.model.DefPrivilege;

public interface MenuServiceMapper {
	
	/**
	 * 查询菜单编码，供编码计算使用
	 * @return
	 */
	public List<DefPrivilege> queryPrivilegecode();
	
	/**
	 * 修改菜单加载顺序
	 * @return
	 */
	public int updateAllAfterNewNode(Map paramMap);
	
	
	/**
	 * 查询菜单的子菜单
	 * @param defPrivilege
	 * @return
	 */
	public List<DefPrivilege> queryMenuChildren(DefPrivilege defPrivilege);
	
	/**
	 * 菜单信息列表查询（总记录数）
	 * @param paramMap
	 * @return
	 */
	public Integer queryMenuListInfoCounts(Map paramMap);
	
	
	/**
	 * 菜单信息列表查询（分页查询）
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryMenuListInfoPages(Map paramMap);
}
