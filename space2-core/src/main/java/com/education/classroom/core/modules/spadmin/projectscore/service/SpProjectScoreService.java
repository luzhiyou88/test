/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.projectscore.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.modules.spadmin.projectscore.dao.SpProjectScoreDao;
import com.education.classroom.core.modules.spadmin.projectscore.entity.SpProjectScore;
import com.education.classroom.core.service.CrudService;

/**
 * 项目评分Service
 * 
 * @author 边磊
 * @version 2016/08/29
 */
@Service
@Transactional(readOnly = true)
public class SpProjectScoreService extends
		CrudService<SpProjectScoreDao, SpProjectScore> {
	@Autowired
	private SpProjectScoreDao spProjectScoreDao;

	public SpProjectScore get(String id) {
		return super.get(id);
	}

	public List<SpProjectScore> findList(SpProjectScore spProjectScore) {
		return super.findList(spProjectScore);
	}

	public List<Map<String, Object>> findScore(String projectId) {

		List<Map<String, Object>> findScore = spProjectScoreDao
				.findScore(projectId);
		return findScore;
	}

	// public Page<SpProjectScore> findPage(Page<SpProjectScore> page,
	// SpProjectScore spProjectScore) {
	// return super.findPage(page, spProjectScore);
	// }

	@Transactional(readOnly = false)
	public void save(SpProjectScore spProjectScore) {
		super.save(spProjectScore);
	}

	@Transactional(readOnly = false)
	public void delete(SpProjectScore spProjectScore) {
		super.delete(spProjectScore);
	}

}