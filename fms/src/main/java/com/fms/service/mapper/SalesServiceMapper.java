package com.fms.service.mapper;

import java.util.*;

public interface SalesServiceMapper {
	@SuppressWarnings("rawtypes")
	public List<Map> queryAgentList(Map<String, String> paramMap);
	public Integer queryAgentListCount(Map<String, String> paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getAgentFamilyInfoList(Map<String, String> paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getAgentCertificationInfoList(Map<String, String> paramMap);
	/*@SuppressWarnings("rawtypes")
	public List<Map> getAgentStoreTraceInfo(Map<String, String> paramMap);*/
	@SuppressWarnings("rawtypes")
	public List<Map> getAgentDepartmentTraceInfo(Map<String, String> paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getAgentNurserInfo(Map<String, String> paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getAgentWorkInfo(Map<String, String> paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getAgentPositionTraceInfo(Map<String, String> paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getFoundStatusInfo(Map<String, String> paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getRebackStatusInfo(Map<String, String> paramMap);
	public void  updateAgentWage(Map<String,String>  paramMap);
	@SuppressWarnings("rawtypes")
	public Integer salaryQueryListCount(Map queryParam);
	@SuppressWarnings("rawtypes")
	public List<Map> salaryQueryListPage(Map queryParam);
	@SuppressWarnings("rawtypes")
	public Integer agentWageListCount(Map queryParam);
	@SuppressWarnings("rawtypes")
	public List<Map> agentWageListPage(Map queryParam);
	@SuppressWarnings("rawtypes")
	public List<Map> getSalaryCalRiskInfoList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getSalaryCalWealthInfoList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getAllCalInfoList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getRiskAssessTradeList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getWeathAssessTradeList(Map paramMap);
    @SuppressWarnings("rawtypes")
	public List<Map> getRiskAssessDetailInfoList(Map paramMap);
    @SuppressWarnings("rawtypes")
    public List<Map> getProductDeclineList(Map paramMap);
    @SuppressWarnings("rawtypes")
	public List<Map> getWealthAssessDetailInfoList(Map paramMap);
    @SuppressWarnings("rawtypes")
	public List<Map> getAllAssessDetailInfoList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getMaxAgentWageInfoList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getMaxAgentAssessInfoList(Map paramMap);
	public String getFoundDate(Map<String,String> paramMap);
	public void  updateAgentAssess(Map<String,String>  paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getAssessFoundStatusInfo(Map<String, String> paramMap);
	@SuppressWarnings("rawtypes")
	public Integer AssessCalQueryListCount(Map queryParam);
	@SuppressWarnings("rawtypes")
	public List<Map> AssessCalQueryListPage(Map queryParam);
	@SuppressWarnings("rawtypes")
	public Integer agentAssessListCount(Map queryParam);
	@SuppressWarnings("rawtypes")
	public List<Map> agentAssessListPage(Map queryParam);
	//新加
	@SuppressWarnings("rawtypes")
	public Integer findAgentBelongCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> findAgentBelongList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getAgentOtherInfo(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> querySalaryList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer querySalaryListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer queryBaseSalaryListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryBaseSalaryList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryGuojinCommissionIdList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer queryGuojinCommissionIdListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryOverseasCommissionIdList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer queryOverseasCommissionIdListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryProjectCommissionIdList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer queryProjectCommissionIdListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer queryReissueIdListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryReissueIdList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> querySaleCommissionIdList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer querySaleCommissionIdListCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> queryWithholdIdList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer queryWithholdIdListCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> queryBaseSalaryDetail(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer queryBaseSalaryDetailCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> querySaleCommissionDetail(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer querySaleCommissionDetailCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> queryGuojinCommissionDetail(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer queryGuojinCommissionDetailCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> queryOverseasCommissionDetail(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer queryOverseasCommissionDetailCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> queryReissueDetail(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer queryReissueDetailCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> queryWithholdDetail(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer queryWithholdDetailCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer queryProjectCommissionDetailCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> queryProjectCommissionDetail(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer queryGeneralSalaryCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> queryGeneralSalary(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer queryGeneralBaseSalaryListCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> queryGeneralBaseSalaryList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getAgentAccInfoList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getAgentsInfo(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer querydetailsCommissionIdListCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> querydetailsCommissionIdList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer querySlyCommissionDetailCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> querySlyCommissionDetail(Map paramMap);
}
