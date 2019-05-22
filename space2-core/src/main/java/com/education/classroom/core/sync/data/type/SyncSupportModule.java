package com.education.classroom.core.sync.data.type;

import java.util.Hashtable;
import java.util.Map;

import com.education.classroom.core.type.ModuleName;


/**
 * 同步支持的模块
 * 用途:用来定义从系统管理模块的数据下行同步到哪些模块，比如：汇金汇诚模块、财富模块
 * @Class Name SyncSupportModule
 * @author 张永生
 * @Create In 2015年12月4日
 */
public class SyncSupportModule {
	
	public static final String INDEX_LOAN = "1";
	
	public static final String INDEX_FORTUNE = "2";
	
	public static final String INDEX_REPORT = "3";

	private static Map<String, String> supportMap = new Hashtable<String, String>();

	public static Map<String, String> getSupportModule(){
		if(supportMap.isEmpty()){
			supportMap.put(INDEX_LOAN, ModuleName.MODULE_LOAN.value);
			supportMap.put(INDEX_FORTUNE, ModuleName.MODULE_FORTUNE.value);
			supportMap.put(INDEX_REPORT, ModuleName.MODULE_REPORT.value);
		}
		return supportMap;
	}
}
