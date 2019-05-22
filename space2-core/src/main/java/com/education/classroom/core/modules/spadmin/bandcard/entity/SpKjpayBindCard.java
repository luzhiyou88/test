package com.education.classroom.core.modules.spadmin.bandcard.entity;



import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 绑卡表增删改查Entity
 * @author 赵新月
 * @version 2016/09/04
 */
public class SpKjpayBindCard extends DataEntity<SpKjpayBindCard> {
	
	private static final long serialVersionUID = 1L;
	private String spaceId;		// 学校标识
	private String userId;		// 用户标识
	private String bindId;		// 绑卡ID
	private String bankCardNo;		// 银行卡号
	private String bankCode;		// 银行编码
	private String mobilePhone;		// 预留的手机号
	private Date bindValid;		// 格式为yyyyMMddHHmmss
	
	public SpKjpayBindCard() {
		super();
	}

	public SpKjpayBindCard(String id){
		super(id);
	}

	@Length(min=1, max=32, message="学校标识长度必须介于 1 和 32 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Length(min=1, max=128, message="用户标识长度必须介于 1 和 128 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=32, message="绑卡ID长度必须介于 1 和 32 之间")
	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}
	
	@Length(min=1, max=50, message="银行卡号长度必须介于 1 和 50 之间")
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	
	@Length(min=1, max=32, message="银行编码长度必须介于 1 和 32 之间")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	@Length(min=1, max=20, message="预留的手机号长度必须介于 1 和 20 之间")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBindValid() {
		return bindValid;
	}

	public void setBindValid(Date bindValid) {
		this.bindValid = bindValid;
	}
	
}