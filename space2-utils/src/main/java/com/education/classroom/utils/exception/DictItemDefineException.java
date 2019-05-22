package com.education.classroom.utils.exception;

public class DictItemDefineException extends ECMBusinessException {
    private static final long serialVersionUID = 1L;

    public DictItemDefineException(String err_code, String error_description) {
        super(err_code, error_description);
    }
}
