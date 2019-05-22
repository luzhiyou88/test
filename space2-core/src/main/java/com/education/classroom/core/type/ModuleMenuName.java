package com.education.classroom.core.type;

import java.util.Map;
import java.util.TreeMap;

/**
 * 模块菜单的名字
 * @Class Name ModuleMenuName
 * @author 张永生
 * @Create In 2015年11月4日
 */
public enum ModuleMenuName {
	
	MENU_SYS_MANAGE("MENU_SYS_MANAGE"), //系统管理菜单
	MENU_LOAN("MENU_LOAN"),             //汇金系统菜单
	MENU_APPROVE("MENU_APPROVE"),       //汇诚系统菜单
	MENU_FORTUNE("MENU_FORTUNE"),       //财富系统菜单
	MENU_REPORT("MENU_REPORT"),         //报表系统菜单
	MENU_COMBIZ("MENU_COMBIZ"),         //共通业务系统菜单
	MENU_FINANCE("MENU_FINANCE"),       //清算系统菜单
	MENU_JOB("MENU_JOB"),               //批处理系统菜单
	MENU_LOANJOB("MENU_LOANJOB"),       //汇金批处理系统菜单
	MENU_FORTUNEJOB("MENU_FORTUNEJOB"), //财富批处理系统菜单
	MENU_APPROVEJOB("MENU_APPROVEJOB"), //汇诚批处理系统菜单
	MENU_REPORTJOB("MENU_REPORTJOB"),   //报表批处理系统菜单
	MENU_PORTAL("MENU_PORTAL"),         //portal系统菜单
	MENU_FOUNDS("MODULE_FOUNDS");       //资金托管系统菜单
	
	public final String value;

	private ModuleMenuName(String value) {
		this.value = value;
	}

	public static String getMenuName(String value) {
		return menuNameMap.get(value);
	}
	
	public static Map<String, String> menuNameMap = new TreeMap<String, String>();

	public static void initMenuName() {
		if (menuNameMap.isEmpty()) {
			menuNameMap.put(ModuleMenuName.MENU_SYS_MANAGE.value, "系统管理菜单");
			menuNameMap.put(ModuleMenuName.MENU_LOAN.value, "汇金系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_APPROVE.value, "汇诚系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_FORTUNE.value, "财富系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_REPORT.value, "报表系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_COMBIZ.value, "共通业务系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_FINANCE.value, "清算系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_JOB.value, "批处理系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_LOANJOB.value, "汇金批处理系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_FORTUNEJOB.value, "财富批处理系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_APPROVEJOB.value, "汇诚批处理系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_REPORTJOB.value, "报表批处理系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_PORTAL.value, "portal系统菜单");
			menuNameMap.put(ModuleMenuName.MENU_FOUNDS.value, "资金托管系统菜单");
		}
	}
	
}
