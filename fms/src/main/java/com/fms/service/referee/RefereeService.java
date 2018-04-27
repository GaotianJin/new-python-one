package com.fms.service.referee;


import java.util.List;
import java.util.Map;

import com.fms.db.model.Referee;


public interface RefereeService {
	
	/**
	 * 分页模糊查询
	 * @param r
	 * @return
	 */
	Map getDatagrid(Referee r);
	
	/**
	 * 修改推荐人审核状态
	 * @param r
	 * @return
	 */
	Map updateStateByRefereeId(Referee r);
	
	/**
	 * 给下拉框
	 * @return
	 */
	List getRefereeStateforList();
}
