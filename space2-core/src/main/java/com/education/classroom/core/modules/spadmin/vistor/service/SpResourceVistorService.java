/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.vistor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.modules.spadmin.vistor.dao.SpResourceVistorDao;
import com.education.classroom.core.modules.spadmin.vistor.entity.SpResourceVistor;
import com.education.classroom.core.service.CrudService;

/**
 * 用户资源统计Service
 * @author 边磊
 * @version 2016/08/09
 */
@Service
@Transactional(readOnly = true)
public class SpResourceVistorService extends CrudService<SpResourceVistorDao, SpResourceVistor> {

	public SpResourceVistor get(String id) {
		return super.get(id);
	}
	
	public List<SpResourceVistor> findList(SpResourceVistor spResourceVistor) {
		return super.findList(spResourceVistor);
	}
	
//	public Page<SpResourceVistor> findPage(Page<SpResourceVistor> page, SpResourceVistor spResourceVistor) {
//		return super.findPage(page, spResourceVistor);
//	}
	
	@Transactional(readOnly = false)
	public void save(SpResourceVistor spResourceVistor) {
		super.save(spResourceVistor);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpResourceVistor spResourceVistor) {
		super.delete(spResourceVistor);
	}
	
}