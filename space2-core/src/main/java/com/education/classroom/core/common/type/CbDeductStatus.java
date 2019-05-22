package com.education.classroom.core.common.type;

import java.util.Hashtable;
import java.util.Map;

/***
 * 划扣状态
 * 共通使用的枚举类型
 * @Class Name deductStatus
 * @author 周亮
 * @Create In 2015年12月16日
 */
public enum CbDeductStatus {
	dhk("0"), 	// 待划扣
	hkcg("1"), 	// 划扣成功
	hksb("2");	// 划扣失败
	
	public final String value;

	private CbDeductStatus(String value) {
		this.value = value;
	}

	public static String getUseFlag(String value) {
		return deductStatusMap.get(value);
	}

	public static Map<String, String> deductStatusMap = new Hashtable<String, String>();

	public static void initUseFlag() {
		if (deductStatusMap.isEmpty()) {
			deductStatusMap.put(CbDeductStatus.dhk.value, "待划扣");
			deductStatusMap.put(CbDeductStatus.hkcg.value, "划扣成功");
			deductStatusMap.put(CbDeductStatus.hksb.value, "划扣失败");
		}
	}
}
