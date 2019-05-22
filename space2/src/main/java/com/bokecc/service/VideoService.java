package com.bokecc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.bokecc.bean.Video;
import com.bokecc.config.Config;
import com.bokecc.util.APIServiceFunction;
import com.bokecc.util.DemoUtil;

public class VideoService {
	public static void main(String[] args) {
		VideoService t=new VideoService();
//		List list=queryTaskStragegy();
//		System.out.println(list);
		
		String videoId="DEEB5D7AB07104579C33DC5901307461";
		@SuppressWarnings("static-access")
		Video video=t.queryVideoById(videoId);
		System.out.println(video);
	}
	public static Video queryVideoById(String videoId){
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("userid", Config.userid);
		paramsMap.put("videoid", videoId);
		long time = System.currentTimeMillis();
		String salt = Config.key;
		String requestURL = APIServiceFunction.createHashedQueryString(paramsMap, time, salt);
		// get方式
		String responseStr = APIServiceFunction.HttpRetrieve(Config.api_video + "?" + requestURL);
		System.out.println("videoStr="+responseStr);
		Document doc = DemoUtil.build(responseStr);
		// 得到视频信息XML
		Element videoElement = doc.getRootElement();
		Video video=new Video();
//		video.setId(Integer.parseInt(videoId));
		video.setSpark_videoid(videoId);
		video.setTitle(videoElement.elementText("title"));
		video.setTag(videoElement.elementText("tags"));
		video.setDescription(videoElement.elementText("desp"));
		video.setDuration(videoElement.elementText("duration"));
		video.setCategory(videoElement.elementText("category"));
		video.setImage(videoElement.elementText("image"));
		System.out.println("video="+video);
		return video;
	}
	public static List<Video> queryTaskStragegy(){
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("userid", Config.userid); //	userid 用户id，不可以为空
		queryMap.put("videoid_from", "10000000"); //	videoid_from 起始videoid，若为空，则从上传的第一个视频开始
		queryMap.put("videoid_to", ""); //	videoid_to 终止videoid，若为空，则到最后一个上传的视频
		queryMap.put("num_per_page", 100 + ""); //	num_per_page 返回信息时，每页包含的视频个数，上限为100个
		queryMap.put("page", "1"); //	page 当前的页数
		queryMap.put("sort", "FILE_SIZE:ASC");
		queryMap.put("categoryid", "3653A9DD6C7EABFB"); //	视频分类（任务攻略）
		queryMap.put("q", "TITLE:");
		List<Video> list=mysearch(queryMap);
		return list;
	}
	public static List<Video> mysearch(Map<String, String> queryMap){//	单页视频上限个数为100个
		DemoUtil.SYNC_VIDEO_LIST = new ArrayList<Video>();
		
		int pageSize=Integer.parseInt(queryMap.get("num_per_page"));
		@SuppressWarnings("unused")
		int pageNum=Integer.parseInt(queryMap.get("page"));
		long time;
		String triggerURL;
		String result;
		Document document;
		String totalStr;
		Integer total;
		int AllPageNum = 1;
		
		time = System.currentTimeMillis();
		System.out.println("查询参数："+queryMap);
		triggerURL = Config.api_searchVideos
				+ "?"
				+ APIServiceFunction.createHashedQueryString(queryMap,time, Config.key);
		result = APIServiceFunction.HttpRetrieve(triggerURL);
		document = DemoUtil.build(result);
		try {
			totalStr = document.getRootElement().element("total").getTextTrim();
		} catch (Exception e) {
			totalStr = "error";
		}
		if(!"error".equals(totalStr)){
			total = Integer.parseInt(totalStr);
			if ((total % pageSize) == 0) {
				AllPageNum = total / pageSize;
			} else {
				AllPageNum = (total / pageSize) + 1;
			}
			if (AllPageNum < 1){
				AllPageNum = 1;
			}
			DemoUtil.apiVideos_parseXML(document);
		}
		return DemoUtil.SYNC_VIDEO_LIST;
	}
}
