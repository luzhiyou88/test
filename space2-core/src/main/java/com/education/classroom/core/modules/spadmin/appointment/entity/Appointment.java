package com.education.classroom.core.modules.spadmin.appointment.entity;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 预约Entity
 * @author 尚军伟
 * @version 2016-08-9
 */
public class Appointment extends DataEntity<Appointment>{
	
	private static final long serialVersionUID = -3312030034465693049L;
	private String LessonId;            //课程ID
	private String userId;          //用户ID
	private String state;          //预约状态
	private int num;                //预约总座位数
	private int count;               //剩余座位数
	
	
	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public Appointment(){}




	public String getLessonId() {
		return LessonId;
	}


	public void setLessonId(String lessonId) {
		LessonId = lessonId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	

	

}
