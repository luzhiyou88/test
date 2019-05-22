package com.education.classroom.core.moneyaccount.entity;

import java.util.List;

import com.education.classroom.core.moneyaccount.BaseOutInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 明细查询响应参数Bean.
 * @Class Name JzhQueryOutInfo
 * @author CHP_3.0 接口组
 * @Create In 2016年1月29日
 */
public class JzhQueryOutInfo extends BaseOutInfo implements IParamInfo{
    /** 商户代码  */
    private String mchntCd;
    /** 请求流水号. */
    private String mchntTxnSsn;
    /** 明细查询opResultSet. */
    private List<QueryOpResultData> queryOpResultData;

    /**
     * 参数输出.
     * @return Bean中参数集合
     */
	public String getParam() {
	    StringBuilder paramSb = new StringBuilder();
	    paramSb.append("retCode=").append(retCode)
	        .append(", retMsg=").append(retMsg)
	        .append(", mchntCd=").append(mchntCd)
	        .append(", mchntTxnSsn=").append(mchntTxnSsn);
	    paramSb.append(", queryOpResultData [");
	    if (queryOpResultData != null) {
	        for (int i = 0; i < queryOpResultData.size(); i++) {
	            paramSb.append("{");
	            paramSb.append(queryOpResultData.get(i).getParam());
	            paramSb.append("}");
	        }
	    }
	    paramSb.append("]");
	    return paramSb.toString();
	}

	/**
	 * 商户代码 的取得处理.
	 *
	 * @return 商户代码
	 */
	public String getMchntCd() {
		return mchntCd;
	}

	/**
	 * 商户代码 的设定处理.
	 *
	 * @param mchntCd
	 *            商户代码
	 */
	public void setMchntCd(String mchntCd) {
		this.mchntCd = mchntCd;
	}

	/**
	 * 请求流水号 的取得处理.
	 *
	 * @return 请求流水号
	 */
	public String getMchntTxnSsn() {
		return mchntTxnSsn;
	}

	/**
	 * 请求流水号 的设定处理.
	 *
	 * @param mchntTxnSsn
	 *            请求流水号
	 */
	public void setMchntTxnSsn(String mchntTxnSsn) {
		this.mchntTxnSsn = mchntTxnSsn;
	}

	/**
	 * 明细查询opResultSet 的取得处理.
	 *
	 * @return 明细查询opResultSet
	 */
	public List<QueryOpResultData> getQueryOpResultData() {
		return queryOpResultData;
	}

	/**
	 * 明细查询opResultSet 的设定处理.
	 *
	 * @param queryOpResultData
	 *            明细查询opResultSet
	 */
	public void setQueryOpResultData(List<QueryOpResultData> queryOpResultData) {
		this.queryOpResultData = queryOpResultData;
	}
}
