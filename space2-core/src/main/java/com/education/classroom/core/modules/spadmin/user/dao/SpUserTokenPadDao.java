/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.user.dao;

import java.util.List;

import com.education.classroom.core.modules.spadmin.user.entity.SpUserToken;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * token——padDAO接口
 * @Class Name SpUserTokenPadDao
 * @author zhujie
 * @Create In 2016年9月7日
 */
@MyBatisDao
public interface SpUserTokenPadDao extends CrudDao<SpUserToken> {
	
	
	public	SpUserToken findByUserId(SpUserToken token);
	public	List<SpUserToken> findLsByUserId(SpUserToken token);
}