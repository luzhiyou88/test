package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 商户端个人用户跳转登录页面（网页版）响应参数Bean.
 * @Class Name JzhWebLoginOutInfo
 * @author CHP_3.0 接口组
 * @Create In 2016年2月22日
 */
public class JzhWebLoginOutInfo extends BaseInfo implements IParamInfo{
	/** 响应码. */
	private String respCode;
	/** 商户代码 */
	private String mchntCd;
	/** 响应码描述. */
	private String respMsg;
	/** 请求流水号. */
	private String mchntTxnSsn;

	/**
	 * 参数输出.
	 * 
	 * @return Bean中参数集合
	 */
	public String getParam() {
		StringBuilder paramSb = new StringBuilder();
		paramSb.append("respCode=").append(respCode)
				.append(", mchntCd=").append(mchntCd)
				.append(", mchntTxnSsn=").append(mchntTxnSsn)
				.append(", respMsg=").append(respMsg);
		return paramSb.toString();
	}

	/**
	 * 响应码 的取得处理
	 * 
	 * @return 响应码
	 */
	public String getRespCode() {
		return respCode;
	}

	/**
	 * 响应码 的设定处理
	 * 
	 * @param respCode
	 */
	public void setRespCode(String respCode) {
		this.respCode = respCode;
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
	 * 请求响应码描述 的取得处理
	 * 
	 * @return 请求响应码描述
	 */
	public String getRespMsg() {
		return respMsg;
	}

	/**
	 * 请求响应码描述 的设定处理
	 * 
	 * @param respMsg
	 */
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
}

