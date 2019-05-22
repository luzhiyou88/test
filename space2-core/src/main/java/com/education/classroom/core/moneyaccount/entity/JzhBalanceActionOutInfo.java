package com.education.classroom.core.moneyaccount.entity;

import java.util.List;

import com.education.classroom.core.moneyaccount.BaseOutInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 余额查询响应参数Bean.
 * @Class Name JzhBalanceActionOutInfo
 * @author CHP_3.0 接口组
 * @Create In 2016年2月1日
 */
public class JzhBalanceActionOutInfo extends BaseOutInfo implements IParamInfo{
    /** 商户代码  */
    private String mchntCd;
    /** 请求流水号. */
    private String mchntTxnSsn;
    /** 待查询的登录帐户. */
    private String custNo;
    /** 余额明细集合. */
    private List<QueryBalanceDetailData> retList ;
    
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
	        		.append(", custNo=").append(custNo);
	      paramSb.append(", retList [");
	      if (retList != null) {
	          for (int i = 0; i < retList.size(); i++) {
	              paramSb.append("{");
	              paramSb.append(retList.get(i).getParam());
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
	 * 余额明细集合 的取得处理.
	 *
	 * @return 余额明细集合
	 */
	public List<QueryBalanceDetailData> getRetList() {
		return retList;
	}

	/**
	 * 余额明细集合 的设定处理.
	 *
	 * @param retList
	 *            余额明细集合
	 */
	public void setRetList(List<QueryBalanceDetailData> retList) {
		this.retList = retList;
	}

	/**
	 * 待查询的登录帐户 的取得处理.
	 *
	 * @return 待查询的登录帐户
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 待查询的登录帐户 的设定处理.
	 *
	 * @param custNo
	 *            待查询的登录帐户
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

}
