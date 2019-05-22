package com.education.classroom.core.common.view;

import java.util.Date;


/**
 * 数据View
 * @Class Name DataView
 * @author 张永生
 * @Create In 2015年12月15日
 */
public class DataView {

	protected String id;                    // id
	protected String remarks;				// 备注
	protected String createBy;				// 创建者Id
	protected Date createTime;				// 创建日期
	protected String modifyBy;				// 更新者Id
	protected Date modifyTime;				// 更新日期
	protected String delFlag; 				// 删除标记（0：正常；1：删除；2：审核）
	
	protected String createByName;  		// 创建者名字
	protected String createByCompanyName;   // 创建者公司名称
	protected String createByDeptName;  	// 创建者部门名称
	protected String modifyByName;			// 更新者名字
	protected String modifyByCompanyName;	// 更新者公司名称
	protected String modifyByDeptName;		// 更新者部门名称
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public void setModifBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getCreateByCompanyName() {
		return createByCompanyName;
	}
	public void setCreateByCompanyName(String createByCompanyName) {
		this.createByCompanyName = createByCompanyName;
	}
	public String getCreateByDeptName() {
		return createByDeptName;
	}
	public void setCreateByDeptName(String createByDeptName) {
		this.createByDeptName = createByDeptName;
	}
	public String getUpdateByName() {
		return modifyByName;
	}
	public void setUpdateByName(String modifyByName) {
		this.modifyByName = modifyByName;
	}
	public String getUpdateByCompanyName() {
		return modifyByCompanyName;
	}
	public void setUpdateByCompanyName(String modifyByCompanyName) {
		this.modifyByCompanyName = modifyByCompanyName;
	}
	public String getUpdateByDeptName() {
		return modifyByDeptName;
	}
	public void setUpdateByDeptName(String modifyByDeptName) {
		this.modifyByDeptName = modifyByDeptName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createBy == null) ? 0 : createBy.hashCode());
		result = prime
				* result
				+ ((createByCompanyName == null) ? 0 : createByCompanyName
						.hashCode());
		result = prime
				* result
				+ ((createByDeptName == null) ? 0 : createByDeptName.hashCode());
		result = prime * result
				+ ((createByName == null) ? 0 : createByName.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((delFlag == null) ? 0 : delFlag.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result
				+ ((modifyBy == null) ? 0 : modifyBy.hashCode());
		result = prime
				* result
				+ ((modifyByCompanyName == null) ? 0 : modifyByCompanyName
						.hashCode());
		result = prime
				* result
				+ ((modifyByDeptName == null) ? 0 : modifyByDeptName.hashCode());
		result = prime * result
				+ ((modifyByName == null) ? 0 : modifyByName.hashCode());
		result = prime * result
				+ ((modifyTime == null) ? 0 : modifyTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataView other = (DataView) obj;
		if (createBy == null) {
			if (other.createBy != null)
				return false;
		} else if (!createBy.equals(other.createBy))
			return false;
		if (createByCompanyName == null) {
			if (other.createByCompanyName != null)
				return false;
		} else if (!createByCompanyName.equals(other.createByCompanyName))
			return false;
		if (createByDeptName == null) {
			if (other.createByDeptName != null)
				return false;
		} else if (!createByDeptName.equals(other.createByDeptName))
			return false;
		if (createByName == null) {
			if (other.createByName != null)
				return false;
		} else if (!createByName.equals(other.createByName))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (delFlag == null) {
			if (other.delFlag != null)
				return false;
		} else if (!delFlag.equals(other.delFlag))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (modifyBy == null) {
			if (other.modifyBy != null)
				return false;
		} else if (!modifyBy.equals(other.modifyBy))
			return false;
		if (modifyByCompanyName == null) {
			if (other.modifyByCompanyName != null)
				return false;
		} else if (!modifyByCompanyName.equals(other.modifyByCompanyName))
			return false;
		if (modifyByDeptName == null) {
			if (other.modifyByDeptName != null)
				return false;
		} else if (!modifyByDeptName.equals(other.modifyByDeptName))
			return false;
		if (modifyByName == null) {
			if (other.modifyByName != null)
				return false;
		} else if (!modifyByName.equals(other.modifyByName))
			return false;
		if (modifyTime == null) {
			if (other.modifyTime != null)
				return false;
		} else if (!modifyTime.equals(other.modifyTime))
			return false;
		return true;
	}

	
}
