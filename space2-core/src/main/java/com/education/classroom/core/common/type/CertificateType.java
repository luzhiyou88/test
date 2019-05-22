package com.education.classroom.core.common.type;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class Name CertificateType
 * @author 周亮
 * @Create In 2015年12月19日
 */
public enum CertificateType {
	SFZ("0", "身份证"),
	HKB("1", "户口簿"),
	HZ("2", "护照"),
	JGZ("3", "军官证"),
	SBZ("4", "士兵证"),
	GAJMLWNDTXZ("5", "港澳居民来往内地通行证"),
	TWTBLWNDTXZ("6", "台湾同胞来往内地通行证"),
	LSSFZ("7", "临时身份证"),
	WGRJLZ("8", "外国人居留证"),
	JCZ("9", "警官证"),
	OTHER("10", "其他证件");
	
	private static Map<String, CertificateType> nameMap = new HashMap<String, CertificateType>(20);
	private static Map<String, CertificateType> codeMap = new HashMap<String, CertificateType>(20);

	static {
		CertificateType[] allValues = CertificateType.values();
		for (CertificateType obj : allValues) {
			nameMap.put(obj.getName(), obj);
			codeMap.put(obj.getCode(), obj);
		}
	}

	private String name;
	private String code;

	private CertificateType(String code, String name) {
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

	public static CertificateType parseByName(String name) {
		return nameMap.get(name);
	}

	public static CertificateType parseByCode(String code) {
		return codeMap.get(code);
	}

	@Override
	public String toString() {
		return this.name;
	}

}
