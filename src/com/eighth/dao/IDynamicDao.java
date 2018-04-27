package com.eighth.dao;

import java.util.List;

import com.eighth.entity.Dynamic;
import com.eighth.entity.Students;

public interface IDynamicDao {
	/**
	 * 查询是数据总条数
	 * @return
	 */
	int getCount(Dynamic d);
	
	/**
	 * 查询所有动态消息
	 * @param d
	 * @return
	 */
	List<Dynamic> selectAll(Dynamic d);
	
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
