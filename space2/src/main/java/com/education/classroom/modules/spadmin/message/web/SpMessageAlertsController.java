/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.message.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.education.classroom.core.modules.spadmin.message.entity.SpMessageAlerts;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.message.service.SpMessageAlertsService;
import com.education.classroom.utils.Encodes;

/**
 * 公告管理Controller
 * @author 尚军伟
 * @version 2016/08/06
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/message/spMessageAlerts")
public class SpMessageAlertsController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpMessageAlertsService spMessageAlertsService;
	
	@ModelAttribute
	public SpMessageAlerts get(@RequestParam(required=false) String id) {
		SpMessageAlerts entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spMessageAlertsService.get(id);
		}
		if (entity == null){
			entity = new SpMessageAlerts();
		}
		return entity;
	}
	
	/**
	 * 公告管理列表中的数据
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spMessageAlerts
	 * @param model
	 * @return String 会员资费管理列表
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = {"list", ""})
	public String list(SpMessageAlerts spMessageAlerts, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("查询公告管理列表中的数据开始");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String title = "";
			String operaterName = "";
			Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
			Encodes decoder = new Encodes();
			if(spMessageAlerts.getTitle() != null){
				title = decoder.decodeBase64String(spMessageAlerts.getTitle());
		        Matcher m1=p.matcher(title); 
		        if(!m1.find()){ 
		    
					title = spMessageAlerts.getTitle();
		        }
				spMessageAlerts.setTitle(title);
			}
			if(spMessageAlerts.getOperaterName() != null){
				operaterName = decoder.decodeBase64String(spMessageAlerts.getOperaterName());
		        Matcher m2=p.matcher(operaterName); 
		        if(!m2.find()){ 
		    
		        	operaterName = spMessageAlerts.getOperaterName();
		        }
				spMessageAlerts.setOperaterName(operaterName);
			}
			User user = UserUtils.getUser();
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if(user.isAdmin()){
				queryMap.put("createBy",user.getId());
			}
			queryMap.put("delFlag","0");
			queryMap.put("title",title);
			queryMap.put("operaterName",operaterName);
			Page<SpMessageAlerts> page = spMessageAlertsService.findPage(new Page<SpMessageAlerts>(request, response), queryMap); 
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("查询公告管理列表中的数据失败！",e);
		}
		logger.info("查询公告管理列表中的数据结束");
		return "modules/spadmin/message/spMessageAlertsList";
	}

	
	/**
	 * 公告管理列表中的表单
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spMessageAlerts
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(SpMessageAlerts spMessageAlerts, Model model) {
		logger.info("查询会员资费管理列表中的表单开始");
		model.addAttribute("spMessageAlerts", spMessageAlerts);
		logger.info("查询会员资费管理列表中的表单结束");
		return "modules/spadmin/message/spMessageAlertsForm";
	}

	
	/**
	 * 公告管理列表中会员公告添加
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spMessageAlerts
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "save")
	public String save(SpMessageAlerts spMessageAlerts, Model model, RedirectAttributes redirectAttributes) {
		logger.info("公告管理列表中会员公告添加开始");
		try {
			if (!beanValidator(model, spMessageAlerts)){
				return form(spMessageAlerts, model);
			}
			spMessageAlertsService.save(spMessageAlerts);
			addMessage(redirectAttributes, "公告管理列表中会员公告添加成功");
		} catch (Exception e) {
			logger.error( "公告管理列表中会员公告添加失败" ,e);
		}
		logger.info("公告管理列表中会员公告添加结束");
		return "redirect:"+Global.getAdminPath()+"/spadmin/message/spMessageAlerts/?repage";
	}
	
	/**
	 * 公告管理列表中会员公告的删除
	 * 2016年8月6日
	 * By shangjunwei
	 * @param spMessageAlerts
	 * @param model
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "delete")
	public String delete(SpMessageAlerts spMessageAlerts, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		logger.info("公告管理列表中会员公告的删除开始");
		String title = null;
		String operaterName = null;
		Encodes decoder = new Encodes();
		try {
			title= spMessageAlerts.getTitle();
			operaterName = spMessageAlerts.getOperaterName();
			 Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
		        Matcher m1=p.matcher(title); 
		        Matcher m2=p.matcher(operaterName);
		        if(m1.find()){ 
					title = decoder.encodeBase64(spMessageAlerts.getTitle());
		        }
		        if(m2.find()){ 
		        	operaterName = decoder.encodeBase64(spMessageAlerts.getOperaterName());
		        }
		    operaterName = spMessageAlerts.getOperaterName();
			spMessageAlertsService.delete(spMessageAlerts);
			addMessage(redirectAttributes, "公告管理列表中会员公告的删除成功");
		} catch (Exception e) {
			logger.error( "公告管理列表中会员公告的删除失败:" + e);
		}
		logger.info("公告管理列表中会员公告的删除结束");
		return "redirect:"+Global.getAdminPath()+"/spadmin/message/spMessageAlerts/?title="+title+"&operaterName="+operaterName;
	}

}