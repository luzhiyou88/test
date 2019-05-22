package com.education.classroom.core.modules.spadmin.innermem.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.innermem.entity.SpInnerMember;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 会员预留信息管理DAO接口
 * @author 尚军伟
 * @version 2016/08/06
 */
@MyBatisDao
public interface SpInnerMemberDao extends CrudDao<SpInnerMember> {
	
	//审核会员预留信息
	public void checkPublishState(Map<String,Object> map);
	
	/**
	 * 条件查询预留会员
	 * 2016年8月8日
	 * By zhangyongsheng
	 * @param params
	 * @return
	 */
	public List<SpInnerMember> getByParams(Map<String,Object> params);
	
	/**
	 * 更新预留会员的状态
	 * 2016年8月15日
	 * By zhangyongsheng
	 * @param innerMember
	 * @return
	 */
	public int updateState(SpInnerMember innerMember);
	
	//验证手机唯一性
	public int checkPhone(Map<String,Object> map);
	
	//预留名额是否已满校验
	public int checkNumber(Map<String,Object> map);
	
	/**
	 * 根据ID删除预留会员
	 * 2016年8月17日
	 * By zhangyongsheng
	 * @param id
	 * @return
	 */
	public int deleteInnerMember(String id);
}