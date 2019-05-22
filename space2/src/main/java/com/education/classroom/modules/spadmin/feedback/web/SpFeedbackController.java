/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.feedback.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.feedback.entity.SpFeedback;
import com.education.classroom.core.modules.spadmin.feedback.service.SpFeedbackService;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;

/**
 * 用户反馈Controller
 * @author 尚军伟
 * @version 2016/08/15
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/feedback/spFeedback")
public class SpFeedbackController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpFeedbackService spFeedbackService;
	
	@ModelAttribute
	public SpFeedback get(@RequestParam(required=false) String id) {
		SpFeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spFeedbackService.get(id);
		}
		if (entity == null){
			entity = new SpFeedback();
		}
		return entity;
	}
	
	/**
	 * 用户反馈管理列表中的数据
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spFeedback
	 * @param model
	 * @return String 用户反馈管理列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpFeedback spFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("用户反馈管理列表中的数据开始");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if(user.isAdmin()){
				queryMap.put("createBy",user.getId());
			}
			queryMap.put("delFlag","0");
			Page<SpFeedback> page = spFeedbackService.findPage(new Page<SpFeedback>(request, response), queryMap); 
			model.addAttribute("page", page);
			logger.info("用户反馈管理列表中的数据结束");
		} catch (Exception e) {
			logger.error("查询用户反馈管理列表中的数据失败！",e);
		}
		return "modules/spadmin/feedback/spFeedbackList";
	}

	/**
	 * 用户反馈管理列表中表单的数据
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spFeedback
	 * @param model
	 * @return String 用户反馈管理列表
	 */
	@RequestMapping(value = "form")
	public String form(SpFeedback spFeedback, Model model) {
		logger.info("用户反馈管理列表中表单的数据开始");
		model.addAttribute("spFeedback", spFeedback);
		logger.info("用户反馈管理列表中表单的数据结束");
		return "modules/spadmin/feedback/spFeedbackForm";
	}

	
	/**
	 * 用户反馈管理列表中表单的保存
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spFeedback
	 * @param model
	 * @return String 用户反馈管理列表
	 */
	@RequestMapping(value = "save")
	public String save(SpFeedback spFeedback, Model model, RedirectAttributes redirectAttributes) {
		logger.info("用户反馈管理列表中表单的保存开始");
		try {
			if (!beanValidator(model, spFeedback)){
				return form(spFeedback, model);
			}
			spFeedbackService.save(spFeedback);
			addMessage(redirectAttributes, "保存用户反馈成功");
			logger.info("用户反馈管理列表中表单的保存结束");
		} catch (Exception e) {
			logger.error("用户反馈管理列表中表单的保存失败！",e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/feedback/spFeedback/?repage";
	}
	
	/**
	 * 用户反馈管理列表中数据的删除
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spFeedback
	 * @param model
	 * @return String 用户反馈管理列表
	 */
	@RequestMapping(value = "delete")
	public String delete(SpFeedback spFeedback, RedirectAttributes redirectAttributes) {
		logger.info("用户反馈管理列表中数据的删除开始");
		try {
			spFeedbackService.delete(spFeedback);
			addMessage(redirectAttributes, "删除用户反馈成功");
			logger.info("用户反馈管理列表中数据的删除结束");
		} catch (Exception e) {
			logger.error("用户反馈管理列表中数据的删除失败！",e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/feedback/spFeedback/?repage";
	}

}