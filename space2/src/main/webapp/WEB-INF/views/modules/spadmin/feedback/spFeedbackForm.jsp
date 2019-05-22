<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户反馈管理</title>
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
		<li><a href="${ctx}/spadmin/feedback/spFeedback/">用户反馈列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/feedback/spFeedback/form?id=${spFeedback.id}&parent.id=${spFeedbackparent.id}">用户反馈${not empty spFeedback.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spFeedback" action="${ctx}/spadmin/feedback/spFeedback/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户：</label>
			<div class="controls">
			    ${spFeedback.userName}
			    </div>
		</div>
		<div class="control-group">
			<label class="control-label">反馈内容：</label>
			<div class="controls">
				 ${spFeedback.content}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反馈内容：</label>
			<div class="controls">
				<fmt:formatDate value="${spFeedback.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="form-actions">
			<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp; -->
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>