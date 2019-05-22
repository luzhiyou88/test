/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.specialty.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.specialty.dao.SpSpecialtyDao;
import com.education.classroom.core.modules.spadmin.specialty.entity.SpSpecialty;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 专业管理Service
 * 
 * @author 边磊
 * @version 2016-08-05
 */
@Service
@Transactional(readOnly = true)
public class SpSpecialtyService extends
		CrudService<SpSpecialtyDao, SpSpecialty> {
	@Autowired
	private SpSpecialtyDao spSpecialtyDao;

	public SpSpecialty get(String id) {
		return super.get(id);
	}

	public Integer checkSpecialtyName(String id, String name) {
		return spSpecialtyDao.checkSpecialtyName(id, name);
	}

	public List<SpSpecialty> findList(Map<String, Object> queryMap) {
		return spSpecialtyDao.findPageList(queryMap);
	}

	public Page<SpSpecialty> findPage(Page<SpSpecialty> page,
			Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpSpecialty> dicts = spSpecialtyDao.findPageList(queryMap);
		PageInfo<SpSpecialty> templatePage = new PageInfo<SpSpecialty>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(SpSpecialty spSpecialty) {
		spSpecialty.setSpaceId(SpaceUtils.get("SpaceId"));
		super.save(spSpecialty);
	}

	@Transactional(readOnly = false)
	public void delete(SpSpecialty spSpecialty) {
		super.delete(spSpecialty);
	}
}