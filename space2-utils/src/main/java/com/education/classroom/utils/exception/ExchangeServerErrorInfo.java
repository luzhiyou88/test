package com.education.classroom.utils.exception;

public class ExchangeServerErrorInfo extends ErrorInfo{
	//查询多个服务器端交换任务数据为空时的异常代码和描述信息
	public static final String ERROR_QUERY_RESULT_NULL_CODE = "COMMON_ERR_SEREXCH_0001";
	public static final String ERROR_QUERY_RESULT_NULL = "查询多个服务器端交换任务数据为空";
	
	//查询单个服务器端交换任务数据为空时的异常代码和描述信息
	public static final String ERROR_QUERY_ITEM_NULL_CODE = "COMMON_ERR_SEREXCH_0002";
	public static final String ERROR_QUERY_ITEM_NULL = "查询单个服务器端交换任务数据为空";
	
	//服务端交换任务信息不完整时的异常代码和描述信息
	public static final String ERROR_INCOMPLETE_INFORMATION_CODE = "COMMON_ERR_SEREXCH_0003";
	public static final String ERROR_INCOMPLETE_INFORMATION = "服务端交换任务信息不完整";
	
}
