/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.resource.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
import com.education.classroom.core.exception.ServiceException;
import com.education.classroom.core.member.type.PublishState;
import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.modules.spadmin.resource.service.SpResourceService;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;

import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;
import com.education.classroom.utils.classroom.SpaceUtils;


/**
 * 资料库管理Controller
 * @author 杨立明
 * @version 2016/08/05
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/resource/spResource")
public class SpResourceController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpResourceService spResourceService;
	
	
	@ModelAttribute
	public SpResource get(@RequestParam(required=false) String id) {
		SpResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = spResourceService.get(id);
		}
		if (entity == null){
			entity = new SpResource();
		}
		return entity;
	}
	
	/**
	 * 资料库管理查询
	 * @param spResource 资料库管理实体类
	 * @param request
	 * @param response
	 * @param model
	 * @return String 返回资料库管理查询页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SpResource spResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("【SpResourceController】.【list】>>>>>>>>>>start>>>>>>>>>>");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filter = request.getParameterMap();
			Map<String,Object>  queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filter, queryMap);
			if (!user.isAdmin()) {
				queryMap.put("createBy", user.getId());
			}
			queryMap.put("categoryId", queryMap.get("spCategory.id"));
			queryMap.put("delFlag", "0");
			System.out.println("查询参数："+queryMap);
			Page<SpResource> page = spResourceService.findPage(new Page<SpResource>(request, response), queryMap); 
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpResourceController】.【list】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpResourceController】.【list】<<<<<<<<<<end<<<<<<<<<<");
		return "modules/spadmin/resource/spResourceList";
	}

	/**
	 * 查询单个资料库管理信息以备编辑
	 * @param spResource 资料库管理实体类
	 * @param model
	 * @return String 返回资料库管理编辑页面
	 */
	@RequestMapping(value = "form")
	public String form(SpResource spResource, Model model) {
		logger.info("【SpResourceController】.【form】>>>>>>>>>>start>>>>>>>>>>");
		try {
			model.addAttribute("spResource", spResource);
			String ccNotifyUrl = getPropertyByName("jdbc","cc_notify_url");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~CC视频回调url="+ccNotifyUrl);
			model.addAttribute("ccNotifyUrl", ccNotifyUrl);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpResourceController】.【form】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpResourceController】.【form】<<<<<<<<<<end<<<<<<<<<<");
		return "modules/spadmin/resource/spResourceForm";
	}
	public static String getPropertyByName(String path, String name) {
		String result = "";
		try {
			result = ResourceBundle.getBundle(path).getString(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return result;
	}

	/**
	 * 资料库管理保存
	 * @param spResource 资料库管理实体类
	 * @param model
	 * @param redirectAttributes
	 * @return String 重定向到资料库管理查询页面
	 */
	@RequestMapping(value = "save")
	public String save(@RequestParam("file") CommonsMultipartFile file,@RequestParam("fileImg") CommonsMultipartFile fileImg, SpResource spResource, Model model, RedirectAttributes redirectAttributes) {
		logger.info("【SpResourceController】.【save】>>>>>>>>>>start>>>>>>>>>>");
		try {
			if (!beanValidator(model, spResource)){
				return form(spResource, model);
			}
			spResource.setPublishState("0");//发布状态默认为空
			spResource.setSpaceId(SpaceUtils.get("SpaceId"));//添加默认的所属空间
			//上传start
			String resourceType=spResource.getType();
			if(!"1".equals(resourceType)){//说明不是视频
				spResource.setDomain("0");//0表示存储在本地ftp
				
				/*
				String fileName = file.getOriginalFilename();// 真实文件名称
				if(fileName!=null && !"".equals(fileName.trim()) && file.getSize()!=0){
					String extName = fileName.substring(fileName.indexOf(".") + 1);//文件后缀
					System.out.println("==============fileName="+fileName+" , extName="+extName);
					UploadUtil.UploadResult uploadResult = UploadUtil.uploadImage(file.getInputStream(), extName, true);
					//System.out.println("))))))))))))))))))))))))))))))))uploadResult.isSuccess="+uploadResult.isSuccess+" , uploadResult.srcName="+uploadResult.srcName+" , uploadResult.thumbName="+uploadResult.thumbName+" , uploadResult.msg="+uploadResult.msg);
					if (uploadResult.isSuccess) {
						String realPath;
						if ("jpg;gif;png;bmp;jpeg".indexOf(extName.toLowerCase()) != -1) {
							realPath="/image/"+uploadResult.srcName;
						}else{
							realPath="/docs/"+uploadResult.srcName;
						}
						spResource.setBaseUrl(realPath);//重置路径值
					} else {
						logger.error(uploadResult.msg);
					}
				}
				*/
				String resultState;
				Map<String,String> resultMap;
				OSSClientUtil util=new OSSClientUtil();
				System.out.println("---------------------fileName="+file.getOriginalFilename());
				if(file!=null && !"".equals(file.getOriginalFilename())){
					resultMap=util.uploadFile2Oss(file);//filename其实是拼接的一个key，保存到baseUrl
					System.out.println("=====================resultMap="+resultMap);
					resultState=resultMap.get("SUCCESS");
					if("1".equals(resultState)){
						String filename=resultMap.get("NAMEKEY");
						System.out.println("文件KEY（保存在数据库）："+filename);
						String realPath=util.getImgOrFileUrl(filename);
						System.out.println("文件URL（访问全路径）："+realPath);
						spResource.setBaseUrl(realPath);//重置路径值
					}else if("0".equals(resultState)){
						String errMsg=resultMap.get("ERRMSG");
						System.out.println("上传资料失败："+errMsg);
						logger.error("上传资料失败："+errMsg);
					}else{
						logger.error("调用OSS接口上传资料失败");
					}
				}
				System.out.println("---------------------fileName="+file.getOriginalFilename());
				if(fileImg!=null && !"".equals(fileImg.getOriginalFilename())){
					resultMap=util.uploadImg2Oss(fileImg);//filename其实是拼接的一个key，保存到baseUrl
					System.out.println("=====================resultMap="+resultMap);
					resultState=resultMap.get("SUCCESS");
					if("1".equals(resultState)){
						String filename=resultMap.get("NAMEKEY");
						System.out.println("文件KEY（保存在数据库）："+filename);
						String realPath=util.getImgOrFileUrl(filename);
						System.out.println("文件URL（访问全路径）："+realPath);
						spResource.setThumbImg(realPath);//重置路径值
					}else if("0".equals(resultState)){
						String errMsg=resultMap.get("ERRMSG");
						System.out.println("上传资料图片失败："+errMsg);
						logger.error("上传资料图片失败："+errMsg);
					}else{
						logger.error("调用OSS接口上传资料图片失败");
					}
				}
				util.destory();
				util=null;
				
			}else{//视频
				spResource.setDomain("1");//1表示存储在cc
			}
			//上传end
			if("1".equals(resourceType)){//说明是视频
				spResource.setDelFlag("1");//先为删除状态，只有cc回调视频缩略图时，再置为0(正常)
			}
			spResourceService.save(spResource);
			addMessage(redirectAttributes, "保存资料库成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "保存资料库发生异常");
			logger.error("【SpResourceController】.【save】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpResourceController】.【save】<<<<<<<<<<end<<<<<<<<<<");
		return "redirect:"+Global.getAdminPath()+"/spadmin/resource/spResource/?repage";
	}
	
	/**
	 * 资料库管理删除
	 * @param spResource 资料库管理实体类
	 * @param redirectAttributes
	 * @return String 重定向到资料库管理查询页面
	 */
	@RequestMapping(value = "delete")
	public String delete(SpResource spResource, RedirectAttributes redirectAttributes) {
		logger.info("【SpResourceController】.【delete】>>>>>>>>>>start>>>>>>>>>>");
		try {
			if(spResource!=null && StringUtils.isNotBlank(spResource.getId())){
				String publishState=spResource.getPublishState();
				String sourceType=spResource.getSourceType();
				System.out.println("publishState="+publishState+" , sourceType="+sourceType);
				if("2".equals(sourceType) && ("1".equals(publishState) || "3".equals(publishState)) ){//平台资料，并且是待审核或审核通过，不可删除
					//判断平台是否删除
					
						//可删，但不删除真实文件
						spResourceService.delete(spResource);
						addMessage(redirectAttributes, "删除资料库成功");
					
				}else{
					//删除真实文件-start
					System.out.println(">>>>>>>>>>开始删除真实文件");
					//spResource = this.get(spResource.getId());//根据id获取真实资料
					String fileUrl=spResource.getBaseUrl();
					String imgUrl=spResource.getThumbImg();
					String fileKey=SpResource.getKeyFromUrl(fileUrl);
					String imgKey=SpResource.getKeyFromUrl(imgUrl);
					System.out.println("资料="+fileKey);
					System.out.println("资料图片="+imgKey);
					OSSClientUtil util=null;
					try{
						util=new OSSClientUtil();
						if(StringUtils.isNotBlank(fileKey)) util.deleteFile(fileKey);
						if(StringUtils.isNotBlank(imgKey)) util.deleteFile(imgKey);
					}catch(Exception e){
						System.out.println("删除真实资料报错了！！！！！！！！！！！！！！！！！！！！！");
						logger.error("删除真实资料报错了！！！！！！！！！！！！！！！！！！！！！");
					}finally{
						if(util!=null){
							util.destory();
						}
					}
					//删除真实文件-end
					
					spResourceService.delete(spResource);
					addMessage(redirectAttributes, "删除资料库成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "删除资料库发生异常");
			logger.error("【SpResourceController】.【delete】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpResourceController】.【delete】<<<<<<<<<<end<<<<<<<<<<");
		return "redirect:"+Global.getAdminPath()+"/spadmin/resource/spResource/?repage";
	}
	

	/**
	 * 发布到平台
	 * @param spResource
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "release2Plat")
	public String release2Plat(SpResource spResource, RedirectAttributes redirectAttributes) {
		logger.info("【SpResourceController】.【release2Plat】>>>>>>>>>>start>>>>>>>>>>");
		try {
			
			//更改状态为：待审核
			spResource.setPublishState("1");
			spResourceService.updatePublishState(spResource);
			addMessage(redirectAttributes, "发布成功，等待审核");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "资料库发布到平台发生异常");
			logger.error("【SpResourceController】.【release2Plat】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpResourceController】.【release2Plat】<<<<<<<<<<end<<<<<<<<<<");
		return "redirect:"+Global.getAdminPath()+"/spadmin/resource/spResource/?repage";
	}
	
	/**
	 * 刷新审核状态
	 * @param spResource
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "flushPublishState")
	public String flushPublishState(SpResource spResource, RedirectAttributes redirectAttributes) {
		logger.info("【SpResourceController】.【flushPublishState】>>>>>>>>>>start>>>>>>>>>>");
		try {
		
				spResourceService.updatePublishState(spResource);
			
			addMessage(redirectAttributes, "刷新审核状态成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "获取资料库审核状态发生异常");
			logger.error("【SpResourceController】.【flushPublishState】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpResourceController】.【flushPublishState】<<<<<<<<<<end<<<<<<<<<<");
		return "redirect:"+Global.getAdminPath()+"/spadmin/resource/spResource/?repage";
	}
	/**
	 * 获取平台资料库列表
	 * @param spResource
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("getPublishResourceList")
	public String getPublishResourceList(SpResource spResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("【SpResourceController】.【getPublishResourceList】>>>>>>>>>>start>>>>>>>>>>");
		try {
			User user = UserUtils.getUser();
			Map<?,?> filter = request.getParameterMap();
			Map<String,Object>  queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filter, queryMap);
			if (!user.isAdmin()) {
				queryMap.put("createBy", user.getId());
			}
			queryMap.put("categoryId", queryMap.get("spCategory.id"));
			queryMap.put("delFlag", "0");// 未删除
			queryMap.put("publishState", "3");//必须的条件：3表示审核通过
			//queryMap.put("spaceIdNot", SpaceUtils.get("SpaceId"));//这个可以从平台查询本学校同步上去的资料，因为本学校可能删除了，然后后悔了，这样还可以从平台拿下来。
			System.out.println("查询平台可本地化数据 参数："+queryMap);
			//List<SpResource> resourceList = dataTransferService.getPublishResourceList(spResource);
			Page<SpResource> page = new Page<SpResource>(request, response);
			System.out.println("======平台可本地化数据 page.count="+page.getCount());
			model.addAttribute("page", page);
			spResource.setSourceType("2");//资料属于平台
			spResource.setPublishState("3");//审核通过
			spResource.setDelFlag("0");
			List<SpResource> resourceList = spResourceService.findList(spResource);//这是本地已经同步过的
			System.out.println("======本地已订阅数据 resourceList.size="+resourceList.size());
			model.addAttribute("resourceList", resourceList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【SpResourceController】.【getPublishResourceList】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpResourceController】.【getPublishResourceList】<<<<<<<<<<end<<<<<<<<<<");
		return "modules/spadmin/resource/spResourceListPublish";
	}
	/**
	 * 获取平台资料，并保存到本地
	 * @param spResource
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("saveResourceFromPlat")
	public String saveResourceFromPlat(SpResource spResource, RedirectAttributes redirectAttributes) {
		logger.info("【SpResourceController】.【saveResourceFromPlat】>>>>>>>>>>start>>>>>>>>>>");
		try {
			System.out.println("======平台 id="+spResource.getId());
			String LocalCategory=spResource.getCategoryId();
			System.out.println("======选择的本地分类 id="+LocalCategory);
			//spResource=dataTransferService.getPublishResourceById(spResource);
			System.out.println("======根据平台id查询平台资料 spResource="+spResource);
			//重新设置资料的本地分类-start
			spResource.setCategoryId(LocalCategory);
			//重新设置资料的本地分类-end
			SpResource hasResource=spResourceService.get(spResource);
			System.out.println("---------根据平台id获取本地资料，hasResource="+hasResource);
			System.out.println("=========平台数据："+spResource);
			if(hasResource!=null){//说明删除了，只需要修改状态
				spResource.setPublishState(PublishState.AUDIT_PASSED);//审核通过
				spResource.setIsNewRecord(false);//修改
				spResource.setDelFlag("0");//0正常 1删除
				spResourceService.save(spResource);
			}else{
				//spResource.setPublishState(PublishState.AUDIT_PASSED);//审核通过
				//spResource.setIsNewRecord(true);//新增
				spResourceService.insert(spResource);
			}
			addMessage(redirectAttributes, "本地化资料库成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "本地化资料库发生异常");
			logger.error("【SpResourceController】.【saveResourceFromPlat】~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("【SpResourceController】.【saveResourceFromPlat】<<<<<<<<<<end<<<<<<<<<<");
		return "redirect:"+Global.getAdminPath()+"/spadmin/resource/spResource/getPublishResourceList?repage";
	}
}