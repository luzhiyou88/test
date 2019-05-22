/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.favor.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.favor.entity.SpFavor;
import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 资源收藏DAO接口
 * 
 * @author 边磊
 * @version 2016/08/10
 */
@MyBatisDao
public interface SpFavorDao extends CrudDao<SpFavor> {
	List<SpResource> findPageList(Map<String, Object> queryMap);

}