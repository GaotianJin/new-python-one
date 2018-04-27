package com.eighth.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IDynamicDao;
import com.eighth.entity.Dynamic;
import com.eighth.service.DynamicService;
@Service
public class DynamicServiceImpl implements DynamicService {
	
	@Autowired
	private IDynamicDao dao;
	@Override
	public Map getDatagrid(Dynamic d) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=d.getPage();
		int pageSize=d.getRows();
		int begin=(page-1)*pageSize;
		d.setPage(begin);
		map.put("total", dao.getCount(d));
		map.put("rows", dao.selectAll(d));
		return map;
	}

	@Override
	public int getNoCount(Dynamic d) {
		return dao.getNoCount(d);
	}

	@Override
	public int insertDynamic(Dynamic d) {
		return dao.insertDynamic(d);
	}

	@Override
	public int updateDynamic(String id) {
		return dao.updateDynamic(id);
	}

}
