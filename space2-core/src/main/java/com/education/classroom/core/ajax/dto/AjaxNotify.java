package com.education.classroom.core.ajax.dto;

import java.io.Serializable;

/**
 * ajax返回消息对象
 * @Class Name AjaxNotify
 * @author 张永生
 * @Create In 2016年4月21日
 */
public class AjaxNotify implements Serializable{

	private static final long serialVersionUID = -1774169464743492403L;

	private String success;
	private String message;
	private int successNum;
	private int failNum;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}

	public int getFailNum() {
		return failNum;
	}

	public void setFailNum(int failNum) {
		this.failNum = failNum;
	}
	
	
	
}
