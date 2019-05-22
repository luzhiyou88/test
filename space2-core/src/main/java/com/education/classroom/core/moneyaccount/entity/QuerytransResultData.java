package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;
/**
 * 交易查询明细集合ResultSet参数bean.
 * @Class Name QuerytransResultData
 * @author CHP_3.0 接口组
 * @Create In 2016年1月29日
 */
public class QuerytransResultData extends BaseInfo implements IParamInfo{
	/** 扩展类型. */
    private String extTp;
    /** 交易日期. */
    private String txnDate;
    /** 交易时分. */
    private String txnTime;
    /** 交易请求方式 */
    private String srcTp;
    /** 交易流水. */
    private String mchntSsn;
    /** 交易金额. */
    private String txnAmt;
    /** 成功交易金额 */
    private String txnAmtSuc;
    /** 合同号. */
    private String contractNo;
    /** 出账用户虚拟账户 */
    private String outFuiouAcctNo;
    /** 出账用户名. */
    private String outCustNo;
    /** 出账用户名称. */
    private String outArtifNm;
    /** 入账用户虚拟账户 */
    private String inFuiouAcctNo;
    /** 入账用户名. */
    private String inCustNo;
    /** 入账用户名称. */
    private String inArtifNm;
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
	                .append(", srcTp=").append(srcTp)
	                .append(", mchntSsn=").append(mchntSsn)
	                .append(", txnAmt=").append(txnAmt)
	                .append(", txnAmtSuc=").append(txnAmtSuc)
	                .append(", contractNo=").append(contractNo)
	                .append(", outFuiouAcctNo=").append(outFuiouAcctNo)
	                .append(", outCustNo=").append(outCustNo)
	                .append(", outArtifNm=").append(outArtifNm)
	                .append(", inFuiouAcctNo=").append(inFuiouAcctNo)
	                .append(", inCustNo=").append(inCustNo)
	                .append(", inArtifNm=").append(inArtifNm)
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

	public String getSrcTp() {
		return srcTp;
	}

	public void setSrcTp(String srcTp) {
		this.srcTp = srcTp;
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

	public String getTxnAmtSuc() {
		return txnAmtSuc;
	}

	public void setTxnAmtSuc(String txnAmtSuc) {
		this.txnAmtSuc = txnAmtSuc;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getOutFuiouAcctNo() {
		return outFuiouAcctNo;
	}

	public void setOutFuiouAcctNo(String outFuiouAcctNo) {
		this.outFuiouAcctNo = outFuiouAcctNo;
	}

	public String getOutCustNo() {
		return outCustNo;
	}

	public void setOutCustNo(String outCustNo) {
		this.outCustNo = outCustNo;
	}

	public String getOutArtifNm() {
		return outArtifNm;
	}

	public void setOutArtifNm(String outArtifNm) {
		this.outArtifNm = outArtifNm;
	}

	public String getInFuiouAcctNo() {
		return inFuiouAcctNo;
	}

	public void setInFuiouAcctNo(String inFuiouAcctNo) {
		this.inFuiouAcctNo = inFuiouAcctNo;
	}

	public String getInCustNo() {
		return inCustNo;
	}

	public void setInCustNo(String inCustNo) {
		this.inCustNo = inCustNo;
	}

	public String getInArtifNm() {
		return inArtifNm;
	}

	public void setInArtifNm(String inArtifNm) {
		this.inArtifNm = inArtifNm;
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
