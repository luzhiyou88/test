package com.education.classroom.common.constants;

/**
 * 系统中常用的常量
 * @Class Name typeConstants
 * @author 路志友
 * @Create In 2016年7月4日
 */
public interface stateConstants {

	/**
	 * 删除
	 */
	public static final String ISDEL = "1";
	
	/**
	 * 未删除
	 */
	public static final String NODEL = "0";
	/**
	 * false
	 */
	public static final String FALSE = "false";
	/**
	 * true
	 */
	public static final String TRUE = "true";
	/**
	 * 允许登录
	 */
	public static final String ALLOW_LOGIN = "1";
	/**
	 * 不允许登陆
	 */
	public static final String NO_ALLOW_LOGIN = "0";
	/**
	 * 激活码状态未使用
	 */
	public static final String  NEW_ACTIV_CODE= "0";
	/**
	 * 激活码状态已使用
	 */
	public static final String  OLD_ACTIV_CODE= "1";
	/**
	 * 激活码状态已失效
	 */
	public static final String  LOSE_ACTIV_CODE= "2";
}
