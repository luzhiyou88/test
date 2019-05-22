package com.education.classroom.core.sync.data.type;

import java.util.Hashtable;
import java.util.Map;

/**
 * 同步数据类型
 * 从系统管理模块同步sys_*表的数据到各个业务模块子系统时传输的数据类型
 * @Class Name SyncType
 * @author 张永生
 * @Create In 2015年12月1日
 */
public enum SyncType {
	
	TYPE_ADD("TYPE_ADD"), 				 	   //新增 
	TYPE_DELETE("TYPE_DELETE"),                //删除
	TYPE_MODIFY("TYPE_MODIFY"),                //修改
	TYPE_ISEXIST("TYPE_ISEXIST");               //是否存在
	
	public final String value;

	private SyncType(String value) {
		this.value = value;
	}

	public static String getTypeName(String value) {
		return typeNameMap.get(value);
	}
	

	public static Map<String, String> typeNameMap = new Hashtable<String, String>();

	public static Map<String, String> getTypeMap(){
		if(typeNameMap.isEmpty()){
			typeNameMap.put(SyncType.TYPE_ADD.value, "新增");
			typeNameMap.put(SyncType.TYPE_DELETE.value, "删除");
			typeNameMap.put(SyncType.TYPE_MODIFY.value, "修改");
			typeNameMap.put(SyncType.TYPE_ISEXIST.value, "是否存在");
		}
		return typeNameMap;
	}
	
}
