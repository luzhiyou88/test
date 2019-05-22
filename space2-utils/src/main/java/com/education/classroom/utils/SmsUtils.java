package com.education.classroom.utils;

import java.util.List;

/**
 * 短信工具类
 * 
 * @Class Name SmsUtils
 * @author 周亮
 * @Create In 2016年1月11日
 */
public class SmsUtils {
	
	/**
	 * 拼装短信
	 * 
	 * @param tplCd
	 * @param lsParam
	 * @return String
	 */
	public static String getSmsMsg(String tpl, List<String> lsParam) {
		String msg = "";
		if (ListUtils.isEmptyList(lsParam)) {
			return msg;
		} 
		
		for (int i = 0; i < lsParam.size(); i++) {
			tpl = tpl.replaceFirst("#info#", lsParam.get(i));
		}
		msg = tpl;
		
		return msg;
	}
}
