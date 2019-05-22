package com.education.classroom.utils.exception;

public class OrgNotExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public OrgNotExistException(String errMsg) {
        super(errMsg);
    }

    public OrgNotExistException() {
        super();
    }
}
