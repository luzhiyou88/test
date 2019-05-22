package com.education.classroom.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;


/**
 * 对象工具类
 * @Class Name ObjectHelper
 * @author 张永生
 * @Create In 2015年12月15日
 */
public class ObjectHelper {

	/**
	 * 判断集合是否为空
	 * 2015年12月15日
	 * By 张永生
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection){
		if( collection == null || collection.isEmpty() ){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断集合不空
	 * 2015年12月15日
	 * By 张永生
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection){
		if( collection != null && !collection.isEmpty() ){
			return true;
		}
		return false;
	}

	/**
	 * 判断map是否为空
	 * 2015年12月15日
	 * By 张永生
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map){
		if( map == null || map.isEmpty() ){
			return true;
		}
		return false;
	}

	/**
	 * 判断对象是否为空
	 * 2015年12月15日
	 * By 张永生
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object){
		if( object == null ){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断对象是否不空
	 * 2016年6月16日
	 * By 张永生
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Object object){
		if( object != null ){
			return true;
		}
		return false;
	}

	/**
	 * 判断数组是否为空
	 * 2015年12月15日
	 * By 张永生
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array){
		if( array == null || array.length == 0 ){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断数组不空
	 * 2016年5月10日
	 * By 张永生
	 * @param array
	 * @return
	 */
	public static boolean isNotEmpty(Object[] array){
		if( array != null && array.length > 0 ){
			return true;
		}
		return false;
	}


	/**
	 * 判断字符串是否为空
	 * 2015年12月15日
	 * By 张永生
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string){
		if( string == null || string.trim().length() == 0 ){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断对象中的所有属性值是否都为空
	 * 有一项不为空，则返回true;全部为空则返回false
	 * 2016年5月18日
	 * By 张永生
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static boolean checkAllFieldsNotNull(Object obj) throws Exception{
		boolean isNotNull = false;  //为空
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if(!"isNewRecord".equals(f.getName()) || !"delFlag".equals(f.getName())){
				if (f.get(obj) != null || !"".equals(f.get(obj))) {
					isNotNull = true;
					break;
				}
			}
		}
		return isNotNull;
	}
}
