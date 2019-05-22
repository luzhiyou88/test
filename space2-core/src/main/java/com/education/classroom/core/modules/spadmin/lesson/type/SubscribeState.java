/**
 * @Probject Name: chp-core
 * @Path: com.creditharmony.core.loan.typeLiveWith.java
 * @Create By 王彬彬
 * @Create In 2015年12月19日 上午11:26:09
 */
package com.education.classroom.core.modules.spadmin.lesson.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 订阅状态
 * @Class Name SubscribeState
 * @author zhujie
 * @Create In 2016年8月17日
 */
public enum SubscribeState {

	WDY("1","未订阅"),
	
	YDY("2","已订阅"),
	
	NDY("3","无法订阅");
	
	private static Map<String, SubscribeState> nameMap = new HashMap<String, SubscribeState>(
			10);
	private static Map<String, SubscribeState> codeMap = new HashMap<String, SubscribeState>(
			10);

	static {
		SubscribeState[] allValues = SubscribeState.values();
		for (SubscribeState obj : allValues) {
			nameMap.put(obj.getName(), obj);
			codeMap.put(obj.getCode(), obj);
		}
	}

	private String name;
	private String code;

	private SubscribeState(String code, String name) {
		this.name = name;
		this.code = code;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static SubscribeState parseByName(String name) {
		return nameMap.get(name);
	}

	public static SubscribeState parseByCode(String code) {
		return codeMap.get(code);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
