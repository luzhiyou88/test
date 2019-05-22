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
import com.education.classroom.core.modules.spadmin.member.dao.SpMemberPostageDao;
import com.education.classroom.core.modules.spadmin.member.entity.SpMemberPostage;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 会员资费管理Service
 * @author 尚军伟
 * @version 2016/08/06
 */
@Service
@Transactional(readOnly = true)
public class SpMemberPostageService extends CrudService<SpMemberPostageDao, SpMemberPostage> {

	@Autowired
	private SpMemberPostageDao memberDao;
	
	public SpMemberPostage get(String id) {
		return super.get(id);
	}
	
	public List<SpMemberPostage> findList(SpMemberPostage spMemberPostage) {
		return super.findList(spMemberPostage);
	}
	
	public Page<SpMemberPostage> findPage(Page<SpMemberPostage> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpMemberPostage> schems = memberDao.findList(filters);
		PageInfo<SpMemberPostage> resultPage = new PageInfo<SpMemberPostage>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpMemberPostage spMemberPostage) {
		super.save(spMemberPostage);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpMemberPostage spMemberPostage) {
		super.delete(spMemberPostage);
	}
	
	//资费名称唯一性验证
	public int selectByName(String name){
		return memberDao.selectByName(name);
	}
	
}