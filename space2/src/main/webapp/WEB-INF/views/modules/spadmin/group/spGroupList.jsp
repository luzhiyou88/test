<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>小组管理</title>
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
	function deleteMess(id) {
		var name = $('#name').val();
		var groupType = $('#groupType').val();
		var publishState = $('#publishState').val();
		var url = ctx + '/spadmin/group/spGroup/delete?id=' + id
				+ '&groupType=' + groupType + '&name=' + name
				+ '&publishState=' + publishState;
		art.dialog({
			content : "确认要删除吗？",
			title : '删除公告',
			fixed : true,
			lock : true,
			width : 200,
			height : 100,
			id : 'confirm',
			okVal : '确认',
			ok : function() {
				window.location.href = url;
			},
			cancel : true
		});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/group/spGroup/">小组列表</a></li>
		<li><a href="${ctx}/spadmin/group/spGroup/form">小组添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spGroup"
		action="${ctx}/spadmin/group/spGroup/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<table>
			<tr>
				<td><label>小组名称：</label> <form:input path="name"
						htmlEscape="false" maxlength="20" class="input-medium" /></td>
				<td><label class="control-label">组类型：</label> <form:select
						path="groupType" class="input-xlarge required">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('sp_group_type')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select></td>
				<td><label class="control-label">审核状态：</label> <form:select
						path="publishState" class="input-xlarge required">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('publish_state')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select></td>
				<td class="btns"><input id="btnSubmit" class="btn btn-primary"
					type="submit" value="查询" /> <input class="btn btn-primary"
					type="reset" value="清空" /></td>

				<td class="clearfix"></td>
			</tr>
		</table>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>小组名称</th>
				<th>组长</th>
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
					<td><c:forEach items="${userList}" var="sp">
							<c:if test="${sp.id eq spGroup.leaderId}">
					     ${sp.name}
					     </c:if>
						</c:forEach></td>
					<td>${fns:getDictLabel(spGroup.groupType, 'sp_group_type', '')}</td>
					<td>${fns:getDictLabel(spGroup.publishState, 'sp_publish_state', '')}
					</td>
					<td>${fn:substring(spGroup.remarks,0,51)}</td>
					<td><fmt:formatDate value="${spGroup.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><c:if
							test="${spGroup.publishState eq '0' || spGroup.publishState eq '2' }">
							<a href="${ctx}/spadmin/group/spGroup/form?id=${spGroup.id}">修改</a>
						</c:if> <a href="#" onclick="deleteMess('${spGroup.id}');">删除</a> <c:if
							test="${spGroup.publishState eq '0' && spGroup.groupType eq '2'  }">
							<a href="${ctx}/spadmin/group/spGroup/send?id=${spGroup.id}"
								onclick="return confirmx('确认要发布该小组到平台吗？', this.href)">发布</a>
						</c:if> <c:if
							test="${spGroup.publishState eq '2' && spGroup.groupType eq '2'  }">
							<a href="${ctx}/spadmin/group/spGroup/send?id=${spGroup.id}"
								onclick="return confirmx('确认要发布该小组到平台吗？', this.href)">再次发布</a>
						</c:if> <c:if
							test="${spGroup.publishState eq '1' && spGroup.groupType eq '2'  }">
							<a href="${ctx}/spadmin/group/spGroup/check?id=${spGroup.id}">刷新审核状态</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>