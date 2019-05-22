/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.classgroup.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 班级小组关联Entity
 * @author 边磊
 * @version 2016/08/06
 */
public class SpClassGroup extends DataEntity<SpClassGroup> {
	
	private static final long serialVersionUID = 1L;
	private String classId;		// 班级
	private String groupId;		// 小组
	
	public SpClassGroup() {
		super();
	}

	public SpClassGroup(String id){
		super(id);
	}

	@Length(min=1, max=32, message="班级长度必须介于 1 和 32 之间")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	@Length(min=1, max=32, message="小组长度必须介于 1 和 32 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}