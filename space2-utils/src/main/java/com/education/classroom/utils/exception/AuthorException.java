package com.education.classroom.utils.exception;

public class AuthorException extends ECMBusinessException {
    
	private static final long serialVersionUID = 1L;
    
    /* 
     * 授权模块的错误信息
     */
    
	public static final String ERROR_AUTHOR_FUNCTION_NOT_EXIST="功能点不存在！"; 
	public static final String ERROR_AUTHOR_FUNCTION_NOT_EXIST_CODE="COMMON_ERR_AUTHOR_0001";

	public static final String ERROR_AUTHOR_FUNTIONINFO_READ="读取功能点出错"; 
	public static final String ERROR_AUTHOR_FUNTIONINFO_READ_CODE="COMMON_ERR_AUTHOR_0002";

	public static final String ERROR_AUTHOR_FUNTIONINFO_ADD="增加功能点出错"; 
	public static final String ERROR_AUTHOR_FUNTIONINFO_ADD_CODE="COMMON_ERR_AUTHOR_0003";

	public static final String ERROR_AUTHOR_FUNTIONINFO_REMOVE="删除功能点出错"; 
	public static final String ERROR_AUTHOR_FUNTIONINFO_REMOVE_CODE="COMMON_ERR_AUTHOR_0004";

	public static final String ERROR_AUTHOR_PERM_DEL="删除授权节点出错"; 
	public static final String ERROR_AUTHOR_PERM_DEL_CODE="COMMON_ERR_AUTHOR_0005";
	
	public static final String ERROR_AUTHOR_TIME_WRONG="授权时限有效性不正确！"; 
	public static final String ERROR_AUTHOR_TIME_WRONG_CODE="COMMON_ERR_AUTHOR_0006";

	public static final String ERROR_AUTHOR_OPERATION_WRONG="操作名称不正确！"; 
	public static final String ERROR_AUTHOR_OPERATION_WRONG_CODE="COMMON_ERR_AUTHOR_0007";

	public static final String ERROR_AUTHOR_ADD="增加授权操作发生错误！"; 
	public static final String ERROR_AUTHOR_ADD_CODE="COMMON_ERR_AUTHOR_0008";

	public static final String ERROR_AUTHOR_DUPLICATION="授权信息重复！"; 
	public static final String ERROR_AUTHOR_DUPLICATION_CODE="COMMON_ERR_AUTHOR_0009";

	public static final String ERROR_AUTHOR_PARENT_FUNCTION_NOT_EXIST="父功能点不存在！"; 
	public static final String ERROR_AUTHOR_PARENT_FUNCTION_NOT_EXIST_CODE="COMMON_ERR_AUTHOR_0010";

	public static final String ERROR_AUTHOR_GET_USER_TREE="获取功能树出错！"; 
	public static final String ERROR_AUTHOR_GET_USER_TREE_CODE="COMMON_ERR_AUTHOR_0011";

	public static final String ERROR_AUTHOR_CHECK="检查用户权限出错！";
	public static final String ERROR_AUTHOR_CHECK_CODE="COMMON_ERR_AUTHOR_0012";
	
	public static final String ERROR_AUTHOR_INVALID_ORGDN="无效的组织机构DN！";
	public static final String ERROR_AUTHOR_INVALID_ORGDN_CODE="COMMON_ERR_AUTHOR_0013";

	public static final String ERROR_AUTHOR_INVALID_PLANTNAME="本站点下没有此电厂！";
	public static final String ERROR_AUTHOR_INVALID_PLANTNAME_CODE="COMMON_ERR_AUTHOR_0014";

	public static final String ERROR_AUTHOR_FUNCTION_UDPATE="更新功能点出错！"; 
	public static final String ERROR_AUTHOR_FUNCTION_UDPATE_CODE="COMMON_ERR_AUTHOR_0015";

	public static final String ERROR_AUTHOR_DB_ERROR="访问数据库发生错误！"; 
	public static final String ERROR_AUTHOR_DB_ERROR_CODE="COMMON_ERR_AUTHOR_0000";

    public AuthorException(String err_code, String error_description) {
        super(err_code, error_description);
    }
}
