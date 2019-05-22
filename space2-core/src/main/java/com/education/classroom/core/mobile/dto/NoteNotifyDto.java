package com.education.classroom.core.mobile.dto;

import java.io.Serializable;

/**
 * 笔记保存返回结果dto
 * @Class Name AppointmentNotifyDto
 * @author shangjunwei 
 * @Create In 2016年9月8日
 */
public class NoteNotifyDto implements Serializable {
	
	private static final long serialVersionUID = -4021950557318296509L;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


}
