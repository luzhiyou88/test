/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.answer.web;

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
import com.education.classroom.core.modules.spadmin.answer.entity.SpAnswer;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.answer.service.SpAnswerService;
import com.education.classroom.utils.StringUtils;

/**
 * 答案Controller
 * @author 路志友
 * @version 2016/08/18
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/answer/spAnswer")
public class SpAnswerController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpAnswerService spAnswerService;
	
	@ModelAttribute
	public SpAnswer get(@RequestParam(required=false) String id) {
		SpAnswer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spAnswerService.get(id);
		}
		if (entity == null){
			entity = new SpAnswer();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpAnswer spAnswer, HttpServletRequest request, HttpServletResponse response, Model model) {
		  
		User user = UserUtils.getUser();
		Map<?,?> filters = request.getParameterMap();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap = CommonUtils.copyQueryMap(filters, queryMap);
		if(user.isAdmin()){
			queryMap.put("createBy",user.getId());
		}
		queryMap.put("delFlag","0");
		Page<SpAnswer> page = spAnswerService.findPage(new Page<SpAnswer>(request, response),  queryMap); 
		model.addAttribute("page", page);
		return "modules/spadmin/answer/spAnswerList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpAnswer spAnswer, Model model) {
		model.addAttribute("spAnswer", spAnswer);
		return "modules/spadmin/answer/spAnswerForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpAnswer spAnswer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spAnswer)){
			return form(spAnswer, model);
		}
		spAnswerService.save(spAnswer);
		addMessage(redirectAttributes, "保存答案成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/answer/spAnswer/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpAnswer spAnswer, RedirectAttributes redirectAttributes) {
		spAnswerService.delete(spAnswer);
		addMessage(redirectAttributes, "删除答案成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/answer/spAnswer/?repage";
	}

}