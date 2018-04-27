package com.sinosoft.core.spring.cxf;

import javax.jws.WebService;

@WebService
public interface CXFWebserviceServer {

	
	/**
	 * 微信登录
	 * @param requestJson
	 * @return
	 */
	public String wechatLogin(String requestJson);
	
	/**
	 * 我的固定资产
	 * @param requestJson
	 * @return
	 */
	public String myFixedAsset(String requestJson);

	/**
	 * 我的浮动资产
	 * @param requestJson
	 * @return
	 */
	public String myFloatAsset(String requestJson);
	/**
	 * 股权产品详情
	 * @param requestJson
	 * @return
	 */
	public String stockProductDetail(String requestJson);

	
////////////////////HSH///////////////////////////////////////////
	/**
	 * 根据客户号和产品ID查询浮收产品详情
	 * @author HSH
	 * @param requestJson
	 * @return
	 */
	public String floatProductDetail(String requestJson);

	/**
	 * 根据客户号和产品ID查询固收产品详情
	 * @author HSH
	 * @param requestJson
	 * @return
	 */
	public String fixedProductDetail(String requestJson);
	
////////////////////HSH///////////////////////////////////////////	
	
	
	
	/**
	 * 根据客户号查询理财经理信息
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	public String findAgentInfoByCustNo(String requestJson);
	/**
	 * 投资分析客户资产配置
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	public String customerAssetAllocation(String requestJson);

	

	/**
	 * 投资分析历史收益排名接口
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	public String historyIncomeRanking(String requestJson);
	

	/**
	 * 投资分析固收类产品回款预期接口
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	public String solidProductReturnedMoney(String requestJson);
	
	/**
	 * 查询所有已发布产品
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	public String findAllProduct(String requestJson);
	
	/**
	 * 根据客户号查询所有已购产品
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String findAllBoughtProduct(String requestJson);
	
	/* ==========================二期新增接口============================== */
				/* ================LOUAO================ */
	/**
	 * 判断是否是老用户
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	public String isOldCustomer(String requestJson);
	
	/**
	 * 判断是否是老用户
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	public String isOldCustInfo(String requestJson);
	
	/**
	 * 定期产品列表
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	public String findAllFixedProduct(String requestJson);
	
	/**
	 * 定期产品详情
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	public String findFixedProductDetail(String requestJson);
	
	/**
	 * 股权产品列表
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	public String findAllStockProduct(String requestJson);
	
	/**
	 * 定期产品列表
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	public String findStockProductDetail(String requestJson);
	
				/* ================SHIYUNPENG============== */

	/**
	 * 查询浮动产品列表
	 * @author SYP
	 * @param requestInfo
	 * @return
	 */
	public String findFloatProductList(String requestJson);

	
	/**
	 * 根据产品id查询浮动产品详情
	 * @author SYP
	 * @param requestInfo
	 * @return
	 */
	public String findFloatProductDetails(String requestJson);
	/**
	 * 查询海外产品列表
	 * @author SYP
	 * @param requestInfo
	 * @return
	 */
	public String findOverseasProductList(String requestJson);
	
	/**
	 * 根据产品id查询海外产品详情
	 * @author SYP
	 * @param requestInfo
	 * @return
	 */
	public String findOverseasProductDetails(String requestJson);

				/* ==============ZYM================ */
	
	/**
	 * 查询客户列表
	 * 
	 * @param agentId
	 * @return
	 */
	public String findAllCustomer(String requestJson);
	
	/**
	 * 查询客户基本信息
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String queryCustBaseInfo(String requestJson);
	
	/**
	 * 查询个人账户信息
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String queryPersonalInfo(String requestJson);
	
	/**
	 * 我的股权资产
	 * @param requestJson
	 * @return
	 */
	public String myStockAsset(String requestJson);
	
	/**
	 * 我的海外资产
	 * @param requestJson
	 * @return
	 */
	public String myOverseasAsset(String requestJson);
	
	/**
	 * 我投资总额
	 * @param requestJson
	 * @return
	 */
	public String myInvestAmount(String requestJson);
	
	/**
	 * 查询热门产品列表
	 * @param requestJson
	 * @return
	 */
	public String hotProductList(String requestJson);
	
	/* ================CJJ================ */
	/**
	 * 根据客户号查询股权产品历史记录
	 * @param requestJson
	 * @return
	 */
	public String stockProductHistory(String requestJson);
	/**
	 * 根据客户号查询固收产品历史
	 * @author HSH
	 * @param requestJson
	 * @return
	 */
	public String fixedProductHistory(String requestJson);
	
	
	/**
	 * 根据客户号查询基金产品历史
	 * @author HSH
	 * @param requestJson
	 * @return
	 */
	public String floatProductHistory(String requestJson);
	/**
	 * 根据客户号查询环球产品历史
	 * @author HSH
	 * @param requestJson
	 * @return
	 */
	public String overseasProductHistory(String requestJson);
	/**
	 * 根据客户号和时间查鲸币
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String whaleCoin(String requestJson);
	/**
	 * 根据客户号查预约记录
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String productOrderRecord(String requestJson);
	
	/**
	 * 根据理财经理Id查rm详情
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String getAgentDetailInfo(String requestJson);
	
	/**
	 * 根据产品Id查所有净值
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String getAllNetValue(String requestJson);
	/**
	 * 根据手机号查询是否是员工
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String isEmployee(String requestJson);
	/**
	 * 查询所有浮动产品
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String queryAllFloatProducts(String requestJson);
}
