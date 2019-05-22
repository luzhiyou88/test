/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.group.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 小组管理Entity
 * 
 * @author 边磊
 * @version 2016/08/06
 */
@SuppressWarnings("serial")
public class UserGroup extends DataEntity<UserGroup> {

	private String userId; // 用户
	private String groupId; // 小组
	private String state; // 状态 0被邀请 1申请加入 3已加入 4拒绝邀请 5拒绝申请6退出 7删除
	private String userName; // 用户名
	private String thumbImg; // 头像

	public UserGroup() {
		super();
	}

	public UserGroup(String id) {
		super(id);
	}

	@Length(min = 1, max = 32, message = "用户长度必须介于 1 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min = 1, max = 32, message = "小组长度必须介于 1 和 32 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Length(min = 1, max = 1, message = "状态  0被邀请 1申请加入 3已加入  4拒绝邀请 5拒绝申请 6已退出 7删除 长度必须介于 1 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
		if (StringUtils.isEmpty(thumbImg)) {
			this.thumbImg = "";
		} else {
			this.thumbImg = thumbImg;
		}
	}

}