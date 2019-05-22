package com.education.classroom.utils.exception;

public class ParameterInvalidException extends ECMSystemException {

	private static final long serialVersionUID = -1942339008214361514L;

	public ParameterInvalidException(String paraInfo) {
		
		super(CommonExceptionInfo.COMMON_ERR_MSG_INVALID_PARAMETER+"\n"+paraInfo);
        
    }
	
	public ParameterInvalidException() {
		
		super(CommonExceptionInfo.COMMON_ERR_MSG_INVALID_PARAMETER);
        
    }
	
}
