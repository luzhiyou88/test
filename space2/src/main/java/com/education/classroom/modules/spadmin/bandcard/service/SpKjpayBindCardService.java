package com.education.classroom.modules.spadmin.bandcard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.bandcard.dao.SpKjpayBindCardDao;
import com.education.classroom.core.modules.spadmin.bandcard.entity.SpKjpayBindCard;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;

/**
 * 绑卡表增删改查Service
 * @author 赵新月
 * @version 2016/08/31
 */
@Service
@Transactional(readOnly = true)
public class SpKjpayBindCardService extends CrudService<SpKjpayBindCardDao, SpKjpayBindCard> {
	@Autowired 
	private SpKjpayBindCardDao  spKjpayBindCardDao;
	
	
	public SpKjpayBindCard get(String id) {
		return super.get(id);
	}
	
	public List<SpKjpayBindCard> findList(SpKjpayBindCard spKjpayBindCard) {
		return super.findList(spKjpayBindCard);
	}
	
	public List<SpKjpayBindCard> findListByBankCode(SpKjpayBindCard spKjpayBindCard) {
		return spKjpayBindCardDao.findListByBankCode(spKjpayBindCard);
	}
	
	public Page<SpKjpayBindCard> findPage(Page<SpKjpayBindCard> page, Map<String, Object> queryMap) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpKjpayBindCard> schems = spKjpayBindCardDao.findPageList(queryMap);
		PageInfo<SpKjpayBindCard> resultPage = new PageInfo<SpKjpayBindCard>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpKjpayBindCard spKjpayBindCard) {
		super.save(spKjpayBindCard);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpKjpayBindCard spKjpayBindCard) {
		super.delete(spKjpayBindCard);
	}
	
}