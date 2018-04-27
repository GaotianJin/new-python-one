package com.eighth.service;

import java.util.List;

import com.eighth.entity.RoleStus;

public interface RoleStusService {
	/**
	 * 获取Json
	 * @return
	 */
	public List<RoleStus> getAll();
	
	/**
	 * 查询该咨询师管理多少学生
	 * @param id
	 * @return
	 */
	int getCountByAkserId(String id);
	
	Object getName();
}
