package com.education.classroom.core.modules.spadmin.resource.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.modules.spadmin.resource.dao.ResourceDao;
import com.education.classroom.core.modules.spadmin.resource.entity.SpResource;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.utils.IdGen;

/**
 * 资料库
 * @author 杨立明
 * @version 2016-08-10
 */
@Service
@Transactional(readOnly = true)
public class ResourceService extends CrudService<ResourceDao, SpResource> {
	@Autowired
	private ResourceDao resourceDao;
	
	public List<Map<String, String>> getCategorySelect(Map<String, Object> paraMap) {
		return resourceDao.getCategorySelect(paraMap);
	}
	public Page<SpResource> findPage(Page<SpResource> page, Map<String, Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpResource> dataList = resourceDao.findList(filters);
		PageInfo<SpResource> dataPage = new PageInfo<SpResource>(dataList);
		PageUtil.convertPage(dataPage, page);
		return page;
	}
	public Page<SpResource> findPage(Page<SpResource> page, SpResource spResource) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpResource> dataList = resourceDao.findList(spResource);
		PageInfo<SpResource> dataPage = new PageInfo<SpResource>(dataList);
		PageUtil.convertPage(dataPage, page);
		return page;
	}
	public SpResource getResourceById(Map<String,Object> paraMap) {
		return resourceDao.getResourceById(paraMap);
	}
	public Map<String,Object> getScoreList(Map<String,Object> paraMap) throws Exception {
		return this.getList4Page("getScoreList", ResourceDao.class, resourceDao, paraMap);
	}
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	public Map<String,Object> getList4Page(String returnMethod,Map<String,Object> paraMap) throws Exception {
		Class<?> classObject=ResourceDao.class;
		Object daoObject=resourceDao;
		return this.getList4Page(returnMethod, classObject, daoObject, paraMap);
	}
	//分页方法：returnMethod是其中要调用的函数，classObject是Dao接口的Class类型，daoObject表示定义的一个classObject类型的对象，paraMap是调用方法所用参数
	@SuppressWarnings("unchecked")
	public Map<String,Object> getList4Page(String returnMethod,Class<?> classObject,Object daoObject,Map<String,Object> paraMap) throws Exception {
		Map<String,Object> resultMap=new LinkedMap();//返回结构：是一个map，里面的前三个值是分页属性，第四个值是list<map>类型的查询结果
		List<Map<String,Object>> dataList;
		String pageSize=(String)paraMap.get("pageSize");
		String pageNo=(String)paraMap.get("pageNo");
		if(StringUtils.isBlank(pageSize) && StringUtils.isBlank(pageSize) ){//都是空，说明不用分页
			//dataList=centerDao.getCourseList(paraMap);
			dataList=(List<Map<String,Object>>) classObject.getMethod(returnMethod, Map.class).invoke(daoObject, paraMap);
		}else{
			//查询总数start
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Connection connection = sqlSession.getConnection();
			Statement sm = connection.createStatement();
			String mapperSql = SqlHelper.getMapperSql(sqlSessionFactory.openSession(),classObject.getName()+"."+returnMethod,paraMap);
			//"com.education.classroom.modules.spadmin.center.dao.CenterDao.getCourseList"
			String mapperSqlLower=mapperSql.toLowerCase().replaceAll("\\s"," ");
			int i=-1;
			if((i=mapperSqlLower.lastIndexOf(" order "))!=-1){
				System.out.println("order i="+i);
				mapperSql=mapperSql.substring(0,i);
			}
			if((i=mapperSqlLower.lastIndexOf(" limt "))!=-1){
				System.out.println("limit i="+i);
				mapperSql=mapperSql.substring(0,i);
			}
			mapperSql="SELECT count(0) "+mapperSql.substring(mapperSqlLower.lastIndexOf(" from "));
			System.out.println("查询总数："+mapperSql);
			ResultSet rs = sm.executeQuery(mapperSql);
			long count=0;
			if(rs.next()){
				count=rs.getLong(1);
			}
			System.out.println("总数："+count);
			if(connection!=null){
				//connection.close();
			}
			//查询总数end
			
			if(StringUtils.isBlank(pageSize) || !pageSize.matches("[0-9]+") ){
				pageSize="20";
			}
			if(StringUtils.isBlank(pageNo) || !pageNo.matches("[0-9]+") ){
				pageNo="1";
			}
			long startIndex=Integer.parseInt(pageSize)*(Integer.parseInt(pageNo)-1);
			paraMap.put("startIndex", startIndex);//查询条件，解决在mapper中无法运算
			resultMap.put("count", count);//总数目
			if(startIndex>=count || count<=0){
				dataList=new ArrayList<Map<String,Object>>();
			}else{
				//dataList=centerDao.getCourseList(paraMap);
				dataList=(List<Map<String,Object>>) classObject.getMethod(returnMethod, Map.class).invoke(daoObject, paraMap);
			}
		}
		resultMap.put("pageSize",pageSize);
		resultMap.put("pageNo",pageNo);
		resultMap.put("list", dataList);
		
		return resultMap;
	}
	public Page<SpResource> getMyResourceList(Page<SpResource> page,SpResource resource) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpResource> dataList = resourceDao.getMyResourceList(resource);
		PageInfo<SpResource> dataPage = new PageInfo<SpResource>(dataList);
		PageUtil.convertPage(dataPage, page);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void saveDownload(Map<String, Object> paraMap) {
		int hasDownload=resourceDao.hasDownload(paraMap);
		if(hasDownload==0){
			paraMap.put("id", IdGen.uuid());
			paraMap.put("type", 2);
			paraMap.put("createDate", new Date());
			resourceDao.saveDownload(paraMap);
		}
		int hasDownloadNum=resourceDao.hasDownloadNum(paraMap);
		if(hasDownloadNum==0){
			paraMap.put("id", IdGen.uuid());
			resourceDao.insertDownloadNum(paraMap);
		}else{
			resourceDao.updateDownloadNum(paraMap);
		}
	}
	
	@Transactional(readOnly = false)
	public void saveReadNum(Map<String, Object> paraMap) {
		int hasReadNum=resourceDao.hasReadNum(paraMap);
		if(hasReadNum==0){
			paraMap.put("id", IdGen.uuid());
			resourceDao.insertReadNum(paraMap);
		}else{
			resourceDao.updateReadNum(paraMap);
		}
	}
	@Transactional(readOnly = false)
	public void saveClickNum(Map<String, Object> paraMap) {
		int hasClickNum=resourceDao.hasClickNum(paraMap);
		if(hasClickNum==0){
			paraMap.put("id", IdGen.uuid());
			resourceDao.insertClickNum(paraMap);
		}else{
			resourceDao.updateClickNum(paraMap);
		}
	}
	
	public List<SpResource> getResourceListInIds(SpResource resource) {
		return resourceDao.getResourceListInIds(resource);
	}
	
	
}
