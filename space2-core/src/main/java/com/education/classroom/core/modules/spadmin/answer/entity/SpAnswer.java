/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.answer.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 答案Entity
 * @author 路志友
 * @version 2016/08/18
 */
public class SpAnswer extends DataEntity<SpAnswer> {
	
	private static final long serialVersionUID = 1L;
	private String user;		// 答题者
	private String examinationId;		// 试卷id
	private String problemType;		// 问题类型  1主观题  2客观题（选择题）
	private String problemId;		// 所属试题
	private String answerContent;		// 答案内容
	private String answerType;		// 回答状态 1 回答正确 2回答错误 3未回答
	private String note;		// 笔记
	private String answerScore;		// 答案得分
	private String answerPath;		// 图片答案路径
	
	public SpAnswer() {
		super();
	}

	public SpAnswer(String id){
		super(id);
	}

	@Length(min=0, max=32, message="答题者长度必须介于 0 和 32 之间")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	@Length(min=0, max=32, message="试卷id长度必须介于 0 和 32 之间")
	public String getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(String examinationId) {
		this.examinationId = examinationId;
	}
	
	@Length(min=0, max=1, message="问题类型  1主观题  2客观题（选择题）长度必须介于 0 和 1 之间")
	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	
	@Length(min=0, max=32, message="所属试题长度必须介于 0 和 32 之间")
	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	
	@Length(min=0, max=512, message="答案内容长度必须介于 0 和 512 之间")
	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	@Length(min=0, max=1, message="回答状态 1 回答正确 2回答错误 3未回答长度必须介于 0 和 1 之间")
	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	
	@Length(min=0, max=500, message="笔记长度必须介于 0 和 500 之间")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Length(min=0, max=11, message="答案得分长度必须介于 0 和 11 之间")
	public String getAnswerScore() {
		return answerScore;
	}

	public void setAnswerScore(String answerScore) {
		this.answerScore = answerScore;
	}
	
	@Length(min=0, max=256, message="图片答案路径长度必须介于 0 和 256 之间")
	public String getAnswerPath() {
		return answerPath;
	}

	public void setAnswerPath(String answerPath) {
		this.answerPath = answerPath;
	}
	
}