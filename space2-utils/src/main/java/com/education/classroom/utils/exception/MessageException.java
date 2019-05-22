package com.education.classroom.utils.exception;

public class MessageException extends ECMBusinessException {
    private static final long serialVersionUID = 1L;

    public MessageException(String err_code, String error_description) {
        super(err_code, error_description);
    }
    
    public MessageException(String error_description) {
        super(error_description);
    }
}
