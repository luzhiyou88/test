/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.education.classroom.core.dict.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.cache.util.CacheUtils;
import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.dict.dao.DictDao;
import com.education.classroom.core.dict.entity.Dict;
import com.education.classroom.core.dict.util.DictUtils;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}
	
	public Page<Dict> findPage(Page<Dict> page, Map<String, Object> filters){
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<Dict> dicts = dao.findList(filters);
		PageInfo<Dict> dictPage = new PageInfo<Dict>(dicts);
		PageUtil.convertPage(dictPage, page);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

}
