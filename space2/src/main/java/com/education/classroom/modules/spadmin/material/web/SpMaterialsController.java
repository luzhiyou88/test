/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.material.web;

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

import com.education.classroom.common.constants.stateConstants;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.material.entity.SpMaterials;
import com.education.classroom.core.modules.spadmin.problems.entity.SpProblems;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.material.service.SpMaterialsService;
import com.education.classroom.modules.spadmin.problems.service.SpProblemsService;
import com.education.classroom.utils.StringUtils;

/**
 * 材料管理Controller
 * @author 路志友
 * @version 2016/08/17
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/material/spMaterials")
public class SpMaterialsController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpMaterialsService spMaterialsService;
	
	@Autowired
	private SpProblemsService problemsService;
	
	@ModelAttribute
	public SpMaterials get(@RequestParam(required=false) String id) {
		SpMaterials entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spMaterialsService.get(id);
		}
		if (entity == null){
			entity = new SpMaterials();
		}
		return entity;
	}
	
	/**
	 * 材料管理列表中的数据
	 * 2016年8月17日
	 * By shangjunwei
	 * @param spMaterials
	 * @param model
	 * @return String 材料管理列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpMaterials spMaterials, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("查询材料管理列表中的数据开始");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if(user.isAdmin()){
				queryMap.put("createBy",user.getId());
			}
			queryMap.put("delFlag","0");
			Page<SpMaterials> page = spMaterialsService.findPage(new Page<SpMaterials>(request, response),  queryMap); 
			model.addAttribute("page", page);
			logger.info("查询材料管理列表中的数据结束");
		} catch (Exception e) {
			logger.error("查询材料管理列表中的数据失败！",e);
		}
		return "modules/spadmin/material/spMaterialsList";
	}

	
	/**
	 * 材料管理列表中的表单
	 * 2016年8月17日
	 * By shangjunwei
	 * @param spMaterials
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(SpMaterials spMaterials, Model model) {
		logger.info("查询材料管理列表中的表单开始");
		model.addAttribute("spMaterials", spMaterials);
		logger.info("查询材料管理列表中的表单结束");
		return "modules/spadmin/material/spMaterialsForm";
	}

	/**
	 * 材料管理列表中数据添加
	 * 2016年8月5日
	 * By shangjunwei
	 * @param spMaterials
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "save")
	public String save(SpMaterials spMaterials, Model model, RedirectAttributes redirectAttributes) {
		logger.info("材料管理列表中数据添加开始");
		try {
			if (!beanValidator(model, spMaterials)){
				return form(spMaterials, model);
			}
			spMaterialsService.save(spMaterials);
			addMessage(redirectAttributes, "保存材料成功");
			logger.info("材料管理列表中数据添加结束");
		} catch (Exception e) {
			logger.error( "材料管理列表中数据添加失败" ,e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/material/spMaterials/?repage";
	}
	
	
	/**
	 * 材料管理列表中类型的删除
	 * 2016年8月17日
	 * By shangjunwei
	 * @param spMaterials
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "delete")
	public String delete(SpMaterials spMaterials, RedirectAttributes redirectAttributes) {
		logger.info("材料管理列表中类型的删除开始");
		try {
			spMaterialsService.delete(spMaterials);
			addMessage(redirectAttributes, "删除材料成功");
			logger.info("材料管理列表中类型的删除结束");
		} catch (Exception e) {
			logger.error( "材料管理列表中类型的删除失败:" + e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/material/spMaterials/?repage";
	}
	
	
	/**
	 * 材料管理列表中材料及关联试题的删除
	 * 2016年9月18日
	 * By shangjunwei
	 * @param spMaterials
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "deleteProblemMaterial")
	public String deleteProblemMaterial(SpMaterials spMaterials, RedirectAttributes redirectAttributes) {
		logger.info("试卷管理列表中试卷及试题的删除开始");
		try {
			//根据试卷ID查询关联的试题列表
			List<SpProblems> probList = problemsService.selectProblemListByMaterials(spMaterials.getId());
			 if (probList.size() > 0) {
				 for(SpProblems prob:probList) {
					 problemsService.delete(prob);
				 }
			 }
			 spMaterialsService.delete(spMaterials);
			addMessage(redirectAttributes, "试卷管理列表中试卷及试题的删除成功");
			logger.info("试试卷管理列表中试卷及试题的删除结束");
		} catch (Exception e) {
			logger.error( "试卷管理列表中试卷及试题的删除失败:" + e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/exam/spExamination/?repage";
	}
	
	
	
	
	/**
	 * 校验试卷是否有关联材料
	 * 2016年9月18日
	 * By shangjunwei
	 * @param materialId
	 * @return boolean
	 */
	@ResponseBody
	@RequestMapping(value = "checkProblemMaterial")
	public String checkProblemMaterial(String materialId){
		logger.info("校验试卷是否有关联材料开始");
		int num = spMaterialsService.checkProblemMaterial(materialId);
		if(num > 0){
			logger.info("校验试卷是否有关联材料结束");
			return stateConstants.TRUE;
		} else {
			logger.info("校验试卷是否有关联材料结束");
			return stateConstants.FALSE;
		}
		
	}
	
	
	/**
	 * 校验材料名称的唯一性
	 * 2016年9月13日
	 * By shangjunwei
	 * @param examinationName
	 * @return int
	 */
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String name,String id){
		logger.info("校验材料名称的唯一性开始");
		int num = spMaterialsService.checkName(name,id);
		if(num > 0){
			logger.info("校验材料名称的唯一性结束");
			return stateConstants.FALSE;
		} else {
			logger.info("校验材料名称的唯一性结束");
			return stateConstants.TRUE;
		}
		
	}

}