/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.favor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.favor.dao.SpFavorDao;
import com.education.classroom.core.modules.spadmin.favor.entity.SpFavor;
import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 资源收藏Service
 * 
 * @author 边磊
 * @version 2016/08/10
 */
@Service
@Transactional(readOnly = true)
public class SpFavorService extends CrudService<SpFavorDao, SpFavor> {
	@Autowired
	private SpFavorDao spFavorDao;

	public SpFavor get(String id) {
		return super.get(id);
	}

	public List<SpFavor> findList(SpFavor spFavor) {
		return super.findList(spFavor);
	}

	public Page<SpResource> findPage(Page<SpResource> page,
			Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpResource> dicts = spFavorDao.findPageList(queryMap);
		PageInfo<SpResource> templatePage = new PageInfo<SpResource>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(SpFavor spFavor) {
		if (spFavor.getIsNewRecord()) {
			spFavor.preInsertNoId();
			spFavorDao.insert(spFavor);
		} else {
			super.save(spFavor);
		}
	}

	@Transactional(readOnly = false)
	public void delete(SpFavor spFavor) {
		super.delete(spFavor);
	}

}