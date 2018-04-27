package com.fms.service.order.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.db.mapper.OrdersMapper;
import com.fms.db.model.Orders;
import com.fms.service.order.OrdersService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.LoginInfo;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersMapper om;
	
	@Override
	public Map<String, Object> getDatagrid(Orders o) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=o.getPage();
		int pageSize=o.getRows();
		int begin=(page-1)*pageSize;
		o.setPage(begin);
		map.put("total", om.getCountOrders(o));
		map.put("rows",om.getOrdersAll(o));
		return map;
	}

	@Override
	public int insertOrders(Orders o) {
		return om.insert(o);
	}

	@Override
	public List<Orders> getStateGroupByOrdersState() {
		return om.getStateGroupByOrdersState();
	}

	@Override
	public List<Orders> getStateGroupByContactStatus() {
		return om.getStateGroupByContactStatus();
	}

}
