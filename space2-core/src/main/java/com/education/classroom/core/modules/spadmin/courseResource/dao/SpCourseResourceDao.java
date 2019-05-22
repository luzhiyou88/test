
package com.education.classroom.core.modules.spadmin.courseResource.dao;

import java.util.List;

import com.education.classroom.core.modules.spadmin.courseResource.entity.SpCourseResource;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 套课资料管理DAO
 * @Class Name SpCourseResourceDao
 * @author zhujie
 * @Create In 2016年8月10日
 */
@MyBatisDao
public interface SpCourseResourceDao extends CrudDao<SpCourseResource> {
	
	public List<SpCourseResource> getByCourseId(String courseId);
	
	public int physicalDelete(SpCourseResource spCourseResource);
	
}