package com.eighth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.INetFollowDao;
import com.eighth.entity.Netfollows;
import com.eighth.service.NetFollowService;
@Service
public class NetFollowServiceImpl implements NetFollowService {
	@Autowired
	private INetFollowDao ndao;

	public int insertNetFollow(Netfollows nf) {
		return ndao.insertNetFollow(nf);
	}

	public Map getDatagrid(Netfollows nf) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=nf.getPage();
		int pageSize=nf.getRows();
		int begin=(page-1)*pageSize;
		nf.setPage(begin);
		map.put("total", ndao.getCount(nf));
		map.put("rows",ndao.selectlistPage(nf));
		return map;
	}

	public Map getDatagridByStuId(Netfollows nf) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=nf.getPage();
		int pageSize=nf.getRows();
		int begin=(page-1)*pageSize;
		nf.setPage(begin);
		map.put("total", ndao.getCountByStudentId(nf));
		map.put("rows", ndao.selectListPageByStuId(nf));
		return map;
	}

	@Override
	public int deleteNetFollow(String id) {
		return ndao.deleteNetFollow(id);
	}

}
