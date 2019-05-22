package com.education.classroom.core.pad.entity;

import java.util.List;

import com.education.classroom.core.modules.spadmin.courseResource.entity.SpCourseResource;
import com.education.classroom.core.modules.spadmin.exam.entity.SpPadExamination;
import com.education.classroom.core.modules.spadmin.handouts.entity.SpHandouts;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.modules.spadmin.project.entity.SpPadProject;
import com.education.classroom.core.modules.spadmin.sign.entity.SpSign;
import com.education.classroom.core.modules.spadmin.space.entity.SpSpace;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.persistence.DataEntity;

/**
 * Pad端登陆信息获取
 * @Class Name PadLoginInfo
 * @author zhujie
 * @Create In 2016年9月7日
 */
public class PadLoginInfo extends DataEntity<PadLoginInfo> {

	private static final long serialVersionUID = 1L;
	
	// 用户信息
	private SpUser user;
	// space2控件信息
	private SpSpace space;
	// 签到信息
	private SpSign sign;
	// 课程信息
	private SpLesson lesson;
	// 课程讲义
	private SpHandouts handouts;
	// 试卷
	private SpPadExamination examination;
	// 路演项目
	private List<SpPadProject> projectList;
	// 套课资源
	private List<SpCourseResource> courseResourceList;
	
	public SpUser getUser() {
		return user;
	}
	public void setUser(SpUser user) {
		this.user = user;
	}
	public SpSpace getSpace() {
		return space;
	}
	public void setSpace(SpSpace space) {
		this.space = space;
	}
	public SpSign getSign() {
		return sign;
	}
	public void setSign(SpSign sign) {
		this.sign = sign;
	}
	public SpLesson getLesson() {
		return lesson;
	}
	public void setLesson(SpLesson lesson) {
		this.lesson = lesson;
	}
	public SpHandouts getHandouts() {
		return handouts;
	}
	public void setHandouts(SpHandouts handouts) {
		this.handouts = handouts;
	}
	public SpPadExamination getExamination() {
		return examination;
	}
	public void setExamination(SpPadExamination examination) {
		this.examination = examination;
	}
	public List<SpPadProject> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<SpPadProject> projectList) {
		this.projectList = projectList;
	}
	public List<SpCourseResource> getCourseResourceList() {
		return courseResourceList;
	}
	public void setCourseResourceList(List<SpCourseResource> courseResourceList) {
		this.courseResourceList = courseResourceList;
	}
	
}