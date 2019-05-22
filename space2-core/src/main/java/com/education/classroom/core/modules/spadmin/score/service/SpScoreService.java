/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.score.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.score.dao.SpScoreDao;
import com.education.classroom.core.modules.spadmin.score.entity.SpScore;
import com.education.classroom.core.modules.spadmin.score.entity.UserSpScore;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 评价Service
 * 
 * @author 边磊
 * @version 2016/08/09
 */
@Service
@Transactional(readOnly = true)
public class SpScoreService extends CrudService<SpScoreDao, SpScore> {
	@Autowired
	private SpScoreDao SpScoreDao;

	public SpScore get(String id) {
		return super.get(id);
	}

	public List<SpScore> findList(SpScore spScore) {
		return super.findList(spScore);
	}

	public Page<UserSpScore> findPage(Page<UserSpScore> page,
			Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<UserSpScore> dicts = SpScoreDao.findPageList(queryMap);
		PageInfo<UserSpScore> templatePage = new PageInfo<UserSpScore>(dicts);
		PageUtil.convertPage(templatePage, page);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(SpScore spScore) {
		if(spScore.getIsNewRecord()){
		spScore.preInsertNoId();
		SpScoreDao.insert(spScore);
		}else{
			super.save(spScore);
		}
	}

	@Transactional(readOnly = false)
	public void delete(SpScore spScore) {
		super.delete(spScore);
	}

	public Double findAVG(String sourceId) {
		return SpScoreDao.findAVG(sourceId);
	}

}