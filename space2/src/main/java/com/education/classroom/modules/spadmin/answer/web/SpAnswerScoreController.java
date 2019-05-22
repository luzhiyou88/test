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
import com.education.classroom.core.modules.spadmin.answer.entity.SpAnswerScore;
import com.education.classroom.core.modules.spadmin.fortesting.service.SpAnswerScoreService;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.utils.StringUtils;

/**
 * 答案Controller
 * @author 路志友
 * @version 2016/08/18
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/answer/spAnswerScore")
public class SpAnswerScoreController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpAnswerScoreService spAnswerScoreService;
	
	@ModelAttribute
	public SpAnswerScore get(@RequestParam(required=false) String id) {
		SpAnswerScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spAnswerScoreService.get(id);
		}
		if (entity == null){
			entity = new SpAnswerScore();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpAnswerScore spAnswerScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		 
		User user = UserUtils.getUser();
		Map<?,?> filters = request.getParameterMap();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap = CommonUtils.copyQueryMap(filters, queryMap);
		if(user.isAdmin()){
			queryMap.put("createBy",user.getId());
		}
		queryMap.put("delFlag","0");
		Page<SpAnswerScore> page = spAnswerScoreService.findPage(new Page<SpAnswerScore>(request, response),  queryMap); 
		model.addAttribute("page", page);
		return "modules/spadmin/answer/spAnswerScoreList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpAnswerScore spAnswerScore, Model model) {
		model.addAttribute("spAnswerScore", spAnswerScore);
		return "modules/spadmin/answer/spAnswerScoreForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpAnswerScore spAnswerScore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spAnswerScore)){
			return form(spAnswerScore, model);
		}
		spAnswerScoreService.save(spAnswerScore);
		addMessage(redirectAttributes, "保存答案成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/answer/spAnswerScore/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpAnswerScore spAnswerScore, RedirectAttributes redirectAttributes) {
		spAnswerScoreService.delete(spAnswerScore);
		addMessage(redirectAttributes, "删除答案成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/answer/spAnswerScore/?repage";
	}

}