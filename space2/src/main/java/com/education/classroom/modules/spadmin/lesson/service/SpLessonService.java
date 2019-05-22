/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.lesson.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.type.LessonSourceType;
import com.education.classroom.core.common.type.LessonType;
import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.exception.ServiceException;
import com.education.classroom.core.member.type.PublishState;
import com.education.classroom.core.modules.spadmin.course.dao.SpCourseDao;
import com.education.classroom.core.modules.spadmin.course.entity.SpCourse;
import com.education.classroom.core.modules.spadmin.courseResource.dao.SpCourseResourceDao;
import com.education.classroom.core.modules.spadmin.courseResource.entity.SpCourseResource;
import com.education.classroom.core.modules.spadmin.exam.dao.SpExaminationDao;
import com.education.classroom.core.modules.spadmin.exam.entity.SpExamination;
import com.education.classroom.core.modules.spadmin.handouts.dao.SpHandoutsDao;
import com.education.classroom.core.modules.spadmin.handouts.entity.SpHandouts;
import com.education.classroom.core.modules.spadmin.lesson.dao.SpLessonDao;
import com.education.classroom.core.modules.spadmin.lesson.entity.PublishLesson;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.modules.spadmin.material.dao.SpMaterialsDao;
import com.education.classroom.core.modules.spadmin.material.entity.SpMaterials;
import com.education.classroom.core.modules.spadmin.problems.dao.SpProblemsDao;
import com.education.classroom.core.modules.spadmin.problems.entity.SpProblems;
import com.education.classroom.core.modules.spadmin.project.dao.SpProjectDao;
import com.education.classroom.core.modules.spadmin.project.dao.SpProjectResourceDao;
import com.education.classroom.core.modules.spadmin.project.dao.SpProjectUserDao;
import com.education.classroom.core.modules.spadmin.project.entity.SpProject;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectResource;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectUser;
import com.education.classroom.core.modules.spadmin.section.dao.SpSectionDao;
import com.education.classroom.core.modules.spadmin.section.entity.SpSection;
import com.education.classroom.core.modules.spadmin.user.dao.SpUserDao;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.core.users.constants.stateConstants;
import com.education.classroom.modules.spadmin.lesson.view.StudioView;
import com.education.classroom.modules.spadmin.projects.service.SpProjectsService;
import com.education.classroom.utils.Collections3;
import com.education.classroom.utils.DateUtils;
import com.education.classroom.utils.IdGen;
import com.education.classroom.utils.ListUtils;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.ccutils.APIServiceFunction;
import com.education.classroom.utils.ccutils.CCConfig;
import com.education.classroom.utils.ccutils.Md5Encrypt;
import com.education.classroom.utils.classroom.SpaceUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 课程管理Service
 * @author 朱杰
 * @version 2016/08/06
 */
@Service
@Transactional(readOnly = true)
public class SpLessonService extends CrudService<SpLessonDao, SpLesson> {
	
	@Autowired
	private SpSectionDao spSectionDao;
	@Autowired
	private SpLessonDao lessonDao;
	@Autowired
	private SpCourseDao spCourseDao;
	@Autowired
	private SpHandoutsDao spHandoutsDao;
	@Autowired
	private SpUserDao spUserDao;
	@Autowired
	private SpCourseResourceDao spCourseResourceDao;
	@Autowired
	private SpExaminationDao examinationDao;
	@Autowired
	private SpProblemsDao problemsDao;
	@Autowired
	private SpMaterialsDao spMaterialsDao;
	@Autowired
	private SpProjectDao spProjectDao;
	@Autowired
	private SpProjectUserDao spProjectUserDao;
	@Autowired
	private SpProjectResourceDao spProjectResourceDao;
	@Autowired
	private SpProjectsService spProjectsService;

	public SpLesson get(String id) {
		return super.get(id);
	}
	
	public Page<SpLesson> findList(Page<SpLesson> page, Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<SpLesson> list = dao.findList(queryMap);
        PageInfo<SpLesson> lstPage = new PageInfo<SpLesson>(list);
        PageUtil.convertPage(lstPage, page);
        return page;
	}
	
	//查询课程带讲义标题
	public Page<SpLesson> findListAndHandouts(Page<SpLesson> page, Map<String, Object> queryMap){
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<SpLesson> list = dao.findListAndHandouts(queryMap);
        PageInfo<SpLesson> lstPage = new PageInfo<SpLesson>(list);
        PageUtil.convertPage(lstPage, page);
        return page;
	}
	
