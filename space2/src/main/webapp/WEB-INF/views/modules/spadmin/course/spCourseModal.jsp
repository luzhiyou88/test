<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	label, input, button, select, textarea {
	    line-height: 1.2;
    }
	</style>
</head>
<body>
	<form:form id="inputForm" modelAttribute="spCourse" class="form-horizontal">
		<input type="hidden" class="courseId" name="id" value="${spCourse.id }"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">套课名称：</label>
			<div class="controls">
				<label class="input-xlarge">${spCourse.courseName }</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程总数：</label>
			<div class="controls">
				<label class="input-xlarge">${spCourse.courseNumber}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">套课分类：</label>
			<div class="controls">
				<sys:treeselect id="spCategory" name="categoryId" value="" labelName="categoryName" labelValue=""
				title="类型" url="/spadmin/category/spCategory/treeData" cssClass="required" notAllowSelectParent="true" allowClear="${spCourse.currentUser.admin}"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程类型：</label>
			<div class="controls">
				<label class="input-xlarge">${fns:getDictLabel(spCourse.courseType, 'cr_fee_level', '-')}</label>
			</div>
		</div>
		<div class="control-group coursePrice" style="<c:if test="${spCourse.courseType == 1}">display:none</c:if>">
			<label class="control-label">套课价格：</label>
			<div class="controls">
				<label class="input-xlarge">${spCourse.coursePrice }</label>（￥）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<label class="input-xlarge">${spCourse.remarks }</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">套课资料：</label>
			<div class="controls">
				<c:forEach items="${fileList }" var="file">
					${file.fileName }<br/>
				</c:forEach>
			</div>
		</div>
	</form:form>
</body>
</html>