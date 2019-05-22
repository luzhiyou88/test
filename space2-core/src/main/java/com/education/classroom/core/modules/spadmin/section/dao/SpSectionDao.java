/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.section.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.section.entity.SpSection;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 节次管理DAO接口
 * @author 路志友
 * @version 2016-08-05
 */
@MyBatisDao
public interface SpSectionDao extends CrudDao<SpSection> {
	
	
	public List<SpSection> findPageList(Map<String,Object> map);
	
	/**
	 * 获取借此下拉框绑定值
	 * 2016年8月7日
	 * By zhujie
	 * @return
	 */
	public List<SpSection> getAllListForDict();
	
	/**
	 * 获取日期内余下的节次
	 * 2016年8月10日
	 * By zhujie
	 * @param lessonDate
	 * @return
	 */
	public List<SpSection> getRemainSectionByDate(Map<String,Object> param);
	

	/**
	 * 根据节次ID查询课程表是否有关联的课程
	 * 2016年8月23日
	 * By shangjunwei
	 * @param sectionId
	 * @return
	 */
	
	public int selectLessonBySectionId(String sectionId);
	
	//查询节次有无重复
	public int selectSectionByTime(Map<String,Object> map);
	
}