/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.course.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.modules.spadmin.courseResource.entity.SpCourseResource;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.persistence.DataEntity;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 套课管理Entity
 * @author 朱杰
 * @version 2016/08/06
 */
public class SpCourse extends DataEntity<SpCourse> {
	
	private static final long serialVersionUID = 1L;
	private String courseName;		// 套课名称
	private String courseNumber;		// 课程总数
	private String spaceId;		// 学校ID
	private String categoryId;		// 套课分类
	private String courseType;		// 课程类型,0 免费 1会员 2收费
	private String courseTypeName;		// 课程类型,0 免费 1会员 2收费
	private Double coursePrice;		// 套课价格
	private String categoryName;    // 套课分类名称
	private List<SpCourseResource> courseResource;// 套课资源
	private List<String> addCourseResourceId;
	private String courseSource;// 套课来源 1：本地 2：平台
	
	private List<SpLesson> lessonList;  //套课关联的课程
	
	public SpCourse() {
		super();
	}

	public SpCourse(String id){
		super(id);
	}

	@Length(min=1, max=128, message="套课名称长度必须介于 1 和 128 之间")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	@Length(min=1, max=11, message="课程总数长度必须介于 1 和 11 之间")
	public String getCourseNumber() {
		return StringUtils.isNotBlank(courseNumber) ? courseNumber : "0";
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	@Length(min=0, max=32, message="学校ID长度必须介于 0 和 32 之间")
	public String getSpaceId() {
		return StringUtils.isNotBlank(spaceId) ? spaceId : SpaceUtils.getSpaceId();
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Length(min=1, max=32, message="套课分类长度必须介于 1 和 32 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=1, max=1, message="课程类型,0 免费 1会员 2收费长度必须介于 1 和 1 之间")
	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
	public Double getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(Double coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getAddCourseResourceId() {
		return addCourseResourceId;
	}

	public void setAddCourseResourceId(List<String> addCourseResourceId) {
		this.addCourseResourceId = addCourseResourceId;
	}

	public List<SpCourseResource> getCourseResource() {
		return courseResource;
	}

	public void setCourseResource(List<SpCourseResource> courseResource) {
		this.courseResource = courseResource;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getCourseSource() {
		return courseSource;
	}

	public void setCourseSource(String courseSource) {
		this.courseSource = courseSource;
	}

	public List<SpLesson> getLessonList() {
		return lessonList;
	}

	public void setLessonList(List<SpLesson> lessonList) {
		this.lessonList = lessonList;
	}
	
	
}