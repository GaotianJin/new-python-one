package com.fms.service.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FundsShareServiceMapper {

	Integer getTradeFundsTotal(Map paramMap);

	List<Map<String, String>> getTradeFundsServiceList(Map paramMap);
	
   //获取基金份额可受让信息总记录数
	@SuppressWarnings("rawtypes")
	Integer getTradeFundsTransFereeTotal(Map paramMap);
	
	//获取基金份额可受让信息总记录
	@SuppressWarnings("rawtypes")
	List<Map<String, String>> getTradeFundsTransFereeServiceList(Map paramMap);
	
	//获取产品记录
	@SuppressWarnings("rawtypes")
	List<Map> getProductMapList(Map paramMap);
	Map<String, String> getTradeDetailInfo(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryTransferAuditList(Map paramMap);
	public Integer queryTransferAuditListCount(Map paramMap);
/**
 * 获取客户等级
 * @param queryMap
 * @return
 */
	Map getCustLevel(Map queryMap);
	//获取基金份额可受让信息总记录
@SuppressWarnings("rawtypes")
List<Map<String, String>> getTradeFundsTransFereeServiceListTotal(Map paramMap);

}
