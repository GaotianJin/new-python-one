package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

import com.fms.db.model.BasicLaw;
import com.fms.db.model.BasicLawAssess;
import com.fms.db.model.BasicLawIns;
import com.fms.db.model.BasicLawWealth;
import com.fms.db.model.BasicLawYB;
import com.sinosoft.core.db.model.DefCom;


public interface BasicLawServiceMapper {
	
	/**
	 * 基本法版本参数信息列表查询（总记录数）
	 * @param paramMap
	 * @return
	 */
	public Integer queryBasicLawVersionInfoCounts(Map paramMap);
	
	
	/**
	 * 基本法版本参数信息列表查询（分页查询）
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryBasicLawVersionInfoPages(Map paramMap);
	
	
	
	/**
	 * 基本法产品参数信息列表查询（总记录数）
	 * @param paramMap
	 * @return
	 */
	public Integer queryBasicLawProductInfoCounts(Map paramMap);
	
	
	/**
	 * 基本法产品参数信息列表查询（分页查询）
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryBasicLawProductInfoPages(Map paramMap);
	
	
	/**
	 * 基本法产品参数信息-根据版本ID和产品ID查询财富产品奖金比例
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryBasicLawWealthByLPID(Map paramMap);
	
	/**
	 * 基本法产品参数信息-根据版本ID和产品ID查询个人寿险产品奖金比例
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryBasicLawInsByLPID(Map paramMap);
	
	/**
	 * 基本法产品参数信息-根据版本ID和产品ID查询银保产品奖金比例
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryBasicLawYBByLPID(Map paramMap);
	
	/**
	 * 基本法产品参数信息-根据版本ID和产品ID查询产品考核参数
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryBasicLawAssessByLPID(Map paramMap);
	
	
	/**
	 * 查询财富产品除指定的版本外，统计归属的执行状态为启用的其他基本法版本
	 */
	public List<BasicLaw> queryWealthProductToVersionExecState(Map<String, Object> paramMap);
	
	/**
	 * 查询银行保险产品除指定的版本外，统计归属的执行状态为启用的其他基本法版本
	 */
	public List<BasicLaw> queryYBProductToVersionExecState(Map<String, Object> paramMap);
	
	/**
	 * 查询个人保险产品除指定的版本外，统计归属的执行状态为启用的其他基本法版本
	 */
	public List<BasicLaw> queryInsProductToVersionExecState(Map<String, Object> paramMap);
	
	/**
	 * 查询产品考核参数除指定的版本外，统计归属的执行状态为启用的其他基本法版本
	 */
	public List<BasicLaw> queryAssessProductToVersionExecState(Map<String, Object> paramMap);
	
}
