/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.projects.web;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.entity.FileInput;
import com.education.classroom.core.common.entity.ReturnMsg;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.project.entity.SpProject;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectResource;
import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.constants.stateConstants;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;

import com.education.classroom.modules.spadmin.projects.service.SpProjectResourceService;
import com.education.classroom.modules.spadmin.projects.service.SpProjectUserService;
import com.education.classroom.modules.spadmin.projects.service.SpProjectsService;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;
import com.education.classroom.utils.classroom.SpaceUtils;
/**
 * 项目管理Controller
 * @author 杨立明
 * @version 2016/08/26
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/projects/spProjects")
public class SpProjectsController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpProjectsService spProjectsService;
	@Autowired
	private SpProjectResourceService spProjectResourceService;
	@Autowired
	private SpProjectUserService spProjectUserService;
	
	
	@ModelAttribute
	public SpProject get(@RequestParam(required=false) String id) {
		SpProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spProjectsService.get(id);
		}
		if (entity == null){
			entity = new SpProject();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpProject spProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("【SpProjectController】.【list】>>>>>>>>>>start>>>>>>>>>>");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filter = request.getParameterMap();
			Map<String,Object>  queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filter, queryMap);
			if (!user.isAdmin()) {
				queryMap.put("createBy", user.getId());
			}
			queryMap.put("delFlag", "0");
			
			Page<SpProject> page = spProjectsService.findPage(new Page<SpProject>(request, response), queryMap); 
			model.addAttribute("page", page);
			
			List<Map<String,String>> lessonList=spProjectsService.getLessonSelect();
			model.addAttribute("lessonList", lessonList);
			List<Map<String,String>> speakerList=spProjectsService.getSpeakerSelect();
			model.addAttribute("speakerList", speakerList);
			//重置查询条件
			model.addAttribute("spProject", spProject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectController】.【list】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpProjectController】.【list】<<<<<<<<<<end<<<<<<<<<<");
		return "modules/spadmin/projects/spProjectsList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpProject spProject, Model model) {
		logger.info("【SpProjectController】.【form】>>>>>>>>>>start>>>>>>>>>>");
		try {
			List<Map<String,String>> lessonList=spProjectsService.getLessonSelect();
			model.addAttribute("lessonList", lessonList);
			List<Map<String,String>> speakerList=spProjectsService.getSpeakerSelect();
			model.addAttribute("speakerList", speakerList);
			
			model.addAttribute("spProject", spProject);
			
			//查询项目资料
			String projectId=spProject.getId();
			List<FileInput> fileList = new ArrayList<FileInput>();
			if(StringUtils.isNoneBlank(projectId)){
				List<SpProjectResource> resourseList= spProjectResourceService.getByProjectId(projectId);
				for (SpProjectResource oneResource : resourseList) {
					FileInput fileOne = new FileInput(oneResource.getId(), oneResource.getName(),oneResource.getBaseUrl());
					fileList.add(fileOne);
				}
			}
			model.addAttribute("fileList", fileList);	
			
			//只能查看详情
			boolean readonlyFlag=spProject.getReadonlyFlag();
			if(readonlyFlag){
				model.addAttribute("readonlyFlag", readonlyFlag);
				//查询参与人名称集合
				List<String> usernameList = spProjectUserService.getUsernameListByProjectId(spProject.getId());
				model.addAttribute("usernameList", usernameList);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectController】.【form】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpProjectController】.【form】>>>>>>>>>>end>>>>>>>>>>");
		return "modules/spadmin/projects/spProjectsForm";
	}
	@RequestMapping(value = "listmy")
	public String listmy(SpProject spProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("【SpProjectController】.【listmy】>>>>>>>>>>start>>>>>>>>>>");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filter = request.getParameterMap();
			Map<String,Object>  queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filter, queryMap);
			if (!user.isAdmin()) {
				//queryMap.put("createBy", user.getId());
			}
			queryMap.put("delFlag", "0");
			queryMap.put("speakerId", UserUtils.getUser().getId());//当前登录用户就是该项目创建者，用来管理自己的项目团队
			
			Page<SpProject> page = spProjectsService.findPage(new Page<SpProject>(request, response), queryMap); 
			model.addAttribute("page", page);
			
			List<Map<String,String>> lessonList=spProjectsService.getLessonSelect();
			model.addAttribute("lessonList", lessonList);
			List<Map<String,String>> speakerList=spProjectsService.getSpeakerSelect();
			model.addAttribute("speakerList", speakerList);
			//重置查询条件
			model.addAttribute("spProject", spProject);
			model.addAttribute("myProject", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectController】.【listmy】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpProjectController】.【listmy】<<<<<<<<<<end<<<<<<<<<<");
		return "modules/spadmin/projects/spProjectsList";
	}
	
	@RequestMapping(value = "save")
	public String save(@RequestParam("fileImg") CommonsMultipartFile fileImg, SpProject spProject, Model model, RedirectAttributes redirectAttributes) {
		logger.info("【SpProjectController】.【save】>>>>>>>>>>start>>>>>>>>>>");
		try {
			if (!beanValidator(model, spProject)){
				return form(spProject, model);
			}
			//检查项目名称是否重复-start
			/*int num=spProjectsService.hasProject(spProject);
			if(num>0){
				logger.error("项目名称不可以重复");
				addMessage(redirectAttributes, "项目名称不可以重复");
				return "redirect:"+Global.getAdminPath()+"/spadmin/projects/spProjects/?repage";
			}*/
			//检查项目名称是否重复-end
			spProject.setSpaceId(SpaceUtils.get("SpaceId"));//添加默认的所属空间
			//上传start
			String resultState;
			Map<String,String> resultMap;
			OSSClientUtil util=new OSSClientUtil();
			System.out.println("---------------------fileName="+fileImg.getOriginalFilename());
			if(fileImg!=null && !"".equals(fileImg.getOriginalFilename())){
				//删除原来的真实文件-start
				SpProject realProject=this.get(spProject.getId());
				String realFile=realProject.getBusinessProposalUrl();
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
					spProject.setBusinessProposalUrl(realPath);//重置路径值
				}else if("0".equals(resultState)){
					String errMsg=resultMap.get("ERRMSG");
					System.out.println("上传商业计划书失败："+errMsg);
					logger.error("上传商业计划书失败："+errMsg);
					addMessage(redirectAttributes, "上传商业计划书失败");
				}else{
					logger.error("调用OSS接口商业计划书失败");
					addMessage(redirectAttributes, "上传商业计划书失败");
				}
				
				//删除切片-start
				String jsonBodyDel = spProject.getJsonBody();
				if (StringUtils.isNotEmpty(jsonBodyDel)){
				//DocResult doc =jsonMapper.fromJson(jsonBodyDel, DocResult.class);
				//	spProjectsService.deleteDoc(doc,util);
				}
				//删除切片-end
				
				//docTools-start
				//DocResult docResult = docConvertService.convertUpload(fileImg, SpaceUtils.getSpaceId());
				/*
				 * if(docResult.isSuccess()){ String jsonBody =
				 * jsonMapper.toJson(docResult).toString(); spProject.setJsonBody(jsonBody); }
				 */
				//docTools-end
			}
			if(util!=null){
				util.destory();
			}
			//上传end
			spProjectsService.save(spProject);//----------------------保存
			addMessage(redirectAttributes, "保存项目成功");
			//添加项目资料-start
			List<String> addResourceIdList = spProject.getAddResourceId();
			if(addResourceIdList!=null){
				Map<String,Object> resourceMap=new HashMap<String,Object>();
				resourceMap.put("projectId", spProject.getId());
				for(int i=0,len=addResourceIdList.size();i<len;i++){
					resourceMap.put("resourceId", addResourceIdList.get(i));
					spProjectResourceService.updateResource(resourceMap);
				}
			}
			//添加项目资料-end
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectController】.【save】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
			addMessage(redirectAttributes, "保存项目失败");
		}
		logger.info("【SpProjectController】.【save】>>>>>>>>>>end>>>>>>>>>>");
		return "redirect:"+Global.getAdminPath()+"/spadmin/projects/spProjects/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpProject spProject, RedirectAttributes redirectAttributes) {
		logger.info("【SpProjectController】.【delete】>>>>>>>>>>start>>>>>>>>>>");
		try {
			spProjectsService.delete(spProject);
			boolean delFileFlag=spProject.getDelFileFlag();
			if(delFileFlag){
				this.deleteFile(spProject.getBusinessProposalUrl());
			}
			addMessage(redirectAttributes, "删除项目成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpProjectController】.【delete】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
			addMessage(redirectAttributes, "删除项目失败");
		}
		logger.info("【SpProjectController】.【delete】>>>>>>>>>>end>>>>>>>>>>");
		return "redirect:"+Global.getAdminPath()+"/spadmin/projects/spProjects/?repage";
	}
	private void deleteFile(String fileUrl){
		System.out.println("商业计划书url="+fileUrl);
		String fileKey=SpResource.getKeyFromUrl(fileUrl);
		System.out.println("商业计划书key="+fileKey);
		OSSClientUtil util=null;
		try{
			util=new OSSClientUtil();
			if(StringUtils.isNotBlank(fileKey)) util.deleteFile(fileKey);
			System.out.println("删除真实商业计划书成功！！！！！！！！！！！！！！！！！！！！！");
		}catch(Exception e){
			System.out.println("删除真实商业计划书报错了！！！！！！！！！！！！！！！！！！！！！");
			logger.error("删除真实商业计划书报错了！！！！！！！！！！！！！！！！！！！！！");
		}finally{
			if(util!=null){
				util.destory();
			}
		}
	}
	
	//添加资源
	@RequestMapping(value = "addResource")
	@ResponseBody
	public String addResource(MultipartFile file,String projectId){
		OSSClientUtil oss = null;
		try{
			oss = new OSSClientUtil();
			Map<String,String> map =oss.uploadFile2Oss(file);
			if("0".equals(map.get("SUCCESS"))){
				return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,map.get("ERRMSG")));
			}
			// 插入套课资源表
			SpProjectResource resource = new SpProjectResource();
			if(StringUtils.isNotEmpty(projectId)){
				resource.setProjectId(projectId);
			}
			resource.setName(file.getOriginalFilename());
			resource.setBaseUrl(oss.getImgOrFileUrl(map.get("NAMEKEY")));
			resource.setCreateDate(new Date());
			resource.setUpdateDate(new Date());
			//docTools-start
			//DocResult docResult = docConvertService.convertUpload(file, SpaceUtils.getSpaceId());
			/*
			 * if(docResult.isSuccess()){ String jsonBody =
			 * jsonMapper.toJson(docResult).toString(); resource.setJsonBody(jsonBody); }
			 */
			//docTools-end
			spProjectResourceService.save(resource);
			
			return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,resource.getId()));
			
		} catch(Exception e){
			logger.error("文件上传失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"文件上传失败"));
			
		} finally {
			if(oss!=null){
				oss.destory();
			}
		}
	}
	//删除资源
	@RequestMapping(value = "delResource")
	@ResponseBody
	public String delResource(String resourceId){
		try{
			spProjectResourceService.deleteById(resourceId);
			SpProjectResource resource=spProjectResourceService.get(resourceId);
			OSSClientUtil oss = null;
			try{
				oss = new OSSClientUtil();
				//删除切片-start
				String jsonBody = resource.getJsonBody();
				if (StringUtils.isNotEmpty(jsonBody)){
					/*
					 * DocResult doc =jsonMapper.fromJson(jsonBody, DocResult.class);
					 * spProjectsService.deleteDoc(doc,oss);
					 */
				}
				//删除切片-end
				if(StringUtils.isNotBlank(resource.getBaseUrl())){
					this.deleteFile(resource.getBaseUrl());
				}
				if(StringUtils.isNotBlank(resource.getThumbImg())){
					this.deleteFile(resource.getThumbImg());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			logger.error("删除套课资源失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"删除套课资源失败"));
		}
		return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE));
	}
	
	@ResponseBody
	@RequestMapping(value = "checkProjectName")
	public String checkProjectName(String projectName,String projectNameOld) {
		if(projectNameOld!=null && projectNameOld.equals(projectName)){
			return "true";
		}
		if (StringUtils.isNotBlank(projectName)) {
			SpProject spProject=new SpProject();
			spProject.setName(projectName);
			int num=spProjectsService.hasProject(spProject);
			if(num>0){
				return "false";
			}
		} 
		return "true";
	}
}