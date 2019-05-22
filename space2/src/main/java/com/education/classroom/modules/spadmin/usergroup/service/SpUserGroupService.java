package com.education.classroom.modules.spadmin.usergroup.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.modules.spadmin.usergroup.dao.SpUserGroupDao;
import com.education.classroom.core.modules.spadmin.usergroup.entity.SpUserGroup;
import com.education.classroom.core.service.CrudService;

/**
 * 用户小组关系Service
 * @author zhangyongsheng
 * @version 2016/08/11
 */
@Service
@Transactional(readOnly = true)
public class SpUserGroupService extends CrudService<SpUserGroupDao, SpUserGroup> {

	public SpUserGroup get(String id) {
		return super.get(id);
	}
	
	public List<SpUserGroup> findList(SpUserGroup spUserGroup) {
		return super.findList(spUserGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(SpUserGroup spUserGroup) {
		super.save(spUserGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpUserGroup spUserGroup) {
		super.delete(spUserGroup);
	}
	
}