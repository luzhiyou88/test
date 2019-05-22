/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.group.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 小组话题表Entity
 * 
 * @author 边磊
 * @version 2016/08/10
 */
public class GroupTopic extends DataEntity<GroupTopic> {

	private static final long serialVersionUID = 3923452324769272268L;
	private String userId; // 用户
	private String groupId; // 属所小组
	private String title; // 标题
	private String content; // 内容
	private Date issueTime; // 发布时间
	private String imgList; // 图片地址
	private String type; // 话题类型 0 话题 1推荐 2公告
	private String userName; // 用户名
	private String thumbImg; // 头像
	private Integer commentNum; // 回复数
	private String groupName;// 小组名称
	private String[] imgs;
	private String topicId;

	public GroupTopic() {
		super();
	}

	public GroupTopic(String id) {
		super(id);
	}

	@Length(min = 1, max = 32, message = "用户长度必须介于 1 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min = 1, max = 32, message = "属所小组长度必须介于 1 和 32 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Length(min = 1, max = 256, message = "标题长度必须介于 1 和 256 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Length(min = 0, max = 1024, message = "内容长度必须介于 0 和 1024 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "发布时间不能为空")
	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}

	@Length(min = 0, max = 256, message = "图片地址长度必须介于 0 和 256 之间")
	public String getImgList() {
		return imgList;
	}

	public void setImgList(String imgList) {
		if (StringUtils.isEmpty(imgList)) {
			this.imgList = "";
		} else {
			this.imgList = imgList;
			this.imgs = imgList.split(",");
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String[] getImgs() {
		return imgs;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	// public void setImgs(String[] imgs) {
	// this.imgs = imgs;
	// }

}