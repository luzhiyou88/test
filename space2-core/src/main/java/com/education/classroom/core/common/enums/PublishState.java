package com.education.classroom.core.common.enums;

public enum PublishState {
	/**
	 * 未发布
	 */
	WFABU("0", "未发布"),
	/**
	 * 发布待审核
	 */
	FABU("1", "发布待审核"),

	/**
	 * 审核未通过
	 */
	WTGSHENHE("2", "审核未通过"),

	/**
	 * 审核通过
	 */
	TGSHENHE("3", "审核通过");

	private final String value;
	private final String label;

	PublishState(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}
