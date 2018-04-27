package com.fms.service.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ModifyCustomerServiceMapper {
	@SuppressWarnings("rawtypes")
	public List<Map> custAscriptionMapList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> custAddressInfoMapList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> custAccInfoMapList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getModifyCustomerVisitInfo(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> customerOtherInvestInfoMapList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> customerMyInvestInfoMapList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getCustFamilyInfoMapList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getCustHouseInfoMapList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getCustCarInfoMapList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getCustInvestmentSuggestList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer checkCustBaseInfoChnNameAndMobileCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer checkCustBaseInfoIdNoOrMobileCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer checkCustBaseInfoChnName(Map paramMap);
	
	/**
	 * 根据手机号与姓名查询
	 * @author Liwentao
	 * @param paramMap
	 * @return list<Map>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> searchCustomer(Map paramMap);

	
	public List<Map> getAllCustNextVisitInfo(Map paramMap);


	/**
	 * 根据手机号与姓名查询
	 * @author Liwentao
	 * @param paramMap
	 * @return list<Map>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> searchOthAgentCustomer(Map paramMap);
	/**
	 * 查询其他理财经理的准客户的手机号是否与当前录入的手机号发生冲突
	 * @author Liwentao
	 * @param paramMap
	 * @return list<Map>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> searchOthAgentPreCustomerByMobile(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getMyCustomerByMobile(Map paramMap);
	/**
	 * 获取客户其他公司投资总额
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public BigDecimal getModifyCustomerInvestAmount(Map paramMap);
	
	/**
	 * 获取客户我们公司投资总额
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public BigDecimal getModifyCustomerMyInvestAmount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> searchAgent(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getAllCustVisitEmail(Map paramMap);
	/**
	 * 获取客户我们公司存续金额
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public BigDecimal getModifyCustomerMyRemainAmount(Map paramMap);

	public List<Map> customerMyInvestInfoMapList02(Map paramMap);
	public List<Map> customerMyInvestInfoMapList03(Map paramMap);
	public List<Map> customerMyInvestInfoMapList04(Map paramMap);

	public BigDecimal getModifyCustomerMyInvestAmount02(Map paramMap);

	public BigDecimal getModifyCustomerMyRemainAmount02(Map paramMap);

	public BigDecimal getModifyCustomerMyInvestShare(Map paramMap);
	public BigDecimal getModifyCustomerMyInvestShare02(Map paramMap);

	public BigDecimal getModifyCustomerMyRemainShare(Map paramMap);
	public BigDecimal getModifyCustomerMyRemainShare02(Map paramMap);

	public List<Map<String, String>> getHistoryRecordList(Map paramMap);
	public Integer getHistoryRecordListCount(Map paramMap);
	
}
