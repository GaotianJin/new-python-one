package com.sinosoft.core.service.mapper;

import java.util.List;
import java.util.Map;


public interface CommonServiceMapper {
	
	/**执行SQL语句
	 * @param paramMap
	 * @return
	 */
	public List<String> exeQuerySql(Map<String, String> paramMap);
	
	/**获取sequence的值
	 * @param paramMap
	 * @return
	 */
//	public String getSeqValByName(Map paramMap);
}
