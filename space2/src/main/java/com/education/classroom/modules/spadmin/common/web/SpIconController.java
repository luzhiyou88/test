/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.common.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.education.classroom.core.common.entity.ReturnMsg;
import com.education.classroom.core.users.constants.stateConstants;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.utils.OssUtils.OSSClientUtil;

/**
 * icon管理Controller
 * @author 朱杰
 * @version 2016/08/06
 */
@Controller
@RequestMapping(value = "${adminPath}/spadmin/common/icon")
public class SpIconController extends BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * icon上传
	 * 2016年9月5日
	 * By zhujie
	 * @param file
	 * @param courseId
	 * @return
	 */
 	@RequestMapping(value = "upload")
	@ResponseBody
	public String upload(MultipartFile file){
		OSSClientUtil oss = null;
		try{
			oss = new OSSClientUtil();
			Map<String,String> map =oss.uploadFile2Oss(file);
			if("0".equals(map.get("SUCCESS"))){
				return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,map.get("ERRMSG")));
			}
			return jsonMapper.toJson(new ReturnMsg(stateConstants.TRUE,null,oss.getImgOrFileUrl(map.get("NAMEKEY"))));
			
		} catch(Exception e){
			logger.error("文件上传失败",e);
			return jsonMapper.toJson(new ReturnMsg(stateConstants.FALSE,"文件上传失败"));
			
		} finally {
			if(oss!=null){
				oss.destory();
			}
		}
	}
}