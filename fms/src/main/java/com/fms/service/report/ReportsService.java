package com.fms.service.report;

import java.util.List;
import java.util.Map;

import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface ReportsService {
	/**
	 * 查询业务部列表信息
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryBusinessManageList(DataGridModel dgm,Map paramMap,LoginInfo loginInfo);
	/**
	 * 查询募集期产品信息
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryRaisingProductsList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	
	@SuppressWarnings("rawtypes") 
	public ResultInfo queryProductBasicInfo(Map map,LoginInfo loginInfo);
	
	@SuppressWarnings("rawtypes") 
	public ResultInfo getProductCoreInfo(Map map,LoginInfo loginInfo);
	/**
	 * 查询条件中的月份
	 * @param loginInfo
	 * @return
	 */
	public List<Map<String, String>> monthQuery(LoginInfo loginInfo);
	
	/**
	 * 产品设置页面列表查询
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryProductList(DataGridModel dgm, Map paramMap);
	/**
	 * 查询条件中的年度
	 * @param loginInfo
	 * @return
	 */
	public List<Map<String, String>> yearQuery(LoginInfo loginInfo);
	/**
	 * 查询所有业务部信息
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Map businessManagementDetail(Map paramMap) throws Exception;
	/**
	 * 查询销售清单信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid querySaleProductsList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	/**
	 * 查询所有募集期产品信息
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	public Map raisingProductsDetail(Map paramMap) throws Exception;
	/**
	 * 查询所有销售清单信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Map saleProductsDetail(Map paramMap) throws Exception;
	/**
	 * 查询赎回详情信息
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryRedemptionDetailList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	/**
	 * 查询赎回详情信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Map redemptionDetail(Map paramMap) throws Exception;
	

	
}
