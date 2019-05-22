/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.education.classroom.core.users.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.users.entity.User;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 条件查询用户
	 * 2016年8月17日
	 * By zhangyongsheng
	 * @param filters
	 * @return
	 */
	public List<User> findListByMap(Map<String, Object> filters);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	
	/**
	 * 插入数据到cr_user_team
	 * @param userTeam
	 * @return
	 */
//	public int insertUserTeam(UserTeam userTeam);
	
	/**
	 * 插入数据到cr_user
	 * @param user
	 * @return
	 */
	public int insertCreatorUser(User user);
	
	/**
	 * 更新数据到cr_user
	 * @param user
	 * @return
	 */
	public int updateCreator(User user);
	/**
	 * 更新数据到cr_user_team
	 * @param userTeam
	 * @return
	 */
//	public int updateCreatorTeam(UserTeam userTeam);
	
	/**
	 * 删除数据cr_user_team
	 * @param userTeam
	 * @return
	 */
	public void deleteCreator(String userId);
	/**
	 * 删除数据sys-user
	 * @param userTeam
	 * @return
	 */
	public void deleteCreatorUser(String userId);

	/**
	 * 检查手机号是否存在
	 * 2016年8月18日
	 * By 路志友
	 * @param mobile
	 * @return
	 */
	public User checkMobile(String mobile);
}
