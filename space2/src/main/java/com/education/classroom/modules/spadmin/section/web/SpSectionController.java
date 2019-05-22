/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.section.web;

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

import com.education.classroom.common.constants.stateConstants;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.section.entity.SpSection;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.section.service.SpSectionService;
import com.education.classroom.utils.StringUtils;
import com.google.common.collect.Maps;

/**
 * 节次管理Controller
 * @author 路志友
 * @version 2016-08-05
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/section/spSection")
public class SpSectionController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpSectionService spSectionService;
	
	@ModelAttribute
	public SpSection get(@RequestParam(required=false) String id) {
		SpSection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spSectionService.get(id);
		}
		if (entity == null){
			entity = new SpSection();
		}
		return entity;
	}
	
	/**
	 * 进入节次列表
	 * 2016年8月5日
	 * By 路志友
	 * @param spSection
	 * @param request
	 * @param response
	 * @param model
	 * @return  String
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpSection spSection, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("进入节次列表");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filter = request.getParameterMap();
			Map<String,Object>  queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filter, queryMap);
			if (!user.isAdmin()) {
				queryMap.put("createBy", user.getId());
			}
			queryMap.put("delFlag", "0");
			Page<SpSection> page = spSectionService.findPage(new Page<SpSection>(request, response), queryMap); 
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("查询节次列表出错"+e);
		}
		logger.info("进入节次列表成功");
		return "modules/spadmin/section/spSectionList";
	}

	/**
	 * 到添加编辑页
	 * 2016年8月5日
	 * By 路志友
	 * @param spSection
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(SpSection spSection, Model model) {
		model.addAttribute("spSection", spSection);
		return "modules/spadmin/section/spSectionForm";
	}

	/**
	 * 保存节次信息
	 * 2016年8月5日
	 * By 路志友
	 * @param spSection
	 * @param model
	 * @param redirectAttributes
	 * @return String
	 */
	@RequestMapping(value = "save")
	public String save(SpSection spSection, Model model, RedirectAttributes redirectAttributes) {
		logger.info("开始保存节次信息");
		if (!beanValidator(model, spSection)){
			return form(spSection, model);
		}
		try {
			String  startTime = spSection.getStartTime();
			String endTime = spSection.getEndTime();
			Map<String,Object> map = Maps.newHashMap();
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				map.put("id",spSection.getId());
				int num = spSectionService.selectSectionByTime(map);
				if (num != 0) {
					addMessage(redirectAttributes, "数据库有重复节次,不能重复添加,保存节次失败");
					logger.info("数据库有重复节次,不能重复添加,保存节次信息失败");
				} else {
				spSection.setName(spSection.getStartTime()+"-"+spSection.getEndTime());
				spSectionService.save(spSection);
				addMessage(redirectAttributes, "保存节次成功");
				logger.info("保存节次信息成功");
				}
			}
			
		} catch (Exception e) {
			logger.error("保存节次信息出错"+e);
		}
		
		return "redirect:"+Global.getAdminPath()+"/spadmin/section/spSection/?repage";
	}
	
	/**
	 * 删除节次信息
	 * 2016年8月5日
	 * By 路志友
	 * @param spSection
	 * @param redirectAttributes
	 * @return String
	 */
	@RequestMapping(value = "delete")
	public String delete(SpSection spSection, RedirectAttributes redirectAttributes) {
		logger.info("开始删除节次节次信息");
		try {
			spSectionService.delete(spSection);
		} catch (Exception e) {
			logger.error("删除节次信息失败"+e);
		}
		
		addMessage(redirectAttributes, "删除节次成功");
		logger.info("删除节次信息成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/section/spSection/?repage";
	}
	
	
	/**
	 * 根据节次ID查询课程表是否有关联的课程
	 * 2016年8月23日
	 * By shangjunwei
	 * @param sectionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkSectionLesson")
	public String checkSectionLesson(String sectionId){
		logger.info("校验节次ID查询课程表是否有关联的课程开始");
		int num = spSectionService.selectLessonBySectionId(sectionId);
		if(num > 0){
			logger.info("校验节次ID查询课程表是否有关联的课程结束");
			return stateConstants.FALSE;
		} else {
			logger.info("校验节次ID查询课程表是否有关联的课程结束");
			return stateConstants.TRUE;
		}
		
	}

}