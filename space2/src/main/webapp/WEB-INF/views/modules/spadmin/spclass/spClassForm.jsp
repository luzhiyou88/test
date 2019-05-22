<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>班级管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate({
							rules: {
								name: {remote: "${ctx}/spadmin/spclass/spClass/checkClassName?id=" + 
									encodeURIComponent('${spClass.id}')},
								  
							},
							messages: {
								name: {remote: "该班级已经被创建！"}
							},

									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									}
								});
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/spclass/spClass/">班级列表</a></li>
		<li class="active"><a
			href="${ctx}/spadmin/spclass/spClass/form?id=${spClass.id}">班级${not empty spClass.id?'修改':'添加'}查看</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="spClass"
		action="${ctx}/spadmin/spclass/spClass/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">班级名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">班主任：</label>
			<div class="controls">
				<form:select path="teacherId" class="input-xlarge required">
					<form:option value="" label="请选择" />
					<form:options items="${userList}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="256" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="保 存" />&nbsp; <input id="btnCancel" class="btn" type="button"
				value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>