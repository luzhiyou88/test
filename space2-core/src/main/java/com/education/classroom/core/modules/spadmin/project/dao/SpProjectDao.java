/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.project.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.project.entity.SpPadProject;
import com.education.classroom.core.modules.spadmin.project.entity.SpProject;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 项目管理DAO接口
 * @author 杨立明
 * @version 2016/08/26
 */
@MyBatisDao
public interface SpProjectDao extends CrudDao<SpProject> {
	//根据课程id删除项目
	public void deleteByLessonId(String lessonId);
	
	public List<SpPadProject> findAllListByPad(Map<String,Object> map);

	public int hasProject(SpProject spProject);

	public Map<String, Object> getRoadshowById(String lessonId);

	public List<Map<String,Object>> getProjectScoreById(String projectId);
	
}