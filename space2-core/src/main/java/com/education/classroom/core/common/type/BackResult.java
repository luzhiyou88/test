package com.education.classroom.core.common.type;

import java.util.Hashtable;
import java.util.Map;

/***
 * 回盘结果
 * 
 * @Class Name
 * @author 赵红江
 * @Create In 2015年12月16日
 */
public enum BackResult {
	SUCESS("1"), // 成功
	FAIL("2"), // 失败
	DELLING("3"); //处理中

	public static Map<String, String> backResultMap = new Hashtable<String, String>();
	static {
		backResultMap.put(BackResult.SUCESS.value, "成功");
		backResultMap.put(BackResult.FAIL.value, "失败");
		backResultMap.put(BackResult.DELLING.value, "处理中");
	}
	public final String value;

	private BackResult(String value) {
		this.value = value;
	}

	public static String getBackResult(String value) {
		return backResultMap.get(value);
	}
}