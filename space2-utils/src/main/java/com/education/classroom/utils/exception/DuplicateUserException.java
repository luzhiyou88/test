package com.education.classroom.utils.exception;

public class DuplicateUserException extends Exception {
    private static final long serialVersionUID = 1L;

    public DuplicateUserException(String userID){
		super(SecurityExceptionInfo.USER_MODIFY_DUPLICATE_ERROR+":"+userID);
	}	
}
