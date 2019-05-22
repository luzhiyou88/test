<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节次管理</title>
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
		
		function checkSection(id){
			if(id == null || id == '' || id == 'undefined'){
				$('#inputForm').submit();
				return false;
			}
			var url = ctx+"/spadmin/section/spSection/checkSectionLesson?sectionId="+id;
			$.ajax({
				type: 'get',
		        url: url,
		        dataType: "json",
		        async: true,
		        success:function(data){
		        	if(data == true){
		        		$('#inputForm').submit();
		        	} else {
		        		art.dialog.alert("课程表有该节次数据，不能修改!");
		        		return false;
		        		
		        	}
		        },
		        error:function(){
		        	art.dialog.alert("请求出错!");
		        }
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/section/spSection/">节次列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/section/spSection/form?id=${spSection.id}">节次${not empty spSection.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spSection" action="${ctx}/spadmin/section/spSection/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
			<input name="startTime" type="text"  readonly="readonly" maxlength="20" class="input-small Wdate required"
					value="${spSection.startTime}"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
			<input name=endTime type="text"  readonly="readonly" maxlength="20" class="input-small Wdate required"
					value="${spSection.endTime}"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit1" class="btn btn-primary" type="button" value="保 存" onclick="checkSection('${spSection.id}');"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>