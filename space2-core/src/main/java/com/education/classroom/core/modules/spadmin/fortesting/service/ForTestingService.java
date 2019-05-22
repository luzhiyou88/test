/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.fortesting.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.fortesting.dao.ForTestingDao;
import com.education.classroom.core.modules.spadmin.fortesting.entity.ForTesting;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.IdGen;

/**
 * 我的测试管理Service
 * 
 * @author 边磊
 * @version 2016/08/19
 */
@Service
@Transactional(readOnly = true)
public class ForTestingService extends CrudService<ForTestingDao, ForTesting> {
	@Autowired
	private ForTestingDao forTestingDao;

	public Integer getAnswer(Map<String, Object> queryMap) {
		return forTestingDao.getAnswer(queryMap);
	}

	@Transactional(readOnly = false)
	public void saveAnswer(Map<String, Object> queryMap) {
		queryMap.put("id", IdGen.uuid());
		forTestingDao.saveAnswer(queryMap);
	}

	@Transactional(readOnly = false)
	public void updateAnswer(Map<String, Object> queryMap) {
		forTestingDao.updateAnswer(queryMap);
	}

	public Page<ForTesting> findForTestingList(Page<ForTesting> page,
			Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<ForTesting> dicts = forTestingDao.findForTestingList(queryMap);
		PageInfo<ForTesting> templatePage = new PageInfo<ForTesting>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page;

	}
}