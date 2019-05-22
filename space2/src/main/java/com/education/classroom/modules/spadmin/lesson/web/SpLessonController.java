
package com.education.classroom.modules.spadmin.lesson.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.entity.ReturnMsg;
import com.education.classroom.core.common.type.LessonSourceType;
import com.education.classroom.core.common.type.LessonType;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.exception.ServiceException;
import com.education.classroom.core.member.type.PublishState;
import com.education.classroom.core.modules.spadmin.course.entity.SpCourse;
import com.education.classroom.core.modules.spadmin.course.type.CourseType;
import com.education.classroom.core.modules.spadmin.courseResource.entity.SpCourseResource;
import com.education.classroom.core.modules.spadmin.exam.entity.SpExamination;
import com.education.classroom.core.modules.spadmin.handouts.entity.SpHandouts;
import com.education.classroom.core.modules.spadmin.lesson.entity.PublishLesson;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLessonForm;
import com.education.classroom.core.modules.spadmin.lesson.type.SubscribeState;
import com.education.classroom.core.modules.spadmin.lesson.util.ScheduleHelper;
import com.education.classroom.core.modules.spadmin.material.entity.SpMaterials;
import com.education.classroom.core.modules.spadmin.problems.entity.SpProblems;
import com.education.classroom.core.modules.spadmin.section.entity.SpSection;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.constants.stateConstants;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.core.week.constants.weekConstants;
import com.education.classroom.modules.spadmin.course.service.SpCourseService;
import com.education.classroom.modules.spadmin.courseresource.service.SpCourseResourceService;
import com.education.classroom.modules.spadmin.exam.service.SpExaminationService;
import com.education.classroom.modules.spadmin.handouts.service.SpHandoutsService;
import com.education.classroom.modules.spadmin.lesson.service.SpLessonService;
import com.education.classroom.modules.spadmin.material.service.SpMaterialsService;
import com.education.classroom.modules.spadmin.problems.service.SpProblemsService;
import com.education.classroom.modules.spadmin.section.service.SpSectionService;
import com.education.classroom.modules.spadmin.user.service.SpUserService;

