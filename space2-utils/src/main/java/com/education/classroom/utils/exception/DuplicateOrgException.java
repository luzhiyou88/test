package com.education.classroom.utils.exception;

public class DuplicateOrgException extends Exception {
    private static final long serialVersionUID = 1L;

    public DuplicateOrgException(){
		super(SecurityExceptionInfo.ORG_MODIFY_ORG_DUPLICATE_ERROR);
	}	
}
