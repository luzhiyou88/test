/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.specialty.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.enums.DelFlagType;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.specialty.entity.SpSpecialty;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.specialty.service.SpSpecialtyService;
import com.education.classroom.utils.StringUtils;

/**
 * 专业管理Controller
 * 
 * @author 边磊
 * @version 2016-08-05
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/specialty/spSpecialty")
public class SpSpecialtyController extends BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SpSpecialtyService spSpecialtyService;

	/**
	 * 查询专业内容
	 * 
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public SpSpecialty get(@RequestParam(required = false) String id) {
		SpSpecialty entity = null;
		try {
			if (StringUtils.isNotBlank(id)) {
				entity = spSpecialtyService.get(id);
			}
			if (entity == null) {
				entity = new SpSpecialty();
			}
		} catch (Exception e) {
			logger.error("查询专业异常", e);
		}
		return entity;
	}

	/**
	 * 查询专业列表
	 * 
	 * @param spSpecialty
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(SpSpecialty spSpecialty, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<SpSpecialty> page = null;
		try {
			User user = UserUtils.getUser();
			Map<?, ?> filters = request.getParameterMap();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if (!user.isAdmin()) {
				queryMap.put("createBy", user.getId());
			}
			queryMap.put("delFlag", DelFlagType.NORMAL.getValue());// 未删除
			page = spSpecialtyService.findPage(new Page<SpSpecialty>(request,
					response), queryMap);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("专业列表查询异常", e);
		}
		model.addAttribute("page", page);
		return "modules/spadmin/specialty/spSpecialtyList";
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param spSpecialty
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(SpSpecialty spSpecialty, Model model) {
		model.addAttribute("spSpecialty", spSpecialty);
		return "modules/spadmin/specialty/spSpecialtyForm";
	}

	/**
	 * 保存专业
	 * 
	 * @param spSpecialty
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(SpSpecialty spSpecialty, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, spSpecialty)) {
				return form(spSpecialty, model);
			}
			spSpecialtyService.save(spSpecialty);
			addMessage(redirectAttributes, "保存专业成功");
		} catch (Exception e) {
			logger.error("保存专业异常", e);
			addMessage(redirectAttributes, "保存专业失败");
		}
		return "redirect:" + Global.getAdminPath()
				+ "/spadmin/specialty/spSpecialty/?repage";
	}

	/**
	 * 删除专业
	 * 
	 * @param spSpecialty
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(SpSpecialty spSpecialty,
			RedirectAttributes redirectAttributes) {
		try {
			spSpecialtyService.delete(spSpecialty);
			addMessage(redirectAttributes, "删除专业成功");
		} catch (Exception e) {
			logger.error("删除专业异常", e);
			addMessage(redirectAttributes, "删除专业失败");
		}
		return "redirect:" + Global.getAdminPath()
				+ "/spadmin/specialty/spSpecialty/?repage";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "checkSpecialtyName")
	public String checkSpecialtyName(String id, String name) {
		String flag = "true";
		Integer fla = spSpecialtyService.checkSpecialtyName(id, name);
		if (fla > 0) {
			flag = "false";
		}
		return flag;

	}
	
}