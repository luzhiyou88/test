/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.sign.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.sign.dao.SpSignDao;
import com.education.classroom.core.modules.spadmin.sign.entity.SpSign;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 签到Service
 * 
 * @author 边磊
 * @version 2016/09/09
 */
@Service
@Transactional(readOnly = true)
public class SpSignService extends CrudService<SpSignDao, SpSign> {
	@Autowired
	private SpSignDao spSignDao;

	public SpSign get(String id) {
		return super.get(id);
	}

	public SpSign getSign(Map<String, Object> queryMap) {
		return spSignDao.getSign(queryMap);
	}

	public SpSign getSign(String userId, String lessonId) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("lessonId",lessonId);
		queryMap.put("userId",userId);
		return spSignDao.getSign(queryMap);
	}

	public List<SpSign> findList(SpSign spSign) {
		return super.findList(spSign);
	}

	public Page<SpSign> findPage(Page<SpSign> page, Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpSign> dicts = spSignDao.findPageList(queryMap);
		PageInfo<SpSign> templatePage = new PageInfo<SpSign>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(SpSign spSign) {
		super.save(spSign);
	}

	@Transactional(readOnly = false)
	public void delete(SpSign spSign) {
		super.delete(spSign);
	}

}