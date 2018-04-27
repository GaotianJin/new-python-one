package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface NetValueServiceMapper {

	// 新增净值信息查询符合条件的行数
	@SuppressWarnings("rawtypes")
	public Integer addNetValueQueryListCount(Map queryParam);

	// 产品设置页面初始化查询脚本
	@SuppressWarnings("rawtypes")
	public List<Map> addNetValueQueryListPage(Map queryParam);

	// 产品净值信息初始页面符合条件的行数
	@SuppressWarnings("rawtypes")
	public Integer netValueQueryListCount(Map queryParam);

	// 产品设置页面初始化查询脚本
	@SuppressWarnings("rawtypes")
	public List<Map> netValueQueryListPage(Map queryParam);

	// 查询净值附件信息页面符合条件的行数
	@SuppressWarnings("rawtypes")
	public Integer netValueFileQueryListCount(Map queryParam);

	// 查询净值附件信息脚本
	@SuppressWarnings("rawtypes")
	public List<Map> netValueFileQueryListPage(Map queryParam);
	// 查询净值信息
	@SuppressWarnings("rawtypes")
	public List<Map> netValueQueryList(Map paramMap);
	// 查询净值信息记录条数
	public Integer netValueQueryCount(Map paramMap);

}
