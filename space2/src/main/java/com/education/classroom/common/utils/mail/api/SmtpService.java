package com.education.classroom.common.utils.mail.api;

import java.security.Provider;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.education.classroom.common.utils.mail.config.Constant;
import com.education.classroom.common.utils.mail.config.MsgBody;
import com.education.classroom.common.utils.mail.config.Smtp;
/**
 * 邮件驱动
 * @Class Name SmtpService
 * @author 路志友
 * @Create In 2016年7月11日
 */
public class SmtpService {

	private SmtpConfigurable configurable;
	
	private Class<?> proClazz;
	
	public SmtpService(SmtpConfigurable configurable) {
		this.configurable = configurable;
		
		try {
			proClazz = Class.forName("com.sun.net.ssl.internal.ssl.Provider");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 邮件发送
	 * 2016年7月11日
	 * By 路志友
	 * @param msgBody
	 * @return int
	 */
	public int send(MsgBody msgBody) {
		
		final Smtp smtp = this.configurable.read();
		
		try {
			Security.addProvider((Provider) proClazz.newInstance());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Properties props = System.getProperties();  
		props.setProperty(Constant.SMTP_HOST, smtp.getHost());  
		props.setProperty(Constant.SMTP_SOCKET_CLASS, Constant.SMTP_SSL_FACTORY);  
		props.setProperty(Constant.SMTP_SOCKET_FALLBACK, "false");  
		props.setProperty(Constant.SMTP_PORT, smtp.getPort());  
		props.setProperty(Constant.SMTP_SOCKET_PORT, smtp.getPort());  
		props.put(Constant.SMTP_AUTH, (null == smtp.getAuth() || "".equals(smtp.getAuth().trim())) ? "true" : smtp.getAuth().trim());
		
		Session session = Session.getDefaultInstance(props, new Authenticator(){  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(smtp.getUser(), smtp.getPass());  
			}
		}); 
		
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(smtp.getUser()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msgBody.getTo(),false));  
			message.setSubject(msgBody.getSubject());  
			message.setText(msgBody.getText()); 
			message.setSentDate(new Date());
			Transport.send(message);
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public static void main(String args[]) {
		
		MsgBody msgBody = new MsgBody();
		msgBody.setTo("1729022402@qq.com");
		msgBody.setSubject("Hello email world");
		msgBody.setText("This is a simple text");
		
		new SmtpService(PropertyConfigration.get()).send(msgBody);
	}
}
