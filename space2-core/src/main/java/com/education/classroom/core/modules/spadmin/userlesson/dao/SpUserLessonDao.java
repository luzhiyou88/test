package com.education.classroom.core.modules.spadmin.userlesson.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.userlesson.entity.SpUserLesson;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;


/**
 * 用户课程关联增删该查DAO接口
 * @author 赵新月
 * @version 2016/08/19
 */
@MyBatisDao
public interface SpUserLessonDao extends CrudDao<SpUserLesson> {
	public List<SpUserLesson> findPageList(Map<String, Object> queryMap);
}