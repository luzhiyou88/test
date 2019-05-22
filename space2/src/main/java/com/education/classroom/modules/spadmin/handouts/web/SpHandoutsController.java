package com.education.classroom.modules.spadmin.handouts.web;

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
import com.education.classroom.core.common.type.LessonType;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.modules.spadmin.handouts.entity.SpHandouts;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.constants.stateConstants;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;

import com.education.classroom.modules.spadmin.common.util.SpCommonUtils;
import com.education.classroom.modules.spadmin.course.service.SpCourseService;
import com.education.classroom.modules.spadmin.handouts.service.SpHandoutsService;
import com.education.classroom.modules.spadmin.lesson.service.SpLessonService;
import com.education.classroom.modules.spadmin.section.service.SpSectionService;
import com.education.classroom.modules.spadmin.user.service.SpUserService;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 讲义Controller
 * @author shangjunwei
 * @version 2016/08/10
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/handouts/spHandouts")
public class SpHandoutsController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpHandoutsService handoutsService;
	@Autowired
	private SpLessonService lessonService;
	
	@Autowired
	private SpCourseService spCourseService;
	@Autowired
	private SpSectionService spSectionService;
	@Autowired
	private SpUserService spUserService;

	
	@ModelAttribute
	public SpHandouts get(@RequestParam(required=false) String id) {
		SpHandouts entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = handoutsService.get(id);
		}
		if (entity == null){
			entity = new SpHandouts();
		}
		return entity;
	}
	
	
	/**
	 * 查询讲义列表数据
	 * 2016年8月16日
	 * By shangjunwei
	 * @param spLesson
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpLesson spLesson, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("查询讲义列表数据开始");
		Page<SpLesson> page = new Page<SpLesson>();
		try {
            User user = UserUtils.getUser();
            Map<?, ?> filters = request.getParameterMap();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap = CommonUtils.copyQueryMap(filters, queryMap);
            if (!user.isAdmin()) {
                queryMap.put("teacherId", user.getId());
            }
            queryMap.put("lessonType", LessonType.LESSON);
            page = lessonService.findListAndHandouts(new Page<SpLesson>(request, response), queryMap);
        } catch (Exception e) {
            logger.error("查询讲义列表数据异常", e);
        }
        model.addAttribute("page", page);
        logger.info("查询讲义列表数据结束");
		return "modules/spadmin/handouts/spHandoutsList";
	}

	/**
	 * 课程列表表单数据
	 * 2016年8月16日
	 * By shangjunwei
	 * @param spLesson
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(SpLesson spLesson, Model model) {
		 logger.info("查询课程列表表单数据开始");
		model.addAttribute("spCourse", spLesson);
		 logger.info("课程列表表单数据结束");
		return "modules/spadmin/handouts/spLessonForm";
	}

	
	
	/**
	 * 讲义列表中数据的删除
	 * 2016年8月16日
	 * By shangjunwei
	 * @param spHandouts
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "delete")
	public String delete(SpHandouts spHandouts, RedirectAttributes redirectAttributes) {
		logger.info("讲义列表中数据的删除开始");
		try {
			handoutsService.delete(spHandouts);
			addMessage(redirectAttributes, "删除讲义成功");
			logger.info("讲义列表中数据的删除结束");
		} catch (Exception e) {
			logger.error( "讲义列表中数据的删除失败:" + e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/handouts/spHandouts/?repage";
	}


	/**
	 * 讲义列表中数据的保存
	 * 2016年8月16日
	 * By shangjunwei
	 * @param lesson
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "saveHandouts")
	public String saveHandouts(SpLesson lesson, HttpServletRequest request, HttpServletResponse response){
		logger.info("讲义列表中数据的保存开始");
		try {
			SpHandouts handouts = new SpHandouts();
			 handouts.setLessonId(lesson.getId());
			 handouts.setDelFlag("0");
			 handouts.setSpaceId(SpaceUtils.getSpaceId());
			 handouts.setTitle(lesson.getTitle());
			 handoutsService.save(handouts);
			 logger.info("讲义列表中数据的保存结束");
		} catch (Exception e) {
			logger.error( "讲义列表中数据的保存失败:" + e);
		}
		return "redirect:"+Global.getAdminPath()+"/spadmin/handouts/spHandouts/?repage";
	}
	
	
	/**
	 * 讲义列表中数据的添加
	 * 2016年8月16日
	 * By shangjunwei
	 * @param spLesson
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "handouts")
	public String showForm(SpLesson spLesson, Model model, RedirectAttributes redirectAttributes) {
		logger.info("讲义列表中数据的添加开始");
		OSSClientUtil oss = null;
		try {
			SpLesson lesson = lessonService.get(spLesson.getId());
			String title = handoutsService.selectTitleByLessonId(spLesson.getId());
			if(StringUtils.isNotEmpty(title)){
				lesson.setTitle(title);
			}
			model.addAttribute("spLesson", lesson);
			
			List<SpHandouts> handoutsList = handoutsService.selectHandoutsByLessonId(spLesson.getId());
			List<FileInput> fLst = new ArrayList<FileInput>();
		    oss = new OSSClientUtil();
			for(SpHandouts han:handoutsList){
				FileInput fi = new FileInput(han.getId(), han.getTitle(),han.getBaseUrl());
				fLst.add(fi);
			}
			model.addAttribute("fileList", fLst);
			logger.info("讲义列表中数据的添加结束");
		} catch (Exception e) {
			logger.error("讲义列表中数据的添加失败",e);
		}finally{
			if(oss != null){
				oss.destory();
			}
		}
		return "modules/spadmin/handouts/spHandoutsForm";
	}
	
	
	/**
	 * 讲义列表中图片上传
	 * 2016年8月16日
	 * By shangjunwei
	 * @param file
	 * @param lessonId,title
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(value = "upload")
	@ResponseBody
	public String upload(MultipartFile file,String lessonId,String title) throws Exception{
		logger.info("讲义列表中图片上传开始");
		OSSClientUtil oss = null;
		// 插入讲义
		SpHandouts handouts = new SpHandouts();
		try{
			oss = new OSSClientUtil();
			Map<String,String> map =oss.uploadFile2Oss(file);
			if("0".equals(map.get("SUCCESS"))){
				return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,map.get("ERRMSG")));
			}
		
			if(StringUtils.isNotEmpty(lessonId)){
				handouts.setLessonId(lessonId);
			}
			handouts.setDelFlag("0");
			handouts.setSpaceId(SpaceUtils.getSpaceId());
			if(StringUtils.isNotEmpty(title)){
				handouts.setTitle(title);
			}
			handouts.setBaseUrl(oss.getImgOrFileUrl(map.get("NAMEKEY")));
			
			List<SpHandouts> shList = handoutsService.selectHandoutsByLessonId(lessonId);
			
			//handoutsService.save(handouts);
			if(shList.size() > 0){
				SpHandouts sh = shList.get(0);
				sh.setDelFlag("1");
				oss.deleteFileByUrl(sh.getBaseUrl());
				String jsonBody = sh.getJsonBody();
				if (StringUtils.isNotEmpty(jsonBody)){
					
					
				}
				handoutsService.delete(sh);
			}
			handoutsService.save(handouts);
			logger.info("讲义列表中图片上传结束");
			return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,handouts.getLessonId()));
			
			
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
	 * 删除课程关联的讲义
	 * 2016年8月16日
	 * By shangjunwei
	 * @param lessonId
	 * @return
	 */
	@RequestMapping(value = "deleteHandouts")
	@ResponseBody
	public String deleteHandouts(String lessonId){
		logger.info("删除课程关联的讲义开始");
		OSSClientUtil oss = null;
		try{
			List<SpHandouts> shList = handoutsService.selectHandoutsByLessonId(lessonId);
			oss = new OSSClientUtil();
			if(shList.size() > 0){
				SpHandouts sh = shList.get(0);
				sh.setDelFlag("1");
				oss.deleteFileByUrl(sh.getBaseUrl());
				String jsonBody = sh.getJsonBody();
				if (StringUtils.isNotEmpty(jsonBody)){
					
				}
				handoutsService.delete(sh);
			}
			 logger.info("删除课程关联的讲义结束");
		}catch(Exception e){
			logger.error("删除讲义失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"删除讲义失败"));
		}
		return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE));
	}
	
}