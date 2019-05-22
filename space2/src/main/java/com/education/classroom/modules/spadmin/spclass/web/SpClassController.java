/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.spclass.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.enums.DelFlagType;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.spclass.entity.SpClass;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.spclass.service.SpClassService;
import com.education.classroom.modules.spadmin.user.service.SpUserService;
import com.education.classroom.utils.StringUtils;

/**
 * 班级管理Controller
 * 
 * @author 边磊
 * @version 2016/08/05
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/spclass/spClass")
public class SpClassController extends BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SpClassService spClassService;
	@Autowired
	private SpUserService SpUserService;

	/**
	 * 查询班级信息
	 * 
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public SpClass get(@RequestParam(required = false) String id) {
		SpClass entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = spClassService.get(id);
		}
		if (entity == null) {
			entity = new SpClass();
		}
		return entity;
	}

	/**
	 * 班级列表查询
	 * 
	 * @param spClass
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(SpClass spClass, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<SpClass> page = null;
		try {
			User user = UserUtils.getUser();
			Map<?, ?> filters = request.getParameterMap();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if (!user.isAdmin()) {
				queryMap.put("createBy", user.getId());
			}
			queryMap.put("delFlag", DelFlagType.NORMAL.getValue());// 未删除
			page = spClassService.findPage(
					new Page<SpClass>(request, response), queryMap);
			SpUser spuser = new SpUser();
			spuser.setUserType("2");// 老师
			spuser.setDelFlag(DelFlagType.NORMAL.getValue());// 未删除
			List<SpUser> userList = SpUserService.findList(spuser);
			model.addAttribute("userList", userList);
		} catch (Exception e) {
			logger.error("查询班级列表异常", e);
		}
		model.addAttribute("page", page);
		return "modules/spadmin/spclass/spClassList";
	}

	/**
	 * 跳转到班级编辑页面
	 * 
	 * @param spClass
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(SpClass spClass, Model model) {
		try {
			SpUser spuser = new SpUser();
			spuser.setUserType("2");// 老师
			spuser.setDelFlag(DelFlagType.NORMAL.getValue());// 未删除
			List<SpUser> userList = SpUserService.findList(spuser);
			model.addAttribute("userList", userList);
		} catch (Exception e) {
			logger.error("获取老师列表失败", e);
		}
		model.addAttribute("spClass", spClass);
		return "modules/spadmin/spclass/spClassForm";
	}

	/**
	 * 保存班级信息
	 * 
	 * @param spClass
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(SpClass spClass, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, spClass)) {
				return form(spClass, model);
			}
			spClassService.save(spClass);
			addMessage(redirectAttributes, "保存班级成功");
		} catch (Exception e) {
			logger.error("保存班级异常", e);
			addMessage(redirectAttributes, "保存班级失败");
		}
		return "redirect:" + Global.getAdminPath()
				+ "/spadmin/spclass/spClass/?repage";
	}

	/**
	 * 删除对应班级
	 * 
	 * @param spClass
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(SpClass spClass, RedirectAttributes redirectAttributes) {
		try {
			spClassService.delete(spClass);
			addMessage(redirectAttributes, "删除班级成功");
		} catch (Exception e) {
			logger.error("删除班级异常", e);
			addMessage(redirectAttributes, "删除班级失败");
		}
		return "redirect:" + Global.getAdminPath()
				+ "/spadmin/spclass/spClass/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "checkClassName")
	public String checkClassName(String id, String name) {
		String flag = "true";
		Integer fla = spClassService.checkName(id, name);
		if (fla > 0) {
			flag = "false";
		}
		return flag;

	}
}