package com.education.classroom.utils.exception;

public class NotAuthorizedException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotAuthorizedException() {
        super(SecurityExceptionInfo.NOT_AUTHORIZED_ERROR);
    }
}
