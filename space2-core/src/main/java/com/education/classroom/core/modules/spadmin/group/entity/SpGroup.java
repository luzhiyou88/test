/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.group.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 小组管理Entity
 * 
 * @author 边磊
 * @version 2016/08/06
 */
public class SpGroup extends DataEntity<SpGroup> {
	private static final long serialVersionUID = 1L;
	private String name; // 小组名称
	private String spaceId; // 所属第二空间
	private String groupType; // 0本班级组 1校内组 2平台组
	private String leaderId; // 组长
	private String thumbImg; // 小组图标
	private String publishState; // 发布状态 0未发布 1等待审核 2审核未通过 3审核通过

	public SpGroup() {
		super();
	}

	public SpGroup(String id) {
		super(id);
	}

	@Length(min = 1, max = 128, message = "小组名称长度必须介于 1 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 1, max = 32, message = "所属第二空间长度必须介于 1 和 32 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}

	@Length(min = 0, max = 1, message = "0本班级组 1校内组 2平台组长度必须介于 0 和 1 之间")
	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	@Length(min = 1, max = 32, message = "组长长度必须介于 1 和 32 之间")
	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	@Length(min = 0, max = 256, message = "小组图标长度必须介于 0 和 256 之间")
	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	@Length(min = 0, max = 1, message = "发布状态 0未发布 1等待审核   2审核未通过 3审核通过长度必须介于 0 和 1 之间")
	public String getPublishState() {
		return publishState;
	}

	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}
}