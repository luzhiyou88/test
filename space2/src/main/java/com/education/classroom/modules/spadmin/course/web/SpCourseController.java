/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.course.web;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.common.entity.FileInput;
import com.education.classroom.core.common.entity.ReturnMsg;
import com.education.classroom.core.common.type.DomainType;
import com.education.classroom.core.common.type.LessonSourceType;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.course.entity.SpCourse;
import com.education.classroom.core.modules.spadmin.course.type.CourseType;
import com.education.classroom.core.modules.spadmin.courseResource.entity.SpCourseResource;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.constants.stateConstants;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;

import com.education.classroom.modules.spadmin.category.service.SpCategoryService;
import com.education.classroom.modules.spadmin.course.service.SpCourseService;
import com.education.classroom.modules.spadmin.courseresource.service.SpCourseResourceService;
import com.education.classroom.modules.spadmin.lesson.service.SpLessonService;

import com.education.classroom.utils.Collections3;
import com.education.classroom.utils.DateUtils;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;
import com.education.classroom.utils.classroom.SpaceUtils;
import com.education.classroom.utils.classroom.upload.FtpDownloadUtil;

/**
 * 套课管理Controller
 * @author 朱杰
 * @version 2016/08/06
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/course/spCourse")
public class SpCourseController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpCourseService spCourseService;
	@Autowired
	private SpCourseResourceService spCourseResourceService;	
	@Autowired
	private SpCategoryService spCategoryService;
	@Autowired
	private SpLessonService spLessonService;
	
	
	@ModelAttribute
	public SpCourse get(@RequestParam(required=false) String id) {
		SpCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spCourseService.get(id);
		}
		if (entity == null){
			entity = new SpCourse();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(SpCourse spCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<SpCourse> page = new Page<SpCourse>();
		try {
            User user = UserUtils.getUser();
            Map<?, ?> filters = request.getParameterMap();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap = CommonUtils.copyQueryMap(filters, queryMap);
            if (!user.isAdmin()) {
                queryMap.put("createBy", user.getId());
            }
            page = spCourseService.findList(new Page<SpCourse>(request, response), queryMap);
            // TODO ：获取套课下课程在平台的状态
            
        } catch (Exception e) {
            logger.error("套课列表查询异常", e);
        }
        model.addAttribute("page", page);
        // 套课分类
 		model.addAttribute("spCategoryList", spCategoryService.findAllListForDict());
		return "modules/spadmin/course/spCourseList";
	}

	
	@RequestMapping(value = "form")
	public String form(SpCourse spCourse, Model model,RedirectAttributes redirectAttributes) {
		try{
			model.addAttribute("spCourse", spCourse);
			// 套课资源		
			List<SpCourseResource> rLst= spCourseResourceService.getByCourseId(spCourse.getId());
			List<FileInput> fLst = new ArrayList<FileInput>();
			for (SpCourseResource spCourseResource : rLst) {
				FileInput fi = new FileInput(spCourseResource.getId(), spCourseResource.getName(),spCourseResource.getBaseUrl());
				fLst.add(fi);
			}
			model.addAttribute("fileList", fLst);		
			// 等待审核或者发布过的课程数
			int rr= spCourseService.getPublishedLessonNum(spCourse.getId());
			model.addAttribute("publishedLessonNum",rr);
		}catch(Exception e){
			logger.error("套课初始化失败",e);
			addMessage(redirectAttributes, "套课初始化失败");
		}
		model.addAttribute("localCourse", LessonSourceType.LOCAL.equals(spCourse.getCourseSource()));
		
		return "modules/spadmin/course/spCourseForm";
	}

	
	@RequestMapping(value = "save")
	public String save(SpCourse spCourse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, spCourse)){
			return form(spCourse, model, redirectAttributes);
		}
		spCourseService.save(spCourse);
		addMessage(redirectAttributes, "保存套课成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/course/spCourse/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SpCourse spCourse, RedirectAttributes redirectAttributes) {
		spCourseService.delete(spCourse);
		addMessage(redirectAttributes, "删除套课成功");
		return "redirect:"+Global.getAdminPath()+"/spadmin/course/spCourse/?repage";
	}
	
	/**
	 * 检查套课名称唯一性
	 * 
	 * 2016年8月6日
	 * By zhujie
	 * @param curriculumName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkCourseName")
	public String checkCurriculumName(String id, String courseName){
		logger.info("校验套课名称的唯一性开始");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("courseName", courseName);
		List<SpCourse> list = spCourseService.findAllList(param);
		if(list.size() > 1 || (list.size()==1 && !list.get(0).getId().equals(id))){
			// 相同套课名称大于2 或者套课名称是1但是并非当前套课的情况
			logger.info("校验套课名称的唯一性结束");
			return stateConstants.FALSE;
		}
		logger.info("校验套课名称的唯一性结束");
		return stateConstants.TRUE;		
	}
	
	/**
	 * 套课资源上传
	 * 2016年8月16日
	 * By zhujie
	 * @param file
	 * @param courseId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "upload")
	@ResponseBody
	public String upload(MultipartFile file,String courseId) throws Exception{
		OSSClientUtil oss = null;
		try{
			oss = new OSSClientUtil();
			Map<String,String> map =oss.uploadFile2Oss(file);
			if("0".equals(map.get("SUCCESS"))){
				return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,map.get("ERRMSG")));
			}
			
			
			
			// 插入套课资源表
			SpCourseResource scr = new SpCourseResource();
			if(StringUtils.isNotEmpty(courseId)){
				scr.setCourseId(courseId);
			}
			scr.setName(file.getOriginalFilename());
			// TODO
			scr.setType("3");
			scr.setDomain(DomainType.SITE);
			scr.setBaseUrl(oss.getImgOrFileUrl(map.get("NAMEKEY")));
			
			spCourseResourceService.save(scr);
			
			return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,scr.getId()));
			
		} catch(Exception e){
			logger.error("文件上传失败",e);
			//return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"文件上传失败"));
			throw new Exception("上传文件失败 文件页数不能大于200!");
			
		} finally {
			if(oss!=null){
				oss.destory();
			}
		}
	}
	
	/**
	 * 删除套课资源
	 * 2016年8月15日
	 * By zhujie
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "deleteResource")
	@ResponseBody
	public String deleteResource(String resourceId){
		try{
			spCourseResourceService.deleteResource(resourceId);
		}catch(Exception e){
			logger.error("删除套课资源失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"删除套课资源失败"));
		}
		return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE));
	}
	
	/**
	 * 1、收费套课：查看课程下的所有课程时间是否与本地冲突
	 * 2、查看套课是否本地存在
	 * 2016年8月16日
	 * By zhujie
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "checkCourseExist")
	@ResponseBody
	public String checkCourseExist(String courseId){
		try{
			SpCourse spCourse = null;
			if(spCourse==null || StringUtils.isEmpty(spCourse.getId())){
				return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"没有套课"));
			}
			if(CourseType.SF.getCode().equals(spCourse.getCourseType())){
				// 收费套课情况下
				// 查看课程下的所有课程时间是否与本地冲突
				// 根据套课查询
				SpLesson param = new SpLesson();
				param.setCourseId(courseId);
				List<SpLesson> publishLessonList = null;
				if(publishLessonList==null || publishLessonList.size()==0){
					return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"没有套课课程"));
				}
				Map<String,Object> tParam = new HashMap<String,Object>();
				tParam.put("checkTimeParam", publishLessonList);
				tParam.put("checkExcludedIds", Collections3.extractToList(publishLessonList,"id"));// 排除已经订阅课程id
				List<SpLesson> conflictList = spLessonService.findAllList(tParam);
				if(conflictList.size()>0){
					String msg="订阅失败:";
					msg += "<br/>";
					for (SpLesson spLesson : conflictList) {
						msg += "该套课下的平台课程与课程【"+spLesson.getName()+"】"
								+"("+DateUtils.formatDate(spLesson.getLessonDate(),"yyyy/MM/dd") 
								+" "+spLesson.getLessonStarttime()+"~"+ spLesson.getLessonEndtime() +")产生冲突";
						msg += "<br/>";
					}
					int count = spCourseService.getSubscribedLessonCount(courseId);
					return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,msg,count > 0));// data表示是否套课下有课程已经添加
				}
			}
			
			SpCourse spCourseLocal= this.get(courseId);
			boolean exist =  spCourse!=null && StringUtils.isNotEmpty(spCourseLocal.getId());
			return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,null, exist ? stateConstants.TRUE : stateConstants.FALSE));
		}catch(Exception e){
			logger.error("套课查看失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"操作失败"));
		}
	}
	
	/**
	 * 获取平台套课信息
	 * 2016年8月16日
	 * By zhujie
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="getPlCourse")
	public String getPlCourse(String courseId, Model model,RedirectAttributes redirectAttributes){
		try{
			SpCourse spCourse = null;
			List<FileInput> fLst = new ArrayList<FileInput>();
			if(spCourse.getCourseResource() != null){
				for (SpCourseResource spCourseResource : spCourse.getCourseResource()) {
					FileInput fi = new FileInput(spCourseResource.getId(), spCourseResource.getName(),spCourseResource.getBaseUrl());
					fLst.add(fi);
				}
			}			
			model.addAttribute("fileList", fLst);
			// 清空套课资源，减缓数据传输
			spCourse.setCourseResource(null);
			model.addAttribute("spCourse", spCourse);
			
		}catch(Exception e){
			logger.error("获取平台套课信息失败",e);
			addMessage(redirectAttributes, "获取平台套课信息");
		}
		
		return "modules/spadmin/course/spCourseModal";
	}
	
	@RequestMapping(value = "download")
	public void download(String fileKey, HttpServletResponse response) {
		logger.info("fileDownload文件下载......");
		SpCourseResource scr = spCourseResourceService.get(fileKey);
		if(scr==null){
			FtpDownloadUtil.fileDownload(response,null,null);
		}else{
			FtpDownloadUtil.fileDownload(response,scr.getBaseUrl(),scr.getName());
		}		
	}
}