package com.education.classroom.utils.exception;

public class AuditException extends ECMBusinessException {
    private static final long serialVersionUID = 1L;

    public static final String ERROR_AUDIT_INVALID_MODULENAME ="错误的模块名称！";
    public static final String ERROR_AUDIT_INVALID_MODULENAME_CODE ="COMMON_ERR_AUDIT_00003";
    
	public static final String ERROR_AUDIT_QUERY="审计信息查询发生错误！"; 
	public static final String ERROR_AUDIT_QUERY_CODE="COMMON_ERR_AUDIT_0001";

	public static final String ERROR_AUDIT_ADD="新增审计信息发生错误！"; 
	public static final String ERROR_AUDIT_ADD_CODE="COMMON_ERR_AUDIT_0002";

    public AuditException(String err_code, String error_description) {
        super(err_code, error_description);
    }
    
    public AuditException(String error_description) {
        super(error_description);
    }
    
}
