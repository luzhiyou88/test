package com.education.classroom.core.exception;

/**
 * Service层公用的Exception
 * 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * @Class Name ServiceException
 * @author 张永生
 * @Create In 2015年11月20日
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
