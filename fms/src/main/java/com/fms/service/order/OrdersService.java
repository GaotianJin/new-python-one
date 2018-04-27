package com.fms.service.order;

import java.util.List;
import java.util.Map;

import com.fms.db.model.Orders;
import com.sinosoft.util.LoginInfo;

public interface OrdersService {
	
	/**
	 * 分页查询
	 * @param o
	 * @return
	 */
	Map<String,Object> getDatagrid(Orders o);
	
	/**
	 * 添加
	 * @param o
	 * @return
	 */
	int insertOrders(Orders o);
	
	/**
     * 查询订单状态给下拉框
     * @return
     */
    List<Orders> getStateGroupByOrdersState();
    
    /**
     * 查询合同状态给下拉框
     * @return
     */
    List<Orders> getStateGroupByContactStatus();
}
