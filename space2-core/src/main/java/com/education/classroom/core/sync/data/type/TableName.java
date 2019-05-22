package com.education.classroom.core.sync.data.type;

import java.util.Map;
import java.util.TreeMap;

/**
 * 需要同步的表名
 * 用于定义系统管理中需要同步到各业务模块子系统的数据库的表的名字
 * 例如：sys_user, sys_role
 * @Class Name NeedSyncTableName
 * @author 张永生
 * @Create In 2015年12月1日
 */
public enum TableName {
	
	SYS_AREA("SYS_AREA"),				 			 //t_gl_area表
	SYS_CITY("SYS_CITY"),                			 //t_glcity表
	SYS_DICT("SYS_DICT"),                			 //t_gldict表
	SYS_MENU("SYS_MENU"),                			 //t_gl_menu表
	SYS_MODULE("SYS_MODULE"),            			 //t_gl_module表
	SYS_MODULE_MENU("SYS_MODULE_MENU"),         	 //t_gl_module_menu表
	SYS_ORG("SYS_ORG"),                				 //t_gl_org表
	SYS_PAGE_AUTH("SYS_PAGE_AUTH"),             	 //t_gl_page_auth表
	SYS_ROLE("SYS_ROLE"),                			 //t_gl_role表
	SYS_ROLE_MODULE("SYS_ROLE_MODULE"),         	 //t_gl_role_module表
	SYS_ROLE_MODULE_MENU("SYS_ROLE_MODULE_MENU"),    //t_gl_role_module_menu表
	SYS_USER("SYS_USER"),                		 	 //t_gl_user表
	SYS_USER_ORG("SYS_USER_ORG"),                    //t_gl_user_org表
	SYS_USER_ROLE("SYS_USER_ROLE");                  //t_gl_user_role表
	
	public final String value;

	private TableName(String value) {
		this.value = value;
	}

	public static String getName(String value) {
		return NeedSyncTableNameMap.get(value);
	}
	
	public static Map<String, String> NeedSyncTableNameMap = new TreeMap<String, String>();

	public static void initTypeMap(){
		NeedSyncTableNameMap.put(TableName.SYS_AREA.value, "sys_area表");
		NeedSyncTableNameMap.put(TableName.SYS_CITY.value, "sys_city表");
		NeedSyncTableNameMap.put(TableName.SYS_DICT.value, "sys_dict表");
		NeedSyncTableNameMap.put(TableName.SYS_MENU.value, "sys_menu表");
		NeedSyncTableNameMap.put(TableName.SYS_MODULE.value, "sys_module表");
		NeedSyncTableNameMap.put(TableName.SYS_MODULE_MENU.value, "sys_module_menu表");
		NeedSyncTableNameMap.put(TableName.SYS_PAGE_AUTH.value, "sys_page_auth表");
		NeedSyncTableNameMap.put(TableName.SYS_ROLE.value, "sys_role表");
		NeedSyncTableNameMap.put(TableName.SYS_ROLE_MODULE.value, "sys_role_module表");
		NeedSyncTableNameMap.put(TableName.SYS_ROLE_MODULE_MENU.value, "sys_role_module_menu表");
		NeedSyncTableNameMap.put(TableName.SYS_USER.value, "sys_user表");
		NeedSyncTableNameMap.put(TableName.SYS_USER_ORG.value, "sys_user_org表");
		NeedSyncTableNameMap.put(TableName.SYS_USER_ROLE.value, "sys_user_role表");
	}
	
}
