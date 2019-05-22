/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.problems.web;

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
import com.google.common.collect.Maps;

/**
 * 试题管理Controller
 * @author 尚军伟
 * @version 2016/08/18
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/problems/spProblems")
public class SpProblemsController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpProblemsService spProblemsService;
	
	@Autowired
	private SpExaminationService examinationService;
	
	@ModelAttribute
	public SpProblems get(@RequestParam(required=false) String id) {
		SpProblems entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spProblemsService.get(id);
		}
		if (entity == null){
			entity = new SpProblems();
		}
		return entity;
	}
	
	
	/**
	 * 试题管理列表中的数据
	 * 2016年8月18日
	 * By shangjunwei
	 * @param spProblems
	 * @param model
	 * @return String 试题管理列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpProblems spProblems, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("查询试题管理列表中的数据开始");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if(user.isAdmin()){
				queryMap.put("createBy",user.getId());
			}
			queryMap.put("delFlag","0");
			Page<SpProblems> page = spProblemsService.findPage(new Page<SpProblems>(request, response),queryMap); 
			for(SpProblems sp: page.getList()){
				String examinationId = sp.getExaminationId();
				String examinationName = spProblemsService.fingExaminationNameById(examinationId);
				sp.setExaminationName(examinationName);
			}
			model.addAttribute("page", page);
			logger.info("查询试题管理列表中的数据结束");
		} catch (Exception e) {
			logger.error("查询试题管理列表中的数据失败！",e);
		}
		return "modules/spadmin/problems/spProblemsList";
	}

	/**
	 * 试题管理列表中的表单数据
	 * 2016年8月18日
	 * By shangjunwei
	 * @param spProblems
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(SpProblems spProblems, Model model,HttpServletRequest request) {
		logger.info("查询试题管理列表中的表单数据开始");
		String xq = request.getParameter("xq");
		model.addAttribute("xq",xq);
		model.addAttribute("spProblems", spProblems);
		logger.info("查询试题管理列表中的表单数据结束");
		return "modules/spadmin/problems/spProblemsForm";
	}

	/**
	 * 试题管理列表中数据添加
	 * 2016年8月18日
	 * By shangjunwei
	 * @param spProblems
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "save")
	public String save(SpProblems spProblems, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		logger.info("试题管理列表中数据添加开始");
		try {
			if (!beanValidator(model, spProblems)){
				return form(spProblems, model, request);
			}
			
			Map<String,Object> map =Maps.newHashMap();
			String examinationId = spProblems.getExaminationId();
			String stem = spProblems.getStem();
			boolean flag = false;
			if (StringUtils.isNotEmpty(examinationId) && StringUtils.isNotEmpty(stem)) {
				map.put("stem", stem);
				map.put("examinationId",examinationId);
				map.put("id", spProblems.getId());
				int num = spProblemsService.selectStem(map);
				if(num > 0){
					addMessage(redirectAttributes, "试题题干重复，添加失败");
					logger.info("试题管理列表中数据添加结束");
					return "redirect:"+Global.getAdminPath()+"/spadmin/problems/spProblems/?repage";
				} else {
					//查询试卷信息
					SpExamination examination = examinationService.get(examinationId);
					 
					//根据试卷ID查询关联的试题总数目
					int sumNumber = spProblemsService.selectTotalNumber(examinationId,spProblems.getId());
				
					//试卷总数目
					String examinationNumber = examination.getExaminationNumber();
					if(sumNumber < (Integer.parseInt(examinationNumber))){
						flag = true;
					}
					if(flag == false){
						addMessage(redirectAttributes, "试题题目数量已满，添加失败");
						logger.info("试题管理列表中数据添加结束");
						return "redirect:"+Global.getAdminPath()+"/spadmin/problems/spProblems/?repage";
					} 
					
					
					
					
					//根据试卷ID查询关联的试题总分数
					String sumScore = spProblemsService.selectSumScore(examinationId,spProblems.getId());
					
				
					
					//试卷总分数
					String examinationTotalScore = examination.getExaminationTotalScore();
					
					boolean flag1 = (Integer.parseInt(spProblems.getProblemScore()) <= (Integer.parseInt(examinationTotalScore)-Integer.parseInt(sumScore)));
					if(flag1 == false){
						addMessage(redirectAttributes, "校验试卷分数超过试卷总分数，添加失败");
						logger.info("校验试卷分数是否超过试卷总分数结束");
						return "redirect:"+Global.getAdminPath()+"/spadmin/problems/spProblems/?repage";
					} 
					
					
					
					spProblemsService.save(spProblems);
					addMessage(redirectAttributes, "保存试题管理成功");
					logger.info("试题管理列表中数据添加结束");
				}
			} 
		} catch (Exception e) {
			logger.error( "试题管理列表中数据添加失败" ,e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/problems/spProblems/?repage";
	}
	
	
	/**
	 * 校验试卷试题数量是否已满
	 * 2016年8月26日
	 * By shangjunwei
	 * @param examinationId
	 * @return boolean
	 */
	@ResponseBody
	@RequestMapping(value = "checkProblemCount")
	public String checkProblemCount(String examinationId,String id){
		logger.info("校验试卷试题数量是否已满开始");
		boolean flag = false;
		 if(StringUtils.isNotEmpty(examinationId)){	
			//查询试卷信息
			SpExamination examination = examinationService.get(examinationId);
			 
			//根据试卷ID查询关联的试题总数目
			int sumNumber = spProblemsService.selectTotalNumber(examinationId,id);
		
			//试卷总数目
			String examinationNumber = examination.getExaminationNumber();
			if(sumNumber < (Integer.parseInt(examinationNumber))){
				flag = true;
			}
			if(flag == false){
				logger.info("校验试卷试题数量是否已满结束");
				return stateConstants.FALSE;
			} else {
				logger.info("校验试卷试题数量是否已满结束");
				return stateConstants.TRUE;
			}
		 } else {
			 String err = "err";
			 return jsonMapper.toJson(err);
		 }
		 
	}
	
	
	/**
	 * 校验试卷试题编号唯一性
	 * 2016年8月26日
	 * By shangjunwei
	 * @param examinationId
	 * @param number
	 * @return boolean
	 */
	@ResponseBody
	@RequestMapping(value = "checkProblemNumber")
	public String checkProblemNumber(String examinationId,String number,String id){
		logger.info("校验试卷试题编号唯一性开始");
		boolean flag = true;
		 if(StringUtils.isNotEmpty(examinationId) && StringUtils.isNotEmpty(number)){	
		//根据试卷ID查询关联的试题编号
			List<String> numberList = spProblemsService.selectProblemNumber(examinationId,id);
			for(int i=0;i<numberList.size();i++){
				if(number.equals(numberList.get(i))){
					flag = false;
					break;
				}
			}
			if(flag == false){
				logger.info("校验试卷试题编号唯一性结束");
				return stateConstants.FALSE;
			} else {
				logger.info("校验试卷试题编号唯一性结束");
				return stateConstants.TRUE;
			}
		 } else {
			 String err = "err";
			 return jsonMapper.toJson(err);
		 }
		 
	}
	
	
	
	/**
	 * 校验试卷分数是否超过试卷总分数
	 * 2016年8月26日
	 * By shangjunwei
	 * @param examinationId
	 * @param problemScore
	 * @return boolean
	 */
	@ResponseBody
	@RequestMapping(value = "checkProblemScore")
	public String checkProblemScore(String examinationId,String problemScore,String id){
		logger.info("校验试卷分数是否超过试卷总分数开始");
		 if(StringUtils.isNotEmpty(examinationId) && StringUtils.isNotEmpty(problemScore)){
		//根据试卷ID查询关联的试题总分数
		String sumScore = spProblemsService.selectSumScore(examinationId,id);
		
		SpExamination examination = examinationService.get(examinationId);
		
		//试卷总分数
		String examinationTotalScore = examination.getExaminationTotalScore();
		
		boolean flag = (Integer.parseInt(problemScore) <= (Integer.parseInt(examinationTotalScore)-Integer.parseInt(sumScore)));
		if(flag == false){
			logger.info("校验试卷分数是否超过试卷总分数结束");
			return stateConstants.FALSE;
		} else {
			logger.info("校验试卷分数是否超过试卷总分数结束");
			return stateConstants.TRUE;
		}
		 } else {
			 String err = "err";
			 return jsonMapper.toJson(err);
		 }
	}
	
	
	
	/**
	 * 试题管理列表中数据的删除
	 * 2016年8月18日
	 * By shangjunwei
	 * @param spProblems
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "delete")
	public String delete(SpProblems spProblems, RedirectAttributes redirectAttributes) {
		logger.info("试题管理列表中数据的删除开始");
		try {
			spProblemsService.delete(spProblems);
			addMessage(redirectAttributes, "删除试题管理成功");
			logger.info("试题管理列表中数据的删除结束");
		} catch (Exception e) {
			logger.error( "试题管理列表中数据的删除失败:" + e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/problems/spProblems/?repage";
	}
	
	/**
	 * 验证试题题干唯一性
	 * @param stem
	 * @param examinationId
	 * true:试题有效，false:试题无效，已经存在
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkStem")
	public String checkStem(String stem, String examinationId,String id) {
		logger.info("验证试题题干唯一性开始");
		Map<String,Object> map =Maps.newHashMap();
		if (StringUtils.isNotEmpty(examinationId) && StringUtils.isNotEmpty(stem)) {
			map.put("stem", stem);
			map.put("examinationId",examinationId);
			map.put("id", id);
			int num = spProblemsService.selectStem(map);
			if(num > 0){
				logger.info("验证试题题干唯一性结束");
				return stateConstants.FALSE;
			} else {
				logger.info("验证试题题干唯一性结束");
				return stateConstants.TRUE;
			}
		} else {
			logger.info("验证试题题干唯一性结束");
			return "输入参数有误";
		}
	}

}