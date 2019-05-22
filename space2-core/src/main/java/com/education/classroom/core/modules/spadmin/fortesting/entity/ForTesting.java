/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.fortesting.entity;

import org.apache.commons.lang3.StringUtils;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 测试列表Entity
 * 
 * @author 边磊
 * @version 2016/08/18
 */
public class ForTesting extends DataEntity<ForTesting> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7865011397993665408L;
	private String examinationName;// 试卷名称
	private String examinationNumber;// 试卷题数
	private String lessonStarttime;// 课程开始时间
	private String lessonEndtime;// 课程结束时间
	private Integer totalScore;// 得分
	private String examinationId;// 试卷id
	private String userId;// 用户id
	private String ranking;
	private String accuracy;
	private String AVGScore;
	private String answerScoreId;//测试结果Id
	private String lessonSource;//测试结果Id

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public String getExaminationNumber() {
		return examinationNumber;
	}

	public void setExaminationNumber(String examinationNumber) {
		this.examinationNumber = examinationNumber;
	}

	public String getLessonStarttime() {
		return lessonStarttime;
	}

	public void setLessonStarttime(String lessonStarttime) {
		this.lessonStarttime = lessonStarttime;
	}

	public String getLessonEndtime() {
		return lessonEndtime;
	}

	public void setLessonEndtime(String lessonEndtime) {
		this.lessonEndtime = lessonEndtime;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public String getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(String examinationId) {
		this.examinationId = examinationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {

		if (StringUtils.isEmpty(ranking)) {
			this.ranking = "";
		} else {
			this.ranking = ranking;

		}

	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getAVGScore() {
		return AVGScore;
	}

	public void setAVGScore(String aVGScore) {
		if (StringUtils.isEmpty(aVGScore)) {
			this.AVGScore = "";
		} else {
			this.AVGScore = aVGScore;
		}
	}

	public String getAnswerScoreId() {
		return answerScoreId;
	}

	public void setAnswerScoreId(String answerScoreId) {
		this.answerScoreId = answerScoreId;
	}

	public String getLessonSource() {
		return lessonSource;
	}

	public void setLessonSource(String lessonSource) {
		this.lessonSource = lessonSource;
	}
 
}