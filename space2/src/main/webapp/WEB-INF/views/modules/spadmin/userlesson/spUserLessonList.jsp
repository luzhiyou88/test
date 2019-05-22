<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户相关课程管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/userlesson/spUserLesson/">用户相关课程列表</a></li>
		<li><a href="${ctx}/spadmin/userlesson/spUserLesson/form">用户相关课程添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spUserLesson" action="${ctx}/spadmin/userlesson/spUserLesson/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			<td><label>用户id：</label>
				<sys:treeselect id="user" name="user.id" value="${spUserLesson.user.id}" labelName="user.name" labelValue="${spUserLesson.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</td>
			<td><label>课程id：</label>
				<form:input path="lessonId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			<td><label>预约状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dic_user_lesson_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td><label>备注：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="256" class="input-medium"/>
			</td>
			<td><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spUserLesson.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>用户id</th>
				<th>课程id</th>
				<th>预约状态</th>
				<th>备注</th>
				<th>创建时间</th>
				<th>删除标识</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spUserLesson">
			<tr>
				<td><a href="${ctx}/spadmin/userlesson/spUserLesson/form?id=${spUserLesson.id}">
					${spUserLesson.id}
				</a></td>
				<td>
					${spUserLesson.user.name}
				</td>
				<td>
					${spUserLesson.lessonId}
				</td>
				<td>
					${fns:getDictLabel(spUserLesson.state, 'dic_user_lesson_state', '')}
				</td>
				<td>
					${spUserLesson.remarks}
				</td>
				<td>
					<fmt:formatDate value="${spUserLesson.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(spUserLesson.delFlag, 'del_flag', '')}
				</td>
				<td>
    				<a href="${ctx}/spadmin/userlesson/spUserLesson/form?id=${spUserLesson.id}">修改</a>
					<a href="${ctx}/spadmin/userlesson/spUserLesson/delete?id=${spUserLesson.id}" onclick="return confirmx('确认要删除该用户相关课程吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>