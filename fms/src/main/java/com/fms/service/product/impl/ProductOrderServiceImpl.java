package com.fms.service.product.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.activemq.leveldb.replicated.dto.Login;
import org.apache.log4j.Logger;
import org.eclipse.core.internal.runtime.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.util.DateUtil;
import com.ctc.wstx.util.StringUtil;
import com.fms.controller.product.ProductController;
import com.fms.db.mapper.AgencyComMapper;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.CustAccInfoMapper;
import com.fms.db.mapper.CustAddressInfoMapper;
import com.fms.db.mapper.CustBaseInfoMapper;
import com.fms.db.mapper.PDAmountOrderInfoMapper;
import com.fms.db.mapper.PDAmountOrderQueueInfoMapper;
import com.fms.db.mapper.PDFactorMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PDWealthMapper;
import com.fms.db.mapper.PDWealthOpenDateMapper;
import com.fms.db.mapper.PdAmountDisInfoMapper;
import com.fms.db.mapper.SysEmailInfoMapper;
import com.fms.db.mapper.TradeCustAccRelaMapper;
import com.fms.db.mapper.TradeCustAddressRelaMapper;
import com.fms.db.mapper.TradeCustInfoMapper;
import com.fms.db.mapper.TradeInfoMapper;
import com.fms.db.mapper.TradeProductInfoMapper;
import com.fms.db.model.AgencyCom;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.CustAccInfo;
import com.fms.db.model.CustAccInfoExample;
import com.fms.db.model.CustAddressInfo;
import com.fms.db.model.CustAddressInfoExample;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.CustBaseInfoExample;
import com.fms.db.model.PDAmountOrderInfo;
import com.fms.db.model.PDAmountOrderInfoExample;
import com.fms.db.model.PDAmountOrderQueueInfo;
import com.fms.db.model.PDAmountOrderQueueInfoExample;
import com.fms.db.model.PDFactor;
import com.fms.db.model.PDFactorExample;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDProductExample;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthExample;
import com.fms.db.model.PDWealthOpenDate;
import com.fms.db.model.PDWealthOpenDateExample;
import com.fms.db.model.PdAmountDisInfo;
import com.fms.db.model.PdAmountDisInfoExample;
import com.fms.db.model.SysEmailAdress;
import com.fms.db.model.SysEmailAdressExample;
import com.fms.db.model.SysEmailInfo;
import com.fms.db.model.TradeCustAccRela;
import com.fms.db.model.TradeCustAddressRela;
import com.fms.db.model.TradeCustInfo;
import com.fms.db.model.TradeInfo;
import com.fms.db.model.TradeProductInfo;
import com.fms.db.model.TradeProductInfoExample;
import com.fms.db.model.TradeStatusInfo;
import com.fms.service.mapper.ProductOrderServiceMapper;
import com.fms.service.mapper.QueryCustomerServiceMapper;
import com.fms.service.mapper.SmsServiceMapper;
import com.fms.service.product.ProductOrderService;
import com.fms.service.sms.SmsService;
import com.mysql.jdbc.log.Log;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefComMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.db.model.DefCom;
import com.sinosoft.core.db.model.DefComExample;
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
import com.sinosoft.util.ResultInfo;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.DateTime;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProductOrderServiceImpl implements ProductOrderService {

	@Autowired
	private DefComMapper defComMapper;
	@Autowired
	private PDProductMapper pdProductMapper;
	@Autowired
	private PDWealthMapper pdWealthMapper;
	@Autowired
	private PDWealthOpenDateMapper pdWealthOpenDateMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private AgencyComMapper agencyComMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private PdAmountDisInfoMapper pdAmountDisInfoMapper;
	// 产品主表
	@Autowired
	private PDAmountOrderInfoMapper pdAmountOrderInfoMapper;
	@Autowired
	private PDAmountOrderQueueInfoMapper pdAmountOrderQueueInfoMapper;
	// 产品额度预约信息表
	@Autowired
	private ProductOrderServiceMapper productOrderServiceMapper;
	@Autowired
	private SmsService smsService;
	@Autowired
	private SysEmailInfoMapper sysEmailInfoMapper;
	@Autowired
	private QueryCustomerServiceMapper queryCustomerServiceMapper;
	@Autowired
	private SmsServiceMapper smsServiceMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	@Autowired
	private TradeCustInfoMapper tradeCustInfoMapper;
	@Autowired
	private CustBaseInfoMapper custBaseInfoMapper;
	@Autowired
	private CustAccInfoMapper custAccInfoMapper;
	@Autowired
	private CustAddressInfoMapper custAddressInfoMapper;
	@Autowired
	private TradeCustAccRelaMapper tradeCustAccRelaMapper;
	@Autowired
	private TradeCustAddressRelaMapper tradeCustAddressRelaMapper;
	@Autowired
	private TradeProductInfoMapper tradeProductInfoMapper;
	@Autowired
	private PDFactorMapper pdFactorMapper;

	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid getAllPdVerificationInfo(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		DataGrid dataGrid = new DataGrid();
		try {
			if (paramMap == null) {
				paramMap = new HashMap();
			}
			paramMap.put("startIndex", dgm.getStartIndex());
			paramMap.put("endIndex", dgm.getEndIndex());
			// 登录信息
			paramMap.put("rolePrivilege", loginInfo.getRolePivilege());// 获取用户权限
			paramMap.put("operComId", loginInfo.getComId().toString());// 登陆用户所属管理机构ID
			paramMap.put("createUserId", loginInfo.getUserId().toString());// 登陆用户ID
			// 获取符合条件的行数
			Integer total = productOrderServiceMapper.queryComPdOrderAuditInfoCount(paramMap);
			List<Map> resultList = productOrderServiceMapper.queryComPdOrderAuditInfo(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataGrid;
	}

	/*
	 * 提交"产品预约审核"信息 返回类型： 无 参数类型： String, LoginInfo 需要修改表：
	 * 产品额度预约信息表(T_PD_AMOUNT_ORDER_INFO),将此表中符合页面条件的记录中ORDER_STATUS属性设置为代表“通过审核”
	 * 的代码 JavaBean: PDAmountOrder类，对应产品额度预约信息表(T_PD_AMOUNT_ORDER_INFO)
	 */
	@Transactional
	public ResultInfo submitPdOrderAudit(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 1.参数判断
		if (pdAmountOrderInfo == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("预约记录为空，不能提交审核！");
			return resultInfo;
		}
		// 2.若传入参数不为空， 进行业务处理
		try {
			pdAmountOrderInfo = pdAmountOrderInfoMapper.selectByPrimaryKey(pdAmountOrderInfo.getPdAmountOrderInfoId());
			String custOrderStatus = pdAmountOrderInfo.getOrderStatus();
			if ("".equals(custOrderStatus)||custOrderStatus == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到预约状态！审核失败！");
			}
			if ("01".equals(custOrderStatus)||"05".equals(custOrderStatus)) {
				pdAmountOrderInfo.setOrderStatus("02");
				//判断定金审核日期是否存在 若不存在则创建为全款审核日期
				Date earnestAuditDate = pdAmountOrderInfo.getEarnestAuditDate();
				if ("".equals(earnestAuditDate)||earnestAuditDate == null) {
					pdAmountOrderInfo.setEarnestAuditDate(new Date());
				}
				BeanUtils.updateObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
				pdAmountOrderInfoMapper.updateByPrimaryKey(pdAmountOrderInfo);
				// 触发短信发送
				resultInfo = smsService.createAndSendProductOrderAuditSms(pdAmountOrderInfo, loginInfo);
				// 触发邮件发送
				// resultInfo =
				// createAndSendProductOrderAuditEmail(pdAmountOrderInfo,
				// loginInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("审核完成！");

			} else if (custOrderStatus != null && !"".equals(custOrderStatus) && "02".equals(custOrderStatus)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该预约已经审核通过，不能再次提交审核！");
			} else if (custOrderStatus != null && !"".equals(custOrderStatus) && "03".equals(custOrderStatus)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该预约已经失效，不能提交审核！");
			}
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("提交审核出现异常，请联系系统管理员！");
			e.printStackTrace();

		}
		return resultInfo;
	}

	/*
	 * 提交"撤销预约"信息 返回类型：待定 参数类型：待定
	 * 需要修改表：产品额度预约信息表(T_PD_AMOUNT_ORDER_INFO)，以及包含产品可分配总额的表(具体表名未找到？？？)
	 */
	@Transactional
	public ResultInfo cancelPdAmountOrderAudit(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		if (pdAmountOrderInfo == null) { // 判断方法接受参数是否为空,若为空，直接返回错误信息
			resultInfo.setSuccess(false);
			resultInfo.setMsg("预约记录为空，无法撤销该预约");
			return resultInfo;
		}
		try { // 根据方法传入参数值，进行撤销操作
			pdAmountOrderInfo = pdAmountOrderInfoMapper.selectByPrimaryKey(pdAmountOrderInfo.getPdAmountOrderInfoId());
			String custOrderStatus = pdAmountOrderInfo.getOrderStatus();
			Long tradeInfoId = pdAmountOrderInfo.getTradeInfoId();
			//判断交易状态为复核-02或退回-15的不能进行撤销
			TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeInfoId);
			String tradeStatus = tradeInfo.getTradeStaus();
			if (tradeStatus != null && !"".equals(tradeStatus) && "02".equals(tradeStatus) ) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("当前交易已提交复核，无法撤销!");
				return resultInfo;
			}else if (tradeStatus != null && !"".equals(tradeStatus) && "15".equals(tradeStatus)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("当前交易已提交复核，无法撤销!");
				return resultInfo;
			} else if (custOrderStatus != null && !"".equals(custOrderStatus) && "03".equals(custOrderStatus)) { // 方法接收的记录已处于“撤销”状态，则返回错误信息
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该预约记录已经撤销，无法再次撤销");
				return resultInfo;
			} else if (custOrderStatus != null && !"".equals(custOrderStatus) && "02".equals(custOrderStatus)) { // 方法接收的记录处于"已打款"状态，则同样返回错误信息
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该预约记录已经打款，无法进行撤销操作");
				return resultInfo;
			} else if (custOrderStatus != null && !"".equals(custOrderStatus) && ("01".equals(custOrderStatus)||"05".equals(custOrderStatus))) { // 方法接收的记录处于"未打款"状态，进行撤销操作
				pdAmountOrderInfo.setOrderStatus("03"); // 该条记录状态从“01”未打款状态变为“03”撤销状态
				BeanUtils.updateObjectSetOperateInfo(pdAmountOrderInfo, loginInfo); // 修改该条记录“操作时间，操作人员“等信息
				pdAmountOrderInfoMapper.updateByPrimaryKey(pdAmountOrderInfo);
				// 修改对应交易为撤销状态
				tradeInfo.setTradeStaus("09"); // 交易状态09：撤销交易
				BeanUtils.updateObjectSetOperateInfo(tradeInfo, loginInfo);
				tradeInfoMapper.updateByPrimaryKey(tradeInfo);
				//发送撤销邮件
				resultInfo = sendProductOrderCancelEmail(pdAmountOrderInfo,loginInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("撤销完成");
			}
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("撤销操作出现异常，请联系系统管理员");
			e.printStackTrace();
		}
		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryComPdAmountOrderInfoList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		DataGrid dataGrid = new DataGrid();
		try {
			if (paramMap == null) {
				paramMap = new HashMap();
			}
			paramMap.put("startIndex", dgm.getStartIndex());
			paramMap.put("endIndex", dgm.getEndIndex());
			paramMap.put("comId", loginInfo.getComId().toString());
			paramMap.put("currentTime", DateUtils.getDateTimeString(new Date()));
			// 获取符合条件的行数
			Integer total = productOrderServiceMapper.queryComPdAmountOrderInfoListCount(paramMap);
			List<Map> resultList = productOrderServiceMapper.queryComPdAmountOrderInfoList(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增预约时获取机构、产品、理财经理的相关信息
	 * 
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	@SuppressWarnings("rawtypes")
	public ResultInfo getProductAndComInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String comId = paramMap.get("comId").toString();
			String productId = paramMap.get("productId").toString();
			String expectOpenDay = paramMap.get("expectOpenDay").toString();
			// String isDistribute = paramMap.get("isDistribute").toString();
			String pdAmountOrderInfoId = null;
			if (paramMap.containsKey("pdAmountOrderInfoId")) {
				pdAmountOrderInfoId = paramMap.get("pdAmountOrderInfoId").toString();
			}
			// 1.获取机构相关信息
			if (comId == null || "".equals(comId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取机构和产品信息出错,未获取到机构相关信息");
			}
			DefCom defCom = defComMapper.selectByPrimaryKey(new Long(comId));
			resultMap.put("comId", comId);
			resultMap.put("comName", defCom.getComName());
			// 2.获取产品相关信息
			PDProduct pdProduct = pdProductMapper.selectByPrimaryKey(new Long(productId));
			resultMap.put("productId", productId);
			resultMap.put("productCode", pdProduct.getProductCode());
			resultMap.put("productName", pdProduct.getProductName());
			// 产品类型及产品子类型
			String productType = pdProduct.getProductType();
			String productSubType = pdProduct.getProductSubType();
			// 获取财富产品信息
			PDWealthExample pdWealthExample = new PDWealthExample();
			PDWealthExample.Criteria pdWealthCriteria = pdWealthExample.createCriteria();
			pdWealthCriteria.andProductIdEqualTo(pdProduct.getProductId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			PDWealth pdWealth = pdWealthMapper.selectByExample(pdWealthExample).get(0);
			// 产品成立日
			resultMap.put("foundDate", DateUtils.getDateString(pdWealth.getFoundDate()));
			// 财富产品固定收益类
			if ("1".equals(productType) && ("01".equals(productSubType) || "02".equals(productSubType))) {
				// 封账日
				resultMap.put("sealingAccDate", DateUtils.getDateTimeString(pdWealth.getRaiseEndDate()));
				// 固定收益类设置开放日就为产品成立日
				// resultMap.put("expectOpenDay",
				// DateUtils.getDateString(pdWealth.getFoundDate()));
			}
			// 财富产品浮动收益类及股权类
			else if ("1".equals(productType) &&  ("03".equals(productSubType) || "04".equals(productSubType))) {
				PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
				PDWealthOpenDateExample.Criteria pdWealthOpenDateCriteria = pdWealthOpenDateExample.createCriteria();
				pdWealthOpenDateCriteria.andPdWealthIdEqualTo(pdWealth.getPdWealthId())
						.andOpenDateEqualTo(DateUtils.getDate(expectOpenDay))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				PDWealthOpenDate pdWealthOpenDate = pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample)
						.get(0);
				resultMap.put("sealingAccDate", DateUtils.getDateTimeString(pdWealthOpenDate.getInvestEndDate()));
				resultMap.put("expectOpenDay", DateUtils.getDateString(pdWealthOpenDate.getOpenDate()));
			}
			// 3.获取产品方相关信息
			AgencyCom agencyCom = agencyComMapper.selectByPrimaryKey(pdProduct.getAgencyComId());
			resultMap.put("agencyComId", agencyCom.getAgencyComId().toString());
			resultMap.put("agencyComName", agencyCom.getAgencyName());
			// 4.获取理财经理信息
			if (pdAmountOrderInfoId != null && !"".equals(pdAmountOrderInfoId)) {
				PDAmountOrderInfo pdAmountOrderInfo = pdAmountOrderInfoMapper
						.selectByPrimaryKey(new Long(pdAmountOrderInfoId));
				Agent agent = agentMapper.selectByPrimaryKey(pdAmountOrderInfo.getAgentId());
				resultMap.put("agentId", agent.getAgentId().toString());
				resultMap.put("agentName", agent.getAgentName());
				resultMap.put("agentMobile", agent.getMobile());
			} else {
				AgentExample agentExample = new AgentExample();
				AgentExample.Criteria agentCriteria = agentExample.createCriteria();
				agentCriteria.andUserIdEqualTo(loginInfo.getUserId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<Agent> agentList = agentMapper.selectByExample(agentExample);
				if (agentList == null) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("您不是财富顾问,无法进行产品预约！");
					return resultInfo;
				}

				resultMap.put("agentId", agentList.get(0).getAgentId().toString());
				resultMap.put("agentName", agentList.get(0).getAgentName());
				resultMap.put("agentMobile", agentList.get(0).getMobile());
			}

			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);

		} catch (IndexOutOfBoundsException ee) {
			ee.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("您不是财富顾问,无法进行产品预约！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取机构和产品信息出错," + e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 保存产品预约相关信息
	 * 
	 * @throws Exception
	 *
	 */
	@Override
	@Transactional
	public synchronized ResultInfo saveProductAmountOrderInfo(PDAmountOrderInfo pdAmountOrderInfo,
			PDAmountOrderQueueInfo pdAmountOrderQueueInfo, Map<String, String> paramMap, LoginInfo loginInfo)
					throws Exception {
		ResultInfo resultInfo = new ResultInfo();

		BeanUtils.insertObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
		BeanUtils.insertObjectSetOperateInfo(pdAmountOrderQueueInfo, loginInfo);
		String inviteCode = null;
		String isInviteCode = paramMap.get("isInviteCode");
		String productName = paramMap.get("productName");
		String isDistribute = paramMap.get("isDistribute");
		PDProduct pdProduct = pdProductMapper.selectByPrimaryKey(pdAmountOrderInfo.getProductId());

		// 获取邀请码,需要邀请码时获取邀请码
		if (isInviteCode != null && !"".equals(isInviteCode) && "01".equals(isInviteCode)) {
			inviteCode = getInviteCode().getObj().toString();
			if (inviteCode == null || "".equals(inviteCode)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取邀请码出错，请联系管理员！");
				return resultInfo;
			}
		}
		pdAmountOrderInfo.setInviteCode(inviteCode);
		pdAmountOrderQueueInfo.setInviteCode(inviteCode);
		// 判断预约额度是否足够(没有将总额度分配到分公司的，以产品的融资规模为标准,分配到分公司的，将以分公司的额度为限制)
		PDWealthExample pdWealthExample = new PDWealthExample();
		PDWealthExample.Criteria pdWealthCriteria = pdWealthExample.createCriteria();
		pdWealthCriteria.andProductIdEqualTo(pdAmountOrderInfo.getProductId())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		PDWealth pdWealth = pdWealthMapper.selectByExample(pdWealthExample).get(0);
		BigDecimal totalAmount = new BigDecimal(0);
		if (isDistribute != null && !"".equals(isDistribute) && "Y".equals(isDistribute)) {
			PdAmountDisInfoExample pdAmountDisInfoExample = new PdAmountDisInfoExample();
			PdAmountDisInfoExample.Criteria criteria = pdAmountDisInfoExample.createCriteria();
			criteria.andProductIdEqualTo(pdAmountOrderInfo.getProductId()).andComIdEqualTo(pdAmountOrderInfo.getComId())
					.andExpectOpenDayEqualTo(pdAmountOrderInfo.getExpectOpenDay())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			PdAmountDisInfo pdAmountDisInfo = pdAmountDisInfoMapper.selectByExample(pdAmountDisInfoExample).get(0);
			// 该分公司的分配额度
			totalAmount = pdAmountDisInfo.getAmount();
		} else {

			String productType = pdProduct.getProductType();
			String productSubType = pdProduct.getProductSubType();
			// 产品融资规模
			if (productType != null && !"".equals(productType) && "1".equals(productType) && productSubType != null
					&& !"".equals(productSubType) && ("01".equals(productSubType) || "02".equals(productSubType))) {
				totalAmount = pdWealth.getFinancingScale();
			} else if (productType != null && !"".equals(productType) && "1".equals(productType)
					&& productSubType != null && !"".equals(productSubType) && ("03".equals(productSubType) || "04".equals(productSubType))) {
				PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
				PDWealthOpenDateExample.Criteria pdWealthOpenDateCriteria = pdWealthOpenDateExample.createCriteria();
				pdWealthOpenDateCriteria.andPdWealthIdEqualTo(pdWealth.getPdWealthId())
						.andOpenDateEqualTo(pdAmountOrderInfo.getExpectOpenDay())
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				PDWealthOpenDate pdWealthOpenDate = pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample)
						.get(0);
				totalAmount = pdWealthOpenDate.getFinancingScale();
			}

		}
		// 如果募集总额未配置，当0来处理
		if (totalAmount == null) {
			totalAmount = new BigDecimal(0);
		}
		List<String> orderStatusList = new ArrayList<String>();
		orderStatusList.add("01");
		orderStatusList.add("02");
		// 获取该分公司的已预约总额
		PDAmountOrderInfoExample pdAmountOrderInfoExample = new PDAmountOrderInfoExample();
		PDAmountOrderInfoExample.Criteria pdAmountOrderInfoCriteria = pdAmountOrderInfoExample.createCriteria();
		pdAmountOrderInfoCriteria.andProductIdEqualTo(pdAmountOrderInfo.getProductId())
				.andExpectOpenDayEqualTo(pdAmountOrderInfo.getExpectOpenDay()).andOrderStatusIn(orderStatusList)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		// 如果额度分配到分公司，要取该分公司的预约总额
		if (isDistribute != null && !"".equals(isDistribute) && "Y".equals(isDistribute)) {
			pdAmountOrderInfoCriteria.andComIdEqualTo(pdAmountOrderInfo.getComId());
		}
		// 新增预约重复提交时排除本身
		if (pdAmountOrderInfo.getPdAmountOrderInfoId() != null) {
			pdAmountOrderInfoCriteria.andPdAmountOrderInfoIdNotEqualTo(pdAmountOrderInfo.getPdAmountOrderInfoId());
		}
		List<PDAmountOrderInfo> pdAmountOrderInfoList = pdAmountOrderInfoMapper
				.selectByExample(pdAmountOrderInfoExample);
		double existSumAmount = 0;
		for (PDAmountOrderInfo existPdAmountOrderInfo : pdAmountOrderInfoList) {
			existSumAmount += existPdAmountOrderInfo.getOrderAmount().doubleValue();
		}
		double remainAmount = totalAmount.doubleValue() - existSumAmount;
		double orderAmount = pdAmountOrderInfo.getOrderAmount().doubleValue();
		// 额度不够，排队预约
		if (remainAmount < orderAmount) {
			// 判断排队人数 不可超过10人
			PDAmountOrderQueueInfoExample pdAmountOrderQueueInfoExample = new PDAmountOrderQueueInfoExample();
			PDAmountOrderQueueInfoExample.Criteria pdAmountOrderQueueInfoCriteria = pdAmountOrderQueueInfoExample
					.createCriteria();
			pdAmountOrderQueueInfoCriteria.andProductIdEqualTo(pdAmountOrderQueueInfo.getProductId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			if (pdAmountOrderQueueInfo.getExpectOpenDay() != null) {
				pdAmountOrderQueueInfoCriteria.andExpectOpenDayEqualTo(pdAmountOrderQueueInfo.getExpectOpenDay());
			}
			Integer queueCount = pdAmountOrderQueueInfoMapper.countByExample(pdAmountOrderQueueInfoExample);
			/*
			 * if (queueCount > 19) { resultInfo.setSuccess(false);
			 * resultInfo.setMsg("本产品排队人数超过上限！不可预约！"); return resultInfo; }
			 */
			// 判断预约额度是否符合产品设置要求，主要校验起投金额，投资限额，递增金额
			BigDecimal startInvestMoney = pdWealth.getStartInvestMoney();
			BigDecimal investLimitMoney = pdWealth.getInvestLimitMoney();
			BigDecimal investIncreaseMoney = pdWealth.getInvestIncreaseMoney();
			double startInvestAmount = startInvestMoney != null ? startInvestMoney.doubleValue() : 0;
			double investLimitAmount = investLimitMoney != null ? investLimitMoney.doubleValue() : 0;
			double investIncreaseAmount = investIncreaseMoney != null ? investIncreaseMoney.doubleValue() : 0;
			if (startInvestAmount > 0 && orderAmount < startInvestAmount) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("本产品起投金额为" + startInvestMoney.toString() + ",投资金额不能小于起投金额！");
				return resultInfo;
			}
			if (investLimitAmount > 0 && investLimitAmount < orderAmount) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("本产品投资限额为" + investLimitMoney.toString() + ",投资金额不能超过投资限额！");
				return resultInfo;
			}
			if (investIncreaseAmount > 0 && (orderAmount - startInvestAmount) % investIncreaseAmount != 0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("本产品起投金额为" + startInvestMoney.toString() + ",递增金额为" + investIncreaseMoney.toString()
						+ ",预约金额不符合要求！");
				return resultInfo;
			}
			Integer queueNo = queueCount + 1;
			// 队列表中 排队序号
			pdAmountOrderQueueInfo.setQueueNo(new Long(queueNo));
			// 01-预约队列中，详见码表custOrderStatus
			pdAmountOrderQueueInfo.setOrderStatus("04");
			// 01-是否分配 否
			pdAmountOrderQueueInfo.setQueueIsDistribute("01");
			// 成立时间
			pdAmountOrderQueueInfo.setOrderDate(DateUtils.getCurrentTimestamp());
			if (pdAmountOrderQueueInfo.getPdAmountOrderQueueInfoId() == null) {
				// 排队表
				Long pdAmountOrderQueueInfoId = commonService.getSeqValByName("SEQ_T_PD_AMOUNT_ORDER_QUEUE_INFO");
				pdAmountOrderQueueInfo.setPdAmountOrderQueueInfoId(pdAmountOrderQueueInfoId);
				pdAmountOrderQueueInfoMapper.insert(pdAmountOrderQueueInfo);
			} else {
				pdAmountOrderQueueInfoMapper.updateByPrimaryKey(pdAmountOrderQueueInfo);
			}

			resultInfo.setSuccess(true);
			resultInfo.setMsg("剩余份额不足！加入预约排队队列成功！您排在第 " + queueNo + " 位！");
			return resultInfo;
		}
		// 判断预约额度是否符合产品设置要求，主要校验起投金额，投资限额，递增金额
		BigDecimal startInvestMoney = pdWealth.getStartInvestMoney();
		BigDecimal investLimitMoney = pdWealth.getInvestLimitMoney();
		BigDecimal investIncreaseMoney = pdWealth.getInvestIncreaseMoney();
		double startInvestAmount = startInvestMoney != null ? startInvestMoney.doubleValue() : 0;
		double investLimitAmount = investLimitMoney != null ? investLimitMoney.doubleValue() : 0;
		double investIncreaseAmount = investIncreaseMoney != null ? investIncreaseMoney.doubleValue() : 0;
		if (startInvestAmount > 0 && orderAmount < startInvestAmount) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("本产品起投金额为" + startInvestMoney.toString() + ",投资金额不能小于起投金额！");
			return resultInfo;
		}
		if (investLimitAmount > 0 && investLimitAmount < orderAmount) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("本产品投资限额为" + investLimitMoney.toString() + ",投资金额不能超过投资限额！");
			return resultInfo;
		}
		if (investIncreaseAmount > 0 && (orderAmount - startInvestAmount) % investIncreaseAmount != 0) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("本产品起投金额为" + startInvestMoney.toString() + ",递增金额为" + investIncreaseMoney.toString()
					+ ",预约金额不符合要求！");
			return resultInfo;
		}
		// 01-预约未打款，详见码表custOrderStatus
		pdAmountOrderInfo.setOrderStatus("01");
		if (pdAmountOrderInfo.getPdAmountOrderInfoId() == null) {
			Long pdAmountOrderInfoId = commonService.getSeqValByName("SEQ_T_PD_AMOUNT_ORDER_INFO");
			pdAmountOrderInfo.setPdAmountOrderInfoId(pdAmountOrderInfoId);
			logger.info("================开始生成交易信息=============");
			// 预约分配动作完成则生成一条交易信息，保存到相应的表里
			// productId
			Long productId = pdProduct.getProductId();
			// 客户custBaseInfoId
			Long custBaseInfoId = pdAmountOrderInfo.getCustBaseInfoId();
			if(custBaseInfoId == null || "".equals(custBaseInfoId)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请先在客户维护菜单中新增客户信息，后选择客户，不支持手打！");
				return resultInfo;
			}
			// AgentId
			Long agentId = pdAmountOrderInfo.getAgentId();
			// ComId
			Long comId = pdAmountOrderInfo.getComId();
			// 生成基本信息t_trade_info
			String tradeInfoNo = commonService.createTradeNo("1"); // 生成交易号
			logger.info("================交易号tradeInfoNo:" + tradeInfoNo);
			TradeInfo tradeInfo = new TradeInfo();
			if (tradeInfoNo != null && !tradeInfoNo.equals("")) {
				Long tradeInfoId = commonService.getSeqValByName("SEQ_T_TRADE_INFO");
				tradeInfo.setTradeInfoId(tradeInfoId);
				tradeInfo.setTradeNo(tradeInfoNo); // 交易号
				tradeInfo.setAgentId(agentId);
				tradeInfo.setCompanyId(comId);
				tradeInfo.setTradeComId(comId); // 交易机构
				tradeInfo.setCurrency("CNY"); // 币种
				tradeInfo.setTradeTotalAssets(new BigDecimal(orderAmount)); // 认购金额
				tradeInfo.setTradeType("1");
				tradeInfo.setTradeDate(new Date());//交易日期
				tradeInfo.setTradeStaus(Constants.TRADE_STATUS_INPUT); // 交易状态01：录入中
				tradeInfo.setInputOperator(loginInfo.getUserCode());
				BeanUtils.insertObjectSetOperateInfo(tradeInfo, loginInfo);
				tradeInfoMapper.insertSelective(tradeInfo);
			} else {
				throw new CisCoreException("生成交易号失败！");
			}
			Long tradeInfoId = tradeInfo.getTradeInfoId();
			logger.info("================交易流水号号TradeInfoId:" + tradeInfoId);
			// 生成预约单，保存
			pdAmountOrderInfo.setTradeInfoId(tradeInfoId);
			pdAmountOrderInfoMapper.insert(pdAmountOrderInfo);
			// 生成交易客户信息（客户和交易的关联关系）t_trade_cust_info
			TradeCustInfo tradeCustInfo = new TradeCustInfo();
			Long tradeCustInfoIdDecimal = commonService.getSeqValByName("SEQ_T_TRADE_CUST_INFO");
			tradeCustInfo.setTradeCustInfoId(tradeCustInfoIdDecimal);
			tradeCustInfo.setCustBaseInfoId(custBaseInfoId);
			tradeCustInfo.setTradeInfoId(tradeInfo.getTradeInfoId());
			BeanUtils.insertObjectSetOperateInfo(tradeCustInfo, loginInfo);
			tradeCustInfoMapper.insert(tradeCustInfo);

			// 保存交易账户信息，全部的账户信息都进行保存；准客户就不保存
			logger.info("================保存交易账户信息=============");
			CustAccInfoExample custAccInfoExample = new CustAccInfoExample();
			custAccInfoExample.createCriteria().andCustBaseInfoIdEqualTo(custBaseInfoId)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAccInfo> custAccInfoList = custAccInfoMapper.selectByExample(custAccInfoExample);
			if (custAccInfoList != null && custAccInfoList.size() > 0) {
				for (CustAccInfo custAccInfoTemp : custAccInfoList) {
					TradeCustAccRela tradeCustAccRelaTemp = new TradeCustAccRela();
					tradeCustAccRelaTemp.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
					logger.info("================客户交易关联流水号TradeCustInfoId:" + tradeCustInfo.getTradeCustInfoId());
					// 所有的账户取消设置为默认账户，防止在交易更新时出错.更新页面全部显示，再次选择设置默认账户。
					tradeCustAccRelaTemp.setTradeAccFlag(null);
					tradeCustAccRelaTemp.setCustAccInfoId(custAccInfoTemp.getCustAccInfoId());
					BeanUtils.insertObjectSetOperateInfo(tradeCustAccRelaTemp, loginInfo);
					tradeCustAccRelaMapper.insert(tradeCustAccRelaTemp);
				}
			}

			// 保存交易地址信息，该客户相关联的地址信息全部查出来进行保存;准客户就不保存
			CustAddressInfoExample custAddressInfoExample = new CustAddressInfoExample();
			custAddressInfoExample.createCriteria().andCustBaseInfoIdEqualTo(custBaseInfoId)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAddressInfo> custAddressInfosList = custAddressInfoMapper.selectByExample(custAddressInfoExample);
			if (custAddressInfosList != null && custAddressInfosList.size() > 0) {
				for (CustAddressInfo custAddressInfoTemp : custAddressInfosList) {
					TradeCustAddressRela tradeCustAddressRelaTemp = new TradeCustAddressRela();
					tradeCustAddressRelaTemp.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
					// 所有的地址取消设置为默认账户，防止在交易更新时出错
					tradeCustAddressRelaTemp.setTradeAddressFlag(null);
					tradeCustAddressRelaTemp.setCustAddressInfoId(custAddressInfoTemp.getCustAddressInfoId());
					BeanUtils.insertObjectSetOperateInfo(tradeCustAddressRelaTemp, loginInfo);
					tradeCustAddressRelaMapper.insert(tradeCustAddressRelaTemp);
				}
			}

			// 保存财富产品要素信息,查询录入项信息
			TradeProductInfo tradeProductInfo = new TradeProductInfo();
			PDFactorExample pdFactorExample = new PDFactorExample();
			pdFactorExample.createCriteria().andPdIdEqualTo(productId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDFactor> pdFactorsList = pdFactorMapper.selectByExample(pdFactorExample);
			// 设置TradeProductInfo信息
			tradeProductInfo.setTradeProductInfoId(commonService.getSeqValByName("SEQ_T_TRADE_PRODUCT_INFO"));
			tradeProductInfo.setTradeInfoId(tradeInfoId);
			tradeProductInfo.setProductId(productId);
			if (pdFactorsList != null && pdFactorsList.size() > 0) {
				// 获取录入项信息
				for (PDFactor pdFactorTemp : pdFactorsList) {
					String factorCode = pdFactorTemp.getFactorCode();
					tradeProductInfo.setParamCode(factorCode);
					if (factorCode.equals("subscriptionFee")) {
						// 设置认购金额录入项信息
						tradeProductInfo.setParamValue(String.valueOf(orderAmount));
					} else {
						tradeProductInfo.setParamValue(null);
					}
					BeanUtils.insertObjectSetOperateInfo(tradeProductInfo, loginInfo);
					tradeProductInfoMapper.insertSelective(tradeProductInfo);
				}
			}
		} else {
			pdAmountOrderInfoMapper.updateByPrimaryKey(pdAmountOrderInfo);
		}
		//给rm发送预约成功邮件
		resultInfo = sendProductOrderEmail(pdAmountOrderInfo,loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品预约成功！是否打开交易页面？");
		resultInfo.setObj(JsonUtils.objectToMap(pdAmountOrderInfo));
		return resultInfo;
	}

	/**
	 * 获取预约信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid getAllPdAmountOrderInfo(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		// 登录信息
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());// 获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());// 登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());// 登陆用户ID
		// 获取符合条件的行数
		Integer total = productOrderServiceMapper.getAllPdAmountOrderInfoCount(paramMap);
		List<Map> resultList = productOrderServiceMapper.getAllPdAmountOrderInfo(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 获取队列预约信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid getRemainAmountOrderInfo(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex = ((dgm.getPage() - 1) * dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		// 登录信息
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());// 获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());// 登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());// 登陆用户ID
		// 获取符合条件的行数
		Integer total = productOrderServiceMapper.getRemainAmountOrderInfoCount(paramMap);
		List<Map> resultList = productOrderServiceMapper.getRemainAmountOrderInfo(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 撤销预约信息,撤销预约未打款的
	 */
	/*
	 * @Override//标志该方法是重写的
	 * 
	 * @Transactional//spring事物注解 public ResultInfo
	 * deletepdAmountOrderInfo(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo
	 * loginInfo) { ResultInfo resultInfo = new ResultInfo(); // 1.判断预约记录是否为空
	 * if(pdAmountOrderInfo==null){ resultInfo.setSuccess(false);
	 * resultInfo.setMsg("预约记录为空！"); return resultInfo; } // 2.预约记录不为空 try {
	 * pdAmountOrderInfo =
	 * pdAmountOrderInfoMapper.selectByPrimaryKey(pdAmountOrderInfo.
	 * getPdAmountOrderInfoId()); String OrderStatus =
	 * pdAmountOrderInfo.getOrderStatus();
	 * if(OrderStatus!=null&&!"".equals(OrderStatus)&&"01".equals(OrderStatus)){
	 * pdAmountOrderInfo.setOrderStatus("03");
	 * BeanUtils.updateObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
	 * pdAmountOrderInfoMapper.updateByPrimaryKey(pdAmountOrderInfo);
	 * resultInfo.setSuccess(true); resultInfo.setMsg("撤销完成！"); }else
	 * if(OrderStatus!=null&&!"".equals(OrderStatus)&&"02".equals(OrderStatus)){
	 * resultInfo.setSuccess(false); resultInfo.setMsg("该预约已经审核通过，不能撤销！"); }else
	 * if(OrderStatus!=null&&!"".equals(OrderStatus)&&"03".equals(OrderStatus)){
	 * resultInfo.setSuccess(false); resultInfo.setMsg("该预约已经失效，不能撤销！"); } }
	 * catch (Exception e) { resultInfo.setSuccess(false);
	 * resultInfo.setMsg("预约撤销出现异常！"); e.printStackTrace();
	 * 
	 * } return resultInfo; }
	 */

	/**
	 * 查询客户预约产品明细信息
	 * 
	 * @param pdAmountOrderInfoId
	 * @return
	 */
	public ResultInfo getCustProductAmountOrderInfo(String pdAmountOrderInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			PDAmountOrderInfo pdAmountOrderInfo = pdAmountOrderInfoMapper
					.selectByPrimaryKey(new Long(pdAmountOrderInfoId));
			resultInfo.setSuccess(true);
			resultInfo.setObj(JsonUtils.objectToMap(pdAmountOrderInfo));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 查询队列客户预约产品明细信息
	 * 
	 * @param pdAmountOrderInfoId
	 * @return
	 */
	public ResultInfo getCustRemainAmountOrderInfo(String pdAmountOrderQueueInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			PDAmountOrderQueueInfo pdAmountOrderQueueInfo = pdAmountOrderQueueInfoMapper
					.selectByPrimaryKey(new Long(pdAmountOrderQueueInfoId));
			resultInfo.setSuccess(true);
			resultInfo.setObj(JsonUtils.objectToMap(pdAmountOrderQueueInfo));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 获取邀请码
	 * 
	 * @return
	 */
	public ResultInfo getInviteCode() {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 生成长度为6位的邀请码，有数字、大写字母、小写字母组成
			String inviteCode = commonService.createInviteCode(6);

			PDAmountOrderInfoExample pdAmountOrderInfoExample = new PDAmountOrderInfoExample();
			PDAmountOrderInfoExample.Criteria criteria = pdAmountOrderInfoExample.createCriteria();
			criteria.andInviteCodeEqualTo(inviteCode).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDAmountOrderInfo> pdAmountOrderInfoList = pdAmountOrderInfoMapper
					.selectByExample(pdAmountOrderInfoExample);
			if (pdAmountOrderInfoList.size() > 0) {
				resultInfo = getInviteCode();
			} else {
				resultInfo.setSuccess(true);
				resultInfo.setObj(inviteCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取邀请码出错，" + e.getMessage());
		}
		return resultInfo;
	}

	@Transactional
	public List<Map<String, String>> comNameQuery() {
		// 查出符合参数类别的公司记录
		DefComExample defComExample = new DefComExample();
		defComExample.createCriteria().andRcStateEqualTo("E");
		List<DefCom> defComList = defComMapper.selectByExample(defComExample);
		// 遍历查所有记录，取出记录中分公司代码与名称
		List<Map<String, String>> comMapList = new ArrayList<Map<String, String>>();
		if (defComList.size() > 0) {
			for (DefCom defCom : defComList) {
				Map<String, String> comMap = new HashMap<String, String>();
				String comId = defCom.getComId().toString();
				String comName = defCom.getComName();
				comMap.put("code", comId);
				comMap.put("codeName", comId + "-" + comName);
				comMapList.add(comMap);
			}
		}
		return comMapList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map getProductOrderDetailInfo(Map paramMap,LoginInfo loginInfo)  throws Exception{
				// 输出日志，便于调试
				logger.info("Service层方法getProductOrderDetailInfo()接受参数个数:" + paramMap.size());
				// 登录信息
				paramMap.put("rolePrivilege", loginInfo.getRolePivilege());// 获取用户权限
				paramMap.put("operComId", loginInfo.getComId().toString());// 登陆用户所属管理机构ID
				paramMap.put("createUserId", loginInfo.getUserId().toString());// 登陆用户ID
				// Mapper接口方法：统计业务报表相关信息
				List<Map> businessStatisticsList = this.productOrderServiceMapper.getProductOrderDetailInfo(paramMap);
						/*getProductOrderDetailInfo(paramMap);*/
				List<Map> dataList = new ArrayList<Map>();
				for(Map productOrderMap :businessStatisticsList){
					Map queryMap =new HashMap();
					String custBaseInfoId=productOrderMap.get("custBaseInfoId").toString();
					if("".equals(custBaseInfoId)){
						dataList.add(productOrderMap);
						continue;
					}
					queryMap.put("custBaseInfoId", custBaseInfoId);
					
					//查询客户已购产品
					List<Map<String, String>> productList=productOrderServiceMapper.getProductsByCustId(queryMap);
					String productStr="";
					Double amount=0.0;
					if(productList.size()>0){
						for(Map productMap : productList){
							String productName=productMap.get("productName").toString();
							Double subscriptionFee=Double.parseDouble(productMap.get("subscriptionFee").toString());
							productStr=productStr+productName;
							amount= amount +subscriptionFee;
						}
					}
					productOrderMap.put("boughtProducts", productStr);
					productOrderMap.put("boughtAmount",amount);
					dataList.add(productOrderMap);
				}
				
				Map<String, Object> datas = new HashMap<String, Object>();
			    datas.put("data", dataList);
			    return datas;
	}
	/*public ResultInfo getProductOrderDetailInfo(Map paraMap) {
		ResultInfo resultInfo = new ResultInfo();
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
		try {
			List<Map> productOrderDetailList = productOrderServiceMapper.getProductOrderDetailInfo(paraMap);
			if (productOrderDetailList != null) {
				// 生成Excel
				Map<String, Object> reportMap = commonService.getReportInfo("ProductOrderDetail");
				// 对sheet名称加上当前月份
				String reportName = reportMap.get("reportName").toString();
				reportMap.put("reportName", reportName);
				if (reportMap != null && reportMap.size() > 0 && productOrderDetailList != null
						&& productOrderDetailList.size() > 0) {
					reportMap.put("reportData", productOrderDetailList);
					reportList.add(reportMap);
				}
				resultInfo.setObj(reportList);
				resultInfo.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导出预约信息出现异常！");
		}
		return resultInfo;
	}*/

	/**
	 * 判断客户在一年之内是否已购买过物业宝产品，客户要求按中文名判断，所以这才会按中文名判断，
	 * 本来想在产品定义中添加产品系列这个属性的，客户嫌麻烦，不让加，所以不要怀疑我这代码有问题,找IT张总也确认过此问题，说就按名称来，于是我妥协了
	 * 
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo isHadBuyWYBProduct(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 获取产品信息
			String productId = paramMap.get("productId").toString();
			PDProduct pdProduct = pdProductMapper.selectByPrimaryKey(new Long(productId));
			String productName = pdProduct.getProductName();
			if (!productName.contains("物业宝") && !productName.contains("物业定期宝")) {
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			PDWealthExample pdWealthExample = new PDWealthExample();
			PDWealthExample.Criteria pdWealthCriteria = pdWealthExample.createCriteria();
			pdWealthCriteria.andProductIdEqualTo(new Long(productId)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			PDWealth pdWealth = pdWealthMapper.selectByExample(pdWealthExample).get(0);
			String foundDate = DateUtils.getDateString(pdWealth.getFoundDate());
			paramMap.put("foundDate", foundDate);
			List<Map> productList = productOrderServiceMapper.getCustHadBuyWYBProductCount(paramMap);
			if (productList == null || productList.size() == 0) {
				resultInfo.setSuccess(true);
				return resultInfo;
			} else {
				StringBuffer buyInfo = new StringBuffer();
				buyInfo.append("[");
				for (Map productMap : productList) {
					String buyProductName = productMap.get("productName").toString();
					String buyProductFoundDate = productMap.get("foundDate").toString();
					buyInfo.append("产品名称：");
					buyInfo.append(buyProductName);
					buyInfo.append(",成立日：");
					buyInfo.append(buyProductFoundDate);
					buyInfo.append("；");
				}
				buyInfo = new StringBuffer(buyInfo.substring(0, buyInfo.length() - 1));
				buyInfo.append("]");
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该客户在一年之内购买过如下物业宝产品：" + buyInfo.toString());
			}
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("判断该客户是否购买过物业宝产品出现异常！");
		}
		return resultInfo;
	}

	/*@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo productOrderQueryInfo(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
		try {
			List<Map> productOrderDetailList = productOrderServiceMapper.exportPdOrderQueryInfo(paramMap);
			if (productOrderDetailList != null) {
				// 生成Excel
				Map<String, Object> reportMap = commonService.getReportInfo("ProductOrderDetail");
				// 对sheet名称加上当前月份
				String reportName = reportMap.get("reportName").toString();
				reportMap.put("reportName", reportName);
				if (reportMap != null && reportMap.size() > 0 && productOrderDetailList != null
						&& productOrderDetailList.size() > 0) {
					reportMap.put("reportData", productOrderDetailList);
					reportList.add(reportMap);
				}
				resultInfo.setObj(reportList);
				resultInfo.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导出预约信息出现异常！");
		}
		return resultInfo;
	}*/

	/**
	 * 
	 * 导出产品预约排队信息
	 * @author ZYM 2017-05-10
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map productOrderQueueInfo(Map paramMap) throws Exception {
		// 输出日志，便于调试
		logger.info("Service层方法productOrderQueueInfo()接受参数个数:" + paramMap.size());
		// Mapper接口方法：统计业务报表相关信息
		List<Map<String, String>> businessStatisticsList = this.productOrderServiceMapper
				.queryProductOrderQueueDetailList(paramMap);
		List<Map> dataList = new ArrayList<Map>();
		//将查出来的数据每条都插入该客户的所有已购产品和认购总额
		for (Map productOrderQueueMap : businessStatisticsList) {
				Map queryMap = new HashMap();
				String custBaseInfoId = productOrderQueueMap.get("custBaseInfoId").toString();
					if ("".equals(custBaseInfoId)) {
						dataList.add(productOrderQueueMap);
						continue;
					}
				queryMap.put("custBaseInfoId", custBaseInfoId);
				//查询某客户所有易购产品
				List<Map<String, String>> productList = productOrderServiceMapper.getProductsByCustId(queryMap);
				String productStr = "";
				Double amount = 0.0;
				if (productList.size() > 0) {
					//将所有产品的列表拿出来 拼到一起 金额也加到一起
					for (Map productMap : productList) {
						String productName = productMap.get("productName").toString();
						Double subscriptionFee = Double.parseDouble(productMap.get("subscriptionFee").toString());
						productStr = productStr + productName;
						amount = amount + subscriptionFee;
					}
				}
				productOrderQueueMap.put("boughtProducts", productStr);
				productOrderQueueMap.put("boughtAmount", amount);
				dataList.add(productOrderQueueMap);
		}
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("data", dataList);
		return datas;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getPdOrderTotalAmount(Map paramMap, LoginInfo loginInfo) {
		String operate = paramMap.get("operate").toString();
		ResultInfo resultInfo = new ResultInfo();
		if (operate != null && !"".equals(operate) && "pdOrderQuery".equals(operate)) {
			paramMap.put("rolePrivilege", loginInfo.getRolePivilege());// 获取用户权限
			paramMap.put("operComId", loginInfo.getComId().toString());// 登陆用户所属管理机构ID
			paramMap.put("createUserId", loginInfo.getUserId().toString());// 登陆用户ID
			BigDecimal orderTotalAmount = productOrderServiceMapper.getPdOrderTotalAmount(paramMap);
			if (orderTotalAmount == null) {
				resultInfo.setObj("0");
			} else {
				resultInfo.setObj(orderTotalAmount.toString());
			}
		} else if (operate != null && !"".equals(operate) && "pdOrderAudit".equals(operate)) {
			paramMap.put("rolePrivilege", loginInfo.getRolePivilege());// 获取用户权限
			paramMap.put("operComId", loginInfo.getComId().toString());// 登陆用户所属管理机构ID
			paramMap.put("createUserId", loginInfo.getUserId().toString());// 登陆用户ID
			BigDecimal orderTotalAmount = productOrderServiceMapper.getPdOrderAuditOrderTotalAmount(paramMap);
			if (orderTotalAmount == null) {
				resultInfo.setObj("0");
			} else {
				resultInfo.setObj(orderTotalAmount.toString());
			}
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 根据邀请码与手机号查询产品预约状态及预约额度
	 * 
	 * @author LIWENTAO
	 * @param
	 * @return
	 * @category EwealthWebService
	 */
	public ResultInfo getProductOrderInfoByMobileNumAndInviteCode(Map<String, Object> paraMap) {
		// 返回信息与状态
		ResultInfo resultInfo = new ResultInfo();
		//
		try {
			// PdAmountOrderInfoExample pdAmountOrderInfoExample = new
			// PdAmountOrderInfoExample();
			// PdAmountOrderInfoExample.Criteria criteria =
			// pdAmountOrderInfoExample.createCriteria();
			// 设置查询条件为请求报文中邀请码与手机号
			// criteria.andInviteCodeEqualTo((String)paraMap.get("inviteCode"))
			// .andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
			// .andMobileEqualTo((String)paraMap.get("mobile"));
			// 查询结果为List类型
			// List<PdAmountOrderInfo> pdAmountOrderInfoList =
			// pdAmountOrderInfoMapper.selectByExample(pdAmountOrderInfoExample);
			List<LinkedHashMap<String, String>> pdAmountOrderInfoList = queryCustomerServiceMapper
					.queryProductOrderInfoByMobileNumAndInviteCode(paraMap);
			if (0 == pdAmountOrderInfoList.size()) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("根据手机号与邀请码未查到任何数据");
			} else if (1 < pdAmountOrderInfoList.size()) {
				resultInfo.setSuccess(false);
				resultInfo.setObj("根据手机号与邀请码查到多条数据");
			} else {
				if (pdAmountOrderInfoList.get(0).containsKey("orderState")
						&& pdAmountOrderInfoList.get(0).containsKey("TradeAmount")
						&& pdAmountOrderInfoList.get(0).containsKey("ProductCode")
						&& pdAmountOrderInfoList.get(0).containsKey("AgentId")) {
					resultInfo.setSuccess(true);
					resultInfo.setMsg("根据手机号与邀请码成功查询到唯一记录");
					resultInfo.setObj(pdAmountOrderInfoList.get(0));
				} else {
					resultInfo.setSuccess(false);
					resultInfo.setObj("此单产品额度预约信息不全，请补齐：产品编号，理财师编号，预约额度，预约状态");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("根据手机号与邀请码获取产品预约额度信息时出错，" + e.getMessage());
			return resultInfo;
		}
		return resultInfo;
	}

	/**
	 * 根据邀请码查询产品预约状态及预约额度
	 * 
	 * @author LIWENTAO
	 * @param
	 * @return
	 * @category EwealthWebService
	 */
	public ResultInfo getProductOrderInfoByInviteCode(Map<String, Object> paraMap) {
		// 返回信息与状态
		ResultInfo resultInfo = new ResultInfo();
		//
		try {
			// PdAmountOrderInfoExample pdAmountOrderInfoExample = new
			// PdAmountOrderInfoExample();
			// PdAmountOrderInfoExample.Criteria criteria =
			// pdAmountOrderInfoExample.createCriteria();
			// 设置查询条件为请求报文中邀请码与手机号
			// criteria.andInviteCodeEqualTo((String)paraMap.get("inviteCode"))
			// .andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
			// .andMobileEqualTo((String)paraMap.get("mobile"));
			// 查询结果为List类型
			// List<PdAmountOrderInfo> pdAmountOrderInfoList =
			// pdAmountOrderInfoMapper.selectByExample(pdAmountOrderInfoExample);
			List<LinkedHashMap<String, String>> pdAmountOrderInfoList = queryCustomerServiceMapper
					.queryProductOrderInfoByInviteCode(paraMap);
			if (0 == pdAmountOrderInfoList.size()) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("根据手机号与邀请码未查到任何数据");
			} else if (1 < pdAmountOrderInfoList.size()) {
				resultInfo.setSuccess(false);
				resultInfo.setObj("根据手机号与邀请码查到多条数据");
			} else {
				if (pdAmountOrderInfoList.get(0).containsKey("orderState")
						&& pdAmountOrderInfoList.get(0).containsKey("tradeAmount")
						&& pdAmountOrderInfoList.get(0).containsKey("productCode")
						&& pdAmountOrderInfoList.get(0).containsKey("agentId")) {
					resultInfo.setSuccess(true);
					resultInfo.setMsg("根据手机号与邀请码成功查询到唯一记录");
					resultInfo.setObj(pdAmountOrderInfoList.get(0));
				} else {
					resultInfo.setSuccess(false);
					resultInfo.setObj("此单产品额度预约信息不全，请补齐：产品编号，理财师编号，预约额度，预约状态");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("根据手机号与邀请码获取产品预约额度信息时出错，" + e.getMessage());
			return resultInfo;
		}
		return resultInfo;
	}

	/**
	 * 
	 * @param pdAmountOrderInfo
	 * @param loginInfo
	 * @return
	 * @category EwealthWebservice
	 */
	@Transactional
	public ResultInfo updatePdOrderStatus(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 1.参数判断
		if (pdAmountOrderInfo == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("预约记录为空，不能提交审核！");
			return resultInfo;
		}
		// 2.若传入参数不为空， 进行业务处理
		try {
			// 请求报文中订单状态
			String orderStatusInRequestXml = pdAmountOrderInfo.getOrderStatus();
			// 数据库中原有订单状态
			pdAmountOrderInfo = pdAmountOrderInfoMapper.selectByPrimaryKey(pdAmountOrderInfo.getPdAmountOrderInfoId());

			if ("02".equals(orderStatusInRequestXml)) {
				pdAmountOrderInfo.setOrderStatus("02");
				BeanUtils.updateObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
				pdAmountOrderInfoMapper.updateByPrimaryKey(pdAmountOrderInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("预约状态改为已打款！");
			} else if ("03".equals(orderStatusInRequestXml)) {
				pdAmountOrderInfo.setOrderStatus("03");
				BeanUtils.updateObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
				pdAmountOrderInfoMapper.updateByPrimaryKey(pdAmountOrderInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("预约状态改为已失效！");
			}
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			e.printStackTrace();
		}
		return resultInfo;
	}

	/**
	 * 获取队列客户机构、产品、理财经理的相关信息
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo getQueueProductAndComInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String comId = paramMap.get("comId").toString();
			String productId = paramMap.get("productId").toString();
			String expectOpenDay = paramMap.get("expectOpenDay").toString();
			String pdAmountOrderQueueInfoId = null;
			if (paramMap.containsKey("pdAmountOrderQueueInfoId")) {
				pdAmountOrderQueueInfoId = paramMap.get("pdAmountOrderQueueInfoId").toString();
			}
			// 1.获取机构相关信息
			if (comId == null || "".equals(comId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取机构和产品信息出错,未获取到机构相关信息");
			}
			DefCom defCom = defComMapper.selectByPrimaryKey(new Long(comId));
			resultMap.put("comId", comId);
			resultMap.put("comName", defCom.getComName());
			// 2.获取产品相关信息
			PDProduct pdProduct = pdProductMapper.selectByPrimaryKey(new Long(productId));
			resultMap.put("productId", productId);
			resultMap.put("productCode", pdProduct.getProductCode());
			resultMap.put("productName", pdProduct.getProductName());
			// 产品类型及产品子类型
			String productType = pdProduct.getProductType();
			String productSubType = pdProduct.getProductSubType();
			// 获取财富产品信息
			PDWealthExample pdWealthExample = new PDWealthExample();
			PDWealthExample.Criteria pdWealthCriteria = pdWealthExample.createCriteria();
			pdWealthCriteria.andProductIdEqualTo(pdProduct.getProductId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			PDWealth pdWealth = pdWealthMapper.selectByExample(pdWealthExample).get(0);
			// 产品成立日
			resultMap.put("foundDate", DateUtils.getDateString(pdWealth.getFoundDate()));
			// 财富产品固定收益类
			if ("1".equals(productType) && ("01".equals(productSubType) || "02".equals(productSubType))) {
				// 封账日
				resultMap.put("sealingAccDate", DateUtils.getDateTimeString(pdWealth.getRaiseEndDate()));
				// 固定收益类设置开放日就为产品成立日
				// resultMap.put("expectOpenDay",
				// DateUtils.getDateString(pdWealth.getFoundDate()));
			}
			// 财富产品浮动收益类及股权类
			else if ("1".equals(productType) && ("03".equals(productSubType) || "04".equals(productSubType))) {
				PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
				PDWealthOpenDateExample.Criteria pdWealthOpenDateCriteria = pdWealthOpenDateExample.createCriteria();
				pdWealthOpenDateCriteria.andPdWealthIdEqualTo(pdWealth.getPdWealthId())
						.andOpenDateEqualTo(DateUtils.getDate(expectOpenDay))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				PDWealthOpenDate pdWealthOpenDate = pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample)
						.get(0);
				resultMap.put("sealingAccDate", DateUtils.getDateTimeString(pdWealthOpenDate.getInvestEndDate()));
				resultMap.put("expectOpenDay", DateUtils.getDateString(pdWealthOpenDate.getOpenDate()));
			}
			// 3.获取产品方相关信息
			AgencyCom agencyCom = agencyComMapper.selectByPrimaryKey(pdProduct.getAgencyComId());
			resultMap.put("agencyComId", agencyCom.getAgencyComId().toString());
			resultMap.put("agencyComName", agencyCom.getAgencyName());
			// 4.获取理财经理信息
			if (pdAmountOrderQueueInfoId != null && !"".equals(pdAmountOrderQueueInfoId)) {
				PDAmountOrderQueueInfo pdAmountOrderQueueInfo = pdAmountOrderQueueInfoMapper
						.selectByPrimaryKey(new Long(pdAmountOrderQueueInfoId));
				Agent agent = agentMapper.selectByPrimaryKey(pdAmountOrderQueueInfo.getAgentId());
				resultMap.put("agentId", agent.getAgentId().toString());
				resultMap.put("agentName", agent.getAgentName());
				resultMap.put("agentMobile", agent.getMobile());
			} else {
				AgentExample agentExample = new AgentExample();
				AgentExample.Criteria agentCriteria = agentExample.createCriteria();
				agentCriteria.andUserIdEqualTo(loginInfo.getUserId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<Agent> agentList = agentMapper.selectByExample(agentExample);
				resultMap.put("agentId", agentList.get(0).getAgentId().toString());
				resultMap.put("agentName", agentList.get(0).getAgentName());
				resultMap.put("agentMobile", agentList.get(0).getMobile());
			}

			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取机构和产品信息出错," + e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 获取预约剩余额度
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getRemainTotalAmount(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());// 获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());// 登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());// 登陆用户ID
		BigDecimal financingScale = productOrderServiceMapper.getProductFinancingScale(paramMap);
		BigDecimal orderAmount = productOrderServiceMapper.getRemainTotalAmount(paramMap);
		BigDecimal remainTotalAmount = financingScale.subtract(orderAmount);
		if (remainTotalAmount == null) {
			resultInfo.setObj("0");
		} else {
			resultInfo.setObj(remainTotalAmount.toString());
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 队列客户预约额度分配 cjj 2017/4/6
	 */
	@Override
	public ResultInfo submitQueueDistribute(String pdAmountOrderQueueInfoId, String amount, String foundDate,
			LoginInfo loginInfo) throws Exception {
		ResultInfo resultInfo = new ResultInfo();

		PDAmountOrderQueueInfo pdAmountOrderQueueInfo = pdAmountOrderQueueInfoMapper
				.selectByPrimaryKey(new Long(pdAmountOrderQueueInfoId));
		PDAmountOrderInfo pdAmountOrderInfo = new PDAmountOrderInfo();
		BeanUtils.copyProperties(pdAmountOrderQueueInfo, pdAmountOrderInfo);
		// productId
		Long productId = pdAmountOrderQueueInfo.getProductId();
		// 客户custBaseInfoId
		Long custBaseInfoId = pdAmountOrderQueueInfo.getCustBaseInfoId();
		// AgentId
		Long agentId = pdAmountOrderQueueInfo.getAgentId();
		// ComId
		Long comId = pdAmountOrderQueueInfo.getComId();
		// 更正预约表客户的实际预约额度
		pdAmountOrderInfo.setOrderAmount(new BigDecimal(amount));
		// 生成主键
		Long pdAmountOrderInfoId = commonService.getSeqValByName("SEQ_T_PD_AMOUNT_ORDER_INFO");
		pdAmountOrderInfo.setPdAmountOrderInfoId(pdAmountOrderInfoId);
		// 固收类产品期望开放日为空
		if (pdAmountOrderInfo.getExpectOpenDay() == null) {
			pdAmountOrderInfo.setExpectOpenDay(DateUtils.getDate(foundDate));
		}
		;

		logger.info("================开始生成交易信息=============");
		// 预约分配动作完成则生成一条交易信息，保存到相应的表里
		// 生成基本信息t_trade_info
		String tradeInfoNo = commonService.createTradeNo("1"); // 生成交易号
		logger.info("================交易号tradeInfoNo:" + tradeInfoNo);
		TradeInfo tradeInfo = new TradeInfo();
		if (tradeInfoNo != null && !tradeInfoNo.equals("")) {
			Long tradeInfoId = commonService.getSeqValByName("SEQ_T_TRADE_INFO");
			tradeInfo.setTradeInfoId(tradeInfoId);
			tradeInfo.setTradeNo(tradeInfoNo); // 交易号
			tradeInfo.setAgentId(agentId);
			tradeInfo.setCompanyId(comId);
			tradeInfo.setTradeComId(comId); // 交易机构
			tradeInfo.setCurrency("CNY"); // 币种
			tradeInfo.setTradeTotalAssets(new BigDecimal(amount)); // 认购金额
			tradeInfo.setTradeType("1");
			tradeInfo.setTradeStaus(Constants.TRADE_STATUS_INPUT); // 交易状态01：录入中
			tradeInfo.setInputOperator(loginInfo.getUserCode());
			BeanUtils.insertObjectSetOperateInfo(tradeInfo, loginInfo);
			tradeInfoMapper.insertSelective(tradeInfo);
		} else {
			throw new CisCoreException("生成交易号失败！");
		}
		// 交易号
		Long tradeInfoId = tradeInfo.getTradeInfoId();
		logger.info("================交易流水号号TradeInfoId:" + tradeInfoId);

		// 创建时间用户等信息，预约队列表
		BeanUtils.insertObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
		// 设置预约状态
		pdAmountOrderInfo.setOrderStatus("01");
		pdAmountOrderInfo.setTradeInfoId(tradeInfoId);
		pdAmountOrderInfoMapper.insert(pdAmountOrderInfo);
		// 设置排队表为已分配状态
		pdAmountOrderQueueInfo.setQueueIsDistribute("02");
		// 更正排队表客户实际预约额度
		pdAmountOrderQueueInfo.setOrderAmount(new BigDecimal(amount));
		BeanUtils.updateObjectSetOperateInfo(pdAmountOrderQueueInfo, loginInfo);
		pdAmountOrderQueueInfoMapper.updateByPrimaryKey(pdAmountOrderQueueInfo);

		// 生成交易客户信息（客户和交易的关联关系）t_trade_cust_info
		TradeCustInfo tradeCustInfo = new TradeCustInfo();
		Long tradeCustInfoIdDecimal = commonService.getSeqValByName("SEQ_T_TRADE_CUST_INFO");
		tradeCustInfo.setTradeCustInfoId(tradeCustInfoIdDecimal);
		tradeCustInfo.setCustBaseInfoId(custBaseInfoId);
		tradeCustInfo.setTradeInfoId(tradeInfo.getTradeInfoId());
		BeanUtils.insertObjectSetOperateInfo(tradeCustInfo, loginInfo);
		tradeCustInfoMapper.insert(tradeCustInfo);

		// 保存交易账户信息，全部的账户信息都进行保存；准客户就不保存
		logger.info("================保存交易账户信息=============");
		CustAccInfoExample custAccInfoExample = new CustAccInfoExample();
		custAccInfoExample.createCriteria().andCustBaseInfoIdEqualTo(custBaseInfoId)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustAccInfo> custAccInfoList = custAccInfoMapper.selectByExample(custAccInfoExample);
		logger.info("================客户交易关联流水号TradeCustInfoId:" + tradeCustInfo.getTradeCustInfoId());
		if (custAccInfoList != null && custAccInfoList.size() > 0) {
			for (CustAccInfo custAccInfoTemp : custAccInfoList) {
				TradeCustAccRela tradeCustAccRelaTemp = new TradeCustAccRela();
				tradeCustAccRelaTemp.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
				// 所有的账户取消设置为默认账户，防止在交易更新时出错.更新页面全部显示，再次选择设置默认账户。
				tradeCustAccRelaTemp.setTradeAccFlag(null);
				tradeCustAccRelaTemp.setCustAccInfoId(custAccInfoTemp.getCustAccInfoId());
				BeanUtils.insertObjectSetOperateInfo(tradeCustAccRelaTemp, loginInfo);
				tradeCustAccRelaMapper.insert(tradeCustAccRelaTemp);
			}
		}

		// 保存交易地址信息，该客户相关联的地址信息全部查出来进行保存；准客户就不保存
		CustAddressInfoExample custAddressInfoExample = new CustAddressInfoExample();
		custAddressInfoExample.createCriteria().andCustBaseInfoIdEqualTo(custBaseInfoId)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustAddressInfo> custAddressInfosList = custAddressInfoMapper.selectByExample(custAddressInfoExample);
		if (custAddressInfosList != null && custAddressInfosList.size() > 0) {
			for (CustAddressInfo custAddressInfoTemp : custAddressInfosList) {
				TradeCustAddressRela tradeCustAddressRelaTemp = new TradeCustAddressRela();
				tradeCustAddressRelaTemp.setTradeCustInfoId(tradeCustInfo.getTradeCustInfoId());
				// 所有的地址取消设置为默认账户，防止在交易更新时出错
				tradeCustAddressRelaTemp.setTradeAddressFlag(null);
				tradeCustAddressRelaTemp.setCustAddressInfoId(custAddressInfoTemp.getCustAddressInfoId());
				BeanUtils.insertObjectSetOperateInfo(tradeCustAddressRelaTemp, loginInfo);
				tradeCustAddressRelaMapper.insert(tradeCustAddressRelaTemp);
			}
		}

		// 保存财富产品要素信息,查询录入项信息
		TradeProductInfo tradeProductInfo = new TradeProductInfo();
		PDFactorExample pdFactorExample = new PDFactorExample();
		pdFactorExample.createCriteria().andPdIdEqualTo(productId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<PDFactor> pdFactorsList = pdFactorMapper.selectByExample(pdFactorExample);
		// 设置TradeProductInfo信息
		tradeProductInfo.setTradeProductInfoId(commonService.getSeqValByName("SEQ_T_TRADE_PRODUCT_INFO"));
		tradeProductInfo.setTradeInfoId(tradeInfoId);
		tradeProductInfo.setProductId(productId);
		if (pdFactorsList != null && pdFactorsList.size() > 0) {
			// 获取录入项信息
			for (PDFactor pdFactorTemp : pdFactorsList) {
				String factorCode = pdFactorTemp.getFactorCode();
				tradeProductInfo.setParamCode(factorCode);
				if (factorCode.equals("subscriptionFee")) {
					// 设置认购金额录入项信息
					tradeProductInfo.setParamValue(amount);
				} else {
					tradeProductInfo.setParamValue(null);
				}
				BeanUtils.insertObjectSetOperateInfo(tradeProductInfo, loginInfo);
				tradeProductInfoMapper.insertSelective(tradeProductInfo);
			}
		}
		//发送分配邮件
		resultInfo = sendProductOrderEmail(pdAmountOrderInfo, loginInfo);
		// 设置分配成功信息
		resultInfo.setSuccess(true);
		resultInfo.setMsg("分配成功！");

		return resultInfo;
	}

	/**
	 * 打款审核触发邮件
	 * 
	 * @param pdAmountOrderInfo
	 * @return
	 */
	private ResultInfo createAndSendProductOrderAuditEmail(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			resultInfo = casProductOrderAuditEmail(pdAmountOrderInfo, loginInfo);
			// 打款审核完成
			resultInfo.setSuccess(true);
			resultInfo.setMsg("发送到账邮件完成！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("到账邮件发送出现异常！");
		}
		return resultInfo;
	}

	/**
	 * 发送到账邮件
	 * 
	 * @param pdAmountOrderInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ResultInfo casProductOrderAuditEmail(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 先创建短信，然后发送
		resultInfo = createProductOrderAuditEmail(pdAmountOrderInfo, loginInfo);
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		List<SysEmailInfo> sysEmailInfoList = (List<SysEmailInfo>) resultInfo.getObj();
		// 发送短信
		for (SysEmailInfo sysEmailInfo : sysEmailInfoList) {
			resultInfo = sendProductOrderAuditEmail(sysEmailInfo, loginInfo);
		}
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 创建到账邮件
	 * 
	 * @param pdAmountOrderInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultInfo createProductOrderAuditEmail(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 获取所有在职员工邮箱地址
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andWorkStateEqualTo("1").andEmailIsNotNull().andRcStateEqualTo("E");
		List<Agent> agents = agentMapper.selectByExample(agentExample);
		List<SysEmailInfo> resultList = new ArrayList<SysEmailInfo>();
		// 邮件信息
		SysEmailInfo sysEmailInfo = null;
		for (int i = 0; i < agents.size(); i++) {
			sysEmailInfo = new SysEmailInfo();
			// 到账通知邮件类型
			sysEmailInfo.setEmailType("10");
			sysEmailInfo.setEmailTitle("到账通知");
			sysEmailInfo.setEmailRelationNo(pdAmountOrderInfo.getPdAmountOrderInfoId());
			sysEmailInfo.setEmailAddress(agents.get(i).getEmail());
			// 创建邮件内容
			Map paramMap = new HashMap();
			paramMap.put("pdAmountOrderInfoId", pdAmountOrderInfo.getPdAmountOrderInfoId());
			Map pdAmountrderMap = smsServiceMapper.getPdAmountrderMap(paramMap);
			sysEmailInfo.setEmailContent(
					"恭喜：" + pdAmountrderMap.get("agentName") + "预约的" + pdAmountrderMap.get("productName") + "产品，金额"
							+ pdAmountrderMap.get("orderAmount") + "元，已到账！请尽快进行交易录入，以确保认购成功！");
			// 01-未发送
			sysEmailInfo.setEmailStatus("01");
			sysEmailInfo.setEmailCreateTime(DateUtils.getCurrentTimestamp());
			BeanUtils.insertObjectSetOperateInfo(sysEmailInfo, loginInfo);
			sysEmailInfoMapper.insert(sysEmailInfo);
			resultList.add(sysEmailInfo);
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("到账邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
	}

	/**
	 * 发送到账短信
	 * 
	 * @param sysEmailInfo
	 * @param loginInfo
	 * @return
	 */
	private ResultInfo sendProductOrderAuditEmail(SysEmailInfo sysEmailInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		DefCodeExample defCodeExample = new DefCodeExample();
		DefCodeExample.Criteria defCodeCriteria = defCodeExample.createCriteria();
		defCodeCriteria.andCodeTypeEqualTo("sendMailParam");
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		Map<String, String> mailHostParam = new HashMap<String, String>();
		if (defCodeList != null && defCodeList.size() > 0) {
			for (DefCode defCode : defCodeList) {
				mailHostParam.put(defCode.getCodeAlias(), defCode.getCodeName());
			}
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到邮件发送服务器相关参数！");
			return resultInfo;
		}
		if (sysEmailInfo != null) {
			String address = sysEmailInfo.getEmailAddress();
			String title = sysEmailInfo.getEmailTitle();
			String content = sysEmailInfo.getEmailContent();
			resultInfo = sendEmailService.sendEmail(address, title, content, mailHostParam);
			if (resultInfo.isSuccess()) {
				sysEmailInfo.setEmailSendTime(DateUtils.getCurrentTimestamp());
				// 02-已发送
				sysEmailInfo.setEmailStatus("02");
			} else {
				// 03-发送失败
				sysEmailInfo.setEmailStatus("03");
			}
			BeanUtils.updateObjectSetOperateInfo(sysEmailInfo, loginInfo);
			sysEmailInfoMapper.updateByPrimaryKey(sysEmailInfo);
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 根据客户号查询客户基本信息
	 * 
	 * @author ZYM
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getCustomerInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 获取客户Id
		List<Map<String, String>> resultMap = productOrderServiceMapper.getCustomerInfo(paramMap);
		if (resultMap.size() > 0) {
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap.get(0));
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询客户信息出现问题！");
		}
		return resultInfo;
	}


	@Override
	public ResultInfo updateProductRemainAmount(Map paramMap, LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		String isDistribute = paramMap.get("isDistribute").toString();
		long productId = Long.parseLong(paramMap.get("productId").toString());
		long tradeInfoId = Long.parseLong(paramMap.get("tradeInfoId").toString());
		String pdAmountOrderInfoId = paramMap.get("pdAmountOrderInfoId").toString();
		String orderAmountStr = paramMap.get("orderAmount").toString();//预约额度
		double orderAmount = 0;
		//判断交易是否成立
		TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeInfoId);
		if ("10".equals(tradeInfo.getTradeStaus())) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该交易已经成立，不能修改!");
			return resultInfo;
		}
		if (orderAmountStr == null || "".equals(orderAmountStr)) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取预约额度错误，请重新输入!");
			return resultInfo;
		}else {
			orderAmount = Double.parseDouble(orderAmountStr); 
		}
		
		PDProduct pdProduct = pdProductMapper.selectByPrimaryKey(productId);
		String productSubType = pdProduct.getProductSubType();
		
		PDAmountOrderInfo pdAmountOrderInfo = pdAmountOrderInfoMapper.selectByPrimaryKey(Long.parseLong(pdAmountOrderInfoId));
		double existOrderAmount = pdAmountOrderInfo.getOrderAmount().doubleValue();
		
		PDWealthExample pdWealthExample = new PDWealthExample();
		PDWealthExample.Criteria pdWealthCriteria = pdWealthExample.createCriteria();
		pdWealthCriteria.andProductIdEqualTo(productId)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		PDWealth pdWealth = pdWealthMapper.selectByExample(pdWealthExample).get(0);
		BigDecimal totalAmount = new BigDecimal(0);
		//修改投资金额做判断,若没有修改金额，不做处理
		if((orderAmount - existOrderAmount) != 0){
			//额度缩小，直接判断金额是否满足要求,并修改数据;额度扩大时判断扩大的金额差去和剩余额度比较
			if ((orderAmount - existOrderAmount) > 0) {
				// 判断预约额度是否足够(没有将总额度分配到分公司的，以产品的融资规模为标准,分配到分公司的，将以分公司的额度为限制)
				if (isDistribute != null && !"".equals(isDistribute) && "Y".equals(isDistribute)) {
					PdAmountDisInfoExample pdAmountDisInfoExample = new PdAmountDisInfoExample();
					PdAmountDisInfoExample.Criteria pdAmountDiscriteria = pdAmountDisInfoExample.createCriteria();
					pdAmountDiscriteria.andProductIdEqualTo(productId).andComIdEqualTo(pdAmountOrderInfo.getComId())
							.andExpectOpenDayEqualTo(pdAmountOrderInfo.getExpectOpenDay())
							.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					PdAmountDisInfo pdAmountDisInfo = pdAmountDisInfoMapper.selectByExample(pdAmountDisInfoExample).get(0);
					// 该分公司的分配额度
					totalAmount = pdAmountDisInfo.getAmount();
				}else {
					String productType = pdProduct.getProductType();
					// 产品融资规模
					if (productType != null && !"".equals(productType) && "1".equals(productType) && productSubType != null
							&& !"".equals(productSubType) && ("01".equals(productSubType) || "02".equals(productSubType))) {
						totalAmount = pdWealth.getFinancingScale();
					} else if (productType != null && !"".equals(productType) && "1".equals(productType)
							&& productSubType != null && !"".equals(productSubType) && ("03".equals(productSubType) || "04".equals(productSubType))) {
						PDWealthOpenDateExample pdWealthOpenDateExample = new PDWealthOpenDateExample();
						PDWealthOpenDateExample.Criteria pdWealthOpenDateCriteria = pdWealthOpenDateExample.createCriteria();
						pdWealthOpenDateCriteria.andPdWealthIdEqualTo(pdWealth.getPdWealthId())
								.andOpenDateEqualTo(pdAmountOrderInfo.getExpectOpenDay())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						PDWealthOpenDate pdWealthOpenDate = pdWealthOpenDateMapper.selectByExample(pdWealthOpenDateExample)
								.get(0);
						totalAmount = pdWealthOpenDate.getFinancingScale();
					}
				}
				// 如果募集总额未配置，当0来处理
				if (totalAmount == null) {
					totalAmount = new BigDecimal(0);
				}
				List<String> orderStatusList = new ArrayList<String>();
				orderStatusList.add("01");
				orderStatusList.add("02");
				// 获取该分公司的已预约总额
				PDAmountOrderInfoExample pdAmountOrderInfoExample = new PDAmountOrderInfoExample();
				PDAmountOrderInfoExample.Criteria pdAmountOrderInfoCriteria = pdAmountOrderInfoExample.createCriteria();
				pdAmountOrderInfoCriteria.andProductIdEqualTo(pdAmountOrderInfo.getProductId())
						.andExpectOpenDayEqualTo(pdAmountOrderInfo.getExpectOpenDay()).andOrderStatusIn(orderStatusList)
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				// 如果额度分配到分公司，要取该分公司的预约总额
				if (isDistribute != null && !"".equals(isDistribute) && "Y".equals(isDistribute)) {
					pdAmountOrderInfoCriteria.andComIdEqualTo(pdAmountOrderInfo.getComId());
				}
				List<PDAmountOrderInfo> pdAmountOrderInfoList = pdAmountOrderInfoMapper
						.selectByExample(pdAmountOrderInfoExample);
				double existSumAmount = 0;
				for (PDAmountOrderInfo existPdAmountOrderInfo : pdAmountOrderInfoList) {
					existSumAmount += existPdAmountOrderInfo.getOrderAmount().doubleValue();
				}
				//剩余金额
				double remainAmount = totalAmount.doubleValue() - existSumAmount;
				//得到扩大的金额
				double expansionMoney = orderAmount - existOrderAmount;
				// 额度不够，进行提示
				if (remainAmount < expansionMoney) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("投资金额大于剩余金额,修改失败!");
					return resultInfo;
				}
			}
			//额度满足；判断预约额度是否符合产品设置要求，主要校验起投金额，投资限额，递增金额
			BigDecimal startInvestMoney = pdWealth.getStartInvestMoney();
			BigDecimal investLimitMoney = pdWealth.getInvestLimitMoney();
			BigDecimal investIncreaseMoney = pdWealth.getInvestIncreaseMoney();
			double startInvestAmount = startInvestMoney != null ? startInvestMoney.doubleValue() : 0;
			double investLimitAmount = investLimitMoney != null ? investLimitMoney.doubleValue() : 0;
			double investIncreaseAmount = investIncreaseMoney != null ? investIncreaseMoney.doubleValue() : 0;
			if (startInvestAmount > 0 && orderAmount < startInvestAmount) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("本产品起投金额为" + startInvestMoney.toString() + ",投资金额不能小于起投金额！");
				return resultInfo;
			}
			if (investLimitAmount > 0 && investLimitAmount < orderAmount) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("本产品投资限额为" + investLimitMoney.toString() + ",投资金额不能超过投资限额！");
				return resultInfo;
			}
			if (investIncreaseAmount > 0 && (orderAmount - startInvestAmount) % investIncreaseAmount != 0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("本产品起投金额为" + startInvestMoney.toString() + ",递增金额为" + investIncreaseMoney.toString()
						+ ",预约金额不符合要求！");
				return resultInfo;
			}
			//额度符合要求，修改额度  t_pd_amount_order_info      
			pdAmountOrderInfo.setOrderAmount(new BigDecimal(orderAmount));
			BeanUtils.updateObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
			pdAmountOrderInfoMapper.updateByPrimaryKey(pdAmountOrderInfo);
			//修改  t_trade_info 
			tradeInfo.setTradeTotalAssets(new BigDecimal(orderAmount));
			BeanUtils.updateObjectSetOperateInfo(tradeInfo, loginInfo);
			tradeInfoMapper.updateByPrimaryKey(tradeInfo);
			//修改  t_trade_product_info 
			TradeProductInfoExample tradeProductInfoExample = new TradeProductInfoExample();
			TradeProductInfoExample.Criteria tradeProductInfocriteria = tradeProductInfoExample.createCriteria();
			tradeProductInfocriteria.andTradeInfoIdEqualTo(tradeInfoId).andProductIdEqualTo(productId).andParamCodeEqualTo("subscriptionFee").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<TradeProductInfo> tradeProductInfoList = tradeProductInfoMapper.selectByExample(tradeProductInfoExample);
			if (tradeProductInfoList != null && tradeProductInfoList.size() > 0) {
				TradeProductInfo tradeProductInfo = tradeProductInfoList.get(0);
				tradeProductInfo.setParamValue(orderAmountStr);
				BeanUtils.updateObjectSetOperateInfo(tradeProductInfo, loginInfo);
				tradeProductInfoMapper.updateByPrimaryKey(tradeProductInfo);
			}
			//发送修改预约审核邮件
			resultInfo = sendUpdateProductOrderEmail(pdAmountOrderInfo,orderAmount,loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("保存成功!");
			return resultInfo;
		}
		
		resultInfo.setSuccess(true);
		resultInfo.setMsg("保存成功!");
		return resultInfo;
	}

	/**
	 * 
	 * 导出产品预约查询信息
	 * @author wanghao
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map productOrderQueryInfo(Map paramMap,LoginInfo loginInfo) throws Exception {
		// 输出日志，便于调试
		logger.info("Service层方法productOrderQueryInfo()接受参数个数:" + paramMap.size());
		// 登录信息
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());// 获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());// 登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());// 登陆用户ID
		// Mapper接口方法：统计业务报表相关信息
		List<Map<String, String>> businessStatisticsList = this.productOrderServiceMapper
				.exportPdOrderQueryInfo(paramMap);
		List<Map> dataList = new ArrayList<Map>();
		//将查出来的数据每条都插入该客户的所有已购产品和认购总额
		for (Map productOrderQueryMap : businessStatisticsList) {
				Map queryMap = new HashMap();
				String custBaseInfoId = productOrderQueryMap.get("custBaseInfoId").toString();
					if ("".equals(custBaseInfoId)) {
						dataList.add(productOrderQueryMap);
						continue;
					}
				queryMap.put("custBaseInfoId", custBaseInfoId);
				//查询某客户所有易购产品
				List<Map<String, String>> productList = productOrderServiceMapper.getProductsByCustId(queryMap);
				String productStr = "";
				Double amount = 0.0;
				if (productList.size() > 0) {
					//将所有产品的列表拿出来 拼到一起 金额也加到一起
					for (Map productMap : productList) {
						String productName = productMap.get("productName").toString();
						Double subscriptionFee = Double.parseDouble(productMap.get("subscriptionFee").toString());
						productStr = productStr + productName;
						amount = amount + subscriptionFee;
					}
				}
				productOrderQueryMap.put("boughtProducts", productStr);
				productOrderQueryMap.put("boughtAmount", amount);
				dataList.add(productOrderQueryMap);
		}
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("data", dataList);
		return datas;
	}

		//////////////////////////////////////////产品预约成功邮件/////////////////////////////////////////////
	
	/**
	 * 发送预约成功邮件
	 * @param pdAmountOrderInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendProductOrderEmail(PDAmountOrderInfo pdAmountOrderInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createProductOrderEmail(pdAmountOrderInfo, loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		SysEmailInfo sysEmailInfo = (SysEmailInfo)resultInfo.getObj();
		//发送短信
		resultInfo = sendEmail(sysEmailInfo,loginInfo);
		
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 创建预约成功邮件
	 * @param pdAmountOrderInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createProductOrderEmail(PDAmountOrderInfo pdAmountOrderInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//理财经理邮箱
		Long agentId = pdAmountOrderInfo.getAgentId();
		Agent agent = agentMapper.selectByPrimaryKey(agentId);
		String agentEmail = agent.getEmail();
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//预约成功通知邮件
		sysEmailInfo.setEmailType("12");
		sysEmailInfo.setEmailRelationNo(pdAmountOrderInfo.getTradeInfoId());
		sysEmailInfo.setEmailAddress(agentEmail);
		sysEmailInfo.setEmailTitle("产品预约成功通知");
		//创建邮件内容
		String mailContent = createProductOrderContent(pdAmountOrderInfo);
		sysEmailInfo.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo.setEmailStatus("01");
		sysEmailInfo.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品预约成功邮件创建成功！");
		resultInfo.setObj(sysEmailInfo);
		return resultInfo;
	}
	
	/**
	 * 生成预约成功邮件类容
	 * @param pdAmountOrderInfo
	 * @return
	 */
	private String createProductOrderContent(PDAmountOrderInfo pdAmountOrderInfo){
		StringBuffer mailContent = new StringBuffer();
		PDProduct pdProduct = pdProductMapper.selectByPrimaryKey(pdAmountOrderInfo.getProductId());
		mailContent.append("您于“");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		mailContent.append(df.format(new Date()));
		mailContent.append("”成功预约《");
		mailContent.append(pdProduct.getProductName());
		mailContent.append("》产品“");
		mailContent.append(pdAmountOrderInfo.getOrderAmount().divide(new BigDecimal("10000")));
		mailContent.append("万”，请知悉。");
		return mailContent.toString();
	}
	
	////////////////////////////////////////////产品预约修改邮件/////////////////////////////////////////////
	
	/**
	 * 发送预约修改邮件
	 * @param pdAmountOrderInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendUpdateProductOrderEmail(PDAmountOrderInfo pdAmountOrderInfo,Double orderAmount,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createUpdateProductOrderEmail(pdAmountOrderInfo, orderAmount,loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		SysEmailInfo sysEmailInfo = (SysEmailInfo)resultInfo.getObj();
		//发送短信
		resultInfo = sendEmail(sysEmailInfo,loginInfo);
		
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 创建预约成功邮件
	 * @param pdAmountOrderInfo
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo createUpdateProductOrderEmail(PDAmountOrderInfo pdAmountOrderInfo,Double orderAmount,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//理财经理邮箱
		Long agentId = pdAmountOrderInfo.getAgentId();
		Agent agent = agentMapper.selectByPrimaryKey(agentId);
		String agentEmail = agent.getEmail();
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//预约成功通知邮件
		sysEmailInfo.setEmailType("13");
		sysEmailInfo.setEmailRelationNo(pdAmountOrderInfo.getTradeInfoId());
		sysEmailInfo.setEmailAddress(agentEmail);
		sysEmailInfo.setEmailTitle("产品预约修改通知");
		//创建邮件内容
		String mailContent = createUpdateProductOrderContent(pdAmountOrderInfo,orderAmount);
		sysEmailInfo.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo.setEmailStatus("01");
		sysEmailInfo.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品预约修改邮件创建成功！");
		resultInfo.setObj(sysEmailInfo);
		return resultInfo;
	}
	
	/**
	 * 生成预约修改邮件类容
	 * @param pdAmountOrderInfo
	 * @return
	 */
	private String createUpdateProductOrderContent(PDAmountOrderInfo pdAmountOrderInfo,Double orderAmount){
		StringBuffer mailContent = new StringBuffer();
		PDProduct pdProduct = pdProductMapper.selectByPrimaryKey(pdAmountOrderInfo.getProductId());
		mailContent.append("您的预约“");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		mailContent.append(df.format(new Date()));
		mailContent.append("”修改为《");
		mailContent.append(pdProduct.getProductName());
		mailContent.append("》产品“");
		mailContent.append(orderAmount/10000);
		mailContent.append("万”，请知悉。");
		return mailContent.toString();
	}
	
	////////////////////////////////////////产品预约撤销邮件/////////////////////////////////
	/**
	 * 发送预约撤销邮件
	 * @param pdAmountOrderInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendProductOrderCancelEmail(PDAmountOrderInfo pdAmountOrderInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createProductOrderCancelEmail(pdAmountOrderInfo,loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		SysEmailInfo sysEmailInfo = (SysEmailInfo)resultInfo.getObj();
		//发送短信
		resultInfo = sendEmail(sysEmailInfo,loginInfo);
		
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 创建预约撤销邮件
	 * @param pdAmountOrderInfo
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo createProductOrderCancelEmail(PDAmountOrderInfo pdAmountOrderInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//理财经理邮箱
		Long agentId = pdAmountOrderInfo.getAgentId();
		Agent agent = agentMapper.selectByPrimaryKey(agentId);
		String agentEmail = agent.getEmail();
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//预约成功通知邮件
		sysEmailInfo.setEmailType("14");
		sysEmailInfo.setEmailRelationNo(pdAmountOrderInfo.getTradeInfoId());
		sysEmailInfo.setEmailAddress(agentEmail);
		sysEmailInfo.setEmailTitle("产品预约撤销通知");
		//创建邮件内容
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String now = df.format(new Date());
		String mailContent = "您的预约“"+now+"”被撤销";
		sysEmailInfo.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo.setEmailStatus("01");
		sysEmailInfo.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品预约撤销邮件创建成功！");
		resultInfo.setObj(sysEmailInfo);
		return resultInfo;
	}
	 		//////////////////////////产品预约公共发送方法//////////////////////////
	/**
	 * 发送邮件
	 * @return
	 */
	@Transactional
	private ResultInfo sendEmail(SysEmailInfo sysEmailInfo,LoginInfo loginInfo){
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
	
	
	//热门产品信息管理
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryHotPdOrderInfoList(DataGridModel dgm,
			Map paramMap, LoginInfo loginInfo) {
		DataGrid dataGrid = new DataGrid();
		try {
			if (paramMap==null) {
				paramMap = new HashMap();
			}
			paramMap.put("startIndex", dgm.getStartIndex());
			paramMap.put("endIndex", dgm.getEndIndex());
			paramMap.put("comId", loginInfo.getComId().toString());
			paramMap.put("currentTime", DateUtils.getDateTimeString(new Date()));
			// 获取符合条件的行数
			Integer total = productOrderServiceMapper.queryHotPdOrderInfoListCount(paramMap);
			List<Map> resultList = productOrderServiceMapper.queryHotPdOrderInfoList(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**修改热门产品状态
	 * @param loginInfo
	 * @return
	 * caijingjing
	 */
	@Override
	public ResultInfo cancelHotProductInfo(String productId, LoginInfo loginInfo)  throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		if (productId != null && !productId.equals("")) {
			//获取产品相关信息
			PDProduct pdProductInfo = pdProductMapper.selectByPrimaryKey(new Long(productId));
			pdProductInfo.setIsHot("02");
			BeanUtils.updateObjectSetOperateInfo(pdProductInfo, loginInfo);
			pdProductMapper.updateByPrimaryKey(pdProductInfo);
		}else {
			throw new CisCoreException("获取产品Id出现异常！");
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("修改热门产品状态成功！");
		return resultInfo;
	}
	
	/**
	 * 批量导入预约审核
	 * ZYM
	 * @return
	 * @throws IOException 
	 * @throws BiffException 
	 */
	@Override
	@Transactional
	public ResultInfo importPDAmountOrderAuditFile(MultipartFile[] importFileNameList,LoginInfo loginInfo) throws Exception {
			ResultInfo resultInfo = new ResultInfo();
			List<String> unUpLoadFile = new ArrayList<String>();
			if (importFileNameList==null||importFileNameList.length==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请选择需要上传的文件！");
				return resultInfo;
			}

			for(MultipartFile multipartFile:importFileNameList){
				// 获得文件名(全路径)
				String uploadFileName = multipartFile.getOriginalFilename();
				//获取文件后缀
				String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
				if(suffix==null||"".equals(suffix)){
					unUpLoadFile.add(uploadFileName+" : 文件类型未知");
					continue;
				}
				logger.info("====file suffix==="+suffix);
				//校验文件
				if(!".xls".equals(suffix)){
					unUpLoadFile.add(uploadFileName+" : 请将文件保存成2003版本");
					continue;
				}
				//得到文件的输入流，转换为WorkBook
				Workbook workbook = Workbook.getWorkbook(multipartFile.getInputStream());
				resultInfo = resolvePDAmountOrderAuditExcel(workbook, loginInfo);
			}
		if(unUpLoadFile.size()>0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg(JsonUtils.objectToJsonStr(unUpLoadFile));
								}
		return resultInfo;
	}
	
	/**
	 * 处理导入的文件流
	 * @param workbook
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo resolvePDAmountOrderAuditExcel(Workbook workbook,LoginInfo loginInfo) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		List<PDAmountOrderInfo> pdAmountOrderInfos = new ArrayList<PDAmountOrderInfo>();
			//只有一个工作表格，取第一个Sheet
			Sheet sheet = workbook.getSheet(0);
			//得到单元格，第一行为列名称，获取每列名称,放入Map中
			Map<String, String> colNameMap = new HashMap<String, String>();
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,i);
					String colName = cell.getContents();
					if (colName!=null&&!"".equals(colName)) {
						colNameMap.put(j+"", colName);
					}
				}
			}
			//总列数
			int colSize = colNameMap.size();
			//获取所有数据，从第二行开始，第一行为表头
            for (int i = 1; i < sheet.getRows(); i++) {
            	//产品名称
            	String productName = "";
            	//产品编码
            	String productCode = "";
            	//客户名
            	String custName = "";
            	//预约总额
            	String orderAmount = "";
                for (int j = 0; j < colSize; j++) {
                	String colName = colNameMap.get(j+"");
                	Cell cell = sheet.getCell(j,i);
                	//获取产品名称
                	if (colName.indexOf("productName")>-1) {
                		productName = cell.getContents().trim();
					}
                	//获取产品编码
                	if (colName.indexOf("productCode")>-1) {
                		productCode = cell.getContents().trim();
					}
                	//获取客户姓名
                	if (colName.indexOf("custName")>-1) {
                		custName = cell.getContents().trim();
					}
                	//获取预约总额
                	if (colName.indexOf("orderAmount")>-1) {
                		orderAmount = cell.getContents().trim();
					}
                }
                   
                    if ("".equals(productName)||"".equals(productCode)||"".equals(custName)||"".equals(orderAmount)) {
                    	resultInfo.setSuccess(false);
                		resultInfo.setMsg("上传文件中有未完整信息！");
                		return resultInfo;
					}
                    logger.info(productName+"+"+productCode+"+"+custName+"+"+orderAmount);
                    //根据产品名称和产品编码获取产品ID
                    PDProductExample pdProductExample = new PDProductExample();
                    pdProductExample.createCriteria().andProductCodeEqualTo(productCode).andProductNameEqualTo(productName)
                    .andStatusEqualTo("2").andRcStateEqualTo("E");
                    List<PDProduct> products = pdProductMapper.selectByExample(pdProductExample);
                    if (products.isEmpty()||products.size() > 1) {
             			throw new CisCoreException("产品/产品编码填写有误！未获取到该产品信息！");
					}
                    Long productId = products.get(0).getProductId();
 					PDAmountOrderInfoExample pdAmountOrderInfoExample = new PDAmountOrderInfoExample();
 					pdAmountOrderInfoExample.createCriteria().andProductIdEqualTo(productId).andCustNameEqualTo(custName)
 					.andOrderStatusEqualTo("01").andRcStateEqualTo("E");
 					List<PDAmountOrderInfo> productOrderInfoList = pdAmountOrderInfoMapper.selectByExample(pdAmountOrderInfoExample);
             		if (productOrderInfoList.isEmpty()) {
             			throw new CisCoreException("未获取到客户姓名为："+custName+"的未打款预约信息！");
					}
 					if (productOrderInfoList.size() > 1) {
 						throw new CisCoreException("客户："+custName+"。存在多笔预约记录，请手动审核！");
					}
 					//判断上传打款金额和预约中的预约金额是否相等
 					BigDecimal orgOrderAmount = productOrderInfoList.get(0).getOrderAmount();
 					BigDecimal newOrderAmount = new BigDecimal(orderAmount);
 					DecimalFormat df = new DecimalFormat("#.00");
 					String a = df.format(orgOrderAmount);
 					String b = df.format(newOrderAmount);
 					if (a.equals(b)) {
						//相等则将其设置为已打款
 						PDAmountOrderInfo pdAmountOrderInfo = productOrderInfoList.get(0);
 						pdAmountOrderInfo.setOrderStatus("02");
 						BeanUtils.updateObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
 						pdAmountOrderInfoMapper.updateByPrimaryKey(pdAmountOrderInfo);
 						pdAmountOrderInfos.add(pdAmountOrderInfo);
					}else {
						throw new CisCoreException("客户："+custName+"。的预约金额与打款金额不符，请核对后再上传！");
					}
            }
			// 循环短信发送
            for (PDAmountOrderInfo pdAmountOrderInfo2 : pdAmountOrderInfos) {
            	resultInfo = smsService.createAndSendProductOrderAuditSms(pdAmountOrderInfo2, loginInfo);
			}
        workbook.close();
		resultInfo.setSuccess(true);
		resultInfo.setMsg("批量打款审核成功！");
		return resultInfo;
	}
	
	/**
	 * 定金打款审核
	 */
	@Transactional
	public ResultInfo submitEarnestAudit(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 1.参数判断
		if (pdAmountOrderInfo == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("预约记录为空，不能提交审核！");
			return resultInfo;
		}
		// 2.若传入参数不为空， 进行业务处理
			pdAmountOrderInfo = pdAmountOrderInfoMapper.selectByPrimaryKey(pdAmountOrderInfo.getPdAmountOrderInfoId());
			String custOrderStatus = pdAmountOrderInfo.getOrderStatus();
			if ("".equals(custOrderStatus)||custOrderStatus == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到预约状态！审核失败！");
			}
			if ("01".equals(custOrderStatus)) {
				pdAmountOrderInfo.setOrderStatus("05");
				pdAmountOrderInfo.setEarnestAuditDate(new Date());
				BeanUtils.updateObjectSetOperateInfo(pdAmountOrderInfo, loginInfo);
				pdAmountOrderInfoMapper.updateByPrimaryKey(pdAmountOrderInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("审核完成！");

			} else if (custOrderStatus != null && !"".equals(custOrderStatus) && "02".equals(custOrderStatus)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该预约已经审核通过，不能再次提交审核！");
			} else if (custOrderStatus != null && !"".equals(custOrderStatus) && "03".equals(custOrderStatus)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该预约已经失效，不能提交审核！");
			} else if (custOrderStatus != null && !"".equals(custOrderStatus) && "05".equals(custOrderStatus)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该预约已经进行过定金审核，不能再次提交审核！");
			}
		return resultInfo;
	}

	/**修改热门产品状态
	 * @param loginInfo
	 * @return
	 * caijingjing
	 */
	@Override
	public ResultInfo addHotProductInfo(String productId, LoginInfo loginInfo)  throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		if (productId != null && !productId.equals("")) {
			//获取产品相关信息
			PDProduct pdProductInfo = pdProductMapper.selectByPrimaryKey(new Long(productId));
			pdProductInfo.setIsHot("01");
			BeanUtils.updateObjectSetOperateInfo(pdProductInfo, loginInfo);
			pdProductMapper.updateByPrimaryKey(pdProductInfo);
		}else {
			throw new CisCoreException("获取产品Id出现异常！");
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("修改热门产品状态成功！");
		return resultInfo;
	}

	@Override
	public List<PDProduct> getProductAllForSelect() {
		List<PDProduct> list=pdProductMapper.getProductAllForSelect();
		return list;
	}
}
