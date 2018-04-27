package com.eighth.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static Date setStringToDate(String strDate){
		if(StringUtil.isEmpty(strDate)){
			return null;
		}else{
			Date date=null;
			try {
				date=sdf.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
	}
	
	public static String setDateToString(Date date){
		String strDate=sdf.format(date);
		return strDate;
	}
}
