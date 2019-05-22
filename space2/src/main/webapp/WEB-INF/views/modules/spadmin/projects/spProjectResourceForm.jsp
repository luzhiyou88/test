<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目资源管理</title>
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
		<li><a href="${ctx}/spadmin/projects/spProjectResource/">项目资源列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/projects/spProjectResource/form?id=${spProjectResource.id}&parent.id=${spProjectResourceparent.id}">项目资源${not empty spProjectResource.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spProjectResource" action="${ctx}/spadmin/projects/spProjectResource/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属项目：</label>
			<div class="controls">
				<select name="projectId" class="input-medium required">
					<option value="">请选择</option>
					<c:forEach items="${projectList}" var="one">
						<option value="${one.id}" ${spProjectResource.projectId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资源路径：</label>
			<div class="controls">
				<form:input id="baseUrl" path="baseUrl" htmlEscape="false" maxlength="256" class="input-medium required" readonly="true" onclick="$('#file').click()"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<input type="file" name="file" id="file" class="input-mini " onchange="$('#baseUrl').val(this.value);"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预览图：</label>
			<div class="controls">
				<form:input id="thumbImg" path="thumbImg" htmlEscape="false" maxlength="256" class="input-medium required" readonly="true" onclick="$('#fileImg').click()"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<input type="file" name="fileImg" id="fileImg" class="input-mini " onchange="$('#thumbImg').val(this.value);" accept="image/*"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>