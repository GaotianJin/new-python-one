package com.sinosoft.core.aop;
/*****************************************************
 * HISTORY
 * 
 * 2013/04/05 chenj 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 *****************************************************/
import org.aspectj.lang.JoinPoint;

import com.sinosoft.core.application.util.CisCoreException;

public interface CommonLog {

	public void before(JoinPoint call);
	public void afterReturn(JoinPoint call);
	public void afterThrowing(CisCoreException e);
	
	
}
