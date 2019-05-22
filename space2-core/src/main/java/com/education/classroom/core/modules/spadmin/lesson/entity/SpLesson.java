/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.lesson.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.education.classroom.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 课程管理Entity
 * 
 * @author 朱杰
 * @version 2016/08/06
 */
public class SpLesson extends DataEntity<SpLesson> {

	private static final long serialVersionUID = 1L;
	private String name; // 课程名称
	private String courseId; // 套课ID
	private String courseName; // 套课名称
	private Date lessonDate; // 程课日期
	private String spaceId; // 第二空间ID
	private String spaceName; // 第二空间名
	private String sectionId; // 节次id
	private String lessonStarttime; // 课程开始时间
	private String lessonEndtime; // 课程结束时间
	@SuppressWarnings("unused")
	private String lessonTime; // 开课时间
	private String lessonNumber; // 课程上课人数
	private String teacherId; // 课程上课老师
	private String teacherName; // 课程上课老师名字
	private String teacherDesc; // 上课老师简介
	private String thumbImg; // 预览图
	private String lessonAdress; // 上课的物理地址
	private String lessonState; // 课程状态 0：未开始，1：已开始，2：已结束
	private String lessonStateLabel; // 课程状态
	private String broadcastId; // 直播间ID
	private String broadcastName; // 直播间名称
	private String broadcastDesc; // 直播间描述
	private String broadcastPass; // 讲师端密码
	private Date signTime; // 教师点名时间
	private String publishState; // 发布状态 0未发布 1等待审核 2审核未通过 3审核通过
	private String publishStateLabel; // 发布状态
	private String lessonDesc; // 课程描述
	private Date lessonDateFrom; // 开课日期From
	private Date lessonDateTo; // 开课日期To
	private String lessonStarttimeFrom;// 开始时间参数
	private String lessonEndtimeTo;// 结束时间参数
	private String lessonSource; // 课程来源
	private String lessonSourceLabel; // 课程来源
	private int clickNum; // 点击量
	private String title; // 讲义标题
	private String baseUrl; // 上传文件路径
	private String subscribeState; // 订阅状态
	private String lessonType;// 类型 0课程 1路演
	private String courseNumber;
	private String memberState; // 是否是会员，0：否，1：是
	private String curriculumType; // 课程类型，1：会员课，2：收费课，需购买
	private String appointmentState; // 是否预约，0：否，1：是
	private String payState; // 是否购买，0：否，1：是
	private double coursePrice; // 课程价钱
	private String shareKey;

	public SpLesson() {
		super();
	}

	public SpLesson(String id) {
		super(id);
	}

	@Length(min = 0, max = 32, message = "课程名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 0, max = 32, message = "套课ID长度必须介于 0 和 32 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLessonDate() {
		return lessonDate;
	}

	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}

