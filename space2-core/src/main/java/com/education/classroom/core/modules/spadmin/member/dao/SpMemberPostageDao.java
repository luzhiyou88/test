/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.member.dao;

import java.util.List;

import com.education.classroom.core.modules.spadmin.member.entity.SpMemberPostage;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 会员资费管理DAO接口
 * @author 尚军伟
 * @version 2016/08/06
 */
@MyBatisDao
public interface SpMemberPostageDao extends CrudDao<SpMemberPostage> {
	
	//资费名称唯一性验证
	public int selectByName(String name);
	
	//获取会员资费列表
	public List<SpMemberPostage> selectMemberPostageList();
	
	
	public SpMemberPostage selectById(String id);
	
}