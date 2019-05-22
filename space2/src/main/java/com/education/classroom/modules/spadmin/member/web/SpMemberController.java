/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.member.web;

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
import com.education.classroom.core.modules.spadmin.member.entity.SpMember;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.member.service.SpMemberService;
import com.education.classroom.utils.StringUtils;
/**
 * 会员管理Controller
 * @author 杨立明
 * @version 2016/08/19
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/member/spMember")
public class SpMemberController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpMemberService spMemberService;
	
	@ModelAttribute
	public SpMember get(@RequestParam(required=false) String id) {
		SpMember entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spMemberService.get(id);
		}
		if (entity == null){
			entity = new SpMember();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpMember spMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Map<?,?> filter = request.getParameterMap();
		Map<String,Object>  queryMap = new HashMap<String,Object>();
		queryMap = CommonUtils.copyQueryMap(filter, queryMap);
		if (!user.isAdmin()) {
			queryMap.put("createBy", user.getId());
		}
		queryMap.put("delFlag", "0");
		queryMap.put("DEL_FLAG_NORMAL", "0");
		
		Page<SpMember> page = spMemberService.findPage(new Page<SpMember>(request, response), queryMap); 
		model.addAttribute("page", page);
		return "modules/spadmin/member/spMemberList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpMember spMember, Model model) {
		model.addAttribute("spMember", spMember);
		return "modules/spadmin/member/spMemberForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpMember spMember, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("================================================"+spMember);
		if (!beanValidator(model, spMember)){
			return form(spMember, model);
		}
		spMemberService.save(spMember);
		addMessage(redirectAttributes, "保存会员成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/member/spMember/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpMember spMember, RedirectAttributes redirectAttributes) {
		spMemberService.delete(spMember);
		addMessage(redirectAttributes, "删除会员成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/member/spMember/?repage";
	}

}