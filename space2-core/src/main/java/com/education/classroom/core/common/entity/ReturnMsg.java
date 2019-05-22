/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.common.entity;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 共通返回消息Entity
 * @author 朱杰
 * @version 2016/08/06
 */
public class ReturnMsg extends DataEntity<ReturnMsg> {
	
	public ReturnMsg(){
	}
	
	public ReturnMsg(String result){
		this.result = result;
	}
	
	public ReturnMsg(String result,String msg){
		this.result = result;
		this.msg = msg;
	}
	
	public ReturnMsg(String result,String msg, Object data){
		this.result = result;
		this.msg = msg;
		this.data = data;
	}
	
	private static final long serialVersionUID = 1L;
	
	private String result;//返回的结果stateConstants
	private String msg;//返回的消息
	private Object data;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}