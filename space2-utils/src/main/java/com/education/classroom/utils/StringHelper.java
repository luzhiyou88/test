package com.education.classroom.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @类功能描述：字符串工具类
 * @创建者：张旭
 * @创建时间：2012-2-7 下午5:20:36
 */
public class StringHelper extends StringUtils{
	
	/**
	 * 判断字符串是否为严格意义上的非空
	 * @param cs(待判断的字符串)
	 * @return 是否非空
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-7 下午5:20:50
	 * @modificator：张旭
	 * @date_modified：2012-2-7 下午5:20:50
	 * @Description：
	 */
	public static boolean isNotEmpty(CharSequence cs){
		return StringUtils.isNotEmpty(cs) && !cs.equals("null") && !cs.equals("NULL");
	}
	
	/**
	 * 判断对象是否非空
	 * @param o(对象)
	 * @return 是否非空
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-7 下午5:44:10
	 * @modificator：张旭
	 * @date_modified：2012-2-7 下午5:44:10
	 * @Description：
	 */
	public static boolean isNotNull(Object o){
		return o != null;
	}
	
	public static boolean isChinese(String str){
		String regEx = "[\\u4e00-\\u9fa5]+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if(m.find()) {
			return true;
		}else{
			return false;
		}
	}

	public static boolean hasEnglish(String str){
		String regEx = "[a-zA-Z]+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}
}
