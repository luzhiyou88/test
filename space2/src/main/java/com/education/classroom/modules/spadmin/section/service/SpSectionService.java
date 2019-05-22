/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.section.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.section.dao.SpSectionDao;
import com.education.classroom.core.modules.spadmin.section.entity.SpSection;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.DateUtils;

/**
 * 节次管理Service
 * @author 路志友
 * @version 2016-08-05
 */
@Service
@Transactional(readOnly = true)
public class SpSectionService extends CrudService<SpSectionDao, SpSection> {
   
	@Autowired
	private SpSectionDao spSectionDao;
	
	public SpSection get(String id) {
		return super.get(id);
	}
	
	public List<SpSection> findList(SpSection spSection) {
		return super.findList(spSection);
	}
	
	public Page<SpSection> findPage(Page<SpSection> page, Map<String,Object> filters) {
		//GenUtils.getTemplatePath();
	    PageHelper.startPage(page.getPageNo(),page.getPageSize());
	    List<SpSection> ls = spSectionDao.findPageList(filters);
		PageInfo<SpSection> resultPage = new PageInfo<SpSection>(ls);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpSection spSection) {
		super.save(spSection);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpSection spSection) {
		super.delete(spSection);
	}
	
	/**
	 * 获取节次下拉框
	 * 2016年8月7日
	 * By zhujie
	 */
	@Transactional(readOnly = true)
	public List<SpSection> getAllListForDict() {
		return dao.getAllListForDict();
	}
	
	public List<SpSection> getRemainSectionByDate(Date lessonDate){
		if(lessonDate==null){
			return new ArrayList<SpSection>();
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lessonDate", lessonDate);
		if(DateUtils.isSameDay(lessonDate, new Date())){
			// 排除今天已经结束的节次
			param.put("hhmmss", DateUtils.formatDate(new Date(), "HH:mm:ss"));
		}
		return dao.getRemainSectionByDate(param);
	}
	
	/**
	 * 根据节次ID查询课程表是否有关联的课程
	 * 2016年8月23日
	 * By shangjunwei
	 * @param sectionId
	 * @return
	 */
	
	public int selectLessonBySectionId(String sectionId){
		return spSectionDao.selectLessonBySectionId(sectionId);
	}
	
	//查询节次有无重复
	public int selectSectionByTime(Map<String,Object> map){
		return spSectionDao.selectSectionByTime(map);
	}
}