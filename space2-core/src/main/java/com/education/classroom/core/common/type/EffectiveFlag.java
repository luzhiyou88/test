package com.education.classroom.core.common.type;

import java.util.Hashtable;
import java.util.Map;

/***
 * 有效标志
 * 共通使用的枚举类型
 * @Class Name EffectiveFlag
 * @author 周亮
 * @Create In 2015年12月16日
 */
public enum EffectiveFlag {
	
	wx("0"), // 无效
	yx("1"); // 有效
	
	public final String value;

	private EffectiveFlag(String value) {
		this.value = value;
	}

	public static String getEffectiveFlag(String value) {
		return EffectiveFlagMap.get(value);
	}

	public static Map<String, String> EffectiveFlagMap = new Hashtable<String, String>();

	public static void initEffectiveFlag() {
		if (EffectiveFlagMap.isEmpty()) {
			EffectiveFlagMap.put(EffectiveFlag.wx.value, "无效");
			EffectiveFlagMap.put(EffectiveFlag.yx.value, "有效");
		}
	}
}
