package com.education.classroom.core.modules.spadmin.space.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.space.entity.SpSpace;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 教室管理DAO接口
 * @author 尚军伟
 * @version 2016/08/06
 */
@MyBatisDao
public interface SpSpaceDao extends CrudDao<SpSpace> {
	
	List<SpSpace> selSpSchool();
	String selSchoolNamebyId(@Param(value = "id")String id);
	
	//根据学校ID查询会员预留数量 
	public int selectReservedQuantityById(String spaceId);
	
	//根据学校ID，使会员预留数量减1
	public void updateReservedQuantityById(Map<String,Object> map);
	
	//校验学校名称唯一性
	public int selectByName(@Param(value = "name") String name,@Param(value = "id") String id);
}