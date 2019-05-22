package com.education.classroom.core.sync.data.type;

/**
 * 同步序列号类型
 * 使用场景：获取runningNumber时的参数从这个常量类中取值
 * @Class Name SyncRunningType
 * @author 张永生
 * @Create In 2015年12月5日
 */
public interface SyncRunningType {

	/**
	 * 同步用户组织结构中间表
	 */
	public static final String SYNC_USER_ORG = "sync_user_org";
	
	/**
	 * 同步用户表
	 */
	public static final String SYNC_USER = "sync_user";
}
