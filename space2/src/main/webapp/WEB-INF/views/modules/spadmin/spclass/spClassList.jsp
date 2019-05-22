<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>班级管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/spclass/spClass/">班级列表</a></li>
		<li><a href="${ctx}/spadmin/spclass/spClass/form">班级添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spClass"
		action="${ctx}/spadmin/spclass/spClass/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>班级名称：</label> <form:input path="name"
					htmlEscape="false" maxlength="20" class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>班级名称</th>
				<th>班主任</th>
				<th>备注</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="spClass">
				<tr>
					<td><a
						href="${ctx}/spadmin/spclass/spClass/form?id=${spClass.id}">
							${fn:substring(spClass.name, 0,20)} </a></td>
					<td><c:forEach items="${userList}" var="sp">
							<c:if test="${sp.id eq spClass.teacherId}">
					     ${sp.name}
					     </c:if>
						</c:forEach></td>
					<td>${fn:substring(spClass.remarks, 0,50)}</td>
					<td><fmt:formatDate value="${spClass.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a
						href="${ctx}/spadmin/spclass/spClass/form?id=${spClass.id}">修改</a>
						<a href="${ctx}/spadmin/spclass/spClass/delete?id=${spClass.id}"
						onclick="return confirmx('确认要删除该班级吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>