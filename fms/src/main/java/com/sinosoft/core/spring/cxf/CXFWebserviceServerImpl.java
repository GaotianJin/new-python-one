package com.sinosoft.core.spring.cxf;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;

import com.fms.webservice.wechat.service.WechatServerService;

@WebService(endpointInterface = "com.sinosoft.core.spring.cxf.CXFWebserviceServer")
public class CXFWebserviceServerImpl implements CXFWebserviceServer {

	/*@Autowired
	private EWealthServerService eWealthServerService;*/
	@Autowired
	private WechatServerService wechatServerService;

	
	/**
	 * 微信登录
	 * @author zhuyueming
	 * @param requestJson
	 * @return
	 */
	public String wechatLogin(String requestJson){
		
		String ip = ContextServlet.path.get();
		String resultJson = wechatServerService.login(requestJson,ip);
		return resultJson;
	}


	/**
	 * 我的固定资产
	 * @author zhuyueming
	 * @param requestJson
	 * @return
	 */
	public String myFixedAsset(String requestJson){
		String resultJson = wechatServerService.myFixedAsset(requestJson);
		return resultJson;
	}

	/**
	 * 我的浮动资产
	 * @author zhuyueming
	 * @param requestJson
	 * @return
	 */
	public String myFloatAsset(String requestJson){
		String resultJson = wechatServerService.myFloatAsset(requestJson);
		return resultJson;
	}
	
	/**
	 * 根据客户号查询浮收产品历史
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String findAllBoughtProduct(String requestJson){
		String resultJson = wechatServerService.findAllBoughtProduct(requestJson);
		return resultJson;
	}
////////////////////HSH///////////////////////////////////////////	
	/**
	 * 根据客户号和产品ID股权产品详情
	 * @author chengong
	 * @param requestJson
	 * @return
	 */
	public String stockProductDetail(String requestJson){
		String resultJson = wechatServerService.stockProductDetail(requestJson);
		return resultJson;
	}
	/**
	 * 根据客户号和产品ID查询浮收产品详情
	 * @author chengong
	 * @param requestJson
	 * @return
	 */
	public String floatProductDetail(String requestJson){
		String resultJson = wechatServerService.floatProductDetail(requestJson);
		return resultJson;
	}
	/**
	 * 根据客户号和产品ID查询固收产品详情
	 * @author chengong
	 * @param requestJson
	 * @return
	 */
	public String fixedProductDetail(String requestJson){
		String resultJson = wechatServerService.fixedProductDetail(requestJson);
		return resultJson;
	}	
	/**
	 * 根据客户号查询固收产品历史
	 * @author HSH
	 * @param requestJson
	 * @return
	 */
	public String fixedProductHistory(String requestJson){
		String resultJson = wechatServerService.fixedProductHistory(requestJson);
		return resultJson;
	}
	
	
	/**
	 * 根据客户号查询浮收产品历史
	 * @author HSH
	 * @param requestJson
	 * @return
	 */
	public String floatProductHistory(String requestJson){
		String resultJson = wechatServerService.floatProductHistory(requestJson);
		return resultJson;
	}
	
	
////////////////////HSH///////////////////////////////////////////
	/**
	 * 根据客户号查询理财经理信息
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findAgentInfoByCustNo(String requestJson) {
		String resultJson = wechatServerService.findAgentInfoByCustNo(requestJson);
		return resultJson;
	}

	/**
	 * 投资分析客户资产配置
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String customerAssetAllocation(String requestJson) {
		String resultJson = wechatServerService.customerAssetAllocation(requestJson);
		return resultJson;
	}
	

	

	/**
	 * 投资分析历史收益排名接口
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String historyIncomeRanking(String requestJson) {
		String resultJson = wechatServerService.historyIncomeRanking(requestJson);
		return resultJson;
	}

	/**
	 * 投资分析固收类产品回款预期接口
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String solidProductReturnedMoney(String requestJson) {
		String resultJson = wechatServerService.solidProductReturnedMoney(requestJson);
		return resultJson;
		
	}


	@Override
	public String findAllProduct(String requestJson) {
		String resultJson = this.wechatServerService.findAllProduct(requestJson );
		return resultJson;
	}

	
				/* ==========================二期新增接口============================== */
							/* ================LOUAO================ */
	@Override
	public String isOldCustomer(String requestJson) {
		String resultJson = this.wechatServerService.isOldCustomer(requestJson );
		return resultJson;
	}
	
	
	@Override
	public String isOldCustInfo(String requestJson) {
		String resultJson = this.wechatServerService.isOldCustInfo(requestJson );
		return resultJson;
	}
	/**
	 * 定期产品列表
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findAllFixedProduct(String requestJson) {
		String resultJson = this.wechatServerService.findAllFixedProduct(requestJson);
		return resultJson;
	}
	
	@Override
	public String findFixedProductDetail(String requestJson) {
		String resultJson = this.wechatServerService.findFixedProductDetail(requestJson );
		return resultJson;
	}


	@Override
	public String findAllStockProduct(String requestJson) {
		String resultJson = this.wechatServerService.findAllStockProduct(requestJson);
		return resultJson;
	}


	@Override
	public String findStockProductDetail(String requestJson) {
		String resultJson = this.wechatServerService.findStockProductDetail(requestJson );
		return resultJson;
	}
							/* ================SHIYUNPENG================ */
	@Override
	public String findFloatProductList(String requestJson) {
		String resultJson = this.wechatServerService.findFloatProductList(requestJson );
		return resultJson;
	}
	@Override
	public String findFloatProductDetails(String requestJson) {
		String resultJson = this.wechatServerService.findFloatProductDetails(requestJson );
		return resultJson;
	}


