/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.comment.web;

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
import com.education.classroom.core.modules.spadmin.comment.entity.SpComment;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.comment.service.SpCommentService;

/**
 * 评论回复Controller
 * @author 尚军伟
 * @version 2016/08/10
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/comment/spComment")
public class SpCommentController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpCommentService spCommentService;
	
	@ModelAttribute
	public SpComment get(@RequestParam(required=false) String id) {
		SpComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spCommentService.get(id);
		}
		if (entity == null){
			entity = new SpComment();
		}
		return entity;
	}
	
	
	/*@RequestMapping(value = {"list", ""})
	public String list(SpComment spComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpComment> page = spCommentService.findPage(new Page<SpComment>(request, response), spComment); 
		model.addAttribute("page", page);
		return "modules/spadmin/comment/spCommentList";
	}*/

	
	@RequestMapping(value = "form")
	public String form(SpComment spComment, Model model) {
		model.addAttribute("spComment", spComment);
		return "modules/spadmin/comment/spCommentForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpComment spComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spComment)){
			return form(spComment, model);
		}
		spCommentService.save(spComment);
		addMessage(redirectAttributes, "保存评论回复成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/comment/spComment/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpComment spComment, RedirectAttributes redirectAttributes) {
		spCommentService.delete(spComment);
		addMessage(redirectAttributes, "删除评论回复成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/comment/spComment/?repage";
	}

}