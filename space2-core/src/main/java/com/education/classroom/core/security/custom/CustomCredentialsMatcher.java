package com.education.classroom.core.security.custom;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Service;

import com.education.classroom.utils.PasswordUtil;


/**
 * 自定义的密码校验器
 * @Class Name CustomCredentialsMatcher
 * @author 张永生
 * @Create In 2015年11月27日
 */
@Service
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken,
			AuthenticationInfo info) {
		//密码校验
		char[] pwd = (char[])authcToken.getCredentials();
		String password = new String(pwd);
		String dbPwd = (String) info.getCredentials();
		return PasswordUtil.encodePassword(password).equals(dbPwd);
	}

}
