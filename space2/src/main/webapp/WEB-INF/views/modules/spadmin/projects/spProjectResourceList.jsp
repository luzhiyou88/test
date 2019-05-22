<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目资源管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var obj = $('.ct');
			$.each(obj,function(k,v){
				var con = v.innerHTML;
				if(con.length > 180){
					v.innerHTML=v.innerHTML.substring(0,180)+'...';
			        } 
			});
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
		<li class="active"><a href="${ctx}/spadmin/projects/spProjectResource/">项目资源列表</a></li>
		<li><a href="${ctx}/spadmin/projects/spProjectResource/form">项目资源添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spProjectResource" action="${ctx}/spadmin/projects/spProjectResource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</td>
			<td><label>所属项目：</label>
				<select name="projectId" class="input-medium">
					<option value="">请选择</option>
					<c:forEach items="${projectList}" var="one">
						<option value="${one.id}" ${spProjectResource.projectId==one.id?"selected":""}>${one.name }</option>
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
				<th>名称</th>
				<th>所属项目</th>
				<th>资源路径</th>
				<th>预览图</th>
				<th>备注</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spProjectResource">
			<tr>
				<td><a href="${ctx}/spadmin/projects/spProjectResource/form?id=${spProjectResource.id}">
					${spProjectResource.name}
				</a></td>
				<td>
					${spProjectResource.projectName}
				</td>
				<td title="${spProjectResource.baseUrl}">
					${(fn:length(spProjectResource.baseUrl)>20)?(fn:substring(spProjectResource.baseUrl,0,20)):(spProjectResource.baseUrl)}
				</td>
				<td title="${spProjectResource.thumbImg}">
					${(fn:length(spProjectResource.thumbImg)>20)?(fn:substring(spProjectResource.thumbImg,0,20)):(spProjectResource.thumbImg)}
				</td>
				<td class="ct">
					${spProjectResource.remarks}
				</td>
				<td>
					<fmt:formatDate value="${spProjectResource.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/projects/spProjectResource/form?id=${spProjectResource.id}">修改</a>
					<a href="${ctx}/spadmin/projects/spProjectResource/delete?id=${spProjectResource.id}" onclick="return confirmx('确认要删除该项目资源吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>