package com.education.classroom.core.sync.data.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.sync.data.entity.SyncUser;

/**
 * 同步用户dao
 * @Class Name SyncUserDao
 * @author 张永生
 * @Create In 2015年12月2日
 */
@MyBatisDao
public interface SyncUserDao extends CrudDao<SyncUser> {

	/**
	 * 条件查询SyncUser对象
	 * 2015年12月7日
	 * By 张永生
	 * @param filter
	 * @return
	 */
	public List<SyncUser> findByParams(Map<String,Object> filter);
	
	/**
	 * 分页查询
	 * 2015年12月8日
	 * By 张永生
	 * @param params
	 * @param pageBounds
	 * @return
	 */
	public List<SyncUser> findByPage(Map<String,Object> params);
	
	/**
	 * 根据角色查询用户
	 * @param filter
	 * @return
	 */
	public List<SyncUser> findUserByRoleId(Map<String,Object> filter);
	
}
