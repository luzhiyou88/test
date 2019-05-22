
package com.education.classroom.core.modules.spadmin.course.dao;

import java.util.List;

import com.education.classroom.core.modules.spadmin.course.entity.SpCourse;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 套课管理DAO接口
 * @author 朱杰
 * @version 2016/08/06
 */
@MyBatisDao
public interface SpCourseDao extends CrudDao<SpCourse> {
	/**
	 * 获取套课下拉框绑定项
	 * 2016年8月7日
	 * By zhujie
	 * @return
	 */
	public List<SpCourse> getAllListForDict(SpCourse param);
	
	/**
	 * 更新套课课程数量
	 * 2016年8月9日
	 * By zhujie
	 * @param lessonId
	 * @return
	 */
	public int updateLessonNum(String id);
	
	/**
	 * 
	 * 2016年8月7日
	 * By zhujie
	 * @return
	 */
	public List<SpCourse> findListByCourseId(SpCourse param);
	
	public int getPublishedLessonNum(String courseId);
	
	public int getSubscribedLessonCount(String courseId);
}