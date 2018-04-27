package com.fms.service.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.util.ResultInfo;

public interface QueryCustomerServiceMapper {
	
	/**
	 * 根据手机号与邮箱号查询客户详细信息
	 * @author LIWENTAO
	 * @param paramMap
	 * @return 
	 */
	public List<LinkedHashMap<String, Object>> queryCustomerInfo(Map<String, Object> paramMap);
	
	/**
	 * 只根据手机号查询客户详细信息
	 * @author LIWENTAO
	 * @param paramMap
	 * @return
	 */
	public List<LinkedHashMap<String, Object>> queryCustomerInfoOnlyByMobile(Map<String, Object> paraMap);
	
	/**
	 * 只根据邮箱查询客户详细信息
	 * @author LIWENTAO
	 * @param paramMap
	 * @return
	 */
	public List<LinkedHashMap<String, Object>> queryCustomerInfoOnlyByEmail(Map<String, Object> paraMap);
	
	/**
	 * 根据客户编码查询客户地址类型
	 * @author LIWENTAO
	 * @param paramMap
	 * @return
	 */
	public List<LinkedHashMap<String, String>> queryAddressInfo(Map<String, Object> paramMap);
	
	/**
	 * 根据客户编码查询理财师详细信息
	 * @author LIWENTAO
	 * @param paramMap
	 * @return
	 */
	public List<LinkedHashMap<String, String>> queryAgentBaseInfo(Map<String, String> paramMap);
	
	/**
	 * 根据手机号，邀请码获取交易信息
	 * @author LIWENTAO
	 * @param paramMap
	 * @return
	 */
	public List<LinkedHashMap<String, String>> queryProductOrderInfoByMobileNumAndInviteCode(Map<String, Object> paraMap);
	
	/**
	 * 根据邀请码获取交易信息
	 * @author LIWENTAO
	 * @param paramMap
	 * @return
	 */
	public List<LinkedHashMap<String, String>> queryProductOrderInfoByInviteCode(Map<String, Object> paraMap);
	
	/**
	 * 根据绿城客户号查询其姓名信息
	 * @author LIWENTAO
	 * @param paramMap
	 * @return
	 */
	public Map<String, String> queryCustomerName(Map<String, Object> paraMap);
	
	/**
	 * 根据绿城客户号查询其订单信息
	 * @author LIWENTAO
	 * @param paramMap
	 * @return
	 */
	public List<LinkedHashMap<String, String>> queryeweatlthProductOrder(Map<String, Object> paraMap);
	
	/**
	 * 查询理财师信息是否存在
	 * @author LIWENTAO
	 * @param paramMap
	 * @return
	 */
	public List<LinkedHashMap<String, String>> queryAgentInfo(Map<String, Object> paraMap);
	
}
