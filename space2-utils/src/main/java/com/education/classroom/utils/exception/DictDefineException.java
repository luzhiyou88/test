package com.education.classroom.utils.exception;

public class DictDefineException extends ECMBusinessException {
    private static final long serialVersionUID = 1L;

    public DictDefineException(String err_code, String error_description) {
        super(err_code, error_description);
    }
}
