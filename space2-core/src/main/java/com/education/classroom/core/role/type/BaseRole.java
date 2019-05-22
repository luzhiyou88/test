package com.education.classroom.core.role.type;

/**
 * 惠民角色枚举,即总部角色枚举
 * 用于定义角色表中初始化的角色
 * @Class Name BaseRole
 * @author 张永生
 * @Create In 2016年1月7日
 */
public enum BaseRole {

	LOANER_DEPT_MANAGER("6110000001","惠民-借款人服务部-部门经理"),
	LOANER_DATA_ANALYSIS("6110000002","惠民-借款人服务部-数据分析专员"),
	DATA_ANALYSIS_LEADER("6110000003","惠民-借款人服务部-数据分析组长"),
	REPAYMENT_COMMISSIONER("6110000004","惠民-借款人服务部-还款管理专员"),
	REPAYMENT_LEADER("6110000005","惠民-借款人服务部-还款管理组长"),
	CAR_COMMISSIONER("6110000006","惠民-借款人服务部-车借专员"),
	CAR_LEADER("6110000007","惠民-借款人服务部-车借组长"),
	MONEY_STATISTICIAN("6110000008","惠民-借款人服务部-款项统计专员"),
	MONEY_LEADER("6110000009","惠民-借款人服务部-款项统计组长"),
	LOANER_CONTRACT_APPROVER("6110000010","惠民-借款人服务部-合同审核专员"),
	CONTRACT_APPROVE_LEADER("6110000011","惠民-借款人服务部-合同审核组长"),
	LOANER_CONTRACT_MAKER("6110000012","惠民-借款人服务部-合同制作专员"),
	CONTRACT_MAKE_LEADER("6110000013","惠民-借款人服务部-合同制作组长"),
	LOANER_DEPT_ADMIN("6110000014","惠民-借款人服务部-部门管理员"),
	LOANER_STATISTIC_MEMBER("6110000015","惠民-借款人服务部-数据统计专员"),
	CAR_CONTRACT_MAKE_MEMBER("6110000016","惠民-借款人服务部-车借合同制作专员"),
	CAR_CONTRACT_APPROVE_MEMBER("6110000017","惠民-借款人服务部-车借合同审核专员"),
	CAR_SALE_DIRECTER("6110000018","惠民-借款人服务部-车借主管"),
	PAYMENT_CUSTOMER_COMMISSIONER("6110000019","惠民-借款人服务部-还款管理客服专员"),
	CONTRACT_APPROVE_COMMISSIONER("6110000020","惠民-借款人服务部-合同审核客户服务专员"),
	CARHOURSE_MANAGER("6110000021","惠民-借款人服务部-车房业务主管"),
	LOANER_DEPT_MASTER("6110000022","惠民_借款人服务部_部门总监"),
	LOANER_CONTRACT_APPROVE_MASTER("6110000023","惠民-借款人服务部-合同审核主管"),
	LOANER_BIZ_UPGRADE_LEADER("6110000024","惠民-借款人服务部-业务提升管理组长"),
	LOANER_CONTRACT_MASTER("6110000025","惠民-借款人服务部-合同制作主管"),
	LOANER_STATISTIC_LEADER("6110000026","惠民-借款人服务部-数据统计管理组长"),
	LOANER_CAR_COMMISSIONER("6110000027", "惠民-借款人服务部-车借专员"),
	LOANER_CAR_MANAGER_ASSISTANT("6110000028", "惠民-借款人服务部-车房业务经理助理"),
	LOANER_BIZ_UPGRADE_COMMISSIONER("6110000029", "惠民-借款人服务部-业务提升管理专员"),
	
