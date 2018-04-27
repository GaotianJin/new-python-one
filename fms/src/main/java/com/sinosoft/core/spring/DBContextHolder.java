package com.sinosoft.core.spring;

public class DBContextHolder {
	/*public static final String DATA_SOURCE_CIS = "dataSource";
	public static final String DATA_SOURCE_LIS = "dataSourceLIS";
	public static final String DATA_SOURCE_YBT = "dataSourceYBT";
	// 业财
	public static final String DATA_SOURCE_YC = "dataSourceYC";
	// 短信平台
	public static final String DATA_SOURCE_DX = "dataSourceDX";
	// 批单和理赔信打印
	public static final String DATA_SOURCE_PRINT = "dataSourcePRINT";

	// 同业公会
	public static final String DATA_SOURCE_TY = "dataSourceTY";

	// AS400数据源，照会接口使用
	public static final String DATA_SOURCE_AS400 = "dataSourceAS400";
	
	public static final String DATA_SOURCE_AS400_ = "dataSourceAS400_";

	// AS400数据源，NFO接口使用
	public static final String DATA_SOURCE_AS400_NFO = "dataSourceAS400_NFO";*/


	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDBType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDBType() {
		return contextHolder.get();
	}

	public static void clearDBType() {
		contextHolder.remove();
	}
}