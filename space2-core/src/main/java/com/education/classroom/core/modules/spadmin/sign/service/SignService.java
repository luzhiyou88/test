package com.education.classroom.core.modules.spadmin.sign.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.sign.dao.SignDao;
import com.education.classroom.core.modules.spadmin.sign.entity.UserSign;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 签到记录 Service
 * 
 * @author 边磊
 * @version 2016/09/06
 */
@Service
@Transactional(readOnly = true)
public class SignService extends CrudService<SignDao, UserSign> {
	@Autowired
	private SignDao signDao;

	public Page<UserSign> findPage(Page<UserSign> page,
			Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<UserSign> dicts = signDao.findPageList(queryMap);
		PageInfo<UserSign> templatePage = new PageInfo<UserSign>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page;
	}

}
