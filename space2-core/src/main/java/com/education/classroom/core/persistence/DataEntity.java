package com.education.classroom.core.persistence;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.utils.IdGen;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 数据Entity类
 * 
 * @Class Name DataEntity
 * @author 张永生
 * @Create In 2015年12月15日
 * @param <T>
 */
public class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;

	protected String remarks; // 备注
	protected String createBy; // 创建者
	protected Date createDate; // 创建时间
	protected String updateBy; // 更新者
	protected Date updateDate; // 更新时间
	protected String delFlag; // 删除标记（0:正常;1:删除）

	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}

	public DataEntity(String id) {
		super(id);
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert() {
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord) {
			setId(IdGen.uuid());
		}
		String userId = UserUtils.getUserId();
		if (StringUtils.isNotBlank(userId)) {
			setCreateBy(userId);
			setUpdateBy(userId);
		}
		setCreateDate(new Date());
		setUpdateDate(getCreateDate());
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */

	public void preInsertNoId() {
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
	
		String userId = UserUtils.getUserId();
		if (StringUtils.isNotBlank(userId)) {
			setCreateBy(userId);
			setUpdateBy(userId);
		}
		setCreateDate(new Date());
		setUpdateDate(getCreateDate());
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate() {
		String userId = UserUtils.getUserId();
		if (StringUtils.isNotBlank(userId)) {
			setUpdateBy(userId);
		}
		setUpdateDate(new Date());
	}

	@Length(min = 0, max = 256)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@JsonIgnore
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@Length(min = 1, max = 1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
