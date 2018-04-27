package com.sinosoft.core.application.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sinosoft.core.application.RuleService;
import com.sinosoft.core.application.util.CalBase;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.db.mapper.TCPdRuleMapper;
import com.sinosoft.core.db.model.TCPdRule;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.StringUtils;

/**
 * 规则计算类，查询解析SQL
 * 
 * @author likai
 * 
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RuleServiceImpl implements RuleService {
	private static final Logger log = Logger.getLogger(RuleServiceImpl.class);
	private String sql;
	//@Autowired
	//private TcPdRuleDAO tcPdRuleDAO;
	@Autowired
	private TCPdRuleMapper tcPdRuleMapper;
	@Autowired
	private CommonService commonService;
	/**
	 * 执行SQL
	 * 
	 * @param id
	 *            SQL对应ID
	 * @param calBase
	 *            计算要素
	 * @return String
	 */
	public String calculate(Integer id, CalBase calBase) {
		if (id == null) {
			log.error("传入计算SQL-ID为空");
			throw new CisCoreException("传入计算SQL-ID为空");
		}
		// 获得待解析计算的SQL
		//TcPdRule tcPdRule = tcPdRuleDAO.load(id);
		TCPdRule tcPdRule = tcPdRuleMapper.selectByPrimaryKey(new BigDecimal(id));
		
		sql = tcPdRule.getRuleSql();
		if (sql == null || "".equals(sql)) {
			log.error("计算SQL为空");
			throw new CisCoreException("计算SQL为空");
		}

		// 将算法中的变量进行解析计算
		this.sql = interpretFactorInSQL(this.sql, calBase);
		if ("".equals(sql)) {
			log.error("SQL合并计算要素后为空");
			throw new CisCoreException("SQL合并计算要素后为空");
		}

		// 执行计算SQL
		String sqlResult = "";
		try {
			sqlResult = executeSQL();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CisCoreException("SQL执行报错");
		}
		log.info("执行后结果：" + sqlResult);

		return sqlResult;
	}

	/**
	 * 解释SQL语句中的变量
	 * 
	 * @return boolean
	 */
	public String interpretFactorInSQL(String sql, CalBase calBase) {
		// 增加计算要素
		if (calBase == null) {
			log.error("计算要素为空");
			throw new CisCoreException("计算要素为空");
		}
		Map<String, Object> hashMap = addBasicFactor(calBase);
		if (hashMap.size() <= 0) {
			log.error("计算要素为空");
			throw new CisCoreException("计算要素为空");
		}
		String str = null;
		StringBuffer stringBuffer = null;
		try {
			while (true) {
				str = StringUtils.getStr(sql, 2, "?").toLowerCase();
				if (str.equals("")) {
					break;
				}
				stringBuffer = new StringBuffer();
				stringBuffer.append("?");
				stringBuffer.append(str);
				stringBuffer.append("?");
				sql = StringUtils.replaceEx(sql, stringBuffer.toString(),
						hashMap.get(str) + "");
			}
			log.info("解析后的SQL：" + sql);
		} catch (Exception ex) {
			log.error("算法SQL中变量解析错误");
			throw new CisCoreException("算法SQL中变量解析错误，具体原因为："+ex);
		}
		return sql;

	}


	/**
	 * 执行SQL语句
	 * 
	 * @return String
	 */
	private String executeSQL() {
		String sqlResult = "";
		//Query query = tcPdRuleDAO.getSessionFactory().getCurrentSession()
		//		.createSQLQuery(sql);
		
		List<String> calSqlList  = commonService.exeQuerySql(sql);
		
		//ArrayList<String> calSqlList = (ArrayList<String>) query.list();

		if (calSqlList != null && calSqlList.size() > 0) {
			sqlResult = String.valueOf(calSqlList.get(0));
		}
		return sqlResult;
	}

	/**
	 * 增加基本要素
	 * 
	 * 将CalBase中的键值对方通过反射放入HashMap
	 * @return Map
	 */
	private Map addBasicFactor(Object obj) {
		Map hashMap = new HashMap();
		String field = "";
		Object value = "";
		try {
			Class c = obj.getClass();
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				if (m[i].getName().indexOf("get") == 0) {
					value = m[i].invoke(obj, new Object[0]);
					field = m[i].getName()
							.substring(3, m[i].getName().length());
					if (value != null && !value.equals("")) {
						hashMap.put(field.toLowerCase(), value);
					}
				}
			}
		} catch (Exception e) {
			log.error("算法计算SQL不存在");
			return null;
		}
		return hashMap;
	}

}