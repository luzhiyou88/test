
package com.education.classroom.modules.spadmin.member.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.member.entity.SpMemberPostage;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.constants.stateConstants;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.member.service.SpMemberPostageService;

/**
 * 会员资费管理Controller
 * @author 尚军伟
 * @version 2016/08/06
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/member/spMemberPostage")
public class SpMemberPostageController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpMemberPostageService spMemberPostageService;
	
	@ModelAttribute
	public SpMemberPostage get(@RequestParam(required=false) String id) {
		SpMemberPostage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spMemberPostageService.get(id);
		}
		if (entity == null){
			entity = new SpMemberPostage();
		}
		return entity;
	}
	
	
	/**
	 * 会员资费管理列表中的数据
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spMemberPostage
	 * @param model
	 * @return String 会员资费管理列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpMemberPostage spMemberPostage, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("查询会员资费管理列表中的数据开始");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if(user.isAdmin()){
				queryMap.put("createBy",user.getId());
			}
			queryMap.put("delFlag","0");
			Page<SpMemberPostage> page = spMemberPostageService.findPage(new Page<SpMemberPostage>(request, response), queryMap); 
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("查询会员资费管理列表中的数据失败！",e);
		}
		logger.info("查询会员资费管理列表中的数据结束");
		return "modules/spadmin/member/spMemberPostageList";
	}

	
	/**
	 * 会员资费管理列表中的表单
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spMemberPostage
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(SpMemberPostage spMemberPostage, Model model) {
		logger.info("查询会员资费管理列表中的表单开始");
		model.addAttribute("spMemberPostage", spMemberPostage);
		logger.info("查询会员资费管理列表中的表单结束");
		return "modules/spadmin/member/spMemberPostageForm";
	}

	
	/**
	 * 会员资费管理列表中会员资费添加
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spMemberPostage
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "save")
	public String save(SpMemberPostage spMemberPostage, Model model, RedirectAttributes redirectAttributes) {
		logger.info("会员资费管理列表中会员资费添加开始");
		try {
			if (!beanValidator(model, spMemberPostage)){
				return form(spMemberPostage, model);
			}
			spMemberPostageService.save(spMemberPostage);
			addMessage(redirectAttributes, "会员资费管理列表中会员资费添加成功");
		} catch (Exception e) {
			logger.error( "会员资费管理列表中会员资费添加失败" ,e);
		}
		logger.info("会员资费管理列表中会员资费添加结束");
		return "redirect:"+Global.getAdminPath()+"/spadmin/member/spMemberPostage/?repage";
	}
	
	
	/**
	 * 会员资费管理列表中会员资费的删除
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spMemberPostage
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "delete")
	public String delete(SpMemberPostage spMemberPostage, RedirectAttributes redirectAttributes) {
		logger.info("会员资费管理列表中会员资费的删除开始");
		try {
			spMemberPostageService.delete(spMemberPostage);
			addMessage(redirectAttributes, "会员资费管理列表中会员资费的删除成功");
		} catch (Exception e) {
			logger.error( "会员资费管理列表中会员资费的删除失败:" + e);
		}
		logger.info("会员资费管理列表中会员资费的删除结束");
		return "redirect:"+Global.getAdminPath()+"/spadmin/member/spMemberPostage/?repage";
	}
	
	
	/**
	 * 校验资费名称的唯一性
	 * 2016年8月6日
	 * By shangjunwei
	 * @param name
	 * @return int
	 */
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String name){
		logger.info("校验资费名称的唯一性开始");
		int num = spMemberPostageService.selectByName(name);
		if(num > 0){
			logger.info("校验资费名称的唯一性结束");
			return stateConstants.FALSE;
		} else {
			logger.info("校验资费名称的唯一性结束");
			return stateConstants.TRUE;
		}
		
	}

}