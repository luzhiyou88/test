/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.category.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 鍒嗙被绠＄悊Entity
 * @author 灏氬啗浼� * @version 2016-08-05
 */
public class SpCategory extends DataEntity<SpCategory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 鐩被鍚嶇О
	private String parentId; // 鐖剁骇ID
	private SpCategory parent;		// 鐖剁骇id
	
	public SpCategory() {
		super();
	}

	public SpCategory(String id){
		super(id);
	}

	@Length(min=1, max=128, message="鐩被鍚嶇О闀垮害蹇呴』浠嬩簬 1 鍜�128 涔嬮棿")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public SpCategory getParent() {
		return parent;
	}

	public void setParent(SpCategory parent) {
		this.parent = parent;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
}