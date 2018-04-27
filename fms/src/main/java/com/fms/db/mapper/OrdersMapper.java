package com.fms.db.mapper;

import com.fms.db.model.Orders;
import com.fms.db.model.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	int countByExample(OrdersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	int deleteByExample(OrdersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	int deleteByPrimaryKey(Integer orderId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	int insert(Orders record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	int insertSelective(Orders record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	List<Orders> selectByExample(OrdersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	Orders selectByPrimaryKey(Integer orderId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	int updateByPrimaryKeySelective(Orders record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order_info
	 * @mbggenerated  Wed Apr 18 19:43:13 CST 2018
	 */
	int updateByPrimaryKey(Orders record);

	/**
     * 查询数据总条数+模糊
     * @param o
     * @return
     */
    int getCountOrders(Orders o);
    
    /**
     * 模糊查询
     * @param o
     * @return
     */
    List<Orders> getOrdersAll(Orders o);
    
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