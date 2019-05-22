package com.education.classroom.utils.exception;

public class DictQueryException extends ECMBusinessException {
    private static final long serialVersionUID = 1L;

    public DictQueryException(String err_code, String error_description) {
        super(err_code, error_description);
    }
}
