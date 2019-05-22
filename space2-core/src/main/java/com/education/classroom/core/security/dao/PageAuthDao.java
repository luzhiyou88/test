package com.education.classroom.core.security.dao;

import java.util.List;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.security.entity.PageAuth;

/**
 * 页面权限控制DAO接口
 * @author 周亮
 * @version 2015-11-02
 */
@MyBatisDao
public interface PageAuthDao extends CrudDao<PageAuth> {
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public List<PageAuth> getPageAuthInfo(PageAuth pa);
}
