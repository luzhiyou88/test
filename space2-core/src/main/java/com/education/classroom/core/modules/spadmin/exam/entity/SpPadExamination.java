/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.exam.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.modules.spadmin.problems.entity.SpPadProblems;
import com.education.classroom.core.persistence.DataEntity;

/**
 * Pad试卷Entity
 * @Class Name SpPadExamination
 * @author zhujie
 * @Create In 2016年9月8日
 */
public class SpPadExamination extends DataEntity<SpPadExamination> {
	
	private static final long serialVersionUID = -3180346311637620050L;
	private String lessonId;		// 课程ID
	private String examinationName;		// 试卷名称
	private String examinationTotalScore;		// 试卷总分数
	private String examinationNumber;		// 题目总数
	private String originators;		// 出题人
	private String timing;		// 试卷用时
	private String state;		// 试卷状态 0未开始 1开始 2 结束
	private List<SpPadProblems> problemsList;
	
	public SpPadExamination() {
		super();
	}

	public SpPadExamination(String id){
		super(id);
	}

	@Length(min=1, max=32, message="课程ID长度必须介于 1 和 32 之间")
	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	
	@Length(min=1, max=128, message="试卷名称长度必须介于 1 和 128 之间")
	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}
	
	@Length(min=1, max=11, message="试卷总分数长度必须介于 1 和 11 之间")
	public String getExaminationTotalScore() {
		return examinationTotalScore;
	}

	public void setExaminationTotalScore(String examinationTotalScore) {
		this.examinationTotalScore = examinationTotalScore;
	}
	
	@Length(min=1, max=11, message="题目总数长度必须介于 1 和 11 之间")
	public String getExaminationNumber() {
		return examinationNumber;
	}

	public void setExaminationNumber(String examinationNumber) {
		this.examinationNumber = examinationNumber;
	}
	
	@Length(min=0, max=200, message="出题人长度必须介于 0 和 200 之间")
	public String getOriginators() {
		return originators;
	}

	public void setOriginators(String originators) {
		this.originators = originators;
	}
	
	@Length(min=1, max=11, message="试卷用时长度必须介于 1 和 11 之间")
	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}
	
	@Length(min=1, max=1, message="试卷状态 0未开始 1开始 2 结束长度必须介于 1 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<SpPadProblems> getProblemsList() {
		return problemsList;
	}

	public void setProblemsList(List<SpPadProblems> problemsList) {
		this.problemsList = problemsList;
	}
	
}