package com.education.classroom.core.modules.spadmin.courseResource.entity;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 套课资料
 * @Class Name SpCourseResource
 * @author zhujie
 * @Create In 2016年8月10日
 */
public class SpCourseResource extends DataEntity<SpCourseResource> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 资料名称
	private String type;		// 1视频2音频3电子书
	private String courseId;		// 套课的id
	private String domain;		// 0本站 1cc
	private String baseUrl;		// 资料路径
	private String thumbImg;		// 预览图
	private String jsonBody;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
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