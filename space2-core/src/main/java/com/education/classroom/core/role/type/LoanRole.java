package com.education.classroom.core.role.type;

import java.util.Hashtable;
import java.util.Map;


/**
 * 汇金角色
 * @Class Name LoanRole
 * @author 张永生
 * @Create In 2016年1月7日
 */
public enum LoanRole {
	
	DEPT_MANAGER("6210000001", "汇金业务部-部门经理"), 
	BIZ_ASSISTANT("6210000002","汇金业务部-业务助理"), 
	AFTER_MANAGER("6210000003","汇金业务部-借后管理员"), 
	EMPLOYEE_RELATIONSHIP("6210000004","汇金业务部-员工关系"),
	
	
	PROVINCE_BIZ_MANAGER("6220000001","汇金省分公司-省分公司业管"), 
    SUB_CITY_BIZ_MANAGER("6220000002","汇金省分公司-城市支公司业管"), 
	SUB_CITY_MANAGER("6220000003","汇金省分公司-城市经理"), 
	
	PROVINCE_GENERAL_MANAGER("6220000004","汇金省分公司-省级分公司总经理"),
	PRODUCT_EXECUTIVE_DIRECTOR("6220000005","汇金省分公司-产品推动主管"),
	BIZ_EXECUTIVE_DIRECTOR("6220000006","汇金省分公司-业务推动主管"),
	CAR_LOAN_DIRECTOR("6220000007","汇金省分公司-车借主管"),
	MARKET_DIRECTOR("6220000008","汇金省分公司-市场主管"),
	OPERATE_CHECK_DIRECTOR("6220000009","汇金省分公司-运营稽核主管"),
	PERSONNEL_MANAGER("6220000010","汇金省分公司-人事行政经理"),
	FINANCE_MANAGER("6220000011","汇金省分公司-财务经理"),
	TRAIN_DIRECTOR("6220000012","汇金省分公司-培训主管"),
	HR_DIRECTOR("6220000013","汇金省分公司-招聘主管"),
	CHARGE_COMMISSIONER("6220000014","汇金省分公司-催收专员"),
	CHARGE_DIRECTOR("6220000015","汇金省分公司-催收主管"),
	
	PERSONNEL_DIRECTOR("6220000016","城市支公司-人事行政主管"),
	CITY_FINANCE_DIRECTOR("6220000017","城市支公司-财务主管"),
	CITY_TRAIN_TEACHER("6220000018","城市支公司-培训讲师"),
	CITY_HR_COMMISSIONER("6220000019","城市支公司-招聘专员"),
	CITY_COMPANY_MANAGER("6220000020","城市支公司-城市经理"),
	
	PROVINCE_GENERAL_SUB_MANAGER("6220000021","汇金省分公司-省级分公司副总经理"),
	PROVINCE_CREDIT_FEE_TRANSFER("6220000022","汇金省分公司-征信服务费对接"),
	CITY_CREDIT_FEE_TRANSFER("6220000023","城市支公司-征信服务费对接"),
	
	
	CITY_MANAGER("6230000001","汇金门店-城市经理"), 
	STORE_MANAGER("6230000002","汇金门店-门店经理"), 
	STORE_ASSISTANT("6230000003","汇金门店-门店副理"), 
	CUSTOMER_SERVICE("6230000004","汇金门店-客服"), 
	VISIT_PERSON("6230000005","汇金门店-外访"), 
	CREDIT_FEE_INPUT("6230000006","汇金门店-征信服务费录入"),
	
	TEAM_MANAGER("6240000001","汇金门店-团队经理"), 
	FINANCING_MANAGER("6240000002","汇金门店-客户经理"),
	STORE_HR_DIRECTOR("6240000003","汇金门店-人事行政专员"),
	STORE_SERVICE_MANAGER("6240000004","汇金门店-服务经理"),
	DIRECT_URGE_ADMIN("6240000005","直催管理专员"),
	DIRECT_URGE_COMMISSIONER("6240000006","直催专员"),
	DIRECT_URGE_ADMIN_CHIEF("6240000007","直催管理主管"),
	DIRECT_URGE_CHIEF("6240000008","直催主管"),
	DIRECT_URGE_ADMIN_MANAGER("6240000009","直催管理经理"),
	STORE_FINANCING_MANAGER("6240000010","汇金门店-客户经理"),
	
