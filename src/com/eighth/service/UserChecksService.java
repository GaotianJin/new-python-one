package com.eighth.service;


import java.util.List;
import java.util.Map;

import com.eighth.entity.UserChecks;



public interface UserChecksService {
	/**
	 * 分页
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map getDatagrid(UserChecks u);
	
	/**
	 * 签到
	 * @param u
	 * @return
	 */
	public int insertUserChecks(UserChecks u);
	
	/**
	 * 根据日期和用户ID查询数据库是否存在签到数据
	 * @return 集合，如果UserChecks.签退时间为空，则显示签退。否则显示签到
	 */
	public List<UserChecks> selectUserChecksByUserIDAndDate(UserChecks u);
	
	/**
	 * 签退
	 * @return
	 */
	public int updateUserChecksState(UserChecks u);
}
