/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.sign.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 签到Entity
 * @author 边磊
 * @version 2016/09/09
 */
public class SpSign extends DataEntity<SpSign> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户id
	private String lessonId;		// 课程id
	private Date signTime;		// 签到时间
	private String state;		// 态状；1签到   2，签到迟到
	
	public SpSign() {
		super();
	}

	public SpSign(String id){
		super(id);
	}

	@Length(min=1, max=32, message="用户id长度必须介于 1 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=32, message="课程id长度必须介于 1 和 32 之间")
	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="签到时间不能为空")
	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	
	@Length(min=1, max=1, message="态状；1签到   2，签到迟到长度必须介于 1 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}