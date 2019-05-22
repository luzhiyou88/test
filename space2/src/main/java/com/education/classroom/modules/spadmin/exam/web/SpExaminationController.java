/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.exam.web;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.common.constants.stateConstants;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.exam.entity.SpExamination;
import com.education.classroom.core.modules.spadmin.problems.entity.SpProblems;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.exam.service.SpExaminationService;
import com.education.classroom.modules.spadmin.problems.service.SpProblemsService;

/**
 * 试卷管理Controller
 * @author 尚军伟
 * @version 2016/08/17
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/exam/spExamination")
public class SpExaminationController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpExaminationService spExaminationService;
	
	@Autowired
	private SpProblemsService problemsService;
	
	@ModelAttribute
	public SpExamination get(@RequestParam(required=false) String id) {
		SpExamination entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spExaminationService.get(id);
		}
		if (entity == null){
			entity = new SpExamination();
		}
		return entity;
	}
	
	
	
	/**
	 * 试卷管理列表中的数据
	 * 2016年8月17日
	 * By shangjunwei
	 * @param spExamination
	 * @param model
	 * @return String 试卷管理列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpExamination spExamination, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("查询试卷管理列表中的数据开始");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if(user.isAdmin()){
				queryMap.put("createBy",user.getId());
			}
			queryMap.put("delFlag","0");
			Page<SpExamination> page = spExaminationService.findPage(new Page<SpExamination>(request, response),  queryMap); 
			model.addAttribute("page", page);
			logger.info("查询试卷管理列表中的数据结束");
		} catch (Exception e) {
			logger.error("查询试卷管理列表中的数据失败！",e);
		}
		return "modules/spadmin/exam/spExaminationList";
	}

	
	/**
	 * 试卷管理列表中的表单
	 * 2016年8月17日
	 * By shangjunwei
	 * @param spExamination
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(SpExamination spExamination, Model model) {
		logger.info("查询试卷管理列表中的表单开始");
		model.addAttribute("spExamination", spExamination);
		logger.info("查询试卷管理列表中的表单结束");
		return "modules/spadmin/exam/spExaminationForm";
	}

	/**
	 * 试卷管理列表中数据添加
	 * 2016年8月5日
	 * By shangjunwei
	 * @param spExamination
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "save")
	public String save(SpExamination spExamination, Model model, RedirectAttributes redirectAttributes) {
		logger.info("试卷管理列表中数据添加开始");
		try {
			if (!beanValidator(model, spExamination)){
				return form(spExamination, model);
			}
			spExamination.setState("0");
			spExaminationService.save(spExamination);
			addMessage(redirectAttributes, "试卷管理列表中数据添加成功");
			logger.info("试卷管理列表中数据添加结束");
		} catch (Exception e) {
			logger.error( "试卷管理列表中数据添加失败" ,e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/exam/spExamination/?repage";
	}
	
	
	/**
	 * 试卷管理列表中类型的删除
	 * 2016年8月17日
	 * By shangjunwei
	 * @param spExamination
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "delete")
	public String delete(SpExamination spExamination, RedirectAttributes redirectAttributes) {
		logger.info("试卷管理列表中类型的删除开始");
		try {
			spExaminationService.delete(spExamination);
			addMessage(redirectAttributes, "试卷管理列表中类型的删除成功");
			logger.info("试卷管理列表中类型的删除结束");
		} catch (Exception e) {
			logger.error( "试卷管理列表中类型的删除失败:" + e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/exam/spExamination/?repage";
	}
	
	/**
	 * 试卷管理列表中试卷及试题的删除
	 * 2016年9月14日
	 * By shangjunwei
	 * @param spExamination
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "deleteProblem")
	public String deleteProblem(SpExamination spExamination, RedirectAttributes redirectAttributes) {
		logger.info("试卷管理列表中试卷及试题的删除开始");
		try {
			//根据试卷ID查询关联的试题列表
			List<SpProblems> probList = problemsService.selectProblemList(spExamination.getId());
			 if (probList.size() > 0) {
				 for(SpProblems prob:probList) {
					 problemsService.delete(prob);
				 }
			 }
			spExaminationService.delete(spExamination);
			addMessage(redirectAttributes, "试卷管理列表中试卷及试题的删除成功");
			logger.info("试试卷管理列表中试卷及试题的删除结束");
		} catch (Exception e) {
			logger.error( "试卷管理列表中试卷及试题的删除失败:" + e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/exam/spExamination/?repage";
	}
	
	
	/**
	 * 校验试卷是否有关联试题
	 * 2016年9月14日
	 * By shangjunwei
	 * @param id
	 * @return int
	 */
	@ResponseBody
	@RequestMapping(value = "checkProblem")
	public String checkProblem(String examId){
		logger.info("校验试卷是否有关联试题开始");
		int num = spExaminationService.checkProblem(examId);
		if(num > 0){
			logger.info("校验试卷是否有关联试题结束");
			return stateConstants.TRUE;
		} else {
			logger.info("校验试卷是否有关联试题结束");
			return stateConstants.FALSE;
		}
		
	}
	
	/**
	 * 校验试卷名称的唯一性
	 * 2016年9月13日
	 * By shangjunwei
	 * @param examinationName
	 * @return int
	 */
	@ResponseBody
	@RequestMapping(value = "checkExaminationName")
	public String checkExaminationName(String examinationName,String id){
		logger.info("校验试卷名称的唯一性开始");
		int num = spExaminationService.checkExaminationName(examinationName,id);
		if(num > 0){
			logger.info("校验试卷名称的唯一性结束");
			return stateConstants.FALSE;
		} else {
			logger.info("校验试卷名称的唯一性结束");
			return stateConstants.TRUE;
		}
		
	}

}