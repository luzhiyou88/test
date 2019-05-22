package com.education.classroom.core.dict.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.dict.entity.Dict;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 字典DAO接口
 * @Class Name DictDao
 * @author 张永生
 * @Create In 2015年12月1日
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
	/**
	 * 条件查询字典
	 * 2015年12月24日
	 * By 张永生
	 * @param params
	 * @return
	 */
	public List<Dict> findListByParams(Map<String,Object> params);
	
	/**
	 * 根据条件查询唯一的字典项
	 * 2015年12月24日
	 * By 张永生
	 * @param params
	 * @return
	 */
	public Dict getByParams(Map<String,Object> params);
	
	/**
	 * 根据多个类型查询
	 * @param typeList
	 * @return
	 */
	public List<Dict> findListByTypes(List<String> typeList);
	
	/**
	 * 根据父类id查找list
	 * 2016年1月19日
	 * By 路志友
	 * @param parentId
	 * @return
	 */
	public List<Dict> getDictListByPId(String parentId);
	
}
