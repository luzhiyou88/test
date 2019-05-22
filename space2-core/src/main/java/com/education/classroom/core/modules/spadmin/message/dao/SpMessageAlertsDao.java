/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.message.dao;

import com.education.classroom.core.modules.spadmin.message.entity.SpMessageAlerts;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 公告管理DAO接口
 * @author 尚军伟
 * @version 2016/08/06
 */
@MyBatisDao
public interface SpMessageAlertsDao extends CrudDao<SpMessageAlerts> {
	
}