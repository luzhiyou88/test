package com.education.classroom.core.modules.spadmin.feedback.entity;


import com.education.classroom.core.persistence.DataEntity;

/**
 * 资源收藏Entity
 * 
 * @author 边磊
 * @version 2016/08/10
 */
public class SpFeedback extends DataEntity<SpFeedback> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3071073145715827170L;
	
	private String userId;
	private String content;
	private String userName;
	private String mobile;
	private String loginName;
	
	public SpFeedback() {
		super();
	}
	public SpFeedback(String id) {
		super(id);
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}