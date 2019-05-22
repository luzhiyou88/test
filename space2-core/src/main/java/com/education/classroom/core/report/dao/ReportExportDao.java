package com.education.classroom.core.report.dao;

import java.util.Map;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.report.entity.ReportExport;

/**
 * 报表导出按钮权限的dao
 * @Class Name ReportExportDao
 * @author 张永生
 * @Create In 2016年6月27日
 */
@MyBatisDao
public interface ReportExportDao extends CrudDao<ReportExport> {

	/**
	 * 条件查询报表导出按钮权限对象
	 * 2016年6月27日
	 * By 张永生
	 * @param params
	 * @return
	 */
	ReportExport findByParams(Map<String,Object> params);
	
}
