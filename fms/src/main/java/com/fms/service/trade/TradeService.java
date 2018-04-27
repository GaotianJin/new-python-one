package com.fms.service.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fms.db.model.DefFileInfo;
import com.fms.db.model.DefPrintInfo;
import com.fms.db.model.TradeCustRoleInfo;
import com.fms.db.model.TradeInfo;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface TradeService {
	
	public DataGrid tradeInputQueryTradeList(DataGridModel dgm,Map paramMap,String tradeStaus,LoginInfo loginInfo);
	
	public ResultInfo saveTradeInfo(TradeInfo tradeInfo,LoginInfo loginInfo);
	
	public List<Map<String, String>> queryCustomerCombo(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeRiskProId(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeRiskProtObj(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeWealthProInfo(HashMap paramMap);
	
	public ResultInfo queryWealthProductDetailInfo(String productId);
	
	public List<Map<String, String>> queryTradeRiskProInfo(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeRoleInfo(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeComId(HashMap paramMap);
	
	/*public List<Map<String, String>> queryTradeStoreId(HashMap paramMap);*/
	
	public List<Map<String, String>> queryTradeAgentId(HashMap paramMap);
	
	public List<Map<String, String>> queryRiskProInfoObjList(HashMap paramMap);
	
	public List<Map<String, String>> queryMonryProInfoObjList(HashMap paramMap);
	
	public List<Map<String,String>> queryCustomInfoInputGrid(HashMap paramMap);
	
	public List<Map<String, String>> queryCustomInfoInputGridById(HashMap paramMap);
	
	public List<Map<String,String>> queryTradeProductFactor(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeBankInfo(Map paramMap);
	
	public List<Map<String,String>> searchTradeBankInfo(HashMap paramMap);
	
	public List<Map<String, String>> queryTradeAddressInfo(Map paramMap);
	
	public List<Map<String,String>> searchTradeAddressInfo(HashMap paramMap);
	
	public List<Map<String,String>> getTradeAddressInfo(HashMap paramMap);
	
	public List<Map<String,String>> queryDefCode(HashMap paramMap);
	//SYP
	public String checkUpFile(HashMap paramMap);
	
	public HashMap queryTradeConclusion(HashMap paramMap);
	
	/////////////////////////////////
	public HashMap queryTradeConclusion2(HashMap paramMap);
	////////////////////////////////
	public ResultInfo saveTradeRole(String param,LoginInfo loginInfo);
	
	public ResultInfo saveTradeRiskPro(String param,LoginInfo loginInfo);
	
	public ResultInfo saveTradeWealthPro(String paramMap,LoginInfo loginInfo);
	
	
	public HashMap delTradeInfo(HashMap paramMap,LoginInfo loginInfo);
	
	public ResultInfo cancelTradeInfo(TradeInfo tradeInfo,LoginInfo loginInfo);
	
	public ResultInfo getTradeStatusInfo(String tradeInfoId);
	
	public HashMap applyTradeCheckInfo(HashMap paramMap);
	
	public HashMap applyTradeAuditInfo(HashMap paramMap);
	
	public HashMap saveTradeCheck(HashMap paramMap);
	
	public HashMap saveTradeAudit(HashMap paramMap);
	
	public ResultInfo saveTradeCusInfo(List<Map<String, String>> custBaseInfoList,TradeInfo tradeInfo,LoginInfo loginInfo);
	
	public ResultInfo delRoleInfo(TradeInfo tradeInfo,TradeCustRoleInfo tradeCustRoleInfo,LoginInfo loginInfo);
	
	public ResultInfo delRiskProInfo(Map<String, Object> paramMap,LoginInfo loginInfo);
	
	public HashMap delMonProInfo(HashMap paramMap);
	
	public ResultInfo delTradeCusInfo(Map<String, String> tradeCustInfoMap,TradeInfo tradeInfo,LoginInfo loginInfo);
	
	public HashMap saveTradeBankInfo(HashMap paramMap,LoginInfo loginInfo);
	
	public HashMap saveTradeAddressInfo(HashMap paramMap,LoginInfo loginInfo);
	
	public List<Map<String, String>> queryOpenDateByProductId(Map paramMap);
	
	public List<Map<String, String>> queryClosedPeriodsByProductId(Map paramMap);
	
	public ResultInfo printTrade(Long id,String dir,LoginInfo loginInfo);
	
	@SuppressWarnings("rawtypes")
	public DataGrid getTradeDownload(DataGridModel dgm,Map paramMap);
	
	public void downloadPrint(HttpServletRequest request, HttpServletResponse response,DefPrintInfo defPrintInfo,LoginInfo loginInfo);
	/**
	 * 插入交易信息
	 * @param tradeInfoMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo insertTradeInfo(Map xmleWealthContentMap);
	/**
	 * 根据客户Id查询客户保险交易信息
	 * @param xmleWealthContentMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getRiskTradeInfo(Map xmleWealthContentMap);
	/**
	 * 生成认购确认书
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo tradeConfirmPrintList(Map paramMap,String uploadDir,LoginInfo loginInfo);

	/**
	 * 生成认购确认书
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo tradeConfirmPrintFloatList(Map paramMap, String uploadDir, LoginInfo loginInfo);
	
	
	HashMap saveTradeInput(HashMap paramMap, LoginInfo loginInfo);

	/*
	 * 查询转让交易录要素信息
	 * 
	 * 
	 */
	@SuppressWarnings("rawtypes")
	List<Map<String,String>> queryTransFerMoneyProInfoObjList(HashMap paramMap);
	
	/**
	 * 生成认购确认书03
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo tradeConfirmPrintFloatList03(Map paramMap, String uploadDir, LoginInfo loginInfo);

	List<Map> getAllTBankInfoByCustId(HashMap paramMap);
	/**
	 * 判断基金风险等级与客户风险等级是否匹配
	 * @param paramMap
	 * @return
	 */
	public ResultInfo checkRiskLevel(HashMap paramMap);

	public String checkCustIDFile(HashMap paramMap);
	
	/**
	 * 获取交易生成的基本信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo getTradeBaseInfo(Map paramMap, LoginInfo loginInfo);
}
