/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.resource.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bokecc.bean.Video;
import com.bokecc.service.VideoService;
import com.education.classroom.core.modules.spadmin.resource.service.SpResourceService;
import com.education.classroom.core.web.BaseController;

import com.education.classroom.utils.StringUtils;


/**
 * 资料库管理Controller
 * @author 杨立明
 * @version 2016/08/05
 */
@Controller
@RequestMapping(value = "/ccResource")
public class CcResourceController extends BaseController {

 protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpResourceService spResourceService;

	
	@RequestMapping(value = "saveVideoImg")
	public void saveVideoImg(String videoid,HttpServletRequest request,PrintWriter printWriter){
		logger.info("[CcResourceController].[saveVideoImg]>>>>>>>>>>start>>>>>>>>>>");
		try {
			System.out.println("[saveVideoImg]%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("[saveVideoImg]%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+videoid);
			System.out.println("[saveVideoImg]%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			if(StringUtils.isNoneBlank(videoid)){
				String status=request.getParameter("status");
				String duration=request.getParameter("duration");
				String image=request.getParameter("image");
				System.out.println("[saveVideoImg]request.status="+status);
				System.out.println("[saveVideoImg]request.duration="+duration);
				System.out.println("[saveVideoImg]request.image="+image);
				if(StringUtils.isNoneBlank(image)){
					Video video = VideoService.queryVideoById(videoid);
					image=video.getImage();
					System.out.println("[saveVideoImg]Spark_videoid="+video.getSpark_videoid());
					System.out.println("[saveVideoImg]image="+image);
				}
				//修改数据库字段值
				Map<String,String> paraMap=new HashMap<String,String>();
				paraMap.put("videoId", videoid);
				paraMap.put("videoImg", image);
				spResourceService.saveVideoImg(paraMap);
				System.out.println("[saveVideoImg]<result>OK</result>");
				printWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <result>OK</result>");
			}else{
				System.out.println("[saveVideoImg]<result>videoid Empty</result>");
				printWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <result>videoid Empty</result>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[CcResourceController].[saveVideoImg]~~~~~~~~~~error~~~~~~~~~~"+e.getMessage());
		}
		logger.info("[CcResourceController].[saveVideoImg]<<<<<<<<<<end<<<<<<<<<<");
	}
}