package com.education.classroom.core.type;

import java.util.Hashtable;
import java.util.Map;

/**
 * 模块名称
 * @Class Name ModuleName
 * @author 张永生
 * @Create In 2015年11月4日
 */
public enum ModuleName {
	
	MODULE_SYS_MANAGE("MODULE_SYS_MANAGE"), //系统管理
	MODULE_LOAN("MODULE_LOAN"),             //汇金系统
	MODULE_APPROVE("MODULE_APPROVE"),       //汇诚系统
	MODULE_FORTUNE("MODULE_FORTUNE"),       //财富系统
	MODULE_REPORT("MODULE_REPORT"),         //报表系统
	MODULE_COMBIZ("MODULE_COMBIZ"),         //共通业务模块
	MODULE_FINANCE("MODULE_FINANCE"),       //清算系统
	MODULE_JOB("MODULE_JOB"),       		//批处理系统
	MODULE_LOANJOB("MODULE_LOANJOB"),       //汇金批处理系统
	MODULE_FORTUNEJOB("MODULE_FORTUNEJOB"), //财富批处理系统
	MODULE_APPROVEJOB("MODULE_APPROVEJOB"), //汇诚批处理系统
	MODULE_REPORTJOB("MODULE_REPORTJOB"),   //报表批处理系统
	MODULE_PORTAL("MODULE_PORTAL"),         //portal系统
	MODULE_FOUNDS("MODULE_FOUNDS");       //资金托管
	
	public final String value;

	private ModuleName(String value) {
		this.value = value;
	}

	public static String getModuleName(String value) {
		return moduleNameMap.get(value);
	}
	
	public static Map<String, String> moduleNameMap = new Hashtable<String, String>();
	/**
	 * 需要数据同步的模块
	 */
	public static Map<String, String> supportMap = new Hashtable<String, String>();

	public static Map<String, String> getModuleName() {
		if(moduleNameMap.isEmpty()){
			moduleNameMap.put(ModuleName.MODULE_SYS_MANAGE.value, "系统管理");
			moduleNameMap.put(ModuleName.MODULE_LOAN.value, "汇金系统");
			moduleNameMap.put(ModuleName.MODULE_APPROVE.value, "汇诚系统");
			moduleNameMap.put(ModuleName.MODULE_FORTUNE.value, "财富系统");
			moduleNameMap.put(ModuleName.MODULE_REPORT.value, "报表系统");
			moduleNameMap.put(ModuleName.MODULE_COMBIZ.value, "共通业务系统");
			moduleNameMap.put(ModuleName.MODULE_FINANCE.value, "清算系统");
			moduleNameMap.put(ModuleName.MODULE_JOB.value, "批处理系统");
			moduleNameMap.put(ModuleName.MODULE_LOANJOB.value, "汇金批处理系统");
			moduleNameMap.put(ModuleName.MODULE_FORTUNEJOB.value, "财富批处理系统");
			moduleNameMap.put(ModuleName.MODULE_APPROVEJOB.value, "汇诚批处理系统");
			moduleNameMap.put(ModuleName.MODULE_REPORTJOB.value, "报表批处理系统");
			moduleNameMap.put(ModuleName.MODULE_PORTAL.value, "portal系统");
			moduleNameMap.put(ModuleName.MODULE_FOUNDS.value, "资金托管系统");
		}
		return moduleNameMap;
	}
	
	/**
	 * 获取需要数据同步的模块
	 * 2015年12月9日
	 * By 张永生
	 */
	public static Map<String, String> getSupportMolule() {
		if(supportMap.isEmpty()){
			supportMap.put(ModuleName.MODULE_LOAN.value, "汇金汇诚");
			supportMap.put(ModuleName.MODULE_FORTUNE.value, "财富系统");
		}
		return supportMap;
	}
	
}