import com.education.classroom.utils.Collections3;
import com.education.classroom.utils.DateUtils;
import com.education.classroom.utils.ListUtils;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 课程管理Controller
 * @author 朱杰
 * @version 2016/08/06
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/lesson/spLesson")
public class SpLessonController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpLessonService spLessonService;
	@Autowired
	private SpCourseService spCourseService;
	@Autowired
	private SpSectionService spSectionService;
	@Autowired
	private SpUserService spUserService;
	@Autowired
	private SpHandoutsService spHandoutsService;
	@Autowired
	private SpCourseResourceService spCourseResourceService;
	
	@Autowired 
	private SpExaminationService spExaminationService;
	@Autowired
	private SpProblemsService spProblemsService;
	@Autowired
	private SpMaterialsService spMaterialsService;
	
	@ModelAttribute
	public SpLesson get(@RequestParam(required=false) String id) {
		SpLesson entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spLessonService.get(id);
		}
		if (entity == null){
			entity = new SpLesson();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpLesson spLesson, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<SpLesson> page = new Page<SpLesson>();
		try {
            User user = UserUtils.getUser();
            Map<?, ?> filters = request.getParameterMap();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap = CommonUtils.copyQueryMap(filters, queryMap);
            if (!user.isAdmin()) {
                queryMap.put("createBy", user.getId());
            }
            queryMap.put("lessonType", LessonType.LESSON);
            page = spLessonService.findList(new Page<SpLesson>(request, response), queryMap);
            
        } catch (Exception e) {
            logger.error("套课列表查询异常", e);
        }
        model.addAttribute("page", page);
		
		return "modules/spadmin/lesson/spLessonList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpLesson spLesson, Model model) {
		// 获取套课下所有课程
		model.addAttribute("spLessonList", spLessonService.findAllListByCouresId(spLesson.getCourseId()));
		// 待编辑的课程
		model.addAttribute("spLesson", spLesson);		
		// 套课List
		model.addAttribute("spCourseList", spCourseService.getAllListForDict());
		// 套课是否是本地的
		SpCourse spCourse = spCourseService.get(spLesson.getCourseId());
		model.addAttribute("spCourse", spCourse);
		//model.addAttribute("localCourse", spCourse!=null && LessonSourceType.LOCAL.equals(spCourse.getCourseSource()));
		return "modules/spadmin/lesson/spLessonForm";
	}
	
	@RequestMapping(value = "getAllListByCouresId")
	@ResponseBody
	public String getAllListByCouresId(String courseId){
		List<SpLesson> list = spLessonService.findAllListByCouresId(courseId);
		return jsonMapper.toJson(list);
	}

	/**
	 * 课程保存
	 * 2016年8月17日
	 * By zhujie
	 * @param lesson
	 * @param deleteLessonIds
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(SpLessonForm lesson,String deleteLessonIds,Model model, RedirectAttributes redirectAttributes) {
		try {
			if(StringUtils.isNotBlank(deleteLessonIds)){
				String[] deleteLessonIdArr = deleteLessonIds.split(",");
				for (String deleteLessonId : deleteLessonIdArr) {
					if(StringUtils.isNotBlank(deleteLessonId)){
						spLessonService.delete(this.get(deleteLessonId));
					}
				}
			}
			if(lesson != null && ListUtils.isNotEmptyList(lesson.getLesson())){
				for (SpLesson elem : lesson.getLesson()) {
					if(StringUtils.isBlank(elem.getCourseId())){
						continue;
					}
					if (!beanValidator(model, elem)){
						return form(elem, model);
					}
					
					spLessonService.save(elem);
				}
			} 			
			addMessage(redirectAttributes, "保存课程成功");
		} catch (Exception e) {
            logger.error("课程更新异常", e);
            addMessage(redirectAttributes, "课程更新异常:"+e.getMessage());
        }
		
		return "redirect:"+Global.getAdminPath()+"/spadmin/lesson/spLesson/?repage";
	}
	
	/**
	 * json格式保存
	 * 2016年8月17日
	 * By zhujie
	 * @param lesson
	 * @param deleteLessonIds
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "saveJson")
	@ResponseBody
	public String saveJson(SpLesson lesson,Model model, RedirectAttributes redirectAttributes) {
		try{
			spLessonService.save(lesson);
			// 检索返回
			lesson = spLessonService.get(lesson.getId());
		}catch(Exception e){
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,e.getMessage()));
		}
		
		return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,null,lesson));
	}
	
	/**
	 * 课程删除
	 * 2016年8月17日
	 * By zhujie
	 * @param spLesson
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SpLesson spLesson, RedirectAttributes redirectAttributes) {		
		try{
			if(StringUtils.isEmpty(spLesson.getId())){
				return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"课程不存在,请刷新页面"));
			}
				spLessonService.delete(spLesson);
			
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"课程已发布，无法删除"));
		}catch(Exception e){
			logger.error("课程删除失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"课程删除失败:"+e.getMessage()));
		}
	}
	
	
	/**
	 * 校验课程是否有关联试卷
	 * 2016年9月19日
	 * By shangjunwei
	 * @param id
	 * @return int
	 */
	@ResponseBody
	@RequestMapping(value = "checkExam")
	public String checkExam(String lessonId){
		logger.info("校验课程是否有关联试卷开始");
		int num = spLessonService.checkExam(lessonId);
		if(num > 0){
			logger.info("校验课程是否有关联试卷结束");
			return stateConstants.TRUE;
		} else {
			logger.info("校验课程是否有关联试卷结束");
			return stateConstants.FALSE;
		}
		
	}
	
	/**
	 * 课程删除
	 * 2016年8月19日
	 * By shangjunwei
	 * @param spLesson
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "deleteLesson")
	@ResponseBody
	public String deleteLesson(SpLesson spLesson, RedirectAttributes redirectAttributes) {		
		try{
			// 查询平台是否未删除
			    spLessonService.delete(spLesson);
			
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"课程已发布或者待审核，删除失败"));
		}catch(Exception e){
			logger.error("课程删除失败",e);
		}
		return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"删除失败"));
	}
	
	
	
	/**
	 * 课程弹出框：课程查看/编辑/添加详细
	 * 2016年8月17日
	 * By zhujie
	 * @param spLesson
	 * @param editForm
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "showModal")
	public String showModal(SpLesson spLesson,SpLessonForm editForm, Model model, RedirectAttributes redirectAttributes) {
		
		if(editForm.getLesson()==null){
			model.addAttribute("spLesson", spLesson);
		}else{
			for (SpLesson editLesson : editForm.getLesson()) {
				if(StringUtils.isNotEmpty(editLesson.getName())){
					model.addAttribute("spLesson", editLesson);
					break;
				}
			}
		}
		// 节次List查询
		List<SpSection> spSectionList = spSectionService.getAllListForDict();
		model.addAttribute("spSectionList", spSectionList);
		
		// 老师List
		model.addAttribute("spTeacherList", spUserService.findTeacherList(SpaceUtils.getSpaceId()));
		
		return "modules/spadmin/lesson/spLessonModal";
	}
	
	/**
	 * 获取该日期内余下的节次
	 * 2016年8月10日
	 * By zhujie
	 * @param lessonDate
	 * @return
	 */
	@RequestMapping(value = "remainSectionByDate")
	@ResponseBody
	public String remainSectionByDate(Date lessonDate){
		List<SpSection> list = spSectionService.getRemainSectionByDate(lessonDate);
		return jsonMapper.toJson(list);
	}

	/**
	 * 发布课程
	 * 2016年8月10日
	 * By zhujie
	 * @param spLesson
	 * @return
	 */
	@RequestMapping(value = "publishLesson")
	@ResponseBody
	public String publishLesson(SpLesson spLesson){
		PublishLesson pl = new PublishLesson();
		try{
			// 套课相关
			SpCourse spCourse = spCourseService.get(spLesson.getCourseId());
			// 课程讲义相关
			SpHandouts spHandouts = spHandoutsService.getByLessonId(spLesson.getId());
			// 套课资源相关
			List<SpCourseResource> spCourseResourceLst = spCourseResourceService.getByCourseId(spLesson.getCourseId());
			// 试卷相关
			SpExamination spExamination = spExaminationService.findByLessonId(spLesson.getId());
			// 试题相关
			List<SpProblems> problemsList = new ArrayList<SpProblems>();
			if(spExamination!=null && StringUtils.isNotEmpty(spExamination.getId())){				
				problemsList = spProblemsService.findByExaminationId(spExamination.getId());
			}
			// 材料相关
			List<SpMaterials> materialsList = new ArrayList<SpMaterials>();
			if(problemsList.size() > 0){
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("ids", Collections3.extractToList(problemsList,"materialId"));
				materialsList = spMaterialsService.findAllList(params);
			}
			
			pl.setSpLesson(spLesson);
			pl.setSpCourse(spCourse);
			pl.setSpHandouts(spHandouts);
			pl.setSpCourseResource(spCourseResourceLst);
			pl.setExamination(spExamination);
			pl.setProblems(problemsList);
			pl.setMaterials(materialsList);
			
			
				// 更新发布状态
				spLesson.setPublishState(PublishState.WAIT_AUDIT);				
				spLessonService.updatePublicStatus(spLesson);
				
				return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE));
		} catch(Exception e){
			logger.error("课程发布失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,e.getMessage()));
		}
	}
	
	/**
	 * 刷新平台发布课程
	 * 2016年8月11日
	 * By zhujie
	 * @param spLesson
	 * @return
	 */
	@RequestMapping(value="refreshPublishStatus")
	@ResponseBody
	public String refreshPublishStatus(SpLesson spLesson){
		try{
			
				
					spLessonService.updatePublicStatus(spLesson);
					return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE));
							
			
		}catch(Exception e){
			logger.error("刷新平台审核课程状态异常",e);
		}
		return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"刷新平台审核课程状态异常"));
	}
	
	/**
	 * 课程表
	 * 2016年8月11日
	 * By zhujie
	 * @param model
	 * @param mode
	 * @return
	 */
	@RequestMapping(value = "schedule")
	public String getWeekSchedule(Model model,String startDate) {
		Date start = null;
		if(StringUtils.isEmpty(startDate)){
			// 默认取今日得前天
			start = DateUtils.addDays(DateUtils.getCurrentDate(), weekConstants.DAY_BEFORE_YESTERDAY);
		}else{
			start = DateUtils.parseDate(startDate);
		}
		Date end = DateUtils.addDays(start,weekConstants.END_OF_WEEK);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lessonDateFrom", start);
		param.put("lessonDateTo", end);
		
		model.addAttribute("spLessonList", spLessonService.findAllList(param));
		
		model.addAttribute("localSpaceId", SpaceUtils.getSpaceId());
		// 节次List查询
		List<SpSection> spSectionList = spSectionService.getAllListForDict();
		model.addAttribute("spSectionList", spSectionList);
		
		// 日期转化
		model.addAttribute("weekDayList", ScheduleHelper.getWeekInfo(start, end));
		
		// 
		model.addAttribute("scheduleDate", start);
		
		
		return "modules/spadmin/lesson/spSchedule";
	}
	
	/**
	 * 获取平台发布的课程列表
	 * 2016年8月12日
	 * By zhujie
	 * @param lessonDate
	 * @param sectionId
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getPublishLesson")
	public String getPublishLesson(Date lessonDate,String sectionId,String courseId, Model model, RedirectAttributes redirectAttributes){
		
		List<SpLesson> publishList = new ArrayList<SpLesson>();
		List<SpLesson> publishLessonList = new ArrayList<SpLesson>();
		List<SpLesson> publishRoadshowList = new ArrayList<SpLesson>();
		
		if(StringUtils.isEmpty(courseId)){
			// 根据日期节次，获取时间段内平台课程 以及 本地课程部分信息
			try{
				
				SpSection s = spSectionService.get(sectionId);
				
				
				if(s!=null && lessonDate!=null){
					SpLesson param = new SpLesson();
					param.setLessonDate(lessonDate);
					param.setLessonStarttimeFrom(s.getStartTime());
					param.setLessonEndtimeTo(s.getEndTime());
					param.setSpaceId(SpaceUtils.getSpaceId());
					param.setPublishState(PublishState.AUDIT_PASSED);// 审核通过
					
					
					
					// 区分课程/路演
					for (SpLesson publish : publishList) {
						if(LessonType.LESSON.equals(publish.getLessonType())){
							publishLessonList.add(publish);
						}else if(LessonType.ROADSHOW.equals(publish.getLessonType())){
							publishRoadshowList.add(publish);
						}
					}
					
					model.addAttribute("publishLessonList", publishLessonList);
					model.addAttribute("publishRoadshowList", publishRoadshowList);
					
					// ****** 以下信息为共通获取   ******
					// 来源:课程表页面
					model.addAttribute("fromSchedule", true);
					// 课程日期
					model.addAttribute("lessonDate", DateUtils.formatDate(lessonDate));
					// 节次信息
					SpSection section = spSectionService.get(sectionId);
					model.addAttribute("spSection", section);
					// 检查是否过去时间
					Date end = DateUtils.convertStringToDate(DateUtils.formatDate(lessonDate)+" "+section.getEndTime()+":00");
					model.addAttribute("isBefore", DateUtils.dateBefore(end,new Date()));
					
					// ****** 以下信息为本地课程添加获取   ******
					// 套课List
					model.addAttribute("spCourseList", spCourseService.getAllListForDict());	
					// 老师List
					model.addAttribute("spTeacherList", spUserService.findTeacherList(SpaceUtils.getSpaceId()));
					
					// ****** 以下信息为本地课程添加获取   ******
					// 演讲人List
					model.addAttribute("entrepreneurList", spUserService.findEntrepreneurList());
				}
			} catch(Exception e){
				logger.error("获取平台课程失败",e);
				addMessage(redirectAttributes, "获取平台课程失败:"+e.getMessage());
			}
			return "modules/spadmin/lesson/spPublishLesson";
		
		}else{
			// 根据套课 获取平台该套课下所有的课程
			try{
				SpCourse course = spCourseService.get(courseId);
				// 根据套课查询
				SpLesson param = new SpLesson();
				param.setCourseId(courseId);
				
				String isConflict = stateConstants.FALSE;
				if(publishLessonList.size() > 0){
					// 根据平台套课，检索本地已订阅的该套课下课程 
					List<String> lessonIds = Collections3.extractToList(publishLessonList,"id");
					Map<String, Object> paramLocal = new HashMap<String, Object>();
					paramLocal.put("checkIds", lessonIds);
					List<SpLesson> lessonLocalList = spLessonService.findAllList(paramLocal);
					
					for (SpLesson plLesson : publishLessonList) {
						SpLesson LessonLocal = Collections3.getElemByProp(lessonLocalList, "id", plLesson.getId());
						if(null == LessonLocal){
							//未从本地订阅,判断是否能够订阅
							Map<String,Object> tParam = new HashMap<String,Object>();
							tParam.put("checkTimeParam",  Arrays.asList(plLesson));
							tParam.put("checkExcludedIds",  Arrays.asList(plLesson.getId()));// 排除自身课程id
							List<SpLesson> conflictList = spLessonService.findAllList(tParam);
							if(conflictList.size() > 0){
								// 时间冲突  无法订阅
								plLesson.setSubscribeState(SubscribeState.NDY.getCode());
								if(CourseType.SF.getCode().equals(course.getCourseType())){
									// 收费套课才做判断
									isConflict = stateConstants.TRUE;
								}
								
							}else{
								// 订阅状态：未订阅
								plLesson.setSubscribeState(SubscribeState.WDY.getCode());
							}
							
						}else{
							// 订阅状态：已订阅
							plLesson.setSubscribeState(SubscribeState.YDY.getCode());
							// 本地保存的信息
							plLesson.setLessonAdress(LessonLocal.getLessonAdress());
							plLesson.setLessonNumber(LessonLocal.getLessonNumber());
						}
					}
				}
				
				model.addAttribute("publishLessonList", publishLessonList);
				model.addAttribute("isConflict", isConflict);
				
			}catch(Exception e){
				logger.error("获取平台课程失败",e);
				addMessage(redirectAttributes, "获取平台课程失败:"+e.getMessage());
			}
			
			return "modules/spadmin/lesson/spPublishLesson2";
		}
	}
	
	/**
	 * 订阅平台课程
	 * 2016年8月12日
	 * By zhujie
	 * @return
	 */
	@RequestMapping(value = "subscribePublishLesson")
	@ResponseBody
	public String subscribePublishLesson(String lessonId,SpLesson spLesson,String categoryId){
		try{
			PublishLesson publishLesson = null;
			
			SpLesson lesson = spLessonService.subscribePublish(spLesson,publishLesson,categoryId);
			
			return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,"",lesson));
			
		}catch(Exception e){
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"课程订阅发生异常:"+e.getMessage()));
		}
	}
	
	/**
	 * 删除平台课程
	 * 2016年8月18日
	 * By zhujie
	 * @param lessonId
	 * @return
	 */
	@RequestMapping(value = "removePublishLesson")
	@ResponseBody
	public String removePublishLesson(String lessonId){
		try{
			SpLesson spLesson = spLessonService.get(lessonId);
			if(spLesson==null ||  stateConstants.ISDEL.equals(spLesson.getDelFlag())){
				throw new ServiceException("课程/路演不存在或已删除");
			}
			if(LessonType.LESSON.equals(spLesson.getLessonType())){
				spLessonService.deletePublishLesson(spLesson);
			}else{
				spLessonService.deletePublishRoadshow(spLesson);
			}			
			
			return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,null,Arrays.asList(spLesson)));
		}catch(Exception e){
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"平台课删除发生异常:"+e.getMessage()));
		}
	}
	
	/**
	 * 删除平台套课下所有课程
	 * 2016年8月18日
	 * By zhujie
	 * @param lessonId
	 * @return
	 */
	@RequestMapping(value = "removePublishLessonByCourse")
	@ResponseBody
	public String removePublishLessonByCourse(String courseId){
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("courseId", courseId);
			param.put("lessonSource", LessonSourceType.PLAT);
			List<SpLesson> spLessonList = spLessonService.findAllList(param);
			if( ListUtils.isEmptyList(spLessonList)){
				throw new ServiceException("该套课下没有课程或已删除");
			}
			spLessonService.deletePublishLessonList(spLessonList);			
			return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,null,spLessonList));
		}catch(Exception e){
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"平台课程删除发生异常:"+e.getMessage()));
		}
	}
	
	/**
	 * 检查课程名称是否存在
	 * 2016年9月23日
	 * By zhujie
	 * @param lessonId
	 * @return
	 */
	@RequestMapping(value = "checkLessonName")
	@ResponseBody
	public String checkLessonName(String id, String name){
		logger.info("校验课程名称的唯一性开始");
		if(StringUtils.isEmpty(name)){
			return stateConstants.TRUE;	
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("lessonType", LessonType.LESSON);
		List<SpLesson> list = spLessonService.findAllList(param);
		if(list.size() > 1 || (list.size()==1 && !list.get(0).getId().equals(id))){
			// 相同套课名称大于2 或者套课名称是1但是并非当前套课的情况
			logger.info("校验课程名称的唯一性结束");
			return stateConstants.FALSE;
		}
		logger.info("校验课程名称的唯一性结束");
		return stateConstants.TRUE;	
	}
}