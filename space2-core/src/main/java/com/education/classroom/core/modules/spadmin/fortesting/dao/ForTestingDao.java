/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.fortesting.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.fortesting.entity.ForTesting;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 我的测试管理DAO接口
 * 
 * @author 边磊
 * @version 2016/08/19
 */
@MyBatisDao
public interface ForTestingDao extends CrudDao<ForTesting> {
	List<ForTesting> findForTestingList(Map<String, Object> queryMap);

	Integer getAnswer(Map<String, Object> queryMap);

	void saveAnswer(Map<String, Object> queryMap);

	void updateAnswer(Map<String, Object> queryMap);

}