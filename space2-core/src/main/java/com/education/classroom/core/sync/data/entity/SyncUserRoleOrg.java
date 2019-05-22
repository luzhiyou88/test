package com.education.classroom.core.sync.data.entity;

import com.education.classroom.core.persistence.SyncEntity;


/**
 * 同步用户与组织机构关系的实体
 * @Class Name SyncUserOrg
 * @author 张永生
 * @Create In 2015年12月3日
 */
public class SyncUserRoleOrg extends SyncEntity<SyncUserRoleOrg>{

	private static final long serialVersionUID = -5036772778052273673L;

	protected String userId;				   // 用户Id
	protected String roleId;				   // 用户Id
	protected String orgId;                    // 组织机构Id
	protected String syncModuleName;           // 同步模块的名称
	protected String syncType;                 // 数据同步类型,参见SyncDataType:TYPE_ADD,TYPE_DELETE,TYPE_MODIFY
	protected Integer syncStatus;              // 数据同步状态:参见SyncStatus:0-未同步;1-同步失败;2-同步成功
	protected int batchNo;                     // 批次号,每一次操作就是一个批次
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSyncModuleName() {
		return syncModuleName;
	}
	public void setSyncModuleName(String syncModuleName) {
		this.syncModuleName = syncModuleName;
	}
	public String getSyncType() {
		return syncType;
	}
	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}
	public Integer getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
