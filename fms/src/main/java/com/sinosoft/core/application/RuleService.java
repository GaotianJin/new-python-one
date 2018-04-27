package com.sinosoft.core.application;

import com.sinosoft.core.application.util.CalBase;

public interface RuleService {
	/**
	 * 执行SQL
	 * @param id SQL对应ID
	 * @param calBase 计算要素
	 * @return String
	 */
	public String calculate(Integer id, CalBase calBase);

	
	/**
	 * 解析SQL语句和提示语中的变量
	 * @param sql
	 * @param hashMap
	 * @return String
	 */
	public String interpretFactorInSQL(String sql ,CalBase calBase);
}