package com.education.classroom.core.log.dao;

import java.util.List;

import com.education.classroom.core.log.entity.Log;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 日志DAO接口
 * @Class Name LogDao
 * @author 张永生
 * @Create In 2015年11月27日
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

	/**
	 * 根据log参数和pageBounds查询日志数据
	 * 2015年12月1日
	 * By 张永生
	 * @param log
	 * @param pageBounds
	 * @return
	 */
	public List<Log> findList(Log log);
	
}
