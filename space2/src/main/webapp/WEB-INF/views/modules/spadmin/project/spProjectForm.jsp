<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>路演-项目详情页面</title>
	<meta name="decorator" content="default"/>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/project/list?lessonId=${project.lessonId}">路演项目列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/project/form?id=${id}">项目详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spProject" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="lessonId" value="${project.lessonId}" />
		<sys:message content="${message}"/>		
		 <div class="control-group">
			<label class="control-label">路演名称：</label>
			<div class="controls">${project.lessonName }</div>
		</div> 
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">${project.name }</div>
		</div> 
		<div class="control-group">
			<label class="control-label">主讲人：</label>
			<div class="controls">${project.speakerName }</div>
		</div>
		<div class="control-group">
			<label class="control-label">特点：</label>
			<div class="controls">
				<form:textarea path="characteristic" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">背景：</label>
			<div class="controls">
				<form:textarea path="backdrop" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场规划：</label>
			<div class="controls">
				<form:textarea path="outletPlaning" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">盈利模式：</label>
			<div class="controls">
				<form:textarea path="profitModel" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商业计划书：</label>
			<div class="controls">
			    <a href ="${project.businessProposalUrl }" >下载商业计划书</a>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
	</form:form>
</body>
</html>