package com.education.classroom.core.member.type;

/**
 * 发布状态
 * @Class Name PublishState
 * @author zhangyongsheng
 * @Create In 2016年8月8日
 */
public interface PublishState {
	
	 /**
	  *  未发布
	  */
	 public static final String UN_PUBLISH = "0";
	 
	 /**
	  *  等待审核
	  */
	 public static final String WAIT_AUDIT = "1";
	 
	 /**
	  *  审核未通过
	  */
	 public static final String AUDIT_UNPASSED = "2";
	 
	 /**
	  *  审核通过
	  */
	 public static final String AUDIT_PASSED = "3";
	 
	 /**
	  * 平台课程
	  */
	 public static final String PLAT = "4";
	 
}
