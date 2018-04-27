package com.sinosoft.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 工具类（数字工具）
 * 
 * @author likai
 * 
 */
public class NumberUtils extends org.springframework.util.NumberUtils {

	/**
	 * 设置数字精度 需要格式化的数据
	 * 
	 * @param value
	 *            BigDecimal
	 * @param precision
	 *            String 精度描述（0.00表示精确到小数点后两位）
	 * @return BigDecimal
	 */
	public static BigDecimal setPrecision(BigDecimal bigDecimal,
			String precision) {
		return new BigDecimal(new DecimalFormat(precision).format(bigDecimal));
	}

	/**
	 * 设置数字精度 需要格式化的数据
	 * 
	 * @param value
	 *            double
	 * @param precision
	 *            String 精度描述（0.00表示精确到小数点后两位)
	 * @return double
	 */
	public static double setPrecision(double value, String precision) {
		return Double.parseDouble(new DecimalFormat(precision).format(value));
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double roundDouble(double value, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b = new BigDecimal(Double.toString(value));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static BigDecimal round(double value, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b = new BigDecimal(Double.toString(value));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param value
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static BigDecimal round(BigDecimal value, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal one = new BigDecimal("1");
		return value.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 提供精确的四舍五入计算处理
	 * @param scale
	 *            输入的乘数
	 * @param scale
	 *            输入的被乘数
	 * @return 四舍五入后的结果
	 */
	public static double mul(double value,BigDecimal scale){
		BigDecimal b1 = new BigDecimal(Double.valueOf(value));
		BigDecimal b2 = b1.multiply(scale);
		return b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}

	/**
	 * 提供精确的四舍五入计算处理
	 * @param scale
	 *            输入的乘数
	 * @param scale
	 *            输入的被乘数
	 * @return 四舍五入后的结果
	 */
	public static double mul(String value,String scale){
		BigDecimal b1 = new BigDecimal(value);
		System.out.println(new BigDecimal(scale.trim()).divide(new BigDecimal("100")));
		BigDecimal b2 = b1.multiply(new BigDecimal(scale.trim()).divide(new BigDecimal("100")));
		return b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	
	/**
	 * @param v1
	 * @param v2
	 * @param precision 精度
	 * @return
	 */
	public static double multiply(String v1,String v2){        
		BigDecimal b1 = new BigDecimal(v1);        
		BigDecimal b2 = new BigDecimal(v2);        
		return b1.multiply(b2).doubleValue();    
	}
	
	public static double divide(String v1,String v2){        
		BigDecimal b1 = new BigDecimal(v1);        
		BigDecimal b2 = new BigDecimal(v2);        
		return b1.divide(b2).doubleValue();    
	}
	
	public static String formatDouble(double doubleValue){
		DecimalFormat df = new DecimalFormat("0.00");
		Double d = new Double(doubleValue);
		return df.format(d);
	}
	
	
	public static double subtract(double d1,double d2){
		double result = 0;
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		result = b1.subtract(b2).doubleValue();
		return result;
	}
	/**
	 * 提供精确的四舍五入计算处理
	 * @param scale
	 *            输入的乘数
	 * @param scale
	 *            输入的被乘数
	 * @return 四舍五入后的结果
	 */
	
	public static void main(String[] args) {
		
		DecimalFormat df=new DecimalFormat(".00");
		double d=1252;
		String st=df.format(d);
		System.out.println(st);
		
		String v1 = "1000000.08";
		String v2 = "12.0";
		System.out.println(round(multiply(v1,v2),4));
//		DecimalFormat    df   = new DecimalFormat("######0.00");
//		double d1 = 3.23456;
//		String str=d1.format(df);
//		String b1="1000";
//		String b2 ="1.0340";
//		System.out.println(b2);
//		System.out.println(mul(b1,b2));
	}
}
