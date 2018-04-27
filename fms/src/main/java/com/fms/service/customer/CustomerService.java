package com.fms.service.customer;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.fms.db.model.Agent;
import com.fms.db.model.CustAccInfo;
import com.fms.db.model.CustAddressInfo;
import com.fms.db.model.CustAssetInfo;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.CustBelongOperation;
import com.fms.db.model.CustCarInfo;
import com.fms.db.model.CustContactInfo;
import com.fms.db.model.CustFamilyInfo;
import com.fms.db.model.CustHobbyInfo;
import com.fms.db.model.CustHouseInfo;
import com.fms.db.model.CustIncomeInfo;
import com.fms.db.model.CustInvestInfo;
import com.fms.db.model.CustOthInfo;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface CustomerService {

	/**
	 * @param custBaseInfo
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo saveCustomerBaseInfo(CustBaseInfo custBaseInfo,String agentId,
			LoginInfo loginInfo);

	/**
	 * @param custBaseInfo
	 * @param custContactInfo
	 * @param custAddressInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	public ResultInfo saveCustContactAndAddressInfo(CustBaseInfo custBaseInfo,
			CustContactInfo custContactInfo,
			List<CustAddressInfo> custAddressInfoList, LoginInfo loginInfo,
			Agent agent,String operate);

	/**
	 * @param custAccInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	public ResultInfo saveCustAccInfo(CustBaseInfo custBaseInfo,List<CustAccInfo> custAccInfoList,
			Agent agent,LoginInfo loginInfo, String operate);

	/**
	 * @param custOthInfo
	 * @param custHobbyInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	public ResultInfo saveCustPersonalInfo(CustBaseInfo custBaseInfo,CustOthInfo custOthInfo,
			List<CustHobbyInfo> custHobbyInfoList,List<CustIncomeInfo> custIncomeInfoList,
			List<CustAssetInfo> custAssetInfoList,List<CustInvestInfo> custInvestInfoList,
			Agent agent,LoginInfo loginInfo,String operate);

	/**
	 * @param custOthInfo
	 * @param custIncomeInfoList
	 * @param custAssetInfoList
	 * @param custInvestInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	public ResultInfo saveCustWealthInfo(CustBaseInfo custBaseInfo,CustOthInfo custOthInfo,
			List<CustIncomeInfo> custIncomeInfoList,
			List<CustAssetInfo> custAssetInfoList,
			List<CustInvestInfo> custInvestInfoList, Agent agent,
			LoginInfo loginInfo,String operate);
	
	
	/**
	 * @param custFamilyInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	public ResultInfo saveCustFamilyInfo(CustBaseInfo custBaseInfo,List<CustFamilyInfo> custFamilyInfoList,
			Agent agent,LoginInfo loginInfo,String operate);
	
	/**
	 * @param custHouseInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	public ResultInfo saveCustHouseInfo(CustBaseInfo custBaseInfo,List<CustHouseInfo> custHouseInfoList,
			Agent agent,LoginInfo loginInfo,String operate);
	
	/**
	 * @param custCarInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	public ResultInfo saveCustCarInfo(CustBaseInfo custBaseInfo,List<CustCarInfo> custCarInfoList,
			Agent agent,LoginInfo loginInfo,String operate);
	
	
	/**
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid searchCustomerInfo(DataGridModel dgm,Map paramMap,LoginInfo loginInfo);
	
	/**
	 * @param custBaseInfo
	 * @return
	 */
	public ResultInfo getCustInfo(CustBaseInfo custBaseInfo,LoginInfo loginInfo);

	/**
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryCustomerList(DataGridModel dgm,Map paramMap,LoginInfo loginInfo);
	
	/**
	 * @param custBaseInfoId
	 * @return
	 */
	public ResultInfo getCustBaseInfo(CustBaseInfo custBaseInfo,String getType);
	
	
	/**
	 * @param paramMap
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo queryCustContactInfoList(Map paramMap,LoginInfo loginInfo,String operate);
	
	/**
	 * @param paramMap
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo queryCustContactDetailInfo(Map paramMap,LoginInfo loginInfo,String operate);
	
	/**
	 * @param custBaseInfoId
	 * @return
	 */
	public ResultInfo verifyCustomerTradeInfo(String custBaseInfoId);
	
	/**保存客户除基本信息以外的所有信息
	 * @param customerInfo
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo saveCustAllInfo(String customerInfo,LoginInfo loginInfo);
	
	
	/**
	 * 从交易页面进入客户管理后，点击返回按钮，判断客户是否为该财富顾问客户
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getCustByAgentInfo(Map paramMap);
	
	/**
	 * 校验客户所属理财经理信息
	 * @param custBaseInfo
	 * @param agent
	 * @param lo
	 * @return
	 */
	public ResultInfo verifyCustBelongAgent(CustBaseInfo custBaseInfo,Agent agent,LoginInfo loginInfo);
	/**
	 * 根据客户ID获取账户信息
	 * @param custBaseInfoId
	 * @return
	 */
	public ResultInfo getCustAccountInfo(String customerNoId);
	/**
	 * 根据客户ID查询客户详细信息
	 * @param customerNoId
	 * @return
	 */
	/*public ResultInfo getCustDetilInfo(String customerNoId);*/
	/**
	 * 插入客户详细信息
	 * @param CustInfoMap
	 * @return
	 * @throws ParseException 
	 */
	@Transactional
	@SuppressWarnings("rawtypes")
	public ResultInfo insertCustInfo(Map custInfoMap);
	/**
	 * 根据客户Id查询财富产品信息
	 * @param xmleWealthContentMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getWealthPdInfo(Map xmleWealthContentMap);
	
	///////////////////////////////////客户归属信息调整功能///////////////////////////////////////////
	//客户基本信息与客户归属信息赋值
	@SuppressWarnings("rawtypes")
	public ResultInfo getCustBelongInfoByCustBaseInfoId(Map map);
	//分页
	DataGrid queryCustomerBelongList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	//保存
	ResultInfo saveBelongOperation(List<CustBelongOperation> custBelongOperationList, String custBaseInfoId,LoginInfo loginInfo) throws Exception;
	//导出
	public ResultInfo exportCustBelongExcel(Map paramMap, LoginInfo loginInfo);
	//查询客户归属历史轨迹
	public DataGrid queryCustHistoryBelongInfo(DataGridModel dgm, Map queryMap, LoginInfo loginInfo);
	//导出客户基本信息
	public Map getCustBaseInfoDetail(Map paramMap) throws Exception;
	// 客户升级审核提交复核按钮
	public ResultInfo investCustAudit(String custBaseInfoId, LoginInfo loginInfo);
	// 客户升级审核列表查询
	public DataGrid queryCustUpgradeAuditList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	// 客户升级审核提交
	public Map saveCustUpgradeAudit(HashMap paramMap, LoginInfo loginInfo);
	// 客户升级审核退回查询
	public HashMap queryCustConclusion(HashMap paramMap);
	//统一升级客户等级
	public Map updateCustInvestGrade(HashMap paramMap, LoginInfo loginInfo);
	//查询客户信息
	public Map queryCustAndAgentInfo(HashMap paramMap);
	//客户查询列表 包含存续规模
	public DataGrid queryCustomerInvestList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
    //客户强制升级
	public Map custForceUpdate(HashMap paramMap, LoginInfo loginInfo);
	//客户导出手机信息初始化查询所有客户
	DataGrid queryAllCustomerList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	
}   
   
