package com.education.classroom.utils.exception;

public class DocumentOperationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String desc;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DocumentOperationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentOperationException(String errorCode, String desc, Throwable e) {
		super(desc, e);
		this.errorCode = errorCode;
		// TODO Auto-generated constructor stub
	}

	

}