	@Length(min = 0, max = 32, message = "第二空间ID长度必须介于 0 和 32 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}

	@Length(min = 0, max = 32, message = "节次id长度必须介于 0 和 32 之间")
	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	@Length(min = 0, max = 20, message = "课程开始时间长度必须介于 0 和 20 之间")
	public String getLessonStarttime() {
		return lessonStarttime;
	}

	public void setLessonStarttime(String lessonStarttime) {
		this.lessonStarttime = lessonStarttime;
	}

	@Length(min = 0, max = 20, message = "课程结束时间长度必须介于 0 和 20 之间")
	public String getLessonEndtime() {
		return lessonEndtime;
	}

	public void setLessonEndtime(String lessonEndtime) {
		this.lessonEndtime = lessonEndtime;
	}

	@Length(min = 0, max = 9, message = "课程上课人数长度必须介于0~999999999 之间")
	public String getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(String lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	@Length(min = 0, max = 32, message = "课程上课老师长度必须介于 0 和 32 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	public String getLessonAdress() {
		return lessonAdress;
	}

	public void setLessonAdress(String lessonAdress) {
		this.lessonAdress = lessonAdress;
	}

	public String getLessonState() {
		return lessonState;
	}

	public void setLessonState(String lessonState) {
		this.lessonState = lessonState;
	}

	@Length(min = 0, max = 32, message = "直播间ID长度必须介于 0 和 32 之间")
	public String getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
	}

	@Length(min = 0, max = 32, message = "直播间名称长度必须介于 0 和 32 之间")
	public String getBroadcastName() {
		return broadcastName;
	}

	public void setBroadcastName(String broadcastName) {
		this.broadcastName = broadcastName;
	}

	@Length(min = 0, max = 255, message = "直播间描述长度必须介于 0 和 255 之间")
	public String getBroadcastDesc() {
		return broadcastDesc;
	}

	public void setBroadcastDesc(String broadcastDesc) {
		this.broadcastDesc = broadcastDesc;
	}

	@Length(min = 0, max = 32, message = "讲师端密码长度必须介于 0 和 32 之间")
	public String getBroadcastPass() {
		return broadcastPass;
	}

	public void setBroadcastPass(String broadcastPass) {
		this.broadcastPass = broadcastPass;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	@Length(min = 0, max = 1, message = "发布状态 0未发布 1等待审核   2审核未通过 3审核通过长度必须介于 0 和 1 之间")
	public String getPublishState() {
		return publishState;
	}

	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}

	@Length(min = 0, max = 512, message = "课程描述长度必须介于 0 和 512 之间")
	public String getLessonDesc() {
		return lessonDesc;
	}

	public void setLessonDesc(String lessonDesc) {
		this.lessonDesc = lessonDesc;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Date getLessonDateFrom() {
		return lessonDateFrom;
	}

	public void setLessonDateFrom(Date lessonDateFrom) {
		this.lessonDateFrom = lessonDateFrom;
	}

	public Date getLessonDateTo() {
		return lessonDateTo;
	}

	public void setLessonDateTo(Date lessonDateTo) {
		this.lessonDateTo = lessonDateTo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLessonStateLabel() {
		return lessonStateLabel;
	}

	public void setLessonStateLabel(String lessonStateLabel) {
		this.lessonStateLabel = lessonStateLabel;
	}

	public String getPublishStateLabel() {
		return publishStateLabel;
	}

	public void setPublishStateLabel(String publishStateLabel) {
		this.publishStateLabel = publishStateLabel;
	}

	public String getLessonSource() {
		return lessonSource;
	}

	public void setLessonSource(String lessonSource) {
		this.lessonSource = lessonSource;
	}

	public String getLessonTime() {
		return (StringUtils.isEmpty(lessonStarttime) ? "    " : lessonStarttime)
				+ "~"
				+ (StringUtils.isEmpty(lessonEndtime) ? "    " : lessonEndtime);
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}

	public String getTeacherDesc() {
		return teacherDesc;
	}

	public void setTeacherDesc(String teacherDesc) {
		this.teacherDesc = teacherDesc;
	}

	public String getLessonStarttimeFrom() {
		return lessonStarttimeFrom;
	}

	public void setLessonStarttimeFrom(String lessonStarttimeFrom) {
		this.lessonStarttimeFrom = lessonStarttimeFrom;
	}

	public String getLessonEndtimeTo() {
		return lessonEndtimeTo;
	}

	public void setLessonEndtimeTo(String lessonEndtimeTo) {
		this.lessonEndtimeTo = lessonEndtimeTo;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getSubscribeState() {
		return subscribeState;
	}

	public void setSubscribeState(String subscribeState) {
		this.subscribeState = subscribeState;
	}

	public String getLessonType() {
		return lessonType;
	}

	public void setLessonType(String lessonType) {
		this.lessonType = lessonType;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getMemberState() {
		return memberState;
	}

	public void setMemberState(String memberState) {
		this.memberState = memberState;
	}

	public String getCurriculumType() {
		return curriculumType;
	}

	public void setCurriculumType(String curriculumType) {
		this.curriculumType = curriculumType;
	}

	public String getAppointmentState() {
		return appointmentState;
	}

	public void setAppointmentState(String appointmentState) {
		this.appointmentState = appointmentState;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public double getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(double coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getShareKey() {
		return shareKey;
	}

	public void setShareKey(String shareKey) {
		this.shareKey = shareKey;
	}

	public String getLessonSourceLabel() {
		return lessonSourceLabel;
	}

	public void setLessonSourceLabel(String lessonSourceLabel) {
		this.lessonSourceLabel = lessonSourceLabel;
	}

}