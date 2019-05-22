/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.user.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.resource.service.SpUtilService;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.user.service.SpUserService;
import com.education.classroom.utils.StringUtils;

/**
 * 用户管理Controller
 * @author 边磊
 * @version 2016/08/06
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/user/spUser")
public class SpUserController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpUserService spUserService;
	@Autowired
	private SpUtilService spUtilService;
	
	@ModelAttribute
	public SpUser get(@RequestParam(required=false) String id) {
		SpUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spUserService.get(id);
		}
		if (entity == null){
			entity = new SpUser();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpUser spUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Map<?,?> filter = request.getParameterMap();
		Map<String,Object>  queryMap = new HashMap<String,Object>();
		queryMap = CommonUtils.copyQueryMap(filter, queryMap);
		if (!user.isAdmin()) {
			queryMap.put("createBy", user.getId());
		}
		//queryMap.put("delFlag", "0");
		
		Page<SpUser> page = spUserService.findPage(new Page<SpUser>(request, response), queryMap); 
		model.addAttribute("page", page);
		
		//查询专业和班级的下拉框
		List<Map<String,String>> specialtyList=spUtilService.getSpecialtySelect();
		model.addAttribute("specialtyList", specialtyList);
		List<Map<String,String>> classList=spUtilService.getClassSelect();
		model.addAttribute("classList", classList);
		//重置查询条件
		model.addAttribute("spUser", spUser);
		
		return "modules/spadmin/user/spUserList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpUser spUser, Model model) {
		//查询专业和班级的下拉框
		List<Map<String,String>> specialtyList=spUtilService.getSpecialtySelect();
		model.addAttribute("specialtyList", specialtyList);
		List<Map<String,String>> classList=spUtilService.getClassSelect();
		model.addAttribute("classList", classList);
		
		model.addAttribute("spUser", spUser);
		return "modules/spadmin/user/spUserForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpUser spUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spUser)){
			return form(spUser, model);
		}
		spUserService.save(spUser);
		addMessage(redirectAttributes, "保存用户成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/user/spUser/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpUser spUser, RedirectAttributes redirectAttributes) {
		spUserService.delete(spUser);
		addMessage(redirectAttributes, "关闭用户成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/user/spUser/?repage";
	}
	@RequestMapping(value = "revert")
	public String revert(SpUser spUser, RedirectAttributes redirectAttributes) {
		spUserService.revert(spUser);
		addMessage(redirectAttributes, "启用用户成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/user/spUser/?repage";
	}

}