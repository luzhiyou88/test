<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>笔记管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/note/spUserNote/">笔记列表</a></li>
		<li><a href="${ctx}/spadmin/note/spUserNote/form">笔记添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spUserNote" action="${ctx}/spadmin/note/spUserNote/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="256" class="input-medium"/>
			</td>
			</tr>
		</table>
		<div class="searchFormButton">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input class="btn btn-primary" type="reset" value="清空"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>笔记内容</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spUserNote">
			<tr>
				<td><a href="${ctx}/spadmin/note/spUserNote/form?id=${spUserNote.id}">
					${spUserNote.title}
				</a></td>
				<td class="ct">
					${spUserNote.remarks}
				</td>
				<td>
    				<a href="${ctx}/spadmin/note/spUserNote/form?id=${spUserNote.id}">修改</a>
					<a href="${ctx}/spadmin/note/spUserNote/delete?id=${spUserNote.id}" onclick="return confirmx('确认要删除该笔记吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>