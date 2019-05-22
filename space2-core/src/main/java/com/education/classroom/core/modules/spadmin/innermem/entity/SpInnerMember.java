package com.education.classroom.core.modules.spadmin.innermem.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 会员预留信息管理Entity
 * @author 尚军伟
 * @version 2016/08/06
 */
public class SpInnerMember extends DataEntity<SpInnerMember> {
	private static final long serialVersionUID = 5661938011052427620L;
	private String spaceId;		// 所属学校
	private String specialtyId;		// 所属专业
	private String specialtyName;		// 所属专业名称
	private String className;		// 所在班级名称
	private String classId;		// 所在班级
	private String userName;		// 用户真实姓名
	private String phoneNo;		// 用户有效手机号
	private String enterYear;		// 入学年份
	private String validPeriod;		// 有效的月份
	private String sex;		// 性别 0男 1女  3其他
	private Date birthday;		// 出生日期
	private String publishState;		// 发布状态 0未发布 1等待审核   2审核未通过 3审核通过
	private String state;		// 留信息预状态 0未激活   1已激活
	private String desc;		// 备注
	private String spaceName;    //所属学校名称
	
	
	
	public SpInnerMember() {
		super();
	}

	public SpInnerMember(String id){
		super(id);
	}

	@Length(min=1, max=32, message="所属学校长度必须介于 1 和 32 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Length(min=0, max=32, message="所属专业长度必须介于 0 和 32 之间")
	public String getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(String specialtyId) {
		this.specialtyId = specialtyId;
	}
	
	@Length(min=0, max=128, message="所属专业名称长度必须介于 0 和 128 之间")
	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}
	
	@Length(min=0, max=128, message="所在班级名称长度必须介于 0 和 128 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Length(min=0, max=32, message="所在班级长度必须介于 0 和 32 之间")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	@Length(min=1, max=20, message="用户真实姓名长度必须介于 1 和 20 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=1, max=11, message="用户有效手机号长度必须介于 1 和 11 之间")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Length(min=1, max=4, message="用户有效手机号长度必须介于 1 和 4 之间")
	public String getEnterYear() {
		return enterYear;
	}

	public void setEnterYear(String enterYear) {
		this.enterYear = enterYear;
	}
	
	
	@Range(min=1, max=12,message="有效的月份必须介于 1 和 12之间")
	public String getValidPeriod() {
		return validPeriod;
	}

	public void setValidPeriod(String validPeriod) {
		this.validPeriod = validPeriod;
	}
	
	@Length(min=0, max=1, message="性别 0男 1女  3其他长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=1, message="发布状态 0未发布 1等待审核   2审核未通过 3审核通过长度必须介于 0 和 1 之间")
	public String getPublishState() {
		return publishState;
	}

	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}
	
	@Length(min=1, max=1, message="留信息预状态 0未激活   1已激活长度必须介于 1 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=256, message="备注长度必须介于 0 和 256 之间")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	
	
}