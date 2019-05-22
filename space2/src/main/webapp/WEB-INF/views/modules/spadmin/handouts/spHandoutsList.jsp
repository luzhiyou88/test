<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script src="${ctxWebInf}/js/spadmin/lesson/lessonList.js" type="text/javascript"></script>
	<script type="text/javascript">
		var lessonList=${fns:toJson(list)};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/lesson/spLesson/">课程列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spLesson" action="${ctx}/spadmin/handouts/spHandouts/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
				<td><label>套课名称：</label>
					<form:input path="courseName" htmlEscape="false" maxlength="32" class="input-medium"/>
				</td>
				<td><label>课程名称：</label>
					<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
				</td>
				<%-- <td>
					<label>上课老师：</label>
					<form:input path="teacherName" htmlEscape="false" maxlength="32" class="input-medium"/>
				</td> --%>
				
			</tr>
			<tr>
				<td>
					<label>开课状态：</label>
					<form:select path="lessonState" class="input-xlarge required">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('curriculum_state')}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</td>
				<td>
					<label>课程日期：</label>
					<input name="lessonDateFrom" type="text" readonly="readonly" maxlength="20" class="input-date Wdate"
						value="<fmt:formatDate value="${spLesson.lessonDateFrom}" pattern="yyyy/MM/dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd',isShowClear:true});"/>
					~	
					<input name="lessonDateTo" type="text" readonly="readonly" maxlength="20" class="input-date Wdate"
						value="<fmt:formatDate value="${spLesson.lessonDateTo}" pattern="yyyy/MM/dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd',isShowClear:true});"/>
				</td>
				<td>
					<label>课程时间：</label>
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
		<div class="searchFormButton">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input class="btn btn-primary" type="reset" value="清空"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>				
				<th>课程名称</th>
				<th>套课名称</th>
				<th>开课时间</th>
				<th>讲义名称</th>
				<th>上课老师</th>
				<th>开课状态</th>
				<th>发布状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spLesson">
			<tr>
				<td>${spLesson.name}</td>
				<td>
					${spLesson.courseName}
				</td>
				<td>${fns:getFormatDate(spLesson.lessonDate)}&nbsp;${spLesson.lessonStarttime}~${spLesson.lessonEndtime}</td>
				<td title="${spLesson.baseUrl}">${spLesson.title}</td>
				<td>${spLesson.teacherName}</td>
				<td>${fns:getDictLabel(spLesson.lessonState, "curriculum_state", "-")}</td>
				<td>${fns:getDictLabel(spLesson.publishState, "sp_publish_state", "-")}</td>
				<td>
				    <c:if test="${spLesson.baseUrl == null }">
				       <c:if test="${spLesson.lessonSource == '2' && spLesson.publishState != '0' }">
				                            已发布的平台课，不能上传讲义
				       </c:if>
				      <c:if test="${(spLesson.lessonSource == '1' || spLesson.lessonSource == '2') && spLesson.publishState == '0' }">
				            <a href="${ctx}/spadmin/handouts/spHandouts/handouts?id=${spLesson.id}">上传讲义</a>
				       </c:if>
				     </c:if>
				     <c:if test="${spLesson.baseUrl != null }">
				        <c:if test="${spLesson.lessonSource == '2' && spLesson.publishState != '0' }">
				                            已发布的平台课，不能修改讲义
				       </c:if>
				      <c:if test="${(spLesson.lessonSource == '1' || spLesson.lessonSource == '2') && spLesson.publishState == '0' }">
				            <a href="${ctx}/spadmin/handouts/spHandouts/handouts?id=${spLesson.id}">修改讲义</a>
				       </c:if>
				     </c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>