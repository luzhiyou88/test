<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
		<li><a href="${ctx}/spadmin/message/spMessageAlerts/">公告管理列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/message/spMessageAlerts/form?id=${spMessageAlerts.id}">公告管理${not empty spMessageAlerts.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spMessageAlerts" action="${ctx}/spadmin/message/spMessageAlerts/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="512" class="input-medium "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发布人名称：</label>
			<div class="controls">
				<form:input path="operaterName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消息类型 ：</label>
			<div class="controls">
				<%-- <form:input path="messageType" htmlEscape="false" maxlength="1" class="input-medium "/> --%>
				<form:select id="messageType" path="messageType" class="input-medium required">
                   <form:option value="1">系统</form:option>
		       </form:select>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>