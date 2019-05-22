package com.education.classroom.core.common.util;

import com.education.classroom.core.common.type.SexType;


/**
 * 性别工具类
 * @Class Name SexUtil
 * @author 张永生
 * @Create In 2016年5月26日
 */
public class SexUtil {

	/**
	 * 根据性别的类型获取性别的中文名称
	 * 2016年5月26日
	 * By 张永生
	 * @return
	 */
	public static String getSexName(String sexType){
		String sexName = SexType.MALE_NAME;
		if(SexType.MALE.equals(sexType)){
			sexName =  SexType.MALE_NAME;
		}else if(SexType.FEMALE.equals(sexType)){
			sexName =  SexType.FEMALE_NAME;
		}
		return sexName;
	}
}
