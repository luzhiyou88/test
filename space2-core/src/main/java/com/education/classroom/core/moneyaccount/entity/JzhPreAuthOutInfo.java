package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseOutInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 预授权接口响应参数Bean.
 * @Class Name JzhPreAuthOutInfo
 * @author CHP_3.0 接口组
 * @Create In 2016年2月3日
 */
public class JzhPreAuthOutInfo extends BaseOutInfo implements IParamInfo {
    /** 商户代码  */
    private String mchntCd;
	/** 预授权合同号. */
    private String contractNo;
    /** 请求流水号. */
    private String mchntTxnSsn;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
	public String getParam() {
		  StringBuilder paramSb = new StringBuilder();
		  paramSb.append("retCode=").append(retCode)
	            .append(", retMsg=").append(retMsg)
                .append(", contractNo=").append(contractNo)
                .append(", mchntTxnSsn=").append(mchntTxnSsn);
		  return paramSb.toString();
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
	 * 预授权合同号 的取得处理
	 * 
	 * @return 预授权合同号
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * 预授权合同号 的设定处理
	 * 
	 * @param contractNo
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

}
