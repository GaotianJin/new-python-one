package com.eighth.service;

import java.util.List;
import java.util.Map;

import com.eighth.entity.Netfollows;
import com.eighth.entity.Students;

public interface NetFollowService {
	/**
	 * 分页
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map getDatagrid(Netfollows nf);
	
	int insertNetFollow(Netfollows nf);
	
	Map getDatagridByStuId(Netfollows nf);
	
	int deleteNetFollow(String id);
}
