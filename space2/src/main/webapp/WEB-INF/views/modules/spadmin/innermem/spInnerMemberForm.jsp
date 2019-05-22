<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员预留信息管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					phoneNo: {remote: "${ctx}/spadmin/innermem/spInnerMember/checkPhoneNo?oldPhoneNo=" + encodeURIComponent('${spInnerMember.phoneNo}')}
				},
				messages: {
					phoneNo: {remote: "会员手机号已存在"}
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
		<li><a href="${ctx}/spadmin/innermem/spInnerMember/">预留会员列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/innermem/spInnerMember/form?id=${spInnerMember.id}">预留会员${not empty spInnerMember.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spInnerMember" action="${ctx}/spadmin/innermem/spInnerMember/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="spaceId" value="${spaceId}" />
		<sys:message content="${message}"/>		
		 <div class="control-group">
			<label class="control-label">所属专业：</label>
			<div class="controls">
			    <form:select id="specialtyId" path="specialtyId" class="input-xlarge required">
                   <form:option value="">请选择</form:option>
                   <c:forEach items="${specials}" var="specialItem">
                      <form:option value="${specialItem.id}">${specialItem.name}</form:option>
                   </c:forEach>
		        </form:select>
		        <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">所在班级：</label>
			<div class="controls">
			    <form:select id="classId" path="classId" class="input-xlarge required">
                   <form:option value="">请选择</form:option>
                   <c:forEach items="${classes}" var="classItem">
                      <form:option value="${classItem.id}">${classItem.name}</form:option>
                   </c:forEach>
		        </form:select>
		        <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">会员姓名：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="20" class="input-small required username"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会员手机号：</label>
			<div class="controls">
				<form:input path="phoneNo" htmlEscape="false" maxlength="11" class="input-small required mobilenum"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入学年份：</label>
			<div class="controls">
				<form:input path="enterYear" htmlEscape="false" maxlength="4" class="input-small required number "/>
				<span class="help-inline"><font color="red">*</font> </span>	
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效期限(月)：</label>
			<div class="controls">
				<form:hidden path="validPeriod" value="12" />12
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:select id="sex" path="sex" class="input-small required">
                   <form:option value="">请选择</form:option>
                   <form:option value="0">男</form:option>
                   <form:option value="1">女</form:option>
			       <form:option value="3">其他</form:option>
		        </form:select>
		        <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生日期：</label>
			<div class="controls">
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
					value="<fmt:formatDate value="${spInnerMember.birthday}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>