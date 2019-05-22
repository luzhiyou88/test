package com.education.classroom.core.modules.spadmin.blackboard.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 板书Entity
 * @author zhangyongsheng
 * @version 2016/08/11
 */
public class SpBlackboard extends DataEntity<SpBlackboard> {
	
	private static final long serialVersionUID = -4739684214781490027L;
	private String userId;		// 用户Id
	private String lessonId;	// 课程id
	private String imgPath;		// img_path
	
	public SpBlackboard() {
		super();
	}

	public SpBlackboard(String id){
		super(id);
	}

	@Length(min=1, max=32, message="用户长度必须介于 1 和 32 之间")
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
	
	@Length(min=1, max=256, message="img_path长度必须介于 1 和 256 之间")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}