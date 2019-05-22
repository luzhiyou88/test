package com.education.classroom.core.sync.data.type;


/**
 * 同步分组关键字的类型
 * 用途:同步中间表数据到汇金汇诚、财富系统使用的t_gl_sync_data_batch表的groupKeyDesc
 * @Class Name SyncGroupByType
 * @author 张永生
 * @Create In 2015年12月5日
 */
public interface SyncGroupKeyType {

	/**
	 * 用于同步t_gl_sync_user和t_gl_sync_user_org表的分组关键字的存储格式
	 */
	public static final String USERID_MODULENAME = "userId_moduleName";
	
	/**
	 * 用于同步t_gl_sync_org表的分组关键字的存储格式
	 */
	public static final String ORGID_MODULENAME = "orgId_moduleName";
	
	/**
	 * 用于同步t_gl_sync_role表的分组关键字的存储格式
	 */
	public static final String ROLEID_MODULENAME = "roleId_moduleName";
	/**
	 * 用于同步t_gl_sync_dict表的分组关键字的存储格式
	 */
	public static final String DICTID_MODULENAME = "dictId_moduleName";
	
}
