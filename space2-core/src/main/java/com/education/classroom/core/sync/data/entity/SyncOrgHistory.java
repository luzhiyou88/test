package com.education.classroom.core.sync.data.entity;

import java.util.Date;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 同步组织结构历史实体
 * 即：从系统管理模块同步组织机构到业务模块的同步历史实体
 * @Class Name SyncOrgHistory
 * @author 张永生
 * @Create In 2015年12月2日
 */
public class SyncOrgHistory extends DataEntity<SyncOrgHistory> {

	private static final long serialVersionUID = -5681917418542038120L;
	private String syncOrgId;            // 同步组织机构实体Id
	private Date   finishTime;           // 完成时间,即数据同步完成的时间
	protected String orgId;                 // 组织机构Id
	protected Date   versionTime;           // 版本时间,即数据产生的时间
	protected int batchNo;                  // 数据产生时生成的批次号
	protected String syncModuleName;        // 同步模块的名称
	protected String syncType;              // 同步数据类型：参见SyncDataType:TYPE_ADD,TYPE_DELETE,TYPE_MODIFY 
	protected Integer syncStatus;           // 同步状态
	protected String parentId;			    // 父级Id
	protected String parentIds; 	     	// 所有父级Id的编号
	protected String name; 	             	// 机构名称
	protected Integer sort;		         	// 排序
	protected String areaId;				// 归属区域
	protected String code; 				 	// 机构编码
	protected String type; 				 	// 机构类型（1：公司；2：部门；3：小组）
	protected String grade; 				// 机构等级（1：一级；2：二级；3：三级；4：四级）
	protected String address; 			 	// 联系地址
	protected String zipCode; 			 	// 邮政编码
	protected String master; 				// 负责人
	protected String phone; 				// 电话
	protected String fax; 				 	// 传真
	protected String email; 				// 邮箱
	protected String useable;               // 是否可用
	protected String provinceId;            // 省份Id
	protected String cityId;                // 城市Id
	protected String districtId;            // 区县Id 
	private String partyType;               // 主体类型:用于描述该组织机构隶属于哪个主体,参见PartyType.java
	private String storeCode;               // 门店编码
	private String carLoanCode;             // 门店的车借编码
	private Integer systemFlag;             // 系统标识
	private String creditPaperless;      	// 信借是否无纸化, 1-是,0-否
	private String carPaperless;         	// 车借是否无纸化, 1-是,0-否
	
	public String getSyncOrgId() {
		return syncOrgId;
	}
	public void setSyncOrgId(String syncOrgId) {
		this.syncOrgId = syncOrgId;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Date getVersionTime() {
		return versionTime;
	}
	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUseable() {
		return useable;
	}
	public void setUseable(String useable) {
		this.useable = useable;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getCarLoanCode() {
		return carLoanCode;
	}
	public void setCarLoanCode(String carLoanCode) {
		this.carLoanCode = carLoanCode;
	}
	public Integer getSystemFlag() {
		return systemFlag;
	}
	public void setSystemFlag(Integer systemFlag) {
		this.systemFlag = systemFlag;
	}
	public String getCreditPaperless() {
		return creditPaperless;
	}
	public void setCreditPaperless(String creditPaperless) {
		this.creditPaperless = creditPaperless;
	}
	public String getCarPaperless() {
		return carPaperless;
	}
	public void setCarPaperless(String carPaperless) {
		this.carPaperless = carPaperless;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + batchNo;
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((districtId == null) ? 0 : districtId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result
				+ ((finishTime == null) ? 0 : finishTime.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((master == null) ? 0 : master.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((parentIds == null) ? 0 : parentIds.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((provinceId == null) ? 0 : provinceId.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result
				+ ((syncModuleName == null) ? 0 : syncModuleName.hashCode());
		result = prime * result
				+ ((syncOrgId == null) ? 0 : syncOrgId.hashCode());
		result = prime * result
				+ ((syncStatus == null) ? 0 : syncStatus.hashCode());
		result = prime * result
				+ ((syncType == null) ? 0 : syncType.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((useable == null) ? 0 : useable.hashCode());
		result = prime * result
				+ ((versionTime == null) ? 0 : versionTime.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		SyncOrgHistory other = (SyncOrgHistory) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		if (batchNo != other.batchNo)
			return false;
		if (cityId == null) {
			if (other.cityId != null)
				return false;
		} else if (!cityId.equals(other.cityId))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (districtId == null) {
			if (other.districtId != null)
				return false;
		} else if (!districtId.equals(other.districtId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (finishTime == null) {
			if (other.finishTime != null)
				return false;
		} else if (!finishTime.equals(other.finishTime))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (master == null) {
			if (other.master != null)
				return false;
		} else if (!master.equals(other.master))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (parentIds == null) {
			if (other.parentIds != null)
				return false;
		} else if (!parentIds.equals(other.parentIds))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (provinceId == null) {
			if (other.provinceId != null)
				return false;
		} else if (!provinceId.equals(other.provinceId))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (syncModuleName == null) {
			if (other.syncModuleName != null)
				return false;
		} else if (!syncModuleName.equals(other.syncModuleName))
			return false;
		if (syncOrgId == null) {
			if (other.syncOrgId != null)
				return false;
		} else if (!syncOrgId.equals(other.syncOrgId))
			return false;
		if (syncStatus == null) {
			if (other.syncStatus != null)
				return false;
		} else if (!syncStatus.equals(other.syncStatus))
			return false;
		if (syncType == null) {
			if (other.syncType != null)
				return false;
		} else if (!syncType.equals(other.syncType))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (useable == null) {
			if (other.useable != null)
				return false;
		} else if (!useable.equals(other.useable))
			return false;
		if (versionTime == null) {
			if (other.versionTime != null)
				return false;
		} else if (!versionTime.equals(other.versionTime))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	
}
