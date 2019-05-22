package com.education.classroom.utils.exception;

public class TDSConnectionFailedException extends ECMSystemException{

	private static final long serialVersionUID = -9162025676887413441L;

	public TDSConnectionFailedException(String msg) {
        super(msg);
    }
}
