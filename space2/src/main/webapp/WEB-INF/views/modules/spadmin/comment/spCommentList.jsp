<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论回复管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/comment/spComment/">评论回复列表</a></li>
		<li><a href="${ctx}/spadmin/comment/spComment/form">评论回复添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spComment" action="${ctx}/spadmin/comment/spComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>用户：</label>
				<sys:treeselect id="user" name="user.id" value="${spComment.user.id}" labelName="user.name" labelValue="${spComment.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
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
				<th>用户</th>
				<th>源资类型 0小组话题</th>
				<th>回复id</th>
				<th>回复内容</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spComment">
			<tr>
				<td><a href="${ctx}/spadmin/comment/spComment/form?id=${spComment.id}">
					${spComment.user.name}
				</a></td>
				<td>
				    <c:if test="${spComment.sourceType == '0'}">小组话题</c:if>
				</td>
				<td>
					${spComment.sourceId}
				</td>
				<td class="ct">
					${spComment.content}
				</td>
				<td>
    				<a href="${ctx}/spadmin/comment/spComment/form?id=${spComment.id}">修改</a>
					<a href="${ctx}/spadmin/comment/spComment/delete?id=${spComment.id}" onclick="return confirmx('确认要删除该评论回复吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>