<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>小组本地化管理</title>
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
		<li class="active"><a
			href="${ctx}/spadmin/group/spGroup/centerList">小组列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spGroup"
		action="${ctx}/spadmin/group/spGroup/centerList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>小组名称：</label> <form:input path="name"
					htmlEscape="false" maxlength="20" class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" />
				<input class="btn btn-primary" type="reset" value="清空"/>
				</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>小组名称</th>
				<th>小组类型</th>
				<th>发布/审核状态</th>
				<th>备注</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="spGroup">
				<tr>
					<td>${fn:substring(spGroup.name,0,20)}</td>

					<td>${fns:getDictLabel(spGroup.groupType, 'sp_group_type', '')}</td>
					<td>${fns:getDictLabel(spGroup.publishState, 'sp_publish_state', '')}
					</td>
					<td>${fn:substring(spGroup.remarks,0,51)}</td>
					<td><fmt:formatDate value="${spGroup.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><c:set var="flag" value="true"></c:set> <c:forEach
							items="${groupList}" var="sp">
							<c:if test="${ sp.id eq spGroup.id }">
							已本地化
							<c:set var="flag" value="false"></c:set>
							</c:if>
						</c:forEach> <c:if test="${flag}">
							<a href="${ctx}/spadmin/group/spGroup/getGroup?id=${spGroup.id}"
								onclick="return confirmx('确认要本地化该小组吗？', this.href)">本地化</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>