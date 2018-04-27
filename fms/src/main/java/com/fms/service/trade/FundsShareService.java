package com.fms.service.trade;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.fms.db.model.TradeFundsShareChange;
import com.fms.db.model.TradeInfo;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface FundsShareService {

	@SuppressWarnings("rawtypes")
	DataGrid queryFundsList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	
	/*
	 * 
	 * 根据条件查询可受让信息
	 */
	@SuppressWarnings("rawtypes")
	DataGrid queryFundsTranFereeList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	
	/**
	 * 转让审核查询列表
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public DataGrid queryTransferAuditList(DataGridModel dgm,Map paramMap,LoginInfo loginInfo);

	/*
	 * 
	 * 初始化基金份额页面理财经理等信息
	 * getTradeTransFereeInfo
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getTradeTransFereeInfo(Map paramMap,LoginInfo loginInfo);
	
	
	/**
	 * 转让审核界面，初始化基金份额产品页面等信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getTransferAuditProInfo(Map paramMap,LoginInfo loginInfo);
	
	@SuppressWarnings("rawtypes")
	ResultInfo getTradeDetailInfo(Map paramMap);


	ResultInfo getTradeTotalAssets(String tradeInfoId);

	ResultInfo saveFundsShareChangeInfo(TradeFundsShareChange tradeFundsShareChange, LoginInfo loginInfo);
   /*
    * 新增转让交易基本信息
    */
	ResultInfo saveTradeInfo(TradeInfo tradeInfo,LoginInfo loginInfo );

	Map saveTradeInput(HashMap paramMap, LoginInfo loginInfo) throws RuntimeException;

	Map saveTradeAudit(HashMap paramMap, LoginInfo loginInfo) throws Exception;
	/*
	    * 撤销删除转让交易基本信息
	    */
	ResultInfo deleteTradeFundsShareRecord(Map paramMap, LoginInfo loginInfo);
}
