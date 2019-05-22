package com.education.classroom.core.persistence;

import com.education.classroom.utils.IdGen;


/**
 * 实体基类
 * 使用场景：供生成runningNumber使用
 * @Class Name IdEntity
 * @author 张永生
 * @Create In 2015年12月5日
 */
public class IdEntity<T> extends BaseEntity<T> {
	
	private static final long serialVersionUID = -6438837813318584033L;
	
	protected Integer version = 1;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public void preInsert() {
		if (!this.isNewRecord){
			setId(IdGen.uuid());
		}
	}

	@Override
	public void preUpdate() {
		setVersion(this.version + 1);
	}
	
	
}
