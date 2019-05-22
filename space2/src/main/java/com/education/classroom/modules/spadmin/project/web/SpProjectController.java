package com.education.classroom.modules.spadmin.project.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.education.classroom.core.common.type.DeleteFlagType;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.modules.spadmin.project.entity.SpProject;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.project.service.SpProjectService;
import com.education.classroom.utils.StringUtils;

/**
 * 路演项目控制器
 * @Class Name SpProjectController
 * @author zhangyongsheng
 * @Create In 2016年8月29日
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/project")
public class SpProjectController extends BaseController {
	
	@Autowired
	private SpProjectService projectService;
	
	@ModelAttribute
	public SpProject get(@RequestParam(required=false) String id) {
		SpProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectService.get(id);
		}
		if (entity == null){
			entity = new SpProject();
		}
		return entity;
	}

	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<SpProject> page = new Page<SpProject>();
		try {
            Map<?, ?> filters = request.getParameterMap();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap = CommonUtils.copyQueryMap(filters, queryMap);
            queryMap.put("delFlag", DeleteFlagType.NORMAL);
            page = projectService.findList(new Page<SpProject>(request, response), queryMap);
        } catch (Exception e) {
            logger.error("路演相关项目列表查询异常", e);
        }
        model.addAttribute("page", page);
        model.addAttribute("lessonId", request.getParameter("lessonId"));
		return "modules/spadmin/project/spProjectList";
	}
	
	@RequestMapping(value = "form")
	public String form(SpProject spProject, Model model) {
        spProject = projectService.get(spProject.getId());
        model.addAttribute("project", spProject);
        model.addAttribute("lessonId", spProject.getLessonId());
		return "modules/spadmin/project/spProjectForm";
	}
	
}
