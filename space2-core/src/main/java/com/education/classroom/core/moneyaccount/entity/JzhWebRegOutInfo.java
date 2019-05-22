package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 个人用户自助开户注册（网页版）响应参数Bean.
 * @Class Name JzhWebRegOutInfo
 * @author CHP_3.0 接口组
 * @Create In 2016年1月19日
 */
public class JzhWebRegOutInfo extends BaseInfo implements IParamInfo{
	/** 响应码. */
    private String respCode;
    /** 商户代码  */
    private String mchntCd;
	/** 响应码描述. */
    private String respMsg;
    /** 请求流水号. */
    private String mchntTxnSsn;
    /** 客户姓名. */
    private String custNm;
    /** 手机号码. */
    private String mobileNo;
    /** 身份证/证件 号码. */
    private String certifId;  
    /** 邮箱地址. */
    private String email;
    /** 开户行地区代码. */
    private String cityId;
    /** 开户行行别. */
    private String parentBankId;
    /** 开户行支行名称. */
    private String bankNm;
    /** 帐号. */
    private String capAcntNo;
    /** 用户在商户系统的标志. */
    private String userIdFrom;   
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
	public String getParam() {
		  StringBuilder paramSb = new StringBuilder();
	        
	        paramSb.append("respCode=").append(respCode)
	        		.append(", mchntCd=").append(mchntCd)
	                .append(", mchntTxnSsn=").append(mchntTxnSsn)
	                .append(", custNm=").append(custNm)
	                .append(", mobileNo=").append(mobileNo)
	                .append(", certifId=").append(certifId)
	                .append(", email=").append(email)
	                .append(", cityId=").append(cityId)
	                .append(", parentBankId=").append(parentBankId)
	                .append(", bankNm=").append(bankNm)
	                .append(", capAcntNo=").append(capAcntNo)
	                .append(", userIdFrom=").append(userIdFrom)
	                .append(", respMsg=").append(respMsg);
	        return paramSb.toString();
	}

	/**
	 * 响应码 的取得处理
	 * 
	 * @return 响应码
	 */
	public String getRespCode() {
		return respCode;
	}

	/**
	 * 响应码 的设定处理
	 * 
	 * @param respCode
	 */
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	/**
	 * 商户代码 的取得处理
	 * 
	 * @return 商户代码
	 */
	public String getMchntCd() {
		return mchntCd;
	}

	/**
	 * 商户代码 的设定处理
	 * 
	 * @param mchntCd
	 */
	public void setMchntCd(String mchntCd) {
		this.mchntCd = mchntCd;
	}

	/**
	 * 请求流水号 的取得处理
	 * 
	 * @return 请求流水号
	 */
	public String getMchntTxnSsn() {
		return mchntTxnSsn;
	}

	/**
	 * 请求流水号 的设定处理
	 * 
	 * @param mchntTxnSsn
	 */
	public void setMchntTxnSsn(String mchntTxnSsn) {
		this.mchntTxnSsn = mchntTxnSsn;
	}

	/**
	 * 请求响应码描述 的取得处理
	 * 
	 * @return 请求流水号
	 */
	public String getRespMsg() {
		return respMsg;
	}

	/**
	 * 请求响应码描述 的设定处理
	 * 
	 * @param respMsg
	 */
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	/**
	 * 客户姓名 的取得处理
	 * 
	 * @return 客户姓名
	 */
	public String getCustNm() {
		return custNm;
	}

	/**
	 * 客户姓名 的设定处理
	 * 
	 * @param custNm
	 */
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	/**
	 * 手机号码 的取得处理
	 * 
	 * @return 手机号码
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * 手机号码 的设定处理
	 * 
	 * @param mobileNo
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * 身份证号码 的取得处理
	 * 
	 * @return 身份证号码
	 */
	public String getCertifId() {
		return certifId;
	}

	/**
	 * 身份证号码 的设定处理
	 * 
	 * @param certifId
	 */
	public void setCertifId(String certifId) {
		this.certifId = certifId;
	}

	/**
	 * 邮箱地址 的取得处理
	 * 
	 * @return 邮箱地址
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 邮箱地址 的设定处理
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 开户行地区代码 的取得处理
	 * 
	 * @return 开户行地区代码
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * 开户行地区代码 的设定处理
	 * 
	 * @param cityId
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * 开户行行别 的取得处理
	 * 
	 * @return 开户行行别
	 */
	public String getParentBankId() {
		return parentBankId;
	}

	/**
	 * 开户行行别 的设定处理
	 * 
	 * @param parentBankId
	 */
	public void setParentBankId(String parentBankId) {
		this.parentBankId = parentBankId;
	}

	/**
	 * 开户行支行名称 的取得处理
	 * 
	 * @return 开户行支行名称
	 */
	public String getBankNm() {
		return bankNm;
	}

	/**
	 * 开户行支行名称 的设定处理
	 * 
	 * @param bankNm
	 */
	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}

	/**
	 * 帐号 的取得处理
	 * 
	 * @return 帐号
	 */
	public String getCapAcntNo() {
		return capAcntNo;
	}

	/**
	 * 帐号 的设定处理
	 * 
	 * @param capAcntNo
	 */
	public void setCapAcntNo(String capAcntNo) {
		this.capAcntNo = capAcntNo;
	}

	/**
	 * 用户在商户系统的标志 的取得处理
	 * 
	 * @return 用户在商户系统的标志
	 */
	public String getUserIdFrom() {
		return userIdFrom;
	}

	/**
	 * 用户在商户系统的标志 的设定处理
	 * 
	 * @param userIdFrom
	 */
	public void setUserIdFrom(String userIdFrom) {
		this.userIdFrom = userIdFrom;
	}

}
