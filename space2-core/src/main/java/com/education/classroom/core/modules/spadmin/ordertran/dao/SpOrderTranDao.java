package com.education.classroom.core.modules.spadmin.ordertran.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.ordertran.entity.SpOrderTran;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;


/**
 * 订单流水表增删改查DAO接口
 * @author 赵新月
 * @version 2016/08/17
 */
@MyBatisDao
public interface SpOrderTranDao extends CrudDao<SpOrderTran> {
	public List<SpOrderTran> findPageList(Map<String, Object> queryMap);
}