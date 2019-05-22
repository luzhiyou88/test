<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>路演管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script src="${ctxWebInf}/js/spadmin/lesson/lessonList.js" type="text/javascript"></script>
	<script type="text/javascript">
		var lessonList=${fns:toJson(list)};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/roadshow/">路演列表</a></li>
		<li><a href="${ctx}/spadmin/roadshow/form">路演添加修改</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spLesson" action="${ctx}/spadmin/roadshow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
				<td><label>路演名称：</label>
					<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
				</td>
				<td>
					<label>演讲人：</label>
					<form:input path="teacherName" htmlEscape="false" maxlength="32" class="input-medium"/>
				</td>
				<td>
					<label>路演日期：</label>
					<input name="lessonDateFrom" type="text" readonly="readonly" maxlength="20" class="input-date Wdate"
						value="<fmt:formatDate value="${spLesson.lessonDateFrom}" pattern="yyyy/MM/dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd',isShowClear:true});"/>
					~	
					<input name="lessonDateTo" type="text" readonly="readonly" maxlength="20" class="input-date Wdate"
						value="<fmt:formatDate value="${spLesson.lessonDateTo}" pattern="yyyy/MM/dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd',isShowClear:true});"/>
				</td>
				<td>
					<label>路演时间：</label>
					<input name="lessonStarttimeFrom" type="text" readonly="readonly" maxlength="20" class="input-time Wdate"
						value="${spLesson.lessonStarttimeFrom}"
						onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:true});"/>
					~
					<input name="lessonEndtimeTo" type="text" readonly="readonly" maxlength="20" class="input-time Wdate"
						value="${spLesson.lessonEndtimeTo}"
						onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:true});"/>
				</td>
			</tr>
		</table>
		<br/>
		<div class="searchFormButton">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input class="btn btn-primary" type="reset" value="清空"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>				
				<th>路演名称</th>
				<th>路演时间</th>
				<th>演讲人</th>
				<th>路演状态</th>
				<th>发布状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spLesson">
			<tr>
				<td>${spLesson.name}</td>
				<td>${fns:getFormatDate(spLesson.lessonDate)}&nbsp;${spLesson.lessonStarttime}~${spLesson.lessonEndtime}</td>
				<td>${spLesson.teacherName}</td>
				<td>${fns:getDictLabel(spLesson.lessonState, "curriculum_state", "-")}</td>
				<td>${fns:getDictLabel(spLesson.publishState, "sp_publish_state", "-")}</td>
				<td>
					<c:if test="${spLesson.publishState=='0' or spLesson.publishState=='2' }">
						&nbsp;
						<a href="${ctx}/spadmin/roadshow/form?id=${spLesson.id}">修改</a>
						<c:if test="${spLesson.lessonSource=='2' }">
							&nbsp;
							<a href="#" onclick="javascript:publishLesson('路演','${ctx}/spadmin/roadshow/publishLesson?id=${spLesson.id}')">发布平台</a>
						</c:if>
					</c:if>
					<c:if test="${spLesson.publishState=='1' or spLesson.publishState=='3'  or spLesson.publishState=='4' }">
						&nbsp;
						<a href="${ctx}/spadmin/roadshow/form?id=${spLesson.id}">查看</a>
					</c:if>
					<c:if test="${spLesson.publishState=='1' and spLesson.lessonSource=='2'}">
						&nbsp;
						<a href="#" onclick="javascript:refreshPublishStatus('${ctx}/spadmin/lesson/spLesson/refreshPublishStatus?id=${spLesson.id}')">刷新发布状态</a>
					</c:if>
					&nbsp;
					<a href="#" onclick="javascript:lessonDelete('${ctx}/spadmin/roadshow/delete?id=${spLesson.id}')">删除</a>
					&nbsp;
					<a href="${ctx}/spadmin/projects/spProjects/list?lessonId=${spLesson.id}">项目列表</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>