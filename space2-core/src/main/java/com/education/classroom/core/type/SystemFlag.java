package com.education.classroom.core.type;

import java.util.Hashtable;
import java.util.Map;


/**
 * 系统标志
 * @Class Name SystemFlag
 * @author 张永生
 * @Create In 2016年1月8日
 */
public enum SystemFlag {
	HEAD(1,"惠民"),                                         // 惠民 
	FORTUNE(2, "财富"),      						   		// 财富
	LOAN(3, "汇金"),             					   		// 汇金
	CHECK(4, "汇诚"),         					   	        // 汇诚
	MANAGE(5, "运维"),                                      // 运维管理
	CARLOAN(6, "车借"),                                     // 车借
	OTHER(7, "其他"),
	SYS_MANAGE(9, "系统管理");                              // 系统管理
	
	public final int value;
	public final String type;
	
	public static Map<Integer, String> mappingMap = new Hashtable<Integer, String>();
	
	private SystemFlag(int value, String type) {
		this.value = value;
		this.type = type;
	}
	
	public static Map<Integer, String> getType(){
		if(mappingMap.isEmpty()){
			mappingMap.put(HEAD.value, HEAD.type);
			mappingMap.put(FORTUNE.value, FORTUNE.type);
			mappingMap.put(LOAN.value, LOAN.type);
			mappingMap.put(CHECK.value, CHECK.type);
			mappingMap.put(OTHER.value, OTHER.type);
			mappingMap.put(SYS_MANAGE.value, SYS_MANAGE.type);
		}
		return mappingMap;
	}
}
