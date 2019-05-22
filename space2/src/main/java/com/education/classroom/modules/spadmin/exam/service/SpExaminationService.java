/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.exam.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.exam.dao.SpExaminationDao;
import com.education.classroom.core.modules.spadmin.exam.entity.SpExamination;
import com.education.classroom.core.modules.spadmin.lesson.dao.SpLessonDao;
import com.education.classroom.core.modules.spadmin.lesson.entity.SpLesson;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.SpringContextHolder;

/**
 * 试卷管理Service
 * @author 尚军伟
 * @version 2016/08/17
 */
@Service
@Transactional(readOnly = true)
public class SpExaminationService extends CrudService<SpExaminationDao, SpExamination> {

	private static SpExaminationDao examinationDao = SpringContextHolder
            .getBean(SpExaminationDao.class);
	
	private static SpLessonDao lessonDao = SpringContextHolder
            .getBean(SpLessonDao.class);
	
	public SpExamination get(String id) {
		return super.get(id);
	}
	
	public List<SpExamination> findList(SpExamination spExamination) {
		return super.findList(spExamination);
	}
	
	public Page<SpExamination> findPage(Page<SpExamination> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpExamination> schems = examinationDao.findList(filters);
		PageInfo<SpExamination> resultPage = new PageInfo<SpExamination>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpExamination spExamination) {
		super.save(spExamination);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpExamination spExamination) {
		super.delete(spExamination);
	}
	
	//查询试卷列表
	public static Map<String, String> getSpExaminationMap(boolean emptyRowFlag) {
        // 检索材料
        List<SpExamination> ls = selSpExamination();
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        if (emptyRowFlag) {
            map.put("", "请选择");
        }
        for (int i = 0; i < ls.size(); i++) {
            map.put(ls.get(i).getId(), ls.get(i).getExaminationName());
        }
        return map;
    }

	 public static List<SpExamination> selSpExamination() {
        // 检索材料
        List<SpExamination> ls = examinationDao.selSpExamination();
        return ls;
     }
	 
	 public SpExamination findByLessonId(String lessonId){
		 return dao.findByLessonId(lessonId);
	 }
	 
	 
	 //查询课程列表
	 public static Map<String, String> getSpLessonMap(boolean emptyRowFlag) {
	        // 检索课程
	        List<SpLesson> ls = selSpLesson();
	        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	        if (emptyRowFlag) {
	            map.put("", "请选择");
	        }
	        for (int i = 0; i < ls.size(); i++) {
	            map.put(ls.get(i).getId(), ls.get(i).getName());
	        }
	        return map;
	    }

		 public static List<SpLesson> selSpLesson() {
	        // 检索课程
	        List<SpLesson> ls = lessonDao.selectLessonList();
	        return ls;
	     }
		 
		//验证试卷名称唯一性
		public int checkExaminationName(String examinationName,String id){
			return examinationDao.checkExaminationName(examinationName,id);
		}
		
		//查看试卷是否关联试题
		public int checkProblem(String examId){
			return examinationDao.checkProblem(examId);
		}
	
}