package com.education.classroom.utils.exception;

public class ExchangeServerException extends ECMBusinessException {

	private static final long serialVersionUID = 1L;

	public ExchangeServerException(String err_code, String error_description){
		super(err_code, error_description);
	}
}
