package com.education.classroom.utils.exception;

public class RoleNotExistException extends Exception{
    private static final long serialVersionUID = 1L;

    public RoleNotExistException(String errMsg){
		super(errMsg);
	}
}
