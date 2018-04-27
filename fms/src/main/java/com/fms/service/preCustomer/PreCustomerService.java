package com.fms.service.preCustomer;

import java.util.Map;

import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface PreCustomerService {
	
	/**
	 * 准客户查询列表
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryPreCustomerList(DataGridModel dgm, Map paramMap,LoginInfo loginInfo);
	/**
	 * 准客户活动量信息查询列表
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getAllPreCustActivityInfo(DataGridModel dgm, Map paramMap,LoginInfo loginInfo);
	/**
	 * 更新准客户信息
	 * @param param
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo getPreCustomerInfo(String param);
	/**
	 * 保存修改的准客户信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo keepPreCustomerInfo(Map tMap,LoginInfo loginInfo);
	
	/**
	 * 准客户基本信息-新增保存
	 * @author LiWenTao
	 * @param map存储客户端页面待保存数据
	 * @param loginInfo存储用户登录信息
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public Map addPreCustomerBasicInfoSave(Map param,LoginInfo loginInfo);
	
	/**
	 * 准客户基本信息-删除保存
	 * @author LiWenTao
	 * @param map
	 * @param loginInfo
	 */
	@SuppressWarnings("rawtypes")
	public Map deletePreCustomerBasicInfo(Map paramMap, LoginInfo loginInfo);
	
	/**
	 * 校验该操作人与财富顾问是否是同一个人
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo updateVerifyPreCustomer(Map paramMap,LoginInfo loginInfo );
	
	
	
	/**
	 * 校验该操作人是不是财富顾问
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo addVerifyPreAgent(LoginInfo loginInfo );
	/**
	 * 导出准客户活动量信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo preCustActivityDetail(Map paramMap,LoginInfo loginInfo);
	/**
	 * 导出准客户活动量管理打分信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo preCustActivityScoreManagement(Map paramMap,LoginInfo loginInfo);
}
