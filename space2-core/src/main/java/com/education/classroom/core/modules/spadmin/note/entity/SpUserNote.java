package com.education.classroom.core.modules.spadmin.note.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 笔记Entity
 * @author zhangyongsheng
 * @version 2016/08/11
 */
public class SpUserNote extends DataEntity<SpUserNote> {
	
	private static final long serialVersionUID = -8978489503395399076L;
	private String userId;				// 用户id
	private String lessonId;			// 课程id
	private String title;				// 标题
	private Date createTime;			// 创建时间
	private String rangeSetting;		// 可看范围 0全部 1 自己  目前该字段待定
	private String imgPath;             //笔记图片
	
	public SpUserNote() {
		super();
	}

	public SpUserNote(String id){
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
	
	@Length(min=0, max=256, message="标题长度必须介于 0 和 256 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=1, max=1, message="可看范围 0全部 1 自己  目前该字段待定长度必须介于 1 和 1 之间")
	public String getRangeSetting() {
		return rangeSetting;
	}

	public void setRangeSetting(String rangeSetting) {
		this.rangeSetting = rangeSetting;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}