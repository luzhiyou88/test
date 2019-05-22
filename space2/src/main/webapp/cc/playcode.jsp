<%@page import="com.bokecc.util.DemoUtil"%>
<%@page import="com.bokecc.util.APIServiceFunction"%>
<%@page import="com.bokecc.config.Config"%>
<%@page import="org.dom4j.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.Map" />
<jsp:directive.page import="java.util.HashMap" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>playcode info</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/admin/css/common_frame.css?v=${v}"/>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/admin/css/common_frame_right.css?v=${v}"/>
</head>
<body style="background: #F5F5F5;">
<div id="main_content" style="margin: 10px;">
<form action="${ctx}/TaskingController/getSysTaskList" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<!-- 内容 开始  -->
	<div class="zhong ml5">
		<div class="page_head">
			<h4><em class="icon14 i_01"></em>&nbsp;<span>视频管理</span> &gt; <span>视频列表</span> </h4>
		</div>
		<!-- /tab1 begin-->
		<div class="mt20">
		<div class="commonWrap">
		
		
	<%
		/**
		 * 功能：获取播放代码接口示例
		 * 版本：2.1.2
		 * 日期：2013-11-13
		 * 作者：gt
		 **/
		Map<String, String> paramsMap = new HashMap<String, String>();
		String videoId = request.getParameter("videoid");
		paramsMap.put("videoid", videoId);
		paramsMap.put("userid", Config.userid);
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		String autoPlay = request.getParameter("autoplay");
		if (width != null) {
			paramsMap.put("player_width", width);
		}
		if (height != null) {
			paramsMap.put("player_height", height);
		}
		if (autoPlay != null) {
			paramsMap.put("auto_play", autoPlay);
		}
		long time = System.currentTimeMillis();
		String salt = Config.key;
		String requestURL = APIServiceFunction.createHashedQueryString(paramsMap, time, salt);
		//get方式
		String responsestr = APIServiceFunction.HttpRetrieve(Config.api_playCode + "?" + requestURL);
		Document doc = DemoUtil.build(responsestr);
		String playCode = doc.getRootElement().element("playcode")
		.getText();
		//out.println(responsestr);
	%>
	<div style="float: left;">
		默认JavaScript代码：<br><textarea cols="50px" rows="5px" id="play_code"><%=playCode%></textarea><br>
		<form action="playcode.jsp">
			<input type="hidden" name="videoid" value="<%=videoId%>">
			播放器宽度：<input id="width" name="width" type="text" size="5px"/>px<br>
			播放器高度：<input id="height" name="height" type="text" size="5px"/>px<br>
			是否自动播放：<input name="autoplay" type="radio" value="true"
				id="autoplay1" />True<input name="autoplay" type="radio"
				value="false" id="autoplay1" />False <br> <input type="submit"
				value="设置"">
		</form>
	</div>
	<div style="float: right; padding-right: 25%">
		视频展示<%=playCode%></div>

		</div>
		</div>
	</div>
</form>
</div>
</body>
</html>
