/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.vistor.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 用户资源统计Entity
 * @author 边磊
 * @version 2016/08/09
 */
public class SpResourceVistor extends DataEntity<SpResourceVistor> {
	
	private static final long serialVersionUID = 1L;
	private String sourceId;		// 资源id
	private String sourceType;		// 资源表来源 0课程 1视频 2音频 3电子书
	private Double scoreAvg;		// 评价平均分
	private String clickNum;		// 点击数-热度
	private String praise;		// 点赞数
	private String downloadNum;		// 下载数
	
	public SpResourceVistor() {
		super();
	}

	public SpResourceVistor(String id){
		super(id);
	}

	@Length(min=1, max=32, message="资源id长度必须介于 1 和 32 之间")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@Length(min=1, max=1, message="资源表来源 0课程 1视频 2音频 3电子书长度必须介于 1 和 1 之间")
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	public Double getScoreAvg() {
		return scoreAvg;
	}

	public void setScoreAvg(Double scoreAvg) {
		this.scoreAvg = scoreAvg;
	}
	
	@Length(min=0, max=11, message="点击数-热度长度必须介于 0 和 11 之间")
	public String getClickNum() {
		return clickNum;
	}

	public void setClickNum(String clickNum) {
		this.clickNum = clickNum;
	}
	
	@Length(min=0, max=11, message="点赞数长度必须介于 0 和 11 之间")
	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise;
	}
	
	@Length(min=0, max=11, message="下载数长度必须介于 0 和 11 之间")
	public String getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(String downloadNum) {
		this.downloadNum = downloadNum;
	}
	
}