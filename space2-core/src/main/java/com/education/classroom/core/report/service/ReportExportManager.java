package com.education.classroom.core.report.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.education.classroom.core.report.dao.ReportExportDao;
import com.education.classroom.core.report.entity.ReportExport;
import com.education.classroom.core.service.CrudService;

/**
 * 报表导出按钮权限Manager
 * @Class Name ReportExportManager
 * @author 张永生
 * @Create In 2016年6月27日
 */
@Service
public class ReportExportManager extends CrudService<ReportExportDao, ReportExport> {

	
	public ReportExport findByParams(Map<String,Object> params){
		return dao.findByParams(params);
	}
	
}
