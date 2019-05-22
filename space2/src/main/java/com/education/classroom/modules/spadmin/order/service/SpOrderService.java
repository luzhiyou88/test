/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.order.dao.SpOrderDao;
import com.education.classroom.core.modules.spadmin.order.entity.SpOrder;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;


/**
 * 订单表增删改查Service
 * @author 赵新月
 * @version 2016/08/18
 */
@Service
@Transactional(readOnly = true)
public class SpOrderService extends CrudService<SpOrderDao, SpOrder> {
	@Autowired
	private SpOrderDao spOrderDao;
	
	public SpOrder get(String id) {
		return super.get(id);
	}
	
	public List<SpOrder> findList(SpOrder spOrder) {
		return super.findList(spOrder);
	}
	
	public Page<SpOrder> findPage(Page<SpOrder> page, Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		PageHelper.orderBy("createDate desc");
		List<SpOrder> schems = spOrderDao.findPageList(queryMap);
		PageInfo<SpOrder> resultPage = new PageInfo<SpOrder>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpOrder spOrder) {
		super.save(spOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpOrder spOrder) {
		super.delete(spOrder);
	}
	
}