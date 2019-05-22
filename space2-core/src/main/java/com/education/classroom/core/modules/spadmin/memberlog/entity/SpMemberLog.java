package com.education.classroom.core.modules.spadmin.memberlog.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;



/**
 * 会员记录管理Entity
 * @author 赵新月
 * @version 2016/08/19
 */
public class SpMemberLog extends DataEntity<SpMemberLog> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户id
	private String memberPrice;		// 买价格购
	private Date buyTime;		// 买时间购
	private String month;		// 通开几个月
	private String postageId;		// 资费ID
	
	public SpMemberLog() {
		super();
	}

	public SpMemberLog(String id){
		super(id);
	}

	@Length(min=1, max=32, message="户用id长度必须介于 1 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(String memberPrice) {
		this.memberPrice = memberPrice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="买时间购不能为空")
	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	
	@Length(min=0, max=11, message="通开几个月长度必须介于 0 和 11 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=32, message="资费ID长度必须介于 0 和 32 之间")
	public String getPostageId() {
		return postageId;
	}

	public void setPostageId(String postageId) {
		this.postageId = postageId;
	}

	
}