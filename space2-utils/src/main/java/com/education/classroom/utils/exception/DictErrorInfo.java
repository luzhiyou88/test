package com.education.classroom.utils.exception;

public class DictErrorInfo extends ErrorInfo{
	//数据字典查询结果集为空时的异常代码和描述信息
	public static final String ERROR_QUERY_RESULT_NULL_CODE = "COMMON_ERR_DICT_0001";
	public static final String ERROR_QUERY_RESULT_NULL = "数据字典查询结果集为空";
	
	//数据字典查询单条数据为空时的异常代码和描述信息
	public static final String ERROR_QUERY_ITEM_NULL_CODE = "COMMON_ERR_DICT_0002";
	public static final String ERROR_QUERY_ITEM_NULL = "数据字典查询单条数据为空";
	
	//数据字典名称被占用时的异常代码和描述信息
	public static final String ERROR_NAME_OCCUPIED_CODE = "COMMON_ERR_DICT_0003";
	public static final String ERROR_NAME_OCCUPIED = "当前提供的数据字典名称已被占用";
	
	//数据字典定义(简单类型)中某个KEYLABEL或KEYVALUE存在重复项时的异常代码和描述信息
	public static final String ERROR_KEYLABELORKEYVALUE_REPEAT_CODE = "COMMON_ERR_DICT_0004";
	public static final String ERROR_KEYLABELORKEYVALUE_REPEAT = "当前提供的数据字典定义(简单类型)中的某个KEYLABEL或KEYVALUE存在重复项";
	
	//数据字典定义(简单类型)中某个KEYLABEL或KEYVALUE已经存在时的异常代码和描述信息
	public static final String ERROR_KEYLABELORKEYVALUE_OCCUPIED_CODE = "COMMON_ERR_DICT_0005";
	public static final String ERROR_KEYLABELORKEYVALUE_OCCUPIED = "当前提供的数据字典定义(简单类型)中的某个KEYLABEL或KEYVALUE已经存在";
	
	//数据字典定义(简单类型)中某个KEYLABEL或KEYVALUE定义不完整或存在空值
	public static final String ERROR_KEYLABELORKEYVALUE_DUMMY_CODE = "COMMON_ERR_DICT_0010";
	public static final String ERROR_KEYLABELORKEYVALUE_DUMMY = "当前提供的数据字典定义(简单类型)中的某个KEYLABEL或KEYVALUE为空";
	
	//数据字典定义(复杂类型)中某个ENUMKEY或ENUMDESC存在重复项时的异常代码和描述信息
	public static final String ERROR_ENUMKEYORENUMDESC_REPEAT_CODE = "COMMON_ERR_DICT_0006";
	public static final String ERROR_ENUMKEYORENUMDESC_REPEAT = "当前提供的数据字典定义(复杂类型)中的某个ENUMKEY或ENUMDESC存在重复项";
	
	//数据字典定义(复杂类型)中某个ENUMKEY或ENUMDESC已经存在时的异常代码和描述信息
	public static final String ERROR_ENUMKEYORENUMDESC_OCCUPIED_CODE = "COMMON_ERR_DICT_0007";
	public static final String ERROR_ENUMKEYORENUMDESC_OCCUPIED = "当前提供的数据字典定义(复杂类型)中的某个ENUMKEY或ENUMDESC已经存在";
	
	//数据字典定义(复杂类型)中某个ENUMLABEL或ENUMVALUE存在重复项时的异常代码和描述信息
	public static final String ERROR_ENUMLABELORENUMVALUE_REPEAT_CODE = "COMMON_ERR_DICT_0008";
	public static final String ERROR_ENUMLABELORENUMVALUE_REPEAT = "当前提供的数据字典定义(复杂类型)中的某个ENUMLABEL或ENUMVALUE存在重复项";
	
	//数据字典定义(复杂类型)中某个ENUMLABEL或ENUMVALUE已经存在时的异常代码和描述信息
	public static final String ERROR_ENUMLABELORENUMVALUE_OCCUPIED_CODE = "COMMON_ERR_DICT_0009";
	public static final String ERROR_ENUMLABELORENUMVALUE_OCCUPIED = "当前提供的数据字典定义(简单类型)中的某个ENUMLABEL或ENUMVALUE已经存在";
	
	public static final String ERROR_SYSTEMDIC_CODE = "COMMON_ERR_DICT_0011";
	public static final String ERROR_SYSTEMDIC = "只读系统字典请在相关的管理模块进行条件查询";
	
	public static final String ERROR_DICNAME_EMPTY_CODE = "COMMON_ERR_DICT_0012";
	public static final String ERROR_DICNAME_EMPTY = "传入的数据字典名称为空！";

}
