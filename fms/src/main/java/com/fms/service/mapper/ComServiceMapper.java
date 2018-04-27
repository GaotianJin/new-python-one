package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

import com.sinosoft.core.db.model.DefCom;


public interface ComServiceMapper {
	
	/**
	 * 机构信息列表查询（全部）
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryComListInfo(Map paramMap);
	
	/**
	 * 机构信息列表查询（总记录数）
	 * @param paramMap
	 * @return
	 */
	public Integer queryComListInfoCounts(Map paramMap);
	
	
	/**
	 * 机构信息列表查询（分页查询）
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryComListInfoPages(Map paramMap);
	
	
	/**
	 * 机构下拉项信息查询
	 * @return
	 */
	public List<Map>  queryComListCode();
	
	
	/**
	 * 查询机构归属信息列表
	 * @return
	 */
	public List<Map> queryComBelongListInfo(Map paramMap);
	
	/**
	 * 根据机构名称查询机构
	 * @param defCom
	 * @return
	 */
	public List<DefCom> queryComByComName(DefCom defCom);
	
	/**
	 * 根据机构代码查询机构
	 * @param defCom
	 * @return
	 */
	public List<DefCom> queryComByComCode(DefCom defCom);
	
}
