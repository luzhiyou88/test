/**
 *Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.score.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.classroom.core.modules.spadmin.score.entity.SpScore;
import com.education.classroom.core.modules.spadmin.score.entity.UserSpScore;
import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 评价DAO接口
 * 
 * @author 边磊
 * @version 2016/08/09
 */
@MyBatisDao
public interface SpScoreDao extends CrudDao<SpScore> {
	List<UserSpScore> findPageList(Map<String, Object> queryMap);

	Double findAVG(@Param(value = "sourceId") String sourceId);

}