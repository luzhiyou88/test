package com.education.classroom.modules.spadmin.note.web;

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
import com.education.classroom.core.modules.spadmin.note.entity.SpUserNote;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.note.service.SpUserNoteService;
import com.education.classroom.utils.StringUtils;

/**
 * 笔记Controller
 * @author zhangyongsheng
 * @version 2016/08/11
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/note/spUserNote")
public class SpUserNoteController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpUserNoteService spUserNoteService;
	
	@ModelAttribute
	public SpUserNote get(@RequestParam(required=false) String id) {
		SpUserNote entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spUserNoteService.get(id);
		}
		if (entity == null){
			entity = new SpUserNote();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpUserNote spUserNote, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpUserNote> page = spUserNoteService.findPage(new Page<SpUserNote>(request, response), spUserNote); 
		model.addAttribute("page", page);
		return "modules/spadmin/note/spUserNoteList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpUserNote spUserNote, Model model) {
		model.addAttribute("spUserNote", spUserNote);
		return "modules/spadmin/note/spUserNoteForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpUserNote spUserNote, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spUserNote)){
			return form(spUserNote, model);
		}
		spUserNoteService.save(spUserNote);
		addMessage(redirectAttributes, "保存笔记成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/note/spUserNote/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpUserNote spUserNote, RedirectAttributes redirectAttributes) {
		spUserNoteService.delete(spUserNote);
		addMessage(redirectAttributes, "删除笔记成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/note/spUserNote/?repage";
	}

}