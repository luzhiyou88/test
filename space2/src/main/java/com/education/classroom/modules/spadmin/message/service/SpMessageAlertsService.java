/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.message.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.message.dao.SpMessageAlertsDao;
import com.education.classroom.core.modules.spadmin.message.entity.SpMessageAlerts;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 公告管理Service
 * @author 尚军伟
 * @version 2016/08/06
 */
@Service
@Transactional(readOnly = true)
public class SpMessageAlertsService extends CrudService<SpMessageAlertsDao, SpMessageAlerts> {

	@Autowired
	private SpMessageAlertsDao messageDao;
	
	public SpMessageAlerts get(String id) {
		return super.get(id);
	}
	
	public List<SpMessageAlerts> findList(SpMessageAlerts spMessageAlerts) {
		return super.findList(spMessageAlerts);
	}
	
	public Page<SpMessageAlerts> findPage(Page<SpMessageAlerts> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpMessageAlerts> schems = messageDao.findList(filters);
		PageInfo<SpMessageAlerts> resultPage = new PageInfo<SpMessageAlerts>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpMessageAlerts spMessageAlerts) {
		super.save(spMessageAlerts);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpMessageAlerts spMessageAlerts) {
		super.delete(spMessageAlerts);
	}
	
}