/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.bandcard.web;

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
import com.education.classroom.core.modules.spadmin.bandcard.entity.SpKjpayBindCard;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.bandcard.service.SpKjpayBindCardService;
import com.education.classroom.utils.StringUtils;

/**
 * 绑卡表增删改查Controller
 * @author 赵新月
 * @version 2016/08/31
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/bandcard/spKjpayBindCard")
public class SpKjpayBindCardController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpKjpayBindCardService spKjpayBindCardService;
	
	@ModelAttribute
	public SpKjpayBindCard get(@RequestParam(required=false) String id) {
		SpKjpayBindCard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spKjpayBindCardService.get(id);
		}
		if (entity == null){
			entity = new SpKjpayBindCard();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpKjpayBindCard spKjpayBindCard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<?, ?> filters = request.getParameterMap();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap = CommonUtils.copyQueryMap(filters, queryMap);
		Page<SpKjpayBindCard> page = spKjpayBindCardService.findPage(new Page<SpKjpayBindCard>(request, response), queryMap); 
		model.addAttribute("page", page);
		return "modules/spadmin/bandcard/spKjpayBindCardList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpKjpayBindCard spKjpayBindCard, Model model) {
		model.addAttribute("spKjpayBindCard", spKjpayBindCard);
		return "modules/spadmin/bandcard/spKjpayBindCardForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpKjpayBindCard spKjpayBindCard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spKjpayBindCard)){
			return form(spKjpayBindCard, model);
		}
		spKjpayBindCardService.save(spKjpayBindCard);
		addMessage(redirectAttributes, "保存绑卡信息成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/bandcard/spKjpayBindCard/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpKjpayBindCard spKjpayBindCard, RedirectAttributes redirectAttributes) {
		spKjpayBindCardService.delete(spKjpayBindCard);
		addMessage(redirectAttributes, "删除绑卡信息成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/bandcard/spKjpayBindCard/?repage";
	}

}