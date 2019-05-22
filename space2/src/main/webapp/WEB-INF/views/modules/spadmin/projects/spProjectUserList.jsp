<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目成员管理</title>
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
		<li><a href="${ctx}/spadmin/projects/spProjects/listmy">我的项目列表</a></li>
		<li><a href="${ctx}/spadmin/projects/spProjects/form?readonlyFlag=true&id=${spProjectUser.projectId}">我的项目详情</a></li>
		<li class="active"><a href="${ctx}/spadmin/projects/spProjectUser/">项目成员列表</a></li>
		<li><a href="javascript:location.href='${ctx}/spadmin/projects/spProjectUser/form?projectId='+$('#projectId').val();">项目成员添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spProjectUser" action="${ctx}/spadmin/projects/spProjectUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>用户名称：</label>
				<form:input path="userName" htmlEscape="false" maxlength="128" class="input-medium"/>
			</td>
			<td><label>项目名称：</label>
<!-- 				<select id="projectId" name="projectId" class="input-medium required" onfocus="this.blur();"> -->
				<input id="projectId" name="projectId" type="hidden" value="${spProjectUser.projectId}">
				<select id="projectIdStr" name="projectIdStr" class="input-medium required" disabled="disabled">
					<option value="">请选择</option>
					<c:forEach items="${projectList}" var="one">
						<option value="${one.id}" ${spProjectUser.projectId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input class="btn btn-primary" type="reset" value="清空"/>
			</td>
			</tr>
		</table>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名称</th>
				<th>项目名称</th>
				<!-- <th>用户头像</th> -->
				<th>性别</th>
				<th>年龄</th>
				<th>能力</th>
				<th>履历</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spProjectUser">
			<tr>
				<td title="${spProjectUser.headerImg}"><a href="${ctx}/spadmin/projects/spProjectUser/form?id=${spProjectUser.id}">
					${spProjectUser.userName}
				</a></td>
				<td>
					${spProjectUser.projectName}
				</td>
				<%-- <td>
					${spProjectUser.headerImg}
				</td> --%>
				<td>
					${spProjectUser.sex==0?"男":"女"}
				</td>
				<td>
					${spProjectUser.age}
				</td>
				<td>
					${spProjectUser.ability}
				</td>
				<td>
					${spProjectUser.antecedents}
				</td>
				<td>
					<fmt:formatDate value="${spProjectUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/projects/spProjectUser/form?id=${spProjectUser.id}">修改</a>
					<a href="${ctx}/spadmin/projects/spProjectUser/delete?id=${spProjectUser.id}" onclick="return confirmx('确认要删除该项目成员吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>