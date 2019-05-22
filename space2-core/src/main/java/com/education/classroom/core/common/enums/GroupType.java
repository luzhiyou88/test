package com.education.classroom.core.common.enums;

public enum GroupType {
	/**
	 * 班级组
	 */
	SPCLASS("0", "班级组"),
	/**
	 *校内组
	 */
	SPSCHOOL("1", "校内组"),

	/**
	 * 平台组
	 */
	SPPLAT("2", "平台组");

	private final String value;
	private final String label;

	GroupType(String value, String label) {
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
