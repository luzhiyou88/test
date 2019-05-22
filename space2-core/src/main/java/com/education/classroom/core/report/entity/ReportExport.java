package com.education.classroom.core.report.entity;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 报表导出权限实体
 * 用来定义哪些角色可以导出
 * @Class Name ReportExport
 * @author 张永生
 * @Create In 2016年6月27日
 */
public class ReportExport extends DataEntity<ReportExport> {

	private static final long serialVersionUID = 1L;
	
	private String orgId;                   // 机构Id
	private String roleId;                  // 角色Id
	private String reportId;                // 报表Id
	private String canImport;               // 是否可以导出: 可否导出（1：是，0：否）
	private String buttonIndexs;            // 按钮索引
	private String fields1;                 // 预留字段1
	private String fields2;                 // 预留字段2
	private String fields3;                 // 预留字段3
	
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getCanImport() {
		return canImport;
	}
	public void setCanImport(String canImport) {
		this.canImport = canImport;
	}
	public String getButtonIndexs() {
		return buttonIndexs;
	}
	public void setButtonIndexs(String buttonIndexs) {
		this.buttonIndexs = buttonIndexs;
	}
	public String getFields1() {
		return fields1;
	}
	public void setFields1(String fields1) {
		this.fields1 = fields1;
	}
	public String getFields2() {
		return fields2;
	}
	public void setFields2(String fields2) {
		this.fields2 = fields2;
	}
	public String getFields3() {
		return fields3;
	}
	public void setFields3(String fields3) {
		this.fields3 = fields3;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((buttonIndexs == null) ? 0 : buttonIndexs.hashCode());
		result = prime * result
				+ ((canImport == null) ? 0 : canImport.hashCode());
		result = prime * result + ((fields1 == null) ? 0 : fields1.hashCode());
		result = prime * result + ((fields2 == null) ? 0 : fields2.hashCode());
		result = prime * result + ((fields3 == null) ? 0 : fields3.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result
				+ ((reportId == null) ? 0 : reportId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		ReportExport other = (ReportExport) obj;
		if (buttonIndexs == null) {
			if (other.buttonIndexs != null)
				return false;
		} else if (!buttonIndexs.equals(other.buttonIndexs))
			return false;
		if (canImport == null) {
			if (other.canImport != null)
				return false;
		} else if (!canImport.equals(other.canImport))
			return false;
		if (fields1 == null) {
			if (other.fields1 != null)
				return false;
		} else if (!fields1.equals(other.fields1))
			return false;
		if (fields2 == null) {
			if (other.fields2 != null)
				return false;
		} else if (!fields2.equals(other.fields2))
			return false;
		if (fields3 == null) {
			if (other.fields3 != null)
				return false;
		} else if (!fields3.equals(other.fields3))
			return false;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		if (reportId == null) {
			if (other.reportId != null)
				return false;
		} else if (!reportId.equals(other.reportId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}
	
	
}
