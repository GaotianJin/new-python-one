package com.fms.service.mapper;

import java.util.Map;
import java.math.BigDecimal;
import java.util.List;
public interface ProductOrderServiceMapper {
	/*
	 * 功能描述：
	 * 参数类型：Map
	 * 返回类型：Integer
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryComPdOrderAuditInfoCount(Map paramMap);
	
	/*
	 * 功能描述：
	 * 参数类型：Map
	 * 返回类型：List<Map>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryComPdOrderAuditInfo(Map paramMap);
	
	
	/**
	 * 查询分公司产品预约信息列表
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryComPdAmountOrderInfoList(Map paramMap);
	
	/**
	 * 查询分公司产品预约信息列表数量
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryComPdAmountOrderInfoListCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer getPdAmountOrderInfo(Map paramMap);
	//获取预约信息
	@SuppressWarnings("rawtypes")
	public List<Map> getAllPdAmountOrderInfo(Map paramMap);
	//获取预约信息行数
	@SuppressWarnings("rawtypes")
	public Integer getAllPdAmountOrderInfoCount(Map paramMap);
	
	//获取队列预约信息
	@SuppressWarnings("rawtypes")
	public List<Map> getRemainAmountOrderInfo(Map paramMap);
	//获取队列预约信息行数
	@SuppressWarnings("rawtypes")
	public Integer getRemainAmountOrderInfoCount(Map paramMap);
	/**
	 * 导出预约信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProductOrderDetailInfo(Map paramMap);
	
	/**
	 * 获取客户在一年内购买的物业宝系列产品信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getCustHadBuyWYBProductCount(Map paramMap);
	
	/**
	 * 预约查询时导出预约信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> exportPdOrderQueryInfo(Map paramMap);
	
	/**
	 * 产品预约查询获取预约总额
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public BigDecimal getPdOrderTotalAmount(Map paramMap);
	
	/**
	 * 产品预约审核获取预约总额
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public BigDecimal getPdOrderAuditOrderTotalAmount(Map paramMap);
	/**
	 * 获取产品预约剩余总额
	 * @param paramMap
	 * @return
	 */
	public BigDecimal getRemainTotalAmount(Map paramMap);
	/**
	 * 获取产品融资规模
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public BigDecimal getProductFinancingScale(Map paramMap);
	/**
	 * 获取产品预约排队详情
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryProductOrderQueueDetailList(Map paramMap);
	
	//获取热门产品管理条数
	@SuppressWarnings("rawtypes")
	public Integer queryHotPdOrderInfoListCount(Map paramMap);
	
	//获取热门产品信息列表
	@SuppressWarnings("rawtypes")
	public List<Map> queryHotPdOrderInfoList(Map paramMap);
	
	/**
	 * 获取客户信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getCustomerInfo(Map paramMap);
	/**
	 * 获取客户已购产品信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getProductsByCustId(Map queryMap);
}
