<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目成员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/projects/spProjects/listmy">我的项目列表</a></li>
		<li><a href="${ctx}/spadmin/projects/spProjects/form?readonlyFlag=true&id=${spProjectUser.projectId}">我的项目详情</a></li>
		<li><a href="javascript:location.href='${ctx}/spadmin/projects/spProjectUser/list?projectId='+$('#projectId').val();">项目成员列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/projects/spProjectUser/form?id=${spProjectUser.id}&parent.id=${spProjectUserparent.id}">项目成员${not empty spProjectUser.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spProjectUser" action="${ctx}/spadmin/projects/spProjectUser/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户名称：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="128" class="input-medium required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<input id="projectId" name="projectId" type="hidden" value="${spProjectUser.projectId}">
				<select id="projectIdStr" name="projectIdStr" class="input-medium required" disabled="disabled">
					<option value="">请选择</option>
					<c:forEach items="${projectList}" var="one">
						<option value="${one.id}" ${spProjectUser.projectId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户头像：</label>
			<div class="controls">
				<form:input id="headerImg" path="headerImg" htmlEscape="false" maxlength="512" class="input-medium required" readonly="true" onclick="$('#fileImg').click()"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<input type="file" name="fileImg" id="fileImg" class="input-mini " onchange="$('#headerImg').val(this.value);" accept="image/*"/>
			</div>
			<c:if test="${!empty spProjectUser.headerImg}">
				<div class="controls">
					<img style="max-width:99px;max-height:99px" src="${spProjectUser.headerImg}">
				</div>
			</c:if>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:radiobutton path="sex" value="0" label="男" checked="true"/>  
                <form:radiobutton path="sex" value="1" label="女" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年龄：</label>
			<div class="controls">
				<form:input path="age" htmlEscape="false" maxlength="2" class="input-medium number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">能力：</label>
			<div class="controls">
				<form:textarea path="ability" htmlEscape="false" maxlength="1024" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">履历：</label>
			<div class="controls">
				<form:textarea path="antecedents" htmlEscape="false" maxlength="1024" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>