/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.resource.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.modules.spadmin.resource.dao.SpUtilDao;
import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.service.CrudService;

/**
 * 资料库管理Service
 * @author 杨立明
 * @version 2016-08-05
 */
@Service
@Transactional(readOnly = true)
public class SpUtilService extends CrudService<SpUtilDao, SpResource> {

	@Autowired
	private SpUtilDao spUtilDao;
	
	public List<Map<String,String>> getMap2List(Map<String,Object> paraMap) {
		return spUtilDao.getMap2List(paraMap);
	}
	public List<Map<String,String>> getMap2List(String querySql) {
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("querySql", querySql);
		return spUtilDao.getMap2List(paraMap);
	}
	//获取专业下拉列表
	public List<Map<String,String>> getSpecialtySelect() {
		String sql="SELECT id,name FROM sp_specialty where del_flag=0";
		List<Map<String,String>> specialtyList=this.getMap2List(sql);
		return specialtyList;
	}
	//获取班级下拉列表
	public List<Map<String,String>> getClassSelect() {
		String sql="SELECT id,name FROM sp_class where del_flag=0";
		List<Map<String,String>> classList=this.getMap2List(sql);
		return classList;
	}
	//获取space下拉列表
	public List<Map<String,String>> getSpaceSelect() {
		String sql="SELECT id,name FROM sp_space where del_flag=0";
		List<Map<String,String>> classList=this.getMap2List(sql);
		return classList;
	}
}