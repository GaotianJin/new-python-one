package com.sinosoft.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class DateUtils {
	private static final Logger log = Logger.getLogger(DateUtils.class);

	/**
	 * 获取当前系统时间戳
	 * 
	 * @return Date
	 * @author likai
	 */
	public static Date getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	/*public static void main(String[] args) {
		Date timestamp = getTimestamp(new Date());
			
		System.out.println(timestamp);
	}*/
	/**
	 * 获取当前系统时间戳
	 * 
	 * @return String
	 * @author likai
	 */
	public static String getCurrentTimestampString() {
		String timestamp = new Timestamp(System.currentTimeMillis()).toString();
		return "to_timestamp('" + timestamp + "','yyyy-mm-dd hh24:mi:ss.ff')";
	}

	/**
	 * 得到当前系统日期,日期格式为"yyyy-MM-dd"
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(new Date());
	}

	/**
	 * 功能:得到某时间单位,在某个时间段内往后或往前推某个时间段的日期 日期格式：yyyy--MM--dd unit:Y,M,D
	 * 
	 * @param baseDate
	 *            起始日期
	 * @param interval
	 *            时间间隔
	 * @param unit
	 *            时间间隔单位，可以为 Y（年）、M（月）、D（天）
	 * @return Date
	 */
	public static Date calDate(Date baseDate, int interval, String unit) {
		Date returnDate = null;

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(baseDate);

		if (unit.equals("Y")) {
			gregorianCalendar.add(Calendar.YEAR, interval);
		}
		if (unit.equals("M")) {
			gregorianCalendar.add(Calendar.MONTH, interval);
		}
		if (unit.equals("D")) {
			gregorianCalendar.add(Calendar.DATE, interval);
		}
		returnDate = gregorianCalendar.getTime();

		gregorianCalendar = null;

		return returnDate;
	}

	/**
	 * 功能:得到某时间单位,在某个时间段内往后或往前推某个时间段的日期 日期格式：yyyy--MM--dd unit:Y,M,D
	 * 
	 * @param baseDate
	 *            起始日期
	 * @param interval
	 *            时间间隔
	 * @param unit
	 *            时间间隔单位，可以为 Y（年）、M（月）、D（天）
	 * @return String
	 * @throws ParseException
	 */
	public static String calDate(String baseDate, int interval, String unit)
			throws ParseException {
		// 获得一个日历的实例
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(baseDate);
		calendar.setTime(date);// 设置日历时间

		// 在日历的日期按照给定的日期单位增加一个间隔;
		if (unit.equals("Y"))
			calendar.add(Calendar.YEAR, interval);
		else if (unit.equals("M"))
			calendar.add(Calendar.MONTH, interval);
		else if (unit.equals("D"))
			calendar.add(Calendar.DATE, interval);
		else
			calendar.add(Calendar.DATE, interval);// 如果传错单位,默认日期单位是天

		return sdf.format(calendar.getTime());
	}

	/**
	 * 比较两个日期的大小, startDate>endDate,return fasle else return true
	 * 
	 * @param if
	 * 
	 * @param compareDate
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean isDateBefore(String startDateString,
			String endDateString) throws ParseException {
		Date stateDate = getDate(startDateString);
		Date endDate = getDate(endDateString);

		if (stateDate.after(endDate)) {
			log.info("开始日期晚于结束日期！！！！");
			return false;
		} else {
			log.info("开始日期早于结束日期！！！");
			return true;
		}
	}

	/**
	 * 通过起始日期和终止日期计算以时间间隔单位为计量标准的时间间隔
	 * 
	 * @param startDate
	 *            起始日期，Date变量
	 * @param endDate
	 *            终止日期，Date变量
	 * @param unit
	 *            时间间隔单位，可用值("Y"--年 "M"--月 "D"--日)
	 * @return int 时间间隔
	 */
	public static int calInterval(Date startDate, Date endDate, String unit) {
		int interval = 0;

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(startDate);
		int years = gregorianCalendar.get(Calendar.YEAR);
		int months = gregorianCalendar.get(Calendar.MONTH);
		int days = gregorianCalendar.get(Calendar.DAY_OF_MONTH);

		GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
		gregorianCalendar2.setTime(endDate);
		int years2 = gregorianCalendar2.get(Calendar.YEAR);
		int months2 = gregorianCalendar2.get(Calendar.MONTH);
		int days2 = gregorianCalendar2.get(Calendar.DAY_OF_MONTH);

		if (unit.equals("Y")) {
			interval = years2 - years;
			if (months2 < months) {
				interval--;
			} else {
				if (months2 == months && days2 < days) {
					interval--;
					if (months2 == 1) { // 如果同是2月，校验润年问题
						if ((years % 4) == 0 && (years2 % 4) != 0) { // 如果起始年是润年，终止年不是润年
							if (days2 == 28) { // 如果终止年不是润年，且2月的最后一天28日，那么补一
								interval++;
							}
						}
					}
				}
			}
		}
		if (unit.equals("M")) {
			interval = years2 - years;
			interval *= 12;

			interval += months2 - months;
			if (days2 < days) {
				interval--;
				// eDays如果是月末，则认为是满一个月
				int maxDate = gregorianCalendar2
						.getActualMaximum(Calendar.DATE);
				if (days2 == maxDate) {
					interval++;
				}
			}
		}
		if (unit.equals("D")) {
			gregorianCalendar.set(years, months, days);
			gregorianCalendar2.set(years2, months2, days2);
			long lInterval = (gregorianCalendar2.getTime().getTime() - gregorianCalendar
					.getTime().getTime()) / 86400000;
			interval = (int) lInterval;
		}
		return interval;
	}

	/**
	 * 通过起始日期和终止日期计算以时间间隔单位为计量标准的时间间隔
	 * 
	 * @param startDate
	 *            起始日期，Date变量
	 * @param endDate
	 *            终止日期，Date变量
	 * @param unit
	 *            时间间隔单位，可用值("Y"--年 "M"--月 "D"--日)
	 * @return int 时间间隔
	 */
	public static int calInterval(String startDate, String endDate, String unit) {
		int interval = 0;
		try {
			interval = calInterval(getDate(startDate), getDate(endDate), unit);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return interval;
	}
	
	/**
	 * 输入符合格式要求的日期字符串，返回日期类型变量
	 * 
	 * @param dateString
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getDate(String dateString) throws ParseException {
		Date date = null;
		if (dateString != null && !"".equals(dateString)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			date = simpleDateFormat.parse(dateString);
			log.info("date:" + date.toString());
		}
		log.info("dateString:" + dateString);

		return date;
	}

	/**
	 * 输入符合格式要求的日期字符串，返回日期类型变量，例如20130405
	 * 
	 * @param dateString
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getDate1(String dateString) throws ParseException {
		Date date = null;
		if (dateString != null && !"".equals(dateString)
				&& dateString.length() == 8) {
			String year = dateString.substring(0, 4);
			String month = dateString.substring(4, 6);
			String day = dateString.substring(6, 8);
			dateString = year + "-" + month + "-" + day;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			date = simpleDateFormat.parse(dateString);
			log.info("date:" + date.toString());
		}
		log.info("dateString:" + dateString);
		return date;
	}

	/**
	 * 输入符合格式要求的日期字符串，返回日期类型变量
	 * 在设置格式化模板的时候，要注意一些占位符所表示的含义：
		y     年 
		M     年中的月份
		D     年中的天数
		d     月份中的天数
		H     一天中的小时数（0-23）
		h     am/pm 中的小时数（1-12）
		m     小时中的分钟数
		s     分钟中的秒数
		S     毫秒数
	 * @param dateString
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getDateTime(String dateString) throws ParseException {
		Date date = null;
		if (dateString != null && !"".equals(dateString)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			date = simpleDateFormat.parse(dateString);
			log.info("date:" + date.toString());
		}
		log.info("dateString:" + dateString);
		return date;
	}

	
	/**
	 * 输入日期类型变量，返回日期字符串
	 * 
	 * @param date
	 * @return String
	 */
	public static String getDateString(Date date) {
		String dateString = null;
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			dateString = simpleDateFormat.format(date);
			log.info("date:" + date.toString());
		}
		log.info("dateString:" + dateString);
		return dateString;
	}

	/**
	 * 输入日期类型变量，返回日期字符串
	 * 
	 * @param date
	 * @return String
	 */
	public static String getDateTimeString(Date date) {
		String dateString = null;
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			dateString = simpleDateFormat.format(date);
			log.info("date:" + date.toString());
		}
		log.info("dateString:" + dateString);

		return dateString;
	}

	/**
	 * 输入日期类型变量，返回日期字符串
	 * 
	 * @param date
	 * @return String
	 */
	public static String getDateChnString(String dateString) {
		Date date = null;
		String dateChnString = "";
		if (dateString != null && !"".equals(dateString)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			try {
				date = simpleDateFormat.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy年MM月dd日");
			dateChnString = simpleDateFormat.format(date);
			log.info("date:" + date.toString());
		}
		log.info("dateString:" + dateString);
		return dateChnString;
	}

	/**
	 * 输入日期类型变量，返回日期int
	 * 
	 * @param date
	 * @return String
	 */
	public static Integer getDateInteger(Date date) {
		String dateString = getDateString(date);
		Integer dateNum = Integer.parseInt(dateString.replace("-", ""));
		return dateNum;
	}

	/**将日期区间段按月份拆分，返回List集合，map中包含本月月份、本月起始日期、本月结束日期
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> getMonthBetween(String startDate, String endDate){
		List<Map> monthList = new ArrayList<Map>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
	    Calendar startDateCalendar = Calendar.getInstance();
	    Calendar endDateCalendar = Calendar.getInstance();
	 
	    try {
			startDateCalendar.setTime(dateFormat.parse(startDate));
			startDateCalendar.set(startDateCalendar.get(Calendar.YEAR), startDateCalendar.get(Calendar.MONTH), 1);
			 
		    endDateCalendar.setTime(dateFormat.parse(endDate));
		    endDateCalendar.set(endDateCalendar.get(Calendar.YEAR), endDateCalendar.get(Calendar.MONTH), 2);
		 
		    Calendar currentCalendar = startDateCalendar;
		    int index = 0;
		    while (currentCalendar.before(endDateCalendar)) {
		    	Map monthMap = new HashMap();
		    	//当前年份
		    	int currentYear = currentCalendar.get(Calendar.YEAR);
		    	//当前月份
		    	int currentMonth = currentCalendar.get(Calendar.MONTH)+1;
		    	String currentMonthStr = currentMonth-10<0?"0"+currentMonth:currentMonth+"";
		    	//当月起始日期
		    	String currentMonthStartDate = currentYear + "-" + currentMonthStr + "-01";
		    	//当月结束日期
		    	String currentMonthEndDate = getLastDayOfMonth(currentYear,currentMonth);
		    	monthMap.put("currentMonth", sdf.format(currentCalendar.getTime()));
		    	monthMap.put("startDate", currentMonthStartDate);
		    	monthMap.put("endDate", currentMonthEndDate);
		    	monthList.add(index,monthMap);
		    	index++;
		    	currentCalendar.add(Calendar.MONTH, 1);
		    }
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
		return monthList;
	}
	
	/** 
     * 获取某年某月的最后一天 
     *  
     * @param year 
     *            年 
     * @param month 
     *            月 
     * @return 最后一天 
     */  
    public static String getLastDayOfMonth(int year, int month) {  
    	int lastDay = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8  
                || month == 10 || month == 12) {  
        	lastDay = 31;  
        }  
        if (month == 4 || month == 6 || month == 9 || month == 11) {  
        	lastDay = 30;  
        }  
        if (month == 2) {  
            if (isLeapYear(year)) {  
            	lastDay = 29;  
            } else {  
            	lastDay = 28;  
            }  
        }  
        String monthStr = month-10<0?"0"+month:month+"";
        String lastDate = year + "-" + monthStr + "-" + lastDay;
        return lastDate;  
    } 

    /**是否闰年
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {  
        return new GregorianCalendar().isLeapYear(year);  
    }  

	
	/**
	 * /**
	 * 
	 * @param args
	 */
	/*public static void main(String[] args) {
		String dateStr = DateUtils.getCurrentDate();
		System.out.println(dateStr);
		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(5, 7);
		String day = dateStr.substring(8, 10);
		System.out.println(year+"====="+month+"====="+day);
		
		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		
		System.out.println(now);
		//System.out.println(getDateChnString("2014-09-09"));
		//System.out.println("yuan zhengjun".toUpperCase());
		
		//List<Map> result = getMonthBetween("2015-03-06", "2015-05-06");
	}*/
	
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "null";
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 将DATA默认日期格式java Tue Mar 03 00:00:00 CST 2015  转成yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public String DateToString(Date date){
		System.out.println(date);		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		String sDate=sdf.format(date);		
		return sDate;
		
	}
	
}