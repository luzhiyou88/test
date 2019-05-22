package com.education.classroom.core.exception.code;


/**
 * ldap异常码
 * @Class Name LdapExceptionCode
 * @author 张永生
 * @Create In 2015年11月20日
 */
public class LdapExceptionCode {
	
	
	/**
	 * ldap用户授权成功
	 */
	public static final int USER_AUTH_SUCCESS = 1;
	public static final String USER_AUTH_SUCCESS_DESC = "ldap用户授权成功";
	/**
	 * ldap用户授权失败
	 */
	public static final int USER_AUTH_FAIL = 2;
	public static final String USER_AUTH_FAIL_DESC = "ldap用户授权失败";
	/**
	 * ldap新增用户成功
	 */
	public static final int USER_ADD_SUCCESS = 3;
	public static final String USER_ADD_SUCCESS_DESC = "ldap用户新增成功";
	/**
	 * ldap新增用户失败
	 */
	public static final int USER_ADD_FAIL = 4;
	public static final String USER_ADD_FAIL_DESC = "ldap用户新增失败";
	/**
	 * ldap更新用户成功
	 */
	public static final int USER_UPDATE_SUCCESS = 5;
	public static final String USER_UPDATE_SUCCESS_DESC = "ldap用户更新成功";
	/**
	 * ldap更新用户失败
	 */
	public static final int USER_UPDATE_FAIL = 6;
	public static final String USER_UPDATE_FAIL_DESC = "ldap用户更新失败";
	/**
	 * ldap删除用户成功
	 */
	public static final int USER_DELETE_SUCCESS = 7;
	public static final String USER_DELETE_SUCCESS_DESC = "ldap用户删除成功";
	/**
	 * ldap删除用户失败
	 */
	public static final int USER_DELETE_FAIL = 8;
	public static final String USER_DELETE_FAIL_DESC = "ldap用户删除失败";
	/**
	 * ldap删除用户与角色之间的关系成功
	 */
	public static final int USER_ROLE_DELETE_SUCCESS = 9;
	public static final String USER_ROLE_DELETE_SUCCESS_DESC = "ldap删除用户与角色关系成功";
	/**
	 * ldap删除用户与角色之间的关系失败
	 */
	public static final int USER_ROLE_DELETE_FAIL = 10;
	public static final String USER_ROLE_DELETE_FAIL_DESC = "ldap删除用户与角色关系失败";
	/**
	 * ldap删除用户与角色之间的关系成功
	 */
	public static final int USER_ROLE_ADD_SUCCESS = 11;
	public static final String USER_ROLE_ADD_SUCCESS_DESC = "ldap建立用户与角色关系成功";
	/**
	/**
	 * ldap删除用户与角色之间的关系失败
	 */
	public static final int USER_ROLE_ADD_FAIL = 12;
	public static final String USER_ROLE_ADD_FAIL_DESC = "ldap建立用户与角色关系失败";
	/**
	 * ldap新增角色成功
	 */
	public static final int ROLE_ADD_SUCCESS = 13;
	public static final String ROLE_ADD_SUCCESS_DESC = "ldap新增角色成功";
	/**
	 * ldap新增角色失败
	 */
	public static final int ROLE_ADD_FAIL = 14;
	public static final String ROLE_ADD_FAIL_DESC = "ldap新增角色失败";
	/**
	 * ldap更新角色成功
	 */
	public static final int ROLE_UPDATE_SUCCESS = 15;
	public static final String ROLE_UPDATE_SUCCESS_DESC = "ldap更新角色成功";
	/**
	 * ldap更新角色失败
	 */
	public static final int ROLE_UPDATE_FAIL = 16;
	public static final String ROLE_UPDATE_FAIL_DESC = "ldap更新角色失败";
	/**
	 * ldap删除角色成功
	 */
	public static final int ROLE_DELETE_SUCCESS = 17;
	public static final String ROLE_DELETE_SUCCESS_DESC = "ldap删除角色成功";
	/**
	 * ldap删除角色失败
	 */
	public static final int ROLE_DELETE_FAIL = 18;
	public static final String ROLE_DELETE_FAIL_DESC = "ldap删除角色失败";
	
	
	/**
	 * ldap删除角色失败
	 */
	public static final int USER_IS_EXIST = 19;
	public static final String USER_IS_EXIST_DESC = "ldap用户已经存在";
	
	/**
	 * 根据异常码获得操作ldap的成功信息
	 * 2015年11月23日
	 * By 张永生
	 * @param exceptionCode
	 * @return
	 */
	public static String getSuccessDesc(int exceptionCode){
		String successDesc = null;
		switch(exceptionCode){
		   case USER_AUTH_FAIL:
			   successDesc =  USER_AUTH_SUCCESS_DESC;
			   break;
		   case USER_ADD_FAIL:
			   successDesc =  USER_ADD_SUCCESS_DESC;
			   break;
		   case USER_UPDATE_FAIL:
			   successDesc =  USER_UPDATE_SUCCESS_DESC;
			   break;
		   case USER_DELETE_FAIL:
			   successDesc =  USER_DELETE_SUCCESS_DESC;
			   break;
		   case USER_ROLE_DELETE_FAIL:
			   successDesc =  USER_ROLE_DELETE_SUCCESS_DESC;
			   break;
		   case USER_ROLE_ADD_FAIL:
			   successDesc =  USER_ROLE_ADD_SUCCESS_DESC;
			   break;
		   case ROLE_ADD_FAIL:
			   successDesc =  ROLE_ADD_SUCCESS_DESC;
			   break;
		   case ROLE_UPDATE_FAIL:
			   successDesc =  ROLE_UPDATE_SUCCESS_DESC;
			   break;
		   case ROLE_DELETE_FAIL:
			   successDesc =  ROLE_DELETE_SUCCESS_DESC;
			   break;
		   default:
			   successDesc = "";
			   break;
		}
		return successDesc;
	}
	
	/**
	 * 根据异常码获得失败描述
	 * 2015年11月23日
	 * By 张永生
	 * @param failCode
	 * @return
	 */
	public static String getFailDesc(int failCode){
		String failDesc = null;
		switch(failCode){
		   case USER_AUTH_FAIL:
			   failDesc =  USER_AUTH_FAIL_DESC;
			   break;
		   case USER_IS_EXIST:
			   failDesc =  USER_IS_EXIST_DESC;
			   break;
		   case USER_ADD_FAIL:
			   failDesc =  USER_ADD_FAIL_DESC;
			   break;
		   case USER_UPDATE_FAIL:
			   failDesc =  USER_UPDATE_FAIL_DESC;
			   break;
		   case USER_DELETE_FAIL:
			   failDesc =  USER_DELETE_FAIL_DESC;
			   break;
		   case USER_ROLE_DELETE_FAIL:
			   failDesc =  USER_ROLE_DELETE_FAIL_DESC;
			   break;
		   case USER_ROLE_ADD_FAIL:
			   failDesc =  USER_ROLE_ADD_FAIL_DESC;
			   break;
		   case ROLE_ADD_FAIL:
			   failDesc =  ROLE_ADD_FAIL_DESC;
			   break;
		   case ROLE_UPDATE_FAIL:
			   failDesc =  ROLE_UPDATE_FAIL_DESC;
			   break;
		   case ROLE_DELETE_FAIL:
			   failDesc =  ROLE_DELETE_FAIL_DESC;
			   break;
		   default:
			   failDesc = "";
			   break;
		}
		return failDesc;
	}
	
}
