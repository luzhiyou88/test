/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.common.util.UserTypeConstants;
import com.education.classroom.core.modules.spadmin.user.dao.SpUserDao;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.StringUtils;

/**
 * 用户管理Service
 * @author 边磊
 * @version 2016/08/06
 */
@Service
@Transactional(readOnly = true)
public class SpUserService extends CrudService<SpUserDao, SpUser> {
	
	@Autowired
	private SpUserDao spUserDao;

	public SpUser get(String id) {
		return super.get(id);
	}
	
	public List<SpUser> findList(SpUser spUser) {
		return super.findList(spUser);
	}
	
	public Page<SpUser> findPage(Page<SpUser> page, SpUser spUser) {
		return null;//super.findPage(page, spUser);
	}
	
	public Page<SpUser> findPage(Page<SpUser> page, Map<String, Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpUser> dataList = spUserDao.findList(filters);
		PageInfo<SpUser> dataPage = new PageInfo<SpUser>(dataList);
		PageUtil.convertPage(dataPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpUser spUser) {
		super.save(spUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpUser spUser) {
		super.delete(spUser);
	}
	@Transactional(readOnly = false)
	public void revert(SpUser spUser) {
		spUserDao.revert(spUser);
	}
	
	public List<SpUser> findTeacherList() {
		SpUser spUser = new SpUser();
		spUser.setUserType(UserTypeConstants.TEACHER_USER);
		return super.findList(spUser);
	}
	public List<SpUser> findTeacherList(String spaceId) {
		SpUser spUser = new SpUser();
		spUser.setUserType(UserTypeConstants.TEACHER_USER);
		if(StringUtils.isNotEmpty(spaceId)){
			spUser.setSpaceId(spaceId);
		}
		return super.findList(spUser);
	}
	public List<SpUser> findEntrepreneurList() {
		SpUser spUser = new SpUser();
		spUser.setUserType(UserTypeConstants.CREATOR_USER);
		return super.findList(spUser);
	}
	public List<SpUser> findEntrepreneurList(String spaceId) {
		SpUser spUser = new SpUser();
		spUser.setUserType(UserTypeConstants.CREATOR_USER);
		if(StringUtils.isNotEmpty(spaceId)){
			spUser.setSpaceId(spaceId);
		}
		return super.findList(spUser);
	}
	
}