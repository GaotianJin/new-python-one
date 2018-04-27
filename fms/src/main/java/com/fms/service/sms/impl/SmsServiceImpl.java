package com.fms.service.sms.impl;

import java.io.Serializable;
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

import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.CustBaseInfoMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PDWealthMapper;
import com.fms.db.mapper.PDWealthNetValueMapper;
import com.fms.db.mapper.SysSmsBatchMapper;
import com.fms.db.mapper.SysSmsInfoMapper;
import com.fms.db.mapper.SysSmsSendResultMapper;
import com.fms.db.mapper.SysSmsTemplateMapper;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.PDAmountOrderInfo;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthNetValue;
import com.fms.db.model.SysSmsBatch;
import com.fms.db.model.SysSmsBatchExample;
import com.fms.db.model.SysSmsInfo;
import com.fms.db.model.SysSmsInfoExample;
import com.fms.db.model.SysSmsSendResult;
import com.fms.db.model.SysSmsTemplate;
import com.fms.db.model.SysSmsTemplateExample;
import com.fms.db.model.TradeStatusInfo;
import com.fms.service.mapper.ManagerServiceMapper;
import com.fms.service.mapper.SmsServiceMapper;
import com.fms.service.sms.SmsService;
import com.fms.webservice.sms.SendSmsService;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
@SuppressWarnings("serial")
@Service("SmsService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SmsServiceImpl implements SmsService,Serializable {

	@Autowired
	private SmsServiceMapper smsServiceMapper;
	@Autowired
	private PDWealthNetValueMapper pdWealthNetValueMapper;
	@Autowired
	private PDWealthMapper pdWealthMapper;
	@Autowired
	private SysSmsBatchMapper sysSmsBatchMapper;
	@Autowired
	private SysSmsTemplateMapper sysSmsTemplateMapper;
	@Autowired
	private SysSmsInfoMapper sysSmsInfoMapper;
	@Autowired
	private DefUserMapper defUserMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private SysSmsSendResultMapper sysSmsSendResultMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private PDProductMapper pdProductMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private CustBaseInfoMapper custBaseInfoMapper;
	@Autowired
	private ManagerServiceMapper managerServiceMapper;
	
	private SendSmsService sendSmsService = new SendSmsService();
	private static final Logger logger = Logger.getLogger(SmsServiceImpl.class);
	
	/**
	 * 生成ToCust生日短信
	 * @author SLY
	 */
	@Override
	public synchronized ResultInfo createBirthToCustSms() {
		ResultInfo resultInfo = new ResultInfo();
		//获取批处理操作信息:默认fms有权限操作批处理
		LoginInfo loginInfo = setBatchLoginInfo();
		if (loginInfo==null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("生成短信失败，失败原因：未获取到批处理操作信息！");
			logger.info("生成短信失败，失败原因：未获取到批处理操作信息！");
			return resultInfo;
		}
		//生成ToCust生日祝福短信,并且直接发送
		resultInfo  = createBirthToCustSms(loginInfo);
		if (!resultInfo.isSuccess()) {
			logger.info(resultInfo.getMsg());
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("短信发送完成");
		return resultInfo;
	}

	/**
	 * 发送短信后结果处理：成功状态设为已发送，失败存进SysSmsSendResult表中
	 * @param serviceUrl
	 * @param serviceFuncName
	 * @param userName
	 * @param passWord
	 * @param sysSmsInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	private ResultInfo sendSms(String serviceUrl,String serviceFuncName,String userName,String passWord,
			SysSmsInfo sysSmsInfo,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map smsParam = new HashMap();
			smsParam.put("userName", userName);
			smsParam.put("passWord", passWord);
			smsParam.put("sendMobile", sysSmsInfo.getSendMobile());
			smsParam.put("sendContent", sysSmsInfo.getSendContent());
			smsParam.put("sendTime", DateUtils.getDateTimeString(sysSmsInfo.getSendTime()));
			resultInfo = sendSmsService.sendSms(serviceUrl, serviceFuncName, smsParam);
			if (resultInfo.isSuccess()) {
				//短信状态设置为已发送
				sysSmsInfo.setSendStatus("02");
				BeanUtils.updateObjectSetOperateInfo(sysSmsInfo, loginInfo);
				sysSmsInfoMapper.updateByPrimaryKeySelective(sysSmsInfo);
				resultInfo.setSuccess(true);
			}else {
				if(resultInfo.getObj()!=null){
					Map resultMap = new HashMap();
					resultMap = (Map)resultInfo.getObj();
					if(resultMap.isEmpty()){
						SysSmsSendResult sysSmsSendResult = new SysSmsSendResult();
						sysSmsSendResult.setSysSmsInfoId(sysSmsInfo.getSysSmsInfoId());
						sysSmsSendResult.setSendResult(resultInfo.getMsg());
						sysSmsSendResult.setErrorId("null");
						sysSmsSendResult.setErrorMsg("错误");
						BeanUtils.insertObjectSetOperateInfo(sysSmsSendResult, loginInfo);
						sysSmsSendResultMapper.insert(sysSmsSendResult);
						logger.info("=====短信"+sysSmsInfo.getSysSmsInfoId()+"发送失败，详情请查看smsResult表"+sysSmsSendResult.getSysSmsSendResultId());
					}else {
						String errid = resultMap.get("result").toString();
						String errorMsg = (String)resultMap.get("desc");
						SysSmsSendResult sysSmsSendResult = new SysSmsSendResult();
						sysSmsSendResult.setSysSmsInfoId(sysSmsInfo.getSysSmsInfoId());
						sysSmsSendResult.setSendResult(resultInfo.getMsg());
						sysSmsSendResult.setErrorId(errid);
						sysSmsSendResult.setErrorMsg(errorMsg);
						BeanUtils.insertObjectSetOperateInfo(sysSmsSendResult, loginInfo);
						sysSmsSendResultMapper.insert(sysSmsSendResult);
						logger.info("=====短信"+sysSmsInfo.getSysSmsInfoId()+"发送失败，详情请查看smsResult表"+sysSmsSendResult.getSysSmsSendResultId());
					}
					
					resultInfo.setSuccess(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	
	/**
	 * 生成ToCust生日短信
	 * @param loginInfo
	 * @author SLY
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createBirthToCustSms(LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"开始生成ToCust生日短信===========");
		try {
			// 1.获取ToCust生日短信模板
			String smsType = "06";
			String sendObjType = "01";
			SysSmsTemplateExample sysSmsTemplateExample = new SysSmsTemplateExample();
			SysSmsTemplateExample.Criteria criteria = sysSmsTemplateExample.createCriteria();
			criteria.andSmsTypeEqualTo(smsType).andSnedObjTypeEqualTo(sendObjType)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<SysSmsTemplate> sysSmsTemplates = sysSmsTemplateMapper.selectByExample(sysSmsTemplateExample);
			SysSmsTemplate sysSmsTemplate = sysSmsTemplates.get(0);
			if (sysSmsTemplate == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成生日短信失败，失败原因：未获取到短信模板！");
				logger.info("生成生日短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}

			// 2.获取当日生日的客户list
			Map paramMap = new HashMap();
			paramMap.put("calDate", currentDate);
			paramMap.put("sendObjType", "01");
			List<Map> listCustomerList = smsServiceMapper.getAllCustInfo(paramMap);
			if (listCustomerList == null || listCustomerList.size() == 0) {
				logger.info("今天没有客户生日！");
				resultInfo.setSuccess(true);
				return resultInfo;
			}

			// 3.记录生日短信批处理：发给客户
			SysSmsBatch sysSmsBatch = new SysSmsBatch();
			sysSmsBatch.setSmsType(smsType);
			sysSmsBatch.setSmsTypeRelationNo(new Long(sendObjType));
			sysSmsBatch.setSmsTypeRelationDate(DateUtils.getDate(currentDate));
			sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
			// 01-未处理，02-已处理
			sysSmsBatch.setSmsStatus("01");
			BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
			sysSmsBatchMapper.insert(sysSmsBatch);
			logger.info("====="+sysSmsBatch.getSysSmsBatchId()+"批次的客户生日祝福短信，当年生日为" + currentDate + "===========");

			// 4.循环客户发短信
			for (Map customerMap : listCustomerList) {
				logger.info("=====生成客户生日祝福短信，客户为" + customerMap.get("custName") + "===========");
				String template = sysSmsTemplate.getSmsTemplate();
				// 获取正确的短信内容
				String sendContent = setValueToTemplate(customerMap, template);
				SysSmsInfo sysSmsInfo = new SysSmsInfo();
				sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
				sysSmsInfo.setBusinessNo((Long) customerMap.get("custBaseInfoId"));
				// 01-与交易流水号关联，02-与客户流水号关联
				sysSmsInfo.setBusinessType("02");
				sysSmsInfo.setSendObj((Long) customerMap.get("custBaseInfoId"));
				sysSmsInfo.setSendObjName(customerMap.get("custName").toString());
				sysSmsInfo.setSendMobile(customerMap.get("custMobile").toString());
				sysSmsInfo.setSendTime(DateUtils.getDateTime(currentDate + " 09:00:00"));
				sysSmsInfo.setSendObjType(sendObjType);
				// 01-未发送，02-已发送
				sysSmsInfo.setSendStatus("01");
				sysSmsInfo.setSendContent(sendContent);
				BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
				sysSmsInfoMapper.insert(sysSmsInfo);
			}
			logger.info("======="+currentDate+"生日的客户生日短信生成完成======");
			// 发送生日短信
			resultInfo = sendMessage(sysSmsBatch, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	
	/**
	 * 根据短信类型获取短信模板
	 * @param smsType
	 * @return
	 */
	private List<SysSmsTemplate> getSmsTemplates(String smsType){
		SysSmsTemplateExample sysSmsTemplateExample = new SysSmsTemplateExample();
		SysSmsTemplateExample.Criteria criteria = sysSmsTemplateExample.createCriteria();
		criteria.andSmsTypeEqualTo(smsType).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<SysSmsTemplate> sysSmsTemplates = sysSmsTemplateMapper.selectByExample(sysSmsTemplateExample);
		return sysSmsTemplates;
	}
	
	/**
	 * 替换模板里面的变量
	 * @param templateValueMap
	 * @param template
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String setValueToTemplate(Map templateValueMap,String template){
		String varStartMark = "[@";
		String varEndMark = "@]";
		int startIndex = template.indexOf(varStartMark);
		int endIndex = template.indexOf(varEndMark);
		while(startIndex>-1){
			String varName = template.substring(startIndex+2, endIndex);
			String varValue = "";
			if (varName!=null&&!"".equals(varName)) {
				if (templateValueMap.containsKey(varName)) {
					varValue = templateValueMap.get(varName).toString();
					//产品名称中包含“产品”两个字，去掉
					if ("productName".equals(varName)&&varValue.indexOf("产品")>=0) {
						varValue = varValue.replace("产品", "");
					}
					String regex = varStartMark + varName + varEndMark;
					template = template.replace(regex, varValue);
					startIndex = template.indexOf(varStartMark);
					endIndex = template.indexOf(varEndMark);
				}else {
					String regex = varStartMark + varName + varEndMark;
					template = template.replace(regex, "");
					startIndex = template.indexOf(varStartMark);
					endIndex = template.indexOf(varEndMark);				
					}
			}else {
				break;
			}
		}
		return template;
	}
	
	/**
	 * 设置批处理的操作人员信息，默认设置为fms
	 * @return
	 */
	private LoginInfo setBatchLoginInfo(){
		LoginInfo loginInfo = new LoginInfo();
		DefUserExample defUserExample = new DefUserExample();
		DefUserExample.Criteria criteria = defUserExample.createCriteria();
		criteria.andUserCodeEqualTo("fms").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
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

	/**
	 * 获取发送短信信息
	 * @author SLY
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	@Override
	public DataGrid getSmsDetailInfo(DataGridModel dgm,Map paramMap) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		DataGrid dataGrid = new DataGrid();
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = smsServiceMapper.getSmsAuditDetailInfoCount(paramMap);
		List<Map> smsDetailList = smsServiceMapper.getSmsAuditDetailInfo(paramMap);
		dataGrid.setRows(smsDetailList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 获取短信发送的参数
	 */
	@Override
	public ResultInfo sendSms(List<SysSmsInfo> sendSmsList,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		int errorCount = 0;
		try {
			//1.获取短信发送相关参数，主要包括短信发送webservice地址，服务方法，用户名，密码
			DefCodeExample defCodeExample = new DefCodeExample();
			DefCodeExample.Criteria criteria = defCodeExample.createCriteria();
			criteria.andCodeTypeEqualTo("smsSendParam");
			List<DefCode> sendSmsParamList = defCodeMapper.selectByExample(defCodeExample);
			String serviceUrl = null;
			String serviceFuncName = null;
			String userName = null;
			String passWord = null;
			if(sendSmsParamList==null||sendSmsParamList.size()==0){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("发送短信，未获取到短信服务器相关参数！");
				logger.info("发送短信，未获取到短信服务器相关参数！");
				return resultInfo;
			}else{
				for (DefCode sendSmsParam:sendSmsParamList) {
					if ("01".equals(sendSmsParam.getCode())) {
						serviceUrl = sendSmsParam.getCodeName();
					}
					else if ("02".equals(sendSmsParam.getCode())) {
						serviceFuncName = sendSmsParam.getCodeName();
					}
					else if ("03".equals(sendSmsParam.getCode())) {
						userName = sendSmsParam.getCodeName();
					}
					else if ("04".equals(sendSmsParam.getCode())) {
						passWord = sendSmsParam.getCodeName();
					}
				}
			}
			//2.循环发送短信
			for (SysSmsInfo sysSmsInfo:sendSmsList) {
				//若已经发送过的sendStatus=02的情况 不再发送
				if(sysSmsInfo.getSendStatus().equals("02")){
					errorCount++;
					continue;
				}else {
					resultInfo = sendSms(serviceUrl,serviceFuncName,userName,passWord,sysSmsInfo,loginInfo);
				}
				
				if(!resultInfo.isSuccess()){
					errorCount++;
				}
/*				//客户和理财经理为同一人是，发给理财经理的短信不发送
				String sendContent = sysSmsInfo.getSendContent();
				String sendObjName = sysSmsInfo.getSendObjName();
				String sendObjType = sysSmsInfo.getSendObjType();
				if(sendObjType!=null&&"02".equals(sendObjType)&&sendContent.indexOf(sendObjName)>-1){
					continue;
				}else {
					sendSms(serviceUrl,serviceFuncName,userName,passWord,sysSmsInfo,loginInfo);
				}*/
			}
			//3.短信发送完成，更新短信批次状态
			updateSysSmsBatchStatus(sendSmsList.get(0).getSysSmsBatchId(),loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
		}
		if(errorCount != 0){
			resultInfo.setSuccess(true);
			resultInfo.setMsg("短信发送完成，有"+errorCount+"条短信未发送成功");
			logger.info("有"+errorCount+"条短信未发送成功");
			return resultInfo;
		}else {
			resultInfo.setSuccess(true);
			resultInfo.setMsg("短信发送完成");
			logger.info("短信发送完成");
			return resultInfo;
		}
	}
	
	/**
	 * 调整批处理状态为已发送
	 * @param sysSmsBatchId
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	private ResultInfo updateSysSmsBatchStatus(Long sysSmsBatchId,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (sysSmsBatchId==null) {
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			Map paramMap = new HashMap();
			paramMap.put("sysSmsBatchId", sysSmsBatchId);
			List<Map> sysSmsInfoList = smsServiceMapper.getSysSmsDetailInfoList(paramMap);
			SysSmsBatch sysSmsBatch = sysSmsBatchMapper.selectByPrimaryKey(sysSmsBatchId);		
			if (sysSmsBatch!=null) {
				for(Map sysSmsInfoMap : sysSmsInfoList){
					if("01".equals(sysSmsInfoMap.get("sendStatus"))){
						resultInfo.setSuccess(true);
						return resultInfo;
					}else {
						continue;
					}
				}
				//状态设置为已处理
				sysSmsBatch.setSmsStatus("02");
				BeanUtils.updateObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.updateByPrimaryKeySelective(sysSmsBatch);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 获取同一批次状态为01的短信发送
	 * @param sysSmsBatch
	 * @param loginInfo
	 * @return
	 */
	private ResultInfo sendMessage(SysSmsBatch sysSmsBatch,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		SysSmsInfoExample sysSmsInfoExample = new SysSmsInfoExample();
		SysSmsInfoExample.Criteria criteria = sysSmsInfoExample.createCriteria();
		criteria.andSysSmsBatchIdEqualTo(sysSmsBatch.getSysSmsBatchId())
				.andSendStatusEqualTo("01")
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<SysSmsInfo> sysSmsInfoList = sysSmsInfoMapper.selectByExample(sysSmsInfoExample);
		resultInfo = sendSms(sysSmsInfoList,loginInfo);
		return resultInfo;
	}
	
	/**
	 * 创建并发送产品预约审核打款短信
	 * @author ZYM
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo createAndSendProductOrderAuditSms(PDAmountOrderInfo pdAmountOrderInfo,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成到账短信===========");
		try {
			//1.获取到账短信模板
			String smsType = "05";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成到账短信失败，失败原因：未获取到短信模板！");
				logger.info("生成到账短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			
			//2.获取短信map信息
			Map paramMap = new HashMap();
			paramMap.put("pdAmountOrderInfoId", pdAmountOrderInfo.getPdAmountOrderInfoId());
			Map pdAmountrderMap = smsServiceMapper.getPdAmountrderMap(paramMap);
			if(pdAmountrderMap==null){
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			
			//3.循环模板发短短信
			SysSmsBatch sysSmsBatch = new SysSmsBatch();
			sysSmsBatch.setSmsType(smsType);
			sysSmsBatch.setSmsTypeRelationNo((Long)pdAmountrderMap.get("pdAmountOrderInfoId"));
			sysSmsBatch.setSmsTypeRelationDate(DateUtils.getDate(currentDate));
			sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
			//01-未处理，02-已处理
			sysSmsBatch.setSmsStatus("01");
			BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
			sysSmsBatchMapper.insert(sysSmsBatch);
			logger.info("======="+sysSmsBatch.getSysSmsBatchId()+"批次的产品预约"+pdAmountrderMap.get("pdAmountOrderInfoId"));
			for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
				String sendObjType = sysSmsTemplate.getSnedObjType();
				String template = sysSmsTemplate.getSmsTemplate();
				String sendContent = setValueToTemplate(pdAmountrderMap, template);
				SysSmsInfo sysSmsInfo = new SysSmsInfo();
				sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
				sysSmsInfo.setBusinessNo((Long)pdAmountrderMap.get("pdAmountOrderInfoId"));
				//01-与交易流水号关联，02-与客户流水号关联,03-预约流水号关联
				sysSmsInfo.setBusinessType("03");
				//短信发送对象类型，01-客户，02-理财经理
				if ("01".equals(sendObjType)) {
					logger.info("=====生成产品预约打款审核短信，客户为"+pdAmountrderMap.get("custName").toString()+"===========");
					//sysSmsInfo.setSendObj((Long)pdAmountrderMap.get("custName"));
					sysSmsInfo.setSendObjName(pdAmountrderMap.get("custName").toString());
					sysSmsInfo.setSendMobile(pdAmountrderMap.get("custMobile").toString());
				}else if("02".equals(sendObjType)){
					logger.info("=====生成产品预约打款审核短信，理财经理为"+pdAmountrderMap.get("agentName").toString()+"===========");
					sysSmsInfo.setSendObj((Long)pdAmountrderMap.get("agentId"));
					sysSmsInfo.setSendObjName(pdAmountrderMap.get("agentName").toString());
					sysSmsInfo.setSendMobile(pdAmountrderMap.get("agentMobile").toString());
				}
				sysSmsInfo.setSendObjType(sendObjType);
				sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
				//01-未发送，02-已发送
				sysSmsInfo.setSendStatus("01");
				sysSmsInfo.setSendContent(sendContent);
				BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
				sysSmsInfoMapper.insert(sysSmsInfo);
				logger.info("=======产品预约"+pdAmountrderMap.get("pdAmountOrderInfoId")+"的到账短信生成完成======");
				//发送短信
				resultInfo = sendMessage(sysSmsBatch,loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 生成产品成立短信
	 * @author SLY
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createProductFoundMes(TradeStatusInfo tradeStatusInfo,LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成产品成立短信===========");
		ResultInfo resultInfo = new ResultInfo();
		try {
			//1.获取产品成立短信模板
			String smsType = "01";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成产品成立短信失败，失败原因：未获取到短信模板！");
				logger.info("生成产品成立短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			
			//2.获取交易信息
			Map paramMap = new HashMap();
			paramMap.put("tradeStatusInfoId", tradeStatusInfo.getTradeStatusInfoId());
			Map tradeInfoMap = smsServiceMapper.getTradeInfoMap(paramMap);
			if(tradeInfoMap==null){
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			
			//3.循环模板发短短信
			SysSmsBatchExample sysSmsBatchExample = new SysSmsBatchExample();
			sysSmsBatchExample.createCriteria().andSmsTypeEqualTo("01").andSmsTypeRelationDateEqualTo(DateUtils.getDate(tradeInfoMap.get("found_Date").toString()))
			.andSmsTypeRelationNoEqualTo((Long)tradeInfoMap.get("productId")).andRcStateEqualTo("E");
			List<SysSmsBatch> smsBatchs = sysSmsBatchMapper.selectByExample(sysSmsBatchExample);
			SysSmsBatch sysSmsBatch = new SysSmsBatch();
			if(smsBatchs.size() == 0){
				sysSmsBatch.setSmsType(smsType);
				sysSmsBatch.setSmsTypeRelationNo((Long)tradeInfoMap.get("productId"));
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getDate(tradeInfoMap.get("found_Date").toString()));
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
			}else {
				sysSmsBatch = smsBatchs.get(0);
			}
				logger.info("======="+sysSmsBatch.getSysSmsBatchId()+"批次的交易"+tradeInfoMap.get("tradeInfoId")+"的产品"+tradeInfoMap.get("productId")+"的成立日"+tradeInfoMap.get("found_Date").toString());
					for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
						String sendObjType = sysSmsTemplate.getSnedObjType();
						String template = sysSmsTemplate.getSmsTemplate();
						String sendContent = setValueToTemplate(tradeInfoMap, template);
						SysSmsInfo sysSmsInfo = new SysSmsInfo();
						sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
						sysSmsInfo.setBusinessNo((Long)tradeInfoMap.get("tradeInfoId"));
						//01-与交易流水号关联，02-与客户流水号关联
						sysSmsInfo.setBusinessType("01");
						//短信发送对象类型，01-客户，02-理财经理
						if ("01".equals(sendObjType)) {
							sysSmsInfo.setSendObj((Long)tradeInfoMap.get("custBaseInfoId"));
							sysSmsInfo.setSendObjName(tradeInfoMap.get("custName").toString());
							sysSmsInfo.setSendMobile(tradeInfoMap.get("custMobile").toString());
						}else if("02".equals(sendObjType)){
							SysSmsInfoExample sysSmsInfoExample = new SysSmsInfoExample();
							sysSmsInfoExample.createCriteria().andSysSmsBatchIdEqualTo(sysSmsBatch.getSysSmsBatchId()).andSendObjTypeEqualTo("02")
							.andSendObjEqualTo((Long)tradeInfoMap.get("agentId"));
							List<SysSmsInfo> lSmsInfos = sysSmsInfoMapper.selectByExample(sysSmsInfoExample);
							if (lSmsInfos.size()>0) {
								continue;
							}
							sysSmsInfo.setSendObj((Long)tradeInfoMap.get("agentId"));
							sysSmsInfo.setSendObjName(tradeInfoMap.get("agentName").toString());
							sysSmsInfo.setSendMobile(tradeInfoMap.get("agentMobile").toString());
						}
						sysSmsInfo.setSendObjType(sendObjType);
						sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
						//01-未发送，02-已发送
						sysSmsInfo.setSendStatus("01");
						sysSmsInfo.setSendContent(sendContent);
						BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
						sysSmsInfoMapper.insert(sysSmsInfo);
					}
				logger.info("=======产品("+tradeInfoMap.get("productName")+")的成立短信生成完成======");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		logger.info("====="+currentDate+"==生成产品成立短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 获取审核短信内容
	 * @author Zym
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid getSmsAuditInfo(DataGridModel dgm, Map paramMap) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer	total = smsServiceMapper.getSmsAuditInfoCount(paramMap);
		List<Map>	resultList = smsServiceMapper.getSmsAuditInfo(paramMap);
		//datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 获取客户审核短信内容
	 * @author chengong
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid getCustSmsAuditInfo(DataGridModel dgm, Map paramMap) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer	total = smsServiceMapper.getCustSmsAuditInfoCount(paramMap);
		List<Map>	resultList = smsServiceMapper.getCustSmsAuditInfo(paramMap);
		//datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 生成产品期间分配短信
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createIncomeDisMessage(Map<String, Object> incomDisNotDueProduct, LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成期间分配短信===========");
		ResultInfo resultInfo = new ResultInfo();
		try {
			//1.产品成立短信,获取短信模板
			String smsType = "03";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成产品期间分配短信失败，失败原因：未获取到短信模板！");
				logger.info("生成产品期间分配短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			
			//2.获取交易信息
			Map paramMap = new HashMap();
			paramMap.put("productId", incomDisNotDueProduct.get("productId").toString());
			paramMap.put("distributeDate", incomDisNotDueProduct.get("distributeDate").toString());
			List<Map<String,String>> tradeInfoList = smsServiceMapper.getIncomDisTradeInfo(paramMap);
			if(tradeInfoList.size()==0){
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			//将产品信息存到batch表中
			 logger.info("=======产品"+incomDisNotDueProduct.get("productName")+"生成期间分配短信===========");
				SysSmsBatch sysSmsBatch = new SysSmsBatch();
				sysSmsBatch.setSmsType(smsType);
				sysSmsBatch.setSmsTypeRelationNo((Long)incomDisNotDueProduct.get("productId"));
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getDate(incomDisNotDueProduct.get("distributeDate").toString()));
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
			//将所得的交易信息
			for (Map tradeInfoMap : tradeInfoList) {
				//3.循环模板发短短信
			   
				logger.info("=======产品"+tradeInfoMap.get("productName")+"的分配日期"+tradeInfoMap.get("distributeDate"));
					for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
						String sendObjType = sysSmsTemplate.getSnedObjType();
						String template = sysSmsTemplate.getSmsTemplate();
						String sendContent = setValueToTemplate(tradeInfoMap, template);
						SysSmsInfo sysSmsInfo = new SysSmsInfo();
						Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
						sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
						sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
						sysSmsInfo.setBusinessNo((Long)tradeInfoMap.get("tradeInfoId"));
						//01-与交易流水号关联，02-与客户流水号关联
						sysSmsInfo.setBusinessType("01");
						//短信发送对象类型，01-客户，02-理财经理
						if ("01".equals(sendObjType)) {
							sysSmsInfo.setSendObj((Long)tradeInfoMap.get("custBaseInfoId"));
							sysSmsInfo.setSendObjName(tradeInfoMap.get("custName").toString());
							sysSmsInfo.setSendMobile(tradeInfoMap.get("custMobile").toString());
						}else if("02".equals(sendObjType)){
							sysSmsInfo.setSendObj((Long)tradeInfoMap.get("agentId"));
							sysSmsInfo.setSendObjName(tradeInfoMap.get("agentName").toString());
							sysSmsInfo.setSendMobile(tradeInfoMap.get("agentMobile").toString());
						}
						sysSmsInfo.setSendObjType(sendObjType);
						sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
						//01-未发送，02-已发送
						sysSmsInfo.setSendStatus("01");
						sysSmsInfo.setSendContent(sendContent);
						BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
						sysSmsInfoMapper.insert(sysSmsInfo);
					}
				logger.info("=======客户("+tradeInfoMap.get("custName")+")的"+tradeInfoMap.get("productName")+"产品期间分配短信生成完成======");
			}
			
			//获取固定人员短信模板
			SysSmsTemplate managerTemplate = getSmsTemplates("11").get(0);
			//获取内勤人员数组
			List<Map> managerInfoList = managerServiceMapper.getAllManagerInfo(paramMap);
			Map tradeInfoMap = tradeInfoList.get(0);
			for (Map managerMap : managerInfoList) {
				SysSmsInfo sysSmsInfo = new SysSmsInfo();
				Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
				sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
				sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
				//sysSmsInfo.setBusinessNo((Long)tradeInfoMap.get("tradeInfoId"));
				//01-与交易流水号关联，02-与客户流水号关联
				sysSmsInfo.setBusinessType("01");
				String sendContent = setValueToTemplate(tradeInfoMap, managerTemplate.getSmsTemplate());
				//设置发送内勤人员信息
				sysSmsInfo.setSendObj((Long)managerMap.get("managerId"));
				sysSmsInfo.setSendObjName(managerMap.get("chnName").toString());
				sysSmsInfo.setSendMobile(managerMap.get("mobile").toString());
				sysSmsInfo.setSendObjType("03");
				sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
				//01-未发送，02-已发送
				sysSmsInfo.setSendStatus("01");
				sysSmsInfo.setSendContent(sendContent);
				BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
				sysSmsInfoMapper.insert(sysSmsInfo);
			}
			logger.info("=======产品("+tradeInfoMap.get("productName")+")的"+"内勤人员期间分配短信生成完成======");
		
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		logger.info("====="+currentDate+"==生成产品期间分配短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 生成产品到期清算短信
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createIncomeDisAllMessage(Map<String, Object> incomDisNotDueProduct, LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成到期清算短信===========");
		ResultInfo resultInfo = new ResultInfo();
		try {
			//1.产品成立短信,获取短信模板
			String smsType = "04";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成产品到期清算短信失败，失败原因：未获取到短信模板！");
				logger.info("生成产品到期清算短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			
			//2.获取交易信息
			Map paramMap = new HashMap();
			paramMap.put("productId", incomDisNotDueProduct.get("productId").toString());
			paramMap.put("distributeDate", incomDisNotDueProduct.get("distributeDate").toString());
			List<Map<String, String>> tradeInfoList = smsServiceMapper.getIncomDisAllTradeInfo(paramMap);
			if(tradeInfoList.size()==0){
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			//将产品信息存到batch表中
			 logger.info("=======产品"+incomDisNotDueProduct.get("productName")+"生成到期清算短信===========");
				SysSmsBatch sysSmsBatch = new SysSmsBatch();
				sysSmsBatch.setSmsType(smsType);
				sysSmsBatch.setSmsTypeRelationNo((Long)incomDisNotDueProduct.get("productId"));
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getDate(incomDisNotDueProduct.get("distributeDate").toString()));
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
			//将所得的交易信息
			for (Map tradeInfoMap : tradeInfoList) {
				//3.循环模板发短短信
			   
				logger.info("=======产品"+tradeInfoMap.get("productName")+"的分配日期"+tradeInfoMap.get("distributeDate"));
					for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
						String sendObjType = sysSmsTemplate.getSnedObjType();
						String template = sysSmsTemplate.getSmsTemplate();
						String sendContent = setValueToTemplate(tradeInfoMap, template);
						SysSmsInfo sysSmsInfo = new SysSmsInfo();
						Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
						sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
						sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
						sysSmsInfo.setBusinessNo((Long)tradeInfoMap.get("tradeInfoId"));
						//01-与交易流水号关联，02-与客户流水号关联
						sysSmsInfo.setBusinessType("01");
						//短信发送对象类型，01-客户，02-理财经理
						if ("01".equals(sendObjType)) {
							sysSmsInfo.setSendObj((Long)tradeInfoMap.get("custBaseInfoId"));
							sysSmsInfo.setSendObjName(tradeInfoMap.get("custName").toString());
							sysSmsInfo.setSendMobile(tradeInfoMap.get("custMobile").toString());
						}else if("02".equals(sendObjType)){
							sysSmsInfo.setSendObj((Long)tradeInfoMap.get("agentId"));
							sysSmsInfo.setSendObjName(tradeInfoMap.get("agentName").toString());
							sysSmsInfo.setSendMobile(tradeInfoMap.get("agentMobile").toString());
						}
						sysSmsInfo.setSendObjType(sendObjType);
						sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
						//01-未发送，02-已发送
						sysSmsInfo.setSendStatus("01");
						sysSmsInfo.setSendContent(sendContent);
						BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
						sysSmsInfoMapper.insert(sysSmsInfo);
					}
				logger.info("=======客户("+tradeInfoMap.get("custName")+")的"+tradeInfoMap.get("productName")+"产品到期清算短信生成完成======");
			}
			//获取固定人员短信模板
			SysSmsTemplate managerTemplate = getSmsTemplates("12").get(0);
			//获取内勤人员数组
			List<Map> managerInfoList = managerServiceMapper.getAllManagerInfo(paramMap);
			Map tradeInfoMap = tradeInfoList.get(0);
			for (Map managerMap : managerInfoList) {
				SysSmsInfo sysSmsInfo = new SysSmsInfo();
				Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
				sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
				sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
				//sysSmsInfo.setBusinessNo((Long)tradeInfoMap.get("tradeInfoId"));
				//01-与交易流水号关联，02-与客户流水号关联
				sysSmsInfo.setBusinessType("01");
				String sendContent = setValueToTemplate(tradeInfoMap, managerTemplate.getSmsTemplate());
				//设置发送内勤人员信息
				sysSmsInfo.setSendObj((Long)managerMap.get("managerId"));
				sysSmsInfo.setSendObjName(managerMap.get("chnName").toString());
				sysSmsInfo.setSendMobile(managerMap.get("mobile").toString());
				sysSmsInfo.setSendObjType("03");
				sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
				//01-未发送，02-已发送
				sysSmsInfo.setSendStatus("01");
				sysSmsInfo.setSendContent(sendContent);
				BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
				sysSmsInfoMapper.insert(sysSmsInfo);
			}
			logger.info("=======产品("+tradeInfoMap.get("productName")+")的"+"内勤人员到期清算短信生成完成======");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		logger.info("====="+currentDate+"==生成产品到期清算短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 生成产品净值短信
	 * @author SLY
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo createPublicNetValueMes(String returnPdwealthNetValueId,String isOrder, String specialType, LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成产品净值短信===========");
		ResultInfo resultInfo = new ResultInfo();
		try {
			//1.获取净值短信模板
			String smsType = "02";
			if ("01".equals(isOrder)) {
				smsType = "07";
			}
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成产品净值短信失败，失败原因：未获取到短信模板！");
				logger.info("生成产品净值短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			
			//2.获取产品净值信息
			Map paramMap = new HashMap();
			paramMap.put("pdWealthNetValueId", new Long(returnPdwealthNetValueId));
			List<Map> pdwealthNetValueList = smsServiceMapper.getPdwealthNetValueList(paramMap);
			if(pdwealthNetValueList==null||pdwealthNetValueList.size()==0){
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			
			//3.循环List发短信
			PDWealthNetValue pdwealthNetValue = pdWealthNetValueMapper.selectByPrimaryKey(new Long(returnPdwealthNetValueId));
			PDWealth pdWealth = pdWealthMapper.selectByPrimaryKey(pdwealthNetValue.getPdWealthId());
			SysSmsBatch sysSmsBatch = new SysSmsBatch();
			sysSmsBatch.setSmsType("02");
			sysSmsBatch.setSmsTypeRelationNo(pdWealth.getProductId());
			sysSmsBatch.setSmsTypeRelationDate(pdwealthNetValue.getPublicDate());
			sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
			//01-未处理，02-已处理
			sysSmsBatch.setSmsStatus("01");
			BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
			sysSmsBatchMapper.insert(sysSmsBatch);
			logger.info("======="+sysSmsBatch.getSysSmsBatchId()+"批次的产品"+pdWealth.getProductId()+",开放日"+pdwealthNetValue.getPublicDate()+"===========");
			
			//固定人员短信创建
			DefCodeExample defCodeExample = new DefCodeExample();
			DefCodeExample.Criteria criteria = defCodeExample.createCriteria();
			criteria.andCodeTypeEqualTo("smsAgentCode");
			List<DefCode> smsAgentCodeList = defCodeMapper.selectByExample(defCodeExample);
			//遍历所有工号并创建短信
			for (DefCode defCode : smsAgentCodeList) {
				//创建营销员工信息map
				Map defMap = smsServiceMapper.getDefMap(defCode.getCodeName());
				String productName = pdwealthNetValueList.get(0).get("productName").toString();
				String publicDate = pdwealthNetValueList.get(0).get("publicDate").toString();
				String netValue = pdwealthNetValueList.get(0).get("netValue").toString();
				String earnRate = pdwealthNetValueList.get(0).get("earnRate").toString();
				defMap.put("productName", productName);
				defMap.put("publicDate", publicDate);
				defMap.put("netValue", netValue);
				defMap.put("earnRate", earnRate);
				//判断是否为特殊短信类型
				if (!"".equals(specialType)) {
					if ("01".equals(specialType)) {
						defMap.put("specialType", "月中开放日");
					}else{
						defMap.put("specialType", "月末开放日");
					}
				}
				//获取短信模板内容
				SysSmsTemplateExample sysSmsTemplateExample = new SysSmsTemplateExample();
				//判断是否生成预估短信
				if ("01".equals(isOrder)) {
					sysSmsTemplateExample.createCriteria().andSmsTypeEqualTo("07").andSnedObjTypeEqualTo("02")
					.andRcStateEqualTo("E");
				}else {
					sysSmsTemplateExample.createCriteria().andSmsTypeEqualTo("02").andSnedObjTypeEqualTo("02")
					.andRcStateEqualTo("E");
				}
				List<SysSmsTemplate> sysSmsTemplates = sysSmsTemplateMapper.selectByExample(sysSmsTemplateExample);
				String sendContent = setValueToTemplate(defMap, sysSmsTemplates.get(0).getSmsTemplate());
				//保存SysSmsInfo信息
				SysSmsInfo sysSmsInfo = new SysSmsInfo();
				sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
				sysSmsInfo.setSendObj((Long)defMap.get("agentId"));
				sysSmsInfo.setSendObjName(defMap.get("agentName").toString());
				sysSmsInfo.setSendMobile(defMap.get("agentPhone").toString());
				sysSmsInfo.setSendObjType("02");
				sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
				//01-未发送，02-已发送
				sysSmsInfo.setSendStatus("01");
				sysSmsInfo.setSendContent(sendContent);
				BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
				sysSmsInfoMapper.insert(sysSmsInfo);
			}
			
			//为每笔交易的客户（理财经理）发短信
			for(Map pdwealthNetValueMap:pdwealthNetValueList){
				logger.info("=======交易"+pdwealthNetValueMap.get("tradeInfoId")+"生成产品成立短信===========");
				//循环模板发短信
				for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
					String sendObjType = sysSmsTemplate.getSnedObjType();
					String template = sysSmsTemplate.getSmsTemplate();
					//判断是否为特殊短信类型
					if (!"".equals(specialType)) {
						if ("01".equals(specialType)) {
							pdwealthNetValueMap.put("specialType", "月中开放日");
						}else{
							pdwealthNetValueMap.put("specialType", "月末开放日");
						}
					}
					String sendContent = setValueToTemplate(pdwealthNetValueMap, template);
					SysSmsInfo sysSmsInfo = new SysSmsInfo();
					sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
					sysSmsInfo.setBusinessNo((Long)pdwealthNetValueMap.get("tradeInfoId"));
					//01-与交易流水号关联，02-与客户流水号关联
					sysSmsInfo.setBusinessType("01");
					//短信发送对象类型，01-客户，02-理财经理
					if ("01".equals(sendObjType)) {
						sysSmsInfo.setSendObj((Long)pdwealthNetValueMap.get("custBaseInfoId"));
						sysSmsInfo.setSendObjName(pdwealthNetValueMap.get("custName").toString());
						sysSmsInfo.setSendMobile(pdwealthNetValueMap.get("custMobile").toString());
					}else if("02".equals(sendObjType)){
						SysSmsInfoExample sysSmsInfoExample = new SysSmsInfoExample();
						sysSmsInfoExample.createCriteria().andSysSmsBatchIdEqualTo(sysSmsBatch.getSysSmsBatchId()).andSendObjTypeEqualTo("02")
						.andSendObjEqualTo((Long)pdwealthNetValueMap.get("agentId"));
						List<SysSmsInfo> lSmsInfos = sysSmsInfoMapper.selectByExample(sysSmsInfoExample);
						if (lSmsInfos.size()>0) {
							continue;
						}
						sysSmsInfo.setSendObj((Long)pdwealthNetValueMap.get("agentId"));
						sysSmsInfo.setSendObjName(pdwealthNetValueMap.get("agentName").toString());
						sysSmsInfo.setSendMobile(pdwealthNetValueMap.get("agentMobile").toString());
					}
					sysSmsInfo.setSendObjType(sendObjType);
					sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
					//01-未发送，02-已发送
					sysSmsInfo.setSendStatus("01");
					sysSmsInfo.setSendContent(sendContent);
					BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
					sysSmsInfoMapper.insert(sysSmsInfo);
				}
				logger.info("=======产品("+pdwealthNetValueMap.get("productName")+")的净值短信生成完成======");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		logger.info("====="+currentDate+"==生成产品净值短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 生成ToAgent生日短信
	 * @author SLY
	 */
	@Override
	public synchronized ResultInfo createBirthToAgentSms() {
		ResultInfo resultInfo = new ResultInfo();
		//获取批处理操作信息:默认fms有权限操作批处理
		LoginInfo loginInfo = setBatchLoginInfo();
		if (loginInfo==null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("生成短信失败，失败原因：未获取到批处理操作信息！");
			logger.info("生成短信失败，失败原因：未获取到批处理操作信息！");
			return resultInfo;
		}
		//生成ToAgent生日祝福短信,并且直接发送
		resultInfo  = createBirthToAgentSms(loginInfo);
		if (!resultInfo.isSuccess()) {
			logger.info(resultInfo.getMsg());
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("短信发送完成");
		return resultInfo;
	}
	
	/**
	 * 生成ToAgent生日短信
	 * @param loginInfo
	 * @author SLY
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createBirthToAgentSms(LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String currentDate = DateUtils.getCurrentDate();
		logger.info("=====" + currentDate + "==开始生成ToAgent生日短信===========");
		try {
			// 1.获取ToAgent生日短信模板
			String smsType = "06";
			String sendObjType = "02";
			SysSmsTemplateExample sysSmsTemplateExample = new SysSmsTemplateExample();
			SysSmsTemplateExample.Criteria criteria = sysSmsTemplateExample.createCriteria();
			criteria.andSmsTypeEqualTo(smsType).andSnedObjTypeEqualTo(sendObjType)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<SysSmsTemplate> sysSmsTemplates = sysSmsTemplateMapper.selectByExample(sysSmsTemplateExample);
			SysSmsTemplate sysSmsTemplate = sysSmsTemplates.get(0);
			if (sysSmsTemplate == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成生日短信失败，失败原因：未获取到短信模板！");
				logger.info("生成生日短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}

			// 2.获取明日生日的客户所属的理财经理list
			String calDate = DateUtils.calDate(currentDate, 1, "D");
			Map paramMap = new HashMap();
			paramMap.put("calDate", calDate);
			List<Map> listCustomerList = smsServiceMapper.getAllCustInfo(paramMap);
			List<Map> agentInfoList = new ArrayList<Map>();
			if (listCustomerList == null || listCustomerList.size() == 0) {
				logger.info("明天没有客户生日！");
				resultInfo.setSuccess(true);
				return resultInfo;
			} else {
				for (Map customerMap : listCustomerList) {
					String agentId = customerMap.get("agentId").toString();
					String custBaseInfoId = customerMap.get("custBaseInfoId").toString();
					Map map = new HashMap();
					map.put("calDate", calDate);
					map.put("sendObjType", sendObjType);
					map.put("agentId", new Long(agentId));
					map.put("custBaseInfoId", new Long(custBaseInfoId));
					Map agentMap = smsServiceMapper.getAgentInfo(map);
					agentInfoList.add(agentMap);
				}
			}

			// 3.记录生日短信批处理：发给理财经理
			SysSmsBatch sysSmsBatch = new SysSmsBatch();
			sysSmsBatch.setSmsType(smsType);
			sysSmsBatch.setSmsTypeRelationNo(new Long(sendObjType));
			sysSmsBatch.setSmsTypeRelationDate(DateUtils.getDate(calDate));
			sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
			// 01-未处理，02-已处理
			sysSmsBatch.setSmsStatus("01");
			BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
			sysSmsBatchMapper.insert(sysSmsBatch);
			logger.info("=====" + sysSmsBatch.getSysSmsBatchId() + "批次的生日客户所属理财经理的提醒短信，当年生日为" + calDate
					+ "===========");

			// 4.循环理财经理发短信
			for (Map agentMap : agentInfoList) {
				logger.info("=====生成明日生日客户所属理财经理提醒短信，理财经理为" + agentMap.get("agentName") + "===========");
				String template = sysSmsTemplate.getSmsTemplate();
				// 获取正确的短信内容
				String sendContent = setValueToTemplate(agentMap, template);
				SysSmsInfo sysSmsInfo = new SysSmsInfo();
				sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
				sysSmsInfo.setBusinessNo((Long) agentMap.get("custBaseInfoId"));
				// 01-与交易流水号关联，02-与客户流水号关联
				sysSmsInfo.setBusinessType("02");
				sysSmsInfo.setSendObj((Long) agentMap.get("agentId"));
				sysSmsInfo.setSendObjName(agentMap.get("agentName").toString());
				sysSmsInfo.setSendMobile(agentMap.get("mobile").toString());
				sysSmsInfo.setSendTime(DateUtils.getDateTime(currentDate + " 15:00:00"));
				sysSmsInfo.setSendObjType(sendObjType);
				// 01-未发送，02-已发送
				sysSmsInfo.setSendStatus("01");
				sysSmsInfo.setSendContent(sendContent);
				BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
				sysSmsInfoMapper.insert(sysSmsInfo);
			}
			logger.info("=======" + calDate + "生日的客户所属理财经理生日提醒短信生成完成======");
			// 发送生日短信
			resultInfo = sendMessage(sysSmsBatch, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 删除短信
	 * @author SLY
	 */
	@Override
	public ResultInfo deleteSms(String sysSmsBatchId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		if (sysSmsBatchId == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("sysSmsBatchId为空，删除失败！");
			return resultInfo;
		}
		try {
			
			//删除短信信息表
			SysSmsInfoExample smsInfoExample = new SysSmsInfoExample();
			smsInfoExample.createCriteria().andSysSmsBatchIdEqualTo(new Long(sysSmsBatchId)).andRcStateEqualTo("E");
			List<SysSmsInfo> smsInfos = sysSmsInfoMapper.selectByExample(smsInfoExample);
			for(SysSmsInfo sysSmsInfo : smsInfos){
				BeanUtils.deleteObjectSetOperateInfo(sysSmsInfo, loginInfo);
				sysSmsInfoMapper.updateByPrimaryKey(sysSmsInfo);
			}
			//删除短信批次表
			SysSmsBatch sysSmsBatch = sysSmsBatchMapper.selectByPrimaryKey(new Long(sysSmsBatchId));
			BeanUtils.deleteObjectSetOperateInfo(sysSmsBatch, loginInfo);
			sysSmsBatchMapper.updateByPrimaryKey(sysSmsBatch);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除短信出现异常！");
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功！");
		return resultInfo;
	}
/*
	*//**
	 * 春节祝福短信
	 *//*
	@Override
	public synchronized ResultInfo createSpringFestival() {
		ResultInfo resultInfo = new ResultInfo();
		//获取批处理操作信息:默认fms有权限操作批处理
		LoginInfo loginInfo = setBatchLoginInfo();
		if (loginInfo==null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("生成短信失败，失败原因：未获取到批处理操作信息！");
			logger.info("生成短信失败，失败原因：未获取到批处理操作信息！");
			return resultInfo;
		}
		//生成春节祝福短信,并且直接发送
		resultInfo  = createSpringFestivalSms(loginInfo);
		if (!resultInfo.isSuccess()) {
			logger.info(resultInfo.getMsg());
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("短信发送完成");
		return resultInfo;
	}
*/
/*	*//**
	 * 生成春节祝福短信
	 * @param loginInfo
	 * @return
	 *//*
	private ResultInfo createSpringFestivalSms(LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String currentDate = DateUtils.getCurrentDate();
		logger.info("=====" + currentDate + "开始生成春节祝福短信===========");
		try {
			// 1.获取春节短信模板
			String smsType = "07";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList == null || sysSmsTemplateList.size() == 0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成春节祝福短信失败，失败原因：未获取到短信模板！");
				logger.info("生成春节祝福短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}

			// 2.获取所有客户和理财经理list
			List<Map> listAllCust = smsServiceMapper.getCustInfo();
			if (listAllCust == null || listAllCust.size() == 0) {
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			List<Map> listAllAgent = smsServiceMapper.getAllAgentInfo();
			if (listAllAgent == null || listAllAgent.size() == 0) {
				resultInfo.setSuccess(true);
				return resultInfo;
			}

			// 3.记录春节祝福短信批处理
			SysSmsBatch sysSmsBatch = new SysSmsBatch();
			sysSmsBatch.setSmsType(smsType);
			// sysSmsBatch.setSmsTypeRelationNo(new Long(sendObjType));
			sysSmsBatch.setSmsTypeRelationDate(DateUtils.getDate(currentDate));
			sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
			// 01-未处理，02-已处理
			sysSmsBatch.setSmsStatus("01");
			BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
			sysSmsBatchMapper.insert(sysSmsBatch);

			// 4.循环模板发短信
			for (SysSmsTemplate sysSmsTemplate : sysSmsTemplateList) {
				String sendObjType = sysSmsTemplate.getSnedObjType();
				String template = sysSmsTemplate.getSmsTemplate();
				//// 短信发送对象类型，01-客户，02-理财经理
				if ("01".equals(sendObjType)) {
					// 循环客户发春节祝福短信
					for (Map custMap : listAllCust) {
						String sendContent = setValueToTemplate(custMap, template);
						SysSmsInfo sysSmsInfo = new SysSmsInfo();
						sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
						// sysSmsInfo.setBusinessNo((Long)customerMap.get("custBaseInfoId"));
						// 01-与交易流水号关联，02-与客户流水号关联
						sysSmsInfo.setBusinessType("02");
						sysSmsInfo.setSendObj((Long) custMap.get("custBaseInfoId"));
						sysSmsInfo.setSendObjName(custMap.get("custName").toString());
						sysSmsInfo.setSendMobile(custMap.get("custMobile").toString());
						sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
						sysSmsInfo.setSendObjType(sendObjType);
						// 01-未发送，02-已发送
						sysSmsInfo.setSendStatus("01");
						sysSmsInfo.setSendContent(sendContent);
						BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
						sysSmsInfoMapper.insert(sysSmsInfo);
						// 发送春节短信
						resultInfo = sendMessage(sysSmsBatch, loginInfo);
					}
				} else if ("02".equals(sendObjType)) {
					// 循环客户发春节祝福短信
					for (Map agentMap : listAllAgent) {
						String sendContent = setValueToTemplate(agentMap, template);
						SysSmsInfo sysSmsInfo = new SysSmsInfo();
						sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
						// sysSmsInfo.setBusinessNo((Long)customerMap.get("custBaseInfoId"));
						// 01-与交易流水号关联，02-与客户流水号关联
						sysSmsInfo.setBusinessType("02");
						sysSmsInfo.setSendObj((Long) agentMap.get("agentId"));
						sysSmsInfo.setSendObjName(agentMap.get("agentName").toString());
						sysSmsInfo.setSendMobile(agentMap.get("agentMobile").toString());
						sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
						sysSmsInfo.setSendObjType(sendObjType);
						// 01-未发送，02-已发送
						sysSmsInfo.setSendStatus("01");
						sysSmsInfo.setSendContent(sendContent);
						BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
						sysSmsInfoMapper.insert(sysSmsInfo);
						// 发送春节短信
						resultInfo = sendMessage(sysSmsBatch, loginInfo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}*/
	
	/**
	 * 生成手机变更短信
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createMoidfyMobileMessage(Long custBaseInfoId,String newMobile, LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成客户手机变更短信===========");
		ResultInfo resultInfo = new ResultInfo();
		try {
			//1.客户手机变更短信,获取短信模板
			String smsType = "08";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成客户手机变更短信失败，失败原因：未获取到短信模板！");
				logger.info("生成客户手机变更短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			
			//2.获取客户信息
			CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
			Map paramMap = new HashMap();
			paramMap.put("custBaseInfoId", custBaseInfoId);
			List<Map> custInfoList = smsServiceMapper.getCustMobile(paramMap);
			Map custInfoMap =  custInfoList.get(0);
			//将客户信息存到batch表中
			 logger.info("=======客户"+custBaseInfo.getChnName()+"生成手机变更短信===========手机"+newMobile+"===========");
				SysSmsBatch sysSmsBatch = new SysSmsBatch();
				sysSmsBatch.setSmsType(smsType);
				sysSmsBatch.setSmsTypeRelationNo(custBaseInfoId);
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getCurrentTimestamp());
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
			
				//使用模板创建短信
					for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
						String sendObjType = sysSmsTemplate.getSnedObjType();
						String template = sysSmsTemplate.getSmsTemplate();
						String sendContent = setValueToTemplate(custInfoMap, template);
						SysSmsInfo sysSmsInfo = new SysSmsInfo();
						Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
						sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
						sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
						sysSmsInfo.setBusinessNo(custBaseInfoId);
						//01-与交易流水号关联，02-与客户流水号关联
						sysSmsInfo.setBusinessType("02");
						//短信发送对象类型，01-客户，02-理财经理
						if ("01".equals(sendObjType)) {
							sysSmsInfo.setSendObj(custBaseInfoId);
							sysSmsInfo.setSendObjName(custBaseInfo.getChnName());
							sysSmsInfo.setSendMobile(newMobile);
						}else if("02".equals(sendObjType)){
							sysSmsInfo.setSendObj((Long)custInfoMap.get("agentId"));
							sysSmsInfo.setSendObjName(custInfoMap.get("agentName").toString());
							sysSmsInfo.setSendMobile(custInfoMap.get("agentMobile").toString());
						}
						sysSmsInfo.setSendObjType(sendObjType);
						sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
						//01-未发送，02-已发送
						sysSmsInfo.setSendStatus("01");
						sysSmsInfo.setSendContent(sendContent);
						BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
						sysSmsInfoMapper.insert(sysSmsInfo);
					}
				logger.info("=======客户("+custBaseInfo.getChnName()+")的"+"手机变更短信生成完成======");
			
		
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		logger.info("====="+currentDate+"==生成手机变更短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 生成邮箱变更短信
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createMoidfyEmailMessage(Long custBaseInfoId,String email,String custMobile, LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成客户邮箱变更短信===========");
		ResultInfo resultInfo = new ResultInfo();
		try {
			//1.客户手机变更短信,获取短信模板
			String smsType = "09";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成客户邮箱变更短信失败，失败原因：未获取到短信模板！");
				logger.info("生成客户邮箱变更短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			
			//2.获取客户信息
			CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
			Map paramMap = new HashMap();
			paramMap.put("custBaseInfoId", custBaseInfoId);
			List<Map> custInfoList = smsServiceMapper.getCustEmail(paramMap);
			Map custInfoMap =  custInfoList.get(0);
			//将客户信息存到batch表中
			 logger.info("=======客户"+custBaseInfo.getChnName()+"生成邮箱变更短信===========邮箱"+email+"===========");
				SysSmsBatch sysSmsBatch = new SysSmsBatch();
				sysSmsBatch.setSmsType(smsType);
				sysSmsBatch.setSmsTypeRelationNo(custBaseInfoId);
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getCurrentTimestamp());
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
			
				//使用模板创建短信
					for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
						String sendObjType = sysSmsTemplate.getSnedObjType();
						String template = sysSmsTemplate.getSmsTemplate();
						String sendContent = setValueToTemplate(custInfoMap, template);
						SysSmsInfo sysSmsInfo = new SysSmsInfo();
						Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
						sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
						sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
						sysSmsInfo.setBusinessNo(custBaseInfoId);
						//01-与交易流水号关联，02-与客户流水号关联
						sysSmsInfo.setBusinessType("02");
						//短信发送对象类型，01-客户，02-理财经理
						if ("01".equals(sendObjType)) {
							sysSmsInfo.setSendObj(custBaseInfoId);
							sysSmsInfo.setSendObjName(custBaseInfo.getChnName());
							sysSmsInfo.setSendMobile(custMobile);
						}else if("02".equals(sendObjType)){
							sysSmsInfo.setSendObj((Long)custInfoMap.get("agentId"));
							sysSmsInfo.setSendObjName(custInfoMap.get("agentName").toString());
							sysSmsInfo.setSendMobile(custInfoMap.get("agentMobile").toString());
						}
						sysSmsInfo.setSendObjType(sendObjType);
						sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
						//01-未发送，02-已发送
						sysSmsInfo.setSendStatus("01");
						sysSmsInfo.setSendContent(sendContent);
						BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
						sysSmsInfoMapper.insert(sysSmsInfo);
					}
				logger.info("=======客户("+custBaseInfo.getChnName()+")的"+"邮箱变更短信生成完成======");
			
		
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		logger.info("====="+currentDate+"==生成邮箱变更短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 生成账户变更短信
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createMoidfyMobileMessage(Long custBaseInfoId,String existAccNo,String custAccNo, LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成客户账户变更短信===========");
		ResultInfo resultInfo = new ResultInfo();
		try {
			//1.客户账户变更短信,获取短信模板
			String smsType = "10";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成客户账户变更短信失败，失败原因：未获取到短信模板！");
				logger.info("生成客户账户变更短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			
			//2.获取客户信息
			CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
			Map paramMap = new HashMap();
			paramMap.put("custBaseInfoId", custBaseInfoId);
			List<Map> custInfoList = smsServiceMapper.getCustEmail(paramMap);
			Map custInfoMap =  custInfoList.get(0);
			//处理银行卡号，隐藏中间位数
			int existAccLength = existAccNo.length();
			String existAccNumber = existAccNo.substring(0, 4)+"******"+existAccNo.substring(existAccLength-4);
			int custAccLength = custAccNo.length();
			String custAccNumber = custAccNo.substring(0, 4)+"******"+custAccNo.substring(custAccLength-4);
			custInfoMap.put("existAccNo", existAccNumber);
			custInfoMap.put("custAccNo", custAccNumber);
			//将客户信息存到batch表中
			 logger.info("=======客户"+custBaseInfo.getChnName()+"生成账户变更短信===========账户"+custAccNo+"===========");
				SysSmsBatch sysSmsBatch = new SysSmsBatch();
				sysSmsBatch.setSmsType(smsType);
				sysSmsBatch.setSmsTypeRelationNo(custBaseInfoId);
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getCurrentTimestamp());
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
			
				//使用模板创建短信
					for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
						String sendObjType = sysSmsTemplate.getSnedObjType();
						String template = sysSmsTemplate.getSmsTemplate();
						String sendContent = setValueToTemplate(custInfoMap, template);
						SysSmsInfo sysSmsInfo = new SysSmsInfo();
						Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
						sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
						sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
						sysSmsInfo.setBusinessNo(custBaseInfoId);
						//01-与交易流水号关联，02-与客户流水号关联
						sysSmsInfo.setBusinessType("02");
						//短信发送对象类型，01-客户，02-理财经理
						if ("01".equals(sendObjType)) {
							sysSmsInfo.setSendObj(custBaseInfoId);
							sysSmsInfo.setSendObjName(custBaseInfo.getChnName());
							sysSmsInfo.setSendMobile(custInfoMap.get("mobile").toString());
						}else if("02".equals(sendObjType)){
							sysSmsInfo.setSendObj((Long)custInfoMap.get("agentId"));
							sysSmsInfo.setSendObjName(custInfoMap.get("agentName").toString());
							sysSmsInfo.setSendMobile(custInfoMap.get("agentMobile").toString());
						}
						sysSmsInfo.setSendObjType(sendObjType);
						sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
						//01-未发送，02-已发送
						sysSmsInfo.setSendStatus("01");
						sysSmsInfo.setSendContent(sendContent);
						BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
						sysSmsInfoMapper.insert(sysSmsInfo);
					}
				logger.info("=======客户("+custBaseInfo.getChnName()+")的"+"账户变更短信生成完成======");
			
		
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		logger.info("====="+currentDate+"==生成账户变更短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 生成期间分配到账短信
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createIncomeToAcctSms(Map paramMap , LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成期间分配到账短信===========");
		ResultInfo resultInfo = new ResultInfo();
			//1.期间分配到账短信,获取短信模板
			String smsType = "15";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成期间分配到账短信失败，失败原因：未获取到短信模板！");
				logger.info("生成期间分配到账短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			//2.获取交易信息
			List<Map<String,String>> tradeInfoList = smsServiceMapper.getTradeInfoByDistributeDate(paramMap);
			if(tradeInfoList.size()==0){
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			//3.将交易产品信息存到batch表中
			 logger.info("=======产品"+tradeInfoList.get(0).get("productName")+"生成期间分配到账短信===========");
				SysSmsBatch sysSmsBatch = new SysSmsBatch();
				sysSmsBatch.setSmsType(smsType);
				sysSmsBatch.setSmsTypeRelationNo(new Long(paramMap.get("productId").toString()));
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getCurrentTimestamp());
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
				//将获取的交易分别生成短信
				for (Map tradeInfoMap : tradeInfoList) {
					//4.循环模板发短短信
						for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
							String sendObjType = sysSmsTemplate.getSnedObjType();
							String template = sysSmsTemplate.getSmsTemplate();
							String sendContent = setValueToTemplate(tradeInfoMap, template);
							SysSmsInfo sysSmsInfo = new SysSmsInfo();
							Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
							sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
							sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
							sysSmsInfo.setBusinessNo((Long)tradeInfoMap.get("tradeInfoId"));
							//01-与交易流水号关联，02-与客户流水号关联
							sysSmsInfo.setBusinessType("01");
							//短信发送对象类型，01-客户，02-理财经理
							if ("01".equals(sendObjType)) {
								sysSmsInfo.setSendObj((Long)tradeInfoMap.get("custBaseInfoId"));
								sysSmsInfo.setSendObjName(tradeInfoMap.get("custName").toString());
								sysSmsInfo.setSendMobile(tradeInfoMap.get("custMobile").toString());
							}else if("02".equals(sendObjType)){
								sysSmsInfo.setSendObj((Long)tradeInfoMap.get("agentId"));
								sysSmsInfo.setSendObjName(tradeInfoMap.get("agentName").toString());
								sysSmsInfo.setSendMobile(tradeInfoMap.get("agentMobile").toString());
							}
							sysSmsInfo.setSendObjType(sendObjType);
							sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
							//01-未发送，02-已发送
							sysSmsInfo.setSendStatus("01");
							sysSmsInfo.setSendContent(sendContent);
							BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
							sysSmsInfoMapper.insert(sysSmsInfo);
						}
					logger.info("=======客户("+tradeInfoMap.get("custName")+")的"+tradeInfoMap.get("productName")+"产品期间分配短信生成完成======");
				}
				//获取固定人员短信模板
				SysSmsTemplate managerTemplate = getSmsTemplates("17").get(0);
				//获取内勤人员数组
				List<Map> managerInfoList = managerServiceMapper.getAllManagerInfo(paramMap);
				Map tradeInfoMap = tradeInfoList.get(0);
				for (Map managerMap : managerInfoList) {
					SysSmsInfo sysSmsInfo = new SysSmsInfo();
					Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
					sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
					sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
					//01-与交易流水号关联，02-与客户流水号关联
					sysSmsInfo.setBusinessType("01");
					String sendContent = setValueToTemplate(tradeInfoMap, managerTemplate.getSmsTemplate());
					//设置发送内勤人员信息
					sysSmsInfo.setSendObj((Long)managerMap.get("managerId"));
					sysSmsInfo.setSendObjName(managerMap.get("chnName").toString());
					sysSmsInfo.setSendMobile(managerMap.get("mobile").toString());
					sysSmsInfo.setSendObjType("03");
					sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
					//01-未发送，02-已发送
					sysSmsInfo.setSendStatus("01");
					sysSmsInfo.setSendContent(sendContent);
					BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
					sysSmsInfoMapper.insert(sysSmsInfo);
				}
				logger.info("=======产品("+tradeInfoMap.get("productName")+")的"+"内勤人员期间分配短信生成完成======");
		logger.info("====="+currentDate+"==生成期间分配到账短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 生成期间分配到账短信
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createAllIncomeToAcctSms(Map paramMap, LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成到期清算到账短信===========");
		ResultInfo resultInfo = new ResultInfo();
			//1.期间分配到账短信,获取短信模板
			String smsType = "16";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成到期清算到账短信失败，失败原因：未获取到短信模板！");
				logger.info("生成到期清算到账短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			//2.获取交易信息
			List<Map<String,String>> tradeInfoList = smsServiceMapper.getTradeInfoByProductId(paramMap);
			if(tradeInfoList.size()==0){
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			//3.将交易产品信息存到batch表中
			 logger.info("=======产品"+tradeInfoList.get(0).get("productName")+"生成到期清算到账短信===========");
				SysSmsBatch sysSmsBatch = new SysSmsBatch();
				sysSmsBatch.setSmsType(smsType);
				sysSmsBatch.setSmsTypeRelationNo(new Long(paramMap.get("productId").toString()));
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getCurrentTimestamp());
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
				//将获取的交易分别生成短信
				for (Map tradeInfoMap : tradeInfoList) {
					//4.循环模板发短短信
						for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
							String sendObjType = sysSmsTemplate.getSnedObjType();
							String template = sysSmsTemplate.getSmsTemplate();
							String sendContent = setValueToTemplate(tradeInfoMap, template);
							SysSmsInfo sysSmsInfo = new SysSmsInfo();
							Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
							sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
							sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
							sysSmsInfo.setBusinessNo((Long)tradeInfoMap.get("tradeInfoId"));
							//01-与交易流水号关联，02-与客户流水号关联
							sysSmsInfo.setBusinessType("01");
							//短信发送对象类型，01-客户，02-理财经理
							if ("01".equals(sendObjType)) {
								sysSmsInfo.setSendObj((Long)tradeInfoMap.get("custBaseInfoId"));
								sysSmsInfo.setSendObjName(tradeInfoMap.get("custName").toString());
								sysSmsInfo.setSendMobile(tradeInfoMap.get("custMobile").toString());
							}else if("02".equals(sendObjType)){
								sysSmsInfo.setSendObj((Long)tradeInfoMap.get("agentId"));
								sysSmsInfo.setSendObjName(tradeInfoMap.get("agentName").toString());
								sysSmsInfo.setSendMobile(tradeInfoMap.get("agentMobile").toString());
							}
							sysSmsInfo.setSendObjType(sendObjType);
							sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
							//01-未发送，02-已发送
							sysSmsInfo.setSendStatus("01");
							sysSmsInfo.setSendContent(sendContent);
							BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
							sysSmsInfoMapper.insert(sysSmsInfo);
						}
					logger.info("=======客户("+tradeInfoMap.get("custName")+")的"+tradeInfoMap.get("productName")+"产品到期清算短信生成完成======");
				}
				//获取固定人员短信模板
				SysSmsTemplate managerTemplate = getSmsTemplates("18").get(0);
				//获取内勤人员数组
				List<Map> managerInfoList = managerServiceMapper.getAllManagerInfo(paramMap);
				Map tradeInfoMap = tradeInfoList.get(0);
				for (Map managerMap : managerInfoList) {
					SysSmsInfo sysSmsInfo = new SysSmsInfo();
					Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
					sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
					sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
					//01-与交易流水号关联，02-与客户流水号关联
					sysSmsInfo.setBusinessType("01");
					String sendContent = setValueToTemplate(tradeInfoMap, managerTemplate.getSmsTemplate());
					//设置发送内勤人员信息
					sysSmsInfo.setSendObj((Long)managerMap.get("managerId"));
					sysSmsInfo.setSendObjName(managerMap.get("chnName").toString());
					sysSmsInfo.setSendMobile(managerMap.get("mobile").toString());
					sysSmsInfo.setSendObjType("03");
					sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
					//01-未发送，02-已发送
					sysSmsInfo.setSendStatus("01");
					sysSmsInfo.setSendContent(sendContent);
					BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
					sysSmsInfoMapper.insert(sysSmsInfo);
				}
				logger.info("=======产品("+tradeInfoMap.get("productName")+")的"+"内勤人员到期清算短信生成完成======");
		logger.info("====="+currentDate+"==生成到期清算到账短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 生成股权产品分配短信
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createStockDisSms(Map paramMap , LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成股权产品分配短信===========");
		ResultInfo resultInfo = new ResultInfo();
			//1.股权产品分配短信,获取短信模板
			String smsType = "19";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成股权产品分配短信失败，失败原因：未获取到短信模板！");
				logger.info("生成股权产品分配短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			//2.获取交易信息
			List<Map<String,String>> tradeInfoList = smsServiceMapper.getStockTradeByDistributeDate(paramMap);
			if(tradeInfoList.size()==0){
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			//3.将交易产品信息存到batch表中
			 logger.info("=======产品"+tradeInfoList.get(0).get("productName")+"生成股权产品分配短信===========");
				SysSmsBatch sysSmsBatch = new SysSmsBatch();
				sysSmsBatch.setSmsType("17");
				sysSmsBatch.setSmsTypeRelationNo(new Long(paramMap.get("productId").toString()));
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getCurrentTimestamp());
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
				//将获取的交易分别生成短信
				for (Map tradeInfoMap : tradeInfoList) {
					//4.循环模板发短短信
						for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
							String sendObjType = sysSmsTemplate.getSnedObjType();
							String template = sysSmsTemplate.getSmsTemplate();
							String sendContent = setValueToTemplate(tradeInfoMap, template);
							SysSmsInfo sysSmsInfo = new SysSmsInfo();
							Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
							sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
							sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
							sysSmsInfo.setBusinessNo((Long)tradeInfoMap.get("tradeInfoId"));
							//01-与交易流水号关联，02-与客户流水号关联
							sysSmsInfo.setBusinessType("01");
							//短信发送对象类型，01-客户，02-理财经理
							if ("01".equals(sendObjType)) {
								sysSmsInfo.setSendObj((Long)tradeInfoMap.get("custBaseInfoId"));
								sysSmsInfo.setSendObjName(tradeInfoMap.get("custName").toString());
								sysSmsInfo.setSendMobile(tradeInfoMap.get("custMobile").toString());
							}else if("02".equals(sendObjType)){
								sysSmsInfo.setSendObj((Long)tradeInfoMap.get("agentId"));
								sysSmsInfo.setSendObjName(tradeInfoMap.get("agentName").toString());
								sysSmsInfo.setSendMobile(tradeInfoMap.get("agentMobile").toString());
							}
							sysSmsInfo.setSendObjType(sendObjType);
							sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
							//01-未发送，02-已发送
							sysSmsInfo.setSendStatus("01");
							sysSmsInfo.setSendContent(sendContent);
							BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
							sysSmsInfoMapper.insert(sysSmsInfo);
						}
					logger.info("=======客户("+tradeInfoMap.get("custName")+")的"+tradeInfoMap.get("productName")+"股权产品分配短信生成完成======");
				}
				//获取固定人员短信模板
				SysSmsTemplate managerTemplate = getSmsTemplates("20").get(0);
				//获取内勤人员数组
				List<Map> managerInfoList = managerServiceMapper.getAllManagerInfo(paramMap);
				Map tradeInfoMap = tradeInfoList.get(0);
				for (Map managerMap : managerInfoList) {
					SysSmsInfo sysSmsInfo = new SysSmsInfo();
					Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
					sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
					sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
					//01-与交易流水号关联，02-与客户流水号关联
					sysSmsInfo.setBusinessType("01");
					String sendContent = setValueToTemplate(tradeInfoMap, managerTemplate.getSmsTemplate());
					//设置发送内勤人员信息
					sysSmsInfo.setSendObj((Long)managerMap.get("managerId"));
					sysSmsInfo.setSendObjName(managerMap.get("chnName").toString());
					sysSmsInfo.setSendMobile(managerMap.get("mobile").toString());
					sysSmsInfo.setSendObjType("03");
					sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
					//01-未发送，02-已发送
					sysSmsInfo.setSendStatus("01");
					sysSmsInfo.setSendContent(sendContent);
					BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
					sysSmsInfoMapper.insert(sysSmsInfo);
				}
				logger.info("=======产品("+tradeInfoMap.get("productName")+")的"+"内勤人员股权产品分配短信生成完成======");
		logger.info("====="+currentDate+"==生成股权产品分配短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 生成股权产品分配到账短信
	 * @author ZYM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo createStockToAccSms(Map paramMap , LoginInfo loginInfo) {
		String currentDate = DateUtils.getCurrentDate();
		logger.info("====="+currentDate+"==开始生成股权产品分配到账短信===========");
		ResultInfo resultInfo = new ResultInfo();
			//1.股权产品分配短信,获取短信模板
			String smsType = "21";
			List<SysSmsTemplate> sysSmsTemplateList = getSmsTemplates(smsType);
			if (sysSmsTemplateList==null||sysSmsTemplateList.size()==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("生成股权产品分配到账短信失败，失败原因：未获取到短信模板！");
				logger.info("生成股权产品分配到账短信失败，失败原因：未获取到短信模板！");
				return resultInfo;
			}
			//2.获取交易信息
			List<Map<String,String>> tradeInfoList = smsServiceMapper.getStockTradeByDistributeDate(paramMap);
			if(tradeInfoList.size()==0){
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			//3.将交易产品信息存到batch表中
			 logger.info("=======产品"+tradeInfoList.get(0).get("productName")+"生成股权产品分配到账短信===========");
				SysSmsBatch sysSmsBatch = new SysSmsBatch();
				sysSmsBatch.setSmsType("18");
				sysSmsBatch.setSmsTypeRelationNo(new Long(paramMap.get("productId").toString()));
				sysSmsBatch.setSmsTypeRelationDate(DateUtils.getCurrentTimestamp());
				sysSmsBatch.setSmsCreateTime(DateUtils.getCurrentTimestamp());
				//01-未处理，02-已处理
				sysSmsBatch.setSmsStatus("01");
				BeanUtils.insertObjectSetOperateInfo(sysSmsBatch, loginInfo);
				sysSmsBatchMapper.insert(sysSmsBatch);
				//将获取的交易分别生成短信
				for (Map tradeInfoMap : tradeInfoList) {
					//4.循环模板发短短信
						for (SysSmsTemplate sysSmsTemplate:sysSmsTemplateList) {
							String sendObjType = sysSmsTemplate.getSnedObjType();
							String template = sysSmsTemplate.getSmsTemplate();
							String sendContent = setValueToTemplate(tradeInfoMap, template);
							SysSmsInfo sysSmsInfo = new SysSmsInfo();
							Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
							sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
							sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
							sysSmsInfo.setBusinessNo((Long)tradeInfoMap.get("tradeInfoId"));
							//01-与交易流水号关联，02-与客户流水号关联
							sysSmsInfo.setBusinessType("01");
							//短信发送对象类型，01-客户，02-理财经理
							if ("01".equals(sendObjType)) {
								sysSmsInfo.setSendObj((Long)tradeInfoMap.get("custBaseInfoId"));
								sysSmsInfo.setSendObjName(tradeInfoMap.get("custName").toString());
								sysSmsInfo.setSendMobile(tradeInfoMap.get("custMobile").toString());
							}else if("02".equals(sendObjType)){
								sysSmsInfo.setSendObj((Long)tradeInfoMap.get("agentId"));
								sysSmsInfo.setSendObjName(tradeInfoMap.get("agentName").toString());
								sysSmsInfo.setSendMobile(tradeInfoMap.get("agentMobile").toString());
							}
							sysSmsInfo.setSendObjType(sendObjType);
							sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
							//01-未发送，02-已发送
							sysSmsInfo.setSendStatus("01");
							sysSmsInfo.setSendContent(sendContent);
							BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
							sysSmsInfoMapper.insert(sysSmsInfo);
						}
					logger.info("=======客户("+tradeInfoMap.get("custName")+")的"+tradeInfoMap.get("productName")+"股权产品分配短信生成完成======");
				}
				//获取固定人员短信模板
				SysSmsTemplate managerTemplate = getSmsTemplates("22").get(0);
				//获取内勤人员数组
				List<Map> managerInfoList = managerServiceMapper.getAllManagerInfo(paramMap);
				Map tradeInfoMap = tradeInfoList.get(0);
				for (Map managerMap : managerInfoList) {
					SysSmsInfo sysSmsInfo = new SysSmsInfo();
					Long sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
					sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
					sysSmsInfo.setSysSmsBatchId(sysSmsBatch.getSysSmsBatchId());
					//01-与交易流水号关联，02-与客户流水号关联
					sysSmsInfo.setBusinessType("01");
					String sendContent = setValueToTemplate(tradeInfoMap, managerTemplate.getSmsTemplate());
					//设置发送内勤人员信息
					sysSmsInfo.setSendObj((Long)managerMap.get("managerId"));
					sysSmsInfo.setSendObjName(managerMap.get("chnName").toString());
					sysSmsInfo.setSendMobile(managerMap.get("mobile").toString());
					sysSmsInfo.setSendObjType("03");
					sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
					//01-未发送，02-已发送
					sysSmsInfo.setSendStatus("01");
					sysSmsInfo.setSendContent(sendContent);
					BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
					sysSmsInfoMapper.insert(sysSmsInfo);
				}
				logger.info("=======产品("+tradeInfoMap.get("productName")+")的"+"内勤人员股权产品分配到账短信生成完成======");
		logger.info("====="+currentDate+"==生成股权产品分配到账短信结束===========");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
}
