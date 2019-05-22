package com.education.classroom.core.modules.spadmin.ordertran.entity;


import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 订单流水表增删改查Entity
 * @author 赵新月
 * @version 2016/08/17
 */
public class SpOrderTran extends DataEntity<SpOrderTran> {
	
	private static final long serialVersionUID = 1L;
	private String tranNo;		// 流水号
	private Date tranDate;		// 流水生成时间
	private String orderNo;		// 订单号
	private String orderType;		// 订单类型 ( 0 课程 ;1 会员)
	private String orderState;		// 订单状态（1 申请支付; 2 支付成功 ;3 支付失败; 4 申请退款; 5 退款成功; 6退款失败）
	private String payAmount;		// 支付金额
	private String gwTranNo;		// 网关流水号
	private String gwResult;		// 网关结果
	private String gwDate;		// 网关时间
	
	public SpOrderTran() {
		super();
	}

	public SpOrderTran(String id){
		super(id);
	}

	@Length(min=0, max=20, message="流水号长度必须介于 0 和 20 之间")
	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}
	
	@Length(min=0, max=20, message="订单号长度必须介于 0 和 20 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=1, message="订单类型 ( 0 课程 ;1 会员)长度必须介于 0 和 1 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=1, message="订单状态（1 申请支付; 2 支付成功 ;3 支付失败; 4 申请退款; 5 退款成功; 6退款失败）长度必须介于 0 和 1 之间")
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
	@Length(min=0, max=24, message="网关流水号长度必须介于 0 和 24 之间")
	public String getGwTranNo() {
		return gwTranNo;
	}

	public void setGwTranNo(String gwTranNo) {
		this.gwTranNo = gwTranNo;
	}
	
	@Length(min=0, max=1, message="网关结果长度必须介于 0 和 1 之间")
	public String getGwResult() {
		return gwResult;
	}

	public void setGwResult(String gwResult) {
		this.gwResult = gwResult;
	}
	
	@Length(min=0, max=14, message="网关时间长度必须介于 0 和 14 之间")
	public String getGwDate() {
		return gwDate;
	}

	public void setGwDate(String gwDate) {
		this.gwDate = gwDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}