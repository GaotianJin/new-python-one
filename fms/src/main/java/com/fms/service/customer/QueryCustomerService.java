package com.fms.service.customer;

import java.util.Map;
import com.sinosoft.util.ResultInfo;

public interface QueryCustomerService {
	/**
	 * 根据手机号，邮箱号查询客户详细信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	public ResultInfo queryCustomerInfo(Map<String, Object> paraMap);
	
	/**
	 * 只根据手机号查询客户详细信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	public ResultInfo obtainCustomerInfoOnlyByMobile(Map<String, Object> paraMap);
	/**
	 * 只根据邮箱查询客户详细信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	public ResultInfo obtainCustomerInfoOnlyByEmail(Map<String, Object> paraMap);
	
	/**
	 * 根据客户编码查询理财师详细信息
	 * @author LIWENTAO
	 * @param customerId
	 * @return
	 */
	public ResultInfo queryAgentInfo(String customerId);
	
	/**
	 * 根据绿城客户号查询其姓名信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	public ResultInfo obtainCustomerNameInfo(Map<String, Object> paraMap);
	
	/**
	 * 根据绿城客户号查询其订单信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	public ResultInfo obtaineweatlthProductOrderInfo(Map<String, Object> paraMap);
	
	/**
	 * 查看理财师编号是否存在于核心系统中
	 * @author LIWENTAO
	 * @param paraMap
	 * @return 
	 */
	public ResultInfo obtainAgentIsExistInfo(Map<String, Object> paraMap);
}
