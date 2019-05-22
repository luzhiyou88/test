package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 明细查询QueryDetail参数bean  .
 * @Class Name QueryDetailData
 * @author CHP_3.0 接口组
 * @Create In 2016年1月29日
 */
public class QueryDetailData extends BaseInfo implements IParamInfo{
	/** 交易流水号. */
    private String transSsn;
    /** 记账时间点. */
    private String recCrtTs;
    /** 账面余额出账金额. */
    private String ctDebitAmt;
    /** 账面余额入账金额 */
    private String ctCreditAmt;
    /** 可用余额出账金额. */
    private String caDebitAmt;
    /** 可用余额入账金额. */
    private String caCreditAmt;
    /** 未转结余额出账金额. */
    private String cuDebitAmt;
    /** 未转结余额入账金额. */
    private String cuCreditAmt;
    /** 冻结余额出账金额. */
    private String cfDebitAmt;
    /** 冻结余额入账金额. */
    private String cfCreditAmt;
    /** 账面余额. */
    private String ctBalance;
    /** 可用余额. */
    private String caBalance;
    /** 未转接余额. */
    private String cuBalance;
    /** 冻结余额. */
    private String cfBalance;
    /** 摘要信息. */
    private String bookDigest;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
	public String getParam() {
		  StringBuilder paramSb = new StringBuilder();
	        
	        paramSb.append("transSsn=").append(transSsn)
	        		.append(", recCrtTs=").append(recCrtTs)
	                .append(", ctDebitAmt=").append(ctDebitAmt)
	                .append(", ctCreditAmt=").append(ctCreditAmt)
	        		.append(", caDebitAmt=").append(caDebitAmt)
			        .append(", caCreditAmt=").append(caCreditAmt)
		            .append(", cuDebitAmt=").append(cuDebitAmt)
		            .append(", cuCreditAmt=").append(cuCreditAmt)
		    		.append(", cfDebitAmt=").append(cfDebitAmt)
			        .append(", cfCreditAmt=").append(cfCreditAmt)
		            .append(", ctBalance=").append(ctBalance)
		            .append(", caBalance=").append(caBalance)
		    		.append(", cuBalance=").append(cuBalance)
			        .append(", cfBalance=").append(cfBalance)
		            .append(", bookDigest=").append(bookDigest);
	        return paramSb.toString();
	}

	public String getTransSsn() {
		return transSsn;
	}

	public void setTransSsn(String transSsn) {
		this.transSsn = transSsn;
	}

	public String getRecCrtTs() {
		return recCrtTs;
	}

	public void setRecCrtTs(String recCrtTs) {
		this.recCrtTs = recCrtTs;
	}

	public String getCtDebitAmt() {
		return ctDebitAmt;
	}

	public void setCtDebitAmt(String ctDebitAmt) {
		this.ctDebitAmt = ctDebitAmt;
	}

	public String getCtCreditAmt() {
		return ctCreditAmt;
	}

	public void setCtCreditAmt(String ctCreditAmt) {
		this.ctCreditAmt = ctCreditAmt;
	}

	public String getCaDebitAmt() {
		return caDebitAmt;
	}

	public void setCaDebitAmt(String caDebitAmt) {
		this.caDebitAmt = caDebitAmt;
	}

	public String getCaCreditAmt() {
		return caCreditAmt;
	}

	public void setCaCreditAmt(String caCreditAmt) {
		this.caCreditAmt = caCreditAmt;
	}

	public String getCuDebitAmt() {
		return cuDebitAmt;
	}

	public void setCuDebitAmt(String cuDebitAmt) {
		this.cuDebitAmt = cuDebitAmt;
	}

	public String getCuCreditAmt() {
		return cuCreditAmt;
	}

	public void setCuCreditAmt(String cuCreditAmt) {
		this.cuCreditAmt = cuCreditAmt;
	}

	public String getCfDebitAmt() {
		return cfDebitAmt;
	}

	public void setCfDebitAmt(String cfDebitAmt) {
		this.cfDebitAmt = cfDebitAmt;
	}

	public String getCfCreditAmt() {
		return cfCreditAmt;
	}

	public void setCfCreditAmt(String cfCreditAmt) {
		this.cfCreditAmt = cfCreditAmt;
	}

	public String getCtBalance() {
		return ctBalance;
	}

	public void setCtBalance(String ctBalance) {
		this.ctBalance = ctBalance;
	}

	public String getCaBalance() {
		return caBalance;
	}

	public void setCaBalance(String caBalance) {
		this.caBalance = caBalance;
	}

	public String getCuBalance() {
		return cuBalance;
	}

	public void setCuBalance(String cuBalance) {
		this.cuBalance = cuBalance;
	}

	public String getCfBalance() {
		return cfBalance;
	}

	public void setCfBalance(String cfBalance) {
		this.cfBalance = cfBalance;
	}

	public String getBookDigest() {
		return bookDigest;
	}

	public void setBookDigest(String bookDigest) {
		this.bookDigest = bookDigest;
	}

}
