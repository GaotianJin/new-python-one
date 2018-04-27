package com.fms.service.referee.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.db.mapper.RefereeMapper;
import com.fms.db.model.Referee;
import com.fms.service.referee.RefereeService;


@Service
public class RefereeServiceImpl implements RefereeService{
	
	@Autowired
	private RefereeMapper rm;
	
	@Override
	public Map getDatagrid(Referee r) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=r.getPage();
		int pageSize=r.getRows();
		int begin=(page-1)*pageSize;
		r.setPage(begin);
		map.put("total", rm.countReferee(r));
		map.put("rows", rm.getRefereeAll(r));
		return map;
	}

	@Override
	public Map updateStateByRefereeId(Referee r) {
		int row=rm.updateStateByRefereeId(r);
		Map map=new HashMap<>();
		if(row==0) {
			map.put("success", false);
			map.put("message", "操作失败,请重试！");
		}else {
			map.put("success", true);
			map.put("message", "操作成功！");
		}
		return map;
	}

	@Override
	public List getRefereeStateforList() {
		return rm.getRefereeStateforList();
	}

	
}
