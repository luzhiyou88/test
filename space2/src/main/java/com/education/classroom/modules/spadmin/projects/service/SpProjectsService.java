/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.projects.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.mapper.JsonMapper;
import com.education.classroom.core.modules.spadmin.project.dao.SpProjectDao;
import com.education.classroom.core.modules.spadmin.project.entity.SpProject;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectResource;
import com.education.classroom.core.modules.spadmin.project.entity.SpProjectUser;
import com.education.classroom.core.modules.spadmin.resource.service.SpUtilService;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;

/**
 * 项目管理Service
 * @author 杨立明
 * @version 2016/08/26
 */
@Service
@Transactional(readOnly = true)
public class SpProjectsService extends CrudService<SpProjectDao, SpProject> {
	@Autowired
	private SpProjectDao spProjectDao;
	@Autowired
	private SpUtilService spUtilService;
	
	@Autowired
	private SpProjectResourceService spProjectResourceService;
	@Autowired
	private SpProjectUserService spProjectUserService;
	

	public SpProject get(String id) {
		return super.get(id);
	}
	
	public List<SpProject> findList(SpProject spProject) {
		return super.findList(spProject);
	}
	
	public Page<SpProject> findPage(Page<SpProject> page, Map<String, Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpProject> dataList = spProjectDao.findList(filters);
		PageInfo<SpProject> dataPage = new PageInfo<SpProject>(dataList);
		PageUtil.convertPage(dataPage, page);
		return page;
	}
	
	/**
	 * 查询路演下所有的项目
	 * 2016年8月31日
	 * By zhujie
	 * @param lessonId
	 * @return
	 */
	public List<SpProject> findAllListByLessonId(String lessonId){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lessonId", lessonId);
		return dao.findAllList(param);
	}
	
	@Transactional(readOnly = false)
	public void save(SpProject spProject) {
		super.save(spProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpProject spProject) {
		//删除所有资料-start
		String projectId=spProject.getId();
		if(StringUtils.isNoneBlank(projectId)){
			List<SpProjectResource> resourseList= spProjectResourceService.getByProjectId(projectId);
			for (SpProjectResource oneResource : resourseList) {
				String resourceId=oneResource.getId();
				if(StringUtils.isNotBlank(resourceId)){
					//spProjectResourceService.deleteById(resourceId);//只删除项目资料的数据
					this.delResource(resourceId);//删除数据和真实文件
				}
			}
		}
		//删除所有资料-end
		//删除所有成员-start
		if(StringUtils.isNoneBlank(projectId)){
			List<SpProjectUser> userList= spProjectUserService.getMembersByProjectId(projectId);
			for (SpProjectUser one : userList) {
				String memberId=one.getId();
				if(StringUtils.isNotBlank(memberId)){
					spProjectUserService.delete(one);//只删除项目成员
				}
			}
		}
		//删除所有成员-end
		super.delete(spProject);
	}
	public void delResource(String resourceId){
		spProjectResourceService.deleteById(resourceId);
		SpProjectResource resource=spProjectResourceService.get(resourceId);
		OSSClientUtil oss = new OSSClientUtil();
		JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
		//删除切片-start
		String jsonBody = resource.getJsonBody();
		if (StringUtils.isNotEmpty(jsonBody)){
			/*
			 * DocResult doc =jsonMapper.fromJson(jsonBody, DocResult.class);
			 * deleteDoc(doc,oss);
			 */
		}
		//删除切片-end
		if(StringUtils.isNotBlank(resource.getBaseUrl())){
			oss.deleteFileByUrl(resource.getBaseUrl());
		}
		if(StringUtils.isNotBlank(resource.getThumbImg())){
			oss.deleteFileByUrl(resource.getThumbImg());
		}
	}
	//删除阿里云上面的切片文件
	/*
	 * public void deleteDoc(DocResult doc,OSSClientUtil oss){
	 * System.out.println("******删除切片******"); //删除swf文件 List<SwfFile> swfFiles =
	 * doc.getSwfFiles(); for (SwfFile swf:swfFiles) { String ossUrl
	 * =swf.getOssUrl(); if (StringUtils.isNotEmpty(ossUrl)) {
	 * oss.deleteFileByUrl(ossUrl); } }
	 * 
	 * //删除txt文件 List<TextFile> textFiles = doc.getTextFiles(); for (TextFile
	 * txt:textFiles) { String ossUrl =txt.getOssUrl(); if
	 * (StringUtils.isNotEmpty(ossUrl)) { oss.deleteFileByUrl(ossUrl); } }
	 * 
	 * //删除缩略图文件 List<ThumbnailsFile> thumbnailsFiles = doc.getThumbnailsFiles();
	 * for (ThumbnailsFile thu:thumbnailsFiles) { String ossUrl =thu.getOssUrl(); if
	 * (StringUtils.isNotEmpty(ossUrl)) { oss.deleteFileByUrl(ossUrl); } } }
	 */
	
	//获取路演下拉列表
	public List<Map<String,String>> getLessonSelect() {
		String sql="SELECT id,name FROM sp_lesson where del_flag=0 and lesson_type=1";
		List<Map<String,String>> dataList=spUtilService.getMap2List(sql);
		return dataList;
	}
	//获取主讲人下拉列表
	public List<Map<String,String>> getSpeakerSelect() {
		String sql="SELECT id,name FROM sp_user where del_flag=0 and user_type=3";
		List<Map<String,String>> dataList=spUtilService.getMap2List(sql);
		return dataList;
	}
	
	//获取项目下拉列表
	public List<Map<String,String>> getProjectSelect() {
		String sql="SELECT id,name FROM sp_project where del_flag=0";
		List<Map<String,String>> dataList=spUtilService.getMap2List(sql);
		return dataList;
	}

	public int hasProject(SpProject spProject) {
		return spProjectDao.hasProject(spProject);
	}
	
}