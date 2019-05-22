/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.resource.dao;

import java.util.Map;

import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 资料库管理DAO接口
 * @author 杨立明
 * @version 2016-08-05
 */
@MyBatisDao
public interface SpResourceDao extends CrudDao<SpResource> {

	void updatePublishState(SpResource spResource);

	String getPublishState(String resourceId);

	void saveVideoImg(Map<String, String> paraMap);
	
}