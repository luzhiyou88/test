package com.education.classroom.core.modules.spadmin.appointment.dao;

import java.util.Map;

import com.education.classroom.core.modules.spadmin.appointment.entity.Appointment;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface AppointmentDao extends CrudDao<Appointment>{

	
	//根据课程ID查询课程来源
	public SpLesson selectLessonType(String lessonId);
	
	//插入数据到用户预约课程表中sp_user_lesson
	public int insertUserLesson(Appointment appointment);
	
	//查询课程预约人数
	public int selectAppointmentNum(String lessonId);
	
	//查询课程预约唯一性
	public int selectSingon(Map<String,Object> map);
	
	//查询课程是否是购买课程
	public String selectPayType(Map<String,Object> map);
	
	//取消预约
	public void updateAppointment(Map<String,Object> map);
}
