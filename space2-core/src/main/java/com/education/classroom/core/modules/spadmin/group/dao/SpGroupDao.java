/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.group.entity.SpGroup;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 小组管理DAO接口
 * 
 * @author 边磊
 * @version 2016/08/06
 */
@MyBatisDao
public interface SpGroupDao extends CrudDao<SpGroup> {
	List<SpGroup> findPageList(Map<String, Object> queryMap);

	List<SpGroup> findCenterList(Map<String, Object> queryMap);

	String checkGroup(@Param(value = "groupId") String groupId);

	Integer checkGroupName(@Param(value = "id") String id,
			@Param(value = "name") String name);
}