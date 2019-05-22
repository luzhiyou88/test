package com.education.classroom.core.sync.data.type;

import java.util.Map;
import java.util.TreeMap;

/**
 * 需要同步的表名
 * 用于定义系统管理中需要同步到各业务模块子系统的数据库的表的名字
 * 例如：t_gl_sync_user
 * @Class Name NeedSyncTableName
 * @author 张永生
 * @Create In 2015年12月1日
 */
public enum NeedSyncTableName {
	
	SYNC_USER("SYNC_USER"),                            //t_gl_sync_user表
	SYNC_ORG("SYNC_ORG"),                              //t_gl_sync_org表
	@Deprecated
	SYNC_USER_ORG("SYNC_USER_ORG"),                    //t_gl_sync_user_org表
	SYNC_ROLE("SYNC_ROLE"),						       //t_gl_sync_role表
	SYNC_DICT("SYNC_DICT"),						       //t_gl_sync_dict表
	@Deprecated
	SYNC_USER_ROLE("SYNC_USER_ROLE"),    			   //t_gl_sync_user_role表
	SYNC_USER_ROLE_ORG("SYNC_USER_ROLE_ORG"),		   //t_gl_sync_user_role_org表
	THREENET_USER("THREENET_USER"),                    //t_gl_threenet_user表
	THREENET_ORG("THREENET_ORG"),                      //t_gl_threenet_org表
	THREENET_ROLE("THREENET_ROLE");                      //t_gl_threenet_role表
	
	public final String value;

	private NeedSyncTableName(String value) {
		this.value = value;
	}

	public static String getName(String value) {
		return tableNameMap.get(value);
	}
	
	public static Map<String, String> tableNameMap = new TreeMap<String, String>();

	public static void initTypeMap(){
		tableNameMap.put(NeedSyncTableName.SYNC_USER.value, "SYNC_USER表");
		tableNameMap.put(NeedSyncTableName.SYNC_ORG.value, "SYNC_ORG表");
		tableNameMap.put(NeedSyncTableName.SYNC_USER_ORG.value, "SYNC_USER_ORG表");
		tableNameMap.put(NeedSyncTableName.SYNC_ROLE.value, "SYNC_ROLE表");
		tableNameMap.put(NeedSyncTableName.SYNC_DICT.value, "SYNC_DICT表");
		tableNameMap.put(NeedSyncTableName.SYNC_USER_ROLE.value, "SYNC_USER_ROLE表");
		tableNameMap.put(NeedSyncTableName.SYNC_USER_ROLE_ORG.value, "SYNC_USER_ROLE_ORG表");
		tableNameMap.put(NeedSyncTableName.THREENET_USER.value, "T_GL_THREENET_USER表");
		tableNameMap.put(NeedSyncTableName.THREENET_ROLE.value, "T_GL_THREENET_ROLE表");
	}
	
}
