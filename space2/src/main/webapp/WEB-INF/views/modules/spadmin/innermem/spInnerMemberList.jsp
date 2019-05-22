<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员预留信息管理管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/innermem/spInnerMember/">预留会员列表</a></li>
		<li><a href="${ctx}/spadmin/innermem/spInnerMember/form">预留会员添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spInnerMember" action="${ctx}/spadmin/innermem/spInnerMember/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<label>用户姓名：</label>
			<form:input path="userName" htmlEscape="false" maxlength="128" class="input-medium"/>
			<label>专业名称：</label>
		    <form:select id="specialtyId" path="specialtyId" class="input-large">
                  <form:option value="">请选择</form:option>
                  <c:forEach items="${specials}" var="specialItem">
                     <form:option value="${specialItem.id}">${specialItem.name}</form:option>
                  </c:forEach>
	        </form:select>
			<label>发布状态 ：</label>
			<form:select id="publishState" path="publishState" class="input-small ">
                  <form:option value="">请选择</form:option>
		       	  <form:option value="1">待审核</form:option>
		          <form:option value="3">审核通过</form:option>
                  <form:option value="2">审核未通过</form:option>
	       </form:select>&nbsp;
		   <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		   <input class="btn btn-primary" type="reset" value="清空"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户姓名</th>
				<th>所属专业</th>
				<th>所在班级</th>
				<th>手机号</th>
				<th>发布状态 </th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spInnerMember">
			<tr>
				<td>
				    <a href="${ctx}/spadmin/innermem/spInnerMember/form?id=${spInnerMember.id}">
					${spInnerMember.userName}
				    </a>
				</td>
				<td>
					${spInnerMember.specialtyName}
				</td>
				<td>
					${spInnerMember.className}
				</td>
				<td>
					${spInnerMember.phoneNo}
				</td>
				<td>
					<c:if test="${spInnerMember.publishState == '1'}">待审核</c:if>
					<c:if test="${spInnerMember.publishState == '2'}">审核未通过</c:if>
					<c:if test="${spInnerMember.publishState == '3'}">审核通过</c:if>
				</td>
				<td>
					<fmt:formatDate value="${spInnerMember.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/innermem/spInnerMember/form?id=${spInnerMember.id}">修改</a>
					<a href="${ctx}/spadmin/innermem/spInnerMember/delete?id=${spInnerMember.id}" onclick="return confirmx('确认要删除该会员预留会员信息吗？', this.href)">删除</a>
				    <c:if test="${spInnerMember.publishState == '1' || spInnerMember.publishState == '2'}">
				       <a href="${ctx}/spadmin/innermem/spInnerMember/getApproveState?id=${spInnerMember.id}" >同步审核结果</a>
				    </c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>