package com.education.classroom.core.modules.spadmin.note.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.note.entity.SpUserNote;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 笔记DAO接口
 * @author zhangyongsheng
 * @version 2016/08/11
 */
@MyBatisDao
public interface SpUserNoteDao extends CrudDao<SpUserNote> {
	
	/**
	 * 条件查询笔记
	 * 2016年8月11日
	 * By zhangyongsheng
	 * @param filters
	 * @return
	 */
	List<SpUserNote> findListByMap(Map<String, Object> filters);
	
	
}