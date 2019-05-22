<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxWebInf}/css/lesson/lesson.css" type="text/css" rel="stylesheet" />
	<script src="${ctxWebInf}/js/spadmin/lesson/lessonForm.js" type="text/javascript"></script>
	<script type="text/javascript">
		var spLessonList = ${fns:toJson(spLessonList)};
		var spLesson = ${fns:toJson(spLesson)};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/lesson/spLesson/">课程列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/lesson/spLesson/form?id=${spLesson.id}&parent.id=${spLessonparent.id}">课程添加修改</a></li>
		<li><a href="${ctx}/spadmin/lesson/spLesson/schedule">课程表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spLesson" action="${ctx}/spadmin/lesson/spLesson/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input type="hidden" name="deleteLessonIds"/>
		<div class="control-group">
			<label class="control-label">套课：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${ empty course  }">
						<!-- 添加课程 或者 本地的套课 -->
						<form:select path="courseId" class="input-xlarge required" backVal="${spLesson.courseId }">
							<form:option value="" label="请选择" />
							<form:options items="${spCourseList}" itemLabel="courseName"
								itemValue="id" htmlEscape="false" />
						</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
						<input type="button" class="btn btn-primary" style="height:26px;line-height:18px;" id ="showmodel" value="添加课程">
						<div style="text-align: right" >
							<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
							<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
						</div>
					</c:when>
					<c:otherwise>
						<label style="line-height: 27px;">${spCourse.courseName }</label>
						<div style="text-align: right" >
							<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
						</div>
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
		<div class="control-group">
			<div class="column-template">
				<div class="column span3" style="position:relative;">
					<div class="shade-pop">
						<a href="javascript:return false;" onclick="lessonEdit(this)" class="btn-allpop show">查看</a>
						<a href="javascript:return false;" onclick="lessonEdit(this)" class="btn-pop edit">编辑</a>
						<a href="javascript:return false;" onclick="lessonDelete(this)" class="btn-pop delete">删除</a>
					</div>
				</div>
			</div>
			<div class="control-template">
				<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
					<label class="control-label" style="width:110px;"></label>
					<div class="controls" style="margin-left:120px;margin-top:5px;"></div>
				</div>
			</div>
			<div class="row show-grid">
			</div>
		</div>
	</form:form>
</body>
</html>