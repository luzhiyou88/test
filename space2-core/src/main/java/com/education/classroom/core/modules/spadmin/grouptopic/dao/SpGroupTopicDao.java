/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.grouptopic.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.modules.spadmin.grouptopic.entity.SpGroupTopic;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 小组话题表DAO接口
 * @author shangjunwei
 * @version 2016/08/10
 */
@MyBatisDao
public interface SpGroupTopicDao extends CrudDao<SpGroupTopic> {
	
	//根据小组ID查询小组类型
	public String SelectGroupType(String groupId);
	
	//根据type类型 查找话题列表
	public List<SpGroupTopic> selectGroupTopicByType(Map<String,Object> map);
	
	//查询组关联的话题列表
	public List<SpGroupTopic> selectAllGroupTopic(String groupId);
	
	public List<SpGroupTopic> selectAllGroupTopicPage(Map<String,Object> map);
	
	//根据话题ID查询小组类型
	public String SelectGroupTypeByTopicId(String groupTopicId);
	
	//设置话题为推荐
	public void updateGroupTopicRecommend(String id);
	
	//取消话题推荐
	public void cancelGroupTopicRecommend(String id);
	
	//查询话题评论数
	public int selectCountNumById(String groupTopicId);
}