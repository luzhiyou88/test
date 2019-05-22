package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;
/**
 * 余额查询QueryBalanceDetailData集合参数bean  .
 * @Class Name QueryBalanceDetailData
 * @author CHP_3.0 接口组
 * @Create In 2016年2月1日
 */
public class QueryBalanceDetailData extends BaseInfo implements IParamInfo{
	/** 用户名. */
    private String userId;
    /** 账面总余额. */
    private String ctBalance;
    /** 可用余额. */
    private String caBalance;
    /** 冻结余额. */
    private String cfBalance;
    /** 未转结余额. */
    private String cuBalance;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
	public String getParam() {
		  StringBuilder paramSb = new StringBuilder();
	        
	        paramSb.append("userId=").append(userId)
	        		.append(", ctBalance=").append(ctBalance)
	                .append(", caBalance=").append(caBalance)
	                .append(", cfBalance=").append(cfBalance)
	        		.append(", cuBalance=").append(cuBalance);
	        return paramSb.toString();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getCfBalance() {
		return cfBalance;
	}

	public void setCfBalance(String cfBalance) {
		this.cfBalance = cfBalance;
	}

	public String getCuBalance() {
		return cuBalance;
	}

	public void setCuBalance(String cuBalance) {
		this.cuBalance = cuBalance;
	}

}
