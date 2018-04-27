package com.sinosoft.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;


//import com.sinosoft.tip.alipay.epolicy.NumberUtils;


public class SimpleMoneyFormat {
	
	private static final List<String> chineseCapitalNumber = Arrays.asList(new String[]{ "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"});
	
//	private static final List<String> chineseMonetaryIntUnit = Arrays.asList(new String[]{"万","仟","佰","拾","亿","仟","佰","拾","万","仟","佰","拾","元"});
	
	private static final List<String> chineseMonetaryDotUnit = Arrays.asList(new String[]{"角","分","厘"});
	
	private static final String UNIT = "仟佰拾个";
	
	private static final String GRADEUNIT = "仟万亿兆";
	
	private static final int GRADE = 4;
	
	private static final NumberFormat nf = new DecimalFormat("#0.###");

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public static String formatChineseCapital(double amount) {
		String amt = nf.format(amount);
		String dotPart = ""; // 取小数位
		String intPart = ""; // 取整数位
		int dotPos;
		
		if ((dotPos = amt.indexOf('.')) != -1) {
			intPart = amt.substring(0, dotPos);
			dotPart = amt.substring(dotPos + 1);
		} else {
			intPart = amt;
		}
		if (intPart.length() > 16)
			throw new java.lang.InternalError("The amount is too big.");
		String intBig = intToBig(intPart);
		String dotBig = dotToBig(dotPart);
		// 以下代码稍做修改，现在是完美的代码啦！
		if ((dotBig.length() == 0) && (intBig.length() != 0)) {
			return intBig + "元整";
		} else if ((dotBig.length() == 0) && (intBig.length() == 0)) {
			return intBig + "零元";
		} else if ((dotBig.length() != 0) && (intBig.length() != 0)) {
			return intBig + "元" + dotBig;
		} else {
			return dotBig;
		}
	}
	
	/**
	 * 
	 * @param amount
	 * @return
	 */
//	public static String formatChineseCapital(String amount) {
//		if (StringUtils.isNotBlank(amount) && NumberUtils.isNumber(amount)) {
//			return formatChineseCapital(Double.valueOf(amount));
//		}
//		return null;
//	}
	private static String dotToBig(String dotPart) {
		// 得到转换后的大写（小数部分）
		String strRet = "";
		for (int i = 0; i < dotPart.length() && i < 3; i++) {
			int num = Integer.parseInt(dotPart.substring(i, i + 1));
			if (num != 0) {
				strRet += chineseCapitalNumber.get(num) + chineseMonetaryDotUnit.get(i);
			}
		}
		return strRet;
	}

	
	private static String dotToBigUnit(String dotPart) {
		// 得到转换后的大写（小数部分）
		String strRet = "";
		for (int i = 0; i < dotPart.length() && i < 3; i++) {
			int num = Integer.parseInt(dotPart.substring(i, i + 1));
		 
				strRet += chineseCapitalNumber.get(num);
			
		}
		return strRet;
	}

	private static String intToBig(String intPart) {
		// 得到转换后的大写（整数部分）
		int grade; // 级长
		String result = "";
		String strTmp = "";
		// 得到当级长
		grade = intPart.length() / GRADE;
		// 调整级次长度
		if (intPart.length() % GRADE != 0)
			grade += 1;
		
		// 对每级数字处理
		for (int i = grade; i >= 1; i--) {
			strTmp = getNowGradeVal(intPart, i);// 取得当前级次数字
			result += getSubUnit(strTmp);// 转换大写
			result = dropZero(result);// 除零
			// 加级次单位
			if (i > 1) // 末位不加单位
				// 单位不能相连续
				if (getSubUnit(strTmp).equals("零零零零")) {
					result += "零" + GRADEUNIT.substring(i - 1, i);
				} else {
					result += GRADEUNIT.substring(i - 1, i);
				}

		}
		return result;
	}

	private static String getNowGradeVal(String strVal, int grade) {
		// 得到当前级次的串
		String rst;
		if (strVal.length() <= grade * GRADE)
			rst = strVal.substring(0, strVal.length() - (grade - 1) * GRADE);
		else
			rst = strVal.substring(strVal.length() - grade * GRADE, strVal.length() - (grade - 1) * GRADE);
		return rst;
	}

	private static String getSubUnit(String strVal) {
		// 数值转换
		String rst = "";

		for (int i = 0; i < strVal.length(); i++) {
			String s = strVal.substring(i, i + 1);
			int num = Integer.parseInt(s);
			if (num == 0) {
				// “零”作特殊处理
				if (i != strVal.length()) // 转换后数末位不能为零
					rst += "零";
			} else {
				// If IntKey = 1 And i = 2 Then
				// “壹拾”作特殊处理
				// “壹拾”合理
				// Else
				rst += chineseCapitalNumber.get(num);
				// End If
				// 追加单位
				if (i != strVal.length() - 1)// 个位不加单位
					rst += UNIT.substring(i + 4 - strVal.length(), i + 4 - strVal.length() + 1);
			}
		}
		return rst;
	}

	private static String dropZero(String strVal) {
		// 去除连继的“零”
		String strRst;
		String strBefore; // 前一位置字符
		String strNow; // 现在位置字符

		strBefore = strVal.substring(0, 1);
		strRst = strBefore;

		for (int i = 1; i < strVal.length(); i++) {
			strNow = strVal.substring(i, i + 1);
			if (strNow.equals("零") && strBefore.equals("零"))
				;// 同时为零
			else
				strRst += strNow;
			strBefore = strNow;
		}

		// 末位去零
		if (strRst.substring(strRst.length() - 1, strRst.length()).equals("零"))
			strRst = strRst.substring(0, strRst.length() - 1);
		return strRst;
	}
	
	public static String changeToDouble(BigDecimal money){
		String d1 = String.valueOf(money);
		if(d1.contains(".")){
			return d1;
		}
		return d1+".00";
	}

	public static void main(String[] args) {
		System.out.println(
				formatChineseCapital(1234.56));
	}
	
	
	public static String formatChineseShare(double amount) {
		String amt = nf.format(amount);
		String dotPart = ""; // 取小数位
		String intPart = ""; // 取整数位
		int dotPos;
		
		if ((dotPos = amt.indexOf('.')) != -1) {
			intPart = amt.substring(0, dotPos);
			dotPart = amt.substring(dotPos + 1);
		} else {
			intPart = amt;
		}
		if (intPart.length() > 16)
			throw new java.lang.InternalError("The amount is too big.");
		String intBig = intToBig(intPart);
		String dotBig = dotToBigUnit(dotPart);
		// 以下代码稍做修改，现在是完美的代码啦！
		if ((dotBig.length() == 0) && (intBig.length() != 0)) {
			return intBig + "(份)";
		} else if ((dotBig.length() == 0) && (intBig.length() == 0)) {
			return intBig + "(份)";
		} else if ((dotBig.length() != 0) && (intBig.length() != 0)) {
			return intBig+"点"+ dotBig+"(份)";
		} else {
			return dotBig;
		}
	}
}
