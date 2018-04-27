package com.fms.service.sms;

import java.util.List;
import java.util.Map;

import com.fms.db.model.PDAmountOrderInfo;
import com.fms.db.model.SysSmsInfo;
import com.fms.db.model.TradeInfo;
import com.fms.db.model.TradeStatusInfo;
import com.google.zxing.Result;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface SmsService {

	/**
	 * 生成ToCust短信
	 * @author SLY
	 * @return
	 */
	public ResultInfo createBirthToCustSms();
	
	
	/**
	 * 获取发送短信信息
	 * @author SLY
	 * @param sysSmsBatchId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getSmsDetailInfo(DataGridModel dgm,Map paramMap);
	
	/**
	 * 发送短信
	 * @param sendSmsList 待发送短信集合
	 * @param loginInfo 操作员登录信息
	 * @return
	 */
	public ResultInfo sendSms(List<SysSmsInfo> sendSmsList,LoginInfo loginInfo);
	
	
	/**
	 * 创建并发送预约打款审核短信
	 * @author ZYM
	 * @param paramMap
	 * @param mobile
	 * @return
	 */
	public ResultInfo createAndSendProductOrderAuditSms(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo);

	/**
	 * 触发生成产品成立短信
	 * @author SLY
	 * @param tradeStatusInfo
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo createProductFoundMes(TradeStatusInfo tradeStatusInfo, LoginInfo loginInfo);
	
	/**
	 * 获取审核短信信息
	 * @author ZYM
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	public DataGrid getSmsAuditInfo(DataGridModel dgm, Map paramMap);
	
	/**
	 * 创建期间分配短信
	 * @author ZYM
	 * @return
	 */
	public ResultInfo createIncomeDisMessage(Map<String, Object> incomDisNotDueProduct, LoginInfo loginInfo);

	/**
	 * 触发生成产品净值短信
	 * @author SLY
	 * @param returnPdwealthNetValueId
	 * @param isOrder 
	 * @param specialType 
	 * @param loginInfo
	 * @return 
	 */
	public ResultInfo createPublicNetValueMes(String returnPdwealthNetValueId, String isOrder, String specialType, LoginInfo loginInfo);

	/**
	 * 创建到期清算配短信
	 * @author ZYM
	 * @return
	 */
	public ResultInfo createIncomeDisAllMessage(Map<String, Object> incomDisNotDueProduct, LoginInfo loginInfo);
	
	/**
	 * 生成ToAgent短信
	 * @author SLY
	 * @return
	 */
	public ResultInfo createBirthToAgentSms();


	/**
	 * 删除短信
	 * @param sysSmsBatchId
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo deleteSms(String sysSmsBatchId, LoginInfo loginInfo);
/*
    *//**
     * 春节祝福短信
     * @return
     *//*
	public ResultInfo createSpringFestival();*/
	
	/**
	 * 创建客户电话变更短信
	 * @author ZYM
	 * @return
	 */
	public ResultInfo createMoidfyMobileMessage(Long custBaseInfoId,String newMobile, LoginInfo loginInfo);

	/**
	 * 创建客户邮箱变更短信
	 * @author ZYM
	 * @param custMobile 
	 * @return
	 */
	public ResultInfo createMoidfyEmailMessage(Long custBaseInfoId, String email, String custMobile, LoginInfo loginInfo);

	/**
	 * 获取客户短信审核信息
	 * @author chengong
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	public DataGrid getCustSmsAuditInfo(DataGridModel dgm, Map paramMap);

	/**
	 * 创建客户账户变更短信
	 * @author ZYM
	 * @param existAccNo 
	 * @param custAccNo 
	 * @return
	 */
	public ResultInfo createMoidfyMobileMessage(Long custBaseInfoId, String existAccNo, String custAccNo,
			LoginInfo loginInfo);

	/**
	 * 创建期间分配到账短信
	 * @author ZYM
	 * @return
	 */
	public ResultInfo createIncomeToAcctSms(Map paramMap, LoginInfo loginInfo);

	/**
	 * 创建到期清算到账短信
	 * @author ZYM
	 * @return
	 */
	public ResultInfo createAllIncomeToAcctSms(Map paramMap, LoginInfo loginInfo);

	/**
	 * 生成股权产品分配短信
	 * @author ZYM
	 * @return
	 */
	public ResultInfo createStockDisSms(Map paramMap, LoginInfo loginInfo);

	/**
	 * 生成股权产品分配到账短信
	 * @author ZYM
	 * @return
	 */
	public ResultInfo createStockToAccSms(Map paramMap, LoginInfo loginInfo);
}
