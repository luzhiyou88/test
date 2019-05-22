<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxWebInf}/css/lesson/lesson.css" type="text/css" rel="stylesheet" />
	<script src="${ctxWebInf}/js/spadmin/lesson/schedule.js" type="text/javascript"></script>
	<script src="${ctxWebInf}/js/spadmin/lesson/comSchedule.js" type="text/javascript"></script>
	<script type="text/javascript">
		var spLessonList = ${fns:toJson(spLessonList)};
		var localSpaceId = ${fns:toJson(localSpaceId)};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/lesson/spLesson/">课程列表</a></li>
		<li><a href="${ctx}/spadmin/lesson/spLesson/form">课程添加修改</a></li>
		<li class="active"><a href="${ctx}/spadmin/lesson/spLesson/schedule">课程表</a></li>
	</ul>
	<div class="container" style="width:1100px;">
		<input id="scheduleDate" type="text" readonly="readonly" maxlength="20" class="input-date Wdate"
					value="<fmt:formatDate value="${scheduleDate}" pattern="yyyy/MM/dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy/MM/dd',isShowClear:true});"/>
		<input id="scheduleSearch" class="btn btn-primary mb10" type="button" value="查询"/>
		<table class='scheduleTable'>
			<thead>
				<tr>
					<th>
						<a href="#" onclick="goTo('${(fn:length(weekDayList))>0?weekDayList[0].date:""}',-1)"  class="turn-left"></a>
						<a href="#" onclick="goTo('${(fn:length(weekDayList))>0?weekDayList[0].date:""}',1)" class="turn-right"></a>
					</th>
					<c:forEach items="${weekDayList}" var="weekDay">
						<th <c:if test="${weekDay.todayFlag}">class="today"</c:if> data-date="${weekDay.date}">
							${weekDay.weekDay }
							<br/>
							<span>${weekDay.monthDay }</span>
						</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${spSectionList}" var="section">
					<tr data-section="${section.id }">
						<td class="menu">${section.name }</td>
						<c:forEach items="${weekDayList}" var="weekDay">
							<td data-date="${weekDay.date}" data-section="${section.id }"><span  class="add-tab"></span>
								<div class="tooltip top tooltip-y">
								  <div class="tooltip-arrow"></div>
								  <div class="tooltip-inner">
								  </div>
								</div>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pop-del" id="myMenu" style="position:absolute;">
			<a href="#" onclick="removeLesson(1)" class="pop-lr lesson">删除该课程</a>
			<a href="#" onclick="removeLesson(2)" class="pop-lr course">删除该课程下所有套课</a>
		</div>
    </div>
    <form id="inputForm" action="${ctx}/spadmin/lesson/spLesson/schedule" method="post" >
    	<input type="hidden" id="startDate" name="startDate"/>
    </form>
</body>
</html>