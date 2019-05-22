package com.education.classroom.core.common.type;

/**
 * 操作常量
 * 用于页面向后台传递的操作标识
 * @Class Name OperationType
 * @author 张永生
 * @Create In 2015年11月5日
 */
public interface OperationType {

	/**
	 * 新增
	 */
	public static final Integer OPERATE_ADD = 1;
	/**
	 * 修改
	 */
	public static final Integer OPERATE_EDIT = 2;
	/**
	 * 删除
	 */
	public static final Integer OPERATE_DELETE = 3;
	
}
