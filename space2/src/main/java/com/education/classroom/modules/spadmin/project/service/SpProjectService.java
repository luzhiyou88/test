package com.education.classroom.modules.spadmin.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.project.dao.SpProjectDao;
import com.education.classroom.core.modules.spadmin.project.entity.SpProject;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 路演相关项目的服务类
 * @Class Name SpProjectService
 * @author zhangyongsheng
 * @Create In 2016年8月29日
 */
@Component
public class SpProjectService extends CrudService<SpProjectDao, SpProject> {
	
	@Autowired
	private SpProjectDao projectDao;

	/**
	 * 分页查询路演相关的项目
	 * 2016年8月29日
	 * By zhangyongsheng
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<SpProject> findList(Page<SpProject> page, Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<SpProject> list = projectDao.findList(queryMap);
        PageInfo<SpProject> lstPage = new PageInfo<SpProject>(list);
        PageUtil.convertPage(lstPage, page);
        return page;
	}
	
}