	MOBILE_SALE_MASTER("6250000001","电销总监"),
	MOBILE_SALE_MANAGER("6250000002","电销现场经理"),
	MOBILE_SALE_RECORDER("6250000003","电销录单专员"),
	MOBILE_SALE_TEAM_MANAGER("6250000004","电销团队主管"),
	MOBILE_SALE_COMMISSIONER("6250000005","电销专员"),
	MOBILE_SALE_DATA_COMMISSIONER("6250000006","电销数据专员"),
	MOBILE_SALE_CUSTOMER_SERVICE("6250000007","电销客服"),
	
	ELECTRIC_MANAGER("6260000001","电催团队经理"),
	ELECTRIC_COMMISSIONER("6260000002","电催专员"),
	
	DATA_ADMIN("6270000001","汇金-数据部-数据管理员"),
	DATA_DIRECTOR("6270000002","汇金-数据部-总监"),
	DATA_MANAGER("6270000003","汇金-数据部-经理"),
	DATA_CHIEF("6270000004","汇金-数据部-主管"),
	DATA_DEPUTY_MANAGER("6270000005","汇金-数据部-副经理"),
	DATA_OVERDUE_ANALYSIS("6270000006","汇金-数据部-逾期数据分析专员"),
	DATA_BIZ_ANALYSIS("6270000007","汇金-数据部-业绩数据分析专员"),
	DATA_EMPLOYEE_RELATIONSHIP("6270000008","汇金-数据部-员工关系"),
	DATA_MASTER("6270000009","汇金-数据部-数据部主管"),
	DATA_ANALYZE_MEMBER("6270000010","汇金-数据部-数据分析专员"),
	
	TELE_REMIND_DATA_COMMISSIONER("6280000001","汇金-电催数据专员"),
	TELE_REMIND_COMMISSIONER("6280000002","汇金-电催专员"),
	
	EVALUATOR("6600000001","车借-评估师"),
	FACE_AUDIT("6600000002","车借-面审"),
	CAR_RECEIVE_MONEY("6600000003","车借-车借回款岗"),
	CAR_MONEY_STATISTICS("6600000004","车借-款项统计专员"),
	CAR_ASSISTANT("6600000005","车借-门店副理"),
	CAR_MANAGER("6600000006","车借-门店经理"),
	CAR_CITYMANAGER("6600000007","车借-城市经理"),
	CITY_MECHANICS("6600000008","城市支公司-机械师"),
	CAR_SALE_DIRECTER("6600000009","车借-车借总监"),
	CAR_CITY_MONEY_RETURN("6600000010","城市支公司-车借-回款岗"),
	CAR_CITY_SUB_MANAGER("6600000011","城市支公司-车借-城市副经理"),
	CAR_CITY_CAR_CHECKER("6600000012","城市支公司-车借-评估师"),
	CAR_LOAN_MANAGER("6600000013","车借部-车借部经理"),
	CAR_LOAN_MASTER("6600000014","车借部-车借主管"),
	LOAN_PERSONNEL_ADMIN("6600000015","人管室-管理员");
	
	
	public final String id;
	public final String name;  //对应角色表中的enname

