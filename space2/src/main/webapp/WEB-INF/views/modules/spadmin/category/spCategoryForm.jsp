<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分类管理管理</title>
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
		<li><a href="${ctx}/spadmin/category/spCategory/">分类管理列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/category/spCategory/form?id=${spCategory.id}">分类管理${not empty spCategory.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spCategory" action="${ctx}/spadmin/category/spCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">分类名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">父级id：</label>
			<div class="controls">
			</div>
		</div> -->
		<div class="control-group">
			<label class="control-label">上级类型：</label>
				<div class="controls">
                <%-- <sys:treeselect id="parent" name="parent.id" value="${spCategory.parent.id}" labelName="parent.name" labelValue="${spCategory.parent.name}"
					title="类型" url="/spadmin/category/spCategory/treeData"   extId="${spCategory.id}"  allowClear="${spCategory.currentUser.admin}"/> --%>
					<sys:treeselect id="parent" name="parent.id" value="${spCategory.parent.id}" labelName="parent.name" labelValue="${spCategory.parent.name}"
					title="类型" url="/spadmin/category/spCategory/treeData"   extId="${spCategory.id}"  allowClear="${spCategory.currentUser.admin}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>