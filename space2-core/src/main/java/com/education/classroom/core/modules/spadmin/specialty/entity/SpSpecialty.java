/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.specialty.entity;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.persistence.DataEntity;

/**
 * 专业管理Entity
 * 
 * @author 边磊
 * @version 2016-08-05
 */
public class SpSpecialty extends DataEntity<SpSpecialty> {
    private static final long serialVersionUID = -7842995298050582160L;
    private String spaceId; // 所属第二空间
    private String name; // 专业名称

    public SpSpecialty() {
        super();
    }

    public SpSpecialty(String id) {
        super(id);
    }

    @Length(min = 1, max = 32, message = "所属第二空间长度必须介于 1 和 32 之间")
    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    @Length(min = 1, max = 64, message = "专业名称长度必须介于 1 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}