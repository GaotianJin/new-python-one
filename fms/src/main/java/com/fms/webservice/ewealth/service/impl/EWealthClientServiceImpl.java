package com.fms.webservice.ewealth.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.mapper.AgencyComMapper;
import com.fms.db.mapper.DefFileInfoMapper;
import com.fms.db.mapper.PDAmountOrderInfoMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PDRiskIntroduceInfoMapper;
import com.fms.db.mapper.PDRiskMapper;
import com.fms.db.mapper.PDRiskSalesInfoMapper;
//import com.fms.db.mapper.PDUeditorMapper;
import com.fms.db.mapper.PDWealthFeeRateMapper;
import com.fms.db.mapper.PDWealthIntroduceInfoMapper;
import com.fms.db.mapper.PDWealthMapper;
import com.fms.db.mapper.PDWealthNetValueMapper;
import com.fms.db.mapper.PDWealthOpenDateMapper;
import com.fms.db.mapper.PDWealthSalesInfoMapper;
import com.fms.db.model.AgencyCom;
import com.fms.db.model.AgencyComExample;
import com.fms.db.model.DefFileInfo;
import com.fms.db.model.DefFileInfoExample;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDProductExample;
import com.fms.db.model.PDRisk;
import com.fms.db.model.PDRisk;
import com.fms.db.model.PDRiskExample;
import com.fms.db.model.PDRiskIntroduceInfo;
import com.fms.db.model.PDRiskIntroduceInfo;
import com.fms.db.model.PDRiskIntroduceInfoExample;
import com.fms.db.model.PDRiskSalesInfo;
import com.fms.db.model.PDRiskSalesInfoExample;
//import com.fms.db.model.PDUeditor;
//import com.fms.db.model.PDUeditorExample;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthExample;
import com.fms.db.model.PDWealthFeeRate;
import com.fms.db.model.PDWealthFeeRateExample;
import com.fms.db.model.PDWealthIntroduceInfo;
import com.fms.db.model.PDWealthIntroduceInfoExample;
import com.fms.db.model.PDWealthNetValue;
import com.fms.db.model.PDWealthNetValueExample;
import com.fms.db.model.PDWealthOpenDate;
import com.fms.db.model.PDWealthOpenDateExample;
import com.fms.db.model.PDWealthSalesInfo;
import com.fms.db.model.PDWealthSalesInfoExample;
import com.fms.service.mapper.ProductServiceMapper;
import com.fms.service.product.impl.ProductServiceImpl;
import com.fms.webservice.ewealth.client.ProductDefSynWebservice_ProductDefSynWebservicePort_Client;
import com.fms.webservice.ewealth.service.EWealthClientService;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EWealthClientServiceImpl implements EWealthClientService {

	private static final Logger log = Logger.getLogger(ProductServiceImpl.class);
	@Autowired
	PDAmountOrderInfoMapper pdAmountOrderInfoMapper;
	@Autowired
	private PDProductMapper pdProductMapper;
	@Autowired
	private AgencyComMapper agencyComMapper;
	@Autowired
	private PDWealthIntroduceInfoMapper  PDWealthIntroduceInfoMapper;
	@Autowired
	private PDWealthSalesInfoMapper pdWealthSalesInfoMapper;
	@Autowired
	private PDRiskIntroduceInfoMapper pdRiskIntroduceInfoMapper;
	@Autowired
	private PDRiskSalesInfoMapper pdRiskSalesInfoMapper;
	@Autowired
	private PDWealthFeeRateMapper pdWealthFeeRateMapper;
//	@Autowired
//	private PDUeditorMapper  pdUeditorMapper;
	@Autowired
	private DefFileInfoMapper  defFileInfoMapper;
	@Autowired
	private PDWealthMapper pdWealthMapper;
	@Autowired
	private PDRiskMapper pdRiskMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private PDWealthOpenDateMapper pdWealthOpenDateMapper;
	@Autowired
	private PDWealthNetValueMapper pDWealthNetValueMapper;
	@Autowired
	private ProductServiceMapper productServiceMapper;
	
	
	/**********************************产品相关接口如下***********************************************************************************/
	/**
	 * 将产品信息同步至前端接口
	 * @param pramMap
	 * @return
	 */
	@Transactional
	@SuppressWarnings({ "rawtypes" })
	public ResultInfo ewealthGetProductInfo(Map pramMap) {
		log.info("开始执行将产品信息同步至前端接口");
		ResultInfo resultInfo=new  ResultInfo();
		String businessCode=null;
		if (pramMap.get("productId")!=null&&pramMap.get("productType")!=null&&pramMap.get("productSubType")!=null){
		log.info("开始查询产品相关信息");
		Map<String, Object> reqDataMap = new HashMap<String, Object>();//创建XML传入的数值信息
		Map<String, Object> headData = new HashMap<String, Object>();//报文头信息
		Map<String, Object> contentData = new HashMap<String, Object>();//报文体信息
		List<Map<String, Object>> wealthContentList = new ArrayList<Map<String,Object>>();//财富产品集合
		List<Map<String, Object>> insuranceContentDataList = new ArrayList<Map<String,Object>>();//保险产品集合
		Map<String, Object> wealthContentData = new HashMap<String, Object>();//财富产品信息
		Map<String, Object> riskContentData = new HashMap<String, Object>();//保险产品信息
		Map<String, Object> inputItemData1 = new HashMap<String, Object>();//前端录入参数信息
		Map<String, Object> inputItemData2 = new HashMap<String, Object>();
		Map<String, Object> showItemData1 = new HashMap<String, Object>();//前端显示参数信息
		Map<String, Object> showItemData2 = new HashMap<String, Object>();
		Map<String, Object> showItemData3 = new HashMap<String, Object>();
		Map<String, Object> showItemData4 = new HashMap<String, Object>();
		Map<String, Object> showItemData5 = new HashMap<String, Object>();
		Map<String, Object> showItemData6 = new HashMap<String, Object>();
		Map<String, Object> showItemData7 = new HashMap<String, Object>();
		Map<String, Object> showItemData8 = new HashMap<String, Object>();
		Map<String, Object> showItemData9 = new HashMap<String, Object>();
		Map<String, Object> showItemData10 = new HashMap<String, Object>();
		Map<String, Object> showItemData11 = new HashMap<String, Object>();
		Map<String, Object> showItemData12 = new HashMap<String, Object>();
		Map<String, Object> showItemData13= new HashMap<String, Object>();
		Map<String, Object> showItemData14= new HashMap<String, Object>();
		Map<String, Object> customerItemData1 = new HashMap<String, Object>();//前端客户录入信息
		Map<String, Object> customerItemData2 = new HashMap<String, Object>();
		Map<String, Object> customerItemData3 = new HashMap<String, Object>();
		Map<String, Object> customerItemData4 = new HashMap<String, Object>();
		Map<String, Object> customerItemData5 = new HashMap<String, Object>();
		List<Map<String, Object>> inputItemListData = new ArrayList<Map<String,Object>>();//前端录入参数集合
		List<Map<String, Object>> showItemDataList = new ArrayList<Map<String,Object>>();//前端展示信息参数集合
		List<Map<String, Object>> productFeeRateItemListData = new ArrayList<Map<String,Object>>();//前端录入预期收益率参数集合
		List<Map<String, Object>> customerItemListData = new ArrayList<Map<String,Object>>();//前端录入客户信息参数集合
		List<Map<String, Object>> appendixItemListData = new ArrayList<Map<String,Object>>();//前端显示产品附件信息集合
		String xmlStr=null;//生成的报文XML
		String md5EncodeXmlStr=null;//加密之后的报文
		String returnEwealthProductXML=null;//前端返回的报文
	    Boolean	keyBalance=false;//返回报文校验结果
	    String  secretKey=null;//秘钥
		String productId=pramMap.get("productId").toString();
		String productType=pramMap.get("productType").toString();
		String productSubType=pramMap.get("productSubType").toString();
		PDProduct pdProductExist=new PDProduct();//产品基本信息
		PDWealth  pdWealthExist=new PDWealth();//财富产品主表
		PDRisk  pdRiskExist=new PDRisk();//保险产品主表
		AgencyCom agencyComExist=new AgencyCom();//产品机构信息表
//		PDUeditor  pdUeditorExist=new PDUeditor();//产品富文本框
		PDWealthIntroduceInfo  PDWealthIntroduceInfoExist=new PDWealthIntroduceInfo();//财富产品说明信息
		PDWealthSalesInfo  pdWealthSalesInfoExist=new PDWealthSalesInfo();//财富产品营销信息
		PDRiskIntroduceInfo PDRiskIntroduceInfoExist=new PDRiskIntroduceInfo();//财富产品说明信息
		PDRiskSalesInfo  pdRiskSalesInfoExist=new PDRiskSalesInfo();//财富产品营销信息
		try {
		log.info("开始组装产品信息报文");
		//查询产品基本信息
		PDProductExample pdProductExample=new PDProductExample();
		pdProductExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new Long(productId));
		List<PDProduct>pdProducts= pdProductMapper.selectByExample(pdProductExample);
		if (pdProducts!=null&&pdProducts.size()>0) {
		pdProductExist=pdProducts.get(0);
		}
		//查询产品方信息
		AgencyComExample  agencyComExample=new AgencyComExample();
		agencyComExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andAgencyComIdEqualTo(pdProductExist.getAgencyComId());
		List<AgencyCom> agencyComs= agencyComMapper.selectByExample(agencyComExample);
	    if (agencyComs!=null&&agencyComs.size()>0) {
	    agencyComExist=agencyComs.get(0);
		}
	    //查询网销产品附文本信息
	 /*   PDUeditorExample  pdUeditorExample=new PDUeditorExample();
	    pdUeditorExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new BigDecimal(productId));
	    List<PDUeditor>  pdUeditors= pdUeditorMapper.selectByExample(pdUeditorExample);
	    if (pdUeditors!=null&&pdUeditors.size()>0) {
		pdUeditorExist=pdUeditors.get(0);
		}*/
	    
	    String htmlContextClob= productServiceMapper.getProductEditorContext(pramMap);
	    
	    if (productType.equals("1")) {
	    //查询财富产品信息
	    PDWealthExample pdWealthExample =new PDWealthExample();
	    pdWealthExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new Long(productId) );
	    List<PDWealth> pdWealths= pdWealthMapper.selectByExample(pdWealthExample);
	    if (pdWealths!=null&&pdWealths.size()>0) {
		pdWealthExist=(PDWealth)pdWealths.get(0);
		}
	    //查询财富类产品说明信息
		PDWealthIntroduceInfoExample  PDWealthIntroduceInfoExample=new PDWealthIntroduceInfoExample();
		PDWealthIntroduceInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new Long(productId));
		List<PDWealthIntroduceInfo> PDWealthIntroduceInfos= PDWealthIntroduceInfoMapper.selectByExample(PDWealthIntroduceInfoExample);
		if (PDWealthIntroduceInfos!=null&&PDWealthIntroduceInfos.size()>0) {
		PDWealthIntroduceInfoExist=PDWealthIntroduceInfos.get(0);
		}
		//查询财富类产品营销信息
		PDWealthSalesInfoExample pdWealthSalesInfoExample=new PDWealthSalesInfoExample();
		pdWealthSalesInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new Long(productId));
		List<PDWealthSalesInfo> pdWealthSalesInfos=pdWealthSalesInfoMapper.selectByExample(pdWealthSalesInfoExample);
		String buyType="";
		if (pdWealthSalesInfos!=null&&pdWealthSalesInfos.size()>0) {
		pdWealthSalesInfoExist=pdWealthSalesInfos.get(0);
		if (pdWealthSalesInfoExist.getSaleWay().equals("02")) {buyType="04";}
		else{
		if (pdProductExist.getIsInviteCode()!=null) {
		if (pdProductExist.getIsInviteCode().equals("01")) 
		{buyType="03";}//是否为邀请码购买，如果01 ：邀请码，则buyType=03 邀请码，否则buyType="01"限额购买
		else{buyType="01";}
		}
		else{
		buyType="01";
		}
		}
		}
		contentData.put("tradeWays", pdWealthSalesInfoExist.getSaleWay());//销售方式
		contentData.put("buyType",buyType);//购买方式，不为邀请码的全为限额度
		contentData.put("promotionWays",pdWealthSalesInfoExist.getPromotionWay()!=null?StringUtils.formatcomma(pdWealthSalesInfoExist.getPromotionWay()):null);//推介方式
		contentData.put("payWays",pdWealthSalesInfoExist.getPayWay()!=null? StringUtils.formatcomma(pdWealthSalesInfoExist.getPayWay()):pdWealthSalesInfoExist.getPayWay());//支付方式
		contentData.put("isShow", pdWealthSalesInfoExist.getIsShow());//是否展示
		contentData.put("isShowFirst", pdWealthSalesInfoExist.getIsShowFirst());//是否展示在首页
		contentData.put("saleChnls",pdWealthSalesInfoExist.getSaleChnnel()!=null?StringUtils.formatcomma(pdWealthSalesInfoExist.getSaleChnnel()):pdWealthSalesInfoExist.getSaleChnnel());//销售渠道
		contentData.put("internetProductType", pdWealthSalesInfoExist.getInternetProductType());
		contentData.put("lvchengFlag",  pdWealthSalesInfoExist.getIsExclusive()!=null?(pdWealthSalesInfoExist.getIsExclusive().equals("1")?"Y":"N"):"N");//是否绿城客户专享
		contentData.put("hotSaleFlag", pdWealthSalesInfoExist.getIsHotSale()!=null?(pdWealthSalesInfoExist.getIsHotSale().equals("1")?"Y":"N"):"N");//是否热销产品
		/******存放财富产品信息********/
		wealthContentData.put("saleSumAmount", pdWealthExist.getFinancingScale()!=null?pdWealthExist.getFinancingScale():"0.00");//融资规模
		wealthContentData.put("minTradeAmount", pdWealthExist.getStartInvestMoney()!=null? pdWealthExist.getStartInvestMoney():"0.00");//起投规模
		wealthContentData.put("maxTradeAmount", pdWealthExist.getInvestLimitMoney()!=null?pdWealthExist.getInvestLimitMoney():"0.00");//投资限额
		wealthContentData.put("investIncreaseMoney", pdWealthExist.getInvestIncreaseMoney()!=null?pdWealthExist.getInvestIncreaseMoney():"0.00");//递增金额
		wealthContentData.put("investDays", (pdWealthExist.getCloseDperiods()!=null)&& pdWealthExist.getCloseDperiods().contains("/")?pdWealthExist.getCloseDperiods().replace("/", ","):pdWealthExist.getCloseDperiods());//封闭期间
		wealthContentData.put("investDaysUnit", pdWealthExist.getCloseDperiodUnit());//封闭期间单位 (固定产品)
		wealthContentData.put("closePeriodDesc", PDWealthIntroduceInfoExist.getClosedPeriodDesc());//封闭期说明（浮动产品）
		wealthContentData.put("beneficalTypes",pdWealthExist.getIncomeWay());//收益方式(固定)
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.0000");
		wealthContentData.put("historyEarnRate", pdWealthExist.getHistoryEarnrate()!=null?decimalFormat.format(pdWealthExist.getHistoryEarnrate().doubleValue()/100.00):"0.00");//历史收益率(浮动)
		wealthContentData.put("investStartTime",pdWealthExist.getRaiseStartDate()!=null?DateUtils.format(pdWealthExist.getRaiseStartDate(), null):"");//投资起期
		wealthContentData.put("investEndTime",pdWealthExist.getRaiseEndDate()!=null?DateUtils.format(pdWealthExist.getRaiseEndDate(), null):"");//投资止期
		wealthContentData.put("closeAccountDate", pdWealthExist.getRaiseEndDate()!=null?DateUtils.format(pdWealthExist.getRaiseEndDate(), null):"");//封账日
		wealthContentData.put("foundDate", pdWealthExist.getFoundDate()!=null?DateUtils.format( pdWealthExist.getFoundDate(),null):"");//成立日
		
		if (productSubType.equals("02")) {
		/************存放固定收益类产品预期收益率信息**************/
		PDWealthFeeRateExample pdWealthFeeRateExample=new PDWealthFeeRateExample();
		pdWealthFeeRateExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andPdWealthIdEqualTo(pdWealthExist.getPdWealthId());
		List<PDWealthFeeRate>  pdWealthFeeRates=pdWealthFeeRateMapper.selectByExample(pdWealthFeeRateExample);
		if (pdWealthFeeRates!=null&&pdWealthFeeRates.size()>0) {
		for (PDWealthFeeRate pdWealthFeeRate : pdWealthFeeRates) {
	    Map<String, Object> productFeeRateItemMap = new HashMap<String, Object>();
	    productFeeRateItemMap.put("subscriptionFeeUpper", pdWealthFeeRate.getSubscriptionFeeUpper()!=null? pdWealthFeeRate.getSubscriptionFeeUpper():"9999999999.00");//认购额上限(元)(不包含)
	    productFeeRateItemMap.put("subscriptionFeeLower", pdWealthFeeRate.getSubscriptionFeeLower()!=null?pdWealthFeeRate.getSubscriptionFeeLower():"0.00");//认购额下限
	    productFeeRateItemMap.put("beneficialType", pdWealthFeeRate.getBeneficialType());//受益权类型
	    productFeeRateItemMap.put("closedPeriod", pdWealthFeeRate.getCloseDperiod());//封闭期
	    productFeeRateItemMap.put("closedPeriodUnit", pdWealthFeeRate.getCloseDperiodunit());//封闭期单位
	    productFeeRateItemMap.put("feeRate", pdWealthFeeRate.getFeeRate()!=null?pdWealthFeeRate.getFeeRate():"0.00");//费用率
	    productFeeRateItemMap.put("expectedFeeRate", pdWealthFeeRate.getExpectedFeeRate()!=null?pdWealthFeeRate.getExpectedFeeRate():"0.00");//客户预期收益率
	    productFeeRateItemMap.put("addExpectedFeeRate", pdWealthFeeRate.getAddExpectedFeeRate()!=null?pdWealthFeeRate.getAddExpectedFeeRate():"0.00");//附加预期收益率
	    productFeeRateItemListData.add(productFeeRateItemMap);}}
		 }
		//将保险信息放置为空
		wealthContentList.add(wealthContentData);
		}
	    else
	    {
	    //查询保险产品信息
	    PDRiskExample pdRiskExample=new PDRiskExample();
	    pdRiskExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new Long(productId));
	    List<PDRisk> pdRisks=pdRiskMapper.selectByExample(pdRiskExample);
	    if (pdRisks!=null&&pdRisks.size()>0) {
	    pdRiskExist=(PDRisk)pdRisks.get(0);
		}
	    //查询保险类网销产品说明信息
		PDRiskIntroduceInfoExample  PDRiskIntroduceInfoExample=new PDRiskIntroduceInfoExample();
		PDRiskIntroduceInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new Long(productId));
		List<PDRiskIntroduceInfo> PDRiskIntroduceInfos= pdRiskIntroduceInfoMapper.selectByExample(PDRiskIntroduceInfoExample);
		if (PDRiskIntroduceInfos!=null&&PDRiskIntroduceInfos.size()>0) {
		PDRiskIntroduceInfoExist=PDRiskIntroduceInfos.get(0);
		}
		//查询保险类网销产品营销信息
		PDRiskSalesInfoExample pdRiskSalesInfoExample=new PDRiskSalesInfoExample();
		pdRiskSalesInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new Long(productId));
		List<PDRiskSalesInfo> pdRiskSalesInfos=pdRiskSalesInfoMapper.selectByExample(pdRiskSalesInfoExample);
		if (pdRiskSalesInfos!=null&&pdRiskSalesInfos.size()>0) {
		pdRiskSalesInfoExist=pdRiskSalesInfos.get(0);
		}
		String buyType="01";//默认为01限制额度
		//判断购买方式 如果是销售方式 为 02：预约交易，则buyType=04,
		if (pdRiskSalesInfoExist.getSaleWay().equals("02")) {buyType="04";}
		else{
		//是否为邀请码购买，如果01 ：邀请码，则buyType=03 邀请码，否则buyType="01"限额购买
		if (pdProductExist.getIsInviteCode().equals("01")) {buyType="03";}else{buyType="01";}
		}
		contentData.put("tradeWays", pdRiskSalesInfoExist.getSaleWay());//销售方式
		contentData.put("buyType",buyType);//购买方式，不为邀请码的全为限额度,如果是预约的就为预约
		contentData.put("promotionWays",pdRiskSalesInfoExist.getPromotionWay()!=null?StringUtils.formatcomma(pdRiskSalesInfoExist.getPromotionWay()):pdRiskSalesInfoExist.getPromotionWay());//推介方式
		contentData.put("payWays", pdRiskSalesInfoExist.getPayWay()!=null?StringUtils.formatcomma(pdRiskSalesInfoExist.getPayWay()):pdRiskSalesInfoExist.getPayWay());//支付方式
		contentData.put("isShow", pdRiskSalesInfoExist.getIsShow());//前端是否展示
		contentData.put("isShowFirst", pdRiskSalesInfoExist.getIsShowFirst());//是否展示在首页
		contentData.put("saleChnls",pdRiskSalesInfoExist.getSaleChnnel()!=null?StringUtils.formatcomma(pdRiskSalesInfoExist.getSaleChnnel()):pdRiskSalesInfoExist.getSaleChnnel());//销售渠道
		contentData.put("internetProductType", pdRiskSalesInfoExist.getInternetProductType());
		contentData.put("lvchengFlag", pdRiskSalesInfoExist.getIsExclusive()!=null?(pdRiskSalesInfoExist.getIsExclusive().equals("1")?"Y":"N"):"N");//是否绿城客户专享
		contentData.put("hotSaleFlag", pdRiskSalesInfoExist.getIsHotSale()!=null?(pdRiskSalesInfoExist.getIsHotSale().equals("1")?"Y":"N"):"N");//是否热销产品
		/*******存放保险信息************/
		riskContentData.put("insureYears", null);//保险期间  具体保险期间  999-终身？？？？
		riskContentData.put("insureYearsUnit", null);//保险期间单位?????
		riskContentData.put("mainFlag", pdRiskExist.getPrFlag());//主附险标志
		riskContentData.put("payYears", null);//缴费期间????
		riskContentData.put("payYearsUnit", null);//缴费期间单位???
		riskContentData.put("minAppAge", pdRiskExist.getMinAppAge());//最小投保年龄
		riskContentData.put("minAppAgeUnit", pdRiskExist.getMinAppAgeUnit());//最小投保年龄
		riskContentData.put("maxAppAge", pdRiskExist.getMaxAppAge());//最小投保年龄
		riskContentData.put("maxAppAgeUnit", pdRiskExist.getMaxAppAgeUnit());//最小投保年龄
		riskContentData.put("description", null);//备注信息
		insuranceContentDataList.add(riskContentData);
		}
		headData.put("businessCode", businessCode);//交易业务类型PRO01:新增产品；PRO02-更新产品(必填)
		contentData.put("productCode", pdProductExist.getProductCode());//产品编码
		contentData.put("productName", pdProductExist.getProductName());//产品名称
		contentData.put("productShortName", pdProductExist.getProductShortName());//产品简称
		contentData.put("agencyName", agencyComExist.getAgencyShortName()!=null?agencyComExist.getAgencyShortName():agencyComExist.getAgencyName());//合作机构名称
		contentData.put("agencyCode", agencyComExist.getAgencyCode());//合作机构编码
		contentData.put("productType", pdProductExist.getProductType());//产品类型
		//网销产品类型为:005-保险,则对应的ProductSUbType传值为:501-汽车保险、502-人身意外、503-重疾防癌、504-教育养老、505-财税规划
		if (pdRiskSalesInfoExist.getInternetProductsubtype()!=null&&pdRiskSalesInfoExist.getInternetProductType().equals("005")) {
			contentData.put("productSubType", pdRiskSalesInfoExist.getInternetProductsubtype());//产品子类
		}
		else{
		contentData.put("productSubType", pdProductExist.getProductSubType());//产品子类
		}
		contentData.put("introduceDate", pdProductExist.getIntroduceDate()!=null?DateUtils.format(pdProductExist.getIntroduceDate(), null):"");//产品引入日期
		/*******存放前端录入信息************/
		inputItemData1.put("inputCode", "subscribeMoney");//前端输入认购额
		inputItemData1.put("inputScope", "");
		inputItemData1.put("isMust", "Y");//是否必填
		inputItemData1.put("seq", "1");//显示顺序
		inputItemListData.add(inputItemData1);
		inputItemData2.put("inputCode", "inviteCode");
		inputItemData2.put("inputScope", "");
		inputItemData2.put("isMust", "Y");//是否必填
		inputItemData2.put("seq", "2");//显示顺序
		inputItemListData.add(inputItemData2);
		/*******存放前端客户显示信息，目前默认为以下信息************/
		customerItemData1.put("inputCode", "custName");//客户姓名
		customerItemData1.put("inputScope", "");
		customerItemData1.put("isMust", "Y");
		customerItemData1.put("seq", "1");
		customerItemListData.add(customerItemData1);
		customerItemData2.put("inputCode", "idType");//证件类型
		customerItemData2.put("inputScope", "1");//默认身份证
		customerItemData2.put("isMust", "Y");
		customerItemData2.put("seq", "2");
		customerItemListData.add(customerItemData2);
		customerItemData3.put("inputCode", "idNo");//证件号码
		customerItemData3.put("inputScope", "");
		customerItemData3.put("isMust", "Y");
		customerItemData3.put("seq", "3");
		customerItemListData.add(customerItemData3);
		customerItemData4.put("inputCode", "idValidityDate");//证件有效期
		customerItemData4.put("inputScope", "");
		customerItemData4.put("isMust", "Y");
		customerItemData4.put("seq", "4");
		customerItemListData.add(customerItemData4);
		customerItemData5.put("inputCode", "address");//地址
		customerItemData5.put("inputScope", "");
		customerItemData5.put("isMust", "Y");
		customerItemData5.put("seq", "5");
		customerItemListData.add(customerItemData5);
		/**************存放前端产品附文本信息******************/
		contentData.put("productInfo", htmlContextClob!=null?"<![CDATA["+htmlContextClob.trim()+"]]>".toString():"");
		/**************产品附件信息显示******************/
		DefFileInfoExample  defFileInfoExample=new DefFileInfoExample();
		defFileInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andBusinessNoEqualTo(new Long(productId)).andBusinessTypeEqualTo("01").andBusinessSubTypeNotEqualTo("104");
		List<DefFileInfo> defFileInfos=defFileInfoMapper.selectByExample(defFileInfoExample);
		if (defFileInfos!=null&&defFileInfos.size()>0) {
		for (DefFileInfo defFileInfo : defFileInfos) {
		Map<String, Object> appendixItemMap = new HashMap<String, Object>();
		
		appendixItemMap.put("appendixName",defFileInfo.getUploadFileName()!=null?StringUtils.getFileName(defFileInfo.getUploadFileName()):"");//附件名称
		appendixItemMap.put("appendixType", defFileInfo.getBusinessSubType());//附件类型
		//生产
//		appendixItemMap.put("appendixUrl", defFileInfo.getFileSavePath()!=null?defFileInfo.getFileSavePath().substring(9, defFileInfo.getFileSavePath().length())+defFileInfo.getFileName():defFileInfo.getFileSavePath());//附件存放地址
        //测试
		appendixItemMap.put("appendixUrl", defFileInfo.getFileSavePath()!=null?defFileInfo.getFileSavePath().substring(4, defFileInfo.getFileSavePath().length())+defFileInfo.getFileName():defFileInfo.getFileSavePath());//附件存放地址

		appendixItemMap.put("description", defFileInfo.getFileDescribe());//附件描述
		log.info(defFileInfo.getFileSavePath().substring(4, defFileInfo.getFileSavePath().length()));
		
		
		log.info(defFileInfo.getFileSavePath());
		appendixItemListData.add(appendixItemMap);
		}
		}
		/**************前台展示信息 ******************/
		if (productType.equals("1")) {
		log.info("财富类网销产品说明信息");
		showItemData1.put("itemName", "productTitle");//产品标题
		showItemData1.put("itemValue", PDWealthIntroduceInfoExist.getProductTitle());
		showItemData1.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData1.put("seq", "1");
		showItemData1.put("description","");
		showItemDataList.add(showItemData1);
		showItemData2.put("itemName", "productKeyword");//产品关键字
		showItemData2.put("itemValue", PDWealthIntroduceInfoExist.getProductKeyword());
		showItemData2.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData2.put("seq", "2");
		showItemData2.put("description","");
		showItemDataList.add(showItemData2);
		showItemData3.put("itemName", "productDesc");//产品描述
		showItemData3.put("itemValue", PDWealthIntroduceInfoExist.getProductDesc());
		showItemData3.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData3.put("seq", "3");
		showItemData3.put("description","");
		showItemDataList.add(showItemData3);
		showItemData4.put("itemName", "productFeatures");//产品特色
		showItemData4.put("itemValue", PDWealthIntroduceInfoExist.getProductFeatures());
		showItemData4.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData4.put("seq", "3");
		showItemData4.put("description","");
		showItemDataList.add(showItemData4);
		showItemData6.put("itemName", "productCostDesc");//产品费用
		showItemData6.put("itemValue", PDWealthIntroduceInfoExist.getProductCostDesc());
		showItemData6.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData6.put("seq", "3");
		showItemData6.put("description","");
		showItemDataList.add(showItemData6);
		showItemData7.put("itemName", "fundSusingDesc");//投资方向
		showItemData7.put("itemValue", PDWealthIntroduceInfoExist.getFundSusingDesc());
		showItemData7.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData7.put("seq", "3");
		showItemData7.put("description","");
		showItemDataList.add(showItemData7);
		showItemData12.put("itemName", "warmWarnDesc");//温馨提示
		showItemData12.put("itemValue", PDWealthIntroduceInfoExist.getWarmWarnDesc());
		showItemData12.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData12.put("seq", "3");
		showItemData12.put("description","");
		showItemDataList.add(showItemData12);
		showItemData14.put("itemName", "preferentialDesc");//优惠信息
		showItemData14.put("itemValue", PDWealthIntroduceInfoExist.getPreferentialDesc());
		showItemData14.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData14.put("seq", "3");
		showItemData14.put("description","");
		showItemDataList.add(showItemData14);
		
		if (productSubType.equals("02")) {
		//1\2\3\4\6\7\12\14
		/************************固定类产品，网销说明信息**************************/
		showItemData8.put("itemName", "addPromotionMeasures");//增信措施
		showItemData8.put("itemValue", PDWealthIntroduceInfoExist.getAddPromotionMeasures());
		showItemData8.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData8.put("seq", "3");
		showItemData8.put("description","");
		showItemDataList.add(showItemData8);
		showItemData9.put("itemName", "historyDesc");//固定收益说明
		showItemData9.put("itemValue", PDWealthIntroduceInfoExist.getHistoryEarnRateDesc());
		showItemData9.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData9.put("seq", "3");
		showItemData9.put("description","");
		showItemDataList.add(showItemData9);
		}
		else
		{
		//1\2\3\4\6\7\12\14
		/************************浮动类产品，网销说明信息**************************/
		showItemData13.put("itemName", "historyEarnRateDesc");//历史收益区间 显示进一个月，一年
		showItemData13.put("itemValue", pdWealthExist.getHistoryEarnratePeriod());
		showItemData13.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData13.put("seq", "3");
		showItemData13.put("description","");
		showItemDataList.add(showItemData13);
		showItemData8.put("itemName", "historyDesc");//收益说明
		showItemData8.put("itemValue", PDWealthIntroduceInfoExist.getHistoryEarnRateDesc());
		showItemData8.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData8.put("seq", "3");
		showItemData8.put("description","");
		showItemDataList.add(showItemData8);
		showItemData9.put("itemName", "investAgentName");//投顾介绍
		showItemData9.put("itemValue", PDWealthIntroduceInfoExist.getAgentName());
		showItemData9.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData9.put("seq", "3");
		showItemData9.put("description","");
		showItemDataList.add(showItemData9);
		showItemData10.put("itemName", "agncyComName");//管理机构机构
		showItemData10.put("itemValue", PDWealthIntroduceInfoExist.getAgncyComName());
		showItemData10.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData10.put("seq", "3");
		showItemData10.put("description","");
		showItemDataList.add(showItemData10);	
		showItemData11.put("itemName", "closedPeriodDesc");//封闭期说明
		showItemData11.put("itemValue", PDWealthIntroduceInfoExist.getClosedPeriodDesc());
		showItemData11.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData11.put("seq", "3");
		showItemData11.put("description","");
		showItemDataList.add(showItemData11);
		showItemData5.put("itemName", "opendayDesc");//开放日说明
		showItemData5.put("itemValue", PDWealthIntroduceInfoExist.getOpendayDesc());
		showItemData5.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData5.put("seq", "3");
		showItemData5.put("description","");
		showItemDataList.add(showItemData5);	
		}
		}
		else
		{
		log.info("保险类网销产品说明信息");
		showItemData1.put("itemName", "productTitle");//产品标题
		showItemData1.put("itemValue", PDRiskIntroduceInfoExist.getProductTitle());
		showItemData1.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData1.put("seq", "1");
		showItemData1.put("description","");
		showItemDataList.add(showItemData1);
		showItemData2.put("itemName", "productKeyword");//产品关键字
		showItemData2.put("itemValue", PDRiskIntroduceInfoExist.getProductKeyword());
		showItemData2.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData2.put("seq", "1");
		showItemData2.put("description","");
		showItemDataList.add(showItemData2);
		showItemData3.put("itemName", "productDesc");//产品描述
		showItemData3.put("itemValue", PDRiskIntroduceInfoExist.getProductDescribe());
		showItemData3.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData3.put("seq", "1");
		showItemData3.put("description","");
		showItemDataList.add(showItemData3);
		showItemData4.put("itemName", "productFeatures");//产品特色
		showItemData4.put("itemValue", PDRiskIntroduceInfoExist.getProductFeatures());
		showItemData4.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData4.put("seq", "1");
		showItemData4.put("description","");
		showItemDataList.add(showItemData4);
		showItemData5.put("itemName", "insuranceInformation");//投保须知
		showItemData5.put("itemValue", PDRiskIntroduceInfoExist.getInsuranceInformation());
		showItemData5.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData5.put("seq", "1");
		showItemData5.put("description","");
		showItemDataList.add(showItemData5);
		showItemData6.put("itemName", "claimsIntrouction");//理赔说明
		showItemData6.put("itemValue", PDRiskIntroduceInfoExist.getClaimsIntrouction());
		showItemData6.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData6.put("seq", "1");
		showItemData6.put("description","");
		showItemDataList.add(showItemData6);
		showItemData7.put("itemName", "insuredPeriodDesc");//保险期间说明
		showItemData7.put("itemValue", PDRiskIntroduceInfoExist.getInsuredPeriodDesc());
		showItemData7.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData7.put("seq", "1");
		showItemData7.put("description","");
		showItemDataList.add(showItemData7);
		showItemData8.put("itemName", "warmWarnDesc");//温馨提示
		showItemData8.put("itemValue", PDRiskIntroduceInfoExist.getWarmWarnDesc());
		showItemData8.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData8.put("seq", "3");
		showItemData8.put("description","");
		showItemDataList.add(showItemData8);
		showItemData9.put("itemName", "preferentialDesc");//优惠信息
		showItemData9.put("itemValue", PDRiskIntroduceInfoExist.getPreferentialDesc());
		showItemData9.put("showAreaType","00");//01:列表区,02：要素区, 03:录入区, 04:详情区
		showItemData9.put("seq", "3");
		showItemData9.put("description","");
		showItemDataList.add(showItemData9);
		
		}
		log.info("开始将处理完的Map放在，集合里面");
		contentData.put("wealthContentList", wealthContentList);
		contentData.put("insuranceContentDataList", insuranceContentDataList);
		contentData.put("inputItemListData",inputItemListData);
		contentData.put("showItemDataList",showItemDataList);
		contentData.put("productFeeRateItemListData",productFeeRateItemListData);
		contentData.put("customerItemListData",customerItemListData);
		contentData.put("appendixItemListData",appendixItemListData);
		reqDataMap.put("headData", headData);//存放报文头
		reqDataMap.put("contentData", contentData);//存放报文内容
