/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.user.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * tokenEntity
 * @author luzhiyou
 * @version 2016/08/08
 */
public class SpUserToken extends DataEntity<SpUserToken> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户
	private String userToken;		// user_token
	
	public SpUserToken() {
		super();
	}

	public SpUserToken(String id){
		super(id);
	}

	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min=1, max=32, message="user_token长度必须介于 1 和 32 之间")
	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
}