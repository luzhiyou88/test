package com.education.classroom.modules.spadmin.blackboard.web;

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

import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.blackboard.entity.SpBlackboard;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.blackboard.service.SpBlackboardService;
import com.education.classroom.utils.StringUtils;

/**
 * 板书Controller
 * @author zhangyongsheng
 * @version 2016/08/11
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/blackboard/spBlackboard")
public class SpBlackboardController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpBlackboardService spBlackboardService;
	
	@ModelAttribute
	public SpBlackboard get(@RequestParam(required=false) String id) {
		SpBlackboard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spBlackboardService.get(id);
		}
		if (entity == null){
			entity = new SpBlackboard();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpBlackboard spBlackboard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpBlackboard> page = spBlackboardService.findPage(new Page<SpBlackboard>(request, response), spBlackboard); 
		model.addAttribute("page", page);
		return "modules/spadmin/blackboard/spBlackboardList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpBlackboard spBlackboard, Model model) {
		model.addAttribute("spBlackboard", spBlackboard);
		return "modules/spadmin/blackboard/spBlackboardForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpBlackboard spBlackboard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spBlackboard)){
			return form(spBlackboard, model);
		}
		spBlackboardService.save(spBlackboard);
		addMessage(redirectAttributes, "保存板书成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/blackboard/spBlackboard/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpBlackboard spBlackboard, RedirectAttributes redirectAttributes) {
		spBlackboardService.delete(spBlackboard);
		addMessage(redirectAttributes, "删除板书成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/blackboard/spBlackboard/?repage";
	}

}