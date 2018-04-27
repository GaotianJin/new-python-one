package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface SmsServiceMapper {

	/**
	 * 获取成立的产品
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getFoundProductList(Map paramMap);
	
	/**
	 * 根据产品和成立日获取该产品所有交易信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllTradeInfoByProductIdAndFoundDate(Map paramMap);
	
	/**
	 * 获取所有未发送净值短信的产品净值信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProductNetValueList(Map paramMap);
	
	/**
	 * 根据产品获取该产品所有交易信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllTradeInfoByProductId(Map paramMap);
	
	/**
	 * 获所有固定收益类产品收益分配信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllProductDisInfo(Map paramMap);
	
	/**
	 * 获固定收益类产品收益分配详细信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProductDisDetailInfo(Map paramMap);
	
	/**
	 * 获取当日生日的客户信息List
	 * @author SLY
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllCustInfo(Map paramMap);
	
	/**
	 * 获取产品成立类别的短信信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProductFoundSmsBatchInfo(Map paramMap);
	
	/**
	 * 获取产品成立类别的短信信息数量
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getProductFoundSmsBatchInfoCount(Map paramMap);
	
	/**
	 * 获取净值公布类别的短信信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getNetValuePublicSmsBatchInfo(Map paramMap);
	
	/**
	 * 获取净值公布类别的短信信息数量
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getNetValuePublicSmsBatchInfoCount(Map paramMap);
	
	/**
	 * 获取收益分配类别的短信信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getIncomeDisSmsBatchInfo(Map paramMap);
	
	/**
	 * 获取收益分配类别的短信信息数量
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getIncomeDisSmsBatchInfoCount(Map paramMap);
	
	/**
	 * 获取产品成立短信详细信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProductFoundSmsDetailInfo(Map paramMap);
	
	/**
	 * 获取产品成立短信数量
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getProductFoundSmsDetailInfoCount(Map paramMap);
	
	/**
	 * 获取净值公布短信详细信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getSmsAuditDetailInfo(Map paramMap);
	
	
	/**
	 * 获取净值公布短信数量
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getSmsAuditDetailInfoCount(Map paramMap);
	
	/**
	 * 获取收益分配短信详细信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getIncomeDisSmsDetailInfo(Map paramMap);
	
	
	/**
	 * 获取收益分配短信数量
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getIncomeDisSmsDetailInfoCount(Map paramMap);
	/**
	 * 获取短信审核信息
	 * @param paramMap
	 * @return
	 */
	public Integer getSmsAuditInfoCount(Map paramMap);
	/**
	 * 获取短信审核信息
	 * @param paramMap
	 * @return
	 */
	public List<Map> getSmsAuditInfo(Map paramMap);
	
	/**
	 * 获取明日生日的客户所属理财经理信息
	 * @author SLY
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getAgentInfo(Map paramMap);

	/**
	 * 获取到账短信map信息
	 * @author SLY
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getPdAmountrderMap(Map paramMap);

	/**
	 * 获取产品成立短信map信息
	 * @author SLY
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getTradeInfoMap(Map paramMap);
	/**
	 * 获取期间分配某产品对应的所有交易
	 * @param paramMap
	 * @author ZYM
	 * @return
	 */
	public List<Map<String, String>> getIncomDisTradeInfo(Map paramMap);
	/**
	 * 获取产品净值短信信息
	 * @author SLY
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getPdwealthNetValueList(Map paramMap);

	/**
	 * 获取同一批次所有短信信息
	 * @author SLY
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getSysSmsDetailInfoList(Map paramMap);
	/**
	 * 获取到期清算某产品对应的所有交易
	 * @param paramMap
	 * @author ZYM
	 * @return
	 */
	public List<Map<String, String>> getIncomDisAllTradeInfo(Map paramMap);

	/**
	 * 获取净值短信营销人员的信息
	 * @param codeName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getDefMap(String codeName);

	/**
	 * 获取春节祝福短信客户和理财经理信息
	 * @return
	 */
	/*@SuppressWarnings("rawtypes")
	public List<Map> getCustInfo();
	@SuppressWarnings("rawtypes")
	public List<Map> getAllAgentInfo();*/
	
	/**
	 * 获取客户变更手机相关信息
	 * @author ZYM
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getCustMobile(Map paramMap);
	/**
	 * 获取客户变更邮箱相关信息
	 * @author ZYM
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getCustEmail(Map paramMap);
	
	/**
	 * 获取客户审核短信总条数
	 * @author chengong
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getCustSmsAuditInfoCount(Map paramMap);
	
	/**
	 * 获取客户审核短信信息
	 * @author chengong
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getCustSmsAuditInfo(Map paramMap);
	/**
	 * 根据产品号获取交易信息
	 * @author ZYM
	 * @return
	 */
	public List<Map<String, String>> getTradeInfoByProductId(Map paramMap);
	/**
	 * 根据分配日期获取交易信息
	 * @author ZYM
	 * @return
	 */
	public List<Map<String, String>> getTradeInfoByDistributeDate(Map paramMap);
	/**
	 * 根据分配日期获取交易信息
	 * @author ZYM
	 * @return
	 */
	public List<Map<String, String>> getStockTradeByDistributeDate(Map paramMap);
}
