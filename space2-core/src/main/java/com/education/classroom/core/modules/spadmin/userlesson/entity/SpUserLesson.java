package com.education.classroom.core.modules.spadmin.userlesson.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;


/**
 * 用户课程关联增删该查Entity
 * @author 赵新月
 * @version 2016/08/19
 */
public class SpUserLesson extends DataEntity<SpUserLesson> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户id
	private String lessonId;		// 课程idI
	private String state;		// 预约状态
	
	public SpUserLesson() {
		super();
	}

	public SpUserLesson(String id){
		super(id);
	}

	@Length(min=1, max=128, message="用户id长度必须介于 1 和 128 之间")
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
	
	@Length(min=0, max=1, message="预约状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}