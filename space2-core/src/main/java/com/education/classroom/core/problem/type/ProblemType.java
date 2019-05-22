package com.education.classroom.core.problem.type;

/**
 * 试题类型
 * @Class Name ProblemType
 * @author zhangyongsheng
 * @Create In 2016年8月18日
 */
public interface ProblemType {

	/**
	 * 单选题
	 */
	public static final String TYPE_SINGLE_CHOICE = "1";
	
	/**
	 * 多选题
	 */
	public static final String TYPE_MULTIPLE_CHOICE = "2";
	
	/**
	 * 不定项选择题
	 */
	public static final String TYPE_NON_DIRECTIONAL_CHOICE = "3";
	
	/**
	 * 主观题即简答题
	 */
	public static final String TYPE_SHORT_QUESTION = "4";
	
}
