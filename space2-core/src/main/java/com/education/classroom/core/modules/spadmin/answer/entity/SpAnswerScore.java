/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.answer.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 答案Entity
 * 
 * @author 路志友
 * @version 2016/08/18
 */
public class SpAnswerScore extends DataEntity<SpAnswerScore> {

	private static final long serialVersionUID = 1L;
	private String user; // 用户id
	private String examinationId; // 试卷id
	private String answerType; // 1课上做的 2课下做的
	private String totalScore; // 得分情况
	private Integer ranking;// 排名

	public SpAnswerScore() {
		super();
	}

	public SpAnswerScore(String id) {
		super(id);
	}

	@Length(min = 0, max = 32, message = "用户id长度必须介于 0 和 32 之间")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Length(min = 0, max = 32, message = "试卷id长度必须介于 0 和 32 之间")
	public String getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(String examinationId) {
		this.examinationId = examinationId;
	}

	@Length(min = 0, max = 1, message = "1课上做的 2课下做的长度必须介于 0 和 1 之间")
	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	@Length(min = 0, max = 11, message = "得分情况长度必须介于 0 和 11 之间")
	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

}