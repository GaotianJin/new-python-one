package com.eighth.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IUserChecksDao;
import com.eighth.entity.UserChecks;
import com.eighth.service.UserChecksService;
@Service
public class UserChecksServiceImpl implements UserChecksService {
	@Autowired
	private IUserChecksDao dao;

	@Override
	public Map getDatagrid(UserChecks u) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=u.getPage();
		int pageSize=u.getRows();
		int begin=(page-1)*pageSize;
		u.setPage(begin);
		map.put("total", dao.selectCount(u));
		map.put("rows", dao.selectAll(u));
		return map;
	}

	@Override
	public int insertUserChecks(UserChecks u) {
		return dao.insertUserChecks(u);
	}

	@Override
	public List<UserChecks> selectUserChecksByUserIDAndDate(UserChecks u) {
		return dao.selectUserChecksByUserIDAndDate(u);
	}

	@Override
	public int updateUserChecksState(UserChecks u) {
		return dao.updateUserChecksState(u);
	}
	


}
