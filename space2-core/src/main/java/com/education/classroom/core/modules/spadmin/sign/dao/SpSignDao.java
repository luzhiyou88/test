/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.sign.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.sign.entity.SpSign;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 签到DAO接口
 * 
 * @author 边磊
 * @version 2016/09/09
 */
@MyBatisDao
public interface SpSignDao extends CrudDao<SpSign> {

	SpSign getSign(Map<String, Object> queryMap);

	List<SpSign> findPageList(Map<String, Object> queryMap);

}