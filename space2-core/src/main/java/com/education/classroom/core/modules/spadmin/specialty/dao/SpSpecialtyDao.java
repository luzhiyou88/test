/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.specialty.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.specialty.entity.SpSpecialty;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 专业管理DAO接口
 * 
 * @author 边磊
 * @version 2016-08-05
 */
@MyBatisDao
public interface SpSpecialtyDao extends CrudDao<SpSpecialty> {
	
    List<SpSpecialty> findPageList(Map<String, Object> queryMap);
	Integer checkSpecialtyName(@Param(value = "id") String id,
			@Param(value = "name") String name);
}