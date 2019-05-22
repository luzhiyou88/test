package com.education.classroom.core.report.dao;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.report.entity.ReportUrl;

/**
 * 报表url的dao
 * @Class Name ReportUrlDao
 * @author 张永生
 * @Create In 2015年12月23日
 */
@MyBatisDao
public interface ReportUrlDao extends CrudDao<ReportUrl> {

	/**
	 * 根据id删除报表url对象
	 * 2015年12月23日
	 * By 张永生
	 * @param id
	 */
	public void deleteById(String id);
}
