package com.fms.service.product.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.groovy.reflection.handlegen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.DefFileInfoMapper;
import com.fms.db.mapper.HolidaysMapper;
import com.fms.db.mapper.PDAmountOrderInfoMapper;
import com.fms.db.mapper.PDContractDetailMapper;
import com.fms.db.mapper.PDContractMapper;
import com.fms.db.mapper.PDFactorMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PDRiskFeeRateMapper;
import com.fms.db.mapper.PDRiskIntroduceInfoMapper;
import com.fms.db.mapper.PDRiskMapper;
import com.fms.db.mapper.PDRiskSalesInfoMapper;
import com.fms.db.mapper.PDUeditorMapper;
import com.fms.db.mapper.PDWealthChargeRateMapper;
import com.fms.db.mapper.PDWealthFeeRateMapper;
import com.fms.db.mapper.PDWealthIntroduceInfoMapper;
import com.fms.db.mapper.PDWealthMapper;
import com.fms.db.mapper.PDWealthNetValueMapper;
import com.fms.db.mapper.PDWealthNetValueTaskMapper;
import com.fms.db.mapper.PDWealthOpenDateMapper;
import com.fms.db.mapper.PDWealthSalesInfoMapper;
import com.fms.db.mapper.PDWealthStockDisMapper;
import com.fms.db.mapper.PDwealthIncomeDisMapper;
import com.fms.db.mapper.PdAmountDisInfoMapper;
import com.fms.db.mapper.SysEmailAdressMapper;
import com.fms.db.mapper.SysEmailInfoMapper;
import com.fms.db.mapper.WebServiceLogInfoMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.DefFileInfo;
import com.fms.db.model.DefFileInfoExample;
import com.fms.db.model.Holidays;
import com.fms.db.model.HolidaysExample;
import com.fms.db.model.PDAmountOrderInfo;
import com.fms.db.model.PDAmountOrderInfoExample;
import com.fms.db.model.PDContract;
import com.fms.db.model.PDContractDetail;
import com.fms.db.model.PDContractDetailExample;
import com.fms.db.model.PDContractExample;
import com.fms.db.model.PDFactor;
import com.fms.db.model.PDFactorExample;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDProductExample;
import com.fms.db.model.PDRisk;
import com.fms.db.model.PDRiskExample;
import com.fms.db.model.PDRiskFeeRate;
import com.fms.db.model.PDRiskFeeRateExample;
import com.fms.db.model.PDRiskIntroduceInfo;
import com.fms.db.model.PDRiskIntroduceInfoExample;
import com.fms.db.model.PDRiskSalesInfo;
import com.fms.db.model.PDRiskSalesInfoExample;
import com.fms.db.model.PDUeditor;
import com.fms.db.model.PDUeditorExample;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthChargeRate;
import com.fms.db.model.PDWealthChargeRate2;
import com.fms.db.model.PDWealthChargeRateExample;
import com.fms.db.model.PDWealthExample;
import com.fms.db.model.PDWealthFeeRate;
import com.fms.db.model.PDWealthFeeRateExample;
import com.fms.db.model.PDWealthIntroduceInfo;
import com.fms.db.model.PDWealthIntroduceInfoExample;
import com.fms.db.model.PDWealthNetValue;
import com.fms.db.model.PDWealthNetValueExample;
import com.fms.db.model.PDWealthNetValueTask;
import com.fms.db.model.PDWealthOpenDate;
import com.fms.db.model.PDWealthOpenDateExample;
import com.fms.db.model.PDWealthSalesInfo;
import com.fms.db.model.PDWealthSalesInfoExample;
import com.fms.db.model.PDWealthStockDis;
import com.fms.db.model.PDWealthStockDisExample;
import com.fms.db.model.PDwealthIncomeDis;
import com.fms.db.model.PDwealthIncomeDisExample;
import com.fms.db.model.PdAmountDisInfo;
import com.fms.db.model.PdAmountDisInfoExample;
import com.fms.db.model.SysEmailAdress;
import com.fms.db.model.SysEmailAdressExample;
import com.fms.db.model.SysEmailInfo;
import com.fms.db.model.SysSmsBatch;
import com.fms.db.model.SysSmsInfo;
import com.fms.db.model.SysSmsInfoExample;
import com.fms.db.model.WebServiceLogInfo;
import com.fms.service.mapper.NetValueServiceMapper;
import com.fms.service.mapper.ProductServiceMapper;
import com.fms.service.product.ProductService;
import com.fms.service.sms.SmsService;
import com.fms.webservice.ewealth.service.EWealthClientService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.core.service.SendEmailService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.NumberUtils;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;







