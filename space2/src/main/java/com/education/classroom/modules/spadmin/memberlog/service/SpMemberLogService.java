/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.memberlog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.memberlog.dao.SpMemberLogDao;
import com.education.classroom.core.modules.spadmin.memberlog.entity.SpMemberLog;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;


/**
 * 会员记录管理Service
 * @author 赵新月
 * @version 2016/08/19
 */
@Service
@Transactional(readOnly = true)
public class SpMemberLogService extends CrudService<SpMemberLogDao, SpMemberLog> {

	@Autowired
	private SpMemberLogDao spMemberLogDao;
	
	public SpMemberLog get(String id) {
		return super.get(id);
	}
	
	public List<SpMemberLog> findList(SpMemberLog spMemberLog) {
		return super.findList(spMemberLog);
	}
	
	public Page<SpMemberLog> findPage(Page<SpMemberLog> page, Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpMemberLog> schems = spMemberLogDao.findPageList(queryMap);
		PageInfo<SpMemberLog> resultPage = new PageInfo<SpMemberLog>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpMemberLog spMemberLog) {
		super.save(spMemberLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpMemberLog spMemberLog) {
		super.delete(spMemberLog);
	}
	
}