	private LoanRole(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Map<String, String> nameCodeMap = new Hashtable<String, String>();

	public static Map<String, String> getNameCodeMap() {
		if(nameCodeMap.isEmpty()){
			nameCodeMap.put(LoanRole.DEPT_MANAGER.name, LoanRole.DEPT_MANAGER.id);
			nameCodeMap.put(LoanRole.BIZ_ASSISTANT.name, LoanRole.BIZ_ASSISTANT.id);
			nameCodeMap.put(LoanRole.PROVINCE_BIZ_MANAGER.name, LoanRole.PROVINCE_BIZ_MANAGER.id);
			nameCodeMap.put(LoanRole.SUB_CITY_BIZ_MANAGER.name, LoanRole.SUB_CITY_BIZ_MANAGER.id);
			nameCodeMap.put(LoanRole.SUB_CITY_MANAGER.name, LoanRole.SUB_CITY_MANAGER.id);
			nameCodeMap.put(LoanRole.CITY_MANAGER.name, LoanRole.CITY_MANAGER.id);
			nameCodeMap.put(LoanRole.STORE_MANAGER.name, LoanRole.STORE_MANAGER.id);
			nameCodeMap.put(LoanRole.STORE_ASSISTANT.name, LoanRole.STORE_ASSISTANT.id);
			nameCodeMap.put(LoanRole.CUSTOMER_SERVICE.name, LoanRole.CUSTOMER_SERVICE.id);
			nameCodeMap.put(LoanRole.VISIT_PERSON.name, LoanRole.VISIT_PERSON.id);
			nameCodeMap.put(LoanRole.TEAM_MANAGER.name, LoanRole.TEAM_MANAGER.id);
			nameCodeMap.put(LoanRole.FINANCING_MANAGER.name, LoanRole.FINANCING_MANAGER.id);
			nameCodeMap.put(LoanRole.MOBILE_SALE_MASTER.name, LoanRole.MOBILE_SALE_MASTER.id);
			nameCodeMap.put(LoanRole.MOBILE_SALE_MANAGER.name, LoanRole.MOBILE_SALE_MANAGER.id);
			nameCodeMap.put(LoanRole.MOBILE_SALE_RECORDER.name, LoanRole.MOBILE_SALE_RECORDER.id);
			nameCodeMap.put(LoanRole.MOBILE_SALE_TEAM_MANAGER.name, LoanRole.MOBILE_SALE_TEAM_MANAGER.id);
			nameCodeMap.put(LoanRole.MOBILE_SALE_COMMISSIONER.name, LoanRole.MOBILE_SALE_COMMISSIONER.id);
			
			nameCodeMap.put(LoanRole.PROVINCE_GENERAL_MANAGER.name, LoanRole.PROVINCE_GENERAL_MANAGER.id);
			nameCodeMap.put(LoanRole.PRODUCT_EXECUTIVE_DIRECTOR.name, LoanRole.PRODUCT_EXECUTIVE_DIRECTOR.id);
			nameCodeMap.put(LoanRole.BIZ_EXECUTIVE_DIRECTOR.name, LoanRole.BIZ_EXECUTIVE_DIRECTOR.id);
			nameCodeMap.put(LoanRole.CAR_LOAN_DIRECTOR.name, LoanRole.CAR_LOAN_DIRECTOR.id);
			nameCodeMap.put(LoanRole.MARKET_DIRECTOR.name, LoanRole.MARKET_DIRECTOR.id);
			nameCodeMap.put(LoanRole.OPERATE_CHECK_DIRECTOR.name, LoanRole.OPERATE_CHECK_DIRECTOR.id);
			nameCodeMap.put(LoanRole.PERSONNEL_MANAGER.name, LoanRole.PERSONNEL_MANAGER.id);
			nameCodeMap.put(LoanRole.FINANCE_MANAGER.name, LoanRole.FINANCE_MANAGER.id);
			nameCodeMap.put(LoanRole.TRAIN_DIRECTOR.name, LoanRole.TRAIN_DIRECTOR.id);
			nameCodeMap.put(LoanRole.HR_DIRECTOR.name, LoanRole.HR_DIRECTOR.id);
			nameCodeMap.put(LoanRole.CHARGE_COMMISSIONER.name, LoanRole.CHARGE_COMMISSIONER.id);
			nameCodeMap.put(LoanRole.CHARGE_DIRECTOR.name, LoanRole.CHARGE_DIRECTOR.id);
			nameCodeMap.put(LoanRole.PERSONNEL_DIRECTOR.name, LoanRole.PERSONNEL_DIRECTOR.id);
			nameCodeMap.put(LoanRole.CITY_FINANCE_DIRECTOR.name, LoanRole.CITY_FINANCE_DIRECTOR.id);
			nameCodeMap.put(LoanRole.CITY_TRAIN_TEACHER.name, LoanRole.CITY_TRAIN_TEACHER.id);
			nameCodeMap.put(LoanRole.CITY_HR_COMMISSIONER.name, LoanRole.CITY_HR_COMMISSIONER.id);
			nameCodeMap.put(LoanRole.CITY_MECHANICS.name, LoanRole.CITY_MECHANICS.id);
			nameCodeMap.put(LoanRole.STORE_HR_DIRECTOR.name, LoanRole.STORE_HR_DIRECTOR.id);
			nameCodeMap.put(LoanRole.STORE_SERVICE_MANAGER.name, LoanRole.STORE_SERVICE_MANAGER.id);
			nameCodeMap.put(LoanRole.CITY_COMPANY_MANAGER.name, LoanRole.CITY_COMPANY_MANAGER.id);
			nameCodeMap.put(LoanRole.CAR_CITY_MONEY_RETURN.name, LoanRole.CAR_CITY_MONEY_RETURN.id);
			nameCodeMap.put(LoanRole.CAR_LOAN_MANAGER.name, LoanRole.CAR_LOAN_MANAGER.id);
			nameCodeMap.put(LoanRole.LOAN_PERSONNEL_ADMIN.name, LoanRole.LOAN_PERSONNEL_ADMIN.id);
		}
		return nameCodeMap;
	}
}
