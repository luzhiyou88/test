package com.education.classroom.utils.ccutils;

/**
 * 功能：设置帐户有关信息及返回路径（基础配置页面）
 * 版本：2.1.2
 * 日期：2013-11-13
 * 作者：chu
 **/
public class CCConfig {
	public static String userid = "9E3A090F3004F27F";
	public static String key = "EfawXi8QtuAiDcaD214pKEHmA8Qfi8wZ";

	// notify_url 视频上传过程中服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	public static String notify_url = "http://apidemo.test.bokecc.com/java/utf8/notify.jsp";
	//	api_videos api获取视频信息接口
	public static String api_videos = "http://spark.bokecc.com/api/videos";
	//	api_userinfo api获取用户信息接口
	public static String api_user = "http://spark.bokecc.com/api/user";
	// api_video api获取指定视频接口
	public static String api_video = "http://spark.bokecc.com/api/video";
	// api_category api获取用户全部分类接口
	public static String api_category = "http://spark.bokecc.com/api/video/category";
	// api_updatevideo api编辑视频接口
	public static String api_updateVideo = "http://spark.bokecc.com/api/video/update";
	// api_deletevideo api删除视频接口
	public static String api_deleteVideo = "http://spark.bokecc.com/api/video/delete";
	// api_playCode api获取视频播放代码接口
	public static String api_playCode = "http://spark.bokecc.com/api/video/playcode";
	//api_searchVideos api搜索视频接口
	public static String api_searchVideos = "http://spark.bokecc.com/api/videos/search";
	//api_create 创建直播间
	public static String api_createRoom = "http://api.l.bokecc.com/api/room/create";
	
	//直播间模板类型：视频直播
	public static String TEMPLATETYPE_ONE = "1";
	//直播间模板类型：视频直播+直播文档
	public static String TEMPLATETYPE_TWO = "2";
	//直播间模板类型：视频直播+聊天互动
	public static String TEMPLATETYPE_THREE = "3";
	//直播间模板类型：视频直播+聊天互动+直播文档
	public static String TEMPLATETYPE_FOUR = "4";
	//直播间模板类型：视频直播+聊天互动+直播文档+直播回答
	public static String TEMPLATETYPE_FIVE = "5";
	
	
	//验证方式:接口验证 ：需要填写checkurl
	public static String AUTHTYPE_ZERO = "0";
	//验证方式:密码验证：需要填写playpass
	public static String AUTHTYPE_ONE = "1";
	//验证方式:免密码验证
	public static String AUTHTYPE_TWO = "2";
	
}
