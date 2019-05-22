/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.grouptopic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.modules.spadmin.grouptopic.dao.SpGroupTopicDao;
import com.education.classroom.core.modules.spadmin.grouptopic.entity.SpGroupTopic;
import com.education.classroom.core.service.CrudService;

/**
 * 小组话题表Service
 * @author shangjunwei
 * @version 2016/08/10
 */
@Service
@Transactional(readOnly = true)
public class SpGroupTopicService extends CrudService<SpGroupTopicDao, SpGroupTopic> {

	public SpGroupTopic get(String id) {
		return super.get(id);
	}
	
	public List<SpGroupTopic> findList(SpGroupTopic spGroupTopic) {
		return super.findList(spGroupTopic);
	}
	
	/*public Page<SpGroupTopic> findPage(Page<SpGroupTopic> page, SpGroupTopic spGroupTopic) {
		return super.findPage(page, spGroupTopic);
	}*/
	
	@Transactional(readOnly = false)
	public void save(SpGroupTopic spGroupTopic) {
		super.save(spGroupTopic);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpGroupTopic spGroupTopic) {
		super.delete(spGroupTopic);
	}
	
}