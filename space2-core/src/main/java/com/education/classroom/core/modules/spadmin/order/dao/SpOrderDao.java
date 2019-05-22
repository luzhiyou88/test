package com.education.classroom.core.modules.spadmin.order.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.order.entity.SpOrder;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;


/**
 * 订单表增删改查DAO接口
 * @author 赵新月
 * @version 2016/08/18
 */
@MyBatisDao
public interface SpOrderDao extends CrudDao<SpOrder> {
	public List<SpOrder> findPageList(Map<String, Object> queryMap);
	
	/**
	 * 根据订单号更新订单状态
	 * @param spOrder
	 * @return
	 */
	public int updateByOrderNo(SpOrder spOrder);
	
	
	public List<SpOrder> findListByOrderNo(SpOrder spOrder);
	
}