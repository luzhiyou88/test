/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.phonecode.dao;

import java.util.Map;

import com.education.classroom.core.modules.spadmin.phonecode.entity.SpCode;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 手机验证码DAO接口
 * @author 边磊
 * @version 2016/08/08
 */
@MyBatisDao
public interface SpCodeDao extends CrudDao<SpCode> {
	
	/**
	 * 根据手机号条件查询验证码
	 * 2016年8月9日
	 * By zhangyongsheng
	 * @param params
	 * @return
	 */
	public SpCode findByPhoneNo(Map<String, Object> params);
	
}