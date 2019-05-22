<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>小组管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									rules : {
										name : {
											remote : "${ctx}/spadmin/group/spGroup/checkGroupName?id="
													+ encodeURIComponent('${spGroup.id}')
										},

									},
									messages : {
										name : {
											remote : "该专业已经被创建！"
										}
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
		<li><a href="${ctx}/spadmin/group/spGroup/">小组列表</a></li>
		<li class="active"><a
			href="${ctx}/spadmin/group/spGroup/form?id=${spGroup.id}">小组${not empty spGroup.id?'修改':'添加'}查看</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="spGroup"
		action="${ctx}/spadmin/group/spGroup/save" method="post"
		class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">小组名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="9"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*&nbsp;&nbsp;1-9个字节</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">组类型：</label>
			<c:if
				test="${ empty spGroup.groupType}">
				<div class="controls">
					<form:select path="groupType" class="input-xlarge required">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('sp_group_minitype')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
					<span class="help-inline"><font color="red">*</font></span>
				</div>
			</c:if>
			<c:if
				test="${not empty spGroup.groupType }">
				<div class="controls">
					<form:select disabled="true" path="groupType"
						class="input-xlarge required">
						<form:options items="${fns:getDictList('sp_group_type')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
					<span class="help-inline"><font color="red">*</font></span>
				</div>
			</c:if>
		</div>
		<div class="control-group">
			<label class="control-label">组长：</label>
			<div class="controls">
				<form:select path="leaderId" class="input-xlarge required">
					<form:option value="" label="请选择" />
					<form:options items="${userList}" itemLabel="name" itemValue="id"
						htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">小组图标：</label>
			<div class="controls">
				<c:if test="${not empty spGroup.thumbImg}">
					<img alt="" src="${spGroup.thumbImg}" width="100" height="100">
				</c:if>
				<input type="file" name="file" accept=".jpg,.png,.gif"><br>
				<span class="help-inline"><font color="red">支持：.jpg
						.png .gif格式</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="50" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="保 存" />&nbsp; <input id="btnCancel" class="btn"
				type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>