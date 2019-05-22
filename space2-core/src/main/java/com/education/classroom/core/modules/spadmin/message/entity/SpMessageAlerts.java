/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.message.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 公告管理Entity
 * @author 尚军伟
 * @version 2016/08/06
 */
public class SpMessageAlerts extends DataEntity<SpMessageAlerts> {
	
	private static final long serialVersionUID = -4988082133494144705L;
	private String title;		// 标题
	private String content;		// 内容
	private String operaterName;		// 发布人名称
	private String messageType;		// 消息类型 1系统
	
	public SpMessageAlerts() {
		super();
	}

	public SpMessageAlerts(String id){
		super(id);
	}

	@Length(min=0, max=128, message="标题长度必须介于 0 和 128 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=512, message="内容长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="发布人名称长度必须介于 0 和 64 之间")
	public String getOperaterName() {
		return operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}
	
	@Length(min=0, max=1, message="消息类型 1系统长度必须介于 0 和 1 之间")
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
}