	/*出借人服务部*/
	LENDER_DEPT_MANAGER("6120000001","惠民-出借人服务部-部门总监"),
	COMPRE_BIZ_LEADER("6120000002","惠民-出借人服务部-综合业务主管"),
	RECEIVE_MONEYER("6120000003","惠民-出借人服务部-回款专员"),
	LENDER_DATA_ANALYSIS("6120000004","惠民-出借人服务部-数据分析员"),
	CONTRACT_AUDIT_MASTER("6120000005","惠民-出借人服务部-合同审核主管"),
	CONTRACT_AUDIT_LEADER("6120000006","惠民-出借人服务部-合同审核业务组长"),
	CONTRACT_AUDIT_DEPUTY_LEADER("6120000007","惠民-出借人服务部-合同审核业务副组长"),
	LENDER_CONTRACT_APPROVER("6120000008","惠民-出借人服务部-合同审核专员"),
	CONTRACT_MAKER_LEADER("6120000009","惠民-出借人服务部-合同制作主管"),
	CONTRACT_MAKER("6120000010","惠民-出借人服务部-合同制作专员"),
	FILE_MAKE_LEADER("6120000011","惠民-出借人服务部-文件制作主管"),	
	FILE_MAKE_TEAM_LEADER("6120000012","惠民-出借人服务部-文件制作组长"),	
	FILE_MAKE_DEPUTY_LEADER("6120000013","惠民-出借人服务部-文件制作副组长"),
	FILE_MAKE_MAKER("6120000014","惠民-出借人服务部-文件制作专员"),	
	BIZ_TRANSFER_LEADER("6120000015","惠民-出借人服务部-业务对接主管"),
	BIZ_TRANSFER_TEAM_LEADER("6120000016","惠民-出借人服务部-业务对接组长"),
	BIZ_TRANSFER_DEPUTY_LEADER("6120000017","惠民-出借人服务部-业务对接副组长"),
	BIZ_TRANSFER_PERSON("6120000018","惠民-出借人服务部-业务对接专员"),	
	BIZ_DEDUCT_PERSON("6120000019","惠民-出借人服务部-划扣专员"),
	CONTRACT_MAKE_TEAM_LEADER("6120000020","惠民-出借人服务部-合同制作组长"),
	CONTRACT_MAKE_TEAM_DEPUTY_LEADER("6120000021","惠民-出借人服务部-合同制作副组长"),
	BIZ_TRANSFER_TEAM_MANAGER("6120000022","惠民-出借人服务部-业务对接经理"),
	BIZ_TRANSFER_LOAN_TRANSFER_TEAM_MEMBER("6120000023","惠民-出借人服务部-业务对接回款对接专员"),
	BIZ_TRANSFER_OFFICE_TEAM_MEMBER("6120000024","惠民-出借人服务部-业务对接综合业务专员"),
	BIZ_TRANSFER_DEPUTY_MANAGER("6120000025","惠民-出借人服务部-业务对接副经理"),
	BIZ_TRANSFER_DEDUCT_TEAM_LEADER("6120000026","惠民-出借人服务部-业务对接划扣组长"),
	BIZ_TRANSFER_DEDUCT_TEAM_SUB_LEADER("6120000027","惠民-出借人服务部-业务对接划扣副组长"),
	BIZ_TRANSFER_DEDUCT_TEAM_MEMBER("6120000028","惠民-出借人服务部-业务对接划扣专员"),
	BIZ_TRANSFER_LOAN_TEAM_LEADER("6120000029","惠民-出借人服务部-业务对接回款组长"),
	BIZ_TRANSFER_LOAN_TEAM_SUB_LEADER("6120000030","惠民-出借人服务部-业务对接回款副组长"),
	BIZ_TRANSFER_LOAN_TEAM_MEMBER("6120000031","惠民-出借人服务部-业务对接回款专员"),
	BIZ_TRANSFER_SMS_TEAM_MEMBER("6120000032","惠民-出借人服务部-业务对接短信提醒专员"),
	FILE_MAKE_SUB_MANAGER("6120000033","惠民-出借人服务部-文件制作副经理"),
	LENDER_DEPT_ADMIN("6120000034","惠民-出借人服务部-部门管理员"),
	 
