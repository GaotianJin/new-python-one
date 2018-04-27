package com.fms.service.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CalSalaryServiceMapper {
	//获取折标系数SQL
	@SuppressWarnings("rawtypes")
	public List<Map> getScaleFactor(Map<String, String> paramMap);
	
	//获取某个财富产品某个月的认购额
	public BigDecimal getSubscriptionFeeAMonth(Map<String, String> paramMap);
	
	//根据认购额获取财富产品(浮动/股权的奖金比例)
	public BigDecimal getWealthBonusRate(Map<String, String> paramMap);
	
	//根据标准保费获取个人寿险奖金比例
	public BigDecimal getPersonLifeBonusRate(Map<String, String> paramMap);
	
	//获取财富产品久期系数
    public BigDecimal getWealthDurationRate(Map<String, String> paramMap);
	
	//获取某个理财经理某个承保成功的总单子
	@SuppressWarnings("rawtypes")
	public List<Map> getPersonLifeFoundList(Map<String, String> paramMap);
	
	//根据标准保费获取个人寿险奖金比例
	public BigDecimal getYBBonusRate(Map<String, String> paramMap);
	
	//获取某个理财经理某个产品某个月的总的标准保费
	public BigDecimal getTotalStandrdPremFromData(Map<String, String> paramMap);
	
    //获取保险产品交易的缴费期间和缴费期间单位
	@SuppressWarnings("rawtypes")
	public List<Map> getPayPeriodList(Map<String, String> paramMap);

	//查找个人寿险趸交的久期系数
	public BigDecimal getPersonLifeFirstDurationRate(Map<String, String> paramMap);
	
	//查找个人寿险趸交的久期系数
    public BigDecimal getPersonLifeDurationRate(Map<String, String> paramMap);
   
   
   //查找银行保险趸交的久期系数
 	public BigDecimal getYBLifeFirstDurationRate(Map<String, String> paramMap);
 	
 	//查找银行保险的久期系数
    public BigDecimal getYBLifeDurationRate(Map<String, String> paramMap);
    
    //查找某个部门下这个月的所有有业绩的agent
    @SuppressWarnings("rawtypes")
	public List<Map> getAgentIdList(Map<String, String> paramMap);
    
    //查询每个agent一个月的总业务费及奖金
    public Double  getAgentWageAmonth(Map<String, String> paramMap);
}
