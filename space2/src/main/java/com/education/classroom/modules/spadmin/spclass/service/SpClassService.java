/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.spclass.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.constants.UserGroupState;
import com.education.classroom.core.modules.spadmin.classgroup.dao.SpClassGroupDao;
import com.education.classroom.core.modules.spadmin.classgroup.entity.SpClassGroup;
import com.education.classroom.core.modules.spadmin.group.dao.SpGroupDao;
import com.education.classroom.core.modules.spadmin.group.entity.SpGroup;
import com.education.classroom.core.modules.spadmin.spclass.dao.SpClassDao;
import com.education.classroom.core.modules.spadmin.spclass.entity.SpClass;
import com.education.classroom.core.modules.spadmin.usergroup.dao.SpUserGroupDao;
import com.education.classroom.core.modules.spadmin.usergroup.entity.SpUserGroup;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.IdGen;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 班级管理Service
 * 
 * @author 边磊
 * @version 2016/08/05
 */
@Service
@Transactional(readOnly = true)
public class SpClassService extends CrudService<SpClassDao, SpClass> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SpClassDao spClassDao;
	@Autowired
	private SpUserGroupDao spUserGroupDao;
	@Autowired
	private SpGroupDao spGroupDao;
	@Autowired
	private SpClassGroupDao SpClassGroupDao;

	public SpClass get(String id) {
		return super.get(id);
	}

	public Integer checkName(String id, String name) {
		return spClassDao.checkName(id, name);
	}

	public List<SpClass> findList(Map<String, Object> filters) {
		return spClassDao.findPageList(filters);
	}

	public Page<SpClass> findPage(Page<SpClass> page,
			Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpClass> dicts = spClassDao.findPageList(queryMap);
		PageInfo<SpClass> templatePage = new PageInfo<SpClass>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(SpClass spClass) {
		boolean flag = spClass.getIsNewRecord();
		spClass.setSpaceId(SpaceUtils.get("SpaceId"));
		SpGroup spGroup = new SpGroup();
		super.save(spClass);
		try {
			if (flag) {
				String spId = IdGen.uuid();
				spGroup.setCreateBy(spClass.getCreateBy());
				spGroup.setCreateDate(spClass.getCreateDate());
				spGroup.setGroupType("0");
				spGroup.setId(spId);
				spGroup.setRemarks(spClass.getRemarks());
				spGroup.setLeaderId(spClass.getTeacherId());
				spGroup.setSpaceId(SpaceUtils.get("SpaceId"));
				spGroup.setUpdateBy(spClass.getCreateBy());
				spGroup.setUpdateDate(spClass.getCreateDate());
				spGroup.setName(spClass.getName());
				spGroup.setPublishState("0");
				spGroupDao.insert(spGroup);
				SpClassGroup cg = new SpClassGroup();
				cg.setId(IdGen.uuid());
				cg.setClassId(spClass.getId());
				cg.setGroupId(spId);
				SpClassGroupDao.insert(cg);
				SpUserGroup  spUserGroup=new SpUserGroup();
				spUserGroup.setUserId(spClass.getTeacherId());
				spUserGroup.setGroupId(spId);
				spUserGroup.setId(IdGen.uuid());
				spUserGroup.setCreateDate(spClass.getCreateDate());
				spUserGroup.setCreateBy(spClass.getCreateBy());
				spUserGroup.setUpdateDate(spClass.getCreateDate());
				spUserGroup.setUpdateBy(spClass.getCreateBy());
				spUserGroup.setState(UserGroupState.STATE_JOINED);
				spUserGroupDao.insert(spUserGroup);
			}
		} catch (Exception e) {
			logger.error("保存" + spClass.getName() + "默认组失败", e);
		}
	}

	@Transactional(readOnly = false)
	public void delete(SpClass spClass) {
		super.delete(spClass);
	}
}