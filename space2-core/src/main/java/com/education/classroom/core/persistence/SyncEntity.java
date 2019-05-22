package com.education.classroom.core.persistence;

import java.util.Date;

import com.education.classroom.utils.IdGen;


/**
 * 同步实体
 * 使用场景:系统管理模块同步数据到各业务模块时使用
 * 作用:同步中间表实体使用
 * @Class Name SyncEntity
 * @author 张永生
 * @Create In 2015年12月3日
 * @param <T>
 */
public class SyncEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = -6496034173926986480L;
	protected String createBy;	// 创建者
	protected Date createTime;	// 创建时间
	protected Date modifyTime;  // 更新时间,程序更新时的时间,没有更新者

	@Override
	public void preInsert() {
		if (!this.isNewRecord){
			setId(IdGen.uuid());
		}
		this.createBy = "admin";
		this.createTime = new Date();
	}

	@Override
	public void preUpdate() {
		this.modifyTime = new Date();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	
}
