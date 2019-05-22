package com.education.classroom.core.moneyaccount.entity;

import java.io.Serializable;


/**
 * 金账户所有数据项
 * @Class Name MoneyAccountInfo
 * @author 武文涛
 * @Create In 2016年2月26日
 */
public class MoneyAccountInfo implements Serializable {
	
	private static final long serialVersionUID = -570343402058897596L;
	
	private String mchntCd;     // 商户代码;
	private String mchntTxnSsn;     // 流水号;
	private String custNm;     // 客户姓名;
	private String certifTp;     // 证件类型（0:身份证7：其他证件）; 
	private String certifId;     // 身份证号码/证件;
	private String mobileNo;     // 手机号码;
	private String email;     // 邮箱地址;
	private String cityId;     // 开户行地区代码;
	private String parentBankId;     // 开户行行别; 
	private String bankNm;     // 开户行支行名称;
	private String capAcntNm;     // 户名;
	private String capAcntNo;     // 帐号;
	private String password;     // 提现密码（不填默认为手机号后6位）;
	private String lpassword;     // 登录密码（不填默认为手机号后6位）;
	private String rem;     // 备注;
	private String userIds;    // 用户登录ID（以”|”分隔（最多只能同时查10个用户））
	private String startDay;    // 起始时间
	private String endDay;      // 截止时间（起止时间不能跨月）
	private String mchntTxnDt;   // 交易日期
	private String custNo;      // 待查询的登录帐户（查询企业注册的手机号或者用户名，多个帐号请以”|”分隔 （最多只能同时查10个用户））
	private String outCustNo;   // 付款登录账户
	private String inCustNo;    // 收款登录账户
	private String amt;         // 转账金额																
	private String contractNo;   // 预授权合同号（预授权请求交易成功时账户系统返回的合同号；如果带合同号，则是从对应预授权的冻结余额转出；如果没带合同号，则是直接从出账用户的可用余额转出；是否需要带合同号，就看具体业务 ）																
	private String busiTp;     // 交易类型
	private String txnSsn;     // 交易流水
	private String txnSt;	   // 交易状态（1 交易成功 2 交易失败）
	private String remark;		// 交易备注		
	private String pageNo;		// 页码（大于零的整数；默认为1;）
	private String pageSize;	// 每页条数（[10,100]之间整数; 默认值:10;最大值:100;）
	private String startTime;	// 起始时间（起始充值完成时间格式：yyyy-MM-dd HH:mm:ss）
	private String endTime;		// 截止时间（截至充值完成时间格式：yyyy-MM-dd HH:mm:ss）
	private String artifNm;		// 法人姓名
	private String flag;        // 富友—金账户接口标识
	
	
	public MoneyAccountInfo() {
	}
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getArtifNm() {
		return artifNm;
	}
	public void setArtifNm(String artifNm) {
		this.artifNm = artifNm;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTxnSt() {
		return txnSt;
	}
	public void setTxnSt(String txnSt) {
		this.txnSt = txnSt;
	}
	public String getTxnSsn() {
		return txnSsn;
	}
	public void setTxnSsn(String txnSsn) {
		this.txnSsn = txnSsn;
	}
	public String getBusiTp() {
		return busiTp;
	}
	public void setBusiTp(String busiTp) {
		this.busiTp = busiTp;
	}
	public String getOutCustNo() {
		return outCustNo;
	}
	public void setOutCustNo(String outCustNo) {
		this.outCustNo = outCustNo;
	}
	public String getInCustNo() {
		return inCustNo;
	}
	public void setInCustNo(String inCustNo) {
		this.inCustNo = inCustNo;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getMchntTxnDt() {
		return mchntTxnDt;
	}
	public void setMchntTxnDt(String mchntTxnDt) {
		this.mchntTxnDt = mchntTxnDt;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getMchntCd() {
		return mchntCd;
	}
	public void setMchntCd(String mchntCd) {
		this.mchntCd = mchntCd;
	}
	public String getMchntTxnSsn() {
		return mchntTxnSsn;
	}
	public void setMchntTxnSsn(String mchntTxnSsn) {
		this.mchntTxnSsn = mchntTxnSsn;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getCertifTp() {
		return certifTp;
	}
	public void setCertifTp(String certifTp) {
		this.certifTp = certifTp;
	}
	public String getCertifId() {
		return certifId;
	}
	public void setCertifId(String certifId) {
		this.certifId = certifId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getParentBankId() {
		return parentBankId;
	}
	public void setParentBankId(String parentBankId) {
		this.parentBankId = parentBankId;
	}
	public String getBankNm() {
		return bankNm;
	}
	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}
	public String getCapAcntNm() {
		return capAcntNm;
	}
	public void setCapAcntNm(String capAcntNm) {
		this.capAcntNm = capAcntNm;
	}
	public String getCapAcntNo() {
		return capAcntNo;
	}
	public void setCapAcntNo(String capAcntNo) {
		this.capAcntNo = capAcntNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLpassword() {
		return lpassword;
	}
	public void setLpassword(String lpassword) {
		this.lpassword = lpassword;
	}
	public String getRem() {
		return rem;
	}
	public void setRem(String rem) {
		this.rem = rem;
	}
	
}
