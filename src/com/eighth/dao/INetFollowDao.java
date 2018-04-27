package com.eighth.dao;

import java.util.List;

import com.eighth.entity.Netfollows;

public interface INetFollowDao {
	/**
	 * 查询是数据总条数
	 * @return
	 */
	int getCount(Netfollows nf);
	
	/**
	 * 查询数据并分页
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	List<Netfollows> selectlistPage(Netfollows nf);
	
	int insertNetFollow(Netfollows nf);
	
	
	int getCountByStudentId(Netfollows nf);
	List<Netfollows> selectListPageByStuId(Netfollows nf);
	
	int deleteNetFollow(String id);
	
}
