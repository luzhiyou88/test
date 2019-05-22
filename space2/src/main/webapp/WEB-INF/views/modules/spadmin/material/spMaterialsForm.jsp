<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/spadmin/material/spMaterials/checkName?id="+ encodeURIComponent('${spMaterials.id}')}
				},
				messages: {
					name: {remote: "材料名称已存在"}
				},
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
		<li><a href="${ctx}/spadmin/material/spMaterials/">材料列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/material/spMaterials/form?id=${spMaterials.id}&parent.id=${spMaterialsparent.id}">材料${not empty spMaterials.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spMaterials" action="${ctx}/spadmin/material/spMaterials/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">材料名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料内容：</label>
			<div class="controls">
				<form:textarea path="cotent" htmlEscape="false"  rows="4" maxlength="256" class="input-large required"/>
			     <span class="help-inline"><font color="red">*</font> </span>
			</div>
				
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>