package com.fms.service.trade;

import java.util.List;
import java.util.Map;

import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface RedemptionService {

	//根据不同权限对客户进行查询
	public List<Map<String, String>> redemptionCustomerQuery(LoginInfo loginInfo);
	//查询产品信息
	public List<Map<String, String>> redemptionProductQuery();
	//根据客户号和产品Id查询具体的交易信息
	@SuppressWarnings("rawtypes")
	public ResultInfo queryTradeInfoByCustandPro(Map paramMap);
	//查询产品开放日
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> redemptionExpectOpenDayQuery(Map paramMap);
	//根据产品和开放日查找相应的费后的净值信息
	@SuppressWarnings("rawtypes")
	public ResultInfo queryNetValueByOpenDay(Map paramMap);
	//保存赎回信息
	@SuppressWarnings("rawtypes")
	public ResultInfo addRedemptionInfo(Map paramMap,LoginInfo loginInfo);
	//修改赎回信息
	@SuppressWarnings("rawtypes")
	public ResultInfo updateRedemptionInfo(Map paramMap,LoginInfo loginInfo);
	//生成赎回单
	@SuppressWarnings("rawtypes")
	public ResultInfo printApplicationForm(Map map,String uploadDir ,LoginInfo loginInfo);
	//赎回申请单下载初始列表
	@SuppressWarnings("rawtypes")
	public DataGrid getRedemptionPrintList(DataGridModel dgm, Map paramMap);
	//交易赎回页面信息列表
	@SuppressWarnings("rawtypes")
	public DataGrid getTradeRedemptionPageList(DataGridModel dgm, Map paramMap,LoginInfo loginInfo);
	//赎回撤销
	@SuppressWarnings("rawtypes")
	public ResultInfo cancelTradeRedemption(Map map,LoginInfo loginInfo);
	//交易赎回修改初始页面获值
	public ResultInfo getTradeRedemptionInfo(String redemptionInfoId, LoginInfo loginInfo);
	//赎回提交确认至复核岗
	public ResultInfo commitRedemptionCheck(String redemptionInfoId, LoginInfo loginInfo);
	//赎回确认初始页面列表信息
	@SuppressWarnings("rawtypes")
	public DataGrid  queryRedemptionConfirmList(DataGridModel dgm, Map paramMap,LoginInfo loginInfo);
	//赎回确认页面获取值
	public ResultInfo getRedemptionConfirmInfo(String redemptionInfoId, LoginInfo loginInfo);
	//保存赎回确认结论
	@SuppressWarnings("rawtypes")
	public ResultInfo saveRedemptionConfirmCheckInfo(Map paramMap,LoginInfo loginInfo);
	//赎回信息上传初始页面列表信息
	@SuppressWarnings("rawtypes")
	public DataGrid queryRedemptionUploadInfoList(DataGridModel dgm, Map paramMap,LoginInfo loginInfo);
	//赎回上传操作页面获取值
	public ResultInfo getRedemptionUploadInfo(String redemptionInfoId, LoginInfo loginInfo);
	//赎回明细上传信息保存
	@SuppressWarnings("rawtypes")
	public ResultInfo saveRedemptionUploadInfo(Map paramMap,LoginInfo loginInfo);
	// 赎回明细上传页面获取实际赎回总信息（实际赎回份额，赎回净值，实际赎回总金额)
	public ResultInfo getActuRedemptionInfo(String redemptionInfoId);
	//根据客户名去查找相应的已经购买的产品
	public List<Map<String, String>> redemtionProductQueryByCustNo(LoginInfo loginInfo,String custNo);
	// 获取赎回结论
	public ResultInfo getDetailActuRedemptionInfo(String redemptionInfoId);
	//根据客户号查找证件号码
	public ResultInfo queryCustomerInfo(String custNo);
	
}
