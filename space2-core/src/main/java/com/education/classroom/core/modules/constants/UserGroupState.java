package com.education.classroom.core.modules.constants;


/**
 * 用户小组关系的状态
 * @Class Name UserGroupState
 * @author zhangyongsheng
 * @Create In 2016年8月11日
 */
public interface UserGroupState {

	  /**
	   * 被邀请
	   */
	  public static final String STATE_INVITED = "0";
	  
	  /**
	   * 申请加入
	   */
	  public static final String STATE_APPLY_JOIN = "1";
	  
	  /**
	   * 已加入
	   */
	  public static final String STATE_JOINED = "3";
	  
	  /**
	   * 拒绝邀请
	   */
	  public static final String STATE_REFUSE_INVITE = "4";
	  
	  /**
	   * 拒绝申请
	   */
	  public static final String STATE_REFUSE_APPLY = "5";
	  
}
