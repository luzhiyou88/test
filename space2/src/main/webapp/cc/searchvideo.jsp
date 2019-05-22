<%@page import="com.bokecc.config.Config"%>
<%@page import="com.bokecc.bean.Video"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bokecc.util.*"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.dom4j.*"%>
<%@page pageEncoding="UTF-8"%>
<%
	/**
	 * 功能：视频搜索接口示例
	 * 版本：2.1.2
	 * 日期：2013-11-13
	 * 作者：gt
	 **/
	 
	String searchQuery = request.getParameter("search_query");
    searchQuery = new String(searchQuery.getBytes("ISO-8859-1"), "UTF-8");
   
	String searchCont = request.getParameter("search_cont");
	searchCont = new String(searchCont.getBytes("ISO-8859-1"), "UTF-8");
	
	String sort = request.getParameter("sort_param");
	String categoryId = request.getParameter("categoryid");
	
	Map<String, String> paramsMap = new HashMap<String, String>();
	paramsMap.put("userid", Config.userid);
	paramsMap.put("q", searchQuery.concat(":").concat(searchCont));
	paramsMap.put("sort", new String(sort.getBytes("ISO-8859-1"), "UTF-8"));
	if(categoryId != null){
		paramsMap.put("categoryid", categoryId);
	}
	long time = System.currentTimeMillis();
	String salt = Config.key;
	System.out.println("查询参数："+paramsMap);
	String requestURL = APIServiceFunction.createHashedQueryString(paramsMap, time, salt);
	System.out.println("查询requestURL："+requestURL);
	String responseStr = APIServiceFunction.HttpRetrieve(Config.api_searchVideos + "?" + requestURL);
	
	Document doc = DemoUtil.build(responseStr);
	Element root = doc.getRootElement();
	if(root.elementIterator("video") == null){
		out.print("<div style='color:red;'>无搜索结果</div>");
	}else{
		@SuppressWarnings("unchecked")
		Iterator<Element> videoElements = root.elementIterator("video");
%>

视频总数：<%=root.elementText("total")%>个
<br />
	<hr>
	<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
		<tr>
			<th align="center">序号</th>
			<th align="center">视频ID</th>
			<th align="center">视频标题</th>
			<th align="center">图片</th>
			<th align="center">片长(s)</th>
			<th align="center">文件大小(M)</th>
			<th align="center">上传时间</th>
			<th align="center">操作</th>
		</tr>
		<%
			int idx=1;
			while(videoElements.hasNext()) {
				Element video = videoElements.next();
				String videoId = video.elementText("id");
		%>
		<tr>
			<td align="center"><%=idx++%></td>
			<td align="center"><%=videoId%></td>
			<td align="center"><%=video.elementText("title")%></td>
			<td align="center">
				<img alt="<%=video.elementText("title")%>" src="<%=video.elementText("image")%>" />
			</td>
			<td align="center"><%=video.elementText("duration")%></td>
			<td align="center">
				<%
					float fileSize = DemoUtil.getFloat(video.elementText("filesize"));
									fileSize = fileSize / 1048576;
									BigDecimal b = new BigDecimal(fileSize);
									fileSize = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
									out.print(fileSize);
				%>
			</td>
			<td align="center"><%=video.elementText("creation-date")%></td>
			<td align="center">
				<a href="edit.jsp?videoid=<%=videoId%>">编辑</a>&nbsp;&nbsp;|&nbsp;
				<a onclick="deletevideo(this.id);" href="#" id="<%=videoId%>">删除</a>&nbsp;&nbsp;|&nbsp;
				<a href="play.jsp?userid=<%=Config.userid%>&videoid=<%=videoId%>">点击播放</a>&nbsp;&nbsp;|&nbsp;
				<a href="playcode.jsp?videoid=<%=videoId%>">查看代码</a>
			</td>
		</tr>
		<%
			}
		}
	%>
</table>
