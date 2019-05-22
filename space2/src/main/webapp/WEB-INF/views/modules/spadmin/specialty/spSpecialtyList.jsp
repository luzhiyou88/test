<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专业管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/specialty/spSpecialty/">专业列表</a></li>
		<li><a href="${ctx}/spadmin/specialty/spSpecialty/form">专业添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spSpecialty" action="${ctx}/spadmin/specialty/spSpecialty/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>专业名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>专业名称</th>
				<th>备注</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spSpecialty">
			<tr>
				<td><a href="${ctx}/spadmin/specialty/spSpecialty/form?id=${spSpecialty.id}">
					${fn:substring(spSpecialty.name, 0,20)}
				</a></td>
				<td>
					${fn:substring(spSpecialty.remarks, 0,50)}
				</td>
				<td>
					<fmt:formatDate value="${spSpecialty.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/specialty/spSpecialty/form?id=${spSpecialty.id}">修改</a>
					<a href="${ctx}/spadmin/specialty/spSpecialty/delete?id=${spSpecialty.id}" onclick="return confirmx('确认要删除该专业吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>