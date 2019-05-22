package com.education.classroom.utils.exception;

public class ExchangeClientErrorInfo extends ErrorInfo{
	//查询多个客户端交换任务数据为空时的异常代码和描述信息
	public static final String ERROR_QUERY_RESULT_NULL_CODE = "COMMON_ERR_CLIEXCH_0001";
	public static final String ERROR_QUERY_RESULT_NULL = "查询多个客户端交换任务数据为空";
	
	//查询单个客户端交换任务数据为空时的异常代码和描述信息
	public static final String ERROR_QUERY_ITEM_NULL_CODE = "COMMON_ERR_CLIEXCH_0002";
	public static final String ERROR_QUERY_ITEM_NULL = "查询单个客户端交换任务数据为空";
	
	//检测到提交的任务中存在正处于尚未完成状态的文档时的异常代码和描述信息
	public static final String ERROR_NOT_YET_COMPLETED_CODE = "COMMON_ERR_CLIEXCH_0003";
	public static final String ERROR_NOT_YET_COMPLETED = "检测到相同的文档正在交换处理中,忽略此条记录！";
	
}
