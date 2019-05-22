/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.resource.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.resource.dao.SpResourceDao;
import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 资料库管理Service
 * @author 杨立明
 * @version 2016-08-05
 */
@Service
@Transactional(readOnly = true)
public class SpResourceService extends CrudService<SpResourceDao, SpResource> {

	@Autowired
	private SpResourceDao spResourceDao;
	
	public SpResource get(String id) {
		return super.get(id);
	}
	
	public List<SpResource> findList(SpResource spResource) {
		return super.findList(spResource);
	}
	
	public Page<SpResource> findPage(Page<SpResource> page, Map<String, Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpResource> dataList = spResourceDao.findList(filters);
		PageInfo<SpResource> dataPage = new PageInfo<SpResource>(dataList);
		PageUtil.convertPage(dataPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpResource spResource) {
		super.save(spResource);
	}
	@Transactional(readOnly = false)
	public void insert(SpResource spResource) {
		spResourceDao.insert(spResource);
	}
	@Transactional(readOnly = false)
	public void update(SpResource spResource) {
		spResourceDao.update(spResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpResource spResource) {
		super.delete(spResource);
	}
	
	@Transactional(readOnly = false)
	public void updatePublishState(SpResource spResource) {
		spResourceDao.updatePublishState(spResource);
	}

	public String getPublishState(String resourceId) {
		return spResourceDao.getPublishState(resourceId);
	}

	@Transactional(readOnly = false)
	public void saveVideoImg(Map<String, String> paraMap) {
		spResourceDao.saveVideoImg(paraMap);
	}
	
}