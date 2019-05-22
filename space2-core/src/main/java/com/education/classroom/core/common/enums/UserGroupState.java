package com.education.classroom.core.common.enums;

public enum UserGroupState {
	/**
	 * 被邀请
	 */
	YAOQING("0", "被邀请"),
	/**
	 * 申请待审核
	 */
	SHENQING("1", "申请待审核"),

	/**
	 * 已加入
	 */
	JOINING("3", "已加入"),

	/**
	 * 拒绝邀请
	 */
	JJYAOQING("4", "拒绝邀请"),

	/**
	 * 拒绝申请
	 */
	JJSHENQING("5", "拒绝申请"),

	/**
	 * 已退出
	 */
	QUIT("6", "已退出"),

	/**
	 * 被删除
	 */
	DEL("7", "被删除");

	private final String value;
	private final String label;

	UserGroupState(String value, String label) {
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
