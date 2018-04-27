package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface ProductServiceMapper {
	/**
	 * 产品设置获取查询符合条件的行数
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer productQueryListCount(Map queryParam);

	/**
	 * 产品设置页面初始化查询脚本
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> productQueryListPage(Map queryParam);
	
	/**
	 * 产品发布获取查询符合条件的行数
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer productReleaseQueryListCount(Map queryParam);

	/**
	 * 产品发布页面初始化查询脚本
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> productReleaseQueryListPage(Map queryParam);

	/**
	 * 开放日获取查询符合条件的行数
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer openDayQueryListCount(Map queryParam);

	/**
	 * 开放日页面初始化查询脚本
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> openDayQueryListPage(Map queryParam);
	
	/**
	 * 获取 (固定收益类)费用比例信息可编辑表格信息
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getModifyPdWealthFeeRateList(Map queryParam);
	
	/**
	 * 获取 (保险费用比例)费用比例信息可编辑表格信息
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getmodifyPdRiskFeeRateList(Map queryParam);
	
	/**
	 * 获取 (录入信息)费用比例信息可编辑表格信息
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getmodifyPdFactorRateInfoList(Map queryParam);

	/**
	 * 根据产品ID获取产品机构信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllComInfo(Map paramMap);
	
	/**
	 * 产品额度分配 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getPdAmountDisInfo(Map paramMap);
	
	/**
	 *产品额度分配 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllPdAmountDisInfo(Map paramMap);
	
	/**
	 * 产品额度分配
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getAllPdAmountDisInfoCount(Map paramMap);
	
	/**
	 * 产品额度分配
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getProductAmountDisAndOrderInfo(Map paramMap);
	
	/**
	 * 获取产品报告的条数
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getAllProductReportInfoCount(Map paramMap);
	/**
	 * 获取产品报告的信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllProductReportInfo(Map paramMap);
	
	/**
	 * 获取浮动收益类产品的投资起始时间
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getFloatPdInvestDate(Map paramMap);

	/**
	 * 根据赎回信息表的剩余份额，判断该产品是否为再投产品
	 * @param valueMap
	 * @return
	 */
	public List<Map<String,Object>> getWealthPdTodayList(Map<String,Object> valueMap);
	
	/**
	 * 获取CLOB富文本编辑信息
	 */	
/*	public List getProductEditorContext(Map valueMap);*/
	
	/**
	 * 获取CLOB富文本编辑信息
	 */	
	public String getProductEditorContext(Map valueMap);
	/**
	 * 根据产品Id 获取产品信息
	 */	
	@SuppressWarnings("rawtypes")
	public Map<String, String> getProuctCoreInfo(Map paramMap);

	/**
	 * 获取浮动类产品信息
	 */	
	public Integer floatProductQueryListCount(Map paramMap);
	/**
	 * 获取浮动类产品信息
	 */	
	public List<Map> floatProductQueryListPage(Map paramMap);
	
	/**
	 * 获取浮动类产品净值信息
	 */	
	public Integer queryNetValueListCount(Map paramMap);
	/**
	 * 获取浮动类产品净值信息
	 */	
	public List<Map> queryNetValueListPage(Map paramMap);

	public List queryProductNetValue(Map paramMap);

	public List<Map> queryProductPublicDate(Map paramMap);

	public List<Map> getProductNetValues(Map paramMap);

	//批量维护开放日，得到募集起期
	public Map getMaxOpenDate(Map paramMap);

	/**
	 * 获取固收产品投后清单
	 */
	public Integer getAllFixedProductIncomeInfoCount(Map paramMap);

	public List<Map> getAllFixedProductIncomeInfo(Map paramMap);
	/**
	 * 查询固收产品投后清单详情
	 */
	public List<Map> queryfixedProductDetailList(Map paramMap);	

	/**
	 * 导出固收产品投后清单
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> exportFixedProductIncomeInfo(Map paramMap);

	/**
	 * 查询固收产品投后详单条数
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryDetailFixedProIncomeCount(Map paramMap);

	/**
	 * 查询固收产品投后详单信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryDetailFixedProIncomeList(Map paramMap);
	/**
	 * 查询净值待办任务条数
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer searchNetValueTaskCount(Map paramMap);
	/**
	 * 查询净值待办任务详情
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> searchNetValueTaskList(Map paramMap);
	/**
	 * 查询所有对应开放日产品信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getOpenDateProductInfo(Map paramMap);
	/**
	 * 查询是否已经生成对应开放日待办信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getOpenDateRecord(Map paramMap);
	/**
	 * 查询股权产品列表条数
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryStockProductListCount(Map paramMap);
	/**
	 * 查询股权产品列表
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryStockProductList(Map paramMap);
	/**
	 * 查询固定产品列表条数
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryFixedProductListCount(Map paramMap);
	/**
	 * 查询固定产品列表
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryFixedProductList(Map paramMap);	

	/**
	 * 浮动产品信息符合条件的行数
	 * @author cjj
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer floatProductListCount(Map queryParam);

	/**
	 * 浮动产品信息初始化查询脚本
	 * @author cjj
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> floatProductListPage(Map queryParam);
	/**
	 * 浮动产品信息符合条件的行数
	 * @author cjj
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer overseasProductListCount(Map queryParam);
	
	/**
	 * 浮动产品信息初始化查询脚本
	 * @author cjj
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> overseasProductListPage(Map queryParam);
	
	/**
	 * 产品查询(浮动海外)近一个月净值信息
	 * @author 	CJJ
	 * @param paramMap
	 * @return
	 */
	public List queryPdSearchNetValue(Map paramMap);
	
	/**
	 * 产品查询(浮动海外)初始化走势图获取产品公布日期信息
	 * @author 	CJJ
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryPdSearchPublicDate(Map paramMap);
	/**
	 * 产品查询菜单查询浮动、海外类产品净值列表符合条件的行数
	 * @author cjj
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryPdNetValueListCount(Map queryParam);
	
	/**
	 * 产品查询菜单查询浮动、海外类产品净值列表初始化查询脚本
	 * @author cjj
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryPdNetValueListPage(Map queryParam);
	
	/**
	 * 浮动产品查询初始化走势图获取产品所有净值信息
	 * 
	 * @param paramMap
	 * @return
	 */
//	public List queryInvestSearchNetValue(Map paramMap);
	
	/**
	 * 浮动产品查询初始化走势图获取产品公布日期信息
	 * 
	 * @param paramMap
	 * @return
	 */
//	public List<Map> queryInvestSearchPublicDate(Map paramMap);
	
	/**
	 * 浮动、海外产品查询，获取产品详细信息
	 * @param paramMap
	 * @return
	 */
	public Map searchProductDetail(Map paramMap);
	/**
	 * 股权产品查询，获取产品详细信息
	 * @param paramMap
	 * @return
	 */
	public Map searchProductStockDetail(Map paramMap);
	/**
	 * 固定产品查询，获取产品详细信息
	 * @param paramMap
	 * @return
	 */
	public Map searchProductFixedDetail(Map paramMap);
	
	/**
	 * 合同管理获取查询符合条件的行数
	 */
	@SuppressWarnings("rawtypes")
	public Integer contractQueryListCount(Map queryParam);

	/**
	 * 合同管理页面初始化查询脚本
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> contractQueryListPage(Map queryParam);
	
	/**
	 * 产品合同详细信息行数
	 */
	@SuppressWarnings("rawtypes")
	public Integer contractDetailQueryListCount(Map queryParam);

	/**
	 * 产品合同详细信息初始化查询脚本
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> contractDetailQueryListPage(Map queryParam);
	
	/**
	 * 导出产品合同号详情信息
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getContractInfoDetail(Map queryParam);

	public Integer getAllStockProductIncomeInfoCount(Map paramMap);
	/**
	 * 查询股权产品投后清单列表
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllStockProductIncomeInfo(Map paramMap);
	/**
	 * 查询股权产品投后详单列表
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryDetailStockProIncomeCount(Map paramMap);
	/**
	 * 查询股权产品投后详单列表
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryDetailStockProIncomeList(Map paramMap);
	/**
	 * 导出股权产品投后清单列表
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> exportStockProductIncomeInfo(Map paramMap);
	/**
	 * 导出股权产品投后详单列表
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryStockProductDetailList(Map paramMap);
	/**
	 * 根据userId获取角色信息
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getUserRoleInfo(Map paramMap);
	/**
	 * 查询投资记录条数
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCustInvestListCount(Map paramMap);
	/**
	 * 查询投资记录
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryCustInvestList(Map paramMap);
	/**
	 * 查询客户认购记录
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> exportDetialCustInvestInfo(Map paramMap);
	/**
	 * 查询客户认购记录
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryCustFloatInvestList(Map paramMap);
	
	}
