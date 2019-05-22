package com.education.classroom.test;

import com.education.classroom.common.utils.mail.api.PropertyConfigration;
import com.education.classroom.common.utils.mail.api.SmtpService;
import com.education.classroom.common.utils.mail.config.MsgBody;

public class emailTest {

	public static void main(String[] args) {
		MsgBody m = new MsgBody();
		m.setTo("lu_zhiyou@126.com");
		m.setSubject("您好 注册成功");
		m.setText("欢迎注册维品汇会员！您可以点击连接登陆后台http://weixinbao.bid");
		System.out.println(new SmtpService(PropertyConfigration.get()).send(m));

	}

}
