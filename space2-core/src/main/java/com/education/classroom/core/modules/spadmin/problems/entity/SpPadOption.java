/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.problems.entity;

import com.education.classroom.core.persistence.DataEntity;

/**
 * Pad用试题选项Entity
 * @Class Name SpOption
 * @author zhujie
 * @Create In 2016年9月8日
 */
public class SpPadOption extends DataEntity<SpPadOption> {
	
	private static final long serialVersionUID = 8331403232236934379L;
	
	public SpPadOption(){};
	
	public SpPadOption(String key,String text,String path,int order){
		this.key = key;
		this.text = text;
		this.path = path;
		this.order = order;
	};
	
	private String key;
	private String text;
	private String path;
	private int order;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}