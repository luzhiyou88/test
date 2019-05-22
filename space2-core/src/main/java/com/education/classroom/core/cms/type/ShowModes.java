package com.education.classroom.core.cms.type;

/**
 * 栏目的展现方式常量
 * @Class Name ShowModes
 * @author 张永生
 * @Create In 2016年5月11日
 */
public interface ShowModes {

	/**
	 * 0:有子栏目显示栏目列表，无子栏目显示内容列表
	 */
	public static final String SHOW_CATEGORY_LIST = "0";
	/**
	 * 1：首栏目内容列表
	 */
	public static final String FIRST_CATEGORY_LIST = "1";
	/**
	 * 2：栏目第一条内容
	 */
	public static final String FIRST_CONTENT = "2";
	
}
