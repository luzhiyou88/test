package com.education.classroom.modules.spadmin.note.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.modules.spadmin.note.dao.SpUserNoteDao;
import com.education.classroom.core.modules.spadmin.note.entity.SpUserNote;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 笔记Service
 * @author zhangyongsheng
 * @version 2016/08/11
 */
@Service
@Transactional(readOnly = true)
public class SpUserNoteService extends CrudService<SpUserNoteDao, SpUserNote> {

	public SpUserNote get(String id) {
		return super.get(id);
	}
	
	public List<SpUserNote> findList(SpUserNote spUserNote) {
		return super.findList(spUserNote);
	}
	
	public Page<SpUserNote> findPage(Page<SpUserNote> page, SpUserNote spUserNote) {
		return null;
	}
	
	@Transactional(readOnly = false)
	public void save(SpUserNote spUserNote) {
		super.save(spUserNote);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpUserNote spUserNote) {
		super.delete(spUserNote);
	}
	
}