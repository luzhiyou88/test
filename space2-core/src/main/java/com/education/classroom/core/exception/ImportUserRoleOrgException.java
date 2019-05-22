package com.education.classroom.core.exception;

/**
 * 调用ldap发生的异常
 * @Class Name LdapException
 * @author 张永生
 * @Create In 2015年11月20日
 */
public class ImportUserRoleOrgException extends Exception {

	private static final long serialVersionUID = -2551582150028968555L;

	public ImportUserRoleOrgException() {
		super();
	}

	public ImportUserRoleOrgException(String message) {
		super(message);
	}

	public ImportUserRoleOrgException(Throwable cause) {
		super(cause);
	}

	public ImportUserRoleOrgException(String message, Throwable cause) {
		super(message, cause);
	}
}
