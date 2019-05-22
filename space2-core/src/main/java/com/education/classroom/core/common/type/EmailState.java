package com.education.classroom.core.common.type;

import java.util.Hashtable;
import java.util.Map;

/***
 * email状态
 * 业务组使用的枚举类型
 * @Class Name
 * @author 赵红江
 * @Create In 2015年12月16日
 */
public enum EmailState {
	FSCG("0"), // 发送成功
	FSSB("1"), // 发送失败
	WFS("2"),  // 未发送
	DFS("3"),  // 待发送
	CX("4"),  // 撤销
	ZTFS("5");  // 暂停发送

	public final String value;

	private EmailState(String value) {
		this.value = value;
	}

	public static String getEmailState(String value) {
		return emailStateMap.get(value);
	}

	public static Map<String, String> emailStateMap = new Hashtable<String, String>();

	public static void initEmailState() {
		if (emailStateMap.isEmpty()) {
			emailStateMap.put(EmailState.FSCG.value, "发送成功");
			emailStateMap.put(EmailState.FSSB.value, "发送失败");
			emailStateMap.put(EmailState.WFS.value, "未发送");
			emailStateMap.put(EmailState.DFS.value, "待发送");
			emailStateMap.put(EmailState.CX.value, "撤销");
			emailStateMap.put(EmailState.ZTFS.value, "暂停发送");
		}
	}
}