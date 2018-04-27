package com.sinosoft.core.interfaces.util;

/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 *****************************************************/
public class Constants {
	public final static String OPERATE_TYPE_ADD = "0";

	public final static String OPERATE_TYPE_UPDATE = "1";

	public final static String OPERATE_TYPE_DELETE = "2";

	public final static String RESULT_FAILED = "0";

	public final static String RESULT_SUCCESS = "1";

	public final static String USER_INFO_SESSION = "userSessionInfo";

	//数据库有效的记录RC_STATE
	public static final String EFFECTIVE_RECORD = "E";
	//数据库已删除的记录RC_STATE
	public static final String DELETE_RECORD = "D";
	//对应T_DEF_CODE表中tradeStatus
	public static final String TRADE_STATUS_INPUT = "01";
	
	public static final String TRADE_STATUS_CANCEL = "15";
	
	public static final String TRADE_STATUS_CHECK = "02";
	
	public static final String TRADE_STATUS_AUDIT = "03";
	
	public static final String TRADE_STATUS_DONE = "04";
}
