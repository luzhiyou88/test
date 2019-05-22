/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.user.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.users.entity.User;

/**
 * 用户管理DAO接口
 * @author 边磊
 * @version 2016/08/06
 */
@MyBatisDao
public interface SpUserDao extends CrudDao<SpUser> {
	
	/**
	 * 根据登录名(手机号)和spaceId查找用户
	 * 2016年8月9日
	 * By zhangyongsheng
	 * @param loginName
	 * @return
	 */
	public SpUser findByLoginName(Map<String,Object> params);
	
	/**
	 * 修改密码
	 * 2016年8月9日
	 * By zhangyongsheng
	 * @param params
	 */
	public void updatePassword(Map<String, Object> params);
	
	/**
	 * 删除老师用户
	 * 2016年8月10日
	 * By 路志友
	 * @param spUser
	 */
	public void  deleteSpUser(String id);
	
	/**
	 * 根据loginName查询
	 * 2016年8月10日
	 * By 路志友
	 * @param u
	 * @return SpUser
	 */
	public SpUser getbySpUser(SpUser u);

	/**
	 * 检查手机号是否存在
	 * 2016年8月18日
	 * By 路志友
	 * @param mobile
	 * @return
	 */
	public SpUser checkMobile(String mobile);

	public int hasUser(User user);

	//启用用户
	public void revert(SpUser spUser);
	
	
	//通过loginName和spaceId查询用户是否存在
	public int selectUser(@Param(value="loginName") String loginName,@Param(value="spaceId") String spaceId);
	
	
}