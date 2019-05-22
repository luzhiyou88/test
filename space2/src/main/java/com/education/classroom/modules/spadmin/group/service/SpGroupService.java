/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.group.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.constants.UserGroupState;
import com.education.classroom.core.modules.spadmin.group.dao.SpGroupDao;
import com.education.classroom.core.modules.spadmin.group.entity.SpGroup;
import com.education.classroom.core.modules.spadmin.usergroup.dao.SpUserGroupDao;
import com.education.classroom.core.modules.spadmin.usergroup.entity.SpUserGroup;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.IdGen;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 小组管理Service
 * 
 * @author 边磊
 * @version 2016/08/06
 */
@Service
@Transactional(readOnly = true)
public class SpGroupService extends CrudService<SpGroupDao, SpGroup> {
	@Autowired
	private SpGroupDao spGroupDao;
	@Autowired
	private SpUserGroupDao spUserGroupDao;

	public SpGroup get(String id) {
		return super.get(id);
	}

	public Integer checkGroupName(String id, String name) {
		return spGroupDao.checkGroupName(id, name);
	}

	public List<SpGroup> findList(SpGroup spGroup) {
		return super.findList(spGroup);
	}

	public Page<SpGroup> findPage(Page<SpGroup> page,
			Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpGroup> dicts = spGroupDao.findPageList(queryMap);
		PageInfo<SpGroup> templatePage = new PageInfo<SpGroup>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(SpGroup spGroup) {
		if (!StringUtils.isEmpty(spGroup.getId())) {
			SpGroup oldSpGroup = spGroupDao.get(spGroup.getId());

			if(oldSpGroup != null){
				spUserGroupDao.delLeader(oldSpGroup.getId(), oldSpGroup.getLeaderId());
			}
			
		}
		spGroup.setSpaceId(SpaceUtils.get("SpaceId"));
		super.save(spGroup);
		SpUserGroup spUserGroup = new SpUserGroup();
		spUserGroup.setUserId(spGroup.getLeaderId());
		spUserGroup.setGroupId(spGroup.getId());
		spUserGroup.setId(IdGen.uuid());
		spUserGroup.setCreateDate(spGroup.getCreateDate());
		spUserGroup.setCreateBy(spGroup.getCreateBy());
		spUserGroup.setUpdateDate(spGroup.getCreateDate());
		spUserGroup.setUpdateBy(spGroup.getCreateBy());
		spUserGroup.setState(UserGroupState.STATE_JOINED);
		spUserGroupDao.insert(spUserGroup);
	}

	@Transactional(readOnly = false)
	public void savePlatGroup(SpGroup spGroup) {
		super.save(spGroup);
	}

	@Transactional(readOnly = false)
	public void delete(SpGroup spGroup) {
		super.delete(spGroup);
	}
}