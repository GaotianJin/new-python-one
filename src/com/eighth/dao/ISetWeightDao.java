package com.eighth.dao;

import java.util.List;

import com.eighth.entity.Askers;

public interface ISetWeightDao {
	/**
	 * 查询所有咨询师
	 * @return
	 */
	List<Askers> getAllAsker();
	/**
	 *修改权重 
	 */
	int updateAskers(Askers asker);
	
	/**
	 * 添加咨询师
	 * @param a
	 * @return
	 */
	int insertAskers(Askers a);
	
	/**
	 * 删除咨询师
	 * @param id
	 * @return
	 */
	int deleteAskers(String id);
	
	/**
	 * 查询咨询师总数
	 * @return
	 */
	int getCountAsker();
}
