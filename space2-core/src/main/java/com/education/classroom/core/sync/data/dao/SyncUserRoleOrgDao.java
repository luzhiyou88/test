package com.education.classroom.core.sync.data.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.sync.data.entity.SyncUserRoleOrg;

/**
 * 同步用户与组织机构dao
 * @Class Name SyncUserOrgDao
 * @author 张永生
 * @Create In 2015年12月2日
 */
@MyBatisDao
public interface SyncUserRoleOrgDao extends CrudDao<SyncUserRoleOrg> {

	/**
	 * 条件查询SyncUserOrg对象
	 * 2015年12月7日
	 * By 张永生
	 * @param filter
	 * @return
	 */
	public List<SyncUserRoleOrg> findByParams(Map<String,Object> filter);
	
	/**
	 * 分页查询
	 * 2015年12月8日
	 * By 张永生
	 * @param params
	 * @param pageBounds
	 * @return
	 */
	public List<SyncUserRoleOrg> findByPage(Map<String,Object> params);
	
}
