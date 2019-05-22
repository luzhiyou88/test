package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseOutInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 解冻响应参数Bean.
 * @Class Name JzhUnFreezeOutInfo
 * @author CHP_3.0 接口组
 * @Create In 2016年2月2日
 */
public class JzhUnFreezeOutInfo extends BaseOutInfo implements IParamInfo{
    /** 商户代码  */
    private String mchntCd;
    /** 请求解冻金额  */
    private String amt;
	/** 成功解冻金额. */
    private String sucAmt;
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
	        		.append(", mchntCd=").append(mchntCd)
	                .append(", mchntTxnSsn=").append(mchntTxnSsn)
	            	.append(", amt=").append(amt)
	                .append(", sucAmt=").append(sucAmt);
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
	 * 请求解冻金额 的取得处理
	 * 
	 * @return 请求解冻金额
	 */
	public String getAmt() {
		return amt;
	}

	/**
	 * 请求解冻金额 的设定处理
	 * 
	 * @param amt
	 */
	public void setAmt(String amt) {
		this.amt = amt;
	}

	/**
	 * 成功解冻金额 的取得处理
	 * 
	 * @return 成功解冻金额
	 */
	public String getSucAmt() {
		return sucAmt;
	}

	/**
	 * 成功解冻金额 的设定处理
	 * 
	 * @param sucAmt
	 */
	public void setSucAmt(String sucAmt) {
		this.sucAmt = sucAmt;
	}
}