	DATA_MASTER("6130000001","惠民-数据管理部-数据管理部负责人"),
	FORTUNE_DATA_PERSON("6130000002","惠民-数据管理部-财富数据专员"),
	LOAN_DATA_PERSON("6130000003","惠民-数据管理部-汇金数据专员"),
	DATA_MANAGER("6130000004","惠民-数据管理部-数据经理/主管"),
	DEDUCT_PERSON("6130000005","惠民-数据管理部-划扣专员"),
	DEDUCT_MASTER("6130000006","惠民-数据管理部-划扣经理/主管"),
	DELIVERY_PERSON("6130000007","惠民-数据管理部-放款结算专员"),
	DELIVERY_MANAGER("6130000008","惠民-数据管理部-放款结算经理/主管"),
	BASE_DEPT_ADMIN("6130000009","惠民-数据管理部-部门管理员"),
	DATA_ENGINEER_LOAN("6130000010","惠民-数据管理部-数据工程师汇金"),
	DATA_PAYBACK_ADMIN("6130000011","惠民-数据管理部-回款管理员"),
	DATA_JINXIN_COMMISSIONER("6130000012","惠民-数据管理部-金信专员"),
	DATA_PAYMENT_COMMISSIONER("6130000013","惠民-数据管理部-回款专员"),
	DATA_JINZHANGHU_ADMIN("6130000014","惠民-数据管理部-金账户管理员"),
	DATA_JINZHANGHU_COMMISSIONER("6130000015","惠民-数据管理部-金账户专员"),
	DATA_ENGINEER_FORTUNE("6130000016","惠民-数据管理部-数据工程师财富"),
	CF_DATA_TRANSFER_MANAGER("6130000017","财富-财富数据部-数据交割经理"),
	CF_DATA_MEMBER("6130000018","财富_财富数据部_数据交割专员"),
	DATA_ZHONGJIN_COMMISSIONER("6130000019","惠民-数据管理部-中金专员"),
	DATA_ZHONGJIN_ADMIN("6130000020","惠民-数据管理部-中金管理员"),
	
	
	RISK_GENERAL_MANAGER("6140000001","惠民-风险管理部-总监/总经理"),
	RISK_MASTER("6140000002","惠民-风险管理部-主管"),
	RECONSIDER_MANAGER("6140000003","惠民-风险管理部-复议经理"),
	RECONSIDER_PERSON("6140000004","惠民-风险管理部-复议专员"),
	EXAMINE_MASTER("6140000005","惠民-风险管理部-稽核主管"),
	EXAMINE_PERSON("6140000006","惠民-风险管理部-稽核专员"),
	COMPLIANCE_MANAGER("6140000007","惠民-风险管理部-合规经理"),
	COMPLIANCE_PERSON("6140000008","惠民-风险管理部-合规专员"),
	RISK_DATA_ANALYSE_PERSON("6140000009","惠民-风险管理部-数据分析专员"),
	RISK_MANAGER("6140000010","惠民-风险管理部-风控经理"),
	RISK_PERSON("6140000011","惠民-风险管理部-信借风控专员"),
	RISK_DEPT_ADMIN("6140000012","惠民-风险管理部-部门管理员"),
	RISK_DATA_ANALYSE_MASTER("6140000013","惠民-风险管理部-数据分析主管"),
	RISK_DATA_ANALYSE_MANAGER("6140000014","惠民-风险管理部-数据分析经理"),
	RISK_PERSON_CAR("6140000015","惠民-风险管理部-车借风控专员"),
	RISK_DEPT_MANAGER("6140000016","惠民-风险管理部-部门经理"),
	PRODUCT_MASTER("6150000001","惠民-需求分析与产品部-部门经理"),
	FORTUNE_PRODUCT_PERSON("6150000002","惠民-需求分析与产品部-财富产品管理专员"),
	LOAN_PRODUCT_PERSON("6150000003","惠民-需求分析与产品部-汇金产品管理专员"),
	PRODUCT_ADMIN("6150000004","惠民-需求分析与产品部-部门管理员"),
	ADMINISTRATOR("6160000001","惠民-总部运维部-超级管理员"),
	BASE_ADMIN("6170000001","惠民-总部-管理员"),
	CONTRACT_MAKE_MASTER("6180000001","惠民-合同制作小组-合同制作主管"),
	CONTRACT_APPROVE_MASTER("6180000002","惠民-合同制作小组-合同审核主管"),
	CONTRACT_MAKE_TEAM_SUB_LEADER("6180000003","惠民-合同制作小组-合同制作副组长"),
	CALL_CUSTOMER_SERVICE("6190000001","惠民-客户服务中心-呼叫中心客服"),
	DAJINRONG_CREDITOR_OPERATOR("6200000001","惠民-大金融债权运营"),
	HUIMIN_BIZ_DEPT_ADMIN("6300000001","惠民事业部-管理员"),
	
	CONTRACT_APPROVE_LEADER_ROLE_TYPE("惠民-借款人服务部-合同审核组长","合同审核组组长角色类型"), 
	// 真正的角色类型（用于gl.t_gl_role的role_type字段），
	// 标记合同审核组组长角色类型。
	// 建立审核小组角色时需要指定角色类型为此处的key
	RATE_APPROVE_LEADER_ROLE_TYPE("惠民-借款人服务部-合同制作组长","合同制作组组长角色类型"); 
	//原利率审核
	// 真正的角色类型（用于gl.t_gl_role的role_type字段），
	//标记利率审核组组长角色类型。
	//建立审核小组角色时需要指定角色类型为此处的key


	public final String id;
	public final String name;   //对应角色表中的enname

	private BaseRole(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
