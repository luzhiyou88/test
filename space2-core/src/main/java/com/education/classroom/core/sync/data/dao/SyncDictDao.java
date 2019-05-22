package com.education.classroom.core.sync.data.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.sync.data.entity.SyncDict;

/**
 * 同步数据字典dao
 * @Class Name SyncDictDao
 * @author 陈伟东
 * @Create In 2015年12月28日
 */
@MyBatisDao
public interface SyncDictDao extends CrudDao<SyncDict>{
	
	/**
	 * 条件查询SyncDict对象
	 * 2015年12月28日
	 * By 陈伟东
	 * @param filter
	 * @return
	 */
	public List<SyncDict> findByParams(Map<String,Object> filter);
	
}