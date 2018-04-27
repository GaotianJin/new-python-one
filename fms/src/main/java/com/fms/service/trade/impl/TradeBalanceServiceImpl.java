package com.fms.service.trade.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.service.balance.FeeCalculationService;
import com.fms.service.mapper.TradeBalanceServiceMapper;
import com.fms.service.trade.TradeBalanceService;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.NumberUtils;
import com.sinosoft.util.ResultInfo;

@Service
public class TradeBalanceServiceImpl implements TradeBalanceService {
	
	@Autowired
	private TradeBalanceServiceMapper tradeBalanceServiceMapper;
	@Autowired
	private FeeCalculationService feeCalculationService;
	@Autowired
	private CommonService commonService;
	
	private static final Logger logger = Logger.getLogger(TradeBalanceServiceImpl.class);

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DataGrid queryBalanceTradeInfoList(DataGridModel dgm, Map paramMap,
			LoginInfo loginInfo) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		
		Integer total = tradeBalanceServiceMapper.queryBalanceTradeInfoListCount(paramMap);
		List<Map> resultList = tradeBalanceServiceMapper.queryBalanceTradeInfoList(paramMap);
		//datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo tradeBalance(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		List<Map<String, Object>> reportList = new ArrayList<Map<String,Object>>();
		String startDate = (String)paramMap.get("startDate");
		String endDate = (String)paramMap.get("endDate");
		String productType = (String)paramMap.get("productType");
		String productSubType = (String)paramMap.get("productSubType");
		List<Map> allMonthList = DateUtils.getMonthBetween(startDate, endDate);
		if (allMonthList==null||allMonthList.size()==0) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("此日期区间内没有要结算的月份");
			return resultInfo;
		}
		
		//对财富产品进行结算
		if(productType!=null&&!"".equals(productType)&&"1".equals(productType)) {
			for (Map monthMap:allMonthList) {
				paramMap.putAll(monthMap);
				String currentMonth = (String)monthMap.get("currentMonth");
				
				ResultInfo wealthTradeBalanceResultInfo = wealthTradeBalance(paramMap);
				if (wealthTradeBalanceResultInfo.isSuccess()) {
					Map<String, Object> reportMap = commonService.getReportInfo("WealthBalanceDetail");
					//对sheet名称加上当前月份
					String reportName = reportMap.get("reportName").toString();
					String subType = "";
					if(productSubType!=null&&!"".equals(productSubType)&&"01".equals(productSubType)){
						subType = "股权类";
					}
					else if(productSubType!=null&&!"".equals(productSubType)&&"02".equals(productSubType)){
						subType = "固定收益类";
					}
					else if(productSubType!=null&&!"".equals(productSubType)&&"03".equals(productSubType)){
						subType = "浮动收益类";
					}
					reportMap.put("reportName", reportName+"("+subType+currentMonth+")");
					List<Map> balanceResultList = (List<Map>)wealthTradeBalanceResultInfo.getObj();
					if (reportMap!=null&&reportMap.size()>0&&balanceResultList!=null&&balanceResultList.size()>0) {
						reportMap.put("reportData", balanceResultList);
						reportList.add(reportMap);
					}
				}
			}
		}
		//对保险产品进行结算
		else if(productType!=null&&!"".equals(productType)&&"2".equals(productType)) {
			for (Map monthMap:allMonthList) {
				paramMap.putAll(monthMap);
				String currentMonth = (String)monthMap.get("currentMonth");
				ResultInfo riskTradeBalanceResultInfo = riskTradeBalance(paramMap);
				if (riskTradeBalanceResultInfo.isSuccess()) {
					Map<String, Object> reportMap = commonService.getReportInfo("RiskBalanceDetail");
					//对sheet名称加上当前月份
					String reportName = reportMap.get("reportName").toString();
					reportMap.put("reportName", reportName+currentMonth);
					List<Map> balanceResultList = (List<Map>)riskTradeBalanceResultInfo.getObj();
					if (reportMap!=null&&reportMap.size()>0&&balanceResultList!=null&&balanceResultList.size()>0) {
						reportMap.put("reportData", balanceResultList);
						reportList.add(reportMap);
					}
				}
			}
		}
		
		resultInfo.setSuccess(true);
		resultInfo.setObj(reportList);
		return resultInfo;
	}

	/**
	 * 保险交易结算手续费
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultInfo riskTradeBalance(Map paramMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map> balanceResultList = new ArrayList<Map>();
			List<Map> balaceFailTradeList = new ArrayList<Map>();
			List<Map> riskTradeInfoList = tradeBalanceServiceMapper.getTradeBalanceRiskInfoList(paramMap);
			logger.info("=======保险交易结算开始++++++++");
			for (Map riskTradeInfoMap:riskTradeInfoList) {
				ResultInfo calFeeResultInfo = feeCalculationService.calculateCounterFee(riskTradeInfoMap);
				if(calFeeResultInfo.isSuccess()){
					Map resultMap = new HashMap();
					resultMap.putAll(riskTradeInfoMap);
					resultMap.putAll((Map)calFeeResultInfo.getObj());
					balanceResultList.add(resultMap);
				}else {
					balaceFailTradeList.add(riskTradeInfoMap);
					logger.info("交易("+riskTradeInfoMap.get("tradeInfoId").toString()+")结算手续费出错："+calFeeResultInfo.getMsg());
					continue;
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(balanceResultList);
			logger.info("=======保险交易结算完成++++++++");
			logger.info("=======保险交易结算失败(共"+balaceFailTradeList.size()+"笔)的交易如下++++++++");
			logger.info(JsonUtils.objectToJsonStr(balaceFailTradeList));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保险交易结算出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 * 财富产品交易结算
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultInfo wealthTradeBalance(Map paramMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map> balanceResultList = new ArrayList<Map>();
			List<Map> balaceFailTradeList = new ArrayList<Map>();
			List<Map> wealthTradeInfoList = tradeBalanceServiceMapper.getTradeBalanceWealthInfoList(paramMap);
			logger.info("=======财富产品交易结算开始,结算咨询服务费和认购费++++++++");
			for (Map wealthTradeInfoMap:wealthTradeInfoList) {
				//01：股权类，02：固定收益类，03：浮动收益类  def_code表code_type = wealthProSubType
				String productSubType = (String)wealthTradeInfoMap.get("productSubType");
				//固定收益类产品只需结算咨询服务费
				if (productSubType!=null&&!"".equals(productSubType)&&"02".equals(productSubType)) {
					ResultInfo calConsultationServiceFeeResultInfo = feeCalculationService.calculateConsultationServiceFee(wealthTradeInfoMap);
					if(calConsultationServiceFeeResultInfo.isSuccess()){
						Map resultMap = new HashMap();
						resultMap.putAll(wealthTradeInfoMap);
						resultMap.putAll((Map)calConsultationServiceFeeResultInfo.getObj());
						balanceResultList.add(resultMap);
					}else {
						balaceFailTradeList.add(wealthTradeInfoMap);
						logger.info("交易("+wealthTradeInfoMap.get("tradeInfoId").toString()+")结算咨询服务费出错："+calConsultationServiceFeeResultInfo.getMsg());
						continue;
					}
				}else {
					//浮动收益类03和股权类01需要结算：认购费、浮动管理费、固定管理费
					wealthTradeInfoMap.put("startDate", paramMap.get("startDate"));
					wealthTradeInfoMap.put("endDate", paramMap.get("endDate"));
					//1.计算认购费
					ResultInfo calSubscriptionFeeResultInfo = feeCalculationService.calculateSubscriptionFee(wealthTradeInfoMap);
					if(calSubscriptionFeeResultInfo.isSuccess()){
						Map resultMap = new HashMap();
						resultMap.putAll(wealthTradeInfoMap);
						resultMap.putAll((Map)calSubscriptionFeeResultInfo.getObj());
						balanceResultList.add(resultMap);
					}else {
						balaceFailTradeList.add(wealthTradeInfoMap);
						logger.info("交易("+wealthTradeInfoMap.get("tradeInfoId").toString()+")结算认购费出错："+calSubscriptionFeeResultInfo.getMsg());
					}
					
				}
			}
			//结算固定管理费和浮动管理费
			logger.info("=======财富产品交易结算固定管理费和浮动管理费++++++++");
			List<Map> wealthMapList = tradeBalanceServiceMapper.getTradeBalanceWealthNotFixedInfoList(paramMap);
			for (Map wealthInfoMap:wealthMapList) {
				wealthInfoMap.put("startDate", paramMap.get("startDate"));
				wealthInfoMap.put("endDate", paramMap.get("endDate"));
				//1.计算固定管理费
				Map<String,List<Map>> calFixedManagementFeeResult = calculateFixedManagementFee(wealthInfoMap);
				if (calFixedManagementFeeResult!=null&&calFixedManagementFeeResult.containsKey("balanceResultList")) {
					balanceResultList.addAll(calFixedManagementFeeResult.get("balanceResultList"));
				}
				if (calFixedManagementFeeResult!=null&&calFixedManagementFeeResult.containsKey("balaceFailTradeList")) {
					balaceFailTradeList.addAll(calFixedManagementFeeResult.get("balaceFailTradeList"));
				}
				//2.计算浮动管理费
				Map<String,List<Map>> calFloatManagementFeeResult = calculateFloatManagementFee(wealthInfoMap);
				if (calFloatManagementFeeResult!=null&&calFloatManagementFeeResult.containsKey("balanceResultList")) {
					balanceResultList.addAll(calFloatManagementFeeResult.get("balanceResultList"));
				}
				if (calFloatManagementFeeResult!=null&&calFloatManagementFeeResult.containsKey("balaceFailTradeList")) {
					balaceFailTradeList.addAll(calFloatManagementFeeResult.get("balaceFailTradeList"));
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(balanceResultList);
			logger.info("=======财富产品交易结算完成++++++++");
			logger.info("=======财富产品交易结算失败(共"+balaceFailTradeList.size()+"笔)的交易如下++++++++");
			logger.info(JsonUtils.objectToJsonStr(balaceFailTradeList));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("财富产品交易结算出现异常！");
		}
		return resultInfo;
	}
	
	
	
	/**
	 * 按净值公布日期分段计算固定管理费
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String,List<Map>> calculateFixedManagementFee(Map paramMap){
		Map<String,List<Map>> calResult = new HashMap<String,List<Map>>();
		List<Map> balanceResultList = new ArrayList<Map>();
		List<Map> balaceFailTradeList = new ArrayList<Map>();
		//获取该产品在结算区间内公布的净值列表信息
		List<Map> netValueList = getNetValueList(paramMap);
		//该日期区间段的净值公布次数
		int size = 0;
		if (netValueList==null||netValueList.size()==0) {
			return null;
		}
		//计算次数
		int count = 0;
		size = netValueList.size();
		String startDate = (String)paramMap.get("startDate");
		String endDate = (String)paramMap.get("endDate");
		String expectOpenDay = (String)paramMap.get("expectOpenDay");
		//确定实际起始结算日期
		String actuCalStartDate = startDate;
		if (startDate==null||"".equals(startDate)) {
			actuCalStartDate = expectOpenDay;
		}else {
			if (Date.valueOf(startDate).before(Date.valueOf(expectOpenDay))) {
				actuCalStartDate = expectOpenDay;
			}
		}
		//获取起始日期的净值
		Map map = new HashMap();
		map.put("pdWealthId", paramMap.get("pdWealthId"));
		map.put("publicDate", actuCalStartDate);
		Map actuStartNetValueMap = tradeBalanceServiceMapper.getBeforeDateActuBalaDateAndNetValue(map);
		String actuStartNetValue = actuStartNetValueMap.get("actuNetValue").toString();
		//获取认购份额,现在还没有考虑赎回的功能，否则每段区间的认购份额的重新计算
		String fixManagementFeeRatio = paramMap.get("fixManagementFeeRatio").toString();
		String subscriptionCopies = paramMap.get("subscriptionCopies").toString();
		//设置最后一次的结算日期和净值
		String lastCalDate = endDate;
		String lastCalNetValue = "";
		//本月结算总和
		Double sumfeeMoney = 0.00;
		String feeRate = "";
		String feeType = "";
		for (Map netValueMap:netValueList) {
			Map calFeeParamMap = new HashMap();
			String publicDate = (String)netValueMap.get("publicDate");
			String netValue = netValueMap.get("netValue").toString();
			calFeeParamMap.put("startDate", actuCalStartDate);
			calFeeParamMap.put("startDateNetValue", actuStartNetValue);
			calFeeParamMap.put("endDate", publicDate);
			calFeeParamMap.put("endDateNetValue", netValue);
//			calFeeParamMap.put("subscriptionCopies", subscriptionCopies);
			calFeeParamMap.put("subscriptionCopies", netValueMap.get("subscriptionCopies"));
			calFeeParamMap.put("fixManagementFeeRatio", fixManagementFeeRatio);
			ResultInfo calFeeresultInfo = feeCalculationService.calculateFixedManagementFee(calFeeParamMap);
			if(calFeeresultInfo.isSuccess()){
				String fixedManagementFee = ((Map<String,String>)calFeeresultInfo.getObj()).get("feeMoney");
				feeRate = (String)((Map)calFeeresultInfo.getObj()).get("feeRate");
				feeType = (String)((Map)calFeeresultInfo.getObj()).get("feeType");
				if(Double.parseDouble(fixedManagementFee)>0){
					sumfeeMoney += Double.parseDouble(fixedManagementFee);
					/*Map resultMap = new HashMap();
					resultMap.putAll(paramMap);
					resultMap.putAll(calFeeParamMap);
					resultMap.putAll((Map)calFeeresultInfo.getObj());
					balanceResultList.add(resultMap);*/
				}
			}else {
				balaceFailTradeList.add(calFeeParamMap);
				logger.info("交易("+paramMap.get("tradeInfoId").toString()+")结算固定管理费出错："+calFeeresultInfo.getMsg());
			}
			count++;
			//每段结算的终止日期为下段结算的起始日期
			actuCalStartDate = publicDate;
			actuStartNetValue = netValue;
			if(count==size){
				if(endDate==null||"".equals(endDate)||endDate.equals(publicDate)){
					continue;
				}
				//终止日期净值未公布，用上次公布的净值
				if (Date.valueOf(endDate).after(Date.valueOf(publicDate))) {
					//获取最后一次计算的净值
					Map getEndDateNetValueParamMap = new HashMap();
					getEndDateNetValueParamMap.put("pdWealthId", paramMap.get("pdWealthId"));
					getEndDateNetValueParamMap.put("publicDate", endDate);
					Map afterEndDateNetValueMap = tradeBalanceServiceMapper.getAfterDateBalaNetValue(getEndDateNetValueParamMap);
					String actuEndNetValue = afterEndDateNetValueMap.get("actuNetValue").toString();
					if(actuEndNetValue!=null&&!"".equals(actuEndNetValue)&&Double.parseDouble(actuEndNetValue)>0){
						lastCalNetValue = actuEndNetValue;
					}else {
						//没有公布的话用最近上一次公布的净值
						lastCalNetValue = netValueMap.get("netValue").toString();
					}
					Map lastCalFeeParamMap = new HashMap();
					lastCalFeeParamMap.put("startDate", actuCalStartDate);
					lastCalFeeParamMap.put("startDateNetValue", actuStartNetValue);
					lastCalFeeParamMap.put("endDate", lastCalDate);
					lastCalFeeParamMap.put("endDateNetValue", lastCalNetValue);
					lastCalFeeParamMap.put("subscriptionCopies", subscriptionCopies);
					lastCalFeeParamMap.put("fixManagementFeeRatio", fixManagementFeeRatio);
					ResultInfo lastCalFeeresultInfo = feeCalculationService.calculateFixedManagementFee(lastCalFeeParamMap);
					if(lastCalFeeresultInfo.isSuccess()){
						String fixedManagementFee = ((Map<String, String>)lastCalFeeresultInfo.getObj()).get("feeMoney");
						feeRate = (String)((Map)lastCalFeeresultInfo.getObj()).get("feeRate");
						feeType = (String)((Map)lastCalFeeresultInfo.getObj()).get("feeType");
						if(Double.parseDouble(fixedManagementFee)>0){
							sumfeeMoney += Double.parseDouble(fixedManagementFee);
							/*Map resultMap = new HashMap();
							resultMap.putAll(paramMap);
							resultMap.putAll(lastCalFeeParamMap);
							resultMap.putAll((Map)lastCalFeeresultInfo.getObj());
							balanceResultList.add(resultMap);*/
						}
					}else {
						balaceFailTradeList.add(calFeeParamMap);
						logger.info("交易("+paramMap.get("tradeInfoId").toString()+")结算固定管理费出错："+lastCalFeeresultInfo.getMsg());
					}
				}
			}
		}
		sumfeeMoney =  NumberUtils.round(sumfeeMoney,4).doubleValue();
		paramMap.put("feeType", feeType);
		paramMap.put("feeRate", feeRate);
		paramMap.put("feeMoney", sumfeeMoney.toString());
		balanceResultList.add(paramMap);
		calResult.put("balanceResultList", balanceResultList);
		calResult.put("balaceFailTradeList", balaceFailTradeList);
		
		return calResult;
	}
	
	
	/**
	 * 计算浮动管理费
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String,List<Map>> calculateFloatManagementFee(Map paramMap){
		Map<String,List<Map>> calResult = new HashMap<String,List<Map>>();
		List<Map> balanceResultList = new ArrayList<Map>();
		List<Map> balaceFailTradeList = new ArrayList<Map>();
		//获取该产品在结算区间内公布的净值列表信息
		//List<Map> netValueList = getNetValueList(paramMap);
		List<Map> netValueList = getOpenDayNetValueList(paramMap);
		if (netValueList==null||netValueList.size()==0) {
			return null;
		}
		//获取认购份额,现在还没有考虑赎回的功能，否则每段区间的认购份额的重新计算
		String floatManagementRatio = paramMap.get("floatManagementRatio").toString();
		String subscriptionCopies = paramMap.get("subscriptionCopies").toString();
		for (Map netValueMap:netValueList) {
			Map calFeeParamMap = new HashMap();
			calFeeParamMap.put("pdWealthId", paramMap.get("pdWealthId"));
			calFeeParamMap.put("publicDate", netValueMap.get("openDate"));
			calFeeParamMap.put("netValue", netValueMap.get("netValue"));
//			calFeeParamMap.put("subscriptionCopies", subscriptionCopies);
			calFeeParamMap.put("subscriptionCopies", netValueMap.get("subscriptionCopies").toString());
			calFeeParamMap.put("floatManagementRatio", floatManagementRatio);
			ResultInfo calFeeresultInfo = feeCalculationService.calculateFloatManagementFee(calFeeParamMap);
			if(calFeeresultInfo.isSuccess()){
				String floatManagementFee = ((Map<String, String>)calFeeresultInfo.getObj()).get("feeMoney");
				if(Double.parseDouble(floatManagementFee)>0){
					Map resultMap = new HashMap();
					resultMap.putAll(paramMap);
					resultMap.putAll(netValueMap);
					resultMap.putAll(calFeeParamMap);
					resultMap.putAll((Map)calFeeresultInfo.getObj());
					balanceResultList.add(resultMap);
				}
			}else {
				balaceFailTradeList.add(calFeeParamMap);
				logger.info("交易("+paramMap.get("tradeInfoId").toString()+")结算浮动管理费出错："+calFeeresultInfo.getMsg());
			}
		}
		calResult.put("balanceResultList", balanceResultList);
		calResult.put("balaceFailTradeList", balaceFailTradeList);
		return calResult;
	}
	
	
	/**
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> getNetValueList(Map paramMap){
		if (paramMap==null||paramMap.size()==0) {
			return null;
		}
		//设置获取的净值类型，01为费前，02为费后，先获取费前的，没有的话获取费后的
		paramMap.put("netType", "01");
		List<Map> netValueList = tradeBalanceServiceMapper.getNetValueList(paramMap);
		if (netValueList==null||netValueList.size()==0) {
			paramMap.put("netType", "02");
			netValueList = tradeBalanceServiceMapper.getNetValueList(paramMap);
		}
		return netValueList;
	}
	
	/**
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> getOpenDayNetValueList(Map paramMap){
		if (paramMap==null||paramMap.size()==0) {
			return null;
		}
		//设置获取的净值类型，01为费前，02为费后，先获取费前的，没有的话获取费后的
		paramMap.put("netType", "01");
		List<Map> netValueList = tradeBalanceServiceMapper.getOpenDayNetValueList(paramMap);
		if (netValueList==null||netValueList.size()==0) {
			paramMap.put("netType", "02");
			netValueList = tradeBalanceServiceMapper.getOpenDayNetValueList(paramMap);
		}
		return netValueList;
	}
	
}
