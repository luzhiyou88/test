/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.comment.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 评论回复Entity
 * @author 尚军伟
 * @version 2016/08/10
 */
public class SpComment extends DataEntity<SpComment> {
	
	private static final long serialVersionUID = -4641547104361700732L;
	private String user;		// 用户
	private String sourceType;		// 源资类型 0小组话题
	private String sourceId;		// 回复id
	private String content;		// 回复内容
	private String userId;
	private String userName;   //用户姓名
	private String thumbImg;  //用户头像
	
	
	public SpComment() {
		super();
	}

	public SpComment(String id){
		super(id);
	}

	@Length(min=1, max=32, message="用户长度必须介于 1 和 32 之间")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	@Length(min=1, max=1, message="源资类型 0小组话题长度必须介于 1 和 1 之间")
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	@Length(min=1, max=32, message="回复id长度必须介于 1 和 32 之间")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@Length(min=1, max=256, message="回复内容长度必须介于 1 和 256 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	
}