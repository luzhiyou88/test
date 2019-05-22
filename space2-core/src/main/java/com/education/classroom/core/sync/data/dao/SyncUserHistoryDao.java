package com.education.classroom.core.sync.data.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.sync.data.entity.SyncUserHistory;

/**
 * 同步用户历史dao
 * @Class Name SyncOrgHistoryDao
 * @author 张永生
 * @Create In 2015年12月2日
 */
@MyBatisDao
public interface SyncUserHistoryDao extends CrudDao<SyncUserHistory> {

	/**
	 * 分页查询
	 * 2015年12月8日
	 * By 张永生
	 * @param params
	 * @param pageBounds
	 * @return
	 */
	public List<SyncUserHistory> findByPage(Map<String,Object> params);
	
}
