package com.education.classroom.utils.exception;

public class UserModifyException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserModifyException(String errMsg) {
        super(errMsg);
    }
}
