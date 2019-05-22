package com.education.classroom.core.modules.spadmin.usergroup.dao;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.usergroup.entity.SpUserGroup;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 用户小组关系DAO接口
 * 
 * @author zhangyongsheng
 * @version 2016/08/11
 */
@MyBatisDao
public interface SpUserGroupDao extends CrudDao<SpUserGroup> {

	void delLeader(@Param(value = "groupId") String groupId,
			@Param(value = "userId") String userId);
}