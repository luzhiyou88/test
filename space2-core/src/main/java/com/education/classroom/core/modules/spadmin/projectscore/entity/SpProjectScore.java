/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.projectscore.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 项目评分Entity
 * 
 * @author 边磊
 * @version 2016/08/29
 */
public class SpProjectScore extends DataEntity<SpProjectScore> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 445431716100297683L;
	private String userId; // 用户id
	private String projectId; // 项目id
	private String score; // 评分

	public SpProjectScore() {
		super();
	}

	public SpProjectScore(String id) {
		super(id);
	}

	@Length(min = 1, max = 32, message = "用户id长度必须介于 1 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min = 1, max = 32, message = "项目id长度必须介于 1 和 32 之间")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Length(min = 1, max = 11, message = "评分长度必须介于 1 和 11 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}