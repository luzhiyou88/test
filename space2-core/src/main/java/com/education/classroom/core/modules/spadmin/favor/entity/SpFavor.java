/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.favor.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 资源收藏Entity
 * 
 * @author 边磊
 * @version 2016/08/10
 */
public class SpFavor extends DataEntity<SpFavor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3282929414066351453L;
	private String userId; // 用户
	private String sourceType; // 类型 1视频 2音频3电子书
	private String sourceId; // 收藏id

	public SpFavor() {
		super();
	}

	public SpFavor(String id) {
		super(id);
	}

	@Length(min = 1, max = 32, message = "用户长度必须介于 1 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min = 1, max = 1, message = "类型 1视频 2音频3电子书长度必须介于 1 和 1 之间")
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Length(min = 1, max = 32, message = "收藏id长度必须介于 1 和 32 之间")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

}