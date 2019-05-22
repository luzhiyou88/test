package com.education.classroom.core.exception;

/**
 * 调用ldap发生的异常
 * @Class Name LdapException
 * @author 张永生
 * @Create In 2015年11月20日
 */
public class LdapException extends RuntimeException {

	private static final long serialVersionUID = -2551582150028968555L;

	public LdapException() {
		super();
	}

	public LdapException(String message) {
		super(message);
	}

	public LdapException(Throwable cause) {
		super(cause);
	}

	public LdapException(String message, Throwable cause) {
		super(message, cause);
	}
}
