package com.fms.service.mapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TradeServiceMapper {

	public List<Map> tradeInputQueryTradeListPage(Map queryParam);
	
	public Integer tradeInputQueryTradeListCount(Map queryParam);
	
	public List<Map<String, String>> queryCustomerCombo(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeRiskProId(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeRiskProtObj(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeRiskProInfo(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeWealthProInfo(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeRoleInfo(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeComId(HashMap paramMap);
	
	/*public List<Map<String, String>> queryTradeStoreId(HashMap paramMap);*/
	
	public List<Map<String, String>> queryTradeAgentId(HashMap paramMap);
	
	public List<Map<String, String>> queryRiskProInfoObjList(HashMap paramMap);
	
	public List<Map<String, String>> queryMonryProInfoObjList(HashMap paramMap);
	
	public List<Map<String, String>> queryCustomInfoInputGrid(HashMap paramMap);
	
	public List<Map<String, String>> queryCustomInfoInputGridById(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeProductFactor(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeBankInfo(Map paramMap);
	
	public List<Map<String, String>> checkUpFile(HashMap paramMap);

	public List<Map<String, String>> searchTradeBankInfo(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeAddressInfo(Map paramMap);
	
	public List<Map<String, String>> searchTradeAddressInfo(HashMap paramMap);
	
	public List<Map<String, String>> getTradeAddressInfo(HashMap paramMap);
	
	public Integer verifyTradeCustCount(Map paramMap);
	
	public List<Map<String, String>> queryDefCode(HashMap paramMap);
	
	public BigDecimal queryWealthProAssets(HashMap paramMap);
	
	public BigDecimal queryRiskProAssets(Map paramMap);
	
	public int updateTradePdCustRoleRela(HashMap paramMap);
	
	public HashMap queryTradeConclusion(HashMap paramMap);
	///////////////////////////////////
	public HashMap queryTradeConclusion2(HashMap paramMap);
/////////////////////////////////
	public List<Map> getAllCustRoleInfoListByTradeInfoId(Map paramMap);
	
	public Long tradePrintInfo(HashMap paramMap);
	
	public Map tradeCustInfo(HashMap paramMap);
	
	public Map tradeGDInfo(HashMap paramMap);
	
	//U0619-8  获取浮动受益权类型，从交易信息里面获取，如果没有就默认为未分类
	public String getFDBenefitType(HashMap paramMap);
	
	
	//U0619-8  获取固定受益权类型，根据认购额、产品期限从预期收益表里匹配读取，如果没有查询到，默认为未分类
	public String getGDBenefitType(HashMap paramMap);
	
	//U0619-7
	public String getColsePeriodCols(HashMap paramMap);
	
	//U0619-6
	public String getColsePeriodByTrade(HashMap paramMap);
	
	//U0619-5
	public String getColsePeriodByProduct(HashMap paramMap);
	
	//U0619-4
	public BigDecimal getFeeRate(HashMap paramMap);
	
	//U0928-4  附加收益率
	public BigDecimal getAddFeeRate(HashMap paramMap);
		
	//U0619-3
	public String getClosePeriodByTrade(HashMap paramMap);
	
	
	public String getClosePeriodByProduct(HashMap paramMap);
	
	public String getBenefitTypeByProduct(HashMap paramMap);
	
	//U0619-2
	public String getBenefitTypeByTrade(HashMap paramMap);
	
	//U0619-1
	public String getClosePeriodUnitByProduct(HashMap paramMap);
	
	public Map tradeFGDInfo(HashMap paramMap);
	
	public List<Map<String, String>> queryOpenDateByProductId(Map paramMap);
	
	public Integer tradeTotal(Map paramMap);
	
	public Integer getTradePrintTotal(Map paramMap);
	
	public List<Map<String, String>> getPrintServiceList(Map paramMap);
	
	public Integer querySaveTradeAddressCount(Map paramMap);
	
	public Integer querySaveTradeBankCount(Map paramMap);
	
	public Map printGetTradeAgentInfo(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getTradeAppntCustInfo(Map paramMap);
	/**
	 * 根据客户Id获取客户交易保险信息
	 * @param xmleWealthContentMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getTradeRiskInfo(Map xmleWealthContentMap);
	
	
	/**
	 * 提交复核校验该客户是否已经被其他理财经理录过交易
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getCheckTradeInputCount(Map paramMap);
	/**
	 * 获取客户认购书内容
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,Object>> getRequestMapInfoList(Map paramMap);
	/**
	 * 获取客户确认书内容
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getConfrimMapInfoList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public Map<String, String> getTradeCheckEmailData(HashMap paramMap);
	/**
	 * 获取认购书浮动类产品认购书内容01 
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> getFloatConfrimMapInfoList(Map paramMap);

	/**
	 * @author 燕
	 *查询转让交易产品录入要素信息
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,String>> queryTransFerMoneyProInfoObjList(HashMap paramMap);
	/**
	 * 获取认购书浮动类产品认购书内容03
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> getFloatConfrimMapInfoList03(Map paramMap);
	
	/**
	 * 获取判断基金风险等级与客户风险等级
	 * @param paramMap
	 * @return
	 */
	public Map getCustAndProductRiskInfo(HashMap paramMap);
	/**
	 * 判断是否上传客户身份证复印件
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, String>> checkCustIDFile(HashMap paramMap);
	/**
	 * 获得交易生成的基本信息
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, String>> getTradeBaseInfo(Map paramMap);

}
