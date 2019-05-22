package com.education.classroom.core.log.facade;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.log.entity.Log;
import com.education.classroom.core.log.service.LogService;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.utils.DateUtils;

/**
 * 日志转换层
 * @Class Name LogFacade
 * @author 张永生
 * @Create In 2015年11月24日
 */
@Service
public class LogFacade {

	@Autowired
	private LogService logManager;


	public Page<Log> findLog(Page<Log> page, Log log) throws Exception {
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		log.setBeginDateStr(fmt.format(log.getBeginDate()));
		log.setEndDateStr(fmt.format(log.getEndDate()));
		
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
    	List<Log> logs = logManager.findList(log);
    	PageInfo<Log> resultPage = new PageInfo<Log>(logs);
    	PageUtil.convertPage(resultPage, page);
    	
    	//TODO 
		return page;
	}
}
