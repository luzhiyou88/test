package com.education.classroom.core.role.type;

import java.util.Hashtable;
import java.util.Map;

/**
 * 汇诚（即核查）角色枚举
 * @Class Name CheckRole
 * @author 张永生
 * @Create In 2016年1月7日
 */
public enum ApproveRole {

	APPROVE_VERIFY_MANAGER("6310000001","汇诚-信审经理"),
	APPROVE_VERIFY_TEAM_LEADER("6310000002","汇诚-信审组长"),
	APPROVE_VERIFY_CHECK("6310000003","汇诚-信审初审"),
	APPROVE_VERIFY_RECHECK("6310000004","汇诚-信审复审"),
	APPROVE_VERIFY_GROUP_CHECK("6310000005","汇诚-信审终审"),
	APPROVE_VERIFY_FINAL_CHECK("6310000006","汇诚-信审高级终审"),
	APPROVE_RECONSIDER_MANAGER("6310000007","汇诚-复议经理"),
	APPROVE_RECONSIDER_TEAM_LEADER("6310000008","汇诚-复议组长"),
	APPROVE_RECONSIDER_CHECK("6310000009","汇诚-复议初审"),
	APPROVE_RECONSIDER_RECHECK("6310000010","汇诚-复议复审"),
	APPROVE_RECONSIDER_FINAL_CHECK("6310000011","汇诚-复议终审"),
	APPROVE_ANTIFRAUD_MANAGER("6310000012","汇诚-反欺诈经理"),
	APPROVE_ANTIFRAUD_MEMBER("6310000013","汇诚-反欺诈专员"),
	APPROVE_CODE_ADMIN("6310000014","汇诚-码值管理员"),
	APPROVE_RULE_ADMIN("6310000015","汇诚-规则管理员"),
	APPROVE_SYNTHETICAL_MEMBER("6310000016","汇诚-综合内勤"),
	APPROVE_TRAIN_MANAGER("6310000017","汇诚-培训经理"),
	APPROVE_DATA_VIEW_MEMBER("6310000018","汇诚-数据查看专员"),
	APPROVE_CAR_RECHECK("6310000019","汇诚-车借复审"),
	APPROVE_CAR_FINAL_CHECK("6310000020","汇诚-车借终审"),
	APPROVE_VERIFY_REPORT("6310000021","汇诚-审批数据分析员"),
	APPROVE_ANTIFRAUD_REPORT("6310000022","汇诚-反欺诈数据分析专员"),
	APPROVE_PERSONNEL_ADMIN("6320000001","汇诚人管室-管理员");

	public final String id;    //对应角色表中的id字段
	public final String name;  //对应角色表中的enname字段

	private ApproveRole(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Map<String, String> NameCodeMap = new Hashtable<String, String>();

	public static Map<String, String> getNameCodeMap() {
		if(NameCodeMap.isEmpty()){
			NameCodeMap.put(ApproveRole.APPROVE_VERIFY_MANAGER.name, ApproveRole.APPROVE_VERIFY_MANAGER.id);
			NameCodeMap.put(ApproveRole.APPROVE_VERIFY_TEAM_LEADER.name, ApproveRole.APPROVE_VERIFY_TEAM_LEADER.id);
			NameCodeMap.put(ApproveRole.APPROVE_VERIFY_CHECK.name, ApproveRole.APPROVE_VERIFY_CHECK.id);
			NameCodeMap.put(ApproveRole.APPROVE_VERIFY_RECHECK.name, ApproveRole.APPROVE_VERIFY_RECHECK.id);
			NameCodeMap.put(ApproveRole.APPROVE_VERIFY_GROUP_CHECK.name, ApproveRole.APPROVE_VERIFY_GROUP_CHECK.id);
			NameCodeMap.put(ApproveRole.APPROVE_VERIFY_FINAL_CHECK.name, ApproveRole.APPROVE_VERIFY_FINAL_CHECK.id);
			NameCodeMap.put(ApproveRole.APPROVE_RECONSIDER_MANAGER.name, ApproveRole.APPROVE_RECONSIDER_MANAGER.id);
			NameCodeMap.put(ApproveRole.APPROVE_RECONSIDER_TEAM_LEADER.name, ApproveRole.APPROVE_RECONSIDER_TEAM_LEADER.id);
			NameCodeMap.put(ApproveRole.APPROVE_RECONSIDER_CHECK.name, ApproveRole.APPROVE_RECONSIDER_CHECK.id);
			NameCodeMap.put(ApproveRole.APPROVE_RECONSIDER_RECHECK.name, ApproveRole.APPROVE_RECONSIDER_RECHECK.id);
			NameCodeMap.put(ApproveRole.APPROVE_RECONSIDER_FINAL_CHECK.name, ApproveRole.APPROVE_RECONSIDER_FINAL_CHECK.id);
			NameCodeMap.put(ApproveRole.APPROVE_ANTIFRAUD_MANAGER.name, ApproveRole.APPROVE_ANTIFRAUD_MANAGER.id);
			NameCodeMap.put(ApproveRole.APPROVE_ANTIFRAUD_MEMBER.name, ApproveRole.APPROVE_ANTIFRAUD_MEMBER.id);
			NameCodeMap.put(ApproveRole.APPROVE_CODE_ADMIN.name, ApproveRole.APPROVE_CODE_ADMIN.id);
			NameCodeMap.put(ApproveRole.APPROVE_RULE_ADMIN.name, ApproveRole.APPROVE_RULE_ADMIN.id);
			NameCodeMap.put(ApproveRole.APPROVE_SYNTHETICAL_MEMBER.name, ApproveRole.APPROVE_SYNTHETICAL_MEMBER.id);
			NameCodeMap.put(ApproveRole.APPROVE_TRAIN_MANAGER.name, ApproveRole.APPROVE_TRAIN_MANAGER.id);
			NameCodeMap.put(ApproveRole.APPROVE_DATA_VIEW_MEMBER.name, ApproveRole.APPROVE_DATA_VIEW_MEMBER.id);
			NameCodeMap.put(ApproveRole.APPROVE_CAR_RECHECK.name, ApproveRole.APPROVE_CAR_RECHECK.id);
			NameCodeMap.put(ApproveRole.APPROVE_CAR_FINAL_CHECK.name, ApproveRole.APPROVE_CAR_FINAL_CHECK.id);
			NameCodeMap.put(ApproveRole.APPROVE_PERSONNEL_ADMIN.name, ApproveRole.APPROVE_PERSONNEL_ADMIN.id);
		}
		return NameCodeMap;
	}
	
}
