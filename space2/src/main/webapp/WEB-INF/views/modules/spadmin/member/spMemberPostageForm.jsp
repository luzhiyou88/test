<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员资费管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/spadmin/member/spMemberPostage/checkName"}
            	},		
            	messages: {		
            		name: {remote: "资费名称已存在"}
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
		<li><a href="${ctx}/spadmin/member/spMemberPostage/">会员资费管理列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/member/spMemberPostage/form?id=${spMemberPostage.id}">会员资费管理${not empty spMemberPostage.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spMemberPostage" action="${ctx}/spadmin/member/spMemberPostage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">会员资费名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-small required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">购买会员的月数：</label>
			<div class="controls">
				<form:input path="month" htmlEscape="false" maxlength="11" class="input-small required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会员资费价格：</label>
			<div class="controls">
				<form:input path="postagePrice" htmlEscape="false" class="input-small required zfu"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="256" class="input-small "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>