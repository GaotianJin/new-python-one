package com.fms.webservice.wechat.service;

public interface WechatServerService {

	/**
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */
	public String login(String requestInfo,String ip);
	/**
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */
	public String myFixedAsset(String requestInfo);
	
	/**
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */
	public String myFloatAsset(String requestInfo);

	/**
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */
	public String stockProductDetail(String requestInfo);
	
	
	/**
	 * @author HSH
	 * @param requestInfo
	 * @return
	 */
	public String floatProductDetail(String requestInfo);
	
	/**
	 * @author HSH
	 * @param requestInfo
	 * @return
	 */
	public String fixedProductDetail(String requestInfo);
	
	/**
	 * @author CJJ
	 * @param requestInfo
	 * @return
	 */
	public String stockProductHistory(String requestInfo);
	
	/**
	 * @author CJJ
	 * @param requestInfo
	 * @return
	 */
	public String fixedProductHistory(String requestInfo);
	
	
	/**
	 * 投资分析客户资产配置接口
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	public String customerAssetAllocation(String requestInfo);
	

	/**
	 * 投资分析历史收益排名接口
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	public String historyIncomeRanking(String requestInfo);
	
	/**
	 * 投资分析固收类产品回款预期接口
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	public String solidProductReturnedMoney(String requestInfo);
	
	/**
	 * 根据客户号查询理财经理信息
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */
	
	public String findAgentInfoByCustNo(String requestInfo);
	
	//查询所有产品
	public String findAllProduct(String requestInfo);
	
	/**
	 * 查询客户所有已购产品
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */
	public String findAllBoughtProduct(String requestInfo);
	
	/* ================================== 二期  ============================ */

	/**
	 * 查询基金和环球产品历史记录
	 * @author CJJ
	 */
	public String floatProductHistory(String requestInfo);

					/* =========================== LOUAO =========================== */
	/**
	 * 股权产品详情
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	public String isOldCustomer(String requestJson);
	/**
	 * 查询定期产品
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
	 * 查询股权产品
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	public String findAllStockProduct(String requestJson);
	/**
	 * 股权产品详情
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	public String findStockProductDetail(String requestJson);

	
				/* =========================== SHIYUNPENG =========================== */
	
	/**
	 * 查询浮动商品列表
	 * @author shiyunpeng
	 * @param requestInfo
	 * @return
	 */
	public String findFloatProductList(String requestInfo);

		
	/**
	 * 查询浮动商品详情
	 * @author shiyunpeng
	 * @param requestInfo
	 * @return
	 */
	public String findFloatProductDetails(String requestInfo);
		
	/**
	 * 查询海外商品列表
	 * @author shiyunpeng
	 * @param requestInfo
	 * @return
	 */
	public String findOverseasProductList(String requestInfo);
		
	/**
	 * 查询海外商品详情
	 * @author shiyunpeng
	 * @param requestInfo
	 * @return
	 */
	public String findOverseasProductDetails(String requestInfo);
		
						/* =========================== ZYM =========================== */
	/**
	 * 查询理财经理名下客户
	 * @author ZYM
	 * @param requestInfo
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
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String myStockAsset(String requestJson);
	/**
	 * 我的海外资产
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String myOverseasAsset(String requestJson);
	/**
	 * 我的认购总额
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String myInvestAmount(String requestJson);
	
	/**
	 * 热门产品列表
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String hotProductList(String requestJson);
	
	/* ================CJJ================ */
	/**海外产品历史列表
	 * @author CJJ
	 * @param requestInfo
	 * @return
	 */
	public String overseasProductHistory(String requestInfo);
	
	/**根据客户号和时间查鲸币
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String whaleCoin(String requestJson);
	
	/**根据客户号查预约记录
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String productOrderRecord(String requestJson);
	/**判断是否老用户
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String isOldCustInfo(String requestJson);
	/**根据理财经理Id查rm详情
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String getAgentDetailInfo(String requestJson);
	/**根据产品Id查所有净值
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String getAllNetValue(String requestJson);
	
	/**根据手机号查询是否是员工
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String isEmployee(String requestJson);
	
	/**查询所有浮动产品
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String queryAllFloatProducts(String requestJson);

	
}
