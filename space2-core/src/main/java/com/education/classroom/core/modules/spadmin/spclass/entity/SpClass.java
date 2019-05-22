/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.spclass.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 班级管理Entity
 * @author 边磊
 * @version 2016/08/05
 */
public class SpClass extends DataEntity<SpClass> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 班级名称
	private String spaceId;		// 所属第二空间
	private String teacherId;		// 班主任
	
	public SpClass() {
		super();
	}

	public SpClass(String id){
		super(id);
	}

	@Length(min=1, max=128, message="班级名称长度必须介于 1 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=32, message="所属第二空间长度必须介于 1 和 32 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Length(min=1, max=32, message="班主任长度必须介于 1 和 32 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
}