package com.education.classroom.utils.exception;

public class CodeServiceException extends ECMBusinessException {
    private static final long serialVersionUID = 1L;

    public CodeServiceException(String err_code, String error_description) {
        super(err_code, error_description);
    }
    
    public CodeServiceException(String error_description) {
        super(error_description);
    }
    
}
