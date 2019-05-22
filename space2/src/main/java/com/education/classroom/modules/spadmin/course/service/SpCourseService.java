/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.course.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.course.dao.SpCourseDao;
import com.education.classroom.core.modules.spadmin.course.entity.SpCourse;
import com.education.classroom.core.modules.spadmin.course.type.CourseType;
import com.education.classroom.core.modules.spadmin.courseResource.dao.SpCourseResourceDao;
import com.education.classroom.core.modules.spadmin.courseResource.entity.SpCourseResource;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 套课管理Service
 * @author 朱杰
 * @version 2016/08/06
 */
@Service
@Transactional(readOnly = true)
public class SpCourseService extends CrudService<SpCourseDao, SpCourse> {

	@Autowired
	private SpCourseResourceDao spCourseResourceDao; 

	public SpCourse get(String id) {
		return super.get(id);
	}
	
	public List<SpCourse> findAllList(Map<String,Object> spCourse) {
		return dao.findAllList(spCourse);
	}
	
	public Page<SpCourse> findList(Page<SpCourse> page, Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<SpCourse> list = dao.findList(queryMap);
        PageInfo<SpCourse> lstPage = new PageInfo<SpCourse>(list);
        PageUtil.convertPage(lstPage, page);
        return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpCourse spCourse) {
		if(CourseType.HY.getCode().equals(spCourse.getCourseType())){
			// 套课类型为会员的情况下，套课价格置空
			spCourse.setCoursePrice(new Double(0));
		}
		super.save(spCourse);
		
		// 添加课程资源
		if(spCourse.getAddCourseResourceId()!=null){
			for(String addCourseResourceId : spCourse.getAddCourseResourceId()){
				if(StringUtils.isBlank(addCourseResourceId)){
					continue;
				}
				SpCourseResource spCourseResource = new SpCourseResource();
				spCourseResource.setId(addCourseResourceId);
				spCourseResource.setCourseId(spCourse.getId());
				spCourseResource.preUpdate();
				spCourseResourceDao.update(spCourseResource);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SpCourse spCourse) {
		// 删除套课
		spCourse.preUpdate();
		super.delete(spCourse);
		// TODO:课程删除
		
	}
	
	/**
	 * 获取套课列表下拉框绑定项
	 * 2016年8月7日
	 * By zhujie
	 * @return
	 */
	public List<SpCourse> getAllListForDict(){
		SpCourse param = new SpCourse();
		param.setSpaceId(SpaceUtils.getSpaceId());
		return dao.getAllListForDict(param);
	}
	
	/**
	 * 获取该套课下等待审核或者已发布过的课程数
	 * 2016年9月5日
	 * By zhujie
	 * @param courseId
	 * @return
	 */
	public int getPublishedLessonNum(String courseId){
		return dao.getPublishedLessonNum(courseId);
	}
	
	/**
	 * 获取套课下已经订阅的平台课程
	 * 2016年9月24日
	 * By zhujie
	 * @param courseId
	 * @return
	 */
	public int getSubscribedLessonCount(String courseId){
		return dao.getSubscribedLessonCount(courseId);
	}
}