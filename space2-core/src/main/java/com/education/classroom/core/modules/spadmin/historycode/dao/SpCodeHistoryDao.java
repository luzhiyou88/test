/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.historycode.dao;

import com.education.classroom.core.modules.spadmin.historycode.entity.SpCodeHistory;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 历史验证码DAO接口
 * @author 边磊
 * @version 2016/08/08
 */
@MyBatisDao
public interface SpCodeHistoryDao extends CrudDao<SpCodeHistory> {
	
}