	@Override
	public String findOverseasProductList(String requestJson) {
		String resultJson = this.wechatServerService.findOverseasProductList(requestJson );
		return resultJson;
	}


	@Override
	public String findOverseasProductDetails(String requestJson) {
		String resultJson = this.wechatServerService.findOverseasProductDetails(requestJson );
		return resultJson;
	}
							/* ================ZYM================ */
	/**
	 * 查询理财经理名下客户
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findAllCustomer(String requestJson) {
		String resultJson = wechatServerService.findAllCustomer(requestJson);
		return resultJson;
		
	}
	
	/**
	 * 查询客户基本信息
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String queryCustBaseInfo(String requestJson) {
		String resultJson = wechatServerService.queryCustBaseInfo(requestJson);
		return resultJson;
		
	}
	
	/**
	 * 查询个人账户信息
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String queryPersonalInfo(String requestJson) {
		String resultJson = wechatServerService.queryPersonalInfo(requestJson);
		return resultJson;
		
	}
	
	/**
	 * 我的股权资产
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String myStockAsset(String requestJson) {
		String resultJson = wechatServerService.myStockAsset(requestJson);
		return resultJson;
		
	}
	
	/**
	 * 我的海外资产
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String myOverseasAsset(String requestJson) {
		String resultJson = wechatServerService.myOverseasAsset(requestJson);
		return resultJson;
		
	}
	
	/**
	 * 我的投资总额
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String myInvestAmount(String requestJson) {
		String resultJson = wechatServerService.myInvestAmount(requestJson);
		return resultJson;
		
	}
	
	/**
	 * 查询热门产品列表
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String hotProductList(String requestJson) {
		String resultJson = wechatServerService.hotProductList(requestJson);
		return resultJson;
		
	}
				
			/* ================CJJ================ */
	/**
	 * 根据客户号查询股权产品历史记录
	 * @param requestJson
	 * @return
	 */
	public String stockProductHistory(String requestJson){
		String resultJson = wechatServerService.stockProductHistory(requestJson);
		return resultJson;
	}
	/**
	 * 根据客户号查询环球产品历史
	 * @author HSH
	 * @param requestJson
	 * @return
	 */
	public String overseasProductHistory(String requestJson){
		String resultJson = wechatServerService.overseasProductHistory(requestJson);
		return resultJson;
	}
	
	/**
	 * 根据客户号和时间查鲸币
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String whaleCoin(String requestJson){
		String resultJson = wechatServerService.whaleCoin(requestJson);
		return resultJson;
	}
	
	/**
	 * 根据客户号查预约记录
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String productOrderRecord(String requestJson){
		String resultJson = wechatServerService.productOrderRecord(requestJson);
		return resultJson;
	}
	
	/**
	 * 根据理财经理Id查rm详情
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String getAgentDetailInfo(String requestJson){
		String resultJson = wechatServerService.getAgentDetailInfo(requestJson);
		return resultJson;
	}
	
	/**
	 * 根据产品Id查所有净值
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String getAllNetValue(String requestJson){
		String resultJson = wechatServerService.getAllNetValue(requestJson);
		return resultJson;
	}
	
	/**
	 * 根据手机号查询是否是员工
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String isEmployee(String requestJson){
		String resultJson = wechatServerService.isEmployee(requestJson);
		return resultJson;
	}
	
	/**
	 * 查询所有浮动产品
	 * @author ZYM
	 * @param requestJson
	 * @return
	 */
	public String queryAllFloatProducts(String requestJson){
		String resultJson = wechatServerService.queryAllFloatProducts(requestJson);
		return resultJson;
	}
}
