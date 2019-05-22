package com.education.classroom.core.sync.data.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.sync.data.entity.SyncUserRoleOrgHistory;

/**
 * 同步用户与组织机构历史dao
 * @Class Name SyncUserOrgHistory
 * @author 张永生
 * @Create In 2015年12月2日
 */
@MyBatisDao
public interface SyncUserRoleOrgHistoryDao extends CrudDao<SyncUserRoleOrgHistory> {

	public List<SyncUserRoleOrgHistory> findByParams(Map<String,Object> filter);
	
	/**
	 * 分页查询
	 * 2015年12月8日
	 * By 张永生
	 * @param params
	 * @param pageBounds
	 * @return
	 */
	public List<SyncUserRoleOrgHistory> findByPage(Map<String,Object> params);
	
	
}
