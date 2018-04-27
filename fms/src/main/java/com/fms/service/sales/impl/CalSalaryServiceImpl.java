package com.fms.service.sales.impl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.mapper.AgentDepartmentMapper;
import com.fms.db.mapper.AgentPositionInfoMapper;
import com.fms.db.mapper.AgentPositionTraceMapper;
import com.fms.db.mapper.AgentWageMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PDRiskMapper;
import com.fms.db.mapper.PDWealthMapper;
import com.fms.db.mapper.TradeInfoMapper;
import com.fms.db.mapper.TradeProductInfoMapper;
import com.fms.db.mapper.TradeStatusInfoMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentPositionInfoExample;
import com.fms.db.model.AgentPositionTrace;
import com.fms.db.model.AgentPositionTraceExample;
import com.fms.db.model.AgentWage;
import com.fms.db.model.AgentWageExample;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDProductExample;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthExample;
import com.fms.db.model.TradeInfo;
import com.fms.db.model.TradeInfoExample;
import com.fms.db.model.TradeProductInfo;
import com.fms.db.model.TradeProductInfoExample;
import com.fms.db.model.TradeStatusInfo;
import com.fms.service.mapper.CalSalaryServiceMapper;
import com.fms.service.mapper.SalesServiceMapper;
import com.fms.service.sales.CalSalaryService;
import com.sinosoft.util.ResultInfo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CalSalaryServiceImpl implements CalSalaryService {
	private static final Logger log = Logger.getLogger(CalSalaryServiceImpl.class);
	@Autowired
	private TradeStatusInfoMapper tradeStatusInfoMapper;
	@Autowired
	private PDRiskMapper pdRiskMapper;
	@Autowired
	private CalSalaryServiceMapper calSalaryServiceMapper;
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	@Autowired
	private SalesServiceMapper salesServiceMapper;
	@Autowired
	private PDWealthMapper pdWealthMapper;
	@Autowired
	private TradeProductInfoMapper tradeProductInfoMapper;
	@Autowired
	private PDProductMapper pdProductMapper;
	@Autowired
	private AgentWageMapper agentMapper;
	@Autowired
	private AgentDepartmentMapper agentDepartmentMapper;
	@Autowired
	private AgentPositionTraceMapper agentPositionTraceMapper;
	@Autowired
	private AgentPositionInfoMapper agentPositionInfoMapper;

	/** 计算每笔交易奖金 **/
	@Transactional
	public ResultInfo calSalByTrade(String tradeInfoId, BigDecimal trueMonery,PDProduct pdProduct,String calYear,String calMonth) {
		log.info("*****开始计算交易Id：" + tradeInfoId+"的此交易奖金++++++");
		Double calBonusMoney = null;
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (tradeInfoId != null && pdProduct != null && trueMonery != null) {
				// 财富产品的奖金 =实际认购额*奖金比例
				if (pdProduct.getProductType().equals("1")) {
					resultInfo = getBonusRate(tradeInfoId, pdProduct, calYear,calMonth);// 获取奖金比例
					if (resultInfo.isSuccess()) {
						calBonusMoney = trueMonery.doubleValue()*(new Double(resultInfo.getObj().toString()));// 计算财富产品的奖金
						resultInfo.setSuccess(true);
						resultInfo.setObj(calBonusMoney);
						return resultInfo;
					} else {
						
						return resultInfo;
					}
				}
				// 保险产品的奖金=标准保费*奖金比例
				if (pdProduct.getProductType().equals("2")) {
					 ResultInfo resultInfo2=getStandrdPrem(tradeInfoId, trueMonery, pdProduct);//获取标准保费
					 resultInfo = getBonusRate(tradeInfoId, pdProduct,calYear, calMonth);// 获取奖金比例
					 if(resultInfo.isSuccess()&&resultInfo2.isSuccess()){
						 calBonusMoney = new Double(resultInfo2.getObj().toString())*(new Double(resultInfo.getObj().toString()));//计算保险产品奖金
						 resultInfo.setSuccess(true);
						 resultInfo.setObj(calBonusMoney);
						 return resultInfo;
					 }
					 else{
						 resultInfo.setSuccess(false);
						 resultInfo.setMsg(resultInfo.getMsg()+"||"+resultInfo2.getMsg());
						 return resultInfo;
					 }
				}
			}
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("计算交易Id：" + tradeInfoId + "单笔奖金失败");
			return resultInfo;
		}
		return resultInfo;
	}

	/** 获取奖金比例 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo getBonusRate(String tradeInfoId, PDProduct pdProduct,String calYear,String calMonth) {
	log.info("******************开始获取交易Id："+tradeInfoId+"的奖金比例***********************");  
	Double bonusRate = null;
	ResultInfo resultInfo=new ResultInfo();
	//查找该交易的财富顾问Id
	TradeInfoExample  tradeInfoExample=new TradeInfoExample();
	tradeInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(new Long(tradeInfoId));
	TradeInfo  tradeInfo=(TradeInfo)tradeInfoMapper.selectByExample(tradeInfoExample).get(0);
	
	/** 财富产品**/
	if (pdProduct.getProductType().equals("1")) {
		/**浮动/股权类-奖金比例 从基本法参数设置 读取 与产品、每月总的认购额有关**/
		if (pdProduct.getProductSubType().equals("01")||pdProduct.getProductSubType().equals("03")) {
		Map paramMap=new HashMap();
		paramMap.put("calDate", (calYear+calMonth).toString());
		paramMap.put("productId", pdProduct.getProductId());
		paramMap.put("agentId", tradeInfo.getAgentId());
		//根据这款产品 当月的总认购费
		BigDecimal totalsubscriptionFeeByMonth=calSalaryServiceMapper.getSubscriptionFeeAMonth(paramMap);
		log.info("该财富顾问："+tradeInfo.getAgentId()+"本年"+(calYear+calMonth).toString()+"年月的"+pdProduct.getProductName()+"产品总认购额为："+totalsubscriptionFeeByMonth);
		if(totalsubscriptionFeeByMonth!=null){
		paramMap.put("totalsubscriptionFeeByMonth", totalsubscriptionFeeByMonth);
		paramMap.put("productId", pdProduct.getProductId());
		//根据该理财经理这款产品当月的总认购额去找相应的奖金比例
		bonusRate=calSalaryServiceMapper.getWealthBonusRate(paramMap).doubleValue();
		log.info("该财富顾问本月的"+pdProduct.getProductName()+"产品的奖金比例为："+bonusRate);
		if(bonusRate!=null){
			resultInfo.setSuccess(true);
			resultInfo.setObj(bonusRate/100);
			return resultInfo;
		}
		else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查找交易Id："+tradeInfoId+"的该浮动/股权产品的奖金比例为空");
			resultInfo.setObj(bonusRate);
			return resultInfo;
		}
		}
	}
		/** 固定收益类-奖金比例：产品期限为1年期(含)以下：奖金比例=0.4%*产品期限（月）/12，产品期限为1年期以上：奖金比例=0.4%+0.35%*（产品期限（月）-12）/12**/
		if (pdProduct.getProductSubType().equals("02")) {
		//固定收益类产品期限为封闭期限(以后可能会有产品的产品期限和受益权类型有关的还需进步处理??????)
		resultInfo=getProductDedline(pdProduct.getProductId().toString());
	    Double productDedline=new Double(resultInfo.getObj().toString());
		if(productDedline!=null){
			if(productDedline>1){
				bonusRate=0.004+0.0035*(productDedline-1);
				resultInfo.setSuccess(true);
				resultInfo.setObj(bonusRate);
				return resultInfo;
			}
			else{
				bonusRate=productDedline*0.004;
				resultInfo.setSuccess(true);
				resultInfo.setObj(bonusRate);
				return resultInfo;
			}
		}
		}
	}
	/**保险产品**/
	if (pdProduct.getProductType().equals("2")) {
		/**个人寿险奖金比例 根据该财富顾问的这个月该产品的标准保费 去找相应的奖金比例**/
		if(pdProduct.getProductSubType().equals("01")){
		Map paramMap=new HashMap();
		paramMap.put("productId", pdProduct.getProductId());
		paramMap.put("agentId", tradeInfo.getAgentId());
		paramMap.put("calYear", calYear);
		paramMap.put("calMonth", calMonth);
		//求出该财富顾问在这个月销售的该产品的总的标准保费
		BigDecimal totalpersonLifeFeeByMonth=new BigDecimal(getTotolStandardPremAmonth(paramMap).toString());
		paramMap.put("totalpersonLifeFeeByMonth", totalpersonLifeFeeByMonth);
		//根据该产品的这个月的标准保费去找相应的区间-奖金比例????????????
		paramMap.put("policyYear", "1");
		BigDecimal getBonusRate=calSalaryServiceMapper.getPersonLifeBonusRate(paramMap);
		if(getBonusRate!=null){
			resultInfo.setSuccess(true);
			resultInfo.setObj(getBonusRate.doubleValue()/100);
			return resultInfo;
		}
		else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查找交易Id："+tradeInfoId+"个人寿险的奖金比例为空");
			resultInfo.setObj(bonusRate);
			return resultInfo;
		}
		}
		/**银行保险奖金比例 根据产品的缴费期限、保单年度 去找相应的奖金比例**/
		if(pdProduct.getProductSubType().equals("03")){
		Map paramMap=new HashMap();
		paramMap.put("productId", pdProduct.getProductId());
		//获取该交易该产品的缴费期限 
		TradeProductInfoExample payPeriodExample=new TradeProductInfoExample();
		payPeriodExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(new Long(tradeInfoId)).andProductIdEqualTo(pdProduct.getProductId()).andParamCodeEqualTo("payPeriod");
		List<TradeProductInfo> payPeriodList=tradeProductInfoMapper.selectByExample(payPeriodExample);
		TradeProductInfoExample payPeriodUnitExample=new TradeProductInfoExample();
		payPeriodUnitExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(new Long(tradeInfoId)).andProductIdEqualTo(pdProduct.getProductId()).andParamCodeEqualTo("payPeriodUnit");
		List<TradeProductInfo> payPeriodUnitList=tradeProductInfoMapper.selectByExample(payPeriodUnitExample);
		if(payPeriodList.size()>0&&payPeriodUnitList.size()>0){
		String payPeriod=payPeriodList.get(0).getParamValue();
		String payPeriodUnit=payPeriodUnitList.get(0).getParamValue();	
		paramMap.put("payPeriod", payPeriod);
		paramMap.put("payPeriodUnit", payPeriodUnit);
		}
		//获取该交易该产品的保单年度(由于现在没有续期产品，目前默认的保单年度为1.如后期有续期功能，此部分需要进行优化-20150416)
		paramMap.put("policyYear", "1")	;
		//根据该产品的缴费期限&保单年度去查找基本法设置里面-奖金比例
		bonusRate=calSalaryServiceMapper.getYBBonusRate(paramMap).doubleValue();
		if(bonusRate!=null){
			resultInfo.setSuccess(true);
			resultInfo.setObj(bonusRate/100);
			return resultInfo;
		}
		else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查找交易Id："+tradeInfoId+"银保产品的奖金比例为空");
			resultInfo.setObj(bonusRate);
			return resultInfo;
		}
		}
	}
		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	/**获取每笔交易的标准保费**/
	public ResultInfo getStandrdPrem(String tradeInfoId, BigDecimal trueMoney,PDProduct pdProduct) {
		ResultInfo resultInfo =new ResultInfo();
		Double standrdPrem = null;
		Double scaleFactor = null;
		Map paramMap = new HashMap();
		paramMap.put("tradeInfoId", tradeInfoId);
		paramMap.put("trueMoney", trueMoney);
		   
		   if (pdProduct.getProductSubType().equals("01")) {
			   List<Map> paramMapList = calSalaryServiceMapper.getScaleFactor(paramMap);//获取折标系数
			   if (paramMapList.size()>0) {
				   scaleFactor = new Double(paramMapList.get(0).get("scaleFactor").toString());
				   standrdPrem = trueMoney.doubleValue()*scaleFactor;// 个人寿险标准保费=实收保费*折标系数
				   resultInfo.setSuccess(true);
				   resultInfo.setObj(standrdPrem);
				   return resultInfo;
			    }
			   else{
				   resultInfo.setSuccess(false);
				   resultInfo.setMsg("计算标准保费,获取交易Id："+tradeInfoId+"的折标系数为空");
				   return resultInfo;
			   }
			}
		  if (pdProduct.getProductSubType().equals("03")) {
					standrdPrem = trueMoney.doubleValue();// 银行保险标准保费=实收保费
					resultInfo.setSuccess(true);
					resultInfo.setObj(standrdPrem);
					return resultInfo;
				}
	 
		return resultInfo;
	}
	
	/**获取这个月某理财经理的标准保费**/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Double getTotolStandardPremAmonth(Map paramMap) {
	Double  totolStandardPrem=0.0;
	String agentId=paramMap.get("agentId").toString();
	String calYear=paramMap.get("calYear").toString();
	String calMonth=paramMap.get("calMonth").toString();
	String productId=paramMap.get("productId").toString();
	Map newParamMap=new HashMap();
	newParamMap.put("agentId", agentId);
	newParamMap.put("statusDate", (calYear+calMonth).toString());
	newParamMap.put("productId", productId);
	
	//获取该理财经理该款产品本月的累计标准保费
	if(agentId!=null&&calYear!=null&&calMonth!=null&&productId!=null){
	//获取该理财经理这个月个人寿险所有已经承保的保单(犹豫撤是否需要加上后，才是累计标准保费???)	
	List<Map> getPflifeFoundStatusList=calSalaryServiceMapper.getPersonLifeFoundList(newParamMap);
	if (getPflifeFoundStatusList.size()>0) {
		for (Map map : getPflifeFoundStatusList) {
			//获取每一笔交易的产品对象
			TradeProductInfoExample tradeProductInfoExample=new TradeProductInfoExample();
			tradeProductInfoExample.createCriteria().andTradeInfoIdEqualTo(new Long(map.get("tradeInfoId").toString())).andRcStateEqualTo("E");
			TradeProductInfo tradeProductInfo=(TradeProductInfo)tradeProductInfoMapper.selectByExample(tradeProductInfoExample).get(0);
			PDProductExample pdProductExample=new PDProductExample();
			pdProductExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(tradeProductInfo.getProductId());
			PDProduct pdProduct=(PDProduct)pdProductMapper.selectByExample(pdProductExample).get(0);
			ResultInfo resultInfo=getStandrdPrem(map.get("tradeInfoId").toString(), new BigDecimal(map.get("actuPremium").toString()), pdProduct);
			if (resultInfo.isSuccess()) {
				Double minStandardPrem=new Double(resultInfo.getObj().toString());
				totolStandardPrem=totolStandardPrem+minStandardPrem;
			}
		   }
	  }
	}
	return totolStandardPrem;
	}
	
	/**获取固定收益类产品的产品期限**/
	@Transactional
	public ResultInfo getProductDedline(String productId) {
		ResultInfo resultInfo=new ResultInfo();
		
		if (productId!=null){
			PDWealthExample pdWealthExample=new PDWealthExample();
			pdWealthExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(new Long(productId));
			PDWealth pdWealth=(PDWealth)pdWealthMapper.selectByExample(pdWealthExample).get(0);
			String colseDPriodUnit= pdWealth.getCloseDperiodUnit();
			if (colseDPriodUnit!=null) {
				if(colseDPriodUnit.equals("Y")){
					Double productDedline=Double.parseDouble(pdWealth.getCloseDperiods());
					resultInfo.setSuccess(true);
					resultInfo.setObj(productDedline);
					return resultInfo;
				}
				if(colseDPriodUnit.equals("M")){
					
					Double productDedline=pdWealth.getCloseDperiod().doubleValue()/12;
					resultInfo.setSuccess(true);
					resultInfo.setObj(productDedline);
					return resultInfo;
				}
				if(colseDPriodUnit.equals("D")){
					
					Double productDedline=pdWealth.getCloseDperiod().doubleValue()/365;
					resultInfo.setSuccess(true);
					resultInfo.setObj(productDedline);
					return resultInfo;
				}
			}
		}
		
		return resultInfo;
		
	}

	/**
	 * 获取某笔单子犹退的奖金
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo getRollBackBonos(TradeInfo tradeInfo,PDProduct pdProduct, Agent agent, TradeStatusInfo tradeStatusInfo) {
		BigDecimal getRollBackBonos=null;
		ResultInfo resultInfo =new ResultInfo();
		//a.找到这笔单子承保的月份
		AgentWageExample agentWageExample=new AgentWageExample();
		agentWageExample.createCriteria().andRcStateEqualTo("E").andTradeinfoIdEqualTo(tradeInfo.getTradeInfoId().toString()).andWageStateEqualTo("2");
		List<AgentWage> agentWageList=agentMapper.selectByExample(agentWageExample);
		if(agentWageList.size()>0){
		AgentWage existAgentWage=(AgentWage)agentWageList.get(0);
		//b.找到这个财富顾问在这个月这款产品承保的标准保费之和
		Map paramMap=new HashMap();
		paramMap.put("calYear", existAgentWage.getCalYear());
		paramMap.put("calMonth", existAgentWage.getCalMonth());
		paramMap.put("agentId", agent.getAgentId());
		paramMap.put("productId", pdProduct.getProductId());
		BigDecimal totolStandardPrem=calSalaryServiceMapper.getTotalStandrdPremFromData(paramMap);
		//计算当时这个月这款产品总的奖金—计算当时这个月(除去这一单)这款产品总的奖金=这个月犹退退回的奖金
		if(pdProduct.getProductType().equals("2")&&pdProduct.getProductSubType().equals("01")){
			Map firstPersonLifeBonusRateMap=new HashMap();
			firstPersonLifeBonusRateMap.put("totalpersonLifeFeeByMonth", totolStandardPrem);
			firstPersonLifeBonusRateMap.put("policyYear", "1");
			//找到当时的奖金比例
			BigDecimal firstbonusRate=calSalaryServiceMapper.getPersonLifeBonusRate(firstPersonLifeBonusRateMap);
			//当时总的奖金额度
			BigDecimal firstTotolBonus=new BigDecimal((totolStandardPrem.doubleValue())*(firstbonusRate.doubleValue()));
			//找到去掉这一单子的奖金比例
			Map secondPersonLifeBonusRateMap=new HashMap();
			secondPersonLifeBonusRateMap.put("totalpersonLifeFeeByMonth", totolStandardPrem.doubleValue()-existAgentWage.getStandardPrem().doubleValue());
			secondPersonLifeBonusRateMap.put("policyYear", "1");
			BigDecimal secondBonusRate=calSalaryServiceMapper.getPersonLifeBonusRate(secondPersonLifeBonusRateMap);
			BigDecimal secondTotolBonus=new BigDecimal((totolStandardPrem.doubleValue()-existAgentWage.getStandardPrem().doubleValue())*secondBonusRate.doubleValue());
			getRollBackBonos=new BigDecimal(firstTotolBonus.doubleValue()-secondTotolBonus.doubleValue());
			resultInfo.setSuccess(true);
			resultInfo.setObj(getRollBackBonos);
			return resultInfo;
		}
		if (pdProduct.getProductType().equals("2")&&pdProduct.getProductSubType().equals("03")) {
			//获取这个产品的奖金比例，这里面只考虑保单年度=1 的情况的 后期续期功能完善后 这部分需要待修改????201504019
			//a.获取银保的奖金比例  和这款产品当时的标准保费
			TradeProductInfoExample tradeProductInfoExample=new TradeProductInfoExample();
			tradeProductInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId()).andProductIdEqualTo(pdProduct.getProductId()).andParamCodeEqualTo("payPeriod");
			List<TradeProductInfo> payPeriodList=tradeProductInfoMapper.selectByExample(tradeProductInfoExample);
			tradeProductInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId()).andProductIdEqualTo(pdProduct.getProductId()).andParamCodeEqualTo("payPeriodUnit");
			List<TradeProductInfo> payPeriodUnitList=tradeProductInfoMapper.selectByExample(tradeProductInfoExample);
			if(payPeriodList.size()>0&&payPeriodUnitList.size()>0){
			String payPeriod=payPeriodUnitList.get(0).getParamValue();
			String payPeriodUnit=payPeriodUnitList.get(0).getParamValue();	
			paramMap.put("payPeriod", payPeriod);
			paramMap.put("payPeriodUnit", payPeriodUnit);
			paramMap.put("policyYear", "1");
			BigDecimal  bonusRate=calSalaryServiceMapper.getYBBonusRate(paramMap);
			getRollBackBonos=new BigDecimal(((existAgentWage.getStandardPrem().doubleValue())*(bonusRate.doubleValue())));
			resultInfo.setSuccess(true);
			resultInfo.setObj(getRollBackBonos);
			return resultInfo;
			 }
			}
		}
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取犹退奖金的单子类型，不再计算范围，产品类型："+pdProduct.getProductType()+";产品子类型："+pdProduct.getProductSubType());
		return resultInfo;
	}

	/**
	 * 获取折标系数
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Transactional
	public ResultInfo getScaleFactor(String tradeInfoId, BigDecimal actulPrem) {
		ResultInfo resultInfo=new ResultInfo();
		if (tradeInfoId!=null&&actulPrem!=null) {
			Map paramMap=new HashMap();
			paramMap.put("tradeInfoId", tradeInfoId);
			paramMap.put("trueMoney", actulPrem);
			  List<Map> paramMapList = calSalaryServiceMapper.getScaleFactor(paramMap);//获取折标系数
			   if (paramMapList.size()>0) {
				 BigDecimal  scaleFactor = new BigDecimal(paramMapList.get(0).get("scaleFactor").toString());
				 if (scaleFactor!=null) {
					 resultInfo.setSuccess(true);
					 resultInfo.setObj(scaleFactor);
					 return resultInfo;
				  }
				 else {
					 resultInfo.setSuccess(false);
					 resultInfo.setMsg("获取交易Id："+tradeInfoId+"的折标系数为空");
					 return resultInfo;
				}
			   }
			   else {
					 resultInfo.setSuccess(false);
					 resultInfo.setMsg("获取交易Id："+tradeInfoId+"的折标系数为空");
					 return resultInfo;
			}
		}
		return resultInfo;
	}
	
/**
 * 获取管理奖金
 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public ResultInfo getsumManageBonus(String agentId,String departMentId ,String CalYear,
			String calmonth) {
		Double sumManageBonus=0.0;
		ResultInfo resultInfo=new ResultInfo();
		if (agentId!=null&&CalYear!=null&&calmonth!=null&&departMentId!=null) {
			//1.查出这个团队长下面这个月的所有有业绩的人AgentId
			Map<String, String> paramMap=new HashMap<String, String>();
			paramMap.put("departmentId", departMentId);
			paramMap.put("calYear", CalYear);
			paramMap.put("calMonth", calmonth);
			List<Map> listAgentIdList=calSalaryServiceMapper.getAgentIdList(paramMap);
			
			if (listAgentIdList.size()>0) {
				for (Map map : listAgentIdList) {
					//1.查询每个agent的相应职级，如果职级不为空，且职级不属于05级别都需要计算相应的管理奖金
					AgentPositionTraceExample agentPositionTraceExample=new AgentPositionTraceExample();
					agentPositionTraceExample.createCriteria().andRcStateEqualTo("E").andAgentIdEqualTo(new Long(map.get("agentId").toString()));
					List<AgentPositionTrace>  agentPositionTraces=agentPositionTraceMapper.selectByExample(agentPositionTraceExample);
					if (agentPositionTraces.size()>0&&!agentPositionTraces.get(0).getPositionCode().equals("05")) {
				    //2.找到这个人这个月所有的业务费
					paramMap.put("agentId", map.get("agentId").toString());
					Double agentWageAmonth=calSalaryServiceMapper.getAgentWageAmonth(paramMap);	
					//3.根据这个人的职级查找相应的管理奖金比例,计算相应的管理奖金；管理奖金=个人总业务费*管理降级比例
					AgentPositionInfoExample agentPositionInfoExample=new AgentPositionInfoExample();
					agentPositionInfoExample.createCriteria().andPositoncodeEqualTo(agentPositionTraces.get(0).getPositionCode()).andGradecodeEqualTo(agentPositionTraces.get(0).getGradeCode());
					BigDecimal manageBonusRate=agentPositionInfoMapper.selectByExample(agentPositionInfoExample).get(0).getManagerate();
					Double mangeBonus=agentWageAmonth*manageBonusRate.doubleValue()/100;
					sumManageBonus=sumManageBonus+mangeBonus;
					}
				}
				resultInfo.setSuccess(true);
				resultInfo.setObj(sumManageBonus);
				
			}
			
		}else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("计算管理奖金的参数为空");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	

/**
 * 获取标准资产
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
public ResultInfo getStandAsset(TradeStatusInfo tradeStatusInfo,PDProduct pdProduct) {
	Double standardAsset=null;
	ResultInfo resultInfo=new ResultInfo();
	
	if(tradeStatusInfo!=null&&pdProduct!=null){
		if(pdProduct.getProductType().equals("1")){
			Map paramMap=new HashMap();
			//获得当前产品的产品期限
			resultInfo=getProductDedline(pdProduct.getProductId().toString());
			if (resultInfo.isSuccess()) {
				Double duration=new Double(resultInfo.getObj().toString());
				paramMap.put("productId",pdProduct.getProductId());
				paramMap.put("duration", duration);
			}
			//根据产品期限找到-久期系数
			BigDecimal durationRate=calSalaryServiceMapper.getWealthDurationRate(paramMap);
			if (durationRate!=null) {
				//财富产品标准资产=认购额*久期系数
				standardAsset=tradeStatusInfo.getActuSubscriptionAmount().doubleValue()*durationRate.doubleValue();
				resultInfo.setSuccess(true);
				resultInfo.setObj(standardAsset);
				return resultInfo;
			}
			else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易ID："+tradeStatusInfo.getTradeInfoId()+"在获取久期系数为空");
				return resultInfo;
			}
		}
		else {
			//保险产品标准资产=实收保费*久期系数
			if(pdProduct.getProductSubType().equals("01")){
				Map paramMap=new HashMap();
				paramMap.put("tradeInfoId", tradeStatusInfo.getTradeInfoId().toString());
				paramMap.put("productId", pdProduct.getProductId().toString());
				List<Map> getPayPeriodList= calSalaryServiceMapper.getPayPeriodList(paramMap);
				if (getPayPeriodList.size()>0) {
					String payPeriod= getPayPeriodList.get(0).get("payPeriod").toString();
					String payPeriodUnit= getPayPeriodList.get(0).get("payPeriodUnit").toString();
					if(payPeriodUnit!=null&&payPeriod!=null){
						BigDecimal durationRate=null;
						//根据相应的缴费期间去查找相应的久期系数
						if (payPeriodUnit.equals("0")||payPeriod.equals("1")) {
							 durationRate=calSalaryServiceMapper.getPersonLifeFirstDurationRate(paramMap);  //查找趸交的久期系数
							 standardAsset=tradeStatusInfo.getActuPremium().doubleValue()*durationRate.doubleValue();	
							 resultInfo.setSuccess(true);
							 resultInfo.setObj(standardAsset);
							 return resultInfo;
						}
						if (payPeriodUnit.equals("Y")) {
							paramMap.put("payPeriod", payPeriod);
							paramMap.put("payPeriodUnit", payPeriodUnit);
							durationRate=calSalaryServiceMapper.getPersonLifeDurationRate(paramMap);   //根据缴费年期去找相应的久期系数
							standardAsset=tradeStatusInfo.getActuPremium().doubleValue()*durationRate.doubleValue();
							resultInfo.setSuccess(true);
							resultInfo.setObj(standardAsset);
							return resultInfo;
						}
						else {
							resultInfo.setSuccess(false);
							resultInfo.setMsg("个人寿险productId："+pdProduct.getProductId()+"的缴费方式，在相应的基本法里面没有进行配置相关的久期系数");
							return resultInfo;	
						}
					}
					else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("获取个人寿险productId："+pdProduct.getProductId()+"的缴费期间信息为空，无法去查找相应的久期系数");
						return resultInfo;
					}
				}
				else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("获取个人寿险productId："+pdProduct.getProductId()+"的缴费期间信息为空，无法去查找相应的久期系数");
					return resultInfo;
				}
			}
			if (pdProduct.getProductSubType().equals("03")) {
				Map paramMap=new HashMap();
				paramMap.put("tradeInfoId", tradeStatusInfo.getTradeInfoId().toString());
				paramMap.put("productId", pdProduct.getProductId().toString());
				List<Map> getPayPeriodList= calSalaryServiceMapper.getPayPeriodList(paramMap);
				if (getPayPeriodList.size()>0) {
					String payPeriod= getPayPeriodList.get(0).get("payPeriod").toString();
					String payPeriodUnit= getPayPeriodList.get(0).get("payPeriodUnit").toString();
					if(payPeriodUnit!=null&&payPeriod!=null){
						BigDecimal durationRate=null;
						//根据相应的缴费期间去查找相应的久期系数
						if (payPeriodUnit.equals("0")||payPeriod.equals("1")) {
							 durationRate=calSalaryServiceMapper.getYBLifeFirstDurationRate(paramMap);  //查找趸交的久期系数
							 standardAsset=tradeStatusInfo.getActuPremium().doubleValue()*durationRate.doubleValue();	
							 resultInfo.setSuccess(true);
							 resultInfo.setObj(standardAsset);
							 return resultInfo;
						}
						if (payPeriodUnit.equals("Y")) {
							paramMap.put("payPeriod", payPeriod);
							paramMap.put("payPeriodUnit", payPeriodUnit);
							durationRate=calSalaryServiceMapper.getYBLifeDurationRate(paramMap);   //根据缴费年期去找相应的久期系数
							if (durationRate!=null) {
								standardAsset=tradeStatusInfo.getActuPremium().doubleValue()*durationRate.doubleValue();
								resultInfo.setSuccess(true);
								resultInfo.setObj(standardAsset);
								return resultInfo;
							}
							else {
								resultInfo.setSuccess(false);
								resultInfo.setMsg("银行保险productId："+pdProduct.getProductId()+"没有查到相关的久期系数值");
								return resultInfo;
							}
					
						}
						else {
							resultInfo.setSuccess(false);
							resultInfo.setMsg("银行保险productId："+pdProduct.getProductId()+"的缴费方式，在相应的基本法里面没有进行配置相关的久期系数");
							return resultInfo;	
						}
					}
					else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("获取银行保险productId："+pdProduct.getProductId()+"的缴费期间信息为空，无法去查找相应的久期系数");
						return resultInfo;
					}
				}
				else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("获取银行productId："+pdProduct.getProductId()+"的缴费期间信息为空，无法去查找相应的久期系数");
					return resultInfo;
				}
			}
		}
		
	}
	return null;
}

/**
 * 获取久期系数
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
public ResultInfo getdurationFactor(String tradeInfoId, String payperiod,String  payperiodUnit) {
	ResultInfo resultInfo=new ResultInfo();
	PDProduct pdProduct=new PDProduct();
	TradeProductInfoExample tradeProductInfoExample=new TradeProductInfoExample();
	tradeProductInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(new Long(tradeInfoId));
	List<TradeProductInfo> tradeProductInfoList=tradeProductInfoMapper.selectByExample(tradeProductInfoExample);
	if (tradeProductInfoList.size()>0) {
		Long productId=tradeProductInfoList.get(0).getProductId();
		PDProductExample pdProductExample=new PDProductExample();
		pdProductExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(productId);
		pdProduct=(PDProduct)pdProductMapper.selectByExample(pdProductExample).get(0);
	}
	Map paramMap=new HashMap();
	//财富产品
	if (pdProduct.getProductType().equals("1")) {
		
		paramMap.put("duration", payperiod);
		paramMap.put("productId", pdProduct.getProductId().toString());
		//根据产品期限找到-久期系数
		BigDecimal durationRate=calSalaryServiceMapper.getWealthDurationRate(paramMap);	
		resultInfo.setSuccess(true);
		resultInfo.setObj(durationRate);
		return resultInfo;
	}
	else {
	//保险产品
		if (pdProduct.getProductSubType().equals("01")) {
			//趸交或者1年期交
			if (payperiodUnit.equals("0")||payperiod.equals("1")) {
				BigDecimal durationRate=calSalaryServiceMapper.getPersonLifeFirstDurationRate(paramMap);
				resultInfo.setSuccess(true);
				resultInfo.setObj(durationRate);
				return resultInfo;
			}
			else {
				paramMap.put("payPeriod", payperiod);
				paramMap.put("payPeriodUnit", payperiodUnit);
				BigDecimal durationRate=calSalaryServiceMapper.getPersonLifeDurationRate(paramMap);
				resultInfo.setSuccess(true);
				resultInfo.setObj(durationRate);
				return resultInfo;
			}
		}
		if (pdProduct.getProductSubType().equals("03")) {
			//趸交或者1年期交
			if (payperiodUnit.equals("0")||payperiod.equals("1")) {
				paramMap.put("productId", pdProduct.getProductId().toString());
				BigDecimal durationRate=calSalaryServiceMapper.getYBLifeFirstDurationRate(paramMap);
				resultInfo.setSuccess(true);
				resultInfo.setObj(durationRate);
				return resultInfo;
			}
			else {
				paramMap.put("productId", pdProduct.getProductId().toString());
				paramMap.put("payPeriod", payperiod);
				paramMap.put("payPeriodUnit", payperiodUnit);
				BigDecimal durationRate=calSalaryServiceMapper.getYBLifeDurationRate(paramMap);
				resultInfo.setSuccess(true);
				resultInfo.setObj(durationRate);
				return resultInfo;
			}
		}
	}
	resultInfo.setSuccess(true);
	resultInfo.setObj("");
	return resultInfo;
}

}
