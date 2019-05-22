package com.education.classroom.core.modules.spadmin.order.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;


/**
 * 订单表增删改查Entity
 * @author 赵新月
 * @version 2016/08/18
 */
public class SpOrder extends DataEntity<SpOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单号
	private String userId;		// 用户ID
	private String spaceId;		// 所属第二空间
	private String orderType;		// 订单类型
	private String auxiliaryId;		// 辅助ID
	private String payType;		// 支付类型
	private String orderState;		// 订单状态
	private String orderPrice;		// 订单价格
	private String userName;    //用户姓名
	private String spaceName;    //空间名称
	
	public SpOrder() {
		super();
	}

	public SpOrder(String id){
		super(id);
	}

	@Length(min=0, max=20, message="订单号长度必须介于 0 和 20 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=32, message="用户ID长度必须介于 0 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=32, message="所属第二空间长度必须介于 1 和 32 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Length(min=0, max=1, message="订单类型长度必须介于 0 和 1 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=32, message="辅助ID长度必须介于 0 和 32 之间")
	public String getAuxiliaryId() {
		return auxiliaryId;
	}

	public void setAuxiliaryId(String auxiliaryId) {
		this.auxiliaryId = auxiliaryId;
	}
	
	@Length(min=0, max=1, message="支付类型长度必须介于 0 和 1 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Length(min=0, max=1, message="订单状态长度必须介于 0 和 1 之间")
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	
	
}