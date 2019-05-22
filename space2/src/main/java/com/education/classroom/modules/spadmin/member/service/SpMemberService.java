/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.member.dao.SpMemberDao;
import com.education.classroom.core.modules.spadmin.member.entity.SpMember;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 会员管理Service
 * @author 杨立明
 * @version 2016/08/19
 */
@Service
@Transactional(readOnly = true)
public class SpMemberService extends CrudService<SpMemberDao, SpMember> {
	
	@Autowired
	private SpMemberDao spMemberDao;

	public SpMember get(String id) {
		return super.get(id);
	}
	
	public List<SpMember> findList(SpMember spMember) {
		return super.findList(spMember);
	}
	
	public Page<SpMember> findPage(Page<SpMember> page, Map<String, Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpMember> dataList = spMemberDao.findList(filters);
		PageInfo<SpMember> dataPage = new PageInfo<SpMember>(dataList);
		PageUtil.convertPage(dataPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpMember spMember) {
		super.save(spMember);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpMember spMember) {
		super.delete(spMember);
	}
	
}