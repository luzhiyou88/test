package com.education.classroom.core.type;


/**
 * 管理员用户标志常量
 * @Class Name AdminUserFlag
 * @author 张永生
 * @Create In 2016年6月17日
 */
public interface AdminUserFlag {

	/**
	 * 财富人管室管理员标志
	 * 用户在财富人管室拥有人管室管理员角色
	 */
	public static final String FORTUNE_HUMAN_OFFICE_ADMIN = "2";
	
	/**
	 * 汇金人管室管理员标志
	 * 用户在汇金人管室拥有人管室管理员角色
	 */
	public static final String LOAN_HUMAN_OFFICE_ADMIN = "3";
	
	/**
	 * 汇诚人管室管理员标志
	 * 用户在汇诚人管室拥有人管室管理员角色
	 */
	public static final String APPROVE_HUMAN_OFFICE_ADMIN = "4";
	
	/**
	 * 借款人服务部管理员标志
	 * 用户在借款人服务部拥有部门管理员角色
	 */
	public static final String LOANER_DEPT_ADMIN = "5";
	
	/**
	 * 出借人服务部管理员标志
	 * 用户在出借人服务部拥有部门管理员角色
	 */
	public static final String LENDER_DEPT_ADMIN = "6";
	
	/**
	 * 惠民事业部管理员标志
	 * 用户在惠民事业部拥有管理员角色
	 */
	public static final String HUIMIN_BIZ_DEPT_ADMIN = "7";
	
}
