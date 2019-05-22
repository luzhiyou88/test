package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 用户信息查询 明细参数Bean.
 * @Class Name QueryUserInfsDetailData
 * @author CHP_3.0 接口组
 * @Create In 2016年1月29日
 */
public class QueryUserInfsDetailData extends BaseInfo implements IParamInfo{
    /** 手机号码. */
    private String mobileNo;
    /** 客户姓名  */
    private String custNm;
	/** 身份证号码. */
    private String certifId;
    /** 邮箱地址. */
    private String email;
    /** 开户行地区代码. */
    private String cityId;
    /** 开户行行别  */
    private String parentBankId;
	/** 开户行支行名称. */
    private String bankNm;
    /** 用户状态. */
    private String userSt;
    /** 帐号. */
    private String capAcntNo;
    /** 卡密认证状态. */
    private String cardPwdVerifySt;
    /** 账户信息验证状态  */
    private String idNmVerifySt;
	/** 账户生效状态. */
    private String contractSt;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
	public String getParam() {
    	  StringBuilder paramSb = new StringBuilder();
	        
	        paramSb.append("mobileNo=").append(mobileNo)
	        		.append(", custNm=").append(custNm)
	                .append(", certifId=").append(certifId)
	                .append(", email=").append(email)
		            .append(", cityId=").append(cityId)
		            .append(", parentBankId=").append(parentBankId)
		            .append(", bankNm=").append(bankNm)
		            .append(", userSt=").append(userSt)
			        .append(", capAcntNo=").append(capAcntNo)
		            .append(", cardPwdVerifySt=").append(cardPwdVerifySt)
		            .append(", idNmVerifySt=").append(idNmVerifySt)
		            .append(", contractSt=").append(contractSt);
	        return paramSb.toString();
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCustNm() {
		return custNm;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public String getCertifId() {
		return certifId;
	}

	public void setCertifId(String certifId) {
		this.certifId = certifId;
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

	public String getCapAcntNo() {
		return capAcntNo;
	}

	public void setCapAcntNo(String capAcntNo) {
		this.capAcntNo = capAcntNo;
	}

	public String getCardPwdVerifySt() {
		return cardPwdVerifySt;
	}

	public void setCardPwdVerifySt(String cardPwdVerifySt) {
		this.cardPwdVerifySt = cardPwdVerifySt;
	}

	public String getIdNmVerifySt() {
		return idNmVerifySt;
	}

	public void setIdNmVerifySt(String idNmVerifySt) {
		this.idNmVerifySt = idNmVerifySt;
	}

	public String getContractSt() {
		return contractSt;
	}

	public void setContractSt(String contractSt) {
		this.contractSt = contractSt;
	}

	public String getUserSt() {
		return userSt;
	}

	public void setUserSt(String userSt) {
		this.userSt = userSt;
	}

}
