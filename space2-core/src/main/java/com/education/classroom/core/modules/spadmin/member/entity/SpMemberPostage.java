/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.member.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 会员资费管理Entity
 * @author 尚军伟
 * @version 2016/08/06
 */
public class SpMemberPostage extends DataEntity<SpMemberPostage> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 会员资费名称
	private String month;		// 购买会员的月份
	private String postagePrice;		// 会员资费价格
	
	public SpMemberPostage() {
		super();
	}

	public SpMemberPostage(String id){
		super(id);
	}

	@Length(min=1, max=128, message="会员资费名称长度必须介于 1 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=11, message="购买会员的月份长度必须介于 1 和 11 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getPostagePrice() {
		return postagePrice;
	}

	public void setPostagePrice(String postagePrice) {
		this.postagePrice = postagePrice;
	}
	
}