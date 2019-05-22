/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.memberlog.web;

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
import com.education.classroom.core.modules.spadmin.memberlog.entity.SpMemberLog;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.memberlog.service.SpMemberLogService;
import com.education.classroom.utils.StringUtils;

/**
 * 会员记录管理Controller
 * @author 赵新月
 * @version 2016/08/19
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/memberlog/spMemberLog")
public class SpMemberLogController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpMemberLogService spMemberLogService;
	
	@ModelAttribute
	public SpMemberLog get(@RequestParam(required=false) String id) {
		SpMemberLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spMemberLogService.get(id);
		}
		if (entity == null){
			entity = new SpMemberLog();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpMemberLog spMemberLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<?, ?> filters = request.getParameterMap();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap = CommonUtils.copyQueryMap(filters, queryMap);
		Page<SpMemberLog> page = spMemberLogService.findPage(new Page<SpMemberLog>(request, response), queryMap); 
		model.addAttribute("page", page);
		return "modules/spadmin/memberlog/spMemberLogList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpMemberLog spMemberLog, Model model) {
		model.addAttribute("spMemberLog", spMemberLog);
		return "modules/spadmin/memberlog/spMemberLogForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpMemberLog spMemberLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spMemberLog)){
			return form(spMemberLog, model);
		}
		spMemberLogService.save(spMemberLog);
		addMessage(redirectAttributes, "保存会员记录成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/memberlog/spMemberLog/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpMemberLog spMemberLog, RedirectAttributes redirectAttributes) {
		spMemberLogService.delete(spMemberLog);
		addMessage(redirectAttributes, "删除会员记录成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/memberlog/spMemberLog/?repage";
	}

}