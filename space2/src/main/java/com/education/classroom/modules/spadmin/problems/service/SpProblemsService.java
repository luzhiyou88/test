/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.problems.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.problems.dao.SpProblemsDao;
import com.education.classroom.core.modules.spadmin.problems.entity.SpProblems;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.StringUtils;

/**
 * 试题管理Service
 * @author 尚军伟
 * @version 2016/08/18
 */
@Service
@Transactional(readOnly = true)
public class SpProblemsService extends CrudService<SpProblemsDao, SpProblems> {

	@Autowired
	private SpProblemsDao problemsDao;
	
	public SpProblems get(String id) {
		return super.get(id);
	}
	
	public List<SpProblems> findList(SpProblems spProblems) {
		return super.findList(spProblems);
	}
	
	public Page<SpProblems> findPage(Page<SpProblems> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpProblems> schems = problemsDao.findList(filters);
		PageInfo<SpProblems> resultPage = new PageInfo<SpProblems>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpProblems spProblems) {
		super.save(spProblems);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpProblems spProblems) {
		super.delete(spProblems);
	}
	
	
	//根据试卷ID查询试卷名称
	public String fingExaminationNameById(String examinationId){
		return problemsDao.fingExaminationNameById(examinationId);
	}
	
	/**
	 * 查询试卷的试题
	 * 2016年8月19日
	 * By zhujie
	 * @param examinationId
	 * @return
	 */
	public List<SpProblems> findByExaminationId(String examinationId){
		if(StringUtils.isEmpty(examinationId)){
			return new ArrayList<SpProblems>();
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("examinationId", examinationId);
		return problemsDao.findAllList(params);
	}
	
	
	//根据试卷ID查询关联的试题总分数
		public String selectSumScore(String examinationId,String id){
			return problemsDao.selectSumScore(examinationId,id);
		}
		
	//根据试卷ID查询关联的试题编号
	public List<String> selectProblemNumber(String examinationId,String id){
		return problemsDao.selectProblemNumber(examinationId,id);
	}
		
	//根据试卷ID查询关联的试题总数目
	public int selectTotalNumber(String examinationId,String id){
		return problemsDao.selectTotalNumber(examinationId,id);
	}
	
	// 验证试题题干唯一性 
	public int selectStem(Map<String,Object> map){
			return problemsDao.selectStem(map);
		}
	
	//根据试卷ID查询关联的试题列表
	public List<SpProblems> selectProblemList(String examId){
		return problemsDao.selectProblemList(examId);
	}
	
	//根据材料ID查询关联的试题列表
	public List<SpProblems> selectProblemListByMaterials(String materialId){
		return problemsDao.selectProblemListByMaterials(materialId);
	}
	
}