package com.education.classroom.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @Class Name DateUtils
 * @author 李静辉
 * @Create In 2016年1月11日
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyyMMddHHmmssSSS",
		"yyyy-MM-dd", 
		"yyyy-MM-dd HH:mm:ss", 
		"yyyy-MM-dd HH:mm", 
		"yyyy-MM", 
		"yyyy-MM-dd HH:mm:ss.SSS",
		"yyyy/MM/dd", 
		"yyyy/MM/dd HH:mm:ss", 
		"yyyy/MM/dd HH:mm", 
		"yyyy/MM",
		"yyyy.MM.dd", 
		"yyyy.MM.dd HH:mm:ss", 
		"yyyy.MM.dd HH:mm", 
		"yyyy.MM",
		"yyyyMMdd",
		"yyyy",
		"MM",
		"dd",
		"yyyy年MM月dd日", 
		"yyyy年MM月"
	};

	private static String monthPattern = "yyyy/MM";
	
	private static String datePattern = "yyyy-MM-dd";
	private static String timePattern = datePattern + " HH:mm:ss";
	
	 
	/**
	 * 得到当前日期字符串 格式（yyyy/MM/dd）
	 */
	public static String getDate() {
		return getDate("yyyy/MM/dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy/MM/dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		if(date==null){
			return "";
		}
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateUtils.formatDate(date, pattern[0].toString());
		} else {
			formatDate = DateUtils.formatDate(date);
		}
		return formatDate;
	}
	
	/**
	 * 按照pattern格式化日期
	 */
	public static String formatDate(Date date,String pattern) {
		return DateFormatUtils.format(date, pattern);
	}
	/**
	 * 按照yyyy-MM-dd格式化日期
	 */
	public static String formatDate(Date date) {
		if(date==null){
			return "";
		}
		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}
	
	public static String getFormateDate(Date date) {
		String formatDate = null;
		if (date != null) {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy/MM/dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}
	
	/**
	 * 得到指定日期的年字符串 格式（dd）
	 * @param date
	 * @return String
	 */
	public static String getYear(Date date) {
		return formatDate(date, "yyyy");
	}

	/**
	 * 得到指定日期的月字符串 格式（dd）
	 * @param date
	 * @return String
	 */
	public static String getMonth(Date date) {
		return formatDate(date, "MM");
	}

	/**
	 * 得到指定日期的日字符串 格式（dd）
	 * @param date
	 * @return String
	 */
	public static String getDay(Date date) {
		return formatDate(date, "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 获取格式化实例
	 * 2015年12月16日
	 * By 张永生
	 * @param pattern
	 * @return
	 */
	public static SimpleDateFormat getFormatInstance(String pattern) {
		if ((pattern == null) || (pattern.length() == 0)) {
			pattern = "yyyy/MM/dd";
		}
		return new SimpleDateFormat(pattern);
	}
	  
	/**
	 * 获取yyyy/MM格式的字符串格式的日期
	 * 2015年12月16日
	 * By 张永生
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static String toMonthDate(Date date) {
	    SimpleDateFormat format = getFormatInstance(DateUtils.monthPattern);
        String returnValue = "";
        if (date != null) {
            returnValue = format.format(date);
        }
		return returnValue;
	}
	
	public static boolean between(Date date, Date dateStart, Date dateEnd) {
		if (date != null && dateStart != null && dateEnd != null) {
			return (dateEqualOrAfter(date, dateStart) && dateEqualOrBefore(
					date, dateEnd));
		}
		return false;
	}

	public static boolean dateEqualOrAfter(Date dateInQuestion, Date date2) {
		if (dateInQuestion.equals(date2)){
			return true;
		}
		return (dateInQuestion.after(date2));

	}

	public static boolean dateEqualOrBefore(Date dateInQuestion, Date date2) {
		if (dateInQuestion.equals(date2)){
			return true;
		}
		return (dateInQuestion.before(date2));
	}

	/**
	 * 比较一个日期是否在另一个日期之前
	 * 2015年12月16日
	 * By 张永生
	 * @param sourceDate
	 * @param targetDate
	 * @return
	 */
	public static boolean dateBefore(Date sourceDate, Date targetDate) {
		return (sourceDate.before(targetDate));
	}
	
	/**
	 * 比较一个日期是否在另一个日期之后
	 * 2015年12月16日
	 * By 张永生
	 * @param sourceDate
	 * @param targetDate
	 * @return
	 */
	public static boolean dateAfter(Date sourceDate, Date targetDate) {
		return (sourceDate.after(targetDate));
	}
	
	/**
	 * 获取String类型的日期。
	 * @param date 时间
	 * @param patten date的格式
	 * @return
	 */
	public static String date2Str(Date date, String patten) {
		DateFormat df = new SimpleDateFormat(patten);
		return df.format(date);
	}
	
	/**
	 * 根据日期，返回当天的星期
	 *
	 * @param date
	 * @param weekdays
	 * @param patten
	 * @return
	 */
	public static String getWeekday(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[intWeek];
	}
	
	/**
	 * 根据日期，返回当天的周
	 *
	 * @param date
	 * @param weekdays
	 * @param patten
	 * @return
	 */
	public static String getChineseWeekday(Date date) {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[intWeek];
	}
	
	/**
	 * 根据日期，返回下一天
	 *
	 * @param day
	 * @return Date
	 */
	public static Date getNextday(Date day) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}
	
	/**
	 * 获取当前系统时间
	 * @param patten  yyyy/MM/dd 或 "yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static String getNowTime() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(c.getTime());
	}
	
	/**
	 * 取得指定月份最后一天
	 *
	 * @param day
	 * @return Date
	 */
	public static Date getLastDayOfMonth(Date day) { 
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, lastDay);
		return c.getTime();
	} 
	
	/**
	 * 转换字符串日期为date
	 * 2016年5月28日
	 * By 张永生
	 * @param aMask
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return (date);
	}
    
    /**
     * 按照timePattern格式转换字符串日期为date
     * 2016年5月28日
     * By 张永生
     * @param strDate
     * @return
     */
	public static Date convertStringToDate(String strDate) {
		Date targetDate = null;
		try {
			targetDate = convertStringToDate(timePattern, strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return targetDate;
	}
	
	/**
	 * 将字符串日期转换成date日期
	 * 格式为yyyy-MM-dd
	 * 2016年6月16日
	 * By 张永生
	 * @param strDate
	 * @return
	 */
	public static Date convertDate(String strDate) {
		Date targetDate = null;
		try {
			targetDate = convertStringToDate(datePattern, strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return targetDate;
	}
	
	/**
	 * 获取不带时分秒的当前日期
	 * 2016年8月11日
	 * By zhujie
	 * @return
	 */
	public static Date getCurrentDate(){
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY,0);
		now.set(Calendar.MINUTE,0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		return now.getTime();
	}
	
	/**
	 * 日期加减
	 * 2016年8月11日
	 * By zhujie
	 * @return
	 */
	public static Date addDays(Date date, int days){
	   Calendar calendar=Calendar.getInstance();   
	   calendar.setTime(date); 
	   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+days);
	   return calendar.getTime();
	}
}
