/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.comment.dao;

import java.util.List;
import java.util.Map;
import com.education.classroom.core.modules.spadmin.comment.entity.SpComment;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 评论回复DAO接口
 * @author 尚军伟
 * @version 2016/08/10
 */
@MyBatisDao
public interface SpCommentDao extends CrudDao<SpComment> {
	
	
	//查询话题关联的小组类型
	public String selectGroupType(String groupTopicId);
	
	//查询单个话题的评论
	//public List<SpComment> selectCommentList(String groupTopicId);
	List<SpComment> findPageList(Map<String, Object> queryMap);
}