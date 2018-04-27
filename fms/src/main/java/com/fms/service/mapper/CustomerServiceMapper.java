package com.fms.service.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CustomerServiceMapper {

	@SuppressWarnings("rawtypes")
	public List<Map> searchCustomerList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public Integer searchCustomerCount(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map> getCustAccList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map> getCustFamilyList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map> getCustAddressList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map> getCustHouseList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map> getCustCarList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryCustomerList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public Integer queryCustomerListCount(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryCustContactInfoList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public Integer queryCustTradeCount(Map paramMap);

	@SuppressWarnings("rawtypes")
	public Integer getCustByAgentInfo(Map paramMap);

	/**
	 * 根据客户ID查询客户信息主表以外的信息
	 * 
	 * @param custBaseInfoId
	 * @return
	 */
	public List<Map<String, Object>> getAccountInfoList(Map<String, Object> custBaseInfoIdMap);

	/**
	 * 根据客户ID获取该客户所有的交易信息
	 * 
	 * @param customerIdMap
	 * @return
	 */
	public List<Map<String, Object>> getTradeInfoList(Map<String, Object> customerIdMap);

	/**
	 * 获取财富产品与当前日期最接近的净值
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getNetValue(Map productIdMap);

	/**
	 * 根据客户ID查询客户住址信息
	 * 
	 * @param customerIdMap
	 * @return
	 */
	public List<Map<String, Object>> getCustAddressInfoList(Map<String, Object> customerIdMap);

	/**
	 * 根据客户Id查询财富产品信息
	 * 
	 * @param xmleWealthContentMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getWealthPdInfoList(Map xmleWealthContentMap);
	
	
	@SuppressWarnings("rawtypes")
	public List<Map> getTradeRedemptionList(Map paramMap);
	////////////////////////////////////////////////////微信端接口开发//////////////////////////////////
    //微信登录查询
	@SuppressWarnings("rawtypes")
	public Map wechatLogin(Map requestParamMap);
	//根据客户号和产品号查询特定产品信息
	@SuppressWarnings("rawtypes")
	public Map getStockDetail(Map requestParamMap);
    //根据客户号查询客户固定资产
	@SuppressWarnings("rawtypes")
	public Map<String, String> getFixedAsset(Map requestParamMap);
    //根据客户号查询客户的今日资产
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getTodayAsset(Map requestParamMap);
	//根据客户号查询客户所持有的所有固定产品
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getFixedProduct(Map requestParamMap);
	//根据客户号查询客户浮动资产CNY人民币
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getFloatAssetCNY(Map requestParamMap);
	//根据客户号查询客户浮动资产 USD美元
	@SuppressWarnings("rawtypes")
	public Map<String, String> getFloatAssetUSD(Map requestParamMap);
	//根据客户号查询客户的本周资产CNY
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getLastAssetCNY(Map requestParamMap);
	//根据客户号查询客户的本周资产USD
	@SuppressWarnings("rawtypes")
	public Map<String, String> getLastAssetUSD(Map requestParamMap);
	//根据客户号查询客户所持有的所有浮动产品
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getFloatProduct03(Map requestParamMap);
	public List<Map<String, String>> getFloatProduct01(Map<String, String> requestParamMap);
	/**
	 * 根据客户号和产品ID查询浮收产品详情
	 * @author HSH
	 * @param requestParamMap
	 * @return
	 */
  	@SuppressWarnings("rawtypes")
	public Map getFloatDetail(Map requestParamMap);
  	
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getRedemptionRecord(Map requestParamMap);
	
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getNetValues(Map requestParamMap);
  	
  	/**
	 * 根据客户号和产品ID查询固收产品详情
	 * @author HSH
	 * @param requestParamMap
	 * @return
	 */
  	@SuppressWarnings("rawtypes")
	public Map getFixedDetail1(Map requestParamMap);
  	
  	/**
	 * 根据客户号和产品号查询固收产品分配记录
	 * @author HSH
	 * @param requestParamMap
	 * @return
	 */
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getDistributeRecord(Map requestParamMap);
  	
  	
  	/**
	 * 根据客户号查询定期产品历史
	 * @author  CJJ
	 * @param requestParamMap
	 * @return
	 */
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getFixedHistory(Map requestParamMap);

  	/**
	 * 根据客户号查询股权产品历史
	 * @author CJJ
	 * @param requestParamMap
	 * @return
	 */
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getStockHistory(Map requestParamMap);
  	 	
  	//获取客户风险等级类型和风险等级分数
  	@SuppressWarnings("rawtypes")
	public Map getCustomerRiskLevelAndRiskLevelScore(Map requestParamMap);
	//投资分析客户资产配置
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getCustomerAssetAllocation01(Map requestParamMap);
    //投资分析客户资产配置
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getCustomerAssetAllocation02(Map requestParamMap);
    //投资分析客户资产配置
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getCustomerAssetAllocation03(Map requestParamMap);
    //投资分析客户资产配置
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getCustomerAssetAllocation04CNY(Map requestParamMap);
    //投资分析客户资产配置
  	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getCustomerAssetAllocation04USD(Map requestParamMap);
  	//投资分析固收类产品回款预期接口
	public List<Map<String, String>> getSolidProductReturnedMoney(Map<String, String> requestParamMap);
	//投资分析历史收益排名接口
	public List<Map<String, String>> getHistoryIncomeRanking(Map<String, String> requestParamMap);
	public List<Map<String, String>> findAllProduct(Map<String, Integer> requestParamMap);
	@SuppressWarnings("rawtypes")
	//根据客户号查询所有已购产品
	public List<Map<String, String>> findAllBoughtProduct(Map requestParamMap);

	   /////////////////////////////客户归属功能Mapper层接口方法///////////////////////////////////////////
		// 根据客户基本信息查询客户历史归属信息总条数
		public Integer queryCustHistoryBelongInfoNums(Map paramMap);
		//根据客户基本信息查询客户历史归属信息
		public List<Map<String, String>> queryCustHistoryBelongInfo(Map paramMap);
	  	@SuppressWarnings("rawtypes")
	  	public Integer findCustomerBelongCount(Map paramMap);
	  	//分页查询
	  	@SuppressWarnings("rawtypes")
	  	public List<Map<String, String>> findCustomerBelongList(Map paramMap);
	  	//导出
	  	public List<Map> exportCustBelongExcel(Map paramMap);
	  	//客户基本信息查询
	  	@SuppressWarnings("rawtypes")
	  	public Map getCustInfoByCustBaseInfoId(Map paramMap);
	  	// 客户历史归属信息查询
	  	@SuppressWarnings("rawtypes")
	  	public List<Map<String,String>> getCustBelongInfoByCustBaseInfoId(Map paramMap);
	  	//导出客户基本信息
		public List<Map<String, String>> getCustBaseInfoDetail(Map paramMap);
		//根据产品ID获取产品合同文件地址
		public List<Map<String, String>> getProductContract(Map paramMap);
		//根据产品ID获取产品推介材料文件地址
		public List<Map<String, String>> getProductMaterial(Map paramMap);
		//根据产品ID获取产品报告文件地址
		public List<Map<String, String>> getPproductReport(Map paramMap);
		//定期产品列表
		public List<Map<String, String>> findAllFixedProduct();
		//定期产品详情
		public Map getFixedProductDetail(Map paramMap);
		//股权产品列表
		public List<Map<String, String>> findAllStockProduct();
		//股权产品详情
		public Map getStockProductDetail(Map paramMap);
		
		//判断是否是老用户
		public Integer isOldCustomerCount1(Map paramMap);
		public Integer isOldCustomerCount2(Map paramMap);
	    ///////////////////////////////////////二期//////////////////////////////////////////////
		// 理财经理登录查询
	  	@SuppressWarnings("rawtypes")
		public Map wechatLoginForAgent(Map<String, String> requestParamMap);
	  	// 查询理财经理名下客户
		public List<Map<String, String>> findAllCustomer(Map<String, String> requestParamMap);
		// 获取客户基本信息
		public List<Map<String, String>> queryCustBaseInfo(Map<String, String> requestParamMap);
		// 查询个人账户信息
		public List<Map<String, String>> queryPersonalInfo(Map<String, String> requestParamMap);
		// 查询股权产品投资金额
		public Map<String, Object> getStockAsset(Map<String, String> requestParamMap);
		// 查询股权产品投资预估金额
		public Map<String, String> getStockForecastAsset(Map<String, String> requestParamMap);
		// 查询股权产品列表
		public List<Map<String, String>> getStockProduct(Map<String, String> requestParamMap);
		// 查询浮动产品投资金额
		public Map<String, Object> getFloatAsset(Map<String, String> requestParamMap);
		// 查询浮动产品预估资产
		public Map<String, String> getFloatForecastAsset(Map<String, String> requestParamMap);
		// 查询海外产品投资金额CNY
		public List<Map<String, String>> getOverseasAssetCNY(Map<String, String> requestParamMap);
		// 查询海外产品投资金额USD
		public Map<String, String> getOverseasAssetUSD(Map<String, String> requestParamMap);
		// 查询海外产品预估资产CNY
		public List<Map<String, String>> getOverseasForecastCNY(Map<String, String> requestParamMap);
		// 查询海外产品预估资产USD
		public Map<String, String> getOverseasForecastUSD(Map<String, String> requestParamMap);
		// 查询海外产品列表
		public List<Map<String, String>> getOverseasProduct(Map<String, String> requestParamMap);
		// 查询投资总额
		public Map<String, String> getInvestAmount(Map<String, String> requestParamMap);
		// 查询理财经理电话 和客服热线
		public Map<String, String> getAgentMobile(Map<String, String> requestParamMap);
		// 查询投资总额CNY
		public List<Map<String, String>> getInvestTotalCNY(Map<String, String> requestParamMap);
		// 查询投资总额USD
		public Map<String, String> getInvestTotalUSD(Map<String, String> requestParamMap);
		
	  	//查询浮动商品列表
		
		public List<Map<String, String>> findFloatProductList();



		//根据产品id查询浮动商品详情
		@SuppressWarnings("rawtypes")
		public Map findFloatProductDetails(Map paramMap);
		
	  	//查询海外商品列表
		public List<Map<String, String>> findOverseasProductList();

		//根据产品id查询海外商品详情
		@SuppressWarnings("rawtypes")
		public Map findOverseasProductDetails(Map paramMap);
		
		

	    ////////////////////////////////////////////////////////////////////////////////////////////////////
		/**
		 * 根据客户号查询浮动历史记录
		 * @author CJJ
		 * @param requestParamMap
		 * @return
		 */
	  	@SuppressWarnings("rawtypes")
		public List<Map<String, String>> getFloatProductHistory(Map requestParamMap);
	  	
	  	/**
		 * 根据客户号查询环球历史记录
		 * @author CJJ
		 * 
		 */
	  	@SuppressWarnings("rawtypes")
		public List<Map<String, String>> getOverseasHistory(Map requestParamMap);
	  	
	  	//根据固定热门产品
		public List<Map<String, String>> getFixedHotProductList(Map<String, String> requestParamMap);
		//根据股权热门产品
		public List<Map<String, String>> getStockHotProductList(Map<String, String> requestParamMap);
		//根据浮动热门产品
		public List<Map<String, String>> getFloatHotProductList(Map<String, String> requestParamMap);
		//根据海外热门产品
		public List<Map<String, String>> getOverseasHotProductList(Map<String, String> requestParamMap);

		public Map<String, Object> getStockHistoryAmount(Map<String, String> requestParamMap);

		public Map<String, Object> getFixedHistoryAmount(Map<String, String> requestParamMap);

		public Map<String, Object> getFloatHistoryAmount(Map<String, String> requestParamMap);

		public Map<String, Object> getOverseasHistoryAmountCNY(Map<String, String> requestParamMap);

		public Map<String, String> getOverseasHistoryAmountUSD(Map<String, String> requestParamMap);

		public Map<String, String> getWhaleCoin(Map<String, String> requestParamMap);

		public List<Map<String, String>> getProductOrderRecord(Map<String, String> requestParamMap);

		public Map getAgentInfoByMobile(Map<String, String> requestParamMap);

		public Map getCustInfoByMobile(Map<String, String> requestParamMap);
		
		public List<Map<String, String>> getFloatNetValues(Map<String, Object> requestParamMap);

		public Map<String, String> getAgentDetailInfo(Map<String, String> requestParamMap);

		public List<Map<String, String>> getCertificationList(Map<String, String> requestParamMap);

		public List<Map<String, String>> getWorkList(Map<String, String> requestParamMap);

		public List<Map<String, String>> getAllNetValue(Map<String, String> requestParamMap);

		public Map queryCustRemainAmount(Map paramMap);

		public Integer queryCustUpgradeAuditListCount(Map paramMap);

		public List<Map<String, String>> queryCustUpgradeAuditList(Map paramMap);

		public HashMap queryCustConclusion(HashMap paramMap);

		public List<Map> getProCustList(HashMap paramMap);

		public Integer queryCustomerInvestListCount(Map paramMap);

		public List<Map<String, String>> queryCustomerInvestList(Map paramMap);

		public List<Map<String, String>> queryAllFloatProducts(Map<String, String> requestParamMap);

		public List getStockProductRecord(Map<String, String> requestParamMap);

		public List<Map> getStockProductSumTotal(Map<String, String> requestParamMap);
	  	
	  		
}