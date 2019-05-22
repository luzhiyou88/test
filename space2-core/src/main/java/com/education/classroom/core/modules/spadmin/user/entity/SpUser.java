/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.user.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.modules.spadmin.space.entity.SpSpace;
import com.education.classroom.core.modules.spadmin.spclass.entity.SpClass;
import com.education.classroom.core.modules.spadmin.specialty.entity.SpSpecialty;
import com.education.classroom.core.persistence.DataEntity;

/**
 * 用户管理Entity
 * @author 边磊
 * @version 2016/08/06
 */
public class SpUser extends DataEntity<SpUser> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 用户姓名
	private String loginName;		// 登录姓名
	private String userPwd;		// 登录密码
	private String thumbImg;		// 用户头像
	private String mobile;		// 手机
	private String email;		// 邮箱
	private String userType;		// 用户类型 1学生 2老师 3创业者 4投资者
	private String userState;		// 用户状态 待开发
	private String spaceId;		// 所属第二空间
	private String enterYear;		// 入学年份
	private String specialtyId;		// 所属专业
	private String classId;		// 所在班级
	
	private SpSpecialty spSpecialty;
	private SpClass spClass;
	private SpSpace spSpace;
	
	public SpUser() {
		super();
	}

	public SpUser(String id){
		super(id);
	}

	public SpSpecialty getSpSpecialty() {
		return spSpecialty;
	}

	public void setSpSpecialty(SpSpecialty spSpecialty) {
		this.spSpecialty = spSpecialty;
	}

	public SpClass getSpClass() {
		return spClass;
	}

	public void setSpClass(SpClass spClass) {
		this.spClass = spClass;
	}

	public SpSpace getSpSpace() {
		return spSpace;
	}

	public void setSpSpace(SpSpace spSpace) {
		this.spSpace = spSpace;
	}

	@Length(min=0, max=128, message="用户姓名长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="登录姓名长度必须介于 0 和 32 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=1, max=32, message="登录密码长度必须介于 1 和 32 之间")
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	@Length(min=0, max=256, message="用户头像长度必须介于 0 和 256 之间")
	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		if(StringUtils.isEmpty(thumbImg)){
			this.thumbImg ="";
		}else{
			this.thumbImg = thumbImg;
		}
	}
	
	@Length(min=1, max=11, message="手机长度必须介于 1 和 11 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=32, message="邮箱长度必须介于 0 和 32 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=64, message="用户类型 1学生 2老师 3创业者 4投资者长度必须介于 0 和 64 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=0, max=11, message="用户状态 待开发长度必须介于 0 和 11 之间")
	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}
	
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Length(min=0, max=4, message="入学年份长度必须介于 0 和 4 之间")
	public String getEnterYear() {
		return enterYear;
	}

	public void setEnterYear(String enterYear) {
		this.enterYear = enterYear;
	}
	
	@Length(min=0, max=32, message="所属专业长度必须介于 0 和 32 之间")
	public String getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(String specialtyId) {
		this.specialtyId = specialtyId;
	}
	
	@Length(min=0, max=32, message="所在班级长度必须介于 0 和 32 之间")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
}