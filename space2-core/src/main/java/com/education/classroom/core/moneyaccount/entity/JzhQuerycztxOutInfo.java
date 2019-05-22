package com.education.classroom.core.moneyaccount.entity;

import java.util.List;

import com.education.classroom.core.moneyaccount.BaseOutInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 充值提现查询接口响应参数Bean.
 * @Class Name JzhQuerycztxOutInfo
 * @author CHP_3.0 接口组
 * @Create In 2016年2月2日
 */
public class JzhQuerycztxOutInfo extends BaseOutInfo implements IParamInfo{
    /** 商户代码  */
    private String mchntCd;
    /** 请求流水号. */
    private String mchntTxnSsn;
    /** 业务类型. */
    private String busiTp;   
    /** 总记录数. */
    private String totalNumber;
    /** 明细查询ResultSet. */
    private List<QuerycztxResultData> relist;

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
	            .append(", busiTp=").append(busiTp)
	            .append(", totalNumber=").append(totalNumber);
	        paramSb.append(", relist [");
	        if (relist != null) {
	            for (int i = 0; i < relist.size(); i++) {
	                paramSb.append("{");
	                paramSb.append(relist.get(i).getParam());
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
	 * 业务类型 的取得处理.
	 *
	 * @return 业务类型
	 */
	public String getBusiTp() {
		return busiTp;
	}

	/**
	 * 业务类型 的设定处理.
	 *
	 * @param busiTp
	 *            业务类型
	 */
	public void setBusiTp(String busiTp) {
		this.busiTp = busiTp;
	}

	/**
	 * 总记录数 的取得处理.
	 *
	 * @return 总记录数
	 */
	public String getTotalNumber() {
		return totalNumber;
	}

	/**
	 * 总记录数 的设定处理.
	 *
	 * @param totalNumber
	 *            总记录数
	 */
	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

	/**
	 * 充值提现查询ResultSet 的取得处理.
	 *
	 * @return 充值提现查询ResultSet
	 */
	public List<QuerycztxResultData> getRelist() {
		return relist;
	}

	/**
	 * 充值提现查询ResultSet 的设定处理.
	 *
	 * @param relist
	 *            充值提现查询ResultSet
	 */
	public void setRelist(List<QuerycztxResultData> relist) {
		this.relist = relist;
	}

}
