/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.member.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.member.entity.SpMember;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 会员管理DAO接口
 * @author 尚军伟
 * @version 2016/08/08
 */
@MyBatisDao
public interface SpMemberDao extends CrudDao<SpMember> {
	
	/**
	 * 条件查询会员
	 * 2016年8月8日
	 * By zhangyongsheng
	 * @param params
	 * @return
	 */
	public SpMember getByParams(Map<String,Object> params);
	
	/**
	 * 条件查询会员
	 * 2016年8月9日
	 * By shangjunwei
	 * @param map
	 * @return
	 */
	public int selectMemUser(Map<String,Object> map);
	
	/**
	 * 根据Id查询会员列表
	 * 2016年8月9日
	 * By zhaoxinyue
	 * @param spMember
	 * @return
	 */
	public List<SpMember> findListById(SpMember spMember);
	
	/**
	 * 根据userId查询会员列表
	 * 2016年8月9日
	 * By zhaoxinyue
	 * @param spMember
	 * @return
	 */
	public List<SpMember> findListByUserId(SpMember spMember);
	
	
	/**
	 * 根据userId更新会员信息
	 * 2016年8月9日
	 * By zhaoxinyue
	 * @param spMember
	 * @return
	 */
	public int updateSelectiveByUserId(SpMember spMember);
	
	
}