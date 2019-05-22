package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;
/**
 * 充值提现查询明细集合ResultSet参数bean.
 * @Class Name QuerycztxResultData
 * @author CHP_3.0 接口组
 * @Create In 2016年2月2日
 */
public class QuerycztxResultData extends BaseInfo implements IParamInfo{
	/** 扩展类型. */
    private String extTp;
    /** 充值提现日期. */
    private String txnDate;
    /** 充值提现时分. */
    private String txnTime;
    /** 充值提现流水. */
    private String mchntSsn;
    /** 充值提现金额. */
    private String txnAmt;
    /** 用户虚拟账户 */
    private String fuiouAcctNo;
    /** 用户名. */
    private String custNo;
    /** 用户名称. */
    private String artifNm;
    /** 交易备注 */
    private String remark;
    /** 交易返回码. */
    private String txnRspCd;
    /** 交易返回码描述. */
    private String rspCdDesc;

    /**
     * 参数输出.
     * @return Bean中参数集合
     */
	public String getParam() {
		  StringBuilder paramSb = new StringBuilder();
	        
	        paramSb.append("extTp=").append(extTp)
	        		.append(", txnDate=").append(txnDate)
	                .append(", txnTime=").append(txnTime)
	                .append(", mchntSsn=").append(mchntSsn)
	                .append(", txnAmt=").append(txnAmt)
	                .append(", fuiouAcctNo=").append(fuiouAcctNo)
	                .append(", custNo=").append(custNo)
	                .append(", artifNm=").append(artifNm)
	                .append(", remark=").append(remark)
	                .append(", txnRspCd=").append(txnRspCd)
	        		.append(", rspCdDesc=").append(rspCdDesc);
	        return paramSb.toString();
	}

	public String getExtTp() {
		return extTp;
	}

	public void setExtTp(String extTp) {
		this.extTp = extTp;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getMchntSsn() {
		return mchntSsn;
	}

	public void setMchntSsn(String mchntSsn) {
		this.mchntSsn = mchntSsn;
	}

	public String getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}


	public String getFuiouAcctNo() {
		return fuiouAcctNo;
	}

	public void setFuiouAcctNo(String fuiouAcctNo) {
		this.fuiouAcctNo = fuiouAcctNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getArtifNm() {
		return artifNm;
	}

	public void setArtifNm(String artifNm) {
		this.artifNm = artifNm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTxnRspCd() {
		return txnRspCd;
	}

	public void setTxnRspCd(String txnRspCd) {
		this.txnRspCd = txnRspCd;
	}

	public String getRspCdDesc() {
		return rspCdDesc;
	}

	public void setRspCdDesc(String rspCdDesc) {
		this.rspCdDesc = rspCdDesc;
	}

}
