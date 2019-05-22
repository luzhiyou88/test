package com.education.classroom.common.utils.mail.config;
/**
 * 邮件定义
 * @Class Name Constant
 * @author 路志友
 * @Create In 2016年7月11日
 */
public class Constant {

	public static final ClassLoader clazzLoader = Constant.class.getClassLoader();
	
	public static final String FILE_PROPERTY_DEFAULT = "smtp-config.properties";
	
	public static final String SMTP_SOCKET_CLASS = "mail.smtp.socketFactory.class";
	
	public static final String SMTP_SOCKET_FALLBACK = "mail.smtp.socketFactory.fallback";
	
	public static final String SMTP_SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	public static final String SMTP_HOST = "mail.smtp.host";
	
	public static final String SMTP_PORT = "mail.smtp.port";
	
	public static final String SMTP_SOCKET_PORT = "mail.smtp.socketFactory.port";
	
	public static final String SMTP_AUTH = "mail.smtp.auth";
	
	public static final String PROPERTY_USER = "username";

	public static final String PROPERTY_PASS = "password";
	
}
