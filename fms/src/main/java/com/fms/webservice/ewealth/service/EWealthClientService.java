package com.fms.webservice.ewealth.service;

import java.util.Map;

import com.sinosoft.util.ResultInfo;

public interface EWealthClientService {
	
	 /**
	 * 产品同步信息给前台端口
	 * @param pramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo ewealthGetProductInfo(Map pramMap);

	/**
	* 产品净值同步前端接口
	* @param paramMap
	* @return
	*/
	@SuppressWarnings("rawtypes")
	public ResultInfo ewealthGetNetValueInfo(Map paramMap);

	/**
	 * 开放日信息同步给前台接口
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo ewealthGetOpenyDayInfo(Map paramMap);

	

}
