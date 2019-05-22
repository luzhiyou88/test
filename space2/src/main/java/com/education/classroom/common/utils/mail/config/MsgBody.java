package com.education.classroom.common.utils.mail.config;
/**
 * 邮件实体类
 * @Class Name MsgBody
 * @author 路志友
 * @Create In 2016年7月11日
 */
public class MsgBody {

	private String to;

	private String subject;

	private String text;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
