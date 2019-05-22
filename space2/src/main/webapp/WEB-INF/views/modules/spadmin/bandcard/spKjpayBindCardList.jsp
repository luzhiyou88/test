<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>绑卡管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/bandcard/spKjpayBindCard/">绑卡列表</a></li>
		<li><a href="${ctx}/spadmin/bandcard/spKjpayBindCard/form">绑卡添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spKjpayBindCard" action="${ctx}/spadmin/bandcard/spKjpayBindCard/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>学校标识：</label>
				<form:input path="spaceId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			<td><label>用户标识：</label>
				<sys:treeselect id="user" name="user.id" value="${spKjpayBindCard.user.id}" labelName="user.name" labelValue="${spKjpayBindCard.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</td>
			<td><label>绑卡ID：</label>
				<form:input path="bindId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			<td><label>银行卡号：</label>
				<form:input path="bankCardNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</td>
			<td><label>银行编码：</label>
				<form:input path="bankCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			<td><label>预留的手机号：</label>
				<form:input path="mobilePhone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</td>
			<td><label>格式为yyyyMMddHHmmss：</label>
				<input name="bindValid" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spKjpayBindCard.bindValid}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</td>
			<td><label>删除标识：</label>
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>学校标识</th>
				<th>用户标识</th>
				<th>绑卡ID</th>
				<th>银行卡号</th>
				<th>银行编码</th>
				<th>预留的手机号</th>
				<th>格式为yyyyMMddHHmmss</th>
				<th>删除标识</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spKjpayBindCard">
			<tr>
				<td><a href="${ctx}/spadmin/bandcard/spKjpayBindCard/form?id=${spKjpayBindCard.id}">
					${spKjpayBindCard.id}
				</a></td>
				<td>
					${spKjpayBindCard.spaceId}
				</td>
				<td>
					${spKjpayBindCard.user.name}
				</td>
				<td>
					${spKjpayBindCard.bindId}
				</td>
				<td>
					${spKjpayBindCard.bankCardNo}
				</td>
				<td>
					${spKjpayBindCard.bankCode}
				</td>
				<td>
					${spKjpayBindCard.mobilePhone}
				</td>
				<td>
					<fmt:formatDate value="${spKjpayBindCard.bindValid}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(spKjpayBindCard.delFlag, 'del_flag', '')}
				</td>
				<td>
    				<a href="${ctx}/spadmin/bandcard/spKjpayBindCard/form?id=${spKjpayBindCard.id}">修改</a>
					<a href="${ctx}/spadmin/bandcard/spKjpayBindCard/delete?id=${spKjpayBindCard.id}" onclick="return confirmx('确认要删除该绑卡吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>