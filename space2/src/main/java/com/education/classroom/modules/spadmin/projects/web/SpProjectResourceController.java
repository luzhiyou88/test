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
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectResource;
import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;

import com.education.classroom.modules.spadmin.projects.service.SpProjectResourceService;
import com.education.classroom.modules.spadmin.projects.service.SpProjectsService;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 项目资源管理Controller
 * @author 杨立明
 * @version 2016/08/30
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/projects/spProjectResource")
public class SpProjectResourceController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpProjectResourceService spProjectResourceService;
	@Autowired
	private SpProjectsService spProjectsService;
	
	
	@ModelAttribute
	public SpProjectResource get(@RequestParam(required=false) String id) {
		SpProjectResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spProjectResourceService.get(id);
		}
		if (entity == null){
			entity = new SpProjectResource();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpProjectResource spProjectResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			User user = UserUtils.getUser();
			Map<?,?> filter = request.getParameterMap();
			Map<String,Object>  queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filter, queryMap);
			if (!user.isAdmin()) {
				queryMap.put("createBy", user.getId());
			}
			queryMap.put("delFlag", "0");
			
			Page<SpProjectResource> page = spProjectResourceService.findPage(new Page<SpProjectResource>(request, response), queryMap); 
			model.addAttribute("page", page);
			
			List<Map<String,String>> projectList=spProjectsService.getProjectSelect();
			model.addAttribute("projectList", projectList);
			//重置查询条件
			model.addAttribute("spProjectResource", spProjectResource);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectResourceController】.【list】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		return "modules/spadmin/projects/spProjectResourceList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpProjectResource spProjectResource, Model model) {
		try {
			List<Map<String,String>> projectList=spProjectsService.getProjectSelect();
			model.addAttribute("projectList", projectList);
			
			model.addAttribute("spProjectResource", spProjectResource);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectResourceController】.【form】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		return "modules/spadmin/projects/spProjectResourceForm";
	}

	
	@RequestMapping(value = "save")
	public String save(@RequestParam("file") CommonsMultipartFile file, @RequestParam("fileImg") CommonsMultipartFile fileImg, SpProjectResource spProjectResource, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, spProjectResource)){
				return form(spProjectResource, model);
			}
			
			//上传start
			String resultState;
			Map<String,String> resultMap;
			OSSClientUtil util=new OSSClientUtil();
			System.out.println("---------------------fileName="+file.getOriginalFilename());
			if(file!=null && !"".equals(file.getOriginalFilename())){
				//删除原来的真实文件-start
				SpProjectResource realProject=this.get(spProjectResource.getId());
				String realFile=realProject.getBaseUrl();
				if(StringUtils.isNotBlank(realFile)){
					this.deleteFile(realFile);
				}
				//删除原来的真实文件-end
				
				resultMap=util.uploadImg2Oss(file);//filename其实是拼接的一个key，保存到baseUrl
				System.out.println("=====================resultMap="+resultMap);
				resultState=resultMap.get("SUCCESS");
				if("1".equals(resultState)){
					String filename=resultMap.get("NAMEKEY");
					//System.out.println("文件KEY（保存在数据库）："+filename);
					String realPath=util.getImgOrFileUrl(filename);
					//System.out.println("文件URL（访问全路径）："+realPath);
					spProjectResource.setBaseUrl(realPath);//重置路径值
					addMessage(redirectAttributes, "保存项目资源成功");
				}else if("0".equals(resultState)){
					String errMsg=resultMap.get("ERRMSG");
					System.out.println("上传项目资源失败："+errMsg);
					logger.error("上传项目资源失败："+errMsg);
					addMessage(redirectAttributes, "上传项目资源失败");
				}else{
					logger.error("调用OSS接口项目资源失败");
					addMessage(redirectAttributes, "上传项目资源失败");
				}
			}
			System.out.println("---------------------fileName="+fileImg.getOriginalFilename());
			if(fileImg!=null && !"".equals(fileImg.getOriginalFilename())){
				//删除原来的真实文件-start
				SpProjectResource realProject=this.get(spProjectResource.getId());
				String realFileImg=realProject.getThumbImg();
				if(StringUtils.isNotBlank(realFileImg)){
					this.deleteFile(realFileImg);
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
					spProjectResource.setThumbImg(realPath);//重置路径值
					addMessage(redirectAttributes, "保存项目资源图片成功");
				}else if("0".equals(resultState)){
					String errMsg=resultMap.get("ERRMSG");
					System.out.println("上传项目资源图片失败："+errMsg);
					logger.error("上传项目资源图片失败："+errMsg);
					addMessage(redirectAttributes, "上传项目资源图片失败");
				}else{
					logger.error("调用OSS接口项目资源图片失败");
					addMessage(redirectAttributes, "上传项目资源图片失败");
				}
			}
			if(util!=null){
				util.destory();
			}
			//上传end
			//docTools-start
			/*
			 * DocResult docResult = docConvertService.convertUpload(fileImg,
			 * SpaceUtils.getSpaceId()); if(docResult.isSuccess()){ String jsonBody =
			 * jsonMapper.toJson(docResult).toString();
			 * spProjectResource.setJsonBody(jsonBody); }
			 */
			//docTools-end
			spProjectResourceService.save(spProjectResource);//----------------------保存
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectResourceController】.【save】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/projects/spProjectResource/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpProjectResource spProjectResource, RedirectAttributes redirectAttributes) {
		try {
			spProjectResourceService.delete(spProjectResource);
			//删除真实文件-start
			String realFile=spProjectResource.getBaseUrl();
			if(StringUtils.isNotBlank(realFile)){
				this.deleteFile(realFile);
			}
			String realFileImg=spProjectResource.getThumbImg();
			if(StringUtils.isNotBlank(realFileImg)){
				this.deleteFile(realFileImg);
			}
			//删除真实文件-end
			addMessage(redirectAttributes, "删除项目资源成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectResourceController】.【delete】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/projects/spProjectResource/?repage";
	}
	
	private void deleteFile(String fileUrl){
		String fileKey=SpResource.getKeyFromUrl(fileUrl);
		OSSClientUtil util=null;
		try{
			util=new OSSClientUtil();
			if(StringUtils.isNotBlank(fileKey)) util.deleteFile(fileKey);
			System.out.println("删除真实wenjian成功！！！！！！！！！！！！！！！！！！！！！");
		}catch(Exception e){
			System.out.println("删除真实wenjian报错了！！！！！！！！！！！！！！！！！！！！！");
			logger.error("删除真实wenjian报错了！！！！！！！！！！！！！！！！！！！！！");
		}finally{
			if(util!=null){
				util.destory();
			}
		}
	}
}