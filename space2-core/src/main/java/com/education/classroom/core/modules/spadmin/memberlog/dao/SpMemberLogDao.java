package com.education.classroom.core.modules.spadmin.memberlog.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.memberlog.entity.SpMemberLog;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;


/**
 * 会员记录管理DAO接口
 * @author 赵新月
 * @version 2016/08/19
 */
@MyBatisDao
public interface SpMemberLogDao extends CrudDao<SpMemberLog> {
	public List<SpMemberLog> findPageList(Map<String, Object> queryMap);
}