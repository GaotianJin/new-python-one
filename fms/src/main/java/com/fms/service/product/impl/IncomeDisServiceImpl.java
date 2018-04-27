package com.fms.service.product.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fms.db.mapper.PDwealthIncomeDisMapper;
import com.fms.db.mapper.PdIncomeDisDetailMapper;
import com.fms.db.mapper.PdIncomeDisMapper;
import com.fms.db.mapper.SysEmailAdressMapper;
import com.fms.db.mapper.SysEmailInfoMapper;
import com.fms.db.mapper.TradeFundsShareChangeMapper;
import com.fms.db.model.PdIncomeDis;
import com.fms.db.model.PdIncomeDisDetail;
import com.fms.db.model.SysEmailAdress;
import com.fms.db.model.SysEmailAdressExample;
import com.fms.db.model.SysEmailInfo;
import com.fms.db.model.TradeFundsShareChange;
import com.fms.db.model.TradeFundsShareChangeExample;
import com.fms.service.mapper.IncomeDisServiceMapper;
import com.fms.service.mapper.SmsServiceMapper;
import com.fms.service.product.IncomeDisService;
import com.fms.service.sms.SmsService;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserExample;
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

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@SuppressWarnings("serial")
@Service("IncomeDisService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IncomeDisServiceImpl implements IncomeDisService,Serializable {

	@Autowired
	private PDwealthIncomeDisMapper pdwealthIncomeDisMapper;
	@Autowired
	private IncomeDisServiceMapper incomeDisServiceMapper;
	@Autowired
	private PdIncomeDisMapper pdIncomeDisMapper;
	@Autowired
	private PdIncomeDisDetailMapper pdIncomeDisDetailMapper;
	@Autowired
	private DefUserMapper defUserMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private SysEmailAdressMapper sysEmailAdressMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private SysEmailInfoMapper sysEmailInfoMapper;
	@Autowired
	private TradeFundsShareChangeMapper tradeFundsShareChangeMapper;
	@Autowired
	private SmsService smsService;
	
	private static final Logger logger = Logger.getLogger(IncomeDisServiceImpl.class);
	
	
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	@Override
	@Transactional
	public  ResultInfo incomeDisBatch() {
		ResultInfo resultInfo = new ResultInfo();
		String currentDate = DateUtils.getCurrentDate();
		LoginInfo loginInfo = setBatchLoginInfo();
		try {
			Map paramMap = new HashMap();
			paramMap.put("currentDate", currentDate);
			List<Map> incomeDisProductList = incomeDisServiceMapper.getAllProductDisInfo(paramMap);
			if (incomeDisProductList==null||incomeDisProductList.size()==0) {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("收益分配结算完成！");
				return resultInfo;
			}
			logger.info("===收益分配产品数数量：==="+incomeDisProductList.size());
			for (Map productIncomeDisInfo:incomeDisProductList) {
				Long productId = (Long)productIncomeDisInfo.get("productId");
				String productName = (String)productIncomeDisInfo.get("productName");
				String distributeDate = (String)productIncomeDisInfo.get("distributeDate");
				String distributeStartDate = (String)productIncomeDisInfo.get("distributeStartDate");
				String distributeEndDate = (String)productIncomeDisInfo.get("distributeEndDate");
				BigDecimal principalDistributeRate = (BigDecimal)productIncomeDisInfo.get("principalDistributeRate");
				logger.info("===收益分配产品流水号及名称：==="+productId+"==="+productName);
				logger.info("===收益分配产品分配日期：==="+distributeDate);
				logger.info("===收益分配产品分配起期：==="+distributeStartDate);
				logger.info("===收益分配产品分配止期：==="+distributeEndDate);
				logger.info("===收益分配产品本金分配比率：==="+principalDistributeRate.toString());
				//获取该产品的所有交易信息
				List<Map> tradeInfoList = incomeDisServiceMapper.getAllTradeInfoByProductId(productIncomeDisInfo);
				Long pdIncomeDistributeId = null;
				PdIncomeDis pdIncomeDis = new PdIncomeDis();
				if (tradeInfoList!=null&&tradeInfoList.size()>0) {
					pdIncomeDistributeId = commonService.getSeqValByName("SEQ_T_PD_INCOME_DIS");
					pdIncomeDis.setPdIncomeDistributeId(pdIncomeDistributeId);
					pdIncomeDis.setPdProductId(productId);
					pdIncomeDis.setDistributePrincipalRate(principalDistributeRate);
					pdIncomeDis.setDistributeStatus("01");
					pdIncomeDis.setDistributeDate(DateUtils.getDate(distributeDate));
					pdIncomeDis.setDistributeStartDate(DateUtils.getDate(distributeStartDate));
					pdIncomeDis.setDistributeEndDate(DateUtils.getDate(distributeEndDate));
					BeanUtils.insertObjectSetOperateInfo(pdIncomeDis, loginInfo);
					pdIncomeDisMapper.insert(pdIncomeDis);
				}
				logger.info("===收益分配该产品交易数量：==="+productId+"==="+productName+"==="+tradeInfoList.size());
				//本次分配利息总额
				double distributeTotalInterest = 0;
				//本次分配本金总额
				double distributeTotalPrincipal = 0;
				//本次分配总额=本次分配利息总额+本次分配本金总额
				double distributeTotal = 0;
				//收益分配天数，默认包括起始日期不包括结束日期
				int distributeDays = DateUtils.calInterval(distributeStartDate, distributeEndDate, "D")+1;
				for (Map tradeInfoMap:tradeInfoList) {
					//该笔交易分配的利息
					double distributeInterest = 0;
					//该笔交易分配的本金
					double distributePrincipal = 0;
					Long tradeInfoId = (Long)tradeInfoMap.get("tradeInfoId");
					//判断交易是否是受让交易
					TradeFundsShareChangeExample tradeFundsShareChangeExample = new TradeFundsShareChangeExample();
					tradeFundsShareChangeExample.createCriteria().andOriginTradeInfoIdEqualTo(tradeInfoId.toString())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<TradeFundsShareChange> tradeFundsShareChangeList = tradeFundsShareChangeMapper.selectByExample(tradeFundsShareChangeExample);
					if (tradeFundsShareChangeList.size()>0) {
						TradeFundsShareChange tradeFundsShareChange = tradeFundsShareChangeList.get(0);
						distributeStartDate = tradeFundsShareChange.getValueDate().toString();
						distributeDays = DateUtils.calInterval(distributeStartDate, distributeEndDate, "D")+1;
					}
					//Long agentId = (Long)tradeInfoMap.get("agentId");
					Long custBaseInfoId = (Long)tradeInfoMap.get("custBaseInfoId");
					BigDecimal actuSubscriptionAmount = (BigDecimal)tradeInfoMap.get("actuSubscriptionAmount");
					Long custAccInfoId = (Long)tradeInfoMap.get("custAccInfoId");
					BigDecimal expectFeeRate = (BigDecimal)tradeInfoMap.get("expectFeeRate");
					logger.info("===收益分配交易信息（交易流水号、实际认购额、预期收益率）：==="+tradeInfoId.toString()+"=="+actuSubscriptionAmount.toString()+"=="+expectFeeRate.toString());
					//计算利息
					double interestRate = distributeDays*NumberUtils.divide(expectFeeRate.toString(),"100")/365;
					distributeInterest = NumberUtils.multiply(actuSubscriptionAmount.toString(),interestRate+"");
					distributeTotalInterest += distributeInterest;
					logger.info("===收益分配交易分配利息：==="+distributeInterest);
					//计算本金
					double principalRate = NumberUtils.divide(principalDistributeRate.toString(),"100");
					distributePrincipal = NumberUtils.multiply(actuSubscriptionAmount.toString(),principalRate+"");
					distributeTotalPrincipal += distributePrincipal;
					logger.info("===收益分配交易分配本金：==="+distributePrincipal);
					//保存单笔交易收益分配信息
					PdIncomeDisDetail pdIncomeDisDetail = new PdIncomeDisDetail();
					Long pdIncomeDistributeDetailId = commonService.getSeqValByName("SEQ_T_PD_INCOME_DIS_DETAIL");
					pdIncomeDisDetail.setPdIncomeDistributeDetailId(pdIncomeDistributeDetailId);
					pdIncomeDisDetail.setPdIncomeDistributeId(pdIncomeDis.getPdIncomeDistributeId());
					pdIncomeDisDetail.setTradeInfoId(tradeInfoId);
					pdIncomeDisDetail.setCustBaseInfoId(custBaseInfoId);
					pdIncomeDisDetail.setExpectedFeeRate(expectFeeRate);
					pdIncomeDisDetail.setDistributeInterest(new BigDecimal(distributeInterest));
					pdIncomeDisDetail.setDistributePrincipal(new BigDecimal(distributePrincipal));
					pdIncomeDisDetail.setCustAccInfoId(custAccInfoId);
					BeanUtils.insertObjectSetOperateInfo(pdIncomeDisDetail, loginInfo);
					pdIncomeDisDetailMapper.insertSelective(pdIncomeDisDetail);
				}
				//分配总额
				distributeTotal = distributeTotalInterest + distributeTotalPrincipal;
				logger.info("===收益分配产品分配信息：==="+productId+"==="+productName);
				logger.info("===收益分配产品分配（利息总额，本金总额、总金额）：==="+distributeTotalInterest+"==="+distributeTotalPrincipal+"==="+distributeTotal);
				pdIncomeDis.setDistributeTotalInterest(new BigDecimal(distributeTotalInterest));
				pdIncomeDis.setDistributeTotalPrincipal(new BigDecimal(distributeTotalPrincipal));
				pdIncomeDis.setDistributeTotal(new BigDecimal(distributeTotal));
				pdIncomeDisMapper.updateByPrimaryKey(pdIncomeDis);
			}
			//收益分配完成
			resultInfo.setSuccess(true);
			resultInfo.setMsg("收益分配完成！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("收益分配批处理出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 * 产品到期清算触发邮件
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	@Override
	@Transactional
	public  ResultInfo incomeDisEmailBatch() {
		ResultInfo resultInfo = new ResultInfo();
		String currentDate = DateUtils.getCurrentDate();
		LoginInfo loginInfo = setBatchLoginInfo();
		try {
			Map paramMap = new HashMap();
			paramMap.put("currentDate", currentDate);
			List<Map<String,Object>> incomeDisNotDueProductList = incomeDisServiceMapper.getAllNotDueProductDisInfo(paramMap);
			if (incomeDisNotDueProductList==null||incomeDisNotDueProductList.size()==0) {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("收益分配结算完成！");
				return resultInfo;
			}
			//遍历每个未到期的收益分配记录
			for (int i = 0; i < incomeDisNotDueProductList.size(); i++) {
				Map<String,Object> incomDisNotDueProduct = incomeDisNotDueProductList.get(i);
				String distributeDate = (String)incomDisNotDueProduct.get("distributeDate");
				//获取分配日期三天前的日期
				Calendar c = Calendar.getInstance();
				Date date = new SimpleDateFormat("yy-MM-dd").parse(distributeDate); 
				c.setTime(date);
				int day=c.get(Calendar.DATE);
				c.set(Calendar.DATE,day-5); 
				String fiveDaysAgo=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
				//如果当前日期和分配日期五天前相同 则触发邮件
				if (currentDate.equals(fiveDaysAgo)) {
//					Long productId = (Long)incomDisNotDueProduct.get("productId");
//					Map param = new HashMap();
//					param.put("productId", productId);
//					Map maxDate = incomeDisServiceMapper.getMaxDistributeDate(param);
//					Date maxDistributeDate = (Date)maxDate.get("maxDate");
					String principalDistributeRate = (String)incomDisNotDueProduct.get("principalDistributeRate");
					//若分配比率是100 则清算 否则进行期间分配
					if ("100".equals(principalDistributeRate)) {
						resultInfo = sendIncomeDisAllEmail(incomDisNotDueProduct,loginInfo);
						//创建到期清算短信
						resultInfo = smsService.createIncomeDisAllMessage(incomDisNotDueProduct,loginInfo);
					}
					else {
						resultInfo = sendIncomeDisEmail(incomDisNotDueProduct,loginInfo);
						//创建期间分配短信
						resultInfo = smsService.createIncomeDisMessage(incomDisNotDueProduct,loginInfo);
					}
				}
			}
			//收益分配完成
			resultInfo.setSuccess(true);
			resultInfo.setMsg("收益分配完成！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("收益分配批处理出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 * 发送产品期分配邮件
	 * @param tradeCallBackInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendIncomeDisEmail(Map<String, Object> incomDisNotDueProduct,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createIncomeDisEmail(incomDisNotDueProduct,loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		List<SysEmailInfo> sysEmailInfoList = (List<SysEmailInfo>)resultInfo.getObj();
		//发送短信
		for (SysEmailInfo sysEmailInfo2 : sysEmailInfoList) {
			resultInfo = sendIncomeDisAllEmail(sysEmailInfo2,loginInfo);
		}
		
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	/**
	 * 发送产品到期清算邮件
	 * @param tradeCallBackInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendIncomeDisAllEmail(Map<String, Object> incomDisNotDueProduct,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createIncomeDisAllEmail(incomDisNotDueProduct,loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		List<SysEmailInfo> sysEmailInfoList = (List<SysEmailInfo>)resultInfo.getObj();
		//发送短信
		for (SysEmailInfo sysEmailInfo2 : sysEmailInfoList) {
			resultInfo = sendIncomeDisAllEmail(sysEmailInfo2,loginInfo);
		}
		
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	
	
	/**
	 * 创建产品期间分配邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createIncomeDisEmail(Map<String, Object> incomDisNotDueProduct,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		Long productId = (Long)incomDisNotDueProduct.get("productId");
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//交易复核通知邮件
		sysEmailInfo.setEmailType("07");
		sysEmailInfo.setEmailRelationNo(productId);
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
				sysEmailInfo1.setEmailType("07");
				sysEmailInfo.setEmailRelationNo(productId);
				sysEmailInfo1.setEmailTitle("产品期间分配通知");
				sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
		//创建邮件内容
			String mailContent = createIncomeDisEmailContent(incomDisNotDueProduct);
			sysEmailInfo1.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo1.setEmailStatus("01");
		sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo1);
		resultList.add(sysEmailInfo1);
				}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品期间分配邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
	}
	
	/**
	 * 创建产品到期清算邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createIncomeDisAllEmail(Map<String, Object> incomDisNotDueProduct,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		Long productId = (Long)incomDisNotDueProduct.get("productId");
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//交易复核通知邮件
		sysEmailInfo.setEmailType("08");
		sysEmailInfo.setEmailRelationNo(productId);
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
				sysEmailInfo1.setEmailType("08");
				sysEmailInfo.setEmailRelationNo(productId);
				sysEmailInfo1.setEmailTitle("产品到期清算通知");
				sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
		//创建邮件内容
			String mailContent = createIncomeDisAllEmailContent(incomDisNotDueProduct);
			sysEmailInfo1.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo1.setEmailStatus("01");
		sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo1);
		resultList.add(sysEmailInfo1);
				}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品到期清算邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
	}
	
	/**
	 * 生成产品期间分配邮件类容
	 * @param callBackEmailData
	 * @return
	 */
	private String createIncomeDisEmailContent(Map<String,Object> incomDisNotDueProduct){
		StringBuffer mailContent = new StringBuffer();

		mailContent.append("《");
		mailContent.append((String)incomDisNotDueProduct.get("productName"));
		mailContent.append("》产品将于“");
		mailContent.append((String)incomDisNotDueProduct.get("distributeDate"));
		mailContent.append("”期间分配");
	/*	mailContent.append((String)incomDisNotDueProduct.get("incomeDisTotal"));*/
		/*mailContent.append("”");*/
		return mailContent.toString();
	}
	
	
	/**
	 * 生成产品到期清算邮件类容
	 * @param callBackEmailData
	 * @return
	 */
	private String createIncomeDisAllEmailContent(Map<String,Object> incomDisNotDueProduct){
		StringBuffer mailContent = new StringBuffer();

		mailContent.append("《");
		mailContent.append((String)incomDisNotDueProduct.get("productName"));
		mailContent.append("》产品将于“");
		mailContent.append((String)incomDisNotDueProduct.get("distributeDate"));
		mailContent.append("”日到期清算");
		/*mailContent.append((String)incomDisNotDueProduct.get("incomeDisTotal"));*/
//		mailContent.append("”");
		return mailContent.toString();
	}
	/**
	 * 发送邮件
	 * @return
	 */
	@Transactional
	private ResultInfo sendIncomeDisAllEmail(SysEmailInfo sysEmailInfo,LoginInfo loginInfo){
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
	 * 设置批处理的操作人员信息，默认设置为fms
	 * @return
	 */
	private LoginInfo setBatchLoginInfo(){
		LoginInfo loginInfo = new LoginInfo();
		DefUserExample defUserExample = new DefUserExample();
		DefUserExample.Criteria criteria = defUserExample.createCriteria();
		criteria.andUserCodeEqualTo("admin").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<DefUser> defUserList = defUserMapper.selectByExample(defUserExample);
		if (defUserList!=null&&defUserList.size()>0) {
			DefUser defUser = defUserList.get(0);
			loginInfo.setUserId(defUser.getUserId());
			loginInfo.setComId(defUser.getComId());
			loginInfo.setLoginComId(defUser.getComId());
			loginInfo.setUserCode(defUser.getUserCode());
		}
		return loginInfo;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryProductIncomeDistibuteList(DataGridModel dgm,
			Map paramMap, LoginInfo loginInfo) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = incomeDisServiceMapper.queryProductIncomeDistibuteListCount(paramMap);
		List<Map> resultList = incomeDisServiceMapper.queryProductIncomeDistibuteList(paramMap);
		//datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryProductIncomeDistibuteDetailList(DataGridModel dgm,
			Map paramMap, LoginInfo loginInfo) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		paramMap.put("sort", dgm.getSort());
		paramMap.put("order", dgm.getOrder());
		Integer total = incomeDisServiceMapper.queryProductIncomeDistibuteDetailListCount(paramMap);
		List<Map> resultList = incomeDisServiceMapper.queryProductIncomeDistibuteDetailList(paramMap);
		//datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}


	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo getProductIncomeDistributeDetailInfo(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		List<Map<String, Object>> reportList = new ArrayList<Map<String,Object>>();
		try {
			List<Map> incomeDisDetailList = incomeDisServiceMapper.getProductIncomeDistributeDetailInfo(paramMap);
			if (incomeDisDetailList!=null) {
				Map<String, Object> reportMap = commonService.getReportInfo("PdIncomeDisDetail");
				//对sheet名称加上当前月份
				String reportName = reportMap.get("reportName").toString();
				reportMap.put("reportName", reportName);
				if (reportMap!=null&&reportMap.size()>0&&incomeDisDetailList!=null&&incomeDisDetailList.size()>0) {
					reportMap.put("reportData", incomeDisDetailList);
					reportList.add(reportMap);
				}
				resultInfo.setObj(reportList);
				resultInfo.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("下载时获取收益分配数据出现异常！");
		}
		return resultInfo;
	}


	@Override
	@Transactional
	public ResultInfo importIncomeDisFile(MultipartFile[] importFileNameList,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		List<String> unUpLoadFile = new ArrayList<String>();
		try {
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
				resultInfo = resolveIncomeDisExcel(workbook, loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("收益分配文件导入出现异常！");
		}
		resultInfo.setSuccess(true);
		if(unUpLoadFile.size()>0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg(JsonUtils.objectToJsonStr(unUpLoadFile));
		}else{
			resultInfo.setMsg("收益分配文件导入成功！");
		}
		return resultInfo;
	}


	/**
	 * @param workbook
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo resolveIncomeDisExcel(Workbook workbook,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
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
			//收益分配批次流水号
			Long pdIncomeDisId = null;
			//获取所有数据，从第二行开始，第一行为表头
            for (int i = 1; i < sheet.getRows(); i++) {
            	//收益分配流水号
            	String incomeDisDetailId = "";
            	//产品方支付本金金额
            	String agencyPrincipal = "";
            	//产品方支付利息金额
            	String agencyInterest = "";
            	//最终确认支付本金金额
            	String confirmPrincipal = "";
            	//最终确认支付利息金额
            	String confirmInterest = "";
                for (int j = 0; j < colSize; j++) {
                	String colName = colNameMap.get(j+"");
                	Cell cell = sheet.getCell(j,i);
                	//获取收益分配流水号
                	if (colName.indexOf("incomeDisDetailId")>-1) {
						incomeDisDetailId = cell.getContents();
					}
                	//获取产品方支付本金金额
                	if (colName.indexOf("agencyPrincipal")>-1) {
						agencyPrincipal = cell.getContents();
					}
                	//获取产品方支付利息金额
                	if (colName.indexOf("agencyInterest")>-1) {
						agencyInterest = cell.getContents();
					}
                	//获取最终确认支付本金金额
                	if (colName.indexOf("confirmPrincipal")>-1) {
						confirmPrincipal = cell.getContents();
					}
                	//获取最终确认支付利息金额
                	if (colName.indexOf("confirmInterest")>-1) {
						confirmInterest = cell.getContents();
					}
                }
                //获取收益分配信息
                PdIncomeDisDetail pdIncomeDisDetail = new PdIncomeDisDetail();
                if (incomeDisDetailId!=null&&!"".equals(incomeDisDetailId)) {
                	pdIncomeDisDetail = pdIncomeDisDetailMapper.selectByPrimaryKey(new Long(incomeDisDetailId));
                	pdIncomeDisId = pdIncomeDisDetail.getPdIncomeDistributeId();
				}else {
					continue;
				}
                //产品方支付本金
                if (agencyPrincipal!=null&&!"".equals(agencyPrincipal)) {
                	pdIncomeDisDetail.setAgencyDistributePrincipal(new BigDecimal(agencyPrincipal));
				}
                //产品方支付利息
                if (agencyInterest!=null&&!"".equals(agencyInterest)) {
                	pdIncomeDisDetail.setAgencyDistributeInterest(new BigDecimal(agencyInterest));
				}
                //最终确认支付本金
                if (confirmPrincipal!=null&&!"".equals(confirmPrincipal)) {
                	pdIncomeDisDetail.setConfirmDistributePrincipal(new BigDecimal(confirmPrincipal));
				}
                //最终确认支付利息
                if (confirmInterest!=null&&!"".equals(confirmInterest)) {
                	pdIncomeDisDetail.setConfirmDistributeInterest(new BigDecimal(confirmInterest));
				}
                //设置操作信息
                BeanUtils.updateObjectSetOperateInfo(pdIncomeDisDetail, loginInfo);
                //更新上传的数据
                pdIncomeDisDetailMapper.updateByPrimaryKeySelective(pdIncomeDisDetail);
            }
            //收益分配导入完成需要更新收益分配状态
            PdIncomeDis pdIncomeDis = pdIncomeDisMapper.selectByPrimaryKey(pdIncomeDisId);
            //设置状态为已分配
            pdIncomeDis.setDistributeStatus("02");
            BeanUtils.updateObjectSetOperateInfo(pdIncomeDis, loginInfo);
            pdIncomeDisMapper.updateByPrimaryKeySelective(pdIncomeDis);
            workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("解析收益分配文件出现异常！");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("收益分配文件导入成功！");
		return resultInfo;
	}

	/**
	 * 发送净值维护邮件
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	@Override
	@Transactional
	public  ResultInfo netValueNoticeBatch() {
		ResultInfo resultInfo = new ResultInfo();
		String currentDate = DateUtils.getCurrentDate();
		LoginInfo loginInfo = setBatchLoginInfo();
		try{
			resultInfo = sendNetValueNoticeEmail(loginInfo);
			//收益分配完成
			resultInfo.setSuccess(true);
			resultInfo.setMsg("发送净值维护邮件完成！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("净值维护批处理出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 * 发送净值维护邮件
	 * @param tradeCallBackInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	private ResultInfo sendNetValueNoticeEmail(LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createNetValueNoticeEmail(loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		List<SysEmailInfo> sysEmailInfoList = (List<SysEmailInfo>)resultInfo.getObj();
		//发送短信
		resultInfo = sendNetValueNoticeEmail(sysEmailInfoList,loginInfo);
		
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	
	
	/**
	 * 创建净值维护邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Transactional
	private ResultInfo createNetValueNoticeEmail(LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//交易复核通知邮件
		sysEmailInfo.setEmailType("09");
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
				sysEmailInfo1.setEmailType("09");
				sysEmailInfo1.setEmailTitle("产品净值维护通知");
				sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
		//创建邮件内容
			sysEmailInfo1.setEmailContent("下午好！又到了录净值和指数的时间啦！");
		//01-未发送
		sysEmailInfo1.setEmailStatus("01");
		sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo1);
		resultList.add(sysEmailInfo1);
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品净值维护邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
	}
	
	/**
	 * 发送邮件
	 * @return
	 */
	@Transactional
	private ResultInfo sendNetValueNoticeEmail(List<SysEmailInfo> sysEmailInfos,LoginInfo loginInfo){
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
		if(sysEmailInfos!=null){
			int sysLen = sysEmailInfos.size();
			String[] address = new String[sysLen];
			for(int i=0;i<sysLen;i++){
				address[i] = sysEmailInfos.get(i).getEmailAddress();
			};
			String title = sysEmailInfos.get(0).getEmailTitle();
			String content = sysEmailInfos.get(0).getEmailContent();
			
			//抄送地址数组othEmailAddress[],可能会有点乱
			SysEmailAdressExample sysEmailAdressExample = new SysEmailAdressExample();
			SysEmailAdressExample.Criteria sysEmailAdressCriteria = sysEmailAdressExample.createCriteria();
		    sysEmailAdressCriteria.andEmailTypeEqualTo("10");
			List<SysEmailAdress> sysEmailAdresses = sysEmailAdressMapper.selectByExample(sysEmailAdressExample);
			int othLen = sysEmailAdresses.size();
			String[] othEmailAddress = new String[othLen];
			for(int i=0;i<othLen;i++){
				othEmailAddress[i] = sysEmailAdresses.get(i).getEmailAddress();
			};
			resultInfo = sendEmailService.sendEmail(address,othEmailAddress, title, content, mailHostParam);
			if (resultInfo.isSuccess()) {
				for(int i=0;i<sysLen;i++){
					SysEmailInfo sysEmailInfo = sysEmailInfos.get(i);
					sysEmailInfo.setEmailSendTime(DateUtils.getCurrentTimestamp());
					//02-已发送
					sysEmailInfo.setEmailStatus("02");
					BeanUtils.updateObjectSetOperateInfo(sysEmailInfo, loginInfo);
					sysEmailInfoMapper.updateByPrimaryKey(sysEmailInfo);
				}	
			}else {
				//03-发送失败
				for(int i=0;i<sysLen;i++){
					SysEmailInfo sysEmailInfo = sysEmailInfos.get(i);
					sysEmailInfos.get(i).setEmailStatus("03");
					BeanUtils.updateObjectSetOperateInfo(sysEmailInfo, loginInfo);
					sysEmailInfoMapper.updateByPrimaryKey(sysEmailInfo);
				}
			}		
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 手动生成收益分配短信
	 * @throws ParseException 
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	@Override
	@Transactional
	public  ResultInfo incomeDisSmsBatch() throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		String currentDate = DateUtils.getCurrentDate();
		LoginInfo loginInfo = setBatchLoginInfo();
			Map paramMap = new HashMap();
			paramMap.put("currentDate", currentDate);
			List<Map<String,Object>> incomeDisNotDueProductList = incomeDisServiceMapper.getAllNotDueProductDisInfo(paramMap);
			if (incomeDisNotDueProductList==null||incomeDisNotDueProductList.size()==0) {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("收益分配结算完成！");
				return resultInfo;
			}
			//遍历每个未到期的收益分配记录
			for (int i = 0; i < incomeDisNotDueProductList.size(); i++) {
				Map<String,Object> incomDisNotDueProduct = incomeDisNotDueProductList.get(i);
				String distributeDate = (String)incomDisNotDueProduct.get("distributeDate");
				//获取分配日期三天前的日期
				Calendar c = Calendar.getInstance();
				Date date = new SimpleDateFormat("yy-MM-dd").parse(distributeDate);
				c.setTime(date);
				int day=c.get(Calendar.DATE);
				c.set(Calendar.DATE,day-5); 
				String fiveDaysAgo=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
				//如果当前日期和分配日期五天前相同 则触发短信
				if (currentDate.equals(fiveDaysAgo)) {
//					Long productId = (Long)incomDisNotDueProduct.get("productId");
//					Map param = new HashMap();
//					param.put("productId", productId);
//					Map maxDate = incomeDisServiceMapper.getMaxDistributeDate(param);
//					Date maxDistributeDate = (Date)maxDate.get("maxDate");
					String principalDistributeRate = (String)incomDisNotDueProduct.get("principalDistributeRate");
					//若分配比率是100 则清算 否则进行期间分配
					if ("100".equals(principalDistributeRate)) {
						//创建到期清算短信
						resultInfo = smsService.createIncomeDisAllMessage(incomDisNotDueProduct,loginInfo);
					}
					else {
						//创建期间分配短信
						resultInfo = smsService.createIncomeDisMessage(incomDisNotDueProduct,loginInfo);
					}
				}
			}
			//收益分配完成
			resultInfo.setSuccess(true);
			resultInfo.setMsg("收益分配短信生成完成！");
		return resultInfo;
	}
}
