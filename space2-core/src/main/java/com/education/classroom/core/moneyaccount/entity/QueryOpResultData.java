package com.education.classroom.core.moneyaccount.entity;

import java.util.List;

import com.education.classroom.core.moneyaccount.BaseInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;
/**
 * 明细查询opResultSet参数bean.
 * @Class Name QueryOpResultData
 * @author CHP_3.0 接口组
 * @Create In 2016年1月29日
 */
public class QueryOpResultData extends BaseInfo implements IParamInfo{
	/** 登录ID. */
    private String userId;
    /** 期初账面总余额. */
    private String ctBalance;
    /** 期初可用总余额. */
    private String caBalance;
    /** 期初未转结总余额 */
    private String cuBalance;
    /** 期初冻结总余额. */
    private String cfBalance;
    /** 明细查询QueryDetail集合   */
    private List<QueryDetailData> queryDetailData;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
	public String getParam() {
		  StringBuilder paramSb = new StringBuilder();
	        
	        paramSb.append("userId=").append(userId)
	        		.append(", ctBalance=").append(ctBalance)
	                .append(", caBalance=").append(caBalance)
	                .append(", cuBalance=").append(cuBalance)
	        		.append(", cfBalance=").append(cfBalance);
	        paramSb.append(", QueryDetailData [");
	        if (queryDetailData != null) {
	            for (int i = 0; i < queryDetailData.size(); i++) {
	                paramSb.append("{");
	                paramSb.append(queryDetailData.get(i).getParam());
	                paramSb.append("}");
	            }
	        }
	        paramSb.append("]");
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

	public List<QueryDetailData> getQueryDetailData() {
		return queryDetailData;
	}

	public void setQueryDetailData(List<QueryDetailData> queryDetailData) {
		this.queryDetailData = queryDetailData;
	}

}
