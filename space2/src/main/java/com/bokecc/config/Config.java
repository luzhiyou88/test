package com.bokecc.config;

/**
 * 功能：设置帐户有关信息及返回路径（基础配置页面）
 * 版本：2.1.2
 * 日期：2013-11-13
 * 作者：chu
 **/
public class Config {
	public static String userid = "B934DEE18E58D275";
	public static String key = "0zqmn9IRDbJK6B8UUSrp9eAtt5xI7dPK";
	public static String category = "8017C8B1CE1A2569";

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

}
