/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.project.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 项目资源管理Entity
 * @author 杨立明
 * @version 2016/08/30
 */
public class SpProjectResource extends DataEntity<SpProjectResource> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String projectId;		// 所属项目
	private String baseUrl;		// 资源路径
	private String thumbImg;		// 预览图
	private String projectName;
	private String jsonBody;
	
	public SpProjectResource() {
		super();
	}

	public SpProjectResource(String id){
		super(id);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Length(min=1, max=128, message="名称长度必须介于 1 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=32, message="所属项目长度必须介于 1 和 32 之间")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Length(min=1, max=256, message="资源路径长度必须介于 1 和 256 之间")
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	@Length(min=0, max=256, message="预览图长度必须介于 0 和 256 之间")
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