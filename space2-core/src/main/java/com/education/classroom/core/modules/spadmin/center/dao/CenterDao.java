package com.education.classroom.core.modules.spadmin.center.dao;

import java.util.List;
import java.util.Map;

import com.education.classroom.core.persistence.annotation.MyBatisDao;

/**
 * 个人中心
 * @author 杨立明
 * @version 2016-08-08
 */
@MyBatisDao
public interface CenterDao{

	Map<String,Object> getUserById(Map<String,Object> map);
	void updateUserById(Map<String,Object> map);
	//保存用户详细资料时，同步新增或修改用户扩展信息
	void updateUserInfoById(Map<String,Object> map);
	void insertUserInfoById(Map<String,Object> map);
	int hasUserInfoById(Map<String,Object> map);
	Map<String, Object> getUserInfoById(Map<String, Object> map);
	//保存头像
	void updatePhoto(Map<String,Object> paraMap);

	//更改绑定手机时判断先手机号码是否存在
	long hasMobile(Map<String, Object> paraMap);
	//验证码
	String getMobileCode(Map<String,Object> map);
	void insertMobileCode(Map<String,Object> map);
	void updateMobileCode(Map<String,Object> map);
	
	//进入首页，查询是否是会员
	Map<String,Object> getMember(Map<String,Object> paraMap);
	//进入首页，获取小组和话题数目
	String getGroupNum(Map<String,Object> paraMap);
	String getTopicNum(Map<String,Object> paraMap);
	void delTopic(Map<String,Object> paraMap);
	
	//课程
	List<Map<String,Object>> getCourseList(Map<String,Object> paraMap);
	Map<String,Object> getCourseById(Map<String,Object> paraMap);
	
	//小组话题
	List<Map<String,Object>> getTopicList(Map<String,Object> paraMap);
	
	//购买会员记录
	List<Map<String,Object>> getBuyMemberLogList(Map<String,Object> paraMap);//弃用
	List<Map<String, Object>> getOrderLog(Map<String, Object> paraMap);//调用平台订单表
	//购买课程，补全信息
	Map<String, Object> getCourseOneById(Map<String, Object> paraMap);
	
	//测试
	Map<String, Object> getExamByCourseId(Map<String, Object> paraMap);
	List<Map<String, Object>> getProblemListByExamId(Map<String, Object> paraMap);
	Map<String, Object> getProblemById(Map<String, Object> paraMap);
	
	//保存试题答案
	int hasAnswer(Map<String, Object> paraMap);
	void insertAnswer(Map<String, Object> paraMap);
	void updateAnswer(Map<String, Object> paraMap);
	
	//保存试卷得分
	void saveAnswerScore(Map<String, Object> paraMap);
	void updtAnswerScore(Map<String, Object> paraMap);
	int hasAnswerScore(Map<String, Object> paraMap);
}


