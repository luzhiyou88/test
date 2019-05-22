/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.answer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.answer.dao.SpAnswerDao;
import com.education.classroom.core.modules.spadmin.answer.entity.SpAnswer;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 答案Service
 * @author 路志友
 * @version 2016/08/18
 */
@Service
@Transactional(readOnly = true)
public class SpAnswerService extends CrudService<SpAnswerDao, SpAnswer> {
    
	@Autowired
	private SpAnswerDao spAnswerDao;
	
	public SpAnswer get(String id) {
		return super.get(id);
	}
	
	public List<SpAnswer> findList(SpAnswer spAnswer) {
		return super.findList(spAnswer);
	}
	
	
	
	public Page<SpAnswer> findPage(Page<SpAnswer> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpAnswer> schems = spAnswerDao.findList(filters);
		PageInfo<SpAnswer> resultPage = new PageInfo<SpAnswer>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpAnswer spAnswer) {
		super.save(spAnswer);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpAnswer spAnswer) {
		super.delete(spAnswer);
	}
	
}