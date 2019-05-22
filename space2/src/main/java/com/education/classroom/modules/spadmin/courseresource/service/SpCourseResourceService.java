package com.education.classroom.modules.spadmin.courseresource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.exception.ServiceException;
import com.education.classroom.core.mapper.JsonMapper;
import com.education.classroom.core.modules.spadmin.courseResource.dao.SpCourseResourceDao;
import com.education.classroom.core.modules.spadmin.courseResource.entity.SpCourseResource;
import com.education.classroom.core.service.CrudService;

import com.education.classroom.modules.spadmin.common.util.SpCommonUtils;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;

/**
 * 套课资料Service
 * @Class Name SpCourseResourceService
 * @author zhujie
 * @Create In 2016年8月10日
 */
@Service
@Transactional(readOnly = true)
public class SpCourseResourceService extends CrudService<SpCourseResourceDao, SpCourseResource> {
	
	public SpCourseResource get(String id) {
		return super.get(id);
	}
	
	public List<SpCourseResource> getByCourseId(String courseId) {
		return dao.getByCourseId(courseId);
	}
	
	/**
	 * 套课资源删除
	 * 2016年8月15日
	 * By zhujie
	 * @param id
	 */
	@Transactional(value="transactionManager",readOnly = false)
	public void deleteResource(String id){
		OSSClientUtil oss = null;
		try{
			SpCourseResource r = this.get(id);
			dao.delete(r);
			
			oss = new OSSClientUtil();
			oss.deleteFileByUrl(r.getBaseUrl());
			// 删除切割文件
			String jsonBody = r.getJsonBody();
			if (StringUtils.isNotEmpty(jsonBody)){
				
			}
			
		}catch(Exception e){
			
			logger.error("套课资源删除失败",e);
			throw new ServiceException("套课资源删除失败");
			
		}finally{
			if(oss != null){
				oss.destory();
			}
		}
		
		
		
	}
}