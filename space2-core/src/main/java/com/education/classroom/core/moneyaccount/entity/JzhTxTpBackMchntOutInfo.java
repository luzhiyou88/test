package com.education.classroom.core.moneyaccount.entity;

import com.education.classroom.core.moneyaccount.BaseInfo;
import com.education.classroom.core.moneyaccount.IParamInfo;

/**
 * 提现退票通知接口响应参数Bean.
 * @Class Name JzhTxTpBackMchntOutInfo
 * @author CHP_3.0 接口组
 * @Create In 2016年2月21日
 */
public class JzhTxTpBackMchntOutInfo extends BaseInfo implements IParamInfo{
	/** 返回说明. */
	private String respMsg;

	/**
	 * 参数输出.
	 * 
	 * @return Bean中参数集合
	 */
	public String getParam() {
		StringBuilder paramSb = new StringBuilder();
		paramSb.append("respMsg=").append(respMsg);
		return paramSb.toString();
	}

	/**
	 * 返回说明 的取得处理
	 * 
	 * @return 返回说明
	 */
	public String getRespMsg() {
		return respMsg;
	}

	/**
	 * 返回说明 的设定处理
	 * 
	 * @param respMsg
	 */
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
}

