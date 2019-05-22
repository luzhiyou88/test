package com.education.classroom.core.modules.spadmin.handouts.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.handouts.entity.SpHandouts;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 讲义DAO接口
 * @author zhangyongsheng
 * @version 2016/08/10
 */
@MyBatisDao
public interface SpHandoutsDao extends CrudDao<SpHandouts> {
	
	public List<SpHandouts> findListByMap(Map<String,Object> params);
	
	public SpHandouts getByLessonId(String lessonId);
	
	//通过课程ID查询讲义标题
	public String selectTitleByLessonId(String lessonId);
	
	//删除课程关联的讲义
	public void deleteHandoutsByLessonId(String lessonId);
	
	//通过课程ID查询讲义
	public List<SpHandouts> selectHandoutsByLessonId(String lessonId);
	
}