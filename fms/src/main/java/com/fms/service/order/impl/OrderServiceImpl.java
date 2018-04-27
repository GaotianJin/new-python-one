package com.fms.service.order.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.db.mapper.OrderMapper;
import com.fms.db.model.Order;
import com.fms.service.order.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderMapper om;

	@Override
	public Map<String, Object> getDatagrid(Order o) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=o.getPage();
		int pageSize=o.getRows();
		int begin=(page-1)*pageSize;
		o.setPage(begin);
		map.put("total", om.getCountOrder(o));
		map.put("rows",om.getOrderAll(o));
		return map;
	}

}
