package com.education.classroom.core.answer.type;

/**
 * 答题结果类型
 * @Class Name AnswerType
 * @author zhangyongsheng
 * @Create In 2016年8月18日
 */
public interface AnswerType {

	/**
	 * 正确
	 */
	public static final String TYPE_RIGHT = "1";
	
	/**
	 * 错误
	 */
	public static final String TYPE_WRONG = "2";
	
	/**
	 * 未答
	 */
	public static final String TYPE_UNANSWERED = "3";
	
}
