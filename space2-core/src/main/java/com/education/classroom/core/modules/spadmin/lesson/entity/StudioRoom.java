
package com.education.classroom.core.modules.spadmin.lesson.entity;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 课程管理Entity
 * @author 尚军伟
 * @version 2016-07-20
 */
public class StudioRoom extends DataEntity<StudioRoom> {

	private static final long serialVersionUID = 7404064174807710456L;

	private String publishUrl;  //推流地址
	
	public StudioRoom(){}

	public String getPublishUrl() {
		return publishUrl;
	}

	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl;
	}
	
	
}