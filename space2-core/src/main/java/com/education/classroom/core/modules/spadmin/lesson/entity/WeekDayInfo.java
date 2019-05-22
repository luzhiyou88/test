/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.lesson.entity;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 课程表日期类
 * @Class Name WeekDayInfo
 * @author zhujie
 * @Create In 2016年8月11日
 */
public class WeekDayInfo extends DataEntity<WeekDayInfo> {
	
	private static final long serialVersionUID = 1L;
	
	private String date;//日期
	private String monthDay;//X月X日
	private String weekDay;//周几
	private boolean todayFlag;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMonthDay() {
		return monthDay;
	}
	public void setMonthDay(String monthDay) {
		this.monthDay = monthDay;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public boolean isTodayFlag() {
		return todayFlag;
	}
	public void setTodayFlag(boolean todayFlag) {
		this.todayFlag = todayFlag;
	}
	
}