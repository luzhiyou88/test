/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.category.dao;

import java.util.List;

import com.education.classroom.core.modules.spadmin.category.entity.SpCategory;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 分类管理DAO
 * @author shangjunwei @version 2016-08-05
 */
@MyBatisDao
public interface SpCategoryDao extends CrudDao<SpCategory> {
	
	public List<SpCategory> findAllListForDict();
}