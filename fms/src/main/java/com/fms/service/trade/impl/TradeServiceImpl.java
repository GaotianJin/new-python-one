package com.fms.service.trade.impl;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.CustAccInfoMapper;
import com.fms.db.mapper.CustAddressInfoMapper;
import com.fms.db.mapper.CustAssetInfoMapper;
import com.fms.db.mapper.CustBaseInfoMapper;
import com.fms.db.mapper.CustCarInfoMapper;
import com.fms.db.mapper.CustContactInfoMapper;
import com.fms.db.mapper.CustFamilyInfoMapper;
import com.fms.db.mapper.CustHobbyInfoMapper;
import com.fms.db.mapper.CustHouseInfoMapper;
import com.fms.db.mapper.CustIncomeInfoMapper;
import com.fms.db.mapper.CustInvestInfoMapper;
import com.fms.db.mapper.CustOthInfoMapper;
import com.fms.db.mapper.CustQuestionnaireInfoMapper;
import com.fms.db.mapper.DefPrintInfoMapper;
import com.fms.db.mapper.PDContractDetailMapper;
import com.fms.db.mapper.PDFactorMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PDWealthMapper;
import com.fms.db.mapper.PDWealthOpenDateMapper;
import com.fms.db.mapper.SysEmailAdressMapper;
import com.fms.db.mapper.SysEmailInfoMapper;
import com.fms.db.mapper.TradeCustAccRelaMapper;
import com.fms.db.mapper.TradeCustAddressRelaMapper;
import com.fms.db.mapper.TradeCustAssetRelaMapper;
import com.fms.db.mapper.TradeCustCarRelaMapper;
import com.fms.db.mapper.TradeCustContactRelaMapper;
import com.fms.db.mapper.TradeCustFamilyRelaMapper;
import com.fms.db.mapper.TradeCustHobbyRelaMapper;
import com.fms.db.mapper.TradeCustHouseRelaMapper;
import com.fms.db.mapper.TradeCustIncomeRelaMapper;
import com.fms.db.mapper.TradeCustInfoMapper;
import com.fms.db.mapper.TradeCustInvestRelaMapper;
import com.fms.db.mapper.TradeCustOthRelaMapper;
import com.fms.db.mapper.TradeCustRoleInfoMapper;
import com.fms.db.mapper.TradeInfoMapper;
import com.fms.db.mapper.TradeOperationMapper;
import com.fms.db.mapper.TradeOperationTraceMapper;
import com.fms.db.mapper.TradePdCustRoleRelaMapper;
import com.fms.db.mapper.TradeProductInfoMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.CustAccInfo;
import com.fms.db.model.CustAccInfoExample;
import com.fms.db.model.CustAddressInfo;
import com.fms.db.model.CustAddressInfoExample;
import com.fms.db.model.CustAssetInfo;
import com.fms.db.model.CustAssetInfoExample;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.CustBaseInfoExample;
import com.fms.db.model.CustCarInfo;
import com.fms.db.model.CustCarInfoExample;
import com.fms.db.model.CustContactInfo;
import com.fms.db.model.CustContactInfoExample;
import com.fms.db.model.CustFamilyInfo;
import com.fms.db.model.CustFamilyInfoExample;
import com.fms.db.model.CustHobbyInfo;
import com.fms.db.model.CustHobbyInfoExample;
import com.fms.db.model.CustHouseInfo;
import com.fms.db.model.CustHouseInfoExample;
import com.fms.db.model.CustIncomeInfo;
import com.fms.db.model.CustIncomeInfoExample;
import com.fms.db.model.CustInvestInfo;
import com.fms.db.model.CustInvestInfoExample;
import com.fms.db.model.CustOthInfo;
import com.fms.db.model.CustOthInfoExample;
import com.fms.db.model.CustQuestionnaireInfoExample;
import com.fms.db.model.DefPrintInfo;
import com.fms.db.model.PDContractDetail;
import com.fms.db.model.PDContractDetailExample;
import com.fms.db.model.PDFactor;
import com.fms.db.model.PDFactorExample;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDProductExample;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthExample;
import com.fms.db.model.PDWealthOpenDate;
import com.fms.db.model.PDWealthOpenDateExample;
import com.fms.db.model.SysEmailAdress;
import com.fms.db.model.SysEmailAdressExample;
import com.fms.db.model.SysEmailInfo;
import com.fms.db.model.TradeCustAccRela;
import com.fms.db.model.TradeCustAccRelaExample;
import com.fms.db.model.TradeCustAddressRela;
import com.fms.db.model.TradeCustAddressRelaExample;
import com.fms.db.model.TradeCustAssetRela;
import com.fms.db.model.TradeCustAssetRelaExample;
import com.fms.db.model.TradeCustCarRela;
import com.fms.db.model.TradeCustCarRelaExample;
import com.fms.db.model.TradeCustContactRela;
import com.fms.db.model.TradeCustContactRelaExample;
import com.fms.db.model.TradeCustFamilyRela;
import com.fms.db.model.TradeCustFamilyRelaExample;
import com.fms.db.model.TradeCustHobbyRela;
import com.fms.db.model.TradeCustHobbyRelaExample;
import com.fms.db.model.TradeCustHouseRela;
import com.fms.db.model.TradeCustHouseRelaExample;
import com.fms.db.model.TradeCustIncomeRela;
import com.fms.db.model.TradeCustIncomeRelaExample;
import com.fms.db.model.TradeCustInfo;
import com.fms.db.model.TradeCustInfoExample;
import com.fms.db.model.TradeCustInvestRela;
import com.fms.db.model.TradeCustInvestRelaExample;
import com.fms.db.model.TradeCustOthRela;
import com.fms.db.model.TradeCustOthRelaExample;
import com.fms.db.model.TradeCustRoleInfo;
import com.fms.db.model.TradeCustRoleInfoExample;
import com.fms.db.model.TradeInfo;
import com.fms.db.model.TradeInfoExample;
import com.fms.db.model.TradeOperation;
import com.fms.db.model.TradeOperationExample;
import com.fms.db.model.TradeOperationTrace;
import com.fms.db.model.TradePdCustRoleRela;
import com.fms.db.model.TradePdCustRoleRelaExample;
import com.fms.db.model.TradeProductInfo;
import com.fms.db.model.TradeProductInfoExample;
import com.fms.db.model.TradeStatusInfo;
import com.fms.service.customer.CustomerService;
import com.fms.service.mapper.CustomerServiceMapper;
import com.fms.service.mapper.ModifyCustomerServiceMapper;
import com.fms.service.mapper.TradeServiceMapper;
import com.fms.service.trade.TradeService;
import com.fms.service.trade.TradeStatusService;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.db.model.DefCodeKey;
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
import com.sinosoft.util.PdfTool;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.SimpleMoneyFormat;
import com.sinosoft.util.StringUtils;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeServiceMapper tradeServiceMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private DefUserMapper defUserMapper;
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	@Autowired
	private TradeCustInfoMapper tradeCustInfoMapper;
	@Autowired
	private TradeCustRoleInfoMapper tradeCustRoleInfoMapper;
	@Autowired
	private PDProductMapper pdProductMapper;
	@Autowired
	private PDWealthMapper pdWealthMapper;
	@Autowired
	private PDFactorMapper pdFactorMapper;
	@Autowired
	private TradeProductInfoMapper tradeProductInfoMapper;
	@Autowired
	private TradePdCustRoleRelaMapper tradePdCustRoleRelaMapper;
	@Autowired
	private TradeOperationMapper tradeOperationMapper;
	@Autowired
	private TradeOperationTraceMapper tradeOperationTraceMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private TradeCustAccRelaMapper tradeCustAccRelaMapper;
	@Autowired
	private TradeCustAddressRelaMapper tradeCustAddressRelaMapper;
	@Autowired
	private TradeCustAssetRelaMapper tradeCustAssetRelaMapper;
	@Autowired
	private TradeCustCarRelaMapper tradeCustCarRelaMapper;
	@Autowired
	private TradeCustContactRelaMapper tradeCustContactRelaMapper;
	@Autowired
	private TradeCustFamilyRelaMapper tradeCustFamilyRelaMapper;
	@Autowired
	private TradeCustHobbyRelaMapper tradeCustHobbyRelaMapper;
	@Autowired
	private TradeCustHouseRelaMapper tradeCustHouseRelaMapper;
	@Autowired
	private TradeCustInvestRelaMapper tradeCustInvestRelaMapper;
	@Autowired
	private TradeCustIncomeRelaMapper tradeCustIncomeRelaMapper;
	@Autowired
	private TradeCustOthRelaMapper tradeCustOthRelaMapper;
	@Autowired
	private CustAccInfoMapper custAccInfoMapper;
	@Autowired
	private CustAddressInfoMapper custAddressInfoMapper;
	@Autowired
	private CustAssetInfoMapper custAssetInfoMapper;
	@Autowired
	private CustCarInfoMapper custCarInfoMapper;
	@Autowired
	private CustContactInfoMapper custContactInfoMapper;
	@Autowired
	private CustFamilyInfoMapper custFamilyInfoMapper;
	@Autowired
	private CustHobbyInfoMapper custHobbyInfoMapper;
	@Autowired
	private CustHouseInfoMapper custHouseInfoMapper;
	@Autowired
	private CustInvestInfoMapper custInvestInfoMapper;
	@Autowired
	private CustIncomeInfoMapper custIncomeInfoMapper;
	@Autowired
	private CustOthInfoMapper custOthInfoMapper;
	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	@Autowired
	private DefPrintInfoMapper defPrintInfoMapper;
	@Autowired
	private CustBaseInfoMapper custBaseInfoMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private TradeStatusService tradeStatusService;
	@Autowired
	private CustQuestionnaireInfoMapper custQuestionnaireInfoMapper;	
	@Autowired
	private SysEmailInfoMapper sysEmailInfoMapper;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private SysEmailAdressMapper sysEmailAdressMapper;
	@Autowired
	private ModifyCustomerServiceMapper modifyCustomerServiceMapper;
	@Resource
	private PDWealthOpenDateMapper pdWealthOpenDateMapper;
	@Autowired
    private PDContractDetailMapper pdContractDetailMapper;
	
	private static final Logger log = Logger.getLogger(TradeServiceImpl.class);

	@Override
	public DataGrid tradeInputQueryTradeList(DataGridModel dgm, Map paramMap, String tradeStaus, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		if (!"01".equals(tradeStaus)) {
			paramMap.put("tradeStaus", tradeStaus);
		}
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		Integer total = tradeServiceMapper.tradeInputQueryTradeListCount(paramMap);
		List<Map> resultList = tradeServiceMapper.tradeInputQueryTradeListPage(paramMap);
		for (Map tradeMap : resultList) {
			if (tradeMap.containsKey("tradeTotalAssets") && tradeMap.get("tradeTotalAssets") != null) {
				BigDecimal tradeTotalAssets = (BigDecimal) tradeMap.get("tradeTotalAssets");
				tradeMap.put("chnTradeTotalAssets",
						SimpleMoneyFormat.formatChineseCapital(tradeTotalAssets.doubleValue()));
			}
		}
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@Override
	public ResultInfo saveTradeInfo(TradeInfo tradeInfo, LoginInfo loginInfo) {
			ResultInfo resultInfo = new ResultInfo();
			if (tradeInfo == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易基本信息为空，不能保存");
				return resultInfo;
			}
			if (tradeInfo.getAgentId() == null || tradeInfo.getAgentId().equals("")) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取财富顾问信息,不能保存");
				return resultInfo;
			}
			//获取页面合同号
			String tradeInfoNo = tradeInfo.getTradeInfoNo();
			//保存或更新操作
			if (tradeInfo.getTradeInfoId() != null) {
				TradeInfoExample tradeInfoExample = new TradeInfoExample();
				tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId())
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeInfo> tradeInfoList = tradeInfoMapper.selectByExample(tradeInfoExample);
				if (tradeInfoList != null && tradeInfoList.size() > 0) {
					TradeInfo existTradeInfo = tradeInfoList.get(0);
					String existTradeInfoNo = existTradeInfo.getTradeInfoNo();
					tradeInfo.setTradeInfoId(tradeInfo.getTradeInfoId());
					if (null != existTradeInfoNo &&  null != tradeInfoNo && !existTradeInfoNo.equals(tradeInfoNo)) {
						//判断更新的合同号是否重复（强制输入）
						PDContractDetailExample contractDetailExampleCheck = new PDContractDetailExample();
						contractDetailExampleCheck.createCriteria().andContractNumberEqualTo(tradeInfoNo).andContractStatusEqualTo("01").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						List<PDContractDetail> contractCheckList = pdContractDetailMapper.selectByExample(contractDetailExampleCheck);
						if (contractCheckList != null && contractCheckList.size() > 0) {
						  //合同号使用过：进行提示重新选择
							resultInfo.setSuccess(false);
							resultInfo.setMsg("合同号重复，请重新选择!");
							return resultInfo;
						}
					}
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existTradeInfo, tradeInfo, loginInfo);
					tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
					//判断合同号是否更改：更改则释放原来的合同号
					if (null != existTradeInfoNo && !"".equals(existTradeInfoNo)) {
						//原合同号有值
						if (null != tradeInfoNo && !"".equals(tradeInfoNo)) {
							//当前有选值
							if (!existTradeInfoNo.equals(tradeInfoNo)) {
								//现选择的合同号和原不同，释放原合同号并更改信息
								PDContractDetailExample existContractDetailExampl = new PDContractDetailExample();
								PDContractDetailExample.Criteria contractDetailCriteria = existContractDetailExampl.createCriteria();
								contractDetailCriteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andContractNumberEqualTo(existTradeInfoNo);
								List<PDContractDetail> existContractDetailList = pdContractDetailMapper.selectByExample(existContractDetailExampl);
								//原合同号是PDContractDetail表里的，则作修改
								if (existContractDetailList != null && existContractDetailList.size() > 0) {
									PDContractDetail contractDetailInfo = existContractDetailList.get(0);
									contractDetailInfo.setContractStatus("02");//未使用
									contractDetailInfo.setTradeInfoId(null);
									BeanUtils.insertObjectSetOperateInfo(contractDetailInfo, loginInfo);
									pdContractDetailMapper.updateByPrimaryKey(contractDetailInfo);
								}
								//修改当前选择的合同号信息
								PDContractDetailExample contractDetailExampl = new PDContractDetailExample();
								PDContractDetailExample.Criteria contractDetailCriteriaTemp = contractDetailExampl.createCriteria();
								contractDetailCriteriaTemp.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andContractNumberEqualTo(tradeInfoNo);
								List<PDContractDetail> contractDetailList = pdContractDetailMapper.selectByExample(contractDetailExampl);
								if (contractDetailList != null && contractDetailList.size() > 0) {
									PDContractDetail contractInfoTemp = contractDetailList.get(0);
									contractInfoTemp.setContractStatus("01");//已使用
									contractInfoTemp.setTradeInfoId(tradeInfo.getTradeInfoId());
									BeanUtils.insertObjectSetOperateInfo(contractInfoTemp, loginInfo);
									pdContractDetailMapper.updateByPrimaryKey(contractInfoTemp);
								}
							}
						}
					}else {
						//原合同号为空,现有值
						if (null != tradeInfoNo && !"".equals(tradeInfoNo)) {
								//修改本次选择的合同号为已使用
								PDContractDetailExample contractDetailExampl = new PDContractDetailExample();
								PDContractDetailExample.Criteria contractDetailCriteriaTemp = contractDetailExampl.createCriteria();
								contractDetailCriteriaTemp.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andContractNumberEqualTo(tradeInfoNo);
								List<PDContractDetail> contractDetailList = pdContractDetailMapper.selectByExample(contractDetailExampl);
								if (contractDetailList != null && contractDetailList.size() > 0) {
									PDContractDetail contractInfoTemp = contractDetailList.get(0);
									contractInfoTemp.setContractStatus("01");//已使用
									contractInfoTemp.setTradeInfoId(tradeInfo.getTradeInfoId());
									BeanUtils.insertObjectSetOperateInfo(contractInfoTemp, loginInfo);
									pdContractDetailMapper.updateByPrimaryKey(contractInfoTemp);
								}
						}
					}
				}
			} else {
				//填写则判断合同号是否重复
				if (null != tradeInfoNo && !"".equals(tradeInfoNo)) {
					//保存或更新之前判断，合同号是否已经使用过
					PDContractDetailExample contractDetailExampleCheck = new PDContractDetailExample();
					contractDetailExampleCheck.createCriteria().andContractNumberEqualTo(tradeInfoNo).andContractStatusEqualTo("01").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<PDContractDetail> contractCheckList = pdContractDetailMapper.selectByExample(contractDetailExampleCheck);
					if (contractCheckList != null && contractCheckList.size() > 0) {
					  //合同号使用过：进行提示重新选择
						resultInfo.setSuccess(false);
						resultInfo.setMsg("合同号重复，请重新选择!");
						return resultInfo;
					}
				}
				Long tradeInfoId = commonService.getSeqValByName("SEQ_T_TRADE_INFO");
				tradeInfo.setTradeInfoId(tradeInfoId);
				tradeInfo.setTradeDate(new Date());//交易日期
				tradeInfo.setTradeStaus(Constants.TRADE_STATUS_INPUT);
				tradeInfo.setInputOperator(loginInfo.getUserCode());
				BeanUtils.insertObjectSetOperateInfo(tradeInfo, loginInfo);
				tradeInfoMapper.insertSelective(tradeInfo);
				//插入数据后，修改选择合同号为已使用
				if (null != tradeInfoNo && !"".equals(tradeInfoNo)) {
					//修改本次选择的合同号为已使用
					PDContractDetailExample contractDetailExampl = new PDContractDetailExample();
					PDContractDetailExample.Criteria contractDetailCriteriaTemp = contractDetailExampl.createCriteria();
					contractDetailCriteriaTemp.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andContractNumberEqualTo(tradeInfoNo);
					List<PDContractDetail> contractDetailList = pdContractDetailMapper.selectByExample(contractDetailExampl);
					if (contractDetailList != null && contractDetailList.size() > 0) {
						PDContractDetail contractInfoTemp = contractDetailList.get(0);
						contractInfoTemp.setContractStatus("01");//已使用
						contractInfoTemp.setTradeInfoId(tradeInfo.getTradeInfoId());
						BeanUtils.insertObjectSetOperateInfo(contractInfoTemp, loginInfo);
						pdContractDetailMapper.updateByPrimaryKey(contractInfoTemp);
					}
				}
			}	
			resultInfo.setSuccess(true);
			resultInfo.setMsg("交易信息保存成功！");
			resultInfo.setObj(tradeInfo);
			
			return resultInfo ;
	}

	@Override
	public List<Map<String, String>> queryCustomerCombo(HashMap paramMap) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		resultList = tradeServiceMapper.queryCustomerCombo(paramMap);
		return resultList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public ResultInfo saveTradeRole(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();

		String tradeCustRoleListStr = JsonUtils.getJsonValueByKey("tradeCustRoleList", param);
		List<TradeCustRoleInfo> tradeCustRoleList = JsonUtils.jsonArrStrToList(tradeCustRoleListStr,
				TradeCustRoleInfo.class);
		// 从页面获取交易号
		String tradeNo = (String) JsonUtils.getJsonValueByKey("tradeNo", param);
		String tradeId = (String) JsonUtils.getJsonValueByKey("tradeInfoId", param);

		// 校验该交易流水号对应是否存在记录
		if (tradeId != null && !tradeId.trim().isEmpty()) {
			Long tradeIdDecimal = new Long(tradeId);
			if (!checkTradeId(tradeIdDecimal)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请先保存交易信息！");
				return resultInfo;
			}
			log.info("存在交易流水号对应的记录，可以保存角色信息！");
		} else {
			log.info("不存在交易流水号对应的记录，不可以保存角色信息！");
			resultInfo.setSuccess(false);
			resultInfo.setMsg("请先保存交易信息！");
			return resultInfo;
		}
		// 获取已经存在的所有客户的角色信息
		Map paramMap = new HashMap();
		paramMap.put("tradeInfoId", tradeId);
		List<Map> tradeCustRoleMapList = tradeServiceMapper.getAllCustRoleInfoListByTradeInfoId(paramMap);
		List<TradeCustRoleInfo> existTradeCustRoleList = new ArrayList<TradeCustRoleInfo>();
		if (tradeCustRoleMapList != null && tradeCustRoleMapList.size() > 0) {
			existTradeCustRoleList = (List<TradeCustRoleInfo>) JsonUtils.listMapToListObj(tradeCustRoleMapList,
					TradeCustRoleInfo.class);
		}
		for (TradeCustRoleInfo tradeCustRoleInfo : tradeCustRoleList) {
			Long tradeCustRoleInfoId = tradeCustRoleInfo.getTradeCustRoleInfoId();
			if (tradeCustRoleInfoId != null) {
				for (TradeCustRoleInfo existTradeCustRoleInfo : existTradeCustRoleList) {
					Long existTradeCustRoleInfoId = existTradeCustRoleInfo.getTradeCustRoleInfoId();
					if (tradeCustRoleInfoId.compareTo(existTradeCustRoleInfoId) == 0) {
						BeanUtils.deleteAndInsertObjectSetOperateInfo(existTradeCustRoleInfo, tradeCustRoleInfo,
								loginInfo);
						tradeCustRoleInfoMapper.updateByPrimaryKey(tradeCustRoleInfo);
					}
					// 如果角色类型改变，或是所选客户改变，则删除该角色下的所有产品信息，删除本次交易客户默认的账户和地址信息
					if ((tradeCustRoleInfo.getTradeCustInfoId()
							.compareTo(existTradeCustRoleInfo.getTradeCustInfoId()) != 0)
							|| !tradeCustRoleInfo.getRoleType().equals(existTradeCustRoleInfo.getRoleType())) {
						Map<String, Object> delRiskParamMap = new HashMap<String, Object>();
						TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(new Long(tradeId));
						// 删除默认账户信息
						// tradeInfo.setCustAccInfoId(null);
						// tradeInfo.setCustAddressInfoId(null);
						BeanUtils.updateObjectSetOperateInfo(tradeCustRoleInfo, loginInfo);
						tradeInfoMapper.updateByPrimaryKey(tradeInfo);
						// 删除该角色对应的所有产品信息
						TradePdCustRoleRelaExample tradePdCustRoleRelaExample = new TradePdCustRoleRelaExample();
						TradePdCustRoleRelaExample.Criteria criteria = tradePdCustRoleRelaExample.createCriteria();
						criteria.andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId())
								.andTradeCustRoleInfoIdEqualTo(tradeCustRoleInfo.getTradeCustRoleInfoId())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						List<TradePdCustRoleRela> tradePdCustRoleRelaList = tradePdCustRoleRelaMapper
								.selectByExample(tradePdCustRoleRelaExample);
						for (TradePdCustRoleRela tradePdCustRoleRela : tradePdCustRoleRelaList) {
							BigDecimal tradeTotalAssets = tradeInfo.getTradeTotalAssets();
							if (tradeTotalAssets == null) {
								tradeTotalAssets = new BigDecimal(0);
							}
							delRiskParamMap.put("tradeInfo", tradeInfo);
							delRiskParamMap.put("productId", tradePdCustRoleRela.getProductId().toString());
							delRiskParamMap.put("riskTotalAssets", tradeTotalAssets.toString());
							resultInfo = delRiskProInfo(delRiskParamMap, loginInfo);
							// delRiskProInfo(paramMap, loginInfo);
						}

					}
				}
			} else {
				tradeCustRoleInfoId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_ROLE_INFO");
				tradeCustRoleInfo.setTradeCustRoleInfoId(tradeCustRoleInfoId);
				BeanUtils.insertObjectSetOperateInfo(tradeCustRoleInfo, loginInfo);
				tradeCustRoleInfoMapper.insert(tradeCustRoleInfo);
			}
		}
		/*
		 * else { for (TradeCustRoleInfo tradeCustRoleInfo:tradeCustRoleList) {
		 * BigDecimal tradeCustRoleInfoId =
		 * commonService.getSeqValByName("SEQ_T_TRADE_CUST_ROLE_INFO");
		 * tradeCustRoleInfo.setTradeCustRoleInfoId(tradeCustRoleInfoId);
		 * BeanUtils.insertObjectSetOperateInfo(tradeCustRoleInfo, loginInfo);
		 * tradeCustRoleInfoMapper.insert(tradeCustRoleInfo); } }
		 */

		resultInfo.setSuccess(true);
		resultInfo.setMsg("角色信息保存成功！");
		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo saveTradeRiskPro(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String riskFactorData = (String) JsonUtils.getJsonValueByKey("riskFactorData", param);
		String tradeRiskProductId = (String) JsonUtils.getJsonValueByKey("tradeRiskProId", param);
		String tradeRiskProtObj = (String) JsonUtils.getJsonValueByKey("tradeRiskProtObj", param);
		String tradeInfoId = (String) JsonUtils.getJsonValueByKey("tradeInfoId", param);

		Long tradeIdDecimal = new Long(tradeInfoId);
		Long tradeRiskProductIdDecimal = new Long(tradeRiskProductId);
		Long tradeRiskProtObjDecimal = new Long(tradeRiskProtObj);
		// 校验该交易流水号对应是否存在记录

		if (tradeInfoId != null && !tradeInfoId.trim().isEmpty()) {
			log.info("存在交易流水号对应的记录，可以保存保险产品信息！");
			tradeIdDecimal = new Long(tradeInfoId);
			if (!checkTradeId(tradeIdDecimal)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请先保存交易信息！");
				return resultInfo;
			}
		} else {
			log.info("不存在交易流水号对应的记录，不可以保存保险单产品信息！");
			resultInfo.setSuccess(false);
			resultInfo.setMsg("请先保存交易信息！");
			return resultInfo;
		}
		BigDecimal totalAssets = new BigDecimal(0);
		BigDecimal proAssets = new BigDecimal(0);
		List<Map> riskFactorList = new ArrayList<Map>();
		riskFactorList = JSON.parseArray(riskFactorData, Map.class);
		// 保存保险产品要素信息
		if (riskFactorList != null && riskFactorList.size() > 0) {

			TradeProductInfoExample countTradeProInfoExample = new TradeProductInfoExample();
			countTradeProInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeIdDecimal)
					.andProductIdEqualTo(tradeRiskProductIdDecimal).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<TradeProductInfo> tradeProductInfoList = tradeProductInfoMapper
					.selectByExample(countTradeProInfoExample);
			if (tradeProductInfoList != null && tradeProductInfoList.size() > 0) {
				// proAssets
				Map paramMap = new HashMap();
				paramMap.put("tradeId", tradeInfoId);
				paramMap.put("tradeRiskProId", tradeRiskProductIdDecimal);
				proAssets = tradeServiceMapper.queryRiskProAssets(paramMap);
				for (Map map : riskFactorList) {
					String factorCode = (String) map.get("code");
					String factorValue = (String) map.get("inputValue");
					if (factorCode != null && "premium".equals(factorCode)) {
						BigDecimal inputValueDecimal = new BigDecimal(factorValue);
						totalAssets = inputValueDecimal.subtract(proAssets);
					}
					for (TradeProductInfo existTradeProductInfo : tradeProductInfoList) {
						String existFactorCode = existTradeProductInfo.getParamCode();
						if (factorCode.equals(existFactorCode)) {
							TradeProductInfo tradeProductInfo = new TradeProductInfo();
							tradeProductInfo.setParamCode(factorCode);
							tradeProductInfo.setParamValue(factorValue);
							tradeProductInfo.setTradeProductInfoId(existTradeProductInfo.getTradeProductInfoId());
							tradeProductInfo.setTradeInfoId(tradeIdDecimal);
							tradeProductInfo.setProductId(tradeRiskProductIdDecimal);
							BeanUtils.deleteAndInsertObjectSetOperateInfo(existTradeProductInfo, tradeProductInfo,
									loginInfo);
							tradeProductInfoMapper.updateByPrimaryKey(tradeProductInfo);

						}
					}
				}
				// 产品客户关联关系,之前保存了关联关系，并且客户角色未改变
				TradePdCustRoleRelaExample tradePdCustRoleRelaExample = new TradePdCustRoleRelaExample();
				TradePdCustRoleRelaExample.Criteria criteria = tradePdCustRoleRelaExample.createCriteria();
				criteria.andTradeInfoIdEqualTo(tradeIdDecimal).andProductIdEqualTo(tradeRiskProductIdDecimal)
						.andTradeCustRoleInfoIdEqualTo(tradeRiskProtObjDecimal)
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradePdCustRoleRela> tradePdCustRoleRelaList = tradePdCustRoleRelaMapper
						.selectByExample(tradePdCustRoleRelaExample);
				if (tradePdCustRoleRelaList != null && tradePdCustRoleRelaList.size() > 0) {

				} else {
					TradePdCustRoleRelaExample tradePdCustRoleRelaExample1 = new TradePdCustRoleRelaExample();
					TradePdCustRoleRelaExample.Criteria criteria1 = tradePdCustRoleRelaExample1.createCriteria();
					criteria1.andTradeInfoIdEqualTo(tradeIdDecimal).andProductIdEqualTo(tradeRiskProductIdDecimal)
							.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<TradePdCustRoleRela> deleteCustRoleRelaExampleList = tradePdCustRoleRelaMapper
							.selectByExample(tradePdCustRoleRelaExample1);
					// 全部逻辑删除
					for (TradePdCustRoleRela existTradePdCustRoleRela : deleteCustRoleRelaExampleList) {
						BeanUtils.deleteObjectSetOperateInfo(existTradePdCustRoleRela, loginInfo);
						existTradePdCustRoleRela.setTradeCustRoleInfoId(tradeRiskProtObjDecimal);
						tradePdCustRoleRelaMapper.updateByPrimaryKey(existTradePdCustRoleRela);
					}
					// 保存关联关系
					TradePdCustRoleRela tradeProductCustRela = new TradePdCustRoleRela();
					tradeProductCustRela
							.setTradePdCustRoleRelaId(commonService.getSeqValByName("SEQ_T_TRADE_PD_CUST_ROLE_RELA"));
					tradeProductCustRela.setTradeCustRoleInfoId(tradeRiskProtObjDecimal);
					tradeProductCustRela.setTradeInfoId(tradeIdDecimal);
					tradeProductCustRela.setProductId(tradeRiskProductIdDecimal);
					BeanUtils.insertObjectSetOperateInfo(tradeProductCustRela, loginInfo);
					tradePdCustRoleRelaMapper.insert(tradeProductCustRela);
				}
			} else {
				for (Map map : riskFactorList) {
					Long tradeProInfoId = commonService.getSeqValByName("SEQ_T_TRADE_PRODUCT_INFO");
					TradeProductInfo tradeProductInfo = new TradeProductInfo();
					String factorCode = (String) map.get("code");
					String factorValue = (String) map.get("inputValue");
					if (factorCode != null && "premium".equals(factorCode)) {
						BigDecimal inputValueDecimal = new BigDecimal(factorValue);
						totalAssets = inputValueDecimal.subtract(proAssets);
					}

					tradeProductInfo.setParamCode(factorCode);
					tradeProductInfo.setParamValue(factorValue);
					tradeProductInfo.setTradeProductInfoId(tradeProInfoId);
					tradeProductInfo.setTradeInfoId(tradeIdDecimal);
					tradeProductInfo.setProductId(tradeRiskProductIdDecimal);
					tradeProductInfo.setCreateDate(DateUtils.getCurrentTimestamp());
					BeanUtils.insertObjectSetOperateInfo(tradeProductInfo, loginInfo);
					tradeProductInfoMapper.insert(tradeProductInfo);

					TradePdCustRoleRela tradeProductCustRela = new TradePdCustRoleRela();
					tradeProductCustRela
							.setTradePdCustRoleRelaId(commonService.getSeqValByName("SEQ_T_TRADE_PD_CUST_ROLE_RELA"));
					tradeProductCustRela.setTradeCustRoleInfoId(tradeRiskProtObjDecimal);
					tradeProductCustRela.setTradeInfoId(tradeIdDecimal);
					tradeProductCustRela.setProductId(tradeRiskProductIdDecimal);
					BeanUtils.insertObjectSetOperateInfo(tradeProductCustRela, loginInfo);
					tradePdCustRoleRelaMapper.insert(tradeProductCustRela);
				}
			}

		}

		TradeInfo queryTradeInfo = tradeInfoMapper.selectByPrimaryKey(new Long(tradeInfoId));
		if (queryTradeInfo != null) {
			if (queryTradeInfo.getTradeTotalAssets() != null
					&& queryTradeInfo.getTradeTotalAssets().doubleValue() >= 0) {
				BigDecimal queryTotalAssets = queryTradeInfo.getTradeTotalAssets();
				totalAssets = totalAssets.add(queryTotalAssets);
			}
		}
		TradeInfo updateTradeInfo = new TradeInfo();
		updateTradeInfo.setTradeInfoId(tradeIdDecimal);
		updateTradeInfo.setTradeTotalAssets(totalAssets);
		tradeInfoMapper.updateByPrimaryKeySelective(updateTradeInfo);
		Map resultMap = new HashMap();
		resultMap.put("totalAssets", totalAssets);
		if (totalAssets != null) {
			resultMap.put("chnTotalAssets", SimpleMoneyFormat.formatChineseCapital(totalAssets.doubleValue()));
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("保险产品信息保存成功！");
		resultInfo.setObj(resultMap);
		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo saveTradeWealthPro(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();

		String wealthFactorData = (String) JsonUtils.getJsonValueByKey("wealthFactorData", param);
		String tradeWealthProId = (String) JsonUtils.getJsonValueByKey("tradeWealthProId", param);
		// String tradeWealthProName =
		// (String)JsonUtils.getJsonValueByKey("tradeWealthProName", param);
		// String userCode = (String)JsonUtils.getJsonValueByKey("userCode",
		// param);
		// BigDecimal userIdDecimal = new
		// BigDecimal(JsonUtils.getJsonValueByKey("userId", param));
		// BigDecimal comIdDecimal = new
		// BigDecimal(JsonUtils.getJsonValueByKey("comId", param));
		String tradeId = (String) JsonUtils.getJsonValueByKey("tradeId", param);
		//
		// String wealthFactorData = (String)paramMap.get("wealthFactorData");
		// String tradeWealthProId = (String)paramMap.get("tradeWealthProId");
		// String tradeWealthProName =
		// (String)paramMap.get("tradeWealthProName");
		// String userCode = (String)paramMap.get("userCode");
		// BigDecimal userIdDecimal = (BigDecimal)paramMap.get("userId");
		// BigDecimal comIdDecimal = (BigDecimal)paramMap.get("userId");
		// String tradeId = (String)paramMap.get("tradeId");
		Long tradeIdDecimal = null;
		Long tradeWealthProIdDecimal = new Long(tradeWealthProId);
		// 校验该交易流水号对应是否存在记录
		if (tradeId != null && !tradeId.trim().isEmpty()) {
			tradeIdDecimal = new Long(tradeId);
			log.info("存在交易流水号对应的记录，可以保存财富产品信息！");
			if (!checkTradeId(tradeIdDecimal)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请先保存交易信息！");
				return resultInfo;
			}
		} else {
			log.info("不存在交易流水号对应的记录，不可以保存财富产品信息！");
			resultInfo.setSuccess(false);
			resultInfo.setMsg("请先保存交易信息！");
			return resultInfo;
		}
		BigDecimal totalAssets = new BigDecimal(0);
		BigDecimal proAssets = new BigDecimal(0);
		List<Map> wealthFactorList = new ArrayList<Map>();
		wealthFactorList = JSON.parseArray(wealthFactorData, Map.class);
		/*
		 * //保存财富 产品信息 pdWealthMapper.insertSelective(record);
		 */
		// 保存财富产品要素信息
		if (wealthFactorList != null && wealthFactorList.size() > 0) {
			TradeProductInfoExample countTradeProInfoExample = new TradeProductInfoExample();
			countTradeProInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeIdDecimal)
					.andProductIdEqualTo(tradeWealthProIdDecimal).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<TradeProductInfo> tradeProductInfoList = tradeProductInfoMapper
					.selectByExample(countTradeProInfoExample);
			if (tradeProductInfoList != null && tradeProductInfoList.size() > 0) {
				for (Map map : wealthFactorList) {
					String factorCode = (String) map.get("code");
					String factorValue = (String) map.get("inputValue");
					if (factorCode != null && "subscriptionFee".equals(factorCode)) {
						BigDecimal inputValueDecimal = new BigDecimal(factorValue);
						totalAssets = inputValueDecimal.subtract(proAssets);
					}
					for (TradeProductInfo existTradeProductInfo : tradeProductInfoList) {
						String existFactorCode = existTradeProductInfo.getParamCode();
						if (factorCode.equals(existFactorCode)) {
							TradeProductInfo tradeProductInfo = new TradeProductInfo();
							tradeProductInfo.setParamCode(factorCode);
							tradeProductInfo.setParamValue(factorValue);
							tradeProductInfo.setTradeProductInfoId(existTradeProductInfo.getTradeProductInfoId());
							tradeProductInfo.setTradeInfoId(tradeIdDecimal);
							tradeProductInfo.setProductId(tradeWealthProIdDecimal);
							BeanUtils.deleteAndInsertObjectSetOperateInfo(existTradeProductInfo, tradeProductInfo,
									loginInfo);
							tradeProductInfoMapper.updateByPrimaryKey(tradeProductInfo);

						}
					}
				}
			} else {
				for (int i = 0; i < wealthFactorList.size(); i++) {
					TradeProductInfo tradeProductInfo = new TradeProductInfo();
					String factorCode = (String) wealthFactorList.get(i).get("code");
					String factorValue = (String) wealthFactorList.get(i).get("inputValue");
					if (factorCode != null && "subscriptionFee".equals(factorCode)) {
						BigDecimal inputValueDecimal = new BigDecimal(factorValue);
						totalAssets = inputValueDecimal.subtract(proAssets);
					}
					tradeProductInfo.setParamCode(factorCode);
					tradeProductInfo.setParamValue(factorValue);
					tradeProductInfo.setTradeProductInfoId(commonService.getSeqValByName("SEQ_T_TRADE_PRODUCT_INFO"));
					tradeProductInfo.setTradeInfoId(tradeIdDecimal);
					tradeProductInfo.setProductId(tradeWealthProIdDecimal);
					BeanUtils.insertObjectSetOperateInfo(tradeProductInfo, loginInfo);
					tradeProductInfoMapper.insertSelective(tradeProductInfo);
				}
			}
		}
		TradeInfo updateTradeInfo = new TradeInfo();
		updateTradeInfo.setTradeInfoId(tradeIdDecimal);
		updateTradeInfo.setTradeTotalAssets(totalAssets);
		tradeInfoMapper.updateByPrimaryKeySelective(updateTradeInfo);
		Map resultMap = new HashMap();
		resultMap.put("totalAssets", totalAssets);
		if (totalAssets != null) {
			resultMap.put("chnTotalAssets", SimpleMoneyFormat.formatChineseCapital(totalAssets.doubleValue()));
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("财富产品信息保存成功！");
		resultInfo.setObj(resultMap);

		return resultInfo;
	}

	/**
	 * 基于接口的财富产品交易信息录入
	 * 
	 * @param tradeProductInfoList
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private void saveWealthTradeInfo(List<TradeProductInfo> tradeProductInfoList, LoginInfo loginInfo, String orderNo) {
		Long productId = new Long(0);
		BigDecimal totalAssets = new BigDecimal(0);
		BigDecimal proAssets = new BigDecimal(0);
		// 1,保存财富产品交易信息
		TradeInfoExample tradeInfoExample = new TradeInfoExample();
		TradeInfoExample.Criteria tradeInfoCriteria = tradeInfoExample.createCriteria();
		tradeInfoCriteria.andOrderNoEqualTo(orderNo).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeInfo> tradeInfoList = tradeInfoMapper.selectByExample(tradeInfoExample);
		Long tradeInfoId = tradeInfoList.get(0).getTradeInfoId();
		// 保存财富产品要素信息
		if (tradeProductInfoList != null && tradeProductInfoList.size() > 0) {
			productId = tradeProductInfoList.get(0).getProductId();
			TradeProductInfoExample countTradeProInfoExample = new TradeProductInfoExample();
			countTradeProInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeInfoId).andProductIdEqualTo(productId)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<TradeProductInfo> tradePdInfoList = tradeProductInfoMapper.selectByExample(countTradeProInfoExample);
			if (tradePdInfoList != null && tradePdInfoList.size() > 0) {
				for (TradeProductInfo tradeProductInfo : tradeProductInfoList) {
					String factorCode = tradeProductInfo.getParamCode();
					String factorValue = tradeProductInfo.getParamValue();
					if (factorCode != null && "subscriptionFee".equals(factorCode)) {
						BigDecimal inputValueDecimal = new BigDecimal(factorValue);
						totalAssets = inputValueDecimal.subtract(proAssets);
					}
					for (TradeProductInfo existTradeProductInfo : tradePdInfoList) {
						String existFactorCode = existTradeProductInfo.getParamCode();
						if (factorCode.equals(existFactorCode)) {
							TradeProductInfo tradeProductInfo1 = new TradeProductInfo();
							tradeProductInfo1.setParamCode(factorCode);
							tradeProductInfo1.setParamValue(factorValue);
							tradeProductInfo1.setTradeProductInfoId(existTradeProductInfo.getTradeProductInfoId());
							tradeProductInfo1.setTradeInfoId(tradeInfoId);
							tradeProductInfo1.setProductId(productId);
							BeanUtils.deleteAndInsertObjectSetOperateInfo(existTradeProductInfo, tradeProductInfo1,
									loginInfo);
							tradeProductInfoMapper.updateByPrimaryKey(tradeProductInfo1);

						}
					}

				}
			} else {
				for (int i = 0; i < tradeProductInfoList.size(); i++) {
					TradeProductInfo tradeProductInfo = new TradeProductInfo();
					String factorCode = tradeProductInfoList.get(i).getParamCode();
					String factorValue = tradeProductInfoList.get(i).getParamValue();
					if (factorCode != null && "subscriptionFee".equals(factorCode)) {
						BigDecimal inputValueDecimal = new BigDecimal(factorValue);
						totalAssets = inputValueDecimal.subtract(proAssets);
					}
					tradeProductInfo = tradeProductInfoList.get(i);
					tradeProductInfo.setTradeInfoId(tradeInfoId);
					tradeProductInfo.setTradeProductInfoId(commonService.getSeqValByName("SEQ_T_TRADE_PRODUCT_INFO"));
					BeanUtils.insertObjectSetOperateInfo(tradeProductInfo, loginInfo);
					tradeProductInfoMapper.insertSelective(tradeProductInfo);
				}
			}
		}

		TradeInfo updateTradeInfo = new TradeInfo();
		updateTradeInfo.setTradeInfoId(tradeInfoId);
		updateTradeInfo.setTradeTotalAssets(totalAssets);
		tradeInfoMapper.updateByPrimaryKeySelective(updateTradeInfo);
	}

	/**
	 * 基于接口的交易客户录入
	 * 
	 * @param custBaseInfoList
	 * @param tradeInfo
	 * @param loginInfo
	 */
	@Transactional
	private void saveTradeCustInfo(String custBaseInfoId, TradeInfo tradeInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 1.提交时先更新客户本次交易的账户和地址信息
			tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeInfo.getTradeInfoId());
			// tradeInfo.setCustAccInfoId(null);
			// tradeInfo.setCustAddressInfoId(null);
			tradeInfoMapper.updateByPrimaryKey(tradeInfo);
			// 2.获取之前保存的交易客户信息
			TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
			TradeCustInfoExample.Criteria criteria = tradeCustInfoExample.createCriteria();
			criteria.andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<TradeCustInfo> tradeCustInfoList = tradeCustInfoMapper.selectByExample(tradeCustInfoExample);
			// 3.保存客户和交易的关联关系，如果之前已经保存过，直接更新
			boolean isSave = false;
			// 已经保存过，直接更新
			for (TradeCustInfo tradeCustInfo : tradeCustInfoList) {
				if (tradeCustInfo.getCustBaseInfoId().compareTo(new Long(custBaseInfoId)) == 0) {
					isSave = true;
					tradeCustInfo.setCustBaseInfoId(new Long(custBaseInfoId));
					BeanUtils.updateObjectSetOperateInfo(tradeCustInfo, loginInfo);
					tradeCustInfoMapper.updateByPrimaryKey(tradeCustInfo);
					// 更新关联信息
					updateTradeCustRelaInfo(tradeInfo, tradeCustInfo, loginInfo);
					break;
				}
			}
			if (!isSave) {
				// 没有保存过，直接新增
				TradeCustInfo tradeCustInfo = new TradeCustInfo();
				Long tradeCustInfoIdDecimal = commonService.getSeqValByName("SEQ_T_TRADE_CUST_INFO");
				tradeCustInfo.setTradeCustInfoId(tradeCustInfoIdDecimal);
				tradeCustInfo.setCustBaseInfoId(new Long(custBaseInfoId));
				tradeCustInfo.setTradeInfoId(tradeInfo.getTradeInfoId());
				BeanUtils.insertObjectSetOperateInfo(tradeCustInfo, loginInfo);
				tradeCustInfoMapper.insert(tradeCustInfo);
				updateTradeCustRelaInfo(tradeInfo, tradeCustInfo, loginInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resultInfo.setSuccess(true);
		resultInfo.setMsg("交易客户信息保存成功!");
	}

	/**
	 * 基于接口的交易信息录入
	 * 
	 * @param tradeInfo
	 * @param loginInfo
	 * @return
	 */
	private ResultInfo saveTradeInfo1(TradeInfo tradeInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (tradeInfo == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易基本信息为空，不能保存");
				return resultInfo;
			}

			if (tradeInfo.getAgentId() == null || tradeInfo.getAgentId().equals("")) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取财富顾问信息,不能保存");
				return resultInfo;
			}

			if (tradeInfo.getOrderNo() != null) {
				TradeInfoExample tradeInfoExample = new TradeInfoExample();
				tradeInfoExample.createCriteria().andOrderNoEqualTo(tradeInfo.getOrderNo())
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeInfo> tradeInfoList = tradeInfoMapper.selectByExample(tradeInfoExample);
				if (tradeInfoList != null && tradeInfoList.size() > 0) {
					TradeInfo existTradeInfo = tradeInfoList.get(0);
					tradeInfo.setTradeInfoId(existTradeInfo.getTradeInfoId());
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existTradeInfo, tradeInfo, loginInfo);
					tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
				} else {
					Long tradeInfoId = commonService.getSeqValByName("SEQ_T_TRADE_INFO");
					tradeInfo.setTradeInfoId(tradeInfoId);
					tradeInfo.setTradeStaus(Constants.TRADE_STATUS_INPUT);
					tradeInfo.setInputOperator(loginInfo.getUserCode());
					BeanUtils.insertObjectSetOperateInfo(tradeInfo, loginInfo);
					tradeInfoMapper.insertSelective(tradeInfo);
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("交易信息保存成功！");
				resultInfo.setObj(tradeInfo);
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("订单号为空，无法录入！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public List<Map<String, String>> queryTradeRiskProId(HashMap paramMap) {
		String proType = (String) paramMap.get("proType");
		List<Map<String, String>> proList = new ArrayList<Map<String, String>>();
		proList = tradeServiceMapper.queryTradeRiskProId(paramMap);
		return proList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, String>> queryTradeRiskProtObj(HashMap paramMap) {
		List<Map<String, String>> insuredObjList = new ArrayList<Map<String, String>>();
		insuredObjList = tradeServiceMapper.queryTradeRiskProtObj(paramMap);
		return insuredObjList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, String>> queryTradeRiskProInfo(HashMap paramMap) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		resultList = tradeServiceMapper.queryTradeRiskProInfo(paramMap);
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, String>> queryTradeWealthProInfo(HashMap paramMap) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		resultList = tradeServiceMapper.queryTradeWealthProInfo(paramMap);
		return resultList;
	}

	/**
	 * 校验是否存在该交易流水号对应的记录
	 * 
	 * @return
	 */
	private boolean checkTradeId(Long tradeIdDecimal) {
		TradeInfoExample tradeInfoExample = new TradeInfoExample();
		tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeIdDecimal);
		int resultCount = tradeInfoMapper.countByExample(tradeInfoExample);
		if (resultCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 校验是否存在该交易流水号对应的记录
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ResultInfo checkCustomerInfo(Long tradeIdDecimal) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setSuccess(true);
		TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeIdDecimal);
		String tradeType = tradeInfo.getTradeType();
		int resultCount = 0;

		if (tradeType != null && !"".equals(tradeType) && "1".equals(tradeType)) {
			// 财富产品
			TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
			TradeCustInfoExample.Criteria criteria = tradeCustInfoExample.createCriteria();
			criteria.andTradeInfoIdEqualTo(tradeIdDecimal).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			resultCount = tradeCustInfoMapper.countByExample(tradeCustInfoExample);
		} else {
			// 保险产品
			Map paramMap = new HashMap();
			paramMap.put("roleType", "1");// 投保人
			paramMap.put("tradeInfoId", tradeIdDecimal.toString());
			resultCount = tradeServiceMapper.verifyTradeCustCount(paramMap);
			if (resultCount != 1) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("角色信息中未添加投保人信息或角色信息未提交！");
				return resultInfo;
			}
			Map paramMap1 = new HashMap();
			paramMap1.put("roleType", "0");// 被保人
			paramMap1.put("tradeInfoId", tradeIdDecimal.toString());
			resultCount = tradeServiceMapper.verifyTradeCustCount(paramMap1);
			if (resultCount < 1) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("角色信息中未添加被保人信息或角色信息未提交！");
				return resultInfo;
			}
		}
		if (resultCount > 0) {
			resultInfo.setSuccess(true);
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("请先保存客户信息！");
		}
		return resultInfo;
	}

	/**
	 * 校验是否存在该
	 * 
	 * @return
	 */
	private boolean checkRoleInfo(Long tradeIdDecimal) {
		TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
		tradeCustInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeIdDecimal)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		int resultCount = tradeCustInfoMapper.countByExample(tradeCustInfoExample);
		if (resultCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 校验是否存在该交易流水号对应的记录
	 * 
	 * @return
	 */
	private boolean checkProductInfo(Long tradeIdDecimal) {
		TradeProductInfoExample tradeProductInfoExample = new TradeProductInfoExample();
		tradeProductInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeIdDecimal)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		int resultCount = tradeProductInfoMapper.countByExample(tradeProductInfoExample);
		if (resultCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean checkTradeAccInfo(Long tradeIdDecimal) {
		TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeIdDecimal);
		Map paramMap = new HashMap();
		if (tradeInfo.getTradeType().equals("2")) {

			return true;
		}

		paramMap.put("tradeId", tradeIdDecimal.toString());
		paramMap.put("tradeType", tradeInfo.getTradeType());

		// List<Map<String,String>> accMapList = queryTradeBankInfo(paramMap);
		int resultCount = tradeServiceMapper.querySaveTradeBankCount(paramMap);
		/*
		 * for (Map<String,String> accMap:accMapList) { String accFlag =
		 * accMap.get("accFlag"); if
		 * (accFlag!=null&&!"".equals(accFlag)&&"1".equals(accFlag)) {
		 * resultCount++; } }
		 */
		if (resultCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean checkTradeAddressInfo(Long tradeIdDecimal) {
		TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeIdDecimal);
		Map paramMap = new HashMap();
		paramMap.put("tradeId", tradeIdDecimal.toString());
		paramMap.put("agentId", tradeInfo.getAgentId().toString());
		// List<Map<String,String>> addressMapList =
		// queryTradeAddressInfo(paramMap);
		int resultCount = tradeServiceMapper.querySaveTradeAddressCount(paramMap);
		/*
		 * for (Map<String,String> addressMap:addressMapList) { String
		 * addressFlag = addressMap.get("addressFlag"); if
		 * (addressFlag!=null&&!"".equals(addressFlag)&&"1".equals(addressFlag))
		 * { resultCount++; } }
		 */
		if (resultCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * 校验客户风控问卷
	 * @param tradeIdDecimal
	 * @return
	 */
	public boolean checkTradeCustomerQuestion(long tradeInfoId){
		TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
		TradeCustInfoExample.Criteria tradeCustInfoCriteria = tradeCustInfoExample.createCriteria();
		tradeCustInfoCriteria.andTradeInfoIdEqualTo(tradeInfoId)
							 .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustInfo> tradeCustInfoList = tradeCustInfoMapper.selectByExample(tradeCustInfoExample);
		if(tradeCustInfoList==null||tradeCustInfoList.size()<=0){
			return false;
		}
		CustQuestionnaireInfoExample custQuestionnaireInfoExample = new CustQuestionnaireInfoExample();
		CustQuestionnaireInfoExample.Criteria custQuestionnaireCriteria = custQuestionnaireInfoExample.createCriteria();
		custQuestionnaireCriteria.andCustBaseInfoIdEqualTo(tradeCustInfoList.get(0).getCustBaseInfoId())
								.andCustQuestionnaireStateEqualTo("01")
								//.andAgentIdEqualTo(tradeInfo.getAgentId())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		int count = custQuestionnaireInfoMapper.countByExample(custQuestionnaireInfoExample);
		if(count<=0){
			return false;
		}else{
			return true;
		}
	}
	
	
	@Override
	public List<Map<String, String>> queryTradeRoleInfo(HashMap paramMap) {
		String tradeId = (String) paramMap.get("tradeId");
		List<Map<String, String>> resultMap = new ArrayList<Map<String, String>>();
		if (tradeId != null && !tradeId.trim().isEmpty()) {
			resultMap = tradeServiceMapper.queryTradeRoleInfo(paramMap);
		}
		return resultMap;
	}

	@Override
	@Transactional
	public HashMap saveTradeInput(HashMap paramMap,LoginInfo loginInfo) {
		String tradeId = (String) paramMap.get("tradeId");
		String tradeType = (String) paramMap.get("tradeType");
		String userCode = (String) paramMap.get("userCode");
		String tradeConclusion = (String) paramMap.get("tradeConclusion");
		String tradeOperationNode = (String) paramMap.get("tradeOperationNode");
		Long userIdDecimal = (Long) paramMap.get("userId");

		// 校验该交易流水号对应是否存在记录
		HashMap resultMap = new HashMap();
		if (tradeId != null && !tradeId.trim().isEmpty()) {
			log.info("存在交易流水号对应的记录，可以保存财富产品信息！");

			Long tradeIdDecimal = new Long(tradeId);

			if (!checkTradeId(tradeIdDecimal)) {
				resultMap.put("success", "false");
				resultMap.put("msg", "请先保存交易信息！");
				return resultMap;
			}
			ResultInfo resultInfo = checkCustomerInfo(tradeIdDecimal);
			if (!resultInfo.isSuccess()) {
				resultMap.put("success", "false");
				resultMap.put("msg", resultInfo.getMsg());
				return resultMap;
			}
			if (tradeType != null && "2".equals(tradeType)) {
				if (!checkRoleInfo(tradeIdDecimal)) {
					resultMap.put("success", "false");
					resultMap.put("msg", "请先保存角色信息！");
					return resultMap;
				}
			}
			if (tradeType != null && "1".equals(tradeType)) {
				if (!checkTradeAddressInfo(tradeIdDecimal)) {
					resultMap.put("success", "false");
					resultMap.put("msg", "请先保存交易地址信息或是地址信息已修改但未保存！");
					return resultMap;
				}
				//购买财富产品必须填写风控问卷
				if(!checkTradeCustomerQuestion(tradeIdDecimal)){
					resultMap.put("success", "false");
					resultMap.put("msg", "请填写客户风控问卷调查或风控问卷调查已过期！");
					return resultMap;
				}
			}
			if (!checkTradeAccInfo(tradeIdDecimal)) {
				resultMap.put("success", "false");
				resultMap.put("msg", "请先保存交易账户信息或是账户信息已修改但未保存！");
				return resultMap;
			}
			if (!checkProductInfo(tradeIdDecimal)) {
				resultMap.put("success", "false");
				resultMap.put("msg", "请先保存产品信息！");
				return resultMap;
			}
		} else {
			log.info("不存在交易流水号对应的记录，不可以保存财富产品信息！");
			resultMap.put("success", "false");
			resultMap.put("msg", "请先保存交易信息！");
			return resultMap;
		}
		TradeInfo tradeInfo = new TradeInfo();
		tradeInfo.setTradeStaus(Constants.TRADE_STATUS_CHECK);
		if ("01".equals(tradeConclusion)) {
			if ("01".equals(tradeOperationNode)) {
				tradeInfo.setTradeStaus(Constants.TRADE_STATUS_CHECK);
			} else if ("02".equals(tradeOperationNode)) {
				tradeInfo.setTradeStaus(Constants.TRADE_STATUS_AUDIT);
			} else if ("03".equals(tradeOperationNode)) {
				tradeInfo.setTradeStaus(Constants.TRADE_STATUS_DONE);
			}
		} else if ("02".equals(tradeConclusion)) {
			tradeInfo.setTradeStaus(tradeOperationNode);
		} else if ("03".equals(tradeConclusion)) {
			if ("02".equals(tradeOperationNode)) {
				//退回
				tradeInfo.setTradeStaus(Constants.TRADE_STATUS_CANCEL);
			} else if ("03".equals(tradeOperationNode)) {
				tradeInfo.setTradeStaus(Constants.TRADE_STATUS_CHECK);
			}
		}

		tradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
		tradeInfo.setModifyUserId(userIdDecimal);
		TradeInfoExample tradeInfoExample = new TradeInfoExample();
		tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(new Long(tradeId));
		tradeInfoMapper.updateByExampleSelective(tradeInfo, tradeInfoExample);

		if (!dealTradeOperation(paramMap)) {
			resultMap.put("success", "false");
			resultMap.put("msg", "交易轨迹保存失败！");
			return resultMap;
		}
		// 更新客户所属理财经理信息
		tradeInfo = tradeInfoMapper.selectByPrimaryKey(new Long(tradeId));
		ResultInfo resultInfo = updateCustBelongAgentInfo(tradeInfo);
		//发送交易录入/审核邮件
		String tradeStatus = tradeInfo.getTradeStaus();
		if (tradeStatus.equals("02")) {
			resultInfo = sendTradeCheckEmail(tradeInfo,paramMap,loginInfo);
		}
		/*if (tradeStatus.equals("03")) {
			resultInfo = sendTradeRecheckEmail(tradeInfo,paramMap,loginInfo);
		}*/
		if (tradeStatus.equals("15")) {
			resultInfo = sendTradeCancelEmail(tradeInfo,paramMap,loginInfo);
		}
		
		if (!resultInfo.isSuccess()) {
			resultMap.put("success", "false");
			resultMap.put("msg", resultInfo.getMsg());
			return resultMap;
		}
		resultMap.put("success", "true");
		resultMap.put("msg", "录入完毕保存成功！");
		return resultMap;
	}
	

	/**
	 * 发送交易复核邮件
	 * @param tradeCallBackInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendTradeCheckEmail(TradeInfo tradeInfo,HashMap paramMap,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createTradeCheckEmail(tradeInfo, paramMap, loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		List<SysEmailInfo> sysEmailInfoList = (List<SysEmailInfo>)resultInfo.getObj();
		//发送短信
		for (SysEmailInfo sysEmailInfo2 : sysEmailInfoList) {
			resultInfo = sendTradeCheckEmail(sysEmailInfo2,loginInfo);
		}
		
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	/**
	 * 发送交易复核邮件
	 * @param tradeCallBackInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendTradeRecheckEmail(TradeInfo tradeInfo,HashMap paramMap,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createTradeRecheckEmail(tradeInfo, paramMap, loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		List<SysEmailInfo> sysEmailInfoList = (List<SysEmailInfo>)resultInfo.getObj();
		//发送短信
		for (SysEmailInfo sysEmailInfo2 : sysEmailInfoList) {

			resultInfo = sendTradeCheckEmail(sysEmailInfo2,loginInfo);
		}
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	
	/*================================================================================================================*/
	/**
	 * 发送交易退回录入邮件
	 * @param tradeCallBackInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendTradeCancelEmail(TradeInfo tradeInfo, HashMap paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		//先创建邮件，然后发送
		resultInfo = createTradeCancelEmail(tradeInfo, paramMap, loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		//发送邮件
			SysEmailInfo sysEmailInfo = (SysEmailInfo)resultInfo.getObj();
			resultInfo = sendTradeCheckEmail(sysEmailInfo,loginInfo);
			
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	/**
	 * 创建交易退回录入邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createTradeCancelEmail(TradeInfo tradeInfo, HashMap paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		//获取交易回访邮件所需数据
		paramMap.put("tradeInfoId", tradeInfo.getTradeInfoId().toString());
		Map<String, String> tradeCheckEmailData = tradeServiceMapper.getTradeCheckEmailData(paramMap);
	
		if(tradeCheckEmailData==null||tradeCheckEmailData.size()==0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到交易录入相关数据，创建邮件失败！");
			return resultInfo;
		}
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//xxxxx通知邮件
		sysEmailInfo.setEmailType("11");
		String tradeInfoId = tradeInfo.getTradeInfoId().toString();
		sysEmailInfo.setEmailRelationNo(Long.parseLong(tradeInfoId));
		sysEmailInfo.setEmailTitle("交易退回通知");
		Agent agentInfo = agentMapper.selectByPrimaryKey(tradeInfo.getAgentId());
		sysEmailInfo.setEmailAddress(agentInfo.getEmail());
		//创建邮件内容
			String mailContent = createEmailContentCancel(tradeCheckEmailData,paramMap);
		sysEmailInfo.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo.setEmailStatus("01");
		sysEmailInfo.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo);
		
		
		resultInfo.setSuccess(true);
		resultInfo.setMsg("交易退回邮件创建成功！");
		resultInfo.setObj(sysEmailInfo);
		return resultInfo;
	}
	/**
	 * 生成交易退回邮件类容
	 * @param callBackEmailData
	 * @return
	 */
	private String createEmailContentCancel(Map<String, String> tradeCheckEmailData, HashMap paramMap) {

		StringBuffer mailContent = new StringBuffer();
		mailContent.append("您好！您提交的交易“");
		mailContent.append(tradeCheckEmailData.get("tradeNo"));
		mailContent.append("”于“");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		mailContent.append(df.format(new Date()));
		mailContent.append("”被退回录入。请及时查看。");
		return mailContent.toString();
		
	}
	

	/*================================================================================================================*/
	/**
	 * 创建交易录入邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createTradeCheckEmail(TradeInfo tradeInfo,HashMap paramMap,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//获取交易回访邮件所需数据
		paramMap.put("tradeInfoId", tradeInfo.getTradeInfoId().toString());
		Map<String, String> tradeCheckEmailData = tradeServiceMapper.getTradeCheckEmailData(paramMap);
		DefUser userName = defUserMapper.selectByPrimaryKey(loginInfo.getUserId());
		String loginUserName = userName.getUserName();
		tradeCheckEmailData.put("loginUserName", loginUserName);
		if(tradeCheckEmailData==null||tradeCheckEmailData.size()==0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到交易录入相关数据，创建邮件失败！");
			return resultInfo;
		}
		/*if (!tradeCheckEmailData.containsKey("agentEmail")) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到理财经理邮箱，创建邮件失败！");
			return resultInfo;
		}*/
		//理财经理邮箱
		//String agentEmail = tradeCheckEmailData.get("agentEmail");
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//交易复核通知邮件
		sysEmailInfo.setEmailType("01");
		String tradeInfoId = tradeInfo.getTradeInfoId().toString();
		sysEmailInfo.setEmailRelationNo(Long.parseLong(tradeInfoId));
		//获取不同邮件类型的邮箱地址
		String emailType = sysEmailInfo.getEmailType();
		SysEmailAdressExample sysEmailAdressExample = new SysEmailAdressExample();
		SysEmailAdressExample.Criteria sysEmailAdressCriteria = sysEmailAdressExample.createCriteria();
		sysEmailAdressCriteria.andEmailTypeEqualTo(emailType);
		List<SysEmailAdress> email = sysEmailAdressMapper.selectByExample(sysEmailAdressExample);
		List<SysEmailInfo> resultList = new ArrayList<SysEmailInfo>();
		SysEmailInfo sysEmailInfo1 = null;
			for (int i = 0; i < email.size(); i++) {
						sysEmailInfo1 = new SysEmailInfo();
						sysEmailInfo1.setEmailType("01");
						sysEmailInfo.setEmailRelationNo(Long.parseLong(tradeInfoId));
						sysEmailInfo1.setEmailTitle("交易录入通知");
						sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
		//创建邮件内容
			String mailContent = createEmailContentCheck(tradeCheckEmailData,paramMap);
			sysEmailInfo1.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo1.setEmailStatus("01");
		sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo1);
		resultList.add(sysEmailInfo1);
				}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("交易回访邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
	}
	/**
	 * 创建交易审核邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createTradeRecheckEmail(TradeInfo tradeInfo,HashMap paramMap,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//获取交易回访邮件所需数据
		paramMap.put("tradeInfoId", tradeInfo.getTradeInfoId().toString());
		Map<String, String> tradeCheckEmailData = tradeServiceMapper.getTradeCheckEmailData(paramMap);
		DefUser userName = defUserMapper.selectByPrimaryKey(loginInfo.getUserId());
		String loginUserName = userName.getUserName();
		tradeCheckEmailData.put("loginUserName", loginUserName);
		if(tradeCheckEmailData==null||tradeCheckEmailData.size()==0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到交易审核相关数据，创建邮件失败！");
			return resultInfo;
		}
		/*if (!tradeCheckEmailData.containsKey("agentEmail")) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到理财经理邮箱，创建邮件失败！");
			return resultInfo;
		}*/
		//理财经理邮箱
		//String agentEmail = tradeCheckEmailData.get("agentEmail");
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//交易复核通知邮件
		sysEmailInfo.setEmailType("02");
		String tradeInfoId = tradeInfo.getTradeInfoId().toString();
		sysEmailInfo.setEmailRelationNo(Long.parseLong(tradeInfoId));
		//获取不同邮件类型的邮箱地址
		String emailType = sysEmailInfo.getEmailType();
		SysEmailAdressExample sysEmailAdressExample = new SysEmailAdressExample();
		SysEmailAdressExample.Criteria sysEmailAdressCriteria = sysEmailAdressExample.createCriteria();
		sysEmailAdressCriteria.andEmailTypeEqualTo(emailType);
		List<SysEmailAdress> email = sysEmailAdressMapper.selectByExample(sysEmailAdressExample);
		List<SysEmailInfo> resultList = new ArrayList<SysEmailInfo>();
		SysEmailInfo sysEmailInfo1 = null;
				for (int i = 0; i < email.size(); i++) {
						sysEmailInfo1 = new SysEmailInfo();
						sysEmailInfo1.setEmailType("02");
						sysEmailInfo.setEmailRelationNo(Long.parseLong(tradeInfoId));
						sysEmailInfo1.setEmailTitle("交易审核通知");
						sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
		//创建邮件内容
			String mailContent = createEmailContentRecheck(tradeCheckEmailData,paramMap);
			sysEmailInfo1.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo1.setEmailStatus("01");
		sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo1);
		resultList.add(sysEmailInfo1);
	}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("交易审核邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
	}
	
	/**
	 * 生成交易审核回访邮件类容
	 * @param callBackEmailData
	 * @return
	 */
	private String createEmailContentCheck(Map<String,String> tradeCheckEmailData,HashMap paramMap){
		StringBuffer mailContent = new StringBuffer();

		mailContent.append("用户“");
		mailContent.append(tradeCheckEmailData.get("loginUserName"));
		mailContent.append("”于“");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		mailContent.append(df.format(new Date()));
		mailContent.append("”提交认购《");
		mailContent.append(tradeCheckEmailData.get("productName"));
		mailContent.append("》产品“");
		mailContent.append(tradeCheckEmailData.get("subscriptionFee"));
		mailContent.append("”，请审核。");
		return mailContent.toString();
	}
	/**
	 * 生成复核交易回访邮件类容
	 * @param callBackEmailData
	 * @return
	 */
	private String createEmailContentRecheck(Map<String,String> tradeCheckEmailData,HashMap paramMap){
		StringBuffer mailContent = new StringBuffer();

		mailContent.append("用户“");
		mailContent.append(tradeCheckEmailData.get("loginUserName"));
		mailContent.append("”于“");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		mailContent.append(df.format(new Date()));
		mailContent.append("”已复核交易《");
		mailContent.append(tradeCheckEmailData.get("productName"));
		mailContent.append("》产品“");
		mailContent.append(tradeCheckEmailData.get("subscriptionFee"));
		mailContent.append("”，请审核。");
		return mailContent.toString();
	}
	
	
	/**
	 * 发送邮件
	 * @return
	 */
	@Transactional
	private ResultInfo sendTradeCheckEmail(SysEmailInfo sysEmailInfo,LoginInfo loginInfo){
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
	
	@Override
	public List<Map<String, String>> queryTradeComId(HashMap paramMap) {
		List<Map<String, String>> resultMap = new ArrayList<Map<String, String>>();
		resultMap = tradeServiceMapper.queryTradeComId(paramMap);
		return resultMap;
	}

	/*@Override
	public List<Map<String, String>> queryTradeStoreId(HashMap paramMap) {
		List<Map<String, String>> resultMap = new ArrayList<Map<String, String>>();
		resultMap = tradeServiceMapper.queryTradeStoreId(paramMap);
		return resultMap;
	}*/
	@Override
	@Transactional
	public HashMap delTradeInfo(HashMap paramMap, LoginInfo loginInfo) {
		String tradeInfoData = (String) paramMap.get("tradeInfoData");
		String userCode = (String) paramMap.get("userCode");
		Long userIdDecimal = (Long) paramMap.get("userId");
		Long comIdDecimal = (Long) paramMap.get("comId");
		List<Map> tradeInfoList = new ArrayList<Map>();
		tradeInfoList = JSON.parseArray(tradeInfoData, Map.class);
		String loginUserId = loginInfo.getUserId().toString();
		HashMap resultMap = new HashMap();
		//若登录用户为fms，则可以删除已撤销交易信息
		if(loginUserId.equals("1")){
		if (tradeInfoList != null && tradeInfoList.size() > 0) {
			for (int i = 0; i < tradeInfoList.size(); i++) {
				// 删除T_TRADE_INFO表
				Integer tradeInfoId = (Integer) tradeInfoList.get(i).get("tradeInfoId");
				TradeInfo tradeInfo = new TradeInfo();
				tradeInfo = tradeInfoMapper.selectByPrimaryKey(new Long(tradeInfoId));

				BeanUtils.deleteObjectSetOperateInfo(tradeInfo, loginInfo);
				/*
				 * tradeInfo.setTradeInfoId(new BigDecimal(tradeInfoId));
				 * tradeInfo.setRcState(Constants.DELETE_RECORD);
				 * tradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
				 * tradeInfo.setModifyUserId(userIdDecimal);
				 */
				tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);

				// 删除产品信息
				TradeProductInfoExample tradeProductInfoExample = new TradeProductInfoExample();
				TradeProductInfoExample.Criteria criteria1 = tradeProductInfoExample.createCriteria();
				criteria1.andTradeInfoIdEqualTo(new Long(tradeInfoId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeProductInfo> tradeProductInfoList = tradeProductInfoMapper
						.selectByExample(tradeProductInfoExample);
				for (TradeProductInfo tradeProductInfo : tradeProductInfoList) {
					BeanUtils.deleteObjectSetOperateInfo(tradeProductInfo, loginInfo);
					tradeProductInfoMapper.updateByPrimaryKey(tradeProductInfo);
				}
				// 删除交易的客户信息及附属信息
				TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
				TradeCustInfoExample.Criteria criteria = tradeCustInfoExample.createCriteria();
				criteria.andTradeInfoIdEqualTo(new Long(tradeInfoId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeCustInfo> tradeCustInfoList = tradeCustInfoMapper.selectByExample(tradeCustInfoExample);

				for (TradeCustInfo tradeCustInfo : tradeCustInfoList) {
					Map<String, String> tradeCustInfoMap = new HashMap<String, String>();
					tradeCustInfoMap.put("tradeCustInfoId", tradeCustInfo.getTradeCustInfoId().toString());
					delTradeCusInfo(tradeCustInfoMap, tradeInfo, loginInfo);
				}
			}
		}
		
		resultMap.put("success", "true");
		resultMap.put("msg", "删除成功！");
		return resultMap;
	}
	else{
			resultMap.put("success", "false");
			resultMap.put("msg", "当前用户不能删除已撤销交易信息！");
			return resultMap;
			
		}
	}

	@Override
	public List<Map<String, String>> queryTradeAgentId(HashMap paramMap) {
		List<Map<String, String>> resultList = tradeServiceMapper.queryTradeAgentId(paramMap);
		return resultList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public synchronized HashMap applyTradeCheckInfo(HashMap paramMap) {
		HashMap resultMap = new HashMap();
		String tradeInfoId = (String) paramMap.get("tradeInfoId");
		String userCode = (String) paramMap.get("userCode");
		Long userIdDecimal = (Long) paramMap.get("userId");
		Long tradeInfoIdDecimal = new Long(tradeInfoId);
		// BigDecimal userIdDecimal = new BigDecimal(userId);
		TradeInfoExample tradeInfoExample = new TradeInfoExample();
		tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeInfoIdDecimal).andCheckOperatorIsNotNull();
		List<TradeInfo> list = tradeInfoMapper.selectByExample(tradeInfoExample);
		if (list != null && list.size() == 0) {
			TradeInfo tradeInfo = new TradeInfo();
			tradeInfo.setTradeInfoId(tradeInfoIdDecimal);
			tradeInfo.setCheckOperator(userCode);
			tradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeInfo.setModifyUserId(userIdDecimal);
			tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
			resultMap.put("success", "true");
		} else if (list != null && list.size() > 0 && userCode.equals(list.get(0).getCheckOperator())) {
			TradeInfo tradeInfo = new TradeInfo();
			tradeInfo.setTradeInfoId(tradeInfoIdDecimal);
			tradeInfo.setCheckOperator(userCode);
			tradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeInfo.setModifyUserId(userIdDecimal);
			tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
			resultMap.put("success", "true");
		} else {
			/*resultMap.put("success", "false");
			resultMap.put("msg", "该交易信息在复核中，不可进行复核！");
			if (list != null && list.size() > 0 && list.get(0).getCheckOperator() != null
					&& !"".equals(list.get(0).getCheckOperator())) {
				resultMap.put("msg", "该交易信息在复核中，不可进行复核，复核人员为" + list.get(0).getCheckOperator() + "！");
			}*/
			//要求复核的时候可以多人交叉复核 注释掉了上面的校验
			resultMap.put("success", "true");
		}
		return resultMap;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public synchronized HashMap applyTradeAuditInfo(HashMap paramMap) {
		HashMap resultMap = new HashMap();
		String tradeInfoId = (String) paramMap.get("tradeInfoId");
		String userCode = (String) paramMap.get("userCode");
		Long userIdDecimal = (Long) paramMap.get("userId");
		Long tradeInfoIdDecimal = new Long(tradeInfoId);
		TradeInfoExample tradeInfoExample = new TradeInfoExample();
		tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeInfoIdDecimal).andAuditOperatorIsNotNull();
		List<TradeInfo> list = tradeInfoMapper.selectByExample(tradeInfoExample);
		if (list != null && list.size() == 0) {
			TradeInfo tradeInfo = new TradeInfo();
			tradeInfo.setTradeInfoId(tradeInfoIdDecimal);
			tradeInfo.setAuditOperator(userCode);
			tradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeInfo.setModifyUserId(userIdDecimal);
			tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);

			resultMap.put("success", "true");
		} else if (list != null && list.size() > 0 && userCode.equals(list.get(0).getAuditOperator())) {
			TradeInfo tradeInfo = new TradeInfo();
			tradeInfo.setTradeInfoId(tradeInfoIdDecimal);
			tradeInfo.setAuditOperator(userCode);
			tradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeInfo.setModifyUserId(userIdDecimal);
			tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
			resultMap.put("success", "true");
		} else {
			/*String auditOperator = list.get(0).getAuditOperator();
			resultMap.put("success", "false");
			if (auditOperator != null && !"".equals(auditOperator)) {
				resultMap.put("msg", "该交易信息在审核中(复核人员" + auditOperator + ")，不可进行审核！");
			} else {
				resultMap.put("msg", "该交易信息在审核中，不可进行审核！");
			}*/
			//要求审核的时候可以多人交叉复核 注释掉了上面的校验
			resultMap.put("success", "true");

		}

		return resultMap;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	@Transactional
	public HashMap saveTradeCheck(HashMap paramMap) {
		HashMap resultMap = new HashMap();
		String tradeInfoId = (String) paramMap.get("tradeInfoId");
		String userCode = (String) paramMap.get("userCode");
		Integer userId = (Integer) paramMap.get("userId");
		BigDecimal tradeInfoIdDecimal = new BigDecimal(tradeInfoId);
		BigDecimal userIdDecimal = new BigDecimal(userId);
		// tradeInfoMapper.updateByPrimaryKeySelective(record);
		return null;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	@Transactional
	public HashMap saveTradeAudit(HashMap paramMap) {
		HashMap resultMap = new HashMap();
		String tradeInfoId = (String) paramMap.get("tradeInfoId");
		String userCode = (String) paramMap.get("userCode");
		Integer userId = (Integer) paramMap.get("userId");
		BigDecimal tradeInfoIdDecimal = new BigDecimal(tradeInfoId);
		BigDecimal userIdDecimal = new BigDecimal(userId);
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, String>> queryRiskProInfoObjList(HashMap paramMap) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		resultList = tradeServiceMapper.queryRiskProInfoObjList(paramMap);
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, String>> queryMonryProInfoObjList(HashMap paramMap) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		resultList = tradeServiceMapper.queryMonryProInfoObjList(paramMap);
		return resultList;
	}

/**
 * @author 燕
 *查询转让交易产品录入要素信息
 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, String>> queryTransFerMoneyProInfoObjList(HashMap paramMap) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		resultList = tradeServiceMapper.queryTransFerMoneyProInfoObjList(paramMap);
		return resultList;
	}
	@Override
	@Transactional
	public ResultInfo delRoleInfo(TradeInfo tradeInfo, TradeCustRoleInfo tradeCustRoleInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		if (tradeInfo.getTradeInfoId() != null) {
			tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeInfo.getTradeInfoId());
			// 1.删除角色信息
			tradeCustRoleInfo = tradeCustRoleInfoMapper.selectByPrimaryKey(tradeCustRoleInfo.getTradeCustRoleInfoId());
			BeanUtils.deleteObjectSetOperateInfo(tradeCustRoleInfo, loginInfo);
			tradeCustRoleInfoMapper.updateByPrimaryKey(tradeCustRoleInfo);
			// 2.删除角色关联的产品信息
			TradePdCustRoleRelaExample tradePdCustRoleRelaExample = new TradePdCustRoleRelaExample();
			TradePdCustRoleRelaExample.Criteria criteria = tradePdCustRoleRelaExample.createCriteria();
			criteria.andTradeCustRoleInfoIdEqualTo(tradeCustRoleInfo.getTradeCustRoleInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<TradePdCustRoleRela> tradePdCustRoleRelaList = tradePdCustRoleRelaMapper
					.selectByExample(tradePdCustRoleRelaExample);
			for (TradePdCustRoleRela tradePdCustRoleRela : tradePdCustRoleRelaList) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				BigDecimal tradeTotalAssets = tradeInfo.getTradeTotalAssets();
				if (tradeTotalAssets == null) {
					tradeTotalAssets = new BigDecimal(0);
				}
				paramMap.put("tradeInfo", tradeInfo);
				paramMap.put("productId", tradePdCustRoleRela.getProductId().toString());
				paramMap.put("riskTotalAssets", tradeTotalAssets.toString());
				resultInfo = delRiskProInfo(paramMap, loginInfo);
				if (!resultInfo.isSuccess()) {
					resultInfo.setMsg("删除角色对应的产品信息出错");
					return resultInfo;
				}
			}
			// 如果是投保人，删除投保人的默认账户信息
			String roleType = tradeCustRoleInfo.getRoleType();
			if (roleType != null && !"".equals(roleType) && "1".equals(roleType)) {
				TradeCustInfo tradeCustInfo = new TradeCustInfo();
				tradeCustInfo.setTradeCustInfoId(tradeCustRoleInfo.getTradeCustInfoId());
				deleteBankInfo(tradeCustInfo, loginInfo);
			}
			/*
			 * //投被保人同一人，删除被保人 TradeCustInfo tradeCustInfo =
			 * tradeCustInfoMapper.selectByPrimaryKey(tradeCustRoleInfo.
			 * getTradeCustInfoId()); if (tradeCustInfo!=null) { Map<String,
			 * String> paramMap = new HashMap<String, String>();
			 * paramMap.put("tradeCustInfoId",tradeCustInfo.getTradeCustInfoId()
			 * .toString()); resultInfo = delTradeCusInfo(paramMap, tradeInfo,
			 * loginInfo); if (!resultInfo.isSuccess()) { return resultInfo; } }
			 */
			resultInfo.setSuccess(true);
			resultInfo.setMsg("角色信息删除成功!");
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("请先保存交易信息!");
		}
		return resultInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public ResultInfo delRiskProInfo(Map<String, Object> paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		TradeInfo tradeInfo = (TradeInfo) paramMap.get("tradeInfo");
		String productId = (String) paramMap.get("productId");
		String riskTotalAssets = (String) paramMap.get("riskTotalAssets");
		if (riskTotalAssets == null || "".equals(riskTotalAssets)) {
			riskTotalAssets = "0";
		}
		BigDecimal riskTotalAssetsDecimal = new BigDecimal(riskTotalAssets);
		if (tradeInfo != null && tradeInfo.getTradeInfoId() != null) {
			// 1.更新交易总金额
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("tradeId", tradeInfo.getTradeInfoId().toString());
			queryMap.put("tradeRiskProId", productId);
			BigDecimal delRiskProAssest = tradeServiceMapper.queryRiskProAssets(queryMap);
			riskTotalAssetsDecimal = riskTotalAssetsDecimal.subtract(delRiskProAssest);
			tradeInfo.setTradeInfoId(tradeInfo.getTradeInfoId());
			tradeInfo.setTradeTotalAssets(riskTotalAssetsDecimal);
			tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
			// 删除交易产品信息
			TradeProductInfoExample tradeProductInfoExample = new TradeProductInfoExample();
			tradeProductInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId())
					.andProductIdEqualTo(new Long(productId)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			TradeProductInfo tradeProductInfo = new TradeProductInfo();
			BeanUtils.deleteObjectSetOperateInfo(tradeProductInfo, loginInfo);
			tradeProductInfoMapper.updateByExampleSelective(tradeProductInfo, tradeProductInfoExample);
			// 3.删除产品角色关联信息
			// tradeServiceMapper.updateTradePdCustRoleRela(paramMap);
			deleteTradePdCustRoleRela(tradeInfo.getTradeInfoId(), new Long(productId), loginInfo);
			Map resultMap = new HashMap();
			resultMap.put("totalAssets", riskTotalAssetsDecimal);
			if (riskTotalAssetsDecimal != null) {
				resultMap.put("chnTotalAssets",
						SimpleMoneyFormat.formatChineseCapital(riskTotalAssetsDecimal.doubleValue()));
			}
			resultInfo.setObj(resultMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("保险产品信息删除成功!");
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("请先保存交易信息!");
		}
		return resultInfo;
	}

	@Transactional
	private boolean deleteTradePdCustRoleRela(Long tradeInfoId, Long productId, LoginInfo loginInfo) {
		TradePdCustRoleRelaExample tradePdCustRoleRelaExample = new TradePdCustRoleRelaExample();
		TradePdCustRoleRelaExample.Criteria criteria = tradePdCustRoleRelaExample.createCriteria();
		criteria.andTradeInfoIdEqualTo(tradeInfoId).andProductIdEqualTo(productId)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradePdCustRoleRela> tradePdCustRoleRelaList = tradePdCustRoleRelaMapper
				.selectByExample(tradePdCustRoleRelaExample);
		for (TradePdCustRoleRela tradePdCustRoleRela : tradePdCustRoleRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradePdCustRoleRela, loginInfo);
			tradePdCustRoleRelaMapper.updateByPrimaryKey(tradePdCustRoleRela);
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Override
	@Transactional
	public HashMap delMonProInfo(HashMap paramMap) {
		HashMap resultMap = new HashMap();
		String tradeInfoId = (String) paramMap.get("tradeId");
		String monTotalAssets = (String) paramMap.get("monTotalAssets");
		String tradeMonProId = (String) paramMap.get("tradeWealthProId");
		String userCode = (String) paramMap.get("userCode");
		Long userIdDecimal = (Long) paramMap.get("userId");
		Long comIdDecimal = (Long) paramMap.get("comId");
		
		//PDProductExample pdProductExample=new PDProductExample();
		//pdProductExample.createCriteria().andProductIdEqualTo(new Long(tradeMonProId)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		Long productId=new Long(tradeMonProId);//获取产品ID
		PDProduct pdProduct=pdProductMapper.selectByPrimaryKey(productId);//根据产品iD获取产品的子类型
		String pst=pdProduct.getProductSubType();//产品的子类型
		boolean isInRaistProduct=false;//定义该产品是否不可以删除
		Date nowDate=new Date();//获取当前的时间，由于判当前时间是否在募集期类
		if(pst.equals("01")||pst.equals("02")){
			//"01"股权产品，"02"固定产品
			PDWealthExample pdWealthExample=new PDWealthExample();
			pdWealthExample.createCriteria().andProductIdEqualTo(productId).andRaiseStartDateLessThanOrEqualTo(nowDate).
			andRaiseEndDateGreaterThanOrEqualTo(nowDate).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDWealth> pdWealthList=pdWealthMapper.selectByExample(pdWealthExample);
			if(!pdWealthList.isEmpty()){
				isInRaistProduct=true;
			}
		}else{
			//为03浮动产品或者04海外产品
			PDWealthExample pdWealthExample=new PDWealthExample();
			pdWealthExample.createCriteria().andProductIdEqualTo(productId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDWealth> pdWealthList=pdWealthMapper.selectByExample(pdWealthExample);
			if(!pdWealthList.isEmpty()){
				PDWealth pdWealth=pdWealthList.get(0);//先获取T_pd_wealth的iD
				PDWealthOpenDateExample pdWealthOpenDateExample=new PDWealthOpenDateExample();
				pdWealthOpenDateExample.createCriteria().andPdWealthIdEqualTo(pdWealth.getPdWealthId()).andInvestStartDateLessThanOrEqualTo(nowDate).andInvestEndDateGreaterThanOrEqualTo(nowDate).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<PDWealthOpenDate> pdWealthOpenDateList=pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample);
				if(!pdWealthOpenDateList.isEmpty()){
					isInRaistProduct=true;
				}
			}else{
				resultMap.put("success", "false");
				resultMap.put("msg", "财富表中找不到该财富产品！");
				return resultMap;
			}
			
			
		}
		
		if(isInRaistProduct){
			resultMap.put("success", "false");
			resultMap.put("msg", "该产品在预约中不能删除！");
			return resultMap;
		}

		BigDecimal monTotalAssetsDecimal = new BigDecimal(monTotalAssets);
		if (tradeInfoId != null && !tradeInfoId.trim().isEmpty()) {
			Long tradeInfoIdDecimal = new Long(tradeInfoId);
			BigDecimal delWealthProAssest = tradeServiceMapper.queryWealthProAssets(paramMap);
			monTotalAssetsDecimal = monTotalAssetsDecimal.subtract(delWealthProAssest);
			TradeInfo tradeInfo = new TradeInfo();
			tradeInfo.setTradeInfoId(tradeInfoIdDecimal);
			tradeInfo.setTradeTotalAssets(monTotalAssetsDecimal);
			tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);

			TradeProductInfoExample tradeProductInfoExample = new TradeProductInfoExample();
			tradeProductInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeInfoIdDecimal)
					.andProductIdEqualTo(new Long(tradeMonProId)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			TradeProductInfo tradeProductInfo = new TradeProductInfo();
			tradeProductInfo.setRcState(Constants.DELETE_RECORD);
			tradeProductInfo.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeProductInfo.setModifyUserId(userIdDecimal);
			tradeProductInfoMapper.updateByExampleSelective(tradeProductInfo, tradeProductInfoExample);

			resultMap.put("totalAssets", monTotalAssetsDecimal);
			if (monTotalAssetsDecimal != null) {
				resultMap.put("chnTotalAssets",
						SimpleMoneyFormat.formatChineseCapital(monTotalAssetsDecimal.doubleValue()));
			}
			resultMap.put("success", "true");
			resultMap.put("msg", "财富产品信息删除成功!");
		} else {
			resultMap.put("success", "false");
			resultMap.put("msg", "请先保存交易信息!");
		}
		return resultMap;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Transactional
	private boolean dealTradeOperation(HashMap paramMap) {
		String tradeId = (String) paramMap.get("tradeId");
		String tradeType = (String) paramMap.get("tradeType");
		String userCode = (String) paramMap.get("userCode");
		String tradeConclusion = (String) paramMap.get("tradeConclusion");
		String tradeRemark = (String) paramMap.get("tradeRemark");
		String tradeOperationNode = (String) paramMap.get("tradeOperationNode");
		Long userIdDecimal = (Long) paramMap.get("userId");
		Long comIdDecimal = (Long) paramMap.get("comId");
		Long tradeOperationID = commonService.getSeqValByName("SEQ_T_TRADE_OPERATION");
		Long tradeOperationTraceID = commonService.getSeqValByName("SEQ_T_TRADE_OPERATION_TRACE");
		Long tradeIdDecimal = new Long(tradeId);
		String rc_state = Constants.EFFECTIVE_RECORD;

		TradeOperationExample tradeOperationExample = new TradeOperationExample();
		tradeOperationExample.createCriteria().andTradeInfoIdEqualTo(tradeIdDecimal)
				.andOperationalNodeEqualTo(tradeOperationNode);
		List<TradeOperation> tradeOperationList = tradeOperationMapper.selectByExample(tradeOperationExample);
		if (tradeOperationList != null && tradeOperationList.size() > 0) {
			TradeOperation tradeOperation = new TradeOperation();
			tradeOperation = tradeOperationList.get(0);

			TradeOperationTrace tradeOperationTrace = new TradeOperationTrace();
			tradeOperationTrace.setTradeInfoId(tradeOperation.getTradeInfoId());
			tradeOperationTrace.setTradeOperationId(tradeOperation.getTradeOperationId());
			tradeOperationTrace.setTradeOperationTraceId(tradeOperationTraceID);
			tradeOperationTrace.setOperationalNode(tradeOperation.getOperationalNode());
			tradeOperationTrace.setUserId(tradeOperation.getUserId());
			tradeOperationTrace.setOperComId(tradeOperation.getOperComId());
			tradeOperationTrace.setConclusion(tradeOperation.getConclusion());
			tradeOperationTrace.setBackTime(DateUtils.getCurrentTimestamp());
			tradeOperationTrace.setCreateDate(DateUtils.getCurrentTimestamp());
			tradeOperationTrace.setCreateUserId(userIdDecimal);
			tradeOperationTrace.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeOperationTrace.setModifyUserId(userIdDecimal);
			tradeOperationTrace.setRcState(rc_state);
			tradeOperationTrace.setRemark(tradeOperation.getRemark());
			tradeOperationTraceMapper.insertSelective(tradeOperationTrace);

			tradeOperation.setConclusion(tradeConclusion);
			tradeOperation.setRemark(tradeRemark);
			tradeOperation.setUserId(userIdDecimal);
			tradeOperation.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeOperation.setModifyUserId(userIdDecimal);
			tradeOperationMapper.updateByExampleSelective(tradeOperation, tradeOperationExample);
		} else {
			TradeOperation tradeOperation = new TradeOperation();
			tradeOperation.setTradeInfoId(tradeIdDecimal);
			tradeOperation.setTradeOperationId(tradeOperationID);
			tradeOperation.setUserId(userIdDecimal);
			tradeOperation.setOperComId(comIdDecimal);
			tradeOperation.setConclusion(tradeConclusion);
			tradeOperation.setCreateDate(DateUtils.getCurrentTimestamp());
			tradeOperation.setCreateUserId(userIdDecimal);
			tradeOperation.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeOperation.setModifyUserId(userIdDecimal);
			tradeOperation.setOperationalNode(tradeOperationNode);
			tradeOperation.setRcState(rc_state);
			tradeOperation.setRemark(tradeRemark);
			tradeOperationMapper.insertSelective(tradeOperation);
		}
		return true;
	}

	@Override
	public HashMap queryTradeConclusion(HashMap paramMap) {
		HashMap resultMap = tradeServiceMapper.queryTradeConclusion(paramMap);
		if (resultMap == null || resultMap.size() == 0) {
			resultMap = new HashMap();
			resultMap.put("success", "false");
		} else {
			resultMap.put("success", "true");
		}
		return resultMap;
	}
	
	/////////////////////////////////////////////////
	@Override
	public HashMap queryTradeConclusion2(HashMap paramMap) {
		HashMap resultMap = tradeServiceMapper.queryTradeConclusion2(paramMap);
		if (resultMap == null || resultMap.size() == 0) {
			resultMap = new HashMap();
			resultMap.put("success", "false");
		} else {
			resultMap.put("success", "true");
		}
		return resultMap;
	}
	////////////////////////////////////////////////////

	@Override
	@Transactional
	public ResultInfo saveTradeCusInfo(List<Map<String, String>> custBaseInfoList, TradeInfo tradeInfo,
			LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 1.提交时先更新客户本次交易的账户和地址信息
			tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeInfo.getTradeInfoId());
			// tradeInfo.setCustAccInfoId(null);
			// tradeInfo.setCustAddressInfoId(null);
			tradeInfoMapper.updateByPrimaryKey(tradeInfo);
			// 2.获取之前保存的交易客户信息
			TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
			TradeCustInfoExample.Criteria criteria = tradeCustInfoExample.createCriteria();
			criteria.andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);			
			List<TradeCustInfo> tradeCustInfoList = tradeCustInfoMapper.selectByExample(tradeCustInfoExample);
			// 3.保存客户和交易的关联关系，如果之前已经保存过，直接更新
			for (Map<String, String> tradeCustInfoMap : custBaseInfoList) {
				String tradeCustInfoId = tradeCustInfoMap.get("tradeCustInfoId");
				String custBaseInfoId = tradeCustInfoMap.get("custBaseInfoId").trim();
				boolean isSave = false;
				// 已经保存过，直接更新
				for (TradeCustInfo tradeCustInfo : tradeCustInfoList) {
					if (tradeCustInfo.getCustBaseInfoId().compareTo(new Long(custBaseInfoId)) == 0) {
						isSave = true;
						tradeCustInfo.setCustBaseInfoId(new Long(custBaseInfoId));
						BeanUtils.updateObjectSetOperateInfo(tradeCustInfo, loginInfo);
						tradeCustInfoMapper.updateByPrimaryKey(tradeCustInfo);
						resultInfo.setObj(tradeCustInfo);
						// 更新关联信息
						updateTradeCustRelaInfo(tradeInfo, tradeCustInfo, loginInfo);
						break;
					}
				}
				if (!isSave) {
					// 没有保存过，先把之前跟该交易关联的客户删除
					if(!tradeCustInfoList.isEmpty()){
						for (TradeCustInfo tradeCustInfo : tradeCustInfoList){
							tradeCustInfo.setRcState(Constants.DELETE_RECORD);
							tradeCustInfoMapper.updateByPrimaryKey(tradeCustInfo);
							// 删除账户关联信息
							deleteTradeCustAccRela(tradeCustInfo, loginInfo);
							//删除地址关联信息
							deleteTradeCustAddressRela(tradeInfo, tradeCustInfo, loginInfo);
							// 删除该交易客户所有已关联的资产关联信息
							deleteTradeCustAssetRela(tradeCustInfo, loginInfo);
							//删除车辆关联信息
							deleteTradeCustCarRela(tradeCustInfo, loginInfo);
							//删除该交易客户所有已关联的联系方式关联信息
							deleteTradeCustContactRela(tradeInfo, tradeCustInfo, loginInfo);
							//删除该交易客户所有已关联的家庭成员关联信息
							deleteTradeCustFamilyRela(tradeCustInfo, loginInfo);
							//删除该交易客户所有已关联的兴趣爱好关联信息
							deleteTradeCustHobbyRela(tradeCustInfo, loginInfo);
							//删除该交易客户所有已关联的房产关联信息
							deleteTradeCustHouseRela(tradeCustInfo, loginInfo);
							//删除该交易客户所有已关联的投资关联信息
							deleteTradeCustInvestRela(tradeCustInfo, loginInfo);
							//删除该交易客户所有已关联的收入来源关联信息
							deleteTradeCustIncomeRela(tradeCustInfo, loginInfo);
							//删除该交易客户所有已关联的其它关联信息
							deleteTradeCustOthRela(tradeCustInfo, loginInfo);
						}
					}
					//之前客户删除后，再插入新客户	
					TradeCustInfo tradeCustInfo = new TradeCustInfo();
					Long tradeCustInfoIdDecimal = commonService.getSeqValByName("SEQ_T_TRADE_CUST_INFO");
					tradeCustInfo.setTradeCustInfoId(tradeCustInfoIdDecimal);
					tradeCustInfo.setCustBaseInfoId(new Long(custBaseInfoId));
					tradeCustInfo.setTradeInfoId(tradeInfo.getTradeInfoId());
					BeanUtils.insertObjectSetOperateInfo(tradeCustInfo, loginInfo);
					tradeCustInfoMapper.insert(tradeCustInfo);
					resultInfo.setObj(tradeCustInfo);
					updateTradeCustRelaInfo(tradeInfo, tradeCustInfo, loginInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		resultInfo.setSuccess(true);
		resultInfo.setMsg("交易客户信息保存成功!");
		return resultInfo;
	}

	@Transactional
	private boolean updateTradeCustRelaInfo(TradeInfo tradeInfo, TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		dealTradeCustAccRela(tradeCustInfo, loginInfo);
		dealTradeCustAddressRela(tradeInfo, tradeCustInfo, loginInfo);
		dealTradeCustAssetRela(tradeCustInfo, loginInfo);
		dealTradeCustCarRela(tradeCustInfo, loginInfo);
		dealTradeCustContactRela(tradeInfo, tradeCustInfo, loginInfo);
		dealTradeCustFamilyRela(tradeCustInfo, loginInfo);
		dealTradeCustHobbyRela(tradeCustInfo, loginInfo);
		dealTradeCustHouseRela(tradeCustInfo, loginInfo);
		dealTradeCustInvestRela(tradeCustInfo, loginInfo);
		dealTradeCustIncomeRela(tradeCustInfo, loginInfo);
		dealTradeCustOthRela(tradeCustInfo, loginInfo);
		return true;
	}

	@Transactional
	private boolean deleteTradeCustRelaInfo(TradeInfo tradeInfo, TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		deleteTradeCustAccRela(tradeCustInfo, loginInfo);
		deleteTradeCustAddressRela(tradeInfo, tradeCustInfo, loginInfo);
		deleteTradeCustAssetRela(tradeCustInfo, loginInfo);
		deleteTradeCustCarRela(tradeCustInfo, loginInfo);
		deleteTradeCustContactRela(tradeInfo, tradeCustInfo, loginInfo);
		deleteTradeCustFamilyRela(tradeCustInfo, loginInfo);
		deleteTradeCustHobbyRela(tradeCustInfo, loginInfo);
		deleteTradeCustHouseRela(tradeCustInfo, loginInfo);
		deleteTradeCustInvestRela(tradeCustInfo, loginInfo);
		deleteTradeCustIncomeRela(tradeCustInfo, loginInfo);
		deleteTradeCustOthRela(tradeCustInfo, loginInfo);
		return true;
	}

	private boolean dealTradeCustAccRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有有效的账户记录信息
		CustAccInfoExample custAccInfoExample = new CustAccInfoExample();
		CustAccInfoExample.Criteria criteria = custAccInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustAccInfo> custAccInfoList = custAccInfoMapper.selectByExample(custAccInfoExample);
		// 2.删除该交易客户所有已关联的账户关联信息
		deleteTradeCustAccRela(tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustAccInfo custAccInfo : custAccInfoList) {
			TradeCustAccRela tradeCustAccRela = new TradeCustAccRela();
			Long tradeCustAccRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_ACC_RELA");
			tradeCustAccRela.setTradeCustAccRelaId(tradeCustAccRelaId);
			tradeCustAccRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustAccRela.setCustAccInfoId(custAccInfo.getCustAccInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustAccRela, loginInfo);
			tradeCustAccRelaMapper.insert(tradeCustAccRela);
		}
		return true;
	}

	private boolean deleteTradeCustAccRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的账户关联信息
		TradeCustAccRelaExample tradeCustAccRelaExample = new TradeCustAccRelaExample();
		TradeCustAccRelaExample.Criteria criteria2 = tradeCustAccRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustAccRela> tradeCustAccRelaList = tradeCustAccRelaMapper.selectByExample(tradeCustAccRelaExample);
		// 2.删除该交易客户所有已关联的账户关联信息
		for (TradeCustAccRela tradeCustAccRela : tradeCustAccRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustAccRela, loginInfo);
			tradeCustAccRelaMapper.updateByPrimaryKey(tradeCustAccRela);
		}
		return true;
	}

	private boolean dealTradeCustAddressRela(TradeInfo tradeInfo, TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有只属于交易财富顾问的有效的地址记录信息
		CustAddressInfoExample custAddressInfoExample = new CustAddressInfoExample();
		CustAddressInfoExample.Criteria criteria = custAddressInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId()).andAgentIdEqualTo(tradeInfo.getAgentId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustAddressInfo> custAddressInfoList = custAddressInfoMapper.selectByExample(custAddressInfoExample);
		// 2.删除该交易客户所有已关联的地址关联信息
		deleteTradeCustAddressRela(tradeInfo, tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustAddressInfo custAddressInfo : custAddressInfoList) {
			TradeCustAddressRela tradeCustAddressRela = new TradeCustAddressRela();
			Long tradeCustAddressRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_ADDRESS_RELA");
			tradeCustAddressRela.setTradeCustAddressRelaId(tradeCustAddressRelaId);
			tradeCustAddressRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustAddressRela.setCustAddressInfoId(custAddressInfo.getCustAddressInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustAddressRela, loginInfo);
			tradeCustAddressRelaMapper.insert(tradeCustAddressRela);
		}
		return true;
	}

	private boolean deleteTradeCustAddressRela(TradeInfo tradeInfo, TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的地址关联信息
		TradeCustAddressRelaExample tradeCustAddressRelaExample = new TradeCustAddressRelaExample();
		TradeCustAddressRelaExample.Criteria criteria2 = tradeCustAddressRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustAddressRela> tradeCustAddressRelaList = tradeCustAddressRelaMapper
				.selectByExample(tradeCustAddressRelaExample);
		// 2.删除该交易客户所有已关联的地址关联信息
		for (TradeCustAddressRela tradeCustAddressRela : tradeCustAddressRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustAddressRela, loginInfo);
			tradeCustAddressRelaMapper.updateByPrimaryKey(tradeCustAddressRela);
		}
		return true;
	}

	private boolean dealTradeCustAssetRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有有效的资产记录信息
		CustAssetInfoExample custAssetInfoExample = new CustAssetInfoExample();
		CustAssetInfoExample.Criteria criteria = custAssetInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustAssetInfo> custAssetInfoList = custAssetInfoMapper.selectByExample(custAssetInfoExample);
		// 2.删除该交易客户所有已关联的资产关联信息
		deleteTradeCustAssetRela(tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustAssetInfo custAssetInfo : custAssetInfoList) {
			TradeCustAssetRela tradeCustAssetRela = new TradeCustAssetRela();
			Long tradeCustASSETRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_ASSET_RELA");
			tradeCustAssetRela.setTradeCustAssetRelaId(tradeCustASSETRelaId);
			tradeCustAssetRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustAssetRela.setCustAssetInfoId(custAssetInfo.getCustAssetInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustAssetRela, loginInfo);
			tradeCustAssetRelaMapper.insert(tradeCustAssetRela);
		}
		return true;
	}

	private boolean deleteTradeCustAssetRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 2.获取该交易客户所有已关联的资产关联信息
		TradeCustAssetRelaExample tradeCustAssetRelaExample = new TradeCustAssetRelaExample();
		TradeCustAssetRelaExample.Criteria criteria2 = tradeCustAssetRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustAssetRela> tradeCustAssetRelaList = tradeCustAssetRelaMapper
				.selectByExample(tradeCustAssetRelaExample);
		// 3.删除该交易客户所有已关联的资产关联信息
		for (TradeCustAssetRela tradeCustAssetRela : tradeCustAssetRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustAssetRela, loginInfo);
			tradeCustAssetRelaMapper.updateByPrimaryKey(tradeCustAssetRela);
		}
		return true;
	}

	private boolean dealTradeCustCarRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有有效的车辆记录信息
		CustCarInfoExample custCarInfoExample = new CustCarInfoExample();
		CustCarInfoExample.Criteria criteria = custCarInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustCarInfo> custCarInfoList = custCarInfoMapper.selectByExample(custCarInfoExample);
		// 2.删除该交易客户所有已关联的车辆关联信息
		deleteTradeCustCarRela(tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustCarInfo custCarInfo : custCarInfoList) {
			TradeCustCarRela tradeCustCarRela = new TradeCustCarRela();
			Long tradeCustCarRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_CAR_RELA");
			tradeCustCarRela.setTradeCustCarRelaId(tradeCustCarRelaId);
			tradeCustCarRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustCarRela.setCustCarInfoId(custCarInfo.getCustCarInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustCarRela, loginInfo);
			tradeCustCarRelaMapper.insert(tradeCustCarRela);
		}
		return true;
	}

	private boolean deleteTradeCustCarRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的车辆关联信息
		TradeCustCarRelaExample tradeCustCarRelaExample = new TradeCustCarRelaExample();
		TradeCustCarRelaExample.Criteria criteria2 = tradeCustCarRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustCarRela> tradeCustCarRelaList = tradeCustCarRelaMapper.selectByExample(tradeCustCarRelaExample);
		// 2.删除该交易客户所有已关联的车辆关联信息
		for (TradeCustCarRela tradeCustCarRela : tradeCustCarRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustCarRela, loginInfo);
			tradeCustCarRelaMapper.updateByPrimaryKey(tradeCustCarRela);
		}
		return true;
	}

	private boolean dealTradeCustContactRela(TradeInfo tradeInfo, TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有只属于交易财富顾问的有效的联系方式记录信息
		CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
		CustContactInfoExample.Criteria criteria = custContactInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId()).andAgentIdEqualTo(tradeInfo.getAgentId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustContactInfo> custContactInfoList = custContactInfoMapper.selectByExample(custContactInfoExample);
		// 2.删除该交易客户所有已关联的联系方式关联信息
		deleteTradeCustContactRela(tradeInfo, tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustContactInfo custContactInfo : custContactInfoList) {
			TradeCustContactRela tradeCustContactRela = new TradeCustContactRela();
			Long tradeCustContactRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_CONTACT_RELA");
			tradeCustContactRela.setTradeCustContactRelaId(tradeCustContactRelaId);
			tradeCustContactRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustContactRela.setCustContactInfoId(custContactInfo.getCustContactInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustContactRela, loginInfo);
			tradeCustContactRelaMapper.insert(tradeCustContactRela);
		}
		return true;
	}

	private boolean deleteTradeCustContactRela(TradeInfo tradeInfo, TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的联系方式关联信息
		TradeCustContactRelaExample tradeCustContactRelaExample = new TradeCustContactRelaExample();
		TradeCustContactRelaExample.Criteria criteria2 = tradeCustContactRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustContactRela> tradeCustContactRelaList = tradeCustContactRelaMapper
				.selectByExample(tradeCustContactRelaExample);
		// 2.删除该交易客户所有已关联的联系方式关联信息
		for (TradeCustContactRela tradeCustContactRela : tradeCustContactRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustContactRela, loginInfo);
			tradeCustContactRelaMapper.updateByPrimaryKey(tradeCustContactRela);
		}
		return true;
	}

	private boolean dealTradeCustFamilyRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有有效的家庭成员记录信息
		CustFamilyInfoExample custFamilyInfoExample = new CustFamilyInfoExample();
		CustFamilyInfoExample.Criteria criteria = custFamilyInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustFamilyInfo> custFamilyInfoList = custFamilyInfoMapper.selectByExample(custFamilyInfoExample);
		// 2.删除该交易客户所有已关联的家庭成员关联信息
		deleteTradeCustFamilyRela(tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustFamilyInfo custFamilyInfo : custFamilyInfoList) {
			TradeCustFamilyRela tradeCustFamilyRela = new TradeCustFamilyRela();
			Long tradeCustFamilyRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_FAMILY_RELA");
			tradeCustFamilyRela.setTradeCustFamilyRelaId(tradeCustFamilyRelaId);
			tradeCustFamilyRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustFamilyRela.setCustFamilyInfoId(custFamilyInfo.getCustFamilyInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustFamilyRela, loginInfo);
			tradeCustFamilyRelaMapper.insert(tradeCustFamilyRela);
		}
		return true;
	}

	private boolean deleteTradeCustFamilyRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的家庭成员关联信息
		TradeCustFamilyRelaExample tradeCustFamilyRelaExample = new TradeCustFamilyRelaExample();
		TradeCustFamilyRelaExample.Criteria criteria2 = tradeCustFamilyRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustFamilyRela> tradeCustFamilyRelaList = tradeCustFamilyRelaMapper
				.selectByExample(tradeCustFamilyRelaExample);
		// 2.删除该交易客户所有已关联的家庭成员关联信息
		for (TradeCustFamilyRela tradeCustFamilyRela : tradeCustFamilyRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustFamilyRela, loginInfo);
			tradeCustFamilyRelaMapper.updateByPrimaryKey(tradeCustFamilyRela);
		}
		return true;
	}

	private boolean dealTradeCustHobbyRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有有效的兴趣爱好记录信息
		CustHobbyInfoExample custHobbyInfoExample = new CustHobbyInfoExample();
		CustHobbyInfoExample.Criteria criteria = custHobbyInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustHobbyInfo> custHobbyInfoList = custHobbyInfoMapper.selectByExample(custHobbyInfoExample);
		// 2.删除该交易客户所有已关联的兴趣爱好关联信息
		deleteTradeCustHobbyRela(tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustHobbyInfo custHobbyInfo : custHobbyInfoList) {
			TradeCustHobbyRela tradeCustHobbyRela = new TradeCustHobbyRela();
			Long tradeCustHobbyRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_HOBBY_RELA");
			tradeCustHobbyRela.setTradeCustHobbyRelaId(tradeCustHobbyRelaId);
			tradeCustHobbyRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustHobbyRela.setCustHobbyInfoId(custHobbyInfo.getCustHobbyInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustHobbyRela, loginInfo);
			tradeCustHobbyRelaMapper.insert(tradeCustHobbyRela);
		}
		return true;
	}

	private boolean deleteTradeCustHobbyRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的兴趣爱好关联信息
		TradeCustHobbyRelaExample tradeCustHobbyRelaExample = new TradeCustHobbyRelaExample();
		TradeCustHobbyRelaExample.Criteria criteria2 = tradeCustHobbyRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustHobbyRela> tradeCustHobbyRelaList = tradeCustHobbyRelaMapper
				.selectByExample(tradeCustHobbyRelaExample);
		// 2.删除该交易客户所有已关联的兴趣爱好关联信息
		for (TradeCustHobbyRela tradeCustHobbyRela : tradeCustHobbyRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustHobbyRela, loginInfo);
			tradeCustHobbyRelaMapper.updateByPrimaryKey(tradeCustHobbyRela);
		}
		return true;
	}

	private boolean dealTradeCustHouseRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有有效的房产记录信息
		CustHouseInfoExample custHouseInfoExample = new CustHouseInfoExample();
		CustHouseInfoExample.Criteria criteria = custHouseInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustHouseInfo> custHouseInfoList = custHouseInfoMapper.selectByExample(custHouseInfoExample);
		// 2.删除该交易客户所有已关联的房产关联信息
		deleteTradeCustHouseRela(tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustHouseInfo custHouseInfo : custHouseInfoList) {
			TradeCustHouseRela tradeCustHouseRela = new TradeCustHouseRela();
			Long tradeCustHouseRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_HOUSE_RELA");
			tradeCustHouseRela.setTradeCustHouseRelaId(tradeCustHouseRelaId);
			tradeCustHouseRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustHouseRela.setCustHouseInfoId(custHouseInfo.getCustHouseInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustHouseRela, loginInfo);
			tradeCustHouseRelaMapper.insert(tradeCustHouseRela);
		}
		return true;
	}

	private boolean deleteTradeCustHouseRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的房产关联信息
		TradeCustHouseRelaExample tradeCustHouseRelaExample = new TradeCustHouseRelaExample();
		TradeCustHouseRelaExample.Criteria criteria2 = tradeCustHouseRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustHouseRela> tradeCustHouseRelaList = tradeCustHouseRelaMapper
				.selectByExample(tradeCustHouseRelaExample);
		// 2.删除该交易客户所有已关联的房产关联信息
		for (TradeCustHouseRela tradeCustHouseRela : tradeCustHouseRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustHouseRela, loginInfo);
			tradeCustHouseRelaMapper.updateByPrimaryKey(tradeCustHouseRela);
		}
		return true;
	}

	private boolean dealTradeCustInvestRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有有效的投资记录信息
		CustInvestInfoExample custInvestInfoExample = new CustInvestInfoExample();
		CustInvestInfoExample.Criteria criteria = custInvestInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustInvestInfo> custInvestInfoList = custInvestInfoMapper.selectByExample(custInvestInfoExample);
		// 2.删除该交易客户所有已关联的投资关联信息
		deleteTradeCustInvestRela(tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustInvestInfo custInvestInfo : custInvestInfoList) {
			TradeCustInvestRela tradeCustInvestRela = new TradeCustInvestRela();
			Long tradeCustInvestRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_INVEST_RELA");
			tradeCustInvestRela.setTradeCustInvestRelaId(tradeCustInvestRelaId);
			tradeCustInvestRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustInvestRela.setCustInvestInfoId(custInvestInfo.getCustInvestInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustInvestRela, loginInfo);
			tradeCustInvestRelaMapper.insert(tradeCustInvestRela);
		}
		return true;
	}

	private boolean deleteTradeCustInvestRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的投资关联信息
		TradeCustInvestRelaExample tradeCustInvestRelaExample = new TradeCustInvestRelaExample();
		TradeCustInvestRelaExample.Criteria criteria2 = tradeCustInvestRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustInvestRela> tradeCustInvestRelaList = tradeCustInvestRelaMapper
				.selectByExample(tradeCustInvestRelaExample);
		// 2.删除该交易客户所有已关联的投资关联信息
		for (TradeCustInvestRela tradeCustInvestRela : tradeCustInvestRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustInvestRela, loginInfo);
			tradeCustInvestRelaMapper.updateByPrimaryKey(tradeCustInvestRela);
		}
		return true;
	}

	private boolean dealTradeCustIncomeRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有有效的收入来源记录信息
		CustIncomeInfoExample custIncomeInfoExample = new CustIncomeInfoExample();
		CustIncomeInfoExample.Criteria criteria = custIncomeInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustIncomeInfo> custIncomeInfoList = custIncomeInfoMapper.selectByExample(custIncomeInfoExample);
		// 2.删除该交易客户所有已关联的收入来源关联信息
		deleteTradeCustIncomeRela(tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustIncomeInfo custIncomeInfo : custIncomeInfoList) {
			TradeCustIncomeRela tradeCustIncomeRela = new TradeCustIncomeRela();
			Long tradeCustIncomeRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_INCOME_RELA");
			tradeCustIncomeRela.setTradeCustIncomeRelaId(tradeCustIncomeRelaId);
			tradeCustIncomeRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustIncomeRela.setCustIncomeInfoId(custIncomeInfo.getCustIncomeInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustIncomeRela, loginInfo);
			tradeCustIncomeRelaMapper.insert(tradeCustIncomeRela);
		}
		return true;
	}

	private boolean deleteTradeCustIncomeRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的收入来源关联信息
		TradeCustIncomeRelaExample tradeCustIncomeRelaExample = new TradeCustIncomeRelaExample();
		TradeCustIncomeRelaExample.Criteria criteria2 = tradeCustIncomeRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustIncomeRela> tradeCustIncomeRelaList = tradeCustIncomeRelaMapper
				.selectByExample(tradeCustIncomeRelaExample);
		// 2.删除该交易客户所有已关联的收入来源关联信息
		for (TradeCustIncomeRela tradeCustIncomeRela : tradeCustIncomeRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustIncomeRela, loginInfo);
			tradeCustIncomeRelaMapper.updateByPrimaryKey(tradeCustIncomeRela);
		}
		return true;
	}

	private boolean dealTradeCustOthRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该客户所有有效的其它记录信息
		CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
		CustOthInfoExample.Criteria criteria = custOthInfoExample.createCriteria();
		criteria.andCustBaseInfoIdEqualTo(tradeCustInfo.getCustBaseInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustOthInfo> custOthInfoList = custOthInfoMapper.selectByExample(custOthInfoExample);
		// 2.删除该交易客户所有已关联的其它关联信息
		deleteTradeCustOthRela(tradeCustInfo, loginInfo);
		// 3.插入新的关联记录
		for (CustOthInfo custOthInfo : custOthInfoList) {
			TradeCustOthRela tradeCustOthRela = new TradeCustOthRela();
			Long tradeCustOthRelaId = commonService.getSeqValByName("SEQ_T_TRADE_CUST_OTH_RELA");
			tradeCustOthRela.setTradeCustOthRelaId(tradeCustOthRelaId);
			tradeCustOthRela.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
			tradeCustOthRela.setCustOthInfoId(custOthInfo.getCustOthInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustOthRela, loginInfo);
			tradeCustOthRelaMapper.insert(tradeCustOthRela);
		}
		return true;
	}

	private boolean deleteTradeCustOthRela(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		// 1.获取该交易客户所有已关联的其它关联信息
		TradeCustOthRelaExample tradeCustOthRelaExample = new TradeCustOthRelaExample();
		TradeCustOthRelaExample.Criteria criteria2 = tradeCustOthRelaExample.createCriteria();
		criteria2.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustOthRela> tradeCustOthRelaList = tradeCustOthRelaMapper.selectByExample(tradeCustOthRelaExample);
		// 2.删除该交易客户所有已关联的其它关联信息
		for (TradeCustOthRela tradeCustOthRela : tradeCustOthRelaList) {
			BeanUtils.deleteObjectSetOperateInfo(tradeCustOthRela, loginInfo);
			tradeCustOthRelaMapper.updateByPrimaryKey(tradeCustOthRela);
		}
		return true;
	}

	@Override
	public List<Map<String, String>> queryCustomInfoInputGrid(HashMap paramMap) {
		List<Map<String, String>> resultMap = tradeServiceMapper.queryCustomInfoInputGrid(paramMap);
		return resultMap;
	}
	/**
	 * 根据客户编号来获取客户信息
	 */
	@Override
	public List<Map<String, String>> queryCustomInfoInputGridById(HashMap paramMap) {
		List<Map<String, String>> resultMap = tradeServiceMapper.queryCustomInfoInputGridById(paramMap);
		return resultMap;
	}

	@Override
	public ResultInfo delTradeCusInfo(Map<String, String> tradeCustInfoMap, TradeInfo tradeInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 1.获取交易详细信息，并删除客户交易账户信息，默认地址信息
			tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeInfo.getTradeInfoId());
			// tradeInfo.setCustAccInfoId(null);
			// tradeInfo.setCustAddressInfoId(null);
			BeanUtils.updateObjectSetOperateInfo(tradeInfo, loginInfo);
			tradeInfoMapper.updateByPrimaryKey(tradeInfo);
			// 2.删除交易客户信息及交易客户关联信息
			String tradeCustInfoId = tradeCustInfoMap.get("tradeCustInfoId");
			TradeCustInfo tradeCustInfo = tradeCustInfoMapper.selectByPrimaryKey(new Long(tradeCustInfoId));
			BeanUtils.deleteObjectSetOperateInfo(tradeCustInfo, loginInfo);
			// 逻辑删除客户
			tradeCustInfoMapper.updateByPrimaryKey(tradeCustInfo);
			// 删除客户默认账户信息
			deleteBankInfo(tradeCustInfo, loginInfo);
			// 删除客户默认地址
			deleteAddressInfo(tradeCustInfo, loginInfo);
			// 逻辑删除交易客户关联信息
			deleteTradeCustRelaInfo(tradeInfo, tradeCustInfo, loginInfo);
			// 3.删除客户角色信息
			TradeCustRoleInfoExample tradeCustRoleInfoExample = new TradeCustRoleInfoExample();
			TradeCustRoleInfoExample.Criteria criteria = tradeCustRoleInfoExample.createCriteria();
			criteria.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<TradeCustRoleInfo> tradeCustRoleInfoList = tradeCustRoleInfoMapper
					.selectByExample(tradeCustRoleInfoExample);
			for (TradeCustRoleInfo tradeCustRoleInfo : tradeCustRoleInfoList) {
				resultInfo = delRoleInfo(tradeInfo, tradeCustRoleInfo, loginInfo);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除客户信息出错");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("交易客户信息删除成功!");
		return resultInfo;
	}

	@Override
	public List<Map<String, String>> queryTradeProductFactor(HashMap paramMap) {
		List<Map<String, String>> resultMap = tradeServiceMapper.queryTradeProductFactor(paramMap);
		return resultMap;
	}

	@Override
	public List<Map<String, String>> queryTradeBankInfo(Map paramMap) {
		List<Map<String, String>> custAccInfoList = null;
		try {
			custAccInfoList = tradeServiceMapper.queryTradeBankInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custAccInfoList;
	}

	@Override
	public List<Map<String, String>> searchTradeBankInfo(HashMap paramMap) {
		List<Map<String, String>> custAccInfoList = null;
		try {
			custAccInfoList = tradeServiceMapper.searchTradeBankInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custAccInfoList;
	}
	
	@Override
	public List<Map> getAllTBankInfoByCustId(HashMap paramMap) {
		List<Map> custAccInfoList = null;
		try {
			custAccInfoList=modifyCustomerServiceMapper.custAccInfoMapList(paramMap);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custAccInfoList;
	}

	@Override
	public HashMap saveTradeBankInfo(HashMap paramMap, LoginInfo loginInfo) {
		String tradeId = (String) paramMap.get("tradeId");
		String rowData = (String) paramMap.get("rowData");
		BigDecimal tradeIdDecimal = new BigDecimal(tradeId);
		List<Map> custAccInfoList = new ArrayList<Map>();
		custAccInfoList = JSON.parseArray(rowData, Map.class);
		if (custAccInfoList != null && custAccInfoList.size() > 0) {
			for (int i = 0; i < custAccInfoList.size(); i++) {
				String tradeCustInfoId =custAccInfoList.get(i).get("tradeCustInfoId").toString();
				String custAccInfoId =custAccInfoList.get(i).get("custAccInfoId").toString();
				TradeCustAccRelaExample tradeCustAccRelaExample = new TradeCustAccRelaExample();
				TradeCustAccRelaExample.Criteria criteria = tradeCustAccRelaExample.createCriteria();
				criteria.andTradeCustInfoIdEqualTo(new Long(tradeCustInfoId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeCustAccRela> tradeCustAccRelaList = tradeCustAccRelaMapper
						.selectByExample(tradeCustAccRelaExample);
				for (TradeCustAccRela tradeCustAccRela : tradeCustAccRelaList) {
					// 设置默认账户
					if (tradeCustAccRela.getCustAccInfoId().compareTo(new Long(custAccInfoId)) == 0) {
						tradeCustAccRela.setTradeAccFlag("1");// 默认账户
					} else {
						tradeCustAccRela.setTradeAccFlag(null);// 取消其它的默认账户
					}
					BeanUtils.updateObjectSetOperateInfo(tradeCustAccRela, loginInfo);
					tradeCustAccRelaMapper.updateByPrimaryKey(tradeCustAccRela);
				}
			}
		}
		HashMap resultMap = new HashMap();
		resultMap.put("success", "true");
		resultMap.put("msg", "交易账户信息保存成功！");
		return resultMap;
	}

	private boolean deleteBankInfo(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		TradeCustAccRelaExample tradeCustAccRelaExample = new TradeCustAccRelaExample();
		TradeCustAccRelaExample.Criteria criteria = tradeCustAccRelaExample.createCriteria();
		criteria.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustAccRela> tradeCustAccRelaList = tradeCustAccRelaMapper.selectByExample(tradeCustAccRelaExample);
		for (TradeCustAccRela tradeCustAccRela : tradeCustAccRelaList) {
			tradeCustAccRela.setTradeAccFlag(null);// 默认账户
			BeanUtils.updateObjectSetOperateInfo(tradeCustAccRela, loginInfo);
			tradeCustAccRelaMapper.updateByPrimaryKey(tradeCustAccRela);
		}
		return true;
	}

	@Override
	public List<Map<String, String>> queryTradeAddressInfo(Map paramMap) {
		List<Map<String, String>> infoList = null;
		try {
			infoList = tradeServiceMapper.queryTradeAddressInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return infoList;
	}

	@Override
	public List<Map<String, String>> searchTradeAddressInfo(HashMap paramMap) {
		List<Map<String, String>> infoList = null;
		try {
			infoList = tradeServiceMapper.searchTradeAddressInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return infoList;
	}
	
	@Override
	public List<Map<String, String>> getTradeAddressInfo(HashMap paramMap) {
		List<Map<String, String>> infoList = null;
		try {
			infoList = tradeServiceMapper.getTradeAddressInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return infoList;
	}

	@Override
	public HashMap saveTradeAddressInfo(HashMap paramMap, LoginInfo loginInfo) {
		String tradeId = (String) paramMap.get("tradeId");
		String rowData = (String) paramMap.get("rowData");
		BigDecimal tradeIdDecimal = new BigDecimal(tradeId);
		List<Map> custAddressInfoList = new ArrayList<Map>();
		custAddressInfoList = JSON.parseArray(rowData, Map.class);
		if (custAddressInfoList != null && custAddressInfoList.size() > 0) {
			for (int i = 0; i < custAddressInfoList.size(); i++) {
				String custAddressInfoId =custAddressInfoList.get(i).get("custAddressInfoId").toString();
				String tradeCustInfoId = custAddressInfoList.get(i).get("tradeCustInfoId").toString();
				TradeCustAddressRelaExample tradeCustAddressRelaExample = new TradeCustAddressRelaExample();
				TradeCustAddressRelaExample.Criteria criteria = tradeCustAddressRelaExample.createCriteria();
				criteria.andTradeCustInfoIdEqualTo(new Long(tradeCustInfoId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeCustAddressRela> tradeCustAddressRelaList = tradeCustAddressRelaMapper
						.selectByExample(tradeCustAddressRelaExample);
				for (TradeCustAddressRela tradeCustAddressRela : tradeCustAddressRelaList) {
					// 设置默认地址
					if (tradeCustAddressRela.getCustAddressInfoId().compareTo(new Long(custAddressInfoId)) == 0) {
						tradeCustAddressRela.setTradeAddressFlag("1");// 默认地址
					} else {
						tradeCustAddressRela.setTradeAddressFlag(null);// 取消其它的默认地址
					}
					BeanUtils.updateObjectSetOperateInfo(tradeCustAddressRela, loginInfo);
					tradeCustAddressRelaMapper.updateByPrimaryKey(tradeCustAddressRela);
				}
			}
		}
		HashMap resultMap = new HashMap();
		resultMap.put("success", "true");
		resultMap.put("msg", "交易地址信息保存成功！");
		return resultMap;
	}

	private boolean deleteAddressInfo(TradeCustInfo tradeCustInfo, LoginInfo loginInfo) {
		TradeCustAddressRelaExample tradeCustAddressRelaExample = new TradeCustAddressRelaExample();
		TradeCustAddressRelaExample.Criteria criteria = tradeCustAddressRelaExample.createCriteria();
		criteria.andTradeCustInfoIdEqualTo(tradeCustInfo.getTradeCustInfoId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustAddressRela> tradeCustAddressRelaList = tradeCustAddressRelaMapper
				.selectByExample(tradeCustAddressRelaExample);
		for (TradeCustAddressRela tradeCustAddressRela : tradeCustAddressRelaList) {
			tradeCustAddressRela.setTradeAddressFlag(null);// 默认账户
			BeanUtils.updateObjectSetOperateInfo(tradeCustAddressRela, loginInfo);
			tradeCustAddressRelaMapper.updateByPrimaryKey(tradeCustAddressRela);
		}
		return true;
	}

	@Override
	public List<Map<String, String>> queryDefCode(HashMap paramMap) {
		List<Map<String, String>> infoList = null;
		try {
			String factorValueCode = (String) paramMap.get("factorValueCode");
			String codeType = (String) paramMap.get("codeType");
			List<String> codeList = new ArrayList<String>();
			factorValueCode = factorValueCode.substring(1);
			String[] string = factorValueCode.split(",");
			String param = "";
			for (int i = 0; i < string.length; i++) {
				param += ",'" + string[i] + "'";
				codeList.add(string[i]);
			}
			// paramMap.put("factorValueCode", param.substring(1));
			// infoList = tradeServiceMapper.queryDefCode(paramMap);

			DefCodeExample defCodeExample = new DefCodeExample();
			defCodeExample.setOrderByClause("code");
			defCodeExample.createCriteria().andCodeTypeEqualTo(codeType).andCodeIn(codeList);
			List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
			List<Map<String, String>> codeMapList = new ArrayList<Map<String, String>>();
			for (DefCode defCode : defCodeList) {
				Map<String, String> codeMap = new HashMap<String, String>();
				String code = defCode.getCode();
				String codeName = defCode.getCodeName();
				codeMap.put("code", code);
				codeMap.put("codeName", code + "-" + codeName);
				codeMap.put("name", codeName);
				codeMapList.add(codeMap);
			}
			return codeMapList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

	@Override
	public List<Map<String, String>> queryOpenDateByProductId(Map paramMap) {
		List<Map<String, String>> openDateList = tradeServiceMapper.queryOpenDateByProductId(paramMap);
		return openDateList;
	}

	@Override
	public List<Map<String, String>> queryClosedPeriodsByProductId(Map paramMap) {
		List list = new ArrayList();
		if (paramMap.get("productId") != null) {
			PDFactorExample pdFactorExample = new PDFactorExample();
			pdFactorExample.createCriteria().andFactorCodeEqualTo("closedPeriods").andRcStateEqualTo("E")
					.andPdIdEqualTo(new Long(paramMap.get("productId").toString()));
			List pdFactorList = pdFactorMapper.selectByExample(pdFactorExample);

			if (pdFactorList.size() > 0) {
				PDFactor pdFactor = (PDFactor) pdFactorList.get(0);
				if (pdFactor.getFactorValue() != null) {
					String[] a = pdFactor.getFactorValue().substring(1).split(",");
					for (int i = 0; i < a.length; i++) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("codeName", a[i]);
						map.put("code", a[i]);
						list.add(map);
					}
				}
			}
		}

		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid getTradeDownload(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		// 1、获取总条数
		Integer total = tradeServiceMapper.tradeTotal(paramMap);
		List<Map<String, String>> resultList = tradeServiceMapper.getPrintServiceList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	public void downloadPrint(HttpServletRequest request, HttpServletResponse response, DefPrintInfo defPrintInfo,LoginInfo loginInfo) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			defPrintInfo = defPrintInfoMapper.selectByPrimaryKey(defPrintInfo.getPrintInfoId());

			String fileSavePath = defPrintInfo.getFileSavePath();
			String fileName = defPrintInfo.getPrintFileName();
			String downLoadPath = fileSavePath + "/" + fileName;
			String documentType = defPrintInfo.getDoucmentType();
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));

			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			/*if (documentType.equals("01")) {
				//获取用户登录信息
				DefUser userInfo = defUserMapper.selectByPrimaryKey(loginInfo.getUserId());
				String loginUserName = userInfo.getUserName();
				//将pdf文件先加水印然后输出
				setWatermark(bos,fileSavePath+fileName,"巨鲸，您的终身财富管理专家，财富顾问：" + loginUserName , 16);
			}*/
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//为下载的文件添加水印
	public static void setWatermark(BufferedOutputStream bos, String input,String waterMarkName, int permission)
			throws DocumentException, IOException {
			PdfReader reader = new PdfReader(input);
			PdfStamper stamper = new PdfStamper(reader, bos);
			stamper.setEncryption(null, null,PdfWriter.ALLOW_PRINTING,PdfWriter.STANDARD_ENCRYPTION_128);
			int total = reader.getNumberOfPages() + 1;
			PdfContentByte content;
			BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
			BaseFont.EMBEDDED);
			PdfGState gs = new PdfGState();
			for (int i = 1; i < total; i++) {
			content = stamper.getOverContent(i);//在内容上方加水印
			//content = stamper.getUnderContent(i);//在内容下方加水印
			gs.setFillOpacity(0.1f);
			// content.setGState(gs);
			content.beginText();
			content.setColorFill(Color.LIGHT_GRAY);
			content.setFontAndSize(base, 15);
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 300,430, 15);
			//content.setColorFill(Color.BLACK);
			//content.setFontAndSize(base, 8);
			//content.showTextAligned(Element.ALIGN_CENTER, "下载时间："+ waterMarkName + "", 300, 10, 0);
			content.endText();


			}
			stamper.close();
			}
	@Override
	public ResultInfo queryWealthProductDetailInfo(String productId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			PDWealthExample pdWealthExample = new PDWealthExample();
			PDWealthExample.Criteria criteria = pdWealthExample.createCriteria();
			criteria.andProductIdEqualTo(new Long(productId)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			PDWealth pdWealth = pdWealthMapper.selectByExample(pdWealthExample).get(0);
			Map pdWealthMap = JsonUtils.objectToMap(pdWealth);
			resultInfo.setSuccess(true);
			resultInfo.setObj(pdWealthMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * @param tradeInfo
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	public ResultInfo cancelTradeInfo(TradeInfo tradeInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			TradeInfo existTradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeInfo.getTradeInfoId());
			String tradeStatus = existTradeInfo.getTradeStaus();
			if (tradeStatus != null && !"".equals(tradeStatus) && !"01".equals(tradeStatus) && !"02".equals(tradeStatus)
					&& !"03".equals(tradeStatus) && !"15".equals(tradeStatus)) {
				DefCodeKey defCodeKey = new DefCodeKey();
				defCodeKey.setCodeType("tradeStatus");
				defCodeKey.setCode(tradeStatus);
				DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
				resultInfo.setSuccess(false);
				if (defCode != null) {
					resultInfo.setMsg("交易状态为(" + defCode.getCodeName() + ")，交易不可撤销！");
				} else {
					resultInfo.setMsg("当前交易状态下，次交易不能撤销！");
				}
			} else {
				BeanUtils.updateObjectSetOperateInfo(existTradeInfo, loginInfo);
				existTradeInfo.setTradeStaus("09");
				tradeInfoMapper.updateByPrimaryKey(existTradeInfo);
				//交易撤销成功,释放原来合同号为未用 
				Long tradeInfoId = existTradeInfo.getTradeInfoId();
				String contrctNum = existTradeInfo.getTradeInfoNo();
				if (null != contrctNum && !"".equals(contrctNum)) {
					PDContractDetailExample pdContractDetailExample = new PDContractDetailExample();
					PDContractDetailExample.Criteria  criteria =  pdContractDetailExample.createCriteria();	
					criteria.andTradeInfoIdEqualTo(tradeInfoId).andContractNumberEqualTo(contrctNum).andContractStatusEqualTo("01").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<PDContractDetail> pdContractDetailsList = pdContractDetailMapper.selectByExample(pdContractDetailExample);
					if (pdContractDetailsList != null && pdContractDetailsList.size() > 0) {
						PDContractDetail pdContractDetail = pdContractDetailsList.get(0);
						pdContractDetail.setContractStatus("02");//释放合同号未用
						pdContractDetail.setTradeInfoId(null);
						BeanUtils.updateObjectSetOperateInfo(pdContractDetail, loginInfo);
						pdContractDetailMapper.updateByPrimaryKey(pdContractDetail);
					}
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("交易撤销成功！");
			}
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("交易撤销出现异常，请联系IT人员！");
		}
		return resultInfo;
	}

	public ResultInfo getTradeStatusInfo(String tradeInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(new Long(tradeInfoId));
			DefCodeKey defCodeKey = new DefCodeKey();
			defCodeKey.setCodeType("tradeStatus");
			defCodeKey.setCode(tradeInfo.getTradeStaus());
			DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
			resultInfo.setSuccess(true);
			resultInfo.setObj(defCode);
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取交易状态出现异常，请联系IT人员！");
		}
		return resultInfo;
	}

	/**
	 * 更新客户所属财富顾问信息
	 * 
	 * @param tradeInfo
	 * @return
	 */
	@Transactional
	private ResultInfo updateCustBelongAgentInfo(TradeInfo tradeInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String tradeType = tradeInfo.getTradeType();
		CustBaseInfo custBaseInfo = new CustBaseInfo();
		// 根据交易类型获取不同客户信息，1-财富交易，2-保险交易
		if ("1".equals(tradeType)) {
			TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
			TradeCustInfoExample.Criteria criteria = tradeCustInfoExample.createCriteria();
			criteria.andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<TradeCustInfo> tradeCustInfoList = tradeCustInfoMapper.selectByExample(tradeCustInfoExample);
			TradeCustInfo tradeCustInfo = new TradeCustInfo();
			if (tradeCustInfoList != null && tradeCustInfoList.size() > 0) {
				tradeCustInfo = tradeCustInfoList.get(0);
				custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(tradeCustInfo.getCustBaseInfoId());
			}
		} else if ("2".equals(tradeType)) {
			Map paramMap = new HashMap();
			paramMap.put("tradeInfoId", tradeInfo.getTradeInfoId());
			List<Map> tradeAppntCustInfoList = tradeServiceMapper.getTradeAppntCustInfo(paramMap);
			Map tradeAppntCustMap = new HashMap();
			if (tradeAppntCustInfoList != null && tradeAppntCustInfoList.size() > 0) {
				tradeAppntCustMap = tradeAppntCustInfoList.get(0);
				custBaseInfo = custBaseInfoMapper
						.selectByPrimaryKey(new Long((String) tradeAppntCustMap.get("custBaseInfoId")));
			}
		}
		if (custBaseInfo == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到交易客户信息");
			return resultInfo;
		}
		Long custAgentId = custBaseInfo.getAgentId();
		if (custAgentId == null) {
			custBaseInfo.setAgentId(tradeInfo.getAgentId());
			custBaseInfo.setModifyUserId(tradeInfo.getModifyUserId());
			custBaseInfo.setModifyDate(tradeInfo.getModifyDate());
			custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
			resultInfo.setSuccess(true);
		} else {
			if (custAgentId.compareTo(tradeInfo.getAgentId()) != 0) {
				Agent custAgentInfo = agentMapper.selectByPrimaryKey(custAgentId);
				resultInfo.setMsg("该客户已经归属理财经理(" + custAgentInfo.getAgentName() + "),您不能再对此客户进行交易！");
				resultInfo.setSuccess(false);
			} else {
				resultInfo.setSuccess(true);
			}
		}
		return resultInfo;
	}

	/**
	 * 基于接口校验客户手机号是否已经存在
	 * 
	 * @param mobile
	 * @return
	 */
	private ResultInfo verifyCustomerMobile(String mobile, String CustomerNo) {
		ResultInfo resultInfo = new ResultInfo();
		// 客户号为空
		if (CustomerNo != null) {

			CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
			custContactInfoExample.createCriteria().andRcStateEqualTo("E").andMobileEqualTo(mobile);
			List<CustContactInfo> listCustContactInfos = custContactInfoMapper.selectByExample(custContactInfoExample);
			if (listCustContactInfos != null && listCustContactInfos.size() > 0) {
				for (CustContactInfo custContactInfo : listCustContactInfos) {

					CustBaseInfoExample comBaseInfoExample = new CustBaseInfoExample();
					comBaseInfoExample.createCriteria().andRcStateEqualTo("E").andCustomerNoNotEqualTo(CustomerNo)
							.andCustBaseInfoIdEqualTo(custContactInfo.getCustBaseInfoId());
					List<CustBaseInfo> listcustBaseInfo = custBaseInfoMapper.selectByExample(comBaseInfoExample);
					if (listcustBaseInfo != null && listcustBaseInfo.size() > 0) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该手机号已经被其他客户使用，请重新输入手机号！");
						return resultInfo;
					}

				}

			}

		}
		resultInfo.setSuccess(true);

		return resultInfo;
	}
	/**
	 * 基于接口保存客户联系信息
	 * @param custBaseInfo
	 * @param custContactInfo
	 * @param agent
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustContactInfo(CustBaseInfo custBaseInfo, CustContactInfo custContactInfo, Agent agent,
			LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();

		try {
			// 客户手机号信息进行校验
			if (custContactInfo.getMobile() != null && custBaseInfo != null) {
				resultInfo = verifyCustomerMobile(custContactInfo.getMobile(), custBaseInfo.getCustomerNo());

				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
			if (agent != null && agent.getAgentId() != null) {

			} else {
				// 获取财富顾问信息
				resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
				agent = (Agent) resultInfo.getObj();
			}

			// 查询财富顾问是否保存过此客户信息
			CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
			CustContactInfoExample.Criteria criteria = custContactInfoExample.createCriteria();
			criteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId()).andAgentIdEqualTo(agent.getAgentId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);

			List<CustContactInfo> contactInfoList = custContactInfoMapper.selectByExample(custContactInfoExample);
			// 逻辑删除原有客户联系信息
			/*
			 * for (CustContactInfo existCustContactInfo:contactInfoList) {
			 * BeanUtils.deleteObjectSetOperateInfo(existCustContactInfo,
			 * loginInfo);
			 * custContactInfoMapper.updateByPrimaryKey(existCustContactInfo); }
			 */
			if (contactInfoList != null && contactInfoList.size() > 0) {
				CustContactInfo existCustContactInfo = contactInfoList.get(0);
				if (custContactInfo.equals(existCustContactInfo)) {
					custContactInfo.setCustContactInfoId(existCustContactInfo.getCustContactInfoId());
					custContactInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
					custContactInfo.setAgentId(agent.getAgentId());
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustContactInfo, custContactInfo, loginInfo);
					custContactInfoMapper.updateByPrimaryKey(custContactInfo);
				} else {
					// 生成客户联系信息主键，保存客户联系方式
					Long custContactInfoId = commonService.getSeqValByName("SEQ_T_CUST_CONTACT_INFO");
					custContactInfo.setCustContactInfoId(custContactInfoId);
					custContactInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
					custContactInfo.setAgentId(agent.getAgentId());
					// BeanUtils.insertObjectSetOperateInfo(custContactInfo,
					// loginInfo);
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustContactInfo, custContactInfo, loginInfo);
					custContactInfoMapper.updateByPrimaryKey(existCustContactInfo);
					custContactInfoMapper.insert(custContactInfo);
				}
			} else {
				// 生成客户联系信息主键，保存客户联系方式
				Long custContactInfoId = commonService.getSeqValByName("SEQ_T_CUST_CONTACT_INFO");
				custContactInfo.setCustContactInfoId(custContactInfoId);
				custContactInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
				custContactInfo.setAgentId(agent.getAgentId());
				BeanUtils.insertObjectSetOperateInfo(custContactInfo, loginInfo);
				custContactInfoMapper.insert(custContactInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户联系信息出错");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("保存客户联系信息成功");
		return resultInfo;
	}
	/**
	 * 基于接口保存客户地址信息
	 * @param custBaseInfo
	 * @param custAddressInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustAddressInfo(CustBaseInfo custBaseInfo, List<CustAddressInfo> custAddressInfoList,
			Agent agent, LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		if (custBaseInfo.getCustBaseInfoId() == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户基本信息为空，请先新增客户基本信息");
			return resultInfo;
		}
		try {
			if (agent != null && agent.getAgentId() != null) {

			} else {
				// 获取财富顾问信息
				resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
				agent = (Agent) resultInfo.getObj();
			}

			// 查询财富顾问之前是否保存过该客户地址信息
			CustAddressInfoExample custAddressInfoExample = new CustAddressInfoExample();
			CustAddressInfoExample.Criteria addressCriteria = custAddressInfoExample.createCriteria();
			addressCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAddressInfo> existCustAddressInfoList = custAddressInfoMapper
					.selectByExample(custAddressInfoExample);
			List<CustAddressInfo> delExistCustAddressInfoList = new ArrayList<CustAddressInfo>();
			delExistCustAddressInfoList.addAll(existCustAddressInfoList);
			// operate = "addCustInfo";

			if (custAddressInfoList != null && custAddressInfoList.size() > 0) {
				// 插入新的客户地址信息
				for (CustAddressInfo custAddressInfo : custAddressInfoList) {
					if (custAddressInfo.getCustAddressInfoId() == null) {
						Long custAddressId = commonService.getSeqValByName("SEQ_T_CUST_ADDRESS_INFO");
						custAddressInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
						custAddressInfo.setCustAddressInfoId(custAddressId);
						custAddressInfo.setAgentId(agent.getAgentId());
						BeanUtils.insertObjectSetOperateInfo(custAddressInfo, loginInfo);
						custAddressInfoMapper.insert(custAddressInfo);
					} else {
						for (CustAddressInfo existCustAddressInfo : existCustAddressInfoList) {
							if (custAddressInfo.getCustAddressInfoId()
									.compareTo(existCustAddressInfo.getCustAddressInfoId()) == 0) {
								if (custAddressInfo.equals(existCustAddressInfo)) {
									BeanUtils.updateObjectSetOperateInfo(existCustAddressInfo, loginInfo);
									custAddressInfoMapper.updateByPrimaryKey(existCustAddressInfo);
									delExistCustAddressInfoList.remove(existCustAddressInfo);
								} else {
									BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustAddressInfo, custAddressInfo,
											loginInfo);
									Long custAddressInfoId = commonService
											.getSeqValByName("SEQ_T_CUST_ADDRESS_INFO");
									custAddressInfo.setCustAddressInfoId(custAddressInfoId);
									custAddressInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
									custAddressInfo.setAgentId(agent.getAgentId());
									custAddressInfoMapper.insert(custAddressInfo);
									custAddressInfoMapper.updateByPrimaryKey(existCustAddressInfo);
									delExistCustAddressInfoList.remove(existCustAddressInfo);
								}
							}
						}
					}
				}
			}
			// 逻辑删除原有客户地址信息
			for (CustAddressInfo delExistCustAddressInfo : delExistCustAddressInfoList) {
				BeanUtils.deleteObjectSetOperateInfo(delExistCustAddressInfo, loginInfo);
				custAddressInfoMapper.updateByPrimaryKey(delExistCustAddressInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户地址信息出错");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("保存客户地址信息成功");
		return resultInfo;
	}

	/**
	 * 获取财富顾问信息
	 * 
	 * @param long1
	 * @return
	 */
	private ResultInfo getAgentInfoByUserId(Long long1) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			AgentExample agentExample = new AgentExample();
			AgentExample.Criteria agentCriteria = agentExample.createCriteria();
			agentCriteria.andUserIdEqualTo(long1).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			Agent agent = new Agent();
			if (agentList != null && agentList.size() > 0) {
				agent = agentList.get(0);
				resultInfo.setObj(agent);
				resultInfo.setSuccess(true);
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到财富顾问相关信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取财富顾问信息出错");
		}

		return resultInfo;
	}

	/**
	 * 插入交易信息
	 */
	@Override
	@Transactional
	public ResultInfo insertTradeInfo(Map xmleWealthContentMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			String productCode= xmleWealthContentMap.get("productId").toString();
			PDProductExample pdProductExample = new PDProductExample();
			PDProductExample.Criteria pdProductCriteria = pdProductExample.createCriteria();
			pdProductCriteria.andProductCodeEqualTo(productCode).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDProduct> pdProducts = pdProductMapper.selectByExample(pdProductExample);
			Long productId = pdProducts.get(0).getProductId();
			String agentCode = xmleWealthContentMap.get("agentId").toString();
			LoginInfo loginInfo = new LoginInfo();
			AgentExample agentExample = new AgentExample();
			AgentExample.Criteria agentCriteria = agentExample.createCriteria();
			agentCriteria.andAgentCodeEqualTo(agentCode).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			Long agentId = agentMapper.selectByExample(agentExample).get(0).getAgentId();
			Long userId = agentMapper.selectByExample(agentExample).get(0).getUserId();
			Long comId = agentMapper.selectByExample(agentExample).get(0).getComId();
			Long storeId = agentMapper.selectByExample(agentExample).get(0).getStoreId();
			loginInfo.setUserId(userId);
			loginInfo.setComId(comId);
			Agent agent = new Agent();
			if (agentId != null && !"".equals(agentId)) {
				agent.setAgentId(agentId);
			}
			String operate = "";
			String cardNo = "";
			// 插入客户基本信息
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			custBaseInfo.setCustomerNo(xmleWealthContentMap.get("customerId").toString());
			custBaseInfo.setChnName(xmleWealthContentMap.get("customerName").toString());
			custBaseInfo.setIdType("01");
			cardNo = xmleWealthContentMap.get("cardNo").toString();
			custBaseInfo.setIdNo(cardNo);
			custBaseInfo.setSex(StringUtils.getSex(cardNo));
			custBaseInfo.setBirthday(DateUtils.getDate1(StringUtils.getBirthday(cardNo)));
			custBaseInfo.setAgentId(agentId);
			resultInfo = customerService.saveCustomerBaseInfo(custBaseInfo, agentId.toString(), loginInfo);
			custBaseInfo = (CustBaseInfo) resultInfo.getObj();
			CustContactInfo custContactInfo = new CustContactInfo();
			custContactInfo.setEmail(xmleWealthContentMap.get("email").toString());
			custContactInfo.setMobile(xmleWealthContentMap.get("mobile").toString());
			saveCustContactInfo(custBaseInfo, custContactInfo, agent, loginInfo, operate);
			// 插入客户地址信息
			CustAddressInfo custAddressInfo = new CustAddressInfo();
			custAddressInfo.setEmail(xmleWealthContentMap.get("email").toString());
			custAddressInfo.setMobile(xmleWealthContentMap.get("mobile").toString());
			custAddressInfo.setAgentId(agentId);
			custAddressInfo.setStreet(xmleWealthContentMap.get("address").toString());
			custAddressInfo.setAddressType("01");
			List<CustAddressInfo> custAddressInfoList = new ArrayList<CustAddressInfo>();
			custAddressInfoList.add(custAddressInfo);
			saveCustAddressInfo(custBaseInfo, custAddressInfoList, agent, loginInfo, operate);
			// 插入交易信息
			String orderNo = xmleWealthContentMap.get("orderNo").toString();
			TradeInfo tradeInfo = new TradeInfo();
			tradeInfo.setOrderNo(orderNo);
			tradeInfo.setAgentId(agentId);
			tradeInfo.setTradeDate(DateUtils.getDate(xmleWealthContentMap.get("orderDate").toString()));
			tradeInfo.setTradeStaus("10");
			tradeInfo.setTradeType("1");
			tradeInfo.setCompanyId(comId);
			tradeInfo.setTradeStoreId(storeId);
			tradeInfo.setTradeComId(comId);
			tradeInfo.setTradeInputDate(DateUtils.getDate(xmleWealthContentMap.get("foundDate").toString()));
			tradeInfo.setTradeNo(commonService.createTradeNo("1"));
			ResultInfo resultInfo2 = new ResultInfo();
			resultInfo2 = saveTradeInfo1(tradeInfo, loginInfo);
			tradeInfo = (TradeInfo) resultInfo2.getObj();
			//插入客户交易状态信息
			TradeStatusInfo tradeStatusInfo = new TradeStatusInfo();
			Long tradeInfoId = tradeInfo.getTradeInfoId();
			tradeStatusInfo.setTradeInfoId(tradeInfoId);
			tradeStatusInfo.setStatusSerialNo((long)1);
			tradeStatusInfo.setActuSubscriptionAmount(new BigDecimal(xmleWealthContentMap.get("orderAmount").toString()));
			tradeStatusInfo.setSubscriptionCopies(new BigDecimal(xmleWealthContentMap.get("orderAmount").toString()));
			tradeStatusInfo.setTradeStatus("10");
			tradeStatusInfo.setProductId(productId);
			tradeStatusService.saveTradeStatus(tradeStatusInfo, loginInfo);
			// 插入客户交易信息
			String custBaseInfoId = custBaseInfo.getCustBaseInfoId().toString();
			saveTradeCustInfo(custBaseInfoId, tradeInfo, loginInfo);
			// 插入交易产品信息
			TradeProductInfo tradeProductInfo = new TradeProductInfo();
			tradeProductInfo.setProductId(productId);
			tradeProductInfo.setParamCode("subscriptionFee");
			tradeProductInfo.setParamValue(((xmleWealthContentMap.get("orderAmount").toString())));// 认金额
			TradeProductInfo tradeProductInfo2 = new TradeProductInfo();
			tradeProductInfo2.setProductId(productId);
			tradeProductInfo2.setParamCode("closedPeriods");
			tradeProductInfo2.setParamValue(xmleWealthContentMap.get("investdays").toString());// 投资期限
			TradeProductInfo tradeProductInfo3 = new TradeProductInfo();
			tradeProductInfo3.setProductId(productId);
			tradeProductInfo3.setParamCode("closedPeriodUnit");
			tradeProductInfo3.setParamValue(xmleWealthContentMap.get("investDaysUnit").toString());// 投资期限单位
			TradeProductInfo tradeProductInfo4 = new TradeProductInfo();
			tradeProductInfo4.setProductId(productId);
			tradeProductInfo4.setParamCode("beneficialType");
			String benefitType = xmleWealthContentMap.get("benefitType").toString();
			if(benefitType!=null&&!"".equals(benefitType)){
				tradeProductInfo4.setParamValue(benefitType);// 收益权类型
			}
			List<TradeProductInfo> tradeProductInfoList = new ArrayList<TradeProductInfo>();
			tradeProductInfoList.add(tradeProductInfo);
			tradeProductInfoList.add(tradeProductInfo2);
			tradeProductInfoList.add(tradeProductInfo3);
			tradeProductInfoList.add(tradeProductInfo4);
			saveWealthTradeInfo(tradeProductInfoList, loginInfo, orderNo);
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("插入交易信息失败");
		}
		return resultInfo;
	}

	/**
	 * 根据客户Id查询客户保险交易信息
	 */
	@Override
	public ResultInfo getRiskTradeInfo(Map xmleWealthContentMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map> tradeRiskInfoList = tradeServiceMapper.getTradeRiskInfo(xmleWealthContentMap);
			resultInfo.setObj(tradeRiskInfoList);
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询客户保险信息出现异常！");
		}
		return resultInfo;
	}
    
	/**
	 * 生成认购确认书
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo printTrade(Long tradeInfoId, String dir, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String path = dir + "WEB-INF/classes/pdfTemplate/subscriptionApply.pdf";
		//String path = PdfTool.class.getClassLoader().getResource("").getPath()+"pdfTemplate/subscriptionApply.pdf";
		Map paramMap = new HashMap();
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dfFile = new SimpleDateFormat("yyyy/MM/dd/");
			String dateStr = dfFile.format(calendar.getTime());
			DefCodeKey defCodeKey = new DefCodeKey();
			defCodeKey.setCodeType("fileSavePath");
			defCodeKey.setCode("02");
			DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
			String fileSavePath = "";
			if (defCode != null) {
				fileSavePath = defCode.getCodeName() + dateStr;
				if (fileSavePath != null && !"".equals(fileSavePath)) {
					File file = new File(fileSavePath);
					if (!file.exists()) {
						file.mkdirs();
					}
				} else {
					resultInfo.setMsg("文件保存路径为空！");
					resultInfo.setSuccess(false);
					return resultInfo;
				}
			} else {
				resultInfo.setMsg("文件输出路径不存在！");
				resultInfo.setSuccess(false);
				return resultInfo;
			}
			paramMap.put("tradeInfoId", tradeInfoId);
			Map<String,Object> pdfDataMap = new HashMap<String, Object>();
			//获取认购申请书的内容
			List<Map<String,Object>> requestMapInfoList = tradeServiceMapper.getRequestMapInfoList(paramMap);
			pdfDataMap = requestMapInfoList.get(0);
			//获取交易号
			String tradeNO=pdfDataMap.get("tradeNo").toString();
			//处理时间格式
			String foundDateNo = pdfDataMap.get("foundDateNo").toString();
			String foundDate = DateUtils.getDateChnString(foundDateNo);
			pdfDataMap.put("foundDate", foundDate);
			String idValidDateNo = pdfDataMap.get("idValidDateNo").toString();
			String idValidDate = DateUtils.getDateChnString(idValidDateNo);
			pdfDataMap.put("idValidDate", idValidDate);
			String applyDateNo = pdfDataMap.get("applyDateNo").toString();
			String applyDate = DateUtils.getDateChnString(applyDateNo);
			pdfDataMap.put("applyDate", applyDate);
			String birthdayNo = pdfDataMap.get("birthdayNo").toString();
			String birthday = DateUtils.getDateChnString(birthdayNo);
			pdfDataMap.put("birthday", birthday);
			//处理认购金额和认购费大小写问题
			String SUBSCRIPTIONFEE = pdfDataMap.get("subscriptionFee").toString();
			DecimalFormat df = new DecimalFormat(".00");// 调整数字匹配格式
			String SUBFEE = SimpleMoneyFormat.formatChineseCapital(Double.valueOf(SUBSCRIPTIONFEE));// 将认购金额改为大写
			double SUBsmall = Double.valueOf(SUBSCRIPTIONFEE);// 将认购额改为标准带小数格式
			String RGE = df.format(SUBsmall);// 将认购额格式化
			pdfDataMap.put("subscriptionAmountUpper", SUBFEE);
			pdfDataMap.put("subscriptionAmountLower", RGE);
			String productSubType = pdfDataMap.get("productSubType").toString();
			if(productSubType.equals("02")){
				//固定收益类，没有认购费
				pdfDataMap.put("subscriptionFeeUpper", "");
				pdfDataMap.put("subscriptionFeeLower", "");
			}else {
				BigDecimal subscriptionFeeRatio = (BigDecimal) pdfDataMap.get("subscriptionFeeRatio");// 初始认购费比率
				String reductionRadio = (String) pdfDataMap.get("reductionRadio");// 初始认购费减免比率
				if(subscriptionFeeRatio==null){
					pdfDataMap.put("subscriptionFeeUpper", "");
					pdfDataMap.put("subscriptionFeeLower", "");
				}else {
					//计算初始认购费
					Double subscriptionFee = NumberUtils.mul(SUBSCRIPTIONFEE, subscriptionFeeRatio.toEngineeringString());
					//再计算减免之后的认购费比例
					subscriptionFeeRatio = new BigDecimal(100.00-Double.parseDouble(reductionRadio));
					//计算减免之后的认购费
					Double subscriptionFeeAfter = NumberUtils.mul(subscriptionFee, subscriptionFeeRatio)/100;
					String STARTFEE = SimpleMoneyFormat.formatChineseCapital(subscriptionFeeAfter);// 将费用转为汉字
					if (subscriptionFeeAfter == 0) {
						STARTFEE = STARTFEE + "整";// 零元+整
					}
					String FEE2 = df.format(subscriptionFeeAfter);// 对数字进行格式化
					pdfDataMap.put("subscriptionFeeUpper", STARTFEE);
					pdfDataMap.put("subscriptionFeeLower", FEE2);
				}
			}
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + pdfDataMap.get("tradeNo").toString();
			/*String targetPath = "D:/usr/pdf/"+fileName+".pdf";
			String targetPathTest = "D:/usr/pdf/";*/
			PdfReader pdfReader = new PdfReader(path);
			PdfStamper ps = new PdfStamper(pdfReader, new FileOutputStream(fileSavePath + fileName + ".pdf"));
			AcroFields fields = ps.getAcroFields();
			BaseFont bfChinese = BaseFont.createFont("STSong-Light,Bold", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED); 
			//PdfTool.setAllPropertyFont(fields,bfChinese);
			// 替换PDF文件里面的标签字段
			for (String key: pdfDataMap.keySet()) {
				Object value = pdfDataMap.get(key);
				fields.setField(key,value.toString());
			}
			//添加条形码
			// 获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上,参数1表示在第一页生成条形码.
			PdfContentByte cb = ps.getOverContent(1);
			Barcode128 code128ext = new Barcode128();
			code128ext.setCode(tradeNO);//在条形码下设置编号（客户要求将交易号码作为此处的编号）
			code128ext.setStartStopText(false);
			code128ext.setSize(10);
			code128ext.setExtended(true);
			code128ext.setTextAlignment(Element.ALIGN_CENTER);
			Image img = code128ext.createImageWithBarcode(cb, null, null);
			img.setAbsolutePosition(450, 758);
			cb.addImage(img);
			//不能缺少
			ps.setFormFlattening(true);
			ps.close();
			
			Map tradeCount = new HashMap();
			Long busin = Long.parseLong(pdfDataMap.get("tradeNo").toString());
			tradeCount.put("tradeNo", busin);
			long times = tradeServiceMapper.tradeTotal(tradeCount) + 1;
			DefPrintInfo defPrint = new DefPrintInfo();
			Long printId = commonService.getSeqValByName("SEQ_T_DEF_PRINT_INFO");
			defPrint.setPrintInfoId(printId);
			defPrint.setPrintTimes(times);
			defPrint.setBusinessNo(busin);
			defPrint.setBusinessType(pdfDataMap.get("tradeType").toString());
			defPrint.setPrintFileName(fileName + ".pdf");
			defPrint.setFileSavePath(fileSavePath);
			defPrint.setPrintTime(DateUtils.getCurrentTimestamp());
			defPrint.setDoucmentType("01");
			BeanUtils.insertObjectSetOperateInfo(defPrint, loginInfo);
			defPrintInfoMapper.insert(defPrint);
			resultInfo.setMsg("认购申请书生成成功，请选择下载并打印！");
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("认购申请书生成出现异常");
		}
		return resultInfo;
	}
	/**
	 * 生成认购确认书02固定类
	 */
	@Override
	public ResultInfo tradeConfirmPrintList(Map param, String uploadDir,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		//String path = PdfTool.class.getClassLoader().getResource("").getPath()+"pdfTemplate/subscriptionConfirm.pdf";
		Map paramMap = new HashMap();
		try {
			//设置生成文件在服务器上的保存位置
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dfFile = new SimpleDateFormat("yyyy/MM/dd/");
			String dateStr = dfFile.format(calendar.getTime());
			DefCodeKey defCodeKey = new DefCodeKey();
			defCodeKey.setCodeType("fileSavePath");
			defCodeKey.setCode("02");
			DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
			String fileSavePath = "";
			if (defCode != null) {
				fileSavePath = defCode.getCodeName() + dateStr;
				if (fileSavePath != null && !"".equals(fileSavePath)) {
					File file = new File(fileSavePath);
					if (!file.exists()) {
						file.mkdirs();
					}
				} else {
					resultInfo.setMsg("文件保存路径为空！");
					resultInfo.setSuccess(false);
					return resultInfo;
				}
			} else {
				resultInfo.setMsg("文件输出路径不存在！");
				resultInfo.setSuccess(false);
				return resultInfo;
			}
			paramMap.put("tradeInfoId",param.get("tradeInfoId"));
			Map<String,Object> pdfDataMap = new HashMap<String, Object>();
			//获取认购申请书的内容
			List<Map<String,Object>> requestMapInfoList = tradeServiceMapper.getConfrimMapInfoList(paramMap);
			pdfDataMap = requestMapInfoList.get(0);
			//拿到模板位置
			/*String path = "";
			int productLength = pdfDataMap.get("productName").toString().length();
			if (productLength < 21) {
				path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionConfirm.pdf";
			}
			if (productLength >= 21 && productLength < 30 ) {
				path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionConfirm_small.pdf";
			}
			if (productLength >= 30 ) {
				path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionConfirm_tiny.pdf";
			}*/
			String path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionConfirm.pdf";
			//处理时间格式
			String foundDateNo = pdfDataMap.get("foundDateNo").toString();
			String foundDate = DateUtils.getDateChnString(foundDateNo);
			pdfDataMap.put("foundDate", foundDate);
			String printDateNo = pdfDataMap.get("printDateNo").toString();
			String printDate = DateUtils.getDateChnString(printDateNo);
			pdfDataMap.put("printDate", "打印日期："+printDate);
			//处理认购金额和认购费大小写问题
			String SUBSCRIPTIONFEE = pdfDataMap.get("subscriptionFee").toString();
			DecimalFormat df = new DecimalFormat(".00");// 调整数字匹配格式
			String SUBFEE = SimpleMoneyFormat.formatChineseCapital(Double.valueOf(SUBSCRIPTIONFEE));// 将认购金额改为大写
			double SUBsmall = Double.valueOf(SUBSCRIPTIONFEE);// 将认购额改为标准带小数格式
			String RGE = df.format(SUBsmall);// 将认购额格式化
			pdfDataMap.put("subscriptionAmountUpper", SUBFEE);
			pdfDataMap.put("subscriptionAmountLower", RGE);
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + pdfDataMap.get("tradeNo").toString();
			/*String targetPath = "D:/usr/pdf/"+fileName+".pdf";
			String targetPathTest = "D:/usr/pdf/";*/
			PdfReader pdfReader = new PdfReader(path);
			PdfStamper ps = new PdfStamper(pdfReader, new FileOutputStream(fileSavePath+fileName+".pdf"));
			AcroFields fields = ps.getAcroFields();
			String fontPath = uploadDir + "WEB-INF/classes/simhei.ttf";
			BaseFont bfChinese=BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			//BaseFont bfChinese = BaseFont.createFont("STSong-Light,Bold", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED); 
			PdfTool.setAllPropertyFont(fields,bfChinese);
			// 替换PDF文件里面的标签字段
			for (String key: pdfDataMap.keySet()) {
				Object value = pdfDataMap.get(key);
				//Paragraph t = new Paragraph(value, chineseFont);
				//t.setAlignment(Paragraph.ALIGN_CENTER);
				fields.setField(key,value.toString());
			}
			//添加条形码
			// 获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上,参数1表示在第一页生成条形码.
			PdfContentByte cb = ps.getOverContent(1);
			Barcode128 code128ext = new Barcode128();
			code128ext.setCode(pdfDataMap.get("tradeNo").toString());//在条形码下设置编码（此编码为交易号码）
			code128ext.setStartStopText(false);
			code128ext.setSize(10);
			code128ext.setExtended(true);
			code128ext.setTextAlignment(Element.ALIGN_CENTER);
			Image img = code128ext.createImageWithBarcode(cb, null, null);
			img.setAbsolutePosition(410, 730);
			cb.addImage(img);
			//获取基金管理人 若为特殊三家机构则添加公章
			String agency = pdfDataMap.get("agency").toString();
			if("杭州巨鲸财富管理有限公司".equals(agency)||"杭州巨鲸资产管理有限公司".equals(agency)||"杭州巨鲸道胜资产管理有限公司".equals(agency)){
				Image tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjcf.png");
				if ("杭州巨鲸财富管理有限公司".equals(agency)) {
					tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjcf.png");
				}
				if ("杭州巨鲸资产管理有限公司".equals(agency)) {
					tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjzc.png");
				}
				if ("杭州巨鲸道胜资产管理有限公司".equals(agency)) {
					tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjds.png");
				}
				/* 设置图片的位置 */
			   tImgCover.setAbsolutePosition(380, 80);
			   /* 设置图片的大小 */
			   tImgCover.scaleAbsolute(134, 134);
			   cb.addImage(tImgCover);       //加载图片
			}
			//不能缺少
			ps.setFormFlattening(true);
			ps.close();
			Map tradeCount = new HashMap();
			Long busin = Long.parseLong(pdfDataMap.get("tradeNo").toString());
			tradeCount.put("tradeNo", busin);
			long times = tradeServiceMapper.tradeTotal(tradeCount) + 1;
			DefPrintInfo defPrint = new DefPrintInfo();
			Long printId = commonService.getSeqValByName("SEQ_T_DEF_PRINT_INFO");
			defPrint.setPrintInfoId(printId);
			defPrint.setPrintTimes(times);
			defPrint.setBusinessNo(busin);
			defPrint.setBusinessType(pdfDataMap.get("tradeType").toString());
			defPrint.setPrintFileName(fileName + ".pdf");
			defPrint.setFileSavePath(fileSavePath);
			defPrint.setPrintTime(DateUtils.getCurrentTimestamp());
			defPrint.setDoucmentType("02");
			BeanUtils.insertObjectSetOperateInfo(defPrint, loginInfo);
			defPrintInfoMapper.insert(defPrint);
			resultInfo.setMsg("认购确认书生成成功，请选择下载并打印！");
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("生成认购确认书出现异常");
		}
		return resultInfo;
	}
	/**
	 * 生成认购确认书01 股权类
	 */
	@Override
	public ResultInfo tradeConfirmPrintFloatList(Map param, String uploadDir,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		//String path = PdfTool.class.getClassLoader().getResource("").getPath()+"pdfTemplate/subscriptionConfirm.pdf";
		Map paramMap = new HashMap();
		try {
			//设置生成文件在服务器上的保存位置
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dfFile = new SimpleDateFormat("yyyy/MM/dd/");
			String dateStr = dfFile.format(calendar.getTime());
			DefCodeKey defCodeKey = new DefCodeKey();
			defCodeKey.setCodeType("fileSavePath");
			defCodeKey.setCode("02");
			DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
			String fileSavePath = "";
			if (defCode != null) {
				fileSavePath = defCode.getCodeName() + dateStr;
				if (fileSavePath != null && !"".equals(fileSavePath)) {
					File file = new File(fileSavePath);
					if (!file.exists()) {
						file.mkdirs();
					}
				} else {
					resultInfo.setMsg("文件保存路径为空！");
					resultInfo.setSuccess(false);
					return resultInfo;
				}
			} else {
				resultInfo.setMsg("文件输出路径不存在！");
				resultInfo.setSuccess(false);
				return resultInfo;
			}
			paramMap.put("tradeInfoId",param.get("tradeInfoId"));
			Map<String,Object> pdfDataMap = new HashMap<String, Object>();
			//获取认购申请书的内容
			List<Map<String,Object>> requestMapInfoList = tradeServiceMapper.getFloatConfrimMapInfoList(paramMap);
			pdfDataMap = requestMapInfoList.get(0);
			//拿到模板位置
			/*String path = "";
			int productLength = pdfDataMap.get("productName").toString().length();
			if (productLength < 21) {
				path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionFloatConfirm.pdf";
			}
			if (productLength >= 21 && productLength < 30 ) {
				path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionFloatConfirm_small.pdf";
			}
			if (productLength >= 30 ) {
				path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionFloatConfirm_tiny.pdf";
			}*/
			String path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionFloatConfirm.pdf";
			//处理时间格式
			String foundDateNo = pdfDataMap.get("foundDateNo").toString();
			String foundDate = DateUtils.getDateChnString(foundDateNo);
			pdfDataMap.put("foundDate", foundDate);
			String printDateNo = pdfDataMap.get("printDateNo").toString();
			String printDate = DateUtils.getDateChnString(printDateNo);
			pdfDataMap.put("printDate", "打印日期："+printDate);
			//处理认购金额和认购费大小写问题
			String SUBSCRIPTIONFEE = pdfDataMap.get("subscriptionFee").toString();
			DecimalFormat df = new DecimalFormat(".00");// 调整数字匹配格式
			String SUBFEE = SimpleMoneyFormat.formatChineseCapital(Double.valueOf(SUBSCRIPTIONFEE));// 将认购金额改为大写
			double SUBsmall = Double.valueOf(SUBSCRIPTIONFEE);// 将认购额改为标准带小数格式
			String RGE = df.format(SUBsmall);// 将认购额格式化
			pdfDataMap.put("subscriptionAmountUpper", SUBFEE);
			pdfDataMap.put("subscriptionAmountLower", RGE);
			//处理认购费金额大小写问题
			String SUBSCRIPTIONFEERATIO = pdfDataMap.get("subscriptionFeeRatio").toString();
			String SUBFEERATIO = SimpleMoneyFormat.formatChineseCapital(Double.valueOf(SUBSCRIPTIONFEERATIO));// 将认购费改为大写
			double SUBFEEsmall = Double.valueOf(SUBSCRIPTIONFEERATIO);// 将认购费改为标准带小数格式
			String FEERGE = df.format(SUBFEEsmall);// 将认购费格式化
			if (SUBFEEsmall == 0.0) {
				FEERGE = "0";
			}
			pdfDataMap.put("subscriptionFeeAmountUpper", SUBFEERATIO);
			pdfDataMap.put("subscriptionFeeAmountLower", FEERGE);
			
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + pdfDataMap.get("tradeNo").toString();
			/*String targetPath = "D:/usr/pdf/"+fileName+".pdf";
			String targetPathTest = "D:/usr/pdf/";*/
			PdfReader pdfReader = new PdfReader(path);
			PdfStamper ps = new PdfStamper(pdfReader, new FileOutputStream(fileSavePath+fileName+".pdf"));
			AcroFields fields = ps.getAcroFields();
			String fontPath = uploadDir + "WEB-INF/classes/simhei.ttf";
			BaseFont bfChinese=BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			PdfTool.setAllPropertyFont(fields,bfChinese);
			// 替换PDF文件里面的标签字段
			for (String key: pdfDataMap.keySet()) {
				Object value = pdfDataMap.get(key);
				//Paragraph t = new Paragraph(value, chineseFont);
				//t.setAlignment(Paragraph.ALIGN_CENTER);
				fields.setField(key,value.toString());
			}
			//添加条形码
			// 获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上,参数1表示在第一页生成条形码.
			PdfContentByte cb = ps.getOverContent(1);
			Barcode128 code128ext = new Barcode128();
			code128ext.setCode(pdfDataMap.get("tradeNo").toString());//在条形码下设置编码（此编码为交易号码）
			code128ext.setStartStopText(false);
			code128ext.setSize(10);
			code128ext.setExtended(true);
			code128ext.setTextAlignment(Element.ALIGN_CENTER);
			Image img = code128ext.createImageWithBarcode(cb, null, null);
			img.setAbsolutePosition(410, 730);
			cb.addImage(img);
			//获取基金管理人 若为特殊三家机构则添加公章
			String agency = pdfDataMap.get("agency").toString();
			if("杭州巨鲸财富管理有限公司".equals(agency)||"杭州巨鲸资产管理有限公司".equals(agency)||"杭州巨鲸道胜资产管理有限公司".equals(agency)){
				Image tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjcf.png");
				if ("杭州巨鲸财富管理有限公司".equals(agency)) {
					tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjcf.png");
				}
				if ("杭州巨鲸资产管理有限公司".equals(agency)) {
					tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjzc.png");
				}
				if ("杭州巨鲸道胜资产管理有限公司".equals(agency)) {
					tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjds.png");
				}
				/* 设置图片的位置 */
				defCodeKey.setCodeType("pdfConfirmPos");
				defCodeKey.setCode("x");
				DefCode defCodeX = defCodeMapper.selectByPrimaryKey(defCodeKey);
				defCodeKey.setCodeType("pdfConfirmPos");
				defCodeKey.setCode("y");
				DefCode defCodeY = defCodeMapper.selectByPrimaryKey(defCodeKey);
			   tImgCover.setAbsolutePosition(380,35);
			   /* 设置图片的大小 */
			   tImgCover.scaleAbsolute(134, 134);
			   cb.addImage(tImgCover);       //加载图片
			}
			//不能缺少
			ps.setFormFlattening(true);
			ps.close();
			Map tradeCount = new HashMap();
			Long busin = Long.parseLong(pdfDataMap.get("tradeNo").toString());
			tradeCount.put("tradeNo", busin);
			long times = tradeServiceMapper.tradeTotal(tradeCount) + 1;
			DefPrintInfo defPrint = new DefPrintInfo();
			Long printId = commonService.getSeqValByName("SEQ_T_DEF_PRINT_INFO");
			defPrint.setPrintInfoId(printId);
			defPrint.setPrintTimes(times);
			defPrint.setBusinessNo(busin);
			defPrint.setBusinessType(pdfDataMap.get("tradeType").toString());
			defPrint.setPrintFileName(fileName + ".pdf");
			defPrint.setFileSavePath(fileSavePath);
			defPrint.setPrintTime(DateUtils.getCurrentTimestamp());
			defPrint.setDoucmentType("02");
			BeanUtils.insertObjectSetOperateInfo(defPrint, loginInfo);
			defPrintInfoMapper.insert(defPrint);
			resultInfo.setMsg("认购确认书生成成功，请选择下载并打印！");
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("生成认购确认书出现异常");
		}
		return resultInfo;
	}
	
	/**
	 * 生成认购确认书03浮动类
	 */
	@Override
	public ResultInfo tradeConfirmPrintFloatList03(Map param, String uploadDir,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
	
		//String path = PdfTool.class.getClassLoader().getResource("").getPath()+"pdfTemplate/subscriptionConfirm.pdf";
		Map paramMap = new HashMap();
		try {
			//设置生成文件在服务器上的保存位置
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dfFile = new SimpleDateFormat("yyyy/MM/dd/");
			String dateStr = dfFile.format(calendar.getTime());
			DefCodeKey defCodeKey = new DefCodeKey();
			defCodeKey.setCodeType("fileSavePath");
			defCodeKey.setCode("02");
			DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
			String fileSavePath = "";
			if (defCode != null) {
				fileSavePath = defCode.getCodeName() + dateStr;
				if (fileSavePath != null && !"".equals(fileSavePath)) {
					File file = new File(fileSavePath);
					if (!file.exists()) {
						file.mkdirs();
					}
				} else {
					resultInfo.setMsg("文件保存路径为空！");
					resultInfo.setSuccess(false);
					return resultInfo;
				}
			} else {
				resultInfo.setMsg("文件输出路径不存在！");
				resultInfo.setSuccess(false);
				return resultInfo;
			}
			paramMap.put("tradeInfoId",param.get("tradeInfoId"));
			Map<String,Object> pdfDataMap = new HashMap<String, Object>();
			//获取认购申请书的内容
			List<Map<String,Object>> requestMapInfoList = tradeServiceMapper.getFloatConfrimMapInfoList03(paramMap);
			pdfDataMap = requestMapInfoList.get(0);
			//拿到模板位置
			/*String path = "";
			int productLength = pdfDataMap.get("productName").toString().length();
			if (productLength < 21) {
				path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionFloatConfirm03.pdf";
			}
			if (productLength >= 21 && productLength < 30 ) {
				path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionFloatConfirm03_small.pdf";
			}
			if (productLength >= 30 ) {
				path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionFloatConfirm03_tiny.pdf";
			}*/
			String path = uploadDir + "WEB-INF/classes/pdfTemplate/subscriptionFloatConfirm03.pdf";
			//判断浮动类产品认购份额，若为空则不允许生成
			String subscriptionCopies = pdfDataMap.get("subscriptionCopies").toString();
			if (subscriptionCopies==null || subscriptionCopies.equals("")) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该交易份额为空，不能生成确认书！");
				return resultInfo;
			}
			//处理时间格式
			String foundDateNo = pdfDataMap.get("foundDateNo").toString();
			String foundDate = DateUtils.getDateChnString(foundDateNo);
			pdfDataMap.put("foundDate", foundDate);
			String printDateNo = pdfDataMap.get("printDateNo").toString();
			String printDate = DateUtils.getDateChnString(printDateNo);
			pdfDataMap.put("printDate","打印日期："+ printDate);
			//处理认购金额和认购费大小写问题
			String SUBSCRIPTIONFEE = pdfDataMap.get("subscriptionFee").toString();
			DecimalFormat df = new DecimalFormat(".00");// 调整数字匹配格式
			String SUBFEE = SimpleMoneyFormat.formatChineseCapital(Double.valueOf(SUBSCRIPTIONFEE));// 将认购金额改为大写
			double SUBsmall = Double.valueOf(SUBSCRIPTIONFEE);// 将认购额改为标准带小数格式
			String RGE = df.format(SUBsmall);// 将认购额格式化
			pdfDataMap.put("subscriptionAmountUpper", SUBFEE);
			pdfDataMap.put("subscriptionAmountLower", RGE);
			//处理认购费金额大小写问题
			String SUBSCRIPTIONFEERATIO = pdfDataMap.get("subscriptionFeeRatio").toString();
			String SUBFEERATIO = SimpleMoneyFormat.formatChineseCapital(Double.valueOf(SUBSCRIPTIONFEERATIO));// 将认购费改为大写
			double SUBFEEsmall = Double.valueOf(SUBSCRIPTIONFEERATIO);// 将认购费改为标准带小数格式
			String FEERGE = df.format(SUBFEEsmall);// 将认购费格式化
			if (SUBFEEsmall == 0.0) {
				FEERGE = "0";
			}
			pdfDataMap.put("subscriptionFeeAmountUpper", SUBFEERATIO);
			pdfDataMap.put("subscriptionFeeAmountLower", FEERGE);
			
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + pdfDataMap.get("tradeNo").toString();
			/*String targetPath = "D:/usr/pdf/"+fileName+".pdf";
			String targetPathTest = "D:/usr/pdf/";*/
			PdfReader pdfReader = new PdfReader(path);
			PdfStamper ps = new PdfStamper(pdfReader, new FileOutputStream(fileSavePath+fileName+".pdf"));
			AcroFields fields = ps.getAcroFields();
			String fontPath = uploadDir + "WEB-INF/classes/simhei.ttf";
			BaseFont bfChinese=BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			//BaseFont bfChinese = BaseFont.createFont("STSong-Light,Bold", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED); 
			PdfTool.setAllPropertyFont(fields,bfChinese);
			// 替换PDF文件里面的标签字段
			for (String key: pdfDataMap.keySet()) {
				Object value = pdfDataMap.get(key);
				//Paragraph t = new Paragraph(value, chineseFont);
				//t.setAlignment(Paragraph.ALIGN_CENTER);
				fields.setField(key,value.toString());
			}
			//添加条形码
			// 获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上,参数1表示在第一页生成条形码.
			PdfContentByte cb = ps.getOverContent(1);
			Barcode128 code128ext = new Barcode128();
			code128ext.setCode(pdfDataMap.get("tradeNo").toString());//在条形码下设置编码（此编码为交易号码）
			code128ext.setStartStopText(false);
			code128ext.setSize(10);
			code128ext.setExtended(true);
			code128ext.setTextAlignment(Element.ALIGN_CENTER);
			Image img = code128ext.createImageWithBarcode(cb, null, null);
			img.setAbsolutePosition(410, 730);
			cb.addImage(img);
			//获取基金管理人 若为特殊三家机构则添加公章
			String agency = pdfDataMap.get("agency").toString();
			if("杭州巨鲸财富管理有限公司".equals(agency)||"杭州巨鲸资产管理有限公司".equals(agency)||"杭州巨鲸道胜资产管理有限公司".equals(agency)){
				Image tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjcf.png");
				if ("杭州巨鲸财富管理有限公司".equals(agency)) {
					tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjcf.png");
				}
				if ("杭州巨鲸资产管理有限公司".equals(agency)) {
					tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjzc.png");
				}
				if ("杭州巨鲸道胜资产管理有限公司".equals(agency)) {
					tImgCover = Image.getInstance(uploadDir+"WEB-INF/classes/pdfTemplate/jjds.png");
				}
				/* 设置图片的位置 */
				defCodeKey.setCodeType("pdfConfirmPos");
				defCodeKey.setCode("x");
				DefCode defCodeX = defCodeMapper.selectByPrimaryKey(defCodeKey);
				defCodeKey.setCodeType("pdfConfirmPos");
				defCodeKey.setCode("y");
				DefCode defCodeY = defCodeMapper.selectByPrimaryKey(defCodeKey);
			   tImgCover.setAbsolutePosition(380, 35);
			   /* 设置图片的大小 */
			   tImgCover.scaleAbsolute(134, 134);
			   cb.addImage(tImgCover);       //加载图片
			}
			//不能缺少
			ps.setFormFlattening(true);
			ps.close();
			Map tradeCount = new HashMap();
			Long busin = Long.parseLong(pdfDataMap.get("tradeNo").toString());
			tradeCount.put("tradeNo", busin);
			long times = tradeServiceMapper.tradeTotal(tradeCount) + 1;
			DefPrintInfo defPrint = new DefPrintInfo();
			Long printId = commonService.getSeqValByName("SEQ_T_DEF_PRINT_INFO");
			defPrint.setPrintInfoId(printId);
			defPrint.setPrintTimes(times);
			defPrint.setBusinessNo(busin);
			defPrint.setBusinessType(pdfDataMap.get("tradeType").toString());
			defPrint.setPrintFileName(fileName + ".pdf");
			defPrint.setFileSavePath(fileSavePath);
			defPrint.setPrintTime(DateUtils.getCurrentTimestamp());
			defPrint.setDoucmentType("02");
			BeanUtils.insertObjectSetOperateInfo(defPrint, loginInfo);
			defPrintInfoMapper.insert(defPrint);
			resultInfo.setMsg("认购确认书生成成功，请选择下载并打印！");
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("生成认购确认书出现异常");
		}
		return resultInfo;
	}

	@Override
	public String checkUpFile(HashMap paramMap) {
		List<Map<String, String>> infoList = null;
		String a = null;
		try {
			infoList = tradeServiceMapper.checkUpFile(paramMap);
			if(infoList.size()>0){
				a="1";
			}else {
				
				a="0";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return a;
	}
	
	
	@Override
	public String checkCustIDFile(HashMap paramMap) {
		List<Map<String, String>> infoList = null;
		String a = null;
		try {
			infoList = tradeServiceMapper.checkCustIDFile(paramMap);
			if(infoList.isEmpty()){
				a="0";
			}else {
				a="1";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return a;
	}
	
	/**
	 * 判断基金风险等级与客户风险等级是否匹配
	 */
	@Override
	public ResultInfo checkRiskLevel(HashMap paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		//Long tradeInfoId = Long.parseLong(paramMap.get("tradeInfoId").toString());
		Map resultMap = tradeServiceMapper.getCustAndProductRiskInfo(paramMap);
		if (resultMap.isEmpty()) {
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		String custRiskLevel = resultMap.get("custRiskLevel").toString();//客户风险类型
		String productGrade = resultMap.get("productGrade").toString();//基金风险等级
		if (custRiskLevel.equals("01")) {
			//保守型客户 只能认购低-01 中低-02
			if (!productGrade.equals("01")&&!productGrade.equals("02")) {
				resultInfo.setSuccess(false);
				return resultInfo;
			}
		}
		if (custRiskLevel.equals("02")) {
			//稳健型客户 只能认购低-01 中低-02 中-03
			if (productGrade.equals("04")||productGrade.equals("05")) {
				resultInfo.setSuccess(false);
				return resultInfo;
			}
		}
		if (custRiskLevel.equals("03")) {
			//平衡型客户 只能认购低-01 中低-02 中-03  中高-04
			if (productGrade.equals("05")) {
				resultInfo.setSuccess(false);
				return resultInfo;
			}
		}
		//剩余成长和进取型客户 可以任意购买
		resultInfo.setMsg("匹配成功！");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 获取交易生成的基本信息
	 */
	@Override
	public ResultInfo getTradeBaseInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		List<Map<String, String>> resultMap = tradeServiceMapper.getTradeBaseInfo(paramMap);
		if (resultMap.size() > 0) {
			resultInfo.setSuccess(true);
			for (Map tradeMap : resultMap) {
				if (tradeMap.containsKey("totalAssets") && tradeMap.get("totalAssets") != null) {
					BigDecimal totalAssets = (BigDecimal) tradeMap.get("totalAssets");
					tradeMap.put("chnTradeTotalAssets",
							SimpleMoneyFormat.formatChineseCapital(totalAssets.doubleValue()));
				}
			}
			resultInfo.setObj(resultMap.get(0));
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询交易信息出现问题！");
		}
		return resultInfo;
		
	}
}
