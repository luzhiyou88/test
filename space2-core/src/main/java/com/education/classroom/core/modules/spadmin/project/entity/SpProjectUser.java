/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.project.entity;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 项目成员Entity
 * @Class Name SpProjectUser
 * @author zhujie
 * @Create In 2016年8月31日
 */
public class SpProjectUser extends DataEntity<SpProjectUser> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户id
	private String projectId;		// 项目id
	private String ability;		// 能力
	private String antecedents;		// 历履
	private String userName;
	private String headerImg;
	private String sex;
	private String age;
	private String projectName;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeaderImg() {
		return headerImg;
	}
	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getAbility() {
		return ability;
	}
	public void setAbility(String ability) {
		this.ability = ability;
	}
	public String getAntecedents() {
		return antecedents;
	}
	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}
	
		
}