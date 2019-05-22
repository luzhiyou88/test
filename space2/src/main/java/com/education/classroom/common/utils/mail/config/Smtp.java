package com.education.classroom.common.utils.mail.config;

/**
 * 邮件配置实体类
 * @Class Name Smtp
 * @author 路志友
 * @Create In 2016年7月11日
 */
public class Smtp {

	private String host;
	private String port;
	private String auth;
	private String user;
	private String pass;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
