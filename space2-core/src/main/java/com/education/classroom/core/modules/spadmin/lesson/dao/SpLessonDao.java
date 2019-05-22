/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.lesson.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 课程管理DAO接口
 * @author 朱杰
 * @version 2016/08/06
 */
@MyBatisDao
public interface SpLessonDao extends CrudDao<SpLesson> {
	
	//根据ID查询课程信息
	public SpLesson getLesson(String lessonId);
	
	//查询相关课程
	public List<SpLesson> selectRelatedLesson(String lessonId);
	
	public int physicalDelete(SpLesson lesson);
	
	//根据老师ID查询课程列表
	public List<SpLesson> selectLessonByTeacherId(String teacherId);
	
	//查询课程带讲义标题
	public List<SpLesson> findListAndHandouts(Map<String,Object> map);
	
	
	//查询相关课程
	public List<SpLesson> findListByCourseId(SpLesson lesson);
	
	//查询课程列表
	public List<SpLesson> selectLessonList();
	
	//查询套课关联的课程信息
	public List<SpLesson> selectCourseLesson(String courseId);
	
	//查询pad登录后获取的课程/路演
	public SpLesson getPadLoginLesson(Map<String,Object> map);
	
	//查询课程是否有关联的试卷
	public int checkExam(String lessonId);
}