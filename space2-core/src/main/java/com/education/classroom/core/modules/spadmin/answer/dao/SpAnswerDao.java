/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.answer.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.answer.entity.SpAnswer;
import com.education.classroom.core.modules.spadmin.problems.entity.SpProblems;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 答案DAO接口
 * @author 路志友
 * @version 2016/08/18
 */
@MyBatisDao
public interface SpAnswerDao extends CrudDao<SpAnswer> {
	
	List<SpProblems>  findPageList (Map<String,Object> qrrymap);
	
}