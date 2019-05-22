/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.modules.spadmin.comment.dao.SpCommentDao;
import com.education.classroom.core.modules.spadmin.comment.entity.SpComment;
import com.education.classroom.core.service.CrudService;

/**
 * 评论回复Service
 * @author 尚军伟
 * @version 2016/08/10
 */
@Service
@Transactional(readOnly = true)
public class SpCommentService extends CrudService<SpCommentDao, SpComment> {

	public SpComment get(String id) {
		return super.get(id);
	}
	
	public List<SpComment> findList(SpComment spComment) {
		return super.findList(spComment);
	}
	
	/*public Page<SpComment> findPage(Page<SpComment> page, SpComment spComment) {
		return super.findPage(page, spComment);
	}*/
	
	@Transactional(readOnly = false)
	public void save(SpComment spComment) {
		super.save(spComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpComment spComment) {
		super.delete(spComment);
	}
	
}