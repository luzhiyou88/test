/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.problems.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.modules.spadmin.material.entity.SpMaterials;
import com.education.classroom.core.persistence.DataEntity;

/**
 * 试题管理Entity
 * 
 * @author 尚军伟
 * @version 2016/08/18
 */
public class SpProblems extends DataEntity<SpProblems> {

	private static final long serialVersionUID = 8331403232236934379L;
	private String examinationId; // 所属试卷
	private String problemType; // 题目类型 字典 1单选 2多选 3不定项 4材料题
	private String stem; // 题干
	private String number; // 题目编号
	private String textType; // 文本类型 1文字
	private String optionA; // option_a
	private String optionAPath; // 图片路径
	private String optionB; // option_b
	private String optionBPath; // 图片路径
	private String optionC; // option_c
	private String optionCPath; // 图片路径
	private String optionD; // option_d
	private String optionDPath; // 图片路径
	private String answer; // 正确答案
	private String analysis; // 解析
	private String ismaterial; // 是否是材料题1材料题 2非材料题
	private String materialId; // 材料id
	private String problemScore; // 分值
	private String stemPath; // 图片题目路径
	private String examinationName; // 所属试卷名称
	private SpMaterials material; // 试题材料

	// 以下为临时属性
	private String note; // 笔记
	private String answerContent; // 答题内容
	private String answerType; // 答题类型：1-正确，2-错误，3-未答

	public SpProblems() {
		super();
	}

	public SpProblems(String id) {
		super(id);
	}

	@Length(min = 0, max = 32, message = "所属试卷长度必须介于 0 和 32 之间")
	public String getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(String examinationId) {
		this.examinationId = examinationId;
	}

	@Length(min = 0, max = 1, message = "题目类型 字典 1单选 2多选 3不定项 4材料题长度必须介于 0 和 1 之间")
	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	@Length(min = 1, max = 512, message = "题干长度必须介于 1 和 512之间")
	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	@Length(min = 0, max = 11, message = "题目编号长度必须介于 0 和 11 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Length(min = 0, max = 1, message = "文本类型 1文字长度必须介于 0 和 1 之间")
	public String getTextType() {
		return textType;
	}

	public void setTextType(String textType) {
		this.textType = textType;
	}

	@Length(min = 0, max = 200, message = "option_a长度必须介于 0 和 200 之间")
	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	@Length(min = 1, max = 255, message = "图片路径长度必须介于 1 和 255 之间")
	public String getOptionAPath() {
		return optionAPath;
	}

	public void setOptionAPath(String optionAPath) {
		this.optionAPath = optionAPath;
	}

	@Length(min = 0, max = 200, message = "option_b长度必须介于 0 和 200 之间")
	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	@Length(min = 1, max = 255, message = "图片路径长度必须介于 1 和 255 之间")
	public String getOptionBPath() {
		return optionBPath;
	}

	public void setOptionBPath(String optionBPath) {
		this.optionBPath = optionBPath;
	}

	@Length(min = 0, max = 200, message = "option_c长度必须介于 0 和 200 之间")
	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	@Length(min = 1, max = 255, message = "图片路径长度必须介于 1 和 255 之间")
	public String getOptionCPath() {
		return optionCPath;
	}

	public void setOptionCPath(String optionCPath) {
		this.optionCPath = optionCPath;
	}

	@Length(min = 0, max = 200, message = "option_d长度必须介于 0 和 200 之间")
	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	@Length(min = 1, max = 255, message = "图片路径长度必须介于 1 和 255 之间")
	public String getOptionDPath() {
		return optionDPath;
	}

	public void setOptionDPath(String optionDPath) {
		this.optionDPath = optionDPath;
	}

	@Length(min = 0, max = 512, message = "正确答案长度必须介于 0 和 512 之间")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Length(min = 1, max = 500, message = "解析长度必须介于 1 和 500 之间")
	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	@Length(min = 0, max = 1, message = "是否是材料题1材料题 2非材料题长度必须介于 0 和 1 之间")
	public String getIsmaterial() {
		return ismaterial;
	}

	public void setIsmaterial(String ismaterial) {
		this.ismaterial = ismaterial;
	}

	@Length(min = 0, max = 32, message = "材料id长度必须介于 0 和 32 之间")
	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	@Length(min = 0, max = 11, message = "分值长度必须介于 0 和 11 之间")
	public String getProblemScore() {
		return problemScore;
	}

	public void setProblemScore(String problemScore) {
		this.problemScore = problemScore;
	}

	@Length(min = 1, max = 256, message = "图片题目路径长度必须介于 1 和 256 之间")
	public String getStemPath() {
		return stemPath;
	}

	public void setStemPath(String stemPath) {
		this.stemPath = stemPath;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		if (StringUtils.isEmpty(answerContent)) {
			this.answerContent = "";
		} else {
			this.answerContent = answerContent;

		}
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public SpMaterials getMaterial() {
		return material;
	}

	public void setMaterial(SpMaterials material) {
		this.material = material;
	}

}