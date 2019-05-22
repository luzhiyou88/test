package com.education.classroom.core.modules.spadmin.usergroup.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 用户小组关系Entity
 * @author zhangyongsheng
 * @version 2016/08/11
 */
public class SpUserGroup extends DataEntity<SpUserGroup> {
	
	private static final long serialVersionUID = -6050777416300354697L;
	private String userId;		// 用户
	private String groupId;		// 小组
	private String state;		// 状态  0被邀请 1申请加入 3已加入  4拒绝邀请 5拒绝申请
	
	public SpUserGroup() {
		super();
	}

	public SpUserGroup(String id){
		super(id);
	}

	@Length(min=1, max=32, message="用户长度必须介于 1 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=32, message="小组长度必须介于 1 和 32 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=1, max=1, message="状态  0被邀请 1申请加入 3已加入  4拒绝邀请 5拒绝申请长度必须介于 1 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}