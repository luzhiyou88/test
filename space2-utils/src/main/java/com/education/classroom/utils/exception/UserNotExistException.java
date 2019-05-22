package com.education.classroom.utils.exception;

public class UserNotExistException extends ECMSystemException{
    private static final long serialVersionUID = 1L;

    public UserNotExistException(String errMsg){
		super("User Not Exist Exception!"+errMsg);
	}
	
	public UserNotExistException(){
		super("User Not Exist Exception!");
	}
}
