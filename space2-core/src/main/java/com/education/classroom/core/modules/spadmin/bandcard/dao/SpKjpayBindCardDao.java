package com.education.classroom.core.modules.spadmin.bandcard.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.bandcard.entity.SpKjpayBindCard;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;


/**
 * 绑卡表增删改查DAO接口
 * @author 赵新月
 * @version 2016/08/31
 */
@MyBatisDao
public interface SpKjpayBindCardDao extends CrudDao<SpKjpayBindCard> {
	public List<SpKjpayBindCard> findPageList(Map<String, Object> queryMap);
	
	
	public List<SpKjpayBindCard> findListByBankCode(SpKjpayBindCard spKjpayBindCard);
	
	public int uptDelFlgByBankCode(SpKjpayBindCard spKjpayBindCard);
	
	
	public int deleteByUserIdBindId(SpKjpayBindCard spKjpayBindCard);
	
	public int uptBindValidByUserIdBindId(SpKjpayBindCard spKjpayBindCard);
	
	public List<SpKjpayBindCard> findValidCardList(SpKjpayBindCard spKjpayBindCard);
	
	
}