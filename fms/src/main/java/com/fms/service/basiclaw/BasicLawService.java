package com.fms.service.basiclaw;

import java.util.Map;
import com.fms.db.model.BasicLaw;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.LoginInfo;

public interface BasicLawService {
	
	/**
	 * 基本法版本信息-新增保存
	 * @param map
	 * @param loginInfo
	 */
	public void addBasicLawVersionSave(Map map,LoginInfo loginInfo);
	
	
	/**
	 * 基本法版本信息-更新保存
	 * @param map
	 * @param loginInfo
	 */
	public void updateBasicLawVersionSave(Map map,LoginInfo loginInfo);
	
	
	/**
	 * 基本法版本信息-更新保存
	 * @param map
	 * @param loginInfo
	 */
	public void deleteBasicLawVersionSave(Long basicLawId,LoginInfo loginInfo);
	
	
	/**
	 * 财富产品奖金比例-新增保存
	 * @param map
	 * @param loginInfo
	 */
	public void addBasicLawProductWealthSave(Map map,LoginInfo loginInfo);
	
	
	/**
	 * 财富产品奖金比例-更新保存
	 * @param map
	 * @param loginInfo
	 */
	void updateBasicLawProductWealthSave(Map map, LoginInfo loginInfo);
	
	/**
	 * 保险产品奖金比例-新增保存
	 * @param map
	 * @param loginInfo
	 */
	public void addBasicLawProductInsSave(Map map,LoginInfo loginInfo);
	
	/**
	 * 保险产品奖金比例-更新保存
	 */
	void updateBasicLawProductInsSave(Map map, LoginInfo loginInfo);
	
	/**
	 * 银行保险产奖金比例品-新增保存
	 * @param map
	 * @param loginInfo
	 */
	public void addBasicLawProductYBSave(Map map,LoginInfo loginInfo);
	
	/**
	 * 车险产品参数-更新保存
	 */
	void updateBasicLawProductVehicleSave(Map map, LoginInfo loginInfo);
	
	/**
	 * 车险产品参数-新增保存
	 * @param map
	 * @param loginInfo
	 */
	public void addBasicLawProductVehicleSave(Map map,LoginInfo loginInfo);
	
	/**
	 * 银行保险产奖金比例品-更新保存
	 */
	void updateBasicLawProductYBSave(Map map, LoginInfo loginInfo);
	
	
	/**
	 * 产品考核参数新增保存
	 * @param map
	 * @param loginInfo
	 */
	public void addBasicLawProductAssessSave(Map map,LoginInfo loginInfo);
	
	
	
	/**
	 * 产品考核参数-更新保存
	 */
	void updateBasicLawProductAssessSave(Map map, LoginInfo loginInfo);
	
	
	/**
	 * 基本法产品参数-删除保存
	 * @param map
	 * @param loginInfo
	 */
	public void deleteBasicLawProductSave(Map paramMap,LoginInfo loginInfo);
	
	/**
	 * 基本法版本参数信息列表查询
	 * @param dgm
	 * @param basicLaw
	 * @return
	 */
	public Map<String, Object> getBasicLawVersionPageList(DataGridModel dgm, Map<String,Object> paramMap);
	
	/**
	 * 基本法产品参数信息列表查询
	 * @param dgm
	 * @param basicLaw
	 * @return
	 */
	public Map<String, Object> getBasicLawProductPageList(DataGridModel dgm, Map<String,Object> paramMap);
	
	/**
	 * 根据基本法版本ID获取基本法版本信息
	 * @param basicLawVersion
	 * @return
	 */
	public BasicLaw getBasicLawVersionInfoById(Long basicLawVersion);
	
	
	/**
	 * 根据基本法版本ID,产品ID获取基本法版本信息
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> queryBasicLawProductVersionInfo(Map<String, Object> paramMap);
	
	
	
	/**
	 * 根据产品类型和产品Id验证该产品是否已经存在有效的考核基本法
	 * @param productType
	 * @param productSubType
	 * @param productId
	 * @return
	 */
	public  Boolean verifyBasicExist(String productType,String productSubType,String productId);
	
	
	
}
