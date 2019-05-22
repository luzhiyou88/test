package com.education.classroom.core.role.type;

import java.util.Hashtable;
import java.util.Map;

/**
 * 财富角色枚举
 * @Class Name FortuneRole
 * @author 张永生
 * @Create In 2016年1月7日
 */
public enum FortuneRole {
	
	DEPT_CEO("6410000001", "财富事业部-财富管理部总裁 "),    
	ASSISTANT_CEO("6410000002", "财富事业部-财富管理部副总裁 "),    
	DEPT_MANAGER("6410000003", "财富事业部-部门经理 "),    
	DEPT_ADMIN("6410000004", "财富事业部-部门管理员"),
	DATA_MANAGER("6410000005", "财富数据部-数据经理"),
	PERSONNEL_MANAGER("6410000006", "财富人管室-人事经理 "), 
	PERSONNEL_COMMISSIONER("6410000007", "财富人管室-人事专员"), 
	DATA_COMMISSIONER("6410000008", "财富数据部-数据专员"),
	PERSONNEL_ADMIN("6410000009", "财富人管室-管理员"),
	CENTER_MANAGER("6460000001", "财富中心-财富中心经理 "),
	CENTER_DATA_POSITION("6460000002", "财富中心-财富中心数据岗 "),
	SUB_MANAGER("6420000001", "财富分公司-分公司经理"),    
	SUB_ASSISTANT_MANAGER("6420000002", "财富分公司-分公司副经理"),    
	SUB_DATA_COMMISSIONER("6420000003", "财富分公司-分公司数据岗"),    
	CITY_MANAGER("6430000001", "财富-支公司经理"),    
	CITY_ASSISTANT_MANAGER("6430000002", "财富-支公司副经理"),    
	CITY_DATA_COMMISSIONER("6430000003", "财富-支公司数据岗"), 
	STORE_MANAGER("6440000001", "财富-营业部-门店经理"),    
	STORE_ASSISTANT_MANAGER("6440000002", "财富-营业部-门店副经理"),    
	LECTURER("6440000003", "财富-营业部-讲师"),    
	OFFICE_STAFF("6440000004", "财富-营业部-综合内勤"),    
	TEAM_MANAGER("6450000001", "财富-团队-团队经理"),    
	FINANCING_MANAGER("6450000002", "财富-团队-理财经理"),
	BRANCH_OFFICE_STAFF("6450000003", "财富-网点-网点综合内勤"),
	TELE_FINANCING_MANAGER("6510000001", "财富-电销理财专员"),
	TELE_TEAM_MANAGER("6510000002", "财富-电销理财团队主管"),
	TELE_STORE_MANAGER("6510000003", "财富-电销理财现场经理"),
	TELE_DATA_MANAGER("6510000004", "财富-电销数据主管"),
	TELE_DEPARTMENT_MANAGER("6510000005", "财富-电销部门负责人"),
	
	DATA_TRANSFER_MANAGER("6520000001", "财富-数据部-数据交割经理"),
	DATA_TRANSFER_COMMISSIONER("6520000002", "财富-数据部-数据交割经理");
	

	
	public final String id;     //对应角色表中的id字段
	public final String name;   //对应角色表中的name字段

