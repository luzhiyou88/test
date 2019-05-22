/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.spclass.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.spclass.entity.SpClass;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 班级管理DAO接口
 * 
 * @author 边磊
 * @version 2016/08/05
 */
@MyBatisDao
public interface SpClassDao extends CrudDao<SpClass> {
	List<SpClass> findPageList(Map<String, Object> queryMap);

	Integer checkName(@Param(value = "id") String id,
			@Param(value = "name") String name);
}