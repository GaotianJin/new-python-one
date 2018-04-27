package com.fms.service.balance.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.service.balance.FeeCalculationService;
import com.fms.service.mapper.FeeCalculationServiceMapper;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.NumberUtils;
import com.sinosoft.util.ResultInfo;

@Service
public class FeeCalculationServiceImpl implements FeeCalculationService {

	@Autowired
	private FeeCalculationServiceMapper feeCalculationServiceMapper;
	
	private static final Logger logger = Logger.getLogger(FeeCalculationServiceImpl.class);
	
	
	/**保险产品计算手续费
	 * 保险产品手续费计算公式 ：手续费 = 实收保费*手续费比列
	 * 手续费比例与以下相关因素有关：1.保单年度，2.缴费年期，3.实收保费（不同产品方不一样）
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo calculateCounterFee(Map calParamsMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (calParamsMap==null||calParamsMap.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算手续费,计算参数为空！");
				return resultInfo;
			}
			logger.info("++++++计算手续费参数==="+JsonUtils.objectToJsonStr(calParamsMap));
			if (!calParamsMap.containsKey("actuPremium")) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算手续费,实收保费为空！");
				return resultInfo;
			}
			if(calParamsMap.get("actuPremium")==null){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算手续费,实收保费为空！");
				return resultInfo;
			}
			//根据计算参数获取手续费率
			Double counterFeeRate = feeCalculationServiceMapper.getRiskCounterFeeRate(calParamsMap);
			if (counterFeeRate==null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算手续费,未获取到手续费率！");
				return resultInfo;
			}
			logger.info("++++++手续费率(%)==="+counterFeeRate.toString());
			String feeRate = NumberUtils.divide(counterFeeRate.toString(), "100")+"";
			String actuPremium = calParamsMap.get("actuPremium").toString();
			//保留4位小数
			double counterFee = NumberUtils.multiply(actuPremium, feeRate);
			String counterFeeStr = NumberUtils.round(counterFee, 4).toString();
			logger.info("++++++手续费为==="+counterFeeStr);
			Map<String,String> result = new HashMap<String, String>();
			result.put("feeType", "手续费");
			result.put("feeRate", counterFeeRate.toString());
			result.put("feeMoney", counterFeeStr);
			resultInfo.setSuccess(true);
			resultInfo.setObj(result);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("计算手续费出现异常！");
		}
		return resultInfo;
	}

	/**
	 * 财富固定收益类产品计算咨询服务费（销售费用）
	 * 合作方应支付的销售费用=∑（每档募集金额*对应每档销售费用）*n个月（产品期限）/12个月
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo calculateConsultationServiceFee(Map calParamsMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (calParamsMap==null||calParamsMap.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算咨询服务费,计算参数为空！");
				return resultInfo;
			}
			logger.info("++++++计算咨询服务费参数==="+JsonUtils.objectToJsonStr(calParamsMap));
			if (!calParamsMap.containsKey("actuSubscriptionAmount")) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算咨询服务费,实际认购额为空！");
				return resultInfo;
			}
			if(calParamsMap.get("actuSubscriptionAmount")==null){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算咨询服务费,实际认购额为空！");
				return resultInfo;
			}
			//根据计算参数获取手续费率
			//Double consultationServiceFeeRate = feeCalculationServiceMapper.getConsultationServiceFeeRate(calParamsMap);
			Double consultationServiceFeeRate = Double.parseDouble(calParamsMap.get("consultationServiceFeeRate").toString());
			if (consultationServiceFeeRate==null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算咨询服务费,未获取到咨询服务费比例！");
				return resultInfo;
			}
			logger.info("++++++咨询服务费比例(%)==="+consultationServiceFeeRate.toString());
			String feeRate = NumberUtils.divide(consultationServiceFeeRate.toString(),"100")+"";
			String actuSubscriptionAmount = calParamsMap.get("actuSubscriptionAmount").toString();
			//封闭期，即产品期限
			String closeDperiod = calParamsMap.get("closeDperiod").toString();
			//封闭期单位，为Y、M、D，封闭期单位不一样，计算公式不一样
			String closeDperiodUnit = calParamsMap.get("closeDperiodUnit").toString();
			if (closeDperiodUnit==null||"".equals(closeDperiodUnit)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算咨询服务费,产品期限信息！");
				return resultInfo;
			}
			logger.info("++++++产品封闭期和封闭期单位==="+closeDperiod+"====="+closeDperiodUnit);
			if("M".equals(closeDperiodUnit)){
				double monthFeeRate = Integer.parseInt(closeDperiod)*NumberUtils.divide(consultationServiceFeeRate.toString(),"100")/12;
				feeRate = monthFeeRate+"";
			}else if("D".equals(closeDperiodUnit)){
				double dayFeeRate = Integer.parseInt(closeDperiod)*NumberUtils.divide(consultationServiceFeeRate.toString(),"100")/365;
				feeRate = dayFeeRate+"";
			}else {
				feeRate = Integer.parseInt(closeDperiod)*NumberUtils.divide(consultationServiceFeeRate.toString(),"100")+"";
			}
			//保留4位小数
			logger.info("++++++转换后的咨询服务费比例==="+feeRate);
			double consultationServiceFee = NumberUtils.multiply(actuSubscriptionAmount, feeRate);
			String consultationServiceFeeStr = NumberUtils.round(consultationServiceFee,4).toString();
			logger.info("++++++咨询服务费为==="+consultationServiceFeeStr);
			Map<String,String> result = new HashMap<String, String>();
			result.put("feeType", "咨询服务费");
			result.put("feeRate", consultationServiceFeeRate.toString());
			result.put("feeMoney", consultationServiceFeeStr);
			resultInfo.setSuccess(true);
			resultInfo.setObj(result);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("计算咨询服务费出现异常！");
		}
		return resultInfo;
	}

	/**财富非固定收益类计算固定管理费
	 * [(An净值+Ai净值)/ 2]*固定管理费比例（年化）/365*相减的净值公布日期区间*认购份额
   	 *	注：a.需判断如果当前的结算终止日期（对应的公布日期）的日期点上有没有相应的净值，如果有则取对应的净值。如果没有，则取前一个净值。
     *      b.计算的净值均是费前的净值；
     *      c.净值与开放日有关联。
     *      d.结算日期是开放日为准。
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo calculateFixedManagementFee(Map calParamsMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (calParamsMap==null||calParamsMap.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算固定管理费,计算参数为空！");
				return resultInfo;
			}
			logger.info("++++++计算固定管理费参数==="+JsonUtils.objectToJsonStr(calParamsMap));
			if (!calParamsMap.containsKey("subscriptionCopies")) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算固定管理费,实际认购份额为空！");
				return resultInfo;
			}
			if(calParamsMap.get("subscriptionCopies")==null){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算固定管理费,实际认购份额为空！");
				return resultInfo;
			}
			//根据计算参数获取固定管理费比例
			Double fixManagementFeeRatio = Double.parseDouble(calParamsMap.get("fixManagementFeeRatio").toString());
			if (fixManagementFeeRatio==null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算固定管理费,未获取到固定管理费比例！");
				return resultInfo;
			}
			logger.info("++++++固定管理费比例(%)==="+fixManagementFeeRatio.toString());
			String feeRate = NumberUtils.divide(fixManagementFeeRatio.toString(),"100")+"";
			String startDate = (String)calParamsMap.get("startDate");
			String startDateNetValue = (String)calParamsMap.get("startDateNetValue");
			String endDate = (String)calParamsMap.get("endDate");
			String endDateNetValue = (String)calParamsMap.get("endDateNetValue");
			int interval = DateUtils.calInterval(startDate, endDate, "D");
			String subscriptionCopies = calParamsMap.get("subscriptionCopies").toString();
			double avgNetValue = Double.parseDouble(startDateNetValue)/2+Double.parseDouble(endDateNetValue)/2;
			double fixManagementFee = avgNetValue*Double.parseDouble(feeRate)*Double.parseDouble(subscriptionCopies)*interval/365;
			String fixManagementFeeStr = NumberUtils.round(fixManagementFee,4).toString();
			logger.info("++++++计算固定管理费区间===startDate：=="+startDate+"===endDate=="+endDate);
			logger.info("++++++计算固定管理费净值===startDateNetValue：=="+startDateNetValue+"===endDateNetValue=="+endDateNetValue);
			logger.info("++++++固定管理费为==="+fixManagementFeeStr);
			Map<String,String> result = new HashMap<String, String>();
			result.put("feeType", "固定管理费");
			result.put("feeRate", fixManagementFeeRatio.toString());
			result.put("feeMoney", fixManagementFeeStr);
			resultInfo.setSuccess(true);
			resultInfo.setObj(result);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("计算固定管理费出现异常！");
		}
		return resultInfo;
	}

	/**财富非固定收益类计算浮动管理费
	 * 浮动管理费=(An净值-Max(Ai净值))*认购份额*浮动管理费比例
	 * 
   	 *	1）认购份额=认购初始份额-赎回份额（目前没有赎回功能）
   	 *	2）目前的追加功能，都是做一个新的交易，所以现在每笔交易只会存在一个初始的认购份额。
   	 *	3）初始认购份额在交易状态维护页面进行录入。
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo calculateFloatManagementFee(Map calParamsMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (calParamsMap==null||calParamsMap.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算浮动管理费,计算参数为空！");
				return resultInfo;
			}
			logger.info("++++++计算浮动管理费参数==="+JsonUtils.objectToJsonStr(calParamsMap));
			if (!calParamsMap.containsKey("subscriptionCopies")) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算浮动管理费,实际认购份额为空！");
				return resultInfo;
			}
			if(calParamsMap.get("subscriptionCopies")==null){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算浮动管理费,实际认购份额为空！");
				return resultInfo;
			}
			//根据计算参数获取固定管理费比例
			Double floatManagementRatio = Double.parseDouble(calParamsMap.get("floatManagementRatio").toString());
			if (floatManagementRatio==null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算浮动管理费,未获取到浮动管理费比例！");
				return resultInfo;
			}
			logger.info("++++++浮动管理费比例(%)==="+floatManagementRatio);
			String feeRate = NumberUtils.divide(floatManagementRatio.toString(),"100")+"";
			String publicDate = (String)calParamsMap.get("publicDate");
			String netValue = calParamsMap.get("netValue").toString();
			String subscriptionCopies = calParamsMap.get("subscriptionCopies").toString();
			Double beforePublicDateMaxNetValue = feeCalculationServiceMapper.getBeforeDateMaxNetValue(calParamsMap);
			
			
			double floatManagementFee = (Double.parseDouble(netValue)-beforePublicDateMaxNetValue)*Double.parseDouble(subscriptionCopies)*Double.parseDouble(feeRate);
			String floatManagementFeeStr = NumberUtils.round(floatManagementFee,4).toString();
			logger.info("++++++计算浮动管理费开放日===publicDate：=="+publicDate);
			logger.info("++++++计算浮动管理费净值===netValue：=="+netValue+"===beforePublicDateMaxNetValue=="+beforePublicDateMaxNetValue);
			logger.info("++++++浮动管理费为==="+floatManagementFee);
			Map<String,String> result = new HashMap<String, String>();
			result.put("feeType", "浮动管理费");
			result.put("feeRate", floatManagementRatio.toString());
			result.put("historyMaxNetValue", beforePublicDateMaxNetValue.toString());
			result.put("feeMoney", floatManagementFeeStr);
			resultInfo.setSuccess(true);
			resultInfo.setObj(result);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("计算浮动管理费出现异常！");
		}
		return resultInfo;
	}

	/**财富非固定收益类计算认购费
	 * 认购费公式=认购金额*认购费比例
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo calculateSubscriptionFee(Map calParamsMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (calParamsMap==null||calParamsMap.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算认购费,计算参数为空！");
				return resultInfo;
			}
			logger.info("++++++计算认购费参数==="+JsonUtils.objectToJsonStr(calParamsMap));
			if (!calParamsMap.containsKey("actuSubscriptionAmount")) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算认购费,实际认购额为空！");
				return resultInfo;
			}
			if(calParamsMap.get("actuSubscriptionAmount")==null){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算认购费,实际认购额为空！");
				return resultInfo;
			}
			//根据计算参数获取手续费率
			Double subscriptionFeeRatio = Double.parseDouble(calParamsMap.get("subscriptionFeeRatio").toString());
			Double reductionRadio = Double.parseDouble(calParamsMap.get("reductionRadio").toString());
			double FeeRatio = (100.00-reductionRadio)/100;
			if (subscriptionFeeRatio==null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算认购费,未获取到认购费比例！");
				return resultInfo;
			}
			logger.info("++++++认购费比例(%)==="+subscriptionFeeRatio.toString());
			String feeRate = NumberUtils.divide(subscriptionFeeRatio.toString(),"100")+"";
			String actuSubscriptionAmount = calParamsMap.get("actuSubscriptionAmount").toString();
			double subscriptionFee = NumberUtils.multiply(actuSubscriptionAmount, feeRate);
			//计算减免之后的认购费
			subscriptionFee = NumberUtils.multiply(subscriptionFee+"", FeeRatio+"");
			String subscriptionFeeStr = NumberUtils.round(subscriptionFee,4).toString();
			logger.info("++++++认购费为==="+subscriptionFeeStr);
			Map<String,String> result = new HashMap<String, String>();
			result.put("feeType", "认购费");
			result.put("feeRate", subscriptionFeeRatio.toString());
			result.put("feeMoney", subscriptionFeeStr);
			resultInfo.setSuccess(true);
			resultInfo.setObj(result);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("计算认购费出现异常！");
		}
		return resultInfo;
	}

}
