/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.material.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 材料管理Entity
 * @author 路志友
 * @version 2016/08/17
 */
public class SpMaterials extends DataEntity<SpMaterials> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 材料名称
	private String cotent;		// 材料内容
	
	public SpMaterials() {
		super();
	}

	public SpMaterials(String id){
		super(id);
	}

	@Length(min=1, max=32, message="材料名称长度必须介于 1 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=255, message="材料内容长度必须介于 1 和 256 之间")
	public String getCotent() {
		return cotent;
	}

	public void setCotent(String cotent) {
		this.cotent = cotent;
	}
	
}