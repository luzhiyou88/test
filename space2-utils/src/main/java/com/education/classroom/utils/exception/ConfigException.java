package com.education.classroom.utils.exception;

public class ConfigException extends ECMBusinessException {
    
	private static final long serialVersionUID = 1L;

	public static final String ERROR_PARAM_INVALID ="配置传参错误！";
	public static final String ERROR_CONFIG_INVALID ="配置信息条目不存在！";
	public static final String ERROR_CONFIG_INSERT ="配置信息无法插入！";
	public static final String ERROR_CONFIG_READ ="配置信息无法读取！";
			
	public static final String ERROR_CONFIG_ITEM_READ ="配置信息条目无法读取！";
	public static final String ERROR_CONFIG_ITEM_WRITE ="配置信息条目无法更新！";
	public static final String ERROR_CONFIG_USER_ID_EMPTY ="传入用户名为空！";
	
	public static final String ERROR_CONFIG_INVALID_MODULENAME ="错误的模块名称！";
	public static final String ERROR_CONFIG_RESULE_EMPTY ="待处理记录不存在！";
	
	public static final String ERROR_PARAM_INVALID_CODE="COMMON_ERR_CONFIG_0001";
	public static final String ERROR_CONFIG_INVALID_CODE="COMMON_ERR_CONFIG_0002";
	public static final String ERROR_CONFIG_INSERT_CODE="COMMON_ERR_CONFIG_0003";
	public static final String ERROR_CONFIG_READ_CODE ="COMMON_ERR_CONFIG_0004";
	public static final String ERROR_CONFIG_ITEM_READ_CODE ="COMMON_ERR_CONFIG_0005";
	public static final String ERROR_CONFIG_ITEM_WRITE_CODE ="COMMON_ERR_CONFIG_0006";
	public static final String ERROR_CONFIG_USER_ID_EMPTY_CODE ="COMMON_ERR_CONFIG_0007";
	public static final String ERROR_CONFIG_INVALID_MODULENAME_CODE ="COMMON_ERR_CONFIG_00078";
	public static final String ERROR_CONFIG_RESULE_EMPTY_CODE ="COMMON_ERR_CONFIG_0008";

    public ConfigException(String err_code, String error_description) {
        super(err_code, error_description);
    }
}
