/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.modules.spadmin.material.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.material.dao.SpMaterialsDao;
import com.education.classroom.core.modules.spadmin.material.entity.SpMaterials;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.SpringContextHolder;

/**
 * 材料管理Service
 * @author 路志友
 * @version 2016/08/17
 */
@Service
@Transactional(readOnly = true)
public class SpMaterialsService extends CrudService<SpMaterialsDao, SpMaterials> {


	private static SpMaterialsDao spMaterialsDao = SpringContextHolder
            .getBean(SpMaterialsDao.class);
	
	
	public SpMaterials get(String id) {
		return super.get(id);
	}
	
	public List<SpMaterials> findList(SpMaterials spMaterials) {
		return super.findList(spMaterials);
	}
	
	/*public Page<SpMaterials> findPage(Page<SpMaterials> page, SpMaterials spMaterials) {
		return super.findPage(page, spMaterials);
	}*/
	
	public Page<SpMaterials> findPage(Page<SpMaterials> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpMaterials> schems = spMaterialsDao.findList(filters);
		PageInfo<SpMaterials> resultPage = new PageInfo<SpMaterials>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SpMaterials spMaterials) {
		super.save(spMaterials);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpMaterials spMaterials) {
		super.delete(spMaterials);
	}
	
	public static Map<String, String> getSpMaterialsMap(boolean emptyRowFlag) {
        // 检索材料
        List<SpMaterials> ls = selSpMaterials();
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        if (emptyRowFlag) {
            map.put("", "请选择");
        }
        for (int i = 0; i < ls.size(); i++) {
            map.put(ls.get(i).getId(), ls.get(i).getName());
        }
        return map;
    }

	 public static List<SpMaterials> selSpMaterials() {
        // 检索材料
        List<SpMaterials> ls = spMaterialsDao.selSpMaterials();
        return ls;
    }
    
	public List<SpMaterials> findAllList(Map<String,Object> params){
		return dao.findAllList(params);
	}
	
	//验证材料名称唯一性
    public int checkName(String name,String id){
    	return spMaterialsDao.checkName(name,id);
    }
    
    //查看试卷是否关联材料
    public int checkProblemMaterial(String materialId){
    	return spMaterialsDao.checkProblemMaterial(materialId);
    }
}