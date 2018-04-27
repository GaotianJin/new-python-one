package com.fms.service.customer;

import java.text.ParseException;
import java.util.Map;

import org.apache.log4j.lf5.LogLevel;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface ModifyCustomerService {
	/**
	 * 客户基本信息赋值
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getModifyCustomerBaseInfo(Map map,LoginInfo loginInfo);
	/**
	 * 客户个人信息赋值
	 * @param param
	 * @return
	 */
	public ResultInfo getModifyCustomerPersonalInfo(String param);
	/**
	 * 客户拜访信息赋值
	 * @param param
	 * @return
	 */
	public ResultInfo getModifyCustomerVisitInfo(String param);
	/**
	 * 客户投资信息赋值
	 * @param param
	 * @return
	 */
	public ResultInfo getModifyCustomerInvestInfo(String param);
	/**
	 * 校验客户基本信息是否与他人准客户/客户冲突
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo checkModifyCustomerBaseInfo(Map paramMap,LoginInfo loginInfo);
	/**
	 * 校验客户基本信息是否与自己准客户/客户冲突
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo checkMyModifyCustomerBaseInfo(Map paramMap,LoginInfo loginInfo);
	/**
	 * 更新客户基本信息
	 * @param custBaseInfo
	 * @param loginInfo
	 * @param agentId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo updateModifyCustomerBaseInfo(Map paramMap,LoginInfo loginInfo);
	/**
	 * 更新客户联系地址信息和账户信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo submitCustomerAddressAndAccInfo(Map paramMap,LoginInfo loginInfo);
	/**
	 * 更新客户拜访信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo submitAllCustomerVisitInfo(Map map,LoginInfo loginInfo);
	/**
	 * 更新客户其他投资信息
	 * @param map
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo submitAllCustomerInvestInfo(Map map,LoginInfo loginInfo);
	/**
	 * 更新客户个人信息
	 * @param map
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo submitModifyCustomerPersonalInfo(Map map,LoginInfo loginInfo);
	/**
	 * 	验证用户提交的客户/准客户信息
	 */
	public ResultInfo verifyCustMobileInfo( String custBaseInfoStr, String agentId, LoginInfo loginInfo );
	/**
	 * 	验证用户提交的客户/准客户身份证号信息
	 * 	@author Liwentao
	 * 	@param custBaseInfStr
	 * 	@param agentId
	 * 	@param loginInfo
	 * 	@return resultInfo
	 */
	public ResultInfo verifyCustIdNoInfo( String custBaseInfoStr, String agentId, LoginInfo loginInfo ) ;
	/**
	 * 根据手机号与姓名查询客户是否与其他理财经理已录信息冲突
	 * @author Liwentao
	 * @param  custBaseInfoMap
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	public ResultInfo verifyCustMobileAndName(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef);
	/**
	 * 根据姓名查询客户是否与其他理财经理已录信息冲突
	 * @author Liwentao
	 * @param  custBaseInfoMap
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	public ResultInfo verifyCustName(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef);
	/**
	 * 根据姓名查询客户是否与其他理财经理已录信息冲突
	 * @author Liwentao
	 * @param  custBaseInfoMap
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	//public ResultInfo verifyCustFiveElements(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef);
	/**
	 * 验证用户录入的客户基本信息是否与自己的准客户/客户发生冲突
	 * @author Liwentao
	 * @param  custBaseInfoMap
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	public ResultInfo verifyCollisionWithOwnCust(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef);
	/**
	 * 验证用户录入的客户基本信息是否与其他理财经理签单客户发生冲突
	 * @author Liwentao
	 * @param  custBaseInfoMap
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	public ResultInfo verifyCollisionWithOthAgentCust(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef);
	/**
	 * 验证用户录入的客户基本信息是否与其他理财经理准客户发生冲突
	 * @author Liwentao
	 * @param  custBaseInfoMap
	 * @param  agentId
	 * @param  modelMap
	 * @return  resultInfo
	 */
	public ResultInfo verifyCollisionWithOthPreAgentCust(String custBaseInfoStr, String agentId, LoginInfo loginInfoObjRef);
	/**
	 * 校验客户是否保存客户地址和账户信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo checkCustAdressAndAccInfo(Map paramMap,LoginInfo loginInfo);
	/**
	 * 提交复核前校验客户是否在其他理财经理有交易
	 * @param param
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo checkTradeInput(String param,LoginInfo loginInfo);
	/**
	 * 插入客户详细信息
	 * @param CustInfoMap
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo insertCustBaseInfo(Map custInfoBaseInfoMap);
	/**
	 * 获取客户其他其他公司投资总额
	 * @param param
	 * @return
	 */
	public ResultInfo getModifyCustomerInvestAmount(String param);
	/**
	 * 获取客户其他我们公司投资总额
	 * @param param
	 * @return
	 */
	public ResultInfo getModifyCustomerMyInvestAmount(String param);
	/**
	 * 获取我们公司存续总额
	 * @param param
	 * @return
	 */
	public ResultInfo getModifyCustomerMyRemainAmount(String param);
	/**
	 * 获取理财经理信息
	 * @param param
	 * @return
	 */
	public DataGrid searchAgentInfo(String param);
	
	/**
	 * 根据问卷编码获取问卷所有问题
	 * @param questionnaireCode
	 * @return
	 */
	public ResultInfo getQuestionnaireQuestion(String questionnaireCode);
	
	
	/**
	 * 保存问卷数据
	 * @param param
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo saveQuestionnaireQuestionInfo(String param, LoginInfo loginInfo);
	
	/**
	 * 查询客户风控问卷信息
	 * @param custQuestionnaireId
	 * @return
	 */
	public ResultInfo getCustQuestionnaireInfo(long custBaseInfoId);
	
	public ResultInfo getModifyCustomerInvestInfo02(String param);
	public ResultInfo getModifyCustomerInvestInfo03(String param);
	public ResultInfo getModifyCustomerInvestInfo04(String param);
	public ResultInfo getModifyCustomerMyInvestAmount02(String param);
	public ResultInfo getModifyCustomerMyRemainAmount02(String param);
	public ResultInfo getModifyCustomerMyInvestShare(String param);
	public ResultInfo getModifyCustomerMyInvestShare02(String param);
	public ResultInfo getModifyCustomerMyRemainShare(String param);
	public ResultInfo getModifyCustomerMyRemainShare02(String param);
	public ResultInfo getCustHistoryRecordInfo(long parseLong);
	public DataGrid getHistoryRecord(DataGridModel dgm, Map map);
	public String checkInvestCustInfo(String custBaseInfoId);
	public String queryCustIdCardUpload(String custBaseInfoId);
	
	
}
