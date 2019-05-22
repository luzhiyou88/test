package com.education.classroom.core.mobile.dto;

import java.io.Serializable;

/**
 * 用户预约返回结果dto
 * @Class Name AppointmentNotifyDto
 * @author shangjunwei 
 * @Create In 2016年8月9日
 */
public class GroupTopicNotifyDto implements Serializable {

	private static final long serialVersionUID = -1907357637462025263L;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


}
