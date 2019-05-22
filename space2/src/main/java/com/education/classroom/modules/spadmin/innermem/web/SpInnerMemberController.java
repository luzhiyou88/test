package com.education.classroom.modules.spadmin.innermem.web;

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
import com.education.classroom.core.common.type.DeleteFlagType;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.innermem.entity.SpInnerMember;
import com.education.classroom.core.modules.spadmin.spclass.entity.SpClass;
import com.education.classroom.core.modules.spadmin.specialty.entity.SpSpecialty;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.innermem.service.SpInnerMemberService;
import com.education.classroom.modules.spadmin.space.service.SpSpaceService;
import com.education.classroom.modules.spadmin.spclass.service.SpClassService;
import com.education.classroom.modules.spadmin.specialty.service.SpSpecialtyService;

import com.education.classroom.utils.classroom.SpaceUtils;
import com.google.common.collect.Maps;

/**
 * 会员预留信息控制器
 * @Class Name SpInnerMemberController
 * @author zhangyongsheng
 * @Create In 2016年8月6日
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/innermem/spInnerMember")
public class SpInnerMemberController extends BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpInnerMemberService innerMemberService;
	@Autowired
	private SpSpecialtyService specialtyService;
	@Autowired
	private SpClassService classService;
	@Autowired
	private SpSpaceService spaceService;
	
	@ModelAttribute
	public SpInnerMember get(@RequestParam(required=false) String id) {
		SpInnerMember entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = innerMemberService.get(id);
		}
		if (entity == null){
			entity = new SpInnerMember();
		}
		return entity;
	}
	
	/**
	 * 会员预留信息管理列表中的数据
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spInnerMember
	 * @param model
	 * @return String 会员预留信息管理列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpInnerMember spInnerMember, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.info("查询会员预留信息管理列表中的数据开始");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if(user.isAdmin()){
				queryMap.put("createBy",user.getId());
			}
			queryMap.put("delFlag", DeleteFlagType.NORMAL);
			Page<SpInnerMember> page = innerMemberService.findPage(
					new Page<SpInnerMember>(request, response), queryMap);
			model.addAttribute("page", page);
			List<SpSpecialty> specialList = specialtyService.findList(queryMap);
			model.addAttribute("specials", specialList);
			logger.info("查询会员预留信息管理列表中的数据结束");
		} catch (Exception e) {
			logger.error("查询会员预留信息管理列表中的数据失败！",e);
		}
		return "modules/spadmin/innermem/spInnerMemberList";
	}
	
	/**
	 * 查看会员审核结果
	 * 2016年8月8日
	 * By zhangyongsheng
	 * @param spInnerMember
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getApproveState"})
	public String getApproveState(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.info("查询查看会员审核结果开始");
		try {
			innerMemberService.checkApproveState(request.getParameter("id"));
			User user = UserUtils.getUser();
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if(user.isAdmin()){
				queryMap.put("createBy",user.getId());
			}
			queryMap.put("delFlag", DeleteFlagType.NORMAL);
			Page<SpInnerMember> page = innerMemberService.findPage(
					new Page<SpInnerMember>(request, response), queryMap);
			model.addAttribute("page", page);
			logger.info("查询查看会员审核结果结束");
		} catch (Exception e) {
			logger.error("查询会员预留信息管理列表中的数据失败！",e);
		}
		return "modules/spadmin/innermem/spInnerMemberList";
	}

	/**
	 * 会员预留信息管理列表中的表单
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spInnerMember
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(SpInnerMember spInnerMember, Model model) {
		model.addAttribute("spInnerMember", spInnerMember);
		Map<String, Object> queryMap = Maps.newHashMap();
		queryMap.put("delFlag", DeleteFlagType.NORMAL);
		List<SpSpecialty> specialList = specialtyService.findList(queryMap);
		model.addAttribute("specials", specialList);
		Map<String, Object> filters = Maps.newHashMap();
		filters.put("delFlag", DeleteFlagType.NORMAL);
		List<SpClass> classes = classService.findList(filters);
		model.addAttribute("classes", classes);
		Map<String, Object> spaceFilters = Maps.newHashMap();
		spaceFilters.put("delFlag", DeleteFlagType.NORMAL);
		//List<SpSpace> spaces = spaceService.findList(spaceFilters);
		//model.addAttribute("space", ArrayHelper.isNotEmpty(spaces) ? spaces.get(0) : null);
		model.addAttribute("spaceId", SpaceUtils.get("SpaceId"));
		return "modules/spadmin/innermem/spInnerMemberForm";
	}

	
	/**
	 * 会员预留信息管理列表中类型添加
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spInnerMember
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "save")
	public String save(SpInnerMember spInnerMember, Model model, RedirectAttributes redirectAttributes) {
		logger.info("保存预留会员开始");
		try {
			if (!beanValidator(model, spInnerMember)){
				return form(spInnerMember, model);
			}
			innerMemberService.save(spInnerMember);
			addMessage(redirectAttributes, "保存预留会员成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存预留会员失败");
			logger.error("会员预留信息管理列表中保存失败" ,e);
		}
		logger.info("保存预留会员结束");
		return "redirect:"+Global.getAdminPath()+"/spadmin/innermem/spInnerMember/?repage";
	}
	
	
	/**
	 * 会员预留信息管理列表中类型的删除
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spInnerMember
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "delete")
	public String delete(SpInnerMember spInnerMember, RedirectAttributes redirectAttributes) {
		logger.info("会员预留信息删除开始");
		try {
			SpInnerMember platInnerMember = null;
			if (platInnerMember != null) {  // 平台上预留会员已经存在就不可以进行删除
				addMessage(redirectAttributes, "该预留会员在平台已经存在，不可以删除");
			} else {
				innerMemberService.deleteById(spInnerMember.getId());
				addMessage(redirectAttributes, "会员预留信息删除成功");
			}
		} catch (Exception e) {
			logger.error( "会员预留信息删除失败:" + e);
		}
		logger.info("会员预留信息删除结束");
		return "redirect:"+Global.getAdminPath()+"/spadmin/innermem/spInnerMember/?repage";
	}
	
	/**
	 * 验证会员手机号是否有效
	 * @param oldPhoneNo
	 * @param phoneNo
	 * true:手机号有效，false:手机号无效，已经注册
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkPhoneNo")
	public String checkPhoneNo(String oldPhoneNo, String phoneNo) {
			if (phoneNo !=null && phoneNo.equals(oldPhoneNo)) {
				return "true";
			} else if (phoneNo !=null && innerMemberService.checkPhoneNo(phoneNo)) {
				return "true";
			}
			return "false";
		
	}

}