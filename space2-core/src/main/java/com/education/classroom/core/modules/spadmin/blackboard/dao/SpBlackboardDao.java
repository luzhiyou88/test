package com.education.classroom.core.modules.spadmin.blackboard.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.blackboard.entity.SpBlackboard;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 板书DAO接口
 * @author zhangyongsheng
 * @version 2016/08/11
 */
@MyBatisDao
public interface SpBlackboardDao extends CrudDao<SpBlackboard> {

	/**
	 * 条件查询板书
	 * 2016年8月11日
	 * By zhangyongsheng
	 * @param filters
	 * @return
	 */
	List<SpBlackboard> findListByMap(Map<String, Object> filters);
	
}