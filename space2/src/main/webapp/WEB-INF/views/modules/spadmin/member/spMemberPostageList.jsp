<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员资费管理管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/member/spMemberPostage/">会员资费管理列表</a></li>
		<li><a href="${ctx}/spadmin/member/spMemberPostage/form">会员资费管理添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spMemberPostage" action="${ctx}/spadmin/member/spMemberPostage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>资费名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>购买月份：</label>
				<form:input path="month" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员资费名称</th>
				<th>购买会员的月份</th>
				<th>会员资费价格</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spMemberPostage">
			<tr>
				<td><a href="${ctx}/spadmin/member/spMemberPostage/form?id=${spMemberPostage.id}">
					${spMemberPostage.name}
				</a></td>
				<td>
					${spMemberPostage.month}
				</td>
				<td>
					${spMemberPostage.postagePrice}
				</td>
				<td class="ct">
					${spMemberPostage.remarks}
				</td>
				<td>
    				<a href="${ctx}/spadmin/member/spMemberPostage/form?id=${spMemberPostage.id}">修改</a>
					<a href="${ctx}/spadmin/member/spMemberPostage/delete?id=${spMemberPostage.id}" onclick="return confirmx('确认要删除该会员资费管理吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>