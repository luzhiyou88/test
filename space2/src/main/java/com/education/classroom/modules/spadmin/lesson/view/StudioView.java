package com.education.classroom.modules.spadmin.lesson.view;

import java.io.Serializable;

import com.education.classroom.core.modules.spadmin.lesson.entity.StudioRoom;

public class StudioView implements Serializable{
	
	private static final long serialVersionUID = 1229957196116663363L;
	private String result;  //返回结果
	private StudioRoom room;  //直播间信息
	
	
	public StudioView(){}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public StudioRoom getRoom() {
		return room;
	}


	public void setRoom(StudioRoom room) {
		this.room = room;
	}
	
	

}
