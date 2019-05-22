/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.section.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 节次管理Entity
 * @author 路志友
 * @version 2016-08-05
 */
public class SpSection extends DataEntity<SpSection> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String startTime;		// 开始时间
	private String endTime;		// 结束时间
	
	public SpSection() {
		super();
	}

	public SpSection(String id){
		super(id);
	}

	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="开始时间长度必须介于 1 和 20 之间")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Length(min=1, max=20, message="结束时间长度必须介于 1 和 20 之间")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}