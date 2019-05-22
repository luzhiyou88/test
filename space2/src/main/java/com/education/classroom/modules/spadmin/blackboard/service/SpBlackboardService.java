package com.education.classroom.modules.spadmin.blackboard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.modules.spadmin.blackboard.dao.SpBlackboardDao;
import com.education.classroom.core.modules.spadmin.blackboard.entity.SpBlackboard;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 板书Service
 * @author zhangyongsheng
 * @version 2016/08/11
 */
@Service
@Transactional(readOnly = true)
public class SpBlackboardService extends CrudService<SpBlackboardDao, SpBlackboard> {

	public SpBlackboard get(String id) {
		return super.get(id);
	}
	
	public List<SpBlackboard> findList(SpBlackboard spBlackboard) {
		return super.findList(spBlackboard);
	}
	
	public Page<SpBlackboard> findPage(Page<SpBlackboard> page, SpBlackboard spBlackboard) {
		return null;
	}
	
	@Transactional(readOnly = false)
	public void save(SpBlackboard spBlackboard) {
		super.save(spBlackboard);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpBlackboard spBlackboard) {
		super.delete(spBlackboard);
	}
	
}