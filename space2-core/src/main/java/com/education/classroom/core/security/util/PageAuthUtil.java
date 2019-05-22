package com.education.classroom.core.security.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.education.classroom.core.security.dao.PageAuthDao;
import com.education.classroom.core.security.entity.PageAuth;
import com.education.classroom.core.users.entity.Role;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.utils.SpringContextHolder;

/**
 * 页面权限控制工具类
 * @author zhou
 * @version 2015-11-02
 */
public class PageAuthUtil {
	
	private static PageAuthDao pageauthDao = SpringContextHolder.getBean(PageAuthDao.class);
	
	/**
	 * 获取当前页面权限控制列表
	 * @param PageAuth
	 * @return PageAuth
	 */
	public static List<PageAuth> getPageAuthList(PageAuth pageAuth){
		List<PageAuth> authList = pageauthDao.getPageAuthInfo(pageAuth);
		return authList;
	}
	
	/**
	 * 获取当前页面权限控制信息字符串
	 * 2015年12月1日
	 * By 张永生
	 * @param pageAuth
	 * @return
	 */
	public static String getPageAuthData(PageAuth pageAuth){
		// 取得当前用户
		User currentUser = UserUtils.getUser();
		// 取得当前用户权限
		List<Role> roleList = currentUser.getRoleList();
		String roleId = roleList.get(0).getId();
		pageAuth.setRoleId(roleId);				// 权限ID
		List<PageAuth> authList = getPageAuthList(pageAuth);
		StringBuffer result = new StringBuffer("");
		for (int i = 0; i < authList.size(); i++) {
			result.append(authList.get(i).getItemId());
			result.append(":");
			result.append(authList.get(i).getDisplayMode());
			result.append(",");
		}
		String authData = "";
		if (StringUtils.isNotEmpty(result.toString())) {
			authData = result.toString().substring(0, result.length() - 1);
		}
		return authData;
	}
}
