package com.education.classroom.utils.exception;

/* 
 * 本类用于统一管理公共模块的错误信息！
 */

public class CommonExceptionInfo extends ErrorInfo{
	
/* 
 * 公用的错误信息，此类错误都用统一的错误代码 COMMON_ERR_000来标识，
 * 包括业务错误和系统错误，一般系统错误居多
 */
	
    public static final String COMMON_ERR_MSG_SITENAME_INITIAL_ERROR = "初始化错误，没有设置站点代码";
    
    public static final String COMMON_ERR_MSG_INVALID_PARAMETER = "传入参数错误！";
    
    public static final String COMMON_ERR_INVALID_DN_SYNTEX = "传入DN参数格式错误！";

}
