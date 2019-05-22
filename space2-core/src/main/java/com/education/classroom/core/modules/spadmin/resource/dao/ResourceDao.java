package com.education.classroom.core.modules.spadmin.resource.dao;


import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 资料库
 * @author 杨立明
 * @version 2016-08-10
 */
@MyBatisDao
public interface ResourceDao extends CrudDao<SpResource> {

	List<Map<String, String>> getCategorySelect(Map<String, Object> paraMap);
	SpResource getResourceById(Map<String,Object> paraMap);
	List<Map<String,Object>> getScoreList(Map<String,Object> paraMap);
	List<SpResource> getMyResourceList(SpResource resource);
	
	int hasDownload(Map<String, Object> paraMap);
	void saveDownload(Map<String, Object> paraMap);
	int hasDownloadNum(Map<String, Object> paraMap);
	void insertDownloadNum(Map<String, Object> paraMap);
	void updateDownloadNum(Map<String, Object> paraMap);
	
	int hasReadNum(Map<String, Object> paraMap);
	void insertReadNum(Map<String, Object> paraMap);
	void updateReadNum(Map<String, Object> paraMap);
	
	int hasClickNum(Map<String, Object> paraMap);
	void insertClickNum(Map<String, Object> paraMap);
	void updateClickNum(Map<String, Object> paraMap);
	
	List<SpResource> getResourceListInIds(SpResource resource);
	
}