@Service("ProductService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProductServiceImpl implements ProductService {
	private static final Logger log = Logger.getLogger(ProductServiceImpl.class);
	@Autowired
	PDProductMapper pDProductMapper;
	@Autowired
	PDWealthMapper pDWealthMapper;
	@Autowired
	PDRiskMapper pDRiskMapper;
	@Autowired
	PDWealthFeeRateMapper pDWealthFeeRateMapper;
	@Autowired
	PDRiskFeeRateMapper pDRiskFeeRateMapper;
	@Autowired
	PDFactorMapper pDFactorMapper;
	@Autowired
	PDWealthChargeRateMapper pDWealthChargeRateMapper;
	@Autowired
	PDWealthNetValueMapper pdWealthNetValueMapper;
	@Autowired
	CommonService commonService;
	@Autowired
	ProductServiceMapper productServiceMapper;
	@Autowired
	NetValueServiceMapper netValueServiceMapper;
	@Autowired
	DefFileInfoMapper defFileInfoMapper;
	@Autowired
	DefCodeMapper defCodeMapper;
	@Autowired
	PDWealthOpenDateMapper pdWealthOpenDateMapper;
	@Autowired
	private PDwealthIncomeDisMapper pdwealthIncomeDisMapper;
	@Autowired
	private PdAmountDisInfoMapper pdAmountDisInfoMapper;
	@Autowired
	private PDWealthIntroduceInfoMapper pDWealthIntroduceInfoMapper;
	@Autowired
	private PDWealthSalesInfoMapper pdWealthSalesInfoMapper;
	@Autowired
	private PDRiskIntroduceInfoMapper pdRiskIntroduceInfoMapper;
	@Autowired
	private PDRiskSalesInfoMapper pDRiskSalesInfoMapper;
	@Autowired
	private PDUeditorMapper pDUeditorMapper;
	@Autowired
	private PDAmountOrderInfoMapper pdAmountOrderInfoMapper;
	@Autowired
	private EWealthClientService eWealthClientService;
	@Autowired
	private WebServiceLogInfoMapper webserviceLogInfoMapper;
	@Autowired
    private SysEmailInfoMapper sysEmailInfoMapper;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private DefUserMapper defUserMapper;
    @Autowired
    private SysEmailAdressMapper sysEmailAdressMapper;
    @Autowired
    private HolidaysMapper holidaysMapper;
    @Autowired
	private SmsService smsService;
    @Autowired
    PDWealthNetValueTaskMapper pDWealthNetValueTaskMapper;
    @Autowired
    private PDContractMapper pDContractMapper;
    @Autowired
    private PDContractDetailMapper pDContractDetailMapper;
    @Autowired
	private PDWealthStockDisMapper pdwealthStockDisMapper;
    @Autowired
   	private AgentMapper agentMapper;
    
	/**
	 * 新增产品基本信息
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public String addPrdocutBasicInfo(Map tMap, LoginInfo loginInfo) {
		PDProduct pDProduct = (PDProduct) tMap.get("pDProductSchema");
		String pdProdcutSeq = null;
		String existProductId = null;
		if (!tMap.get("productId").equals("null")) {
			existProductId = tMap.get("productId").toString();
		}
		/*** 重复提交更新产品主表T_PD_PRODUCT ***/
		if (pDProduct != null && existProductId != null) {
			this.verifyProductCode(existProductId, pDProduct.getProductCode());
			pDProduct.setProductId(new Long(existProductId));
			// 查找已经存在的产品主表,修改相应的产品信息
			PDProductExample pdProductExample = new PDProductExample();
			pdProductExample.createCriteria().andProductIdEqualTo(new Long(existProductId))
					.andRcStateEqualTo("E");
			PDProduct existProduct = (PDProduct) pDProductMapper.selectByExample(pdProductExample).get(0);
			existProduct.setProductShortName(pDProduct.getProductShortName());
			existProduct.setAgencyComId(pDProduct.getAgencyComId());
			existProduct.setProductCode(pDProduct.getProductCode());
			existProduct.setProductName(pDProduct.getProductName());
			existProduct.setIntroduceDate(pDProduct.getIntroduceDate());
			existProduct.setIsInviteCode(pDProduct.getIsInviteCode());
			existProduct.setIsOrder(pDProduct.getIsOrder());
			existProduct.setSalesStatus(pDProduct.getSalesStatus());
			existProduct.setRemark(pDProduct.getRemark());
			existProduct.setProductManager(pDProduct.getProductManager());//获取添加产品经理(2017/3/18陈功改)
			existProduct.setIsHot("01");//初始化为热门产品
			com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(existProduct, loginInfo);
			pDProductMapper.updateByPrimaryKey(existProduct);
			pdProdcutSeq = existProductId;
		}
		/**** 新增保存产品主表T_PD_PRODUCT ***/
		else {
			this.verifyProductCode(existProductId, pDProduct.getProductCode());
			//pdProdcutSeq = commonService.getSeqValByName("SEQ_T_PD_PRODUCT").toString();
			//pDProduct.setProductId(new Long(pdProdcutSeq));
			pDProduct.setStatus("0");// 已定义
			pDProduct.setIsHot("01");//初始化为热门产品
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDProduct, loginInfo);
			pDProductMapper.insert(pDProduct);
		}
		return pDProduct.getProductId().toString();
	}

	/**
	 * 新增网销产品信息
	 */
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public String addPrdocutInternetInfo(Map tMap, LoginInfo loginInfo) {
		String productId = tMap.get("productId").toString();
		String productType = tMap.get("productType").toString();
		Long wealthIntroduceInfoSeq = null;
		Long wealthSalesInfoSeq = null;
		Long riskIntroduceInfoSeq = null;
		Long riskSalesInfoSeq = null;
		String SucFlag = "";
		if (tMap.get("sucFlag") != null) {
			SucFlag = tMap.get("sucFlag").toString();
		}
		/********* 新增产品网销信息 **********/
		if (!SucFlag.equals("saveSuc")) {
			log.info("新增网销产品信息");
			if (productType.equals("1")) {
				PDWealthIntroduceInfo pdWealthSEOIntroductInfo = (PDWealthIntroduceInfo) tMap
						.get("pdWealthIntroductSEOInfo");// 获取产品说明信息
				//wealthIntroduceInfoSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_INTRODUCE_INFO");
				PDWealthIntroduceInfo pdWealthIntroduceInfo = (PDWealthIntroduceInfo) tMap.get("PDWealthIntroduceInfo");// 获取产品说明信息
				pdWealthIntroduceInfo.setPdWealthIntroduceInfoId(wealthIntroduceInfoSeq);
				pdWealthIntroduceInfo.setProductId(new Long(productId));
				pdWealthIntroduceInfo.setProductTitle(pdWealthSEOIntroductInfo.getProductTitle());// 产品标题
				pdWealthIntroduceInfo.setProductKeyword(pdWealthSEOIntroductInfo.getProductKeyword());// 产品关键字
				pdWealthIntroduceInfo.setProductDesc(pdWealthSEOIntroductInfo.getProductDesc());// 产品描述
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdWealthIntroduceInfo, loginInfo);
				pDWealthIntroduceInfoMapper.insert(pdWealthIntroduceInfo);// 插入财富产品说明信息

				wealthSalesInfoSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_SALES_INFO");
				PDWealthSalesInfo pdWealthSalesInfo = (PDWealthSalesInfo) tMap.get("pdWealthSalesInfo");// 获取产品营销信息
				pdWealthSalesInfo.setPdWealthSalesInfoId(wealthSalesInfoSeq);
				pdWealthSalesInfo.setProductId(new Long(productId));
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdWealthSalesInfo, loginInfo);
				pdWealthSalesInfoMapper.insert(pdWealthSalesInfo);// 插入财富产品营销信息
				SucFlag = "saveSuc";
			} else {
				PDRiskIntroduceInfo PDRiskSEOIntroductInfo = (PDRiskIntroduceInfo) tMap.get("pdRiskIntroductSEOInfo");// 获取产品说明信息
				riskIntroduceInfoSeq = commonService.getSeqValByName("SEQ_T_PD_RISK_INTRODUCE_INFO");
				PDRiskIntroduceInfo PDRiskIntroduceInfo = (PDRiskIntroduceInfo) tMap.get("PDRiskIntroduceInfo");
				PDRiskIntroduceInfo.setPdRiskIntroduceInfoId(riskIntroduceInfoSeq);
				PDRiskIntroduceInfo.setProductTitle(PDRiskSEOIntroductInfo.getProductTitle());// 产品标题
				PDRiskIntroduceInfo.setProductKeyword(PDRiskSEOIntroductInfo.getProductKeyword());// 产品关键字
				PDRiskIntroduceInfo.setProductDescribe(PDRiskSEOIntroductInfo.getProductDescribe());// 产品描述
				PDRiskIntroduceInfo.setProductId(new Long(productId));
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(PDRiskIntroduceInfo, loginInfo);
				pdRiskIntroduceInfoMapper.insert(PDRiskIntroduceInfo);// 插入保险韩品说明信息
				riskSalesInfoSeq = commonService.getSeqValByName("SEQ_T_PD_RISK_SALES_INFO");
				PDRiskSalesInfo pdRiskSalesInfo = (PDRiskSalesInfo) tMap.get("pdRiskSalesInfo");
				pdRiskSalesInfo.setProductId(new Long(productId));
				pdRiskSalesInfo.setPdRiskSalesInfoId(riskSalesInfoSeq);
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdRiskSalesInfo, loginInfo);
				pDRiskSalesInfoMapper.insert(pdRiskSalesInfo);// 插入保险产品营销信息
				SucFlag = "saveSuc";
			}
		}
		/********* 重复提交产品网销信息 **********/
		else {
			log.info("重复提交网销产品信息");
			if (productType.equals("1")) {
				PDWealthIntroduceInfo PDWealthIntroduceInfoExist = null;
				PDWealthSalesInfo pdWealthSalesInfoExist = null;
				//先查询到原来的财富网销产品说明信息,在重新插入一条新的记录
				PDWealthIntroduceInfoExample PDWealthIntroduceInfoExample = new PDWealthIntroduceInfoExample();
				PDWealthIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDWealthIntroduceInfo> PDWealthIntroduceInfoList = pDWealthIntroduceInfoMapper
						.selectByExample(PDWealthIntroduceInfoExample);
				if (PDWealthIntroduceInfoList.size() > 0) {
					// 如果存在，则进行删除
					PDWealthIntroduceInfoExist = (PDWealthIntroduceInfo) PDWealthIntroduceInfoList.get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(PDWealthIntroduceInfoExist, loginInfo);
					pDWealthIntroduceInfoMapper.updateByPrimaryKey(PDWealthIntroduceInfoExist);
				}
				wealthIntroduceInfoSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_INTRODUCE_INFO");
				PDWealthIntroduceInfo PDWealthIntroduceInfo = (PDWealthIntroduceInfo) tMap.get("PDWealthIntroduceInfo");
				PDWealthIntroduceInfo.setPdWealthIntroduceInfoId(wealthIntroduceInfoSeq);
				PDWealthIntroduceInfo.setProductId(new Long(productId));
				PDWealthIntroduceInfo.setProductId(new Long(productId));
				PDWealthIntroduceInfo pdWealthSEOIntroductInfo = (PDWealthIntroduceInfo) tMap
						.get("pdWealthIntroductSEOInfo");
				PDWealthIntroduceInfo.setProductTitle(pdWealthSEOIntroductInfo.getProductTitle());
				PDWealthIntroduceInfo.setProductDesc(pdWealthSEOIntroductInfo.getProductDesc());
				PDWealthIntroduceInfo.setProductKeyword(pdWealthSEOIntroductInfo.getProductKeyword());
				// 如果存在就删除新增
				if (PDWealthIntroduceInfoList.size() > 0) {
					com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(PDWealthIntroduceInfoExist,
							PDWealthIntroduceInfo, loginInfo);
					pDWealthIntroduceInfoMapper.insert(PDWealthIntroduceInfo);// 插入财富产品说明信息
				} else {
					// 没有就新增
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(PDWealthIntroduceInfo, loginInfo);
					pDWealthIntroduceInfoMapper.insert(PDWealthIntroduceInfo);// 插入财富产品说明信息
				}
				// 先查询到原来的才网销产品营销信息,在重新插入一条新的记录
				PDWealthSalesInfoExample pdWealthSalesInfoExample = new PDWealthSalesInfoExample();
				pdWealthSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDWealthSalesInfo> pdWealthSalesInfoList = pdWealthSalesInfoMapper
						.selectByExample(pdWealthSalesInfoExample);
				if (pdWealthSalesInfoList.size() > 0) {
					pdWealthSalesInfoExist = (PDWealthSalesInfo) pdWealthSalesInfoList.get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdWealthSalesInfoExist, loginInfo);
					pdWealthSalesInfoMapper.updateByPrimaryKey(pdWealthSalesInfoExist);
				}
				wealthSalesInfoSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_SALES_INFO");
				PDWealthSalesInfo pdWealthSalesInfo = (PDWealthSalesInfo) tMap.get("pdWealthSalesInfo");
				pdWealthSalesInfo.setPdWealthSalesInfoId(wealthSalesInfoSeq);
				pdWealthSalesInfo.setProductId(new Long(productId));
				com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(pdWealthSalesInfoExist,
						pdWealthSalesInfo, loginInfo);
				pdWealthSalesInfoMapper.insert(pdWealthSalesInfo);// 插入财富产品营销信息
				SucFlag = "saveSuc";
			} else {
				PDRiskIntroduceInfo PDRiskIntroduceInfoExist = null;
				PDRiskSalesInfo pdRiskSalesInfoExist = null;
				// 先查询到原来的保险网销产品说明信息,在重新插入一条新的记录
				PDRiskIntroduceInfoExample PDRiskIntroduceInfoExample = new PDRiskIntroduceInfoExample();
				PDRiskIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDRiskIntroduceInfo> PDRiskIntroduceInfoList = pdRiskIntroduceInfoMapper
						.selectByExample(PDRiskIntroduceInfoExample);
				if (PDRiskIntroduceInfoList.size() > 0) {
					PDRiskIntroduceInfoExist = (PDRiskIntroduceInfo) PDRiskIntroduceInfoList.get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(PDRiskIntroduceInfoExist, loginInfo);
					pdRiskIntroduceInfoMapper.updateByPrimaryKey(PDRiskIntroduceInfoExist);
				}
				riskIntroduceInfoSeq = commonService.getSeqValByName("SEQ_T_PD_RISK_INTRODUCE_INFO");
				PDRiskIntroduceInfo PDRiskIntroduceInfo = (PDRiskIntroduceInfo) tMap.get("PDRiskIntroduceInfo");
				PDRiskIntroduceInfo.setPdRiskIntroduceInfoId(riskIntroduceInfoSeq);
				PDRiskIntroduceInfo.setProductId(new Long(productId));
				PDRiskIntroduceInfo pdRiskSEOIntroductInfo = (PDRiskIntroduceInfo) tMap.get("pdRiskIntroductSEOInfo");
				PDRiskIntroduceInfo.setProductTitle(pdRiskSEOIntroductInfo.getProductTitle());
				PDRiskIntroduceInfo.setProductKeyword(pdRiskSEOIntroductInfo.getProductKeyword());
				PDRiskIntroduceInfo.setProductDescribe(pdRiskSEOIntroductInfo.getProductDescribe());
				// 如果存在的话，就删除在新增
				if (PDRiskIntroduceInfoList.size() > 0) {
					com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(PDRiskIntroduceInfoExist,
							PDRiskIntroduceInfo, loginInfo);
					pdRiskIntroduceInfoMapper.insert(PDRiskIntroduceInfo);// 插入保险韩品说明信息
				} else {
					// 新增
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(PDRiskIntroduceInfo, loginInfo);
					pdRiskIntroduceInfoMapper.insert(PDRiskIntroduceInfo);// 插入保险韩品说明信息
				}
				PDRiskSalesInfoExample pdRiskSalesInfoExample = new PDRiskSalesInfoExample();
				pdRiskSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDRiskSalesInfo> pdRiskSalesInfoList = pDRiskSalesInfoMapper
						.selectByExample(pdRiskSalesInfoExample);
				if (pdRiskSalesInfoList.size() > 0) {
					pdRiskSalesInfoExist = (PDRiskSalesInfo) pdRiskSalesInfoList.get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdRiskSalesInfoExist, loginInfo);
					pDRiskSalesInfoMapper.updateByPrimaryKey(pdRiskSalesInfoExist);
				}
				riskSalesInfoSeq = commonService.getSeqValByName("SEQ_T_PD_RISK_SALES_INFO");
				PDRiskSalesInfo pdRiskSalesInfo = (PDRiskSalesInfo) tMap.get("pdRiskSalesInfo");
				pdRiskSalesInfo.setProductId(new Long(productId));
				pdRiskSalesInfo.setPdRiskSalesInfoId(riskSalesInfoSeq);
				com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(pdRiskSalesInfoExist, pdRiskSalesInfo,
						loginInfo);
				pDRiskSalesInfoMapper.insert(pdRiskSalesInfo);// 插入保险产品营销信息
				SucFlag = "saveSuc";
			}
		}
		return SucFlag;
	}

	/**
	 * 新增产品核心信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public String addPrdocutCoreInfo(Map tMap, LoginInfo loginInfo) {
		String productId = tMap.get("productId").toString();
		String productType = tMap.get("productType").toString();
		String productSubType = tMap.get("productSubType").toString();
		String existSucFlag = "";
		String internetInfoSucFlag = null;
		Long pdwealthSeq = null;
		Long pdriskSeq = null;
		if (tMap.get("sucFlag") != null) {
			existSucFlag = tMap.get("sucFlag").toString();
		}
		/******* 新增产品信息 ***********/
		if (!existSucFlag.equals("sucFlag")) {
			if (productType.equals("1")) {
				log.info("新增财富产品信息");
				PDWealth pDWealth = (PDWealth) tMap.get("pDWealthSchema");
				pdwealthSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH");
				pDWealth.setProductId(new Long(productId));
				pDWealth.setPdWealthId(pdwealthSeq);
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealth, loginInfo);
				pDWealthMapper.insert(pDWealth);
				if (productSubType.equals("02")) {
					log.info("新增固定收益产品信息");
					// 保存财富费用比例表T_PD_WEALTH_FEE_RATE
					List<PDWealthFeeRate> pDWealthFeeRateList = new ArrayList<PDWealthFeeRate>();
					pDWealthFeeRateList = (List<PDWealthFeeRate>) tMap.get("WealthFeeRateInfolist");
					for (int i = 0; i < pDWealthFeeRateList.size(); i++) {
						Long pdwealthFeeRateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_FEE_RATE");
						PDWealthFeeRate pDWealthFeeRate = (PDWealthFeeRate) pDWealthFeeRateList.get(i);
						pDWealthFeeRate.setPdWealthFeeRateId(pdwealthFeeRateSeq);
						pDWealthFeeRate.setPdWealthId(pDWealth.getPdWealthId());
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthFeeRate, loginInfo);
						pDWealthFeeRateMapper.insert(pDWealthFeeRate);
					}
					// 保存收益分配信息
					List<PDwealthIncomeDis> pdwealthIncomeDisList = new ArrayList<PDwealthIncomeDis>();
					pdwealthIncomeDisList = (List<PDwealthIncomeDis>) tMap.get("pdwealthIncomeDisList");
					for (PDwealthIncomeDis pdwealthIncomeDis : pdwealthIncomeDisList) {
						Long pdWealthIncomeDistributeId = commonService
								.getSeqValByName("SEQ_T_PD_WEALTH_INCOME_DIS");
						pdwealthIncomeDis.setPdWealthIncomeDistributeId(pdWealthIncomeDistributeId);
						pdwealthIncomeDis.setPdWealthId(pDWealth.getPdWealthId());
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdwealthIncomeDis, loginInfo);
						pdwealthIncomeDisMapper.insert(pdwealthIncomeDis);
					}
					// 保存固定收益类分类信息T_PD_WEALTH_CHARGE_RATE
					PDWealthChargeRate pDWealthChargeRate = (PDWealthChargeRate) tMap.get("pDWealthChargeRate");
					if (pDWealthChargeRate != null) {
						Long pdwealthChargeRateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_CHARGE_RATE");
						pDWealthChargeRate.setPdWealthChargeRateId(pdwealthChargeRateSeq);
						pDWealthChargeRate.setPdWealthId(pDWealth.getPdWealthId());
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthChargeRate, loginInfo);
						pDWealthChargeRateMapper.insert(pDWealthChargeRate);
					}
				} else {
					log.info("新增浮动、股权收益产品信息");
					if (productSubType.equals("01")) {
						// 保存浮动类收益分配信息
						List<PDWealthStockDis> pdwealthStockDisList = new ArrayList<PDWealthStockDis>();
						pdwealthStockDisList = (List<PDWealthStockDis>) tMap.get("pdwealthStockDisList");
						for (PDWealthStockDis pdwealthStockDis : pdwealthStockDisList) {
							Long pdWealthStockDisId = commonService
									.getSeqValByName("SEQ_T_PD_WEALTH_STOCK_DIS");
							pdwealthStockDis.setPdWealthStockDisId(pdWealthStockDisId);
							pdwealthStockDis.setPdWealthId(pDWealth.getPdWealthId());
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdwealthStockDis, loginInfo);
							pdwealthStockDisMapper.insert(pdwealthStockDis);
						}
					}
					// 保存浮动股权收益类分类信息T_PD_WEALTH_CHARGE_RATE
					PDWealthChargeRate pDWealthChargeRate = (PDWealthChargeRate) tMap.get("pDWealthChargeRate");
					if (pDWealthChargeRate != null) {
						Long pdwealthChargeRateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_CHARGE_RATE");
						pDWealthChargeRate.setPdWealthChargeRateId(pdwealthChargeRateSeq);
						pDWealthChargeRate.setPdWealthId(pDWealth.getPdWealthId());
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthChargeRate, loginInfo);
						pDWealthChargeRateMapper.insert(pDWealthChargeRate);
					}
				}
			} else {
				log.info("新增保险产品信息");
				PDRisk pDRisk = (PDRisk) tMap.get("pDRiskShcema");
				// 保存保险基本信息T_PD_RISK
				if (pDRisk != null) {
					pdriskSeq = commonService.getSeqValByName("SEQ_T_PD_RISK");
					pDRisk.setPdRiskId(pdriskSeq);
					pDRisk.setProductId(new Long(productId));
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDRisk, loginInfo);
					pDRiskMapper.insert(pDRisk);
				}
				List<PDRiskFeeRate> pDRiskFeeRateList = new ArrayList<PDRiskFeeRate>();
				// 保存保险费用比例表T_PD_RISK_FEE_RATE
				pDRiskFeeRateList = (List<PDRiskFeeRate>) tMap.get("insuraceRateInfolist");
				for (int i = 0; i < pDRiskFeeRateList.size(); i++) {
					PDRiskFeeRate pDRiskFeeRate = (PDRiskFeeRate) pDRiskFeeRateList.get(i);
					Long pDRiskFeeRateSeq = commonService.getSeqValByName("SEQ_T_PD_RISK_FEE_RATE");
					pDRiskFeeRate.setPdRiskId(pDRisk.getPdRiskId());
					pDRiskFeeRate.setPdRiskFeeRateId(pDRiskFeeRateSeq);
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDRiskFeeRate, loginInfo);
					pDRiskFeeRateMapper.insert(pDRiskFeeRate);
				}
			}
			// 录入信息T_PD_FACTOR
			List<PDFactor> pDFactorList = new ArrayList<PDFactor>();
			pDFactorList = (List<PDFactor>) tMap.get("factorInfolist");
			for (int i = 0; i < pDFactorList.size(); i++) {
				PDFactor pDFactor = (PDFactor) pDFactorList.get(i);
				Long pDFactorSeq = commonService.getSeqValByName("SEQ_T_PD_FACTOR");
				pDFactor.setPdFactorId(pDFactorSeq);
				pDFactor.setPdId(new Long(productId));
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDFactor, loginInfo);
				pDFactorMapper.insert(pDFactor);
			}

			//录入产品核心要素触发邮件
			sendProductCoreEmail(productId,loginInfo);
			internetInfoSucFlag = "sucFlag";
		} else {
			log.info("重复提交产品核心信息");
			/*** 重复提交的产品信息先将原来的数据全部进行逻辑删除,除了主表不删除 ***/
			PDWealth exisPdWealth = null;
			List<PDFactor> exisPdFactorList = null;
			List<PDWealthFeeRate> exisPdWealthFeeRateList = null;
			List<PDwealthIncomeDis> existPdwealthIncomeDisList = null;
			List<PDWealthStockDis> existPdwealthStockDisList = null;
			List<PDWealthChargeRate> existPdwealthChargeRates = null;
			PDWealthChargeRate exisPdWealthChargeRate = null;
			PDRisk exisPdRisk = null;
			List<PDRiskFeeRate> exisPdRiskFeeRateList = null;
			// 删除已存在的 录入信息T_PD_FACTOR
			PDFactorExample pdFactorExample = new PDFactorExample();
			pdFactorExample.createCriteria().andPdIdEqualTo(new Long(productId)).andRcStateEqualTo("E");
			exisPdFactorList = pDFactorMapper.selectByExample(pdFactorExample);
			if (exisPdFactorList.size() > 0) {
				for (int i = 0; i < exisPdFactorList.size(); i++) {
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo((PDFactor) exisPdFactorList.get(i),
							loginInfo);
					pDFactorMapper.updateByPrimaryKey((PDFactor) exisPdFactorList.get(i));
				}
			}
			/*** 删除财富产品信息 ****/
			if (productType.equals("1")) {
				log.info("重复提交产品核心信息，删除财富产品信息");
				// 删除已存在的 财富信息主表T_PD_WEALTH
				PDWealthExample pdWealthExample = new PDWealthExample();
				pdWealthExample.createCriteria().andProductIdEqualTo(new Long(productId)).andRcStateEqualTo("E");
				exisPdWealth = (PDWealth) pDWealthMapper.selectByExample(pdWealthExample).get(0);
				if (exisPdWealth != null) {
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdWealth, loginInfo);
				}
				pDWealthMapper.updateByPrimaryKey(exisPdWealth);
				/*** 删除固定收益类 *****/
				if (productSubType.equals("02")) {
					log.info("重复提交产品核心信息，删除财富产品固定类产品信息");
					// 删除 固定收益分类信息 T_PD_WEALTH_CHARGE_RATE
					PDWealthChargeRateExample pdWealthChargeRateExample = new PDWealthChargeRateExample();
					pdWealthChargeRateExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
							.andRcStateEqualTo("E");
					existPdwealthChargeRates = pDWealthChargeRateMapper.selectByExample(pdWealthChargeRateExample);
					if (existPdwealthChargeRates.size() > 0) {
						exisPdWealthChargeRate = (PDWealthChargeRate) existPdwealthChargeRates.get(0);
						com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdWealthChargeRate, loginInfo);
						pDWealthChargeRateMapper.updateByPrimaryKey(exisPdWealthChargeRate);
					}
					// 删除 财富费用比例信息 T_PD_WEALTH_FEE_RATE
					PDWealthFeeRateExample pdWealthFeeRateExample = new PDWealthFeeRateExample();
					pdWealthFeeRateExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
							.andRcStateEqualTo("E");
					exisPdWealthFeeRateList = pDWealthFeeRateMapper.selectByExample(pdWealthFeeRateExample);
					if (exisPdWealthFeeRateList.size() > 0) {
						for (int i = 0; i < exisPdWealthFeeRateList.size(); i++) {
							com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdWealthFeeRateList.get(i),
									loginInfo);
							pDWealthFeeRateMapper.updateByPrimaryKey(exisPdWealthFeeRateList.get(i));
						}
					}
					// 删除收益分配配置信息
					PDwealthIncomeDisExample pdwealthIncomeDisExample = new PDwealthIncomeDisExample();
					pdwealthIncomeDisExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
							.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					existPdwealthIncomeDisList = pdwealthIncomeDisMapper.selectByExample(pdwealthIncomeDisExample);
					for (PDwealthIncomeDis pdwealthIncomeDis : existPdwealthIncomeDisList) {
						com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdwealthIncomeDis, loginInfo);
						pdwealthIncomeDisMapper.updateByPrimaryKey(pdwealthIncomeDis);
					}
				} else {
					log.info("重复提交产品核心信息，删除财富产品浮动类产品信息");
					if (productSubType.equals("01")) {
						PDWealthStockDisExample pdWealthStockDisExample = new PDWealthStockDisExample();
						pdWealthStockDisExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						existPdwealthStockDisList = pdwealthStockDisMapper.selectByExample(pdWealthStockDisExample);
						for (PDWealthStockDis pdwealthStockDis : existPdwealthStockDisList) {
							com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdwealthStockDis, loginInfo);
							pdwealthStockDisMapper.updateByPrimaryKey(pdwealthStockDis);
						}
						
					}
					// 删除 浮动股权分类信息 T_PD_WEALTH_CHARGE_RATE
					PDWealthChargeRateExample pdWealthChargeRateExample = new PDWealthChargeRateExample();
					pdWealthChargeRateExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
							.andRcStateEqualTo("E");
					existPdwealthChargeRates = pDWealthChargeRateMapper.selectByExample(pdWealthChargeRateExample);
					if (existPdwealthChargeRates.size() > 0) {
						exisPdWealthChargeRate = (PDWealthChargeRate) existPdwealthChargeRates.get(0);
						com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdWealthChargeRate, loginInfo);
						pDWealthChargeRateMapper.updateByPrimaryKey(exisPdWealthChargeRate);
					}
				}
			} else {
				log.info("重复提交产品核心信息，删除保险类产品信息");
				/*** 删除保险产品信息 ****/
				// 保险产品 删除已存在的 保险信息主表 T_PD_RISK
				PDRiskExample pdRiskExample = new PDRiskExample();
				pdRiskExample.createCriteria().andProductIdEqualTo(new Long(productId)).andRcStateEqualTo("E");
				List<PDRisk> pdRisks = pDRiskMapper.selectByExample(pdRiskExample);
				if (pdRisks != null && pdRisks.size() > 0) {
					exisPdRisk = (PDRisk) pDRiskMapper.selectByExample(pdRiskExample).get(0);
				}
				if (exisPdRisk != null) {
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdRisk, loginInfo);
				}
				pDRiskMapper.updateByPrimaryKey(exisPdRisk);
				// 保险费用比例 T_PD_RISK_FEE_RATE
				PDRiskFeeRateExample pdRiskFeeRateExample = new PDRiskFeeRateExample();
				pdRiskFeeRateExample.createCriteria().andPdRiskIdEqualTo(exisPdRisk.getPdRiskId())
						.andRcStateEqualTo("E");
				exisPdRiskFeeRateList = pDRiskFeeRateMapper.selectByExample(pdRiskFeeRateExample);
				if (exisPdRiskFeeRateList != null) {
					for (int i = 0; i < exisPdRiskFeeRateList.size(); i++) {
						com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdRiskFeeRateList.get(i), loginInfo);
						pDRiskFeeRateMapper.updateByPrimaryKey(exisPdRiskFeeRateList.get(i));
					}
				}
			}
			/**** 删除产品核心信息后，新增产品信息 ******/
			// 保存产品主表T_PD_PRODUCT
			if (productType.equals("1")) {
				PDWealth pDWealth = (PDWealth) tMap.get("pDWealthSchema");
				// 保存财富信息表T_PD_WEALTH
				if (pDWealth != null) {
					pdwealthSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH");
					pDWealth.setProductId(new Long(productId));
					pDWealth.setPdWealthId(pdwealthSeq);
					if (exisPdWealth != null) {
						com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdWealth, pDWealth,
								loginInfo);
					} else {
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealth, loginInfo);
					}
					pDWealthMapper.insert(pDWealth);
				}
				// 固定收益类
				if (productSubType.equals("02")) {
					log.info("重复提交产品核心信息，新增固定收益类产品信息");
					// 保存财富费用比例表T_PD_WEALTH_FEE_RATE
					List<PDWealthFeeRate> pDWealthFeeRateList = new ArrayList<PDWealthFeeRate>();
					pDWealthFeeRateList = (List<PDWealthFeeRate>) tMap.get("WealthFeeRateInfolist");
					for (int i = 0; i < pDWealthFeeRateList.size(); i++) {
						Long pdwealthFeeRateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_FEE_RATE");
						PDWealthFeeRate pDWealthFeeRate = (PDWealthFeeRate) pDWealthFeeRateList.get(i);
						pDWealthFeeRate.setPdWealthFeeRateId(pdwealthFeeRateSeq);
						pDWealthFeeRate.setPdWealthId(pdwealthSeq);
						if (exisPdWealthFeeRateList.size() > 0) {
							com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(
									exisPdWealthFeeRateList.get(0), pDWealthFeeRate, loginInfo);
						} else {
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthFeeRateList.get(i),
									loginInfo);
						}
						pDWealthFeeRateMapper.insert(pDWealthFeeRate);
					}
					// 保存收益分配信息
					List<PDwealthIncomeDis> pdwealthIncomeDisList = new ArrayList<PDwealthIncomeDis>();
					pdwealthIncomeDisList = (List<PDwealthIncomeDis>) tMap.get("pdwealthIncomeDisList");
					for (PDwealthIncomeDis pdwealthIncomeDis : pdwealthIncomeDisList) {
						Long pdWealthIncomeDistributeId = commonService
								.getSeqValByName("SEQ_T_PD_WEALTH_INCOME_DIS");
						pdwealthIncomeDis.setPdWealthIncomeDistributeId(pdWealthIncomeDistributeId);
						pdwealthIncomeDis.setPdWealthId(pdwealthSeq);
						if (existPdwealthIncomeDisList.size() > 0) {
							com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(
									existPdwealthIncomeDisList.get(0), pdwealthIncomeDis, loginInfo);
						} else {
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdwealthIncomeDis, loginInfo);
						}
						pdwealthIncomeDisMapper.insert(pdwealthIncomeDis);
					}
					
					// 保存固定收益类分类信息T_PD_WEALTH_CHARGE_RATE
					PDWealthChargeRate pDWealthChargeRate = (PDWealthChargeRate) tMap.get("pDWealthChargeRate");
					if (pDWealthChargeRate != null) {
						Long pdwealthChargeRateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_CHARGE_RATE");
						pDWealthChargeRate.setPdWealthChargeRateId(pdwealthChargeRateSeq);
						pDWealthChargeRate.setPdWealthId(pdwealthSeq);
						if (exisPdWealthChargeRate != null) {
							com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdWealthChargeRate,
									pDWealthChargeRate, loginInfo);
						} else {
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthChargeRate, loginInfo);
						}
						pDWealthChargeRateMapper.insert(pDWealthChargeRate);// 保存固定收益类分类信息T_PD_WEALTH_CHARGE_RATE
					}
				} else {
					log.info("重复提交产品核心信息，新增浮动类产品信息");
					if (productSubType.equals("01")) {
						// 保存浮动类收益分配信息
						List<PDWealthStockDis> pdwealthStockDisList = new ArrayList<PDWealthStockDis>();
						pdwealthStockDisList = (List<PDWealthStockDis>) tMap.get("pdwealthStockDisList");
						for (PDWealthStockDis pdwealthStockDis : pdwealthStockDisList) {
							Long pdWealthStockDisId = commonService
									.getSeqValByName("SEQ_T_PD_WEALTH_STOCK_DIS");
							pdwealthStockDis.setPdWealthStockDisId(pdWealthStockDisId);
							pdwealthStockDis.setPdWealthId(pdwealthSeq);
							if (pdwealthStockDisList.size() > 0) {
								com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(
										pdwealthStockDisList.get(0), pdwealthStockDis, loginInfo);
							}else {
								com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdwealthStockDis, loginInfo);
							}
							pdwealthStockDisMapper.insert(pdwealthStockDis);
						}
					}
					// 保存浮动股权收益类分类信息T_PD_WEALTH_CHARGE_RATE
					PDWealthChargeRate pDWealthChargeRate = (PDWealthChargeRate) tMap.get("pDWealthChargeRate");
					if (pDWealthChargeRate != null) {
						Long pdwealthChargeRateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_CHARGE_RATE");
						pDWealthChargeRate.setPdWealthChargeRateId(pdwealthChargeRateSeq);
						pDWealthChargeRate.setPdWealthId(pdwealthSeq);
						if (exisPdWealthChargeRate != null) {
							com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdWealthChargeRate,
									pDWealthChargeRate, loginInfo);
						} else {
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthChargeRate, loginInfo);
						}
						pDWealthChargeRateMapper.insert(pDWealthChargeRate);// 保存固定收益类分类信息T_PD_WEALTH_CHARGE_RATE
					}
				}
			} else {
				log.info("重复提交产品核心信息，新增保险类产品信息");
				PDRisk pDRisk = (PDRisk) tMap.get("pDRiskShcema");
				// 保存保险基本信息T_PD_RISK
				if (pDRisk != null) {
					pdriskSeq = commonService.getSeqValByName("SEQ_T_PD_RISK");
					pDRisk.setPdRiskId(pdriskSeq);
					pDRisk.setProductId(new Long(productId));
					if (exisPdRisk != null) {
						com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdRisk, pDRisk, loginInfo);
					} else {
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDRisk, loginInfo);
					}
					pDRiskMapper.insert(pDRisk);
				}
				List<PDRiskFeeRate> pDRiskFeeRateList = new ArrayList<PDRiskFeeRate>();
				// 保存保险费用比例表T_PD_RISK_FEE_RATE
				pDRiskFeeRateList = (List<PDRiskFeeRate>) tMap.get("insuraceRateInfolist");
				for (int i = 0; i < pDRiskFeeRateList.size(); i++) {
					PDRiskFeeRate pDRiskFeeRate = (PDRiskFeeRate) pDRiskFeeRateList.get(i);
					Long pDRiskFeeRateSeq = commonService.getSeqValByName("SEQ_T_PD_RISK_FEE_RATE");
					pDRiskFeeRate.setPdRiskId(pDRisk.getPdRiskId());
					pDRiskFeeRate.setPdRiskFeeRateId(pDRiskFeeRateSeq);
					if (exisPdRiskFeeRateList.size() > 0) {
						com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdRiskFeeRateList.get(0),
								pDRiskFeeRate, loginInfo);
						pDRiskFeeRateMapper.insert(pDRiskFeeRate);
					} else {
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDRiskFeeRateList.get(i), loginInfo);
						pDRiskFeeRateMapper.insert(pDRiskFeeRate);
					}
				}
			}
			// 保存录入项信息表T_PD_RISK_FEE_RATE
			List<PDFactor> PDFactorList = new ArrayList<PDFactor>();
			PDFactorList = (List<PDFactor>) tMap.get("factorInfolist");
			if (PDFactorList != null) {
				for (int i = 0; i < PDFactorList.size(); i++) {
					Long pdfactorSeq = commonService.getSeqValByName("SEQ_T_PD_FACTOR");
					PDFactor pDFactor = (PDFactor) PDFactorList.get(i);
					pDFactor.setPdId(new Long(productId));
					pDFactor.setPdFactorId(pdfactorSeq);
					if (exisPdFactorList != null && exisPdFactorList.size() > 0) {
						com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdFactorList.get(0),
								pDFactor, loginInfo);
					} else {
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDFactor, loginInfo);
					}
					pDFactorMapper.insert(pDFactor);
				}
			}
			/******** 重复提交,新增结束 ***********/
			internetInfoSucFlag = "sucFlag";
		}
		return internetInfoSucFlag;
	}


	/**
	 * 发送交易复核邮件
	 * @param tradeCallBackInfo
	 * @return
	 */
	@Transactional
	private void sendProductCoreEmail(String productId,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createProductCoreEmail(productId,loginInfo);
		SysEmailInfo sysEmailInfo = (SysEmailInfo)resultInfo.getObj();
		//发送短信
			resultInfo = sendProductCoreEmail(sysEmailInfo,loginInfo);
	}

	
	/**
	 * 创建交易回访邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createProductCoreEmail(String productId,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//获取交易回访邮件所需数据
		Map paramMap = new HashMap();
		paramMap.put("productId", productId);
		Map<String,String> productCoreEmailData = productServiceMapper.getProuctCoreInfo(paramMap);
		DefUser userName = defUserMapper.selectByPrimaryKey(loginInfo.getUserId());
		String loginUserName = userName.getUserName();
		productCoreEmailData.put("loginUserName", loginUserName);
		if(productCoreEmailData==null||productCoreEmailData.size()==0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到交易赎回相关数据，创建邮件失败！");
			return resultInfo;
		}
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//产品要素录入通知邮件
		sysEmailInfo.setEmailType("04");
		sysEmailInfo.setEmailRelationNo(Long.parseLong(productId));
		sysEmailInfo.setEmailTitle("录入产品核心要素审核通知");
		//理财经理不同，则获取不同邮箱
		if (loginUserName.equals("赵雯雯")||loginUserName.equals("李寅杰")||
				loginUserName.equals("钱晓鸣")||loginUserName.equals("赵晶")||loginUserName.equals("毕静")) {
			sysEmailInfo.setEmailAddress("lijie@whalewealth.com");
		}
		if (loginUserName.equals("朱王康")||loginUserName.equals("倪丹丹")) {
			sysEmailInfo.setEmailAddress("zhaowenwen@whalewealth.com");
		}
		if (loginUserName.equals("沈一超")||loginUserName.equals("张舒阳")||loginUserName.equals("杨飞")) {
			sysEmailInfo.setEmailAddress("liyinjie@whalewealth.com");
		}
		if (loginUserName.equals("程倩")) {
			sysEmailInfo.setEmailAddress("zhaojing@whalewealth.com");
		}
		if (loginUserName.equals("朱非铭")) {
			sysEmailInfo.setEmailAddress("bijing@whalewealth.com");
		}
		//创建邮件内容
		String mailContent = createProductCoreEmailContent(productCoreEmailData);
		sysEmailInfo.setEmailContent(mailContent);
	//01-未发送
	sysEmailInfo.setEmailStatus("01");
	sysEmailInfo.setEmailCreateTime(DateUtils.getCurrentTimestamp());
	BeanUtils.insertObjectSetOperateInfo(sysEmailInfo,loginInfo);
	Long sysEmailInfoId = commonService.getSeqValByName("SEQ_T_SYS_EMAIL_INFO");
	sysEmailInfo.setSysEmailInfoId(sysEmailInfoId);
	sysEmailInfoMapper.insert(sysEmailInfo);
		/*String emailType = sysEmailInfo.getEmailType();
		SysEmailAdressExample sysEmailAdressExample = new SysEmailAdressExample();
		SysEmailAdressExample.Criteria sysEmailAdressCriteria = sysEmailAdressExample.createCriteria();
		sysEmailAdressCriteria.andEmailTypeEqualTo(emailType);
		List<SysEmailAdress> email = sysEmailAdressMapper.selectByExample(sysEmailAdressExample);
		List<SysEmailInfo> resultList = new ArrayList<SysEmailInfo>();
		SysEmailInfo sysEmailInfo1 = null;
		for (int i = 0; i < email.size(); i++) {
			sysEmailInfo1 = new SysEmailInfo();
			sysEmailInfo1.setEmailType("04");
			sysEmailInfo1.setEmailRelationNo(Long.parseLong(productId));
			sysEmailInfo1.setEmailTitle("录入产品核心要素审核通知");
			sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
			//创建邮件内容
				String mailContent = createProductCoreEmailContent(productCoreEmailData);
				sysEmailInfo1.setEmailContent(mailContent);
			//01-未发送
			sysEmailInfo1.setEmailStatus("01");
			sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
			BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1,loginInfo);
			Long sysEmailInfoId = commonService.getSeqValByName("SEQ_T_SYS_EMAIL_INFO");
			sysEmailInfo1.setSysEmailInfoId(sysEmailInfoId);
			sysEmailInfoMapper.insert(sysEmailInfo1);
			resultList.add(sysEmailInfo1);
		}*/
		resultInfo.setSuccess(true);
		resultInfo.setMsg("交易赎回邮件创建成功！");
		resultInfo.setObj(sysEmailInfo);
		return resultInfo;
	}
	
	
	/**
	 * 生成交易审核回访邮件类容
	 * @param callBackEmailData
	 * @return
	 */
	private String createProductCoreEmailContent(Map<String,String> productCoreEmailData){
		StringBuffer mailContent = new StringBuffer();

		mailContent.append("用户“");
		mailContent.append(productCoreEmailData.get("loginUserName"));
		mailContent.append("”于“");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		mailContent.append(df.format(new Date()));
		mailContent.append("”录入提交《");
		mailContent.append(productCoreEmailData.get("productName"));
		mailContent.append("》产品要素，请审核。");
		return mailContent.toString();
	}
	/**
	 * 发送邮件
	 * @return
	 */
	@Transactional
	private ResultInfo sendProductCoreEmail(SysEmailInfo sysEmailInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		DefCodeExample defCodeExample = new DefCodeExample();
		DefCodeExample.Criteria defCodeCriteria = defCodeExample.createCriteria();
		defCodeCriteria.andCodeTypeEqualTo("sendMailParam");
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		Map<String,String> mailHostParam = new HashMap<String,String>();
		if (defCodeList!=null&&defCodeList.size()>0) {
			for(DefCode defCode:defCodeList){
				mailHostParam.put(defCode.getCodeAlias(), defCode.getCodeName());
			}
		}else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到邮件发送服务器相关参数！");
			return resultInfo;
		}
		if(sysEmailInfo!=null){
			String address = sysEmailInfo.getEmailAddress();
			String title = sysEmailInfo.getEmailTitle();
			String content = sysEmailInfo.getEmailContent();
			resultInfo = sendEmailService.sendEmail(address, title, content, mailHostParam);
			if (resultInfo.isSuccess()) {
				sysEmailInfo.setEmailSendTime(DateUtils.getCurrentTimestamp());
				//02-已发送
				sysEmailInfo.setEmailStatus("02");
			}else {
				//03-发送失败
				sysEmailInfo.setEmailStatus("03");
			}
			BeanUtils.updateObjectSetOperateInfo(sysEmailInfo, loginInfo);
			sysEmailInfoMapper.updateByPrimaryKey(sysEmailInfo);
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	
	
	
	
	
	
	/**
	 * 产品设置页面初始化查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getPageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = productServiceMapper.productQueryListCount(paramMap);
		List<Map> resultList = productServiceMapper.productQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 浮动类产品初始化查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid searchFloatProductNetValue(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = productServiceMapper.floatProductQueryListCount(paramMap);
		List<Map> resultList = productServiceMapper.floatProductQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 浮动类产品净值初始化查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid searchNetValue(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = productServiceMapper.queryNetValueListCount(paramMap);
		List<Map> resultList = productServiceMapper.queryNetValueListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 新增产品净值信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public String addNetValueInfo(Map tMap, LoginInfo loginInfo) {
		PDWealthNetValue pDWealthNetValue = (PDWealthNetValue) tMap.get("pDWealthNetValueSchema");
		String returnPdwealthNetValueId = null;
		String existPdwealthNetValueId = null;
		// 重复提交：对于新增产品重复提交的情况，直接更新产品主表其他的表，全部做逻辑删除，然后在重新INSERT数据
		if (tMap.get("existPdwealthdNetValueId").toString().equals("isnull")) {
			returnPdwealthNetValueId = insertWealthNetValueInfo(tMap, loginInfo);
			ResultInfo resultInfo = new ResultInfo();
			//发送产品净值短信
			String isOrder = pDWealthNetValue.getIsOrder();
			String specialType = pDWealthNetValue.getSpecialType();
			resultInfo = smsService.createPublicNetValueMes(returnPdwealthNetValueId,isOrder,specialType, loginInfo);
		} else {
			existPdwealthNetValueId = tMap.get("existPdwealthdNetValueId").toString();
			tMap.put("modifyRecordNetWorthId", existPdwealthNetValueId);
			tMap.put("publicDate", pDWealthNetValue.getPublicDate());
			tMap.put("netType", pDWealthNetValue.getNetType());
			tMap.put("netValue", pDWealthNetValue.getNetValue());
			tMap.put("isOrder", pDWealthNetValue.getIsOrder());
			tMap.put("specialType", pDWealthNetValue.getSpecialType());
			tMap.put("earnRate", pDWealthNetValue.getEarnRate());
			returnPdwealthNetValueId = modifyWealthNetValueInfo(existPdwealthNetValueId, tMap, loginInfo, "add");
		}
		
		return returnPdwealthNetValueId;
	}

	/**
	 * 新增产品净值详细信息处理
	 * 
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public String insertWealthNetValueInfo(Map tMap, LoginInfo loginInfo) {
		Long pdWealthNetValueSeq = null;
		PDWealthNetValue pDWealthNetValue = (PDWealthNetValue) tMap.get("pDWealthNetValueSchema");
		String productId = (String) tMap.get("productId");
		PDWealthExample pdWealthExample = new PDWealthExample();
		pdWealthExample.createCriteria().andProductIdEqualTo(new Long(productId))
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		PDWealth pdWealth = pDWealthMapper.selectByExample(pdWealthExample).get(0);
		if (pDWealthNetValue != null) {
			// 校验该产品公布日期，该净值是否已经存在
			this.verifyNetValue(pdWealthNetValueSeq, pDWealthNetValue.getNetType(), pDWealthNetValue.getPublicDate(),
					pdWealth.getPdWealthId(), "insert");
			pdWealthNetValueSeq = commonService.getSeqValByName("SEQ_T_PD_WHEAL_NET_VALUE");
			pDWealthNetValue.setPdWhealNetValueId(pdWealthNetValueSeq);
			pDWealthNetValue.setPdWealthId(pdWealth.getPdWealthId());
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthNetValue, loginInfo);
			pdWealthNetValueMapper.insert(pDWealthNetValue);
			
		}
		
		//return pDWealthNetValue.getPdWealthId().toString();
		return pDWealthNetValue.getPdWhealNetValueId().toString();
	}

	/**
	 * 修改产品净值详细信息处理
	 */
	@SuppressWarnings({ "rawtypes" })
	public String modifyWealthNetValueInfo(String existPdwealthdNetValueId, Map tMap, LoginInfo loginInfo,
			String operate) {
		String modifyRecordNetWorthId = tMap.get("modifyRecordNetWorthId").toString();
		try {
			Date publicDate = null;
			if (operate.equals("add")) {
				publicDate = DateUtils.getDate(new SimpleDateFormat("yyyy-MM-dd").format(tMap.get("publicDate")));
			} else {
				publicDate = DateUtils.getDate(tMap.get("publicDate").toString());
			}
			String netType = tMap.get("netType").toString();
			String netValue = tMap.get("netValue").toString();
			String isOrder = tMap.get("isOrder").toString();
			String specialType = tMap.get("specialType").toString();
			String earnRate = tMap.get("earnRate").toString();
			PDWealthNetValue pdWealthNetValue = pdWealthNetValueMapper
					.selectByPrimaryKey(new Long(modifyRecordNetWorthId));
			// 校验现在的净值在当前是否存在重复
			this.verifyNetValue(pdWealthNetValue.getPdWhealNetValueId(), netType, publicDate,
					pdWealthNetValue.getPdWealthId(), "update");
			pdWealthNetValue.setNetValue(new BigDecimal(netValue));
			pdWealthNetValue.setNetType(netType);
			pdWealthNetValue.setPublicDate(publicDate);
			pdWealthNetValue.setIsOrder(isOrder);
			pdWealthNetValue.setSpecialType(specialType);
			pdWealthNetValue.setEarnRate(earnRate);
			com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(pdWealthNetValue, loginInfo);
			pdWealthNetValueMapper.updateByPrimaryKey(pdWealthNetValue);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return modifyRecordNetWorthId;
	}

	/**
	 * 查询新增净值信息的列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getaddNetValuePageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = netValueServiceMapper.addNetValueQueryListCount(paramMap);
		List<Map> resultList = netValueServiceMapper.addNetValueQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 产品净值信息初始列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getNetValuePageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = netValueServiceMapper.netValueQueryListCount(paramMap);
		List<Map> resultList = netValueServiceMapper.netValueQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		log.info("净值总行数：" + total);
		return dataGrid;
	}
	
	/**
	 * 查询产品净值信息列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getNetValueList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = netValueServiceMapper.netValueQueryCount(paramMap);
		List<Map> resultList = netValueServiceMapper.netValueQueryList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		log.info("净值总行数：" + total);
		return dataGrid;
	}
	
	/**
	 * 浮动类产品净值初始化查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo getProductNetValue( Map paramMap,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		try {
			
			String productId = (String)paramMap.get("productId");
			PDProduct pdProduct = pDProductMapper.selectByPrimaryKey(Long.parseLong(productId));
			String productName = pdProduct.getProductName();
			Map resultMap = new HashMap();
			resultMap.put("productName", productName);
			List<Map> netValueList = productServiceMapper.queryProductNetValue(paramMap);
			List netList = new ArrayList();
			for (int i = netValueList.size()-1; i >= 0; i--) {
				BigDecimal netValue = (BigDecimal)netValueList.get(i).get("netValue");
				netList.add(netValue);
			}
			resultMap.put("netValueList", netList);
			List<Map> publicDateList = productServiceMapper.queryProductPublicDate(paramMap);
			List dateList = new ArrayList();
			for (int i = publicDateList.size()-1; i >= 0; i--) {
				Date publicDate = (Date)publicDateList.get(i).get("publicDate");
				dateList.add(publicDate);
			}
			resultMap.put("publicDateList", dateList);
			resultInfo.setMsg("查询成功！");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 查询净值新增附件列表信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getNetValueFileInfo(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = netValueServiceMapper.netValueFileQueryListCount(paramMap);
		List<Map> resultList = netValueServiceMapper.netValueFileQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 删除净值信息
	 */
	@Transactional
	public void deleteNetValueInfo(Long uid, LoginInfo loginInfo) {
		PDWealthNetValue pdWealthNetValue = pdWealthNetValueMapper.selectByPrimaryKey(uid);
		com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdWealthNetValue, loginInfo);
		pdWealthNetValueMapper.updateByPrimaryKey(pdWealthNetValue);
	}

	/**
	 * 获取净值信息修改页面
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public Map getUpdateListInfo(Long wealthNetValueId) {
		Map paramMap = new HashMap();
		PDWealthNetValue pdWealthNetValue = pdWealthNetValueMapper.selectByPrimaryKey(wealthNetValueId);
		paramMap.put("publicDate", DateUtils.getDateString(pdWealthNetValue.getPublicDate()));
		DefCodeExample defCodeExample = new DefCodeExample();
		defCodeExample.createCriteria().andCodeEqualTo(pdWealthNetValue.getNetType())
				.andCodeTypeEqualTo("netValueType");
		DefCode defCode = (DefCode) defCodeMapper.selectByExample(defCodeExample).get(0);
		paramMap.put("netType", pdWealthNetValue.getNetType() + "-" + defCode.getCodeName());
		paramMap.put("netValue", pdWealthNetValue.getNetValue());
		paramMap.put("isOrder", pdWealthNetValue.getIsOrder());
		paramMap.put("specialType", pdWealthNetValue.getSpecialType());
		paramMap.put("earnRate", pdWealthNetValue.getEarnRate());
		return paramMap;
	}

	/**
	 * 保存修改的净值信息
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public String updatNetValueSave(Map tMap, LoginInfo loginInfo) {
		String returnPdwealthNetValueId = null;
		if (tMap != null) {
			String modifyRecordNetWorthId = tMap.get("modifyRecordNetWorthId").toString();
			returnPdwealthNetValueId = modifyWealthNetValueInfo(modifyRecordNetWorthId, tMap, loginInfo, "update");
			//创建净值类短信
			ResultInfo resultInfo = new ResultInfo();
			String isOrder = tMap.get("isOrder").toString();
			String specialType = tMap.get("specialType").toString();
			resultInfo = smsService.createPublicNetValueMes(returnPdwealthNetValueId,isOrder,specialType,loginInfo);
		}
		return returnPdwealthNetValueId;
	}

	/**
	 * 新增开放日信息
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public String addopenDayInfo(Map tMap, LoginInfo loginInfo) {
		String returnPdwealthOpenId = null;
		String financingScale = "";
		String productId = tMap.get("productId").toString();
		if (tMap != null) {
			String existPdwealthOpenId = tMap.get("existpdwealthOpenDayId").toString();
			if (existPdwealthOpenId.equals("isnull")) {
				Long pdWealthOpenDateSeq = null;
				String openDay = tMap.get("openDate").toString();
				String investStartDate = tMap.get("investStartDate").toString();
				String investEndDate = tMap.get("investEndDate").toString();
				if (tMap.containsKey("financingScale")) {
					financingScale = tMap.get("financingScale").toString();
				}
				PDWealthExample pdWealthExample = new PDWealthExample();
				pdWealthExample.createCriteria().andProductIdEqualTo(new Long(productId)).andRcStateEqualTo("E");
				PDWealth pdWealth = (PDWealth) pDWealthMapper.selectByExample(pdWealthExample).get(0);
				Date date = null;
				try {
					date = DateUtils.getDate(openDay);
					// 校验
					this.verifyWealthOpenDay(existPdwealthOpenId, pdWealth.getPdWealthId(), date);
					PDWealthOpenDate pdWealthOpenDate = new PDWealthOpenDate();
					pdWealthOpenDateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_OPEN_DATE");
					pdWealthOpenDate.setPdWealthId(pdWealth.getPdWealthId());
					// 获取投资起始时间
					/*
					 * Map paramMap = new HashMap(); paramMap.put("productId",
					 * productId); paramMap.put("openDate", openDay); Map
					 * resultMap =
					 * productServiceMapper.getFloatPdInvestDate(paramMap);
					 * if(resultMap!=null&&resultMap.size()>0){ }
					 */
					if (financingScale != null && !"".equals(financingScale)) {
						pdWealthOpenDate
								.setFinancingScale(new BigDecimal(NumberUtils.multiply(financingScale, "10000")));
					}
					pdWealthOpenDate.setInvestStartDate(DateUtils.getDateTime(investStartDate));
					pdWealthOpenDate.setInvestEndDate(DateUtils.getDateTime(investEndDate));
					pdWealthOpenDate.setPdWealthOpenDateId(pdWealthOpenDateSeq);
					pdWealthOpenDate.setOpenDate(date);
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdWealthOpenDate, loginInfo);
					pdWealthOpenDateMapper.insert(pdWealthOpenDate);
					returnPdwealthOpenId = pdWealthOpenDate.getPdWealthId().toString();
					return returnPdwealthOpenId;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				Date openDayDate = null;
				try {
					openDayDate = DateUtils.getDate(tMap.get("openDate").toString());
					String investStartDate = tMap.get("investStartDate").toString();
					String investEndDate = tMap.get("investEndDate").toString();
					PDWealthOpenDate pdWealthOpenDate = pdWealthOpenDateMapper
							.selectByPrimaryKey(new Long(existPdwealthOpenId));
					this.verifyWealthOpenDay(existPdwealthOpenId, pdWealthOpenDate.getPdWealthId(),
							openDayDate);
					pdWealthOpenDate.setInvestStartDate(DateUtils.getDate1(investStartDate));
					pdWealthOpenDate.setInvestEndDate(DateUtils.getDate1(investEndDate));
					// 获取投资起始时间
					/*
					 * Map paramMap = new HashMap(); paramMap.put("productId",
					 * productId); paramMap.put("openDate", openDayDate); Map
					 * resultMap =
					 * productServiceMapper.getFloatPdInvestDate(paramMap);
					 * if(resultMap!=null&&resultMap.size()>0){
					 * pdWealthOpenDate.setInvestStartDate(DateUtils.getDateTime
					 * (resultMap.get("lastOpenDate").toString()));
					 * pdWealthOpenDate.setInvestEndDate(DateUtils.getDateTime(
					 * resultMap.get("openDate").toString())); }
					 */
					if (financingScale != null && !"".equals(financingScale)) {
						pdWealthOpenDate
								.setFinancingScale(new BigDecimal(NumberUtils.multiply(financingScale, "10000")));
					}
					pdWealthOpenDate.setOpenDate(openDayDate);
					com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(pdWealthOpenDate, loginInfo);
					pdWealthOpenDateMapper.updateByPrimaryKey(pdWealthOpenDate);
					returnPdwealthOpenId = existPdwealthOpenId;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return returnPdwealthOpenId;
			}
		}
		return returnPdwealthOpenId;
	}
	/**
	 * 批量维护开放日信息
	 */
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Transactional
	public ResultInfo addOpenDaysInfo(HashMap tMap, LoginInfo loginInfo) {
 		ResultInfo resultInfo = new ResultInfo();
 		List<String> openDateList = new ArrayList<String>();
 		try {
 			String defendStartDate = (String)tMap.get("defendStartDate");
 	 		String defendEndDate = (String)tMap.get("defendEndDate");
 	 		String isOrder = (String)tMap.get("isOrder");
 	 		String openDayRules = (String)tMap.get("openDayRules");
 	 		//维护开始日期
 	 		String startYear = defendStartDate.substring(0, 4);
 	 		int startYearNum = Integer.parseInt(startYear);
 	 		String startMonth = defendStartDate.substring(5, 7);
 	 		int startMonthNum = Integer.parseInt(startMonth);
 	 		String startDay = defendStartDate.substring(8, 10);
 	 		int startDayNum = Integer.parseInt(startDay);
 	 		//维护结束日期
 	 		String endYear = defendEndDate.substring(0, 4);
 	 		int endYearNum = Integer.parseInt(endYear);
 	 		String endMonth = defendEndDate.substring(5, 7);
 	 		int endMonthNum = Integer.parseInt(endMonth);
 	 		String endDay = defendEndDate.substring(8, 10);
 	 		int endDayNum = Integer.parseInt(endDay);
 	 		String openDateDay = (String)tMap.get("openDate");
 	 		int openDateDayNum = 0;
 	 		if(!"".equals(openDateDay)&&openDateDay != null){
 	 			if (openDateDay.contains("/")) {
 	 				return null;
 	 			}
 	 			openDateDayNum = Integer.parseInt(openDateDay);
 	 		}
 	 		
 	 		//因为holidays中的日期是yyyy/MM/dd这种格式
 	 		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
 	 		if("1".equals(isOrder)){
 	 		//----------------每月几号
 	 			Calendar calendar = Calendar.getInstance();
 	 			int month;
 	 			int time;
 	 			//判断维护起期与开放日
 	 			if (Long.parseLong(openDateDay) > startDayNum ||Long.parseLong(openDateDay) == startDayNum) {
 	 				month = startMonthNum-1;
 	 			}else{
 	 				month = startMonthNum;
 	 			}
 	 			//判断维护止期与开放日
 	 			if(Long.parseLong(openDateDay) > startDayNum ||Long.parseLong(openDateDay) == startDayNum){
 	 				if(Long.parseLong(openDateDay) < endDayNum ||Long.parseLong(openDateDay) == endDayNum){
 	 	 				time = endMonthNum-startMonthNum+1;
 	 				}else{
 	 					time = endMonthNum-startMonthNum;
 	 				}
 	 			}else{
 	 				if(Long.parseLong(openDateDay) < endDayNum ||Long.parseLong(openDateDay) == endDayNum){
 	 					time = endMonthNum-startMonthNum;
 	 				}else{
 	 					time = endMonthNum-startMonthNum-1;
 	 				}
 	 			}
 	 			//维护期间在一年之内，如果维护期间大于一年，这里要重新考虑
 	 			for(int i=0;i<time;i++)
 				{
 	 				calendar.set(startYearNum, month+i, openDateDayNum);
 	 				String openDay = simpleDateFormat.format(calendar.getTime());
 	 				Date addDate = null;
 	 	 	 		for(int j=1;j<8;j++){
 	 	 	 			HolidaysExample holidaysExample = new HolidaysExample();
 	 	 	 	 		holidaysExample.createCriteria().andHolidayEqualTo(openDay);
 	 	 	 	 		List<Holidays> holidays = holidaysMapper.selectByExample(holidaysExample);
 	 	 	 	 		if(holidays.size()>0){
 	 	 	 	 			String newOpenDay = openDay.replace("/", "-");
 	 	 	 	 		    //此处可做一个判断，根据所传的值判断是向前推一天还是向后推一天
 	 	 	 	 			addDate = DateUtils.calDate(DateUtils.getDate(newOpenDay), 1, "D");
 	 	 	 	 			openDay = simpleDateFormat.format(addDate);
 	 	 	 	 		}else{
 	 	 	 	 			break;
 	 	 	 	 		}
 	 	 	 		}
 	 	 	 	    openDateList.add(openDay.replace("/", "-"));
 				}
 	 		}else{
				if ("1".equals(openDayRules)) {
					// ----------------每月最后一个工作日
					//如果维护止期不是月末，那么这个月的月末算吗？
					for(int i=0;i<endMonthNum-startMonthNum+1;i++){
						Calendar calendar = Calendar.getInstance();
						//如果维护期间大于一年，这里要重新考虑
						calendar.set(Calendar.YEAR, Integer.parseInt(startYear));
						calendar.set(Calendar.MONTH, startMonthNum+i-1);
						calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
						// 每月最后一天
						String openDay = simpleDateFormat.format(calendar.getTime());
						for (int j = 1; j < 8; j++) {
							HolidaysExample holidaysExample = new HolidaysExample();
							holidaysExample.createCriteria().andHolidayEqualTo(openDay);
							List<Holidays> holidays = holidaysMapper.selectByExample(holidaysExample);
							if (holidays.size() > 0) {
								String newOpenDay = openDay.replace("/", "-");
								// 此处可做一个判断，根据所传的值判断是向前推一天还是向后推一天
								Date addDate = DateUtils.calDate(DateUtils.getDate(newOpenDay), -1, "D");
								openDay = simpleDateFormat.format(addDate);
							} else {
								break;
							}
						}
						openDateList.add(openDay.replace("/", "-"));
					}
				} else if ("2".equals(openDayRules)) {
					// ----------------每周周一
					Calendar calendar = Calendar.getInstance();
					calendar.setFirstDayOfWeek(Calendar.MONDAY);
					calendar.setTime(DateUtils.getDate(defendStartDate));
					calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					// 获取本周周一
					String fixedOpenDay = DateUtils.getDateString(calendar.getTime());
					String newDefStartDate = defendStartDate.substring(0, 10);
					//如果维护起期不是周一，那就延续到下周一
					if(!fixedOpenDay.equals(newDefStartDate)){
						calendar.add(Calendar.DATE, 7);
						fixedOpenDay = DateUtils.getDateString(calendar.getTime());
					}
					//计算出维护期间周一的个数
					int interval = DateUtils.calInterval(fixedOpenDay, defendEndDate.substring(0, 10), "D");
					int number = interval/7+1;
					for(int i=0;i<number;i++){
						if(i!=0){
							calendar.add(Calendar.DATE, 7);
						}
						String openDay = simpleDateFormat.format(calendar.getTime());
						for (int j = 1; j < 8; j++) {
							HolidaysExample holidaysExample = new HolidaysExample();
							holidaysExample.createCriteria().andHolidayEqualTo(openDay);
							List<Holidays> holidays = holidaysMapper.selectByExample(holidaysExample);
							if (holidays.size() > 0) {
								calendar.add(Calendar.DATE, 7);
								openDay = simpleDateFormat.format(calendar.getTime());
								number = number-1;
							} else {
								break;
							}
						}
						openDateList.add(openDay.replace("/", "-"));
					}
 	 			}else if("3".equals(openDayRules)){
 	 			// ----------------每月月底前十个工作日
					//如果维护止期不是月末，那么这个月的月末算吗？
					for(int i=0;i<endMonthNum-startMonthNum+1;i++){
						Calendar calendar = Calendar.getInstance();
						//如果维护期间大于一年，这里要重新考虑
						calendar.set(Calendar.YEAR, Integer.parseInt(startYear));
						calendar.set(Calendar.MONTH, startMonthNum+i-1);
						calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
						// 每月最后一天
						String openDay = simpleDateFormat.format(calendar.getTime());
						for(int k = 0 ;k < 9; k++){
						for (int j = 1; j < 8; j++) {
							HolidaysExample holidaysExample = new HolidaysExample();
							holidaysExample.createCriteria().andHolidayEqualTo(openDay);
							List<Holidays> holidays = holidaysMapper.selectByExample(holidaysExample);
							if (holidays.size() > 0) {
								String newOpenDay = openDay.replace("/", "-");
								// 此处可做一个判断，根据所传的值判断是向前推一天还是向后推一天
								Date addDate = DateUtils.calDate(DateUtils.getDate(newOpenDay), -1, "D");
								openDay = simpleDateFormat.format(addDate);
							} else {
								break;
							}
						}
						String sizeOpenDay = openDay.replace("/", "-");
						Date addDate = DateUtils.calDate(DateUtils.getDate(sizeOpenDay), -1, "D");
						openDay = simpleDateFormat.format(addDate);
					}
						openDateList.add(openDay.replace("/", "-"));
					}
 	 			}else{
 	 				resultInfo.setSuccess(false);
					resultInfo.setMsg("请重新选择！");
					return resultInfo;
 	 			}
 	 		}
 	 		//得到openDateList，然后执行插入
 	 			log.info(openDateList);
 	 			if (openDateList.size() == 0) {
 	 				resultInfo.setSuccess(false);
					resultInfo.setMsg("未获取到相应的开放日！");
					return resultInfo;
				}
 	 			//根据productId得到pdWealthId
 	 			String productId = tMap.get("productId").toString();
 	 			PDWealthExample pdWealthExample = new PDWealthExample();
				pdWealthExample.createCriteria().andProductIdEqualTo(new Long(productId)).andRcStateEqualTo("E");
				PDWealth pdWealth = (PDWealth) pDWealthMapper.selectByExample(pdWealthExample).get(0);
				Long pdWealthId = pdWealth.getPdWealthId();
				PDWealthOpenDate newPdWealthOpenDate = new PDWealthOpenDate();
 				for (String eachOpenDate : openDateList) {
 					if (tMap != null) {
 						String existPdwealthOpenId = tMap.get("existpdwealthOpenDayId").toString();
 	//					if (existPdwealthOpenId.equals("isnull")) {
 							Long pdWealthOpenDateSeq = null;
 							Date openDay = DateUtils.getDate(eachOpenDate);
 								// 校验
 								this.verifyWealthOpenDay(existPdwealthOpenId, pdWealthId, openDay);
 								pdWealthOpenDateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_OPEN_DATE");
 								newPdWealthOpenDate.setPdWealthId(pdWealth.getPdWealthId());
 								newPdWealthOpenDate.setPdWealthOpenDateId(pdWealthOpenDateSeq);
 								newPdWealthOpenDate.setFinancingScale(new BigDecimal(100000000));
 								newPdWealthOpenDate.setOpenDate(openDay);
 								//获取之前最大的开放日作为本次开放日的募集起期
 								Map paramMap = new HashMap();
 								paramMap.put("pdWealthId", pdWealthId);
 								paramMap.put("openDay", openDay);
 								Map resultMap = productServiceMapper.getMaxOpenDate(paramMap);
 								if(resultMap != null){
 									String maxOpenDate = resultMap.get("maxOpenDate").toString();
 									newPdWealthOpenDate.setInvestStartDate(DateUtils.getDate(maxOpenDate));
 									//募集止期默认为开放日提前两天的下午五点
 									Calendar c = Calendar.getInstance();
 									c.setTime(openDay);
 									c.set(Calendar.HOUR_OF_DAY, 17);
 									newPdWealthOpenDate.setInvestEndDate(DateUtils.calDate(c.getTime(), -2, "D"));
 								}
 								com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(newPdWealthOpenDate, loginInfo);
 								pdWealthOpenDateMapper.insert(newPdWealthOpenDate);
 	//					} else {
 							//防止重复提交
		/*				Date openDay = DateUtils.getDate(eachOpenDate);
						
						PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
						pdWealthOpenDateExample.createCriteria()
								.andPdWealthOpenDateIdEqualTo(new Long(existPdwealthOpenId)).andRcStateEqualTo("E");
						PDWealthOpenDate existPDWealthOpenDate = (PDWealthOpenDate)pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample).get(0);
						existPDWealthOpenDate.setPdWealthId(pdWealth.getPdWealthId());
						existPDWealthOpenDate.setFinancingScale(new BigDecimal(1000));
						existPDWealthOpenDate.setOpenDate(openDay);
						this.verifyWealthOpenDay(existPdwealthOpenId, existPDWealthOpenDate.getPdWealthId(), openDay);
						// 获取之前最大的开放日作为本次开放日的募集起期
						Map paramMap = new HashMap();
						paramMap.put("pdWealthId", pdWealthId);
						paramMap.put("openDay", openDay);
						Map resultMap = productServiceMapper.getMaxOpenDate(paramMap);
						if (resultMap != null) {
							String maxOpenDate = resultMap.get("maxOpenDate").toString();
							existPDWealthOpenDate.setInvestStartDate(DateUtils.getDate(maxOpenDate));
							// 募集止期默认为开放日提前两天的下午五点
							Calendar c = Calendar.getInstance();
							c.setTime(openDay);
							c.set(Calendar.HOUR_OF_DAY, 17);
							existPDWealthOpenDate.setInvestEndDate(DateUtils.calDate(c.getTime(), -2, "D"));
						}
						com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(existPDWealthOpenDate, loginInfo);
						pdWealthOpenDateMapper.updateByPrimaryKey(existPDWealthOpenDate);
 						}*/
				}	
 	 		}
 				//维护募集期
 				PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
 				pdWealthOpenDateExample.createCriteria().andPdWealthIdEqualTo(pdWealthId).andRcStateEqualTo("E");
 				List<PDWealthOpenDate> pdWealthOpenDates = pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample);
 				for(PDWealthOpenDate pdWealthOpenDate:pdWealthOpenDates){
 					Date lastOpenDate = pdWealthOpenDate.getOpenDate();
 					if(pdWealthOpenDate.getInvestStartDate() == null||"".equals(pdWealthOpenDate.getInvestStartDate())){
 						continue;
 					}
 					String investStartDate = pdWealthOpenDate.getInvestStartDate().toString();
 					Map paramMap = new HashMap();
					paramMap.put("pdWealthId", pdWealthId);
					paramMap.put("openDay", lastOpenDate);
					Map resultMap = productServiceMapper.getMaxOpenDate(paramMap);
					if(resultMap != null){
						String maxOpenDate = resultMap.get("maxOpenDate").toString();
						if(!investStartDate.equals(maxOpenDate)){
							pdWealthOpenDate.setInvestStartDate(DateUtils.getDate(maxOpenDate));
							BeanUtils.updateObjectSetOperateInfo(pdWealthOpenDate, loginInfo);
							pdWealthOpenDateMapper.updateByPrimaryKey(pdWealthOpenDate);
						}
					}
 				}
 				resultInfo.setSuccess(true);
				resultInfo.setMsg("保存成功！");
				resultInfo.setObj(newPdWealthOpenDate.getPdWealthOpenDateId());
				return resultInfo;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是:"+e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 校验该产品公布日期，该净值是否已经存在
	 * 
	 * @param existPdwealthOpenId
	 * @param pdwealthId
	 * @param openDay
	 */
	public void verifyWealthOpenDay(String existPdwealthOpenId, Long pdwealthId, Date openDay) {
		if (existPdwealthOpenId.equals("isnull")) {
			PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
			pdWealthOpenDateExample.createCriteria().andRcStateEqualTo("E").andOpenDateEqualTo(openDay)
					.andPdWealthIdEqualTo(pdwealthId);
			List<PDWealthOpenDate> pdwealthOpenDateList = pdWealthOpenDateMapper
					.selectByExample(pdWealthOpenDateExample);
			if (pdwealthOpenDateList != null && pdwealthOpenDateList.size() > 0) {
				throw new CisCoreException("该产品已经存在此公布日期，请重新增加！");
			}
		} else {
			PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
			List<PDWealthOpenDate> pdwealthOpenDateList = null;
			if (pdwealthId != null) {
				pdWealthOpenDateExample.createCriteria().andRcStateEqualTo("E").andOpenDateEqualTo(openDay)
						.andPdWealthIdEqualTo(new Long(pdwealthId))
						.andPdWealthOpenDateIdNotEqualTo(new Long(existPdwealthOpenId));
				pdwealthOpenDateList = pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample);
			}
			if (pdwealthOpenDateList != null && pdwealthOpenDateList.size() > 0) {
				throw new CisCoreException("该产品已经存在此公布日期，请重新增加！");
			}
		}
	}

	/**
	 * 开放日页面列表初始化
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getOpenDayPageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = productServiceMapper.openDayQueryListCount(paramMap);
		List<Map> resultList = productServiceMapper.openDayQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 删除开放日信息
	 */
	@Transactional
	public void deleteOpenDayInfo(Long uid, LoginInfo loginInfo) {
		PDWealthOpenDate pdWealthOpenDate = pdWealthOpenDateMapper.selectByPrimaryKey(uid);
		com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdWealthOpenDate, loginInfo);
		pdWealthOpenDateMapper.updateByPrimaryKey(pdWealthOpenDate);
	}

	/**
	 * 获取开放日修改的信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public Map getUpdateOpenDayListInfo(Long wealthOpenDateId) {
		Map paramMap = new HashMap();
		PDWealthOpenDate pdWealthOpenDate = pdWealthOpenDateMapper.selectByPrimaryKey(wealthOpenDateId);
		PDWealth pdWealth = pDWealthMapper.selectByPrimaryKey(pdWealthOpenDate.getPdWealthId());
		paramMap.put("openDate", DateUtils.getDateString(pdWealthOpenDate.getOpenDate()));
		paramMap.put("productId", pdWealth.getProductId().toString());
		paramMap.put("investStartDate", DateUtils.getDateTimeString(pdWealthOpenDate.getInvestStartDate()));
		paramMap.put("investEndDate", DateUtils.getDateTimeString(pdWealthOpenDate.getInvestEndDate()));
		if (pdWealthOpenDate.getFinancingScale() != null) {
			paramMap.put("financingScale",
					NumberUtils.divide(pdWealthOpenDate.getFinancingScale().toString(), "10000") + "");
		}
		return paramMap;
	}

	/**
	 * 保存修改的开放日信息
	 */
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public void updateOpenDaySave(Map tMap, LoginInfo loginInfo) {
		if (tMap != null) {
			String modifywealthOpenDateId = tMap.get("modifywealthOpenDateId").toString();
			String investStartDate = tMap.get("investStartDate").toString();
			String investEndDate = tMap.get("investEndDate").toString();
			String financingScale = "";
			if (tMap.containsKey("financingScale")) {
				financingScale = tMap.get("financingScale").toString();
			}
			Date openDayDate = null;
			try {
				String openDate = tMap.get("openDay").toString();
				openDayDate = DateUtils.getDate(openDate);
				PDWealthOpenDate pdWealthOpenDate = pdWealthOpenDateMapper
						.selectByPrimaryKey(new Long(modifywealthOpenDateId));
				this.verifyWealthOpenDay(modifywealthOpenDateId, pdWealthOpenDate.getPdWealthId(),
						openDayDate);
				pdWealthOpenDate.setOpenDate(openDayDate);
				if (financingScale != null && !"".equals(financingScale)) {
					pdWealthOpenDate.setFinancingScale(new BigDecimal(NumberUtils.multiply(financingScale, "10000")));
				}
				pdWealthOpenDate.setInvestStartDate(DateUtils.getDateTime(investStartDate));
				pdWealthOpenDate.setInvestEndDate(DateUtils.getDateTime(investEndDate));
				com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(pdWealthOpenDate, loginInfo);
				pdWealthOpenDateMapper.updateByPrimaryKey(pdWealthOpenDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 产品发布初始查询页面
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getReleasePageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = productServiceMapper.productReleaseQueryListCount(paramMap);
		List<Map> resultList = productServiceMapper.productReleaseQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 产品提交审核动作
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public void submitAudit(String pram, LoginInfo loginInfo) {
		//Long z = commonService.getSeqValByName("SEQ_T_PD_PRODUCT");
		PDProduct pdProductDB = new PDProduct();
		PDWealth pdWealthDB = new PDWealth();
		PDRisk pdRiskDB = new PDRisk();
		PDWealthIntroduceInfo PDWealthIntroduceInfoDB = new PDWealthIntroduceInfo();
		PDWealthSalesInfo pdWealthSalesInfoDB = new PDWealthSalesInfo();
		PDRiskIntroduceInfo PDRiskIntroduceInfoDB = new PDRiskIntroduceInfo();
		PDRiskSalesInfo pdRiskSalesInfoDB = new PDRiskSalesInfo();
		PDProduct newProduct = new PDProduct();
		if (pram != null) {
			// 先查出原来的数据
			pdProductDB = pDProductMapper.selectByPrimaryKey(new Long(pram));
			BeanUtils.copyProperties(pdProductDB, newProduct);
			// 更改主表Product并保存，再创建一条新PRODUCT记录，将状态置于"1-待审核" 状态，进入产品发布页面
			newProduct.setStatus("1");
			newProduct.setProductId(null);
			com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(pdProductDB, newProduct, loginInfo);
			pDProductMapper.updateByPrimaryKey(pdProductDB);
			pDProductMapper.insert(newProduct);
			// 如是财富产品只需要更改T_PD_WEALTH/T_PD_FACTOR
			if (pdProductDB.getProductType().equals("1") || pdProductDB.getProductType() == "1") {
				// 更改财富主表的Product_ID
				PDWealthExample pdWealthExample = new PDWealthExample();
				pdWealthExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(new Long(pram));
				if (pDWealthMapper.selectByExample(pdWealthExample) != null) {
					pdWealthDB = (PDWealth) pDWealthMapper.selectByExample(pdWealthExample).get(0);
					pdWealthDB.setProductId(newProduct.getProductId());
					pDWealthMapper.updateByPrimaryKey(pdWealthDB);
				}
				// 更改财富网销产品说明信息的Product_ID
				PDWealthIntroduceInfoExample PDWealthIntroduceInfoExample = new PDWealthIntroduceInfoExample();
				PDWealthIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(pram));
				List<PDWealthIntroduceInfo> PDWealthIntroduceInfos = pDWealthIntroduceInfoMapper
						.selectByExample(PDWealthIntroduceInfoExample);
				if (PDWealthIntroduceInfos != null && PDWealthIntroduceInfos.size() > 0) {
					PDWealthIntroduceInfoDB = (PDWealthIntroduceInfo) pDWealthIntroduceInfoMapper
							.selectByExample(PDWealthIntroduceInfoExample).get(0);
					PDWealthIntroduceInfoDB.setProductId(newProduct.getProductId());
					pDWealthIntroduceInfoMapper.updateByPrimaryKey(PDWealthIntroduceInfoDB);
				}
				// 更改财富网销产品营销信息的PRODOUCT_ID
				PDWealthSalesInfoExample pdWealthSalesInfoExample = new PDWealthSalesInfoExample();
				pdWealthSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(pram));
				List<PDWealthSalesInfo> pdWealthSalesInfos = pdWealthSalesInfoMapper
						.selectByExample(pdWealthSalesInfoExample);
				if (pdWealthSalesInfos != null && pdWealthSalesInfos.size() > 0) {
					pdWealthSalesInfoDB = (PDWealthSalesInfo) pdWealthSalesInfoMapper
							.selectByExample(pdWealthSalesInfoExample).get(0);
					pdWealthSalesInfoDB.setProductId(newProduct.getProductId());
					pdWealthSalesInfoMapper.updateByPrimaryKey(pdWealthSalesInfoDB);
				}
			} else {
				PDRiskExample pdRiskExample = new PDRiskExample();
				pdRiskExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(new Long(pram));
				if (pDRiskMapper.selectByExample(pdRiskExample) != null && pDRiskMapper.selectByExample(pdRiskExample).size()>0) {
					// 查出原来的信息进行修改相应的关联的产品主表ID
					pdRiskDB = (PDRisk) pDRiskMapper.selectByExample(pdRiskExample).get(0);
					pdRiskDB.setProductId(newProduct.getProductId());
					pDRiskMapper.updateByPrimaryKey(pdRiskDB);
				}
				// 更改保险网销产品说明信息的Product_ID
				PDRiskIntroduceInfoExample PDRiskIntroduceInfoExample = new PDRiskIntroduceInfoExample();
				PDRiskIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(pram));
				List<PDRiskIntroduceInfo> PDRiskIntroduceInfos = pdRiskIntroduceInfoMapper
						.selectByExample(PDRiskIntroduceInfoExample);
				if (PDRiskIntroduceInfos != null && PDRiskIntroduceInfos.size() > 0) {
					PDRiskIntroduceInfoDB = (PDRiskIntroduceInfo) pdRiskIntroduceInfoMapper
							.selectByExample(PDRiskIntroduceInfoExample).get(0);
					PDRiskIntroduceInfoDB.setProductId(newProduct.getProductId());
					pdRiskIntroduceInfoMapper.updateByPrimaryKey(PDRiskIntroduceInfoDB);
				}
				// 更改保险网销产品营销信息的PRODOUCT_ID
				PDRiskSalesInfoExample pdRiskSalesInfoExample = new PDRiskSalesInfoExample();
				pdRiskSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(pram));
				List<PDRiskSalesInfo> pdRiskSalesInfos = pDRiskSalesInfoMapper.selectByExample(pdRiskSalesInfoExample);
				if (pdRiskSalesInfos != null && pdRiskSalesInfos.size() > 0) {
					pdRiskSalesInfoDB = (PDRiskSalesInfo) pDRiskSalesInfoMapper.selectByExample(pdRiskSalesInfoExample).get(0);
					pdRiskSalesInfoDB.setProductId(newProduct.getProductId());
					pDRiskSalesInfoMapper.updateByPrimaryKey(pdRiskSalesInfoDB);
				}
			}
		}
		// 更改T_PD_FACTOR
		PDFactorExample pdFactorExample = new PDFactorExample();
		pdFactorExample.createCriteria().andRcStateEqualTo("E").andPdIdEqualTo(new Long(pram));
		List pdFactorList = (List) pDFactorMapper.selectByExample(pdFactorExample);
		if (pdFactorList != null) {
			for (int i = 0; i < pdFactorList.size(); i++) {
				PDFactor pdFactorDB = (PDFactor) pdFactorList.get(i);
				pdFactorDB.setPdId(newProduct.getProductId());
				pDFactorMapper.updateByPrimaryKey(pdFactorDB);
			}
		}
		// 更改文件上传的协议信息表
		DefFileInfoExample defFileInfoExample = new DefFileInfoExample();
		// 01-产品模块的协议文件类型
		defFileInfoExample.createCriteria().andBusinessNoEqualTo(new Long(pram)).andBusinessTypeEqualTo("01")
				.andRcStateEqualTo("E");
		List<DefFileInfo> defFileInfoList = defFileInfoMapper.selectByExample(defFileInfoExample);
		if (defFileInfoList != null && defFileInfoList.size() > 0) {
			for (DefFileInfo defFileInfo : defFileInfoList) {
				defFileInfo.setBusinessNo(newProduct.getProductId());
				defFileInfoMapper.updateByPrimaryKey(defFileInfo);
			}
		}
		// 更改产品附文本信息表
		PDUeditorExample pdUeditorExample = new PDUeditorExample();
		pdUeditorExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
				.andProductIdEqualTo(new Long(pram));
		List<PDUeditor> pdUeditors = pDUeditorMapper.selectByExample(pdUeditorExample);
		if (pdUeditors != null && pdUeditors.size() > 0) {
			PDUeditor pdUeditor = pdUeditors.get(0);
			pdUeditor.setProductId(newProduct.getProductId());
			pDUeditorMapper.updateByPrimaryKey(pdUeditor);
		}
	}

	/**
	 * 产品修改申请动作
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public void modifyApply(String pram, LoginInfo loginInfo) {
		Long newPdProdcutSeq = commonService.getSeqValByName("SEQ_T_PD_PRODUCT");
		// 查询从数据库已有的对象
		PDProduct pdProductDB = new PDProduct();
		PDWealth pdWealthDB = new PDWealth();
		PDRisk pdRiskDB = new PDRisk();
		PDWealthIntroduceInfo PDWealthIntroduceInfoDB = new PDWealthIntroduceInfo();
		PDWealthSalesInfo pdWealthSalesInfoDB = new PDWealthSalesInfo();
		PDRiskIntroduceInfo PDRiskIntroduceInfoDB = new PDRiskIntroduceInfo();
		PDRiskSalesInfo pdRiskSalesInfoDB = new PDRiskSalesInfo();
		// 存放新的对象
		PDProduct newProduct = new PDProduct();
		if (pram != null) {
			pdProductDB = pDProductMapper.selectByPrimaryKey(new Long(pram));
			BeanUtils.copyProperties(pdProductDB, newProduct);
			// 更改主表Product并保存，再创建一条新PRODUCT记录，将状态置于"0-待提交" 状态，进入产品设置页面
			newProduct.setStatus("0");
			newProduct.setProductId(newPdProdcutSeq);
			com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(pdProductDB, newProduct, loginInfo);
			// 保存更改的数据
			pDProductMapper.updateByPrimaryKey(pdProductDB);
			pDProductMapper.insert(newProduct);
			if (pdProductDB.getProductType().equals("1") || pdProductDB.getProductType() == "1") {
				// 如是财富产品只需要更改T_PD_WEALTH/T_PD_FACTOR
				PDWealthExample pdWealthExample = new PDWealthExample();
				pdWealthExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(new Long(pram));
				if (pDWealthMapper.selectByExample(pdWealthExample) != null) {
					pdWealthDB = (PDWealth) pDWealthMapper.selectByExample(pdWealthExample).get(0);
					pdWealthDB.setProductId(newProduct.getProductId());
					pDWealthMapper.updateByPrimaryKey(pdWealthDB);
				}
				// 更改财富网销产品说明信息的Product_ID
				PDWealthIntroduceInfoExample PDWealthIntroduceInfoExample = new PDWealthIntroduceInfoExample();
				PDWealthIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(pram));
				List<PDWealthIntroduceInfo> PDWealthIntroduceInfos = pDWealthIntroduceInfoMapper
						.selectByExample(PDWealthIntroduceInfoExample);
				if (PDWealthIntroduceInfos != null && PDWealthIntroduceInfos.size() > 0) {
					PDWealthIntroduceInfoDB = (PDWealthIntroduceInfo) pDWealthIntroduceInfoMapper
							.selectByExample(PDWealthIntroduceInfoExample).get(0);
					PDWealthIntroduceInfoDB.setProductId(newProduct.getProductId());
					pDWealthIntroduceInfoMapper.updateByPrimaryKey(PDWealthIntroduceInfoDB);
				}
				// 更改财富网销产品营销信息的PRODOUCT_ID
				PDWealthSalesInfoExample pdWealthSalesInfoExample = new PDWealthSalesInfoExample();
				List<PDWealthSalesInfo> pdWealthSalesInfos = pdWealthSalesInfoMapper
						.selectByExample(pdWealthSalesInfoExample);
				if (pdWealthSalesInfos != null && pdWealthSalesInfos.size() > 0) {
					pdWealthSalesInfoDB = (PDWealthSalesInfo) pdWealthSalesInfoMapper
							.selectByExample(pdWealthSalesInfoExample).get(0);
					pdWealthSalesInfoDB.setProductId(newProduct.getProductId());
					pdWealthSalesInfoMapper.updateByPrimaryKey(pdWealthSalesInfoDB);
				}
			} else {
				PDRiskExample pdRiskExample = new PDRiskExample();
				pdRiskExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(new Long(pram));
				if (pDRiskMapper.selectByExample(pdRiskExample) != null) {
					pdRiskDB = (PDRisk) pDRiskMapper.selectByExample(pdRiskExample).get(0);
					pdRiskDB.setProductId(newProduct.getProductId());
					pDRiskMapper.updateByPrimaryKey(pdRiskDB);
				}
				// 更改保险网销产品说明信息的Product_ID
				PDRiskIntroduceInfoExample PDRiskIntroduceInfoExample = new PDRiskIntroduceInfoExample();
				PDRiskIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(pram));
				List<PDRiskIntroduceInfo> PDRiskIntroduceInfos = pdRiskIntroduceInfoMapper
						.selectByExample(PDRiskIntroduceInfoExample);
				if (PDRiskIntroduceInfos != null && PDRiskIntroduceInfos.size() > 0) {
					PDRiskIntroduceInfoDB = (PDRiskIntroduceInfo) pdRiskIntroduceInfoMapper
							.selectByExample(PDRiskIntroduceInfoExample).get(0);
					PDRiskIntroduceInfoDB.setProductId(newProduct.getProductId());
					pdRiskIntroduceInfoMapper.updateByPrimaryKey(PDRiskIntroduceInfoDB);
				}
				// 更改保险网销产品营销信息的PRODOUCT_ID
				PDRiskSalesInfoExample pdRiskSalesInfoExample = new PDRiskSalesInfoExample();
				pdRiskSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(pram));
				List<PDRiskSalesInfo> pdRiskSalesInfos = pDRiskSalesInfoMapper.selectByExample(pdRiskSalesInfoExample);
				if (pdRiskSalesInfos != null && pdRiskSalesInfos.size() > 0) {
					pdRiskSalesInfoDB = (PDRiskSalesInfo) pDRiskSalesInfoMapper.selectByExample(pdRiskSalesInfoExample)
							.get(0);
					pdRiskSalesInfoDB.setProductId(newProduct.getProductId());
					pDRiskSalesInfoMapper.updateByPrimaryKey(pdRiskSalesInfoDB);
				}
			}
			// 更改T_PD_FACTOR
			PDFactorExample pdFactorExample = new PDFactorExample();
			pdFactorExample.createCriteria().andRcStateEqualTo("E").andPdIdEqualTo(new Long(pram));
			List pdFactorList = (List) pDFactorMapper.selectByExample(pdFactorExample);
			if (pdFactorList != null) {
				for (int i = 0; i < pdFactorList.size(); i++) {
					PDFactor pdFactorDB = (PDFactor) pdFactorList.get(i);
					pdFactorDB.setPdId(newProduct.getProductId());
					pDFactorMapper.updateByPrimaryKey(pdFactorDB);
				}
			}
			// 更改文件上传的协议信息表
			DefFileInfoExample defFileInfoExample = new DefFileInfoExample();
			// 01-产品模块的协议文件类型
			defFileInfoExample.createCriteria().andBusinessNoEqualTo(new Long(pram)).andBusinessTypeEqualTo("01")
					.andRcStateEqualTo("E");
			List<DefFileInfo> defFileInfoList = defFileInfoMapper.selectByExample(defFileInfoExample);
			if (defFileInfoList != null && defFileInfoList.size() > 0) {
				for (DefFileInfo defFileInfo : defFileInfoList) {
					defFileInfo.setBusinessNo(newProduct.getProductId());
					defFileInfoMapper.updateByPrimaryKey(defFileInfo);
				}
			}
			// 更改产品附文本信息表
			PDUeditorExample pdUeditorExample = new PDUeditorExample();
			pdUeditorExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
					.andProductIdEqualTo(new Long(pram));
			List<PDUeditor> pdUeditors = pDUeditorMapper.selectByExample(pdUeditorExample);
			if (pdUeditors != null && pdUeditors.size() > 0) {
				PDUeditor pdUeditor = pdUeditors.get(0);
				pdUeditor.setProductId(newProduct.getProductId());
				pDUeditorMapper.updateByPrimaryKey(pdUeditor);
			}

		}
	}

	/**
	 * 产品发布动作
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public ResultInfo productRelease(String pram, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 先创建一个新数据的Sequence和新对象
		//Long newPdProdcutSeq = commonService.getSeqValByName("SEQ_T_PD_PRODUCT");
		PDProduct pdProductDB = new PDProduct();
		PDWealth pdWealthDB = new PDWealth();
		PDRisk pdRiskDB = new PDRisk();
		PDWealthIntroduceInfo PDWealthIntroduceInfoDB = new PDWealthIntroduceInfo();
		PDWealthSalesInfo pdWealthSalesInfoDB = new PDWealthSalesInfo();
		PDRiskIntroduceInfo PDRiskIntroduceInfoDB = new PDRiskIntroduceInfo();
		PDRiskSalesInfo pdRiskSalesInfoDB = new PDRiskSalesInfo();
		PDProduct newProduct = new PDProduct();
		if (pram != null) {
			// 先查出原来的数据
			pdProductDB = pDProductMapper.selectByPrimaryKey(new Long(pram));
			BeanUtils.copyProperties(pdProductDB, newProduct);
			// 更改主表Product并保存，再创建一条新PRODUCT记录，将状态置于"2-发布审核" 状态
			newProduct.setStatus("2");
			newProduct.setProductId(null);
			com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(pdProductDB, newProduct, loginInfo);
			// 保存更改的数据
			pDProductMapper.updateByPrimaryKey(pdProductDB);
			pDProductMapper.insert(newProduct);
			if (pdProductDB.getProductType().equals("1") || pdProductDB.getProductType() == "1") {
				// 如是财富产品只需要更改T_PD_WEALTH/T_PD_FACTOR
				PDWealthExample pdWealthExample = new PDWealthExample();
				pdWealthExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(new Long(pram));
				List<PDWealth> pdWealths = pDWealthMapper.selectByExample(pdWealthExample);
				if (pdWealths != null && pdWealths.size() > 0) {
					pdWealthDB = (PDWealth) pDWealthMapper.selectByExample(pdWealthExample).get(0);
					pdWealthDB.setProductId(newProduct.getProductId());
					pDWealthMapper.updateByPrimaryKey(pdWealthDB);
				}
				// 更改财富网销产品说明信息的Product_ID
				PDWealthIntroduceInfoExample PDWealthIntroduceInfoExample = new PDWealthIntroduceInfoExample();
				PDWealthIntroduceInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
						.andProductIdEqualTo(new Long(pram));
				List<PDWealthIntroduceInfo> PDWealthIntroduceInfos = pDWealthIntroduceInfoMapper
						.selectByExample(PDWealthIntroduceInfoExample);
				if (PDWealthIntroduceInfos != null && PDWealthIntroduceInfos.size() > 0) {
					PDWealthIntroduceInfoDB = (PDWealthIntroduceInfo) pDWealthIntroduceInfoMapper
							.selectByExample(PDWealthIntroduceInfoExample).get(0);
					PDWealthIntroduceInfoDB.setProductId(newProduct.getProductId());
					pDWealthIntroduceInfoMapper.updateByPrimaryKey(PDWealthIntroduceInfoDB);
				}
				// 更改财富网销产品营销信息的PRODOUCT_ID
				PDWealthSalesInfoExample pdWealthSalesInfoExample = new PDWealthSalesInfoExample();
				pdWealthSalesInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
						.andProductIdEqualTo(new Long(pram));
				List<PDWealthSalesInfo> pdWealthSalesInfos = pdWealthSalesInfoMapper
						.selectByExample(pdWealthSalesInfoExample);
				if (pdWealthSalesInfos != null && pdWealthSalesInfos.size() > 0) {
					pdWealthSalesInfoDB = (PDWealthSalesInfo) pdWealthSalesInfoMapper
							.selectByExample(pdWealthSalesInfoExample).get(0);
					pdWealthSalesInfoDB.setProductId(newProduct.getProductId());
					pdWealthSalesInfoMapper.updateByPrimaryKey(pdWealthSalesInfoDB);
				}
			} else {
				PDRiskExample pdRiskExample = new PDRiskExample();
				pdRiskExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(new Long(pram));
				if (pDRiskMapper.selectByExample(pdRiskExample) != null&&pDRiskMapper.selectByExample(pdRiskExample).size()>0) {
					pdRiskDB = (PDRisk) pDRiskMapper.selectByExample(pdRiskExample).get(0);
					pdRiskDB.setProductId(newProduct.getProductId());
					pDRiskMapper.updateByPrimaryKey(pdRiskDB);
				}
				// 更改保险网销产品说明信息的Product_ID
				PDRiskIntroduceInfoExample PDRiskIntroduceInfoExample = new PDRiskIntroduceInfoExample();
				PDRiskIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(pram));
				List<PDRiskIntroduceInfo> PDRiskIntroduceInfos = pdRiskIntroduceInfoMapper
						.selectByExample(PDRiskIntroduceInfoExample);
				if (PDRiskIntroduceInfos != null && PDRiskIntroduceInfos.size() > 0) {
					PDRiskIntroduceInfoDB = (PDRiskIntroduceInfo) pdRiskIntroduceInfoMapper
							.selectByExample(PDRiskIntroduceInfoExample).get(0);
					PDRiskIntroduceInfoDB.setProductId(newProduct.getProductId());
					pdRiskIntroduceInfoMapper.updateByPrimaryKey(PDRiskIntroduceInfoDB);
				}
				// 更改保险网销产品营销信息的PRODOUCT_ID
				PDRiskSalesInfoExample pdRiskSalesInfoExample = new PDRiskSalesInfoExample();
				pdRiskSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(pram));
				List<PDRiskSalesInfo> pdRiskSalesInfos = pDRiskSalesInfoMapper.selectByExample(pdRiskSalesInfoExample);
				if (pdRiskSalesInfos != null && pdRiskSalesInfos.size() > 0) {
					pdRiskSalesInfoDB = (PDRiskSalesInfo) pDRiskSalesInfoMapper.selectByExample(pdRiskSalesInfoExample)
							.get(0);
					pdRiskSalesInfoDB.setProductId(newProduct.getProductId());
					pDRiskSalesInfoMapper.updateByPrimaryKey(pdRiskSalesInfoDB);
				}
			}
			// 更改T_PD_FACTOR
			PDFactorExample pdFactorExample = new PDFactorExample();
			pdFactorExample.createCriteria().andRcStateEqualTo("E").andPdIdEqualTo(new Long(pram));
			List pdFactorList = (List) pDFactorMapper.selectByExample(pdFactorExample);
			if (pdFactorList != null) {
				for (int i = 0; i < pdFactorList.size(); i++) {
					PDFactor pdFactorDB = (PDFactor) pdFactorList.get(i);
					pdFactorDB.setPdId(newProduct.getProductId());
					pDFactorMapper.updateByPrimaryKey(pdFactorDB);
				}
			}
			// 更改文件上传的协议信息表
			DefFileInfoExample defFileInfoExample = new DefFileInfoExample();
			// 01-产品模块的协议文件类型
			defFileInfoExample.createCriteria().andBusinessNoEqualTo(new Long(pram)).andBusinessTypeEqualTo("01")
					.andRcStateEqualTo("E");
			List<DefFileInfo> defFileInfoList = defFileInfoMapper.selectByExample(defFileInfoExample);
			if (defFileInfoList != null && defFileInfoList.size() > 0) {
				for (DefFileInfo defFileInfo : defFileInfoList) {
					defFileInfo.setBusinessNo(newProduct.getProductId());
					defFileInfoMapper.updateByPrimaryKey(defFileInfo);
				}
			}
			// 更改产品附文本信息表
			PDUeditorExample pdUeditorExample = new PDUeditorExample();
			pdUeditorExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
					.andProductIdEqualTo(new Long(pram));
			List<PDUeditor> pdUeditors = pDUeditorMapper.selectByExample(pdUeditorExample);
			if (pdUeditors != null && pdUeditors.size() > 0) {
				PDUeditor pdUeditor = pdUeditors.get(0);
				pdUeditor.setProductId(newProduct.getProductId());
				pDUeditorMapper.updateByPrimaryKey(pdUeditor);
			}
			//产品发布触发邮件
			resultInfo = sendProductReleaseEmail(pram,loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setObj(null);
			resultInfo.setMsg("发布成功");
		}

		return resultInfo;
	}

	/**
	 * 发送产品发布邮件
	 * @param tradeCallBackInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendProductReleaseEmail(String productId,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createProductReleaseEmail(productId,loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		List<SysEmailInfo> sysEmailInfoList = (List<SysEmailInfo>)resultInfo.getObj();
		//发送短信
		for (SysEmailInfo sysEmailInfo2 : sysEmailInfoList) {
			resultInfo = sendProductReleaseEmail(sysEmailInfo2,loginInfo);
		}
		
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	
	/**
	 * 创建产品发布邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createProductReleaseEmail(String productId,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//获取交易回访邮件所需数据
		PDProduct productInfo = pDProductMapper.selectByPrimaryKey(Long.parseLong(productId));
		Map<String,String> productReleaseEmailData = new HashMap<String, String>();
		productReleaseEmailData.put("productName", productInfo.getProductName());
		DefUser userName = defUserMapper.selectByPrimaryKey(loginInfo.getUserId());
		String loginUserName = userName.getUserName();
		productReleaseEmailData.put("loginUserName", loginUserName);
		if(productReleaseEmailData==null||productReleaseEmailData.size()==0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到交易赎回相关数据，创建邮件失败！");
			return resultInfo;
		}
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//产品成立通知邮件
		sysEmailInfo.setEmailType("05");
		sysEmailInfo.setEmailRelationNo(Long.parseLong(productId));
		//获取不同邮件类型的邮箱地址
		String emailType = sysEmailInfo.getEmailType();
		SysEmailAdressExample sysEmailAdressExample = new SysEmailAdressExample();
		SysEmailAdressExample.Criteria sysEmailAdressCriteria = sysEmailAdressExample.createCriteria();
		sysEmailAdressCriteria.andEmailTypeEqualTo(emailType);
		List<SysEmailAdress> email = sysEmailAdressMapper.selectByExample(sysEmailAdressExample);
		//List<Map<String, String>> emailList = JsonUtils.listObjectToListMap(email);
		List<SysEmailInfo> resultList = new ArrayList<SysEmailInfo>();
		SysEmailInfo sysEmailInfo1 = null;
		for (int i = 0; i < email.size(); i++) {
			sysEmailInfo1 = new SysEmailInfo();
			//产品成立通知邮件
			sysEmailInfo1.setEmailType("05");
			sysEmailInfo1.setEmailRelationNo(Long.parseLong(productId));
			sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
			sysEmailInfo1.setEmailTitle("产品发布通知");
			//创建邮件内容
				String mailContent = createProductReleaseEmailContent(productReleaseEmailData);
				sysEmailInfo1.setEmailContent(mailContent);
			//01-未发送
			sysEmailInfo1.setEmailStatus("01");
			sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
			BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1,loginInfo);
			Long sysEmailInfoId = commonService.getSeqValByName("SEQ_T_SYS_EMAIL_INFO");
			sysEmailInfo1.setSysEmailInfoId(sysEmailInfoId);
			sysEmailInfoMapper.insert(sysEmailInfo1);
			resultList.add(sysEmailInfo1);
		}
		
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品发布邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
	}
	
	
	/**
	 * 生成产品发布邮件类容
	 * @param callBackEmailData
	 * @return
	 */
	private String createProductReleaseEmailContent(Map<String,String> productReleaseEmailData){
		StringBuffer mailContent = new StringBuffer();

		mailContent.append("用户“");
		mailContent.append(productReleaseEmailData.get("loginUserName"));
		mailContent.append("”于“");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		mailContent.append(df.format(new Date()));
		mailContent.append("”发布产品《");
		mailContent.append(productReleaseEmailData.get("productName"));
		mailContent.append("》，现可正式预约。");
		return mailContent.toString();
	}
	/**
	 * 发送邮件
	 * @return
	 */
	@Transactional
	private ResultInfo sendProductReleaseEmail(SysEmailInfo sysEmailInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		DefCodeExample defCodeExample = new DefCodeExample();
		DefCodeExample.Criteria defCodeCriteria = defCodeExample.createCriteria();
		defCodeCriteria.andCodeTypeEqualTo("sendMailParam");
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		Map<String,String> mailHostParam = new HashMap<String,String>();
		if (defCodeList!=null&&defCodeList.size()>0) {
			for(DefCode defCode:defCodeList){
				mailHostParam.put(defCode.getCodeAlias(), defCode.getCodeName());
			}
		}else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到邮件发送服务器相关参数！");
			return resultInfo;
		}
		if(sysEmailInfo!=null){
			String address = sysEmailInfo.getEmailAddress();
			String title = sysEmailInfo.getEmailTitle();
			String content = sysEmailInfo.getEmailContent();
			//String othEmailAddress = sysEmailInfo.getOthEmailAddress();
			resultInfo = sendEmailService.sendEmail(address, title, content, mailHostParam);
			if (resultInfo.isSuccess()) {
				sysEmailInfo.setEmailSendTime(DateUtils.getCurrentTimestamp());
				//02-已发送
				sysEmailInfo.setEmailStatus("02");
			}else {
				//03-发送失败
				sysEmailInfo.setEmailStatus("03");
			}
			BeanUtils.updateObjectSetOperateInfo(sysEmailInfo, loginInfo);
			sysEmailInfoMapper.updateByPrimaryKey(sysEmailInfo);
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	
	
	/**
	 * 获取产品基本信息
	 */
	@Transactional
	public ResultInfo queryProductBasicInfo(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> productInfoMap = new HashMap<String, Object>();
		try {
			// 获取需要修改的产品主表信息T_PD_PRODUCT
			PDProduct modifyProduct = (PDProduct) pDProductMapper.selectByPrimaryKey(new Long(param));
			productInfoMap.put("modifyBasicProduct", JsonUtils.objectToMap(modifyProduct));
			productInfoMap.put("modifyStatus", modifyProduct.getStatus());
			resultInfo.setObj(productInfoMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取产品基本信息成功！");
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品基本信息失败！");
		}
		return resultInfo;
	}

	/**
	 * 获取产品互联网信息
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public ResultInfo queryProductInternetInfo(Map tMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> productInternetInfoMap = new HashMap<String, Object>();
		try {
			String productId = tMap.get("modifyProductId").toString();
			String productType = tMap.get("modifyProductType").toString();
			if (productType.equals("1")) {
				// 查找财富产品说明信息
				PDWealthIntroduceInfoExample PDWealthIntroduceInfoExample = new PDWealthIntroduceInfoExample();
				PDWealthIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDWealthIntroduceInfo> PDWealthIntroduceInfos = pDWealthIntroduceInfoMapper
						.selectByExample(PDWealthIntroduceInfoExample);
				if (PDWealthIntroduceInfos.size() > 0) {
					PDWealthIntroduceInfo PDWealthIntroduceInfo = (PDWealthIntroduceInfo) PDWealthIntroduceInfos.get(0);
					productInternetInfoMap.put("PDWealthIntroduceInfo", JsonUtils.objectToMap(PDWealthIntroduceInfo));
				}
				// 查找财富产品营销信息
				PDWealthSalesInfoExample pdWealthSalesInfoExample = new PDWealthSalesInfoExample();
				pdWealthSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDWealthSalesInfo> pdWealthSalesInfos = pdWealthSalesInfoMapper
						.selectByExample(pdWealthSalesInfoExample);
				if (pdWealthSalesInfos.size() > 0) {
					PDWealthSalesInfo pdWealthSalesInfo = (PDWealthSalesInfo) pdWealthSalesInfos.get(0);
					if (pdWealthSalesInfo.getPayWay() != null && pdWealthSalesInfo.getPayWay() != "") {
						pdWealthSalesInfo.setPayWay(StringUtils.formatcomma(pdWealthSalesInfo.getPayWay()));
					}
					if (pdWealthSalesInfo.getSaleChnnel() != null && pdWealthSalesInfo.getSaleChnnel() != "") {
						pdWealthSalesInfo.setSaleChnnel(StringUtils.formatcomma(pdWealthSalesInfo.getSaleChnnel()));
					}
					if (pdWealthSalesInfo.getPromotionWay() != null && pdWealthSalesInfo.getPromotionWay() != "") {
						pdWealthSalesInfo.setPromotionWay(StringUtils.formatcomma(pdWealthSalesInfo.getPromotionWay()));
					}
					productInternetInfoMap.put("pdWealthSalesInfo", JsonUtils.objectToMap(pdWealthSalesInfo));
				}
			} else {
				// 查找保险产品说明信息
				PDRiskIntroduceInfoExample PDRiskIntroduceInfoExample = new PDRiskIntroduceInfoExample();
				PDRiskIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDRiskIntroduceInfo> PDRiskIntroduceInfos = pdRiskIntroduceInfoMapper
						.selectByExample(PDRiskIntroduceInfoExample);
				if (PDRiskIntroduceInfos.size() > 0) {
					PDRiskIntroduceInfo PDRiskIntroduceInfo = (PDRiskIntroduceInfo) PDRiskIntroduceInfos.get(0);
					productInternetInfoMap.put("PDRiskIntroduceInfo", JsonUtils.objectToMap(PDRiskIntroduceInfo));
				}
				// 查询保险营销信息
				PDRiskSalesInfoExample pdRiskSalesInfoExample = new PDRiskSalesInfoExample();
				pdRiskSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDRiskSalesInfo> pdRiskSalesInfos = pDRiskSalesInfoMapper.selectByExample(pdRiskSalesInfoExample);
				if (pdRiskSalesInfos.size() > 0) {
					PDRiskSalesInfo pdRiskSalesInfo = (PDRiskSalesInfo) pdRiskSalesInfos.get(0);
					if (pdRiskSalesInfo.getPayWay() != null && pdRiskSalesInfo.getPayWay() != "") {
						pdRiskSalesInfo.setPayWay(StringUtils.formatcomma(pdRiskSalesInfo.getPayWay()));
					}
					if (pdRiskSalesInfo.getSaleChnnel() != null && pdRiskSalesInfo.getSaleChnnel() != "") {
						pdRiskSalesInfo.setSaleChnnel(StringUtils.formatcomma(pdRiskSalesInfo.getSaleChnnel()));
					}
					if (pdRiskSalesInfo.getPromotionWay() != null && pdRiskSalesInfo.getPromotionWay() != "") {
						pdRiskSalesInfo.setPromotionWay(StringUtils.formatcomma(pdRiskSalesInfo.getPromotionWay()));
					}
					productInternetInfoMap.put("pdRiskSalesInfo", JsonUtils.objectToMap(pdRiskSalesInfo));
				}
			}
			resultInfo.setObj(productInternetInfoMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取产品网销信息成功！");
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品网销信息失败！");
		}
		return resultInfo;
	}

	/**
	 * 修改产品基本信息
	 */
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public String updatePrdocutBasicInfo(Map tMap, LoginInfo loginInfo) {
		// 校验该产品编码是否重复
		String existProductId = tMap.get("modifyProductId").toString();
		PDProduct pdProduct = (PDProduct) tMap.get("pDProductSchema");
		if (existProductId != null) {
			// 校验修改的产品编码是否重复
			verifyProductCode(existProductId, pdProduct.getProductCode());
			// 查找已经存在的产品主表,修改相应的产品信息
			PDProductExample pdProductExample = new PDProductExample();
			pdProductExample.createCriteria().andProductIdEqualTo(new Long(existProductId))
					.andRcStateEqualTo("E");
			PDProduct existProduct = (PDProduct) pDProductMapper.selectByExample(pdProductExample).get(0);
			existProduct.setProductShortName(pdProduct.getProductShortName());
			existProduct.setAgencyComId(pdProduct.getAgencyComId());
			existProduct.setProductCode(pdProduct.getProductCode());
			existProduct.setProductName(pdProduct.getProductName());
			existProduct.setIntroduceDate(pdProduct.getIntroduceDate());
			existProduct.setIsInviteCode(pdProduct.getIsInviteCode());
			existProduct.setIsOrder(pdProduct.getIsOrder());
			existProduct.setSalesStatus(pdProduct.getSalesStatus());
			existProduct.setRemark(pdProduct.getRemark());
			existProduct.setProductManager(pdProduct.getProductManager());
			com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(existProduct, loginInfo);
			pDProductMapper.updateByPrimaryKey(existProduct);
		}
		return existProductId;
	}

	/** 修改产品网销信息 **/
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public ResultInfo SaveUpdatePrdocutInternetInfo(Map tMap, LoginInfo loginInfo) {
		String productId = tMap.get("productId").toString();
		String productType = tMap.get("productType").toString();
		ResultInfo resultInfo = new ResultInfo();
		if (productId != null) {
			if (productType.equals("1")) {
				PDWealthIntroduceInfo PDWealthIntroduceInfoExist = null;
				PDWealthSalesInfo pdWealthSalesInfoExist = null;
				// 先查询到原来的财富网销产品说明信息,在重新插入一条新的记录
				PDWealthIntroduceInfoExample PDWealthIntroduceInfoExample = new PDWealthIntroduceInfoExample();
				PDWealthIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDWealthIntroduceInfo> PDWealthIntroduceInfoList = pDWealthIntroduceInfoMapper
						.selectByExample(PDWealthIntroduceInfoExample);
				if (PDWealthIntroduceInfoList != null && PDWealthIntroduceInfoList.size() > 0) {
					PDWealthIntroduceInfoExist = (PDWealthIntroduceInfo) PDWealthIntroduceInfoList.get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(PDWealthIntroduceInfoExist, loginInfo);
					pDWealthIntroduceInfoMapper.updateByPrimaryKey(PDWealthIntroduceInfoExist);
				}
				Long wealthIntroduceInfoSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_INTRODUCE_INFO");
				PDWealthIntroduceInfo PDWealthIntroduceInfo = (PDWealthIntroduceInfo) tMap.get("PDWealthIntroduceInfo");
				PDWealthIntroduceInfo.setPdWealthIntroduceInfoId(wealthIntroduceInfoSeq);
				PDWealthIntroduceInfo.setProductId(new Long(productId));
				PDWealthIntroduceInfo pdWealthSEOIntroductInfo = (PDWealthIntroduceInfo) tMap
						.get("pdWealthSEOIntroductInfo");
				PDWealthIntroduceInfo.setProductTitle(pdWealthSEOIntroductInfo.getProductTitle());// 产品标题
				PDWealthIntroduceInfo.setProductKeyword(pdWealthSEOIntroductInfo.getProductKeyword());// 产品关键字
				PDWealthIntroduceInfo.setProductDesc(pdWealthSEOIntroductInfo.getProductDesc());// 产品描述
				// 存在的话重新删除并插入
				if (PDWealthIntroduceInfoList != null && PDWealthIntroduceInfoList.size() > 0) {
					com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(PDWealthIntroduceInfoExist,
							PDWealthIntroduceInfo, loginInfo);
					pDWealthIntroduceInfoMapper.insert(PDWealthIntroduceInfo);// 插入财富产品说明信息
				} else {
					// 不存在就直接插入
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(PDWealthIntroduceInfo, loginInfo);
					pDWealthIntroduceInfoMapper.insert(PDWealthIntroduceInfo);
				}
				// 先查询到原来的才网销产品营销信息,在重新插入一条新的记录
				PDWealthSalesInfoExample pdWealthSalesInfoExample = new PDWealthSalesInfoExample();
				pdWealthSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDWealthSalesInfo> pdWealthSalesInfoList = pdWealthSalesInfoMapper
						.selectByExample(pdWealthSalesInfoExample);
				if (pdWealthSalesInfoList != null && pdWealthSalesInfoList.size() > 0) {
					pdWealthSalesInfoExist = (PDWealthSalesInfo) pdWealthSalesInfoList.get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdWealthSalesInfoExist, loginInfo);
					pdWealthSalesInfoMapper.updateByPrimaryKey(pdWealthSalesInfoExist);
				}
				Long wealthSalesInfoSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_SALES_INFO");
				PDWealthSalesInfo pdWealthSalesInfo = (PDWealthSalesInfo) tMap.get("pdWealthSalesInfo");
				pdWealthSalesInfo.setPdWealthSalesInfoId(wealthSalesInfoSeq);
				pdWealthSalesInfo.setProductId(new Long(productId));
				if (pdWealthSalesInfoList != null && pdWealthSalesInfoList.size() > 0) {
					com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(pdWealthSalesInfoExist,
							pdWealthSalesInfo, loginInfo);
					pdWealthSalesInfoMapper.insert(pdWealthSalesInfo);// 插入财富产品营销信息
				} else {
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdWealthSalesInfo, loginInfo);
					pdWealthSalesInfoMapper.insert(pdWealthSalesInfo);// 插入财富产品营销信息
				}
			} else {
				PDRiskIntroduceInfo PDRiskIntroduceInfoExist = null;
				PDRiskSalesInfo pdRiskSalesInfoExist = null;
				// 先查询到原来的保险网销产品说明信息,在重新插入一条新的记录
				PDRiskIntroduceInfoExample PDRiskIntroduceInfoExample = new PDRiskIntroduceInfoExample();
				PDRiskIntroduceInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDRiskIntroduceInfo> PDRiskIntroduceInfoList = pdRiskIntroduceInfoMapper
						.selectByExample(PDRiskIntroduceInfoExample);
				if (PDRiskIntroduceInfoList != null && PDRiskIntroduceInfoList.size() > 0) {
					PDRiskIntroduceInfoExist = (PDRiskIntroduceInfo) PDRiskIntroduceInfoList.get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(PDRiskIntroduceInfoExist, loginInfo);
					pdRiskIntroduceInfoMapper.updateByPrimaryKey(PDRiskIntroduceInfoExist);
				}
				Long riskIntroduceInfoSeq = commonService.getSeqValByName("SEQ_T_PD_RISK_INTRODUCE_INFO");
				PDRiskIntroduceInfo PDRiskIntroduceInfo = (PDRiskIntroduceInfo) tMap.get("PDRiskIntroduceInfo");
				PDRiskIntroduceInfo.setPdRiskIntroduceInfoId(riskIntroduceInfoSeq);
				PDRiskIntroduceInfo.setProductId(new Long(productId));
				PDRiskIntroduceInfo pdRiskSEOIntroductInfo = (PDRiskIntroduceInfo) tMap.get("pdRiskSEOIntroductInfo");
				PDRiskIntroduceInfo.setProductKeyword(pdRiskSEOIntroductInfo.getProductKeyword());
				PDRiskIntroduceInfo.setProductTitle(pdRiskSEOIntroductInfo.getProductTitle());
				PDRiskIntroduceInfo.setProductDescribe(pdRiskSEOIntroductInfo.getProductDescribe());
				if (PDRiskIntroduceInfoList != null && PDRiskIntroduceInfoList.size() > 0) {
					com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(PDRiskIntroduceInfoExist,
							PDRiskIntroduceInfo, loginInfo);
					pdRiskIntroduceInfoMapper.insert(PDRiskIntroduceInfo);// 插入保险韩品说明信息
				} else {
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(PDRiskIntroduceInfo, loginInfo);
					pdRiskIntroduceInfoMapper.insert(PDRiskIntroduceInfo);// 插入保险韩品说明信息
				}
				PDRiskSalesInfoExample pdRiskSalesInfoExample = new PDRiskSalesInfoExample();
				pdRiskSalesInfoExample.createCriteria().andRcStateEqualTo("E")
						.andProductIdEqualTo(new Long(productId));
				List<PDRiskSalesInfo> pdRiskSalesInfoList = pDRiskSalesInfoMapper
						.selectByExample(pdRiskSalesInfoExample);
				if (pdRiskSalesInfoList != null && pdRiskSalesInfoList.size() > 0) {
					pdRiskSalesInfoExist = (PDRiskSalesInfo) pdRiskSalesInfoList.get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdRiskSalesInfoExist, loginInfo);
					pDRiskSalesInfoMapper.updateByPrimaryKey(pdRiskSalesInfoExist);
				}
				Long riskSalesInfoSeq = commonService.getSeqValByName("SEQ_T_PD_RISK_SALES_INFO");
				PDRiskSalesInfo pdRiskSalesInfo = (PDRiskSalesInfo) tMap.get("pdRiskSalesInfo");
				pdRiskSalesInfo.setProductId(new Long(productId));
				pdRiskSalesInfo.setPdRiskSalesInfoId(riskSalesInfoSeq);
				if (pdRiskSalesInfoList != null && pdRiskSalesInfoList.size() > 0) {
					com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(pdRiskSalesInfoExist,
							pdRiskSalesInfo, loginInfo);
					pDRiskSalesInfoMapper.insert(pdRiskSalesInfo);// 插入保险产品营销信息
				} else {
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdRiskSalesInfo, loginInfo);
					pDRiskSalesInfoMapper.insert(pdRiskSalesInfo);// 插入保险产品营销信息
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setMsg("修改产品网销信息成功");
		}
		return resultInfo;
	}

	/** 获取产品核心信息返回到修改页面 **/
	@SuppressWarnings("rawtypes")
	@Transactional
	public ResultInfo queryProductCoreInfo(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> productInfoMap = new HashMap<String, Object>();
		try {
			// 获取需要修改的产品主表信息T_PD_PRODUCT
			PDProduct modifyProduct = (PDProduct) pDProductMapper.selectByPrimaryKey(new Long(param));
			productInfoMap.put("modifyProduct", JsonUtils.objectToMap(modifyProduct));
			productInfoMap.put("modifyStatus", modifyProduct.getStatus());
			PDWealth modifyPdWealth = null;
			// 1-财富产品
			if (modifyProduct.getProductType() == "1" || modifyProduct.getProductType().equals("1")) {
				// 财富产品信息 T_PD_WEALTH
				PDWealthExample pdWealthExample = new PDWealthExample();
				pdWealthExample.createCriteria().andProductIdEqualTo(modifyProduct.getProductId())
						.andRcStateEqualTo("E");
				List<PDWealth> pdWealths = pDWealthMapper.selectByExample(pdWealthExample);
				if (pdWealths.size() > 0) {
					modifyPdWealth = (PDWealth) pdWealths.get(0);
					modifyPdWealth.setFinancingScale(
							new BigDecimal(NumberUtils.divide(modifyPdWealth.getFinancingScale().toString(), "10000")));
					productInfoMap.put("modifyPdWealth", JsonUtils.objectToMap(modifyPdWealth));
					productInfoMap.put("modifyPdWealth", JsonUtils.objectToMap(modifyPdWealth));
				}
				// 固定收益
				if (modifyProduct.getProductSubType().equals("02") || modifyProduct.getProductSubType() == "02") {
					// 固定收益分类信息 T_PD_WEALTH_CHARGE_RATE
					PDWealthChargeRateExample pdWealthChargeRateExample = new PDWealthChargeRateExample();
					if (modifyPdWealth != null) {
						pdWealthChargeRateExample.createCriteria().andPdWealthIdEqualTo(modifyPdWealth.getPdWealthId())
								.andRcStateEqualTo("E");
						List<PDWealthChargeRate> pdWealthChargeRates = pDWealthChargeRateMapper
								.selectByExample(pdWealthChargeRateExample);
						if (pdWealthChargeRates.size() > 0) {
							PDWealthChargeRate modifyPdWealthChargeRate = (PDWealthChargeRate) pdWealthChargeRates
									.get(0);
///////////////////////////////
							PDWealthChargeRate2 pDWealthChargeRate2 =new PDWealthChargeRate2();
							if(modifyPdWealthChargeRate.getTaxFee()!=null){
							pDWealthChargeRate2.setTaxFee(modifyPdWealthChargeRate.getTaxFee().toPlainString());}
							if(modifyPdWealthChargeRate.getChannelFee()!=null){
							pDWealthChargeRate2.setChannelFee(modifyPdWealthChargeRate.getChannelFee().toPlainString());}
							productInfoMap.put("modifyPdWealthChargeRate",
									JsonUtils.objectToMap(modifyPdWealthChargeRate));
							productInfoMap.put("modifyPdWealthChargeRate2",
									JsonUtils.objectToMap(pDWealthChargeRate2));
///////////////////////////////
						}
						// 财富费用比例信息 T_PD_WEALTH_FEE_RATE
						List<Map> modifyPdWealthFeeRateList = getModifyPdWealthFeeRateInfo(
								modifyPdWealth.getPdWealthId().toString());
						productInfoMap.put("modifyPdWealthFeeRateList", modifyPdWealthFeeRateList);
						// 收益分配配置信息
						PDwealthIncomeDisExample pdwealthIncomeDisExample = new PDwealthIncomeDisExample();
						pdwealthIncomeDisExample.createCriteria().andPdWealthIdEqualTo(modifyPdWealth.getPdWealthId())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						List<PDwealthIncomeDis> pdwealthIncomeDisList = pdwealthIncomeDisMapper
								.selectByExample(pdwealthIncomeDisExample);
						List<Map<String, String>> pdwealthIncomeDisMapList = JsonUtils
								.listObjectToListMap(pdwealthIncomeDisList);
						productInfoMap.put("modifyPdwealthIncomeDisMapList", pdwealthIncomeDisMapList);
					}
				} else {
					if (modifyPdWealth != null) {
						// 股权类收益分配配置信息T_PD_WEALTH_SROCK_DIS
						if (modifyProduct.getProductSubType().equals("01") || modifyProduct.getProductSubType() == "01") {
							PDWealthStockDisExample pdwealthStockDisExample = new PDWealthStockDisExample();
							pdwealthStockDisExample.createCriteria().andPdWealthIdEqualTo(modifyPdWealth.getPdWealthId())
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
							List<PDWealthStockDis> pdwealthStockDisList = pdwealthStockDisMapper
									.selectByExample(pdwealthStockDisExample);
							List<Map<String, String>> pdwealthStockDisMapList = JsonUtils
									.listObjectToListMap(pdwealthStockDisList);
							productInfoMap.put("modifyPdwealthStockDisMapList", pdwealthStockDisMapList);
						}
						// 01-浮动 03-股权T_PD_WEALTH_CHARGE_RATE
						PDWealthChargeRateExample pdWealthChargeRateExample = new PDWealthChargeRateExample();
						pdWealthChargeRateExample.createCriteria().andPdWealthIdEqualTo(modifyPdWealth.getPdWealthId())
								.andRcStateEqualTo("E");
						List<PDWealthChargeRate> pdWealthChargeRates = pDWealthChargeRateMapper
								.selectByExample(pdWealthChargeRateExample);
						if (pdWealthChargeRates.size() > 0) {
							PDWealthChargeRate modifyPdWealthChargeRate = (PDWealthChargeRate) pdWealthChargeRates
									.get(0);
///////////////////////////////
					PDWealthChargeRate2 pDWealthChargeRate2 =new PDWealthChargeRate2(); 
					if(modifyPdWealthChargeRate.getFixManagementFeeRatio()!=null){
					pDWealthChargeRate2.setFixManagementFeeRatio(modifyPdWealthChargeRate.getFixManagementFeeRatio().toPlainString());}
					if(modifyPdWealthChargeRate.getFloatManagementFeeRatio()!=null){
					pDWealthChargeRate2.setFloatManagementFeeRatio(modifyPdWealthChargeRate.getFloatManagementFeeRatio().toPlainString());}	
					if(modifyPdWealthChargeRate.getTaxFee()!=null){
						pDWealthChargeRate2.setTaxFee(modifyPdWealthChargeRate.getTaxFee().toPlainString());}
					
							productInfoMap.put("modifyPdWealthChargeRate",
									JsonUtils.objectToMap(modifyPdWealthChargeRate));
							
							productInfoMap.put("modifyPdWealthChargeRate2",
									JsonUtils.objectToMap(pDWealthChargeRate2));
///////////////////////////////
						}
					}
				}
				// 获取财富产品的录入信息T_PD_FACTOR
				List<Map> modifyPdFactorRateList = this.getmodifyPdFactorRateInfo(
						modifyProduct.getProductId().toString(), modifyProduct.getProductType());
				productInfoMap.put("modifyPdFactorRateList", modifyPdFactorRateList);
			} else {
				// 保险产品信息 T_PD_RISK
				PDRiskExample pdRiskExample = new PDRiskExample();
				pdRiskExample.createCriteria().andProductIdEqualTo(modifyProduct.getProductId()).andRcStateEqualTo("E");
				List<PDRisk> pdrisksis = pDRiskMapper.selectByExample(pdRiskExample);
				if (pdrisksis != null && pdrisksis.size() > 0) {
					PDRisk modifyPdRisk = (PDRisk) pDRiskMapper.selectByExample(pdRiskExample).get(0);
					productInfoMap.put("modifyPdRisk", JsonUtils.objectToMap(modifyPdRisk));
					// 保险费用比例 T_PD_RISK_FEE_RATE
					List<Map> modifyPdRiskFeeRateList = this
							.getmodifyPdRiskFeeRateInfo(modifyPdRisk.getPdRiskId().toString());
					productInfoMap.put("modifyPdRiskFeeRateList", modifyPdRiskFeeRateList);
					// 获取保险产品的录入信息T_PD_FACTOR
					List<Map> modifyPdFactorRateList = this.getmodifyPdFactorRateInfo(
							modifyProduct.getProductId().toString(), modifyProduct.getProductType());
					productInfoMap.put("modifyPdFactorRateList", modifyPdFactorRateList);
				}
			}
			resultInfo.setObj(productInfoMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取产品修改信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品修改信息失败！");
		}
		return resultInfo;
	}

	/** 修改产品核心信息 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo updatePrdocutCoreInfo(Map tMap, LoginInfo loginInfo) {
		String productId = tMap.get("modifyProductId").toString();
		String productType = tMap.get("modifyProductType").toString();
		String productSubType = tMap.get("modifyProductSubType").toString();
		ResultInfo resultInfo = new ResultInfo();
		if (productId != null) {
			/*** 重复提交的产品信息先将原来的数据全部进行逻辑删除,除了主表不删除 ***/
			PDWealth exisPdWealth = null;
			List<PDFactor> exisPdFactorList = null;
			List<PDWealthFeeRate> exisPdWealthFeeRateList = null;
			List<PDwealthIncomeDis> existPdwealthIncomeDisList = null;
			List<PDWealthStockDis> existPdwealthStockDisList = null;
			List<PDWealthChargeRate> existPdwealthChargeRates = null;
			PDWealthChargeRate exisPdWealthChargeRate = null;
			PDRisk exisPdRisk = null;
			List<PDRiskFeeRate> exisPdRiskFeeRateList = null;
			// 删除已存在的 录入信息T_PD_FACTOR
			PDFactorExample pdFactorExample = new PDFactorExample();
			pdFactorExample.createCriteria().andPdIdEqualTo(new Long(productId)).andRcStateEqualTo("E");
			exisPdFactorList = pDFactorMapper.selectByExample(pdFactorExample);
			if (exisPdFactorList.size() > 0) {
				for (int i = 0; i < exisPdFactorList.size(); i++) {
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo((PDFactor) exisPdFactorList.get(i),
							loginInfo);
					pDFactorMapper.updateByPrimaryKey((PDFactor) exisPdFactorList.get(i));
				}
			}
			if (productType.equals("1")) {
				// 删除已存在的 财富信息主表T_PD_WEALTH
				PDWealthExample pdWealthExample = new PDWealthExample();
				pdWealthExample.createCriteria().andProductIdEqualTo(new Long(productId)).andRcStateEqualTo("E");
				List<PDWealth> pdWealths = pDWealthMapper.selectByExample(pdWealthExample);
				if (pdWealths != null && pdWealths.size() > 0) {
					exisPdWealth = (PDWealth) pDWealthMapper.selectByExample(pdWealthExample).get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdWealth, loginInfo);
					pDWealthMapper.updateByPrimaryKey(exisPdWealth);
				}
				if (exisPdWealth != null) {
					/*** 删除固定收益类 *****/
					if (productSubType.equals("02")) {
						// 删除 固定收益分类信息 T_PD_WEALTH_CHARGE_RATE
						PDWealthChargeRateExample pdWealthChargeRateExample = new PDWealthChargeRateExample();
						pdWealthChargeRateExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
								.andRcStateEqualTo("E");
						existPdwealthChargeRates = pDWealthChargeRateMapper.selectByExample(pdWealthChargeRateExample);
						if (existPdwealthChargeRates.size() > 0) {
							exisPdWealthChargeRate = (PDWealthChargeRate) existPdwealthChargeRates.get(0);
							com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdWealthChargeRate, loginInfo);
							pDWealthChargeRateMapper.updateByPrimaryKey(exisPdWealthChargeRate);
						}
						// 删除 财富费用比例信息 T_PD_WEALTH_FEE_RATE
						PDWealthFeeRateExample pdWealthFeeRateExample = new PDWealthFeeRateExample();
						pdWealthFeeRateExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
								.andRcStateEqualTo("E");
						exisPdWealthFeeRateList = pDWealthFeeRateMapper.selectByExample(pdWealthFeeRateExample);
						if (exisPdWealthFeeRateList.size() > 0) {
							for (int i = 0; i < exisPdWealthFeeRateList.size(); i++) {
								com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdWealthFeeRateList.get(i),
										loginInfo);
								pDWealthFeeRateMapper.updateByPrimaryKey(exisPdWealthFeeRateList.get(i));
							}
						}
						// 删除收益分配配置信息
						PDwealthIncomeDisExample pdwealthIncomeDisExample = new PDwealthIncomeDisExample();
						pdwealthIncomeDisExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						existPdwealthIncomeDisList = pdwealthIncomeDisMapper.selectByExample(pdwealthIncomeDisExample);
						if (existPdwealthIncomeDisList.size() > 0) {
							for (PDwealthIncomeDis pdwealthIncomeDis : existPdwealthIncomeDisList) {
								com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdwealthIncomeDis, loginInfo);
								pdwealthIncomeDisMapper.updateByPrimaryKey(pdwealthIncomeDis);
							}
						}
					} else {
						if (productSubType.equals("01")) {
							PDWealthStockDisExample pdWealthStockDisExample = new PDWealthStockDisExample();
							pdWealthStockDisExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
							existPdwealthStockDisList = pdwealthStockDisMapper.selectByExample(pdWealthStockDisExample);
							for (PDWealthStockDis pdwealthStockDis : existPdwealthStockDisList) {
								com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdwealthStockDis, loginInfo);
								pdwealthStockDisMapper.updateByPrimaryKey(pdwealthStockDis);
							}
						}
						// 删除 浮动股权分类信息 T_PD_WEALTH_CHARGE_RATE
						PDWealthChargeRateExample pdWealthChargeRateExample = new PDWealthChargeRateExample();
						pdWealthChargeRateExample.createCriteria().andPdWealthIdEqualTo(exisPdWealth.getPdWealthId())
								.andRcStateEqualTo("E");
						existPdwealthChargeRates = pDWealthChargeRateMapper.selectByExample(pdWealthChargeRateExample);
						if (existPdwealthChargeRates.size() > 0) {
							exisPdWealthChargeRate = (PDWealthChargeRate) existPdwealthChargeRates.get(0);
							com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdWealthChargeRate, loginInfo);
							pDWealthChargeRateMapper.updateByPrimaryKey(exisPdWealthChargeRate);
						}
					}
				}
			} else {
				/*** 删除财富产品信息 ****/
				// 保险产品 删除已存在的 保险信息主表 T_PD_RISK
				PDRiskExample pdRiskExample = new PDRiskExample();
				pdRiskExample.createCriteria().andProductIdEqualTo(new Long(productId)).andRcStateEqualTo("E");
				if (pDRiskMapper.selectByExample(pdRiskExample).size() > 0) {
					exisPdRisk = (PDRisk) pDRiskMapper.selectByExample(pdRiskExample).get(0);
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdRisk, loginInfo);
					pDRiskMapper.updateByPrimaryKey(exisPdRisk);
				}
				if (exisPdRisk != null) {
					// 保险费用比例 T_PD_RISK_FEE_RATE
					PDRiskFeeRateExample pdRiskFeeRateExample = new PDRiskFeeRateExample();
					pdRiskFeeRateExample.createCriteria().andPdRiskIdEqualTo(exisPdRisk.getPdRiskId())
							.andRcStateEqualTo("E");
					exisPdRiskFeeRateList = pDRiskFeeRateMapper.selectByExample(pdRiskFeeRateExample);
					if (exisPdRiskFeeRateList != null) {
						for (int i = 0; i < exisPdRiskFeeRateList.size(); i++) {
							com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(exisPdRiskFeeRateList.get(i),
									loginInfo);
							pDRiskFeeRateMapper.updateByPrimaryKey(exisPdRiskFeeRateList.get(i));
						}
					}
				}
			}
			/**** 重复提交产品后 新增产品信息 ******/
			// 保存产品主表T_PD_PRODUCT
			if (productType.equals("1")) {
				Long pdwealthSeq = null;
				PDWealth pDWealth = (PDWealth) tMap.get("pDWealthSchema");
				// 保存财富信息表T_PD_WEALTH
				if (pDWealth != null) {
					pdwealthSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH");
					pDWealth.setProductId(new Long(productId));
					pDWealth.setPdWealthId(pdwealthSeq);
					if (exisPdWealth != null) {
						com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdWealth, pDWealth,
								loginInfo);
					} else {
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealth, loginInfo);
					}
					pDWealthMapper.insert(pDWealth);
				}
				// 固定收益类
				if (productSubType.equals("02")) {
					//更新产品预约中的产品的成立日,先更新额度分配表
					PdAmountDisInfoExample pdAmountDisInfoExample = new PdAmountDisInfoExample();
					PdAmountDisInfoExample.Criteria pdAmountDisInfoCriteria = pdAmountDisInfoExample.createCriteria();
					pdAmountDisInfoCriteria.andProductIdEqualTo(pDWealth.getProductId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					PdAmountDisInfo pdAmountDisInfo = new PdAmountDisInfo();
					pdAmountDisInfo.setExpectOpenDay(pDWealth.getFoundDate());
					com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(pdAmountDisInfo, loginInfo);
					pdAmountDisInfoMapper.updateByExampleSelective(pdAmountDisInfo, pdAmountDisInfoExample);
					//更新产品预约表
					PDAmountOrderInfoExample pdAmountOrderInfoExample = new PDAmountOrderInfoExample();
					PDAmountOrderInfoExample.Criteria criteria = pdAmountOrderInfoExample.createCriteria();
					criteria.andProductIdEqualTo(pDWealth.getProductId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					PDAmountOrderInfo pdAmountOrderInfo = new PDAmountOrderInfo();
					pdAmountOrderInfo.setExpectOpenDay(pDWealth.getFoundDate());
					//com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
					pdAmountOrderInfoMapper.updateByExampleSelective(pdAmountOrderInfo, pdAmountOrderInfoExample);
					// 保存财富费用比例表T_PD_WEALTH_FEE_RATE
					List<PDWealthFeeRate> pDWealthFeeRateList = new ArrayList<PDWealthFeeRate>();
					pDWealthFeeRateList = (List<PDWealthFeeRate>) tMap.get("WealthFeeRateInfolist");
					for (int i = 0; i < pDWealthFeeRateList.size(); i++) {
						Long pdwealthFeeRateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_FEE_RATE");
						PDWealthFeeRate pDWealthFeeRate = (PDWealthFeeRate) pDWealthFeeRateList.get(i);
						pDWealthFeeRate.setPdWealthFeeRateId(pdwealthFeeRateSeq);
						pDWealthFeeRate.setPdWealthId(pDWealth.getPdWealthId());
						if (exisPdWealthFeeRateList != null && exisPdWealthFeeRateList.size() > 0) {
							com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(
									exisPdWealthFeeRateList.get(0), pDWealthFeeRate, loginInfo);
						} else {
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthFeeRateList.get(i),
									loginInfo);
						}
						pDWealthFeeRateMapper.insert(pDWealthFeeRate);
					}
					// 保存收益分配信息
					List<PDwealthIncomeDis> pdwealthIncomeDisList = new ArrayList<PDwealthIncomeDis>();
					pdwealthIncomeDisList = (List<PDwealthIncomeDis>) tMap.get("pdwealthIncomeDisList");
					if (pdwealthIncomeDisList != null && pdwealthIncomeDisList.size() > 0) {
						for (PDwealthIncomeDis pdwealthIncomeDis : pdwealthIncomeDisList) {
							Long pdWealthIncomeDistributeId = commonService
									.getSeqValByName("SEQ_T_PD_WEALTH_INCOME_DIS");
							pdwealthIncomeDis.setPdWealthIncomeDistributeId(pdWealthIncomeDistributeId);
							pdwealthIncomeDis.setPdWealthId(pDWealth.getPdWealthId());
							if (existPdwealthIncomeDisList != null && existPdwealthIncomeDisList.size() > 0) {
								com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(
										existPdwealthIncomeDisList.get(0), pdwealthIncomeDis, loginInfo);
							} else {
								com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdwealthIncomeDis, loginInfo);
							}
							pdwealthIncomeDisMapper.insert(pdwealthIncomeDis);
						}
					}
					// 保存固定收益类分类信息T_PD_WEALTH_CHARGE_RATE
					PDWealthChargeRate pDWealthChargeRate = (PDWealthChargeRate) tMap.get("pDWealthChargeRate");
					if (pDWealthChargeRate != null) {
						Long pdwealthChargeRateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_CHARGE_RATE");
						pDWealthChargeRate.setPdWealthChargeRateId(pdwealthChargeRateSeq);
						pDWealthChargeRate.setPdWealthId(pDWealth.getPdWealthId());
						if (exisPdWealthChargeRate != null) {
							com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdWealthChargeRate,
									pDWealthChargeRate, loginInfo);
						} else {
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthChargeRate, loginInfo);
						}
						pDWealthChargeRateMapper.insert(pDWealthChargeRate);// 保存固定收益类分类信息T_PD_WEALTH_CHARGE_RATE
					}
				} else {
					
					if (productSubType.equals("01")) {
						// 保存股权收益分配信息
						List<PDWealthStockDis> pdwealthStockDisList = new ArrayList<PDWealthStockDis>();
						pdwealthStockDisList = (List<PDWealthStockDis>) tMap.get("pdwealthStockDisList");
						if (pdwealthStockDisList != null && pdwealthStockDisList.size() > 0) {
							for (PDWealthStockDis pdwealthStockDis : pdwealthStockDisList) {
								Long pdWealthStockDisId = commonService
										.getSeqValByName("SEQ_T_PD_WEALTH_STOCK_DIS");
								
								pdwealthStockDis.setPdWealthStockDisId(pdWealthStockDisId);
								pdwealthStockDis.setPdWealthId(pDWealth.getPdWealthId());
								
								if (existPdwealthStockDisList != null && existPdwealthStockDisList.size() > 0) {
									com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(
											existPdwealthStockDisList.get(0), pdwealthStockDis, loginInfo);
								} else {
									com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdwealthStockDis, loginInfo);
								}
								pdwealthStockDisMapper.insert(pdwealthStockDis);
							}
						}
					}
					// 保存浮动股权收益类分类信息T_PD_WEALTH_CHARGE_RATE
					PDWealthChargeRate pDWealthChargeRate = (PDWealthChargeRate) tMap.get("pDWealthChargeRate");
					if (pDWealthChargeRate != null){
						Long pdwealthChargeRateSeq = commonService.getSeqValByName("SEQ_T_PD_WEALTH_CHARGE_RATE");
						pDWealthChargeRate.setPdWealthChargeRateId(pdwealthChargeRateSeq);
						pDWealthChargeRate.setPdWealthId(pDWealth.getPdWealthId());
						if (exisPdWealthChargeRate!=null) {
							com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdWealthChargeRate,
									pDWealthChargeRate, loginInfo);
						} else {
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDWealthChargeRate, loginInfo);
						}
						pDWealthChargeRateMapper.insert(pDWealthChargeRate);// 保存固定收益类分类信息T_PD_WEALTH_CHARGE_RATE
					}
					if (exisPdWealth!=null) {
						//如果存在净值和开放日需要更改它们的财富产品流水号
						PDWealthNetValueExample pdWealthNetValueExample=new PDWealthNetValueExample();
						pdWealthNetValueExample.createCriteria().andRcStateEqualTo("E").andPdWealthIdEqualTo(exisPdWealth.getPdWealthId());
						List<PDWealthNetValue> pdWealthNetValues=pdWealthNetValueMapper.selectByExample(pdWealthNetValueExample);
						if (pdWealthNetValues!=null&&pdWealthNetValues.size()>0) {
							for (PDWealthNetValue pdWealthNetValue : pdWealthNetValues) {
								pdWealthNetValue.setPdWealthId(pDWealth.getPdWealthId());
								pdWealthNetValueMapper.updateByPrimaryKey(pdWealthNetValue);
							}
						}
						
						PDWealthOpenDateExample pdWealthOpenDateExample=new PDWealthOpenDateExample();
						pdWealthOpenDateExample.createCriteria().andRcStateEqualTo("E").andPdWealthIdEqualTo(exisPdWealth.getPdWealthId());
						List<PDWealthOpenDate>  pdWealthOpenDates=pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample);
						if (pdWealthOpenDates!=null&&pdWealthOpenDates.size()>0) {
							for (PDWealthOpenDate pdWealthOpenDate : pdWealthOpenDates) {
								pdWealthOpenDate.setPdWealthId(pDWealth.getPdWealthId());
								pdWealthOpenDateMapper.updateByPrimaryKey(pdWealthOpenDate);
							}
							
						}
					}
			
					
				}
			} else {
				Long pdriskSeq = null;
				PDRisk pDRisk = (PDRisk) tMap.get("pDRiskShcema");
				// 保存保险基本信息T_PD_RISK
				if (pDRisk != null) {
					pdriskSeq = commonService.getSeqValByName("SEQ_T_PD_RISK");
					pDRisk.setPdRiskId(pdriskSeq);
					pDRisk.setProductId(new Long(productId));
					if (exisPdRisk != null) {
						com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdRisk, pDRisk, loginInfo);
					} else {
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDRisk, loginInfo);
					}
					pDRiskMapper.insert(pDRisk);
				}
				List<PDRiskFeeRate> pDRiskFeeRateList = new ArrayList<PDRiskFeeRate>();
				// 保存保险费用比例表T_PD_RISK_FEE_RATE
				pDRiskFeeRateList = (List<PDRiskFeeRate>) tMap.get("insuraceRateInfolist");
				if (pDRiskFeeRateList != null && pDRiskFeeRateList.size() > 0) {
					for (int i = 0; i < pDRiskFeeRateList.size(); i++) {
						PDRiskFeeRate pDRiskFeeRate = (PDRiskFeeRate) pDRiskFeeRateList.get(i);
						Long pDRiskFeeRateSeq = commonService.getSeqValByName("SEQ_T_PD_RISK_FEE_RATE");
						pDRiskFeeRate.setPdRiskId(pDRisk.getPdRiskId());
						pDRiskFeeRate.setPdRiskFeeRateId(pDRiskFeeRateSeq);
						if (exisPdRiskFeeRateList != null && exisPdRiskFeeRateList.size() > 0) {
							com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(
									exisPdRiskFeeRateList.get(0), pDRiskFeeRate, loginInfo);
							pDRiskFeeRateMapper.insert(pDRiskFeeRate);
						} else {
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDRiskFeeRateList.get(i), loginInfo);
							pDRiskFeeRateMapper.insert(pDRiskFeeRate);
						}
					}
				}
			}
			// 保存录入项信息表T_PD_RISK_FEE_RATE
			List<PDFactor> PDFactorList = new ArrayList<PDFactor>();
			PDFactorList = (List<PDFactor>) tMap.get("factorInfolist");
			if (PDFactorList != null) {
				for (int i = 0; i < PDFactorList.size(); i++) {
					Long pdfactorSeq = commonService.getSeqValByName("SEQ_T_PD_FACTOR");
					PDFactor pDFactor = (PDFactor) PDFactorList.get(i);
					pDFactor.setPdId(new Long(productId));
					pDFactor.setPdFactorId(pdfactorSeq);
					if (exisPdFactorList != null && exisPdFactorList.size() > 0) {
						com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(exisPdFactorList.get(0),
								pDFactor, loginInfo);
					} else {
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pDFactor, loginInfo);
					}
					pDFactorMapper.insert(pDFactor);
					
				}
			}
			/******** 重复提交,新增结束 ***********/
			resultInfo.setMsg("修改成功");
			resultInfo.setSuccess(true);
		}
		return resultInfo;
	}
	
	
	/**
	 * 获取 (固定收益类)费用比例信息可编辑表格信息
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getModifyPdWealthFeeRateInfo(String param) {
		List<Map> getModifyPdWealthFeeRateList = new ArrayList<Map>();
		Map queryParam = new HashMap();
		queryParam.put("wealthId", param);
		try {
			getModifyPdWealthFeeRateList = productServiceMapper.getModifyPdWealthFeeRateList(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getModifyPdWealthFeeRateList;
	}

	/**
	 * 获取 (保险费用比例)费用比例信息可编辑表格信息
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getmodifyPdRiskFeeRateInfo(String param) {
		List<Map> getmodifyPdRiskFeeRateList = new ArrayList<Map>();
		Map queryParam = new HashMap();
		queryParam.put("riskId", param);
		try {
			getmodifyPdRiskFeeRateList = productServiceMapper.getmodifyPdRiskFeeRateList(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getmodifyPdRiskFeeRateList;
	}

	/**
	 * 获取 (录入信息)可编辑表格信息
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getmodifyPdFactorRateInfo(String param, String productType) {
		List<Map> getmodifyPdFactorRateList = new ArrayList<Map>();
		Map queryParam = new HashMap();
		queryParam.put("productId", param);
		if (productType.equals("1") || productType == "1") {
			queryParam.put("factorCode", "wealthfactorCode");
		} else {
			queryParam.put("factorCode", "riskfactorCode");
		}
		try {
			getmodifyPdFactorRateList = productServiceMapper.getmodifyPdFactorRateInfoList(queryParam);
			if (getmodifyPdFactorRateList.size() > 0 && productType.equals("1")) {
				for (int i = 0; i < getmodifyPdFactorRateList.size(); i++) {
					if (getmodifyPdFactorRateList.get(i).get("factorName").equals("closedPeriods")) {
						String factorValueName = getmodifyPdFactorRateList.get(i).get("factorValue").toString().substring(1);
						getmodifyPdFactorRateList.get(i).put("factorValueName", factorValueName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getmodifyPdFactorRateList;
	}

	/**
	 * 校验产品编码是否重复
	 * 
	 * @param existProductId
	 * @param productCode
	 */
	public void verifyProductCode(String existProductId, String productCode) {
		PDProductExample pdProductExample = new PDProductExample();
		PDProductExample.Criteria criteria = pdProductExample.createCriteria();
		criteria.andRcStateEqualTo("E").andProductCodeEqualTo(productCode);
		if (existProductId != null) {
			criteria.andProductIdNotEqualTo(new Long(existProductId));
			List<PDProduct> productList = pDProductMapper.selectByExample(pdProductExample);
			if (productList != null && productList.size() > 0) {
				log.info("该产品编码已经存在！请重新录入！");
				throw new CisCoreException("该产品编码已经存在！请重新录入！");
			}
		} else {
			List<PDProduct> productList = pDProductMapper.selectByExample(pdProductExample);
			if (productList != null && productList.size() > 0) {
				log.info("该产品编码已经存在！请重新录入！");
				throw new CisCoreException("该产品编码已经存在！请重新录入！");
			}
		}
	}

	/**
	 * 校验该产品公布日期，该净值是否已经存在
	 * 
	 * @param pdwealthNetvalueId
	 * @param netType
	 * @param publicDate
	 * @param pdweathId
	 * @param operate
	 */
	public void verifyNetValue(Long pdwealthNetvalueId, String netType, Date publicDate, Long pdweathId,
			String operate) {
		if (pdwealthNetvalueId == null) {
			if (operate.equals("insert")) {
				if (publicDate != null && pdweathId != null) {
					PDWealthNetValueExample pdWealthNetValueExample = new PDWealthNetValueExample();
					pdWealthNetValueExample.createCriteria().andPdWealthIdEqualTo(pdweathId)
							.andPublicDateEqualTo(publicDate).andRcStateEqualTo("E").andNetTypeEqualTo(netType);
					List<PDWealthNetValue> pdWealthNetValuesList = pdWealthNetValueMapper
							.selectByExample(pdWealthNetValueExample);
					if (pdWealthNetValuesList != null && pdWealthNetValuesList.size() > 0) {
						throw new CisCoreException("该产品在此公布日期已经存在相同的费用类型的净值，请重新配置！");
					}
				}
			}
		} else {
			if (operate.equals("update")) {
				if (publicDate != null && pdweathId != null) {
					PDWealthNetValueExample pdWealthNetValueExample = new PDWealthNetValueExample();
					pdWealthNetValueExample.createCriteria().andPdWealthIdEqualTo(pdweathId)
							.andPublicDateEqualTo(publicDate).andRcStateEqualTo("E")
							.andPdWhealNetValueIdNotEqualTo(pdwealthNetvalueId);
					List<PDWealthNetValue> pdWealthNetValuesList = pdWealthNetValueMapper
							.selectByExample(pdWealthNetValueExample);
					if (pdWealthNetValuesList != null && pdWealthNetValuesList.size() > 0) {
						throw new CisCoreException("该产品在此公布日期已经存在相同的费用类型的净值，请重新配置！");
					}
				}
			}
		}
	}

	/**
	 * 查询产品要素信息
	 */
	public List<Map<String, String>> queryProductFactor(String codeType) {
		DefCodeExample defCodeExample = new DefCodeExample();
		defCodeExample.setOrderByClause("code_alias");
		defCodeExample.createCriteria().andCodeTypeEqualTo(codeType);
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		List<Map<String, String>> codeMapList = new ArrayList<Map<String, String>>();
		if (codeType.equals("riskfactorCode")) {
			for (DefCode defCode : defCodeList) {
				Map<String, String> codeMap = new HashMap<String, String>();
				String code = defCode.getCode();
				String codeName = defCode.getCodeName();
				if (code.equals("amnt") || code.equals("premium") || code.equals("insurdPeriod")
						|| code.equals("insurdPeriodUnit") || code.equals("payPeriod") || code.equals("payPeriodUnit")
						|| code.equals("payIntv")) {
					codeMap.put("factorCode", code);
					codeMap.put("factorName", codeName);
					codeMap.put("factorTrueName", codeName);
					codeMapList.add(codeMap);
				}
			}
		}
		if (codeType.equals("wealthfactorCode")) {
			for (DefCode defCode : defCodeList) {
				Map<String, String> codeMap = new HashMap<String, String>();
				String code = defCode.getCode();
				String codeName = defCode.getCodeName();
				codeMap.put("factorCode", code);
				codeMap.put("factorName", codeName);
				codeMap.put("factorTrueName", codeName);
				codeMapList.add(codeMap);
			}
		}
		return codeMapList;
	}

	/**
	 * 查询合作机构
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo getAllComInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map> comInfoList = productServiceMapper.getAllComInfo(paramMap);
			resultInfo.setObj(comInfoList);
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 保存产品额度分配信息
	 */
	@Transactional
	public ResultInfo saveProductAmountDisInfo(List<PdAmountDisInfo> pdAmountDisInfoList, String productInfo,
			LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			String productIdStr = JsonUtils.getJsonValueByKey("productId", productInfo);
			String expectOpenDayStr = JsonUtils.getJsonValueByKey("expectOpenDay", productInfo);
			// 产品ID
			Long productId = new Long(productIdStr);
			// 期望开放日或成立日
			Date expectOpenDay = DateUtils.getDate(expectOpenDayStr);
			if (expectOpenDay == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到期望开放日或成立日");
				return resultInfo;
			}
			// 新增
			if (operate != null && !"".equals(operate) && "addPdAmountDis".equals(operate)) {
				if (pdAmountDisInfoList.get(0).getPdAmountDisInfoId() == null) {
					// 已经添加过不能再添加分配
					PdAmountDisInfoExample pdAmountDisInfoExample = new PdAmountDisInfoExample();
					PdAmountDisInfoExample.Criteria criteria = pdAmountDisInfoExample.createCriteria();
					criteria.andProductIdEqualTo(productId).andExpectOpenDayEqualTo(expectOpenDay)
							.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<PdAmountDisInfo> existPdAmountDisInfoList = pdAmountDisInfoMapper
							.selectByExample(pdAmountDisInfoExample);
					if (existPdAmountDisInfoList != null && existPdAmountDisInfoList.size() > 0) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该产品本次开放日额度已经分配，不能重复分配！");
						return resultInfo;
					}
				}
				for (PdAmountDisInfo pdAmountDisInfo : pdAmountDisInfoList) {
					pdAmountDisInfo.setProductId(productId);
					pdAmountDisInfo.setExpectOpenDay(expectOpenDay);
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdAmountDisInfo, loginInfo);
					if (pdAmountDisInfo.getPdAmountDisInfoId() == null) {
						Long pdAmountDisInfoId = commonService.getSeqValByName("SEQ_T_PD_AMOUNT_DIS_INFO");
						pdAmountDisInfo.setPdAmountDisInfoId(pdAmountDisInfoId);
						pdAmountDisInfoMapper.insert(pdAmountDisInfo);
					} else {
						pdAmountDisInfoMapper.updateByPrimaryKey(pdAmountDisInfo);
					}
				}
			} else if (operate != null && !"".equals(operate) && "modifyPdAmountDis".equals(operate)) {
				PdAmountDisInfoExample pdAmountDisInfoExample = new PdAmountDisInfoExample();
				PdAmountDisInfoExample.Criteria criteria = pdAmountDisInfoExample.createCriteria();
				criteria.andProductIdEqualTo(productId).andExpectOpenDayEqualTo(expectOpenDay)
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<PdAmountDisInfo> existAmountDisInfoList = pdAmountDisInfoMapper
						.selectByExample(pdAmountDisInfoExample);
				// 修改时先删除原来所有的分配信息
				for (PdAmountDisInfo existPdAmountDisInfo : existAmountDisInfoList) {
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(existPdAmountDisInfo, loginInfo);
					pdAmountDisInfoMapper.updateByPrimaryKey(existPdAmountDisInfo);
				}
				// 保存新的分配信息
				for (PdAmountDisInfo pdAmountDisInfo : pdAmountDisInfoList) {
					pdAmountDisInfo.setProductId(productId);
					pdAmountDisInfo.setExpectOpenDay(expectOpenDay);
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdAmountDisInfo, loginInfo);
					if (pdAmountDisInfo.getPdAmountDisInfoId() == null) {
						Long pdAmountDisInfoId = commonService.getSeqValByName("SEQ_T_PD_AMOUNT_DIS_INFO");
						pdAmountDisInfo.setPdAmountDisInfoId(pdAmountDisInfoId);
						pdAmountDisInfoMapper.insert(pdAmountDisInfo);
					} else {
						pdAmountDisInfoMapper.updateByPrimaryKey(pdAmountDisInfo);
					}
				}
			}
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 查询产品
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultInfo getProductInfo(String productId) {
		ResultInfo resultInfo = new ResultInfo();
		Map productMap = new HashMap();
		try {
			PDProduct pdProduct = pDProductMapper.selectByPrimaryKey(new Long(productId));
			Map<String, String> productMapInfo = JsonUtils.objectToMap(pdProduct);
			// 添加产品基本信息
			productMap.put("productInfo", productMapInfo);
			PDWealthExample pdWealthExample = new PDWealthExample();
			PDWealthExample.Criteria criteria = pdWealthExample.createCriteria();
			criteria.andProductIdEqualTo(new Long(productId)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDWealth> pdWealthList = pDWealthMapper.selectByExample(pdWealthExample);
			if (pdWealthList != null && pdWealthList.size() > 0) {
				productMap.put("pdWealthInfo", JsonUtils.objectToMap(pdWealthList.get(0)));
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(productMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 获取额度分配信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getPdAmountDisInfo(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map> pdAmountDisMapList = productServiceMapper.getPdAmountDisInfo(paramMap);
			resultInfo.setObj(pdAmountDisMapList);
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid getAllPdAmountDisInfo(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = productServiceMapper.getAllPdAmountDisInfoCount(paramMap);
		List<Map> resultList = productServiceMapper.getAllPdAmountDisInfo(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 获取产品额度的分配及预约
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getProductAmountDisAndOrderInfo(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// Map map =
			// productServiceMapper.getProductAmountDisAndOrderInfo(paramMap);
			String productId = paramMap.get("productId").toString();
			String expectOpenDay = paramMap.get("expectOpenDay").toString();
			String financingScale = "";
			PDProduct pdProduct = pDProductMapper.selectByPrimaryKey(new Long(productId));
			String productType = pdProduct.getProductType();
			String productSubType = pdProduct.getProductSubType();
			// 获取财富产品信息
			PDWealthExample pdWealthExample = new PDWealthExample();
			PDWealthExample.Criteria pdWealthCriteria = pdWealthExample.createCriteria();
			pdWealthCriteria.andProductIdEqualTo(pdProduct.getProductId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			PDWealth pdWealth = pDWealthMapper.selectByExample(pdWealthExample).get(0);
			// 财富产品固定收益类
			if ("1".equals(productType) && ("01".equals(productSubType) ||"02".equals(productSubType))) {
				financingScale = pdWealth.getFinancingScale().toString();
			}
			// 财富产品浮动收益类及股权类
			else if ("1".equals(productType) &&  "03".equals(productSubType)) {
				PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
				PDWealthOpenDateExample.Criteria pdWealthOpenDateCriteria = pdWealthOpenDateExample.createCriteria();
				pdWealthOpenDateCriteria.andPdWealthIdEqualTo(pdWealth.getPdWealthId())
						.andOpenDateEqualTo(DateUtils.getDate(expectOpenDay))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				PDWealthOpenDate pdWealthOpenDate = pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample)
						.get(0);
				if (pdWealthOpenDate.getFinancingScale() != null) {
					financingScale = pdWealthOpenDate.getFinancingScale().toString();
				}
			}
			// 已预约金额
			PDAmountOrderInfoExample pdAmountOrderInfoExample = new PDAmountOrderInfoExample();
			PDAmountOrderInfoExample.Criteria pdAmountOrderInfoCriteria = pdAmountOrderInfoExample.createCriteria();
			pdAmountOrderInfoCriteria.andProductIdEqualTo(new Long(productId))
					.andExpectOpenDayEqualTo(DateUtils.getDate(expectOpenDay)).andOrderStatusNotEqualTo("03")
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDAmountOrderInfo> pdAmountOrderInfoList = pdAmountOrderInfoMapper
					.selectByExample(pdAmountOrderInfoExample);
			double orderTotalAmount = 0;
			for (PDAmountOrderInfo pdAmountOrderInfo : pdAmountOrderInfoList) {
				orderTotalAmount += pdAmountOrderInfo.getOrderAmount().doubleValue();
			}
			// 已分配金额
			PdAmountDisInfoExample pdAmountDisInfoExample = new PdAmountDisInfoExample();
			PdAmountDisInfoExample.Criteria pdAmountDisInfoCriteria = pdAmountDisInfoExample.createCriteria();
			pdAmountDisInfoCriteria.andProductIdEqualTo(new Long(productId))
					.andExpectOpenDayEqualTo(DateUtils.getDate(expectOpenDay))
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PdAmountDisInfo> pdAmountDisInfoList = pdAmountDisInfoMapper.selectByExample(pdAmountDisInfoExample);
			double disTotalAmount = 0;
			for (PdAmountDisInfo pdAmountDisInfo : pdAmountDisInfoList) {
				disTotalAmount += pdAmountDisInfo.getAmount().doubleValue();
			}
			// 返回信息
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("financingScale", financingScale);
			resultMap.put("orderTotalAmount", new BigDecimal(orderTotalAmount).toString());
			resultMap.put("disTotalAmount", new BigDecimal(disTotalAmount).toString());
			resultMap.put("remainTotalAmount", new BigDecimal(disTotalAmount - orderTotalAmount).toString());
			resultInfo.setObj(resultMap);
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 获取产品报告信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid getAllProductReportInfo(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		// 获取符合条件的行数
		Integer total = productServiceMapper.getAllProductReportInfoCount(paramMap);
		List<Map> resultList = productServiceMapper.getAllProductReportInfo(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@Transactional
	public List<Map<String, String>> tproductQueryAllRelease() {
		PDProductExample pDProductExample = new PDProductExample();
		pDProductExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andStatusEqualTo("2");
		List<PDProduct> pdProductList = pDProductMapper.selectByExample(pDProductExample);
		List<Map<String, String>> productMapList = new ArrayList<Map<String, String>>();
		for (PDProduct pDProduct : pdProductList) {
			Map<String, String> productMap = new HashMap<String, String>();
			String productId = String.valueOf(pDProduct.getProductId());
			String productCode = pDProduct.getProductCode();
			String productName = pDProduct.getProductName();
			productMap.put("code", productId);
			productMap.put("codeName", productCode + "-" + productName);
			productMapList.add(productMap);
		}
		return productMapList;
	}

	/**
	 * 删除产品报告
	 */
	public ResultInfo deleteProductReport(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		DefFileInfoExample defFileInfoExample = new DefFileInfoExample();
		defFileInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
				.andFileInfoIdEqualTo(new Long(param));
		List<DefFileInfo> defFileInfos = defFileInfoMapper.selectByExample(defFileInfoExample);
		if (defFileInfos != null && defFileInfos.size() > 0) {
			DefFileInfo defFileInfo = (DefFileInfo) defFileInfos.get(0);
			com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(defFileInfo, loginInfo);
			defFileInfoMapper.updateByPrimaryKey(defFileInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("删除成功");
		}
		return resultInfo;
	}

	/**
	 * 保存产品附文本问题
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo addProductEditorInfo(Map tMap, LoginInfo loginInfo) {

		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag = null;
		String productId = tMap.get("productId").toString();
		if (tMap.get("editorInfoSucFlag") != null) {
			editorInfoSucFlag = tMap.get("editorInfoSucFlag").toString();
		}
		String htmlContext = null;
		if (!editorInfoSucFlag.equals("sucFlag")) {
			/*** 新增附文本编辑框信息 ****/
			PDUeditor pdUeditor = new PDUeditor();
			if (tMap.get("htmlContext") != null) {
				htmlContext = tMap.get("htmlContext").toString().trim();
			}
			Long pdUEditorSeq = commonService.getSeqValByName("SEQ_T_PD_UEDITOR");
			pdUeditor.setUeditorId(pdUEditorSeq);
			pdUeditor.setProductId(new Long(productId));
//			pdUeditor.setEditorHtml(htmlContext);
			pdUeditor.setEditorHtmlClob(htmlContext);
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(pdUeditor, loginInfo);
			pDUeditorMapper.insert(pdUeditor);
			resultInfo.setSuccess(true);
			resultInfo.setObj("sucFlag");
			resultInfo.setMsg("产品附文本信息保存成功");
			return resultInfo;
		} else {
			/*** 重复提交附文本编辑框信息 ****/
			PDUeditor pdUeditorExist = null;
			PDUeditorExample pdUeditorExample = new PDUeditorExample();
			pdUeditorExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
					.andProductIdEqualTo(new Long(productId));
			List<PDUeditor> pdUeditors = pDUeditorMapper.selectByExample(pdUeditorExample);
			if (pdUeditors != null && pdUeditors.size() > 0) {
				pdUeditorExist = pdUeditors.get(0);
				com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(pdUeditorExist, loginInfo);
				pDUeditorMapper.updateByPrimaryKey(pdUeditorExist);
			}
			/****** 删除后，重新插入一条记录 ********/
			PDUeditor newPdUeditor = new PDUeditor();
			if (tMap.get("htmlContext") != null) {
				htmlContext = tMap.get("htmlContext").toString().trim();
			}
			Long pdUEditorSeq = commonService.getSeqValByName("SEQ_T_PD_UEDITOR");
			newPdUeditor.setUeditorId(pdUEditorSeq);
			newPdUeditor.setProductId(new Long(productId));
//			newPdUeditor.setEditorHtml(htmlContext);
			newPdUeditor.setEditorHtmlClob(htmlContext);
			if (pdUeditorExist != null) {
				com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(pdUeditorExist, newPdUeditor,
						loginInfo);
			} else {
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(newPdUeditor, loginInfo);
			}
			pDUeditorMapper.insert(newPdUeditor);
			resultInfo.setSuccess(true);
			resultInfo.setObj("sucFlag");
			resultInfo.setMsg("产品附文本信息保存成功");
			return resultInfo;
		}
	}

	
	
	/**
	 * 获取符文本编辑信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getProductEditorContext(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		if (paramMap!=null) {
			String htmlContextClob= productServiceMapper.getProductEditorContext(paramMap);	
			resultInfo.setSuccess(true);
			resultInfo.setObj(htmlContextClob);
		}
		return resultInfo;
	}

	/**
	 * 产品发布，同步信息至互联网端接口
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public ResultInfo internetRelease(Map param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map pramMap = new HashMap();
		Long WebserviceLogSeq = commonService.getSeqValByName("SEQ_T_WEBSERVICE_LOG_INFO");
		WebServiceLogInfo webserviceLogInfo = new WebServiceLogInfo();// 存储在日志表
		String productIdExist = "";
		if (param != null && param.get("productId") != null) {
			productIdExist = param.get("productId").toString();
			PDProductExample pdProductExample = new PDProductExample();
			pdProductExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
					.andProductIdEqualTo(new Long(productIdExist));
			PDProduct pdProduct = pDProductMapper.selectByExample(pdProductExample).get(0);
			// 校验该产品是否需要发布到互联网
			if (pdProduct.getProductType().equals("1")) {
				PDWealthSalesInfoExample pdWealthSalesInfoExample = new PDWealthSalesInfoExample();
				pdWealthSalesInfoExample.createCriteria().
										 andRcStateEqualTo(Constants.EFFECTIVE_RECORD).
										 andProductIdEqualTo(pdProduct.getProductId());
				List<PDWealthSalesInfo> pdWealthSalesInfos = pdWealthSalesInfoMapper.selectByExample(pdWealthSalesInfoExample);
				if (pdWealthSalesInfos != null && pdWealthSalesInfos.size() > 0) {
					// 如果推介方式为01-在线则同步到互联网，否则不需要同步至互联网端
					if (pdWealthSalesInfos.get(0).getPromotionWay() != null&& pdWealthSalesInfos.get(0).getPromotionWay().contains("02")) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该产品的推介方式为线下推介，无需同步至互联网端");
						return resultInfo;
					} else {
						pramMap.put("productId", pdProduct.getProductId());
						pramMap.put("productType", pdProduct.getProductType());
						pramMap.put("productSubType", pdProduct.getProductSubType());
						resultInfo = eWealthClientService.ewealthGetProductInfo(pramMap);// 调用产品同步接口
					}
				} else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("该产品的没有配置推介方式为，默认为线下推介，无需同步至互联网端");
					return resultInfo;
				}

			} else {
				PDRiskSalesInfoExample pdRiskSalesInfoExample = new PDRiskSalesInfoExample();
				pdRiskSalesInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductIdEqualTo(pdProduct.getProductId());
				List<PDRiskSalesInfo> pdRiskSalesInfos = pDRiskSalesInfoMapper.selectByExample(pdRiskSalesInfoExample);
				if (pdRiskSalesInfos != null && pdRiskSalesInfos.size() > 0) {
					// 如果推介方式为01-在线则同步到互联网，否则不需要同步至互联网端
					if (pdRiskSalesInfos.get(0).getPromotionWay() != null&& pdRiskSalesInfos.get(0).getPromotionWay().contains("02")) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该产品的推介方式为线下推介，无需同步至互联网端");
						return resultInfo;
					} else {
						pramMap.put("productId", pdProduct.getProductId());
						pramMap.put("productType", pdProduct.getProductType());
						pramMap.put("productSubType", pdProduct.getProductSubType());
						resultInfo = eWealthClientService.ewealthGetProductInfo(pramMap);// 调用产品同步接口
			
					}
				} else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("该产品的没有配置推介方式为，默认为线下推介，无需同步至互联网端");
					return resultInfo;
				}
			}
			if (resultInfo.isSuccess()) {
				// 存储日志信息
				webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
				webserviceLogInfo.setWebserviceCode("eWealthGetProductInfo");
				webserviceLogInfo.setBusinessNo(productIdExist);
				webserviceLogInfo.setWebserviceName("产品发布信息同步接口");
				webserviceLogInfo.setResponseStatus("01");// 02-失败
				webserviceLogInfo.setXmlContent(resultInfo.getObj().toString());
				webserviceLogInfo.setResponseMsg(resultInfo.getMsg());
				webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
				webserviceLogInfoMapper.insert(webserviceLogInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("产品信息同步成功");
				return resultInfo;
			}
			// 产品同步互联网端成功
			else {
				// 存储日志信息
				webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
				webserviceLogInfo.setWebserviceCode("eWealthGetProductInfo");
				webserviceLogInfo.setBusinessNo(productIdExist);
				webserviceLogInfo.setWebserviceName("产品发布信息同步接口");
				webserviceLogInfo.setResponseStatus("02");// 02-失败
				webserviceLogInfo.setXmlContent(resultInfo.getObj()!=null?resultInfo.getObj().toString():null);
				webserviceLogInfo.setResponseMsg(resultInfo.getMsg());
				webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
				webserviceLogInfoMapper.insert(webserviceLogInfo);
				resultInfo.setSuccess(false);
				resultInfo.setMsg("产品信息同步失败：原因是：" + resultInfo.getMsg());
				return resultInfo;
			}
		} else {
			webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
			webserviceLogInfo.setWebserviceCode("eWealthGetProductInfo");
			webserviceLogInfo.setBusinessNo(productIdExist);
			webserviceLogInfo.setWebserviceName("产品发布信息同步接口");
			webserviceLogInfo.setResponseStatus("02");// 02-失败
			webserviceLogInfo.setXmlContent(null);
			webserviceLogInfo.setResponseMsg(resultInfo.getMsg());
			webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
			webserviceLogInfoMapper.insert(webserviceLogInfo);
			resultInfo.setSuccess(false);
			resultInfo.setMsg("产品信息同步失败，获取产品相关信息失败" );
			return resultInfo;
		}
	}

	/**
	 * 新增修改净值信息，同步互联网接口
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo internetNetValue(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Long WebserviceLogSeq = commonService.getSeqValByName("SEQ_T_WEBSERVICE_LOG_INFO");
		WebServiceLogInfo webserviceLogInfo = new WebServiceLogInfo();// 存储在日志表
		if (paramMap != null && paramMap != null) {
			resultInfo = eWealthClientService.ewealthGetNetValueInfo(paramMap);
			if (resultInfo.isSuccess()) {
				// 存储日志信息
				webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
				webserviceLogInfo.setWebserviceCode("eWealthGetNetValueInfo");
				webserviceLogInfo.setBusinessNo(paramMap.get("productId").toString());
				webserviceLogInfo.setWebserviceName("产品净值信息同步接口");
				webserviceLogInfo.setResponseStatus("01");// 02-失败
				webserviceLogInfo.setXmlContent(resultInfo.getObj().toString());
				webserviceLogInfo.setResponseMsg(resultInfo.getMsg());
				webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
				webserviceLogInfoMapper.insert(webserviceLogInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("产品净值信息同步成功");
				return resultInfo;
			} else {
				webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
				webserviceLogInfo.setWebserviceCode("eWealthGetNetValueInfo");
				webserviceLogInfo.setBusinessNo(paramMap.get("productId").toString());
				webserviceLogInfo.setWebserviceName("产品净值信息同步接口");
				webserviceLogInfo.setResponseStatus("02");// 02-失败
				webserviceLogInfo.setXmlContent(resultInfo.getObj() != null ? resultInfo.getObj().toString() : "");
				webserviceLogInfo.setResponseMsg(resultInfo.getMsg());
				webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
				webserviceLogInfoMapper.insert(webserviceLogInfo);
				resultInfo.setSuccess(false);
				return resultInfo;
			}
		} else {
			webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
			webserviceLogInfo.setWebserviceCode("eWealthGetNetValueInfo");
			webserviceLogInfo.setBusinessNo("");
			webserviceLogInfo.setWebserviceName("产品发布信息同步接口");
			webserviceLogInfo.setResponseStatus("02");// 02-失败
			webserviceLogInfo.setXmlContent(resultInfo.getObj() != null ? resultInfo.getObj().toString() : "");
			webserviceLogInfo.setResponseMsg("获取产品流水号失败，无法执行产品净值信息同步接口");
			webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
			webserviceLogInfoMapper.insert(webserviceLogInfo);
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品流水号失败，无法执行产品净值信息同步接口");
		}
		return resultInfo;
	}

	/**
	 * 新增修改开放日信息，同步互联网接口
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo internetOpenDay(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Long WebserviceLogSeq = commonService.getSeqValByName("SEQ_T_WEBSERVICE_LOG_INFO");
		WebServiceLogInfo webserviceLogInfo = new WebServiceLogInfo();// 存储在日志表
		if (paramMap != null && paramMap != null) {
			resultInfo = eWealthClientService.ewealthGetOpenyDayInfo(paramMap);
			if (resultInfo.isSuccess()) {
				// 存储日志信息
				webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
				webserviceLogInfo.setWebserviceCode("eWealthGetNetValueInfo");
				webserviceLogInfo.setBusinessNo(paramMap.get("productId").toString());
				webserviceLogInfo.setWebserviceName("产品开放日信息同步接口");
				webserviceLogInfo.setResponseStatus("01");// 02-失败
				webserviceLogInfo.setXmlContent(resultInfo.getObj().toString());
				webserviceLogInfo.setResponseMsg(resultInfo.getMsg());
				webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
				webserviceLogInfoMapper.insert(webserviceLogInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("产品开放日信息同步成功");
				return resultInfo;
			} else {
				webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
				webserviceLogInfo.setWebserviceCode("eWealthGetNetValueInfo");
				webserviceLogInfo.setBusinessNo(paramMap.get("productId").toString());
				webserviceLogInfo.setWebserviceName("产品开放日信息同步接口");
				webserviceLogInfo.setResponseStatus("02");// 02-失败
				webserviceLogInfo.setXmlContent(resultInfo.getObj() != null ? resultInfo.getObj().toString() : "");
				webserviceLogInfo.setResponseMsg(resultInfo.getMsg());
				webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
				webserviceLogInfoMapper.insert(webserviceLogInfo);
				resultInfo.setSuccess(false);
				return resultInfo;
			}
		} else {
			webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
			webserviceLogInfo.setWebserviceCode("eWealthGetNetValueInfo");
			webserviceLogInfo.setBusinessNo("");
			webserviceLogInfo.setWebserviceName("产品开放日信息同步接口");
			webserviceLogInfo.setResponseStatus("02");// 02-失败
			webserviceLogInfo.setXmlContent(resultInfo.getObj() != null ? resultInfo.getObj().toString() : "");
			webserviceLogInfo.setResponseMsg("获取产品流水号失败，无法执行产品开放日信息同步接口");
			webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
			webserviceLogInfoMapper.insert(webserviceLogInfo);
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品流水号失败，无法执行产品开放日信息同步接口");
		}
		return resultInfo;
	}

	public Map getProductNetValues(Map paramMap) {
		Map datas = new HashMap();
		try {
			List<Map> resultList = productServiceMapper.getProductNetValues(paramMap);
			datas.put("data", resultList);
		} catch (Exception e) {
			e.printStackTrace();
			datas.put("Msg", e.getMessage());
		}
		return datas;
	}
	
	
	/**
	 * 获取固收产品投后清单信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid getAlltFixedProductIncomeInfo(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		// 获取符合条件的行数
		Integer total = productServiceMapper.getAllFixedProductIncomeInfoCount(paramMap);
		List<Map> resultList = productServiceMapper.getAllFixedProductIncomeInfo(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 		
	 * 		下载固收产品详情信息
	 * */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map fixedProductDetail(Map paramMap){
		// 输出日志，便于调试
		log.info("Service层方法fixedProductDetail()接受参数个数:" + paramMap.size());
		// Mapper接口方法：查详情信息
		List<Map> fixedProductDetailList = productServiceMapper.queryfixedProductDetailList(paramMap);
		Map<String, Object> datas = new HashMap<String, Object>();
	    datas.put("data", fixedProductDetailList);
	    return datas;
	}	

	/**
	 * 固收产品投后清单信息导出
	 */
	@Override
	public Map exportFixedProIncomeInfo(Map paramMap) {
		List<Map<String,String>> fixedProIncomeInfo = this.productServiceMapper.exportFixedProductIncomeInfo(paramMap);
		Map<String, Object> workDatas = new HashMap<String, Object>();
		workDatas.put("data", fixedProIncomeInfo);
	    return workDatas;
	}

	/**
	 * 查询固收产品投后详单
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryDetailFixedProIncomeList(DataGridModel dgm, Map paramMap) {
			if (paramMap == null) {
				paramMap = new HashMap();
			}
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			// 获取符合条件的行数
			Integer total = productServiceMapper.queryDetailFixedProIncomeCount(paramMap);
			List<Map> resultList = productServiceMapper.queryDetailFixedProIncomeList(paramMap);
			DataGrid dataGrid = new DataGrid();
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
			return dataGrid;
	}
	/**
	 * @author wanghao
	 * 每周周二产品净值披露频率任务代办
	 * @throws ParseException 
	 */
	@Override
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo createNetValueWeekBatch() throws ParseException {
		PDWealthNetValueTask pdWealthNetValueTask = new PDWealthNetValueTask();
		ResultInfo resultInfo = new ResultInfo();
		PDWealthExample pdWealthExample = new PDWealthExample();
		PDWealthExample.Criteria pdWealthCriteria = pdWealthExample.createCriteria();
		pdWealthCriteria.andNetValueDisclosureEqualTo("01")
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<PDWealth> netValueWeekList = pDWealthMapper.selectByExample(pdWealthExample);
		pdWealthNetValueTask.setHandleState("01");
		pdWealthNetValueTask.setCreateUserId(new Long("1"));
		pdWealthNetValueTask.setOperComId(new Long("1"));
		pdWealthNetValueTask.setRcState(Constants.EFFECTIVE_RECORD);
		pdWealthNetValueTask.setModifyUserId(new Long("1"));
		if(netValueWeekList != null && netValueWeekList.size() > 0){
			for (PDWealth pdwealthInfo : netValueWeekList) {
				//判断是否已经生成开放日相对应的记录
				/*Map paramMap = new HashMap();
				paramMap.put("productId", pdwealthInfo.getProductId());
				int existRecordCount = productServiceMapper.getOpenDateRecord(paramMap);
				if (existRecordCount < 1) {*/
				//判断该产品上周五是否是开放日 如果是开放日 则不需再生成一条净值待办任务
					//获取上周五日期
				String date = DateUtils.calDate(DateUtils.getCurrentDate(), -4, "D");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date netDate = sdf.parse(date);
				PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
				pdWealthOpenDateExample.createCriteria().andPdWealthIdEqualTo(pdwealthInfo.getPdWealthId())
				.andOpenDateEqualTo(netDate).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<PDWealthOpenDate> pdWealthOpenDateList = pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample);
				if (pdWealthOpenDateList.isEmpty()) {
					pdWealthNetValueTask.setProductId(pdwealthInfo.getProductId());
					pdWealthNetValueTask.setCreateDate(new Date());
					pdWealthNetValueTask.setModifyDate(new Date());
					Date d = new Date(); 
					Date taskCommissionDate = new Date();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					taskCommissionDate = df.parse(df.format(new Date(d.getTime() - (long)4 * 24 * 60 * 60 * 1000)));
					pdWealthNetValueTask.setTaskCommission(taskCommissionDate);
					pDWealthNetValueTaskMapper.insert(pdWealthNetValueTask);
				}
			}
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("处理成功");
		return resultInfo;
		
		
	}

	
	
	/**
	 * 查询净值待办任务列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid searchNetValueTask(DataGridModel dgm, Map paramMap) {
			if (paramMap == null) {
				paramMap = new HashMap();
			}
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			// 获取符合条件的行数
			Integer total = productServiceMapper.searchNetValueTaskCount(paramMap);
			List<Map> resultList = productServiceMapper.searchNetValueTaskList(paramMap);
			DataGrid dataGrid = new DataGrid();
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
			return dataGrid;
	}

	/**
	 * 每月一次产品净值披露频率任务代办
	 * chengong
	 */
	@Override
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void netValueMonthBatch() {
		PDWealthExample pdWealthExample = new PDWealthExample();
		pdWealthExample.createCriteria().andNetValueDisclosureEqualTo("02").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<PDWealth> pdWealthList = pDWealthMapper.selectByExample(pdWealthExample);
		if(!pdWealthList.isEmpty()){
			//*******判断月底是否是假期，如果是，就减一天，直到它不是。***********************************
			Calendar cal=Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, -2);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			String taskDay=sdf.format(cal.getTime());
			HolidaysExample holidaysExample = new HolidaysExample();
	 	 		holidaysExample.createCriteria().andHolidayEqualTo(taskDay);
	 	 		List<Holidays> holidays = holidaysMapper.selectByExample(holidaysExample);
	 	 		while(!holidays.isEmpty()){
	 	 			cal.add(Calendar.DAY_OF_MONTH, -1);	 	 			
	 				taskDay = sdf.format(cal.getTime());
	 				HolidaysExample holidaysExample1 = new HolidaysExample();
	 				holidaysExample1.createCriteria().andHolidayEqualTo(taskDay);
	 				holidays = holidaysMapper.selectByExample(holidaysExample1);
	 	 		}
	 	 	//******************************************************************************	
			PDWealthNetValueTask pdWealthNetValueTask=new PDWealthNetValueTask();
			pdWealthNetValueTask.setTaskCommission(cal.getTime());
			pdWealthNetValueTask.setHandleState("01");
			pdWealthNetValueTask.setCreateUserId(new Long("1"));
			pdWealthNetValueTask.setOperComId(new Long("1"));
			pdWealthNetValueTask.setRcState(Constants.EFFECTIVE_RECORD);
			pdWealthNetValueTask.setModifyUserId(new Long("1"));
			for (PDWealth pdWealth : pdWealthList) {
				//判断是否已经生成开放日相对应的记录
				Map paramMap = new HashMap();
				paramMap.put("productId", pdWealth.getProductId());
				int existRecordCount = productServiceMapper.getOpenDateRecord(paramMap);
				if (existRecordCount < 1) {
					pdWealthNetValueTask.setProductId(pdWealth.getProductId());				
					pdWealthNetValueTask.setCreateDate(new Date());				
					pdWealthNetValueTask.setModifyDate(new Date());				
					pDWealthNetValueTaskMapper.insert(pdWealthNetValueTask);
				}
			}
		}
	}

	/**
	 * 新增产品净值信息(净值披露频率任务代办)
	 * @author wanghao
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public String addNetValueTaskInfo(Map tMap, LoginInfo loginInfo) {
		PDWealthNetValue pDWealthNetValue = (PDWealthNetValue) tMap.get("pDWealthNetValueSchema");
		String returnPdwealthNetValueId = null;
		String existPdwealthNetValueId = null;
		// 重复提交：对于新增产品重复提交的情况，直接更新产品主表其他的表，全部做逻辑删除，然后在重新INSERT数据
		if (tMap.get("existPdwealthdNetValueId").toString().equals("isnull")) {
			returnPdwealthNetValueId = insertWealthNetValueInfo(tMap, loginInfo);
			ResultInfo resultInfo = new ResultInfo();
			//发送产品净值短信
			String isOrder = pDWealthNetValue.getIsOrder();
			String specialType = pDWealthNetValue.getSpecialType();
			resultInfo = smsService.createPublicNetValueMes(returnPdwealthNetValueId,isOrder,specialType, loginInfo);
		} else {
			existPdwealthNetValueId = tMap.get("existPdwealthdNetValueId").toString();
			tMap.put("modifyRecordNetWorthId", existPdwealthNetValueId);
			tMap.put("publicDate", pDWealthNetValue.getPublicDate());
			tMap.put("netType", pDWealthNetValue.getNetType());
			tMap.put("netValue", pDWealthNetValue.getNetValue());
			tMap.put("isOrder", pDWealthNetValue.getIsOrder());
			tMap.put("specialType", pDWealthNetValue.getSpecialType());
			tMap.put("earnRate", pDWealthNetValue.getEarnRate());
			returnPdwealthNetValueId = modifyWealthNetValueInfo(existPdwealthNetValueId, tMap, loginInfo, "add");
			
		}
		String  pdWealthNetValueTaskId = (String )tMap.get("netValueTaskId");
		//更改产品净值代办 处理状态
		PDWealthNetValueTask pDWealthNetValueTask=pDWealthNetValueTaskMapper.selectByPrimaryKey(new Long(pdWealthNetValueTaskId));
		if(pDWealthNetValueTask!=null){
			pDWealthNetValueTask.setHandleState("02");
			//将操作人相关信息修改
			BeanUtils.updateObjectSetOperateInfo(pDWealthNetValueTask, loginInfo);
			pDWealthNetValueTaskMapper.updateByPrimaryKey(pDWealthNetValueTask);
		}
		
		return returnPdwealthNetValueId;
	}


	/**
	 * 净值待办标记为已处理
	 * louao
	 */
	@Override
	public ResultInfo setNetValueTaskState(String pdWealthNetValueTaskId,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			//得到净值待办任务
			PDWealthNetValueTask pDWealthNetValueTask=pDWealthNetValueTaskMapper.selectByPrimaryKey(new Long(pdWealthNetValueTaskId));
			if(pDWealthNetValueTask!=null){
				pDWealthNetValueTask.setHandleState("02");
				//将操作人相关信息修改
				BeanUtils.updateObjectSetOperateInfo(pDWealthNetValueTask, loginInfo);
				pDWealthNetValueTaskMapper.updateByPrimaryKey(pDWealthNetValueTask);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("处理成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("处理失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 自动生成开放日净值待办任务
	 * @author ZYM
	 * @throws Exception 
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void openDateBatch() throws Exception {
		//当前日期减一天
		Map paramMap = new HashMap();
		String date = DateUtils.calDate(DateUtils.getCurrentDate(), -1, "D");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date netDate = sdf.parse(date);
		paramMap.put("openDate", date);
		//从pd_wealth_open_date中查询所有与当前日期减一天相符的对象
		List<Map> listResult = productServiceMapper.getOpenDateProductInfo(paramMap);
		if (listResult.isEmpty()) {
			return;
		}else {
			//循环遍历数组插入对应开放日的产品信息
			for (Map map : listResult) {
				Long productId = new Long(map.get("productId").toString());
				PDWealthNetValueTask pdWealthNetValueTask = new PDWealthNetValueTask();
				pdWealthNetValueTask.setProductId(productId);
				pdWealthNetValueTask.setHandleState("01");
				pdWealthNetValueTask.setRcState("E");
				pdWealthNetValueTask.setCreateDate(DateUtils.getCurrentTimestamp());
				pdWealthNetValueTask.setModifyDate(DateUtils.getCurrentTimestamp());
				pdWealthNetValueTask.setCreateUserId(new Long(1));
				pdWealthNetValueTask.setModifyUserId(new Long(1));
				pdWealthNetValueTask.setOperComId(new Long(1));
				pdWealthNetValueTask.setTaskCommission(netDate);
				pDWealthNetValueTaskMapper.insert(pdWealthNetValueTask);
			}
		}
		
	}

/**
	 * 创建期间分配到账短信
	 * @author ZYM
	 */
	@Override
	public ResultInfo createIncomeToAcctSms(Map paramMap,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		resultInfo = smsService.createIncomeToAcctSms(paramMap, loginInfo);
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 创建到期清算到账短信
	 * @author ZYM
	 */
	@Override
	public ResultInfo createAllIncomeToAcctSms(Map paramMap,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		resultInfo = smsService.createAllIncomeToAcctSms(paramMap, loginInfo);
		resultInfo.setSuccess(true);
		return resultInfo;
	}
/**
	 * 保存新增合同信息
	 * @return
	 */ 
	@Override
	public ResultInfo saveContractInfo(PDContract contractInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			//得到起始序列号
			int startNum = Integer.parseInt(contractInfo.getContractStartNum());
			int endNum = Integer.parseInt(contractInfo.getContractEndNum());
			int numTemp = startNum;
			//将信息先存到合同主表中
			PDContract contractInfoTemp = new PDContract();
			contractInfoTemp.setContractCode(contractInfo.getContractCode());
			contractInfoTemp.setContractStartNum(contractInfo.getContractStartNum());
			contractInfoTemp.setContractEndNum(contractInfo.getContractEndNum());
			contractInfoTemp.setProductId(contractInfo.getProductId());
			//根据productId判断该产品已经录过合同信息
			PDContractExample contractExample = new PDContractExample();
			PDContractExample.Criteria contractCriteria = contractExample.createCriteria();
			contractCriteria.andProductIdEqualTo(contractInfo.getProductId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDContract> contractTempList = pDContractMapper.selectByExample(contractExample);
			if (contractTempList != null && contractTempList.size() > 0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该产品合同信息已存在!");
				return resultInfo;
			}else {
				BeanUtils.insertObjectSetOperateInfo(contractInfoTemp, loginInfo);
				pDContractMapper.insert(contractInfoTemp);
				//将信息循环插入合同详情表中
				for (int i = startNum; i <= endNum; i++) {
					String str1 = "**" + numTemp;
					String str2 = str1.substring(str1.length() - 3, str1.length());
					// 将所有*替换为0
					str2 = str2.replace("*", "0");
					String contractCodeTemp = contractInfo.getContractCode();
					String finalStr = contractCodeTemp +"-"+str2;
					PDContractDetail pdContractDetail = new PDContractDetail();
					pdContractDetail.setContractId(contractInfoTemp.getContractId());
					pdContractDetail.setContractNumber(finalStr);
					pdContractDetail.setContractStatus("02");//合同号初始化为未用状态
					//根据productId和合同号判断是否重复
					BeanUtils.insertObjectSetOperateInfo(pdContractDetail, loginInfo);
					pDContractDetailMapper.insert(pdContractDetail);
					numTemp = i + 1;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("合同信息保存失败!");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("新增合同信息成功!");
		return resultInfo;
	}
	
	/**
	 * 合同管理页面初始化查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getContractInfoPageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = productServiceMapper.contractQueryListCount(paramMap);
		List<Map> resultList = productServiceMapper.contractQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 产品合同详细信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getDetailContractInfoPageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = productServiceMapper.contractDetailQueryListCount(paramMap);
		List<Map> resultList = productServiceMapper.contractDetailQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 获取产品合同信息
	 */
	@Transactional
	public ResultInfo getUpdatePDContractInfo(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> productInfoMap = new HashMap<String, Object>();
		// 获取需要修改的产品主表信息T_PD_PRODUCT
		PDContractExample pdContractExample = new PDContractExample();
		pdContractExample.createCriteria().andProductIdEqualTo(new Long(param)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<PDContract> pdContractInfoList = pDContractMapper.selectByExample(pdContractExample);
		productInfoMap.put("productContractInfo", JsonUtils.objectToMap(pdContractInfoList.get(0)));
		resultInfo.setObj(productInfoMap);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("获取产品基本信息成功！");
		return resultInfo;
	}

	
	/**
	 * 修改产品合同信息
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public ResultInfo updatePdContractInfo(Map tMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 校验该产品编码是否重复
		String existProductId = tMap.get("productId").toString();
		PDContract newPDContract = (PDContract) tMap.get("pdContractInfo");
		if (existProductId != null) {
			//获取历史记录
			PDContractExample pdContractExample = new PDContractExample();
			pdContractExample.createCriteria().andProductIdEqualTo(new Long(existProductId))
			.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDContract> pdContracts = pDContractMapper.selectByExample(pdContractExample);
			if (pdContracts.size() < 1) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取合同信息有误！");
				return resultInfo;
			}
			PDContract existPDContract = pdContracts.get(0);
			//若想修改合同编码 则现需判断是否有交易录入次产品的合同号
			if (!newPDContract.getContractCode().equals(existPDContract.getContractCode())) {
				PDContractDetailExample pdContractDetailExample = new PDContractDetailExample();
				pdContractDetailExample.createCriteria().andContractIdEqualTo(existPDContract.getContractId())
				.andContractStatusEqualTo("01").andRcStateEqualTo("E");
				List<PDContractDetail> pdContractDetails = pDContractDetailMapper.selectByExample(pdContractDetailExample);
				if (pdContractDetails.size() > 0) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("当前存在交易关联该产品合同，不可修改合同编码！");
					return resultInfo;
				}
			}
			//若为缩小范围，需判断缩小的范围中是否有已经使用的合同号
			Integer existContractEndNum = Integer.parseInt(existPDContract.getContractEndNum());
			Integer newContractEndNum = Integer.parseInt(newPDContract.getContractEndNum());
			if ( newContractEndNum < existContractEndNum) {
				for (int i = 0; i < (existContractEndNum - newContractEndNum); i++) {
					String str1 = "**" + (newContractEndNum+1+i);
					String str2 = str1.substring(str1.length() - 3, str1.length());
					// 将所有*替换为0
					str2 = str2.replace("*", "0");
					String contractCode = existPDContract.getContractCode();
					String finalStr = contractCode +"-"+str2;
					//查询该合同号是否在使用中
					PDContractDetailExample pdContractDetailExample = new PDContractDetailExample();
					pdContractDetailExample.createCriteria().andContractNumberEqualTo(finalStr).
					andContractStatusEqualTo("01").andRcStateEqualTo("E");
					List<PDContractDetail> pdContractDetails = pDContractDetailMapper.selectByExample(pdContractDetailExample);
					if (pdContractDetails.size() > 0) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("不能修改！因为缩小范围中包含正在使用的合同号！“" + finalStr +"”");
						return resultInfo;
					}
				}
				
				//循环将缩小的记录逻辑删除
				for (int i = 0; i < (existContractEndNum - newContractEndNum); i++) {
					String str1 = "**" + (newContractEndNum+1+i);
					String str2 = str1.substring(str1.length() - 3, str1.length());
					// 将所有*替换为0
					str2 = str2.replace("*", "0");
					String contractCode = existPDContract.getContractCode();
					String finalStr = contractCode +"-"+str2;
					PDContractDetailExample pdContractDetailExample = new PDContractDetailExample();
					pdContractDetailExample.createCriteria().andContractNumberEqualTo(finalStr).andRcStateEqualTo("E");
					List<PDContractDetail> pdContractDetails = pDContractDetailMapper.selectByExample(pdContractDetailExample);
					BeanUtils.deleteObjectSetOperateInfo(pdContractDetails.get(0), loginInfo);
					pDContractDetailMapper.updateByPrimaryKey(pdContractDetails.get(0));
				}
			}else {
				//若为放大范围，循环插入数据
				for (int i = 0; i < (newContractEndNum - existContractEndNum); i++) {
					String str1 = "**" + (existContractEndNum + 1 + i);
					String str2 = str1.substring(str1.length() - 3, str1.length());
					// 将所有*替换为0
					str2 = str2.replace("*", "0");
					String contractCode = newPDContract.getContractCode();
					String finalStr = contractCode +"-"+str2;
					PDContractDetail pdContractDetail = new PDContractDetail();
					pdContractDetail.setContractId(existPDContract.getContractId());
					pdContractDetail.setContractNumber(finalStr);
					pdContractDetail.setContractStatus("02");//合同号初始化为未用状态
					BeanUtils.insertObjectSetOperateInfo(pdContractDetail, loginInfo);
					pDContractDetailMapper.insert(pdContractDetail);
				}
			}
			//更新所有现存的记录
			PDContractDetailExample pdContractDetailExample = new PDContractDetailExample();
			pdContractDetailExample.createCriteria().andContractIdEqualTo(existPDContract.getContractId()).andRcStateEqualTo("E");
			List<PDContractDetail> pdContractDetails = pDContractDetailMapper.selectByExample(pdContractDetailExample);
			for (PDContractDetail pdContractDetail : pdContractDetails) {
				int length = pdContractDetail.getContractNumber().length();
				String contractNo = pdContractDetail.getContractNumber().substring(length-3, length);
				String newContractNumber = newPDContract.getContractCode() + "-" + contractNo;
				pdContractDetail.setContractNumber(newContractNumber);
				BeanUtils.updateObjectSetOperateInfo(pdContractDetail, loginInfo);
				pDContractDetailMapper.updateByPrimaryKey(pdContractDetail);
			}
			//更新主表信息
			PDContract pdContract = new PDContract();
			pdContract = pDContractMapper.selectByPrimaryKey(existPDContract.getContractId());
			pdContract.setContractCode(newPDContract.getContractCode());
			pdContract.setContractEndNum(newPDContract.getContractEndNum());
			BeanUtils.updateObjectSetOperateInfo(pdContract, loginInfo);
			pDContractMapper.updateByPrimaryKey(pdContract);
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("修改成功！");
		return resultInfo;
	}
	/**
	 * 删除产品合同
	 * @author ZYM
	 */
	@Override
	public ResultInfo deleteContract(String contractId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		if (contractId == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("contractId为空，删除失败！");
			return resultInfo;
		}
		//判断是否有在使用的合同
		PDContractDetailExample pdContractDetailExample = new PDContractDetailExample();
		pdContractDetailExample.createCriteria().andContractIdEqualTo(new Long(contractId)).andContractStatusEqualTo("01").andRcStateEqualTo("E");
		List<PDContractDetail> pdContractDetails = pDContractDetailMapper.selectByExample(pdContractDetailExample);
		if (pdContractDetails.size() > 0) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("存在交易正在使用改产品下的合同号！删除失败！");
			return resultInfo;
		}
		//删除产品合同详情信息表
		PDContractDetailExample pdContractDetailExample1 = new PDContractDetailExample();
		pdContractDetailExample1.createCriteria().andContractIdEqualTo(new Long(contractId)).andRcStateEqualTo("E");
		List<PDContractDetail> PDContractDetailList = pDContractDetailMapper.selectByExample(pdContractDetailExample1);
		for(PDContractDetail pdContractDetail : PDContractDetailList){
			BeanUtils.deleteObjectSetOperateInfo(pdContractDetail, loginInfo);
			pDContractDetailMapper.updateByPrimaryKey(pdContractDetail);
		}
		//删除产品合同批次表
		PDContract pdContract = pDContractMapper.selectByPrimaryKey(new Long(contractId));
		BeanUtils.deleteObjectSetOperateInfo(pdContract, loginInfo);
		pDContractMapper.updateByPrimaryKey(pdContract);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功！");
		return resultInfo;
	}	
	
	/**
	 * 导出产品合同号详情信息
	 * */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map getContractInfoDetail(Map paramMap){
		List<Map<String,String>> businessStatisticsList = productServiceMapper.getContractInfoDetail(paramMap);
		Map<String, Object> datas = new HashMap<String, Object>();
	    datas.put("data", businessStatisticsList);
	    return datas;
	}	
	/**
	 * 股权产品列表查询
	 *  @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid queryStockProductList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = productServiceMapper.queryStockProductListCount(paramMap);
		List<Map> resultList = productServiceMapper.queryStockProductList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 固定产品列表查询
	 *  @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid queryFixedProductList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = productServiceMapper.queryFixedProductListCount(paramMap);
		List<Map> resultList = productServiceMapper.queryFixedProductList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 查询浮动产品信息显示初始信息列表
	 * @author cjj
	 * @param paramMap
	 * @param dgm
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid getFloatProductPageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = productServiceMapper.floatProductListCount(paramMap);
		List<Map> resultList = productServiceMapper.floatProductListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 查询海外产品信息显示初始信息列表
	 * @author cjj
	 * @param paramMap
	 * @param dgm
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid getOverseasProductPageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = productServiceMapper.overseasProductListCount(paramMap);
		List<Map> resultList = productServiceMapper.overseasProductListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	/**
	 * 产品查询(浮动海外)初始化走势图获取产品所有净值信息
	 * CJJ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo getPdSearchNetValue( Map paramMap,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		try {
			
			String productId = (String)paramMap.get("productId");
			PDProduct pdProduct = pDProductMapper.selectByPrimaryKey(Long.parseLong(productId));
			String productName = pdProduct.getProductName();
			Map resultMap = new HashMap();
			resultMap.put("productName", productName);
			List<Map> netValueList = productServiceMapper.queryPdSearchNetValue(paramMap);
			List netList = new ArrayList();
			for (int i = netValueList.size()-1; i >= 0; i--) {
				BigDecimal netValue = (BigDecimal)netValueList.get(i).get("netValue");
				netList.add(netValue);
			}
			resultMap.put("netValueList", netList);
			List<Map> publicDateList = productServiceMapper.queryPdSearchPublicDate(paramMap);
			List dateList = new ArrayList();
			for (int i = publicDateList.size()-1; i >= 0; i--) {
				Date publicDate = (Date)publicDateList.get(i).get("publicDate");
				dateList.add(publicDate);
			}
			resultMap.put("publicDateList", dateList);
			resultInfo.setMsg("查询成功！");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 产品查询菜单查询浮动、海外类产品净值列表
	 * cjj
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid pdSearchNetValue(Map paramMap, DataGridModel dgm) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = productServiceMapper.queryPdNetValueListCount(paramMap);
		List<Map> resultList = productServiceMapper.queryPdNetValueListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	/**
	 * 浮动产品查询初始化走势图获取产品所有净值信息
	 * 
	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Transactional
//	public ResultInfo getInvestSearchNetValue( Map paramMap,LoginInfo loginInfo) {
//		ResultInfo resultInfo = new ResultInfo();
//		if (paramMap == null) {
//			paramMap = new HashMap();
//		}
//		try {
//			
//			String productId = (String)paramMap.get("productId");
//			PDProduct pdProduct = pDProductMapper.selectByPrimaryKey(Long.parseLong(productId));
//			String productName = pdProduct.getProductName();
//			Map resultMap = new HashMap();
//			resultMap.put("productName", productName);
//			List<Map> netValueList = productServiceMapper.queryInvestSearchNetValue(paramMap);
//			List netList = new ArrayList();
//			for (int i = netValueList.size()-1; i >= 0; i--) {
//				BigDecimal netValue = (BigDecimal)netValueList.get(i).get("netValue");
//				netList.add(netValue);
//			}
//			resultMap.put("netValueList", netList);
//			List<Map> publicDateList = productServiceMapper.queryInvestSearchPublicDate(paramMap);
//			List dateList = new ArrayList();
//			for (int i = publicDateList.size()-1; i >= 0; i--) {
//				Date publicDate = (Date)publicDateList.get(i).get("publicDate");
//				dateList.add(publicDate);
//			}
//			resultMap.put("publicDateList", dateList);
//			resultInfo.setMsg("查询成功！");
//			resultInfo.setObj(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultInfo.setSuccess(false);
//			resultInfo.setMsg(e.getMessage());
//		}
//		return resultInfo;
//	}
	
	/**
	 * 产品查询，获取产品详细信息页面
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultInfo searchProductDetail(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		Map productDetail = new HashMap();
		String productSubType = paramMap.get("productSubType").toString();
		if ("01".equals(productSubType)) {
			 productDetail = productServiceMapper.searchProductStockDetail(paramMap);
		}
		if ("02".equals(productSubType)) {
			 productDetail = productServiceMapper.searchProductFixedDetail(paramMap);
		}
		if ("03".equals(productSubType)||"04".equals(productSubType)) {
			 productDetail = productServiceMapper.searchProductDetail(paramMap);
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("查询成功！");
		resultInfo.setObj(productDetail);
		return resultInfo;
	}
	
	////////////////////////////////////股权产品投后清单//////////////////////////////
	/**
	 * 获取股权产品投后清单信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid getAlltStockProductIncomeInfo(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		// 获取符合条件的行数
		Integer total = productServiceMapper.getAllStockProductIncomeInfoCount(paramMap);
		List<Map> resultList = productServiceMapper.getAllStockProductIncomeInfo(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 查询股权产品投后详单
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryDetailStockProIncomeList(DataGridModel dgm, Map paramMap) {
			if (paramMap == null) {
				paramMap = new HashMap();
			}
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			// 获取符合条件的行数
			Integer total = productServiceMapper.queryDetailStockProIncomeCount(paramMap);
			List<Map> resultList = productServiceMapper.queryDetailStockProIncomeList(paramMap);
			DataGrid dataGrid = new DataGrid();
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
			return dataGrid;
	}
	
	/**
	 * 股权产品投后清单信息导出
	 */
	@Override
	public Map exportStockProIncomeInfo(Map paramMap) {
		List<Map<String,String>> stockProIncomeInfo = this.productServiceMapper.exportStockProductIncomeInfo(paramMap);
		Map<String, Object> workDatas = new HashMap<String, Object>();
		workDatas.put("data", stockProIncomeInfo);
	    return workDatas;
	}
	
	/**
	 * 		
	 * 		下载股权产品详情信息
	 * */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map stockProductDetail(Map paramMap){
		// 输出日志，便于调试
		log.info("Service层方法fixedProductDetail()接受参数个数:" + paramMap.size());
		// Mapper接口方法：查详情信息
		List<Map> stockProductDetailList = productServiceMapper.queryStockProductDetailList(paramMap);
		Map<String, Object> datas = new HashMap<String, Object>();
	    datas.put("data", stockProductDetailList);
	    return datas;
	}	
	/**
	 * 产品查询中客户投资记录
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryCustInvestList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		DataGrid dataGrid = new DataGrid();
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		//若为总部运营岗ZBYY 则可以查询所有购买该产品的客户及认购金额
		Long userId = loginInfo.getUserId();
		paramMap.put("userId", userId);
		List<Map> resultMap = productServiceMapper.getUserRoleInfo(paramMap);
		for (Map roleMap : resultMap) {
			//String roleName = roleMap.get("roleName").toString();
			String roleCode = roleMap.get("roleCode").toString();
			if (roleCode.equals("fms") || roleCode.equals("ZBYY")) {
				Integer total = productServiceMapper.queryCustInvestListCount(paramMap);
				List<Map<String, String>> resultList = productServiceMapper.queryCustInvestList(paramMap);
				// datagrid 数据信息
				dataGrid.setRows(resultList);
				dataGrid.setTotal(total);
				return dataGrid;
			}
		}
		//查询agentId 若为rm则查名下客户
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
		List<Agent> agents = agentMapper.selectByExample(agentExample);
		if (!agents.isEmpty()) {
			paramMap.put("agentId", agents.get(0).getAgentId().toString());
			Integer total = productServiceMapper.queryCustInvestListCount(paramMap);
			List<Map<String, String>> resultList = productServiceMapper.queryCustInvestList(paramMap);
			// datagrid 数据信息
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
			return dataGrid;
		}
		return dataGrid;
	}
	/**
	 * 客户认购记录信息导出
	 */
	@Override
	public Map exportDetialCustInvestInfo(Map paramMap) {
		List<Map<String,String>> custInvestInfo = this.productServiceMapper.exportDetialCustInvestInfo(paramMap);
		Map<String, Object> workDatas = new HashMap<String, Object>();
		workDatas.put("data", custInvestInfo);
	    return workDatas;
	}
	
	/**
	 * 产品删除
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public ResultInfo productDelete(String productId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		//删除产品主表
		PDProduct pdProduct = pDProductMapper.selectByPrimaryKey(Long.parseLong(productId));
		BeanUtils.deleteObjectSetOperateInfo(pdProduct, loginInfo);
		pDProductMapper.updateByPrimaryKey(pdProduct);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功！");
		return resultInfo;
	}

	/**
	 * 创股权产品分配短信
	 * @author ZYM
	 */
	@Override
	public ResultInfo createStockDisSms(Map paramMap,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		resultInfo = smsService.createStockDisSms(paramMap, loginInfo);
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 创股权产品分配到账短信
	 * @author ZYM
	 */
	@Override
	public ResultInfo createStockToAccSms(Map paramMap,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		resultInfo = smsService.createStockToAccSms(paramMap, loginInfo);
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 删除预约额度分配
	 * @throws ParseException 
	 */
	public ResultInfo deleteProductAmountDisInfo(Map paramMap, LoginInfo loginInfo) throws ParseException {
		ResultInfo resultInfo = new ResultInfo();
		Long productId = Long.parseLong(paramMap.get("productId").toString());
		Date expectOpenDay = DateUtils.getDate(paramMap.get("expectOpenDay").toString());
		PdAmountDisInfoExample pdAmountDisInfoExample = new PdAmountDisInfoExample();
		pdAmountDisInfoExample.createCriteria().andProductIdEqualTo(productId).
		andExpectOpenDayEqualTo(expectOpenDay).andRcStateEqualTo("E");
		List<PdAmountDisInfo> pdAmountDisInfoList = pdAmountDisInfoMapper.selectByExample(pdAmountDisInfoExample);
		for (PdAmountDisInfo pdAmountDisInfo : pdAmountDisInfoList) {
			BeanUtils.deleteObjectSetOperateInfo(pdAmountDisInfo, loginInfo);
			pdAmountDisInfoMapper.updateByPrimaryKey(pdAmountDisInfo);
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功");
		return resultInfo;
	}
	/**
	 * 产品查询中客户投资记录
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryCustFloatInvestList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		DataGrid dataGrid = new DataGrid();
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		//若为总部运营岗ZBYY 则可以查询所有购买该产品的客户及认购金额
		Long userId = loginInfo.getUserId();
		paramMap.put("userId", userId);
		List<Map> resultMap = productServiceMapper.getUserRoleInfo(paramMap);
		for (Map roleMap : resultMap) {
			//String roleName = roleMap.get("roleName").toString();
			String roleCode = roleMap.get("roleCode").toString();
			if (roleCode.equals("fms") || roleCode.equals("ZBYY")) {
				Integer total = productServiceMapper.queryCustInvestListCount(paramMap);
				List<Map<String, String>> resultList = productServiceMapper.queryCustFloatInvestList(paramMap);
				// datagrid 数据信息
				dataGrid.setRows(resultList);
				dataGrid.setTotal(total);
				return dataGrid;
			}
		}
		//查询agentId 若为rm则查名下客户
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
		List<Agent> agents = agentMapper.selectByExample(agentExample);
		if (!agents.isEmpty()) {
			paramMap.put("agentId", agents.get(0).getAgentId().toString());
			Integer total = productServiceMapper.queryCustInvestListCount(paramMap);
			List<Map<String, String>> resultList = productServiceMapper.queryCustFloatInvestList(paramMap);
			// datagrid 数据信息
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
			return dataGrid;
		}
		return dataGrid;
	}
}