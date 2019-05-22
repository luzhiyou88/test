<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分类管理管理</title>
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
			
			$('.flmc').each(function(k,v){
				var name = $(v).text().trim();
				if(name.length>20){
					$(v).text(name.substring(0,20)+'...');
				}
			})
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
		<li class="active"><a href="${ctx}/spadmin/category/spCategory/">分类管理列表</a></li>
		<li><a href="${ctx}/spadmin/category/spCategory/form">分类管理添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spCategory" action="${ctx}/spadmin/category/spCategory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分类名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分类名称</th>
				<th>备注</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spCategory">
			<tr>
				<td class="flmc"><a href="${ctx}/spadmin/category/spCategory/form?id=${spCategory.id}">
					${spCategory.name}
				</a></td>
				<td class="ct">
					${spCategory.remarks}
				</td>
				<td>
					<fmt:formatDate value="${spCategory.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/category/spCategory/form?id=${spCategory.id}">修改</a>
					<a href="${ctx}/spadmin/category/spCategory/delete?id=${spCategory.id}" onclick="return confirmx('确认要删除该分类管理吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>