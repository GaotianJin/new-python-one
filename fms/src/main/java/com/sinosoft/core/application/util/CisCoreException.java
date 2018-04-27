package com.sinosoft.core.application.util;
/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 * Description 核心系统异常处理类
 *****************************************************/

public class CisCoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorCode = "";

	public CisCoreException() {
		super();
	}

	public CisCoreException(String msg, Throwable cause) {
		super(msg);
		super.initCause(cause);
	}

	public CisCoreException(String msg) {
		super(msg);
	}

	public CisCoreException(Throwable cause) {
		super();
		super.initCause(cause);
	}

	public CisCoreException(String msg, String errorcode, Throwable cause) {
		super(msg);
		super.initCause(cause);
		errorCode = errorcode;
	}

	public CisCoreException(String msg, String errorcode) {
		super(msg);
		errorCode = errorcode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
