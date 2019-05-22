/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.userlesson.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.userlesson.dao.SpUserLessonDao;
import com.education.classroom.core.modules.spadmin.userlesson.entity.SpUserLesson;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;


/**
 * 用户课程关联增删该查Service
 * @author 赵新月
 * @version 2016/08/19
 */
@Service
@Transactional(readOnly = true)
public class SpUserLessonService extends CrudService<SpUserLessonDao, SpUserLesson> {
	@Autowired SpUserLessonDao spUserLessonDao;
	
	
	public SpUserLesson get(String id) {
		return super.get(id);
	}
	
	public List<SpUserLesson> findList(SpUserLesson spUserLesson) {
		return super.findList(spUserLesson);
	}
	
	public Page<SpUserLesson> findPage(Page<SpUserLesson> page, Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpUserLesson> schems = spUserLessonDao.findPageList(queryMap);
		PageInfo<SpUserLesson> resultPage = new PageInfo<SpUserLesson>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpUserLesson spUserLesson) {
		super.save(spUserLesson);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpUserLesson spUserLesson) {
		super.delete(spUserLesson);
	}
	
}