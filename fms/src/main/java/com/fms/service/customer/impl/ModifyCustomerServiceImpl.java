package com.fms.service.customer.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.ast.Var;
import org.opensaml.xml.encryption.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.CustAccInfoMapper;
import com.fms.db.mapper.CustAddressInfoMapper;
import com.fms.db.mapper.CustBaseInfoMapper;
import com.fms.db.mapper.CustCarInfoMapper;
import com.fms.db.mapper.CustContactInfoMapper;
import com.fms.db.mapper.CustFamilyInfoMapper;
import com.fms.db.mapper.CustHistoryInvestmentMapper;
import com.fms.db.mapper.CustHobbyInfoMapper;
import com.fms.db.mapper.CustHouseInfoMapper;
import com.fms.db.mapper.CustInvestmentSuggestMapper;
import com.fms.db.mapper.CustOthInfoMapper;
import com.fms.db.mapper.CustQuestionnaireInfoMapper;
import com.fms.db.mapper.CustVisitInfoMapper;
import com.fms.db.mapper.DefFileInfoMapper;
import com.fms.db.mapper.DefQuestionAnswerMapper;
import com.fms.db.mapper.DefQuestionMapper;
import com.fms.db.mapper.DefQuestionnaireMapper;
import com.fms.db.mapper.TradeInfoMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.CustAccInfo;
import com.fms.db.model.CustAccInfoExample;
import com.fms.db.model.CustAddressInfo;
import com.fms.db.model.CustAddressInfoExample;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.CustBaseInfoExample;
import com.fms.db.model.CustCarInfo;
import com.fms.db.model.CustCarInfoExample;
import com.fms.db.model.CustContactInfo;
import com.fms.db.model.CustContactInfoExample;
import com.fms.db.model.CustFamilyInfo;
import com.fms.db.model.CustFamilyInfoExample;
import com.fms.db.model.CustHistoryInvestment;
import com.fms.db.model.CustHistoryInvestmentExample;
import com.fms.db.model.CustHobbyInfo;
import com.fms.db.model.CustHobbyInfoExample;
import com.fms.db.model.CustHouseInfo;
import com.fms.db.model.CustHouseInfoExample;
import com.fms.db.model.CustInvestmentSuggest;
import com.fms.db.model.CustInvestmentSuggestExample;
import com.fms.db.model.CustOthInfo;
import com.fms.db.model.CustOthInfoExample;
import com.fms.db.model.CustQuestionnaireInfo;
import com.fms.db.model.CustQuestionnaireInfoExample;
import com.fms.db.model.CustVisitInfo;
import com.fms.db.model.CustVisitInfoExample;
import com.fms.db.model.DefFileInfo;
import com.fms.db.model.DefFileInfoExample;
import com.fms.db.model.DefQuestion;
import com.fms.db.model.DefQuestionAnswer;
import com.fms.db.model.DefQuestionAnswerExample;
import com.fms.db.model.DefQuestionExample;
import com.fms.db.model.DefQuestionnaire;
import com.fms.db.model.DefQuestionnaireExample;
import com.fms.db.model.TradeInfo;
import com.fms.service.customer.ModifyCustomerService;
import com.fms.service.mapper.ModifyCustomerServiceMapper;
import com.fms.service.mapper.SmsServiceMapper;
import com.fms.service.mapper.TradeServiceMapper;
import com.fms.service.sms.SmsService;
import com.fms.service.trade.impl.TradeStatusServiceImpl;
import com.itextpdf.text.log.Logger;
import com.sinosoft.core.db.mapper.DefBankMapper;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.model.DefBank;
import com.sinosoft.core.db.model.DefBankExample;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ModifyCustomerServiceImpl implements ModifyCustomerService {
	/********** 变量声明区 ***************************************************/
	@Autowired
	private CustBaseInfoMapper custBaseInfoMapper;
	@Autowired
	private CustContactInfoMapper custContactInfoMapper;
	@Autowired
	private ModifyCustomerServiceMapper modifyCustomerServiceMapper;
	@Autowired
	private CustOthInfoMapper custOthInfoMapper;
	@Autowired
	private CustInvestmentSuggestMapper custInvestmentSuggestMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private CustAddressInfoMapper custAddressInfoMapper;
	@Autowired
	private CustAccInfoMapper custAccInfoMapper;
	@Autowired
	private CustVisitInfoMapper custVisitInfoMapper;
	@Autowired
	private CustHistoryInvestmentMapper custHistoryInvestmentMapper;
	@Autowired
	private CustFamilyInfoMapper custFamilyInfoMapper;
	@Autowired
	private CustHouseInfoMapper custHouseInfoMapper;
	@Autowired
	private CustCarInfoMapper custCarInfoMapper;
	@Autowired
	private CustHobbyInfoMapper custHobbyInfoMapper;
	@Autowired
	private AgentMapper agentMapper; // 理财师信息表Mapper接口
	@Autowired
	private TradeServiceMapper tradeServiceMapper;
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private DefBankMapper defBankMapper;
	@Autowired
	private DefQuestionMapper defQuestionMapper;
	@Autowired
	private DefQuestionAnswerMapper defQuestionAnswerMapper;
	@Autowired
	private DefQuestionnaireMapper defQuestionnaireMapper;
	@Autowired
	private CustQuestionnaireInfoMapper custQuestionnaireMapper;
	@Autowired
	private SmsService smsService;
	@Autowired
	private DefFileInfoMapper defFileInfoMapper;
	
	
	
	/****************************** 方法声明区 *************************************/
	/**
	 * 验证用户提交的客户/准客户手机号信息
	 * 
	 * @author Liwentao
	 * @param custBaseInfStr
	 * @param agentId
	 * @param loginInfo
	 * @return resultInfo
	 */
	/*@Override
	public ResultInfo verifyCustFiveElements(String custBaseInfoStr, String agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
				*//** 获取录入用户信息的财富顾问信息，并进行判断 **//*
				Agent agent = new Agent();
				// 若传入的agentId不为空，则根据agentId获取财富顾问信息
				if (agentId != null && !"".equals(agentId)) {
					agent = this.agentMapper.selectByPrimaryKey(new Long(agentId));
				} else
				// 若传入的agentId为空，则根据loginInfo获取财富顾问信息
				{
					resultInfo = getAgentByUserId(loginInfo.getUserId());
					if (!resultInfo.isSuccess()) {
						return resultInfo;
					}
					agent = (Agent) resultInfo.getObj();
					// resultInfo对象状态等内容重置
					resultInfo.setSuccess(false);
					resultInfo.setObj(null);
				}
				*//** 客户基本信息字符串转为CustBaseInfo对象 **//*
				CustBaseInfo custBaseInfoObj = new CustBaseInfo();
				custBaseInfoObj = (CustBaseInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfoObj);
				*//** 验证客户提交的五要素 **//*
				String name = custBaseInfoObj.getChnName();
				String gender = custBaseInfoObj.getSex();
				String idType = custBaseInfoObj.getIdType();
				String idNo = custBaseInfoObj.getIdNo();
				java.util.Date birthdate = custBaseInfoObj.getBirthday();
				// 若五要素均不为空，则需跟据此五要素查询数据库
				if (name != null && !"".equals(name) && gender != null && !"".equals(gender) && idType != null
						&& !"".equals(idType) && idNo != null && !"".equals(idNo) && birthdate != null
						&& !"".equals(birthdate)) {
					// 设置查询条件为客户输入的五要素
					CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();
					CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
					custBaseInfoCriteria.andChnNameEqualTo(name).andSexEqualTo(gender).andIdTypeEqualTo(idType)
							.andIdNoEqualTo(idNo).andBirthdayEqualTo(custBaseInfoObj.getBirthday()).andAgentIdNotEqualTo(agent.getAgentId()).andCustLevelEqualTo("01").andRcStateEqualTo( Constants.EFFECTIVE_RECORD );
					if ( custBaseInfoObj.getCustBaseInfoId() != null &&  !"".equals( custBaseInfoObj.getCustBaseInfoId()) ) {
						custBaseInfoCriteria.andCustBaseInfoIdNotEqualTo( custBaseInfoObj.getCustBaseInfoId() );
					}
					// 查询
					List<CustBaseInfo> custBaseInfos = this.custBaseInfoMapper.selectByExample(custBaseInfoExample);
					// 判断
					if (custBaseInfos != null && custBaseInfos.size() > 0) {
						resultInfo.setSuccess(true);
						resultInfo.setMsg("您提交的信息与其他财富顾问的客户发生冲突");
						return resultInfo;
					}
					// 设置查询条件为客户输入的五要素
					CustBaseInfoExample custBaseInfoExample2 = new CustBaseInfoExample();
					CustBaseInfoExample.Criteria custBaseInfoCriteria2 = custBaseInfoExample2.createCriteria();
					custBaseInfoCriteria2.andChnNameEqualTo(name).andSexEqualTo(gender).andIdTypeEqualTo(idType)
							.andIdNoEqualTo(idNo).andBirthdayEqualTo( custBaseInfoObj.getBirthday() ).andAgentIdEqualTo( agent.getAgentId() ).andCustLevelEqualTo("01").andRcStateEqualTo( Constants.EFFECTIVE_RECORD );
					if ( custBaseInfoObj.getCustBaseInfoId() != null &&  !"".equals( custBaseInfoObj.getCustBaseInfoId()) ) {
						custBaseInfoCriteria2.andCustBaseInfoIdNotEqualTo( custBaseInfoObj.getCustBaseInfoId() );
					}
					// 查询
					List<CustBaseInfo> custBaseInfos2 = this.custBaseInfoMapper.selectByExample(custBaseInfoExample2);
					// 判断
					if (custBaseInfos2 != null && custBaseInfos2.size() > 0) {
						resultInfo.setSuccess(true);
						resultInfo.setMsg("您提交的信息与自己的签单客户发生冲突");
						return resultInfo;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("验证客户五基本要素时出错");
			return resultInfo;
		}
		// 返回处理结果
		return resultInfo;
	}*/

	/**
	 * 验证用户提交的客户/准客户手机号信息
	 * 
	 * @author Liwentao
	 * @param custBaseInfStr
	 * @param agentId
	 * @param loginInfo
	 * @return resultInfo
	 */
	@SuppressWarnings("unused")
	@Override
	public ResultInfo verifyCustMobileInfo(String custBaseInfoStr, String agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			/** 获取录入用户信息的财富顾问信息，并进行判断 **/
			Agent agent = new Agent();
			// 若传入的agentId不为空，则根据agentId获取财富顾问信息
			if (agentId != null && !"".equals(agentId)) {
				agent = this.agentMapper.selectByPrimaryKey(new Long(agentId));
			} else
			// 若传入的agentId为空，则根据loginInfo获取财富顾问信息
			{
				resultInfo = getAgentByUserId(loginInfo.getUserId());
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
				agent = (Agent) resultInfo.getObj();
				// resultInfo对象状态等内容重置
				resultInfo.setSuccess(false);
				resultInfo.setObj(null);
			}
			/** Json字符串 **/
			// Json字符串转为CustBaseInfo对象
			CustBaseInfo custBaseInfoObj = new CustBaseInfo();
			custBaseInfoObj = (CustBaseInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfoObj);
			// Json字符串转为CustOthInfo对象
			CustOthInfo custOthInfoObj = new CustOthInfo();
			custOthInfoObj = (CustOthInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custOthInfoObj);
			// Json字符串转为CustContactInfo对象
			CustContactInfo custContactInfoObj = new CustContactInfo();
			custContactInfoObj = (CustContactInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custContactInfoObj);
			/** 如果用户提交的客户或准客户手机号不为空，则需对手机号进行判断是否与自己名下其他客户手机号产生冲突 **/
			Long agentIdInUser = agent.getAgentId();
			if (custContactInfoObj.getMobile() != null && !"".equals(custContactInfoObj.getMobile())) {
				CustContactInfo custContactInfo;
				CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
				CustContactInfoExample.Criteria custConCriteria = custContactInfoExample.createCriteria();
				custConCriteria.andMobileEqualTo(custContactInfoObj.getMobile())
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andAgentIdEqualTo(agent.getAgentId());
				if (custBaseInfoObj.getCustBaseInfoId() != null && !"".equals(custBaseInfoObj.getCustBaseInfoId())) {
					custConCriteria.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
				}
				List<CustContactInfo> custContactInfosList = custContactInfoMapper
						.selectByExample(custContactInfoExample);
				if ((custContactInfosList != null) && (0 < custContactInfosList.size())) {
						resultInfo.setSuccess(true);
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回处理结果
		return resultInfo;
	}

	/**
	 * 获取财富顾问信息
	 * 
	 * @param userId
	 * @return
	 */
	private ResultInfo getAgentByUserId(Long userId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			AgentExample agentExample = new AgentExample();
			AgentExample.Criteria agentCriteria = agentExample.createCriteria();
			agentCriteria.andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			Agent agent = new Agent();
			if (agentList != null && agentList.size() > 0) {
				agent = agentList.get(0);
				resultInfo.setObj(agent);
				resultInfo.setSuccess(true);
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("您不是财富顾问!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("系统出错!");
		}
		return resultInfo;
	}

	/**
	 * 验证用户提交的客户/准客户身份证号信息
	 * 
	 * @author Liwentao
	 * @param custBaseInfStr
	 * @param agentId
	 * @param loginInfo
	 * @return resultInfo
	 */
	@Override
	public ResultInfo verifyCustIdNoInfo(String custBaseInfoStr, String agentId, LoginInfo loginInfo) {
		// 状态标志
		ResultInfo resultInfo = new ResultInfo();
		// 程序代码放入try----catch
		try {
			/** 获取录入用户信息的财富顾问信息，并进行判断 **/
			Agent agent = new Agent();
			// 若传入的agentId不为空，则根据agentId获取财富顾问信息
			if (agentId != null && !"".equals(agentId)) {
				agent = this.agentMapper.selectByPrimaryKey(new Long(agentId));
			} else
			// 若传入的agentId为空，则根据loginInfo获取财富顾问信息
			{
				resultInfo = getAgentByUserId(loginInfo.getUserId());
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
				agent = (Agent) resultInfo.getObj();
				// resultInfo对象状态等内容重置
				resultInfo.setSuccess(false);
				resultInfo.setObj(null);
			}
			/** Json字符串 **/
			// Json字符串转为CustBaseInfo对象
			CustBaseInfo custBaseInfoObj = new CustBaseInfo();
			custBaseInfoObj = (CustBaseInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfoObj);
			// Json字符串转为CustOthInfo对象
			CustOthInfo custOthInfoObj = new CustOthInfo();
			custOthInfoObj = (CustOthInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custOthInfoObj);
			// Json字符串转为CustContactInfo对象
			CustContactInfo custContactInfoObj = new CustContactInfo();
			custContactInfoObj = (CustContactInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custContactInfoObj);
			/** 如果用户提交的客户或准客户手机号不为空，则需对手机号进行判断是否与自己名下其他客户手机号产生冲突 **/
			if (custBaseInfoObj.getIdType() != null && !"".equals(custBaseInfoObj.getIdType())
					&& custBaseInfoObj.getIdNo() != null && !"".equals(custBaseInfoObj.getIdNo())) {
				if (!"1".equals(custBaseInfoObj.getIdType())) {
					return resultInfo;
				}
				CustBaseInfoExample custBaseInfoExampleIdNo = new CustBaseInfoExample(); // 设置查询条件为姓名
				CustBaseInfoExample.Criteria criteriaIdNo = custBaseInfoExampleIdNo.createCriteria();
				criteriaIdNo.andIdNoEqualTo(custBaseInfoObj.getIdNo()).andIdTypeEqualTo(custBaseInfoObj.getIdType())
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andAgentIdEqualTo(agent.getAgentId());
				// 如果客户基本信息流水号不为空，则查询除去该条记录的其他数据
				if ( custBaseInfoObj.getCustBaseInfoId() != null && !"".equals(custBaseInfoObj.getCustBaseInfoId())) {
					criteriaIdNo.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
				}
				List<CustBaseInfo> custBaseInfoIdList = custBaseInfoMapper.selectByExample(custBaseInfoExampleIdNo);
				if ((custBaseInfoIdList != null) && (0 < custBaseInfoIdList.size())) {
						resultInfo.setSuccess(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回状态标志
		return resultInfo;
	}

	/**
	 * 根据手机号与姓名查询客户是否与其他理财经理已录信息冲突
	 * 
	 * @author Liwentao
	 * @param custBaseInfoMap
	 * @param agentId
	 * @param modelMap
	 * @return resultInfo
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultInfo verifyCustMobileAndName(String custBaseInfoStr, String agentId,
			LoginInfo loginInfoObjRef) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			/** 获取录入用户信息的财富顾问信息，并进行判断 **/
			Agent agent = new Agent();
			// 若传入的agentId不为空，则根据agentId获取财富顾问信息
			if (agentId != null && !"".equals(agentId)) {
				agent = this.agentMapper.selectByPrimaryKey(new Long(agentId));
			} else
			// 若传入的agentId为空，则根据loginInfo获取财富顾问信息
			{
				resultInfo = getAgentByUserId(loginInfoObjRef.getUserId());
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
				agent = (Agent) resultInfo.getObj();
				// resultInfo对象状态等内容重置
				resultInfo.setSuccess(false);
				resultInfo.setObj(null);
			}
			/** Json字符串 **/
			// Json字符串转为CustBaseInfo对象
			CustBaseInfo custBaseInfoObj = new CustBaseInfo();
			custBaseInfoObj = (CustBaseInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfoObj);
			// Json字符串转为CustOthInfo对象
			CustOthInfo custOthInfoObj = new CustOthInfo();
			custOthInfoObj = (CustOthInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custOthInfoObj);
			// Json字符串转为CustContactInfo对象
			CustContactInfo custContactInfoObj = new CustContactInfo();
			custContactInfoObj = (CustContactInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custContactInfoObj);
			if ( custContactInfoObj.getMobile() != null && !"".equals(custContactInfoObj.getMobile())) {
				Map nameAndMobileMap = new HashMap();
				nameAndMobileMap.put( "chnName", custBaseInfoObj.getChnName() );
				nameAndMobileMap.put( "mobile", custContactInfoObj.getMobile() );
				nameAndMobileMap.put( "agentId", agent.getAgentId() );
				if ( custBaseInfoObj.getCustBaseInfoId() != null  && !"".equals(custBaseInfoObj.getCustBaseInfoId()) ) {
					nameAndMobileMap.put( "custBaseInfoId",  custBaseInfoObj.getCustBaseInfoId() );
				}
				List<Map<String, Object>> resultList = this.modifyCustomerServiceMapper.searchCustomer(nameAndMobileMap);
				if (resultList == null || 0 == resultList.size()) {
					return resultInfo;
				} else {
						resultInfo.setSuccess(true);
						resultInfo.setMsg("您当前录入的客户已是其他理财经理的客户或准客户！系统建议您该客户由原财富顾问继续维护！单击【确定】继续提交客户信息，单击【取消】放弃提交");
						return resultInfo;
				}
			}
		} 
		catch (Exception e) 
		{
			resultInfo.setMsg("验证客户姓名与手机号出错");
			return resultInfo;
		}
		return resultInfo;
	}

	/**
	 * 根据姓名查询客户是否与其他理财经理已录信息冲突
	 * 
	 * @author Liwentao
	 * @param custBaseInfoMap
	 * @param agentId
	 * @param modelMap
	 * @return resultInfo
	 */
	public ResultInfo verifyCustName(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef) {
		ResultInfo resultInfo = new ResultInfo();
		try {
					/** 获取录入用户信息的财富顾问信息，并进行判断 **/
					Agent agent = new Agent();
					// 若传入的agentId不为空，则根据agentId获取财富顾问信息
					if (agentId != null && !"".equals(agentId)) {
						agent = this.agentMapper.selectByPrimaryKey(new Long(agentId));
					} else
					// 若传入的agentId为空，则根据loginInfo获取财富顾问信息
					{
						resultInfo = getAgentByUserId(loginInfoObjRef.getUserId());
						if (!resultInfo.isSuccess()) {
							return resultInfo;
						}
						agent = (Agent) resultInfo.getObj();
						// resultInfo对象状态等内容重置
						resultInfo.setSuccess(false);
						resultInfo.setObj(null);
					}
					/** Json字符串 **/
					// Json字符串转为CustBaseInfo对象
					CustBaseInfo custBaseInfoObj = new CustBaseInfo();
					custBaseInfoObj = (CustBaseInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfoObj);
					// Json字符串转为CustOthInfo对象
					CustOthInfo custOthInfoObj = new CustOthInfo();
					custOthInfoObj = (CustOthInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custOthInfoObj);
					// Json字符串转为CustContactInfo对象
					CustContactInfo custContactInfoObj = new CustContactInfo();
					custContactInfoObj = (CustContactInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custContactInfoObj);
					// 根据姓名查询数据库，判断浏览器用户自己的客户中是否存在重名客户
					CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample(); // 设置查询条件为姓名
					CustBaseInfoExample.Criteria criteria = custBaseInfoExample.createCriteria();
					criteria.andChnNameEqualTo( custBaseInfoObj.getChnName() ).andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					criteria.andAgentIdEqualTo(agent.getAgentId());
					// 如果客户基本信息流水号不为空，则查询除去该条记录的其他数据
					if ( custBaseInfoObj.getCustBaseInfoId() != null &&  !"".equals(custBaseInfoObj.getCustBaseInfoId()) ) {
						criteria.andCustBaseInfoIdNotEqualTo( custBaseInfoObj.getCustBaseInfoId() );
					}
					List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample( custBaseInfoExample );
					// 判断返回条数
					if ((custBaseInfoList == null) || (0 == custBaseInfoList.size())) {
						return resultInfo;
					} else {
							resultInfo.setSuccess(true);
					}
		} catch (Exception e) {
			resultInfo.setMsg("验证姓名时出错");
		}
		return resultInfo;
	}

	/**
	 * 客户基本信息赋值
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo getModifyCustomerBaseInfo(Map map,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		Map resultMap = new HashMap();
		Map custContactInfoMap = new HashMap();
		Map custBaseInfoMap = new HashMap();
		Map custOthInfoMap = new HashMap();
		try {
			if (map != null) {
				paramMap.put("custBaseInfoId", map.get("custBaseInfoId"));
				Long custBaseInfoId = new Long(map.get("custBaseInfoId").toString());
				// 获取客户主表基本信息
				CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
				// 获取客户联系信息
				CustContactInfoExample contactInfoExample = new CustContactInfoExample();
				CustContactInfoExample.Criteria contactInfoCrteria = contactInfoExample.createCriteria();
				contactInfoCrteria.andCustBaseInfoIdEqualTo(custBaseInfoId)
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustContactInfo> contactInfoList = custContactInfoMapper.selectByExample(contactInfoExample);
				if (contactInfoList != null && contactInfoList.size() > 0) {
					CustContactInfo custContactInfo = contactInfoList.get(0);
					custContactInfoMap = JsonUtils.objectToMap(custContactInfo);
				}
				// 获取客户其他信息
				CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
				CustOthInfoExample.Criteria custOthInfoCriteria = custOthInfoExample.createCriteria();
				custOthInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId)
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustOthInfo> custOthInfoList = custOthInfoMapper.selectByExample(custOthInfoExample);
				if (custOthInfoList != null && custOthInfoList.size() > 0) {
					CustOthInfo custOthInfo = custOthInfoList.get(0);
					custOthInfoMap = JsonUtils.objectToMap(custOthInfo);
				}
				// 客户基本信息,联系信息,其他信息放入Map
				Map customerInfoMap = new HashMap();
				custBaseInfoMap = JsonUtils.objectToMap(custBaseInfo);
				customerInfoMap.putAll(custContactInfoMap);
				customerInfoMap.putAll(custOthInfoMap);
				customerInfoMap.putAll(custBaseInfoMap);
				// 获取客户联系地址信息
				List<Map> custAddressInfoMapList = modifyCustomerServiceMapper.custAddressInfoMapList(paramMap);
				// 获取客户账户信息
				List<Map> custAccInfoMapList = modifyCustomerServiceMapper.custAccInfoMapList(paramMap);
				// 获取客户归属信息
				List<Map> custAscriptionMapList = modifyCustomerServiceMapper.custAscriptionMapList(paramMap);
				resultMap.put("custBaseInfoAndContactInfo", customerInfoMap);
				resultMap.put("custAddressInfoMapList", custAddressInfoMapList);
				resultMap.put("custAccInfoMapList", custAccInfoMapList);
				resultMap.put("custAscriptionMapList", custAscriptionMapList);
				resultMap.put("loginUserId", loginInfo.getUserId());
				resultInfo.setObj(resultMap);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("客户基本信息赋值成功!");
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setObj("客户基本信息赋值失败!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("客户基本信息赋值失败!");
		}
		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getModifyCustomerPersonalInfo(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map resultInfoMap = new HashMap();
		Map paramMap = new HashMap();
		try {
			if (param != null) {
				paramMap.put("custBaseInfoId", param);
				// 获取客户其他信息
				CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
				CustOthInfoExample.Criteria custOthInfoCriteria = custOthInfoExample.createCriteria();
				custOthInfoCriteria.andCustBaseInfoIdEqualTo(new Long(param))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustOthInfo> custOthInfoList = custOthInfoMapper.selectByExample(custOthInfoExample);
				if (custOthInfoList != null && custOthInfoList.size() > 0) {
					CustOthInfo custOthInfo = custOthInfoList.get(0);
					resultInfoMap.put("custOthInfoMap", JsonUtils.objectToMap(custOthInfo));
				}
				List<Map> custFamilyInfoMapList = modifyCustomerServiceMapper.getCustFamilyInfoMapList(paramMap);
				List<Map> custHouseInfoMapList = modifyCustomerServiceMapper.getCustHouseInfoMapList(paramMap);
				List<Map> custCarInfoMapList = modifyCustomerServiceMapper.getCustCarInfoMapList(paramMap);
				resultInfoMap.put("custFamilyInfoMapList", custFamilyInfoMapList);
				resultInfoMap.put("custHouseInfoMapList", custHouseInfoMapList);
				resultInfoMap.put("custCarInfoMapList", custCarInfoMapList);
				// 获取客户投资意向和建议
				List<Map> custInvestmentSuggestList = modifyCustomerServiceMapper
						.getCustInvestmentSuggestList(paramMap);
				resultInfoMap.put("custInvestmentSuggestList", custInvestmentSuggestList);
				// 获取客户兴趣爱好信息
				List<CustHobbyInfo> custHobbyInfoList = getCustHobbyInfoList(paramMap);
				resultInfoMap.put("custHobbyInfoList", custHobbyInfoList);
				resultInfo.setObj(resultInfoMap);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取客户个人信息成功！");
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户个人信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户个人信息失败");

		}
		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getModifyCustomerVisitInfo(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map resultInfoMap = new HashMap();
		Map paramMap = new HashMap();
		try {
			if (param != null) {
				paramMap.put("custBaseInfoId", param);
				List<Map> custVisitInfoMapList = modifyCustomerServiceMapper.getModifyCustomerVisitInfo(paramMap);
				resultInfoMap.put("custVisitInfoMapList", custVisitInfoMapList);
				resultInfo.setSuccess(true);
				resultInfo.setObj(resultInfoMap);
				resultInfo.setMsg("获取客户拜访信息成功！");
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户拜访信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户拜访信息失败！");
		}
		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getModifyCustomerInvestInfo(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map resultMap = new HashMap();
		Map paramMap = new HashMap();
		try {
			if (param != null) {
				paramMap.put("custBaseInfoId", param);
				// 获取客户其他公司的投资信息
				List<Map> customerOtherInvestInfoMapList = modifyCustomerServiceMapper
						.customerOtherInvestInfoMapList(paramMap);
				// 获取客户我司投资记录信息
				List<Map> customerMyInvestInfoMapList = modifyCustomerServiceMapper
						.customerMyInvestInfoMapList(paramMap);
				resultMap.put("customerOtherInvestInfoMapList", customerOtherInvestInfoMapList);
				resultMap.put("customerMyInvestInfoMapList", customerMyInvestInfoMapList);
				resultInfo.setObj(resultMap);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取客户投资信息成功！");
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户投资信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户投资信息失败！");
		}
		return resultInfo;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getModifyCustomerInvestInfo02(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map resultMap = new HashMap();
		Map paramMap = new HashMap();
		try {
			if (param != null) {
				paramMap.put("custBaseInfoId", param);
				// 获取客户我司投资记录信息
				List<Map> customerMyInvestInfoMapList = modifyCustomerServiceMapper
						.customerMyInvestInfoMapList02(paramMap);
				resultMap.put("customerMyInvestInfoMapList02", customerMyInvestInfoMapList);
				resultInfo.setObj(resultMap);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取客户投资信息成功！");
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户投资信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户投资信息失败！");
		}
		return resultInfo;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getModifyCustomerInvestInfo03(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map resultMap = new HashMap();
		Map paramMap = new HashMap();
		try {
			if (param != null) {
				paramMap.put("custBaseInfoId", param);
				// 获取客户我司投资记录信息
				List<Map> customerMyInvestInfoMapList = modifyCustomerServiceMapper
						.customerMyInvestInfoMapList03(paramMap);
				resultMap.put("customerMyInvestInfoMapList03", customerMyInvestInfoMapList);
				resultInfo.setObj(resultMap);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取客户投资信息成功！");
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户投资信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户投资信息失败！");
		}
		return resultInfo;
	}
	
	/*海外投资信息*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getModifyCustomerInvestInfo04(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map resultMap = new HashMap();
		Map paramMap = new HashMap();
		try {
			if (param != null) {
				paramMap.put("custBaseInfoId", param);
				// 获取客户我司投资记录信息
				List<Map> customerMyInvestInfoMapList = modifyCustomerServiceMapper
						.customerMyInvestInfoMapList04(paramMap);
				resultMap.put("customerMyInvestInfoMapList04", customerMyInvestInfoMapList);
				resultInfo.setObj(resultMap);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取客户投资信息成功！");
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取客户投资信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户投资信息失败！");
		}
		return resultInfo;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo checkModifyCustomerBaseInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (paramMap != null) {
				String chnName = paramMap.get("chnName").toString();
				String mobile = paramMap.get("mobile").toString();
				// String custBaseInfoId =
				// paramMap.get("custBaseInfoId").toString();
				String agentId = paramMap.get("agentId").toString();
				// String idNo = paramMap.get("idNo").toString();
				if (chnName != null && mobile != null && agentId != null) {
					paramMap.put("rolePrivilege", loginInfo.getRolePivilege());// 获取用户权限
					paramMap.put("operComId", loginInfo.getComId().toString());// 登陆用户所属管理机构ID
					paramMap.put("createUserId", loginInfo.getUserId().toString());// 登陆用户ID
					// 根据姓名和手机号判别，提示已是他人客户，建议由原财富顾问继续维护，可以选择继续录入或放弃
					Integer total = modifyCustomerServiceMapper.checkCustBaseInfoChnNameAndMobileCount(paramMap);
					if (total > 0) {
						// 该客户已经属于其他理财经理客户
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该客户已经属于其他理财经理，是否继续录入？");
					} else {
						resultInfo.setSuccess(true);
						resultInfo.setMsg("该客户不属于其他理财经理！");
					}
				}
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("验证客户信息获取页面数据异常！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("验证客户信息出现异常!");
		}
		return resultInfo;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo checkMyModifyCustomerBaseInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		if (paramMap != null) {
			String chnName = paramMap.get("chnName").toString();
			String mobile = paramMap.get("mobile").toString();
			String custBaseInfoId = paramMap.get("custBaseInfoId").toString();
			String agentId = paramMap.get("agentId").toString();
			String idNo = paramMap.get("idNo").toString();
			try {
				// 手机或者身份证号重复，提醒自己名下已有重复客户；可以选择编辑已有客户，否则不能继续录入或保存
				if (agentId != null && mobile != null && idNo != null && custBaseInfoId != null && chnName != null) {
					Integer totalIdNoOrMobile = modifyCustomerServiceMapper
							.checkCustBaseInfoIdNoOrMobileCount(paramMap);
					if (totalIdNoOrMobile > 0) {
						// 存在重复客户
						resultInfo.setSuccess(false);
						resultInfo.setObj("01");
						resultInfo.setMsg("该理财经理已经存在该客户，不能继续录入！");
					} else {
						// 不存在重复客户，继续验证是否存在重名客户
						Integer totalChnName = modifyCustomerServiceMapper.checkCustBaseInfoChnName(paramMap);
						if (totalChnName > 0) {
							resultInfo.setSuccess(false);
							resultInfo.setObj("02");
							resultInfo.setMsg("该理财经理名下已经有重名客户，是否继续录入！");
						} else {
							resultInfo.setSuccess(true);
							resultInfo.setMsg("正常录入客户！");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("验证自己名下客户出现异常！");
			}

		}
		return resultInfo;
	}

	/**
	 * 新增更新客户基本信息
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public ResultInfo updateModifyCustomerBaseInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (paramMap != null) {
				// 获取财富顾问信息
				CustBaseInfo custBaseInfo = (CustBaseInfo) paramMap.get("custBaseInfo");
				CustContactInfo custContactInfo = (CustContactInfo) paramMap.get("custContactInfo");
				CustOthInfo custOthInfo = (CustOthInfo) paramMap.get("custOthInfo");
				// Long custBaseInfoId = new
				// Long(paramMap.get("custBaseInfoId").toString());
				Long agentId = custBaseInfo.getAgentId();
				if (agentId == null || agentId.equals("")) {
					resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
					agentId = (Long) resultInfo.getObj();
					custBaseInfo.setAgentId(agentId);
				}
				// 验证手机邮箱唯一性
				if (loginInfo.getUserId() != 1) {
					// 客户手机号信息进行校验
					if (custContactInfo.getMobile() != null ) {
						resultInfo = verifyCustomerMobile(custContactInfo.getMobile(),custBaseInfo.getCustBaseInfoId());
						if (!resultInfo.isSuccess()) {
							return resultInfo;
						}
					}
					// 客户邮箱信息进行校验
					if ( custContactInfo.getEmail() != null ) {
						resultInfo = verifyCustomerEmail(custContactInfo.getEmail(),custBaseInfo.getCustBaseInfoId());
						if (!resultInfo.isSuccess()) {
							return resultInfo;
						}
					}
				}
				
				CustBaseInfo existCustBaseInfo = new CustBaseInfo();
				CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();
				CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
				if ("1".equals(custBaseInfo.getIdType())) {
					if(custBaseInfo.getIdNo()!=null&&!custBaseInfo.getIdNo().equals("")){
						custBaseInfo.setIdNo(custBaseInfo.getIdNo().toUpperCase());
					}
				}
				/*
				 * // 客户信息五要素查询客户
				 * custBaseInfoCriteria.andChnNameEqualTo(custBaseInfo.
				 * getChnName()).andSexEqualTo(custBaseInfo.getSex())
				 * .andBirthdayEqualTo(custBaseInfo.getBirthday()).
				 * andIdNoEqualTo(custBaseInfo.getIdNo())
				 * .andIdTypeEqualTo(custBaseInfo.getIdType()).andRcStateEqualTo
				 * (Constants.EFFECTIVE_RECORD);
				 */
				if (custBaseInfo.getCustBaseInfoId() != null) {
					custBaseInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
							.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<CustBaseInfo> existCustBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample);
					if (existCustBaseInfoList != null && existCustBaseInfoList.size() > 0) {
						// 该客户已经存在，只能更新客户基本信息
						String  custLevel = custBaseInfo.getCustLevel();
						if (custLevel.equals("02")) {
							existCustBaseInfo = existCustBaseInfoList.get(0);
							Long custBaseInfoId = existCustBaseInfo.getCustBaseInfoId();
							// 判断信息是否完全相同，如果完全相同就不作处理，不相同就更新
							if (!existCustBaseInfo.equals(custBaseInfo)) {
								BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustBaseInfo, custBaseInfo, loginInfo);
								custBaseInfo.setCustBaseInfoId(custBaseInfoId);
								custBaseInfo.setCustomerNo(existCustBaseInfo.getCustomerNo());
								if (custBaseInfo.getBirthday() != null && !"".equals(custBaseInfo.getBirthday())) {
									custBaseInfo.setAge(new Long(DateUtils.calInterval(custBaseInfo.getBirthday(),DateUtils.getCurrentTimestamp(), "Y")));
								}
									BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
									custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
									resultInfo.setSuccess(true);
									resultInfo.setMsg("更新客户基本信息成功！");
									resultInfo.setObj(custBaseInfo);
							}
							resultInfo = saveCustContactInfo(custBaseInfo.getCustBaseInfoId(), custContactInfo, agentId, loginInfo);
							if(!resultInfo.isSuccess()){
								return resultInfo;
							}
							resultInfo = saveCustBaseOthInfo(custBaseInfo.getCustBaseInfoId(), custOthInfo, agentId, loginInfo);
							if(!resultInfo.isSuccess()){
								return resultInfo;
							}
							resultInfo.setSuccess(true);
							resultInfo.setMsg("客户基本信息保存成功");
							resultInfo.setObj(custBaseInfo);
							return resultInfo;
						}
						else{
							Long loginUserId = loginInfo.getUserId();
							if(loginUserId == 1){
								existCustBaseInfo = existCustBaseInfoList.get(0);
								Long custBaseInfoId = existCustBaseInfo.getCustBaseInfoId();
								// 判断信息是否完全相同，如果完全相同就不作处理，不相同就更新
								if (!existCustBaseInfo.equals(custBaseInfo)) {
									BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustBaseInfo, custBaseInfo, loginInfo);
									custBaseInfo.setCustBaseInfoId(custBaseInfoId);
									custBaseInfo.setCustomerNo(existCustBaseInfo.getCustomerNo());
									if (custBaseInfo.getBirthday() != null && !"".equals(custBaseInfo.getBirthday())) {
										custBaseInfo.setAge(new Long(DateUtils.calInterval(custBaseInfo.getBirthday(),DateUtils.getCurrentTimestamp(), "Y")));
									}
										BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
										custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
										resultInfo.setSuccess(true);
										resultInfo.setMsg("更新客户基本信息成功！");
										resultInfo.setObj(custBaseInfo);
								}
								resultInfo = saveCustContactInfo(custBaseInfo.getCustBaseInfoId(), custContactInfo, agentId, loginInfo);
								if(!resultInfo.isSuccess()){
									return resultInfo;
								}
								resultInfo = saveCustBaseOthInfo(custBaseInfo.getCustBaseInfoId(), custOthInfo, agentId, loginInfo);
								if(!resultInfo.isSuccess()){
									return resultInfo;
								}
								resultInfo.setSuccess(true);
								resultInfo.setMsg("客户基本信息保存成功");
								resultInfo.setObj(custBaseInfo);
								return resultInfo;
							}
							else
							{
								resultInfo.setSuccess(false);
								resultInfo.setMsg("当前用户不可修改客户基本信息！");
								resultInfo.setObj(null);
							}
						}
						
						
					} else {
						// 客户不存在，创建客户号，保存客户基本信息
						// 1.生成客户基本信息主键
						Long custBaseInfoId = commonService.getSeqValByName("SEQ_T_CUST_BASE_INFO");
						// 2.生成客户号
						String customerNo = commonService.createCustomerNo();
						// 3.保存客户基本信息
						custBaseInfo.setCustBaseInfoId(custBaseInfoId);
						custBaseInfo.setCustomerNo(customerNo);
						if (custBaseInfo.getBirthday() != null && !"".equals(custBaseInfo.getBirthday())) {
							custBaseInfo.setAge(new Long(DateUtils.calInterval(custBaseInfo.getBirthday(),
									DateUtils.getCurrentTimestamp(), "Y")) );
						}
						custBaseInfo.setAgentId(agentId);
						BeanUtils.insertObjectSetOperateInfo(custBaseInfo, loginInfo);
						custBaseInfoMapper.insert(custBaseInfo);
						//
						resultInfo = saveCustContactInfo(custBaseInfo.getCustBaseInfoId(), custContactInfo, agentId, loginInfo);
						if(!resultInfo.isSuccess()){
							return resultInfo;
						}
						resultInfo = saveCustBaseOthInfo(custBaseInfo.getCustBaseInfoId(), custOthInfo, agentId, loginInfo);
						if(!resultInfo.isSuccess()){
							return resultInfo;
						}
						resultInfo.setSuccess(true);
						resultInfo.setMsg("客户基本信息保存成功");
						resultInfo.setObj(custBaseInfo);
						return resultInfo;
					}
				} 
				else 
				{
					// 客户不存在，创建客户号，保存客户基本信息
					// 1.生成客户基本信息主键
					Long custBaseInfoId = commonService.getSeqValByName("SEQ_T_CUST_BASE_INFO");
					// 2.生成客户号
					String customerNo = commonService.createCustomerNo();
					// 3.保存客户基本信息
					
					custBaseInfo.setCustBaseInfoId(custBaseInfoId);
					custBaseInfo.setCustomerNo(customerNo);
					if (custBaseInfo.getBirthday() != null && !"".equals(custBaseInfo.getBirthday())) {
						custBaseInfo.setAge(new Long( DateUtils.calInterval(custBaseInfo.getBirthday(),
								DateUtils.getCurrentTimestamp(), "Y")));
					}
					custBaseInfo.setAgentId(agentId);
					BeanUtils.insertObjectSetOperateInfo(custBaseInfo, loginInfo);
					custBaseInfoMapper.insert(custBaseInfo);
					resultInfo = saveCustContactInfo(custBaseInfo.getCustBaseInfoId(), custContactInfo, agentId, loginInfo);
					if(!resultInfo.isSuccess()){
						return resultInfo;
					}
					resultInfo = saveCustBaseOthInfo(custBaseInfo.getCustBaseInfoId(), custOthInfo, agentId, loginInfo);
					if(!resultInfo.isSuccess()){
						return resultInfo;
					}
					resultInfo.setSuccess(true);
					resultInfo.setMsg("客户基本信息保存成功");
					resultInfo.setObj(custBaseInfo);
					return resultInfo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户基本信息出现异常！");
		}
		return resultInfo;
	}

	/**
	 * 保存客户联系信息
	 * 
	 * @param custBaseInfo
	 * @param custContactInfo
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustContactInfo(Long custBaseInfoId,CustContactInfo custContactInfo,
			Long agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();

		try {
			// 查询财富顾问是否保存过此客户信息
			CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
			CustContactInfoExample.Criteria criteria = custContactInfoExample.createCriteria();
			criteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustContactInfo> contactInfoList = custContactInfoMapper.selectByExample(custContactInfoExample);
			if (contactInfoList != null && contactInfoList.size() > 0) {
				// 该客户已经保存过联系信息，更新即可
				CustContactInfo existCustContactInfo = contactInfoList.get(0);
				if (!custContactInfo.equals(existCustContactInfo)) {
					// 联系信息有改动，更新，没改动就不作操作
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustContactInfo, custContactInfo, loginInfo);
					custContactInfo.setCustContactInfoId(existCustContactInfo.getCustContactInfoId());
					custContactInfo.setCustBaseInfoId(custBaseInfoId);
					custContactInfo.setAgentId(agentId);
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustContactInfo, custContactInfo, loginInfo);
					custContactInfoMapper.updateByPrimaryKey(custContactInfo);
					//获取客户级别
					CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
					String custLevel = custBaseInfo.getCustLevel();
					//判断是否修改签单客户，若为准客户则不会生成短信
					if ("01".equals(custLevel)) {
						//判断是否是电话修改。是则创建电话修改短信
						if (!existCustContactInfo.getMobile().equals(custContactInfo.getMobile())) {
							resultInfo = smsService.createMoidfyMobileMessage(custBaseInfoId, custContactInfo.getMobile(), loginInfo);
						}
						//判断是否是邮箱修改。是则创建电话修改短信
						if (!existCustContactInfo.getEmail().equals(custContactInfo.getEmail())) {
							resultInfo = smsService.createMoidfyEmailMessage(custBaseInfoId, custContactInfo.getEmail(),custContactInfo.getMobile(), loginInfo);
						}
					}
				}
			} else {
				// 生成客户联系信息主键，保存客户联系方式
				Long custContactInfoId = commonService.getSeqValByName("SEQ_T_CUST_CONTACT_INFO");
				custContactInfo.setCustContactInfoId(custContactInfoId);
				custContactInfo.setCustBaseInfoId(custBaseInfoId);
				custContactInfo.setAgentId(agentId);
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
	 * 校验客户手机号是否已经存在
	 * 
	 * @param mobile
	 * @return
	 */
	private ResultInfo verifyCustomerMobile(String mobile,Long existCustBaseInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		// 客户号为空
		if (mobile!= null) {
			CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
			//手机
			custContactInfoExample.createCriteria().andRcStateEqualTo("E").andMobileEqualTo(mobile);
			List<CustContactInfo> listCustContactInfos = custContactInfoMapper.selectByExample(custContactInfoExample);
			if (listCustContactInfos != null && listCustContactInfos.size() > 0) {
				/*for (CustContactInfo custContactInfo : listCustContactInfos) {

					CustBaseInfoExample comBaseInfoExample = new CustBaseInfoExample();
					comBaseInfoExample.createCriteria().andRcStateEqualTo("E").andCustBaseInfoIdEqualTo(custBaseInfoId)
							.andCustBaseInfoIdEqualTo(custContactInfo.getCustBaseInfoId());
					List<CustBaseInfo> listcustBaseInfo = custBaseInfoMapper.selectByExample(comBaseInfoExample);
					if (listcustBaseInfo != null && listcustBaseInfo.size() > 0) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该手机号已经被其他客户使用，请重新输入手机号！");
						return resultInfo;
					}
				}*/
				for (CustContactInfo custContactInfo : listCustContactInfos) {
					Long custBaseInfoIdInCustContactInfo = custContactInfo.getCustBaseInfoId();
					CustBaseInfo custBaseInfo = this.custBaseInfoMapper.selectByPrimaryKey(custBaseInfoIdInCustContactInfo);
					String custLevel = custBaseInfo.getCustLevel();
					if (custLevel != null && "01".equals(custLevel) && !custBaseInfoIdInCustContactInfo.equals(existCustBaseInfoId)){
						resultInfo.setSuccess(false); 
						resultInfo.setMsg("该手机号已经被其他签单客户使用，请重新输入！");
						return resultInfo;
					}
				}
			}
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	/**
	 * 校验客户邮箱是否已经存在
	 * 
	 * @param email
	 * @return
	 */
	private ResultInfo verifyCustomerEmail(String email,Long existCustBaseInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		// 客户号为空
		if (email!= null) {
			CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
			//邮箱
			custContactInfoExample.createCriteria().andRcStateEqualTo("E").andEmailEqualTo(email);
			List<CustContactInfo> listCustContactInfos = custContactInfoMapper.selectByExample(custContactInfoExample);
			if (listCustContactInfos != null && listCustContactInfos.size() > 0) {
				for (CustContactInfo custContactInfo : listCustContactInfos) {
					Long custBaseInfoIdInCustContactInfo = custContactInfo.getCustBaseInfoId();
					CustBaseInfo custBaseInfo = this.custBaseInfoMapper.selectByPrimaryKey(custBaseInfoIdInCustContactInfo);
					String custLevel = custBaseInfo.getCustLevel();
					if (custLevel != null && "01".equals(custLevel)&& !custBaseInfoIdInCustContactInfo.equals(existCustBaseInfoId)) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该邮箱已经被其他签单客户使用，请重新输入！");
						return resultInfo;
					}
				}
			}
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 更新客户基本信息的其他信息
	 * 
	 * @param custOthInfo
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustOthInfo(Long custBaseInfoId, CustOthInfo custOthInfo, Long agentId,
			LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
			CustOthInfoExample.Criteria custOthCriteria = custOthInfoExample.createCriteria();
			custOthCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustOthInfo> existCustOthInfoList = custOthInfoMapper.selectByExample(custOthInfoExample);
			if (custOthInfo != null) {
				if (existCustOthInfoList != null && existCustOthInfoList.size() > 0) {
					CustOthInfo existCustOthInfo = existCustOthInfoList.get(0);
					// 已经存在数据，更新即可
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustOthInfo, custOthInfo, loginInfo);
					custOthInfo.setCustOthInfoId(existCustOthInfo.getCustOthInfoId());
					custOthInfo.setCustBaseInfoId(custBaseInfoId);
					custOthInfo.setAgentId(agentId);
					custOthInfo.setCustType(existCustOthInfo.getCustType());
					custOthInfo.setBuildingName(existCustOthInfo.getBuildingName());
					//custOthInfo.setCustBuildingno(existCustOthInfo.getCustBuildingno());
					BeanUtils.updateObjectSetOperateInfo(custOthInfo, loginInfo);
					custOthInfoMapper.updateByPrimaryKey(custOthInfo);
				} else {
					// 插入新的个人信息
					Long custOthInfoId = commonService.getSeqValByName("SEQ_T_CUST_OTH_INFO");
					custOthInfo.setCustOthInfoId(custOthInfoId);
					custOthInfo.setCustBaseInfoId(custBaseInfoId);
					custOthInfo.setAgentId(agentId);
					BeanUtils.insertObjectSetOperateInfo(custOthInfo, loginInfo);
					custOthInfoMapper.insert(custOthInfo);
				}
			}
			resultInfo.setMsg("更新客户基本信息的其他信息成功!");
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("更新客户基本信息的其他信息出现异常!");
			resultInfo.setSuccess(false);
		}

		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo submitCustomerAddressAndAccInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		final String custBaseInfoIdStr = paramMap.get("custBaseInfoId").toString();
		try {
			
			if (paramMap != null) {
				String custLevel = paramMap.get("custLevel").toString();
				//若为准客户，则可进行操作
				if (custLevel.equals("02")) {
					//String custBaseInfoIdStr = paramMap.get("custBaseInfoId").toString();
					if (custBaseInfoIdStr != null && !"".equals(custBaseInfoIdStr)) {
						Long custBaseInfoId = new Long(custBaseInfoIdStr);
						String agentIdStr = paramMap.get("agentId").toString();
						if ( agentIdStr != null && !"".equals(agentIdStr)) {
							// resultMap对象存储待返回地址和账户信息
							Map resultMap = new HashMap();
							Long agentId = new Long(agentIdStr);
							List<CustAddressInfo> custAddressInfoList = (List<CustAddressInfo>) paramMap.get("custAddressInfoList");
							resultInfo = saveCustAddressInfo(custBaseInfoId, custAddressInfoList, agentId, loginInfo);
							if (!resultInfo.isSuccess()) {
								resultInfo.setSuccess(false);
								resultInfo.setMsg("提交客户联系地址信息出错！");
							}
							resultMap.put("addressList", resultInfo.getObj());
							List<CustAccInfo> custAccInfoList = (List<CustAccInfo>) paramMap.get("custAccInfoList");
							resultInfo = saveCustAccInfo(custBaseInfoId, custAccInfoList, agentId, loginInfo,resultInfo);
							if (!resultInfo.isSuccess()) {
								resultInfo.setSuccess(false);
								resultInfo.setMsg("提交客户联系地址信息出错！");
							} else {
								resultInfo.setSuccess(true);
								resultInfo.setMsg("保存客户联系地址和账户信息成功！");
							}
							resultMap.put("accList", resultInfo.getObj());
							resultInfo.setObj(resultMap);
						} else {
							resultInfo.setSuccess(false);
							resultInfo.setMsg("请先保存客户基本信息!");
						}
					} else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("请先保存客户基本信息!");
					}
			  
				}
				//若为签单客户
				if(custLevel.equals("01")){
				String loginUserId = loginInfo.getUserId().toString();
				//String custBaseInfoIdStr = paramMap.get("custBaseInfoId").toString();
				//若为签单客户，并且地址和账户信息无改动，则可以提交
				CustAddressInfoExample custAddrInfoExample = new CustAddressInfoExample();
				CustAddressInfoExample.Criteria addrCriteria = custAddrInfoExample.createCriteria();
				addrCriteria.andCustBaseInfoIdEqualTo(Long.parseLong(custBaseInfoIdStr)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				//已存在的客户地址信息
				List<CustAddressInfo> existCustAddrInfoList = this.custAddressInfoMapper.selectByExample(custAddrInfoExample);
				CustAccInfoExample accInfoExample = new CustAccInfoExample();
				CustAccInfoExample.Criteria accCriteria = accInfoExample.createCriteria();
				accCriteria.andCustBaseInfoIdEqualTo(Long.parseLong(custBaseInfoIdStr)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				//已存在的客户账户信息
				List<CustAccInfo> existCustAccInfoList = this.custAccInfoMapper.selectByExample(accInfoExample);
				//若登录用户为fms，则可以修改信息
				if(loginUserId.equals("1")){
				//String custBaseInfoIdStr = paramMap.get("custBaseInfoId").toString();
				if (custBaseInfoIdStr != null && !"".equals(custBaseInfoIdStr)) {
					Long custBaseInfoId = new Long(custBaseInfoIdStr);
					String agentIdStr = paramMap.get("agentId").toString();
					if ( agentIdStr != null && !"".equals(agentIdStr)) {
						// resultMap对象存储待返回地址和账户信息
						Map resultMap = new HashMap();
						Long agentId = new Long(agentIdStr);
						List<CustAddressInfo> custAddressInfoList = (List<CustAddressInfo>) paramMap.get("custAddressInfoList");
						resultInfo = saveCustAddressInfo(custBaseInfoId, custAddressInfoList, agentId, loginInfo);
						if (!resultInfo.isSuccess()) {
							resultInfo.setSuccess(false);
							resultInfo.setMsg("提交客户联系地址信息出错！");
						}
						resultMap.put("addressList", resultInfo.getObj());
						List<CustAccInfo> custAccInfoList = (List<CustAccInfo>) paramMap.get("custAccInfoList");
						resultInfo = saveCustAccInfo(custBaseInfoId, custAccInfoList, agentId, loginInfo,resultInfo);
						if (!resultInfo.isSuccess()) {
							resultInfo.setSuccess(false);
							resultInfo.setMsg("提交客户联系地址信息出错！");
						} else {
							//ZYM-判断是否修改账户 若有修改则创建账户变更短信
							//若为删除或者新增不生成短信
						if (existCustAccInfoList.size() == custAccInfoList.size()) {
							for (int i=0 ; i<existCustAccInfoList.size() ; i++) {
							String existAccNo = existCustAccInfoList.get(i).getBankAccNo();
							String custAccNo = custAccInfoList.get(i).getBankAccNo();
								if (!existAccNo.equals(custAccNo)) {
									//不一致则生成短信
									resultInfo = smsService.createMoidfyMobileMessage(custBaseInfoId,existAccNo,custAccNo, loginInfo);
								}
							}
						}
						resultInfo.setSuccess(true);
						resultInfo.setMsg("保存客户联系地址和账户信息成功！");
						}
						resultMap.put("accList", resultInfo.getObj());
						resultInfo.setObj(resultMap);
					} else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("请先保存客户基本信息!");
					}
				} else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("请先保存客户基本信息!");
				}
		  }else {  
			  //不为fms登录
			  //若仅添加账户/地址信息 则可以保存
			 
			  List<CustAccInfo> custAccInfoList = (List<CustAccInfo>) paramMap.get("custAccInfoList");
			  		 int i = 0; int j = 0;
			  		for (CustAccInfo custAccInfo : custAccInfoList) {
						Long custAccInfoId = custAccInfo.getCustAccInfoId();
						if (custAccInfoId == null) {
								Long custBaseInfoId = new Long(custBaseInfoIdStr);
								String agentIdStr = paramMap.get("agentId").toString();
								if ( agentIdStr != null && !"".equals(agentIdStr)) {
									Long agentId = new Long(agentIdStr);
									resultInfo = saveCustAccInfo(custBaseInfoId, custAccInfoList, agentId, loginInfo,resultInfo);
								}else {
									resultInfo.setSuccess(false);
									resultInfo.setMsg("请先保存客户基本信息!");
								}
								i = i - 1;
						}else if (!existCustAccInfoList.get(i).equals(custAccInfo)) {
							resultInfo.setMsg("已保存账户信息不可修改");
						}
						i++;
					}
			  		//若存在修改账户信息 则返回并报错
			      if (resultInfo.getMsg().equals("已保存账户信息不可修改")) {
			    	  resultInfo.setSuccess(false);
					resultInfo.setMsg("已保存地址/账户信息不可修改!");
					return resultInfo;
				} 
			      //若存在删减账户的情况 返回并报错
			      if (custAccInfoList.size() < existCustAccInfoList.size()) {
			    	  resultInfo.setSuccess(false);
						resultInfo.setMsg("已保存地址/账户信息不可修改!");
						return resultInfo;
				}
			     
			      List<CustAddressInfo> custAddressInfoList = (List<CustAddressInfo>)paramMap.get("custAddressInfoList");
			  	for (CustAddressInfo custAddressInfo : custAddressInfoList) {
					Long custAddressInfoId = custAddressInfo.getCustAddressInfoId();
					if (custAddressInfoId == null) {
							Long custBaseInfoId = new Long(custBaseInfoIdStr);
							String agentIdStr = paramMap.get("agentId").toString();
							if ( agentIdStr != null && !"".equals(agentIdStr)) {
								Long agentId = new Long(agentIdStr);
								resultInfo = saveCustAddressInfo(custBaseInfoId, custAddressInfoList, agentId, loginInfo);
							}else {
								resultInfo.setSuccess(false);
								resultInfo.setMsg("请先保存客户基本信息!");
							}
							j = j - 1;
					}else if (!existCustAddrInfoList.get(j).equals(custAddressInfo)) {
						resultInfo.setMsg("已保存地址信息不可修改");
					}
					j++;
				}
				//若存在修改账户信息 则返回并报错
			      if (resultInfo.getMsg().equals("已保存地址信息不可修改")) {
			    	  resultInfo.setSuccess(false);
					resultInfo.setMsg("已保存地址/账户信息不可修改!");
					return resultInfo;
				} 
			      //若存在删减账户的情况 返回并报错
			      if (custAddressInfoList.size() < existCustAddrInfoList.size()) {
			    	  resultInfo.setSuccess(false);
						resultInfo.setMsg("已保存地址/账户信息不可修改!");
						return resultInfo;
				}
			    else {
			    	// resultMap对象存储待返回地址和账户信息
					Map resultMap = new HashMap();
		      		resultMap.put("accList",resultInfo.getObj());
		      		resultInfo.setObj(resultMap);
		    		resultInfo.setSuccess(true);
			    	resultInfo.setMsg("保存客户地址和账户信息成功！");
				}	  	
					  
			      
				}
		  }
	}
							
			
	else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请先保存客户基本信息!");
			}
    } catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("提交客户联系地址信息和账户信息出现异常！");
		}
		return resultInfo;
	}

	/**
	 * 更新客户地址信息
	 * 
	 * @param custBaseInfo
	 * @param custAddressInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustAddressInfo(Long custBaseInfoId, List<CustAddressInfo> custAddressInfoList,Long agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {	
				if (resultInfo.getMsg().equals("已保存地址信息不可修改")) {
					resultInfo.setMsg("已保存地址信息不可修改");
					resultInfo.setSuccess(false);
					return resultInfo;
				}
				// 根据custBaseInfoId查询客户地址信息
				CustAddressInfoExample custAddrInfoExample = new CustAddressInfoExample();
				CustAddressInfoExample.Criteria addrCriteria = custAddrInfoExample.createCriteria();
				addrCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustAddressInfo> existCustAddrInfoList = custAddressInfoMapper.selectByExample(custAddrInfoExample);
				// 若客户地址信息已存在
				boolean agentChangeStatus = false;
				if (existCustAddrInfoList != null && existCustAddrInfoList.size() > 0) {
					int custAddrelistSize = existCustAddrInfoList.size();
					for(int i = 0; i < custAddrelistSize; ++i) {
						CustAddressInfo custAddressInfo = existCustAddrInfoList.get(i);
						// 判断custAddressInfo中agentId是否与浏览器传入的agentI相同
						if (agentId.compareTo(custAddressInfo.getAgentId()) != 0) {
							agentChangeStatus = true;
							break;
						}
					}
				}
				// 若客户地址信息中关联的agentId与浏览器传入的agentId不同：1 逻辑删除原有地址信息,2 重新存储新地址信息
				if(agentChangeStatus) {
							// 逻辑删除客户地址信息
							for (CustAddressInfo existCustAddressInfo : existCustAddrInfoList) {
								BeanUtils.deleteObjectSetOperateInfo(existCustAddressInfo, loginInfo);
								this.custAddressInfoMapper.updateByPrimaryKey(existCustAddressInfo);
							}
							// 重新保存新的客户地址信息
							for (CustAddressInfo custAddressInfoFromBrow : custAddressInfoList) {
								Long custAddressId = commonService.getSeqValByName("SEQ_T_CUST_ADDRESS_INFO");
								custAddressInfoFromBrow.setCustBaseInfoId(custBaseInfoId);
								custAddressInfoFromBrow.setCustAddressInfoId(custAddressId);
								custAddressInfoFromBrow.setAgentId(agentId);
								BeanUtils.insertObjectSetOperateInfo(custAddressInfoFromBrow, loginInfo);
								this.custAddressInfoMapper.insert(custAddressInfoFromBrow);
							}
							// 根据custBaseInfoId查询客户地址信息
							Map paramMap = new HashMap();
							paramMap.put("custBaseInfoId", custBaseInfoId);
							// 获取客户联系地址信息
							List<Map> custAddressInfoMapList = this.modifyCustomerServiceMapper.custAddressInfoMapList(paramMap);
							// 返回客户地址信息
							resultInfo.setObj(custAddressInfoMapList);
				} else {
						// 根据agentId和custBaseInfoId查询查询客户地址信息
						CustAddressInfoExample custAddressInfoExample = new CustAddressInfoExample();
						CustAddressInfoExample.Criteria addressCriteria = custAddressInfoExample.createCriteria();
						addressCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						List<CustAddressInfo> existCustAddressInfoList = custAddressInfoMapper.selectByExample(custAddressInfoExample);
						// 将查询到的客户地址信息放入delExistCustAddressInfoList
						List<CustAddressInfo> delExistCustAddressInfoList = new ArrayList<CustAddressInfo>();
						delExistCustAddressInfoList.addAll(existCustAddressInfoList);
						// 判断浏览器传入的custAddressInfoList
						if (custAddressInfoList != null && custAddressInfoList.size() > 0) {
							// 遍历浏览器传入的地址列表custAddressInfoList
							for (CustAddressInfo custAddressInfo : custAddressInfoList) {
									if (custAddressInfo.getCustAddressInfoId() == null) {
												Long custAddressId = commonService.getSeqValByName("SEQ_T_CUST_ADDRESS_INFO");
												custAddressInfo.setCustBaseInfoId(custBaseInfoId);
												custAddressInfo.setCustAddressInfoId(custAddressId);
												custAddressInfo.setAgentId(agentId);
												BeanUtils.insertObjectSetOperateInfo(custAddressInfo, loginInfo);
												this.custAddressInfoMapper.insert(custAddressInfo);
									} 
									else 
									{
												for (CustAddressInfo existCustAddressInfo : existCustAddressInfoList) {
															if (custAddressInfo.getCustAddressInfoId().compareTo(existCustAddressInfo.getCustAddressInfoId()) == 0) {
																if (!custAddressInfo.equals(existCustAddressInfo)) {
																	// 如果页面信息和已经存在信息完全相同，则不作处理，否则更新
																	custAddressInfo.setCustAddressInfoId(existCustAddressInfo.getCustAddressInfoId());
																	custAddressInfo.setCustBaseInfoId(custBaseInfoId);
																	custAddressInfo.setAgentId(agentId);
																	/*BeanUtils.updateObjectSetOperateInfo(custAddressInfo, loginInfo);*/
																	BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustAddressInfo, custAddressInfo,
																			loginInfo);
																	custAddressInfoMapper.updateByPrimaryKey(custAddressInfo);
																	delExistCustAddressInfoList.remove(existCustAddressInfo);
																} 
																else 
																{
																	delExistCustAddressInfoList.remove(existCustAddressInfo);
																}
															}
												}
									}
							}
						}
						// 逻辑删除客户原有的地址信息
						for (CustAddressInfo delExistCustAddressInfo : delExistCustAddressInfoList) {
							BeanUtils.deleteObjectSetOperateInfo(delExistCustAddressInfo, loginInfo);
							custAddressInfoMapper.updateByPrimaryKey(delExistCustAddressInfo);
						}
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
	 * 基于接口的更新客户地址信息
	 * 
	 * @param custBaseInfo
	 * @param custAddressInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveInternetCustAddressInfo(Long custBaseInfoId, CustAddressInfo custAddressInfo,
			Long agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 查询财富顾问之前是否保存过该客户地址信息
			CustAddressInfoExample custAddressInfoExample = new CustAddressInfoExample();
			CustAddressInfoExample.Criteria addressCriteria = custAddressInfoExample.createCriteria();
			addressCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andAddressTypeEqualTo("02");
			List<CustAddressInfo> existCustAddressInfoList = custAddressInfoMapper
					.selectByExample(custAddressInfoExample);
			custAddressInfo.setAddressType("02");
			boolean flog = false;
			if (existCustAddressInfoList != null && existCustAddressInfoList.size() > 0) {
				for (CustAddressInfo existCustAddressInfo : existCustAddressInfoList) {
					if (custAddressInfo.equals(existCustAddressInfo)) {
						// 如果页面信息和已经存在信息完全相同，则不作处理，否则新增
						flog = true;
						break;
					}
				}
				if (flog == false) {
					Long custAddressId = commonService.getSeqValByName("SEQ_T_CUST_ADDRESS_INFO");
					custAddressInfo.setCustBaseInfoId(custBaseInfoId);
					custAddressInfo.setCustAddressInfoId(custAddressId);
					custAddressInfo.setAgentId(agentId);
					custAddressInfo.setAddressType("02");
					BeanUtils.insertObjectSetOperateInfo(custAddressInfo, loginInfo);
					custAddressInfoMapper.insert(custAddressInfo);
				}
			} else {
				// 没有地址信息，直接新增
				Long custAddressId = commonService.getSeqValByName("SEQ_T_CUST_ADDRESS_INFO");
				custAddressInfo.setCustBaseInfoId(custBaseInfoId);
				custAddressInfo.setCustAddressInfoId(custAddressId);
				custAddressInfo.setAgentId(agentId);
				custAddressInfo.setAddressType("02");
				BeanUtils.insertObjectSetOperateInfo(custAddressInfo, loginInfo);
				custAddressInfoMapper.insert(custAddressInfo);
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
	 * 更新客户账户信息
	 * 
	 * @param custAccInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustAccInfo(Long custBaseInfoId, List<CustAccInfo> custAccInfoList, Long agentId,LoginInfo loginInfo,ResultInfo resultInfo) {
		//ResultInfo resultInfo = new ResultInfo();
		try {
			if (resultInfo.getMsg().equals("已保存账户信息不可修改")) {
				resultInfo.setMsg("已保存账户信息不可修改");
				resultInfo.setSuccess(false);
				return resultInfo;
			}
			// 判断agentId是否发生变更
			// 根据custBaseInfoId查询客户账户信息
			CustAccInfoExample accInfoExample = new CustAccInfoExample();
			CustAccInfoExample.Criteria accCriteria = accInfoExample.createCriteria();
			accCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAccInfo> existAccInfoList = this.custAccInfoMapper.selectByExample(accInfoExample);
			// 若客户账户信息已存在
			boolean agentChangeStatus = false;
			if (existAccInfoList != null && existAccInfoList.size() > 0) {
				int custAcclistSize = existAccInfoList.size();
				for(int i = 0; i < custAcclistSize; ++i) {
					CustAccInfo custAddressInfo = existAccInfoList.get(i);
					// 判断custAddressInfo中agentId是否与浏览器传入的agentI相同
					if (agentId.compareTo(custAddressInfo.getAgentId()) != 0) {
						agentChangeStatus = true;
						break;
					}
				}
			}
			// 若客户账户信息中关联的agentId与浏览器传入的agentId不同：1 逻辑删除原有账户信息,2 重新存储账户信息
			if(agentChangeStatus) {
						// 逻辑删除原理财经理录入的账户信息
						for (CustAccInfo existAccInfo : existAccInfoList) {
							BeanUtils.deleteObjectSetOperateInfo(existAccInfo, loginInfo);
							this.custAccInfoMapper.updateByPrimaryKey(existAccInfo);
						}
						// 重新保存新理财经理录入的账户信息
						for (CustAccInfo accInfoFromBrow : custAccInfoList) {
							Long custAccInfoId = commonService.getSeqValByName("SEQ_T_CUST_ACC_INFO");
							accInfoFromBrow.setCustBaseInfoId(custBaseInfoId);
							accInfoFromBrow.setCustAccInfoId(custAccInfoId);
							accInfoFromBrow.setAgentId(agentId);
							BeanUtils.insertObjectSetOperateInfo(accInfoFromBrow, loginInfo);
							this.custAccInfoMapper.insert(accInfoFromBrow);
						}
						// 根据custBaseInfoId查询账户信息
						Map paramMap = new HashMap();
						paramMap.put("custBaseInfoId", custBaseInfoId);
						// 获取客户账户信息
						List<Map> custAccInfoMapList = this.modifyCustomerServiceMapper.custAccInfoMapList(paramMap);
						// 将新增加账户信息返回页面
						resultInfo.setObj(custAccInfoMapList);
			} else {
					//理财经理agentId未发生变更
					CustAccInfoExample custAccInfoExample = new CustAccInfoExample();
					CustAccInfoExample.Criteria custAccCriteria = custAccInfoExample.createCriteria();
					custAccCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<CustAccInfo> existCustAccInfoList = custAccInfoMapper.selectByExample(custAccInfoExample);
					List<CustAccInfo> delExistCustAccInfoList = new ArrayList<CustAccInfo>();
					delExistCustAccInfoList.addAll(existCustAccInfoList);
					if (custAccInfoList != null && custAccInfoList.size() > 0) {
						for (CustAccInfo custAccInfo : custAccInfoList) {
							if (custAccInfo.getCustAccInfoId() == null) {
								// 插入客户新增的账户信息
								Long custAccInfoId = commonService.getSeqValByName("SEQ_T_CUST_ACC_INFO");
								custAccInfo.setCustAccInfoId(custAccInfoId);
								custAccInfo.setCustBaseInfoId(custBaseInfoId);
								custAccInfo.setAgentId(agentId);
								BeanUtils.insertObjectSetOperateInfo(custAccInfo, loginInfo);
								custAccInfoMapper.insert(custAccInfo);
							} else {
								for (CustAccInfo existCustAccInfo : existCustAccInfoList) {
									if (custAccInfo.getCustAccInfoId().compareTo(existCustAccInfo.getCustAccInfoId()) == 0) {
										if (!custAccInfo.equals(existCustAccInfo)) {
											// 页面信息和数据库信息完全一致，则不作处理，否则更新
											custAccInfo.setCustAccInfoId(existCustAccInfo.getCustAccInfoId());
											custAccInfo.setCustBaseInfoId(custBaseInfoId);
											custAccInfo.setAgentId(agentId);
											BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustAccInfo, custAccInfo,
													loginInfo);
											custAccInfoMapper.updateByPrimaryKey(custAccInfo);
											delExistCustAccInfoList.remove(existCustAccInfo);
										} else {
											delExistCustAccInfoList.remove(existCustAccInfo);
										}
									}
								}
							}
						}
					}
					// 将客户删除的账户信息进行逻辑删除
					for (CustAccInfo delExistCustAccInfo : delExistCustAccInfoList) {
						BeanUtils.deleteObjectSetOperateInfo(delExistCustAccInfo, loginInfo);
						custAccInfoMapper.updateByPrimaryKey(delExistCustAccInfo);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户账户信息出错 ");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("保存客户账户信息成功");
		return resultInfo;
	}
	/**
	 * 基于接口的更新客户账户信息
	 * 
	 * @param custAccInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveInternetCustAccInfo(Long custBaseInfoId, CustAccInfo custAccInfo, Long agentId,
			LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustAccInfoExample custAccInfoExample = new CustAccInfoExample();
			CustAccInfoExample.Criteria custAccCriteria = custAccInfoExample.createCriteria();
			custAccCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAccInfo> existCustAccInfoList = custAccInfoMapper.selectByExample(custAccInfoExample);
			boolean flag = false;
			if (existCustAccInfoList != null && existCustAccInfoList.size() > 0) {
				for (CustAccInfo existCustAccInfo : existCustAccInfoList) {
					if (custAccInfo.equals(existCustAccInfo)) {
						flag = true;
						break;
					}
				}
				if (flag == false) {
					Long custAccInfoId = commonService.getSeqValByName("SEQ_T_CUST_ACC_INFO");
					custAccInfo.setCustAccInfoId(custAccInfoId);
					custAccInfo.setCustBaseInfoId(custBaseInfoId);
					custAccInfo.setAgentId(agentId);
					BeanUtils.insertObjectSetOperateInfo(custAccInfo, loginInfo);
					custAccInfoMapper.insert(custAccInfo);
				}
			} else {
				Long custAccInfoId = commonService.getSeqValByName("SEQ_T_CUST_ACC_INFO");
				custAccInfo.setCustAccInfoId(custAccInfoId);
				custAccInfo.setCustBaseInfoId(custBaseInfoId);
				custAccInfo.setAgentId(agentId);
				BeanUtils.insertObjectSetOperateInfo(custAccInfo, loginInfo);
				custAccInfoMapper.insert(custAccInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户账户信息出错 ");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("保存客户账户信息成功");
		return resultInfo;
	}
	/**
	 * 更新客户拜访信息
	 * 
	 * @param map
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo submitAllCustomerVisitInfo(Map map, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (map != null) {
				// 根据客户Id查询出已经保存的拜访信息，逻辑删除
				Long custBaseInfoId = new Long(map.get("custBaseInfoId").toString());
				Long agentId = new Long(map.get("agentId").toString());
				// 获取页面的拜访信息
				List<CustVisitInfo> custVisitInfoList = (List<CustVisitInfo>) map.get("custVisitInfoList");
				if (agentId == null || agentId.equals("")) {
					resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
					agentId = (Long) resultInfo.getObj();
				}
				CustVisitInfoExample custVisitInfoExample = new CustVisitInfoExample();
				CustVisitInfoExample.Criteria custVisitInfoCriteria = custVisitInfoExample.createCriteria();
				custVisitInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId)
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				// 获取已经存在的拜访信息
				List<CustVisitInfo> exsitCustVisitInfoList = custVisitInfoMapper.selectByExample(custVisitInfoExample);
				List<CustVisitInfo> dealExsitCustVisitInfoList = new ArrayList<CustVisitInfo>();
				dealExsitCustVisitInfoList.addAll(exsitCustVisitInfoList);
				if (custVisitInfoList != null && custVisitInfoList.size() > 0) {
					for (CustVisitInfo custVisitInfo : custVisitInfoList) {
						if (custVisitInfo.getCustVisitorInfoId() == null) {
							// 录入新增的拜访信息
							Long custVisitorInfoId = commonService.getSeqValByName("SEQ_T_CUST_VISIT_INFO");
							custVisitInfo.setCustVisitorInfoId(custVisitorInfoId);
							custVisitInfo.setCustBaseInfoId(custBaseInfoId);
							custVisitInfo.setAgentId(agentId);
							custVisitInfo.setCustVisitTime(DateUtils.getTimestamp(custVisitInfo.getCustVisitTime()));
							custVisitInfo.setNextVisitTime(DateUtils.getTimestamp(custVisitInfo.getNextVisitTime()));
							BeanUtils.insertObjectSetOperateInfo(custVisitInfo, loginInfo);
							custVisitInfoMapper.insert(custVisitInfo);	
						} else {
							if (exsitCustVisitInfoList != null && exsitCustVisitInfoList.size() > 0) {
								for (CustVisitInfo existCustVisitInfo : exsitCustVisitInfoList) {
									if (existCustVisitInfo.getCustVisitorInfoId()
											.compareTo(custVisitInfo.getCustVisitorInfoId()) == 0) {
										// 页面信息和数据信息完全相同，不作处理，否则更新
										if (!existCustVisitInfo.equals(custVisitInfo)) {
											custVisitInfo
													.setCustVisitorInfoId(existCustVisitInfo.getCustVisitorInfoId());
											custVisitInfo.setCustBaseInfoId(custBaseInfoId);
											custVisitInfo.setAgentId(agentId);
											BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustVisitInfo,
													custVisitInfo, loginInfo);
											custVisitInfoMapper.updateByPrimaryKey(custVisitInfo);
											dealExsitCustVisitInfoList.remove(existCustVisitInfo);
										}else {
											dealExsitCustVisitInfoList.remove(existCustVisitInfo);
										}
									}
								}
							}
						}
					}
				}
				// 逻辑删除客户删除的记录
				if (dealExsitCustVisitInfoList != null && dealExsitCustVisitInfoList.size() > 0) {
					for (CustVisitInfo dealExsitCustVisitInfo : dealExsitCustVisitInfoList) {
						BeanUtils.deleteObjectSetOperateInfo(dealExsitCustVisitInfo, loginInfo);
						custVisitInfoMapper.updateByPrimaryKey(dealExsitCustVisitInfo);
					}
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("更新客户拜访信息成功！");
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("更新客户拜访信息出错！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户拜访信息出现异常！");
		}
		return resultInfo;
	}

	/**
	 * 更新客户其他投资信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo submitAllCustomerInvestInfo(Map map, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (map != null) {
				// 获取页面其他投资记录
				List<CustHistoryInvestment> custHistoryInvestmentList = (List<CustHistoryInvestment>) map
						.get("custHistoryInvestmentList");
				Long custBaseInfoId = new Long(map.get("custBaseInfoId").toString());
				Long agentId = new Long(map.get("agentId").toString());
				if (agentId == null || agentId.equals("")) {
					resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
					agentId = (Long) resultInfo.getObj();
				}
				// 获取客户已经存在的投资信息
				CustHistoryInvestmentExample custHistoryInvestmentExample = new CustHistoryInvestmentExample();
				CustHistoryInvestmentExample.Criteria custHistoryInvestmentCriteria = custHistoryInvestmentExample
						.createCriteria();
				custHistoryInvestmentCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId)
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustHistoryInvestment> existCustHistoryInvestmentList = custHistoryInvestmentMapper
						.selectByExample(custHistoryInvestmentExample);
				List<CustHistoryInvestment> deleExsitcustHistoryInvestmentList = new ArrayList<CustHistoryInvestment>();
				deleExsitcustHistoryInvestmentList.addAll(existCustHistoryInvestmentList);
				if (custHistoryInvestmentList != null && custHistoryInvestmentList.size() > 0) {
					for (CustHistoryInvestment custHistoryInvestment : custHistoryInvestmentList) {
						if (custHistoryInvestment.getCustHistoryInvestmentId() == null) {
							// 插入数据库不存在的投资记录
							Long custHistoryInvestmentId = commonService
									.getSeqValByName("SEQ_T_CUST_HISTORY_INVESTMENT");
							custHistoryInvestment.setCustHistoryInvestmentId(custHistoryInvestmentId);
							custHistoryInvestment.setCustBaseInfoId(custBaseInfoId);
							custHistoryInvestment.setAgentId(agentId);
							BeanUtils.insertObjectSetOperateInfo(custHistoryInvestment, loginInfo);
							custHistoryInvestmentMapper.insert(custHistoryInvestment);
						} else {
							if (existCustHistoryInvestmentList != null && existCustHistoryInvestmentList.size() > 0) {
								for (CustHistoryInvestment existCustHistoryInvestment : existCustHistoryInvestmentList) {
									if (custHistoryInvestment.getCustHistoryInvestmentId()
											.compareTo(existCustHistoryInvestment.getCustHistoryInvestmentId()) == 0) {
										if (!custHistoryInvestment.equals(existCustHistoryInvestment)) {
											// 页面数据和数据库数据完全相同，不作操作，否则更新
											custHistoryInvestment.setCustHistoryInvestmentId(
													existCustHistoryInvestment.getCustHistoryInvestmentId());
											custHistoryInvestment.setCustBaseInfoId(custBaseInfoId);
											custHistoryInvestment.setAgentId(agentId);
											BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustHistoryInvestment,
													custHistoryInvestment, loginInfo);
											custHistoryInvestmentMapper.updateByPrimaryKey(custHistoryInvestment);
											deleExsitcustHistoryInvestmentList.remove(existCustHistoryInvestment);
										}else {
											deleExsitcustHistoryInvestmentList.remove(existCustHistoryInvestment);
										}
									}
								}
							}
						}
					}
				}
				// 上面删除掉相同的和更改的记录就剩下客户在页面删除的，逻辑删除掉即可
				if (deleExsitcustHistoryInvestmentList != null && deleExsitcustHistoryInvestmentList.size() > 0) {
					for (CustHistoryInvestment deleExsitCustHistoryInvestment : deleExsitcustHistoryInvestmentList) {
						BeanUtils.deleteObjectSetOperateInfo(deleExsitCustHistoryInvestment, loginInfo);
						custHistoryInvestmentMapper.updateByPrimaryKey(deleExsitCustHistoryInvestment);
					}
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("更新客户投资信息成功！");
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("更新客户其他投资信息出错！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户其他投资信息出现异常！");
		}
		return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo submitModifyCustomerPersonalInfo(Map map, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			Long custBaseInfoId = new Long(map.get("custBaseInfoId").toString());
			Long agentId = new Long(map.get("agentId").toString());
			if (agentId == null || agentId.equals("")) {
				resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
				agentId = (Long) resultInfo.getObj();
			}
			List<CustInvestmentSuggest> custInvestmentSuggestList = (List<CustInvestmentSuggest>) map
					.get("custInvestmentSuggestList");
			// 更新客户投资意向和建议信息
			updateCustInvestmentSuggest(custBaseInfoId, custInvestmentSuggestList, agentId, loginInfo);
			List<CustFamilyInfo> custFamilyInfoList = (List<CustFamilyInfo>) map.get("custFamilyInfoList");
			// 更新客户家庭信息
			saveCustFamilyInfo(custBaseInfoId, custFamilyInfoList, agentId, loginInfo);
			List<CustHouseInfo> custHouseInfoList = (List<CustHouseInfo>) map.get("custHouseInfoList");
			// 更新客户房产信息
			saveCustHouseInfo(custBaseInfoId, custHouseInfoList, agentId, loginInfo);
			List<CustCarInfo> custCarInfoList = (List<CustCarInfo>) map.get("custCarInfoList");
			// 更新客户车辆信息
			saveCustCarInfo(custBaseInfoId, custCarInfoList, agentId, loginInfo);
			CustOthInfo custOthInfo = (CustOthInfo) map.get("custOthInfo");
			// 更新客户其他信息
			saveCustOthInfo(custBaseInfoId, custOthInfo, agentId, loginInfo);
			// 更新客户兴趣爱好信息
			List<CustHobbyInfo> custHobbyInfoList = (List<CustHobbyInfo>) map.get("custHobbyInfoList");
			saveCustHobbyInfo(custBaseInfoId, custHobbyInfoList, agentId, loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("更新客户个人信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户个人信息出现异常！");
		}
		return resultInfo;
	}

	/**
	 * 更新客户投资意向和建议信息
	 * 
	 * @param custBaseInfoId
	 * @param custInvestmentSuggestList
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo updateCustInvestmentSuggest(Long custBaseInfoId,
			List<CustInvestmentSuggest> custInvestmentSuggestList, Long agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 根据客户Id查询出数据库存在的记录
			CustInvestmentSuggestExample custInvestmentSuggestExample = new CustInvestmentSuggestExample();
			CustInvestmentSuggestExample.Criteria custInvestmentSuggestCriteria = custInvestmentSuggestExample
					.createCriteria();
			custInvestmentSuggestCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustInvestmentSuggest> existCustInvestmentSuggestList = custInvestmentSuggestMapper
					.selectByExample(custInvestmentSuggestExample);
			List<CustInvestmentSuggest> dealExistCustInvestmentSuggestList = new ArrayList<CustInvestmentSuggest>();
			dealExistCustInvestmentSuggestList.addAll(existCustInvestmentSuggestList);
			// 保存客户投资意向和建议信息
			if (custInvestmentSuggestList != null && custInvestmentSuggestList.size() > 0) {
				for (CustInvestmentSuggest custInvestmentSuggest : custInvestmentSuggestList) {
					if (custInvestmentSuggest.getCustInvestSuggestId() == null) {
						// 理財经理信息新增加的信息存入数据库
						Long custInvestSuggestId = commonService.getSeqValByName("SEQ_T_CUST_INVESTMENT_SUGGEST");
						custInvestmentSuggest.setCustInvestSuggestId(custInvestSuggestId);
						custInvestmentSuggest.setCustBaseInfoId(custBaseInfoId);
						custInvestmentSuggest.setAgentId(agentId);
						BeanUtils.insertObjectSetOperateInfo(custInvestmentSuggest, loginInfo);
						custInvestmentSuggestMapper.insert(custInvestmentSuggest);
					} else {
						if (existCustInvestmentSuggestList != null && existCustInvestmentSuggestList.size() > 0) {
							for (CustInvestmentSuggest existCustInvestmentSuggest : existCustInvestmentSuggestList) {
								if (custInvestmentSuggest.getCustInvestSuggestId()
										.compareTo(existCustInvestmentSuggest.getCustInvestSuggestId()) == 0) {
									// 页面信息和数据库信息完全一致，不作处理，否则更新
									if (!custInvestmentSuggest.equals(existCustInvestmentSuggest)) {
										custInvestmentSuggest.setCustInvestSuggestId(
												existCustInvestmentSuggest.getCustInvestSuggestId());
										custInvestmentSuggest.setCustBaseInfoId(custBaseInfoId);
										custInvestmentSuggest.setAgentId(agentId);
										BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustInvestmentSuggest,
												custInvestmentSuggest, loginInfo);
										custInvestmentSuggestMapper.updateByPrimaryKey(custInvestmentSuggest);
										dealExistCustInvestmentSuggestList.remove(existCustInvestmentSuggest);
									}else {
										dealExistCustInvestmentSuggestList.remove(existCustInvestmentSuggest);
									}
								}
							}
						}
					}
				}
			}
			// 逻辑删除掉客户删除的记录
			if (dealExistCustInvestmentSuggestList != null && dealExistCustInvestmentSuggestList.size() > 0) {
				for (CustInvestmentSuggest custInvestmentSuggest : dealExistCustInvestmentSuggestList) {
					BeanUtils.deleteObjectSetOperateInfo(custInvestmentSuggest, loginInfo);
					custInvestmentSuggestMapper.updateByPrimaryKey(custInvestmentSuggest);
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setMsg("更新客户投资投资意向和建议信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户投资投资意向和建议信息出现异常！");
		}
		return resultInfo;
	}

	/**
	 * 更新客户家庭信息
	 * 
	 * @param custFamilyInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustFamilyInfo(Long custBaseInfoId, List<CustFamilyInfo> custFamilyInfoList,
			Long agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustFamilyInfoExample custFamilyInfoExample = new CustFamilyInfoExample();
			CustFamilyInfoExample.Criteria custFamilyCriteria = custFamilyInfoExample.createCriteria();
			custFamilyCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustFamilyInfo> existCustFamilyInfoList = custFamilyInfoMapper.selectByExample(custFamilyInfoExample);
			if (existCustFamilyInfoList != null && existCustFamilyInfoList.size() > 0) {
				// 逻辑删除之前保存的客户家庭信息
				for (CustFamilyInfo existCustFamilyInfo : existCustFamilyInfoList) {
					BeanUtils.deleteObjectSetOperateInfo(existCustFamilyInfo, loginInfo);
					custFamilyInfoMapper.updateByPrimaryKey(existCustFamilyInfo);
				}
			}
			if (custFamilyInfoList != null && custFamilyInfoList.size() > 0) {
				// 保存新的客户家庭信息
				for (CustFamilyInfo custFamilyInfo : custFamilyInfoList) {
					Long custFamilyInfoId = commonService.getSeqValByName("SEQ_T_CUST_FAMILY_INFO");
					custFamilyInfo.setCustFamilyInfoId(custFamilyInfoId);
					custFamilyInfo.setCustBaseInfoId(custBaseInfoId);
					custFamilyInfo.setAgentId(agentId);
					BeanUtils.insertObjectSetOperateInfo(custFamilyInfo, loginInfo);
					custFamilyInfoMapper.insert(custFamilyInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("保存客户家庭信息出错");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setMsg("保存客户家庭信息成功");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 更新客户房产信息
	 * 
	 * @param custHouseInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustHouseInfo(Long custBaseInfoId, List<CustHouseInfo> custHouseInfoList,
			Long agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustHouseInfoExample custHouseInfoExample = new CustHouseInfoExample();
			CustHouseInfoExample.Criteria custHouseCriteria = custHouseInfoExample.createCriteria();
			custHouseCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustHouseInfo> existCustHouseInfoList = custHouseInfoMapper.selectByExample(custHouseInfoExample);
			if (existCustHouseInfoList != null && existCustHouseInfoList.size() > 0) {
				// 逻辑删除之前保存的客户房产信息
				for (CustHouseInfo existCustHouseInfo : existCustHouseInfoList) {
					BeanUtils.deleteObjectSetOperateInfo(existCustHouseInfo, loginInfo);
					custHouseInfoMapper.updateByPrimaryKey(existCustHouseInfo);
				}
			}
			if (custHouseInfoList != null && custHouseInfoList.size() > 0) {
				// 保存新的客户房产信息
				for (CustHouseInfo custHouseInfo : custHouseInfoList) {
					Long custHouseInfoId = commonService.getSeqValByName("SEQ_T_CUST_HOUSE_INFO");
					custHouseInfo.setCustHouseInfoId(custHouseInfoId);
					custHouseInfo.setCustBaseInfoId(custBaseInfoId);
					custHouseInfo.setAgentId(agentId);
					BeanUtils.insertObjectSetOperateInfo(custHouseInfo, loginInfo);
					custHouseInfoMapper.insert(custHouseInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("保存客户房产信息出错");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setMsg("保存客户房产信息成功");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 更新客户车辆信息
	 * 
	 * @param custCarInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustCarInfo(Long custBaseInfoId, List<CustCarInfo> custCarInfoList, Long agentId,
			LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustCarInfoExample custCarInfoExample = new CustCarInfoExample();
			CustCarInfoExample.Criteria custCarInfoCriteria = custCarInfoExample.createCriteria();
			custCarInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustCarInfo> existCustCarInfoList = custCarInfoMapper.selectByExample(custCarInfoExample);
			if (existCustCarInfoList != null && existCustCarInfoList.size() > 0) {
				// 逻辑删除之前保存的客户车辆信息
				for (CustCarInfo existCustCarInfo : existCustCarInfoList) {
					BeanUtils.deleteObjectSetOperateInfo(existCustCarInfo, loginInfo);
					custCarInfoMapper.updateByPrimaryKey(existCustCarInfo);
				}
			}
			if (custCarInfoList != null && custCarInfoList.size() > 0) {
				// 保存新的客户车辆信息
				for (CustCarInfo custCarInfo : custCarInfoList) {
					Long custCarInfoId = commonService.getSeqValByName("SEQ_T_CUST_CAR_INFO");
					custCarInfo.setCustCarInfoId(custCarInfoId);
					custCarInfo.setCustBaseInfoId(custBaseInfoId);
					custCarInfo.setAgentId(agentId);
					BeanUtils.insertObjectSetOperateInfo(custCarInfo, loginInfo);
					custCarInfoMapper.insert(custCarInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("保存客户车辆信息出错");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setMsg("保存客户车辆信息成功");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 保存客户爱好信息
	 * 
	 * @param custBaseInfo
	 * @param custHobbyInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustHobbyInfo(Long custBaseInfoId, List<CustHobbyInfo> custHobbyInfoList,
			Long agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustHobbyInfoExample custHobbyInfoExample = new CustHobbyInfoExample();
			CustHobbyInfoExample.Criteria custHobbyCriteria = custHobbyInfoExample.createCriteria();
			custHobbyCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustHobbyInfo> existCustHobbyInfoList = custHobbyInfoMapper.selectByExample(custHobbyInfoExample);
			if (existCustHobbyInfoList != null && existCustHobbyInfoList.size() > 0) {
				// 删除客户爱好信息
				for (CustHobbyInfo existCustHobbyInfo : existCustHobbyInfoList) {
					BeanUtils.deleteObjectSetOperateInfo(existCustHobbyInfo, loginInfo);
					custHobbyInfoMapper.updateByPrimaryKey(existCustHobbyInfo);
				}
			}
			if (custHobbyInfoList != null && custHobbyInfoList.size() > 0) {
				// 插入新的爱好信息
				for (CustHobbyInfo custHobbyInfo : custHobbyInfoList) {
					Long custHobbyInfoId = commonService.getSeqValByName("SEQ_T_CUST_HOBBY_INFO");
					custHobbyInfo.setCustBaseInfoId(custBaseInfoId);
					custHobbyInfo.setCustHobbyInfoId(custHobbyInfoId);
					custHobbyInfo.setAgentId(agentId);
					BeanUtils.insertObjectSetOperateInfo(custHobbyInfo, loginInfo);
					custHobbyInfoMapper.insert(custHobbyInfo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("保存客户爱好信息出错");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setMsg("保存客户爱好信息成功");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 更新客户基本信息其他信息
	 * 
	 * @param custOthInfo
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustBaseOthInfo(Long custBaseInfoId, CustOthInfo custOthInfo, Long agentId,
			LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
			CustOthInfoExample.Criteria custOthCriteria = custOthInfoExample.createCriteria();
			custOthCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustOthInfo> existCustOthInfoList = custOthInfoMapper.selectByExample(custOthInfoExample);
			if (custOthInfo != null) {
				if (existCustOthInfoList != null && existCustOthInfoList.size() > 0) {
					CustOthInfo existCustOthInfo = existCustOthInfoList.get(0);
					existCustOthInfo.setCustType(custOthInfo.getCustType());
					existCustOthInfo.setBuildingName(custOthInfo.getBuildingName());
					//existCustOthInfo.setCustBuildingno(custOthInfo.getCustBuildingno());
					BeanUtils.updateObjectSetOperateInfo(existCustOthInfo, loginInfo);
					custOthInfoMapper.updateByPrimaryKey(existCustOthInfo);
				} else {
					// 插入新的个人信息
					Long custOthInfoId = commonService.getSeqValByName("SEQ_T_CUST_OTH_INFO");
					custOthInfo.setCustOthInfoId(custOthInfoId);
					custOthInfo.setCustBaseInfoId(custBaseInfoId);
					custOthInfo.setAgentId(agentId);
					BeanUtils.insertObjectSetOperateInfo(custOthInfo, loginInfo);
					custOthInfoMapper.insert(custOthInfo);
				}
			}
			resultInfo.setMsg("更新客户基本信息其他信息出错！");
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("更新客户基本信息其他信息出现异常！");
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}

	/**
	 * 获取客户兴趣爱好信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	private List<CustHobbyInfo> getCustHobbyInfoList(Map paramMap) {
		List<CustHobbyInfo> custHobbyInfoList = null;
		try {
			CustHobbyInfoExample custHobbyInfoExample = new CustHobbyInfoExample();
			CustHobbyInfoExample.Criteria custHobbyInfoCriteria = custHobbyInfoExample.createCriteria();
			custHobbyInfoCriteria.andCustBaseInfoIdEqualTo(new Long(paramMap.get("custBaseInfoId").toString()))
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			custHobbyInfoList = custHobbyInfoMapper.selectByExample(custHobbyInfoExample);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custHobbyInfoList;
	}

	/**
	 * 获取财富顾问信息
	 * 
	 * @param userId
	 * @return
	 */
	private ResultInfo getAgentInfoByUserId(Long userId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			AgentExample agentExample = new AgentExample();
			AgentExample.Criteria agentCriteria = agentExample.createCriteria();
			agentCriteria.andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			if (agentList != null && agentList.size() > 0) {
				Long agentId = agentList.get(0).getAgentId();
				resultInfo.setObj(agentId);
				resultInfo.setSuccess(true);
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("您不是财富顾问!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("系统出错!");
		}

		return resultInfo;
	}
	/**
	 * 验证录入的客户基本信息是否与自己名下的准客户/客户发生冲突
	 * @author Liwentao
	 * @param  custBaseInfoMap
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultInfo verifyCollisionWithOwnCust(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			/** 1.获取录入用户信息的财富顾问信息，并进行判断 **/
			Agent agent = new Agent();
			//若传入的agentId不为空，则根据agentId获取财富顾问信息
			if (agentId != null && !"".equals(agentId)) {   
				agent = this.agentMapper.selectByPrimaryKey(new Long(agentId));
			}else{  
				// 若传入的agentId为空，则根据loginInfo获取财富顾问信息
				resultInfo = getAgentByUserId(loginInfoObjRef.getUserId());  
				if (!resultInfo.isSuccess()){
					resultMap.put("errorCode", "01");
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
				agent = (Agent)resultInfo.getObj();
			}
			/** 获取页面传入的客户数据 **/
			//获取客户基本信息
			CustBaseInfo custBaseInfoObj = new CustBaseInfo();
			custBaseInfoObj = (CustBaseInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfoObj); 
			//获取客户其他信息
			CustOthInfo custOthInfoObj = new CustOthInfo();
			custOthInfoObj = (CustOthInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custOthInfoObj); 
			//获取客户联系信息
			CustContactInfo custContactInfoObj = new CustContactInfo();
			custContactInfoObj = (CustContactInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custContactInfoObj); 
			
			/** 2.根据客户五要素校验客户是否重复 **/
			String name = custBaseInfoObj.getChnName(); // 获取用户录入的数据
			String gender = custBaseInfoObj.getSex();
			String idType = custBaseInfoObj.getIdType();
			String idNo = custBaseInfoObj.getIdNo();
			//身份证号最后一个字母转为大写
			if (idType!=null&&!"".equals(idType)&&"1".equals(idType)) {
				idNo = idNo.toUpperCase();
			}
			Date birthday = custBaseInfoObj.getBirthday();
			//若五要素均不为空，则需跟据此五要素查询数据库
			if (name != null && !"".equals(name) && gender != null && !"".equals(gender) 
					&& idType != null && !"".equals(idType) && idNo != null && !"".equals(idNo) 
					&& birthday != null) {   
				//2.1判断是否为自己的客户
				CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();  
				CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
				custBaseInfoCriteria.andChnNameEqualTo(name)
									.andSexEqualTo(gender)
									.andIdTypeEqualTo(idType)
									.andIdNoEqualTo(idNo)
									.andBirthdayEqualTo(birthday)
									.andAgentIdEqualTo(agent.getAgentId())
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD );
				// 若客户基本信息流水号不为空,则需排除自己这条记录
				if (custBaseInfoObj.getCustBaseInfoId() != null) {  
					custBaseInfoCriteria.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
				}
				List<CustBaseInfo> existCustBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample); 
				if (existCustBaseInfoList != null && existCustBaseInfoList.size() > 0) {  
					resultInfo.setSuccess(true);
					resultInfo.setMsg("检测到您名下已存在相同身份证及名字的客户!您是否需要带出已存在客户信息?");
					resultMap.put("errorCode", "02");
					resultMap.put("resultData", JsonUtils.objectToMap(existCustBaseInfoList.get(0)));
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
				//2.2判断是否与他人签单客户是否重复
				CustBaseInfoExample othSignCustBaseInfoExample = new CustBaseInfoExample();  
				CustBaseInfoExample.Criteria othSignCustBaseInfoCriteria = othSignCustBaseInfoExample.createCriteria();
				othSignCustBaseInfoCriteria.andChnNameEqualTo(name)
									.andSexEqualTo(gender)
									.andIdTypeEqualTo(idType)
									.andIdNoEqualTo(idNo)
									.andBirthdayEqualTo(birthday)
									.andAgentIdNotEqualTo(agent.getAgentId())
									.andCustLevelEqualTo("01")
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD );
				// 若客户基本信息流水号不为空,则需排除自己这条记录
				if (custBaseInfoObj.getCustBaseInfoId() != null) {  
					othSignCustBaseInfoCriteria.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
				}
				List<CustBaseInfo> othSignExistCustBaseInfoList = custBaseInfoMapper.selectByExample(othSignCustBaseInfoExample); 
				if (othSignExistCustBaseInfoList != null && othSignExistCustBaseInfoList.size() > 0) { 
					Agent othSignAgent = agentMapper.selectByPrimaryKey(othSignExistCustBaseInfoList.get(0).getAgentId());
					resultInfo.setSuccess(true);
					resultInfo.setMsg("该客户已经归属财富顾问（"+othSignAgent.getAgentName()+"），您不能再维护此客户信息或对此客户进行交易!");
					resultMap.put("errorCode", "03");
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
				//2.3判断是否与他人准客户是否重复
				CustBaseInfoExample othPreCustBaseInfoExample = new CustBaseInfoExample();  
				CustBaseInfoExample.Criteria othPreCustBaseInfoCriteria = othPreCustBaseInfoExample.createCriteria();
				othPreCustBaseInfoCriteria.andChnNameEqualTo(name)
									.andSexEqualTo(gender)
									.andIdTypeEqualTo(idType)
									.andIdNoEqualTo(idNo)
									.andBirthdayEqualTo(birthday)
									.andAgentIdNotEqualTo(agent.getAgentId())
									.andCustLevelEqualTo("02")
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD );
				// 若客户基本信息流水号不为空,则需排除自己这条记录
				if (custBaseInfoObj.getCustBaseInfoId() != null) {  
					othPreCustBaseInfoCriteria.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
				}
				List<CustBaseInfo> othPreExistCustBaseInfoList = custBaseInfoMapper.selectByExample(othPreCustBaseInfoExample); 
				if (othPreExistCustBaseInfoList != null && othPreExistCustBaseInfoList.size() > 0) { 
					Agent othPreAgent = agentMapper.selectByPrimaryKey(othPreExistCustBaseInfoList.get(0).getAgentId());
					resultInfo.setSuccess(true);
					resultInfo.setMsg("检测到财富顾问（"+othPreAgent.getAgentName()+"）名下已存在该准客户!您是否需要继续提交?");
					resultMap.put("errorCode", "04");
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
			}
			/** 3根据身份证号校验客户是否重复 **/
			if (idType!=null&&!"".equals(idType)&&"1".equals(idType)&&idNo!=null&&!"".equals(idNo)) {
				//4.1校验是否与自己的客户重复
				CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();  
				CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
				custBaseInfoCriteria.andIdTypeEqualTo(idType)
									.andIdNoEqualTo(idNo)
									.andAgentIdEqualTo(agent.getAgentId())
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD );
				// 若客户基本信息流水号不为空,则需排除自己这条记录
				if (custBaseInfoObj.getCustBaseInfoId() != null) {  
					custBaseInfoCriteria.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
				}
				List<CustBaseInfo> existCustBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample); 
				if (existCustBaseInfoList != null && existCustBaseInfoList.size() > 0) {  
					resultInfo.setSuccess(true);
					resultInfo.setMsg("检测到您名下已存在具有相同身份证号的客户!您是否需要带出已存在客户信息?");
					resultMap.put("errorCode", "08");
					resultMap.put("resultData", JsonUtils.objectToMap(existCustBaseInfoList.get(0)));
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
				//3.2校验是否与他人签单客户重复
				CustBaseInfoExample othSignCustBaseInfoExample = new CustBaseInfoExample();  
				CustBaseInfoExample.Criteria othSignCustBaseInfoCriteria = othSignCustBaseInfoExample.createCriteria();
				othSignCustBaseInfoCriteria.andIdTypeEqualTo(idType)
									.andIdNoEqualTo(idNo)
									.andAgentIdNotEqualTo(agent.getAgentId())
									.andCustLevelEqualTo("01")
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD );
				// 若客户基本信息流水号不为空,则需排除自己这条记录
				if (custBaseInfoObj.getCustBaseInfoId() != null) {  
					othSignCustBaseInfoCriteria.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
				}
				List<CustBaseInfo> othSignExistCustBaseInfoList = custBaseInfoMapper.selectByExample(othSignCustBaseInfoExample); 
				if (othSignExistCustBaseInfoList != null && othSignExistCustBaseInfoList.size() > 0) { 
					Agent othSignAgent = agentMapper.selectByPrimaryKey(othSignExistCustBaseInfoList.get(0).getAgentId());
					resultInfo.setSuccess(true);
					resultInfo.setMsg("该客户已经归属财富顾问（"+othSignAgent.getAgentName()+"）,您不能再维护此客户信息或对此客户进行交易!");
					resultMap.put("errorCode", "09");
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
				//3.3判断是否与他人准客户是否重复
				CustBaseInfoExample othPreCustBaseInfoExample = new CustBaseInfoExample();  
				CustBaseInfoExample.Criteria othPreCustBaseInfoCriteria = othPreCustBaseInfoExample.createCriteria();
				othPreCustBaseInfoCriteria.andIdTypeEqualTo(idType)
									.andIdNoEqualTo(idNo)
									.andAgentIdNotEqualTo(agent.getAgentId())
									.andCustLevelEqualTo("02")
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD );
				// 若客户基本信息流水号不为空,则需排除自己这条记录
				if (custBaseInfoObj.getCustBaseInfoId() != null) {  
					othPreCustBaseInfoCriteria.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
				}
				List<CustBaseInfo> othPreExistCustBaseInfoList = custBaseInfoMapper.selectByExample(othPreCustBaseInfoExample); 
				if (othPreExistCustBaseInfoList != null && othPreExistCustBaseInfoList.size() > 0) { 
					Agent othPreAgent = agentMapper.selectByPrimaryKey(othPreExistCustBaseInfoList.get(0).getAgentId());
					resultInfo.setSuccess(true);
					resultInfo.setMsg("检测到财富顾问（"+othPreAgent.getAgentName()+"）名下已存在该准客户!您是否需要继续提交?");
					resultMap.put("errorCode", "10");
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
			}
			/** 4根据手机号校验客户是否重复 **/
		/*	String mobile = custContactInfoObj.getMobile();
			if(mobile!=null&&!"".equals(mobile)){
				//4.1校验是否与自己的客户重复
				Map paramMap = new HashMap();
				paramMap.put("agentId", agent.getAgentId().toString());
				paramMap.put("mobile", mobile);
				if (custBaseInfoObj.getCustBaseInfoId() != null) {
					paramMap.put("custBaseInfoId", custBaseInfoObj.getCustBaseInfoId().toString());
				}
				List<Map> custInfoMapList = modifyCustomerServiceMapper.getMyCustomerByMobile(paramMap);
				if(custInfoMapList!=null&&custInfoMapList.size()>0){
					resultInfo.setSuccess(true);
					resultInfo.setMsg("检测到您名下已存在具有相同手机号的客户!您是否需要带出已存在客户的信息?");
					resultMap.put("errorCode", "05");
					resultMap.put("resultData", custInfoMapList.get(0));
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
				//4.2校验是否与他人签单客户是否重复
				Map othSignParamMap = new HashMap<String, Object>();
				othSignParamMap.put( "agentId", agent.getAgentId());
				othSignParamMap.put( "custLevel", "01");
				othSignParamMap.put( "mobile", mobile);
				if (custBaseInfoObj.getCustBaseInfoId() != null) {
					othSignParamMap.put("custBaseInfoId", custBaseInfoObj.getCustBaseInfoId().toString());
				}
				List<Map<String, Object>> othSignCustInfoMapList = modifyCustomerServiceMapper.searchOthAgentPreCustomerByMobile(othSignParamMap);
				// 若其他理财经理的准客户的手机号存在，则说明页面录入手机号与其他理财经理的准客户的手机号发生冲突
				if (othSignCustInfoMapList != null && 0 < othSignCustInfoMapList.size()) {
					Agent othSignAgent = agentMapper.selectByPrimaryKey(new Long(othSignCustInfoMapList.get(0).get("agentId").toString()));
					resultInfo.setSuccess(true);
					resultInfo.setMsg("该客户已经归属理财经理（"+othSignAgent.getAgentName()+"），您不能再维护此客户信息或对此客户进行交易！");
					resultMap.put("errorCode", "06");
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
				//4.3校验是否与他人准客户是否重复
				Map othPreParamMap = new HashMap<String, Object>();
				othPreParamMap.put( "agentId", agent.getAgentId());
				othPreParamMap.put( "custLevel", "02");
				othPreParamMap.put( "mobile", mobile);
				if (custBaseInfoObj.getCustBaseInfoId() != null) {
					othPreParamMap.put("custBaseInfoId", custBaseInfoObj.getCustBaseInfoId().toString());
				}
				List<Map<String, Object>> othPreCustInfoMapList = modifyCustomerServiceMapper.searchOthAgentPreCustomerByMobile(othPreParamMap);
				// 若其他理财经理的准客户的手机号存在，则说明页面录入手机号与其他理财经理的准客户的手机号发生冲突
				if (othPreCustInfoMapList != null && 0 < othPreCustInfoMapList.size()) {
					Agent othPreAgent = agentMapper.selectByPrimaryKey(new Long(othPreCustInfoMapList.get(0).get("agentId").toString()));
					resultInfo.setSuccess(true);
					resultInfo.setMsg("检测到财富顾问（"+othPreAgent.getAgentName()+"）名下已存在该准客户!您是否需要继续提交?");
					resultMap.put("errorCode", "07");
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
			}*/
			/**5 校验姓名与自己名下已存在准客户是否存在冲突***/
			String nameInUser = custBaseInfoObj.getChnName();
			if (name != null && !"".equals(name)) {
				CustBaseInfoExample checkNameCollisionWithMyPreCustExample = new CustBaseInfoExample();
				CustBaseInfoExample.Criteria checkNamCollisionWithMyPreCustCriteria = checkNameCollisionWithMyPreCustExample.createCriteria();
				checkNamCollisionWithMyPreCustCriteria.andChnNameEqualTo(nameInUser)
																											.andAgentIdEqualTo(agent.getAgentId())
																											.andRcStateEqualTo(Constants.EFFECTIVE_RECORD)
																											.andCustLevelEqualTo("02");
				// 若客户基本信息流水号不为空,则需查询时排除自己这条记录
				if (custBaseInfoObj.getCustBaseInfoId() != null ) {
					checkNamCollisionWithMyPreCustCriteria.andCustBaseInfoIdNotEqualTo( custBaseInfoObj.getCustBaseInfoId() );
				}
				List<CustBaseInfo> existCustBaseInfoList = this.custBaseInfoMapper.selectByExample(checkNameCollisionWithMyPreCustExample);
				// 若根据姓名查询到其他经理名下已存在同名准客户
				if (existCustBaseInfoList != null &&  existCustBaseInfoList.size() > 0 ) {
					resultMap.put( "errorCode", "11" );
					resultInfo.setSuccess(true);
					resultInfo.setMsg("检测到您名下已存在同名准客户!您是否需要继续提交?");
					resultInfo.setObj(resultMap);
					return resultInfo;
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(exception.getMessage());
			return  resultInfo;
		}	
		resultInfo.setSuccess(true);
		resultMap.put("errorCode", "00");
		resultInfo.setObj(resultMap);
		return resultInfo;
	}
	/**
	 * 验证用户录入的客户基本信息是否与其他理财经理签单客户发生冲突
	 * @author Liwentao
	 * @param  custBaseInfoMap
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultInfo verifyCollisionWithOthAgentCust(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef) {
		ResultInfo resultInfo = new ResultInfo();
		List<ResultInfo>  resultInfosList = new ArrayList<ResultInfo>();
		try {
						ResultInfo  resultInfoOne = new ResultInfo();// 新分配对象用于存储返回数据
						/** 获取录入用户信息的财富顾问信息，并进行判断 **/
						Agent agent = new Agent();
						if (agentId != null && !"".equals(agentId)) {   // 若传入的agentId不为空，则根据agentId获取财富顾问信息
							agent = this.agentMapper.selectByPrimaryKey(new Long(agentId));
						} 
						else
						{  
							resultInfoOne = getAgentByUserId(loginInfoObjRef.getUserId());  // 若传入的agentId为空，则根据loginInfo获取财富顾问信息
							if ( !resultInfoOne.isSuccess() ) {
								resultInfosList.add(resultInfoOne);
								resultInfo.setObj(resultInfosList);
								return resultInfo;
							}
							agent = (Agent) resultInfoOne.getObj();
							resultInfoOne.setObj(null);
						}
						resultInfosList.add(resultInfoOne);
						/** Json字符串 **/
						CustBaseInfo custBaseInfoObj = new CustBaseInfo();
						custBaseInfoObj = (CustBaseInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfoObj); // Json字符串转为CustBaseInfo对象
						CustOthInfo custOthInfoObj = new CustOthInfo();
						custOthInfoObj = (CustOthInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custOthInfoObj);  // Json字符串转为CustOthInfo对象
						CustContactInfo custContactInfoObj = new CustContactInfo();
						custContactInfoObj = (CustContactInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custContactInfoObj); // Json字符串转为CustContactInfo对象
						/** 若五要素不为空，则验证五要素*/
						ResultInfo resultInfoFiveEle = new ResultInfo();
						String name = custBaseInfoObj.getChnName(); // 获取用户录入的数据
						String gender = custBaseInfoObj.getSex();
						String idType = custBaseInfoObj.getIdType();
						String idNo = custBaseInfoObj.getIdNo();
						java.util.Date birthdate = custBaseInfoObj.getBirthday();
						if (name != null && !"".equals(name) && gender != null && !"".equals(gender) && idType != null && !"".equals(idType) && idNo != null && !"".equals(idNo) && birthdate != null&& !"".equals(birthdate)) {   // 若五要素均不为空，则需跟据此五要素查询数据库
							CustBaseInfoExample custBaseInfoExample2 = new CustBaseInfoExample();  // 设置查询条件为客户输入的五要素
							CustBaseInfoExample.Criteria custBaseInfoCriteria2 = custBaseInfoExample2.createCriteria();
							custBaseInfoCriteria2.andChnNameEqualTo(name).andSexEqualTo(gender).andIdTypeEqualTo(idType).andIdNoEqualTo(idNo).andBirthdayEqualTo( custBaseInfoObj.getBirthday() ).andAgentIdNotEqualTo( agent.getAgentId() ).andCustLevelEqualTo("01").andRcStateEqualTo( Constants.EFFECTIVE_RECORD );
							if ( custBaseInfoObj.getCustBaseInfoId() != null &&  !"".equals( custBaseInfoObj.getCustBaseInfoId()) ) {  // 若客户基本信息流水号不为空,则需排除自己这条记录
								custBaseInfoCriteria2.andCustBaseInfoIdNotEqualTo( custBaseInfoObj.getCustBaseInfoId() );
							}
							List<CustBaseInfo> custBaseInfos2 = this.custBaseInfoMapper.selectByExample(custBaseInfoExample2);  // 查询
							if (custBaseInfos2 != null && custBaseInfos2.size() > 0) {  // 判断
								resultInfoFiveEle.setSuccess(true);
								resultInfoFiveEle.setMsg("您提交客户信息与其他理财经理名下的签单客户信息发生冲突!您不能提交该客户信息");
								resultInfosList.add(resultInfoFiveEle);  // 将冲突结果添加到列表
								resultInfo.setObj( resultInfosList ); // 直接返回不再判断其他情况
								return resultInfo;
							}
						}
						resultInfosList.add(resultInfoFiveEle);
						/**若手机号不为空，则验证手机号**/
						ResultInfo resultInfoMobile = new ResultInfo();
						/** 如果用户提交的客户或准客户手机号不为空，则需对手机号进行判断是否与自己名下其他客户手机号产生冲突 **/
						if ( custContactInfoObj.getMobile() != null && !"".equals(custContactInfoObj.getMobile()) ) {
							// 当前理财师Id, 准客户级别,手机号
							Map paramMap = new HashMap<String, Object>();
							paramMap.put( "agentId", agent.getAgentId() );
							paramMap.put( "custLevel", "01");
							paramMap.put( "mobile", custContactInfoObj.getMobile());
							// 查询其他理财经理的准客户的手机号
							List<Map<String, Object>> resultInfoData = this.modifyCustomerServiceMapper.searchOthAgentPreCustomerByMobile(paramMap);
							// 若其他理财经理的准客户的手机号存在，则说明页面录入手机号与其他理财经理的准客户的手机号发生冲突
							if ((resultInfoData != null) && (0 < resultInfoData.size())) {
									resultInfoMobile.setSuccess(true);
									resultInfoMobile.setMsg("您提交的客户手机号与他人名下签单客户的手机号相同!您不能提交该客户信息");
									resultInfosList.add(resultInfoMobile);  // 将冲突结果添加到列表
									resultInfo.setObj( resultInfosList ); // 将返回ResultInfo对象中属性对象属性设置为列表resultInfosList
									return resultInfo;
							}
						}
						resultInfosList.add(resultInfoMobile);
						/**若证件类型为身份证且不为空,则验证身份证号 **/
						ResultInfo resultInfoIdNo = new ResultInfo();
						if (custBaseInfoObj.getIdType() != null && !"".equals(custBaseInfoObj.getIdType()) && custBaseInfoObj.getIdNo() != null && !"".equals(custBaseInfoObj.getIdNo())) {
							if ("1".equals(custBaseInfoObj.getIdType())) {
								CustBaseInfoExample custBaseInfoExampleIdNo = new CustBaseInfoExample(); // 设置查询条件为姓名
								CustBaseInfoExample.Criteria criteriaIdNo = custBaseInfoExampleIdNo.createCriteria();
								criteriaIdNo.andIdNoEqualTo(custBaseInfoObj.getIdNo()).andIdTypeEqualTo(custBaseInfoObj.getIdType()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andAgentIdNotEqualTo(agent.getAgentId()).andCustLevelEqualTo("01");
								if ( custBaseInfoObj.getCustBaseInfoId() != null && !"".equals(custBaseInfoObj.getCustBaseInfoId())) { // 如果客户基本信息流水号不为空，则查询除去该条记录的其他数据
									criteriaIdNo.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
								}
								List<CustBaseInfo> custBaseInfoIdList = custBaseInfoMapper.selectByExample(custBaseInfoExampleIdNo);
								if ((custBaseInfoIdList != null) && (0 < custBaseInfoIdList.size())) {
									resultInfoIdNo.setSuccess(true);
									resultInfoIdNo.setMsg("您提交的身份证号与他人名下签单客户的身份证号相同!您不能提交该客户信息!");
									resultInfosList.add(resultInfoIdNo);  // 将冲突结果添加到列表
									resultInfo.setObj( resultInfosList ); // 直接返回不再判断其他情况
									return resultInfo;
								}
							}
						}
						resultInfosList.add(resultInfoIdNo);
						/**若姓名与手机号同时不为空,则验证姓名与手机号 **/
						/*ResultInfo resultInfoMobAndName = new ResultInfo();
						if ( custContactInfoObj.getMobile() != null && !"".equals(custContactInfoObj.getMobile()) ) {
							Map nameAndMobileMap = new HashMap();
							nameAndMobileMap.put( "chnName", custBaseInfoObj.getChnName() );
							nameAndMobileMap.put( "mobile", custContactInfoObj.getMobile() );
							nameAndMobileMap.put( "agentId", agent.getAgentId() );
							nameAndMobileMap.put("custLevel", "01");
							if ( custBaseInfoObj.getCustBaseInfoId() != null  && !"".equals(custBaseInfoObj.getCustBaseInfoId()) ) {
								nameAndMobileMap.put( "custBaseInfoId",  custBaseInfoObj.getCustBaseInfoId() );
							}
							List<Map<String, Object>> resultList = this.modifyCustomerServiceMapper.searchOthAgentCustomer(nameAndMobileMap);
							if (resultList != null && 0 < resultList.size()) {
								resultInfoMobAndName.setSuccess(true);
								resultInfoMobAndName.setMsg("您提交的客户姓名与手机号与他人名下签单客户发生冲突！您不能提交该信息或修改后再提交");
								resultInfosList.add(resultInfoMobAndName);  // 将冲突结果添加到列表
								resultInfo.setObj( resultInfosList ); // 直接返回不再判断其他情况
								return resultInfo;
							}
						}
						resultInfosList.add(resultInfoMobAndName);*/
						/**若姓名不为空，则验证姓名*/
						/*ResultInfo resultInfoName = new ResultInfo();
						CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample(); // 设置查询条件为姓名
						CustBaseInfoExample.Criteria criteria = custBaseInfoExample.createCriteria();
						criteria.andChnNameEqualTo( custBaseInfoObj.getChnName() ).andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andAgentIdNotEqualTo(agent.getAgentId()).andCustLevelEqualTo("01");
						//criteria.andAgentIdEqualTo(agent.getAgentId());
						if ( custBaseInfoObj.getCustBaseInfoId() != null &&  !"".equals(custBaseInfoObj.getCustBaseInfoId()) ) { // 如果客户基本信息流水号不为空，则查询除去该条记录的其他数据
							criteria.andCustBaseInfoIdNotEqualTo( custBaseInfoObj.getCustBaseInfoId() ); 
						}
						List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample( custBaseInfoExample );
						if ((custBaseInfoList != null) && (0 < custBaseInfoList.size())) { // 判断返回条数
							resultInfoName.setSuccess(true);
							resultInfoName.setMsg("您提交的客户姓名与他人名下签单客户发生冲突!您可以点击【确定】继续录入或者点击【取消】返回页面");
							resultInfosList.add(resultInfoName);  // 将冲突结果添加到列表
							resultInfo.setObj( resultInfosList ); // 直接返回不再判断其他情况
							return resultInfo;
						}
						resultInfosList.add(resultInfoName);*/
		} catch (Exception e) {
						resultInfo.setMsg("验证您录入的客户信息时出错");
						resultInfo.setSuccess(true);
						return resultInfo;
		}
		resultInfo.setObj(resultInfosList);
		return  resultInfo;
	}
	/**
	 * 验证用户录入的客户基本信息是否与其他理财经理准客户发生冲突
	 * @author Liwentao
	 * @param  custBaseInfoMap
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultInfo verifyCollisionWithOthPreAgentCust(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef) {
		ResultInfo resultInfo = new ResultInfo();
		List<ResultInfo>  resultInfosList = new ArrayList<ResultInfo>();
		try {
						ResultInfo  resultInfoOne = new ResultInfo();// 新分配对象用于存储返回数据
						/** 获取录入用户信息的财富顾问信息，并进行判断 **/
						Agent agent = new Agent();
						if (agentId != null && !"".equals(agentId)) {   // 若传入的agentId不为空，则根据agentId获取财富顾问信息
							agent = this.agentMapper.selectByPrimaryKey(new Long(agentId));
						} 
						else
						{  
							resultInfoOne = getAgentByUserId(loginInfoObjRef.getUserId());  // 若传入的agentId为空，则根据loginInfo获取财富顾问信息
							if ( !resultInfoOne.isSuccess() ) {
								resultInfosList.add(resultInfoOne);
								resultInfo.setObj(resultInfosList);
								return resultInfo;
							}
							agent = (Agent) resultInfoOne.getObj();
							resultInfoOne.setObj(null);
						}
						resultInfosList.add(resultInfoOne);
						/** Json字符串 **/
						CustBaseInfo custBaseInfoObj = new CustBaseInfo();
						custBaseInfoObj = (CustBaseInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfoObj); // Json字符串转为CustBaseInfo对象
						CustOthInfo custOthInfoObj = new CustOthInfo();
						custOthInfoObj = (CustOthInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custOthInfoObj);  // Json字符串转为CustOthInfo对象
						CustContactInfo custContactInfoObj = new CustContactInfo();
						custContactInfoObj = (CustContactInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custContactInfoObj); // Json字符串转为CustContactInfo对象
						/** 若五要素不为空，则验证五要素*/
						ResultInfo resultInfoFiveEle = new ResultInfo();
						String name = custBaseInfoObj.getChnName(); // 获取用户录入的数据
						String gender = custBaseInfoObj.getSex();
						String idType = custBaseInfoObj.getIdType();
						String idNo = custBaseInfoObj.getIdNo();
						java.util.Date birthdate = custBaseInfoObj.getBirthday();
						if (name != null && !"".equals(name) && gender != null && !"".equals(gender) && idType != null && !"".equals(idType) && idNo != null && !"".equals(idNo) && birthdate != null&& !"".equals(birthdate)) {   // 若五要素均不为空，则需跟据此五要素查询数据库
							CustBaseInfoExample custBaseInfoExample2 = new CustBaseInfoExample();  // 设置查询条件为客户输入的五要素
							CustBaseInfoExample.Criteria custBaseInfoCriteria2 = custBaseInfoExample2.createCriteria();
							custBaseInfoCriteria2.andChnNameEqualTo(name).andSexEqualTo(gender).andIdTypeEqualTo(idType).andIdNoEqualTo(idNo).andBirthdayEqualTo( custBaseInfoObj.getBirthday() ).andAgentIdNotEqualTo( agent.getAgentId() ).andCustLevelEqualTo("02").andRcStateEqualTo( Constants.EFFECTIVE_RECORD );
							if ( custBaseInfoObj.getCustBaseInfoId() != null &&  !"".equals( custBaseInfoObj.getCustBaseInfoId()) ) {  // 若客户基本信息流水号不为空,则需排除自己这条记录
								custBaseInfoCriteria2.andCustBaseInfoIdNotEqualTo( custBaseInfoObj.getCustBaseInfoId() );
							}
							List<CustBaseInfo> custBaseInfos2 = this.custBaseInfoMapper.selectByExample(custBaseInfoExample2);  // 查询
							if (custBaseInfos2 != null && custBaseInfos2.size() > 0) {  // 判断
								resultInfoFiveEle.setSuccess(true);
								resultInfoFiveEle.setMsg("您提交的五要素信息与他人名下准客户的五要素发生冲突!点击【确定】继续提交,点击【取消】则放弃提交");
							}
						}
						resultInfosList.add(resultInfoFiveEle);  // 五要素验证结果添加到列表
						/**若手机号不为空，则验证手机号是否与其他理财经理名下的准客户的手机号发生冲突**/
						ResultInfo resultInfoMobile = new ResultInfo();
						if ( custContactInfoObj.getMobile() != null && !"".equals(custContactInfoObj.getMobile()) ) {
							/*CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
							CustContactInfoExample.Criteria custConCriteria = custContactInfoExample.createCriteria();
							custConCriteria.andMobileEqualTo(custContactInfoObj.getMobile()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andAgentIdNotEqualTo(agent.getAgentId());
							if (custBaseInfoObj.getCustBaseInfoId() != null && !"".equals(custBaseInfoObj.getCustBaseInfoId())) {
								custConCriteria.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
							}*/
							// 当前理财师Id, 准客户级别,手机号
							Map paramMap = new HashMap<String, Object>();
							paramMap.put( "agentId", agent.getAgentId() );
							paramMap.put( "custLevel", "02");
							paramMap.put( "mobile", custContactInfoObj.getMobile());
							// 查询其他理财经理的准客户的手机号
							List<Map<String, Object>> resultInfoData = this.modifyCustomerServiceMapper.searchOthAgentPreCustomerByMobile(paramMap);
							// 若其他理财经理的准客户的手机号存在，则说明页面录入手机号与其他理财经理的准客户的手机号发生冲突
							if ((resultInfoData != null) && (0 < resultInfoData.size())) {
									resultInfoMobile.setSuccess(true);
									resultInfoMobile.setMsg("您提交的客户手机号与他人名下准客户的手机号相同!点击【确定】继续提交,点击【取消】则放弃提交");
							}
						}
						resultInfosList.add(resultInfoMobile); // 手机号验证结果添加到列表
						/**若证件类型为身份证且不为空,则验证身份证号 **/
						ResultInfo resultInfoIdNo = new ResultInfo();
						if (custBaseInfoObj.getIdType() != null && !"".equals(custBaseInfoObj.getIdType()) && custBaseInfoObj.getIdNo() != null && !"".equals(custBaseInfoObj.getIdNo())) {
							if ("1".equals(custBaseInfoObj.getIdType())) {
								CustBaseInfoExample custBaseInfoExampleIdNo = new CustBaseInfoExample(); // 设置查询条件为姓名
								CustBaseInfoExample.Criteria criteriaIdNo = custBaseInfoExampleIdNo.createCriteria();
								criteriaIdNo.andIdNoEqualTo(custBaseInfoObj.getIdNo()).andIdTypeEqualTo(custBaseInfoObj.getIdType()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andAgentIdNotEqualTo(agent.getAgentId()).andCustLevelEqualTo("02");
								if ( custBaseInfoObj.getCustBaseInfoId() != null && !"".equals(custBaseInfoObj.getCustBaseInfoId())) { // 如果客户基本信息流水号不为空，则查询除去该条记录的其他数据
									criteriaIdNo.andCustBaseInfoIdNotEqualTo(custBaseInfoObj.getCustBaseInfoId());
								}
								List<CustBaseInfo> custBaseInfoIdList = custBaseInfoMapper.selectByExample(custBaseInfoExampleIdNo);
								if ((custBaseInfoIdList != null) && (0 < custBaseInfoIdList.size())) {
									resultInfoIdNo.setSuccess(true);
									resultInfoIdNo.setMsg("您提交的身份证号与他人名下准客户的身份证号相同!点击【确定】继续提交,点击【取消】则放弃提交");
									//resultInfosList.add(resultInfoIdNo);  // 将冲突结果添加到列表
									//resultInfo.setObj( resultInfosList ); // 直接返回不再判断其他情况
									//return resultInfo;
								}
							}
						}
						resultInfosList.add(resultInfoIdNo); // 身份证号验证结果添加到列表
						/**若姓名与手机号同时不为空,则验证姓名与手机号 **/
						/*ResultInfo resultInfoMobAndName = new ResultInfo();
						if ( custContactInfoObj.getMobile() != null && !"".equals(custContactInfoObj.getMobile()) ) {
							Map nameAndMobileMap = new HashMap();
							nameAndMobileMap.put( "chnName", custBaseInfoObj.getChnName() );
							nameAndMobileMap.put( "mobile", custContactInfoObj.getMobile() );
							nameAndMobileMap.put( "agentId", agent.getAgentId() );
							nameAndMobileMap.put("custLevel", "02");
							if ( custBaseInfoObj.getCustBaseInfoId() != null  && !"".equals(custBaseInfoObj.getCustBaseInfoId()) ) {
								nameAndMobileMap.put( "custBaseInfoId",  custBaseInfoObj.getCustBaseInfoId() );
							}
							List<Map<String, Object>> resultList = this.modifyCustomerServiceMapper.searchOthAgentCustomer(nameAndMobileMap);
							if (resultList != null && 0 < resultList.size()) {
								resultInfoMobAndName.setSuccess(true);
								resultInfoMobAndName.setMsg("您提交的客户姓名与手机号与其他理财经理名下的准客户发生冲突!点击【确定】继续提交,点击【取消】则放弃提交");
							}
						}
						resultInfosList.add(resultInfoMobAndName); */
						/**若姓名不为空，则验证其他理财经理名下的准客户姓名是否已存在*/
						/*ResultInfo resultInfoName = new ResultInfo();
						CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample(); // 设置查询条件为姓名
						CustBaseInfoExample.Criteria criteria = custBaseInfoExample.createCriteria();
						criteria.andChnNameEqualTo( custBaseInfoObj.getChnName() ).andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andAgentIdNotEqualTo(agent.getAgentId()).andCustLevelEqualTo("02");
						//criteria.andAgentIdEqualTo(agent.getAgentId());   失误
						if ( custBaseInfoObj.getCustBaseInfoId() != null &&  !"".equals(custBaseInfoObj.getCustBaseInfoId()) ) { // 如果客户基本信息流水号不为空，则查询除去该条记录的其他数据
							criteria.andCustBaseInfoIdNotEqualTo( custBaseInfoObj.getCustBaseInfoId() ); 
						}
						List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample( custBaseInfoExample );
						if ((custBaseInfoList != null) && (0 < custBaseInfoList.size())) { // 判断返回条数
							resultInfoName.setSuccess(true);
							resultInfoName.setMsg("您提交的客户姓名与其他理财经理名下的准客户的姓名发生冲突!您可以点击【确定】继续录入或者点击【取消】返回页面");
							//resultInfosList.add(resultInfoName);  // 将冲突结果添加到列表
							//resultInfo.setObj( resultInfosList ); // 直接返回不再判断其他情况
							//return resultInfo;
						}
						resultInfosList.add(resultInfoName); //姓名验证结果添加到列表*/
			} catch (Exception e) {
						resultInfo.setMsg("验证您录入的客户信息时出错");
						resultInfo.setSuccess(true);
						return resultInfo;
		}
		resultInfo.setObj(resultInfosList);
		return  resultInfo;
	}
	/**
	 * 校验客户是否保存客户地址和账户信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo checkCustAdressAndAccInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if(paramMap!=null){
				// 获取客户基本信息流水号
				Long custBaseInfoId = new Long(paramMap.get("custBaseInfoId").toString());
				// 获取理财师基本信息流水号
				Long agentId = new Long(paramMap.get("agentId").toString());
				// 获取交易产品流水号Id
				Long tradeId = new Long( paramMap.get("tradeId").toString() );
				// 根据交易流水号查询该交易产品所属类型
				TradeInfo tradeInfo = this.tradeInfoMapper.selectByPrimaryKey(tradeId);
				// 交易产品类型为财富产品，则需验证客户账户信息
				List<CustAccInfo> custAccInfoList = null;
				if ( tradeInfo.getTradeType() != null && "1".equals(tradeInfo.getTradeType()) ) {
					CustAccInfoExample custAccInfoExample = new CustAccInfoExample();
					CustAccInfoExample.Criteria custAccInfoCriteria = custAccInfoExample.createCriteria();
					custAccInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId)
					                   .andAgentIdEqualTo(agentId)
					                   .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					custAccInfoList = custAccInfoMapper.selectByExample(custAccInfoExample);
				}
				// 验证客户地址信息
				CustAddressInfoExample custAddressInfoExample = new CustAddressInfoExample();
				CustAddressInfoExample.Criteria custAddressInfoCriteria = custAddressInfoExample.createCriteria();
				custAddressInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId)
				                       .andAgentIdEqualTo(agentId)
				                       .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustAddressInfo> custAddressInfoList = custAddressInfoMapper.selectByExample(custAddressInfoExample);
				// 若交易产品类型为财富产品，则同时判断地址信息和账户信息
				if ( tradeInfo.getTradeType() != null && "1".equals(tradeInfo.getTradeType()) ) {
					if(custAddressInfoList.size()>0&&custAccInfoList.size()>0){
						resultInfo.setSuccess(true);
						resultInfo.setMsg("校验通过！");
					}else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("请先保存客户地址信息和账户信息！");
					}
				} 
				// 若交易产品类型为保险产品，则只判断地址信息
				else {
					if(custAddressInfoList.size()>0){
						resultInfo.setSuccess(true);
						resultInfo.setMsg("校验通过！");
					}else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("请先保存客户地址信息和账户信息！");
					}
				}
				
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("校验客户是否保存客户地址和账户信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("校验客户是否保存客户地址和账户信息出现异常！");
		}
		return resultInfo;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo checkTradeInput(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null&&!"".equals(param)){
				    resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
				    Long agentId = (Long) resultInfo.getObj();
				    Long custBaseInfoId = new Long(param);
					CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
					CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();
					CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
					// 判断五要素是否为空
					if (custBaseInfo.getIdType() == null || "".equals(custBaseInfo.getIdType())) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("请先提交客户基本信息后再返回");
						return resultInfo;
					}
					if (custBaseInfo.getIdNo() == null || "".equals( custBaseInfo.getIdNo() )) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("请先提交客户基本信息后再返回");
						return resultInfo;
					}
					if (custBaseInfo.getSex() == null || "".equals(custBaseInfo.getSex())) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("请先提交客户基本信息后再返回");
						return resultInfo;
					}
					if (custBaseInfo.getBirthday() == null || "".equals(custBaseInfo.getBirthday())) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("请先提交客户基本信息后再返回");
						return resultInfo;
					}
					custBaseInfoCriteria.andChnNameEqualTo(custBaseInfo.getChnName())
					                    .andIdTypeEqualTo(custBaseInfo.getIdType())
					                    .andIdNoEqualTo(custBaseInfo.getIdNo())
					                    .andSexEqualTo(custBaseInfo.getSex())
					                    .andBirthdayEqualTo(custBaseInfo.getBirthday())
					                    .andAgentIdNotEqualTo(agentId)
					                    .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample);
					if(custBaseInfoList!=null&&custBaseInfoList.size()>0){
						String customerNo = custBaseInfoList.get(0).getCustomerNo();
						paramMap.put("customerNo", customerNo);
						Integer checkTradeInputCount = tradeServiceMapper.getCheckTradeInputCount(paramMap);
						if(checkTradeInputCount>0){
							resultInfo.setSuccess(false);
							resultInfo.setMsg("该客户在其他经理处已经存在交易,无法进行交易录入!");
						}else {
							resultInfo.setSuccess(true);
							resultInfo.setMsg("校验通过！");
						}
					}else{
						resultInfo.setSuccess(true);
						resultInfo.setMsg("校验通过！");
					}
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("前台传入的客户id为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}


	@SuppressWarnings({ "rawtypes", "unused"})
	@Override
	public ResultInfo insertCustBaseInfo(Map custInfoBaseInfoMap) {
		ResultInfo resultInfo = new ResultInfo();
	    try {
	 	    String mobile = custInfoBaseInfoMap.get("mobile").toString();
	 	    String idNo =  custInfoBaseInfoMap.get("cardNo").toString().toUpperCase();
	 	    String sex = StringUtils.getSex(idNo);
	 	    String birthday = StringUtils.getBirthday(idNo);
	 	    String idType = "1";
	 	    String chnName = custInfoBaseInfoMap.get("customerName").toString();
			String agentCode = custInfoBaseInfoMap.get("agentCode").toString();
			if (agentCode.equals("99999")) {
				DefCodeExample defCodeExample = new DefCodeExample();
				DefCodeExample.Criteria defCodeCriteria = defCodeExample.createCriteria();
				defCodeCriteria.andCodeTypeEqualTo("defaultAgent");
				List<DefCode> defCodes = defCodeMapper.selectByExample(defCodeExample);
				agentCode = defCodes.get(0).getCode();
			}
			LoginInfo loginInfo = new LoginInfo();
			AgentExample agentExample = new AgentExample();
			AgentExample.Criteria agentCriteria = agentExample.createCriteria();
			agentCriteria.andAgentCodeEqualTo(agentCode).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			if (agentList == null || agentList.size() == 0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("系统不存在该理财师工号对应的财富顾问！");
				return resultInfo;
			}
			Long agentId = agentList.get(0).getAgentId();
			Long userId = agentMapper.selectByExample(agentExample).get(0).getUserId();
			Long comId = agentMapper.selectByExample(agentExample).get(0).getComId();
			loginInfo.setUserId(userId);
			loginInfo.setComId(comId);
	    	CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();
	    	CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
	    	custBaseInfoCriteria.andIdNoEqualTo(idNo).andCustLevelEqualTo("01")
	    	                    .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
	    	CustBaseInfo custBaseInfo = new CustBaseInfo();
	    	List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample);
	    	if(custBaseInfoList!=null&&custBaseInfoList.size()>0){
	    		custBaseInfo = custBaseInfoList.get(0);
	    		Long custBaseInfoId = custBaseInfo.getCustBaseInfoId();
	    		CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
	    		CustContactInfoExample.Criteria custContactInfoCriteria = custContactInfoExample.createCriteria();
	    		custContactInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
	    		                       .andMobileEqualTo(mobile).andAgentIdEqualTo(agentId)
	    		                       .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
	    		List<CustContactInfo> custContactInfoList = custContactInfoMapper.selectByExample(custContactInfoExample);
	    		if(custContactInfoList!=null&&custContactInfoList.size()>0){
	    			//核心存在这样的签单客户，更新地址和账户信息,联系信息
	    			custBaseInfo.setIdValidityDate(DateUtils.getDate(custInfoBaseInfoMap.get("cardNoDate").toString()));
	    			BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
	    			custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
	    			resultInfo = saveCustomerOherInfo(custInfoBaseInfoMap, custBaseInfoId, agentId, loginInfo);
	    		}else {
					//核心存在身份证一样的签单客户，但是手机号码不一致，无法同步
	    			resultInfo.setSuccess(false);
	    			resultInfo.setMsg("核心存在身份证一样的签单客户，但是手机号码不一致，无法同步!");
	    			return resultInfo;
				}
	    	}else {
				CustBaseInfoExample preCustBaseInfoExample = new CustBaseInfoExample();
				CustBaseInfoExample.Criteria preCustBaseInfoCriteria = preCustBaseInfoExample.createCriteria();
				preCustBaseInfoCriteria.andIdNoEqualTo(idNo).andAgentIdEqualTo(agentId).andCustLevelEqualTo("02")
				                       .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustBaseInfo> preCustBaseInfoList = custBaseInfoMapper.selectByExample(preCustBaseInfoExample);
				CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
				CustContactInfoExample.Criteria custContactInfoCriteria = custContactInfoExample.createCriteria();
				custContactInfoCriteria.andAgentIdEqualTo(agentId).andMobileEqualTo(mobile)
				                       .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustContactInfo> custContactInfoList = custContactInfoMapper.selectByExample(custContactInfoExample);
				if(preCustBaseInfoList!=null&&preCustBaseInfoList.size()>0){
					Long custBaseInfoId = preCustBaseInfoList.get(0).getCustBaseInfoId();
					CustContactInfoExample preCustContactInfoExample = new CustContactInfoExample();
					CustContactInfoExample.Criteria preCustContactInfoCriteria = preCustContactInfoExample.createCriteria();
					preCustContactInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId)
					                          .andAgentIdEqualTo(agentId).andMobileEqualTo(mobile)
					                          .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<CustContactInfo> preCustContactInfoList = custContactInfoMapper.selectByExample(preCustContactInfoExample);
					if(preCustContactInfoList!=null&&preCustContactInfoList.size()>0){
						custBaseInfo = preCustBaseInfoList.get(0);
						//该客户在核心是准客户，需要更新信息
			    		custBaseInfo.setChnName(custInfoBaseInfoMap.get("customerName").toString());
						custBaseInfo.setIdType("1");
						custBaseInfo.setSex(StringUtils.getSex(idNo));
						custBaseInfo.setBirthday(DateUtils.getDate1(StringUtils.getBirthday(idNo)));
						custBaseInfo.setIdValidityDate(DateUtils.getDate(custInfoBaseInfoMap.get("cardNoDate").toString()));
						custBaseInfo.setCustObtainWay("07");
						BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
						custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
						resultInfo = saveCustomerOherInfo(custInfoBaseInfoMap, custBaseInfoId, agentId, loginInfo);
					}else {
						CustContactInfoExample custContactInfoByIdNoExample = new CustContactInfoExample();
						CustContactInfoExample.Criteria custContactInfoByIdNoCriteria = custContactInfoByIdNoExample.createCriteria();
						custContactInfoByIdNoCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId).andAgentIdEqualTo(agentId)
						                       .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						List<CustContactInfo> custContactInfoByIdNoList = custContactInfoMapper.selectByExample(custContactInfoByIdNoExample);
						if(custContactInfoByIdNoList!=null&&custContactInfoByIdNoList.size()>0){
							if(custContactInfoByIdNoList.get(0).getMobile()!=null){
								resultInfo.setSuccess(false);
								resultInfo.setMsg("核心存在这样的客户，但是电话号码不一致，无法同步！");
								return resultInfo;
							}else {
								custBaseInfo = preCustBaseInfoList.get(0);
								custBaseInfo.setChnName(custInfoBaseInfoMap.get("customerName").toString());
								custBaseInfo.setIdType("1");
								custBaseInfo.setSex(StringUtils.getSex(idNo));
								custBaseInfo.setBirthday(DateUtils.getDate1(StringUtils.getBirthday(idNo)));
								custBaseInfo.setIdValidityDate(DateUtils.getDate(custInfoBaseInfoMap.get("cardNoDate").toString()));
								custBaseInfo.setCustObtainWay("07");
								BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
								custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
								resultInfo = saveCustomerOherInfo(custInfoBaseInfoMap, custBaseInfoId, agentId, loginInfo);
							}
						}else {
							//没有联系信息，直接更新
							custBaseInfo = preCustBaseInfoList.get(0);
							custBaseInfo.setChnName(custInfoBaseInfoMap.get("customerName").toString());
							custBaseInfo.setIdType("1");
							custBaseInfo.setSex(StringUtils.getSex(idNo));
							custBaseInfo.setBirthday(DateUtils.getDate1(StringUtils.getBirthday(idNo)));
							custBaseInfo.setIdValidityDate(DateUtils.getDate(custInfoBaseInfoMap.get("cardNoDate").toString()));
							custBaseInfo.setCustObtainWay("07");
							BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
							custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
							resultInfo = saveCustomerOherInfo(custInfoBaseInfoMap, custBaseInfoId, agentId, loginInfo);
						}
					}
				}else if (custContactInfoList!=null&&custContactInfoList.size()>0) {
					Long custBaseInfoIdByMobile = custContactInfoList.get(0).getCustBaseInfoId();
					CustBaseInfoExample custBaseInfoByMobileExample = new CustBaseInfoExample();
					CustBaseInfoExample.Criteria custBaseInfoByMobileCriteria = custBaseInfoByMobileExample.createCriteria();
					custBaseInfoByMobileCriteria.andCustBaseInfoIdEqualTo(custBaseInfoIdByMobile)
					                            .andAgentIdEqualTo(agentId).andCustLevelEqualTo("02")
					                            .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<CustBaseInfo> custBaseInfoByMobileList = custBaseInfoMapper.selectByExample(custBaseInfoByMobileExample);
					if(custBaseInfoByMobileList!=null&&custBaseInfoByMobileList.size()>0){
						custBaseInfo = custBaseInfoByMobileList.get(0);
						if(custBaseInfo.getIdNo()==null){
							//没有身份证信息，直接更新
							custBaseInfo.setIdNo(idNo);
							custBaseInfo.setChnName(custInfoBaseInfoMap.get("customerName").toString());
							custBaseInfo.setIdType("1");
							custBaseInfo.setSex(StringUtils.getSex(idNo));
							custBaseInfo.setBirthday(DateUtils.getDate1(StringUtils.getBirthday(idNo)));
							custBaseInfo.setIdValidityDate(DateUtils.getDate(custInfoBaseInfoMap.get("cardNoDate").toString()));
							custBaseInfo.setCustObtainWay("07");
							BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
							custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
							resultInfo = saveCustomerOherInfo(custInfoBaseInfoMap, custBaseInfoIdByMobile, agentId, loginInfo);
						}else{
							resultInfo.setSuccess(false);
							resultInfo.setMsg("核心存在电话相同，但身份证号不同的客户，无法同步！");
							return resultInfo;
						}
					}
				}else {
					//核心不存在这样的客户，直接新增
					// 1，生成客户Id
					Long custBaseInfoId = commonService.getSeqValByName("SEQ_T_CUST_BASE_INFO");
					// 2.生成客户号
					String customerNo = commonService.createCustomerNo();
		    		custBaseInfo.setCustBaseInfoId(custBaseInfoId);
		    		custBaseInfo.setCustomerNo(customerNo);
		    		custBaseInfo.setCustLevel("02");
		    		custBaseInfo.setChnName(custInfoBaseInfoMap.get("customerName").toString());
					custBaseInfo.setIdType("1");
					custBaseInfo.setIdNo(idNo);
					custBaseInfo.setSex(StringUtils.getSex(idNo));
					custBaseInfo.setBirthday(DateUtils.getDate1(StringUtils.getBirthday(idNo)));
					custBaseInfo.setAgentId(agentId);
					custBaseInfo.setIdValidityDate(DateUtils.getDate(custInfoBaseInfoMap.get("cardNoDate").toString()));
					custBaseInfo.setCustObtainWay("07");
					BeanUtils.insertObjectSetOperateInfo(custBaseInfo, loginInfo);
					custBaseInfoMapper.insert(custBaseInfo);
					//保存客户其他信息
					resultInfo = saveCustomerOherInfo(custInfoBaseInfoMap, custBaseInfoId, agentId, loginInfo);
				}
			}
	    	String customerNo = custBaseInfo.getCustomerNo();
	    	resultInfo.setObj(customerNo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户信息同步接口出现异常！");
		}
		return resultInfo;
	}
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private ResultInfo saveCustomerOherInfo(Map custInfoBaseInfoMap,Long custBaseInfoId,
			Long agentId,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//更新客户联系信息
			CustContactInfo custContactInfo = new CustContactInfo();
			custContactInfo.setEmail(custInfoBaseInfoMap.get("email").toString());
			custContactInfo.setMobile(custInfoBaseInfoMap.get("mobile").toString());
			custContactInfo.setAgentId(agentId);
			saveCustContactInfo(custBaseInfoId, custContactInfo, agentId, loginInfo);
			// 插入客户账户信息
			CustAccInfo custAccInfo = new CustAccInfo();
			String bankNo = custInfoBaseInfoMap.get("bankNo").toString();
			if (!bankNo.equals("99999")) {
				custAccInfo.setBankAccNo(bankNo);
			}
			String openBankName = custInfoBaseInfoMap.get("openBankName").toString();
			if (!openBankName.equals("99999")) {
				DefBankExample defBankExample = new DefBankExample();
				DefBankExample.Criteria defBankCriteria = defBankExample.createCriteria();
				defBankCriteria.andBankCodeEqualTo(openBankName);
				List<DefBank> defBanks = defBankMapper.selectByExample(defBankExample);
				if (defBanks != null && defBanks.size() > 0) {
					String bankId = defBanks.get(0).getBankId().toString();
					custAccInfo.setBankCode(bankId);
				}
			}
			String openBranchName = custInfoBaseInfoMap.get("openBranchName").toString();
			if (!openBranchName.equals("99999")) {
				custAccInfo.setBranchBankName(openBranchName);
			}
			String openAccountName = custInfoBaseInfoMap.get("openAccountName").toString();
			if(!openAccountName.equals("99999")){
				custAccInfo.setAccName(openAccountName);
			}
			if(custAccInfo.getBankAccNo()!=null||custAccInfo.getAccName()!=null||
					custAccInfo.getBankCode()!=null||custAccInfo.getBranchBankName()!=null){
				saveInternetCustAccInfo(custBaseInfoId, custAccInfo, agentId, loginInfo);
			}
			// 插入客户其他信息
			CustOthInfo custOthInfo = new CustOthInfo();
			custOthInfo.setCustType("04");
			saveCustOthInfo(custBaseInfoId, custOthInfo, agentId, loginInfo);
			// 插入客户地址信息
			CustAddressInfo custAddressInfo = new CustAddressInfo();
			String province = custInfoBaseInfoMap.get("province").toString();
			if(!province.equals("99999")){
				custAddressInfo.setProvince(province);
			}
			String city = custInfoBaseInfoMap.get("city").toString();
			if(!city.equals("99999")){
				custAddressInfo.setCity(city);
			}
			String area = custInfoBaseInfoMap.get("area").toString();
			if(!area.equals("99999")){
				custAddressInfo.setCountry(area);
			}
			String street = custInfoBaseInfoMap.get("street").toString();
			if (!street.equals("99999")) {
				custAddressInfo.setStreet(street);
			}
			if(custAddressInfo.getProvince()!=null||custAddressInfo.getCity()!=null||
					custAddressInfo.getCountry()!=null||custAddressInfo.getStreet()!=null){
				saveInternetCustAddressInfo(custBaseInfoId, custAddressInfo, agentId, loginInfo);
			}
			resultInfo.setSuccess(true);
			resultInfo.setMsg("客户信息同步成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户地址或者账户信息出现异常！");
		}
		return resultInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getModifyCustomerInvestAmount(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null){
				paramMap.put("custBaseInfoId", param);
				BigDecimal modifyCustomerInvestAmount = modifyCustomerServiceMapper.getModifyCustomerInvestAmount(paramMap);
				if(modifyCustomerInvestAmount==null){
					resultInfo.setObj("0");
				}else {
					resultInfo.setObj(modifyCustomerInvestAmount.toPlainString());
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取投资总额成功!");
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取投资总额失败！");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户其他公司投资总额出现异常!");
		}
		return resultInfo;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getModifyCustomerMyInvestAmount(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null){
				paramMap.put("custBaseInfoId", param);
				BigDecimal modifyCustomerInvestAmount = modifyCustomerServiceMapper.getModifyCustomerMyInvestAmount(paramMap);
				if(modifyCustomerInvestAmount==null){
					resultInfo.setObj("0");
				}else {
					resultInfo.setObj(modifyCustomerInvestAmount.toPlainString());
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取投资总额成功!");
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取投资总额失败！");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户我们公司投资总额出现异常!");
		}
		return resultInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getModifyCustomerMyInvestAmount02(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null){
				paramMap.put("custBaseInfoId", param);
				BigDecimal modifyCustomerInvestAmount = modifyCustomerServiceMapper.getModifyCustomerMyInvestAmount02(paramMap);
				if(modifyCustomerInvestAmount==null){
					resultInfo.setObj("0");
				}else {
					resultInfo.setObj(modifyCustomerInvestAmount.toPlainString());
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取投资总额成功!");
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取投资总额失败！");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户我们公司投资总额出现异常!");
		}
		return resultInfo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getModifyCustomerMyInvestShare(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null){
				paramMap.put("custBaseInfoId", param);
				BigDecimal modifyCustomerInvestAmount = modifyCustomerServiceMapper.getModifyCustomerMyInvestShare(paramMap);
				if(modifyCustomerInvestAmount==null){
					resultInfo.setObj("0");
				}else {
					resultInfo.setObj(modifyCustomerInvestAmount.toPlainString());
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取投资总额成功!");
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取投资总额失败！");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户我们公司投资总额出现异常!");
		}
		return resultInfo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getModifyCustomerMyInvestShare02(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null){
				paramMap.put("custBaseInfoId", param);
				BigDecimal modifyCustomerInvestAmount = modifyCustomerServiceMapper.getModifyCustomerMyInvestShare02(paramMap);
				if(modifyCustomerInvestAmount==null){
					resultInfo.setObj("0");
				}else {
					resultInfo.setObj(modifyCustomerInvestAmount.toPlainString());
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取投资总额成功!");
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取投资总额失败！");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户我们公司投资总额出现异常!");
		}
		return resultInfo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getModifyCustomerMyRemainAmount(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null){
				paramMap.put("custBaseInfoId", param);
				BigDecimal modifyCustomerRemainAmount = modifyCustomerServiceMapper.getModifyCustomerMyRemainAmount(paramMap);
				if(modifyCustomerRemainAmount==null){
					resultInfo.setObj("0");
				}else {
					resultInfo.setObj(modifyCustomerRemainAmount.toPlainString());
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取投资总额成功!");
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取投资总额失败！");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户我们公司投资总额出现异常!");
		}
		return resultInfo;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getModifyCustomerMyRemainAmount02(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null){
				paramMap.put("custBaseInfoId", param);
				BigDecimal modifyCustomerRemainAmount = modifyCustomerServiceMapper.getModifyCustomerMyRemainAmount02(paramMap);
				if(modifyCustomerRemainAmount==null){
					resultInfo.setObj("0");
				}else {
					resultInfo.setObj(modifyCustomerRemainAmount.toPlainString());
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取投资总额成功!");
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取投资总额失败！");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户我们公司投资总额出现异常!");
		}
		return resultInfo;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getModifyCustomerMyRemainShare(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null){
				paramMap.put("custBaseInfoId", param);
				BigDecimal modifyCustomerRemainAmount = modifyCustomerServiceMapper.getModifyCustomerMyRemainShare(paramMap);
				if(modifyCustomerRemainAmount==null){
					resultInfo.setObj("0");
				}else {
					resultInfo.setObj(modifyCustomerRemainAmount.toPlainString());
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取投资总额成功!");
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取投资总额失败！");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户我们公司投资总额出现异常!");
		}
		return resultInfo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getModifyCustomerMyRemainShare02(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if(param!=null){
				paramMap.put("custBaseInfoId", param);
				BigDecimal modifyCustomerRemainAmount = modifyCustomerServiceMapper.getModifyCustomerMyRemainShare02(paramMap);
				if(modifyCustomerRemainAmount==null){
					resultInfo.setObj("0");
				}else {
					resultInfo.setObj(modifyCustomerRemainAmount.toPlainString());
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取投资总额成功!");
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取投资总额失败！");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户我们公司投资总额出现异常!");
		}
		return resultInfo;
	}
	
	/**
	 * 获取理财经理信息
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DataGrid searchAgentInfo(String param) {
		DataGrid dataGrid = new DataGrid();
		Map paramMap = new HashMap();
		try {
			List<Map> resultList = null;
			if (param != null && !"".equals(param)) {
				Long aLong = new Long(param);
				paramMap.put("agentId", aLong);
				resultList = this.modifyCustomerServiceMapper.searchAgent(paramMap);
			}
			dataGrid.setRows(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 根据问卷编码获取问卷所有问题
	 * @param questionnaireCode
	 * @return
	 */
	@Override
	public ResultInfo getQuestionnaireQuestion(String questionnaireType) {
		ResultInfo resultInfo = new ResultInfo();
		List<Map<String, Object>> questionListAndAnswerList = new ArrayList<Map<String,Object>>();
		//获取问卷编码
		DefQuestionnaireExample defQuestionnaireExample = new DefQuestionnaireExample();
		DefQuestionnaireExample.Criteria defQuestionnaireCriteria = defQuestionnaireExample.createCriteria();
		defQuestionnaireCriteria.andQuestionnaireTypeEqualTo(questionnaireType)
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<DefQuestionnaire> defQuestionnaireList = defQuestionnaireMapper.selectByExample(defQuestionnaireExample);
		if(defQuestionnaireList==null||defQuestionnaireList.size()==0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到相关问卷！");
			return resultInfo;
		}
		String questionnaireCode = defQuestionnaireList.get(0).getQuestionnaireCode();
		//1.根据问卷编码获取到该问卷的所有问题
		DefQuestionExample defQuestionExample = new DefQuestionExample();
		defQuestionExample.setOrderByClause("QUESTION_CODE");
		DefQuestionExample.Criteria  defQuestionCriteria = defQuestionExample.createCriteria();
		defQuestionCriteria.andQuestionnaireCodeEqualTo(questionnaireCode)
						   .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<DefQuestion> defQuestionList = defQuestionMapper.selectByExample(defQuestionExample);
		//循环每个问题，获取答案
		for(DefQuestion defQuestion:defQuestionList){
			Map<String, Object> questionMap = new HashMap<String, Object>();
			//获取问题的答案
			DefQuestionAnswerExample defQuestionAnswerExample = new DefQuestionAnswerExample();
			defQuestionAnswerExample.setOrderByClause("ANSWER_CODE");
			DefQuestionAnswerExample.Criteria defQuestionAnswerCriteria = defQuestionAnswerExample.createCriteria();
			defQuestionAnswerCriteria.andQuestionCodeEqualTo(defQuestion.getQuestionCode())
									 .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<DefQuestionAnswer> defQuestionAnswerList = defQuestionAnswerMapper.selectByExample(defQuestionAnswerExample);
			for(DefQuestionAnswer defQuestionAnswer:defQuestionAnswerList){
				defQuestionAnswer.setAnswerContent(defQuestionAnswer.getAnswerCode()+"."+defQuestionAnswer.getAnswerContent());
			}
			questionMap.put("question", defQuestion);
			questionMap.put("questionAnswerList", defQuestionAnswerList);
			questionListAndAnswerList.add(questionMap);
		}
		resultInfo.setSuccess(true);
		resultInfo.setObj(questionListAndAnswerList);
		return resultInfo;
	}

	@Override
	@Transactional
	public ResultInfo saveQuestionnaireQuestionInfo(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		CustQuestionnaireInfo custQuestionnaireInfo = (CustQuestionnaireInfo)JsonUtils.jsonStrToObject(param, CustQuestionnaireInfo.class);
		//获取客户风险等级
		DefCodeExample defCodeExample = new DefCodeExample();
		DefCodeExample.Criteria defCodeCriteria = defCodeExample.createCriteria();
		//获取问卷的类型  03-机构问卷   04-自然人问卷
		String questionnaireType = JsonUtils.getJsonValueByKey("questionnaireType", param);
		if(questionnaireType!=null&&!"".equals(questionnaireType)){
			if("03".equals(questionnaireType)){
				//判断问卷分数是否为空
				if (custQuestionnaireInfo.getScore()!=null) {
					Integer score = custQuestionnaireInfo.getScore();
					if(score>=0&&score<=40){
						//01----保守型
						custQuestionnaireInfo.setCustRiskLevel("01");
					}else if(score>=41&&score<=80){
						//02----稳健型
						custQuestionnaireInfo.setCustRiskLevel("02");
					}else if(score>=81&&score<=120) {
						//03----平衡型
						custQuestionnaireInfo.setCustRiskLevel("03");
					}else if(score>=121&&score<=160){
						//04----成长型
						custQuestionnaireInfo.setCustRiskLevel("04");
					}else if(score>=161&&score<=200){
						//05----进取型
						custQuestionnaireInfo.setCustRiskLevel("05");
					}else{
						resultInfo.setSuccess(false);
						resultInfo.setMsg("计算客户风险类型出错，保存失败！");
						return resultInfo;
					}
				}else{
					resultInfo.setSuccess(false);
					resultInfo.setMsg("未获取到客户问卷总得分，保存失败！");
					return resultInfo;
				}
			}else if("04".equals(questionnaireType)){
				if (custQuestionnaireInfo.getScore()!=null) {
					Integer score = custQuestionnaireInfo.getScore();
					if(score>=0&&score<=30){
						//01----保守型
						custQuestionnaireInfo.setCustRiskLevel("01");
					}else if(score>=31&&score<=60){
						//02----稳健型
						custQuestionnaireInfo.setCustRiskLevel("02");
					}else if(score>=61&&score<=90) {
						//03----平衡型
						custQuestionnaireInfo.setCustRiskLevel("03");
					}else if(score>=91&&score<=120){
						//04----成长型
						custQuestionnaireInfo.setCustRiskLevel("04");
					}else if(score>=121&&score<=150){
						//05----进取型
						custQuestionnaireInfo.setCustRiskLevel("05");
					}else{
						resultInfo.setSuccess(false);
						resultInfo.setMsg("计算客户风险类型出错，保存失败！");
						return resultInfo;
					}
				}else{
					resultInfo.setSuccess(false);
					resultInfo.setMsg("未获取到客户问卷总得分，保存失败！");
					return resultInfo;
				}
			}
		}
		/*if(custQuestionnaireInfo.getScore()!=null){
			Integer score = custQuestionnaireInfo.getScore();
			defCodeCriteria.andCodeTypeEqualTo("custRiskLevel");
			List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
			if(defCodeList!=null&&defCodeList.size()>0){
				for(DefCode defCode:defCodeList){
					Integer lowerScore = new Integer(defCode.getCodeAlias());
					Integer upperScore = new Integer(defCode.getOtherSign());
					if(score>=lowerScore&&score<=upperScore){
						custQuestionnaireInfo.setCustRiskLevel(defCode.getCode());
						break;
					}
				}
			}else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("计算客户风险类型出错，保存失败！");
				return resultInfo;
			}
		}else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到客户问卷总得分，保存失败！");
			return resultInfo;
		}*/
		//根据问卷类型获取问卷编码
		/*String questionnaireType = JsonUtils.getJsonValueByKey("questionnaireType", param);*/
		if(questionnaireType!=null&&!"".equals(questionnaireType)){
			DefQuestionnaireExample defQuestionnaireExample = new DefQuestionnaireExample();
			DefQuestionnaireExample.Criteria defQuestionnaireCriteria = defQuestionnaireExample.createCriteria();
			defQuestionnaireCriteria.andQuestionnaireTypeEqualTo(questionnaireType)
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<DefQuestionnaire> defQuestionnaireList = defQuestionnaireMapper.selectByExample(defQuestionnaireExample);
			if(defQuestionnaireList!=null&&defQuestionnaireList.size()>0){
				custQuestionnaireInfo.setQuestionnaireId(defQuestionnaireList.get(0).getQuestionnaireId());
			}else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到相关问卷信息，保存失败！");
				return resultInfo;
			}
		}else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获问卷类型信息，保存失败！");
			return resultInfo;
		}
		if(custQuestionnaireInfo.getCustQuestionnaireId()==null||custQuestionnaireInfo.getCustQuestionnaireId()==0){
			BeanUtils.insertObjectSetOperateInfo(custQuestionnaireInfo, loginInfo);
			custQuestionnaireInfo.setCustQuestionnaireState("01");
			custQuestionnaireMapper.insert(custQuestionnaireInfo);
		}else{
			long custQuestionnaireId = custQuestionnaireInfo.getCustQuestionnaireId();
			CustQuestionnaireInfo existCustQuestionnaireInfo = custQuestionnaireMapper.selectByPrimaryKey(custQuestionnaireId);
			//custQuestionnaireState:  01：有效期内,02：有效期外
			custQuestionnaireInfo.setCustQuestionnaireState("01");
			String custQuestionnaireState = existCustQuestionnaireInfo.getCustQuestionnaireState();
			if(custQuestionnaireState!=null&&"02".equals(custQuestionnaireState)){
				custQuestionnaireInfo.setCustQuestionnaireId(null);
				BeanUtils.insertObjectSetOperateInfo(custQuestionnaireInfo, loginInfo);
				custQuestionnaireMapper.insert(custQuestionnaireInfo);
			}else{
				BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustQuestionnaireInfo, custQuestionnaireInfo, loginInfo);
				custQuestionnaireMapper.updateByPrimaryKey(custQuestionnaireInfo);
			}
		}
		//风险级别转换为中文显示
		custQuestionnaireInfo.setCustRiskLevel(commonService.getCodeName("custRiskLevel", custQuestionnaireInfo.getCustRiskLevel()));
		resultInfo.setSuccess(true);
		resultInfo.setMsg("保存成功！");
		resultInfo.setObj(custQuestionnaireInfo);
		return resultInfo;
	}
	
	
	/**
	 * 查询客户风控问卷信息
	 * @param custQuestionnaireId
	 * @return
	 */
	public ResultInfo getCustQuestionnaireInfo(long custBaseInfoId){
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> custQuestionMap = new HashMap<String, String>();
		CustQuestionnaireInfoExample custQuestionnaireInfoExample = new CustQuestionnaireInfoExample();
		custQuestionnaireInfoExample.setOrderByClause("SUBMIT_DATE DESC");
		CustQuestionnaireInfoExample.Criteria custQuestionnaireInfoCriteria = custQuestionnaireInfoExample.createCriteria();
		custQuestionnaireInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfoId)
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustQuestionnaireInfo> custQuestionnaireInfoList = custQuestionnaireMapper.selectByExample(custQuestionnaireInfoExample);
		if(custQuestionnaireInfoList!=null&&custQuestionnaireInfoList.size()>0){
			CustQuestionnaireInfo custQuestionnaireInfo = custQuestionnaireInfoList.get(0);
			custQuestionnaireInfo.setCustRiskLevel(commonService.getCodeName("custRiskLevel", custQuestionnaireInfo.getCustRiskLevel()));
			custQuestionMap = JsonUtils.objectToMap(custQuestionnaireInfo);
			DefQuestionnaireExample defQuestionnaireExample = new DefQuestionnaireExample();
			DefQuestionnaireExample.Criteria defQuestionnaireCriteria = defQuestionnaireExample.createCriteria();
			defQuestionnaireCriteria.andQuestionnaireIdEqualTo(custQuestionnaireInfoList.get(0).getQuestionnaireId())
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<DefQuestionnaire> defQuestionnaireList = defQuestionnaireMapper.selectByExample(defQuestionnaireExample);
			if(defQuestionnaireList!=null&&defQuestionnaireList.size()>0){
				custQuestionMap.put("questionnaireType", defQuestionnaireList.get(0).getQuestionnaireType());
			}
			resultInfo.setObj(custQuestionMap);
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	/**
	 * 风控问卷历史记录
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public DataGrid getHistoryRecord(DataGridModel dgm,Map map) {
		DataGrid dataGrid = new DataGrid();
		Map paramMap = new HashMap();
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		try {
			if (map != null) {
				paramMap.put("custBaseInfoId", map.get("custBaseInfoId"));
				// 获取风控问卷信息
				Integer total = modifyCustomerServiceMapper.getHistoryRecordListCount(paramMap);
				List<Map<String, String>> custHistoryRecordList = modifyCustomerServiceMapper.getHistoryRecordList(paramMap);
				dataGrid.setRows(custHistoryRecordList);
				dataGrid.setTotal(total);
			} 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 查询客户风控问卷历史记录详细信息
	 * @param custQuestionnaireId
	 * @return
	 */
	public ResultInfo getCustHistoryRecordInfo(long custQuestionnaireId){
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> custQuestionMap = new HashMap<String, String>();
		CustQuestionnaireInfoExample custQuestionnaireInfoExample = new CustQuestionnaireInfoExample();
		custQuestionnaireInfoExample.setOrderByClause("SUBMIT_DATE DESC");
		CustQuestionnaireInfoExample.Criteria custQuestionnaireInfoCriteria = custQuestionnaireInfoExample.createCriteria();
		custQuestionnaireInfoCriteria.andCustQuestionnaireIdEqualTo(custQuestionnaireId)
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		CustQuestionnaireInfo custQuestionnaireInfoList = custQuestionnaireMapper.selectByPrimaryKey(custQuestionnaireId);
		if(custQuestionnaireInfoList!=null){
			custQuestionnaireInfoList.setCustRiskLevel(commonService.getCodeName("custRiskLevel", custQuestionnaireInfoList.getCustRiskLevel()));
			if (custQuestionnaireInfoList.getQuestionnaireId() == 3) {
				custQuestionnaireInfoList.setQuestionnaireId(new Long(1));
			}
			if (custQuestionnaireInfoList.getQuestionnaireId() == 4) {
				custQuestionnaireInfoList.setQuestionnaireId(new Long(2));
			}
			custQuestionMap = JsonUtils.objectToMap(custQuestionnaireInfoList);
			DefQuestionnaireExample defQuestionnaireExample = new DefQuestionnaireExample();
			DefQuestionnaireExample.Criteria defQuestionnaireCriteria = defQuestionnaireExample.createCriteria();
			defQuestionnaireCriteria.andQuestionnaireIdEqualTo(custQuestionnaireInfoList.getQuestionnaireId())
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			DefQuestionnaire defQuestionnaireList = defQuestionnaireMapper.selectByPrimaryKey(custQuestionnaireInfoList.getQuestionnaireId());
			if(defQuestionnaireList!=null){
				custQuestionMap.put("questionnaireType", defQuestionnaireList.getQuestionnaireType());
			}
			resultInfo.setObj(custQuestionMap);
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	/**
	 * 校验准客户 填写专业投资者的人 是否上传过资产证明材料
	 * @param custBaseInfoId
	 * @return
	 */
	@Override
	public String checkInvestCustInfo(String custBaseInfoId) {
		String returnStr = "true";
		DefFileInfoExample defFileInfoExample = new DefFileInfoExample();
		defFileInfoExample.createCriteria().andBusinessNoEqualTo(Long.parseLong(custBaseInfoId))
		.andBusinessTypeEqualTo("12").andRcStateEqualTo("E");
		List<DefFileInfo> defFileInfos = defFileInfoMapper.selectByExample(defFileInfoExample);
		if (defFileInfos.isEmpty() || "".equals(defFileInfos) || defFileInfos.size() == 0) {
			returnStr = "false";
		}
		return returnStr;
	}
	
	/**
	 * 校验客户是否上传身份证复印件
	 * @param custBaseInfoId
	 * @return
	 */
	@Override
	public String queryCustIdCardUpload(String custBaseInfoId) {
		String returnStr = "true";
		DefFileInfoExample defFileInfoExample = new DefFileInfoExample();
		defFileInfoExample.createCriteria().andBusinessNoEqualTo(Long.parseLong(custBaseInfoId))
		.andBusinessTypeEqualTo("10").andRcStateEqualTo("E");
		List<DefFileInfo> defFileInfos = defFileInfoMapper.selectByExample(defFileInfoExample);
		if (defFileInfos.isEmpty() || "".equals(defFileInfos) || defFileInfos.size() == 0) {
			returnStr = "false";
		}
		return returnStr;
	}
}
