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
import com.education.classroom.core.modules.spadmin.answer.dao.SpAnswerScoreDao;
import com.education.classroom.core.modules.spadmin.answer.entity.SpAnswerScore;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 答案Service
 * 
 * @author 路志友
 * @version 2016/08/18
 */
@Service
@Transactional(readOnly = true)
public class SpAnswerScoreService extends
		CrudService<SpAnswerScoreDao, SpAnswerScore> {

	@Autowired
	private SpAnswerScoreDao spAnswerScoreDao;

	public SpAnswerScore get(String id) {
		return super.get(id);
	}

	public List<SpAnswerScore> findList(SpAnswerScore spAnswerScore) {
		return super.findList(spAnswerScore);
	}

	public List<SpAnswerScore> findList(Map<String, Object> queryMap) {
		return spAnswerScoreDao.findList(queryMap);
	}

	public Page<SpAnswerScore> findPage(Page<SpAnswerScore> page,
			Map<String, Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpAnswerScore> schems = spAnswerScoreDao.findList(filters);
		PageInfo<SpAnswerScore> resultPage = new PageInfo<SpAnswerScore>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(SpAnswerScore spAnswerScore) {
		super.save(spAnswerScore);
	}

	@Transactional(readOnly = false)
	public void delete(SpAnswerScore spAnswerScore) {
		super.delete(spAnswerScore);
	}

}