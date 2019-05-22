package com.education.classroom.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * 数组帮助类
 * @Class Name ArrayHelper
 * @author 张永生
 * @Create In 2015年12月15日
 */
public class ArrayHelper {

	/**
	 * 泛型数组转为列表.
	 * @param <T>
	 * @param array
	 * @return array = null 或 长度为0返回，长度为0的列表
	 */
	public static <T> List<T> toList(T[] array) {
		List<T> rtn = new ArrayList<T>();
		if (array != null) {
			for (T t : array) {
				rtn.add(t);
			}
		}
		return rtn;
	}

	/**
	 * Array is Null.
	 * @param <T>
	 * @param array
	 * @return
	 */
	public static <T> boolean isNull(T[] array) {
		return !isNotNull(array);
	}

	/**
	 * Array is not Null.
	 * @param <T>
	 * @param array
	 * @return
	 */
	public static <T> boolean isNotNull(T[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * 判断list是否为不空
	 * 2015年12月15日
	 * By 张永生
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list){
		return list != null && list.size() > 0;
	}
	
	/**
	 * 判断list是否为空
	 * 2016年6月22日
	 * By 张永生
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list){
		return list == null || list.size() == 0;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Set set){
		return set != null && set.size() > 0;
	}
}
