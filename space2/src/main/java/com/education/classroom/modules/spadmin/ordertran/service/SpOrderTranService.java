/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.ordertran.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.ordertran.dao.SpOrderTranDao;
import com.education.classroom.core.modules.spadmin.ordertran.entity.SpOrderTran;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;


/**
 * 订单流水表增删改查Service
 * @author 赵新月
 * @version 2016/08/17
 */
@Service
@Transactional(readOnly = true)
public class SpOrderTranService extends CrudService<SpOrderTranDao, SpOrderTran> {
	@Autowired
	private SpOrderTranDao spOrderTranDao;
		
	public SpOrderTran get(String id) {
		return super.get(id);
	}
	
	public List<SpOrderTran> findList(SpOrderTran spOrderTran) {
		return super.findList(spOrderTran);
	}
	
	public Page<SpOrderTran> findPage(Page<SpOrderTran> page, Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		PageHelper.orderBy("createDate desc");
		List<SpOrderTran> schems = spOrderTranDao.findPageList(queryMap);
		PageInfo<SpOrderTran> resultPage = new PageInfo<SpOrderTran>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpOrderTran spOrderTran) {
		super.save(spOrderTran);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpOrderTran spOrderTran) {
		super.delete(spOrderTran);
	}
	
}