	@Transactional(value="transactionManager",readOnly = false)
	public void save(SpLesson spLesson) {
		
		if(StringUtils.isEmpty(spLesson.getSpaceId())){
			// space_id
			spLesson.setSpaceId(SpaceUtils.getSpaceId());
		}
		if(StringUtils.isEmpty(spLesson.getBroadcastId())){
			// 直播间id
			spLesson.setBroadcastId(IdGen.uuid());
		}
		
		// 开始时间-结束时间
		SpSection spSection = spSectionDao.get(spLesson.getSectionId());
		if(spSection != null){
			spLesson.setLessonStarttime(spSection.getStartTime());
			spLesson.setLessonEndtime(spSection.getEndTime());
		}
		// 课程来源
		if(StringUtils.isEmpty(spLesson.getLessonSource())){
			spLesson.setLessonSource(LessonSourceType.LOCAL);
		}
		// 发布状态
		if(StringUtils.isEmpty(spLesson.getPublishState())){
			spLesson.setPublishState(PublishState.UN_PUBLISH);
		}
		super.save(spLesson);
		// 更新套课课程数量
		if(StringUtils.isNotEmpty(spLesson.getCourseId())){
			spCourseDao.updateLessonNum(spLesson.getCourseId());
		}
	}
	
	@Transactional(value="transactionManager",readOnly = false)
	public void delete(SpLesson spLesson) {
		spLesson.preUpdate();
		// 删除课程/路演
		dao.delete(spLesson);
		// 更新套课课程数量
		if(StringUtils.isNotEmpty(spLesson.getCourseId())){
			spCourseDao.updateLessonNum(spLesson.getCourseId());
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lessonId", spLesson.getId());
		List<SpProject> spProjects = spProjectDao.findAllList(param);
		if(ListUtils.isNotEmptyList(spProjects)){
			for (SpProject spProject : spProjects) {
				spProjectsService.delete(spProject);
			}
		}
	}
	
	@Transactional(value="transactionManager",readOnly = false)
	public void deletePublishLessonList(List<SpLesson> spLessonList) {
		for (SpLesson spLesson : spLessonList) {
			this.deletePublishLesson(spLesson);
		}
	}
	
	public List<SpLesson> findAllList(Map<String,Object> param){
		return dao.findAllList(param);
	}
	
	/**
	 * 获取套课下所有课程
	 * 2016年8月9日
	 * By zhujie
	 * @param courseId
	 * @return
	 */
	public List<SpLesson> findAllListByCouresId(String courseId){
		if(StringUtils.isEmpty(courseId)){
			return new ArrayList<SpLesson>();
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("courseId", courseId);
		return dao.findAllList(param);
	}
	
	public StudioView makeCcRoom(SpLesson spLesson){
		Map<String, String> treeMap = new TreeMap<String, String>();
		String key=CCConfig.key;
		treeMap.put("userid", CCConfig.userid);
		treeMap.put("templatetype",CCConfig.TEMPLATETYPE_FIVE);
		treeMap.put("authtype", CCConfig.AUTHTYPE_TWO);
		treeMap.put("barrage", "0");	
		treeMap.put("foreignpublish", "1");	
		treeMap.put("name",spLesson.getBroadcastName() );
		treeMap.put("desc", spLesson.getBroadcastDesc() );
		treeMap.put("publisherpass", spLesson.getBroadcastPass() );
		treeMap.put("assistantpass", spLesson.getBroadcastPass() );
		String qs = APIServiceFunction.createQueryString(treeMap);
		
		//生成时间片
		long time = new Date().getTime() / 1000;	
		String hash = Md5Encrypt.md5(String.format("%s&time=%s&salt=%s", qs, time, key));
		
		
		String address = CCConfig.api_createRoom+"?"+qs+"&time="+time+"&hash="+hash;
		String responseStr = APIServiceFunction.HttpRetrieve(address);
		ObjectMapper mapper = new ObjectMapper();
		StudioView studioView = null;
		try {
			studioView = mapper.readValue(responseStr, StudioView.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return studioView;
	}
	
	/**
	 * 更新课程
	 * 2016年8月10日
	 * By zhujie
	 * @param spLesson
	 */
	@Transactional(value="transactionManager",readOnly = false)
	public void updatePublicStatus(SpLesson spLesson) {
		SpLesson update = new SpLesson();
		if(StringUtils.isEmpty(spLesson.getId()) || StringUtils.isEmpty(spLesson.getPublishState())){
			throw new ServiceException("更新课程发布状态异常");
		}
		update.setId(spLesson.getId());
		update.setPublishState(spLesson.getPublishState());
		update.preUpdate();
		dao.update(update);
	}
	
	/**
	 * 订阅平台课程/路演
	 * 2016年8月31日
	 * By zhujie
	 * @param spLesson
	 * @param publishLesson
	 * @param categoryId
	 * @return
	 */
	@Transactional(value="transactionManager",readOnly = false)
	public SpLesson subscribePublish(SpLesson spLesson, PublishLesson publishLesson, String categoryId){
		// 课程/路演信息
		SpLesson plLesson = publishLesson.getSpLesson();
		String modal = LessonType.LESSON.equals(plLesson.getLessonType()) ? "课程" : "路演";
		SpLesson oldLesson = dao.get(publishLesson.getSpLesson().getId());
		if(oldLesson !=null && !stateConstants.ISDEL.equals(oldLesson.getDelFlag())){
			throw new ServiceException("已经订阅过该"+modal);
		}
		// 节次信息使用本地的
		SpSection spSection = null;
		if(StringUtils.isNotEmpty(spLesson.getSectionId())){
			spSection = spSectionDao.get(spLesson.getSectionId());
			if(spSection==null){
				throw new ServiceException("节次不存在");
			}
		}else{
			Map<String,Object> sectionParam = new HashMap<String,Object>();
			sectionParam.put("starttimeFrom", plLesson.getLessonStarttime());
			sectionParam.put("endtimeTo", plLesson.getLessonEndtime());
			List<SpSection> l = spSectionDao.findAllList(sectionParam);
			if(l.size()==0 || l.size() > 1){
				throw new ServiceException("无法将该"+modal+"归纳到本地某一节次");
			}else{
				spSection = l.get(0);
			}
		}
		
		plLesson.setCourseId(spLesson.getCourseId());
		plLesson.setSectionId(spSection.getId());
		plLesson.setLessonStarttime(spSection.getStartTime());
		plLesson.setLessonEndtime(spSection.getEndTime());
		plLesson.setLessonNumber(spLesson.getLessonNumber());
		plLesson.setLessonAdress(spLesson.getLessonAdress());
		plLesson.setPublishState(PublishState.PLAT);
		plLesson.setLessonSource(LessonSourceType.PLAT);// 课程来源：平台
		
		// 再次检查是否时间冲突
		Map<String,Object> tParam = new HashMap<String,Object>();
		tParam.put("checkTimeParam",  Arrays.asList(plLesson));
		tParam.put("checkExcludedIds",  Arrays.asList(plLesson.getId()));// 排除自身课程id
		List<SpLesson> conflictList = this.findAllList(tParam);
		if(conflictList.size()>0){
			String msg="订阅失败:";
			msg += "<br/>";
			msg += "与课程/路演【"+conflictList.get(0).getName()+"】"
						+"("+DateUtils.formatDate(conflictList.get(0).getLessonDate(),"yyyy/MM/dd") 
						+" "+conflictList.get(0).getLessonStarttime()+"~"+ conflictList.get(0).getLessonEndtime() +")产生冲突";
				msg += "<br/>"; 
			throw new ServiceException(msg);
		}
		
		plLesson.preUpdate();
		if(oldLesson!=null){
			dao.update(plLesson);
		}else{
			dao.insert(plLesson);
		}
		
		if(LessonType.LESSON.equals(plLesson.getLessonType())){
			// 订阅课程
			return this.subscribePublishLesson(publishLesson, categoryId);
			
		}else if(LessonType.ROADSHOW.equals(plLesson.getLessonType())){
			// 订阅路演
			return this.subscribePublishRoadshow(publishLesson);
		}else{
			throw new ServiceException("类型数据错误");
		}
	}
	
	/**
	 * 订阅平台课程
	 * 2016年8月12日
	 * By zhujie
	 * @param publishLesson
	 * @return
	 */
	private SpLesson subscribePublishLesson(PublishLesson publishLesson, String categoryId){
		
		// 套课信息
		if(StringUtils.isNotEmpty(categoryId)){
			// 选择的套课分类不为空，则本地没有套课相关信息，做保存操作
			SpCourse plCourse = publishLesson.getSpCourse();
			if(plCourse == null){
				throw new ServiceException("套课信息订阅失败");
			}
			plCourse.setCategoryId(categoryId);
			plCourse.setSpaceId(SpaceUtils.getSpaceId());
			plCourse.setCourseSource(LessonSourceType.PLAT);//套课来源：平台
			plCourse.preUpdate();
			spCourseDao.insert(plCourse);
			
			//套课资料
			List<SpCourseResource> courseResourceList = publishLesson.getSpCourseResource();
			if(courseResourceList!=null){
				for (SpCourseResource spCourseResource : courseResourceList) {
					if(spCourseResourceDao.get(spCourseResource.getId())!=null){
						spCourseResourceDao.update(spCourseResource);
					}else{
						spCourseResourceDao.insert(spCourseResource);
					}				
				}
			}
		}
		
						
		// 讲义信息
		SpHandouts spHandouts = publishLesson.getSpHandouts();
		if(spHandouts!=null){
			if(spHandoutsDao.get(spHandouts.getId()) !=null){
				spHandouts.preUpdate();
				spHandoutsDao.update(spHandouts);
			}else{
				spHandouts.preUpdate();
				spHandoutsDao.insert(spHandouts);
			}
		}
		
		
		// 老师信息
		SpUser teacher = publishLesson.getTeacher();
		if(teacher == null){
			throw new ServiceException("老师信息订阅失败");
		}
		// 订阅老师部分信息
		SpUser teacherLocal = new SpUser();
		teacherLocal.setId(teacher.getId());//id
		teacherLocal.setName(teacher.getName());//老师名字
		teacherLocal.setSpaceId(teacher.getSpaceId());//空间id
		teacherLocal.setRemarks(teacher.getRemarks());//老师简介
		teacherLocal.setCreateBy(teacher.getCreateBy());
		teacherLocal.setCreateDate(teacher.getCreateDate());
		teacherLocal.setUpdateBy(teacher.getUpdateBy());
		teacherLocal.setUpdateDate(teacher.getUpdateDate());
		teacherLocal.setDelFlag(teacher.getDelFlag());
		if(spUserDao.get(teacherLocal.getId())!=null){
			spUserDao.update(teacherLocal);
		}else{
			spUserDao.insert(teacherLocal);
		}
		
		// 试卷、试题、材料信息
		if(publishLesson.getExamination() != null){
			// 试卷同步
			if(examinationDao.get(publishLesson.getExamination().getId())!=null){
				examinationDao.update(publishLesson.getExamination());
			}else{
				examinationDao.insert(publishLesson.getExamination());
			}
			
			// 试题同步
			if(ListUtils.isNotEmptyList(publishLesson.getProblems())){
				for (SpProblems spProblem : publishLesson.getProblems()) {
					if(problemsDao.get(spProblem.getId())!=null){
						problemsDao.update(spProblem);
					}else{
						problemsDao.insert(spProblem);
					}
				}
			}
			
			// 材料同步
			if(ListUtils.isNotEmptyList(publishLesson.getMaterials())){
				for (SpMaterials spMaterial : publishLesson.getMaterials()) {
					if(spMaterialsDao.get(spMaterial.getId())!=null){
						spMaterialsDao.update(spMaterial);
					}else{
						spMaterialsDao.insert(spMaterial);
					}
				}
			}
		}
		
		// 更新套课课程数量
		spCourseDao.updateLessonNum(publishLesson.getSpLesson().getCourseId());
		
		return dao.get(publishLesson.getSpLesson().getId());
	}
	
	/**
	 * 订阅平台路演
	 * 2016年8月12日
	 * By zhujie
	 * @param publishLesson
	 * @return
	 */
	@Transactional(value="transactionManager",readOnly = false)
	public SpLesson subscribePublishRoadshow(PublishLesson publishLesson){
		// 项目信息同步
		if(ListUtils.isNotEmptyList(publishLesson.getSpProjects())){
			for (SpProject spProject : publishLesson.getSpProjects()) {
				if(spProjectDao.get(spProject.getId())!=null){
					spProjectDao.update(spProject);
				}else{
					spProjectDao.insert(spProject);
				}
			}			
		}
		// 演讲人同步
		SpUser teacher = publishLesson.getTeacher();
		if(teacher == null){
			throw new ServiceException("演讲人信息订阅失败");
		}
		// 订阅演讲人部分信息
		SpUser teacherLocal = new SpUser();
		teacherLocal.setId(teacher.getId());//id
		teacherLocal.setName(teacher.getName());//演讲人名字
		teacherLocal.setSpaceId(teacher.getSpaceId());//空间id
		teacherLocal.setRemarks(teacher.getRemarks());//演讲人简介
		teacherLocal.setCreateBy(teacher.getCreateBy());
		teacherLocal.setCreateDate(teacher.getCreateDate());
		teacherLocal.setUpdateBy(teacher.getUpdateBy());
		teacherLocal.setUpdateDate(teacher.getUpdateDate());
		teacherLocal.setDelFlag(teacher.getDelFlag());
		if(spUserDao.get(teacherLocal.getId())!=null){
			spUserDao.update(teacherLocal);
		}else{
			spUserDao.insert(teacherLocal);
		}
		if(spUserDao.get(teacher.getId())!=null){
			spUserDao.update(teacher);
		}else{
			spUserDao.insert(teacher);
		}
		// 项目成员同步
		if(ListUtils.isNotEmptyList(publishLesson.getSpProjectUsers())){
			for (SpProjectUser spProjectUser : publishLesson.getSpProjectUsers()) {
				if(spProjectUserDao.get(spProjectUser.getId())!=null){
					spProjectUserDao.update(spProjectUser);
				}else{
					spProjectUserDao.insert(spProjectUser);
				}
				
			}			
		}
		// 项目资源同步
		if(ListUtils.isNotEmptyList(publishLesson.getSpProjectResources())){
			for (SpProjectResource spProjectResource : publishLesson.getSpProjectResources()) {
				if(spProjectResourceDao.get(spProjectResource.getId())!=null){
					spProjectResourceDao.update(spProjectResource);
				}else{
					spProjectResourceDao.insert(spProjectResource);
				}
				
			}			
		}
		return dao.get(publishLesson.getSpLesson().getId());
	}
	
	//根据老师ID查询课程列表
	public List<SpLesson> selectLessonByTeacherId(String teacherId){
		return dao.selectLessonByTeacherId(teacherId);
	}
	
	/**
	 * 删除平台课程/路演
	 * 2016年8月19日
	 * By zhujie
	 * @param spLesson
	 */
	@Transactional(value="transactionManager",readOnly = false)
	public void deletePublishLesson(SpLesson spLesson){
		try{
			// 删除课程
			dao.delete(spLesson);			
			// 删除课程讲义
			spHandoutsDao.deleteHandoutsByLessonId(spLesson.getId());
			// 更新套课课程数量
			spCourseDao.updateLessonNum(spLesson.getCourseId());
			// 删除试卷
			SpExamination se = examinationDao.findByLessonId(spLesson.getId());
			examinationDao.deleteByLessonId(spLesson.getId());
			// 删除试题
			List<SpProblems> problems = new ArrayList<SpProblems>();
			if(se!=null && StringUtils.isNotEmpty(se.getId())){
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("examinationId", se.getId());
				problems = problemsDao.findAllList(params);
				problemsDao.deleteByExaminationId(se.getId());
			}
			
			// 删除材料
			if(ListUtils.isNotEmptyList(problems)){
				for (SpProblems spProblems : problems) {
					if(StringUtils.isNotEmpty(spProblems.getMaterialId())){
						spMaterialsDao.delete(new SpMaterials(spProblems.getMaterialId()));
					}				
				}
			}			
			
		}catch(Exception e){
			logger.error("删除课程失败",e);
			throw new ServiceException("删除课程失败:"+e.getMessage());
		}
	}
	
	/**
	 * 删除平台路演
	 * 2016年8月19日
	 * By zhujie
	 * @param spLesson
	 */
	@SuppressWarnings("unchecked")
	@Transactional(value="transactionManager",readOnly = false)
	public void deletePublishRoadshow(SpLesson spLesson){
		try{
			// 删除路演
			dao.delete(spLesson);			
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("lessonId", spLesson.getId());
			List<SpProject> spProjects = spProjectDao.findAllList(param);
			if(ListUtils.isNotEmptyList(spProjects)){
				// 删除项目
				spProjectDao.deleteByLessonId(spLesson.getId());
				// 删除项目成员
				spProjectUserDao.deleteByProjectIds(Collections3.extractToList(spProjects,"id"));
				// 删除项目资源
				spProjectResourceDao.deleteByProjectIds(Collections3.extractToList(spProjects,"id"));
			}	
			
		}catch(Exception e){
			logger.error("删除路演失败",e);
			throw new ServiceException("删除路演失败:"+e.getMessage());
		}
	}
	
	//查询课程是否有关联的试卷
	public int checkExam(String lessonId){
		return lessonDao.checkExam(lessonId);
	}
}