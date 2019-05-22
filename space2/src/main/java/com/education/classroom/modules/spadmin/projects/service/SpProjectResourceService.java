/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.projects.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.project.dao.SpProjectResourceDao;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectResource;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
/**
 * 项目资源管理Service
 * @author 杨立明
 * @version 2016/08/30
 */
@Service
@Transactional(readOnly = true)
public class SpProjectResourceService extends CrudService<SpProjectResourceDao, SpProjectResource> {
	@Autowired
	private SpProjectResourceDao spProjectResourceDao;

	public SpProjectResource get(String id) {
		return super.get(id);
	}
	
	public List<SpProjectResource> findList(SpProjectResource spProjectResource) {
		return super.findList(spProjectResource);
	}
	
	public Page<SpProjectResource> findPage(Page<SpProjectResource> page, Map<String, Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpProjectResource> dataList = spProjectResourceDao.findList(filters);
		PageInfo<SpProjectResource> dataPage = new PageInfo<SpProjectResource>(dataList);
		PageUtil.convertPage(dataPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpProjectResource spProjectResource) {
		super.save(spProjectResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpProjectResource spProjectResource) {
		super.delete(spProjectResource);
	}
	
	public List<SpProjectResource> findAllListByProjectIds(List<String> projectIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("projectIds", projectIds);
		return dao.findAllList(param);
	}
	
	//根据主键id删除项目资料
	@Transactional(readOnly = false)
	public void deleteById(String resourceId) {
		spProjectResourceDao.deleteById(resourceId);
	}
	//添加项目成功后，修改项目资料里面的projectId，使之联系起来
	@Transactional(readOnly = false)
	public void updateResource(Map<String, Object> resourceMap) {
		spProjectResourceDao.updateResource(resourceMap);
	}
	//根据projectId查询出对应的所有项目资料
	public List<SpProjectResource> getByProjectId(String projectId) {
		Map<String, Object> filters=new HashMap<String, Object>();
		filters.put("projectId", projectId);
		filters.put("delFlag", "0");
		List<SpProjectResource> dataList = spProjectResourceDao.findList(filters);
		return dataList;
	}
	
}