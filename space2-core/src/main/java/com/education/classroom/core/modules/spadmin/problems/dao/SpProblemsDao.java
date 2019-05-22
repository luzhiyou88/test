/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.problems.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.problems.entity.SpProblems;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 试题管理DAO接口
 * @author 尚军伟
 * @version 2016/08/18
 */
@MyBatisDao
public interface SpProblemsDao extends CrudDao<SpProblems> {
	
	/**
	 * 条件查询试题
	 * 2016年8月18日
	 * By zhangyongsheng
	 * @param params
	 * @return
	 */
	public List<SpProblems> findListByMap(Map<String,Object> params);
	
	//根据试卷ID查询试卷名称
	public String fingExaminationNameById(String examinationId);
	//删除试卷题目
	public int deleteByExaminationId(String examinationId);
	
	//根据试卷ID查询关联的试题总分数
	public String selectSumScore(@Param(value = "examinationId") String examinationId,@Param(value = "id") String id);
	
	//根据试卷ID查询关联的试题编号
	public List<String> selectProblemNumber(@Param(value = "examinationId") String examinationId,@Param(value = "id") String id);
	
	//根据试卷ID查询关联的试题总数目
	public int selectTotalNumber(@Param(value = "examinationId") String examinationId,@Param(value = "id") String id);
	
	// 验证试题题干唯一性 
	public int selectStem(Map<String,Object> map);
	
	//根据试卷ID查询关联的试题列表
	public List<SpProblems> selectProblemList(String examId);
	
	//根据材料ID查询关联的试题列表
	public List<SpProblems> selectProblemListByMaterials(String materialId);
	/**
	 * pad查询
	 * 2016年9月8日
	 * By zhujie
	 * @param params
	 * @return
	 */
	public List<SpProblems> findAllListByPad(Map<String,Object> params);
	 
}