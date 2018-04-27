package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface IndexServiceMapper {

	/**
	 * 公司政策列表查询
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryComPolicyListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryComPolicyList(Map paramMap);
	
	/**
	 * 获取公司政策信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getCompanyPolicyContext(Map paramMap);
	
	/**
	 * 获取行业新闻信息
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProfessionNewsContext(Map paramMap);
	/**
	 * 通讯录列表查询
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryListComRosterCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryListComRoster(Map paramMap);
	/**
	 * 行业新闻列表查询
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryProfessionNewsListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryProfessionNewsList(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public Integer queryCompanyNewsListCount(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryCompanyNewsList(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map> getCompanyNewsContext(Map paramMap);
	/**
	 * 查询用户自我介绍信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String querySelfIntroductionText(Map paramMap);
	

}
