package com.education.classroom.core.common.enums;

public enum DelFlagType {
	/**
	 *未删除
	 */
	NORMAL("0", "未删除"),
	/**
	 *已删除
	 */
	DELETE("1", "已删除");

	private final String value;
	private final String label;

	DelFlagType(String value, String label) {
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
