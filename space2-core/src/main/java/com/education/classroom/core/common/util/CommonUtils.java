package com.education.classroom.core.common.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.education.classroom.core.persistence.Page;


/**
 * 通用工具类
 * @Class Name CommonUtils
 * @author 张永生
 * @Create In 2015年11月24日
 */
public class CommonUtils {


	public static Date newDate() {
		return new Date();
	}

	@SuppressWarnings("rawtypes")
	private static Map<String, Object> getMap(Object entityo, Class entityc,
			Map<String, Object> map) {
		for (Field field : entityc.getDeclaredFields()) {
			if (!field.getName().equalsIgnoreCase("serialVersionUID")) {
				field.setAccessible(true);
				String pname = field.getName();
				Object value = null;
				try {
					value = field.get(entityo);
					if (null != value){
					   map.put(pname, value);
					}
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, Object> objectToMap(Map<String, Object> map,
			Object entity) {
		Class classnow = entity.getClass();
		while (classnow != Object.class) {
			getMap(entity, classnow, map);
			classnow = classnow.getSuperclass();
		}
		return map;
	}

	public static Long convertDecimalToLong(Object object) {
		if (object != null) {
			BigDecimal decimal = (BigDecimal) object;
			return Long.valueOf(decimal.longValue());
		}
		return null;
	}

	public static Integer convertDecimalToInteger(Object object) {
		if (object != null) {
			BigDecimal decimal = (BigDecimal) object;
			return Integer.valueOf(decimal.intValue());
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static void pageConversion(Page sourcePage, Page targetPage) {
		targetPage.setPageSize(sourcePage.getPageSize());
		targetPage.setPageNo(sourcePage.getPageNo());
		targetPage.setCount(sourcePage.getCount());
	}

	public static Double convertDecimalToDouble(Object object) {
		if (object != null) {
			BigDecimal decimal = (BigDecimal) object;
			return Double.valueOf(decimal.doubleValue());
		}
		return null;
	}

	public static Date convertDateFormat(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dates = sdf.format(date);
		return sdf.parse(dates);
	}

	public static Date convertStringToDate(String pattern, String dateString) {
		Date newDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			newDate = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public static Date formatDate(String pattern, Date date) {
		Date newDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			newDate = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public static String getDate(Date trialTime) {
		Calendar calendar = new GregorianCalendar();
		if (trialTime == null) {
			trialTime = new Date(System.currentTimeMillis());
		}
		String ret = "";
		calendar.setTime(trialTime);
		int year = calendar.get(1);
		int month = calendar.get(2) + 1;
		int day_of_month = calendar.get(5);
		ret = year + "-";
		if (month < 10) {
			ret = ret + "0" + month + "-";
		} else {
			ret = ret + month + "-";
		}
		if (day_of_month < 10) {
			ret = ret + "0" + day_of_month;
		} else {
			ret = ret + day_of_month;
		}
		return ret;
	}

	public static String getFullDate(Date trialTime) {
		String ret = "";
		if (trialTime == null) {
			trialTime = new Date(System.currentTimeMillis());
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(trialTime);

		int year = calendar.get(1);
		int month = calendar.get(2) + 1;
		int day_of_month = calendar.get(5);
		int hour = calendar.get(11);
		int minute = calendar.get(12);
		int second = calendar.get(13);
		ret = year + "-";
		if (month < 10) {
			ret = ret + "0" + month + "-";
		} else {
			ret = ret + month + "-";
		}
		if (day_of_month < 10) {
			ret = ret + "0" + day_of_month;
		} else {
			ret = ret + day_of_month;
		}
		ret = ret + " ";
		if (hour < 10) {
			ret = ret + "0" + hour + "-";
		} else {
			ret = ret + hour + ":";
		}
		if (minute < 10) {
			ret = ret + "0" + minute + ":";
		} else {
			ret = ret + minute + ":";
		}
		if (second < 10) {
			ret = ret + "0" + second;
		} else {
			ret = ret + second;
		}
		return ret;
	}

	public static String getSimpleDate(Date trialTime) {
		String ret = "";
		if (trialTime == null) {
			trialTime = new Date(System.currentTimeMillis());
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(trialTime);
		int year = calendar.get(1);
		int month = calendar.get(2) + 1;
		int day_of_month = calendar.get(5);
		int hour = calendar.get(11);
		int minute = calendar.get(12);
		int second = calendar.get(13);
		ret = year + "";
		if (month < 10) {
			ret = ret + "0" + month + "";
		} else {
			ret = ret + month + "";
		}
		if (day_of_month < 10) {
			ret = ret + "0" + day_of_month;
		} else {
			ret = ret + day_of_month;
		}
		ret = ret + "";
		if (hour < 10) {
			ret = ret + "0" + hour + "";
		} else {
			ret = ret + hour + "";
		}
		if (minute < 10) {
			ret = ret + "0" + minute + "";
		} else {
			ret = ret + minute + "";
		}
		if (second < 10) {
			ret = ret + "0" + second;
		} else {
			ret = ret + second;
		}
		return ret;
	}

	public static boolean validNumberRange(Long num, Long startNum, Long endNum) {
		try {
			return (num.longValue() >= startNum.longValue())
					&& (num.longValue() <= endNum.longValue());
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean validNumberRange(Double num, Double startNum,
			Double endNum) {
		try {
			return (num.doubleValue() >= startNum.doubleValue())
					&& (num.doubleValue() <= endNum.doubleValue());
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean validNumberRange(Integer num, Integer startNum,
			Integer endNum) {
		try {
			return (num.intValue() >= startNum.intValue())
					&& (num.intValue() <= endNum.intValue());
		} catch (Exception e) {
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, Object> copyQueryMap(Map<?, ?> filter,
			Map<String, Object> queryMap) {
		for (Map.Entry entry : filter.entrySet()) {
			String[] value = (String[]) entry.getValue();
			if ((value != null) && (!"".equals(value[0]))) {
				queryMap.put(entry.getKey().toString(), value[0].trim());
			}
		}
		return queryMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> convertMapKeyToString(Map<?, ?> origMap) {
		Map destMap = new LinkedHashMap();
		for (Iterator i$ = origMap.keySet().iterator(); i$.hasNext();) {
			Object key = i$.next();
			destMap.put(String.valueOf(key), origMap.get(key));
		}
		return destMap;
	}
}
