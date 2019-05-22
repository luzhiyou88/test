package com.education.classroom.core.sync.data.entity;

import java.util.Date;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 同步数据字典对象
 * @Class Name SyncDict
 * @author 陈伟东
 * @Create In 2015年12月28日
 */
public class SyncDict extends DataEntity<SyncDict>{
	
	private static final long serialVersionUID = 8914519833516418938L;
	
	private Date versionTime;
    private Integer batchNo;
    private String syncModuleName;
    private String syncType;
    private Integer syncStatus;
    private String dictId;
    private String value;
    private String label;
    private String type;
    private String description;
    private Integer sort;
    private String parentId;
    private String remarks;
    private String delFlag;
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;
    private Integer systemFlag;
    
	public Date getVersionTime() {
		return versionTime;
	}
	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
	}
	public Integer getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
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
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getSystemFlag() {
		return systemFlag;
	}
	public void setSystemFlag(Integer systemFlag) {
		this.systemFlag = systemFlag;
	}
    
}