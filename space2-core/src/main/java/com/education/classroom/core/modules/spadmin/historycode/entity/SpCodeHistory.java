/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.historycode.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 历史验证码Entity
 * @author 边磊
 * @version 2016/08/08
 */
public class SpCodeHistory extends DataEntity<SpCodeHistory> {
	
	private static final long serialVersionUID = 1L;
	private String phoneNo;		// phone_no
	private String code;		// 验证码
	
	public SpCodeHistory() {
		super();
	}

	public SpCodeHistory(String id){
		super(id);
	}

	@Length(min=1, max=11, message="phone_no长度必须介于 1 和 11 之间")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Length(min=1, max=6, message="验证码长度必须介于 1 和 6 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}