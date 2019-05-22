/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.answer.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.answer.entity.SpAnswerScore;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 答案DAO接口
 * 
 * @author 路志友
 * @version 2016/08/18
 */
@MyBatisDao
public interface SpAnswerScoreDao extends CrudDao<SpAnswerScore> {

	List<SpAnswerScore> findList(Map<String,Object> queryMap);
}