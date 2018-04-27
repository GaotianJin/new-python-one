package com.fms.service.sales;

import java.math.BigDecimal;
import com.fms.db.model.Agent;
import com.fms.db.model.PDProduct;
import com.fms.db.model.TradeInfo;
import com.fms.db.model.TradeStatusInfo;
import com.sinosoft.util.ResultInfo;

/*
 * 奖金计算类
 */
public interface CalSalaryService {
	/**计算每笔交易的奖金**/
	public ResultInfo calSalByTrade(String tradeInfoId,BigDecimal trueMonery,PDProduct pdProduct,String calYear,String calMonth) ;
	/**获取奖金比例**/
	public ResultInfo getBonusRate(String tradeInfoId,PDProduct pdProduct,String calYear,String calMonth);
	/**获取标准保费**/
	public ResultInfo getStandrdPrem(String tradeInfoId,BigDecimal trueMonery,PDProduct pdProduct);
	/**获取某个单子的犹退的奖金金额**/
	public ResultInfo getRollBackBonos(TradeInfo tradeInfo,PDProduct pdProduct,Agent agent,TradeStatusInfo tradeStatusInfo);
	/**获取某个单子的折标系数**/
	public ResultInfo getScaleFactor(String tradeInfoId,BigDecimal actulPrem);
	/**获取理财经理的管理奖金**/
	public ResultInfo getsumManageBonus(String agentId,String departMentId,String CalYear,String calmonth);
	/**获得某笔保单的标准资产**/
	public ResultInfo getStandAsset(TradeStatusInfo tradeStatusInfo,PDProduct pdProduct); 
	/**获得久期系数**/
	public ResultInfo getdurationFactor(String tradeInfoId,String payperiod,String payperiodUnit);
	/**获取财富产品期限**/
	public ResultInfo getProductDedline(String tradeInfoId);
	
}
