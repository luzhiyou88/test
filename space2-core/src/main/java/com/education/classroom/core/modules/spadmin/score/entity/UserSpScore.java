/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.score.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 评价Entity
 * 
 * @author 边磊
 * @version 2016/08/09
 */
public class UserSpScore extends DataEntity<UserSpScore> {

	private static final long serialVersionUID = 1L;
	private String userId; // 用户
	private String sourceType; // 资源类型 0课程 1视频 2音频 3文档
	private String sourceId; // 评论id
	private Integer level; // 评价星级
	private String content; // 评价内容
	private Date createTime; // 评价时间
	private String userName;
	private String thumbImg;

	public UserSpScore() {
		super();
	}

	public UserSpScore(String id) {
		super(id);
	}

	@Length(min = 1, max = 32, message = "用户长度必须介于 1 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min = 1, max = 1, message = "资源类型 0课程 1视频 2音频 3文档长度必须介于 1 和 1 之间")
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Length(min = 1, max = 32, message = "评论id长度必须介于 1 和 32 之间")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Length(min = 0, max = 512, message = "评价内容长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "评价时间不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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