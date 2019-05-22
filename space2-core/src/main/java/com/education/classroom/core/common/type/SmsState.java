package com.education.classroom.core.common.type;

import java.util.Hashtable;
import java.util.Map;

/***
 * 
 * 短信状态
 * @Class Name
 * @author 赵红江
 * @Create In 2015年12月16日
 */
public enum SmsState {
	
	FSCG("0"), // 发送成功
	FSSB("1"), // 发送失败
	DFS("2"),  // 待发送
	WFS("3"),  // 未发送
	TY("4"),   // 停用
	YFS("5");  // 已发送

	public final String value;

	private SmsState(String value) {
		this.value = value;
	}

	public static String getSmsState(String value) {
		return smsStateMap.get(value);
	}

	public static Map<String, String> smsStateMap = new Hashtable<String, String>();

	public static void initSmsState() {
		if (smsStateMap.isEmpty()) {
			smsStateMap.put(SmsState.FSCG.value, "发送成功");
			smsStateMap.put(SmsState.FSSB.value, "发送失败");
			smsStateMap.put(SmsState.DFS.value, "待发送");
			smsStateMap.put(SmsState.WFS.value, "未发送");
			smsStateMap.put(SmsState.TY.value, "停用");
			smsStateMap.put(SmsState.YFS.value, "已发送");
		}
	}
}