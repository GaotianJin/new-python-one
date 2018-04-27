package com.eighth.service;

import java.util.Map;

import com.eighth.entity.Dynamic;

public interface DynamicService {
	/**
	 * 分页查询
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map getDatagrid(Dynamic d);
	
	/**
	 * 查询未读动态条数
	 * @param d
	 * @return
	 */
	int getNoCount(Dynamic d);
	
	/**
	 * 添加动态
	 * @param d
	 * @return
	 */
	int insertDynamic(Dynamic d);
	
	/**
	 * 修改状态
	 * @param id
	 * @return
	 */
	int updateDynamic(String id);
}
