<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员记录管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/memberlog/spMemberLog/">会员记录列表</a></li>
		<li><a href="${ctx}/spadmin/memberlog/spMemberLog/form">会员记录添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spMemberLog" action="${ctx}/spadmin/memberlog/spMemberLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			<td><label>户用id：</label>
				<sys:treeselect id="user" name="user.id" value="${spMemberLog.user.id}" labelName="user.name" labelValue="${spMemberLog.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</td>
			<td><label>买价格购：</label>
				<form:input path="memberPrice" htmlEscape="false" class="input-medium"/>
			</td>
			<td><label>买时间购：</label>
				<input name="buyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spMemberLog.buyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</td>
			<td><label>通开几个月：</label>
				<form:input path="month" htmlEscape="false" maxlength="11" class="input-medium"/>
			</td>
			<td><label>资费ID：</label>
				<form:input path="postageId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			<td><label>create_date：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spMemberLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>id</th>
				<th>户用id</th>
				<th>购买价格</th>
				<th>购买时间</th>
				<th>开通几个月</th>
				<th>资费ID</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spMemberLog">
			<tr>
				<td><a href="${ctx}/spadmin/memberlog/spMemberLog/form?id=${spMemberLog.id}">
					${spMemberLog.id}
				</a></td>
				<td>
					${spMemberLog.user.name}
				</td>
				<td>
					${spMemberLog.memberPrice}
				</td>
				<td>
					<fmt:formatDate value="${spMemberLog.buyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${spMemberLog.month}
				</td>
				<td>
					${spMemberLog.postageId}
				</td>
				<td>
    				<a href="${ctx}/spadmin/memberlog/spMemberLog/form?id=${spMemberLog.id}">修改</a>
					<a href="${ctx}/spadmin/memberlog/spMemberLog/delete?id=${spMemberLog.id}" onclick="return confirmx('确认要删除该会员记录吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>