package com.education.classroom.core.modules.spadmin.sign.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.sign.entity.UserSign;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 签到DAO接口
 * 
 * @author 边磊
 * @version 2016/09/06
 */
@MyBatisDao
public interface SignDao extends CrudDao<UserSign> {

	List<UserSign> findPageList(Map<String, Object> queryMap);

}
