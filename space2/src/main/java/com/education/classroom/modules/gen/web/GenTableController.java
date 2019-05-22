package com.education.classroom.modules.gen.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.gen.entity.GenTable;
import com.education.classroom.modules.gen.service.GenTableService;
import com.education.classroom.modules.gen.util.GenUtils;
import com.education.classroom.utils.StringUtils;

/**
 * 业务表Controller
 * @author ThinkGem
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genTable")
public class GenTableController extends BaseController {

	@Autowired
	private GenTableService genTableService;
	
	@ModelAttribute
	public GenTable get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return genTableService.get(id);
		}else{
			return new GenTable();
		}
	}
	
	@RequiresPermissions("gen:genTable:view")
	@RequestMapping(value = {"list", ""})
	public String list(GenTable genTable, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Map<?, ?> filters = request.getParameterMap();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap = CommonUtils.copyQueryMap(filters, queryMap);
		if (!user.isAdmin()) {
			queryMap.put("createBy", user.getId());
		}
		queryMap.put("delFlag", "0");
		Page<GenTable> page = genTableService.findPage(new Page<GenTable>(
				request, response), queryMap);
		model.addAttribute("page", page);
		return "modules/gen/genTableList";
	}

	@RequiresPermissions("gen:genTable:view")
	@RequestMapping(value = "form")
	public String form(GenTable genTable, Model model) {
		// 获取物理表列表
		List<GenTable> tableList = genTableService.findTableListFormDb(new GenTable());
		model.addAttribute("tableList", tableList);
		// 验证表是否存在
		if (StringUtils.isBlank(genTable.getId()) && !genTableService.checkTableName(genTable.getName())){
			addMessage(model, "下一步失败！" + genTable.getName() + " 表已经添加！");
			genTable.setName("");
		}
		// 获取物理表字段
		else{
			genTable = genTableService.getTableFormDb(genTable);
		}
		model.addAttribute("genTable", genTable);
		model.addAttribute("config", GenUtils.getConfig());
		return "modules/gen/genTableForm";
	}

	@RequiresPermissions("gen:genTable:edit")
	@RequestMapping(value = "save")
	public String save(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, genTable)){
			return form(genTable, model);
		}
		// 验证表是否已经存在
		if (StringUtils.isBlank(genTable.getId()) && !genTableService.checkTableName(genTable.getName())){
			addMessage(model, "保存失败！" + genTable.getName() + " 表已经存在！");
			genTable.setName("");
			return form(genTable, model);
		}
		genTableService.save(genTable);
		addMessage(redirectAttributes, "保存业务表'" + genTable.getName() + "'成功");
		return "redirect:" + adminPath + "/gen/genTable/?repage";
	}
	
	@RequiresPermissions("gen:genTable:edit")
	@RequestMapping(value = "delete")
	public String delete(GenTable genTable, RedirectAttributes redirectAttributes) {
		genTableService.delete(genTable);
		addMessage(redirectAttributes, "删除业务表成功");
		return "redirect:" + adminPath + "/gen/genTable/?repage";
	}

}