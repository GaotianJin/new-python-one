package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface DepartmentServiceMapper {
	/**
	 * 业务部下拉项信息查询
	 * @return
	 */
	public List<Map>  queryDepartmentListCode();
	
	/**
	 * 业务部信息列表查询（全部）
	 * @param paramMap
	 * @return
	 */
	public Integer branchDepartmentListCount(Map queryParam);
	
	/**
	 * 业务部信息列表查询（全部）
	 * @param paramMap
	 * @return
	 */
	public List<Map> branchDepartmentListPage(Map queryParam);
	
	/**
	 * 查询机构归属信息列表
	 * @return
	 */
	public List<Map> queryDepartmentBelongListInfo(Map paramMap);
}
