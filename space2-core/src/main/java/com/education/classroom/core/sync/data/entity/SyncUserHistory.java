package com.education.classroom.core.sync.data.entity;

import java.util.Date;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 同步用户的历史对象
 * 即：从系统管理模块同步用户到业务模块的历史记录:汇金和财富
 * @Class Name SyncUserHistory
 * @author 张永生
 * @Create In 2015年12月2日
 */
public class SyncUserHistory extends DataEntity<SyncUserHistory> {

	private static final long serialVersionUID = 898060144276425395L;
	private String syncUserId;             		// 同步用户实体Id
	private Date   finishTime;             		// 完成时间,即数据同步完成的时间
	protected String userId;                	// 用户Id
	protected int    batchNo;                   // 批次号
	protected Date   versionTime;           	// 版本时间,即数据产生的时间
	protected String syncModuleName;            // 同步模块的名称
	protected String syncType;                  // 同步数据类型：参见SyncDataType:TYPE_ADD,TYPE_DELETE,TYPE_MODIFY 
	protected Integer syncStatus;           	// 同步状态,参见SyncStatus:0-未同步;1-同步失败;2-同步成功
	protected String companyId;			    	// 归属公司
	protected String departmentId;		    	// 归属部门
	protected String loginName;			    	// 登录名
	protected String password;		        	// 密码
	protected String userCode;    				// 工号,对应emp_code
	protected String name;						// 姓名
	protected String email;						// 邮箱
	protected String phone;						// 电话
	protected String mobile;					// 手机
	protected String userType;					// 用户类型
	protected String loginIp;					// 最后登陆IP
	protected Date loginDate;					// 最后登陆日期
	protected String status;					// 状态:1-允许登录;0-不允许登录
	protected String oldLoginName;				// 原登录名
	protected String newPassword;				// 新密码
	protected String oldLoginIp;				// 上次登陆IP
	protected Date oldLoginDate;				// 上次登陆日期
	private String hasLogin;   					// 是否登录用户与员工
	private Date entryTime;   					// 入职时间
	private String sex;         				// 用户性别
	private int level;							// 用户等级
	private String posPwd;     					// pos密码
	private String callEmpCode;   			    // 外呼员工号,汇诚使用
	private String leaderId;                    // 上级Id
	private Integer systemFlag;                 // 系统标识
	
	
	public String getSyncUserId() {
		return syncUserId;
	}
	public void setSyncUserId(String syncUserId) {
		this.syncUserId = syncUserId;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public Date getVersionTime() {
		return versionTime;
	}
	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
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
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOldLoginName() {
		return oldLoginName;
	}
	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldLoginIp() {
		return oldLoginIp;
	}
	public void setOldLoginIp(String oldLoginIp) {
		this.oldLoginIp = oldLoginIp;
	}
	public Date getOldLoginDate() {
		return oldLoginDate;
	}
	public void setOldLoginDate(Date oldLoginDate) {
		this.oldLoginDate = oldLoginDate;
	}
	public String getHasLogin() {
		return hasLogin;
	}
	public void setHasLogin(String hasLogin) {
		this.hasLogin = hasLogin;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getPosPwd() {
		return posPwd;
	}
	public void setPosPwd(String posPwd) {
		this.posPwd = posPwd;
	}
	public String getCallEmpCode() {
		return callEmpCode;
	}
	public void setCallEmpCode(String callEmpCode) {
		this.callEmpCode = callEmpCode;
	}
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public Integer getSystemFlag() {
		return systemFlag;
	}
	public void setSystemFlag(Integer systemFlag) {
		this.systemFlag = systemFlag;
	}
	
	
}
