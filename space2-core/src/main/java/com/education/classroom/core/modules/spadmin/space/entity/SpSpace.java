package com.education.classroom.core.modules.spadmin.space.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.education.classroom.core.users.entity.Area;

/**
 * 教室管理Entity
 * @author 尚军伟
 * @version 2016/08/06
 */
public class SpSpace extends DataEntity<SpSpace> {
	
	private static final long serialVersionUID = 491559497984584650L;
	private String name;		// 学校名称
	private Area province; // 属省份所
	private String provinceId; // 属省份所Id
	private Area city; // 所属城市
	private String cityId; // 所属城市Id
	private String schoolManager;		// 管理人
	private String managerMobile;		// 管理人电话
	private int reservedQuantity;      //预留数量
	
	public SpSpace() {
		super();
	}

	public SpSpace(String id){
		super(id);
	}

	@Length(min=1, max=128, message="学校名称长度必须介于 1 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Length(min=1, max=64, message="管理人长度必须介于 1 和 64 之间")
	public String getSchoolManager() {
		return schoolManager;
	}

	public void setSchoolManager(String schoolManager) {
		this.schoolManager = schoolManager;
	}
	
	@Length(min=1, max=11, message="管理人电话长度必须介于 1 和 11 之间")
	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}

	public Area getProvince() {
		return province;
	}

	public void setProvince(Area province) {
		this.province = province;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public Area getCity() {
		return city;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public int getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(int reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}
	
	
	
}