/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.user.dao;

import java.util.List;

import com.education.classroom.core.modules.spadmin.user.entity.SpUserToken;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * tokenDAO接口
 * @author luzhiyou
 * @version 2016/08/08
 */
@MyBatisDao
public interface SpUserTokenDao extends CrudDao<SpUserToken> {
	
	
	public	SpUserToken findByUserId(SpUserToken token);
	
	/**
	 * 查询token集合
	 * 2016年9月14日
	 * By zhangyongsheng
	 * @param token
	 * @return
	 */
	public	List<SpUserToken> findTokens(SpUserToken token);
	
}