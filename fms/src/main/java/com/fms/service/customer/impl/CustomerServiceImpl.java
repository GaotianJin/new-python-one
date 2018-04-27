package com.fms.service.customer.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
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

import com.fms.controller.customer.CustomerController;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.CustAccInfoMapper;
import com.fms.db.mapper.CustAddressInfoMapper;
import com.fms.db.mapper.CustAssetInfoMapper;
import com.fms.db.mapper.CustBaseInfoMapper;
import com.fms.db.mapper.CustBelongOperationMapper;
import com.fms.db.mapper.CustCarInfoMapper;
import com.fms.db.mapper.CustContactInfoMapper;
import com.fms.db.mapper.CustFamilyInfoMapper;
import com.fms.db.mapper.CustHobbyInfoMapper;
import com.fms.db.mapper.CustHouseInfoMapper;
import com.fms.db.mapper.CustIncomeInfoMapper;
import com.fms.db.mapper.CustInvestInfoMapper;
import com.fms.db.mapper.CustOperationMapper;
import com.fms.db.mapper.CustOperationTraceMapper;
import com.fms.db.mapper.CustOthInfoMapper;
import com.fms.db.mapper.DefFileInfoMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PDWealthFeeRateMapper;
import com.fms.db.mapper.PDWealthMapper;
import com.fms.db.mapper.PDWealthNetValueMapper;
import com.fms.db.mapper.PdIncomeDisDetailMapper;
import com.fms.db.mapper.RedemptionInfoMapper;
import com.fms.db.mapper.SysEmailAdressMapper;
import com.fms.db.mapper.SysEmailInfoMapper;
import com.fms.db.mapper.TradeInfoMapper;
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
import com.fms.db.model.CustBelongOperation;
import com.fms.db.model.CustBelongOperationExample;
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
import com.fms.db.model.CustOperation;
import com.fms.db.model.CustOperationExample;
import com.fms.db.model.CustOperationTrace;
import com.fms.db.model.CustOthInfo;
import com.fms.db.model.CustOthInfoExample;
import com.fms.db.model.DefFileInfo;
import com.fms.db.model.DefFileInfoExample;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthExample;
import com.fms.db.model.PDWealthFeeRate;
import com.fms.db.model.PDWealthFeeRateExample;
import com.fms.db.model.PDWealthNetValue;
import com.fms.db.model.PDWealthNetValueExample;
import com.fms.db.model.PdIncomeDisDetail;
import com.fms.db.model.PdIncomeDisDetailExample;
import com.fms.db.model.RedemptionInfo;
import com.fms.db.model.RedemptionInfoExample;
import com.fms.db.model.SysEmailAdress;
import com.fms.db.model.SysEmailAdressExample;
import com.fms.db.model.SysEmailInfo;
import com.fms.db.model.SysSmsInfo;
import com.fms.db.model.SysSmsTemplate;
import com.fms.db.model.SysSmsTemplateExample;
import com.fms.db.model.TradeInfo;
import com.fms.db.model.TradeInfoExample;
import com.fms.db.model.TradeOperation;
import com.fms.db.model.TradeOperationExample;
import com.fms.db.model.TradeOperationTrace;
import com.fms.db.model.TradeProductInfo;
import com.fms.db.model.TradeProductInfoExample;
import com.fms.service.customer.CustomerService;
import com.fms.service.mapper.CustomerServiceMapper;
import com.sinosoft.core.db.mapper.DefCodeMapper;
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

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	@Autowired
	private CustBaseInfoMapper custBaseInfoMapper;
	@Autowired
	private CustContactInfoMapper custContactInfoMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private CustAddressInfoMapper custAddressInfoMapper;
	@Autowired
	private CustAccInfoMapper custAccInfoMapper;
	@Autowired
	private CustOthInfoMapper custOthInfoMapper;
	@Autowired
	private CustHobbyInfoMapper custHobbyInfoMapper;
	@Autowired
	private CustIncomeInfoMapper custIncomeInfoMapper;
	@Autowired
	private CustAssetInfoMapper custAssetInfoMapper;
	@Autowired
	private CustInvestInfoMapper custInvestInfoMapper;
	@Autowired
	private CustFamilyInfoMapper custFamilyInfoMapper;
	@Autowired
	private CustCarInfoMapper custCarInfoMapper;
	@Autowired
	private CustHouseInfoMapper custHouseInfoMapper;
	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	@Autowired
	private TradeProductInfoMapper tradeProductInfoMapper;
	@Autowired
	private PDProductMapper pdProductMapper;
	@Autowired
	private PDWealthMapper pdWealthMapper;
	@Autowired
	private RedemptionInfoMapper redemptionInfoMapper;
	@Autowired
	private PDWealthFeeRateMapper pdWealthFeeRateMapper;
	@Autowired
	private PdIncomeDisDetailMapper pdIncomeDisDetailMapper;
	@Autowired
	private PDWealthNetValueMapper pdWealthNetValueMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	
	@Autowired
	private CustBelongOperationMapper custBelongOperationMapper;
	@Autowired
	private CustOperationMapper custOperationMapper;
	@Autowired
	private CustOperationTraceMapper custOperationTraceMapper;
	
	@Autowired
	private DefFileInfoMapper defFileInfoMapper;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private SysEmailAdressMapper sysEmailAdressMapper;
	@Autowired
	private SysEmailInfoMapper sysEmailInfoMapper;
	
	@Override
	@Transactional
	public ResultInfo saveCustomerBaseInfo(CustBaseInfo custBaseInfo, String agentId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 获取财富顾问信息
			Agent agent = new Agent();
			if (agentId != null && !"".equals(agentId)) {
				agent = agentMapper.selectByPrimaryKey(new Long(agentId));
			} else {
				resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
				agent = (Agent) resultInfo.getObj();
			}
			/*// 客户证件号校验
			if (custBaseInfo.getIdNo() != null && custBaseInfo != null) {
				resultInfo = verifyCustomerIdNo(custBaseInfo.getIdNo());

				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}*/

			CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();
			CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
			if ("1".equals(custBaseInfo.getIdType())) {
				custBaseInfo.setIdNo(custBaseInfo.getIdNo().toUpperCase());
			}
			// 客户信息五要素查询客户
			custBaseInfoCriteria.andChnNameEqualTo(custBaseInfo.getChnName()).andSexEqualTo(custBaseInfo.getSex())
					.andBirthdayEqualTo(custBaseInfo.getBirthday()).andIdNoEqualTo(custBaseInfo.getIdNo())
					.andIdTypeEqualTo(custBaseInfo.getIdType()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample);
			// 判断客户是否存在,五要素一致
			if (custBaseInfoList != null && custBaseInfoList.size() > 0) {
				// 客户存在
				CustBaseInfo existCustBaseInfo = custBaseInfoList.get(0);
				// 判断录入的客户是否已经有所属财富顾问
				resultInfo = verifyCustAgentInfo(existCustBaseInfo, agent);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
				// 判断两个客户基本信息是否完全一致
				if (existCustBaseInfo.equals(custBaseInfo)) {
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustBaseInfo, custBaseInfo, loginInfo);
					custBaseInfo.setCustBaseInfoId(existCustBaseInfo.getCustBaseInfoId());
					custBaseInfo.setCustomerNo(existCustBaseInfo.getCustomerNo());
					custBaseInfo.setAge(existCustBaseInfo.getAge());
					custBaseInfo.setAgentId(existCustBaseInfo.getAgentId());
					custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
					resultInfo.setSuccess(true);
					resultInfo.setMsg("客户基本信息保存成功");
					resultInfo.setObj(existCustBaseInfo);
				} else {
					/*
					 * //进行逻辑删除，并更新客户信息附属信息 //1.生成客户基本信息主键 BigDecimal
					 * custBaseInfoId =
					 * commonService.getSeqValByName("SEQ_T_CUST_BASE_INFO");
					 * //2.设置相关信息新纪录的相关信息
					 * custBaseInfo.setCustBaseInfoId(custBaseInfoId);
					 * custBaseInfo.setCustomerNo(existCustBaseInfo.
					 * getCustomerNo());
					 * custBaseInfo.setAge((short)DateUtils.calInterval(
					 * custBaseInfo.getBirthday(),
					 * DateUtils.getCurrentTimestamp(), "Y"));
					 * BeanUtils.deleteAndInsertObjectSetOperateInfo(
					 * existCustBaseInfo, custBaseInfo, loginInfo);
					 * custBaseInfoMapper.insert(custBaseInfo);
					 * //3.逻辑删除现有客户基本信息记录
					 * custBaseInfoMapper.updateByPrimaryKey(existCustBaseInfo);
					 * //4.更新客户相关附属信息 resultInfo =
					 * updateCustAllInfo(existCustBaseInfo,custBaseInfoId,
					 * loginInfo); if (!resultInfo.isSuccess()) { return
					 * resultInfo; }
					 */

					custBaseInfo.setCustBaseInfoId(existCustBaseInfo.getCustBaseInfoId());
					custBaseInfo.setCustomerNo(existCustBaseInfo.getCustomerNo());
					custBaseInfo.setAge(existCustBaseInfo.getAge());
					custBaseInfo.setAgentId(existCustBaseInfo.getAgentId());
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustBaseInfo, custBaseInfo, loginInfo);
					custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
					resultInfo.setSuccess(true);
					resultInfo.setMsg("客户基本信息保存成功");
					resultInfo.setObj(custBaseInfo);

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
				custBaseInfo.setAge((long) DateUtils.calInterval(custBaseInfo.getBirthday(),
						DateUtils.getCurrentTimestamp(), "Y"));
				BeanUtils.insertObjectSetOperateInfo(custBaseInfo, loginInfo);
				custBaseInfoMapper.insert(custBaseInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("客户基本信息保存成功");
				resultInfo.setObj(custBaseInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户基本信息出错");
		}
		return resultInfo;
	}

	/**
	 * 保存客户联系信息和地址信息
	 * 
	 * @param custBaseInfo
	 * @param custContactInfo
	 * @param custAddressInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Override
	@Transactional
	public ResultInfo saveCustContactAndAddressInfo(CustBaseInfo custBaseInfo, CustContactInfo custContactInfo,
			List<CustAddressInfo> custAddressInfoList, LoginInfo loginInfo, Agent agent, String operate) {
		// 保存客户联系信息
		ResultInfo resultInfo = new ResultInfo();
		resultInfo = saveCustContactInfo(custBaseInfo, custContactInfo, agent, loginInfo, operate);
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		// 保存客户地址信息
		resultInfo = saveCustAddressInfo(custBaseInfo, custAddressInfoList, agent, loginInfo, operate);
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("客户联系信息与地址信息保存成功");
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
	 * 校验客户手机号是否已经存在
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
						//long custAddressId = commonService.getSeqValByName("SEQ_T_CUST_ADDRESS_INFO");
						custAddressInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
						//custAddressInfo.setCustAddressInfoId(custAddressId);
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
									Long custAddressInfoId = commonService.getSeqValByName("SEQ_T_CUST_ADDRESS_INFO");
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
	 * @param custAccInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Override
	@Transactional
	public ResultInfo saveCustAccInfo(CustBaseInfo custBaseInfo, List<CustAccInfo> custAccInfoList, Agent agent,
			LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		if (custBaseInfo.getCustBaseInfoId() == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户基本信息为空，请先新增客户基本信息");
			return resultInfo;
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
		try {
			CustAccInfoExample custAccInfoExample = new CustAccInfoExample();
			CustAccInfoExample.Criteria custAccCriteria = custAccInfoExample.createCriteria();
			custAccCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAccInfo> existCustAccInfoList = custAccInfoMapper.selectByExample(custAccInfoExample);
			List<CustAccInfo> delExistCustAccInfoList = new ArrayList<CustAccInfo>();
			delExistCustAccInfoList.addAll(existCustAccInfoList);
			if (custAccInfoList != null && custAccInfoList.size() > 0) {
				// 全部插入数据库
				for (CustAccInfo custAccInfo : custAccInfoList) {
					if (custAccInfo.getCustAccInfoId() == null) {
						Long custAccInfoId = commonService.getSeqValByName("SEQ_T_CUST_ACC_INFO");
						custAccInfo.setCustAccInfoId(custAccInfoId);
						custAccInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
						custAccInfo.setAgentId(agent.getAgentId());
						BeanUtils.insertObjectSetOperateInfo(custAccInfo, loginInfo);
						custAccInfoMapper.insert(custAccInfo);
					} else {
						for (CustAccInfo existCustAccInfo : existCustAccInfoList) {
							if (custAccInfo.getCustAccInfoId().compareTo(existCustAccInfo.getCustAccInfoId()) == 0) {
								if (custAccInfo.equals(existCustAccInfo)) {
									BeanUtils.updateObjectSetOperateInfo(existCustAccInfo, loginInfo);
									custAccInfoMapper.updateByPrimaryKey(existCustAccInfo);
									delExistCustAccInfoList.remove(existCustAccInfo);
								} else {
									BeanUtils.deleteAndInsertObjectSetOperateInfo(existCustAccInfo, custAccInfo,
											loginInfo);
									Long custAccInfoId = commonService.getSeqValByName("SEQ_T_CUST_ACC_INFO");
									custAccInfo.setCustAccInfoId(custAccInfoId);
									custAccInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
									custAccInfo.setAgentId(agent.getAgentId());
									custAccInfoMapper.insert(custAccInfo);
									custAccInfoMapper.updateByPrimaryKey(existCustAccInfo);
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

	@Override
	/**
	 * 保存客户个人信息
	 * 
	 * @param custBaseInfo
	 * @param custHobbyInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	public ResultInfo saveCustPersonalInfo(CustBaseInfo custBaseInfo, CustOthInfo custOthInfo,
			List<CustHobbyInfo> custHobbyInfoList, List<CustIncomeInfo> custIncomeInfoList,
			List<CustAssetInfo> custAssetInfoList, List<CustInvestInfo> custInvestInfoList, Agent agent,
			LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		if (custBaseInfo.getCustBaseInfoId() == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户基本信息为空，请先新增客户基本信息");
			return resultInfo;
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
		try {
			CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
			CustOthInfoExample.Criteria custOthCriteria = custOthInfoExample.createCriteria();
			custOthCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustOthInfo> existCustOthInfoList = custOthInfoMapper.selectByExample(custOthInfoExample);
			// 删除已保存的客户个人信息
			for (CustOthInfo existCustOthInfo : existCustOthInfoList) {
				BeanUtils.deleteObjectSetOperateInfo(existCustOthInfo, loginInfo);
				custOthInfoMapper.updateByPrimaryKey(existCustOthInfo);
			}
			if (custOthInfo != null) {
				// 插入新的个人信息
				//Long custOthInfoId = commonService.getSeqValByName("SEQ_T_CUST_OTH_INFO");
				//custOthInfo.setCustOthInfoId(custOthInfoId);
				custOthInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
				custOthInfo.setAgentId(agent.getAgentId());
				BeanUtils.insertObjectSetOperateInfo(custOthInfo, loginInfo);
				custOthInfoMapper.insert(custOthInfo);
			}
			// 保存客户爱好信息
			resultInfo = saveCustHobbyInfo(custBaseInfo, custHobbyInfoList, loginInfo, operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			// 保存客户收入来源信息
			resultInfo = saveCustIncomeInfo(custBaseInfo, custIncomeInfoList, agent, loginInfo, operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			// 保存客户资产构成信息
			resultInfo = saveCustAssetInfo(custBaseInfo, custAssetInfoList, agent, loginInfo, operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			// 保存客户投资产品信息
			resultInfo = saveCustInvestInfo(custBaseInfo, custInvestInfoList, agent, loginInfo, operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("保存客户个人信息出错");
			resultInfo.setSuccess(false);
		}
		resultInfo.setMsg("保存客户个人信息成功");
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
	private ResultInfo saveCustHobbyInfo(CustBaseInfo custBaseInfo, List<CustHobbyInfo> custHobbyInfoList,
			LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		if (custBaseInfo.getCustBaseInfoId() == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户基本信息为空，请先新增客户基本信息");
			return resultInfo;
		}
		resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		Agent agent = (Agent) resultInfo.getObj();
		try {
			CustHobbyInfoExample custHobbyInfoExample = new CustHobbyInfoExample();
			CustHobbyInfoExample.Criteria custHobbyCriteria = custHobbyInfoExample.createCriteria();
			custHobbyCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
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
					custHobbyInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
					custHobbyInfo.setCustHobbyInfoId(custHobbyInfoId);
					custHobbyInfo.setAgentId(agent.getAgentId());
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
	 * 保存客户财富状况信息
	 * 
	 * @param custOthInfo
	 * @param custIncomeInfoList
	 * @param custAssetInfoList
	 * @param custInvestInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Override
	@Transactional
	public ResultInfo saveCustWealthInfo(CustBaseInfo custBaseInfo, CustOthInfo custOthInfo,
			List<CustIncomeInfo> custIncomeInfoList, List<CustAssetInfo> custAssetInfoList,
			List<CustInvestInfo> custInvestInfoList, Agent agent, LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		if (custBaseInfo.getCustBaseInfoId() == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户基本信息为空，请先新增客户基本信息");
			return resultInfo;
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
		// 保存客户财富状况相关信息
		resultInfo = saveCustOthInfo(custBaseInfo, custOthInfo, agent, loginInfo, operate);
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		// 保存客户收入来源信息
		resultInfo = saveCustIncomeInfo(custBaseInfo, custIncomeInfoList, agent, loginInfo, operate);
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		// 保存客户资产构成信息
		resultInfo = saveCustAssetInfo(custBaseInfo, custAssetInfoList, agent, loginInfo, operate);
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		// 保存客户投资产品信息
		resultInfo = saveCustInvestInfo(custBaseInfo, custInvestInfoList, agent, loginInfo, operate);
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		return resultInfo;
	}

	/**
	 * @param custOthInfo
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustOthInfo(CustBaseInfo custBaseInfo, CustOthInfo custOthInfo, Agent agent,
			LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
			CustOthInfoExample.Criteria custOthCriteria = custOthInfoExample.createCriteria();
			custOthCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustOthInfo> existCustOthInfoList = custOthInfoMapper.selectByExample(custOthInfoExample);
			// 逻辑删除已存在的客户其他信息
			if (existCustOthInfoList != null && existCustOthInfoList.size() > 0) {
				for (CustOthInfo existCustOthInfo : existCustOthInfoList) {
					BeanUtils.deleteObjectSetOperateInfo(existCustOthInfo, loginInfo);
					custOthInfoMapper.updateByPrimaryKey(existCustOthInfo);
				}
			}
			if (custOthInfo != null) {
				// 插入新的个人信息
				//Long custOthInfoId = commonService.getSeqValByName("SEQ_T_CUST_OTH_INFO");
				//custOthInfo.setCustOthInfoId(custOthInfoId);
				custOthInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
				custOthInfo.setAgentId(agent.getAgentId());
				BeanUtils.insertObjectSetOperateInfo(custOthInfo, loginInfo);
				custOthInfoMapper.insert(custOthInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("保存客户财富状况信息出错");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setMsg("保存客户财富状况信息成功");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * @param custBaseInfo
	 * @param custIncomeInfoList
	 * @param agent
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustIncomeInfo(CustBaseInfo custBaseInfo, List<CustIncomeInfo> custIncomeInfoList,
			Agent agent, LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustIncomeInfoExample custIncomeInfoExample = new CustIncomeInfoExample();
			CustIncomeInfoExample.Criteria custIncomeCriteria = custIncomeInfoExample.createCriteria();
			custIncomeCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustIncomeInfo> existCustIncomeInfoList = custIncomeInfoMapper.selectByExample(custIncomeInfoExample);
			if (existCustIncomeInfoList != null && existCustIncomeInfoList.size() > 0) {
				// 逻辑删除已经存在的收入来源信息
				for (CustIncomeInfo existCustIncomeInfo : existCustIncomeInfoList) {
					BeanUtils.deleteObjectSetOperateInfo(existCustIncomeInfo, loginInfo);
					custIncomeInfoMapper.updateByPrimaryKey(existCustIncomeInfo);
				}
			}
			if (custIncomeInfoList != null && custIncomeInfoList.size() > 0) {
				// 保存新的收入来源信息
				for (CustIncomeInfo custIncomeInfo : custIncomeInfoList) {
					Long custIncomeInfoId = commonService.getSeqValByName("SEQ_T_CUST_INCOME_INFO");
					custIncomeInfo.setCustIncomeInfoId(custIncomeInfoId);
					custIncomeInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
					custIncomeInfo.setAgentId(agent.getAgentId());
					BeanUtils.insertObjectSetOperateInfo(custIncomeInfo, loginInfo);
					custIncomeInfoMapper.insert(custIncomeInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("保存客户收入来源信息出错");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setMsg("保存客户收入来源信息成功");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * @param custBaseInfo
	 * @param custAssetInfoList
	 * @param agent
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustAssetInfo(CustBaseInfo custBaseInfo, List<CustAssetInfo> custAssetInfoList, Agent agent,
			LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustAssetInfoExample custAssetInfoExample = new CustAssetInfoExample();
			CustAssetInfoExample.Criteria custAssetCriteria = custAssetInfoExample.createCriteria();
			custAssetCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAssetInfo> existCustAssetInfoList = custAssetInfoMapper.selectByExample(custAssetInfoExample);
			if (existCustAssetInfoList != null && existCustAssetInfoList.size() > 0) {
				// 逻辑删除目前资产构成信息
				for (CustAssetInfo existCustAssetInfo : existCustAssetInfoList) {
					BeanUtils.deleteObjectSetOperateInfo(existCustAssetInfo, loginInfo);
					custAssetInfoMapper.updateByPrimaryKey(existCustAssetInfo);
				}
			}
			// 保存最新的资产构成信息
			if (custAssetInfoList != null && custAssetInfoList.size() > 0) {
				for (CustAssetInfo custAssetInfo : custAssetInfoList) {
					Long custAssetInfoId = commonService.getSeqValByName("SEQ_T_CUST_ASSET_INFO");
					custAssetInfo.setCustAssetInfoId(custAssetInfoId);
					custAssetInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
					custAssetInfo.setAgentId(agent.getAgentId());
					BeanUtils.insertObjectSetOperateInfo(custAssetInfo, loginInfo);
					custAssetInfoMapper.insert(custAssetInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("保存客户资产构成信息出错");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setMsg("保存客户资产构成信息成功");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * @param custBaseInfo
	 * @param custInvestInfoList
	 * @param agent
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	private ResultInfo saveCustInvestInfo(CustBaseInfo custBaseInfo, List<CustInvestInfo> custInvestInfoList,
			Agent agent, LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			CustInvestInfoExample custInvestInfoExample = new CustInvestInfoExample();
			CustInvestInfoExample.Criteria custInvestCriteria = custInvestInfoExample.createCriteria();
			custInvestCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustInvestInfo> existCustInvestInfoList = custInvestInfoMapper.selectByExample(custInvestInfoExample);
			if (existCustInvestInfoList != null && existCustInvestInfoList.size() > 0) {
				// 逻辑删除之前保存的客户投资信息
				for (CustInvestInfo existCustInvestInfo : existCustInvestInfoList) {
					BeanUtils.deleteObjectSetOperateInfo(existCustInvestInfo, loginInfo);
					custInvestInfoMapper.updateByPrimaryKey(existCustInvestInfo);
				}
			}
			if (custInvestInfoList != null && custInvestInfoList.size() > 0) {
				// 保存新的客户投资信息
				for (CustInvestInfo custInvestInfo : custInvestInfoList) {
					Long custInvestInfoId = commonService.getSeqValByName("SEQ_T_CUST_INVEST_INFO");
					custInvestInfo.setCustInvestInfoId(custInvestInfoId);
					custInvestInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
					custInvestInfo.setAgentId(agent.getAgentId());
					BeanUtils.insertObjectSetOperateInfo(custInvestInfo, loginInfo);
					custInvestInfoMapper.insert(custInvestInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("保存客户投资产品信息出错");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setMsg("保存客户投资产品信息成功");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * @param custFamilyInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Override
	@Transactional
	public ResultInfo saveCustFamilyInfo(CustBaseInfo custBaseInfo, List<CustFamilyInfo> custFamilyInfoList,
			Agent agent, LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		if (custBaseInfo.getCustBaseInfoId() == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户基本信息为空，请先新增客户基本信息");
			return resultInfo;
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
		try {
			CustFamilyInfoExample custFamilyInfoExample = new CustFamilyInfoExample();
			CustFamilyInfoExample.Criteria custFamilyCriteria = custFamilyInfoExample.createCriteria();
			custFamilyCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
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
					custFamilyInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
					custFamilyInfo.setAgentId(agent.getAgentId());
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
	 * @param custHouseInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Override
	@Transactional
	public ResultInfo saveCustHouseInfo(CustBaseInfo custBaseInfo, List<CustHouseInfo> custHouseInfoList, Agent agent,
			LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		if (custBaseInfo.getCustBaseInfoId() == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户基本信息为空，请先新增客户基本信息");
			return resultInfo;
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
		try {
			CustHouseInfoExample custHouseInfoExample = new CustHouseInfoExample();
			CustHouseInfoExample.Criteria custHouseCriteria = custHouseInfoExample.createCriteria();
			custHouseCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
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
					custHouseInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
					custHouseInfo.setAgentId(agent.getAgentId());
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
	 * @param custCarInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Override
	@Transactional
	public ResultInfo saveCustCarInfo(CustBaseInfo custBaseInfo, List<CustCarInfo> custCarInfoList, Agent agent,
			LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		if (custBaseInfo.getCustBaseInfoId() == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户基本信息为空，请先新增客户基本信息");
			return resultInfo;
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
		try {
			CustCarInfoExample custCarInfoExample = new CustCarInfoExample();
			CustCarInfoExample.Criteria custCarInfoCriteria = custCarInfoExample.createCriteria();
			custCarInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
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
					custCarInfo.setCustBaseInfoId(custBaseInfo.getCustBaseInfoId());
					custCarInfo.setAgentId(agent.getAgentId());
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
	 * 更新客户基本信息是级联更新客户所有附属关联信息
	 * 
	 * @param custBaseInfo
	 * @param newCustBaseInfoID
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo updateCustAllInfo(CustBaseInfo custBaseInfo, Long newCustBaseInfoID, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 更新客户联系信息
			CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
			CustContactInfoExample.Criteria criteria = custContactInfoExample.createCriteria();
			criteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustContactInfo> contactInfoList = custContactInfoMapper.selectByExample(custContactInfoExample);
			if (contactInfoList != null && contactInfoList.size() > 0) {
				for (CustContactInfo custContactInfo : contactInfoList) {
					custContactInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custContactInfo, loginInfo);
					custContactInfoMapper.updateByPrimaryKey(custContactInfo);
				}
			}
			// 更新客户地址信息
			CustAddressInfoExample custAddressInfoExample = new CustAddressInfoExample();
			CustAddressInfoExample.Criteria addressCriteria = custAddressInfoExample.createCriteria();
			addressCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAddressInfo> custAddressInfoList = custAddressInfoMapper.selectByExample(custAddressInfoExample);
			if (custAddressInfoList != null && custAddressInfoList.size() > 0) {
				for (CustAddressInfo custAddressInfo : custAddressInfoList) {
					custAddressInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custAddressInfo, loginInfo);
					custAddressInfoMapper.updateByPrimaryKey(custAddressInfo);
				}
			}
			// 更新客户账户信息
			CustAccInfoExample custAccInfoExample = new CustAccInfoExample();
			CustAccInfoExample.Criteria custAccCriteria = custAccInfoExample.createCriteria();
			custAccCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAccInfo> custAccInfoList = custAccInfoMapper.selectByExample(custAccInfoExample);
			if (custAccInfoList != null && custAccInfoList.size() > 0) {
				for (CustAccInfo custAccInfo : custAccInfoList) {
					custAccInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custAccInfo, loginInfo);
					custAccInfoMapper.updateByPrimaryKey(custAccInfo);
				}
			}
			// 更新客户其他信息
			CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
			CustOthInfoExample.Criteria custOthCriteria = custOthInfoExample.createCriteria();
			custOthCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustOthInfo> custOthInfoList = custOthInfoMapper.selectByExample(custOthInfoExample);
			if (custOthInfoList != null && custOthInfoList.size() > 0) {
				for (CustOthInfo custOthInfo : custOthInfoList) {
					custOthInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custOthInfo, loginInfo);
					custOthInfoMapper.updateByPrimaryKey(custOthInfo);
				}
			}
			// 更新客户爱好信息
			CustHobbyInfoExample custHobbyInfoExample = new CustHobbyInfoExample();
			CustHobbyInfoExample.Criteria custHobbyCriteria = custHobbyInfoExample.createCriteria();
			custHobbyCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustHobbyInfo> custHobbyInfoList = custHobbyInfoMapper.selectByExample(custHobbyInfoExample);
			if (custHobbyInfoList != null && custHobbyInfoList.size() > 0) {
				for (CustHobbyInfo custHobbyInfo : custHobbyInfoList) {
					custHobbyInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custHobbyInfo, loginInfo);
					custHobbyInfoMapper.updateByPrimaryKey(custHobbyInfo);
				}
			}
			// 更新客户收入来源信息
			CustIncomeInfoExample custIncomeInfoExample = new CustIncomeInfoExample();
			CustIncomeInfoExample.Criteria custIncomeCriteria = custIncomeInfoExample.createCriteria();
			custIncomeCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustIncomeInfo> custIncomeInfoList = custIncomeInfoMapper.selectByExample(custIncomeInfoExample);
			if (custIncomeInfoList != null && custIncomeInfoList.size() > 0) {
				for (CustIncomeInfo custIncomeInfo : custIncomeInfoList) {
					custIncomeInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custIncomeInfo, loginInfo);
					custIncomeInfoMapper.updateByPrimaryKey(custIncomeInfo);
				}
			}
			// 更新客户资产构成信息
			CustAssetInfoExample custAssetInfoExample = new CustAssetInfoExample();
			CustAssetInfoExample.Criteria custAssetCriteria = custAssetInfoExample.createCriteria();
			custAssetCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustAssetInfo> custAssetInfoList = custAssetInfoMapper.selectByExample(custAssetInfoExample);
			if (custAssetInfoList != null && custAssetInfoList.size() > 0) {
				for (CustAssetInfo custAssetInfo : custAssetInfoList) {
					custAssetInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custAssetInfo, loginInfo);
					custAssetInfoMapper.updateByPrimaryKey(custAssetInfo);
				}
			}
			// 更新新客户投资产品信息
			CustInvestInfoExample custInvestInfoExample = new CustInvestInfoExample();
			CustInvestInfoExample.Criteria custInvestCriteria = custInvestInfoExample.createCriteria();
			custInvestCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustInvestInfo> custInvestInfoList = custInvestInfoMapper.selectByExample(custInvestInfoExample);
			if (custInvestInfoList != null && custInvestInfoList.size() > 0) {
				for (CustInvestInfo custInvestInfo : custInvestInfoList) {
					custInvestInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custInvestInfo, loginInfo);
					custInvestInfoMapper.updateByPrimaryKey(custInvestInfo);
				}
			}
			// 更新客户家庭信息
			CustFamilyInfoExample custFamilyInfoExample = new CustFamilyInfoExample();
			CustFamilyInfoExample.Criteria custFamilyCriteria = custFamilyInfoExample.createCriteria();
			custFamilyCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustFamilyInfo> custFamilyInfoList = custFamilyInfoMapper.selectByExample(custFamilyInfoExample);
			if (custFamilyInfoList != null && custFamilyInfoList.size() > 0) {
				for (CustFamilyInfo custFamilyInfo : custFamilyInfoList) {
					custFamilyInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custFamilyInfo, loginInfo);
					custFamilyInfoMapper.updateByPrimaryKey(custFamilyInfo);
				}
			}
			// 更新客户房产信息
			CustHouseInfoExample custHouseInfoExample = new CustHouseInfoExample();
			CustHouseInfoExample.Criteria custHouseCriteria = custHouseInfoExample.createCriteria();
			custHouseCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustHouseInfo> custHouseInfoList = custHouseInfoMapper.selectByExample(custHouseInfoExample);
			if (custHouseInfoList != null && custHouseInfoList.size() > 0) {
				for (CustHouseInfo custHouseInfo : custHouseInfoList) {
					custHouseInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custHouseInfo, loginInfo);
					custHouseInfoMapper.updateByPrimaryKey(custHouseInfo);
				}
			}
			// 更新客户车辆信息
			CustCarInfoExample custCarInfoExample = new CustCarInfoExample();
			CustCarInfoExample.Criteria custCarInfoCriteria = custCarInfoExample.createCriteria();
			custCarInfoCriteria.andCustBaseInfoIdEqualTo(custBaseInfo.getCustBaseInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustCarInfo> custCarInfoList = custCarInfoMapper.selectByExample(custCarInfoExample);
			if (custCarInfoList != null && custCarInfoList.size() > 0) {
				for (CustCarInfo custCarInfo : custCarInfoList) {
					custCarInfo.setCustBaseInfoId(newCustBaseInfoID);
					BeanUtils.updateObjectSetOperateInfo(custCarInfo, loginInfo);
					custCarInfoMapper.updateByPrimaryKey(custCarInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新客户相关信息出错");
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("更新客户相关信息成功");
		return resultInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid searchCustomerInfo(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		
		
		 ResultInfo resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
		 if (!resultInfo.isSuccess()) 
		 { 
			 paramMap.put("agentId", "null"); 
		 }else
		 { 
			 paramMap.put("agentId", ((Agent)resultInfo.getObj()).getAgentId().toString()); 
		 }
		 

		Integer total = customerServiceMapper.searchCustomerCount(paramMap);
		List<Map> resultList = customerServiceMapper.searchCustomerList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * @param custBaseInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getCustInfo(CustBaseInfo custBaseInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> custInfoMap = new HashMap<String, Object>();
		if (custBaseInfo.getCustBaseInfoId() == null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户基本信息为空，未获取到客户详细信息");
			return resultInfo;
		}
		resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
		/*
		 * if (!resultInfo.isSuccess()) { return resultInfo; } Agent agent =
		 * (Agent)resultInfo.getObj();
		 */
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("custBaseInfoId", custBaseInfo.getCustBaseInfoId().toString());
			if (resultInfo.isSuccess()) {
				Agent agent = (Agent) resultInfo.getObj();
				paramMap.put("agentId", agent.getAgentId().toString());
			} else {
				paramMap.put("agentId", null);
			}
			paramMap.put("rcState", Constants.EFFECTIVE_RECORD);
			// 获取客户联信信息
			CustContactInfo custContactInfo = getCustContactInfo(paramMap);
			if (custContactInfo != null) {
				custInfoMap.put("custContactInfo", JsonUtils.objectToMap(custContactInfo));
			}
			// 获取客户地址信息
			List<Map> custAddressInfoList = getCustAddressInfoList(paramMap);
			if (custAddressInfoList != null && custAddressInfoList.size() > 0) {
				custInfoMap.put("custAddressInfoList", custAddressInfoList);
			}
			// 获取客户账户信息
			// List<CustAccInfo> custAccInfoList = getCustAccInfoList(paramMap);
			List<Map> custAccInfoList = getCustAccInfoList(paramMap);
			if (custAccInfoList != null && custAccInfoList.size() > 0) {
				// custInfoMap.put("custAccInfoList",
				// JsonUtils.listObjectToListMap(custAccInfoList));
				custInfoMap.put("custAccInfoList", custAccInfoList);
			}
			// 获取客户个人信息
			CustOthInfo custOthInfo = getCustPersonalInfo(paramMap);
			if (custOthInfo != null) {
				custInfoMap.put("custPersonalInfo", JsonUtils.objectToMap(custOthInfo));
			}
			// 获取客户爱好信息
			List<CustHobbyInfo> custHobbyInfoList = getCustHobbyInfoList(paramMap);
			if (custHobbyInfoList != null && custHobbyInfoList.size() > 0) {
				custInfoMap.put("custHobbyInfoList", JsonUtils.listObjectToListMap(custHobbyInfoList));
			}
			// 获取客户收入来源信息
			List<CustIncomeInfo> custIncomeInfoList = getCustIncomeInfoList(paramMap);
			if (custIncomeInfoList != null && custIncomeInfoList.size() > 0) {
				custInfoMap.put("custIncomeInfoList", JsonUtils.listObjectToListMap(custIncomeInfoList));
			}
			// 获取客户资产构成信息
			List<CustAssetInfo> custAssetInfoList = getCustAssetInfoList(paramMap);
			if (custAssetInfoList != null && custAssetInfoList.size() > 0) {
				custInfoMap.put("custAssetInfoList", JsonUtils.listObjectToListMap(custAssetInfoList));
			}
			// 获取客户投资信息
			List<CustInvestInfo> custInvestInfoList = getCustInvestInfoList(paramMap);
			if (custInvestInfoList != null && custInvestInfoList.size() > 0) {
				custInfoMap.put("custInvestInfoList", JsonUtils.listObjectToListMap(custInvestInfoList));
			}
			// 获取客户家庭成员信息
			// List<CustFamilyInfo> custFamilyInfoList =
			// getCustFamilyInfoList(paramMap);
			List<Map> custFamilyInfoList = getCustFamilyInfoList(paramMap);
			if (custFamilyInfoList != null && custFamilyInfoList.size() > 0) {
				// custInfoMap.put("custFamilyInfoList",
				// JsonUtils.listObjectToListMap(custFamilyInfoList));
				custInfoMap.put("custFamilyInfoList", custFamilyInfoList);
			}
			// 获取客户房产信息
			List<Map> custHouseInfoList = getCustHouseInfoList(paramMap);
			if (custHouseInfoList != null && custHouseInfoList.size() > 0) {
				custInfoMap.put("custHouseInfoList", custHouseInfoList);
			}
			// 获取客户车辆信息
			List<Map> custCarInfoList = getCustCarInfoList(paramMap);
			if (custCarInfoList != null && custCarInfoList.size() > 0) {
				custInfoMap.put("custCarInfoList", custCarInfoList);
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(custInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户详细信息出错");
		}
		return resultInfo;
	}

	/**
	 * 获取客户联系信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private CustContactInfo getCustContactInfo(Map paramMap) {
		try {
			CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
			CustContactInfoExample.Criteria criteria = custContactInfoExample.createCriteria();
			if (paramMap.containsKey("custBaseInfoId")) {
				criteria.andCustBaseInfoIdEqualTo(new Long((String) paramMap.get("custBaseInfoId")));
			}
			if (paramMap.containsKey("agentId")) {
				if (paramMap.get("agentId") != null) {
					criteria.andAgentIdEqualTo(new Long((String) paramMap.get("agentId")));
				} else {
					criteria.andAgentIdIsNull();
				}
			}
			if (paramMap.containsKey("rcState")) {
				criteria.andRcStateEqualTo((String) paramMap.get("rcState"));
			}
			List<CustContactInfo> custContactInfoList = custContactInfoMapper.selectByExample(custContactInfoExample);
			if (custContactInfoList != null && custContactInfoList.size() > 0) {
				return custContactInfoList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * @param paramMap
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo queryCustContactInfoList(Map paramMap, LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (paramMap == null) {
				paramMap = new HashMap();
			}
			paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
			paramMap.put("operComId", loginInfo.getComId().toString());
			paramMap.put("createUserId", loginInfo.getUserId().toString());
			List<Map<String, String>> contactInfoList = customerServiceMapper.queryCustContactInfoList(paramMap);
			resultInfo.setObj(contactInfoList);
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户联系信息列表出错");
			e.printStackTrace();
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultInfo queryCustContactDetailInfo(Map paramMap, LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (paramMap == null) {
				paramMap = new HashMap();
			}
			paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
			paramMap.put("operComId", loginInfo.getComId().toString());
			paramMap.put("createUserId", loginInfo.getUserId().toString());
			paramMap.put("rcState", Constants.EFFECTIVE_RECORD);

			CustContactInfo custContactInfo = getCustContactInfo(paramMap);
			if (custContactInfo != null) {
				map.put("custContactInfo", JsonUtils.objectToMap(custContactInfo));
			}
			List<Map> custAddressList = getCustAddressInfoList(paramMap);
			map.put("custAddressInfoList", custAddressList);
			resultInfo.setObj(map);
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户联系详细信息出错");
			e.printStackTrace();
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 获取客户地址信息
	 * 
	 * @param paramMap
	 * @return
	 */
	// private List<CustAddressInfo> getCustAddressInfoList(Map paramMap){
	@SuppressWarnings("rawtypes")
	private List<Map> getCustAddressInfoList(Map paramMap) {
		List<Map> custAddressInfoList = null;
		try {
			custAddressInfoList = customerServiceMapper.getCustAddressList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custAddressInfoList;
	}

	/**
	 * 获取客户账户信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Map> getCustAccInfoList(Map paramMap) {
		List<Map> custAccInfoList = null;
		try {
			custAccInfoList = customerServiceMapper.getCustAccList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custAccInfoList;
	}

	/**
	 * 获取客户个人信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private CustOthInfo getCustPersonalInfo(Map paramMap) {
		try {
			CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
			CustOthInfoExample.Criteria criteria = custOthInfoExample.createCriteria();
			if (paramMap.containsKey("custBaseInfoId")) {
				criteria.andCustBaseInfoIdEqualTo(new Long((String) paramMap.get("custBaseInfoId")));
			}
			if (paramMap.containsKey("rcState")) {
				criteria.andRcStateEqualTo((String) paramMap.get("rcState"));
			}
			List<CustOthInfo> custOthInfoList = custOthInfoMapper.selectByExample(custOthInfoExample);
			if (custOthInfoList != null && custOthInfoList.size() > 0) {
				return custOthInfoList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * 获取客户收入来源信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<CustHobbyInfo> getCustHobbyInfoList(Map paramMap) {
		List<CustHobbyInfo> custHobbyInfoList = null;
		try {
			CustHobbyInfoExample custHobbyInfoExample = new CustHobbyInfoExample();
			CustHobbyInfoExample.Criteria criteria = custHobbyInfoExample.createCriteria();
			if (paramMap.containsKey("custBaseInfoId")) {
				criteria.andCustBaseInfoIdEqualTo(new Long((String) paramMap.get("custBaseInfoId")));
			}
			if (paramMap.containsKey("rcState")) {
				criteria.andRcStateEqualTo((String) paramMap.get("rcState"));
			}
			custHobbyInfoList = custHobbyInfoMapper.selectByExample(custHobbyInfoExample);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custHobbyInfoList;
	}

	/**
	 * 获取客户收入来源信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<CustIncomeInfo> getCustIncomeInfoList(Map paramMap) {
		List<CustIncomeInfo> custIncomeInfoList = null;
		try {
			CustIncomeInfoExample custIncomeInfoExample = new CustIncomeInfoExample();
			CustIncomeInfoExample.Criteria criteria = custIncomeInfoExample.createCriteria();
			if (paramMap.containsKey("custBaseInfoId")) {
				criteria.andCustBaseInfoIdEqualTo(new Long((String) paramMap.get("custBaseInfoId")));
			}
			if (paramMap.containsKey("rcState")) {
				criteria.andRcStateEqualTo((String) paramMap.get("rcState"));
			}
			custIncomeInfoList = custIncomeInfoMapper.selectByExample(custIncomeInfoExample);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custIncomeInfoList;
	}

	/**
	 * 获取客户资产构成信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<CustAssetInfo> getCustAssetInfoList(Map paramMap) {
		List<CustAssetInfo> custAssetInfoList = null;
		try {
			CustAssetInfoExample custAssetInfoExample = new CustAssetInfoExample();
			CustAssetInfoExample.Criteria criteria = custAssetInfoExample.createCriteria();
			if (paramMap.containsKey("custBaseInfoId")) {
				criteria.andCustBaseInfoIdEqualTo(new Long((String) paramMap.get("custBaseInfoId")));
			}
			if (paramMap.containsKey("rcState")) {
				criteria.andRcStateEqualTo((String) paramMap.get("rcState"));
			}
			custAssetInfoList = custAssetInfoMapper.selectByExample(custAssetInfoExample);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custAssetInfoList;
	}

	/**
	 * 获取客户投资构成信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<CustInvestInfo> getCustInvestInfoList(Map paramMap) {
		List<CustInvestInfo> custInvestInfoList = null;
		try {
			CustInvestInfoExample custInvestInfoExample = new CustInvestInfoExample();
			CustInvestInfoExample.Criteria criteria = custInvestInfoExample.createCriteria();
			if (paramMap.containsKey("custBaseInfoId")) {
				criteria.andCustBaseInfoIdEqualTo(new Long((String) paramMap.get("custBaseInfoId")));
			}
			if (paramMap.containsKey("rcState")) {
				criteria.andRcStateEqualTo((String) paramMap.get("rcState"));
			}
			custInvestInfoList = custInvestInfoMapper.selectByExample(custInvestInfoExample);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custInvestInfoList;
	}

	/**
	 * 获取客户家庭成员信息
	 * 
	 * @param paramMap
	 * @return
	 */
	// private List<CustFamilyInfo> getCustFamilyInfoList(Map paramMap){
	@SuppressWarnings("rawtypes")
	private List<Map> getCustFamilyInfoList(Map paramMap) {
		List<Map> custFamilyInfoList = null;
		try {
			custFamilyInfoList = customerServiceMapper.getCustFamilyList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custFamilyInfoList;
	}

	/**
	 * 获取客户房产信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Map> getCustHouseInfoList(Map paramMap) {
		List<Map> custHouseInfoList = null;
		try {
			custHouseInfoList = customerServiceMapper.getCustHouseList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custHouseInfoList;
	}

	/**
	 * 获取客户车辆信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Map> getCustCarInfoList(Map paramMap) {
		List<Map> custCarInfoList = null;
		try {
			custCarInfoList = customerServiceMapper.getCustCarList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return custCarInfoList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryCustomerList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		// paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		//查询agentId 若为rm则查名下客户
		Long userId = loginInfo.getUserId();
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
		List<Agent> agents = agentMapper.selectByExample(agentExample);
		if (!agents.isEmpty()) {
			paramMap.put("agentId", agents.get(0).getAgentId().toString());
		}
		Integer total = customerServiceMapper.queryCustomerListCount(paramMap);
		List<Map<String, String>> resultList = customerServiceMapper.queryCustomerList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	/**
	 * 客户手机信息导出菜单；初始化查询所有客户
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryAllCustomerList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		// paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		/*//查询agentId 若为rm则查名下客户
		Long userId = loginInfo.getUserId();
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
		List<Agent> agents = agentMapper.selectByExample(agentExample);
		if (!agents.isEmpty()) {
			paramMap.put("agentId", agents.get(0).getAgentId().toString());
		}*/
		Integer total = customerServiceMapper.queryCustomerListCount(paramMap);
		List<Map<String, String>> resultList = customerServiceMapper.queryCustomerList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@Override
	public ResultInfo getCustBaseInfo(CustBaseInfo custBaseInfo, String getType) {
		ResultInfo resultInfo = new ResultInfo();
		if (getType == null || "".equals(getType)) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询类型不能为空");
		}
		try {
			// 根据客户信息流水号查询客户基本信息
			if ("01".equals(getType)) {
				custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfo.getCustBaseInfoId());
			}
			// 根据客户五要素信息查询客户
			else if ("02".equals(getType)) {
				CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();
				CustBaseInfoExample.Criteria criteria = custBaseInfoExample.createCriteria();
				criteria.andChnNameEqualTo(custBaseInfo.getChnName()).andSexEqualTo(custBaseInfo.getSex())
						.andBirthdayEqualTo(custBaseInfo.getBirthday()).andIdTypeEqualTo(custBaseInfo.getIdType())
						.andIdNoEqualTo(custBaseInfo.getIdNo());
				List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample);
				if (custBaseInfoList != null && custBaseInfoList.size() > 0) {
					custBaseInfo = custBaseInfoList.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户基本信息出错");
		}
		resultInfo.setSuccess(true);
		resultInfo.setObj(JsonUtils.objectToMap(custBaseInfo));
		return resultInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultInfo verifyCustomerTradeInfo(String custBaseInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map paramMap = new HashMap();
			paramMap.put("custBaseInfoId", custBaseInfoId);
			Integer tradeCount = customerServiceMapper.queryCustTradeCount(paramMap);
			if (tradeCount > 0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("当前客户有正在处理中的交易，不能修改客户信息！");
			} else {
				resultInfo.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("判断当前客户是否有处理中的交易信息出错");
		}
		return resultInfo;
	}

	/**
	 * @param customerInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public ResultInfo saveCustAllInfo(String customerInfo, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			String operate = JsonUtils.getJsonValueByKey("operate", customerInfo);
			String agentId = JsonUtils.getJsonValueByKey("agentId", customerInfo);
			// 获取财富顾问信息
			Agent agent = new Agent();
			if (agentId != null && !"".equals(agentId)) {
				// agent.setAgentId(new BigDecimal(agentId));
				agent = agentMapper.selectByPrimaryKey(new Long(agentId));
			} else {
				resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
				agent = (Agent) resultInfo.getObj();
			}
			// 获取客户基本信息
			String custBaseInfoStr = JsonUtils.getJsonValueByKey("custBaseInfo", customerInfo);
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			custBaseInfo = (CustBaseInfo) JsonUtils.jsonStrToObject(custBaseInfoStr, custBaseInfo);
			if (custBaseInfo == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("客户基本信息为空，请先新增客户基本信息！");
				return resultInfo;
			}
			if (custBaseInfo.getCustBaseInfoId() == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("客户基本信息未保存，请先保存客户基本信息！");
				return resultInfo;
			}
			// 判断客户是否已经归属于某个财富顾问
			custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfo.getCustBaseInfoId());
			resultInfo = verifyCustAgentInfo(custBaseInfo, agent);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}

			// 获取客户联系信息
			String custContactInfoStr = JsonUtils.getJsonValueByKey("custContactInfo", customerInfo);
			custContactInfoStr = JsonUtils.decodeUrlParams(custContactInfoStr, "utf-8");
			CustContactInfo custContactInfo = (CustContactInfo) JsonUtils.jsonStrToObject(custContactInfoStr,
					CustContactInfo.class);
			if (custContactInfo != null) {
				resultInfo = saveCustContactInfo(custBaseInfo, custContactInfo, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}

			// 获取客户地址信息
			String custAddressListStr = JsonUtils.getJsonValueByKey("custAddressList", customerInfo);
			List<CustAddressInfo> custAddressInfoList = JsonUtils.jsonArrStrToList(custAddressListStr,
					CustAddressInfo.class);
			if (custAddressInfoList != null && custAddressInfoList.size() > 0) {
				resultInfo = saveCustAddressInfo(custBaseInfo, custAddressInfoList, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}

			// 获取账户信息
			String custAccInfoListStr = JsonUtils.getJsonValueByKey("custAccInfo", customerInfo);
			List<CustAccInfo> custAccInfoList = JsonUtils.jsonArrStrToList(custAccInfoListStr, CustAccInfo.class);
			if (custAccInfoList != null && custAccInfoList.size() >= 0) {
				resultInfo = saveCustAccInfo(custBaseInfo, custAccInfoList, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
			// 获取客户个人信息
			String custPersonalInfo = JsonUtils.getJsonValueByKey("custPersonalInfo", customerInfo);
			CustOthInfo custOthInfo = (CustOthInfo) JsonUtils.jsonStrToObject(custPersonalInfo, CustOthInfo.class);
			if (custOthInfo != null) {
				resultInfo = saveCustOthInfo(custBaseInfo, custOthInfo, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
			// 获取客户爱好信息
			String custHobbyListStr = JsonUtils.getJsonValueByKey("hobbyInfo", customerInfo);
			List<CustHobbyInfo> custHobbyInfoList = JsonUtils.jsonArrStrToList(custHobbyListStr, CustHobbyInfo.class);
			if (custHobbyInfoList != null && custHobbyInfoList.size() >= 0) {
				resultInfo = saveCustHobbyInfo(custBaseInfo, custHobbyInfoList, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
			// 获取客户收入来源信息
			String incomeListStr = JsonUtils.getJsonValueByKey("incomeList", customerInfo);
			List<CustIncomeInfo> custIncomeInfoList = JsonUtils.jsonArrStrToList(incomeListStr, CustIncomeInfo.class);
			if (custIncomeInfoList != null && custIncomeInfoList.size() >= 0) {
				resultInfo = saveCustIncomeInfo(custBaseInfo, custIncomeInfoList, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
			// 获取客户资产构成信息
			String assetList = JsonUtils.getJsonValueByKey("assetList", customerInfo);
			List<CustAssetInfo> custAssetInfoList = JsonUtils.jsonArrStrToList(assetList, CustAssetInfo.class);
			if (custAssetInfoList != null && custAssetInfoList.size() >= 0) {
				resultInfo = saveCustAssetInfo(custBaseInfo, custAssetInfoList, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
			// 获取客户投资金融产品类型信息
			String investList = JsonUtils.getJsonValueByKey("investList", customerInfo);
			List<CustInvestInfo> custInvestInfoList = JsonUtils.jsonArrStrToList(investList, CustInvestInfo.class);
			if (custInvestInfoList != null && custInvestInfoList.size() >= 0) {
				resultInfo = saveCustInvestInfo(custBaseInfo, custInvestInfoList, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
			// 获取客户家庭信息
			String familyInfoList = JsonUtils.getJsonValueByKey("familyInfoList", customerInfo);
			List<CustFamilyInfo> custFamilyInfoList = JsonUtils.jsonArrStrToList(familyInfoList, CustFamilyInfo.class);
			if (custFamilyInfoList != null && custFamilyInfoList.size() >= 0) {
				resultInfo = saveCustFamilyInfo(custBaseInfo, custFamilyInfoList, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
			// 获取客户房产信息
			String houseInfoList = JsonUtils.getJsonValueByKey("houseInfoList", customerInfo);
			List<CustHouseInfo> custHouseInfoList = JsonUtils.jsonArrStrToList(houseInfoList, CustHouseInfo.class);
			if (custHouseInfoList != null && custHouseInfoList.size() >= 0) {
				resultInfo = saveCustHouseInfo(custBaseInfo, custHouseInfoList, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
			// 获取客户车辆信息
			String carInfoList = JsonUtils.getJsonValueByKey("carInfoList", customerInfo);
			List<CustCarInfo> custCarInfoList = JsonUtils.jsonArrStrToList(carInfoList, CustCarInfo.class);
			if (custCarInfoList != null && custCarInfoList.size() >= 0) {
				resultInfo = saveCustCarInfo(custBaseInfo, custCarInfoList, agent, loginInfo, operate);
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("客户信息保存出错！");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("客户信息保成功！");
		return resultInfo;
	}

	@SuppressWarnings("rawtypes")
	public ResultInfo getCustByAgentInfo(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			Integer count = customerServiceMapper.getCustByAgentInfo(paramMap);
			if (count > 0) {
				resultInfo.setSuccess(true);
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该客户不是您的客户或客户联系信息不完整！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到财富顾问相关客户信息");
		}
		return resultInfo;
	}

	/**
	 * 判断客户是否已归属于某个理财经理
	 * 
	 * @return
	 */
	private ResultInfo verifyCustAgentInfo(CustBaseInfo custBaseInfo, Agent agent) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			Long existAgentId = custBaseInfo.getAgentId();
			if (existAgentId != null && existAgentId.compareTo(agent.getAgentId()) != 0) {
				Agent existAgent = agentMapper.selectByPrimaryKey(existAgentId);
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该客户已经归属理财经理(" + existAgent.getAgentName() + "),您不能再维护此客户信息或对此客户进行交易！");
				resultInfo.setObj(custBaseInfo);
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	@Override
	public ResultInfo verifyCustBelongAgent(CustBaseInfo custBaseInfo, Agent agent, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 获取财富顾问信息
			if (agent == null) {
				agent = new Agent();
			}
			if (agent.getAgentId() != null) {
				agent = agentMapper.selectByPrimaryKey(agent.getAgentId());
			} else {
				resultInfo = getAgentInfoByUserId(loginInfo.getUserId());
				if (!resultInfo.isSuccess()) {
					return resultInfo;
				}
				agent = (Agent) resultInfo.getObj();
			}
			custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfo.getCustBaseInfoId());
			resultInfo = verifyCustAgentInfo(custBaseInfo, agent);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			return resultInfo;
		}
		return resultInfo;
	}

	/**
	 * 根据客户Id查询客户资产信息
	 * 
	 * @param customerNoId
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public ResultInfo getCustAccountInfo(String customerNoId) {
		ResultInfo resultInfo = new ResultInfo();
		String custChnName = "";
		// 1,根据客户ID获取客户姓名
		CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();
		CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
		custBaseInfoCriteria.andCustomerNoEqualTo(customerNoId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample);
		if (custBaseInfoList != null && custBaseInfoList.size() > 0) {
			CustBaseInfo custBaseInfo = custBaseInfoList.get(0);
			custChnName = custBaseInfo.getChnName();
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("不存在该客户！");
			return resultInfo;
		}
		// 2,获取客户投资总额和在投资产金额
		resultInfo = getCustAllTradeInfo(customerNoId);
		Map<String, Object> wealthPdAmountMap = (HashMap<String, Object>) resultInfo.getObj();
		wealthPdAmountMap.put("custChnName", custChnName);
		resultInfo.setSuccess(true);
		resultInfo.setObj(wealthPdAmountMap);
		return resultInfo;
	}

	/**
	 * 获取客户所有的交易信息
	 * 
	 * @return
	 */

	private ResultInfo getCustAllTradeInfo(String customerNoId) {
		ResultInfo resultInfo = new ResultInfo();
		List<TradeInfo> tradeInfoList = new ArrayList<TradeInfo>();
		Map<String, Object> customerIdMap = new HashMap<String, Object>();
		customerIdMap.put("customerId", customerNoId);
		List<Map<String, Object>> tradeInfoMapList = customerServiceMapper.getTradeInfoList(customerIdMap);
		if (tradeInfoMapList != null && tradeInfoMapList.size() > 0) {
			for (Map<String, Object> map : tradeInfoMapList) {
				TradeInfo tradeInfo = new TradeInfo();
				tradeInfo.setTradeInfoId(new Long(map.get("TRADE_INFO_ID").toString()));
				tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeInfo.getTradeInfoId());
				tradeInfoList.add(tradeInfo);
			}
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该用户没有交易信息！");
			return resultInfo;
		}
		resultInfo = calWealthTrade(tradeInfoList);
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 计算客户的资产信息
	 * 
	 * @param tradeInfoList
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private ResultInfo calWealthTrade(List<TradeInfo> tradeInfoList) {
		ResultInfo resultInfo = new ResultInfo();
		BigDecimal custInvestingTotal = new BigDecimal(0.00);// 在投金额
		BigDecimal expectedProfitAmountTotal = new BigDecimal(0.00);// 预期收益
		BigDecimal custAssetsAmount = new BigDecimal(0.00);// 投资总额
		BigDecimal custinterestTotal = new BigDecimal(0.00);// 总额收益
		BigDecimal custPrincipalTotal = new BigDecimal(0.00);// 持有本金
		// 循环处理客户所有的交易
		for (TradeInfo tradeInfo : tradeInfoList) {
			// 1.财富产品；2.保险
			String tradeType = tradeInfo.getTradeType();
			if (tradeType != null && "1".equals(tradeType)) {
				// 1.判断产品类型
				TradeProductInfoExample tradeProductInfoExample = new TradeProductInfoExample();
				TradeProductInfoExample.Criteria tradeProductInfoCriteria = tradeProductInfoExample.createCriteria();
				tradeProductInfoCriteria.andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId())
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeProductInfo> tradeProductInfoList = tradeProductInfoMapper
						.selectByExample(tradeProductInfoExample);
				if (tradeProductInfoList != null && tradeProductInfoList.size() > 0) {
					for (TradeProductInfo tradeProductInfo : tradeProductInfoList) {
						String paramCode = tradeProductInfo.getParamCode();
						// 获取客户投资总额
						if (paramCode.equals("subscriptionFee")) {
							custAssetsAmount = custAssetsAmount.add(new BigDecimal(tradeProductInfo.getParamValue()));
						}
					}
					Long productId = tradeProductInfoList.get(0).getProductId();
					// 2.获取产品信息
					PDProduct pdProduct = pdProductMapper.selectByPrimaryKey(productId);
					// 产品类型1.财富2.保险
					String productType = pdProduct.getProductType();
					// 产品子类01-股权类；02-固定收益类；03-浮动收益类
					String productSubType = pdProduct.getProductSubType();
					if (productType != null && !"".equals(productType) && "1".equals(productType)
							&& productSubType != null && !"".equals(productSubType) && "02".equals(productSubType)) {
						// 计算固收类产品的收益总额
						PdIncomeDisDetailExample pdIncomeDisDetailExample = new PdIncomeDisDetailExample();
						PdIncomeDisDetailExample.Criteria pdIncomeDisDetailCriteria = pdIncomeDisDetailExample
								.createCriteria();
						pdIncomeDisDetailCriteria.andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						List<PdIncomeDisDetail> pdIncomeDisDetailList = pdIncomeDisDetailMapper
								.selectByExample(pdIncomeDisDetailExample);
						if (pdIncomeDisDetailList != null && pdIncomeDisDetailList.size()>0) {
							for (PdIncomeDisDetail pdIncomeDisDetail : pdIncomeDisDetailList) {
								BigDecimal confirmDistributeInterestDecimal = pdIncomeDisDetail
										.getConfirmDistributeInterest();
								if (confirmDistributeInterestDecimal == null) {
									confirmDistributeInterestDecimal = new BigDecimal(0);
								} else {
									custinterestTotal = custinterestTotal.add(confirmDistributeInterestDecimal);
								}
							}
						}
						resultInfo = calFixedWealthTradeInfo(tradeInfo, tradeProductInfoList, pdProduct,
								pdIncomeDisDetailList);
						Map<String, Object> subscriptionFeeMap = (HashMap<String, Object>) resultInfo.getObj();
						// 计算在投资产
						BigDecimal subscriptionFee = new BigDecimal(
								subscriptionFeeMap.get("subscriptionFee").toString());
						custInvestingTotal = custInvestingTotal.add(subscriptionFee);
						// 计算预期收益
						BigDecimal expectedProfitAmountBigDecimal = new BigDecimal(
								subscriptionFeeMap.get("expectedProfitAmountBigDecimal").toString());
						expectedProfitAmountTotal = expectedProfitAmountTotal.add(expectedProfitAmountBigDecimal);
						// 获取持有本金
						custPrincipalTotal = custPrincipalTotal
								.add(new BigDecimal(subscriptionFeeMap.get("custPrincipal").toString()));
					} else if (productType != null && !"".equals(productType) && "1".equals(productType)
							&& ((productSubType != null && !"".equals(productSubType) && "01".equals(productSubType))
									|| (productSubType != null && !"".equals(productSubType)
											&& "03".equals(productSubType)))) {
						resultInfo = calFloatWealthTradeInfo(tradeInfo, tradeProductInfoList, pdProduct);
						Map<String, Object> floatPdInterestMap = (HashMap<String, Object>) resultInfo.getObj();
						BigDecimal floatscriptionFee = new BigDecimal(
								floatPdInterestMap.get("floatscriptionFee").toString());
						custInvestingTotal = custInvestingTotal.add(floatscriptionFee);
						custinterestTotal = custinterestTotal
								.add((new BigDecimal(floatPdInterestMap.get("floatPdInterest").toString())));

					}
				} else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("该客户没有对应的交易产品记录！");
					return resultInfo;
				}

			} else {
				// 保险暂时不作统计
			}

		}
		Map<String, Object> wealthPdAmountMap = new HashMap<String, Object>();
		wealthPdAmountMap.put("custInvestingTotal", custInvestingTotal);
		wealthPdAmountMap.put("expectedProfitAmountTotal", expectedProfitAmountTotal);
		wealthPdAmountMap.put("custAssetsAmount", custAssetsAmount);
		wealthPdAmountMap.put("custinterestTotal", custinterestTotal);
		wealthPdAmountMap.put("custPrincipalTotal", custPrincipalTotal);
		resultInfo.setSuccess(true);
		resultInfo.setObj(wealthPdAmountMap);

		return resultInfo;
	}

	/**
	 * 计算客户固定收益类产品的在投金额
	 * 
	 * @param tradeInfo
	 * @return
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	private ResultInfo calFixedWealthTradeInfo(TradeInfo tradeInfo, List<TradeProductInfo> tradeProductInfoList,
			PDProduct pdProduct, List<PdIncomeDisDetail> pdIncomeDisDetailList) {
		ResultInfo resultInfo = new ResultInfo();
		BigDecimal subscriptionFee = new BigDecimal(0);
		double expectedProfitAmount = 0;
		BigDecimal custPrincipal = new BigDecimal(0);
		try {
			// 1.判断交易是否处于产品期限内
			PDWealthExample pdWealthExample = new PDWealthExample();
			PDWealthExample.Criteria pdWealthCriteria = pdWealthExample.createCriteria();
			pdWealthCriteria.andProductIdEqualTo(pdProduct.getProductId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDWealth> pdWealthList = pdWealthMapper.selectByExample(pdWealthExample);
			if (pdWealthList != null && pdWealthList.size() > 0) {
				PDWealth pdWealth = pdWealthList.get(0);
				// 产品封闭期
				String closedPeriord = "";
				// 产品封闭期单位
				String closedPeriordUnit = pdWealth.getCloseDperiodUnit();
				// 获取产品的封闭期和封闭期单位
				for (TradeProductInfo tradeProductInfo : tradeProductInfoList) {
					String paramCode = tradeProductInfo.getParamCode();
					if (paramCode.equals("closedPeriods")) {
						closedPeriord = tradeProductInfo.getParamValue();
					}
				}
				if (closedPeriord == null || "".equals(closedPeriord)) {
					closedPeriord = pdWealth.getCloseDperiods();
				}
				// 产品成立日期
				String foundDate = DateUtils.getDateString(pdWealth.getFoundDate());
				// 计算产品到期日
				String tradeEndDate = DateUtils.calDate(foundDate, Integer.parseInt(closedPeriord), closedPeriordUnit);
				tradeEndDate = DateUtils.calDate(tradeEndDate, -1, "D");
				// 系统当前日期
				String currentDate = DateUtils.getCurrentDate();
				if ((Date.valueOf(tradeEndDate)).after(Date.valueOf(currentDate))) {
					for (TradeProductInfo tradeProductInfo : tradeProductInfoList) {
						String paramCode = tradeProductInfo.getParamCode();
						if (paramCode != null && !"".equals(paramCode) && paramCode.equals("subscriptionFee")) {
							// 计算客户固定收益类产品的在投金额
							subscriptionFee = new BigDecimal(tradeProductInfo.getParamValue());
							// 获取固定收益率
							PDWealthFeeRateExample pdWealthFeeRateExample = new PDWealthFeeRateExample();
							PDWealthFeeRateExample.Criteria pdWealthFeeRateCriteria = pdWealthFeeRateExample
									.createCriteria();
							pdWealthFeeRateCriteria.andPdWealthIdEqualTo(pdWealth.getPdWealthId())
									.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
							List<PDWealthFeeRate> pdWealthFeeRateList = pdWealthFeeRateMapper
									.selectByExample(pdWealthFeeRateExample);
							String expectedFeeRateTotal = "";
							if (pdWealthFeeRateList != null && pdWealthFeeRateList.size() > 0) {
								for (PDWealthFeeRate pdWealthFeeRate : pdWealthFeeRateList) {
									BigDecimal subscriptionFeeLower = (pdWealthFeeRate
											.getSubscriptionFeeLower() == null ? new BigDecimal(0)
													: pdWealthFeeRate.getSubscriptionFeeLower());
									BigDecimal subscriptionFeeUpper = (pdWealthFeeRate
											.getSubscriptionFeeUpper() == null ? new BigDecimal(999999999)
													: pdWealthFeeRate.getSubscriptionFeeUpper());
									if (subscriptionFee.doubleValue() >= subscriptionFeeLower.doubleValue()
											&& subscriptionFeeUpper.doubleValue() > subscriptionFee.doubleValue()) {
										BigDecimal expectedFeeRate = pdWealthFeeRate.getExpectedFeeRate()==null?new BigDecimal(0):pdWealthFeeRate.getExpectedFeeRate();
										BigDecimal addexpectedFeeRate = pdWealthFeeRate.getAddExpectedFeeRate()==null?new BigDecimal(0):pdWealthFeeRate.getAddExpectedFeeRate();
										expectedFeeRateTotal = (expectedFeeRate.add(addexpectedFeeRate)).toString();
									}

								}
							}
							// 获取投资年限
							if(expectedFeeRateTotal!=null&&!"".equals(expectedFeeRateTotal)){
								if (closedPeriordUnit == null && "".equals(closedPeriordUnit)) {
									resultInfo.setSuccess(false);
								} else if (closedPeriordUnit.equals("Y")) {
									// 计算客户固定收益:在投金额x固定收益率x获取投资年限
									expectedProfitAmount = NumberUtils.multiply(
											NumberUtils.multiply(subscriptionFee + "", expectedFeeRateTotal) + "",
											closedPeriord) / 100;
								} else if (closedPeriordUnit.equals("M")) {
									expectedProfitAmount = NumberUtils.multiply(
											NumberUtils.multiply(subscriptionFee + "", expectedFeeRateTotal) + "",
											closedPeriord) / 100 / 12;

								} else {
									expectedProfitAmount = NumberUtils.multiply(
											NumberUtils.multiply(subscriptionFee + "", expectedFeeRateTotal) + "",
											closedPeriord) / 100 / 365;
								}
							}
							// 计算客户固收在投的本金(开始投资金额-分配金额)
							// 获取分配金额
							expectedProfitAmount = NumberUtils.setPrecision(expectedProfitAmount, "0.00");
							BigDecimal conFirmDisTributePrincipal = new BigDecimal(0);
							if (pdIncomeDisDetailList != null && pdIncomeDisDetailList.size() > 0) {
								for (PdIncomeDisDetail pdIncomeDisDetail : pdIncomeDisDetailList) {
									conFirmDisTributePrincipal = pdIncomeDisDetail.getConfirmDistributePrincipal();
									if (conFirmDisTributePrincipal == null) {
										conFirmDisTributePrincipal = new BigDecimal(0);
									}
									custPrincipal = subscriptionFee.subtract(conFirmDisTributePrincipal);

								}
							} else {
								custPrincipal = subscriptionFee.subtract(conFirmDisTributePrincipal);
							}

						}
					}
				}

			}
			BigDecimal expectedProfitAmountBigDecimal = new BigDecimal(expectedProfitAmount);
			expectedProfitAmountBigDecimal = NumberUtils.setPrecision(expectedProfitAmountBigDecimal, "0.00");
			resultInfo.setSuccess(true);
			Map<String, Object> subscriptionFeeMap = new HashMap<String, Object>();
			subscriptionFeeMap.put("subscriptionFee", subscriptionFee);
			subscriptionFeeMap.put("expectedProfitAmountBigDecimal", expectedProfitAmountBigDecimal);
			subscriptionFeeMap.put("custPrincipal", custPrincipal);
			resultInfo.setObj(subscriptionFeeMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("计算预期收益出现异常！");
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}

	/**
	 * 计算客户浮动收益类产品的在投金额和收益
	 *   
	 * @param tradeInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ResultInfo calFloatWealthTradeInfo(TradeInfo tradeInfo, List<TradeProductInfo> tradeProductInfoList,
			PDProduct pdProduct) {
		ResultInfo resultInfo = new ResultInfo();
		BigDecimal floatPdInterest = new BigDecimal(0);
		BigDecimal floatscriptionFee = new BigDecimal(0);
		Map<String, Object> floatPdInterestMap = new HashMap<String, Object>();
		try {
			// 判断该产品是否赎回
			// 根据产品ID得到赎回信息
			RedemptionInfoExample redemptionInfoExample = new RedemptionInfoExample();
			RedemptionInfoExample.Criteria redemptionInfoCriteria = redemptionInfoExample.createCriteria();
			redemptionInfoCriteria.andPdProductIdEqualTo(pdProduct.getProductId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<RedemptionInfo> redemptionInfoList = redemptionInfoMapper.selectByExample(redemptionInfoExample);
			if (redemptionInfoList != null && redemptionInfoList.size() > 0) {
				RedemptionInfo redemptionInfo = redemptionInfoList.get(0);
				// 根据赎回状态判断是否赎回
				String redemaptionStatus = redemptionInfo.getRedemptionStatus();
				if (redemaptionStatus != null && !"".equals(redemaptionStatus) && redemaptionStatus.equals("04")) {
					// 获取最近净值
					@SuppressWarnings("rawtypes")
					Map productIdMap = new HashMap();
					productIdMap.put("productId", pdProduct.getProductId());
					Map<String, Object> netValueMap = customerServiceMapper.getNetValue(productIdMap);
					BigDecimal netValue = new BigDecimal(netValueMap.get("netValue").toString());
					// 获取剩余份额，再和最近的净值相乘就得到浮动类产品的在投资产
					BigDecimal remainingTltalShare = redemptionInfo.getRemainingTotalShare();
					// 计算浮动产品的在投资产
					floatscriptionFee = remainingTltalShare.multiply(netValue);
					// 获取赎回的份数
					BigDecimal alreadyRedempTionToTalShare = redemptionInfo.getAlreadyRedemptionTotalShare();
					// 获取初始净值
					String expectOpenDay = "";
					for (TradeProductInfo tradeProductInfo : tradeProductInfoList) {
						String paramCode = tradeProductInfo.getParamCode();
						if (paramCode.equals("expectOpenDay")) {
							expectOpenDay = tradeProductInfo.getParamValue();
						}
					}
					PDWealthNetValueExample pdWealthNetValueExample = new PDWealthNetValueExample();
					PDWealthNetValueExample.Criteria pdWealthNetValueCriteria = pdWealthNetValueExample
							.createCriteria();
					pdWealthNetValueCriteria.andPublicDateEqualTo(Date.valueOf(expectOpenDay))
							.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<PDWealthNetValue> pdWealthNetValueList = pdWealthNetValueMapper
							.selectByExample(pdWealthNetValueExample);
					if (pdWealthNetValueList != null && pdWealthNetValueList.size() > 0) {
						BigDecimal startNetValue = pdWealthNetValueList.get(0).getNetValue();
						// 计算浮动产品的收益（已赎回的份数x（最近净值-初始净值））
						floatPdInterest = alreadyRedempTionToTalShare.multiply((netValue.subtract(startNetValue)));
					}

				} else if (redemaptionStatus != null && !"".equals(redemaptionStatus) || redemaptionStatus.equals("01")
						|| redemaptionStatus.equals("02") || redemaptionStatus.equals("03")
						|| redemaptionStatus.equals("05")) {
					// 没有赎回，直接根据交易产品信息获取在投资产
					for (TradeProductInfo tradeProductInfo : tradeProductInfoList) {
						String paramCode = tradeProductInfo.getParamCode();
						if (paramCode != null && !"".equals(paramCode) && paramCode.equals("subscriptionFee")) {
							floatscriptionFee = new BigDecimal(tradeProductInfo.getParamValue());
						}
					}
				}

			} else {
				// 没有赎回信息,直接根据交易产品信息获取在投资产
				for (TradeProductInfo tradeProductInfo : tradeProductInfoList) {
					String paramCode = tradeProductInfo.getParamCode();
					if (paramCode != null && !"".equals(paramCode) && paramCode.equals("subscriptionFee")) {
						floatscriptionFee = new BigDecimal(tradeProductInfo.getParamValue());
					}
				}
			}
			resultInfo.setSuccess(true);
			floatPdInterestMap.put("floatPdInterest", floatPdInterest);
			floatPdInterestMap.put("floatscriptionFee", floatscriptionFee);
			resultInfo.setObj(floatPdInterestMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("计算浮动产品的在投金额和收益出现异常！");
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}

	/**
	 * 插入客户信息
	 * 
	 * @throws ParseException
	 */
	@Transactional
	@Override
	@SuppressWarnings("rawtypes")
	public ResultInfo insertCustInfo(Map xmleWealthContentMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			String agentCode = xmleWealthContentMap.get("agentCode").toString();
			if(agentCode.equals("99999")){
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
			if(agentList==null||agentList.size()==0){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("系统不存在该理财师工号对应的财富顾问！");
			}
			String agentId = agentList.get(0).getAgentId().toString();
			Long userId = agentMapper.selectByExample(agentExample).get(0).getUserId();
			Long comId = agentMapper.selectByExample(agentExample).get(0).getComId();
			loginInfo.setUserId(userId);
			loginInfo.setComId(comId);
			String operate = "";
			Agent agent = new Agent();
			if (agentId != null && !"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			// 插入客户基本信息
			CustBaseInfo custBaseInfo = new CustBaseInfo();
			custBaseInfo.setChnName(xmleWealthContentMap.get("customerName").toString());
			custBaseInfo.setIdType("01");
			String cardNo = xmleWealthContentMap.get("cardNo").toString();
			custBaseInfo.setIdNo(cardNo);
			custBaseInfo.setSex(StringUtils.getSex(cardNo));
			custBaseInfo.setBirthday(DateUtils.getDate1(StringUtils.getBirthday(cardNo)));
			custBaseInfo.setAgentId(new Long(agentId));
			custBaseInfo.setIdValidityDate(DateUtils.getDate(xmleWealthContentMap.get("cardNoDate").toString()));
			saveCustomerBaseInfo(custBaseInfo, agentId, loginInfo);
			//插入客户联系信息
			CustContactInfo custContactInfo = new CustContactInfo();
			custContactInfo.setEmail(xmleWealthContentMap.get("email").toString());
			custContactInfo.setMobile(xmleWealthContentMap.get("mobile").toString());
			custContactInfo.setAgentId(new Long(agentId));
			saveCustContactInfo(custBaseInfo, custContactInfo, agent, loginInfo, operate);
			//插入客户账户信息
			CustAccInfo custAccInfo = new CustAccInfo();
			String bankNo = xmleWealthContentMap.get("bankNo").toString();
			if(!bankNo.equals("99999")){
				custAccInfo.setBankAccNo(bankNo);
			}
			String openBankName = xmleWealthContentMap.get("openBankName").toString();
			if(!openBankName.equals("99999")){
				custAccInfo.setBankCode(openBankName);
			}
			String openBranchName = xmleWealthContentMap.get("openBranchName").toString();
			if(!openBranchName.equals("99999")){
				custAccInfo.setBranchBankName(openBranchName);
			}
			List<CustAccInfo> custAccInfoList = new ArrayList<CustAccInfo>();
			custAccInfoList.add(custAccInfo);
			saveCustAccInfo(custBaseInfo, custAccInfoList, agent, loginInfo, operate);
			// 插入客户地址信息
			CustAddressInfo custAddressInfo = new CustAddressInfo();
			custAddressInfo.setEmail(xmleWealthContentMap.get("email").toString());
			custAddressInfo.setMobile(xmleWealthContentMap.get("mobile").toString());
			custAddressInfo.setAgentId(new Long(agentId));
			List<CustAddressInfo> custAddressInfoList = new ArrayList<CustAddressInfo>();
			custAddressInfoList.add(custAddressInfo);
			saveCustAddressInfo(custBaseInfo, custAddressInfoList, agent, loginInfo, operate);
			CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();
			CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
			custBaseInfoCriteria.andIdTypeEqualTo("01").andIdNoEqualTo(cardNo)
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample);
			String customerNo = "";
			if (custBaseInfoList != null && custBaseInfoList.size() > 0) {
				customerNo = custBaseInfoList.get(0).getCustomerNo();
			}
			resultInfo.setObj(customerNo);
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户信息失败");
			return resultInfo;
		}
		return resultInfo;
	}

	/**
	 * 根据客户Id查询财富产品信息
	 * 
	 * @param xmleWealthContentMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo getWealthPdInfo(Map xmleWealthContentMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map> WealthPdInfoList = customerServiceMapper.getWealthPdInfoList(xmleWealthContentMap);
			resultInfo.setObj(WealthPdInfoList);
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("不存在该客户号对应的财富产品信息！");
			resultInfo.setSuccess(false);
		}

		return resultInfo;
	}
////////////////////////////////////////客户归属调整功能业务层处理实现方法////////////////////////////////////////////
	/**
	 * 根据客户流水号查询客户历史归属分页
	 * 
	 * @return DataGrid
	 * @author zhangjie
	 * @param
	 */
	public DataGrid queryCustHistoryBelongInfo(DataGridModel dgm, Map queryMap, LoginInfo loginInfo) {
		// 分页开始与结束值放入查询Map
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		queryMap.put("startIndex", startIndex);
		queryMap.put("endIndex", dgm.getEndIndex());
		// 登陆用户相关信息放入查询Map
		Integer total = 0;
		// 查询数据总数
		try {
			total = this.customerServiceMapper.queryCustHistoryBelongInfoNums(queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询符合Map中查询条件的所有数据
		List<Map<String, String>> rows = this.customerServiceMapper.queryCustHistoryBelongInfo(queryMap);
		// 待返回对象
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(total);
		dataGrid.setRows(rows);
		return dataGrid;
	}

	/**
	 * 客户归属分页查询
	 * 
	 * @param
	 * @author zhangjie
	 * @return
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public DataGrid queryCustomerBelongList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if(paramMap==null){
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		Integer total = this.customerServiceMapper.findCustomerBelongCount(paramMap);
		List<Map<String,String>> rows = this.customerServiceMapper.findCustomerBelongList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(total);
		dataGrid.setRows(rows);
		return dataGrid;
	}

	/**
	 * 客户基本信息与客户归属信息赋值
	 * 
	 * @param param
	 * @return
	 * @author zhangjie
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo getCustBelongInfoByCustBaseInfoId(Map map) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		Map resultMap = new HashMap();
		// 若Map参数中包含非空的客户基本信息流水号，则根据此Id查询客户基本信息与历史调整信息
		if (map != null) {
			paramMap.put("custBaseInfoId", map.get("custBaseInfoId"));
			// 根据客户基本信息流水号查询出客户基本信息
			Map custInfo = this.customerServiceMapper.getCustInfoByCustBaseInfoId(paramMap);
			// 根据客户基本信息流水号查询出客户历史调整信息
			List<Map<String, String>> custBelongList = this.customerServiceMapper.getCustBelongInfoByCustBaseInfoId(paramMap);
			// 将客户基本信息与客户历史调整信息放入同一Map以供返回
			resultMap.put("custInfo", custInfo);
			resultMap.put("custBelongList", custBelongList);
			resultInfo.setObj(resultMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("客户基本信息赋值成功!");
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setObj("客户基本信息赋值失败!");
		}
		return resultInfo;
	}

	// 保存客户归属
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public ResultInfo saveBelongOperation(List<CustBelongOperation> custBelongOperationList, String custBaseInfoId,
			LoginInfo loginInfo) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		// 遍历Http请求传入的理财经理调整信息
		for (CustBelongOperation custBelongOperation : custBelongOperationList) {
			// -1. 根据Http请求传入的客户基本信息流水号查询该客户的历史归属信息
			CustBelongOperationExample custBelongOperationExample = new CustBelongOperationExample();
			CustBelongOperationExample.Criteria criteria = custBelongOperationExample.createCriteria();
			criteria.andCustBaseInfoIdEqualTo(new Long(custBaseInfoId))
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustBelongOperation> custBelongOperations = this.custBelongOperationMapper.selectByExample(custBelongOperationExample);
			// 0. 若客户轨迹表为空，则需将客户当前财富顾问数据插入数据库
			if (custBelongOperations == null || custBelongOperations.size() < 1) {
				CustBaseInfo custBaseInfoExist = this.custBaseInfoMapper
						.selectByPrimaryKey(new Long(custBaseInfoId));
				// 组装待插入的第一任客户归属调整对象custBelongOperation
				CustBelongOperation custBelongOperationForInsert = new CustBelongOperation();
				custBelongOperationForInsert.setCustBaseInfoId(new Long(custBaseInfoId));
				custBelongOperationForInsert.setAgentId(custBaseInfoExist.getAgentId());
				custBelongOperationForInsert.setUserId(loginInfo.getUserId());
				custBelongOperationForInsert.setBelongStartDate(custBaseInfoExist.getCreateDate());
				// http请求传入的'归属起日'早于当前财富顾问的'归属起日'，则返回提示信息
				if (custBelongOperationForInsert.getBelongStartDate().after(custBelongOperation.getBelongStartDate())) {
					resultInfo.setMsg("归属起日不能早于上次归属起日");
					resultInfo.setSuccess(false);
					return resultInfo;
				}
				// 客户归属止日为Http请求传入的新客户归属调整对象的归属起日的前一天
				String belongEndDateStr = DateUtils.getDateTimeString(custBelongOperation.getBelongStartDate());
				belongEndDateStr = DateUtils.calDate(belongEndDateStr, -1, "D");
				custBelongOperationForInsert.setBelongEndDate(DateUtils.getDate(belongEndDateStr));
				custBelongOperationForInsert.setAdjustCause(custBelongOperation.getAdjustCause());
				java.util.Date currentDate = new java.util.Date();
				custBelongOperationForInsert.setAdjustDate(currentDate);
				BeanUtils.insertObjectSetOperateInfo(custBelongOperationForInsert, loginInfo);
				// 将组装好的custBelongOperation插入数据库
				// 生成主键
				Long  custBelongOperationIdForInsert = this.commonService
						.getSeqValByName("SEQ_T_CUST_BELONG_OPERATION");
				custBelongOperationForInsert.setCustOperationId(custBelongOperationIdForInsert);
				this.custBelongOperationMapper.insert(custBelongOperationForInsert);

			}
			// 1.设置前任财富顾问的归属止期为新财富顾问的归属起期的前一天
			for (CustBelongOperation custBelongOperationExist : custBelongOperations) {
				if (custBelongOperationExist.getBelongEndDate() == null) {
					// 该财富顾问即将成为上任财富顾问，设置归属止日为http请求传入的最新财富顾问的归属起日的前一天
					String currentDate = DateUtils.getDateTimeString(custBelongOperation.getBelongStartDate());
					currentDate = DateUtils.calDate(currentDate, -1, "D");
					custBelongOperationExist.setBelongEndDate(DateUtils.getDate(currentDate));
					// http请求传入的'归属起日'早于当前财富顾问的'归属起日'，则返回提示信息
					if (custBelongOperationExist.getBelongStartDate().after(DateUtils.getDate(currentDate))) {
						resultInfo.setMsg("归属起日不能早于上次归属起日");
						resultInfo.setSuccess(false);
						return resultInfo;
					}
					// 设置该财富顾问的调整原因为http请求传入的调整原因
					custBelongOperationExist.setAdjustCause(custBelongOperation.getAdjustCause());
					// 更新
					BeanUtils.updateObjectSetOperateInfo(custBelongOperationExist, loginInfo);
					this.custBelongOperationMapper.updateByPrimaryKey(custBelongOperationExist);
				}
			}
			// 2.Http请求传入的理财经理Id与客户当前的理财经理Id相同，则直接保存不需要修改其他表
			CustBaseInfo custBaseInfoExist = this.custBaseInfoMapper.selectByPrimaryKey(new Long(custBaseInfoId));
			if (custBaseInfoExist.getAgentId().compareTo(custBelongOperation.getAgentId()) != 0) {
				// 1修改客户基本信息表中的agentId,oper_com_id,modify_user_id
				// 修改客户基本信息表字段:agentId为最新的agentId
				CustBaseInfo custBaseInfo = this.custBaseInfoMapper.selectByPrimaryKey(new Long(custBaseInfoId));
				custBaseInfo.setAgentId(custBelongOperation.getAgentId());
				// 修改客户基本信息表字段:oper_com_id为最新的理财经理所属的分公司的com_id
				Agent agent = this.agentMapper.selectByPrimaryKey(custBelongOperation.getAgentId());
				custBaseInfo.setOperComId(agent.getComId());
				// 修改客户基本信息表字段:modify_user_id,modify_date
				BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
				// 根据主键更新客户基本信息表
				this.custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
				// 2修改客户其他信息表agentId
				CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
				CustOthInfoExample.Criteria criteriaCustContact = custOthInfoExample.createCriteria();
				criteriaCustContact.andCustBaseInfoIdEqualTo(new Long(custBaseInfoId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustOthInfo> custOthInfos = this.custOthInfoMapper.selectByExample(custOthInfoExample);
				for (CustOthInfo custOthInfo : custOthInfos) {
					custOthInfo.setAgentId(custBelongOperation.getAgentId());
					BeanUtils.updateObjectSetOperateInfo(custOthInfo, loginInfo);
					this.custOthInfoMapper.updateByPrimaryKey(custOthInfo);
				}
				// 3修改客户联系信息表agentId
				CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
				CustContactInfoExample.Criteria criteriaCustCon = custContactInfoExample.createCriteria();
				criteriaCustCon.andCustBaseInfoIdEqualTo(new Long(custBaseInfoId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustContactInfo> custContactInfos = this.custContactInfoMapper
						.selectByExample(custContactInfoExample);
				for (CustContactInfo custContactInfo : custContactInfos) {
					custContactInfo.setAgentId(custBelongOperation.getAgentId());
					BeanUtils.updateObjectSetOperateInfo(custContactInfo, loginInfo);
					this.custContactInfoMapper.updateByPrimaryKey(custContactInfo);
				}
				// 4修改客户地址信息表agentId
				CustAddressInfoExample custAddressInfoExample = new CustAddressInfoExample();
				CustAddressInfoExample.Criteria custAddressInfo = custAddressInfoExample.createCriteria();
				custAddressInfo.andCustBaseInfoIdEqualTo(new Long(custBaseInfoId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustAddressInfo> custAddressInfos = this.custAddressInfoMapper
						.selectByExample(custAddressInfoExample);
				for (CustAddressInfo custAddressInfo1 : custAddressInfos) {
					custAddressInfo1.setAgentId(custBelongOperation.getAgentId());
					BeanUtils.updateObjectSetOperateInfo(custAddressInfo1, loginInfo);
					this.custAddressInfoMapper.updateByPrimaryKey(custAddressInfo1);
				}
				// 4修改客户账户信息表agentId
				CustAccInfoExample custAccInfoExample = new CustAccInfoExample();
				CustAccInfoExample.Criteria custAccInfo = custAccInfoExample.createCriteria();
				custAccInfo.andCustBaseInfoIdEqualTo(new Long(custBaseInfoId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustAccInfo> custAccInfos = this.custAccInfoMapper
						.selectByExample(custAccInfoExample);
				for (CustAccInfo custAccInfo1 : custAccInfos) {
					custAccInfo1.setAgentId(custBelongOperation.getAgentId());
					BeanUtils.updateObjectSetOperateInfo(custAccInfo1, loginInfo);
					this.custAccInfoMapper.updateByPrimaryKey(custAccInfo1);
				}
			}
			// 3. 插入Http请求传入最新的财富顾问信息
			// 生成主键
			Long custBelongOperationId = this.commonService.getSeqValByName("SEQ_T_CUST_BELONG_OPERATION");
			// 插入数据
			BeanUtils.insertObjectSetOperateInfo(custBelongOperation, loginInfo);
			java.util.Date currentDate = new java.util.Date();
			custBelongOperation.setAdjustDate(currentDate);
			custBelongOperation.setCustBaseInfoId(new Long(custBaseInfoId));
			custBelongOperation.setUserId(loginInfo.getUserId());
			custBelongOperation.setCustOperationId(custBelongOperationId);
			// 最新的财富顾问调整原因用于设置上任财富顾问的调整原因，最新的财富顾问调整原因为空
			custBelongOperation.setAdjustCause(null);
			this.custBelongOperationMapper.insert(custBelongOperation);
			/*// 调整完成后，给客户发送调整短信通知
			// 获取客户姓名
			CustBaseInfoExample custBaseInfoExample = new CustBaseInfoExample();
			CustBaseInfoExample.Criteria custBaseInfoCriteria = custBaseInfoExample.createCriteria();
			custBaseInfoCriteria.andCustBaseInfoIdEqualTo(new Long(custBaseInfoId))
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<CustBaseInfo> custBaseInfoList = custBaseInfoMapper.selectByExample(custBaseInfoExample);
			String chnName = "";
			String custLevel = "";
			String agentName = "";
			String agentMobile = "";
			if (custBaseInfoList != null && custBaseInfoList.size() > 0) {
				CustBaseInfo custBaseInfo = custBaseInfoList.get(0);
				chnName = custBaseInfo.getChnName();
				custLevel = custBaseInfo.getCustLevel();
				Long agentId = custBaseInfo.getAgentId();
				AgentExample agentExample = new AgentExample();
				AgentExample.Criteria agentCriteria = agentExample.createCriteria();
				agentCriteria.andAgentIdEqualTo(agentId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<Agent> agentList = agentMapper.selectByExample(agentExample);
				if (agentList != null && agentList.size() > 0) {
					agentName = agentList.get(0).getAgentName();
					agentMobile = agentList.get(0).getMobile();
				} else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("根据该客户对应的agentId找不到对应的财富顾问，无法生成短信");
					return resultInfo;
				}
			} else {
				resultInfo.setMsg("客户归属调整出现错误，原因为根据前台传入的custBaseInfoId找不到对应的客户记录");
				resultInfo.setSuccess(false);
				return resultInfo;
			}*/
			/*if (custLevel != null && !"".equals(custLevel) && custLevel.equals("01")) {
				String mobile = "";
				// 获取客户手机号码
				CustContactInfoExample custContactInfoExample = new CustContactInfoExample();
				CustContactInfoExample.Criteria custContactInfoCriteria = custContactInfoExample.createCriteria();
				custContactInfoCriteria.andCustBaseInfoIdEqualTo(new Long(custBaseInfoId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<CustContactInfo> custContactInfoList = custContactInfoMapper
						.selectByExample(custContactInfoExample);
				if (custContactInfoList != null && custContactInfoList.size() > 0) {
					mobile = custContactInfoList.get(0).getMobile();
				} else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("找不到该客户对应的手机号，无法发送短信");
					return resultInfo;
				}
				// 获取客户短信
				logger.info("=======开始生成客户(" + chnName + ")的财富顾问调整短信，调整日期日期" + DateUtils.getCurrentTimestampString()
						+ "===========");
				// 获取短信模板
				String smsType = "07";
				SysSmsTemplateExample sysSmsTemplateExample = new SysSmsTemplateExample();
				SysSmsTemplateExample.Criteria sysSmsTemplatecriteria = sysSmsTemplateExample.createCriteria();
				sysSmsTemplatecriteria.andSmsTypeEqualTo(smsType).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<SysSmsTemplate> sysSmsTemplateList = sysSmsTemplateMapper.selectByExample(sysSmsTemplateExample);
				if (sysSmsTemplateList == null || sysSmsTemplateList.size() == 0) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("生成短信失败，失败原因：未获取到短信模板！");
					logger.info("生成客户财富顾问变更短信失败，失败原因：未获取到短信模板！");
					return resultInfo;
				}
				// 生成短信
				SysSmsInfo sysSmsInfo = new SysSmsInfo();
				Long  sysSmsInfoId = commonService.getSeqValByName("SEQ_T_SYS_SMS_INFO");
				sysSmsInfo.setSysSmsInfoId(sysSmsInfoId);
				// sysSmsInfo.setSysSmsBatchId(sysSmsBatchId);
				sysSmsInfo.setBusinessNo(custBelongOperationId);
				sysSmsInfo.setBusinessType("02");
				sysSmsInfo.setSendObj(new Long(custBaseInfoId));
				sysSmsInfo.setSendObjName(chnName);
				sysSmsInfo.setSendMobile(mobile);
				sysSmsInfo.setSendObjType(sysSmsTemplateList.get(0).getSnedObjType());
				sysSmsInfo.setSendTime(DateUtils.getCurrentTimestamp());
				// 01-未发送，02-已发送
				sysSmsInfo.setSendStatus("01");
				Map paramMap = new HashMap();
				paramMap.put("custName", chnName);
				paramMap.put("agentName", agentName);
				paramMap.put("mobile", agentMobile);
				String sendContent = setValueToTemplate(paramMap, sysSmsTemplateList.get(0).getSmsTemplate());
				sysSmsInfo.setSendContent(sendContent);
				BeanUtils.insertObjectSetOperateInfo(sysSmsInfo, loginInfo);
				sysSmsInfoMapper.insert(sysSmsInfo);
				// 发送短信
				List<SysSmsInfo> sendSmsList = new ArrayList<SysSmsInfo>();
				sendSmsList.add(sysSmsInfo);
				// smsService.sendSms(sendSmsList, loginInfo);
			}*/
		}
		resultInfo.setMsg("客户归属信息保存成功");
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 
	 * 导出客户归属Excel表
	 * 
	 * @param Map,LoginInfo
	 * @author zhangjie
	 * @return ResultInfo
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo exportCustBelongExcel(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 1
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();

		// 2
		List<Map> custBelongList = this.customerServiceMapper.exportCustBelongExcel(paramMap);
		// 3.判断Mpper层返回的数据
		if (custBelongList != null) {
			// 生成Excel
			Map<String, Object> reportMap = commonService.getReportInfo("custBelongAdjust");
			// 对sheet名称加上当前月份
			String reportName = reportMap.get("reportName").toString();
			reportMap.put("reportName", reportName);
			if (reportMap != null && reportMap.size() > 0 && custBelongList != null && custBelongList.size() > 0) {
				reportMap.put("reportData", custBelongList);
				reportList.add(reportMap);
			} else {
				Map<String, Object> emptyMap = new HashMap<String, Object>();
				// 查询结果为空时，放入Excel表头
				emptyMap.put("customerNo", "");
				emptyMap.put("chnName", "");
				emptyMap.put("custLevel", "");
				emptyMap.put("custObtainWayName", "");
				emptyMap.put("custQuality", "");
				emptyMap.put("custType", "");
				emptyMap.put("agentName", "");
				emptyMap.put("storeName, ", "");
				emptyMap.put("comName", "");
				emptyMap.put("agoAgentName", "");
				emptyMap.put("custBelongStartDay", "");
				emptyMap.put("signTimes, ", "");
				custBelongList.add(emptyMap);
				reportMap.put("reportData", custBelongList);
				reportList.add(reportMap);
			}
			resultInfo.setObj(reportList);
			resultInfo.setSuccess(true);
		}
		return resultInfo;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 		
	 * 		导出客户基本信息
	 * */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map getCustBaseInfoDetail(Map paramMap)  throws Exception {
		// 输出日志，便于调试
		logger.info("Service层方法getCustBaseInfoDetail()接受参数个数:" + paramMap.size());
		// Mapper接口方法：统计业务报表相关信息
		List<Map<String,String>> businessStatisticsList = this.customerServiceMapper.getCustBaseInfoDetail(paramMap);
		Map<String, Object> datas = new HashMap<String, Object>();
	    datas.put("data", businessStatisticsList);
	    return datas;
	}	
	/**
	 * 客户升级审核提交复核按钮
	 */
	@Transactional
	public ResultInfo investCustAudit(String custBaseInfoId,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		//校验升级客户是否上传相应文件
		DefFileInfoExample defFileInfoExample = new DefFileInfoExample();
		defFileInfoExample.createCriteria().andBusinessNoEqualTo(Long.parseLong(custBaseInfoId))
		.andBusinessTypeEqualTo("15").andRcStateEqualTo("E");
		List<DefFileInfo> defFileInfos = defFileInfoMapper.selectByExample(defFileInfoExample);
		if (defFileInfos.isEmpty() || defFileInfos == null || defFileInfos.size() < 1) {
			resultInfo.setMsg("请上传相应材料再做提交！");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(Long.parseLong(custBaseInfoId));
		custBaseInfo.setInvestAuditState("01");
		BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
		custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
		resultInfo = createUpgradeAuditEmail(custBaseInfo, loginInfo);
		resultInfo.setMsg("提交成功！");
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * 发送升级客户审核邮件
	 * @return
	 */
	@Transactional
	private ResultInfo createUpgradeAuditEmail(CustBaseInfo custBaseInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createUpgradeCustEmail(custBaseInfo, loginInfo);
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
	 * 创建升级客户审核邮件
	 * @param custBaseInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createUpgradeCustEmail(CustBaseInfo custBaseInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//升级客户审核通知邮件
		sysEmailInfo.setEmailType("12");
		sysEmailInfo.setEmailRelationNo(custBaseInfo.getCustBaseInfoId());
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
						sysEmailInfo1.setEmailType("12");
						sysEmailInfo.setEmailRelationNo(custBaseInfo.getCustBaseInfoId());
						sysEmailInfo1.setEmailTitle("客户升级审核通知");
						sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
		Agent agent = agentMapper.selectByPrimaryKey(custBaseInfo.getAgentId());
		String agentName = agent.getAgentName();
		//创建邮件内容
		String mailContent = "理财顾问："+agentName+"。于"+DateUtils.getCurrentTimestamp()+"发起客户:"
		+custBaseInfo.getChnName()+"升级审核请求。请及时处理。";
		sysEmailInfo1.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo1.setEmailStatus("01");
		sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1, loginInfo);
		sysEmailInfoMapper.insert(sysEmailInfo1);
		resultList.add(sysEmailInfo1);
				}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("升级客户审核通知邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
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
	/**
	 * 获取客户升级审核列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryCustUpgradeAuditList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		Integer total = customerServiceMapper.queryCustUpgradeAuditListCount(paramMap);
		List<Map<String, String>> resultList = customerServiceMapper.queryCustUpgradeAuditList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	/**
	 * 保存客户升级审核信息
	 */
	@Override
	@Transactional
	public HashMap saveCustUpgradeAudit(HashMap paramMap,LoginInfo loginInfo) {
		String custBaseInfoId = paramMap.get("custBaseInfoId").toString();
		String custCheckConclusion = paramMap.get("custCheckConclusion").toString();
		String custCheckRemark = paramMap.get("custCheckRemark").toString();
		String custOperationNode = paramMap.get("custOperationNode").toString();
		String userCode = paramMap.get("userCode").toString();
		Long userIdDecimal = (Long) paramMap.get("userId");

		HashMap resultMap = new HashMap();
		CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(Long.parseLong(custBaseInfoId));
		if ("01".equals(custCheckConclusion)) {
			custBaseInfo.setInvestAuditState("03");
			custBaseInfo.setInvestCustomerType("02");
		} else if ("02".equals(custCheckConclusion)) {
			custBaseInfo.setInvestAuditState("02");
		}

		BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
		custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);

		if (!dealCustOperation(paramMap)) {
			resultMap.put("success", "false");
			resultMap.put("msg", "客户升级轨迹保存失败！");
			return resultMap;
		}
		resultMap.put("success", "true");
		resultMap.put("msg", "审核完毕保存成功！");
		return resultMap;
	}
	
	/**
	 * 客户升级审核轨迹保存
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@Transactional
	private boolean dealCustOperation(HashMap paramMap) {
		String custBaseInfoId = (String) paramMap.get("custBaseInfoId");
		String userCode = (String) paramMap.get("userCode");
		String custCheckConclusion = (String) paramMap.get("custCheckConclusion");
		String custCheckRemark = (String) paramMap.get("custCheckRemark");
		String custOperationNode = (String) paramMap.get("custOperationNode");
		Long userIdDecimal = (Long) paramMap.get("userId");
		Long comIdDecimal = (Long) paramMap.get("comId");
		Long custOperationID = commonService.getSeqValByName("SEQ_T_CUST_OPERATION");
		Long custOperationTraceID = commonService.getSeqValByName("SEQ_T_CUST_OPERATION_TRACE");
		Long custBaseInfoIdDecimal = new Long(custBaseInfoId);
		String rc_state = Constants.EFFECTIVE_RECORD;

		CustOperationExample custOperationExample = new CustOperationExample();
		custOperationExample.createCriteria().andCustBaseInfoIdEqualTo(custBaseInfoIdDecimal)
				.andOperationalNodeEqualTo(custOperationNode);
		List<CustOperation> custOperationList = custOperationMapper.selectByExample(custOperationExample);
		if (custOperationList != null && custOperationList.size() > 0) {
			CustOperation custOperation = new CustOperation();
			custOperation = custOperationList.get(0);

			CustOperationTrace custOperationTrace = new CustOperationTrace();
			custOperationTrace.setCustBaseInfoId(custOperation.getCustBaseInfoId());
			custOperationTrace.setCustOperationId(custOperation.getCustOperationId());
			custOperationTrace.setCustOperationTraceId(custOperationTraceID);
			custOperationTrace.setOperationalNode(custOperation.getOperationalNode());
			custOperationTrace.setUserId(custOperation.getUserId());
			custOperationTrace.setOperComId(custOperation.getOperComId());
			custOperationTrace.setConclusion(custOperation.getConclusion());
			custOperationTrace.setBackTime(DateUtils.getCurrentTimestamp());
			custOperationTrace.setCreateDate(DateUtils.getCurrentTimestamp());
			custOperationTrace.setCreateUserId(userIdDecimal);
			custOperationTrace.setModifyDate(DateUtils.getCurrentTimestamp());
			custOperationTrace.setModifyUserId(userIdDecimal);
			custOperationTrace.setRcState(rc_state);
			custOperationTrace.setRemark(custOperation.getRemark());
			custOperationTraceMapper.insertSelective(custOperationTrace);

			custOperation.setConclusion(custCheckConclusion);
			custOperation.setRemark(custCheckRemark);
			custOperation.setUserId(userIdDecimal);
			custOperation.setModifyDate(DateUtils.getCurrentTimestamp());
			custOperation.setModifyUserId(userIdDecimal);
			custOperationMapper.updateByExampleSelective(custOperation, custOperationExample);
		} else {
			CustOperation custOperation = new CustOperation();
			custOperation.setCustBaseInfoId(custBaseInfoIdDecimal);
			custOperation.setCustOperationId(custOperationID);
			custOperation.setUserId(userIdDecimal);
			custOperation.setOperComId(comIdDecimal);
			custOperation.setConclusion(custCheckConclusion);
			custOperation.setCreateDate(DateUtils.getCurrentTimestamp());
			custOperation.setCreateUserId(userIdDecimal);
			custOperation.setModifyDate(DateUtils.getCurrentTimestamp());
			custOperation.setModifyUserId(userIdDecimal);
			custOperation.setOperationalNode(custOperationNode);
			custOperation.setRcState(rc_state);
			custOperation.setRemark(custCheckRemark);
			custOperationMapper.insertSelective(custOperation);
		}
		return true;
	}
	/**
	 * 客户退回结论查询
	 */
	@Override
	public HashMap queryCustConclusion(HashMap paramMap) {
		HashMap resultMap = customerServiceMapper.queryCustConclusion(paramMap);
		if (resultMap == null || resultMap.size() == 0) {
			resultMap = new HashMap();
			resultMap.put("success", "false");
		} else {
			resultMap.put("success", "true");
		}
		return resultMap;
	}
	
	/**
	 * 客户统一升级处理
	 */
	@Override
	public HashMap updateCustInvestGrade(HashMap paramMap,LoginInfo loginInfo) {
		HashMap resultMap = new HashMap();
		List<Map> resultList = customerServiceMapper.getProCustList(paramMap);
		for (Map map : resultList) {
			Long custBaseInfoId =Long.parseLong(map.get("custBaseInfoId").toString());
			CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
			custBaseInfo.setInvestCustomerType("02");
			BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
			custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
		}
		resultMap.put("success", "true");
		return resultMap;
	}
	
	/**
	 * 客户退回结论查询
	 */
	@Override
	public Map queryCustAndAgentInfo(HashMap paramMap) {
		Map resultMap = new HashMap();
		Long custBaseInfoId = Long.parseLong(paramMap.get("custBaseInfoId").toString());
		CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
		Agent agent = agentMapper.selectByPrimaryKey(custBaseInfo.getAgentId());
		resultMap.put("chnName", custBaseInfo.getChnName());
		resultMap.put("agentName", agent.getAgentName());
		return resultMap;
	}
	/**
	 * 客户查询列表 包含存续规模
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryCustomerInvestList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		// paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		//查询agentId 若为rm则查名下客户
		Long userId = loginInfo.getUserId();
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
		List<Agent> agents = agentMapper.selectByExample(agentExample);
		if (!agents.isEmpty()) {
			paramMap.put("agentId", agents.get(0).getAgentId().toString());
		}
		Integer total = customerServiceMapper.queryCustomerInvestListCount(paramMap);
		List<Map<String, String>> resultList = customerServiceMapper.queryCustomerInvestList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	/**
	 * 强制升级处理
	 */
	@Override
	public HashMap custForceUpdate(HashMap paramMap,LoginInfo loginInfo) {
		HashMap resultMap = new HashMap();
		String param1=(String) paramMap.get("param");
		HashMap hashMap=(HashMap) JsonUtils.jsonStrToMap(param1);
		
		Long custBaseInfoId= Long.parseLong(hashMap.get("custBaseInfoId").toString());
		CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
		custBaseInfo.setInvestCustomerType("02");
		BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
		custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
		resultMap.put("success", "true");
		return resultMap;
	}
}