	private FortuneRole(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Map<String, String> NameCodeMap = new Hashtable<String, String>();
	public static Map<String, String> CodeNameMap = new Hashtable<String, String>();

	public static Map<String, String> getNameCodeMap() {
		if(NameCodeMap.isEmpty()){
			NameCodeMap.put(FortuneRole.DEPT_CEO.name, FortuneRole.DEPT_CEO.id);
			NameCodeMap.put(FortuneRole.ASSISTANT_CEO.name, FortuneRole.ASSISTANT_CEO.id);
			NameCodeMap.put(FortuneRole.DEPT_MANAGER.name, FortuneRole.DEPT_MANAGER.id);
			NameCodeMap.put(FortuneRole.DEPT_ADMIN.name, FortuneRole.DEPT_ADMIN.id);
			NameCodeMap.put(FortuneRole.DATA_MANAGER.name, FortuneRole.DATA_MANAGER.id);
			NameCodeMap.put(FortuneRole.DATA_COMMISSIONER.name, FortuneRole.DATA_COMMISSIONER.id);
			NameCodeMap.put(FortuneRole.PERSONNEL_MANAGER.name, FortuneRole.PERSONNEL_MANAGER.id);
			NameCodeMap.put(FortuneRole.PERSONNEL_ADMIN.name, FortuneRole.PERSONNEL_ADMIN.id);
			NameCodeMap.put(FortuneRole.PERSONNEL_COMMISSIONER.name, FortuneRole.PERSONNEL_COMMISSIONER.id);
			NameCodeMap.put(FortuneRole.CENTER_MANAGER.name, FortuneRole.CENTER_MANAGER.id);
			NameCodeMap.put(FortuneRole.CENTER_DATA_POSITION.name, FortuneRole.CENTER_DATA_POSITION.id);
			NameCodeMap.put(FortuneRole.SUB_MANAGER.name, FortuneRole.SUB_MANAGER.id);
			NameCodeMap.put(FortuneRole.SUB_ASSISTANT_MANAGER.name, FortuneRole.SUB_ASSISTANT_MANAGER.id);
			NameCodeMap.put(FortuneRole.SUB_DATA_COMMISSIONER.name, FortuneRole.SUB_DATA_COMMISSIONER.id);
			NameCodeMap.put(FortuneRole.CITY_MANAGER.name, FortuneRole.CITY_MANAGER.id);
			NameCodeMap.put(FortuneRole.CITY_ASSISTANT_MANAGER.name, FortuneRole.CITY_ASSISTANT_MANAGER.id);
			NameCodeMap.put(FortuneRole.CITY_DATA_COMMISSIONER.name, FortuneRole.CITY_DATA_COMMISSIONER.id);
			NameCodeMap.put(FortuneRole.STORE_MANAGER.name, FortuneRole.STORE_MANAGER.id);
			NameCodeMap.put(FortuneRole.STORE_ASSISTANT_MANAGER.name, FortuneRole.STORE_ASSISTANT_MANAGER.id);
			NameCodeMap.put(FortuneRole.LECTURER.name, FortuneRole.LECTURER.id);
			NameCodeMap.put(FortuneRole.OFFICE_STAFF.name, FortuneRole.OFFICE_STAFF.id);
			NameCodeMap.put(FortuneRole.TEAM_MANAGER.name, FortuneRole.TEAM_MANAGER.id);
			NameCodeMap.put(FortuneRole.FINANCING_MANAGER.name, FortuneRole.FINANCING_MANAGER.id);
			NameCodeMap.put(FortuneRole.BRANCH_OFFICE_STAFF.name, FortuneRole.BRANCH_OFFICE_STAFF.id);
			NameCodeMap.put(FortuneRole.TELE_FINANCING_MANAGER.name, FortuneRole.TELE_FINANCING_MANAGER.id);
			NameCodeMap.put(FortuneRole.TELE_TEAM_MANAGER.name, FortuneRole.TELE_TEAM_MANAGER.id);
			NameCodeMap.put(FortuneRole.TELE_STORE_MANAGER.name, FortuneRole.TELE_STORE_MANAGER.id);
			NameCodeMap.put(FortuneRole.TELE_DATA_MANAGER.name, FortuneRole.TELE_DATA_MANAGER.id);
			NameCodeMap.put(FortuneRole.TELE_DEPARTMENT_MANAGER.name, FortuneRole.TELE_DEPARTMENT_MANAGER.id);
		}
		return NameCodeMap;
	}
	
	public static Map<String, String> getNameMap() {
		if(CodeNameMap.isEmpty()){
			CodeNameMap.put(FortuneRole.DEPT_CEO.id, FortuneRole.DEPT_CEO.name);
			CodeNameMap.put(FortuneRole.ASSISTANT_CEO.id, FortuneRole.ASSISTANT_CEO.name);
			CodeNameMap.put(FortuneRole.DEPT_MANAGER.id, FortuneRole.DEPT_MANAGER.name);
			CodeNameMap.put(FortuneRole.DEPT_ADMIN.id, FortuneRole.DEPT_ADMIN.name);
			CodeNameMap.put(FortuneRole.DATA_MANAGER.id, FortuneRole.DATA_MANAGER.name);
			CodeNameMap.put(FortuneRole.DATA_COMMISSIONER.id, FortuneRole.DATA_COMMISSIONER.name);
			CodeNameMap.put(FortuneRole.PERSONNEL_MANAGER.id, FortuneRole.PERSONNEL_MANAGER.name);
			CodeNameMap.put(FortuneRole.PERSONNEL_ADMIN.id, FortuneRole.PERSONNEL_ADMIN.name);
			CodeNameMap.put(FortuneRole.PERSONNEL_COMMISSIONER.id, FortuneRole.PERSONNEL_COMMISSIONER.name);
			CodeNameMap.put(FortuneRole.CENTER_MANAGER.id, FortuneRole.CENTER_MANAGER.name);
			CodeNameMap.put(FortuneRole.CENTER_DATA_POSITION.id, FortuneRole.CENTER_DATA_POSITION.name);
			CodeNameMap.put(FortuneRole.SUB_MANAGER.id, FortuneRole.SUB_MANAGER.name);
			CodeNameMap.put(FortuneRole.SUB_ASSISTANT_MANAGER.id, FortuneRole.SUB_ASSISTANT_MANAGER.name);
			CodeNameMap.put(FortuneRole.SUB_DATA_COMMISSIONER.id, FortuneRole.SUB_DATA_COMMISSIONER.name);
			CodeNameMap.put(FortuneRole.CITY_MANAGER.id, FortuneRole.CITY_MANAGER.name);
			CodeNameMap.put(FortuneRole.CITY_ASSISTANT_MANAGER.id, FortuneRole.CITY_ASSISTANT_MANAGER.name);
			CodeNameMap.put(FortuneRole.CITY_DATA_COMMISSIONER.id, FortuneRole.CITY_DATA_COMMISSIONER.name);
			CodeNameMap.put(FortuneRole.STORE_MANAGER.id, FortuneRole.STORE_MANAGER.name);
			CodeNameMap.put(FortuneRole.STORE_ASSISTANT_MANAGER.id, FortuneRole.STORE_ASSISTANT_MANAGER.name);
			CodeNameMap.put(FortuneRole.LECTURER.id, FortuneRole.LECTURER.name);
			CodeNameMap.put(FortuneRole.OFFICE_STAFF.id, FortuneRole.OFFICE_STAFF.name);
			CodeNameMap.put(FortuneRole.TEAM_MANAGER.id, FortuneRole.TEAM_MANAGER.name);
			CodeNameMap.put(FortuneRole.FINANCING_MANAGER.id, FortuneRole.FINANCING_MANAGER.name);
			CodeNameMap.put(FortuneRole.BRANCH_OFFICE_STAFF.id, FortuneRole.BRANCH_OFFICE_STAFF.name);
			CodeNameMap.put(FortuneRole.TELE_FINANCING_MANAGER.id, FortuneRole.TELE_FINANCING_MANAGER.name);
			CodeNameMap.put(FortuneRole.TELE_TEAM_MANAGER.id, FortuneRole.TELE_TEAM_MANAGER.name);
			CodeNameMap.put(FortuneRole.TELE_STORE_MANAGER.id, FortuneRole.TELE_STORE_MANAGER.name);
			CodeNameMap.put(FortuneRole.TELE_DATA_MANAGER.id, FortuneRole.TELE_DATA_MANAGER.name);
			CodeNameMap.put(FortuneRole.TELE_DEPARTMENT_MANAGER.id, FortuneRole.TELE_DEPARTMENT_MANAGER.name);
		}
		return CodeNameMap;
	}
}

