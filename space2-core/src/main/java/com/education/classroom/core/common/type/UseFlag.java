package com.education.classroom.core.common.type;

import java.util.Hashtable;
import java.util.Map;

/***
 * 使用标识
 * 共通使用的枚举类型
 * @Class Name UseFlag
 * @author 周亮
 * @Create In 2015年12月16日
 */
public enum UseFlag {
	TY("0"), // 停用
	QY("1"); // 启用
	
	public final String value;

	private UseFlag(String value) {
		this.value = value;
	}

	public static String getUseFlag(String value) {
		return UseFlagMap.get(value);
	}

	public static Map<String, String> UseFlagMap = new Hashtable<String, String>();

	public static void initUseFlag() {
		if (UseFlagMap.isEmpty()) {
			UseFlagMap.put(UseFlag.TY.value, "停用");
			UseFlagMap.put(UseFlag.QY.value, "启用");
		}
	}
}
