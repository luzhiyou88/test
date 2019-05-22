package com.education.classroom.modules.spadmin.handouts.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.modules.spadmin.handouts.dao.SpHandoutsDao;
import com.education.classroom.core.modules.spadmin.handouts.entity.SpHandouts;
import com.education.classroom.core.service.CrudService;

/**
 * 讲义Service
 * @Class Name HandoutsService
 * @author zhangyongsheng
 * @Create In 2016年8月10日
 */
@Service
@Transactional(readOnly = true)
public class SpHandoutsService extends CrudService<SpHandoutsDao, SpHandouts> {

	@Autowired
	private SpHandoutsDao handoutsDao;
	
	public SpHandouts get(String id) {
		return super.get(id);
	}
	
	public SpHandouts getByLessonId(String lessonId) {
		return dao.getByLessonId(lessonId);
	}
	
	public List<SpHandouts> findListByMap(Map<String,Object> params) {
		return handoutsDao.findListByMap(params);
	}
	
	@Transactional(readOnly = false)
	public void save(SpHandouts spHandouts) {
		super.save(spHandouts);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpHandouts spHandouts) {
		super.delete(spHandouts);
	}
	
	//通过课程ID查询讲义标题
	public String selectTitleByLessonId(String lessonId){
		return handoutsDao.selectTitleByLessonId(lessonId);
	}
	
	//删除课程关联的讲义
	@Transactional(readOnly = false)
	public void deleteHandoutsByLessonId(String lessonId){
		handoutsDao.deleteHandoutsByLessonId(lessonId);
	}
	
	//通过课程ID查询讲义
	public List<SpHandouts> selectHandoutsByLessonId(String lessonId){
		return handoutsDao.selectHandoutsByLessonId(lessonId);
	}
}