package com.education.classroom.core.sync.data.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.sync.data.entity.SyncOrg;

/**
 * 同步组织结构dao
 * @Class Name SyncOrgDao
 * @author 张永生
 * @Create In 2015年12月2日
 */
@MyBatisDao
public interface SyncOrgDao extends CrudDao<SyncOrg> {

	/**
	 * 条件查询SyncOrg对象
	 * 2015年12月7日
	 * By 张永生
	 * @param filter
	 * @return
	 */
	public List<SyncOrg> findByParams(Map<String,Object> filter);
	
}
