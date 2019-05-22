/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.project.entity.SpProjectResource;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 项目资源管理DAO接口
 * @author 杨立明
 * @version 2016/08/30
 */
@MyBatisDao
public interface SpProjectResourceDao extends CrudDao<SpProjectResource> {
	
	public void deleteByProjectIds(@Param("projectIds") List<String> projectIds);
	
	void deleteById(String resourceId);

	void updateResource(Map<String, Object> resourceMap);
}