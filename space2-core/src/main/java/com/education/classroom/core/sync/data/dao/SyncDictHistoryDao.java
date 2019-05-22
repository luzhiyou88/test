package com.education.classroom.core.sync.data.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.sync.data.entity.SyncDictHistory;

/**
 * 同步数据字典历史dao
 * @Class Name SyncDictHistoryDao
 * @author 陈伟东
 * @Create In 2015年12月28日
 */
@MyBatisDao
public interface SyncDictHistoryDao extends CrudDao<SyncDictHistory>{
	/**
	 * 条件查询SyncDictHistory对象
	 * 2015年12月28日
	 * By 陈伟东
	 * @param filter
	 * @return
	 */
	public List<SyncDictHistory> findByParams(Map<String,Object> filter);
}