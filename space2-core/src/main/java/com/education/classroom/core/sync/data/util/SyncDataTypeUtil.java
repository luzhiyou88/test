package com.education.classroom.core.sync.data.util;

import java.util.Hashtable;

import com.education.classroom.core.sync.data.type.SyncType;


/**
 * 同步数据的数据类型工具类
 * @Class Name DataTypeUtil
 * @author 张永生
 * @Create In 2015年12月3日
 */
public class SyncDataTypeUtil {
	
	public static Integer VALUE_ADD = 1;
	public static Integer VALUE_MODIFY = 2;
	public static Integer VALUE_DELETE = 3;

	private static Hashtable<String, Integer> types = new Hashtable<String, Integer>();
	
	/**
	 * 根据同步数据类型的key获取其对应的value
	 * 2015年12月3日
	 * By 张永生
	 * @param typeKey
	 * @return
	 */
	public static Integer getTypeValue(String typeKey){
		if (types.isEmpty()) {
			types.put(SyncType.TYPE_ADD.value, VALUE_ADD);
			types.put(SyncType.TYPE_MODIFY.value, VALUE_MODIFY);
			types.put(SyncType.TYPE_DELETE.value, VALUE_DELETE);
		}
		return types.get(typeKey);
	}
}
