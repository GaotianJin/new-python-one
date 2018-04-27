package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface BuildServiceMapper {
	
	/**
	 * 楼盘信息列表查询（全部）
	 * @param paramMap
	 * @return
	 */
	public Integer branchBuildListCount(Map queryParam);
	
	/**
	 * 楼盘信息列表查询（全部）
	 * @param paramMap
	 * @return
	 */
	public List<Map> branchBuildListPage(Map queryParam);
	
	/**
	 * 楼盘下拉项信息查询
	 * @return
	 */
	public List<Map>  queryBuildListCode();
	
	 
	/**
	 * 判断楼盘下是否有网点归属
	 * */
	public Integer branchStoreCount(Map map);
}
