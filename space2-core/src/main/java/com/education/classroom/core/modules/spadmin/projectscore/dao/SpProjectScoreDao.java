/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.projectscore.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.projectscore.entity.SpProjectScore;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 项目评分DAO接口
 * 
 * @author 边磊
 * @version 2016/08/29
 */
@MyBatisDao
public interface SpProjectScoreDao extends CrudDao<SpProjectScore> {

	public List<Map<String, Object>> findScore(
			@Param(value = "projectId") String projectId);

}