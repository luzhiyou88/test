package com.education.classroom.core.mobile.dto;

import java.io.Serializable;

import com.education.classroom.core.modules.spadmin.member.entity.SpMember;

/**
 * 注册返回结果dto
 * @Class Name RegisterNotifyDto
 * @author zhangyongsheng
 * @Create In 2016年8月8日
 */
public class RegisterNotifyDto implements Serializable {

	private static final long serialVersionUID = -8044577182381828027L;

	private boolean success;
	private SpMember member;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public SpMember getMember() {
		return member;
	}

	public void setMember(SpMember member) {
		this.member = member;
	}

}
