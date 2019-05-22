/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.grouptopic.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.grouptopic.entity.SpGroupTopic;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.grouptopic.service.SpGroupTopicService;

/**
 * 小组话题表Controller
 * @author shangjunwei
 * @version 2016/08/10
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/grouptopic/spGroupTopic")
public class SpGroupTopicController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpGroupTopicService spGroupTopicService;
	
	@ModelAttribute
	public SpGroupTopic get(@RequestParam(required=false) String id) {
		SpGroupTopic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spGroupTopicService.get(id);
		}
		if (entity == null){
			entity = new SpGroupTopic();
		}
		return entity;
	}
	
	
	//@RequestMapping(value = {"list", ""})
	/*public String list(SpGroupTopic spGroupTopic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpGroupTopic> page = spGroupTopicService.findPage(new Page<SpGroupTopic>(request, response), spGroupTopic); 
		model.addAttribute("page", page);
		return "modules/spadmin/grouptopic/spGroupTopicList";
	}*/

	
	@RequestMapping(value = "form")
	public String form(SpGroupTopic spGroupTopic, Model model) {
		model.addAttribute("spGroupTopic", spGroupTopic);
		return "modules/spadmin/grouptopic/spGroupTopicForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpGroupTopic spGroupTopic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spGroupTopic)){
			return form(spGroupTopic, model);
		}
		spGroupTopicService.save(spGroupTopic);
		addMessage(redirectAttributes, "保存小组话题表成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/grouptopic/spGroupTopic/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpGroupTopic spGroupTopic, RedirectAttributes redirectAttributes) {
		spGroupTopicService.delete(spGroupTopic);
		addMessage(redirectAttributes, "删除小组话题表成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/grouptopic/spGroupTopic/?repage";
	}

}