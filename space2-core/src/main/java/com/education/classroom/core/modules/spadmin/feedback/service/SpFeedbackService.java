/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.feedback.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.feedback.dao.SpFeedbackDao;
import com.education.classroom.core.modules.spadmin.feedback.entity.SpFeedback;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 用户反馈Service
 * @author 尚军伟
 * @version 2016/08/15
 */
@Service
@Transactional(readOnly = true)
public class SpFeedbackService extends CrudService<SpFeedbackDao, SpFeedback> {

	@Autowired
	private SpFeedbackDao feedbackDao;
	public SpFeedback get(String id) {
		return super.get(id);
	}
	
	public List<SpFeedback> findList(SpFeedback spFeedback) {
		return super.findList(spFeedback);
	}
	
	public Page<SpFeedback> findPage(Page<SpFeedback> page,Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpFeedback> schems = feedbackDao.findList(filters);
		PageInfo<SpFeedback> resultPage = new PageInfo<SpFeedback>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpFeedback spFeedback) {
		super.save(spFeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpFeedback spFeedback) {
		super.delete(spFeedback);
	}
	
}