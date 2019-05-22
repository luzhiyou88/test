/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.order.web;

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
import com.education.classroom.core.modules.spadmin.order.entity.SpOrder;
import com.education.classroom.core.modules.spadmin.space.entity.SpSpace;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.order.service.SpOrderService;
import com.education.classroom.modules.spadmin.space.service.SpSpaceService;
import com.education.classroom.modules.spadmin.user.service.SpUserService;
import com.education.classroom.utils.StringUtils;

/**
 * 订单表增删改查Controller
 * @author 赵新月
 * @version 2016/08/18
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/order/spOrder")
public class SpOrderController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpOrderService spOrderService;
	@Autowired
	private SpUserService userService;
	@Autowired
	private SpSpaceService spaceService;
	
	@ModelAttribute
	public SpOrder get(@RequestParam(required=false) String id) {
		SpOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spOrderService.get(id);
		}
		if (entity == null){
			entity = new SpOrder();
		}
		return entity;
	}
	
	/**
	 * 订单管理列表中的数据
	 * 2016年8月15日
	 * By 
	 * @param spOrder
	 * @param model
	 * @return String 订单管理列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpOrder spOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("查询订单管理列表中的数据开始");
		try {
			Map<?, ?> filters = request.getParameterMap();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			Page<SpOrder> page = spOrderService.findPage(new Page<SpOrder>(request, response), queryMap); 
			for (SpOrder so:page.getList()) {
				if (StringUtils.isNotEmpty(so.getUserId()) && 
					StringUtils.isNotEmpty(so.getSpaceId())
				) {
					SpUser spUser = userService.get(so.getUserId());
					if(spUser!=null){
						String userName = spUser.getName();
						if (StringUtils.isNotEmpty(userName)) {
						  so.setUserName(userName);
						}
					}else{
						so.setUserName(so.getUserId());
					}
				
					SpSpace spSpace =spaceService.get(so.getSpaceId());
					if(spSpace!=null){
						String spaceName = spSpace.getName();
						if (StringUtils.isNotEmpty(spaceName)) {
						  so.setSpaceName(spaceName);
						}
					}else{
						so.setSpaceName(so.getSpaceId());
					}
				}
			}
			model.addAttribute("page", page);
			logger.info("查询订单管理列表中的数据结束");
		} catch (Exception e) {
			logger.error("查询订单管理列表中的数据失败！",e);
		}
		return "modules/spadmin/order/spOrderList";
	}

	/**
	 * 订单管理列表中的表单数据
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spOrder
	 * @param model
	 * @return String 订单管理列表中的表单数据
	 */
	@RequestMapping(value = "form")
	public String form(SpOrder spOrder, Model model) {
		logger.info("订单管理列表中的表单数据开始");
		model.addAttribute("spOrder", spOrder);
		logger.info("订单管理列表中的表单数据结束");
		return "modules/spadmin/order/spOrderForm";
	}

	/**
	 * 订单管理列表中的表单数据的保存
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spOrder
	 * @param model
	 * @return String 订单管理列表中的表单数据
	 */
	@RequestMapping(value = "save")
	public String save(SpOrder spOrder, Model model, RedirectAttributes redirectAttributes) {
		logger.info("保存订单管理列表中的表单数据开始");
		try {
			if (!beanValidator(model, spOrder)){
				return form(spOrder, model);
			}
			spOrderService.save(spOrder);
			addMessage(redirectAttributes, "订单管理列表中的表单数据保存成功");
			logger.info("订单管理列表中的表单数据保存结束");
		} catch (Exception e) {
			logger.error("订单管理列表中的表单数据保存失败！",e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/order/spOrder/?repage";
	}
	
	/**
	 * 订单管理列表中的表单数据的删除
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spOrder
	 * @param model
	 * @return String 订单管理列表中的列表
	 */
	@RequestMapping(value = "delete")
	public String delete(SpOrder spOrder, RedirectAttributes redirectAttributes) {
		logger.info("订单管理列表中的表单数据的删除开始");
		try {
			spOrderService.delete(spOrder);
			addMessage(redirectAttributes, "订单管理列表中的表单数据的删除成功");
			logger.info("订单管理列表中的表单数据的删除结束");
		} catch (Exception e) {
			logger.error("订单管理列表中的表单数据的删除失败！",e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/order/spOrder/?repage";
	}

}