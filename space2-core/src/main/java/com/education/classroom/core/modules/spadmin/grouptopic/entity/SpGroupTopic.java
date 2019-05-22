/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.grouptopic.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 小组话题表Entity
 * 
 * @author shangjunwei
 * @version 2016/08/10
 */
public class SpGroupTopic extends DataEntity<SpGroupTopic> {

	private static final long serialVersionUID = 3923452324769272268L;
	private String topicId;
	private String userId; // 用户
	private String groupId; // 属所小组
	private String title; // 标题
	private String content; // 内容
	private Date issueTime; // 发布时间
	private String imgList; // 图片地址
	private String type; // 话题类型 0 话题 1推荐 2公告
	private String[] imgs; // 图片地址转化数组
	private Integer commentNum; // 评论数
	private String userName; // 用户姓名
	private String groupName; // 小组名称
	private String thumbImg; // 用户头像

	public SpGroupTopic() {
		super();
	}

	public SpGroupTopic(String id) {
		super(id);
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
		this.imgList = imgList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getImgs() {
		return imgs;
	}

	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

}