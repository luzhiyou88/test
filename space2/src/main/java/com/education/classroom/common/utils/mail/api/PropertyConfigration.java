package com.education.classroom.common.utils.mail.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.education.classroom.common.utils.mail.config.Constant;
import com.education.classroom.common.utils.mail.config.Smtp;
/**
 * 邮件发送配置文件
 * @Class Name PropertyConfigration
 * @author 路志友
 * @Create In 2016年7月11日
 */
public class PropertyConfigration implements SmtpConfigurable {

	private static InputStream inputStream = SmtpConfigurable.class
			.getClassLoader().getResourceAsStream(
					Constant.FILE_PROPERTY_DEFAULT);

	private static PropertyConfigration pc = buildPropertyConfiguration();

	private static PropertyConfigration buildPropertyConfiguration() {
		return new PropertyConfigration();
	}

	public static PropertyConfigration get() {
		if (null == pc) {
			pc = buildPropertyConfiguration();
		}
		return pc;
	}

	private Properties properties;

	private PropertyConfigration() {
		properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Smtp read() {

		Smtp smtp = new Smtp();

		String host = properties.getProperty(Constant.SMTP_HOST);
		String port = properties.getProperty(Constant.SMTP_PORT);
		String auth = properties.getProperty(Constant.SMTP_AUTH);
		String user = properties.getProperty(Constant.PROPERTY_USER);
		String pass = properties.getProperty(Constant.PROPERTY_PASS);

		smtp.setHost(host);
		smtp.setPort(port);
		smtp.setAuth(auth);
		smtp.setUser(user);
		smtp.setPass(pass);

		return smtp;
	}

}
