/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.projects.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.project.dao.SpProjectUserDao;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectUser;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.StringUtils;

/**
 * 项目成员管理Service
 * @Class Name SpProjectUserService
 * @author zhujie
 * @Create In 2016年8月31日
 */
@Service
@Transactional(readOnly = true)
public class SpProjectUserService extends CrudService<SpProjectUserDao, SpProjectUser> {
	@Autowired
	private SpProjectUserDao spProjectUserDao;

	public SpProjectUser get(String id) {
		return super.get(id);
	}
	
	public Page<SpProjectUser> findPage(Page<SpProjectUser> page, Map<String, Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpProjectUser> dataList = spProjectUserDao.findList(filters);
		PageInfo<SpProjectUser> dataPage = new PageInfo<SpProjectUser>(dataList);
		PageUtil.convertPage(dataPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpProjectUser spProjectUser) {
		super.save(spProjectUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpProjectUser spProjectUser) {
		super.delete(spProjectUser);
	}
	
	public List<SpProjectUser> findAllByProjectIds(List<String> projectIds){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("projectIds", projectIds);
		return dao.findAllList(param);
	}
	
	public List<String> getUsernameListByProjectId(String projectId) {
		List<String> usernameList=new ArrayList<String>();
		Page<SpProjectUser> page=new Page<SpProjectUser>();
		page.setPageSize(9999);//设置大一点
		page.setPageNo(1);
		Map<String, Object> filters=new HashMap<String, Object>();
		filters.put("projectId", projectId);
		page=this.findPage(page, filters);
		List<SpProjectUser> list = page.getList();
		if(list!=null){
			for(int i=0,len=list.size();i<len;i++){
				SpProjectUser one=list.get(i);
				String username=one.getUserName();
				if(StringUtils.isNotBlank(username)){
					usernameList.add(username);
				}
			}
		}
		return usernameList;
	}
	
	//根据projectId查询出对应的所有成员
	public List<SpProjectUser> getMembersByProjectId(String projectId) {
		Map<String, Object> filters=new HashMap<String, Object>();
		filters.put("projectId", projectId);
		filters.put("delFlag", "0");
		List<SpProjectUser> dataList = spProjectUserDao.findList(filters);
		return dataList;
	}
}