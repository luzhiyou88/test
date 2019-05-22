/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.space.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.space.dao.SpSpaceDao;
import com.education.classroom.core.modules.spadmin.space.entity.SpSpace;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.SpringContextHolder;
import com.education.classroom.utils.StringUtils;

/**
 * 教室管理Service
 * @author 尚军伟
 * @version 2016/08/06
 */
@Service
public class SpSpaceService extends CrudService<SpSpaceDao, SpSpace> {

	private static SpSpaceDao spSchoolDao = SpringContextHolder
            .getBean(SpSpaceDao.class);
	@Autowired
	private SpSpaceDao spaceDao;
	
	public SpSpace get(String id) {
		return super.get(id);
	}
	
	public List<SpSpace> findList(Map<String, Object> filters) {
		return spaceDao.findList(filters);
	}
	
	public Page<SpSpace> findPage(Page<SpSpace> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpSpace> schems = spaceDao.findList(filters);
		PageInfo<SpSpace> resultPage = new PageInfo<SpSpace>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	

	public static String getSchoolNamebyId(String id) {
		String name = "";
		if (StringUtils.isNotBlank(id)) {
			name = spSchoolDao.selSchoolNamebyId(id);
		}
		return name;
	}
	
	public static Map<String, String> getSpSchoolMap(boolean emptyRowFlag) {
		// 检索产品类型
		List<SpSpace> ls = selSpSchool();
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		if (emptyRowFlag) {
			map.put("", "请选择");
		}
		for (int i = 0; i < ls.size(); i++) {
			map.put(ls.get(i).getId(), ls.get(i).getName());
		}
		return map;
	}

	public static List<SpSpace> selSpSchool() {
		List<SpSpace> ls = spSchoolDao.selSpSchool();
		return ls;
	}

}