package com.education.classroom.core.modules.spadmin.sign.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.education.classroom.core.persistence.DataEntity;

@SuppressWarnings("serial")
public class UserSign extends DataEntity<UserSign> {

	private String lessonName;
	private String signFlag;
	private Date lessonDate;
	private Date lessonSign;
	private String lessonStartTime;
	private String lessonEndTime;
	private String signTime;
	private String singnState;

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getSingnState() {
		return singnState;
	}

	public void setSingnState(String singnState) {
		if (StringUtils.isEmpty(singnState)) {
			signFlag = "0";
		} else {
			signFlag = "1";
		}
		this.singnState = singnState;
	}

	public Date getLessonDate() {
		return lessonDate;
	}

	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}

	public Date getLessonSign() {
		return lessonSign;
	}

	public void setLessonSign(Date lessonSign) {
		this.lessonSign = lessonSign;
	}

	public String getLessonStartTime() {
		return lessonStartTime;
	}

	public void setLessonStartTime(String lessonStartTime) {
		this.lessonStartTime = lessonStartTime;
	}

	public String getLessonEndTime() {
		return lessonEndTime;
	}

	public void setLessonEndTime(String lessonEndTime) {
		this.lessonEndTime = lessonEndTime;
	}

}
