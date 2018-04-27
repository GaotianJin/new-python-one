package com.fms.service.order;

import java.util.Map;

import com.fms.db.model.Order;

public interface OrderService {
	
	/**
	 * 分页查询
	 * @param o
	 * @return
	 */
	Map<String,Object> getDatagrid(Order o);
}
