
package com.education.classroom.modules.spadmin.roadshow.web;

import java.util.ArrayList;
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
import com.education.classroom.core.common.type.LessonType;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.member.type.PublishState;
import com.education.classroom.core.modules.spadmin.lesson.entity.PublishLesson;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.modules.spadmin.project.entity.SpProject;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectResource;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectUser;
import com.education.classroom.core.modules.spadmin.section.entity.SpSection;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.constants.stateConstants;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.lesson.service.SpLessonService;
import com.education.classroom.modules.spadmin.projects.service.SpProjectResourceService;
import com.education.classroom.modules.spadmin.projects.service.SpProjectUserService;
import com.education.classroom.modules.spadmin.projects.service.SpProjectsService;
import com.education.classroom.modules.spadmin.section.service.SpSectionService;
import com.education.classroom.modules.spadmin.user.service.SpUserService;

import com.education.classroom.utils.Collections3;
import com.education.classroom.utils.ListUtils;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 路演管理Controller
 * @Class Name SpRoadshowController
 * @author zhujie
 * @Create In 2016年8月26日
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/roadshow")
public class SpRoadshowController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpLessonService spLessonService;
	@Autowired
	private SpSectionService spSectionService;
	@Autowired
	private SpUserService spUserService;
	
	@Autowired
	private SpProjectsService spProjectsService;
	@Autowired
	private SpProjectUserService spProjectUserService;
	@Autowired
	private SpProjectResourceService spProjectResourceService;
	
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
            queryMap.put("lessonType", LessonType.ROADSHOW);
            page = spLessonService.findList(new Page<SpLesson>(request, response), queryMap);
            
        } catch (Exception e) {
            logger.error("路演列表查询异常", e);
        }
        model.addAttribute("page", page);
		
		return "modules/spadmin/roadshow/spRoadshowList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpLesson spLesson, Model model) {
		// 待编辑的路演
		model.addAttribute("spLesson", spLesson);
		// 节次List查询
		List<SpSection> spSectionList = spSectionService.getAllListForDict();
		model.addAttribute("spSectionList", spSectionList);
		
		// List
		model.addAttribute("entrepreneurList", spUserService.findEntrepreneurList(SpaceUtils.getSpaceId()));
		return "modules/spadmin/roadshow/spRoadshowForm";
	}

	/**
	 * 路演保存
	 * 2016年8月17日
	 * By zhujie
	 * @param lesson
	 * @param deleteLessonIds
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(SpLesson lesson,Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, lesson)){
				return form(lesson, model);
			}
			lesson.setLessonType(LessonType.ROADSHOW);
			spLessonService.save(lesson);	
			addMessage(redirectAttributes, "保存路演成功");
		} catch (Exception e) {
            logger.error("路演更新异常", e);
            addMessage(redirectAttributes, "路演更新异常:"+e.getMessage());
        }
		
		return "redirect:"+Global.getAdminPath()+"/spadmin/roadshow/?repage";
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
			lesson.setLessonType(LessonType.ROADSHOW);
			spLessonService.save(lesson);
			// 检索返回
			lesson = spLessonService.get(lesson.getId());
		}catch(Exception e){
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,e.getMessage()));
		}
		
		return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,null,lesson));
	}
	
	/**
	 * 本地路演删除
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
				return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"路演不存在,请刷新页面"));
			}
		
				spLessonService.delete(spLesson);
				return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE));
		}catch(Exception e){
			logger.error("路演删除失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"路演删除失败:"+e.getMessage()));
		}
	}

	/**
	 * 发布路演
	 * 2016年8月10日
	 * By zhujie
	 * @param spLesson
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "publishLesson")
	@ResponseBody
	public String publishRoadshow(SpLesson spLesson){
		PublishLesson pl = new PublishLesson();
		try{
			// 项目相关
			List<SpProject> spProjects = spProjectsService.findAllListByLessonId(spLesson.getId());
			// 项目成员相关
			List<SpProjectUser> spProjectUsers = new ArrayList<SpProjectUser>();
			// 项目资源相关
			List<SpProjectResource> spProjectResources = new ArrayList<SpProjectResource>();
			if(ListUtils.isNotEmptyList(spProjects)){
				spProjectUsers = spProjectUserService.findAllByProjectIds(Collections3.extractToList(spProjects,"id"));
				spProjectResources = spProjectResourceService.findAllListByProjectIds(Collections3.extractToList(spProjects,"id"));
			}
			pl.setSpLesson(spLesson);
			pl.setSpProjects(spProjects);
			pl.setSpProjectUsers(spProjectUsers);
			pl.setSpProjectResources(spProjectResources);
			
		
				spLesson.setPublishState(PublishState.WAIT_AUDIT);				
				spLessonService.updatePublicStatus(spLesson);
				
			
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"课程发布失败"));
		} catch(Exception e){
			logger.error("课程发布失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,e.getMessage()));
		}
	}
	
	/**
	 * 订阅平台课程
	 * 2016年8月12日
	 * By zhujie
	 * @return
	 */
	@RequestMapping(value = "subscribePublishRoadshow")
	@ResponseBody
	public String subscribePublishRoadshow(String lessonId,SpLesson spLesson){
		try{
			PublishLesson publishLesson = null;
			
			SpLesson lesson = spLessonService.subscribePublish(spLesson,publishLesson,null);
			
			return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,"",lesson));
			
		}catch(Exception e){
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"课程订阅发生异常:"+e.getMessage()));
		}
	}
	
	/**
	 * 检查路演名称是否存在
	 * 2016年9月23日
	 * By zhujie
	 * @param lessonId
	 * @return
	 */
	@RequestMapping(value = "checkRoadshowName")
	@ResponseBody
	public String checkRoadshowName(String id, String name){
		logger.info("校验路演名称的唯一性开始");
		if(StringUtils.isEmpty(name)){
			return stateConstants.TRUE;	
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("lessonType", LessonType.ROADSHOW);
		List<SpLesson> list = spLessonService.findAllList(param);
		if(list.size() > 1 || (list.size()==1 && !list.get(0).getId().equals(id))){
			// 相同套课名称大于2 或者套课名称是1但是并非当前套课的情况
			logger.info("校验路演名称的唯一性结束");
			return stateConstants.FALSE;
		}
		logger.info("校验路演名称的唯一性结束");
		return stateConstants.TRUE;	
	}
	
}