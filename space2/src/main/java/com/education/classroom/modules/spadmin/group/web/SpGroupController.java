/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.group.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.enums.DelFlagType;
import com.education.classroom.core.common.enums.PublishState;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.group.entity.SpGroup;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.group.service.SpGroupService;
import com.education.classroom.modules.spadmin.user.service.SpUserService;

import com.education.classroom.utils.Encodes;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 小组管理Controller
 * 
 * @author 边磊
 * @version 2016/08/06
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/group/spGroup")
public class SpGroupController extends BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SpGroupService spGroupService;
	@Autowired
	private SpUserService spUserService;


	/**
	 * 查询小组信息
	 * 
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public SpGroup get(@RequestParam(required = false) String id) {
		SpGroup entity = null;
		try {
			if (StringUtils.isNotBlank(id)) {
				entity = spGroupService.get(id);
			}
			if (entity == null) {
				entity = new SpGroup();
			}
		} catch (Exception e) {
			logger.error("小组信息查询异常", e);
		}
		return entity;
	}

	/**
	 * 查询小组列表
	 * 
	 * @param spGroup
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(SpGroup spGroup, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<SpGroup> page = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String name = "";
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			if (spGroup.getName() != null) {
				name = Encodes.decodeBase64String(spGroup.getName());
				Matcher m1 = p.matcher(name);
				if (!m1.find()) {

					name = spGroup.getName();
				}
				spGroup.setName(name);
			}
			User user = UserUtils.getUser();
			Map<?, ?> filters = request.getParameterMap();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if (!user.isAdmin()) {
				queryMap.put("createBy", user.getId());
			}
			queryMap.put("delFlag", DelFlagType.NORMAL.getValue());// 未删除
			page = spGroupService.findPage(
					new Page<SpGroup>(request, response), queryMap);
			SpUser spuser = new SpUser();
			spuser.setUserType("2");// 2 老师
			spuser.setDelFlag(DelFlagType.NORMAL.getValue());// 未删除
			List<SpUser> userList = spUserService.findList(spuser);
			model.addAttribute("userList", userList);
		} catch (Exception e) {
			logger.error("小组列表查询异常", e);
		}
		model.addAttribute("page", page);
		return "modules/spadmin/group/spGroupList";
	}

	/**
	 * 跳转到小组编辑页
	 * 
	 * @param spGroup
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(SpGroup spGroup, Model model) {
		try {
			SpUser spuser = new SpUser();
			spuser.setUserType("2");// 2老师
			spuser.setDelFlag(DelFlagType.NORMAL.getValue());// 未删除
			List<SpUser> userList = spUserService.findList(spuser);
			model.addAttribute("userList", userList);
		} catch (Exception e) {
			logger.error("获取老师列表失败", e);
		}
		model.addAttribute("spGroup", spGroup);
		return "modules/spadmin/group/spGroupForm";
	}

	/**
	 * 保存小组
	 * 
	 * @param spGroup
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(SpGroup spGroup, Model model,
			@RequestParam("file") CommonsMultipartFile file,
			RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, spGroup)) {
				return form(spGroup, model);
			}
			if (!file.isEmpty()) {
				OSSClientUtil o = new OSSClientUtil();
				if (!spGroup.getIsNewRecord()) {
					SpGroup group = spGroupService.get(spGroup);
					o.deleteFileByUrl(group.getThumbImg());
				}
				Map<String, String> map = o.uploadImg2Oss(file);
				if ("1".equals(map.get("SUCCESS"))) {
					if (!StringUtils.isEmpty(map.get("NAMEKEY"))) {
						String url = o.getImgOrFileUrl(map.get("NAMEKEY"));
						spGroup.setThumbImg(url);

					}
				}
				o.destory();
			}
			spGroupService.save(spGroup);
			addMessage(redirectAttributes, "保存小组成功");
		} catch (Exception e) {
			logger.error("保存小组异常", e);
			addMessage(redirectAttributes, "保存小组失败");
		}
		return "redirect:" + Global.getAdminPath()
				+ "/spadmin/group/spGroup/?repage";
	}

	/**
	 * 删除小组
	 * 
	 * @param spGroup
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(SpGroup spGroup,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response) {
		String groupType = null;
		String name = null;
		String publishState = null;
		try {
			groupType = spGroup.getGroupType();
			name = spGroup.getName();
			publishState = spGroup.getPublishState();
			spGroupService.delete(spGroup);
			addMessage(redirectAttributes, "删除小组成功");
		} catch (Exception e) {
			logger.error("删除小组异常", e);
		}

		return "redirect:" + Global.getAdminPath()
				+ "/spadmin/group/spGroup/?groupType=" + groupType + "&name="
				+ Encodes.encodeBase64(name) + "&publishState=" + publishState;
	}

	/**
	 * 发布小组
	 * 
	 * @param spGroup
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "send")
	public String send(SpGroup spGroup, RedirectAttributes redirectAttributes) {
		try {
			spGroup.setSpaceId(SpaceUtils.get("SpaceId"));
			spGroup.setPublishState(PublishState.FABU.getValue());// 待审核
		

				spGroupService.save(spGroup);
			
		} catch (Exception e) {
			logger.error("发布小组异常", e);
			addMessage(redirectAttributes, "发布小组失败");

		}
		return "redirect:" + Global.getAdminPath()
				+ "/spadmin/group/spGroup/?repage";
	}

	/**
	 * 刷新小组审核状态
	 * 
	 * @param spGroup
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "check")
	public String check(SpGroup spGroup, RedirectAttributes redirectAttributes) {
		try {
			
			
		} catch (Exception e) {
			logger.error("查询小组审核状态异常", e);
			addMessage(redirectAttributes, "查询小组审核状态失败");

		}
		return "redirect:" + Global.getAdminPath()
				+ "/spadmin/group/spGroup/?repage";
	}

	/**
	 * 获取平台组列表
	 * 
	 * @param spGroup
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "centerList")
	public String centerList(SpGroup spGroup, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<SpGroup> page = null;
		try {
			Map<?, ?> filters = request.getParameterMap();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			queryMap.put("spaceId", SpaceUtils.get("SpaceId"));
			queryMap.put("delFlag", DelFlagType.NORMAL.getValue());// 未删除
			page = new Page<SpGroup>(request,
					response);
			SpGroup group = new SpGroup();
			List<SpGroup> groupList = spGroupService.findList(group);
			model.addAttribute("groupList", groupList);
		} catch (Exception e) {
			logger.error("平台小组列表获取异常", e);
		}
		model.addAttribute("page", page);
		return "modules/spadmin/group/centerGroupList";
	}

	/**
	 * 本地化小组
	 * 
	 * @param spGroup
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "getGroup")
	public String getGroup(String id, RedirectAttributes redirectAttributes) {
		try {
		
			
			addMessage(redirectAttributes, "本地化小组成功");
		} catch (Exception e) {
			logger.error("本地化小组异常", e);
			addMessage(redirectAttributes, "本地化小组失败");
		}
		return "redirect:" + Global.getAdminPath()
				+ "/spadmin/group/spGroup/centerList?repage";
	}

	@ResponseBody
	@RequestMapping(value = "checkGroupName")
	public String checkGroupName(String id, String name) {
		String flag = "true";
		Integer fla = spGroupService.checkGroupName(id, name);
		if (fla > 0) {
			flag = "false";
		}
		return flag;

	}
}