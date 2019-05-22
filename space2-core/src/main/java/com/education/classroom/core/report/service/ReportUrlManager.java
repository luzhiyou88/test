package com.education.classroom.core.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.report.dao.ReportUrlDao;
import com.education.classroom.core.report.entity.ReportUrl;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.StringUtils;

/**
 * 报表url管理类
 * @Class Name ReportUrlManager
 * @author 张永生
 * @Create In 2015年12月23日
 */
@Service
public class ReportUrlManager extends CrudService<ReportUrlDao, ReportUrl> {

	@Transactional(readOnly = false)
	public void save(ReportUrl reportUrl){
		super.save(reportUrl);
		if (StringUtils.isBlank(reportUrl.getId())){
			reportUrl.preInsert();
			dao.insert(reportUrl);
		}else{
			reportUrl.preUpdate();
			dao.update(reportUrl);
		}
	}
	
	public ReportUrl get(String id){
		return super.get(id);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id){
		dao.deleteById(id);
	}
	
}
