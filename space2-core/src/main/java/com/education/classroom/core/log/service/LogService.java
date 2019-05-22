package com.education.classroom.core.log.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.education.classroom.core.log.dao.LogDao;
import com.education.classroom.core.log.entity.Log;
import com.education.classroom.core.service.CrudService;

/**
 * 日志Manager
 * @Class Name logManager
 * @author 张永生
 * @Create In 2015年11月24日
 */
@Service
public class LogService extends CrudService<LogDao, Log> {
	
	/**
	 * 根据log参数和pageBounds来查询日志数据
	 * 2015年12月1日
	 * By 张永生
	 * @param log
	 * @param pageBounds
	 * @return
	 */
	public List<Log> findList(Log log){
		return dao.findList(log);
	}
	
}
