package com.education.classroom.core.sync.data.entity;

import com.education.classroom.core.persistence.SyncEntity;

/**
 * 同步数据批次实体
 * 主要用于中间表数据的同步
 * @Class Name SyncDataBatch
 * @author 张永生
 * @Create In 2015年12月5日
 */
public class SyncDataBatch extends SyncEntity<SyncDataBatch>{

	private static final long serialVersionUID = -1924282210496563885L;
	
	protected int batchNo;       			    // 批次号,每一次操作就是一个批次
	protected String groupKey;					// 分组key,存储数据格式: userId_moduleName
	protected String groupKeyDesc;				// 分组key的描述,例如t_gl_user_org表同步时,此数据列存储的数据格式: userId_moduleName
	protected String syncTableName;             // 同步数据的表名,例如: t_gl_sync_user_org
	protected Integer syncStatus;               // 数据同步状态:参见SyncStatus:0-未同步;1-同步失败;2-同步成功
	protected String syncModuleName;			// 需要同步数据到哪个模块的名称
	
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public String getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
	public Integer getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	public String getGroupKeyDesc() {
		return groupKeyDesc;
	}
	public void setGroupKeyDesc(String groupKeyDesc) {
		this.groupKeyDesc = groupKeyDesc;
	}
	public String getSyncTableName() {
		return syncTableName;
	}
	public void setSyncTableName(String syncTableName) {
		this.syncTableName = syncTableName;
	}
	public String getSyncModuleName() {
		return syncModuleName;
	}
	public void setSyncModuleName(String syncModuleName) {
		this.syncModuleName = syncModuleName;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchNo;
		result = prime * result
				+ ((groupKey == null) ? 0 : groupKey.hashCode());
		result = prime * result
				+ ((groupKeyDesc == null) ? 0 : groupKeyDesc.hashCode());
		result = prime * result
				+ ((syncModuleName == null) ? 0 : syncModuleName.hashCode());
		result = prime * result
				+ ((syncStatus == null) ? 0 : syncStatus.hashCode());
		result = prime * result
				+ ((syncTableName == null) ? 0 : syncTableName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SyncDataBatch other = (SyncDataBatch) obj;
		if (batchNo != other.batchNo)
			return false;
		if (groupKey == null) {
			if (other.groupKey != null)
				return false;
		} else if (!groupKey.equals(other.groupKey))
			return false;
		if (groupKeyDesc == null) {
			if (other.groupKeyDesc != null)
				return false;
		} else if (!groupKeyDesc.equals(other.groupKeyDesc))
			return false;
		if (syncModuleName == null) {
			if (other.syncModuleName != null)
				return false;
		} else if (!syncModuleName.equals(other.syncModuleName))
			return false;
		if (syncStatus == null) {
			if (other.syncStatus != null)
				return false;
		} else if (!syncStatus.equals(other.syncStatus))
			return false;
		if (syncTableName == null) {
			if (other.syncTableName != null)
				return false;
		} else if (!syncTableName.equals(other.syncTableName))
			return false;
		return true;
	}
	
}
