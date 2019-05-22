/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.exam.entity.SpExamination;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 试卷管理DAO接口
 * @author 尚军伟
 * @version 2016/08/17
 */
@MyBatisDao
public interface SpExaminationDao extends CrudDao<SpExamination> {
	
	//查询试卷列表
	List<SpExamination> selSpExamination();
	
	// 查询课程对应的试卷
	SpExamination findByLessonId(String lessonId);
	
	int deleteByLessonId(String lessonId);
	
	//验证试卷名称唯一性
	public int checkExaminationName(@Param(value = "examinationName") String examinationName,@Param(value = "id") String id);
	
	//查看试卷是否关联试题
	public int checkProblem(String examId);
}