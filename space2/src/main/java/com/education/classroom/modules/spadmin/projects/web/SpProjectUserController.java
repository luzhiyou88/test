/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.projects.web;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectUser;
import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.projects.service.SpProjectUserService;
import com.education.classroom.modules.spadmin.projects.service.SpProjectsService;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;

/**
 * 项目团队管理Controller
 * @author 杨立明
 * @version 2016/09/02
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/projects/spProjectUser")
public class SpProjectUserController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpProjectUserService spProjectUserService;
	@Autowired
	private SpProjectsService spProjectsService;
	
	@ModelAttribute
	public SpProjectUser get(@RequestParam(required=false) String id) {
		SpProjectUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spProjectUserService.get(id);
		}
		if (entity == null){
			entity = new SpProjectUser();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("projectId") String projectId,SpProjectUser spProjectUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("【SpProjectUserController】.【list】>>>>>>>>>>start>>>>>>>>>>");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filter = request.getParameterMap();
			Map<String,Object>  queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filter, queryMap);
			if (!user.isAdmin()) {
				queryMap.put("createBy", user.getId());
			}
			queryMap.put("delFlag", "0");
			
			Page<SpProjectUser> page = spProjectUserService.findPage(new Page<SpProjectUser>(request, response), queryMap); 
			model.addAttribute("page", page);
			
			List<Map<String,String>> projectList=spProjectsService.getProjectSelect();
			model.addAttribute("projectList", projectList);
			//重置查询条件
			model.addAttribute("spProjectUser", spProjectUser);
			model.addAttribute("spProjectUser", spProjectUser);//继续绑定projectId
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectUserController】.【list】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpProjectUserController】.【list】>>>>>>>>>>start>>>>>>>>>>");
		return "modules/spadmin/projects/spProjectUserList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpProjectUser spProjectUser, Model model) {
		logger.info("【SpProjectUserController】.【form】>>>>>>>>>>start>>>>>>>>>>");
		try {
			List<Map<String,String>> projectList=spProjectsService.getProjectSelect();
			model.addAttribute("projectList", projectList);
			
			model.addAttribute("spProjectUser", spProjectUser);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectUserController】.【form】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpProjectUserController】.【form】>>>>>>>>>>start>>>>>>>>>>");
		return "modules/spadmin/projects/spProjectUserForm";
	}

	
	@RequestMapping(value = "save")
	public String save(@RequestParam("fileImg") CommonsMultipartFile fileImg, SpProjectUser spProjectUser, Model model, RedirectAttributes redirectAttributes) {
		logger.info("【SpProjectUserController】.【save】>>>>>>>>>>start>>>>>>>>>>");
		try {
			if (!beanValidator(model, spProjectUser)){
				return form(spProjectUser, model);
			}
			
			//上传start
			String resultState;
			Map<String,String> resultMap;
			OSSClientUtil util=new OSSClientUtil();
			System.out.println("---------------------fileName="+fileImg.getOriginalFilename());
			if(fileImg!=null && !"".equals(fileImg.getOriginalFilename())){
				//删除原来的真实文件-start
				SpProjectUser realProjectUser=this.get(spProjectUser.getId());
				String realFile=realProjectUser.getHeaderImg();
				if(StringUtils.isNotBlank(realFile)){
					this.deleteFile(realFile);
				}
				//删除原来的真实文件-end
				resultMap=util.uploadImg2Oss(fileImg);//filename其实是拼接的一个key，保存到baseUrl
				System.out.println("=====================resultMap="+resultMap);
				resultState=resultMap.get("SUCCESS");
				if("1".equals(resultState)){
					String filename=resultMap.get("NAMEKEY");
					//System.out.println("文件KEY（保存在数据库）："+filename);
					String realPath=util.getImgOrFileUrl(filename);
					//System.out.println("文件URL（访问全路径）："+realPath);
					spProjectUser.setHeaderImg(realPath);//重置路径值
				}else if("0".equals(resultState)){
					String errMsg=resultMap.get("ERRMSG");
					System.out.println("上传成员头像失败："+errMsg);
					logger.error("上传成员头像失败："+errMsg);
					addMessage(redirectAttributes, "上传成员头像失败");
				}else{
					logger.error("调用OSS接口成员头像失败");
					addMessage(redirectAttributes, "上传成员头像失败");
				}
			}
			if(util!=null){
				util.destory();
			}
			//上传end
			
			spProjectUserService.save(spProjectUser);//----------------------保存
			addMessage(redirectAttributes, "保存项目团队成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectUserController】.【save】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpProjectUserController】.【save】>>>>>>>>>>start>>>>>>>>>>");
		return "redirect:"+Global.getAdminPath()+"/spadmin/projects/spProjectUser/?repage&projectId="+spProjectUser.getProjectId();
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpProjectUser spProjectUser, RedirectAttributes redirectAttributes) {
		logger.info("【SpProjectUserController】.【delete】>>>>>>>>>>start>>>>>>>>>>");
		try {
			spProjectUserService.delete(spProjectUser);
			addMessage(redirectAttributes, "删除项目团队成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectUserController】.【delete】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpProjectUserController】.【delete】>>>>>>>>>>start>>>>>>>>>>");
		return "redirect:"+Global.getAdminPath()+"/spadmin/projects/spProjectUser/?repage";
	}
	
	private void deleteFile(String fileUrl){
		//System.out.println("成员头像url="+fileUrl);
		String fileKey=SpResource.getKeyFromUrl(fileUrl);
		//System.out.println("成员头像key="+fileKey);
		OSSClientUtil util=null;
		try{
			util=new OSSClientUtil();
			if(StringUtils.isNotBlank(fileKey)) util.deleteFile(fileKey);
			System.out.println("删除真实成员头像成功！！！！！！！！！！！！！！！！！！！！！");
		}catch(Exception e){
			System.out.println("删除真实成员头像报错了！！！！！！！！！！！！！！！！！！！！！");
			logger.error("删除真实成员头像报错了！！！！！！！！！！！！！！！！！！！！！");
		}finally{
			if(util!=null){
				util.destory();
			}
		}
	}
	
}