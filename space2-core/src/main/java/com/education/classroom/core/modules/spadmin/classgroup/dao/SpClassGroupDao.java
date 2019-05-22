/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.classgroup.dao;

import com.education.classroom.core.modules.spadmin.classgroup.entity.SpClassGroup;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 班级小组关联DAO接口
 * @author 边磊
 * @version 2016/08/06
 */
@MyBatisDao
public interface SpClassGroupDao extends CrudDao<SpClassGroup> {
	
	/**
	 * 根据班级ID查找班级组
	 * 2016年8月11日
	 * By zhangyongsheng
	 * @param classId
	 * @return
	 */
	public SpClassGroup getByClassId(String classId);
	
}