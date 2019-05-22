/**
 * @Probject Name: chp-core
 * @Path: com.creditharmony.core.loan.typeLiveWith.java
 * @Create By 王彬彬
 * @Create In 2015年12月19日 上午11:26:09
 */
package com.education.classroom.core.modules.spadmin.course.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 套课类型
 * @Class Name CourseType
 * @author zhujie
 * @Create In 2016年8月6日
 */
public enum CourseType {

	HY("1","会员"),
	
	SF("2","收费");
	
	
	private static Map<String, CourseType> nameMap = new HashMap<String, CourseType>(
			10);
	private static Map<String, CourseType> codeMap = new HashMap<String, CourseType>(
			10);

	static {
		CourseType[] allValues = CourseType.values();
		for (CourseType obj : allValues) {
			nameMap.put(obj.getName(), obj);
			codeMap.put(obj.getCode(), obj);
		}
	}

	private String name;
	private String code;

	private CourseType(String code, String name) {
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

	public static CourseType parseByName(String name) {
		return nameMap.get(name);
	}

	public static CourseType parseByCode(String code) {
		return codeMap.get(code);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
