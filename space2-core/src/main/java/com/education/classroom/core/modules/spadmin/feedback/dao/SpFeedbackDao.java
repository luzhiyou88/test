/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.feedback.dao;

import com.education.classroom.core.modules.spadmin.feedback.entity.SpFeedback;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 用户反馈DAO接口
 * @author 尚军伟
 * @version 2016/08/15
 */
@MyBatisDao
public interface SpFeedbackDao extends CrudDao<SpFeedback> {
	
}