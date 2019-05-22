/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.ordertran.web;

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
import com.education.classroom.core.modules.spadmin.ordertran.entity.SpOrderTran;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.ordertran.service.SpOrderTranService;
import com.education.classroom.utils.StringUtils;

/**
 * 订单流水表增删改查Controller
 * @author 赵新月
 * @version 2016/08/17
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/ordertran/spOrderTran")
public class SpOrderTranController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpOrderTranService spOrderTranService;
	
	@ModelAttribute
	public SpOrderTran get(@RequestParam(required=false) String id) {
		SpOrderTran entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spOrderTranService.get(id);
		}
		if (entity == null){
			entity = new SpOrderTran();
		}
		return entity;
	}
	
	/**
	 * 订单流水管理列表中的数据
	 * 2016年8月15日
	 * By 
	 * @param spOrderTran
	 * @param model
	 * @return String 订单流水管理列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpOrderTran spOrderTran, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("查询订单流水管理列表中的数据开始");
		try {
			Map<?, ?> filters = request.getParameterMap();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			Page<SpOrderTran> page = spOrderTranService.findPage(new Page<SpOrderTran>(request, response), queryMap); 
			model.addAttribute("page", page);
			logger.info("查询订单流水管理列表中的数据结束");
		} catch (Exception e) {
			logger.error("查询订单流水管理列表中的数据失败！",e);
		}
		return "modules/spadmin/ordertran/spOrderTranList";
	}

	/**
	 * 订单流水管理列表中表单的数据
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spOrderTran
	 * @param model
	 * @return String 订单流水管理列表中表单的数据
	 */
	@RequestMapping(value = "form")
	public String form(SpOrderTran spOrderTran, Model model) {
		logger.info("订单流水管理列表中表单的数据开始");
		model.addAttribute("spOrderTran", spOrderTran);
		logger.info("订单流水管理列表中表单的数据结束");
		return "modules/spadmin/ordertran/spOrderTranForm";
	}

	/**
	 * 订单流水管理列表中表单的数据保存
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spOrderTran
	 * @param model
	 * @return String 订单流水管理列表中表单的数据列表
	 */
	@RequestMapping(value = "save")
	public String save(SpOrderTran spOrderTran, Model model, RedirectAttributes redirectAttributes) {
		logger.info("订单流水管理列表中表单的数据保存开始");
		try {
			if (!beanValidator(model, spOrderTran)){
				return form(spOrderTran, model);
			}
			spOrderTranService.save(spOrderTran);
			addMessage(redirectAttributes, "订单流水管理列表中表单的数据保存成功");
			logger.info("订单流水管理列表中表单的数据保存结束");
		} catch (Exception e) {
			logger.error("订单流水管理列表中表单的数据保存失败！",e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/ordertran/spOrderTran/?repage";
	}
	
	/**
	 * 订单流水管理列表中表单的数据的删除
	 * 2016年8月15日
	 * By shangjunwei
	 * @param spOrderTran
	 * @param model
	 * @return String 订单流水管理列表中表单的数据列表
	 */
	@RequestMapping(value = "delete")
	public String delete(SpOrderTran spOrderTran, RedirectAttributes redirectAttributes) {
		logger.info("订单流水管理列表中表单的数据开始");
		try {
			spOrderTranService.delete(spOrderTran);
			addMessage(redirectAttributes, "订单流水管理列表中表单的数据删除成功");
			logger.info("订单流水管理列表中表单的数据删除结束");
		} catch (Exception e) {
			logger.error("订单流水管理列表中表单的数据删除失败！",e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/ordertran/spOrderTran/?repage";
	}

}