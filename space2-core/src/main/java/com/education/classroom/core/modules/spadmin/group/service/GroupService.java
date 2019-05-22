/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.group.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.enums.DelFlagType;
import com.education.classroom.core.common.enums.UserGroupState;
import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.group.dao.GroupDao;
import com.education.classroom.core.modules.spadmin.group.entity.GroupTopic;
import com.education.classroom.core.modules.spadmin.group.entity.UserGroup;
import com.education.classroom.core.modules.spadmin.group.entity.UserSpGroup;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 小组管理Service
 * 
 * @author 边磊
 * @version 2016/08/12
 */
@Service
@Transactional(readOnly = true)
public class GroupService extends CrudService<GroupDao, UserSpGroup> {
	@Autowired
	private GroupDao groupDao;

	public UserSpGroup get(String id) {
		return super.get(id);
	}

	public UserSpGroup get(String id, String userId) {
		return groupDao.get(id, userId);
	}

	public UserGroup getUserGroup(String id) {
		return groupDao.getUserGroup(id);
	}

	public List<UserSpGroup> findList(UserSpGroup userSpGroup) {
		return super.findList(userSpGroup);
	}

	public List<UserSpGroup> findList(Map<String, Object> queryMap) {
		return groupDao.findList(queryMap);
	}

	public List<UserSpGroup> pageForList(Page<UserSpGroup> page,
			Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<UserSpGroup> dicts = groupDao.pageForList(queryMap);
		PageInfo<UserSpGroup> templatePage = new PageInfo<UserSpGroup>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page.getList();

	}

	@Transactional(readOnly = false)
	public void joinGroup(String groupId, String userId, String id) {
		Map<String, Object> jMap = new HashMap<String, Object>();
		jMap.put("groupId", groupId);
		jMap.put("userId", userId);
		try {
			Map<String, Object> dMap = new HashMap<String, Object>();
			dMap.put("groupId", groupId);
			dMap.put("userId", userId);
			dMap.put("DEL_FLAG", DelFlagType.DELETE.getValue());// 退出
			groupDao.updGrouper(dMap);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("删除干扰因素失败");
		}
		jMap.put("state", UserGroupState.SHENQING.getValue());// 申请加入小组
		jMap.put("id", id);
		groupDao.joinGroup(jMap);
	}

	@Transactional(readOnly = false)
	public void outGroup(String groupId, String userId) {
		Map<String, Object> oMap = new HashMap<String, Object>();
		oMap.put("groupId", groupId);
		oMap.put("userId", userId);
		oMap.put("state", UserGroupState.QUIT.getValue());// 退出
		oMap.put("DEL_FLAG", DelFlagType.DELETE.getValue());// 退出

		groupDao.updGrouper(oMap);
	}

	@Transactional(readOnly = false)
	public void auditGrouper(String userGroupId, Boolean flag) {
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("userGroupId", userGroupId);
		if (flag) {
			aMap.put("state", UserGroupState.JOINING.getValue());// 加入
		} else {
			aMap.put("state", UserGroupState.JJSHENQING.getValue());// 拒绝
			aMap.put("DEL_FLAG", DelFlagType.DELETE.getValue());// 退出

		}
		groupDao.updGrouper(aMap);
	}

	public List<SpUser> getGrouperList(String groupId) {
		return groupDao.getGrouperList(groupId);
	}

	@Transactional(readOnly = false)
	public void delGroup(String groupId, String userId) {
		Map<String, Object> dMap = new HashMap<String, Object>();
		dMap.put("groupId", groupId);
		dMap.put("userId", userId);
		dMap.put("state", UserGroupState.DEL.getValue());// 删除
		dMap.put("DEL_FLAG", DelFlagType.DELETE.getValue());// 退出
		groupDao.updGrouper(dMap);
	}

	public List<UserGroup> findPreGrouperList(String groupId) {
		return groupDao.findPreGrouperList(groupId);
	}

	public Page<GroupTopic> findGroupTopicList(Page<GroupTopic> page,
			Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<GroupTopic> dicts = groupDao.findGroupTopicList(queryMap);
		PageInfo<GroupTopic> templatePage = new PageInfo<GroupTopic>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page;

	}
}