//		String uploadDir=pramMap.get("uploadDir").toString();
		xmlStr = com.sinosoft.util.XmlUtils.createXml("ewealth_fmtdef.xml", "eWealthGetProductInfo", reqDataMap);//生成后报文
//		xmlStr = com.sinosoft.util.XmlUtils.createXml("ewealth_fmtdef.xml", "eWealthGetProductInfo", reqDataMap);//生成后报文
		log.info(xmlStr);
		log.info("+++++++++++++++++++++++++++");
		} catch (Exception e) {
	    e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("组装产品信息报文失败");
		resultInfo.setObj(e.getMessage());
		return resultInfo;	
		}
		try {
		log.info("开始报文加密");	
		DefCodeExample  defCodeExample=new DefCodeExample();
		defCodeExample.createCriteria().andCodeTypeEqualTo("secretKey").andCodeAliasEqualTo("1");
		List<DefCode> defCodes= defCodeMapper.selectByExample(defCodeExample);
		if (defCodes!=null&&defCodes.size()>0) {
		secretKey=defCodes.get(0).getCode().toString();
		md5EncodeXmlStr =com.sinosoft.util.XmlUtils.md5EncodeEwealthXml(secretKey,xmlStr);//加密之后的报文
		log.info("md5EncodeXmlStr"+md5EncodeXmlStr);
		}
		else
		{
		resultInfo.setSuccess(false);
		resultInfo.setObj(xmlStr);
		resultInfo.setMsg("获取加密秘钥失败，无法进行加密");
		return  resultInfo;
		}
		} catch (Exception e) {
	    e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setObj(xmlStr+e.getMessage());
		resultInfo.setMsg("报文加密失败");
		return resultInfo;		
		}
		try {
		log.info("调用WSDL服务端方法");	
		ProductDefSynWebservice_ProductDefSynWebservicePort_Client productClient=new ProductDefSynWebservice_ProductDefSynWebservicePort_Client();
		returnEwealthProductXML= productClient.invokeServerProductInfo(md5EncodeXmlStr);
		log.info("调用前端产品接口后返回的加密信息："+returnEwealthProductXML);
		} catch (Exception e) {
	    e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("调用WSDL服务端方法出现异常");
		resultInfo.setObj(returnEwealthProductXML);
		return resultInfo;		
		}
		try {
		keyBalance =com.sinosoft.util.XmlUtils.checkEwealthXml(secretKey, returnEwealthProductXML);
		if (keyBalance) {
		Map<String,Object> xmlDataMap=new HashMap<String,Object>();
		com.sinosoft.util.XmlUtils.parseXmlToMap(returnEwealthProductXML, xmlDataMap);
		Map xmleWealthContentMap = (Map)xmlDataMap.get("eWealthContent");
		String resultCode = xmleWealthContentMap.get("resultCode").toString();
		String resultDecription=xmleWealthContentMap.get("resultDecription").toString();
        if (resultCode.equals("01")) {
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品同步信息成功");
		resultInfo.setObj(returnEwealthProductXML);
        return resultInfo;
		}
        else{
        resultInfo.setSuccess(false);
        resultInfo.setMsg("产品同步返回结果失败，原因是："+resultDecription);
        resultInfo.setObj(returnEwealthProductXML);
        return resultInfo;
        }
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("接收返回报文校验不一致");
		resultInfo.setObj(returnEwealthProductXML);
		return resultInfo;		
		}
		} catch (Exception e) {
	    e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("解析前端返回结果校验出现异常");
		resultInfo.setObj(returnEwealthProductXML.toString()+e.getMessage());
		return resultInfo;		
		}
		}
		else{
		log.info("productId:"+pramMap.get(""));
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品信息为空，产品信息同步失败");
		return resultInfo;
		}
	}

	/**
	 * 将产品净值信息同步至前端
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo ewealthGetNetValueInfo(Map paramMap) {
		Map<String, Object> reqDataMap = new HashMap<String, Object>();//创建XML传入的数值信息
		Map<String, Object> headData = new HashMap<String, Object>();//报文头信息
		Map<String, Object> contentData = new HashMap<String, Object>();//报文体信息
		ResultInfo  resultInfo=new ResultInfo();
		String productId=null;
		PDProduct pdProductExsit=null;
		PDWealth pdWealthExist=null;
		String xmlStr=null;//生成的报文XML
		String md5EncodeXmlStr=null;//加密之后的报文
		String returnEwealthProductXML=null;//前端返回的报文
		Boolean	keyBalance=false;//返回报文校验结果
		String  secretKey=null;//秘钥
		List<Map<String, Object>> netValueItemDataList = new ArrayList<Map<String,Object>>();//前端录入预期收益率参数集合
		if (paramMap.get("productId")!=null) {
		productId=paramMap.get("productId").toString();
		PDProductExample pdProductExample=new PDProductExample();
		pdProductExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new Long(productId));
		List<PDProduct> pdProducts= pdProductMapper.selectByExample(pdProductExample);
		if(pdProducts!=null&&pdProducts.size()>0){
		pdProductExsit=pdProducts.get(0);
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品信息为空，无法进行报文组装");
		return resultInfo;	
		}
		PDWealthExample pdWealthExample=new PDWealthExample();
		pdWealthExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(pdProductExsit.getProductId());
		List<PDWealth> pdWealths=pdWealthMapper.selectByExample(pdWealthExample);
		if (pdWealths!=null&&pdWealths.size()>0) {
		pdWealthExist=pdWealths.get(0);
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取财富产品信息为空，无法进行报文组装");
		return resultInfo;
		}
		
		PDWealthNetValueExample pdWealthNetValueExample=new PDWealthNetValueExample();
		pdWealthNetValueExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andPdWealthIdEqualTo(pdWealthExist.getPdWealthId());
		List<PDWealthNetValue> pdWealthNetValues=pDWealthNetValueMapper.selectByExample(pdWealthNetValueExample);
		if (pdWealthNetValues!=null&&pdWealthNetValues.size()>0) {
//		BigDecimal bd=new BigDecimal(val);
		for (PDWealthNetValue pdWealthNetValue : pdWealthNetValues) {
			 Map<String, Object> netValueItem = new HashMap<String, Object>();
			 netValueItem.put("netType", pdWealthNetValue.getNetType());//01-费前 02-费后
			 netValueItem.put("netValue", pdWealthNetValue.getNetValue().setScale(4, BigDecimal.ROUND_HALF_UP));//净值
			 netValueItem.put("publicDate", pdWealthNetValue.getPublicDate()!=null?DateUtils.format(pdWealthNetValue.getPublicDate(), null):null);//公布日期
			 netValueItemDataList.add(netValueItem);
		}
		}
		else
		{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("没有获取相关开放日，无法同步信息");
		return resultInfo;
		}
		/*********************调用方法创建报文*********************/
		try {
		contentData.put("productCode", pdProductExsit.getProductCode());//产品编码
		contentData.put("productName", pdProductExsit.getProductName());//产品名称	
		contentData.put("netValueInfoList", netValueItemDataList);//报文体内容
		reqDataMap.put("headData", headData);//存放报文头
		reqDataMap.put("contentData", contentData);//存放报文内容
		//	String uploadDir=pramMap.get("uploadDir").toString();
		//	xmlStr = com.sinosoft.util.XmlUtils.createXmlStr(uploadDir,"ewealth_fmtdef.xml", "eWealthGetProductInfo", reqDataMap);//生成后报文
		xmlStr = com.sinosoft.util.XmlUtils.createXml("ewealth_fmtdef.xml", "eWealthGetNetValueInfo", reqDataMap);//生成后报文
		log.info(xmlStr);
		log.info("+++++++++++++++++++++++++++");
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("调用createXMl组织报文异常");
		resultInfo.setObj(e.getMessage());
		return resultInfo;
		}
		/***********************报文加密*************************/
		try {
		log.info("开始报文加密");
		DefCodeExample  defCodeExample=new DefCodeExample();
		defCodeExample.createCriteria().andCodeTypeEqualTo("secretKey").andCodeAliasEqualTo("1");
		List<DefCode> defCodes= defCodeMapper.selectByExample(defCodeExample);
		if (defCodes!=null&&defCodes.size()>0) {
		secretKey=defCodes.get(0).getCode().toString();
		md5EncodeXmlStr =com.sinosoft.util.XmlUtils.md5EncodeEwealthXml(secretKey,xmlStr);//加密之后的报文
		log.info("md5EncodeXmlStr"+md5EncodeXmlStr);
		}
		else
		{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("报文加密,获取秘钥失败");
		return resultInfo;	
		}
		} catch (Exception e) {
		 e.printStackTrace();	
		resultInfo.setSuccess(false);
		resultInfo.setMsg("报文加密失败");
		resultInfo.setObj(e.getMessage());
		return resultInfo;
		}
	/***********************调用WSDL服务端方法*************************/
	try {
		log.info("调用WSDL服务端方法");	
		ProductDefSynWebservice_ProductDefSynWebservicePort_Client productClient=new ProductDefSynWebservice_ProductDefSynWebservicePort_Client();
		returnEwealthProductXML= productClient.invokeServerProductNetValueInfo(md5EncodeXmlStr);
		log.info("调用前端产品接口后返回的加密信息："+returnEwealthProductXML);
		} catch (Exception e) {
	    e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("调用WSDL服务端方法出现异常");
		resultInfo.setObj(returnEwealthProductXML);
		return resultInfo;		
		}
		try {
		keyBalance =com.sinosoft.util.XmlUtils.checkEwealthXml(secretKey, returnEwealthProductXML);
		if (keyBalance) {
		Map<String,Object> xmlDataMap=new HashMap<String,Object>();
		com.sinosoft.util.XmlUtils.parseXmlToMap(returnEwealthProductXML, xmlDataMap);
		Map xmleWealthContentMap = (Map)xmlDataMap.get("eWealthContent");
		String resultCode = xmleWealthContentMap.get("resultCode").toString();
		String resultDecription=xmleWealthContentMap.get("resultDecription").toString();
        if (resultCode.equals("01")) {
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品净值信息同步互联网成功");
		resultInfo.setObj(returnEwealthProductXML);
        return resultInfo;
		}
        if (resultCode.equals("02")) {
        resultInfo.setSuccess(false);
        resultInfo.setMsg("产品净值信息同步互联网返回结果失败，原因是："+resultDecription);
        resultInfo.setObj(returnEwealthProductXML);
        return resultInfo;
		}
        else{
        resultInfo.setSuccess(false);
        resultInfo.setMsg("该产品在互联网端不存在净值信息无需同步至互联网");
        resultInfo.setObj(returnEwealthProductXML);
        return resultInfo;
        }
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("接收返回报文校验不一致");
		resultInfo.setObj(returnEwealthProductXML);
		return resultInfo;
		}
		} catch (Exception e){
	    e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("解析前端返回结果校验出现异常");
		resultInfo.setObj(returnEwealthProductXML.toString()+e.getMessage());
		return resultInfo;		
		}
	}
	else{
	resultInfo.setSuccess(false);
	resultInfo.setMsg("获取产品流水号为空，无法进行报文组装");
    return resultInfo;
	}
	}

	/**
	 *  将产品开放日信息同步至前端
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo ewealthGetOpenyDayInfo(Map paramMap) {
		Map<String, Object> reqDataMap = new HashMap<String, Object>();//创建XML传入的数值信息
		Map<String, Object> headData = new HashMap<String, Object>();//报文头信息
		Map<String, Object> contentData = new HashMap<String, Object>();//报文体信息
		ResultInfo  resultInfo=new ResultInfo();
		String productId=null;
		PDProduct pdProductExsit=null;
		PDWealth pdWealthExist=null;
		String xmlStr=null;//生成的报文XML
		String md5EncodeXmlStr=null;//加密之后的报文
		String returnEwealthProductXML=null;//前端返回的报文
		Boolean	keyBalance=false;//返回报文校验结果
		String  secretKey=null;//秘钥
		List<Map<String, Object>> openDayItemDataList = new ArrayList<Map<String,Object>>();//前端录入预期收益率参数集合
		if (paramMap.get("productId")!=null) {
		productId=paramMap.get("productId").toString();
		PDProductExample pdProductExample=new PDProductExample();
		pdProductExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(new Long(productId));
		List<PDProduct> pdProducts= pdProductMapper.selectByExample(pdProductExample);
		if(pdProducts!=null&&pdProducts.size()>0){
		pdProductExsit=pdProducts.get(0);
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品信息为空，无法进行报文组装");
		return resultInfo;	
		}
		PDWealthExample pdWealthExample=new PDWealthExample();
		pdWealthExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(pdProductExsit.getProductId());
		List<PDWealth> pdWealths=pdWealthMapper.selectByExample(pdWealthExample);
		if (pdWealths!=null&&pdWealths.size()>0) {
		pdWealthExist=pdWealths.get(0);
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取财富产品信息为空，无法进行报文组装");
		return resultInfo;
		}
		PDWealthOpenDateExample pdWealthOpenDateExample=new  PDWealthOpenDateExample();
		pdWealthOpenDateExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andPdWealthIdEqualTo(pdWealthExist.getPdWealthId());
		List<PDWealthOpenDate>pdWealthOpenDates= pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample);
		if (pdWealthOpenDates!=null&&pdWealthOpenDates.size()>0) {
		for (PDWealthOpenDate pdWealthOpenDate : pdWealthOpenDates) {
			 Map<String, Object> openDayItem = new HashMap<String, Object>();
			 openDayItem.put("openDay", pdWealthOpenDate.getOpenDate()!=null?DateUtils.format(pdWealthOpenDate.getOpenDate(), null):null);
			 openDayItemDataList.add(openDayItem);
		}
		}
		else
		{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("没有获取相关开放日，无法同步信息");
		return resultInfo;
		}
		/*********************调用方法创建报文*********************/
		try {
		contentData.put("productCode", pdProductExsit.getProductCode());
		contentData.put("openDayItemDataList", openDayItemDataList);//报文体内容
		reqDataMap.put("headData", headData);//存放报文头
		reqDataMap.put("contentData", contentData);//存放报文内容
		xmlStr = com.sinosoft.util.XmlUtils.createXml("ewealth_fmtdef.xml", "eWealthGetOpenDayInfo", reqDataMap);//生成后报文
		log.info(xmlStr);
		log.info("+++++++++++++++++++++++++++");
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("调用createXMl组织报文异常");
		resultInfo.setObj(e.getMessage());
		return resultInfo;
		}
		/***********************报文加密*************************/
		try {
		log.info("开始报文加密");
		DefCodeExample  defCodeExample=new DefCodeExample();
		defCodeExample.createCriteria().andCodeTypeEqualTo("secretKey").andCodeAliasEqualTo("1");
		List<DefCode> defCodes= defCodeMapper.selectByExample(defCodeExample);
		if (defCodes!=null&&defCodes.size()>0) {
		secretKey=defCodes.get(0).getCode().toString();
		md5EncodeXmlStr =com.sinosoft.util.XmlUtils.md5EncodeEwealthXml(secretKey,xmlStr);//加密之后的报文
		log.info("md5EncodeXmlStr"+md5EncodeXmlStr);
		}
		else
		{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("报文加密,获取秘钥失败");
		return resultInfo;	
		}
		} catch (Exception e) {
		 e.printStackTrace();	
		resultInfo.setSuccess(false);
		resultInfo.setMsg("报文加密失败");
		resultInfo.setObj(e.getMessage());
		return resultInfo;
		}
	/***********************调用WSDL服务端方法*************************/
	try {
		log.info("调用WSDL服务端方法处理开放日信息");	
		ProductDefSynWebservice_ProductDefSynWebservicePort_Client productClient=new ProductDefSynWebservice_ProductDefSynWebservicePort_Client();
		returnEwealthProductXML= productClient.invokeServerProductOpenDateInfo(md5EncodeXmlStr);
		log.info("调用前端产品接口后返回的加密信息："+returnEwealthProductXML);
		} catch (Exception e) {
	    e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("调用WSDL服务端方法出现异常");
		resultInfo.setObj(returnEwealthProductXML);
		return resultInfo;		
		}
		try {
		keyBalance =com.sinosoft.util.XmlUtils.checkEwealthXml(secretKey, returnEwealthProductXML);
		if (keyBalance) {
		Map<String,Object> xmlDataMap=new HashMap<String,Object>();
		com.sinosoft.util.XmlUtils.parseXmlToMap(returnEwealthProductXML, xmlDataMap);
		Map xmleWealthContentMap = (Map)xmlDataMap.get("eWealthContent");
		String resultCode = xmleWealthContentMap.get("resultCode").toString();
		String resultDecription=xmleWealthContentMap.get("resultDecription").toString();
        if (resultCode.equals("01")) {
		resultInfo.setSuccess(true);
		resultInfo.setMsg("开放日信息同步成功");
		resultInfo.setObj(returnEwealthProductXML);
        return resultInfo;
		}
       if(resultCode.equals("02")){
            resultInfo.setSuccess(false);
            resultInfo.setMsg("产品同步返回结果失败，原因是："+resultDecription);
            resultInfo.setObj(returnEwealthProductXML);
            return resultInfo;
        }
        else{
        resultInfo.setSuccess(false);
        resultInfo.setMsg("开放日信息同步失败，原因是该产品在互联网端不存在");
        resultInfo.setObj(returnEwealthProductXML);
        return resultInfo;
        }
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("接收返回报文校验不一致");
		resultInfo.setObj(returnEwealthProductXML);
		return resultInfo;
		}
		} catch (Exception e) {
	    e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("解析前端返回结果校验出现异常");
		resultInfo.setObj(returnEwealthProductXML.toString()+e.getMessage());
		return resultInfo;		
		}
	
	
	}
	else{
	resultInfo.setSuccess(false);
	resultInfo.setMsg("获取产品流水号为空，无法进行报文组装");
    return resultInfo;
	}
	}



	

}
