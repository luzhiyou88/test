package com.education.classroom.utils;

import com.education.classroom.utils.MD5Util;

/**
 * 密码工具类
 * @Class Name PasswordUtil
 * @author zhangyongsheng
 * @Create In 2016年8月11日
 */
public class PasswordUtil {

	/**
	 * md5加密
	 * 2016年8月11日
	 * By zhangyongsheng
	 * @param password
	 * @return
	 */
	public static String encodePassword(String password){
		return MD5Util.MD5Encode(password, "UTF-8");
	}
}
