/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.lesson.entity;

import java.util.List;

import com.education.classroom.core.modules.spadmin.course.entity.SpCourse;
import com.education.classroom.core.modules.spadmin.courseResource.entity.SpCourseResource;
import com.education.classroom.core.modules.spadmin.exam.entity.SpExamination;
import com.education.classroom.core.modules.spadmin.handouts.entity.SpHandouts;
import com.education.classroom.core.modules.spadmin.material.entity.SpMaterials;
import com.education.classroom.core.modules.spadmin.problems.entity.SpProblems;
import com.education.classroom.core.modules.spadmin.project.entity.SpProject;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectResource;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectUser;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.persistence.DataEntity;

/**
 * 发布课程实体
 * @Class Name PublishLesson
 * @author zhujie
 * @Create In 2016年8月10日
 */
public class PublishLesson extends DataEntity<PublishLesson> {
	
	private static final long serialVersionUID = 1L;
	
	private SpLesson spLesson;
	
	private SpCourse spCourse;
	
	private SpHandouts spHandouts;
	
	private List<SpCourseResource> spCourseResource;
	
	private SpUser teacher;
	
	private SpExamination examination;
	
	private List<SpProblems> problems;
	
	private List<SpMaterials> materials;
	
	private List<SpProject> spProjects;
	
	private List<SpProjectUser> spProjectUsers;
	
	private List<SpProjectResource> spProjectResources;

	public SpLesson getSpLesson() {
		return spLesson;
	}

	public void setSpLesson(SpLesson spLesson) {
		this.spLesson = spLesson;
	}

	public SpCourse getSpCourse() {
		return spCourse;
	}

	public void setSpCourse(SpCourse spCourse) {
		this.spCourse = spCourse;
	}

	public SpHandouts getSpHandouts() {
		return spHandouts;
	}

	public void setSpHandouts(SpHandouts spHandouts) {
		this.spHandouts = spHandouts;
	}

	public List<SpCourseResource> getSpCourseResource() {
		return spCourseResource;
	}

	public void setSpCourseResource(List<SpCourseResource> spCourseResource) {
		this.spCourseResource = spCourseResource;
	}

	public SpUser getTeacher() {
		return teacher;
	}

	public void setTeacher(SpUser teacher) {
		this.teacher = teacher;
	}

	public SpExamination getExamination() {
		return examination;
	}

	public void setExamination(SpExamination examination) {
		this.examination = examination;
	}

	public List<SpProblems> getProblems() {
		return problems;
	}

	public void setProblems(List<SpProblems> problems) {
		this.problems = problems;
	}

	public List<SpMaterials> getMaterials() {
		return materials;
	}

	public void setMaterials(List<SpMaterials> materials) {
		this.materials = materials;
	}

	public List<SpProject> getSpProjects() {
		return spProjects;
	}

	public void setSpProjects(List<SpProject> spProjects) {
		this.spProjects = spProjects;
	}

	public List<SpProjectUser> getSpProjectUsers() {
		return spProjectUsers;
	}

	public void setSpProjectUsers(List<SpProjectUser> spProjectUsers) {
		this.spProjectUsers = spProjectUsers;
	}

	public List<SpProjectResource> getSpProjectResources() {
		return spProjectResources;
	}

	public void setSpProjectResources(List<SpProjectResource> spProjectResources) {
		this.spProjectResources = spProjectResources;
	}


}