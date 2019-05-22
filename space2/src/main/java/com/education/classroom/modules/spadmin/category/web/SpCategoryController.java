/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.category.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.education.classroom.core.modules.spadmin.category.entity.SpCategory;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.constants.stateConstants;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.category.service.SpCategoryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 分类管理Controller
 * @author 尚军伟
 * @version 2016-08-05
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/category/spCategory")
public class SpCategoryController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpCategoryService spCategoryService;
	
	@ModelAttribute
	public SpCategory get(@RequestParam(required=false) String id) {
		SpCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spCategoryService.get(id);
		}
		if (entity == null){
			entity = new SpCategory();
		}
		return entity;
	}
	
	
	/**
	 * 类型管理列表中的数据
	 * 2016年8月5日
	 * By shangjunwei
	 * @param spCategory
	 * @param model
	 * @return String 类型管理列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpCategory spCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("类型管理列表中的数据开始");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			if(user.isAdmin()){
				queryMap.put("createBy",user.getId());
			}
			queryMap.put("delFlag","0");
			Page<SpCategory> page = spCategoryService.findPage(new Page<SpCategory>(request, response), queryMap); 
			for (SpCategory ct : page.getList()) {
			    if (ct != null) {
			        ct.setParent(spCategoryService.get(ct.getParentId()));
			    }
			}
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("查询类型管理列表中的数据失败！",e);
		}
		logger.info("类型管理列表中的数据结束");
		return "modules/spadmin/category/spCategoryList";
	}

	
	/**
	 * 类型管理列表中的表单
	 * 2016年8月5日
	 * By shangjunwei
	 * @param spCategory
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(SpCategory spCategory, Model model) {
		logger.info("查询类型管理列表中的表单开始");
		try {
			if (spCategory != null) {
				spCategory.setParent(spCategoryService.get(spCategory.getParentId()));
			}
			model.addAttribute("spCategory", spCategory);
		} catch (Exception e) {
			logger.error("查询类型管理列表中的表单失败！",e);
		}
		logger.info("查询类型管理列表中的表单结束");
		return "modules/spadmin/category/spCategoryForm";
	}

	
	/**
	 * 类型管理列表中类型添加
	 * 2016年8月5日
	 * By shangjunwei
	 * @param spCategory
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "save")
	public String save(SpCategory spCategory, Model model, RedirectAttributes redirectAttributes) {
		logger.info("类型管理列表中类型添加开始");
		try {
			if (!beanValidator(model, spCategory)){
				return form(spCategory, model);
			}
			spCategoryService.save(spCategory);
			addMessage(redirectAttributes, "类型管理列表中类型添加成功");
		} catch (Exception e) {
			logger.error( "类型管理列表中类型添加失败" ,e);
		}
		logger.info("类型管理列表中类型添加结束");
		return "redirect:"+Global.getAdminPath()+"/spadmin/category/spCategory/?repage";
	}
	
	

	/**
	 * 类型管理列表中类型的删除
	 * 2016年8月5日
	 * By shangjunwei
	 * @param spMemberPostage
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "delete")
	public String delete(SpCategory spCategory, RedirectAttributes redirectAttributes) {
		logger.info("类型管理列表中类型的删除开始");
		try {
			spCategoryService.delete(spCategory);
			addMessage(redirectAttributes, "类型管理列表中类型的删除成功");
		} catch (Exception e) {
			logger.error( "类型管理列表中类型的删除失败:" + e);
		}
		logger.info("类型管理列表中类型的删除结束");
		return "redirect:"+Global.getAdminPath()+"/spadmin/category/spCategory/?repage";
	}
	
	  /**
     * 类型树形结构查询
     * 
     * @param extId
     * @param response
     * @return
     */
    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(
            @RequestParam(required = false) String extId,
            HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        try {
        	SpCategory ct = new SpCategory();
            ct.setDelFlag(stateConstants.NODEL);
            List<SpCategory> list = spCategoryService.findList(ct);
            for (int i = 0; i < list.size(); i++) {
            	SpCategory e = list.get(i);
                if (StringUtils.isBlank(extId)
                    || (extId != null && !extId.equals(e.getId()))) {
                    Map<String, Object> map = Maps.newHashMap();
                    map.put("id", e.getId());
                    map.put("pId", e.getParent() == null ? 0 : e.getParent()
                            .getId());
                    map.put("name", e.getName());
                    mapList.add(map);
                }
            }
        } catch (Exception e) {
            logger.error("类型树形结构查询异常", e);
        }
        return mapList;
    }

     
    @RequestMapping(value = "tree")
	public String tree(Model model) {
		 List<Map<String, Object>> mapList = Lists.newArrayList();
	            String  extId = "";
	            SpCategory ct = new SpCategory();
	            ct.setDelFlag(stateConstants.NODEL);
	            List<SpCategory> list = spCategoryService.findList(ct);
	            for (int i = 0; i < list.size(); i++) {
	            	SpCategory e = list.get(i);
	                if (StringUtils.isBlank(extId)
	                    || (extId != null && !extId.equals(e.getId()))) {
	                    Map<String, Object> map = Maps.newHashMap();
	                    map.put("id", e.getId());
	                    map.put("pId", e.getParent() == null ? 0 : e.getParent()
	                            .getId());
	                    map.put("name", e.getName());
	                    mapList.add(map);
	                }
	            }
		model.addAttribute("typeList", mapList);
		return "modules/spadmin/category/spCategoryTree";
	}
	
	@RequestMapping(value = "none")
	public String none() {
		return "redirect:"+Global.getAdminPath()+"/spadmin/category/spCategory/list?repage";
	}
	
	@RequestMapping(value = "index")
	public String index() {
		return "modules/spadmin/category/spCategoryIndex";
	}
}