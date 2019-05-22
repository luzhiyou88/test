/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.group.entity.GroupTopic;
import com.education.classroom.core.modules.spadmin.group.entity.UserGroup;
import com.education.classroom.core.modules.spadmin.group.entity.UserSpGroup;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 小组管理DAO接口
 * 
 * @author 边磊
 * @version 2016/08/11
 */
@MyBatisDao
public interface GroupDao extends CrudDao<UserSpGroup> {
	void joinGroup(Map<String, Object> jMap);

	void updGrouper(Map<String, Object> adMap);

	UserSpGroup get(@Param(value = "id") String id,
			@Param(value = "userId") String userId);

	List<SpUser> getGrouperList(@Param(value = "groupId") String groupId);

	UserGroup getUserGroup(String userGroupId);

	List<UserGroup> findPreGrouperList(@Param(value = "groupId") String groupId);

	List<GroupTopic> findGroupTopicList(Map<String, Object> queryMap);

	List<UserSpGroup> pageForList(Map<String, Object> params);

}