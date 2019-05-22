/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.material.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.material.entity.SpMaterials;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 材料管理DAO接口
 * @author 路志友
 * @version 2016/08/17
 */
@MyBatisDao
public interface SpMaterialsDao extends CrudDao<SpMaterials> {
	
	//查询材料列表键值对
    List<SpMaterials> selSpMaterials();
    
    //验证材料名称唯一性
    public int checkName(@Param(value = "name") String name,@Param(value = "id") String id);
    
    //查看试卷是否关联材料
    public int checkProblemMaterial(String materialId);
}