/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.category.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.category.dao.SpCategoryDao;
import com.education.classroom.core.modules.spadmin.category.entity.SpCategory;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 分类管理Service
 * @author shangjunwei  @version 2016-08-05
 */
@Service
@Transactional(readOnly = true)
public class SpCategoryService extends CrudService<SpCategoryDao, SpCategory> {

	@Autowired
	private SpCategoryDao spCategoryDao;
	
	public SpCategory get(String id) {
		return super.get(id);
	}
	
	public List<SpCategory> findList(SpCategory spCategory) {
		return super.findList(spCategory);
	}
	
	public Page<SpCategory> findPage(Page<SpCategory> page,Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpCategory> schems = spCategoryDao.findList(filters);
		PageInfo<SpCategory> resultPage = new PageInfo<SpCategory>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpCategory spCategory) {
		super.save(spCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpCategory spCategory) {
		super.delete(spCategory);
	}
	
	/**
	 * 课程类型全值-下拉框
	 * 2016年8月6日
	 * By zhujie
	 * @return
	 */
	public List<SpCategory> findAllListForDict(){
		return dao.findAllListForDict();
	}
}