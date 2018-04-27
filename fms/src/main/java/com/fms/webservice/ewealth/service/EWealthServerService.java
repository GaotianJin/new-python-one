package com.fms.webservice.ewealth.service;
import com.sinosoft.util.ResultInfo;

public interface EWealthServerService {
	/**
	 * 根据产品流水号获取产品详细信息
	 * 
	 * @param productId
	 * @return
	 */
	public String getProductInfo(String productId);

	/**
	 * 根据客户ID查询客户账户信息
	 * 
	 * @param customerNoId
	 * @return
	 */
	public String getCustAccountInfo(String customerNoIdXml);
	/**
	 * 插入客户信息
	 * 
	 * @param custInfoXml
	 * @return
	 */
	public String insertCustInfo(String custInfoXml);

	/**
	 * 插入交易信息
	 * 
	 * @param tradeInfoXml
	 * @return
	 */
	public String insertTradeInfo(String tradeInfoXml);

	/**
	 * 根据客户Id查询客户保险交易信息
	 * 
	 * @param riskTradeInfoXml
	 * @return
	 */
	public ResultInfo getRiskTradeInfo(String riskTradeInfoXml);
	/**
	 * 根据客户Id查询财富产品信息
	 * @param requestXml
	 * @return
	 */
	public ResultInfo getWealthPdInfo(String requestXml);

	/**
	 * 根据用户手机号与用户邮箱查询客户详细信息
	 * @author LIWENTAO
	 * @param requestXml
	 * @return
	 */
	public String getCustomerInfo(String requestXml);
	

	/**
	 * 根据客户编号查询理财师详细信息
	 * @author LIWENTAO
	 * @param requestXml
	 * @return
	 */
	public String getAgentBaseInfo(String requestXml);

	/**
	 * 根据用户手机号与邀请码获取交易信息与邀请码状态
	 * @author LIWENTAO
	 * @param requestXml
	 * @return
	 */
	public String getTradeInfoAndInviteCodeStatus(String requestXml);

	/**
	 * 将邀请码失效
	 * @author LIWENTAO
	 * @param requestXml
	 * @return
	 */
	public String invalidateInviteCode(String requestXml);
	
	/**
	 * 查看理财师是否已经存在于核心系统中
	 * @author LIWENTAO
	 * @param requestXml
	 * @return 
	 */
	public String agentIsExistOrNot(String requestXml);
}
