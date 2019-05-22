package com.education.classroom.core.modules.spadmin.handouts.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 讲义Entity
 * @Class Name SpHandouts
 * @author zhangyongsheng
 * @Create In 2016年8月10日
 */
public class SpHandouts extends DataEntity<SpHandouts> {
	
	private static final long serialVersionUID = 1L;
	private String title;			// 讲义标题
	private String lessonId;		// 课程ID
	private String spaceId;			// 第二空间ID
	private String domain;			// 0本地1cc
	private String baseUrl;			// 讲义路径
	private String thumbImg;		// 图片
	private String jsonBody;
	
	public SpHandouts() {
		super();
	}

	public SpHandouts(String id){
		super(id);
	}

	@Length(min=0, max=128, message="讲义标题长度必须介于 0 和 128 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=32, message="课程ID长度必须介于 0 和 32 之间")
	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	
	@Length(min=1, max=32, message="第二空间ID长度必须介于 1 和 32 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Length(min=0, max=1, message="0本地1cc长度必须介于 0 和 1 之间")
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Length(min=0, max=200, message="讲义路径长度必须介于 0 和 200 之间")
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	@Length(min=0, max=200, message="thumb_img长度必须介于 0 和 200 之间")
	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	public String getJsonBody() {
		return jsonBody;
	}

	public void setJsonBody(String jsonBody) {
		this.jsonBody = jsonBody;
	}
	
}