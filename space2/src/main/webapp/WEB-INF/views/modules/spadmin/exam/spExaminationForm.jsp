<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试卷管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			var examinationName = $('#examinationName').val();
			$("#inputForm").validate({
				rules: {
					examinationName: {remote: "${ctx}/spadmin/exam/spExamination/checkExaminationName?id="+ encodeURIComponent('${spExamination.id}')}
				},
				messages: {
					examinationName: {remote: "试卷名称已存在"}
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
		<li><a href="${ctx}/spadmin/exam/spExamination/">试卷管理列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/exam/spExamination/form?id=${spExamination.id}&parent.id=${spExaminationparent.id}">试卷管理${not empty spExamination.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spExamination" action="${ctx}/spadmin/exam/spExamination/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">所属课程：</label>
			<div class="controls">
				<form:select path="lessonId"  items="${fncr:getSpLessonMap(true)}" class="input-large required" ></form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">试卷名称：</label>
			<div class="controls">
				<form:input path="examinationName" htmlEscape="false" maxlength="50" class="input-large required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">试卷总分数：</label>
			<div class="controls">
				<form:input path="examinationTotalScore" htmlEscape="false" maxlength="11" class="input-mini required znum"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">题目总数：</label>
			<div class="controls">
				<form:input path="examinationNumber" htmlEscape="false" maxlength="11" class="input-mini number required znum"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出题人：</label>
			<div class="controls">
				<form:input path="originators" htmlEscape="false" maxlength="200" class="input-small "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考试用时：</label>
			<div class="controls">
				<form:input path="timing" htmlEscape="false" maxlength="11" class="input-mini number required znum"/>
				<span class="help-inline">(分钟)<font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">试卷状态 ：</label>
			<div class="controls">
				<form:input path="state" htmlEscape="false" maxlength="1" class="input-small required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false"  rows="4" maxlength="256" class="input-large "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>