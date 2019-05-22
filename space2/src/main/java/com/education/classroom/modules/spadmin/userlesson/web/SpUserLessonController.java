/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.userlesson.web;

import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.userlesson.entity.SpUserLesson;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.userlesson.service.SpUserLessonService;
import com.education.classroom.utils.StringUtils;

/**
 * 用户课程关联增删该查Controller
 * @author 赵新月
 * @version 2016/08/19
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/userlesson/spUserLesson")
public class SpUserLessonController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpUserLessonService spUserLessonService;
	
	@ModelAttribute
	public SpUserLesson get(@RequestParam(required=false) String id) {
		SpUserLesson entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spUserLessonService.get(id);
		}
		if (entity == null){
			entity = new SpUserLesson();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpUserLesson spUserLesson, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<?, ?> filters = request.getParameterMap();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap = CommonUtils.copyQueryMap(filters, queryMap);
		Page<SpUserLesson> page = spUserLessonService.findPage(new Page<SpUserLesson>(request, response), queryMap); 
		model.addAttribute("page", page);
		return "modules/spadmin/userlesson/spUserLessonList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpUserLesson spUserLesson, Model model) {
		model.addAttribute("spUserLesson", spUserLesson);
		return "modules/spadmin/userlesson/spUserLessonForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpUserLesson spUserLesson, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spUserLesson)){
			return form(spUserLesson, model);
		}
		spUserLessonService.save(spUserLesson);
		addMessage(redirectAttributes, "保存用户相关课程成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/userlesson/spUserLesson/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpUserLesson spUserLesson, RedirectAttributes redirectAttributes) {
		spUserLessonService.delete(spUserLesson);
		addMessage(redirectAttributes, "删除用户相关课程成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/userlesson/spUserLesson/?repage";
	}

}