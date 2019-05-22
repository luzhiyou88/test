/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.project.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 项目管理Entity
 * @author 杨立明
 * @version 2016/08/26
 */
public class SpPadProject extends DataEntity<SpPadProject> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 项目名称
	private String lessonId;		// 路演id
	private String speakerId;		// 主讲人Id
	private String characteristic;		// 特点
	private String backdrop;		// 背景
	private String outletPlaning;		// 市场规划
	private String profitModel;		// 盈利模式
	private String businessProposalUrl;		// 商业计划书
	private String spaceId;		// 所属空间
	private Date beginUpdateDate;		// 开始 修改时间
	private Date endUpdateDate;		// 结束 修改时间
	
	private String lessonName;
	private String speakerName;
	private String spaceName;
	private List<SpProjectResource> projectResourceList;
	private List<String> addResourceId;
	private boolean delFileFlag=false;
	private boolean readonlyFlag;
	private String jsonBody;
	
	private List<SpProjectUser> projectUserList;
	
	
	public SpPadProject() {
		super();
	}

	public SpPadProject(String id){
		super(id);
	}
	
	public boolean getReadonlyFlag() {
		return readonlyFlag;
	}

	public void setReadonlyFlag(boolean readonlyFlag) {
		this.readonlyFlag = readonlyFlag;
	}

	public List<SpProjectResource> getProjectResourceList() {
		return projectResourceList;
	}

	public void setProjectResourceList(List<SpProjectResource> projectResourceList) {
		this.projectResourceList = projectResourceList;
	}

	public List<String> getAddResourceId() {
		return addResourceId;
	}

	public void setAddResourceId(List<String> addResourceId) {
		this.addResourceId = addResourceId;
	}
	
	public boolean getDelFileFlag() {
		return delFileFlag;
	}

	public void setDelFileFlag(boolean delFileFlag) {
		this.delFileFlag = delFileFlag;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	@Length(min=1, max=128, message="项目名称长度必须介于 1 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=32, message="路演id长度必须介于 1 和 32 之间")
	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	
	@Length(min=1, max=32, message="主讲人Id长度必须介于 1 和 32 之间")
	public String getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(String speakerId) {
		this.speakerId = speakerId;
	}
	
	@Length(min=0, max=1024, message="特点长度必须介于 0 和 1024 之间")
	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}
	
	@Length(min=0, max=1024, message="背景长度必须介于 0 和 1024 之间")
	public String getBackdrop() {
		return backdrop;
	}

	public void setBackdrop(String backdrop) {
		this.backdrop = backdrop;
	}
	
	@Length(min=0, max=2048, message="市场规划长度必须介于 0 和 2048 之间")
	public String getOutletPlaning() {
		return outletPlaning;
	}

	public void setOutletPlaning(String outletPlaning) {
		this.outletPlaning = outletPlaning;
	}
	
	@Length(min=0, max=1024, message="盈利模式长度必须介于 0 和 1024 之间")
	public String getProfitModel() {
		return profitModel;
	}

	public void setProfitModel(String profitModel) {
		this.profitModel = profitModel;
	}
	
	@Length(min=1, max=256, message="商业计划书长度必须介于 1 和 256 之间")
	public String getBusinessProposalUrl() {
		return businessProposalUrl;
	}

	public void setBusinessProposalUrl(String businessProposalUrl) {
		this.businessProposalUrl = businessProposalUrl;
	}
	
	@Length(min=1, max=32, message="所属空间长度必须介于 1 和 32 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}

	public String getJsonBody() {
		return jsonBody;
	}

	public void setJsonBody(String jsonBody) {
		this.jsonBody = jsonBody;
	}

	public List<SpProjectUser> getProjectUserList() {
		return projectUserList;
	}

	public void setProjectUserList(List<SpProjectUser> projectUserList) {
		this.projectUserList = projectUserList;
	}
		
}