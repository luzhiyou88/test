/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.education.classroom.core.users.dao;

import com.education.classroom.core.persistence.TreeDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.users.entity.Area;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
	//校验区域名称唯一性
	public int selectByName(String name);
}
