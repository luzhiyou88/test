package com.education.classroom.core.custom.tag;

import java.util.List;

/**
 * 自定义标签
 * @Class Name CustomTag
 * @author 张永生
 * @Create In 2015年12月15日
 */
public class CustomTag {

	/**
	 * 判断对象是否存在于list中
	 * 2015年12月15日
	 * By 张永生
	 * @param list
	 * @param o
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean contains(List list, Object o) {
		return list.contains(o);
	}
}
