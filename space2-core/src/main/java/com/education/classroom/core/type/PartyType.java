package com.education.classroom.core.type;

import java.util.Hashtable;
import java.util.Map;


/**
 * 主体类型
 * @Class Name PartyType
 * @author 张永生
 * @Create In 2015年12月22日
 */
public enum PartyType {

	
	CAIFU("2", "财富"),      						       // 财富
	HUIJIN("3", "汇金"),             					   // 汇金
	HUICHENG("4", "汇诚"),         					   // 汇诚
	HUIMIN("9", "惠民");                                // 惠民
	
	
	public final String value;
	public final String type;
	
	public static Map<String, String> mappingMap = new Hashtable<String, String>();
	
	private PartyType(String value, String type) {
		this.value = value;
		this.type = type;
	}
	
	public static Map<String, String> getType(){
		if(mappingMap.isEmpty()){
			mappingMap.put(HUIJIN.value, HUIJIN.type);
			mappingMap.put(HUICHENG.value, HUICHENG.type);
			mappingMap.put(CAIFU.value, CAIFU.type);
			mappingMap.put(HUIMIN.value, HUIMIN.type);
		}
		return mappingMap;
	}
}
