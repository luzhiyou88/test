<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li><a href="${ctx}/spadmin/user/spUser/">用户列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/user/spUser/form?id=${spUser.id}">用户${not empty spUser.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spUser" action="${ctx}/spadmin/user/spUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录姓名：</label>
			<div class="controls">
				<form:input path="loginName" htmlEscape="false" maxlength="32" class="input-medium " readonly="true"/>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">登录密码：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="userPwd" htmlEscape="false" maxlength="32" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">用户头像：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:hidden id="thumbImg" path="thumbImg" htmlEscape="false" maxlength="256" class="input-xlarge"/> --%>
<%-- 				<sys:ckfinder input="thumbImg" type="files" uploadPath="/spadmin/user/spUser" selectMultiple="true"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="control-group">
			<label class="control-label">手机：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="11" class="input-medium" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="32" class="input-medium " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型：</label>
			<div class="controls">
				<form:select path="userType" class="input-medium ">
					<form:options items="${fns:getDictList('sp_user_type')}" itemLabel="label" itemValue="value"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入学年份：</label>
			<div class="controls">
				<form:input path="enterYear" htmlEscape="false" maxlength="4" class="input-medium number" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:true});" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属专业：</label>
			<div class="controls">
				<select name="specialtyId" class="input-medium">
					<c:forEach items="${specialtyList}" var="one">
						<option value="${one.id}" ${spUser.specialtyId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">所在班级：</label>
			<div class="controls">
				<select name="classId" class="input-medium">
					<c:forEach items="${classList}" var="one">
						<option value="${one.id}" ${spUser.classId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="256" class="input-medium "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>