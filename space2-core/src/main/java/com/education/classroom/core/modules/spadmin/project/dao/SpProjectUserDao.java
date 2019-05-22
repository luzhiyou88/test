/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.project.entity.SpProjectUser;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 项目成员管理DAO接口
 * @Class Name SpProjectUserDao
 * @author zhujie
 * @Create In 2016年8月31日
 */
@MyBatisDao
public interface SpProjectUserDao extends CrudDao<SpProjectUser> {
	
	public void deleteByProjectIds(@Param("projectIds") List<String> projectIds);
}