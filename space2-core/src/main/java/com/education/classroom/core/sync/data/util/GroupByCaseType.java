package com.education.classroom.core.sync.data.util;


/**
 * 数据分组场景的类型
 * @Class Name GroupByCaseType
 * @author 张永生
 * @Create In 2015年12月7日
 */
public interface GroupByCaseType {

	/**
	 * 用户表分组场景
	 */
	public static final int CASE_USER = 1;
	/**
	 * 组织机构表分组场景
	 */
	public static final int CASE_ORG = 2;
	/**
	 * 用户与组织机构中间表的分组场景
	 */
	@Deprecated
	public static final int CASE_USER_ORG = 3;
	
	/**
	 * 角色表分组场景
	 */
	public static final int CASE_ROLE = 4;
	/**
	 * 数据字典表分组场景
	 */
	public static final int CASE_DICT = 5;
	
	/**
	 * 用户角色表分组场景
	 */
	@Deprecated
	public static final int CASE_USER_ROLE = 6;
	
	/**
	 * 用户角色组织表分组场景
	 */
	public static final int CASE_USER_ROLE_ORG = 7;
	
}
